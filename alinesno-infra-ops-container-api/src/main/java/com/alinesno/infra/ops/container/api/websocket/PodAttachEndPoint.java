package com.alinesno.infra.ops.container.api.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alinesno.infra.common.core.context.SpringContext;
import com.alinesno.infra.ops.container.k8s.container.ContainersV1;
import com.alinesno.infra.ops.container.k8s.ops.AttachOpsParams;
import com.alinesno.infra.ops.container.service.IEnvClusterService;
import com.google.common.base.Splitter;

import io.kubernetes.client.openapi.ApiClient;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Pod Attach Endpoint
 * 处理与 Kubernetes Pod 的 WebSocket 连接和交互
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@ServerEndpoint(value = "/ws/containers/terminal")
@Component
public class PodAttachEndPoint {

    private Logger logger = LoggerFactory.getLogger(PodAttachEndPoint.class);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(200);
    private static final ConcurrentHashMap<Session, Process> pool = new ConcurrentHashMap<>(100);

    /**
     * WebSocket 连接建立时触发的方法
     *
     * @param session WebSocket 会话
     * @throws Exception 异常
     */
    @OnOpen
    public void onOpen(Session session) throws Exception {
        String queryString = session.getQueryString();
        String decode = URLDecoder.decode(queryString, StandardCharsets.UTF_8);
        Map<String, String> query = Splitter.on("&").withKeyValueSeparator("=").split(decode);
        ApplicationContext ctx = SpringContext.getApplicationContext();

        IEnvClusterService envClusterService = ctx.getBean(IEnvClusterService.class);

        String namespace = query.get("namespace");
        String name = query.get("name");
        String container = query.get("container");

        String userid = null ;
        ApiClient apiClient = envClusterService.getApiClientByConfigByUserId(userid);

        AttachOpsParams attachOpsParams = new AttachOpsParams();

        attachOpsParams.setContainer(container).setPodName(name).setNamespaceName(namespace);
        logger.info("Attach to pod terminal: {}", attachOpsParams);

        ContainersV1 containersV1 = new ContainersV1();

        String[] command = {"/bin/sh"};
        Process attach = containersV1.attach(attachOpsParams, command, apiClient);
        pool.put(session, attach);
        executorService.execute(() -> {
            while (true) {
                try {
                    if (!session.isOpen()) {
                        return;
                    }
                    byte[] read = new byte[64];
                    InputStream inputStream = attach.getInputStream();
                    RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
                    inputStream.read(read);
                    logger.info("Read data from exec process");
                    basicRemote.sendBinary(ByteBuffer.wrap(read));
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                    return;
                }
            }
        });
    }

    /**
     * 接收客户端发送的消息
     *
     * @param message 客户端发送的消息
     * @param session WebSocket 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        executorService.execute(() -> {
            logger.info("Command: {}", message);
            Process attach = pool.get(session);
            OutputStream outputStream = attach.getOutputStream();
            try {
                if (attach.isAlive()) {
                    outputStream.write(message.getBytes());
                    outputStream.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * WebSocket 连接关闭时触发的方法
     *
     * @param session WebSocket 会话
     */
    @OnClose
    public void onClose(Session session) {
        Process process = pool.get(session);
        if (process != null) {
            process.destroy();
        }
    }

    /**
     * WebSocket 发生错误时触发的方法
     *
     * @param session WebSocket 会话
     * @param error   错误信息
     * @throws IOException IO 异常
     */
    @OnError
    public void onError(Session session, Throwable error) throws IOException {
        onClose(session);
        session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "Invalid connection"));
        error.printStackTrace();
    }
}

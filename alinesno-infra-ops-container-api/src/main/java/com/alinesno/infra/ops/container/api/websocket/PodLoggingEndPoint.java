package com.alinesno.infra.ops.container.api.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alinesno.infra.common.core.context.SpringContext;
import com.alinesno.infra.ops.container.k8s.ops.LogOpsParams;
import com.alinesno.infra.ops.container.k8s.pod.PodV1;
import com.alinesno.infra.ops.container.service.kubernetes.IEnvClusterService;
import com.google.common.base.Splitter;

import io.kubernetes.client.openapi.ApiClient;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Pod 日志通道
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@ServerEndpoint("/ws/pod/logging")
@Component
public class PodLoggingEndPoint {
    private Logger logger = LoggerFactory.getLogger(PodLoggingEndPoint.class);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * WebSocket 连接建立时触发的方法
     *
     * @param session WebSocket 会话对象
     * @throws Exception 当处理过程中出现异常时抛出异常
     */
    @OnOpen
    public void onOpen(Session session) throws Exception {
        executorService.execute(() -> {
            String queryString = session.getQueryString();
            String decode = URLDecoder.decode(queryString, StandardCharsets.UTF_8);
            Map<String, String> query = Splitter.on("&").withKeyValueSeparator("=").split(decode);

            ApplicationContext ctx = SpringContext.getApplicationContext();

            IEnvClusterService envClusterService = ctx.getBean(IEnvClusterService.class);
            String namespace = query.get("namespace");
            String name = query.get("name");
            String container = query.get("container");
            String token = query.get("token");

            String userid = null ;

            ApiClient apiClient = envClusterService.getApiClientByConfigByUserId(userid);
            PodV1 podV1 = new PodV1();
            LogOpsParams logOpsParams = new LogOpsParams();
            logOpsParams.setContainer(container).setName(name).setNamespaceName(namespace);
            logger.info("attach to pod log {}", logOpsParams);
            try (InputStream stream = podV1.log(logOpsParams, apiClient)) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
                while (session.isOpen()) {
                    String line = bufferedReader.readLine();
                    basicRemote.sendText(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 接收到 WebSocket 消息时触发的方法
     *
     * @param message 接收到的消息
     * @param session WebSocket 会话对象
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("ws: {}", message);
    }

    /**
     * WebSocket 连接关闭时触发的方法
     *
     * @param session WebSocket 会话对象
     */
    @OnClose
    public void onClose(Session session) {

    }

    /**
     * WebSocket 发生错误时触发的方法
     *
     * @param session WebSocket 会话对象
     * @param error   发生的错误
     * @throws IOException 当处理过程中出现 I/O 异常时抛出异常
     */
    @OnError
    public void onError(Session session, Throwable error) throws IOException {
        session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "链接不合法"));
        error.printStackTrace();
    }
}

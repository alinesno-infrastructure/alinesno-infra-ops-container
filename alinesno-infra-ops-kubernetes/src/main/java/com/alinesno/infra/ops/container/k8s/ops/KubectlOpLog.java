package com.alinesno.infra.ops.container.k8s.ops;

import java.io.InputStream;

import io.kubernetes.client.openapi.ApiClient;

/**
 * Kubectl操作日志接口
 * 用于执行kubectl日志操作
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public interface KubectlOpLog {
    /**
     * 获取日志
     *
     * @param params     日志操作参数
     * @param apiClient  Kubernetes API客户端
     * @return 日志输入流
     */
    InputStream log(final LogOpsParams params, ApiClient apiClient);
}

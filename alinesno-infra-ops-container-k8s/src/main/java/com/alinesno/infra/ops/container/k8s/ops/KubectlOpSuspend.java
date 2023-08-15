package com.alinesno.infra.ops.container.k8s.ops;

import io.kubernetes.client.openapi.ApiClient;

/**
 * Kubectl挂起操作接口
 * 用于执行kubectl挂起操作
 *
 * @author luoxiaodong
 * @version 1.0.0
 *
 * @param <T> 挂起操作的结果类型
 */
public interface KubectlOpSuspend<T> {
    /**
     * 执行挂起操作
     *
     * @param params     挂起操作参数
     * @param apiClient  Kubernetes API客户端
     * @return 挂起操作的结果
     */
    T suspend(final SuspendOpsParams params, ApiClient apiClient);
}

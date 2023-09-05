package com.alinesno.infra.ops.container.k8s.ops;

import io.kubernetes.client.openapi.ApiClient;

/**
 * Kubectl操作附加接口
 * 用于执行kubectl附加操作
 *
 *
 * @author luoxiaodong
 * @version 1.0.0
 * @param <R> 结果类型
 */
public interface KubectlOpAttach<R> {
    /**
     * 执行附加操作
     *
     * @param params     附加操作参数
     * @param command    命令
     * @param apiClient  Kubernetes API客户端
     * @return 附加操作的进程
     */
    Process attach(final AttachOpsParams params, String[] command, ApiClient apiClient);
}

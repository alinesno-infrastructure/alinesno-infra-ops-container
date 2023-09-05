package com.alinesno.infra.ops.container.k8s.ops;

import io.kubernetes.client.openapi.ApiClient;

/**
 * Kubectl操作删除接口
 * 用于执行kubectl删除操作
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public interface KubectlOpDelete {
    /**
     * 执行删除操作
     *
     * @param params 删除操作参数
     * @param client Kubernetes API客户端
     */
    void delete(DeleteOpsParams params, ApiClient client);
}

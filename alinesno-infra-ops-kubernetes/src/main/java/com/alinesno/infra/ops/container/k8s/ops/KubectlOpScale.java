package com.alinesno.infra.ops.container.k8s.ops;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.V1Scale;
import io.kubernetes.client.openapi.models.V1ScaleBuilder;

/**
 * Kubectl操作缩放接口
 * 用于执行kubectl缩放操作
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public interface KubectlOpScale {
    /**
     * 执行缩放操作
     *
     * @param params     缩放操作参数
     * @param apiClient  Kubernetes API客户端
     */
    void scale(final ScaleOpsParams params, ApiClient apiClient);

    /**
     * 预设缩放配置
     *
     * @param namespace  命名空间
     * @param podName    Pod名称
     * @param replicas   副本数量
     * @return 缩放配置
     */
    default V1Scale presetScale(String namespace, String podName, int replicas) {
        V1ScaleBuilder v1ScaleBuilder = new V1ScaleBuilder();
        return v1ScaleBuilder
                .withNewMetadata()
                .withName(podName)
                .withNamespace(namespace)
                .endMetadata()
                .withNewSpec()
                .withReplicas(replicas)
                .endSpec()
                .build();
    }
}

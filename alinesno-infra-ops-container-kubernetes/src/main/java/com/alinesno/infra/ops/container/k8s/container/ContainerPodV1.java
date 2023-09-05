package com.alinesno.infra.ops.container.k8s.container;

import io.kubernetes.client.openapi.models.V1Container;
import io.kubernetes.client.openapi.models.V1Pod;

/**
 * 容器和 Pod 的组合对象
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class ContainerPodV1 {
    private V1Container container;
    private V1Pod pod;

    /**
     * 获取容器对象
     *
     * @return 容器对象
     */
    public V1Container getContainer() {
        return container;
    }

    /**
     * 设置容器对象
     *
     * @param container 容器对象
     * @return 当前对象
     */
    public ContainerPodV1 setContainer(V1Container container) {
        this.container = container;
        return this;
    }

    /**
     * 获取 Pod 对象
     *
     * @return Pod 对象
     */
    public V1Pod getPod() {
        return pod;
    }

    /**
     * 设置 Pod 对象
     *
     * @param pod Pod 对象
     * @return 当前对象
     */
    public ContainerPodV1 setPod(V1Pod pod) {
        this.pod = pod;
        return this;
    }
}

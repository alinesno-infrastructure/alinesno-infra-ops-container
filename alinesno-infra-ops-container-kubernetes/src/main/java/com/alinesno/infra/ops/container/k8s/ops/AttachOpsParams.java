package com.alinesno.infra.ops.container.k8s.ops;

/**
 * 附加操作参数类
 * 封装了查询工作负载所需的参数信息
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class AttachOpsParams {
    /**
     * 命名空间
     */
    private String namespaceName;
    private String podName;
    private String container;

    /**
     * 获取命名空间名称
     * @return 命名空间名称
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * 设置命名空间名称
     * @param namespaceName 命名空间名称
     * @return 当前对象
     */
    public AttachOpsParams setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
        return this;
    }

    /**
     * 获取Pod名称
     * @return Pod名称
     */
    public String getPodName() {
        return podName;
    }

    /**
     * 设置Pod名称
     * @param podName Pod名称
     * @return 当前对象
     */
    public AttachOpsParams setPodName(String podName) {
        this.podName = podName;
        return this;
    }

    /**
     * 获取容器名称
     * @return 容器名称
     */
    public String getContainer() {
        return container;
    }

    /**
     * 设置容器名称
     * @param container 容器名称
     * @return 当前对象
     */
    public AttachOpsParams setContainer(String container) {
        this.container = container;
        return this;
    }
}

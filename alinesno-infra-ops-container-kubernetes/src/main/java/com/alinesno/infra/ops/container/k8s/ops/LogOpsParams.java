package com.alinesno.infra.ops.container.k8s.ops;

/**
 * 日志查询通用参数类
 * 用于指定查询日志的参数
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class LogOpsParams {
    /**
     * 命名空间
     */
    private String namespaceName;
    /**
     * 名称
     */
    private String name;
    /**
     * 容器
     */
    private String container;

    /**
     * 获取容器
     * @return 容器
     */
    public String getContainer() {
        return container;
    }

    /**
     * 设置容器
     * @param container 容器
     * @return 当前对象
     */
    public LogOpsParams setContainer(String container) {
        this.container = container;
        return this;
    }

    /**
     * 获取命名空间
     * @return 命名空间
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * 设置命名空间
     * @param namespaceName 命名空间
     * @return 当前对象
     */
    public LogOpsParams setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
        return this;
    }

    /**
     * 获取名称
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * @param name 名称
     * @return 当前对象
     */
    public LogOpsParams setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 将日志查询通用参数转换为字符串表示形式
     * @return 字符串表示形式
     */
    @Override
    public String toString() {
        return "LogOpsParams{" +
                "namespaceName='" + namespaceName + '\'' +
                ", name='" + name + '\'' +
                ", container='" + container + '\'' +
                '}';
    }
}

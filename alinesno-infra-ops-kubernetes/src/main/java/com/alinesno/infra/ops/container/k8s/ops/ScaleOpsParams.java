package com.alinesno.infra.ops.container.k8s.ops;

/**
 * 扩缩容操作参数
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class ScaleOpsParams {
    /**
     * 副本数量
     */
    private Integer replica;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 命名空间
     */
    private String namespace;
    
    /**
     * 操作类型
     */
    private String type;

    /**
     * 获取操作类型
     *
     * @return 操作类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置操作类型
     *
     * @param type 操作类型
     * @return ScaleOpsParams对象
     */
    public ScaleOpsParams setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 获取副本数量
     *
     * @return 副本数量
     */
    public Integer getReplica() {
        return replica;
    }

    /**
     * 设置副本数量
     *
     * @param replica 副本数量
     * @return ScaleOpsParams对象
     */
    public ScaleOpsParams setReplica(Integer replica) {
        this.replica = replica;
        return this;
    }

    /**
     * 获取名称
     *
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     * @return ScaleOpsParams对象
     */
    public ScaleOpsParams setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取命名空间
     *
     * @return 命名空间
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * 设置命名空间
     *
     * @param namespace 命名空间
     * @return ScaleOpsParams对象
     */
    public ScaleOpsParams setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }
}

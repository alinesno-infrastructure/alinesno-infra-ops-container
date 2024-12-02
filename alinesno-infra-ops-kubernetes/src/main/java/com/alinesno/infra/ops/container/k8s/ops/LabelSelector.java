package com.alinesno.infra.ops.container.k8s.ops;

/**
 * 标签选择器类
 * 用于指定标签选择器的键、值和操作符
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class LabelSelector {

    private String key; // 键
    private String value; // 值
    private String op; // 操作符

    /**
     * 获取键
     * @return 键
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置键
     * @param key 键
     * @return 当前对象
     */
    public LabelSelector setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * 获取值
     * @return 值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置值
     * @param value 值
     * @return 当前对象
     */
    public LabelSelector setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * 获取操作符
     * @return 操作符
     */
    public String getOp() {
        return op;
    }

    /**
     * 设置操作符
     * @param op 操作符
     * @return 当前对象
     */
    public LabelSelector setOp(String op) {
        this.op = op;
        return this;
    }

    /**
     * 将标签选择器转换为字符串表示形式
     * @return 字符串表示形式
     */
    @Override
    public String toString() {
        return key + op + value;
    }
}

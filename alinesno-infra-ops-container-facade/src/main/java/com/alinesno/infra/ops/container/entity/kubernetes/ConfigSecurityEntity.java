package com.alinesno.infra.ops.container.entity.kubernetes;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 功能名：密钥管理实体类
 * 数据表：config_security
 * 表备注：密钥管理
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("config_security")
public class ConfigSecurityEntity extends InfraBaseEntity {
    // 密钥名称
    @TableField("key_name")
    private String keyName;

    // 命名空间
    @TableField("namespace")
    private String namespace;

    // 类型
    @TableField("type")
    private String type;

    // 密钥数量
    @TableField("key_count")
    private Long keyCount;

    // 密钥键
    @TableField("key_name_values")
    private String keyNameValues;

    // 密钥值
    @TableField("key_value")
    private String keyValue;

    // getter and setter methods

    /**
     * 获取密钥名称
     */
    public String getKeyName() {
        return this.keyName;
    }

    /**
     * 设置密钥名称
     */
    public ConfigSecurityEntity setKeyName(String keyName) {
        this.keyName = keyName;
        return this;
    }

    /**
     * 获取命名空间
     */
    public String getNamespace() {
        return this.namespace;
    }

    /**
     * 设置命名空间
     */
    public ConfigSecurityEntity setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    /**
     * 获取类型
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置类型
     */
    public ConfigSecurityEntity setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 获取密钥数量
     */
    public Long getKeyCount() {
        return this.keyCount;
    }

    /**
     * 设置密钥数量
     */
    public ConfigSecurityEntity setKeyCount(Long keyCount) {
        this.keyCount = keyCount;
        return this;
    }

    /**
     * 获取密钥键
     */
    public String getKeyNameValues() {
        return this.keyNameValues;
    }

    /**
     * 设置密钥键
     */
    public ConfigSecurityEntity setKeyNameValues(String keyNameValues) {
        this.keyNameValues = keyNameValues;
        return this;
    }

    /**
     * 获取密钥值
     */
    public String getKeyValue() {
        return this.keyValue;
    }

    /**
     * 设置密钥值
     */
    public ConfigSecurityEntity setKeyValue(String keyValue) {
        this.keyValue = keyValue;
        return this;
    }
}

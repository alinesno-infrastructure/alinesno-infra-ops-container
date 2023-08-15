package com.alinesno.infra.ops.container.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 功能名：配置字典
 * 数据表：config_dict
 * 表备注：配置字典
 * 
 * @TableName("config_dict")
 * 
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("config_dict")
public class ConfigDictEntity extends InfraBaseEntity {

    /**
     * 配置名称
     */
    @TableField("config_name")
    private String configName;

    /**
     * 全名空间
     */
    @TableField("namespace")
    private String namespace;

    /**
     * 配置项目
     */
    @TableField("config_item")
    private String configItem;

    /**
     * 配置值
     */
    @TableField("config_value")
    private String configValue;

    /**
     * 配置 YAML 内容
     */
    @TableField("yaml_content")
    private String yamlContent;

    // getter and setter methods

    /**
     * 获取配置名称
     */
    public String getConfigName() {
        return this.configName;
    }

    /**
     * 设置配置名称
     */
    public ConfigDictEntity setConfigName(String configName) {
        this.configName = configName;
        return this;
    }

    /**
     * 获取全名空间
     */
    public String getNamespace() {
        return this.namespace;
    }

    /**
     * 设置全名空间
     */
    public ConfigDictEntity setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    /**
     * 获取配置项目
     */
    public String getConfigItem() {
        return this.configItem;
    }

    /**
     * 设置配置项目
     */
    public ConfigDictEntity setConfigItem(String configItem) {
        this.configItem = configItem;
        return this;
    }

    /**
     * 获取配置值
     */
    public String getConfigValue() {
        return this.configValue;
    }

    /**
     * 设置配置值
     */
    public ConfigDictEntity setConfigValue(String configValue) {
        this.configValue = configValue;
        return this;
    }

    /**
     * 获取配置 YAML 内容
     */
    public String getYamlContent() {
        return this.yamlContent;
    }

    /**
     * 设置配置 YAML 内容
     */
    public ConfigDictEntity setYamlContent(String yamlContent) {
        this.yamlContent = yamlContent;
        return this;
    }
}

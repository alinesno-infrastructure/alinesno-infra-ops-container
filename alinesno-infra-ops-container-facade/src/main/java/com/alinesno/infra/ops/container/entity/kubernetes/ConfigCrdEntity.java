package com.alinesno.infra.ops.container.entity.kubernetes;

import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 功能名：自定义资源
 * 数据表：config_crd
 * 表备注：自定义资源
 * 
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("config_crd")
public class ConfigCrdEntity extends InfraBaseEntity  {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 分组
     */
    @TableField("group_name")
    private String groupName;

    /**
     * Scope
     */
    @TableField("scope")
    private String scope;

    /**
     * 创建时间
     */
    @TableField("creation_time")
    private Date creationTime;

    // getter and setter methods

    /**
     * 获取名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置名称
     */
    public ConfigCrdEntity setName(String name) {
        this.name = name;
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
    public ConfigCrdEntity setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 获取分组
     */
    public String getGroupName() {
        return this.groupName;
    }

    /**
     * 设置分组
     */
    public ConfigCrdEntity setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 获取Scope
     */
    public String getScope() {
        return this.scope;
    }

    /**
     * 设置Scope
     */
    public ConfigCrdEntity setScope(String scope) {
        this.scope = scope;
        return this;
    }

    /**
     * 获取创建时间
     */
    public Date getCreationTime() {
        return this.creationTime;
    }

    /**
     * 设置创建时间
     */
    public ConfigCrdEntity setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
        return this;
    }
}

package com.alinesno.infra.ops.container.entity.kubernetes;

import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 功能名：kubernetes插件机制
 * 数据表：env_plugins
 * 表备注：kubernetes插件机制
 *
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("env_plugins")
public class EnvPluginsEntity extends InfraBaseEntity {
    // fields
    /**
     * 插件名称
     */
    @TableField("name")
    private String name;

    /**
     * 插件描述
     */
    @TableField("describe_plugin")
    private String describePlugin;

    /**
     * 发布时间
     */
    @TableField("publish_time")
    private Date publishTime;
    
    /**
     * 版本号
     */
    @TableField("version")
    private String version;
    
    /**
     * 状态
     */
    @TableField("status")
    private String status;
    
    /**
     * 是否安装
     */
    @TableField("is_installed")
    private Integer isInstalled;
    
    // getter and setter methods
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescribePlugin() {
        return this.describePlugin;
    }
    
    public void setDescribePlugin(String describePlugin) {
        this.describePlugin = describePlugin;
    }
    
    public Date getPublishTime() {
        return this.publishTime;
    }
    
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getIsInstalled() {
        return this.isInstalled;
    }
    
    public void setIsInstalled(Integer isInstalled) {
        this.isInstalled = isInstalled;
    }
}

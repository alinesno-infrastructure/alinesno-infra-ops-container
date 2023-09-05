package com.alinesno.infra.ops.container.entity.kubernetes;

import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 功能名：事件管理
 * 数据表：event_list
 * 表备注：事件管理
 *
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("events")
public class EventsEntity extends InfraBaseEntity {
    // fields
    /**
     * 来源组件
     */
    @TableField("component")
    private String component;

    /**
     * 来源主机
     */
    @TableField("host")
    private String host;

    /**
     * 服务
     */
    @TableField("service")
    private String service;

    /**
     * 命名空间
     */
    @TableField("namespace")
    private String namespace;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 原因
     */
    @TableField("reason")
    private String reason;

    /**
     * 消息
     */
    @TableField("message")
    private String message;

    /**
     * 次数
     */
    @TableField("count")
    private Long count;

    /**
     * 首次发生时间
     */
    @TableField("first_time")
    private Date firstTime;

    /**
     * 最近发生时间
     */
    @TableField("last_time")
    private Date lastTime;

    /**
     * 事件详细信息
     */
    @TableField("event_details")
    private String eventDetails;

    // getter and setter methods
    public String getComponent() {
        return this.component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Date getFirstTime() {
        return this.firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public Date getLastTime() {
        return this.lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getEventDetails() {
        return this.eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }
}

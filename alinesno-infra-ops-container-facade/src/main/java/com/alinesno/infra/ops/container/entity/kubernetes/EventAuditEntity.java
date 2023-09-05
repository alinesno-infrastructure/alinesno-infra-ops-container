package com.alinesno.infra.ops.container.entity.kubernetes;

import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 功能名：审计日志
 * 数据表：event_audit
 * 表备注：审计日志
 *
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("event_audit")
public class EventAuditEntity extends InfraBaseEntity {
    // fields
    /**
     * 触发时间
     */
    @TableField("trigger_time")
    private Date triggerTime;

    /**
     * 租户
     */
    @TableField("tenant")
    private String tenant;

    /**
     * 用户
     */
    @TableField("user")
    private String user;

    /**
     * 操作
     */
    @TableField("operation")
    private String operation;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 对象
     */
    @TableField("object")
    private String object;

    /**
     * 标签
     */
    @TableField("label")
    private String label;

    /**
     * ClientIP
     */
    @TableField("client_ip")
    private String clientIp;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

    // getter and setter methods
    public Date getTriggerTime() {
        return this.triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getTenant() {
        return this.tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObject() {
        return this.object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getClientIp() {
        return this.clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

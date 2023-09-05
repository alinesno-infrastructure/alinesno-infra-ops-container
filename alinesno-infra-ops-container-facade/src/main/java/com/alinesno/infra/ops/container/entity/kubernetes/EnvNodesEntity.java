package com.alinesno.infra.ops.container.entity.kubernetes;

import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 功能名：节点管理
 * 数据表：env_nodes
 * 表备注：节点管理
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("env_nodes")
public class EnvNodesEntity extends InfraBaseEntity {
    // fields
    /**
     * 主机名
     */
    @TableField("hostname")
    private String hostname;
    /**
     * IP地址
     */
    @TableField("ip_address")
    private String ipAddress;
    /**
     * 状态
     */
    @TableField("status")
    private String status;
    /**
     * 角色
     */
    @TableField("role")
    private String role;
    /**
     * 污点
     */
    @TableField("taint")
    private String taint;
    /**
     * 是否可调度
     */
    @TableField("schedulable")
    private Integer schedulable;
    /**
     * 负载值
     */
    @TableField("load_value")
    private Long loadValue;
    /**
     * 占用CPU百分比
     */
    @TableField("cpu_usage")
    private Long cpuUsage;
    /**
     * 使用内存
     */
    @TableField("memory_usage")
    private Long memoryUsage;
    /**
     * 容器组数量
     */
    @TableField("container_group_count")
    private Long containerGroupCount;
    /**
     * 加入时间
     */
    @TableField("join_time")
    private Date joinTime;
    
    // getter and setter methods
    public String getHostname() {
        return this.hostname;
    }
    
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    
    public String getIpAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getTaint() {
        return this.taint;
    }
    
    public void setTaint(String taint) {
        this.taint = taint;
    }
    
    public Integer getSchedulable() {
        return this.schedulable;
    }
    
    public void setSchedulable(Integer schedulable) {
        this.schedulable = schedulable;
    }
    
    public Long getLoadValue() {
        return this.loadValue;
    }
    
    public void setLoadValue(Long loadValue) {
        this.loadValue = loadValue;
    }
    
    public Long getCpuUsage() {
        return this.cpuUsage;
    }
    
    public void setCpuUsage(Long cpuUsage) {
        this.cpuUsage = cpuUsage;
    }
    
    public Long getMemoryUsage() {
        return this.memoryUsage;
    }
    
    public void setMemoryUsage(Long memoryUsage) {
        this.memoryUsage = memoryUsage;
    }
    
    public Long getContainerGroupCount() {
        return this.containerGroupCount;
    }
    
    public void setContainerGroupCount(Long containerGroupCount) {
        this.containerGroupCount = containerGroupCount;
    }
    
    public Date getJoinTime() {
        return this.joinTime;
    }
    
    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}

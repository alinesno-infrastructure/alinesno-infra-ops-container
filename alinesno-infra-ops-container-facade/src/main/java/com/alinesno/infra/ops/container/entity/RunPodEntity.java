package com.alinesno.infra.ops.container.entity;

import java.math.BigDecimal;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 容器组
 * 数据表：run_pod
 * 表备注：容器组
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("run_pod")
public class RunPodEntity extends InfraBaseEntity {

    /**
     * 容器组
     */
    @TableField("container_group_name")
    private String containerGroupName;

    /**
     * 命名空间
     */
    @TableField("namespace_name")
    private String namespaceName;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

    /**
     * 容器数
     */
    @TableField("container_count")
    private Long containerCount;

    /**
     * 重启次数
     */
    @TableField("restart_count")
    private Long restartCount;

    /**
     * CPU使用量
     */
    @TableField("cpu_usage")
    private BigDecimal cpuUsage;

    /**
     * 内存使用量
     */
    @TableField("memory_usage")
    private BigDecimal memoryUsage;

    /**
     * 运行时间（秒）
     */
    @TableField("age")
    private Long age;

    /**
     * Pod IP地址
     */
    @TableField("pod_ip_address")
    private String podIpAddress;

    /**
     * Node IP地址
     */
    @TableField("node_ip_address")
    private String nodeIpAddress;

    /**
     * CPU限制
     */
    @TableField("cpu_limit")
    private BigDecimal cpuLimit;

    /**
     * 内存限制
     */
    @TableField("memory_limit")
    private BigDecimal memoryLimit;

    /**
     * 探针状态
     */
    @TableField("probe_status")
    private String probeStatus;

    // getter and setter

    public String getContainerGroupName() {
        return this.containerGroupName;
    }

    public RunPodEntity setContainerGroupName(String arg) {
        this.containerGroupName = arg;
        return this;
    }

    public String getNamespaceName() {
        return this.namespaceName;
    }

    public RunPodEntity setNamespaceName(String arg) {
        this.namespaceName = arg;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public RunPodEntity setStatus(String arg) {
        this.status = arg;
        return this;
    }

    public Long getContainerCount() {
        return this.containerCount;
    }

    public RunPodEntity setContainerCount(Long arg) {
        this.containerCount = arg;
        return this;
    }

    public Long getRestartCount() {
        return this.restartCount;
    }

    public RunPodEntity setRestartCount(Long arg) {
        this.restartCount = arg;
        return this;
    }

    public BigDecimal getCpuUsage() {
        return this.cpuUsage;
    }

    public RunPodEntity setCpuUsage(BigDecimal arg) {
        this.cpuUsage = arg;
        return this;
    }

    public BigDecimal getMemoryUsage() {
        return this.memoryUsage;
    }

    public RunPodEntity setMemoryUsage(BigDecimal arg) {
        this.memoryUsage = arg;
        return this;
    }

    public Long getAge() {
        return this.age;
    }

    public RunPodEntity setAge(Long arg) {
        this.age = arg;
        return this;
    }

    public String getPodIpAddress() {
        return this.podIpAddress;
    }

    public RunPodEntity setPodIpAddress(String arg) {
        this.podIpAddress = arg;
        return this;
    }

    public String getNodeIpAddress() {
        return this.nodeIpAddress;
    }

    public RunPodEntity setNodeIpAddress(String arg) {
        this.nodeIpAddress = arg;
        return this;
    }

    public BigDecimal getCpuLimit() {
        return this.cpuLimit;
    }

    public RunPodEntity setCpuLimit(BigDecimal arg) {
        this.cpuLimit = arg;
        return this;
    }

    public BigDecimal getMemoryLimit() {
        return this.memoryLimit;
    }

    public RunPodEntity setMemoryLimit(BigDecimal arg) {
        this.memoryLimit = arg;
        return this;
    }

    public String getProbeStatus() {
        return this.probeStatus;
    }

    public RunPodEntity setProbeStatus(String arg) {
        this.probeStatus = arg;
        return this;
    }
}

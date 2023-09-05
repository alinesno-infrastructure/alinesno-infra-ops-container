package com.alinesno.infra.ops.container.entity.kubernetes;

import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 工作负载
 * 数据表：run_deployments
 * 表备注：工作负载
 *
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("run_deployments")
public class RunDeploymentsEntity extends InfraBaseEntity {

    /**
     * 名称
     */
    @TableField("deployment_name")
    private String deploymentName;

    /**
     * 类型（有状态服务、无状态服务、守护进程服务）
     */
    @TableField("type")
    private String type;

    /**
     * 命名空间
     */
    @TableField("namespace_name")
    private String namespaceName;

    /**
     * 运行状态
     */
    @TableField("status")
    private String status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 运行时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 容器IP地址
     */
    @TableField("container_ip_address")
    private String containerIpAddress;

    /**
     * node节点IP地址
     */
    @TableField("node_ip_address")
    private String nodeIpAddress;

    // getter and setter

    public String getDeploymentName() {
        return this.deploymentName;
    }

    public RunDeploymentsEntity setDeploymentName(String arg) {
        this.deploymentName = arg;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public RunDeploymentsEntity setType(String arg) {
        this.type = arg;
        return this;
    }

    public String getNamespaceName() {
        return this.namespaceName;
    }

    public RunDeploymentsEntity setNamespaceName(String arg) {
        this.namespaceName = arg;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public RunDeploymentsEntity setStatus(String arg) {
        this.status = arg;
        return this;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public RunDeploymentsEntity setCreateTime(Date arg) {
        this.createTime = arg;
        return this;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public RunDeploymentsEntity setStartTime(Date arg) {
        this.startTime = arg;
        return this;
    }

    public String getContainerIpAddress() {
        return this.containerIpAddress;
    }

    public RunDeploymentsEntity setContainerIpAddress(String arg) {
        this.containerIpAddress = arg;
        return this;
    }

    public String getNodeIpAddress() {
        return this.nodeIpAddress;
    }

    public RunDeploymentsEntity setNodeIpAddress(String arg) {
        this.nodeIpAddress = arg;
        return this;
    }
}

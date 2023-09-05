package com.alinesno.infra.ops.container.entity.kubernetes;

import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 服务
 * 数据表：run_services
 * 表备注：服务
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("run_services")
public class RunServicesEntity extends InfraBaseEntity {

    /**
     * 名称
     */
    @TableField("service_name")
    private String serviceName;

    /**
     * 命名空间
     */
    @TableField("namespace_name")
    private String namespaceName;

    /**
     * 集群IP
     */
    @TableField("cluster_ip_address")
    private String clusterIpAddress;

    /**
     * 服务类型(ClusterIP/NodePort)
     */
    @TableField("service_type")
    private String serviceType;

    /**
     * 访问信息
     */
    @TableField("access_info")
    private String accessInfo;

    /**
     * 创建时间
     */
    @TableField("creation_time")
    private Date creationTime;

    /**
     * 标签键值
     */
    @TableField("label_key_values")
    private String labelKeyValues;

    // getter and setter

    public String getServiceName() {
        return this.serviceName;
    }

    public RunServicesEntity setServiceName(String arg) {
        this.serviceName = arg;
        return this;
    }

    public String getNamespaceName() {
        return this.namespaceName;
    }

    public RunServicesEntity setNamespaceName(String arg) {
        this.namespaceName = arg;
        return this;
    }

    public String getClusterIpAddress() {
        return this.clusterIpAddress;
    }

    public RunServicesEntity setClusterIpAddress(String arg) {
        this.clusterIpAddress = arg;
        return this;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public RunServicesEntity setServiceType(String arg) {
        this.serviceType = arg;
        return this;
    }

    public String getAccessInfo() {
        return this.accessInfo;
    }

    public RunServicesEntity setAccessInfo(String arg) {
        this.accessInfo = arg;
        return this;
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public RunServicesEntity setCreationTime(Date arg) {
        this.creationTime = arg;
        return this;
    }

    public String getLabelKeyValues() {
        return this.labelKeyValues;
    }

    public RunServicesEntity setLabelKeyValues(String arg) {
        this.labelKeyValues = arg;
        return this;
    }
}

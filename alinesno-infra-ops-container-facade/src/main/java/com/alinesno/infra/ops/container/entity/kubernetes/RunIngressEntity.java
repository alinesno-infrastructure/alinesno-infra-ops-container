package com.alinesno.infra.ops.container.entity.kubernetes;

import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 路由
 * 数据表：run_ingress
 * 表备注：路由
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("run_ingress")
public class RunIngressEntity extends InfraBaseEntity {

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 命名空间
     */
    @TableField("namespace")
    private String namespace;

    /**
     * 访问地址
     */
    @TableField("address")
    private String address;

    /**
     * 创建时间
     */
    @TableField("creation_time")
    private Date creationTime;

    /**
     * 标签值
     */
    @TableField("label_values")
    private String labelValues;

    /**
     * 访问协议
     */
    @TableField("protocol")
    private String protocol;

    // getter and setter

    public String getName() {
        return this.name;
    }

    public RunIngressEntity setName(String arg) {
        this.name = arg;
        return this;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public RunIngressEntity setNamespace(String arg) {
        this.namespace = arg;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public RunIngressEntity setAddress(String arg) {
        this.address = arg;
        return this;
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public RunIngressEntity setCreationTime(Date arg) {
        this.creationTime = arg;
        return this;
    }

    public String getLabelValues() {
        return this.labelValues;
    }

    public RunIngressEntity setLabelValues(String arg) {
        this.labelValues = arg;
        return this;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public RunIngressEntity setProtocol(String arg) {
        this.protocol = arg;
        return this;
    }
}

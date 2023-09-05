package com.alinesno.infra.ops.container.entity.kubernetes;

import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 存储类
 * 数据表：storage_class
 * 表备注：存储类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("storage_class")
public class StorageClassEntity extends InfraBaseEntity {

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
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

    /**
     * 访问模式
     */
    @TableField("access_mode")
    private String accessMode;

    /**
     * 容量
     */
    @TableField("capacity")
    private String capacity;

    /**
     * 是否挂载
     */
    @TableField("is_mounted")
    private Integer isMounted;

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
     * 注释值
     */
    @TableField("annotation_value")
    private String annotationValue;

    // getter and setter

    public String getName() {
        return this.name;
    }

    public StorageClassEntity setName(String arg) {
        this.name = arg;
        return this;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public StorageClassEntity setNamespace(String arg) {
        this.namespace = arg;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public StorageClassEntity setType(String arg) {
        this.type = arg;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public StorageClassEntity setStatus(String arg) {
        this.status = arg;
        return this;
    }

    public String getAccessMode() {
        return this.accessMode;
    }

    public StorageClassEntity setAccessMode(String arg) {
        this.accessMode = arg;
        return this;
    }

    public String getCapacity() {
        return this.capacity;
    }

    public StorageClassEntity setCapacity(String arg) {
        this.capacity = arg;
        return this;
    }

    public Integer getIsMounted() {
        return this.isMounted;
    }

    public StorageClassEntity setIsMounted(Integer arg) {
        this.isMounted = arg;
        return this;
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public StorageClassEntity setCreationTime(Date arg) {
        this.creationTime = arg;
        return this;
    }

    public String getLabelValues() {
        return this.labelValues;
    }

    public StorageClassEntity setLabelValues(String arg) {
        this.labelValues = arg;
        return this;
    }

    public String getAnnotationValue() {
        return this.annotationValue;
    }

    public StorageClassEntity setAnnotationValue(String arg) {
        this.annotationValue = arg;
        return this;
    }
}

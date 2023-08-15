package com.alinesno.infra.ops.container.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 存储卷
 * 数据表：storage_class_volume
 * 表备注：存储卷
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("storage_class_volume")
public class StorageClassVolumeEntity extends InfraBaseEntity {

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 供应商
     */
    @TableField("provider")
    private String provider;

    /**
     * 是否默认存储
     */
    @TableField("is_default")
    private Integer isDefault;

    /**
     * 排序
     */
    @TableField("sort_order")
    private Long sortOrder;

    // getter and setter

    public String getIcon() {
        return this.icon;
    }

    public StorageClassVolumeEntity setIcon(String arg) {
        this.icon = arg;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public StorageClassVolumeEntity setName(String arg) {
        this.name = arg;
        return this;
    }

    public String getProvider() {
        return this.provider;
    }

    public StorageClassVolumeEntity setProvider(String arg) {
        this.provider = arg;
        return this;
    }

    public Integer getIsDefault() {
        return this.isDefault;
    }

    public StorageClassVolumeEntity setIsDefault(Integer arg) {
        this.isDefault = arg;
        return this;
    }

    public Long getSortOrder() {
        return this.sortOrder;
    }

    public StorageClassVolumeEntity setSortOrder(Long arg) {
        this.sortOrder = arg;
        return this;
    }

}

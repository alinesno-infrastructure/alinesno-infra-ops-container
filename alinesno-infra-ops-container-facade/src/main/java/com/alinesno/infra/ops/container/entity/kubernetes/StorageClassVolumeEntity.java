package com.alinesno.infra.ops.container.entity.kubernetes;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;

/**
 * 存储卷
 * 数据表：storage_class_volume
 * 表备注：存储卷
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("storage_class_volume")
@Data
public class StorageClassVolumeEntity extends InfraBaseEntity {

    /**
     * 图标
     */
    @TableField("icon")
	@ColumnType(length=255)
	@ColumnComment("图标")
    private String icon;

    /**
     * 名称
     */
    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("名称")
    private String name;

    /**
     * 供应商
     */
    @TableField("provider")
	@ColumnType(length=255)
	@ColumnComment("供应商")
    private String provider;

    /**
     * 是否默认存储
     */
    @TableField("is_default")
	@ColumnType(length=255)
	@ColumnComment("是否默认存储")
    private Integer isDefault;

    /**
     * 排序
     */
    @TableField("sort_order")
	@ColumnType(length=255)
	@ColumnComment("排序")
    private Long sortOrder;
}

package com.alinesno.infra.ops.container.entity.kubernetes;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
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
@Data
public class StorageClassEntity extends InfraBaseEntity {

    /**
     * 名称
     */
    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("名称")
    private String name;

    /**
     * 命名空间
     */
    @TableField("namespace")
	@ColumnType(length=255)
	@ColumnComment("命名空间")
    private String namespace;

    /**
     * 类型
     */
    @TableField("type")
	@ColumnType(length=255)
	@ColumnComment("类型")
    private String type;

    /**
     * 状态
     */
    @TableField("status")
	@ColumnType(length=255)
	@ColumnComment("状态")
    private String status;

    /**
     * 访问模式
     */
    @TableField("access_mode")
	@ColumnType(length=255)
	@ColumnComment("访问模式")
    private String accessMode;

    /**
     * 容量
     */
    @TableField("capacity")
	@ColumnType(length=255)
	@ColumnComment("容量")
    private String capacity;

    /**
     * 是否挂载
     */
    @TableField("is_mounted")
	@ColumnType(length=255)
	@ColumnComment("是否挂载")
    private Integer isMounted;

    /**
     * 创建时间
     */
    @TableField("creation_time")
	@ColumnType(length=255)
	@ColumnComment("创建时间")
    private Date creationTime;

    /**
     * 标签值
     */
    @TableField("label_values")
	@ColumnType(length=255)
	@ColumnComment("标签值")
    private String labelValues;

    /**
     * 注释值
     */
    @TableField("annotation_value")
	@ColumnType(length=255)
	@ColumnComment("注释值")
    private String annotationValue;
}

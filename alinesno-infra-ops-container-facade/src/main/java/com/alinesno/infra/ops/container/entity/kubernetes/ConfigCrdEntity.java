package com.alinesno.infra.ops.container.entity.kubernetes;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;

/**
 * 功能名：自定义资源
 * 数据表：config_crd
 * 表备注：自定义资源
 * 
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("config_crd")
@Data
public class ConfigCrdEntity extends InfraBaseEntity  {

    /**
     * 名称
     */
    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("名称")
    private String name;

    /**
     * 类型
     */
    @TableField("type")
	@ColumnType(length=255)
	@ColumnComment("类型")
    private String type;

    /**
     * 分组
     */
    @TableField("group_name")
	@ColumnType(length=255)
	@ColumnComment("分组")
    private String groupName;

    /**
     * Scope
     */
    @TableField("scope")
	@ColumnType(length=255)
	@ColumnComment("Scope")
    private String scope;

    /**
     * 创建时间
     */
    @TableField("creation_time")
	@ColumnType(length=255)
	@ColumnComment("创建时间")
    private Date creationTime;
}

package com.alinesno.infra.ops.container.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.EqualsAndHashCode;

/**
 * 功能名：kubernetes插件机制
 * 数据表：env_plugins
 * 表备注：kubernetes插件机制
 *
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("env_plugins")
@Data
public class EnvPluginsEntity extends InfraBaseEntity {
    // fields
    /**
     * 插件名称
     */
    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("插件名称")
    private String name;

    /**
     * 插件描述
     */
    @TableField("describe_plugin")
	@ColumnType(length=255)
	@ColumnComment("插件描述")
    private String describePlugin;

    /**
     * 发布时间
     */
    @TableField("publish_time")
	@ColumnType(length=255)
	@ColumnComment("发布时间")
    private Date publishTime;
    
    /**
     * 版本号
     */
    @TableField("version")
	@ColumnType(length=255)
	@ColumnComment("版本号")
    private String version;
    
    /**
     * 状态
     */
    @TableField("status")
	@ColumnType(length=255)
	@ColumnComment("状态")
    private String status;
    
    /**
     * 是否安装
     */
    @TableField("is_installed")
	@ColumnType(length=255)
	@ColumnComment("是否安装")
    private Integer isInstalled;
}

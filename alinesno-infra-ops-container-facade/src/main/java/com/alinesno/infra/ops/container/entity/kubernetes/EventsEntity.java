package com.alinesno.infra.ops.container.entity.kubernetes;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 功能名：事件管理
 * 数据表：event_list
 * 表备注：事件管理
 *
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("events")
@Data
public class EventsEntity extends InfraBaseEntity {
    // fields
    /**
     * 来源组件
     */
    @TableField("component")
	@ColumnType(length=255)
	@ColumnComment("来源组件")
    private String component;

    /**
     * 来源主机
     */
    @TableField("host")
	@ColumnType(length=255)
	@ColumnComment("来源主机")
    private String host;

    /**
     * 服务
     */
    @TableField("service")
	@ColumnType(length=255)
	@ColumnComment("服务")
    private String service;

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
     * 原因
     */
    @TableField("reason")
	@ColumnType(length=255)
	@ColumnComment("原因")
    private String reason;

    /**
     * 消息
     */
    @TableField("message")
	@ColumnType(length=255)
	@ColumnComment("消息")
    private String message;

    /**
     * 次数
     */
    @TableField("count")
	@ColumnType(length=255)
	@ColumnComment("次数")
    private Long count;

    /**
     * 首次发生时间
     */
    @TableField("first_time")
	@ColumnType(length=255)
	@ColumnComment("首次发生时间")
    private Date firstTime;

    /**
     * 最近发生时间
     */
    @TableField("last_time")
	@ColumnType(length=255)
	@ColumnComment("最近发生时间")
    private Date lastTime;

    /**
     * 事件详细信息
     */
    @TableField("event_details")
	@ColumnType(length=255)
	@ColumnComment("事件详细信息")
    private String eventDetails;
}

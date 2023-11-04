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
 * 功能名：审计日志
 * 数据表：event_audit
 * 表备注：审计日志
 *
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("event_audit")
@Data
public class EventAuditEntity extends InfraBaseEntity {
    // fields
    /**
     * 触发时间
     */
    @TableField("trigger_time")
	@ColumnType(length=255)
	@ColumnComment("触发时间")
    private Date triggerTime;

    /**
     * 租户
     */
    @TableField("tenant")
	@ColumnType(length=255)
	@ColumnComment("租户")
    private String tenant;

    /**
     * 用户
     */
    @TableField("user")
	@ColumnType(length=255)
	@ColumnComment("用户")
    private String user;

    /**
     * 操作
     */
    @TableField("operation")
	@ColumnType(length=255)
	@ColumnComment("操作")
    private String operation;

    /**
     * 类型
     */
    @TableField("type")
	@ColumnType(length=255)
	@ColumnComment("类型")
    private String type;

    /**
     * 对象
     */
    @TableField("object")
	@ColumnType(length=255)
	@ColumnComment("对象")
    private String object;

    /**
     * 标签
     */
    @TableField("label")
	@ColumnType(length=255)
	@ColumnComment("标签")
    private String label;

    /**
     * ClientIP
     */
    @TableField("client_ip")
	@ColumnType(length=255)
	@ColumnComment("ClientIP")
    private String clientIp;

    /**
     * 状态
     */
    @TableField("status")
	@ColumnType(length=255)
	@ColumnComment("状态")
    private String status;
}

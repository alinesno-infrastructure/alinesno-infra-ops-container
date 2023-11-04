package com.alinesno.infra.ops.container.entity.kubernetes;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import java.util.Date;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 功能名：节点管理
 * 数据表：env_nodes
 * 表备注：节点管理
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("env_nodes")
@Data
public class EnvNodesEntity extends InfraBaseEntity {
    // fields
    /**
     * 主机名
     */
    @TableField("hostname")
	@ColumnType(length=255)
	@ColumnComment("主机名")
    private String hostname;
    /**
     * IP地址
     */
    @TableField("ip_address")
	@ColumnType(length=255)
	@ColumnComment("IP地址")
    private String ipAddress;
    /**
     * 状态
     */
    @TableField("status")
	@ColumnType(length=255)
	@ColumnComment("状态")
    private String status;
    /**
     * 角色
     */
    @TableField("role")
	@ColumnType(length=255)
	@ColumnComment("角色")
    private String role;
    /**
     * 污点
     */
    @TableField("taint")
	@ColumnType(length=255)
	@ColumnComment("污点")
    private String taint;
    /**
     * 是否可调度
     */
    @TableField("schedulable")
	@ColumnType(length=255)
	@ColumnComment("是否可调度")
    private Integer schedulable;
    /**
     * 负载值
     */
    @TableField("load_value")
	@ColumnType(length=255)
	@ColumnComment("负载值")
    private Long loadValue;
    /**
     * 占用CPU百分比
     */
    @TableField("cpu_usage")
	@ColumnType(length=255)
	@ColumnComment("占用CPU百分比")
    private Long cpuUsage;
    /**
     * 使用内存
     */
    @TableField("memory_usage")
	@ColumnType(length=255)
	@ColumnComment("使用内存")
    private Long memoryUsage;
    /**
     * 容器组数量
     */
    @TableField("container_group_count")
	@ColumnType(length=255)
	@ColumnComment("容器组数量")
    private Long containerGroupCount;
    /**
     * 加入时间
     */
    @TableField("join_time")
	@ColumnType(length=255)
	@ColumnComment("加入时间")
    private Date joinTime;
}

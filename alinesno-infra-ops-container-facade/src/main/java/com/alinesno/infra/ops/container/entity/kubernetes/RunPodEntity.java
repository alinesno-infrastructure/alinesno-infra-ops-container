package com.alinesno.infra.ops.container.entity.kubernetes;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import java.math.BigDecimal;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;

/**
 * 容器组
 * 数据表：run_pod
 * 表备注：容器组
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("run_pod")
@Data
public class RunPodEntity extends InfraBaseEntity {

    /**
     * 容器组
     */
    @TableField("container_group_name")
	@ColumnType(length=255)
	@ColumnComment("容器组")
    private String containerGroupName;

    /**
     * 命名空间
     */
    @TableField("namespace_name")
	@ColumnType(length=255)
	@ColumnComment("命名空间")
    private String namespaceName;

    /**
     * 状态
     */
    @TableField("status")
	@ColumnType(length=255)
	@ColumnComment("状态")
    private String status;

    /**
     * 容器数
     */
    @TableField("container_count")
	@ColumnType(length=255)
	@ColumnComment("容器数")
    private Long containerCount;

    /**
     * 重启次数
     */
    @TableField("restart_count")
	@ColumnType(length=255)
	@ColumnComment("重启次数")
    private Long restartCount;

    /**
     * CPU使用量
     */
    @TableField("cpu_usage")
	@ColumnType(length=255)
	@ColumnComment("CPU使用量")
    private BigDecimal cpuUsage;

    /**
     * 内存使用量
     */
    @TableField("memory_usage")
	@ColumnType(length=255)
	@ColumnComment("内存使用量")
    private BigDecimal memoryUsage;

    /**
     * 运行时间（秒）
     */
    @TableField("age")
	@ColumnType(length=255)
	@ColumnComment("运行时间（秒）")
    private Long age;

    /**
     * Pod IP地址
     */
    @TableField("pod_ip_address")
	@ColumnType(length=255)
	@ColumnComment("Pod")
    private String podIpAddress;

    /**
     * Node IP地址
     */
    @TableField("node_ip_address")
	@ColumnType(length=255)
	@ColumnComment("Node")
    private String nodeIpAddress;

    /**
     * CPU限制
     */
    @TableField("cpu_limit")
	@ColumnType(length=255)
	@ColumnComment("CPU限制")
    private BigDecimal cpuLimit;

    /**
     * 内存限制
     */
    @TableField("memory_limit")
	@ColumnType(length=255)
	@ColumnComment("内存限制")
    private BigDecimal memoryLimit;

    /**
     * 探针状态
     */
    @TableField("probe_status")
	@ColumnType(length=255)
	@ColumnComment("探针状态")
    private String probeStatus;
}

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
 * 工作负载
 * 数据表：run_deployments
 * 表备注：工作负载
 *
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("run_deployments")
@Data
public class RunDeploymentsEntity extends InfraBaseEntity {

    /**
     * 名称
     */
    @TableField("deployment_name")
	@ColumnType(length=255)
	@ColumnComment("名称")
    private String deploymentName;

    /**
     * 类型（有状态服务、无状态服务、守护进程服务）
     */
    @TableField("type")
	@ColumnType(length=255)
	@ColumnComment("类型（有状态服务、无状态服务、守护进程服务）")
    private String type;

    /**
     * 命名空间
     */
    @TableField("namespace_name")
	@ColumnType(length=255)
	@ColumnComment("命名空间")
    private String namespaceName;

    /**
     * 运行状态
     */
    @TableField("status")
	@ColumnType(length=255)
	@ColumnComment("运行状态")
    private String status;

    /**
     * 创建时间
     */
    @TableField("create_time")
	@ColumnType(length=255)
	@ColumnComment("创建时间")
    private Date createTime;

    /**
     * 运行时间
     */
    @TableField("start_time")
	@ColumnType(length=255)
	@ColumnComment("运行时间")
    private Date startTime;

    /**
     * 容器IP地址
     */
    @TableField("container_ip_address")
	@ColumnType(length=255)
	@ColumnComment("容器IP地址")
    private String containerIpAddress;

    /**
     * node节点IP地址
     */
    @TableField("node_ip_address")
	@ColumnType(length=255)
	@ColumnComment("node节点IP地址")
    private String nodeIpAddress;
}

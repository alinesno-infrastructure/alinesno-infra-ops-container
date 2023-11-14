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
 * 服务
 * 数据表：run_services
 * 表备注：服务
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("run_services")
@Data
public class RunServicesEntity extends InfraBaseEntity {

    /**
     * 名称
     */
    @TableField("service_name")
	@ColumnType(length=255)
	@ColumnComment("名称")
    private String serviceName;

    /**
     * 命名空间
     */
    @TableField("namespace_name")
	@ColumnType(length=255)
	@ColumnComment("命名空间")
    private String namespaceName;

    /**
     * 集群IP
     */
    @TableField("cluster_ip_address")
	@ColumnType(length=255)
	@ColumnComment("集群IP")
    private String clusterIpAddress;

    /**
     * 服务类型(ClusterIP/NodePort)
     */
    @TableField("service_type")
	@ColumnType(length=255)
	@ColumnComment("服务类型(ClusterIP/NodePort)")
    private String serviceType;

    /**
     * 访问信息
     */
    @TableField("access_info")
	@ColumnType(length=255)
	@ColumnComment("访问信息")
    private String accessInfo;

    /**
     * 创建时间
     */
    @TableField("creation_time")
	@ColumnType(length=255)
	@ColumnComment("创建时间")
    private Date creationTime;

    /**
     * 标签键值
     */
    @TableField("label_key_values")
	@ColumnType(length=255)
	@ColumnComment("标签键值")
    private String labelKeyValues;
}

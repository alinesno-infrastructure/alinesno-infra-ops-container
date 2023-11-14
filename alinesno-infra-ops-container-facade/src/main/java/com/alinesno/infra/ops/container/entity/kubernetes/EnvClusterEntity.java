package com.alinesno.infra.ops.container.entity.kubernetes;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;

/**
 * 功能名：环境集群
 * 数据表：env_cluster
 * 表备注：环境集群
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("env_cluster")
@Data
public class EnvClusterEntity extends InfraBaseEntity {
    
    /**
     * 配置类型
     */
    @TableField("config_type")
	@ColumnType(length=255)
	@ColumnComment("配置类型")
    private String configType;
    
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
    @TableField("cluster_name")
	@ColumnType(length=255)
	@ColumnComment("名称")
    private String clusterName;
    
    /**
     * ApiServer地址
     */
    @TableField("api_server_url")
	@ColumnType(length=255)
	@ColumnComment("ApiServer地址")
    private String apiServerUrl;
    
    /**
     * 有效日期
     */
    @TableField("expiry_date")
	@ColumnType(length=255)
	@ColumnComment("有效日期")
    private String expiryDate;
    
    /**
     * 节点数
     */
    @TableField("node_count")
	@ColumnType(length=255)
	@ColumnComment("节点数")
    private String nodeCount;
    
    /**
     * 物理服务器信息
     */
    @TableField("physical_server_info")
	@ColumnType(length=255)
	@ColumnComment("物理服务器信息")
    private String physicalServerInfo;
    
    /**
     * 物理存储信息
     */
    @TableField("physical_storage_info")
	@ColumnType(length=255)
	@ColumnComment("物理存储信息")
    private String physicalStorageInfo;
    
    /**
     * 物理内存信息
     */
    @TableField("physical_memory_info")
	@ColumnType(length=255)
	@ColumnComment("物理内存信息")
    private String physicalMemoryInfo;
    
    /**
     * 密钥数
     */
    @TableField("key_count")
	@ColumnType(length=255)
	@ColumnComment("密钥数")
    private String keyCount;
    
    /**
     * 守护进程服务数
     */
    @TableField("daemon_service_count")
	@ColumnType(length=255)
	@ColumnComment("守护进程服务数")
    private String daemonServiceCount;
    
    /**
     * 无状态服务数
     */
    @TableField("stateless_service_count")
	@ColumnType(length=255)
	@ColumnComment("无状态服务数")
    private String statelessServiceCount;
    
    /**
     * 存储卷数
     */
    @TableField("volume_count")
	@ColumnType(length=255)
	@ColumnComment("存储卷数")
    private String volumeCount;
    
    /**
     * 服务数
     */
    @TableField("service_count")
	@ColumnType(length=255)
	@ColumnComment("服务数")
    private String serviceCount;
    
    /**
     * 容器组数量
     */
    @TableField("container_group_count")
	@ColumnType(length=255)
	@ColumnComment("容器组数量")
    private String containerGroupCount;
    
    /**
     * 状态
     */
    @TableField("status")
	@ColumnType(length=255)
	@ColumnComment("状态")
    private String status;

    /**
     * 是否当前集群(1当前集群|0非当前集群)
     */
    @TableField("current_cluster")
	@ColumnType(length=255)
	@ColumnComment("是否当前集群(1当前集群|0非当前集群)")
    private String currentCluster;

    /**
     * 集群配置
     */
    @TableField("kube_config")
	@ColumnType(length=255)
	@ColumnComment("集群配置")
    private String kubeConfig;
}

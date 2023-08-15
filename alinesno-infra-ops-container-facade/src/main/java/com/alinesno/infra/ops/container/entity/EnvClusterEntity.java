package com.alinesno.infra.ops.container.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 功能名：环境集群
 * 数据表：env_cluster
 * 表备注：环境集群
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("env_cluster")
public class EnvClusterEntity extends InfraBaseEntity {
    
    /**
     * 配置类型
     */
    @TableField("config_type")
    private String configType;
    
    /**
     * 图标
     */
    @TableField("icon")
    private String icon;
    
    /**
     * 名称
     */
    @TableField("cluster_name")
    private String clusterName;
    
    /**
     * ApiServer地址
     */
    @TableField("api_server_url")
    private String apiServerUrl;
    
    /**
     * 有效日期
     */
    @TableField("expiry_date")
    private String expiryDate;
    
    /**
     * 节点数
     */
    @TableField("node_count")
    private String nodeCount;
    
    /**
     * 物理服务器信息
     */
    @TableField("physical_server_info")
    private String physicalServerInfo;
    
    /**
     * 物理存储信息
     */
    @TableField("physical_storage_info")
    private String physicalStorageInfo;
    
    /**
     * 物理内存信息
     */
    @TableField("physical_memory_info")
    private String physicalMemoryInfo;
    
    /**
     * 密钥数
     */
    @TableField("key_count")
    private String keyCount;
    
    /**
     * 守护进程服务数
     */
    @TableField("daemon_service_count")
    private String daemonServiceCount;
    
    /**
     * 无状态服务数
     */
    @TableField("stateless_service_count")
    private String statelessServiceCount;
    
    /**
     * 存储卷数
     */
    @TableField("volume_count")
    private String volumeCount;
    
    /**
     * 服务数
     */
    @TableField("service_count")
    private String serviceCount;
    
    /**
     * 容器组数量
     */
    @TableField("container_group_count")
    private String containerGroupCount;
    
    /**
     * 状态
     */
    @TableField("status")
    private String status;

    /**
     * 是否当前集群(1当前集群|0非当前集群)
     */
    @TableField("current_cluster")
    private String currentCluster;

    /**
     * 集群配置
     */
    @TableField("kube_config")
    private String kubeConfig;

    // getter and setter methods

    // ...
}

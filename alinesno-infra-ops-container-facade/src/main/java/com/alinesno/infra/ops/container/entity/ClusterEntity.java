package com.alinesno.infra.ops.container.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
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
public class ClusterEntity extends InfraBaseEntity {
    
    /**
     * 配置类型
     */
    @TableField("config_type")
	@ColumnType(length=10)
	@ColumnComment("配置类型")
    private String configType;
    
    /**
     * 图标
     */
    @TableField("icon")
	@ColumnType(length=128)
	@ColumnComment("图标")
    private String icon;
    
    /**
     * 名称
     */
    @TableField("cluster_name")
	@ColumnType(length=64)
	@ColumnComment("名称")
    private String clusterName;
    
    /**
     * ApiServer地址
     */
    @TableField("api_server_url")
	@ColumnType(length=128)
	@ColumnComment("ApiServer地址")
    private String apiServerUrl;

    /**
     * 是否当前集群(1当前集群|0非当前集群)
     */
    @TableField("current_cluster")
	@ColumnType(length=1)
	@ColumnComment("是否当前集群(1当前集群|0非当前集群)")
    private String currentCluster;

    /**
     * 集群配置
     */
    @TableField("kube_config")
	@ColumnType(MySqlTypeConstant.LONGTEXT)
	@ColumnComment("集群配置")
    private String kubeConfig;
}


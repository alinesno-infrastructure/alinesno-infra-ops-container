package com.alinesno.infra.ops.container.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
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
@TableName("env_cluster_user")
@Data
public class ClusterUserEntity extends InfraBaseEntity {

    @TableField("user_id")
    @Column(comment = "用户ID" , length = 32, type = MySqlTypeConstant.BIGINT)
    private long userId;

    @TableField("cluster_id")
    @Column(comment = "集群ID" , length = 32, type = MySqlTypeConstant.BIGINT)
    private long clusterId;  // 集群ID

    @TableField("namespace")
    @Column(comment = "命名空间" , length = 128, type = MySqlTypeConstant.VARCHAR)
    private String namespace ; // 命名空间

}
    

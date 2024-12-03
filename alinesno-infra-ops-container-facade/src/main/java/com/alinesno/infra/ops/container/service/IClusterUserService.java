package com.alinesno.infra.ops.container.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.container.api.ClusterDto;
import com.alinesno.infra.ops.container.entity.ClusterUserEntity;

/**
 * 事件 Service接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
public interface IClusterUserService extends IBaseService<ClusterUserEntity> {

    /**
     * 根据用户ID获取集群信息
     *
     * @param userId
     * @return
     */
    ClusterDto getByUserId(long userId) ;

    /**
     * 设置用户集群
     * @param userId
     * @param clusterId
     */
    void setUserCluster(long userId , long clusterId  , String namespace) ;

}
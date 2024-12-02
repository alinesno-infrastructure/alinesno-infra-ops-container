package com.alinesno.infra.ops.container.service;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.api.ClusterDto;
import com.alinesno.infra.ops.container.entity.ClusterEntity;
import com.alinesno.infra.ops.container.mapper.ClusterMapper;
import io.kubernetes.client.openapi.ApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 环境集群Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Service
public class ClusterServiceImpl extends IBaseServiceImpl<ClusterEntity, ClusterMapper> implements IClusterService {

    @Override
    public ApiClient getApiClientByConfigByUserId(String userId) {
        return null;
    }

    @Override
    public void choiceCluster(String id, String userId) {

    }

    @Override
    public ClusterEntity getClusterRunStatus(String userId) {
        return null;
    }

    @Override
    public void saveCluster(ClusterDto clusterDTO) {

        ClusterEntity entity = new ClusterEntity();

        BeanUtils.copyProperties(clusterDTO, entity);

        entity.setConfigType(clusterDTO.getConfigType());
        entity.setClusterName(clusterDTO.getClusterName());
        entity.setApiServerUrl(clusterDTO.getApiServerUrl());
        entity.setKubeConfig(clusterDTO.getKubeConfig());

        save(entity);
      }

}

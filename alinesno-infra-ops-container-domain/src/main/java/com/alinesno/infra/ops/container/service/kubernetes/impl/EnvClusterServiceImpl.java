package com.alinesno.infra.ops.container.service.kubernetes.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.kubernetes.EnvClusterEntity;
import com.alinesno.infra.ops.container.mapper.kubernetes.EnvClusterMapper;
import com.alinesno.infra.ops.container.service.kubernetes.IEnvClusterService;
import io.kubernetes.client.openapi.ApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 环境集群Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class EnvClusterServiceImpl extends IBaseServiceImpl<EnvClusterEntity, EnvClusterMapper> implements IEnvClusterService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(EnvClusterServiceImpl.class);

    @Override
    public ApiClient getApiClientByConfigByUserId(String userId) {
        return null;
    }

    @Override
    public void choiceCluster(String id, String userId) {

    }

    @Override
    public EnvClusterEntity getClusterRunStatus(String userId) {
        return null;
    }
}

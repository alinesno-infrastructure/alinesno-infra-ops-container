package com.alinesno.infra.ops.container.service.kubernetes.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.kubernetes.EnvPluginsEntity;
import com.alinesno.infra.ops.container.mapper.kubernetes.EnvPluginsMapper;
import com.alinesno.infra.ops.container.service.kubernetes.IEnvPluginsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 环境插件 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class EnvPluginsServiceImpl extends IBaseServiceImpl<EnvPluginsEntity, EnvPluginsMapper> implements IEnvPluginsService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(EnvPluginsServiceImpl.class);
}

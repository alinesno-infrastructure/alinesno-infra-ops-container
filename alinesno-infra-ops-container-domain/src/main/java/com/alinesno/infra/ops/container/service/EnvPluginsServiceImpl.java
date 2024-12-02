package com.alinesno.infra.ops.container.service;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.EnvPluginsEntity;
import com.alinesno.infra.ops.container.mapper.EnvPluginsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 环境插件 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Service
public class EnvPluginsServiceImpl extends IBaseServiceImpl<EnvPluginsEntity, EnvPluginsMapper> implements IEnvPluginsService {

}

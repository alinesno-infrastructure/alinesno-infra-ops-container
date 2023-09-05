package com.alinesno.infra.ops.container.service.kubernetes.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.kubernetes.ConfigCrdEntity;
import com.alinesno.infra.ops.container.mapper.kubernetes.ConfigCrdMapper;
import com.alinesno.infra.ops.container.service.kubernetes.IConfigCrdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 自定义资源Service业务层处理
 * 
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class ConfigCrdServiceImpl extends IBaseServiceImpl<ConfigCrdEntity, ConfigCrdMapper> implements IConfigCrdService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(ConfigCrdServiceImpl.class);
}

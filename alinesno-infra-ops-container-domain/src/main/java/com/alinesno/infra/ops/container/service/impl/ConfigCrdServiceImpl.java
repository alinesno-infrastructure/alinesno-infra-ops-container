package com.alinesno.infra.ops.container.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.ConfigCrdEntity;
import com.alinesno.infra.ops.container.mapper.ConfigCrdMapper;
import com.alinesno.infra.ops.container.service.IConfigCrdService;
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

package com.alinesno.infra.ops.container.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.ConfigDictEntity;
import com.alinesno.infra.ops.container.mapper.ConfigDictMapper;
import com.alinesno.infra.ops.container.service.IConfigDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 配置字典Service业务层处理
 * 
 * @author luoxiaodong
 * @version 1.0.0
 */
@Service
public class ConfigDictServiceImpl extends IBaseServiceImpl<ConfigDictEntity, ConfigDictMapper> implements IConfigDictService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(ConfigDictServiceImpl.class);
}

package com.alinesno.infra.ops.container.service.kubernetes.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.kubernetes.ConfigDictEntity;
import com.alinesno.infra.ops.container.mapper.kubernetes.ConfigDictMapper;
import com.alinesno.infra.ops.container.service.kubernetes.IConfigDictService;
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

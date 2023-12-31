package com.alinesno.infra.ops.container.service.kubernetes.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.kubernetes.ConfigSecurityEntity;
import com.alinesno.infra.ops.container.mapper.kubernetes.ConfigSecurityMapper;
import com.alinesno.infra.ops.container.service.kubernetes.IConfigSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 密钥管理Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class ConfigSecurityServiceImpl extends IBaseServiceImpl<ConfigSecurityEntity, ConfigSecurityMapper> implements IConfigSecurityService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(ConfigSecurityServiceImpl.class);
}

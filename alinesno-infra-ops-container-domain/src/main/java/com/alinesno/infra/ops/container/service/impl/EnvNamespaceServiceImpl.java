package com.alinesno.infra.ops.container.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.EnvNamespaceEntity;
import com.alinesno.infra.ops.container.mapper.EnvNamespaceMapper;
import com.alinesno.infra.ops.container.service.IEnvNamespaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 命名空间Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class EnvNamespaceServiceImpl extends IBaseServiceImpl<EnvNamespaceEntity, EnvNamespaceMapper> implements IEnvNamespaceService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(EnvNamespaceServiceImpl.class);
}

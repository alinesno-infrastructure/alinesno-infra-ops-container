package com.alinesno.infra.ops.container.service.kubernetes.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.kubernetes.EnvNamespaceEntity;
import com.alinesno.infra.ops.container.mapper.kubernetes.EnvNamespaceMapper;
import com.alinesno.infra.ops.container.service.kubernetes.IEnvNamespaceService;
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

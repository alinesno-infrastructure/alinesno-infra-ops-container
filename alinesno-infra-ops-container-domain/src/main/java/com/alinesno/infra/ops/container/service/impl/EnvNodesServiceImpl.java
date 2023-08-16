package com.alinesno.infra.ops.container.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.EnvNodesEntity;
import com.alinesno.infra.ops.container.mapper.EnvNodesMapper;
import com.alinesno.infra.ops.container.service.IEnvNodesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 环境节点 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class EnvNodesServiceImpl extends IBaseServiceImpl<EnvNodesEntity, EnvNodesMapper> implements IEnvNodesService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(EnvNodesServiceImpl.class);
}

package com.alinesno.infra.ops.container.service.kubernetes.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.kubernetes.RunDeploymentsEntity;
import com.alinesno.infra.ops.container.mapper.kubernetes.RunDeploymentsMapper;
import com.alinesno.infra.ops.container.service.kubernetes.IRunDeploymentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 运行部署 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class RunDeploymentsServiceImpl extends IBaseServiceImpl<RunDeploymentsEntity, RunDeploymentsMapper> implements IRunDeploymentsService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(RunDeploymentsServiceImpl.class);
}

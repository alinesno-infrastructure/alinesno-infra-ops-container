package com.alinesno.infra.ops.container.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.RunServicesEntity;
import com.alinesno.infra.ops.container.mapper.RunServicesMapper;
import com.alinesno.infra.ops.container.service.IRunServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 运行服务 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class RunServicesServiceImpl extends IBaseServiceImpl<RunServicesEntity, RunServicesMapper> implements IRunServicesService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(RunServicesServiceImpl.class);
}

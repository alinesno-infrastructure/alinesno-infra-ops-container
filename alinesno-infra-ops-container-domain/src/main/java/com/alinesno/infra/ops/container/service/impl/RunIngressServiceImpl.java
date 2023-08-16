package com.alinesno.infra.ops.container.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.RunIngressEntity;
import com.alinesno.infra.ops.container.mapper.RunIngressMapper;
import com.alinesno.infra.ops.container.service.IRunIngressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 运行入口 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class RunIngressServiceImpl extends IBaseServiceImpl<RunIngressEntity, RunIngressMapper> implements IRunIngressService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(RunIngressServiceImpl.class);
}

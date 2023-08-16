package com.alinesno.infra.ops.container.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.RunPodEntity;
import com.alinesno.infra.ops.container.mapper.RunPodMapper;
import com.alinesno.infra.ops.container.service.IRunPodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 运行Pod Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class RunPodServiceImpl extends IBaseServiceImpl<RunPodEntity, RunPodMapper> implements IRunPodService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(RunPodServiceImpl.class);
}

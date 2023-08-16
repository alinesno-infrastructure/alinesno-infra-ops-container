package com.alinesno.infra.ops.container.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.ManagerRecordEntity;
import com.alinesno.infra.ops.container.mapper.ManagerRecordMapper;
import com.alinesno.infra.ops.container.service.IManagerRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 管理记录 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class ManagerRecordServiceImpl extends IBaseServiceImpl<ManagerRecordEntity, ManagerRecordMapper> implements IManagerRecordService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(ManagerRecordServiceImpl.class);
}

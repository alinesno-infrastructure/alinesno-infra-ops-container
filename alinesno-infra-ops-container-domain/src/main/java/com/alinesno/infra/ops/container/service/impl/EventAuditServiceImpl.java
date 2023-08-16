package com.alinesno.infra.ops.container.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.EventAuditEntity;
import com.alinesno.infra.ops.container.mapper.EventAuditMapper;
import com.alinesno.infra.ops.container.service.IEventAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 事件审计 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class EventAuditServiceImpl extends IBaseServiceImpl<EventAuditEntity, EventAuditMapper> implements IEventAuditService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(EventAuditServiceImpl.class);
}

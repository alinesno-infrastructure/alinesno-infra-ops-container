package com.alinesno.infra.ops.container.service;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.EventAuditEntity;
import com.alinesno.infra.ops.container.mapper.EventAuditMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 事件审计 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Service
public class EventAuditServiceImpl extends IBaseServiceImpl<EventAuditEntity, EventAuditMapper> implements IEventAuditService {

}

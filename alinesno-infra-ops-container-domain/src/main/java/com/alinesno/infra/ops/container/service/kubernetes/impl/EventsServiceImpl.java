package com.alinesno.infra.ops.container.service.kubernetes.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.kubernetes.EventsEntity;
import com.alinesno.infra.ops.container.mapper.kubernetes.EventsMapper;
import com.alinesno.infra.ops.container.service.kubernetes.IEventsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 事件 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class EventsServiceImpl extends IBaseServiceImpl<EventsEntity, EventsMapper> implements IEventsService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(EventsServiceImpl.class);
}

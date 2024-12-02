package com.alinesno.infra.ops.container.service;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.EventsEntity;
import com.alinesno.infra.ops.container.mapper.EventsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 事件 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Service
public class EventsServiceImpl extends IBaseServiceImpl<EventsEntity, EventsMapper> implements IEventsService {

}

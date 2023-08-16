package com.alinesno.infra.ops.container.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.StorageClassEntity;
import com.alinesno.infra.ops.container.mapper.StorageClassMapper;
import com.alinesno.infra.ops.container.service.IStorageClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 存储类 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class StorageClassServiceImpl extends IBaseServiceImpl<StorageClassEntity, StorageClassMapper> implements IStorageClassService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(StorageClassServiceImpl.class);
}

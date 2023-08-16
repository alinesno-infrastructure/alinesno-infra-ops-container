package com.alinesno.infra.ops.container.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.entity.StorageClassVolumeEntity;
import com.alinesno.infra.ops.container.mapper.StorageClassVolumeMapper;
import com.alinesno.infra.ops.container.service.IStorageClassVolumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 存储类卷 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class StorageClassVolumeServiceImpl extends IBaseServiceImpl<StorageClassVolumeEntity, StorageClassVolumeMapper> implements IStorageClassVolumeService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(StorageClassVolumeServiceImpl.class);
}

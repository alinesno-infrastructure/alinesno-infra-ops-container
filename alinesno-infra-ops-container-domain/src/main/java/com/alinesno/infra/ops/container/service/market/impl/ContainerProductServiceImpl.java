package com.alinesno.infra.ops.container.service.market.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.api.dto.ContainerProductDTO;
import com.alinesno.infra.ops.container.deploy.impl.ContainerDeployServiceImpl;
import com.alinesno.infra.ops.container.entity.market.ContainerProductEntity;
import com.alinesno.infra.ops.container.mapper.market.ContainerProductMapper;
import com.alinesno.infra.ops.container.service.market.IContainerProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 容器产品服务实现类
 */
@Service
public class ContainerProductServiceImpl extends IBaseServiceImpl<ContainerProductEntity, ContainerProductMapper> implements IContainerProductService {

    private static final Logger log = LoggerFactory.getLogger(ContainerDeployServiceImpl.class);

    /**
     * 创建容器产品
     *
     * @param containerProductDTO 容器产品DTO
     */
    @Override
    public void createProduct(ContainerProductDTO containerProductDTO) {
        ContainerProductEntity entity = new ContainerProductEntity();
        BeanUtils.copyProperties(containerProductDTO, entity);
        this.save(entity);
    }
}

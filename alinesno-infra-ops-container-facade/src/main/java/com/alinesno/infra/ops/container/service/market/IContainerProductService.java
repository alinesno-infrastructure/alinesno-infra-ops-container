package com.alinesno.infra.ops.container.service.market;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.container.api.dto.ContainerProductDTO;
import com.alinesno.infra.ops.container.entity.market.ContainerProductEntity;

/**
 * 容器产品服务接口
 */
public interface IContainerProductService extends IBaseService<ContainerProductEntity> {
    /**
     * 创建容器产品
     *
     * @param containerProductDTO 容器产品DTO
     */
    void createProduct(ContainerProductDTO containerProductDTO);
}

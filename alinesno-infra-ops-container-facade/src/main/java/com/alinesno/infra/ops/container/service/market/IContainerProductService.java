package com.alinesno.infra.ops.container.service.market;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.container.api.dto.ContainerProductDTO;
import com.alinesno.infra.ops.container.entity.market.CommentEntity;
import com.alinesno.infra.ops.container.entity.market.ContainerProductEntity;

public interface IContainerProductService extends IBaseService<ContainerProductEntity> {
    void createProduct(ContainerProductDTO containerProductDTO);
}

package com.alinesno.infra.ops.container.service;

import java.util.List;

import com.alinesno.infra.ops.container.entity.ContentPostTypeEntity;
import com.alinesno.infra.common.facade.services.IBaseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author WeiXiaoJin
 * @version 1.0.0
 */

public interface IContentPostTypeService extends IBaseService<ContentPostTypeEntity> {

	List<ContentPostTypeEntity> findAllWithApplication(QueryWrapper<ContentPostTypeEntity> restWrapper);

}

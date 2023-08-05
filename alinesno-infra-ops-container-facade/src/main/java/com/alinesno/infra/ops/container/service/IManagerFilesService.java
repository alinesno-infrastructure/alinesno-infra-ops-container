package com.alinesno.infra.ops.container.service;

import com.alinesno.infra.ops.container.entity.ManagerFilesEntity;
import com.alinesno.infra.common.facade.services.IBaseService;

public interface IManagerFilesService extends IBaseService<ManagerFilesEntity> {
	
	ManagerFilesEntity saveOne(ManagerFilesEntity entity);
 

}

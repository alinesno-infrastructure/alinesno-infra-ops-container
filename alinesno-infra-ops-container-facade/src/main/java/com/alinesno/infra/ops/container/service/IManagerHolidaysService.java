package com.alinesno.infra.ops.container.service;

import com.alinesno.infra.ops.container.entity.ManagerHolidaysEntity;
import com.alinesno.infra.common.facade.services.IBaseService;

/**
 * <p>
 * 节假日设置 服务类
 * </p>
 *
 * @author WeiXiaoJin
 * @version 1.0.0
 */

public interface IManagerHolidaysService extends IBaseService<ManagerHolidaysEntity> {
	boolean CheckHolidays(String holidays, String operatorId);
}

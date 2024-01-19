package com.alinesno.infra.ops.container.api;

import com.alinesno.infra.common.facade.mapper.entity.BaseEntity;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.facade.response.HttpStatus;
import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.common.web.adapter.rest.BaseController;
import com.alinesno.infra.ops.container.k8s.KuberanetesPageBean;
import com.alinesno.infra.ops.container.service.kubernetes.IEnvClusterService;
import io.kubernetes.client.openapi.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Kubernetes适配器
 * 
 * 该类继承自`BaseRestAdapter`，用于与Kubernetes进行交互的适配器。
 *
 * @author luoxiaodong
 * @version 1.0.0
 *
 * @param <T> 实体类型
 * @param <S> 服务类型
 */
public class KubernatesAdapter<T extends BaseEntity, S extends IBaseService<T>> extends BaseController<T, S> {

	@Autowired
	private IEnvClusterService envClusterService;

	/**
	 * 根据用户获取配置的ApiClient
	 * 
	 * @param userId 用户ID
	 * @return ApiClient对象
	 */
	public ApiClient getApiClientByConfigByUserId(String userId) {
		return envClusterService.getApiClientByConfigByUserId(userId);
	}

	/**
	 * 转换为Kubernetes分页数据
	 * 
	 * @param kubePage Kubernetes分页数据
	 * @return 转换后的TableDataInfo对象
	 */
	public <B> TableDataInfo toKubernatesPage(KuberanetesPageBean<B> kubePage) {

		TableDataInfo dInfo = new TableDataInfo();
		dInfo.setCode(HttpStatus.SUCCESS);
		dInfo.setMsg("查询成功");
		dInfo.setRows(kubePage.getRecords());
		dInfo.setTotal(kubePage.getTotal());

		return dInfo;
	}

}

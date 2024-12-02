package com.alinesno.infra.ops.container.api;

import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.facade.response.HttpStatus;
import com.alinesno.infra.common.web.adapter.rest.SuperController;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import com.alinesno.infra.ops.container.service.IClusterService;
import io.kubernetes.client.openapi.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Kubernetes适配器
 * 该类继承自`BaseRestAdapter`，用于与Kubernetes进行交互的适配器。
 *
 * @author luoxiaodong
 * @version 1.0.0
 *
 */
public class KubernetesAdapter extends SuperController {

	@Autowired
	private IClusterService envClusterService;

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
	public <B> TableDataInfo toKubernetesPage(KubernetesPageBean<B> kubePage) {

		TableDataInfo dInfo = new TableDataInfo();
		dInfo.setCode(HttpStatus.SUCCESS);
		dInfo.setMsg("查询成功");
		dInfo.setRows(kubePage.getRecords());
		dInfo.setTotal(kubePage.getTotal());

		return dInfo;
	}

}

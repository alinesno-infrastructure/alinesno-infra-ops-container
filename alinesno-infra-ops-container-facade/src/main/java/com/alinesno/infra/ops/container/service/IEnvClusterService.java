package com.alinesno.infra.ops.container.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.container.entity.EnvClusterEntity;
import io.kubernetes.client.openapi.ApiClient;

/**
 * 环境集群 Service 接口
 * 定义了与环境集群相关的业务方法
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public interface IEnvClusterService extends IBaseService<EnvClusterEntity> {

	/**
	 * 根据用户 ID 获取 ApiClient
	 *
	 * @param userId 用户 ID
	 * @return ApiClient 对象
	 */
	public ApiClient getApiClientByConfigByUserId(String userId);

	/**
	 * 选择当前集群
	 *
	 * @param id     集群 ID
	 * @param userId 用户 ID
	 */
	public void choiceCluster(String id, String userId);

	/**
	 * 获取当前集群的运行节点
	 *
	 * @param userId 用户 ID
	 * @return 当前集群的运行节点信息
	 */
	public EnvClusterEntity getClusterRunStatus(String userId);
}

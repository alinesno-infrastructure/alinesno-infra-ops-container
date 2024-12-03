package com.alinesno.infra.ops.container.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.container.api.ClusterDto;
import com.alinesno.infra.ops.container.entity.ClusterEntity;
import io.kubernetes.client.openapi.ApiClient;

/**
 * 环境集群 Service 接口
 * 定义了与环境集群相关的业务方法
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public interface IClusterService extends IBaseService<ClusterEntity> {

	/**
	 * 根据用户 ID 获取 ApiClient
	 *
	 * @param userId 用户 ID
	 * @return ApiClient 对象
	 */
	ApiClient getApiClientByConfigByUserId(String userId);

	/**
	 * 选择当前集群
	 *
	 * @param id     集群 ID
	 * @param userId 用户 ID
	 */
	void choiceCluster(String id, String userId);

	/**
	 * 获取当前集群的运行节点
	 *
	 * @param userId 用户 ID
	 * @return 当前集群的运行节点信息
	 */
	ClusterEntity getClusterRunStatus(String userId);

	/**
	 * 保存集群
	 * @param clusterDTO
	 */
	void saveCluster(ClusterDto clusterDTO);

	/**
	 * 获取到用户当前设置的集群信息
	 * @param currentUserId
	 * @return
	 */
    ClusterEntity getCluster(long currentUserId);

}

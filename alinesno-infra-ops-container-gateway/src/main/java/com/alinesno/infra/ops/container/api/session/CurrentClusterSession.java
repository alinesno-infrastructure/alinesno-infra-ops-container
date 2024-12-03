package com.alinesno.infra.ops.container.api.session;

import com.alinesno.infra.common.core.context.SpringContext;
import com.alinesno.infra.common.web.adapter.login.account.CurrentAccountJwt;
import com.alinesno.infra.ops.container.api.ClusterDto;
import com.alinesno.infra.ops.container.service.IClusterUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 当前集群会话
 * 
 * 该类用于获取当前用户的集群配置信息。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Slf4j
@Component
public class CurrentClusterSession {

	private static final String NULL_APPLICATION_ID = "null";
	private static final String CURRENT_CLUSTER = "1";

	/**
	 * 获取当前用户的集群配置信息
	 * 
	 * @return 当前用户的集群配置信息
	 */
	public static ClusterDto get() {

		long currentUserId = CurrentAccountJwt.getUserId() ;

		IClusterUserService managerProjectService = SpringContext.getBean(IClusterUserService.class);

        return managerProjectService.getByUserId(currentUserId);
	}

	/**
	 * 获取当前集群ID
	 * 
	 * @return 当前集群ID
	 */
	public static long getClusterId() {
		return get().getId() ;
	}

	/**
	 * 获取当前配置的Kubernetes信息
	 * 
	 * @return 当前配置的Kubernetes信息
	 */
	public static String getKubeConfig() {
		return get().getKubeConfig();
	}

}

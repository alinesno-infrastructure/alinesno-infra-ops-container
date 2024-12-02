package com.alinesno.infra.ops.container.api.session;

import com.alinesno.infra.ops.container.entity.ClusterEntity;
import com.alinesno.infra.ops.container.service.IClusterService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 当前集群会话
 * 
 * 该类用于获取当前用户的集群配置信息。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Component
public class CurrentClusterSession {

	private static final Logger log = LoggerFactory.getLogger(CurrentClusterSession.class);

	private static final String NULL_APPLICATION_ID = "null";
	private static final String CURRENT_CLUSTER = "1";

	@Autowired
	private IClusterService applicationService;

	/**
	 * 获取当前用户的集群配置信息
	 * 
	 * @param request HTTP请求对象
	 * @return 当前用户的集群配置信息
	 */
	public ClusterEntity get(HttpServletRequest request) {
		try {
			return this.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前用户的集群配置信息
	 * 
	 * @return 当前用户的集群配置信息
	 */
	public ClusterEntity get() {
		return null ;
	}

	/**
	 * 获取当前集群ID
	 * 
	 * @return 当前集群ID
	 */
	public String getClusterId() {
		return null;
	}

	/**
	 * 获取当前配置的Kubernetes信息
	 * 
	 * @return 当前配置的Kubernetes信息
	 */
	public static String getKubeConfig() {

		String kubeConfig = null;

		return kubeConfig;
	}

}

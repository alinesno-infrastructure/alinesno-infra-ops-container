package com.alinesno.infra.ops.container.api;

import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.facade.response.HttpStatus;
import com.alinesno.infra.common.web.adapter.rest.SuperController;
import com.alinesno.infra.ops.container.api.session.CurrentClusterSession;
import com.alinesno.infra.ops.container.k8s.KubernetesApiClient;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import com.alinesno.infra.ops.container.service.IClusterService;
import io.kubernetes.client.openapi.ApiClient;
import lombok.SneakyThrows;
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

	@SneakyThrows
	public ApiClient getApiClient() {
		ClusterDto cluster = CurrentClusterSession.get() ;

		if(cluster.getConfigType().equals("token")){
			return KubernetesApiClient.getApiClientByToken(cluster.getApiServerUrl() , cluster.getKubeConfig()) ;
		}else{
			return KubernetesApiClient.getApiClientByConfig(cluster.getKubeConfig()) ;
		}
	}

	/**
	 * 获取当前集群的命名空间。
	 * @return
	 */
	public String getNamespace() {
		String namespace =CurrentClusterSession.get().getNamespace();
		if(namespace == null){
			namespace = "default" ;
		}
		return namespace;
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

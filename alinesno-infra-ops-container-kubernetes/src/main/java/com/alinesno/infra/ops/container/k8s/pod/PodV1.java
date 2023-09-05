package com.alinesno.infra.ops.container.k8s.pod;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import com.alinesno.infra.ops.container.k8s.KubernetesApiParent;
import com.alinesno.infra.ops.container.k8s.ops.GetOpsParams;
import com.alinesno.infra.ops.container.k8s.ops.KubectlOpGet;
import com.alinesno.infra.ops.container.k8s.ops.KubectlOpLog;
import com.alinesno.infra.ops.container.k8s.ops.LogOpsParams;
import io.kubernetes.client.PodLogs;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import org.apache.commons.collections4.CollectionUtils;

/**
 * PodV1类
 *
 * 该类提供了操作Pod的相关方法，包括获取Pod日志、获取Pod信息等。
 * 继承自KubernatesApiParent类，实现了KubectlOpLog和KubectlOpGet接口。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class PodV1 extends KubernetesApiParent implements KubectlOpLog, KubectlOpGet<V1Pod> {

	/**
	 * 获取Pod的日志流
	 *
	 * @param params     日志操作参数
	 * @param apiClient  API客户端
	 * @return Pod日志的输入流
	 * @throws RuntimeException 如果发生IO异常或API调用异常
	 */
	@Override
	public InputStream log(LogOpsParams params, ApiClient apiClient) throws RuntimeException {
		PodLogs logs = new PodLogs(apiClient);
		try {
			return logs.streamNamespacedPodLog(params.getNamespaceName(), params.getName(), params.getContainer());
		} catch (IOException | ApiException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取Pod列表
	 *
	 * @param params     获取操作参数
	 * @param apiClient  API客户端
	 * @return Pod列表
	 * @throws RuntimeException 如果发生API调用异常
	 */
	@Override
	public List<V1Pod> get(GetOpsParams params, ApiClient apiClient) throws RuntimeException {
		// 创建CoreV1Api实例
		CoreV1Api api = new CoreV1Api(apiClient);
		// 获取指定Namespace和Deployment下的所有Pod
		String namespace = params.getNamespaceName();
		String labelSelector = null;

		if (CollectionUtils.isNotEmpty(params.getLabelSelector())) {
			labelSelector = null ; // params.getLabelSelector().stream().map(LabelSelector::toString).collect(Collectors.joining(","));
		}
		String fieldSelector = null;

		if (CollectionUtils.isNotEmpty(params.getFieldSelector())) {
			fieldSelector = null ; //  params.getFieldSelector().stream().map(LabelSelector::toString).collect(Collectors.joining(","));
		}
		try {
			return api.listNamespacedPod(namespace, null, null, null, fieldSelector, labelSelector, null, null, null, null, false)
					.getItems();
		} catch (ApiException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取单个Pod
	 *
	 * @param namespace  Pod所在的命名空间
	 * @param name       Pod的名称
	 * @param client     API客户端
	 * @return 单个Pod对象，如果找不到则返回null
	 * @throws RuntimeException 如果发生API调用异常
	 */
	@Override
	public V1Pod one(String namespace, String name, ApiClient client) throws RuntimeException {
		CoreV1Api api = new CoreV1Api(client);
		try {
			V1PodList v1PodList = api.listNamespacedPod(
					namespace,
					null,
					false,
					null,
					"metadata.name=" + name,
					null,
					1,
					null,
					null,
					null,
					false
			);
			if (v1PodList.getItems().isEmpty()) {
				return null;
			} else {
				return v1PodList.getItems().get(0);
			}
		} catch (ApiException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取Pod的YAML表示
	 *
	 * @param namespace  Pod所在的命名空间
	 * @param name       Pod的名称
	 * @param client     API客户端
	 * @return Pod的YAML表示
	 */
	@Override
	public String yaml(String namespace, String name, ApiClient client) {
		return KubectlOpGet.super.yaml(namespace, name, client);
	}
}

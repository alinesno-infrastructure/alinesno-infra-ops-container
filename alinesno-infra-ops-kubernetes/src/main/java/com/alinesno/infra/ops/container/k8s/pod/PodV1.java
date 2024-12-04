package com.alinesno.infra.ops.container.k8s.pod;

import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.ops.container.k8s.KubernetesApiParent;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import com.alinesno.infra.ops.container.k8s.ops.GetOpsParams;
import com.alinesno.infra.ops.container.k8s.ops.KubectlOpGet;
import com.alinesno.infra.ops.container.k8s.ops.KubectlOpLog;
import com.alinesno.infra.ops.container.k8s.ops.LogOpsParams;
import io.kubernetes.client.PodLogs;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1ContainerStatus;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodCondition;
import io.kubernetes.client.openapi.models.V1PodList;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * PodV1类
 *
 * 该类提供了操作Pod的相关方法，包括获取Pod日志、获取Pod信息等。
 * 继承自KubernetesApiParent类，实现了KubectlOpLog和KubectlOpGet接口。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Slf4j
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
			log.error("Error occurred while getting pod logs: " , e);
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
			log.error("Error occurred while listing pods: " , e);
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

	public static KubernetesPageBean<PodInfo> queryByPage(ApiClient apiClient, DatatablesPageBean page, String namespace) {
		KubernetesPageBean<PodInfo> kubePage = new KubernetesPageBean<>();

		CoreV1Api api = new CoreV1Api(apiClient);

		// 获取分页参数
		int start = (page.getPageNum() - 1) * page.getPageSize();
		int limit = page.getPageSize(); // 每页显示的记录数量
		String _continue = null; // 用于分页的标识符

		List<V1Pod> allPods = new ArrayList<>();
		while (true) {
			try {
				V1PodList podList = api.listNamespacedPod(
						namespace,
						null, // pretty
						null, // allowWatchBookmarks
						_continue,
						null, // fieldSelector
						null, // labelSelector
						limit,
						null, // resourceVersion
						null, // resourceVersionMatch
						null, // timeoutSeconds
						false // watch
				);
				allPods.addAll(podList.getItems());

				// 如果已经获取到足够的Pod或者没有更多Pod可获取
				if (allPods.size() >= start + limit || (podList.getMetadata().getContinue() == null)) {
					break;
				}

				// 更新_continue参数
				_continue = podList.getMetadata().getContinue();
			} catch (ApiException e) {
				// 处理异常
				log.error("Error occurred while listing pods: " , e);
				break;
			}
		}

		int total = allPods.size();

		// 只保留当前页的数据
		List<V1Pod> list = allPods.subList(start, Math.min(start + limit, allPods.size()));

		log.debug("podList size: {}", list.size());

		List<PodInfo> pods = new ArrayList<>();
		for (V1Pod pod : list) {
			PodInfo podInfo = convertV1PodToPodInfo(pod);
			pods.add(podInfo);
		}

		kubePage.setRecords(pods);
		kubePage.setTotal(total);

		return kubePage;
	}

	private static PodInfo convertV1PodToPodInfo(V1Pod pod) {

		PodInfo podInfo = new PodInfo();
		podInfo.setName(Objects.requireNonNull(pod.getMetadata()).getName());
		try{
			podInfo.setReady(Objects.requireNonNull(Objects.requireNonNull(pod.getStatus()).getConditions()).stream()
					.filter(condition -> "Ready".equals(condition.getType()))
					.findFirst()
					.map(V1PodCondition::getStatus)
					.orElse("Unknown"));
		}catch(Exception e){
			podInfo.setReady("Unknown");
		}

		podInfo.setNodeName(Objects.requireNonNull(pod.getSpec()).getNodeName());
		podInfo.setIpAddress(Objects.requireNonNull(pod.getStatus()).getPodIP());
		podInfo.setPhase(pod.getStatus().getPhase());

		// 容器状态信息
		StringBuilder containerStatusBuilder = new StringBuilder();
		if (pod.getStatus().getContainerStatuses() != null) {
			for (V1ContainerStatus status : pod.getStatus().getContainerStatuses()) {
				containerStatusBuilder.append(status.getName()).append(": ").append(status.getReady()).append(", ");
			}
			if (!containerStatusBuilder.isEmpty()) {
				containerStatusBuilder.setLength(containerStatusBuilder.length() - 2); // 去掉最后的逗号和空格
			}
		}
		podInfo.setContainerStatus(containerStatusBuilder.toString());

		// 删除等待时间
		podInfo.setDeletionGracePeriodSeconds(pod.getMetadata().getDeletionGracePeriodSeconds() != null ?
				pod.getMetadata().getDeletionGracePeriodSeconds().toString() : "N/A");

		// 重启次数
		int restartCount = 0;
		if (pod.getStatus().getContainerStatuses() != null) {
			for (V1ContainerStatus status : pod.getStatus().getContainerStatuses()) {
				restartCount += status.getRestartCount();
			}
		}
		podInfo.setRestartCount(restartCount);

		// 创建时间
		String age = fromAgo(Date.from(Objects.requireNonNull(pod.getMetadata().getCreationTimestamp()).toInstant()));
		podInfo.setAge(age) ;

		return podInfo;
	}


	/**
	 * Pod信息类，用于封装Pod的信息。
	 */
	@Data
	@ToString
	public static class PodInfo {
		private String name; // 名称
		private String ready; // 就绪状态
		private String nodeName; // 所在节点
		private String ipAddress; // IP 地址
		private String phase; // 阶段
		private String containerStatus; // 容器状态
		private String deletionGracePeriodSeconds; // 删除等待
		private int restartCount; // 已重启次数
		private String age ; // 创建时间
	}
}

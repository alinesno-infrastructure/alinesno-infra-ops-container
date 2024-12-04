package com.alinesno.infra.ops.container.k8s.deployment;

import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.ops.container.k8s.KubernetesApiParent;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.V1Container;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1DeploymentList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class DeploymentV1 extends KubernetesApiParent {

    /**
     * 分布查询部署信息
     *
     * @param apiClient Kubernetes API客户端
     * @param page      分页参数
     * @return 包含分页Deployment信息的KubernetesPageBean
     */
    @SneakyThrows
    public static KubernetesPageBean<DeploymentInfo> queryByPage(ApiClient apiClient, DatatablesPageBean page , String namespace) {
        KubernetesPageBean<DeploymentInfo> kubePage = new KubernetesPageBean<>();

        AppsV1Api api = new AppsV1Api(apiClient);

        // 获取分页参数
        int start = (page.getPageNum() - 1) * page.getPageSize();
        int limit = page.getPageSize(); // 每页显示的记录数量
        String _continue = null; // 用于分页的标识符

        List<V1Deployment> allDeployments = new ArrayList<>();
        while (true) {
            V1DeploymentList deploymentList = api.listNamespacedDeployment(namespace, null, null, null, null, null, limit, _continue, null, null, false);
            allDeployments.addAll(deploymentList.getItems());

            // 如果已经获取到足够的部署或者没有更多部署可获取
            if (allDeployments.size() >= start + limit || (Objects.requireNonNull(deploymentList.getMetadata()).getContinue() == null)) {
                break;
            }

            // 更新_continue参数
            _continue = deploymentList.getMetadata().getContinue();
        }

        int total = allDeployments.size();

        // 只保留当前页的数据
        List<V1Deployment> deploymentsForPage = allDeployments.subList(start, Math.min(start + limit, allDeployments.size()));

        log.debug("deployments size: {}", deploymentsForPage.size());

        List<DeploymentInfo> deploymentInfos = deploymentsForPage.stream()
                .map(DeploymentV1::convertV1DeploymentToDeploymentInfo) // 使用lambda表达式
                .collect(Collectors.toList());

        kubePage.setRecords(deploymentInfos);
        kubePage.setTotal(total);

        return kubePage;
    }

    private static DeploymentInfo convertV1DeploymentToDeploymentInfo(V1Deployment deployment) {
        DeploymentInfo info = new DeploymentInfo();

        info.setName(Objects.requireNonNull(deployment.getMetadata()).getName());
        info.setReady(getReadyStatus(deployment));
        info.setUpToDate(String.valueOf(Objects.requireNonNull(deployment.getStatus()).getUpdatedReplicas()));
        info.setAvailable(String.valueOf(deployment.getStatus().getAvailableReplicas()));
        info.setAge(fromAgo(Date.from(Objects.requireNonNull(deployment.getMetadata().getCreationTimestamp()).toInstant())));
        info.setImages(Objects.requireNonNull(Objects.requireNonNull(deployment.getSpec()).getTemplate().getSpec()).getContainers().stream()
                .map(V1Container::getImage)
                .collect(Collectors.toList()));

        return info;
    }

    private static String getReadyStatus(V1Deployment deployment) {
        var status = deployment.getStatus();
        if (status != null && status.getReadyReplicas() != null && status.getReplicas() != null) {
            return status.getReadyReplicas() + "/" + status.getReplicas();
        }
        return "未知";
    }

    /**
     * 表示Kubernetes (k8s) Deployment信息的JavaBean。
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeploymentInfo {
        private String name; // 部署名称
        private String ready; // 已就绪的Pod数量/总Pod数量
        private String upToDate; // 是否是最新的版本
        private String available; // 可用状态
        private String age; // 创建时间或更新时间
        private List<String> images; // 使用的镜像列表
    }

}

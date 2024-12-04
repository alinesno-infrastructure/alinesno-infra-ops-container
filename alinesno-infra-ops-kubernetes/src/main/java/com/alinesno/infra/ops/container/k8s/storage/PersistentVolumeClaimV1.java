package com.alinesno.infra.ops.container.k8s.storage;

import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PersistentVolumeClaim;
import io.kubernetes.client.openapi.models.V1PersistentVolumeClaimList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

import static com.alinesno.infra.ops.container.k8s.KubernetesApiParent.fromAgo;

@Slf4j
public class PersistentVolumeClaimV1 {

    private final CoreV1Api api;

    public PersistentVolumeClaimV1(ApiClient apiClient) {
        this.api = new CoreV1Api(apiClient);
    }

    /**
     * 获取命名空间下的所有PVC列表，并进行分页处理。
     *
     * @param page      分页参数
     * @param namespace 命名空间
     * @return 包含分页PVC信息的KubernetesPageBean
     */
    public KubernetesPageBean<PVCInfo> queryByPage(DatatablesPageBean page, String namespace) {
        KubernetesPageBean<PVCInfo> kubePage = new KubernetesPageBean<>();

        // 获取分页参数
        int start = (page.getPageNum() - 1) * page.getPageSize();
        int limit = page.getPageSize(); // 每页显示的记录数量
        String _continue = null; // 用于分页的标识符

        List<V1PersistentVolumeClaim> allPvcs = new ArrayList<>();
        try {
            while (true) {
                V1PersistentVolumeClaimList pvcList = api.listNamespacedPersistentVolumeClaim(namespace, null, null, null, null, null, limit, _continue, null, null, false);
                allPvcs.addAll(pvcList.getItems());

                if (allPvcs.size() >= start + limit || (pvcList.getMetadata().getContinue() == null)) {
                    break;
                }

                _continue = pvcList.getMetadata().getContinue();
            }
        } catch (ApiException e) {
            log.error("Error fetching persistent volume claims", e);
            throw new RuntimeException(e);
        }

        int total = allPvcs.size();

        // 只保留当前页的数据
        List<V1PersistentVolumeClaim> pvcsForPage = allPvcs.subList(start, Math.min(start + limit, allPvcs.size()));

        log.debug("pvcs size: {}", pvcsForPage.size());

        List<PVCInfo> pvcInfos = pvcsForPage.stream()
                .map(this::convertV1PvcToPvcInfo) // 使用lambda表达式
                .collect(Collectors.toList());

        kubePage.setRecords(pvcInfos);
        kubePage.setTotal(total);

        return kubePage;
    }

    /**
     * 将V1PersistentVolumeClaim对象转换为PVCInfo对象。
     *
     * @param pvc Kubernetes V1PersistentVolumeClaim对象
     * @return 转换后的PVCInfo对象
     */
    private PVCInfo convertV1PvcToPvcInfo(V1PersistentVolumeClaim pvc) {
        PVCInfo info = new PVCInfo();

        info.setName(Objects.requireNonNull(pvc.getMetadata()).getName());
        info.setNamespace(Objects.requireNonNull(pvc.getMetadata()).getNamespace());
        info.setStatus(Objects.requireNonNull(pvc.getStatus()).getPhase());
        info.setVolumeName(Objects.requireNonNull(pvc.getSpec()).getVolumeName());
        info.setCapacity(String.valueOf(Objects.requireNonNull(pvc.getStatus().getCapacity()).get("storage")));
        info.setAccessModes(String.join(", ", Objects.requireNonNull(pvc.getSpec().getAccessModes())));
        info.setCreationTimestamp(Date.from(Objects.requireNonNull(pvc.getMetadata().getCreationTimestamp()).toInstant()));
        info.setAge(fromAgo(info.getCreationTimestamp()));
        info.setLabels(pvc.getMetadata().getLabels());

        return info;
    }

    /**
     * 表示Kubernetes (k8s) PersistentVolumeClaim V1信息的JavaBean。
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PVCInfo {
        private String name; // PVC名称
        private String namespace; // 所属命名空间
        private String status; // 状态
        private String volumeName; // 关联的PV名称
        private String capacity; // 请求容量
        private String accessModes; // 访问模式
        private Date creationTimestamp; // 创建时间
        private String age ; // 存活时间
        private Map<String, String> labels; // 标签
    }

}
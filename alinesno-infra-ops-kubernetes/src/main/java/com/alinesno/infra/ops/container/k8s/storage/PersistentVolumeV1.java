package com.alinesno.infra.ops.container.k8s.storage;

import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PersistentVolume;
import io.kubernetes.client.openapi.models.V1PersistentVolumeList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

import static com.alinesno.infra.ops.container.k8s.KubernetesApiParent.fromAgo;

@Slf4j
public class PersistentVolumeV1 {

    private final CoreV1Api api;

    public PersistentVolumeV1(ApiClient apiClient) {
        this.api = new CoreV1Api(apiClient);
    }

    /**
     * 获取所有持久卷列表，并进行分页处理。
     *
     * @param page 分页参数
     * @return 包含分页PV信息的KubernetesPageBean
     */
    public KubernetesPageBean<PVInfo> queryByPage(DatatablesPageBean page) {
        KubernetesPageBean<PVInfo> kubePage = new KubernetesPageBean<>();

        // 获取分页参数
        int start = (page.getPageNum() - 1) * page.getPageSize();
        int limit = page.getPageSize(); // 每页显示的记录数量
        String _continue = null; // 用于分页的标识符

        List<V1PersistentVolume> allPvs = new ArrayList<>();
        try {
            while (true) {
                V1PersistentVolumeList pvList = api.listPersistentVolume(null, null, null, null, null, limit, _continue, null, -1, false);
                allPvs.addAll(pvList.getItems());

                if (allPvs.size() >= start + limit || (Objects.requireNonNull(pvList.getMetadata()).getContinue() == null)) {
                    break;
                }

                _continue = pvList.getMetadata().getContinue();
            }
        } catch (ApiException e) {
            log.error("Error fetching persistent volumes", e);
            throw new RuntimeException(e);
        }

        int total = allPvs.size();

        // 只保留当前页的数据
        List<V1PersistentVolume> pvsForPage = allPvs.subList(start, Math.min(start + limit, allPvs.size()));

        log.debug("pvs size: {}", pvsForPage.size());

        List<PVInfo> pvInfos = pvsForPage.stream()
                .map(this::convertV1PersistentVolumeToPVInfo) // 使用lambda表达式
                .collect(Collectors.toList());

        kubePage.setRecords(pvInfos);
        kubePage.setTotal(total);

        return kubePage;
    }

    /**
     * 将V1PersistentVolume对象转换为PVInfo对象。
     *
     * @param pv Kubernetes V1PersistentVolume对象
     * @return 转换后的PVInfo对象
     */
    private PVInfo convertV1PersistentVolumeToPVInfo(V1PersistentVolume pv) {
        PVInfo info = new PVInfo();

        info.setName(Objects.requireNonNull(pv.getMetadata()).getName());
        info.setStatus(Objects.requireNonNull(pv.getStatus()).getPhase());
        info.setCapacity(String.valueOf(Objects.requireNonNull(Objects.requireNonNull(pv.getSpec()).getCapacity()).get("storage")));
        info.setAccessModes(String.join(", ", Objects.requireNonNull(pv.getSpec().getAccessModes())));
        info.setReclaimPolicy(pv.getSpec().getPersistentVolumeReclaimPolicy());
        info.setCreationTimestamp(Date.from(Objects.requireNonNull(pv.getMetadata().getCreationTimestamp()).toInstant()));
        info.setAge(fromAgo(info.getCreationTimestamp()));
        info.setLabels(pv.getMetadata().getLabels());

        return info;
    }

    /**
     * 表示Kubernetes (k8s) PersistentVolume V1信息的JavaBean。
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PVInfo {
        private String name; // PV名称
        private String status; // 状态
        private String capacity; // 容量
        private String accessModes; // 访问模式
        private String reclaimPolicy; // 回收策略
        private Date creationTimestamp; // 创建时间
        private String age ; // 存活时间
        private Map<String, String> labels; // 标签
    }
}
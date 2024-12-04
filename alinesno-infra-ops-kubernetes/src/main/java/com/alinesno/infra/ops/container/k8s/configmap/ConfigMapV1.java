package com.alinesno.infra.ops.container.k8s.configmap;

import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1ConfigMap;
import io.kubernetes.client.openapi.models.V1ConfigMapList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

import static com.alinesno.infra.ops.container.k8s.KubernetesApiParent.fromAgo;

/**
 * Kubernetes ConfigMap V1 API交互类
 */
@Slf4j
public class ConfigMapV1 {

    private final CoreV1Api api;

    public ConfigMapV1(ApiClient apiClient) {
        this.api = new CoreV1Api(apiClient);
    }

    /**
     * 获取命名空间下的所有ConfigMap列表，并进行分页处理
     *
     * @param page      分页参数
     * @param namespace 命名空间
     * @return 包含分页ConfigMap信息的KubernetesPageBean
     */
    @SneakyThrows
    public KubernetesPageBean<ConfigmapInfo> queryByPage(DatatablesPageBean page, String namespace) {
        KubernetesPageBean<ConfigmapInfo> kubePage = new KubernetesPageBean<>();

        // 获取分页参数
        int start = (page.getPageNum() - 1) * page.getPageSize();
        int limit = page.getPageSize(); // 每页显示的记录数量
        String _continue = null; // 用于分页的标识符

        List<V1ConfigMap> allConfigMaps = new ArrayList<>();
        while (true) {
            V1ConfigMapList configMapList = api.listNamespacedConfigMap(namespace, null, null, null, null, null, limit, _continue, null, null, false);
            allConfigMaps.addAll(configMapList.getItems());

            // 如果已经获取到足够的ConfigMap或者没有更多ConfigMap可获取
            if (allConfigMaps.size() >= start + limit || (Objects.requireNonNull(configMapList.getMetadata()).getContinue() == null)) {
                break;
            }

            // 更新_continue参数
            _continue = configMapList.getMetadata().getContinue();
        }

        int total = allConfigMaps.size();

        // 只保留当前页的数据
        List<V1ConfigMap> configMapsForPage = allConfigMaps.subList(start, Math.min(start + limit, allConfigMaps.size()));

        log.debug("configMaps size: {}", configMapsForPage.size());

        List<ConfigmapInfo> configmapInfos = configMapsForPage.stream()
                .map(this::convertV1ConfigMapToConfigmapInfo) // 使用lambda表达式
                .collect(Collectors.toList());

        kubePage.setRecords(configmapInfos);
        kubePage.setTotal(total);

        return kubePage;
    }

    /**
     * 将V1ConfigMap对象转换为ConfigmapInfo对象
     *
     * @param configMap Kubernetes V1ConfigMap对象
     * @return 转换后的ConfigmapInfo对象
     */
    private ConfigmapInfo convertV1ConfigMapToConfigmapInfo(V1ConfigMap configMap) {
        ConfigmapInfo info = new ConfigmapInfo();

        info.setName(Objects.requireNonNull(configMap.getMetadata()).getName());
        info.setNamespace(Objects.requireNonNull(configMap.getMetadata()).getNamespace());
        info.setCreationTimestamp(Date.from(Objects.requireNonNull(configMap.getMetadata().getCreationTimestamp()).toInstant()));
        info.setData(configMap.getData()); // 数据键值对
        info.setBinaryData(configMap.getBinaryData()); // 二进制数据键值对
        info.setAnnotations(Objects.requireNonNull(configMap.getMetadata()).getAnnotations());
        info.setAge(fromAgo(info.getCreationTimestamp()));

        return info;
    }

    /**
     * 创建一个新的ConfigMap
     *
     * @param namespace 命名空间
     * @param configmapInfo ConfigmapInfo对象
     * @return 创建的ConfigMap信息
     */
    @SneakyThrows
    public V1ConfigMap createConfigMap(String namespace, ConfigmapInfo configmapInfo) {
        V1ConfigMap configMap = new V1ConfigMap();
        configMap.setMetadata(new io.kubernetes.client.openapi.models.V1ObjectMeta()
                .name(configmapInfo.getName())
                .namespace(configmapInfo.getNamespace())
                .annotations(configmapInfo.getAnnotations()));
        configMap.setData(configmapInfo.getData());
        configMap.setBinaryData(configmapInfo.getBinaryData());

        return api.createNamespacedConfigMap(namespace, configMap, null, null, null , null);
    }

    /**
     * 更新现有的ConfigMap
     *
     * @param namespace 命名空间
     * @param name      ConfigMap名称
     * @param configmapInfo ConfigmapInfo对象
     * @return 更新后的ConfigMap信息
     */
    @SneakyThrows
    public V1ConfigMap updateConfigMap(String namespace, String name, ConfigmapInfo configmapInfo) {
        V1ConfigMap configMap = new V1ConfigMap();
        configMap.setMetadata(new io.kubernetes.client.openapi.models.V1ObjectMeta()
                .name(name)
                .namespace(namespace)
                .annotations(configmapInfo.getAnnotations()));
        configMap.setData(configmapInfo.getData());
        configMap.setBinaryData(configmapInfo.getBinaryData());

        return api.replaceNamespacedConfigMap(name, namespace, configMap, null, null, null,null);
    }

    /**
     * 删除指定的ConfigMap
     *
     * @param namespace 命名空间
     * @param name      ConfigMap名称
     */
    @SneakyThrows
    public void deleteConfigMap(String namespace, String name) {
        api.deleteNamespacedConfigMap(name, namespace, null, null, null, null, null, null);
    }

    /**
     * 表示Kubernetes (k8s) ConfigMap V1信息的JavaBean。
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConfigmapInfo {
        private String name; // ConfigMap名称
        private String namespace; // 所属命名空间
        private Date creationTimestamp; // 创建时间
        private String age ;
        private Map<String, String> data; // 数据键值对
        private Map<String, byte[]> binaryData; // 二进制数据键值对
        private Map<String, String> annotations; // 注解
    }
}
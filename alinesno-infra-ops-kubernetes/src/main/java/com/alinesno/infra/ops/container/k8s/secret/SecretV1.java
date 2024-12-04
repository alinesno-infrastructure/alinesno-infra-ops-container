package com.alinesno.infra.ops.container.k8s.secret;

import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.ops.container.k8s.KubernetesApiParent;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Secret;
import io.kubernetes.client.openapi.models.V1SecretList;
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
public class SecretV1 extends KubernetesApiParent {

    /**
     * 分布查询密钥信息
     *
     * @param apiClient Kubernetes API客户端
     * @param page      分页参数
     * @return 包含分页Secret信息的KubernetesPageBean
     */
    @SneakyThrows
    public static KubernetesPageBean<SecretInfo> queryByPage(ApiClient apiClient, DatatablesPageBean page, String namespace) {
        KubernetesPageBean<SecretInfo> kubePage = new KubernetesPageBean<>();

        CoreV1Api api = new CoreV1Api(apiClient);

        // 获取分页参数
        int start = (page.getPageNum() - 1) * page.getPageSize();
        int limit = page.getPageSize(); // 每页显示的记录数量
        String _continue = null; // 用于分页的标识符

        List<V1Secret> allSecrets = new ArrayList<>();
        while (true) {
            V1SecretList secretList = api.listNamespacedSecret(namespace, null, null, null, null, null, limit, _continue, null, null, false);
            allSecrets.addAll(secretList.getItems());

            // 如果已经获取到足够的密钥或者没有更多密钥可获取
            if (allSecrets.size() >= start + limit || (Objects.requireNonNull(secretList.getMetadata()).getContinue() == null)) {
                break;
            }

            // 更新_continue参数
            _continue = secretList.getMetadata().getContinue();
        }

        int total = allSecrets.size();

        // 只保留当前页的数据
        List<V1Secret> secretsForPage = allSecrets.subList(start, Math.min(start + limit, allSecrets.size()));

        log.debug("secrets size: {}", secretsForPage.size());

        List<SecretInfo> secretInfos = secretsForPage.stream()
                .map(SecretV1::convertV1SecretToSecretInfo)
                .collect(Collectors.toList());

        kubePage.setRecords(secretInfos);
        kubePage.setTotal(total);

        return kubePage;
    }

    private static SecretInfo convertV1SecretToSecretInfo(V1Secret secret) {
        SecretInfo info = new SecretInfo();

        info.setName(Objects.requireNonNull(secret.getMetadata()).getName());
        info.setType(secret.getType());
        info.setImmutable(secret.getImmutable() != null && secret.getImmutable());
        info.setCreationTimestamp(Date.from(Objects.requireNonNull(secret.getMetadata().getCreationTimestamp()).toInstant()));

        info.setAge(fromAgo(info.getCreationTimestamp()));

        return info;
    }

    /**
     * 表示Kubernetes (k8s) Secret信息的JavaBean。
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SecretInfo {
        private String name; // 密钥名称
        private String type; // 密钥类型
        private boolean immutable; // 是否不可变
        private Date creationTimestamp; // 创建时间戳
        private String age ; // 密钥创建时间
    }
}
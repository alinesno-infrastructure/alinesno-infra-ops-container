package com.alinesno.infra.ops.container.k8s.ingress;

import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.ops.container.k8s.KubernetesApiParent;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.NetworkingV1Api;
import io.kubernetes.client.openapi.models.V1Ingress;
import io.kubernetes.client.openapi.models.V1IngressList;
import io.kubernetes.client.openapi.models.V1IngressRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 分布查询Ingress信息
 */
@Slf4j
public class IngressV1 extends KubernetesApiParent {

    /**
     * 分布查询Ingress信息
     *
     * @param apiClient Kubernetes API客户端
     * @param page      分页参数
     * @param namespace 命名空间
     * @return 包含分页Ingress信息的KubernetesPageBean
     */
    @SneakyThrows
    public static KubernetesPageBean<IngressInfo> queryByPage(ApiClient apiClient, DatatablesPageBean page, String namespace) {
        KubernetesPageBean<IngressInfo> kubePage = new KubernetesPageBean<>();

        NetworkingV1Api api = new NetworkingV1Api(apiClient);

        // 获取分页参数
        int start = (page.getPageNum() - 1) * page.getPageSize();
        int limit = page.getPageSize(); // 每页显示的记录数量
        String _continue = null; // 用于分页的标识符

        List<V1Ingress> allIngresses = new ArrayList<>();
        while (true) {
            V1IngressList ingressList = api.listNamespacedIngress(namespace, null, null, null, null, null, limit, _continue, null, null, false);
            allIngresses.addAll(ingressList.getItems());

            // 如果已经获取到足够的Ingress或者没有更多Ingress可获取
            if (allIngresses.size() >= start + limit || (Objects.requireNonNull(ingressList.getMetadata()).getContinue() == null)) {
                break;
            }

            // 更新_continue参数
            _continue = ingressList.getMetadata().getContinue();
        }

        int total = allIngresses.size();

        // 只保留当前页的数据
        List<V1Ingress> ingressesForPage = allIngresses.subList(start, Math.min(start + limit, allIngresses.size()));

        log.debug("ingresses size: {}", ingressesForPage.size());

        List<IngressInfo> ingressInfos = ingressesForPage.stream()
                .map(IngressV1::convertV1IngressToIngressInfo) // 使用lambda表达式
                .collect(Collectors.toList());

        kubePage.setRecords(ingressInfos);
        kubePage.setTotal(total);

        return kubePage;
    }

    private static IngressInfo convertV1IngressToIngressInfo(V1Ingress ingress) {
        IngressInfo info = new IngressInfo();

        info.setName(Objects.requireNonNull(ingress.getMetadata()).getName());
        info.setNamespace(Objects.requireNonNull(ingress.getMetadata()).getNamespace());
        info.setAge(fromAgo(Date.from(Objects.requireNonNull(ingress.getMetadata().getCreationTimestamp()).toInstant())));
        info.setAnnotations(Objects.requireNonNull(ingress.getMetadata()).getAnnotations());

        if (ingress.getSpec() != null && ingress.getSpec().getRules() != null) {
            info.setRules(ingress.getSpec().getRules().stream()
                    .map(V1IngressRule::getHost)
                    .collect(Collectors.toList()));

            if (!info.getRules().isEmpty()) {
                // Assuming we take the first rule for simplicity
                var firstRule = ingress.getSpec().getRules().get(0);
                if (firstRule.getHttp() != null && firstRule.getHttp().getPaths() != null && !firstRule.getHttp().getPaths().isEmpty()) {
                    var path = firstRule.getHttp().getPaths().get(0);
                    info.setService(path.getBackend().getService().getName());
                    info.setPath(path.getPath());
                }
            }
        }

        return info;
    }

    /**
     * 表示Kubernetes (k8s) Ingress V1信息的JavaBean。
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IngressInfo {
        private String name; // Ingress名称
        private String namespace; // 所属命名空间
        private List<String> rules; // 规则列表（域名）
        private String service; // 后端服务名称
        private String path; // 路径
        private String age; // 创建时间
        private Map<String, String> annotations; // 注解
    }

}

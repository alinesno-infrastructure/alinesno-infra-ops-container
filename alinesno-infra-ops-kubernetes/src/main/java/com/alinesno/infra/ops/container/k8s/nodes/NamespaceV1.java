package com.alinesno.infra.ops.container.k8s.nodes;

import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.ops.container.k8s.KubernetesApiParent;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
public class NamespaceV1 extends KubernetesApiParent {

    /**
     * 分布查询节点信息
     *
     * @param apiClient
     * @param page
     * @return
     */
    @SneakyThrows
    public static KubernetesPageBean<NamespaceInfo> queryByPage(ApiClient apiClient, DatatablesPageBean page) {
        KubernetesPageBean<NamespaceInfo> kubePage = new KubernetesPageBean<>();

        CoreV1Api api = new CoreV1Api(apiClient);

        // 获取分页参数
        int start = (page.getPageNum() - 1) * page.getPageSize();
        int limit = page.getPageSize(); // 每页显示的记录数量
        String _continue = null; // 用于分页的标识符

        List<V1Namespace> allNodes = new ArrayList<>();
        while (true) {
            V1NamespaceList nodeList = api.listNamespace(null, null, null, null, null, limit, _continue, null, null, false);
            allNodes.addAll(nodeList.getItems());

            // 如果已经获取到足够的节点或者没有更多节点可获取
            if (allNodes.size() >= start + limit || (Objects.requireNonNull(nodeList.getMetadata()).getContinue() == null)) {
                break;
            }

            // 更新_continue参数
            _continue = nodeList.getMetadata().getContinue();
        }

        int total = allNodes.size() ;


        // 只保留当前页的数据
        List<V1Namespace> list = allNodes.subList(start, Math.min(start + limit, allNodes.size()));

        log.debug("nodeList size: {}", list.size());

        List<NamespaceInfo> nodes = new ArrayList<>();
        for (V1Namespace node : list) {
            NamespaceInfo nodeInfo = convertV1NodeToNodeInfo(node);
            nodes.add(nodeInfo);
        }

        kubePage.setRecords(nodes);
        kubePage.setTotal(total);

        return kubePage;
    }

    private static NamespaceInfo convertV1NodeToNodeInfo(V1Namespace node) {
        NamespaceInfo nodeInfo = new NamespaceInfo();

        String access = "public" ;
        String name = Objects.requireNonNull(node.getMetadata()).getName();
        String phase = Objects.requireNonNull(node.getStatus()).getPhase();
        String age = fromAgo(Date.from(Objects.requireNonNull(Objects.requireNonNull(node.getMetadata()).getCreationTimestamp()).toInstant()));

        nodeInfo.setAccess(access);
        nodeInfo.setStar(0);
        nodeInfo.setName(name);
        nodeInfo.setPhase(phase);
        nodeInfo.setAge(age);

        return nodeInfo;
    }

    @Data
    @ToString
    public static class NamespaceInfo {
        private String access; // 访问权限
        private int star;      // 收藏
        private String name;   // 名称
        private String phase;  // 阶段
        private String age;       // 年龄或持续时间
    }

}

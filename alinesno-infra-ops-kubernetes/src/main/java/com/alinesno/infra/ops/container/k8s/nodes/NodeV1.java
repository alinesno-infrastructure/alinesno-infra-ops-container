package com.alinesno.infra.ops.container.k8s.nodes;

import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.ops.container.k8s.KubernetesApiParent;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeList;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
public class NodeV1 extends KubernetesApiParent {
    /**
     * 查询集群的状态
     */
    @SneakyThrows
    public static List<V1Node> queryNodeList(ApiClient apiClient) {
        CoreV1Api api = new CoreV1Api(apiClient);
        return api.listNode(null , null , null , null , null , 20 , null , null , null , false).getItems();
    }

    /**
     * 分布查询节点信息
     *
     * @param apiClient
     * @param page
     * @return
     */
    @SneakyThrows
    public static KubernetesPageBean<NodeInfo> queryByPage(ApiClient apiClient, DatatablesPageBean page) {
        KubernetesPageBean<NodeInfo> kubePage = new KubernetesPageBean<>();

        CoreV1Api api = new CoreV1Api(apiClient);

        // 获取分页参数
        int start = (page.getPageNum() - 1) * page.getPageSize();
        int limit = page.getPageSize(); // 每页显示的记录数量
        String _continue = null; // 用于分页的标识符

        List<V1Node> allNodes = new ArrayList<>();
        while (true) {
            V1NodeList nodeList = api.listNode(null, null, null, null, null, limit, _continue, null, null, false);
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
        List<V1Node> list = allNodes.subList(start, Math.min(start + limit, allNodes.size()));

        log.debug("nodeList size: {}", list.size());

        List<NodeInfo> nodes = new ArrayList<>();
        for (V1Node node : list) {
            NodeInfo nodeInfo = convertV1NodeToNodeInfo(node);
            nodes.add(nodeInfo);
        }

        kubePage.setRecords(nodes);
        kubePage.setTotal(total);

        return kubePage;
    }

    private static NodeInfo convertV1NodeToNodeInfo(V1Node node) {
        NodeInfo nodeInfo = new NodeInfo();

        String name = Objects.requireNonNull(node.getMetadata()).getName();
        String roles = Objects.requireNonNull(Objects.requireNonNull(node.getMetadata()).getLabels()).get("kubernetes.io/role");
        String hostName = Objects.requireNonNull(Objects.requireNonNull(node.getMetadata()).getLabels()).get("kubernetes.io/hostname");
        String internalIp = Objects.requireNonNull(Objects.requireNonNull(node.getStatus()).getAddresses()).stream()
                .filter(x -> x.getType().equals("InternalIP"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Internal IP not found"))
                .getAddress();

        String containerRuntimeVersion = Objects.requireNonNull(Objects.requireNonNull(node.getStatus()).getNodeInfo()).getContainerRuntimeVersion();
        String kubeletVersion = Objects.requireNonNull(Objects.requireNonNull(node.getStatus()).getNodeInfo()).getKubeletVersion();
        String age = fromAgo(Date.from(Objects.requireNonNull(node.getMetadata().getCreationTimestamp()).toInstant()));
        String osImage = Objects.requireNonNull(Objects.requireNonNull(node.getStatus()).getNodeInfo()).getOsImage();
        String oskernelVersion = Objects.requireNonNull(Objects.requireNonNull(node.getStatus()).getNodeInfo()).getKernelVersion();

        nodeInfo.setName(name);
        nodeInfo.setRoles(roles);
        nodeInfo.setHostName(hostName);
        nodeInfo.setInternalIp(internalIp);
        nodeInfo.setContainerRuntimeVersion(containerRuntimeVersion);
        nodeInfo.setAge(age);
        nodeInfo.setKubeletVersion(kubeletVersion);
        nodeInfo.setOskernelVersion(oskernelVersion);
        nodeInfo.setOsImage(osImage);

        return nodeInfo;
    }

    @Data
    @ToString
    public static class NodeInfo {
        private String name;      // 名称
        private String roles;     // 角色
        private String hostName; // 主机名
        private String internalIp; // 内部IP
        private String containerRuntimeVersion; // 容器运行时版本
        private String age;       // 年龄/存在时间
        private String kubeletVersion; // Kubelet版本
        private String osImage;   // 操作系统镜像 (重复出现，使用一次)
        private String oskernelVersion;   // 内核版本
    }
}

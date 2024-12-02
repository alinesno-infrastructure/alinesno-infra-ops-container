package com.alinesno.infra.ops.container.k8s.nodes;

import com.alinesno.infra.ops.container.k8s.KubernetesApiParent;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Node;
import lombok.SneakyThrows;

import java.util.List;

public class NodeV1 extends KubernetesApiParent {
    /**
     * 查询集群的状态
     */
    @SneakyThrows
    public static List<V1Node> queryNodeList(ApiClient apiClient) {
        CoreV1Api api = new CoreV1Api(apiClient);
        return api.listNode(null , null , null , null , null , 20 , null , null , null , false).getItems();
    }

}

package com.alinesno.infra.ops.container.k8s.container;

import java.util.ArrayList;
import java.util.List;

import com.alinesno.infra.ops.container.k8s.KubernetesApiParent;
import com.alinesno.infra.ops.container.k8s.ops.AttachOpsParams;
import com.alinesno.infra.ops.container.k8s.ops.GetOpsParams;
import com.alinesno.infra.ops.container.k8s.ops.KubectlOpAttach;
import com.alinesno.infra.ops.container.k8s.ops.KubectlOpGet;
import io.kubernetes.client.Exec;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;

/**
 * Pod 操作对象
 * 提供了对 Pod 的查询和附加操作
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class ContainersV1 extends KubernetesApiParent implements KubectlOpGet<V1Pod>, KubectlOpAttach {

    /**
     * 查询 Pod 列表
     *
     * @param params    查询参数
     * @param apiClient ApiClient 对象
     * @return Pod 列表
     * @throws ApiException 当查询失败时抛出异常
     */
    @Override
    public List<V1Pod> get(final GetOpsParams params, ApiClient apiClient) {
        CoreV1Api api = new CoreV1Api(apiClient);
        List<V1Pod> pods = new ArrayList<>();
        try {
            V1PodList v1PodList = api.listNamespacedPod(params.getNamespaceName(), null, false, null, null, params.toNativeLabelSelector(), null, null, null, null, false);
            List<V1Pod> items = v1PodList.getItems();
            pods.addAll(items);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        return pods;
    }

    /**
     * 获取单个 Pod 的详细信息
     *
     * @param namespace 命名空间
     * @param name      Pod 名称
     * @param client    ApiClient 对象
     * @return Pod 对象
     */
    @Override
    public V1Pod one(String namespace, String name, ApiClient client) {
        return null;
    }

    /**
     * 获取 Pod 的 YAML 配置
     *
     * @param namespace 命名空间
     * @param name      Pod 名称
     * @param client    ApiClient 对象
     * @return Pod 的 YAML 配置
     */
    @Override
    public String yaml(String namespace, String name, ApiClient client) {
        return "";
    }

    /**
     * 附加到 Pod
     *
     * @param params    附加操作参数
     * @param command   附加命令
     * @param apiClient ApiClient 对象
     * @return 附加的进程对象
     * @throws Exception 当附加操作失败时抛出异常
     */
    @Override
    public Process attach(AttachOpsParams params, String[] command, ApiClient apiClient) {
        try {
            Exec exec = new Exec(apiClient);
            return exec.exec(params.getNamespaceName(), params.getPodName(), command, params.getContainer(), true, true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

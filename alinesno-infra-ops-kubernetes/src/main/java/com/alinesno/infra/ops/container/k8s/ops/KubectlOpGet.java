package com.alinesno.infra.ops.container.k8s.ops;

import java.util.List;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.Yaml;

/**
 * Kubectl操作获取接口
 * 用于执行kubectl获取操作
 *
 * @author luoxiaodong
 * @version 1.0.0
 *
 * @param <R> 结果类型
 */
public interface KubectlOpGet<R> {
    /**
     * 获取多个资源
     *
     * @param params     获取操作参数
     * @param apiClient  Kubernetes API客户端
     * @return 多个资源列表
     */
    List<R> get(final GetOpsParams params, ApiClient apiClient);

    /**
     * 获取单个资源
     *
     * @param namespace  命名空间
     * @param name       资源名称
     * @param client     Kubernetes API客户端
     * @return 单个资源
     */
    R one(String namespace, String name, ApiClient client);

    /**
     * 获取资源的YAML表示形式
     *
     * @param namespace  命名空间
     * @param name       资源名称
     * @param client     Kubernetes API客户端
     * @return 资源的YAML表示形式
     */
    default String yaml(String namespace, String name, ApiClient client) {
        R one = one(namespace, name, client);
        if (one == null) {
            return "";
        }
        return Yaml.dump(one);
    }
}

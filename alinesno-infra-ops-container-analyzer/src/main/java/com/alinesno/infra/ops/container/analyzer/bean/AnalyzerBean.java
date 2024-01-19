package com.alinesno.infra.ops.container.analyzer.bean;

import com.alinesno.infra.ops.container.k8s.KubernetesApiClient;
import io.kubernetes.client.openapi.ApiClient;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 表示分析器，用于对 Kubernetes 对象进行分析。
 */
@Data
public class AnalyzerBean {

    /**
     * Kubernetes 客户端。
     */
    private ApiClient client;

    /**
     * 命名空间。
     */
    private String namespace;

    /**
     * 预分析信息映射。
     */
    private Map<String, PreAnalysisBean> preAnalysisMap;

    /**
     * 分析结果列表。
     */
    private List<ResultBean> results;

    /**
     * OpenAPI Schema 文档。
     */
    private String openapiSchema;

}
package com.alinesno.infra.ops.container.analyzer.bean;

import io.kubernetes.client.openapi.models.*;
import lombok.Data;

import java.util.List;

/**
 * 表示预分析信息。
 */
@Data
public class PreAnalysisBean {

    /**
     * Pod 对象。
     */
    private V1Pod pod;

    /**
     * 失败详细信息列表。
     */
    private List<FailureBean> failureDetails;

    /**
     * Deployment 对象。
     */
    private V1Deployment deployment;

    /**
     * ReplicaSet 对象。
     */
    private V1ReplicaSet replicaSet;

    /**
     * PersistentVolumeClaim 对象。
     */
    private V1PersistentVolumeClaim persistentVolumeClaim;

    /**
     * Endpoints 对象。
     */
    private V1Endpoints endpoint;

    /**
     * Ingress 对象。
     */
    private V1Ingress ingress;

    /**
     * HorizontalPodAutoscaler 对象。
     */
    private V1HorizontalPodAutoscaler horizontalPodAutoscalers;

    /**
     * PodDisruptionBudget 对象。
     */
    private V1PodDisruptionBudget podDisruptionBudget;

    /**
     * StatefulSet 对象。
     */
    private V1StatefulSet statefulSet;

    /**
     * NetworkPolicy 对象。
     */
    private V1NetworkPolicy networkPolicy;

    /**
     * Node 对象。
     */
    private V1Node node;

    /**
     * ValidatingWebhookConfiguration 对象。
     */
    private V1ValidatingWebhookConfiguration validatingWebhook;

    /**
     * MutatingWebhookConfiguration 对象。
     */
    private V1MutatingWebhookConfiguration mutatingWebhook;

//    /**
//     * GatewayClass 对象。
//     */
//    private V1Ga gatewayClass;
//
//    /**
//     * Gateway 对象。
//     */
//    private V1Gateway gateway;
//
//    /**
//     * HTTPRoute 对象。
//     */
//    private V1HTTPRoute httpRoute;
//
//    /**
//     * Trivy 漏洞报告。
//     */
//    private VulnerabilityReport trivyVulnerabilityReport;
//
//    /**
//     * Trivy 配置审计报告。
//     */
//    private ConfigAuditReport trivyConfigAuditReport;


    public PreAnalysisBean(V1Pod pod, List<FailureBean> failureDetails) {
        this.pod = pod;
        this.failureDetails = failureDetails;
    }
}
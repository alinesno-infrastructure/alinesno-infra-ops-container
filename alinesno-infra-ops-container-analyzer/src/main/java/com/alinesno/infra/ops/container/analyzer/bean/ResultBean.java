package com.alinesno.infra.ops.container.analyzer.bean;

import lombok.Data;

import java.util.List;

/**
 * 表示对 Kubernetes 对象进行分析的结果。
 */
@Data
public class ResultBean {

    /**
     * 分析的 Kubernetes 对象的类型或种类（例如："Pod"）。
     */
    private String kind;

    /**
     * 分析的 Kubernetes 对象的名称。
     */
    private String name;

    /**
     * 与分析的 Kubernetes 对象相关联的故障列表。
     */
    private List<FailureBean> error;

    /**
     * 与分析结果相关的额外细节或信息。
     */
    private String details;

    /**
     * 分析的 Kubernetes 对象的父对象（如果适用）。
     */
    private String parentObject;

    /**
     * 预先分析对象
     */
    private PreAnalysisBean preAnalysisBean ;

    /**
     * 使用指定的参数构造新的 Result 对象。
     *
     * @param kind          分析的 Kubernetes 对象的类型或种类。
     * @param name          分析的 Kubernetes 对象的名称。
     * @param error         与分析的 Kubernetes 对象相关联的故障列表。
     * @param details       与分析结果相关的额外细节或信息。
     * @param parentObject  分析的 Kubernetes 对象的父对象（如果适用）。
     */
    public ResultBean(String kind, String name, List<FailureBean> error, String details, String parentObject) {
        this.kind = kind;
        this.name = name;
        this.error = error;
        this.details = details;
        this.parentObject = parentObject;
    }

    public ResultBean(String kind, String name, List<FailureBean> error, PreAnalysisBean preAnalysis) {
    }
}
package com.alinesno.infra.ops.container.analyzer.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 表示对 Kubernetes 对象进行分析的失败信息。
 */
@Data
public class FailureBean {

    /**
     * 失败的文本描述信息。
     */
    private String text;

    /**
     * 失败相关的 Kubernetes 文档。
     */
    private String kubernetesDoc;

    /**
     * 与失败相关的敏感信息列表。
     */
    private List<SensitiveBean> sensitiveList;

    public FailureBean(String message, ArrayList<SensitiveBean> es) {
        this.text = message ;
        this.sensitiveList = es ;
    }
}

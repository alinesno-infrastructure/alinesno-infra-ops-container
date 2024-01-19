package com.alinesno.infra.ops.container.analyzer.bean;

import lombok.Data;

import java.util.List;

/**
 * 前端请求参数类型
 */
@Data
public class ReqParams {

    private String filters  ; // 过滤条件，管理用于分析 Kubernetes 资源的过滤器
    private List<String> namespace ; // 所选择的命名空间

}

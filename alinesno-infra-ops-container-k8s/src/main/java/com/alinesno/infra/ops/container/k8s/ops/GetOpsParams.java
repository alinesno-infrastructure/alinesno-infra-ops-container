package com.alinesno.infra.ops.container.k8s.ops;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

/**
 * 查询工作负载通用参数类
 * 用于指定查询工作负载的参数
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class GetOpsParams {
    /**
     * 命名空间
     */
    private String namespaceName;
    /**
     * 标签选择器列表
     */
    List<LabelSelector> labelSelector;
    /**
     * 字段选择器列表
     */
    List<FieldSelector> fieldSelector;
    private String deploymentName;

    /**
     * 获取部署名称
     * @return 部署名称
     */
    public String getDeploymentName() {
        return deploymentName;
    }

    /**
     * 获取命名空间名称
     * @return 命名空间名称
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * 设置命名空间名称
     * @param namespaceName 命名空间名称
     * @return 当前对象
     */
    public GetOpsParams setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
        return this;
    }

    /**
     * 获取标签选择器列表
     * @return 标签选择器列表
     */
    public List<LabelSelector> getLabelSelector() {
        return labelSelector;
    }

    /**
     * 设置标签选择器列表
     * @param labelSelector 标签选择器列表
     * @return 当前对象
     */
    public GetOpsParams setLabelSelector(List<LabelSelector> labelSelector) {
        this.labelSelector = labelSelector;
        return this;
    }

    /**
     * 获取字段选择器列表
     * @return 字段选择器列表
     */
    public List<FieldSelector> getFieldSelector() {
        return fieldSelector;
    }

    /**
     * 设置字段选择器列表
     * @param fieldSelector 字段选择器列表
     * @return 当前对象
     */
    public GetOpsParams setFieldSelector(List<FieldSelector> fieldSelector) {
        this.fieldSelector = fieldSelector;
        return this;
    }

    /**
     * 设置部署名称
     * @param deploymentName 部署名称
     * @return 当前对象
     */
    public GetOpsParams setDeploymentName(String deploymentName) {
        this.deploymentName = deploymentName;
        return this;
    }

    /**
     * 将标签选择器列表转换为原生标签选择器字符串
     * @return 原生标签选择器字符串
     */
    public String toNativeLabelSelector() {
        if (CollectionUtils.isNotEmpty(this.labelSelector)) {
            return labelSelector.stream().map(LabelSelector::toString).collect(Collectors.joining(","));
        } else {
            return "";
        }
    }

    /**
     * 将字段选择器列表转换为字段标签选择器字符串
     * @return 字段标签选择器字符串
     */
    public String toFieldLabelSelector() {
        if (CollectionUtils.isNotEmpty(this.fieldSelector)) {
            return fieldSelector.stream().map(FieldSelector::toString).collect(Collectors.joining(","));
        } else {
            return "";
        }
    }
}

package com.alinesno.infra.ops.container.k8s.ops;

import java.util.List;

/**
 * 查询工作负载通用参数
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class SuspendOpsParams {

  /**
   * 命名空间名称
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
  
  /**
   * 是否挂起
   */
  private Boolean suspend;
  
  /**
   * 部署名称
   */
  private String deploymentName;

  /**
   * 获取部署名称
   *
   * @return 部署名称
   */
  public String getDeploymentName() {
    return deploymentName;
  }

  /**
   * 获取命名空间名称
   *
   * @return 命名空间名称
   */
  public String getNamespaceName() {
    return namespaceName;
  }

  /**
   * 获取是否挂起
   *
   * @return 是否挂起
   */
  public Boolean getSuspend() {
    return suspend;
  }

  /**
   * 设置是否挂起
   *
   * @param suspend 是否挂起
   * @return SuspendOpsParams对象
   */
  public SuspendOpsParams setSuspend(Boolean suspend) {
    this.suspend = suspend;
    return this;
  }

  /**
   * 设置命名空间名称
   *
   * @param namespaceName 命名空间名称
   * @return SuspendOpsParams对象
   */
  public SuspendOpsParams setNamespaceName(String namespaceName) {
    this.namespaceName = namespaceName;
    return this;
  }

  /**
   * 获取标签选择器列表
   *
   * @return 标签选择器列表
   */
  public List<LabelSelector> getLabelSelector() {
    return labelSelector;
  }

  /**
   * 设置标签选择器列表
   *
   * @param labelSelector 标签选择器列表
   * @return SuspendOpsParams对象
   */
  public SuspendOpsParams setLabelSelector(List<LabelSelector> labelSelector) {
    this.labelSelector = labelSelector;
    return this;
  }

  /**
   * 获取字段选择器列表
   *
   * @return 字段选择器列表
   */
  public List<FieldSelector> getFieldSelector() {
    return fieldSelector;
  }

  /**
   * 设置字段选择器列表
   *
   * @param fieldSelector 字段选择器列表
   * @return SuspendOpsParams对象
   */
  public SuspendOpsParams setFieldSelector(List<FieldSelector> fieldSelector) {
    this.fieldSelector = fieldSelector;
    return this;
  }

  /**
   * 设置部署名称
   *
   * @param deploymentName 部署名称
   * @return SuspendOpsParams对象
   */
  public SuspendOpsParams setDeploymentName(String deploymentName) {
    this.deploymentName = deploymentName;
    return this;
  }
}

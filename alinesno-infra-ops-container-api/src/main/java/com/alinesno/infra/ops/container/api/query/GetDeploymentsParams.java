package com.alinesno.infra.ops.container.api.query;

/**
 * 获取部署参数
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class GetDeploymentsParams {

  /**
   * 页码
   */
  private Integer pageNum;
  
  /**
   * 每页大小
   */
  private Integer pageSize;
  
  /**
   * 部署名称
   */
  private String deploymentName;
  
  /**
   * 工作负载
   */
  private String workload;
  
  /**
   * 命名空间名称
   */
  private String namespaceName;

  /**
   * 获取页码
   *
   * @return 页码
   */
  public Integer getPageNum() {
    return pageNum;
  }

  /**
   * 设置页码
   *
   * @param pageNum 页码
   * @return GetDeploymentsParams对象
   */
  public GetDeploymentsParams setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
    return this;
  }

  /**
   * 获取每页大小
   *
   * @return 每页大小
   */
  public Integer getPageSize() {
    return pageSize;
  }

  /**
   * 设置每页大小
   *
   * @param pageSize 每页大小
   * @return GetDeploymentsParams对象
   */
  public GetDeploymentsParams setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * 获取部署名称
   *
   * @return 部署名称
   */
  public String getDeploymentName() {
    return deploymentName;
  }

  /**
   * 设置部署名称
   *
   * @param deploymentName 部署名称
   * @return GetDeploymentsParams对象
   */
  public GetDeploymentsParams setDeploymentName(String deploymentName) {
    this.deploymentName = deploymentName;
    return this;
  }

  /**
   * 获取工作负载
   *
   * @return 工作负载
   */
  public String getWorkload() {
    return workload;
  }

  /**
   * 设置工作负载
   *
   * @param workload 工作负载
   * @return GetDeploymentsParams对象
   */
  public GetDeploymentsParams setWorkload(String workload) {
    this.workload = workload;
    return this;
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
   * 设置命名空间名称
   *
   * @param namespaceName 命名空间名称
   * @return GetDeploymentsParams对象
   */
  public GetDeploymentsParams setNamespaceName(String namespaceName) {
    this.namespaceName = namespaceName;
    return this;
  }
}

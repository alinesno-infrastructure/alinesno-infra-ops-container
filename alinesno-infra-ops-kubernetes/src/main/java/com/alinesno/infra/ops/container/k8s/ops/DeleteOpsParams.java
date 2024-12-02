package com.alinesno.infra.ops.container.k8s.ops;

/**
 * 删除操作参数类
 * 封装了删除操作所需的参数信息
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class DeleteOpsParams {

  private String name;
  private String namespace;

  /**
   * 获取名称
   * @return 名称
   */
  public String getName() {
    return name;
  }

  /**
   * 设置名称
   * @param name 名称
   * @return 当前对象
   */
  public DeleteOpsParams setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * 获取命名空间
   * @return 命名空间
   */
  public String getNamespace() {
    return namespace;
  }

  /**
   * 设置命名空间
   * @param namespace 命名空间
   * @return 当前对象
   */
  public DeleteOpsParams setNamespace(String namespace) {
    this.namespace = namespace;
    return this;
  }
}

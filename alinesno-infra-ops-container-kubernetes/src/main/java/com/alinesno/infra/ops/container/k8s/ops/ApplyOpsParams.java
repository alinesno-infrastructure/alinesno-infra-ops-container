package com.alinesno.infra.ops.container.k8s.ops;

/**
 * 应用操作参数类
 * 封装了应用操作所需的参数信息
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class ApplyOpsParams {

  private String yaml;

  /**
   * 获取YAML配置文件内容
   * @return YAML配置文件内容
   */
  public String getYaml() {
    return yaml;
  }

  /**
   * 设置YAML配置文件内容
   * @param yaml YAML配置文件内容
   * @return 当前对象
   */
  public ApplyOpsParams setYaml(String yaml) {
    this.yaml = yaml;
    return this;
  }
}

package com.alinesno.infra.ops.container.k8s.ops;

/**
 * 字段选择器类
 * 用于指定字段选择条件
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class FieldSelector {

  private String key;
  private String value;
  private String operator;

  /**
   * 获取操作符
   * @return 操作符
   */
  public String getOperator() {
    return operator;
  }

  /**
   * 设置操作符
   * @param operator 操作符
   * @return 当前对象
   */
  public FieldSelector setOperator(String operator) {
    this.operator = operator;
    return this;
  }

  /**
   * 获取键
   * @return 键
   */
  public String getKey() {
    return key;
  }

  /**
   * 设置键
   * @param key 键
   * @return 当前对象
   */
  public FieldSelector setKey(String key) {
    this.key = key;
    return this;
  }

  /**
   * 获取值
   * @return 值
   */
  public String getValue() {
    return value;
  }

  /**
   * 设置值
   * @param value 值
   * @return 当前对象
   */
  public FieldSelector setValue(String value) {
    this.value = value;
    return this;
  }

  @Override
  public String toString() {
    return key + operator + value;
  }
}

package com.alinesno.infra.ops.container.k8s.ops;

import io.kubernetes.client.openapi.ApiClient;

/**
 * Kubectl Apply操作接口
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public interface KubectlOpApply {
  /**
   * 执行Apply操作
   * @param params Apply操作参数
   * @param apiClient Kubernetes API客户端
   */
  void apply(final ApplyOpsParams params, ApiClient apiClient);
}

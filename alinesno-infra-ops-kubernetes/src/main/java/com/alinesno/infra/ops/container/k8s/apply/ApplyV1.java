package com.alinesno.infra.ops.container.k8s.apply;

import com.alinesno.infra.ops.container.k8s.KubernetesApiParent;
import com.alinesno.infra.ops.container.k8s.ops.ApplyOpsParams;
import com.alinesno.infra.ops.container.k8s.ops.KubectlOpApply;
import io.kubernetes.client.common.KubernetesObject;
import io.kubernetes.client.extended.kubectl.Kubectl;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1StatefulSet;
import io.kubernetes.client.util.Yaml;

/**
 * 应用部署操作类
 * 实现了KubectlOpApply接口，提供了应用部署操作的方法
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class ApplyV1 extends KubernetesApiParent implements KubectlOpApply {

  /**
   * 根据资源类型获取对应的类
   * @param kind 资源类型
   * @return 对应的类
   */
  static Class<? extends KubernetesObject> getClassForKind(String kind) {
    switch (kind) {
      case "deployments":
        return V1Deployment.class;
      case "statefulsets":
        return V1StatefulSet.class;
    }
    return null;
  }

  /**
   * 应用部署操作
   * @param params 应用操作参数
   * @param apiClient API客户端
   */
  @Override
  public void apply(ApplyOpsParams params, ApiClient apiClient) {
    String yaml = params.getYaml();
    try {
      Object loaded = Yaml.load(yaml);
      Class<KubernetesObject> objectClass = (Class<KubernetesObject>) loaded.getClass();
      Kubectl.apply(objectClass)
              .apiClient(apiClient)
              .forceConflict(true)
              .resource((KubernetesObject) loaded)
              .execute();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}

package com.alinesno.infra.ops.container.k8s.svc;

import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceList;
import io.kubernetes.client.openapi.models.V1ServicePort;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.alinesno.infra.ops.container.k8s.KubernetesApiParent.fromAgo;

/**
 * SvcV1 k8s 接口服务
 */
@Slf4j
public class SvcV1 {


    public static KubernetesPageBean<SvcInfo> queryByPage(ApiClient apiClient, DatatablesPageBean page, String namespace) {
        KubernetesPageBean<SvcInfo> kubePage = new KubernetesPageBean<>();

        CoreV1Api api = new CoreV1Api(apiClient);

        // 获取分页参数
        int start = (page.getPageNum() - 1) * page.getPageSize();
        int limit = 500 ; // page.getPageSize(); // 每页显示的记录数量
        String _continue = null; // 用于分页的标识符

        List<V1Service> allServices = new ArrayList<>();
        while (true) {
            try {
                V1ServiceList serviceList = api.listNamespacedService(
                        namespace,
                        null, // pretty
                        null, // allowWatchBookmarks
                        _continue,
                        null, // fieldSelector
                        null, // labelSelector
                        limit,
                        null, // resourceVersion
                        null, // resourceVersionMatch
                        null, // timeoutSeconds
                        false // watch
                );
                allServices.addAll(serviceList.getItems());

                // 如果已经获取到足够的服务或者没有更多服务可获取
                if (allServices.size() >= start + limit || (Objects.requireNonNull(serviceList.getMetadata()).getContinue() == null)) {
                    break;
                }

                // 更新_continue参数
                _continue = serviceList.getMetadata().getContinue();
            } catch (ApiException e) {
                // 处理异常
                log.error("Error occurred while listing services: " , e);
                break;
            }
        }

        int total = allServices.size();

        // 只保留当前页的数据
        List<V1Service> list = allServices.subList(start, Math.min(start + limit, allServices.size()));

        log.debug("serviceList size: {}", list.size());

        List<SvcInfo> services = new ArrayList<>();
        for (V1Service svc : list) {
            SvcInfo svcInfo = convertV1ServiceToSvcInfo(svc);
            services.add(svcInfo);
        }

        kubePage.setRecords(services);
        kubePage.setTotal(total);

        return kubePage;
    }

    private static SvcInfo convertV1ServiceToSvcInfo(V1Service svc) {
        SvcInfo svcInfo = new SvcInfo();

        svcInfo.setName(Objects.requireNonNull(svc.getMetadata()).getName());
        svcInfo.setNamespace(svc.getMetadata().getNamespace());
        svcInfo.setType(Objects.requireNonNull(svc.getSpec()).getType());
        svcInfo.setClusterIP(svc.getSpec().getClusterIP());

        // 处理端口列表
        List<PortInfo> portDescriptions = getPortInfo(svc);
        svcInfo.setPorts(portDescriptions);

        // 设置创建时间
        String age = fromAgo(Date.from(Objects.requireNonNull(svc.getMetadata().getCreationTimestamp()).toInstant()));
        svcInfo.setAge(age) ;

        return svcInfo;
    }

    @NotNull
    private static List<PortInfo> getPortInfo(V1Service svc) {
        List<PortInfo> portDescriptions = new ArrayList<>();

        if (Objects.requireNonNull(svc.getSpec()).getPorts() != null) {
            for (V1ServicePort port : svc.getSpec().getPorts()) {

                PortInfo portDescription = new PortInfo();

                portDescription.setPort(String.valueOf(port.getPort()));
                portDescription.setNodePort(port.getNodePort() == null ? "" : String.valueOf(port.getNodePort())) ;
                portDescription.setContainerPort(String.valueOf(port.getTargetPort()));
                portDescription.setName(port.getName());
                portDescription.setProtocol(port.getProtocol());

                portDescriptions.add(portDescription);
            }
        }

        return portDescriptions;
    }

    @Data
    @ToString
    public static class SvcInfo {
        private String name;
        private String namespace;
        private String type;
        private String clusterIP;
        private List<PortInfo> ports; // 端口可能有多个，使用List存储
        private String age;
    }

    @Data
    @ToString
    public static class PortInfo {
        private String port;
        private String nodePort ;
        private String containerPort ;
        private String name;
        private String protocol;
    }

}

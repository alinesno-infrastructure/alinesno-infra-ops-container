package com.alinesno.infra.ops.container.analyzer.handle;

import com.alinesno.infra.ops.container.analyzer.bean.AnalyzerBean;
import com.alinesno.infra.ops.container.analyzer.bean.FailureBean;
import com.alinesno.infra.ops.container.analyzer.bean.PreAnalysisBean;
import com.alinesno.infra.ops.container.analyzer.bean.ResultBean;
import com.alinesno.infra.ops.container.analyzer.utils.K8SUtils;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.Config;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Pod 分析服务
 */
public class PodAnalyzer {

    public List<ResultBean> analyze(AnalyzerBean a) throws ApiException, IOException {
        String kind = "Pod";

        CoreV1Api coreV1APi = new CoreV1Api(a.getClient());

        V1PodList list = coreV1APi.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
        List<ResultBean> results = new ArrayList<>();

        for (V1Pod pod : list.getItems()) {
            List<FailureBean> failures = new ArrayList<>();

            if ("Pending".equals(Objects.requireNonNull(pod.getStatus()).getPhase())) {

                for (V1PodCondition condition : Objects.requireNonNull(pod.getStatus().getConditions())) {
                   if ("PodScheduled".equals(condition.getType()) && "Unschedulable".equals(condition.getReason())) {
                        if (condition.getMessage() != null && !condition.getMessage().isEmpty()) {
                            failures.add(new FailureBean(condition.getMessage(), new ArrayList<>()));
                        }
                   }
                }

            }

            for (V1ContainerStatus containerStatus : Objects.requireNonNull(pod.getStatus().getContainerStatuses())) {

                if (Objects.requireNonNull(containerStatus.getState()).getWaiting() != null) {
                    if (isErrorReason(containerStatus.getState().getWaiting().getReason()) && containerStatus.getState().getWaiting().getMessage() != null && !containerStatus.getState().getWaiting().getMessage().isEmpty()) {
                        failures.add(new FailureBean(containerStatus.getState().getWaiting().getMessage(), new ArrayList<>()));
                    }
                }

                if ("ContainerCreating".equals(containerStatus.getState().getWaiting().getReason()) && "Pending".equals(pod.getStatus().getPhase())) {

                    // parse the event log and append details
                    CoreV1Event evt = fetchLatestEvent(a, Objects.requireNonNull(pod.getMetadata()).getNamespace(), pod.getMetadata().getName());
                    if (evt == null) {
                        continue;
                    }
                    if ("FailedCreatePodSandBox".equals(evt.getReason()) && evt.getMessage() != null && !evt.getMessage().isEmpty()) {
                        failures.add(new FailureBean(evt.getMessage(), new ArrayList<>()));
                    }
                }

                // This represents container that is in CrashLoopBackOff state due to conditions such as OOMKilled
                if ("CrashLoopBackOff".equals(containerStatus.getState().getWaiting().getReason())) {
                    failures.add(new FailureBean(String.format("the last termination reason is %s container=%s pod=%s", containerStatus.getLastState().getTerminated().getReason(), containerStatus.getName(), pod.getMetadata().getName()), new ArrayList<>()));
                } else {
                    // when pod is Running but its ReadinessProbe fails
                    if (!containerStatus.getReady() && "Running".equals(pod.getStatus().getPhase())) {
                        // parse the event log and append details
                        CoreV1Event evt = fetchLatestEvent(a, Objects.requireNonNull(pod.getMetadata()).getNamespace(), pod.getMetadata().getName());
                        if (evt == null) {
                            continue;
                        }
                        if ("Unhealthy".equals(evt.getReason()) && evt.getMessage() != null && !evt.getMessage().isEmpty()) {
                            failures.add(new FailureBean(evt.getMessage(), new ArrayList<>()));
                        }
                    }
                }
            }

            if (!failures.isEmpty()) {
                String key = String.format("%s/%s", Objects.requireNonNull(pod.getMetadata()).getNamespace(), pod.getMetadata().getName());
                PreAnalysisBean preAnalysis = new PreAnalysisBean(pod, failures);
                results.add(new ResultBean(kind, key, null, preAnalysis));
            }

            for (ResultBean result : results) {
                String parent = K8SUtils.getParent(coreV1APi, null , null , null , Objects.requireNonNull(result.getPreAnalysisBean().getPod().getMetadata()));
                result.setParentObject(parent);
            }

        }

        return results;
    }

    private boolean isErrorReason(String reason) {
        List<String> failureReasons = List.of(
                "CrashLoopBackOff", "ImagePullBackOff", "CreateContainerConfigError", "PreCreateHookError", "CreateContainerError", "PreStartHookError", "RunContainerError", "ImageInspectError", "ErrImagePull", "ErrImageNeverPull", "InvalidImageName"
        );

        return failureReasons.contains(reason);
    }

    @SneakyThrows
    public static CoreV1Event fetchLatestEvent(AnalyzerBean a , String namespace, String name) throws ApiException {

        ApiClient apiClient = Config.defaultClient();
        CoreV1Api coreV1Api = new CoreV1Api(apiClient);

        // Get the list of events
        CoreV1EventList eventList = coreV1Api.listNamespacedEvent(namespace, null, null, null, null, "involvedObject.name=" + name, null, null, null, null , null);

        // Find the most recent event
        CoreV1Event latestEvent = null;
        for (CoreV1Event event : eventList.getItems()) {
            if (latestEvent == null || Objects.requireNonNull(event.getLastTimestamp()).isAfter(latestEvent.getLastTimestamp())) {
                latestEvent = event;
            }
        }

        return latestEvent;
    }
}

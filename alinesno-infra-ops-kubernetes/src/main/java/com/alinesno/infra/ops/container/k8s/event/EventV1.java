package com.alinesno.infra.ops.container.k8s.event;

import com.alinesno.infra.common.core.utils.DateUtils;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.CoreV1Event;
import io.kubernetes.client.openapi.models.CoreV1EventList;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Kubernetes Event V1 API交互类
 */
@Slf4j
public class EventV1 {

    private final CoreV1Api api;

    public EventV1(ApiClient apiClient) {
        this.api = new CoreV1Api(apiClient);
    }

    /**
     * 获取命名空间下的所有事件列表，并进行分页处理
     *
     * @param page      分页参数
     * @param namespace 命名空间
     * @return 包含分页事件信息的KubernetesPageBean
     */
    @SneakyThrows
    public KubernetesPageBean<EventInfo> queryByPage(DatatablesPageBean page, String namespace) {
        KubernetesPageBean<EventInfo> kubePage = new KubernetesPageBean<>();

        // 获取分页参数
        int start = (page.getPageNum() - 1) * page.getPageSize();
        int limit = page.getPageSize(); // 每页显示的记录数量
        String _continue = null; // 用于分页的标识符

        List<CoreV1Event> allEvents = new ArrayList<>();
        while (true) {
            CoreV1EventList eventList = api.listNamespacedEvent(namespace, null, null, null, null, null, limit, _continue, null, null, false);
            allEvents.addAll(eventList.getItems());

            // 如果已经获取到足够的事件或者没有更多事件可获取
            if (allEvents.size() >= start + limit || (Objects.requireNonNull(eventList.getMetadata()).getContinue() == null)) {
                break;
            }

            // 更新_continue参数
            _continue = eventList.getMetadata().getContinue();
        }

        int total = allEvents.size();

        // 只保留当前页的数据
        List<CoreV1Event> eventsForPage = allEvents.subList(start, Math.min(start + limit, allEvents.size()));

        log.debug("events size: {}", eventsForPage.size());

        List<EventInfo> eventInfos = eventsForPage.stream()
                .map(this::convertCoreV1EventToEventInfo) // 使用lambda表达式
                .collect(Collectors.toList());

        kubePage.setRecords(eventInfos);
        kubePage.setTotal(total);

        return kubePage;
    }

    /**
     * 将CoreV1Event对象转换为EventInfo对象
     *
     * @param event Kubernetes CoreV1Event对象
     * @return 转换后的EventInfo对象
     */
    private EventInfo convertCoreV1EventToEventInfo(CoreV1Event event) {
        EventInfo info = new EventInfo();

        // 安全地获取时间戳，如果为空则使用当前时间
        info.setTime(event.getEventTime() != null ? DateUtils.parseDate(event.getEventTime().toString()) : DateUtils.getNowDate());

        // 设置计数，默认为0
        info.setCount(event.getCount() != null ? event.getCount() : 0);

        // 直接设置类型、原因和消息，假设这些字段不会为null（如果可能为null，则需要类似的时间戳处理）
        info.setType(event.getType());
        info.setReason(event.getReason());
        info.setMessage(event.getMessage());

        // 涉及的对象名称
        info.setObj(event.getInvolvedObject() != null && event.getInvolvedObject().getName() != null ?
                event.getInvolvedObject().getName() : "N/A");

        // 事件来源组件名称
        info.setSource(event.getSource() != null && event.getSource().getComponent() != null ?
                event.getSource().getComponent() : "N/A");

        return info;
    }

    /**
     * 表示Kubernetes (k8s) Event V1信息的JavaBean。
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class EventInfo {
        private Date time; // 时间：事件发生的时间
        private int count;   // 重复次数：该事件重复出现的次数
        private String type; // 类型：事件的类型，例如 'Normal' 或 'Warning'
        private String reason; // 原因：事件产生的原因
        private String obj; // 对象：涉及的对象，通常是Pod、Node等资源的名字
        private String source; // 来源：事件的来源，可以是控制面组件或节点名称
        private String message; // 消息：关于事件的详细信息
    }
}
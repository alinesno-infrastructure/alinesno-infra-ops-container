package com.alinesno.infra.ops.container.api.controller;

import com.alinesno.infra.common.core.constants.SpringInstanceScope;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.web.adapter.rest.BaseController;
import com.alinesno.infra.ops.container.entity.kubernetes.EnvClusterEntity;
import com.alinesno.infra.ops.container.service.kubernetes.IEnvClusterService;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 环境集群相关请求的Controller。
 * 继承自BaseController类并实现IEnvClusterService接口。
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Api(tags = "EnvCluster")
@RestController
@Scope(SpringInstanceScope.PROTOTYPE)
@RequestMapping("/api/infra/simple/crm/env_cluster")
public class EnvClusterController extends BaseController<EnvClusterEntity, IEnvClusterService> {

    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(EnvClusterController.class);

    @Autowired
    private IEnvClusterService service;

    /**
     * 获取EnvClusterEntity的DataTables数据。
     *
     * @param request HttpServletRequest对象。
     * @param model Model对象。
     * @param page DatatablesPageBean对象。
     * @return 包含DataTables数据的TableDataInfo对象。
     */
    @ResponseBody
    @PostMapping("/datatables")
    public TableDataInfo datatables(HttpServletRequest request, Model model, DatatablesPageBean page) {
        log.debug("page = {}", ToStringBuilder.reflectionToString(page));
        return this.toPage(model, this.getFeign(), page);
    }

    @Override
    public IEnvClusterService getFeign() {
        return this.service;
    }
}

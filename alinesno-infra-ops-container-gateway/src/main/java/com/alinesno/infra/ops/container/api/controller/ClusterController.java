package com.alinesno.infra.ops.container.api.controller;

import com.alinesno.infra.common.core.constants.SpringInstanceScope;
import com.alinesno.infra.common.extend.datasource.annotation.DataPermissionSave;
import com.alinesno.infra.common.extend.datasource.annotation.DataPermissionScope;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.common.web.adapter.login.account.CurrentAccountBean;
import com.alinesno.infra.common.web.adapter.login.account.CurrentAccountJwt;
import com.alinesno.infra.common.web.adapter.login.annotation.CurrentAccount;
import com.alinesno.infra.common.web.adapter.rest.BaseController;
import com.alinesno.infra.ops.container.api.ClusterDto;
import com.alinesno.infra.ops.container.api.session.CurrentClusterSession;
import com.alinesno.infra.ops.container.entity.ClusterEntity;
import com.alinesno.infra.ops.container.k8s.KubernetesApiClient;
import com.alinesno.infra.ops.container.service.IClusterService;
import com.alinesno.infra.ops.container.service.IClusterUserService;
import io.kubernetes.client.openapi.ApiClient;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 环境集群相关请求的Controller。
 * 继承自BaseController类并实现IEnvClusterService接口。
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Api(tags = "EnvCluster")
@RestController
@Scope(SpringInstanceScope.PROTOTYPE)
@RequestMapping("/api/infra/ops/container/cluster")
public class ClusterController extends BaseController<ClusterEntity, IClusterService> {

    @Autowired
    private IClusterService service;

    @Autowired
    private IClusterUserService clusterUserService ;

    /**
     * 获取EnvClusterEntity的DataTables数据。
     *
     * @param request HttpServletRequest对象。
     * @param model Model对象。
     * @param page DatatablesPageBean对象。
     * @return 包含DataTables数据的TableDataInfo对象。
     */
    @DataPermissionScope
    @ResponseBody
    @PostMapping("/datatables")
    public TableDataInfo datatables(HttpServletRequest request, Model model, DatatablesPageBean page) {
        log.debug("page = {}", ToStringBuilder.reflectionToString(page));
        return this.toPage(model, this.getFeign(), page);
    }

    /**
     * 保存集群 saveCluster
     * @return
     */
    @SneakyThrows
    @DataPermissionSave
    @PostMapping("/saveCluster")
    public AjaxResult saveCluster(@RequestBody @Validated ClusterDto clusterDTO){

        // 验证集群是否正常
        ApiClient apiClient = null ;

        try {
            if (clusterDTO.getKubeConfig().equals("token")) {
                apiClient = KubernetesApiClient.getApiClientByConfig(clusterDTO.getKubeConfig());
            } else {
                apiClient = KubernetesApiClient.getApiClientByToken(clusterDTO.getApiServerUrl(), clusterDTO.getKubeConfig());
            }

            log.debug("apiClient = {}", apiClient);
        }catch (Exception e){
            log.error("集群配置不正确", e);
            throw new RuntimeException("集群配置不正确");
        }

        service.saveCluster(clusterDTO);

        return ok();
    }

    /**
     * 配置集群 命名空间
     */
    @GetMapping("/choiceNamespace")
    public AjaxResult choiceNamespace(@RequestParam(value = "namespace") String namespace) {

        long clusterId = CurrentClusterSession.getClusterId() ;
        clusterUserService.setUserCluster(CurrentAccountJwt.getUserId(), clusterId , namespace);

        return ok();
    }

    /**
     * 配置集群 choiceCluster
     */
    @GetMapping("/choiceCluster")
    public AjaxResult choiceCluster(@RequestParam(value = "clusterId") long clusterId) {
        clusterUserService.setUserCluster(CurrentAccountJwt.getUserId(), clusterId , null);
        return ok();
    }

    /**
     * 获取当前应用
     * @return
     */
    @GetMapping("/currentCluster")
    public AjaxResult currentCluster(@CurrentAccount CurrentAccountBean currentAccount) {

        ClusterDto e =  CurrentClusterSession.get() ;

        if(e == null){
            return AjaxResult.success("当前用户没有选择集群") ;
        }

        String defaultIcon = "fa-solid fa-file-shield" ;
        e.setIcon(defaultIcon);

        return AjaxResult.success(e);
    }

    @Override
    public IClusterService getFeign() {
        return this.service;
    }
}

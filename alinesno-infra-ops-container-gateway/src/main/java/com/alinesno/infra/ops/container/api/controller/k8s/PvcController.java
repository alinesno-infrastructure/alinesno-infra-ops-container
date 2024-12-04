package com.alinesno.infra.ops.container.api.controller.k8s;

import com.alinesno.infra.common.core.constants.SpringInstanceScope;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.ops.container.api.KubernetesAdapter;
import com.alinesno.infra.ops.container.k8s.KubernetesPageBean;
import com.alinesno.infra.ops.container.k8s.storage.PersistentVolumeClaimV1;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "EnvCluster")
@RestController
@Scope(SpringInstanceScope.PROTOTYPE)
@RequestMapping("/api/infra/ops/container/k8s/pvc")
public class PvcController extends KubernetesAdapter {

    @ResponseBody
    @PostMapping("/datatables")
    public TableDataInfo datatables(DatatablesPageBean page) {
        log.debug("page = {}", ToStringBuilder.reflectionToString(page));

        PersistentVolumeClaimV1 persistentVolumeV1 = new PersistentVolumeClaimV1(getApiClient()) ;
        KubernetesPageBean<PersistentVolumeClaimV1.PVCInfo> kubePage = persistentVolumeV1.queryByPage(page , getNamespace()) ;

        return toKubernetesPage(kubePage) ;
    }


}

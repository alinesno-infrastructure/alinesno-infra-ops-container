package com.alinesno.infra.ops.container.analyzer;

import com.alinesno.infra.ops.container.analyzer.bean.AnalyzerBean;
import com.alinesno.infra.ops.container.analyzer.bean.ReqParams;
import com.alinesno.infra.ops.container.analyzer.bean.ResultBean;
import com.alinesno.infra.ops.container.analyzer.handle.PodAnalyzer;
import com.alinesno.infra.ops.container.k8s.KubernetesApiClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class BaseAnalyzerImpl implements BaseAnalyzer {

    @Value("${alinesno.infra.ops.container.kubernetes.api-server}")
    private String apiServerUrl ;

    @Value("${alinesno.infra.ops.container.kubernetes.token}")
    private String k8sToken;

    @SneakyThrows
    @Override
    public List<ResultBean> analyze(ReqParams reqParams) {

        log.debug("reParams = {}" , reqParams);

        String filters = reqParams.getFilters() ;
        log.debug("filters = {}" , filters);

        BaseHandleAnalyzer baseHandleAnalyzer = new PodAnalyzer();

        AnalyzerBean analyzerBean = new AnalyzerBean() ;
        analyzerBean.setClient(KubernetesApiClient.getApiClientByToken(apiServerUrl , k8sToken));

        List<ResultBean> resultBeans = baseHandleAnalyzer.analyze(analyzerBean) ;
        log.debug("resultBeans = {}" , resultBeans);

        return resultBeans;
    }
}

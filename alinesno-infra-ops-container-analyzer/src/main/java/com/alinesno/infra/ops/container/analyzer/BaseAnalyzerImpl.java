package com.alinesno.infra.ops.container.analyzer;

import com.alinesno.infra.ops.container.analyzer.bean.ReqParams;
import com.alinesno.infra.ops.container.analyzer.bean.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class BaseAnalyzerImpl implements BaseAnalyzer {

    @Override
    public List<ResultBean> analyze(ReqParams reqParams) {

        log.debug("reParams = {}" , reqParams);

        String filters = reqParams.getFilters() ;
        log.debug("filters = {}" , filters);

        List<ResultBean> resultBeans = new ArrayList<>() ;

        log.debug("resultBeans = {}" , resultBeans);

        return resultBeans;
    }
}

package com.alinesno.infra.ops.container.analyzer.provider;

import com.alinesno.infra.common.core.constants.SpringInstanceScope;
import com.alinesno.infra.common.web.adapter.rest.SuperController;
import com.alinesno.infra.ops.container.analyzer.BaseAnalyzer;
import com.alinesno.infra.ops.container.analyzer.bean.ReqParams;
import com.alinesno.infra.ops.container.analyzer.bean.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 运行状态情况分析
 */
@Slf4j
@RestController
@Scope(SpringInstanceScope.PROTOTYPE)
@RequestMapping("/api/infra/ops/container/analyzer")
public class AnalyzerController extends SuperController {

    @Autowired
    private BaseAnalyzer baseAnalyzer ;

    /**
     * 获取到k8s运行状态信息
     * @return
     */
    @PostMapping("analyze")
    public List<ResultBean> analyze(@RequestBody ReqParams reqParams){

        List<ResultBean> resultBeans = baseAnalyzer.analyze(reqParams) ;

        log.debug("resultBeans = {}" , resultBeans);

        return resultBeans ;
    }

}

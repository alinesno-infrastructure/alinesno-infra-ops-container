package com.alinesno.infra.ops.container.analyzer.handle;

import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.ops.container.analyzer.BaseHandleAnalyzer;
import com.alinesno.infra.ops.container.analyzer.BaseHandleTest;
import com.alinesno.infra.ops.container.analyzer.bean.AnalyzerBean;
import com.alinesno.infra.ops.container.analyzer.bean.ResultBean;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
class PodAnalyzerTest extends BaseHandleTest {

    @SneakyThrows
    @Test
    void analyze() {

        BaseHandleAnalyzer baseHandleAnalyzer = new PodAnalyzer() ;

        AnalyzerBean analyzerBean = new AnalyzerBean() ;
        analyzerBean.setClient(getApiClient());

        List<ResultBean> list = baseHandleAnalyzer.analyze(analyzerBean) ;
        System.out.println(JSONObject.toJSONString(list));
    }
}
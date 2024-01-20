package com.alinesno.infra.ops.container.analyzer;

import com.alinesno.infra.ops.container.analyzer.bean.AnalyzerBean;
import com.alinesno.infra.ops.container.analyzer.bean.ResultBean;
import io.kubernetes.client.openapi.ApiException;

import java.util.List;

public interface BaseHandleAnalyzer {

    List<ResultBean> analyze(AnalyzerBean a) throws ApiException ;

}

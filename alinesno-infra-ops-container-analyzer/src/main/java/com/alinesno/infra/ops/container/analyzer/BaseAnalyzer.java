package com.alinesno.infra.ops.container.analyzer;

import com.alinesno.infra.ops.container.analyzer.bean.ReqParams;
import com.alinesno.infra.ops.container.analyzer.bean.ResultBean;

import java.util.List;

public interface BaseAnalyzer {

    List<ResultBean> analyze(ReqParams reqParams) ;

}

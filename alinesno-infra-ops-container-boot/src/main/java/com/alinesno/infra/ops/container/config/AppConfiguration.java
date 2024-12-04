package com.alinesno.infra.ops.container.config;

import com.alinesno.infra.common.extend.datasource.enable.EnableInfraDataScope;
import com.alinesno.infra.common.facade.enable.EnableActable;
import com.alinesno.infra.common.web.adapter.sso.enable.EnableInfraSsoApi;
import com.alinesno.infra.common.web.log.aspect.LogAspect;
import com.dtflys.forest.springboot.annotation.ForestScan;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableActable
@EnableInfraSsoApi
@EnableAsync
@MapperScan("com.alinesno.infra.ops.container.mapper")
@ForestScan({
        "com.alinesno.infra.common.web.adapter.base.consumer" ,
        "com.alinesno.infra.base.platform.adapter"
})
@EnableInfraDataScope
@EnableScheduling
@Configuration
public class AppConfiguration implements CommandLineRunner {

    @Bean
    public LogAspect getLogAspect() {
        return new LogAspect();
    }

    @Override
    public void run(String... args) throws Exception {

        log.debug("项目启动完成");

    }

}

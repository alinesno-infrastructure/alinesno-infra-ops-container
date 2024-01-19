package com.alinesno.infra.ops.container;

import com.alinesno.infra.common.facade.enable.EnableActable;
import com.alinesno.infra.common.web.adapter.sso.enable.EnableInfraSsoApi;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 集成一个Java开发示例工具
 * @author LuoAnDong
 * @since 2023年8月3日 上午6:23:43
 */
@EnableActable
@EnableInfraSsoApi
@MapperScan("com.alinesno.infra.ops.container.mapper")
@SpringBootApplication
public class OpsContainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpsContainerApplication.class, args);
	}

}

package com.alinesno.infra.ops.container.k8s.dto;

import lombok.Data;

/**
 * 容器运行端口
 * 
 * 该类表示容器的运行端口信息。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Data
public class RunPodContainerPortDto {

	/**
	 * 容器端口
	 * 
	 * 表示容器内部监听的端口号。
	 */
	private Integer containerPort;
	
	/**
	 * 主机IP
	 * 
	 * 表示容器所在主机的IP地址。
	 */
	private String hostIP;
	
	/**
	 * 主机端口
	 * 
	 * 表示容器映射到主机的端口号。
	 */
	private Integer hostPort;
	
	/**
	 * 端口名称
	 * 
	 * 表示容器端口的名称。
	 */
	private String name;
	
	/**
	 * 协议
	 * 
	 * 表示容器端口使用的协议，如TCP或UDP。
	 */
	private String protocol;


}

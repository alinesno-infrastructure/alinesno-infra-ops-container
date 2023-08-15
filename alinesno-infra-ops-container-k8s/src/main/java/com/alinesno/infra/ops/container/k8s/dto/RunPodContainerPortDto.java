package com.alinesno.infra.ops.container.k8s.dto;

/**
 * 容器运行端口
 * 
 * 该类表示容器的运行端口信息。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
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

	/**
	 * 获取容器端口
	 * 
	 * @return 容器端口号
	 */
	public Integer getContainerPort() {
		return containerPort;
	}

	/**
	 * 设置容器端口
	 * 
	 * @param containerPort 容器端口号
	 */
	public void setContainerPort(Integer containerPort) {
		this.containerPort = containerPort;
	}

	/**
	 * 获取主机IP
	 * 
	 * @return 主机IP地址
	 */
	public String getHostIP() {
		return hostIP;
	}

	/**
	 * 设置主机IP
	 * 
	 * @param hostIP 主机IP地址
	 */
	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	/**
	 * 获取主机端口
	 * 
	 * @return 主机端口号
	 */
	public Integer getHostPort() {
		return hostPort;
	}

	/**
	 * 设置主机端口
	 * 
	 * @param hostPort 主机端口号
	 */
	public void setHostPort(Integer hostPort) {
		this.hostPort = hostPort;
	}

	/**
	 * 获取端口名称
	 * 
	 * @return 端口名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置端口名称
	 * 
	 * @param name 端口名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取协议
	 * 
	 * @return 协议名称
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * 设置协议
	 * 
	 * @param protocol 协议名称
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

}

package com.alinesno.infra.ops.container.k8s.dto;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能名： 容器组 数据表： run_pod
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Data
@Accessors(chain =true)
public class RunPodDto {

	/**
	 * 容器名称
	 */
	private String containerName;

	/**
	 * "容器组"
	 */
	private String containerGroupName;
	/**
	 * "命名空间"
	 */
	private String namespaceName;
	/**
	 * "状态"
	 */
	private String status;
	/**
	 * "容器数"
	 */
	private int containerCount;
	/**
	 * "重启次数"
	 */
	private int restartCount;
	/**
	 * "CPU使用量"
	 */
	private BigDecimal cpuUsage;
	/**
	 * "内存使用量"
	 */
	private BigDecimal memoryUsage;
	/**
	 * "运行时间（秒）"
	 */
	private String age;
	/**
	 * "Pod IP地址"
	 */
	private String podIpAddress;
	/**
	 * "Node IP地址"
	 */
	private String nodeIpAddress;

	/**
	 * 节点的名称
	 */
	private String nodeName ; 
	/**
	 * "CPU限制"
	 */
	private BigDecimal cpuLimit;
	/**
	 * "内存限制"
	 */
	private BigDecimal memoryLimit;
	/**
	 * "探针状态"
	 */
	private String probeStatus;

	private String imageName; // = container.getImage();
	private String imagePullPolicy; // = container.getImagePullPolicy();

	// 获取资源限制和使用信息
	private BigDecimal memLimit; // (memLimitQuantity == null) ? "" : memLimitQuantity.getNumber().floatValue() +
	private String cpuRequest; // = (cpuRequestQuantity == null) ? ""
	private String memRequest; // = (memRequestQuantity == null) ? ""
	private Double cpuPercent; // = getPercentage(cpuUsage, cpuRequest);
	private Double memoryPercent; // = getPercentage(memoryUsage, memRequest);
	private String servicePorts;

	/**
	 * 服务端口列表
	 */
	private List<RunPodContainerPortDto> containertPot ; 

	/**
	 * 快速从 dto 转换成 entity
	 * 
	 * @return dto 对应的 entity
	 */
	public RunPodDto mapToDto() {
		ObjectMapper toDtoMapper = new ObjectMapper();
		toDtoMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return toDtoMapper.convertValue(this, RunPodDto.class);
	}

	/**
	 * 快速从 entity 转换成 dto
	 * 
	 * @return dto 对应的 entity
	 */
	public RunPodDto fromDto(RunPodDto entity) {
		ObjectMapper fromDtoMapper = new ObjectMapper();
		fromDtoMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return fromDtoMapper.convertValue(entity, RunPodDto.class);
	}

}

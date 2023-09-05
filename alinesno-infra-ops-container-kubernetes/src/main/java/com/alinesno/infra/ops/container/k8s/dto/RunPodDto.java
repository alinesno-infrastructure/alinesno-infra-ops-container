package com.alinesno.infra.ops.container.k8s.dto;

import java.math.BigDecimal;
import java.util.List;

import com.alinesno.infra.common.facade.base.BaseDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 功能名： 容器组 数据表： run_pod
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
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

	// getter and setter

	public String getContainerGroupName()

	{
		return this.containerGroupName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public List<RunPodContainerPortDto> getContainertPot() {
		return containertPot;
	}

	public void setContainertPot(List<RunPodContainerPortDto> containertPot) {
		this.containertPot = containertPot;
	}

	public String getServicePorts() {
		return servicePorts;
	}

	public void setServicePorts(String servicePorts) {
		this.servicePorts = servicePorts;
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public BigDecimal getMemLimit() {
		return memLimit;
	}

	public void setMemLimit(BigDecimal memLimit) {
		this.memLimit = memLimit;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImagePullPolicy() {
		return imagePullPolicy;
	}

	public void setImagePullPolicy(String imagePullPolicy) {
		this.imagePullPolicy = imagePullPolicy;
	}

	public String getCpuRequest() {
		return cpuRequest;
	}

	public void setCpuRequest(String cpuRequest) {
		this.cpuRequest = cpuRequest;
	}

	public String getMemRequest() {
		return memRequest;
	}

	public void setMemRequest(String memRequest) {
		this.memRequest = memRequest;
	}

	public Double getCpuPercent() {
		return cpuPercent;
	}

	public void setCpuPercent(Double cpuPercent) {
		this.cpuPercent = cpuPercent;
	}

	public Double getMemoryPercent() {
		return memoryPercent;
	}

	public void setMemoryPercent(Double memoryPercent) {
		this.memoryPercent = memoryPercent;
	}

	public RunPodDto setContainerGroupName(String arg) {
		this.containerGroupName = arg;
		return this;
	}

	public String getNamespaceName()

	{
		return this.namespaceName;
	}

	public RunPodDto setNamespaceName(String arg) {
		this.namespaceName = arg;
		return this;
	}

	public String getStatus()

	{
		return this.status;
	}

	public RunPodDto setStatus(String arg) {
		this.status = arg;
		return this;
	}

	public int getContainerCount()

	{
		return this.containerCount;
	}

	public RunPodDto setContainerCount(int arg) {
		this.containerCount = arg;
		return this;
	}

	public int getRestartCount()

	{
		return this.restartCount;
	}

	public RunPodDto setRestartCount(int arg) {
		this.restartCount = arg;
		return this;
	}

	public BigDecimal getCpuUsage()

	{
		return this.cpuUsage;
	}

	public RunPodDto setCpuUsage(BigDecimal arg) {
		this.cpuUsage = arg;
		return this;
	}

	public BigDecimal getMemoryUsage()

	{
		return this.memoryUsage;
	}

	public RunPodDto setMemoryUsage(BigDecimal arg) {
		this.memoryUsage = arg;
		return this;
	}

	public String getAge()

	{
		return this.age;
	}

	public RunPodDto setAge(String arg) {
		this.age = arg;
		return this;
	}

	public String getPodIpAddress()

	{
		return this.podIpAddress;
	}

	public RunPodDto setPodIpAddress(String arg) {
		this.podIpAddress = arg;
		return this;
	}

	public String getNodeIpAddress()

	{
		return this.nodeIpAddress;
	}

	public RunPodDto setNodeIpAddress(String arg) {
		this.nodeIpAddress = arg;
		return this;
	}

	public BigDecimal getCpuLimit()

	{
		return this.cpuLimit;
	}

	public RunPodDto setCpuLimit(BigDecimal arg) {
		this.cpuLimit = arg;
		return this;
	}

	public BigDecimal getMemoryLimit()

	{
		return this.memoryLimit;
	}

	public RunPodDto setMemoryLimit(BigDecimal arg) {
		this.memoryLimit = arg;
		return this;
	}

	public String getProbeStatus()

	{
		return this.probeStatus;
	}

	public RunPodDto setProbeStatus(String arg) {
		this.probeStatus = arg;
		return this;
	}

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

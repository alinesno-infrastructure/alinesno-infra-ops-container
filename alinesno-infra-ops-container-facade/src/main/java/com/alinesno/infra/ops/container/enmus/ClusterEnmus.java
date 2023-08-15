package com.alinesno.infra.ops.container.enmus;

/**
 * 是否当前集群枚举
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public enum ClusterEnmus {

	CURRENT("1", "当前集群"), NO_CURRENT("0", "非当前集群");

	private String value;
	private String label;

	/**
	 * 构造方法
	 * 
	 * @param value 枚举值
	 * @param label 枚举标签
	 */
	ClusterEnmus(String value, String label) {
		this.value = value;
		this.label = label;
	}

	/**
	 * 获取枚举值
	 * 
	 * @return 枚举值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 获取枚举标签
	 * 
	 * @return 枚举标签
	 */
	public String getLabel() {
		return label;
	}

}

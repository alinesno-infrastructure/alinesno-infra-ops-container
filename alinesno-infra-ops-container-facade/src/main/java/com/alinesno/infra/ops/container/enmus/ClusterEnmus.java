package com.alinesno.infra.ops.container.enmus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 是否当前集群枚举
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum ClusterEnmus {

	CURRENT("1", "当前集群"), NO_CURRENT("0", "非当前集群");

	private final String value;
	private final String label;


}

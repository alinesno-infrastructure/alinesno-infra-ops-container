package com.alinesno.infra.ops.container.k8s;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 分页对象
 * 
 * 该类表示分页数据的对象，包含记录列表、总记录数和当前页码等属性。
 * 
 * @param <T> 记录类型
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@ToString
@Data
public class KubernetesPageBean<T> {

	private List<T> records;
	private int total;
	private int pageNow;

}

package com.alinesno.infra.ops.container.k8s;

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
public class KuberanetesPageBean<T> {

	/**
	 * 记录列表
	 * 
	 * 表示当前页的记录列表。
	 */
	private List<T> records;
	
	/**
	 * 总记录数
	 * 
	 * 表示符合查询条件的总记录数。
	 */
	private int total;
	
	/**
	 * 当前页码
	 * 
	 * 表示当前页的页码。
	 */
	private int pageNow;

	/**
	 * 获取记录列表
	 * 
	 * @return 当前页的记录列表
	 */
	public List<T> getRecords() {
		return records;
	}

	/**
	 * 设置记录列表
	 * 
	 * @param records 当前页的记录列表
	 */
	public void setRecords(List<T> records) {
		this.records = records;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return 符合查询条件的总记录数
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param total 符合查询条件的总记录数
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 获取当前页码
	 * 
	 * @return 当前页的页码
	 */
	public int getPageNow() {
		return pageNow;
	}

	/**
	 * 设置当前页码
	 * 
	 * @param pageNow 当前页的页码
	 */
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	/**
	 * 返回对象的字符串表示形式
	 * 
	 * @return 对象的字符串表示形式
	 */
	@Override
	public String toString() {
		return "KuberanatePageBean [records=" + records + ", total=" + total + ", pageNow=" + pageNow + "]";
	}

}

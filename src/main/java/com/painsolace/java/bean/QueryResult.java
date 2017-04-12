package com.painsolace.java.bean;

import java.util.List;

/**
 * 用于保存分页查询结果，查询总数等
 *
 */
public class QueryResult<T> {

	private List<T> resluts;
	private int totalCount;
	private int start;
	private int pageSize;

	public List<T> getResluts() {
		return resluts;
	}

	public void setResluts(List<T> resluts) {
		this.resluts = resluts;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}

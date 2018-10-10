package com.hx.core.page.asyn;

import java.util.List;

/**
 * 异步分页
 * @author Administrator
 *
 * @param <T>
 */
public class PageResult<T> {
	private long count;// 总记录数
	private List<T> dataList;//数据

	
	
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}

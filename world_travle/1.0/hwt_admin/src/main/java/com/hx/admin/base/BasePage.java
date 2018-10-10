package com.hx.admin.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ro on 2016/4/22.
 * 分页基础对象
 */
public class BasePage implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7162704073858397656L;

	/**
     * 每页显示多少条 初始化20条
     */
    private  Integer pageSize = 10;

    /**
     * 起始条数 默认第一条
     */
    private Integer startNum = 0;
    
    /**
     * 排序
     */
    private String orderBy;

    /**
     * 参数
     */
    private  Map<String,Object> paramMap;

    /**
     * 获取总页数
     * @param pageMax
     * @return
     */
    public  Integer getPageNo(Integer pageMax) {
        Integer pageNo = 1;
        if(pageMax != null && pageMax > 0){
            pageNo = pageMax % pageSize == 0 ? (int) Math.ceil(pageMax/pageSize) : (int) Math.ceil(pageMax/pageSize) + 1;
        }
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getParamMap() {
        if(paramMap == null)
            paramMap = new HashMap<>();
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
    
	public Integer getStartNum() {
		return startNum;
	}
	
	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	
	public String getOrderBy() {
		return orderBy;
	}
	
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}

package com.hx.core.es.entity;

import org.elasticsearch.search.sort.SortOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/6/27 17:30
 * @Description: elasticsearch 查询参数
 */
public class SearchRequestParam implements Serializable {


    /**
     * 索引名称
     */
    private String[] indexes;

    /**
     * 类型名称
     */
    private String[] types;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * 开始时间
     */
    private long startTime = 0;

    /**
     * 结束时间
     */
    private long endTime = 0;

    /**
     * 需要显示的字段
     */
    private String[] fields;

    /**
     * 排序字段
     */
    private Map<String,SortOrder> sortField;

    /**
     * 大小
     */
    private Integer size;

    /**
     * true 使用，短语精准匹配
     */
    private boolean matchPhrase = true;

    /**
     * 高亮字段
     */
    private String[] highlightField;

    /**
     * 过滤条件should （OR）
     */
    private Map<String,Object> shouldStr;
    
    /**
     * 过滤条件should （OR） key有多个值的情况
     */
    private List<KeyValue> shouldStrs;
    
    /**
     * 过滤条件must （AND）
     */
    private Map<String,Object> mustStr;
    
    /**
     * 过滤条件notMust （！=） key有多个值的情况
     */
    private List<KeyValue> notMustStrs;
    
    /**
     * 过滤条件notMust （！=）
     */
    private Map<String,Object> notMustStr;
    
    /**
     * 权重
     * @return
     */
    private Map<String,Float> boots = new HashMap<>();
    
    public Map<String, Float> getBoots() {
		return boots;
	}

	public void setBoots(Map<String, Float> boots) {
		this.boots = boots;
	}

	public String[] getIndexes() {
        return indexes;
    }

    public SearchRequestParam setIndexes(String[] indexes) {
        this.indexes = indexes;
        return this;
    }

    public String[] getTypes() {
        return types;
    }

    public SearchRequestParam setTypes(String[] types) {
        this.types = types;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public SearchRequestParam setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public SearchRequestParam setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public long getStartTime() {
        return startTime;
    }

    public SearchRequestParam setStartTime(long startTime) {
        this.startTime = startTime;
        return this;
    }

    public long getEndTime() {
        return endTime;
    }

    public SearchRequestParam setEndTime(long endTime) {
        this.endTime = endTime;
        return this;
    }

    public String[] getFields() {
        return fields;
    }

    public SearchRequestParam setFields(String[] fields) {
        this.fields = fields;
        return this;
    }

    public Map<String, SortOrder> getSortField() {
        return sortField;
    }

    public SearchRequestParam setSortField(Map<String, SortOrder> sortField) {
        this.sortField = sortField;
        return this;
    }

    public boolean isMatchPhrase() {
        return matchPhrase;
    }

    public SearchRequestParam setMatchPhrase(boolean matchPhrase) {
        this.matchPhrase = matchPhrase;
        return this;
    }

    public String[] getHighlightField() {
        return highlightField;
    }

    public SearchRequestParam setHighlightField(String[] highlightField) {
        this.highlightField = highlightField;
        return this;
    }

   
    public Map<String, Object> getShouldStr() {
		return shouldStr;
	}

	public void setShouldStr(Map<String, Object> shouldStr) {
		this.shouldStr = shouldStr;
	}

	
    public Integer getSize() {
        return size;
    }

    public SearchRequestParam setSize(Integer size) {
        this.size = size;
        return this;
    }

	public Map<String, Object> getMustStr() {
		return mustStr;
	}

	public void setMustStr(Map<String, Object> mustStr) {
		this.mustStr = mustStr;
	}

	public List<KeyValue> getShouldStrs() {
		return shouldStrs;
	}

	public void setShouldStrs(List<KeyValue> shouldStrs) {
		this.shouldStrs = shouldStrs;
	}

	public List<KeyValue> getNotMustStrs() {
		return notMustStrs;
	}

	public void setNotMustStrs(List<KeyValue> notMustStrs) {
		this.notMustStrs = notMustStrs;
	}

	public Map<String, Object> getNotMustStr() {
		return notMustStr;
	}

	public void setNotMustStr(Map<String, Object> notMustStr) {
		this.notMustStr = notMustStr;
	}
	
	
	
}

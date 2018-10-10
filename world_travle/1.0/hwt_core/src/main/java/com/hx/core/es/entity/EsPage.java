package com.hx.core.es.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Ro on 2018/4/25.
 */

public class EsPage implements Serializable{
    /**
	 * 
	 */
	private static final Long serialVersionUID = 1L;
	
	// 指定的或是页面参数
    private Integer currentPage; // 当前页
    private Integer pageSize; // 每页显示多少条

    // 查询es结果
    private Long recordCount; // 总记录数
    private List<Map<String, Object>> recordList; // 本页的数据列表

    // 计算
    private Long pageCount; // 总页数
    private Long beginPageIndex; // 页码列表的开始索引（包含）
    private Long endPageIndex; // 页码列表的结束索引（包含）

    /**
     * 只接受前4个必要的属性，会自动的计算出其他3个属性的值
     *
     * @param currentPage
     * @param pageSize
     * @param recordCount
     * @param recordList
     */
    public EsPage(Integer currentPage, Integer pageSize, Long recordCount, List<Map<String, Object>> recordList) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
        this.recordList = recordList;

        // 计算总页码
        pageCount = ((recordCount + pageSize - 1) / pageSize);

        // 计算 beginPageIndex 和 endPageIndex
        // >> 总页数不多于10页，则全部显示
        if (pageCount <= 10) {
            beginPageIndex = 1l;
            endPageIndex = pageCount;
        }
        // >> 总页数多于10页，则显示当前页附近的共10个页码
        else {
            // 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
            beginPageIndex = (long) (currentPage - 4);
            endPageIndex = (long) (currentPage + 5);
            // 当前面的页码不足4个时，则显示前10个页码
            if (beginPageIndex < 1) {
                beginPageIndex = 1l;
                endPageIndex = 10l;
            }
            // 当后面的页码不足5个时，则显示后10个页码
            if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 10 + 1;
            }
        }
    }


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public List<Map<String, Object>> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Map<String, Object>> recordList) {
        this.recordList = recordList;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getBeginPageIndex() {
        return beginPageIndex;
    }

    public void setBeginPageIndex(Long beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    public Long getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(Long endPageIndex) {
        this.endPageIndex = endPageIndex;
    }


	@Override
	public String toString() {
		return "EsPage [currentPage=" + currentPage + ", pageSize=" + pageSize + ", recordCount=" + recordCount
				+ ", recordList=" + recordList + ", pageCount=" + pageCount + ", beginPageIndex=" + beginPageIndex
				+ ", endPageIndex=" + endPageIndex + "]";
	}
    
    
}

package com.common.util;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/8 11:23
 * @Description:
 */
public class PageUtil {


    private Integer pageSize;

    private Integer index;

    private static PageUtil pageUtil;

    public static PageUtil init(Integer pageNo ,Integer pageSize){
        if (null == pageUtil)
            pageUtil = new PageUtil();
        if (null == pageSize || pageSize <= 0) {
            pageUtil.pageSize = 10;
        }  else {
            pageUtil.pageSize = pageSize;
        }
        if (null == pageNo || pageNo <= 0) {
            pageUtil.index = 0;
        } else {
            pageUtil.index = (pageNo - 1) * pageUtil.pageSize;
        }
        return pageUtil;
    }



    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getIndex() {
        return index;
    }

}

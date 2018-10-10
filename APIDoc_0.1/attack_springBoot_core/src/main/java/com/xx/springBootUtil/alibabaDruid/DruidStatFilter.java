package com.xx.springBootUtil.alibabaDruid;

import com.alibaba.druid.support.http.WebStatFilter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 访问 http://127.0.0.1/druid/index.html 即可查看数据源及SQL统计等
 * @author LiuGang
 */
@WebFilter( filterName="druidWebStatFilter",urlPatterns="/*",
			initParams={
			@WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
	})
public class DruidStatFilter extends WebStatFilter {

}

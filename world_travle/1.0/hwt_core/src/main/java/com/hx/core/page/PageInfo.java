package com.hx.core.page;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * 项目名称：coupon 类名称：PageInfo 类描述： 分页工具类 创建日期：2017年8月2日11:32:05
 * 分页工具 - liugang
 */

@Component
@Scope(value = "prototype")
public class PageInfo {
	private int pageNumber; // 当前页
	private int pageSize; // 每页显示记录
	private String html; // 生成的分页html代码

	public PageInfo() {
		this.pageNumber = 1;
		this.pageSize = 15;
	}
	
	/**
	 * LiuGang
	 * 初始化 - 分页
	 * @param pageNumber - 当前页数
	 * @param pageSize 一次查询的数量
	 */
	public PageInfo(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	/**
	 * LiuGang
	 * 方法说明：格式化生成HTML代码 -- 管理后台模板 后台分页管理
	 * @param totalRow 总页数
	 * @param request 请求
	 */
	public void format(int totalRow, HttpServletRequest request) {
		// String method = request.getMethod();
		String url = request.getRequestURI() + "?";
		Enumeration<String> enums = request.getParameterNames();
		if (!enums.hasMoreElements()) {
			pageNumber = 1;
		}
		while (enums.hasMoreElements()) {
			String key = enums.nextElement();
			if (key.equals("pageNumber")) {
				int value = Integer.parseInt(request.getParameter(key));
				pageNumber = value;
				continue;
			}
			if (key.equals("pageSize")) {
				int value = Integer.parseInt(request.getParameter(key));
				pageSize = value;
				continue;
			}
			if (key.equals("d")) {
				continue;
			}
			String value = request.getParameter(key);
			// if (method!=null&&method.equals("GET")) {
			// try {
			// value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
			// } catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			// }
			// } else {
			// try {
			// value = URLEncoder.encode(value, "UTF-8");
			// } catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			// }
			// }
			url += key + "=" + value + "&";
		}
		url += "d=" + System.nanoTime() + "&";
		int totalPage = (int) Math.ceil((double) totalRow / pageSize); //总页数
		if (totalPage == 0) {
			totalPage = 1;
		}
		StringBuffer value = new StringBuffer();
		
		/**
		 * 
		 *  分页格式
		 * <div class="pagin">
				<div class="message">
					共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页
				</div>
				<ul class="paginList">
					<li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
					<li class="paginItem"><a href="javascript:;">1</a></li>
					<li class="paginItem current"><a href="javascript:;">2</a></li>
					<li class="paginItem"><a href="javascript:;">3</a></li>
					<li class="paginItem"><a href="javascript:;">4</a></li>
					<li class="paginItem"><a href="javascript:;">5</a></li>
					<li class="paginItem more"><a href="javascript:;">...</a></li>
					<li class="paginItem"><a href="javascript:;">10</a></li>
					<li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
				</ul>
		   </div>
		 * 
		 */
		
		if (totalRow > 0) {
			value.append("<div class=\"pagin\">");
			value.append(" <div class='message'>共 <i class=\"blue\">" + totalRow
						+ " </i>条记录，当前显示第&nbsp;<i class=\"blue\">" + pageNumber + "&nbsp;</i>页</div>");
			
			value.append("<ul class=\"paginList\">");
			
			//显示上一页
			if (pageNumber > 1) {
				value.append("<li class='paginItem'><a href=\""
						+  url + "pageNumber=" + (pageNumber - 1)+ "&pageSize=" + pageSize 
						+ "\"><span class='pagepre'></span></a></li>");
			}
			if (totalPage > 5) { // 总页数大于15页 则判断当前页的位置信息
				if (pageNumber > 3) {
					//显示第一页
					value.append("<li class='paginItem'><a href=\""
							+  url + "pageNumber=1&pageSize=" + pageSize 
							+ "\">1</a></li>");
					//显示前面三个点
					if(pageNumber > 4 ){
						value.append("<li class='paginItem more'><a href=\"javascript:;void()\">...</a></li>");
					}
					
					if (totalPage - pageNumber > 1) {
						for (int i = pageNumber - 2; i < pageNumber; i++) {
							if(i == pageNumber){
								value.append("<li class='paginItem current'><a href='" + url + "pageNumber=" + i
										+ "&pageSize=" + pageSize + "'> " + i
										+ " </a></li>");
							}else{
								value.append("<li class='paginItem'><a href='" + url + "pageNumber=" + i
										+ "&pageSize=" + pageSize + "'> " + i
										+ " </a></li>");
							}
							
						}
						for (int i = pageNumber; i <= pageNumber + 2; i++) {
							if(i == pageNumber){
								value.append("<li class='paginItem current'><a href='" + url + "pageNumber=" + i
										+ "&pageSize=" + pageSize + "'> " + i
										+ " </a></li>");
							}else{
								value.append("<li class='paginItem'><a href='" + url + "pageNumber=" + i
										+ "&pageSize=" + pageSize + "'> " + i
										+ " </a></li>");
							}
						}
					} else {
						for (int i = totalPage - 4; i <= totalPage; i++) {
							if(i == pageNumber){
								value.append("<li class='paginItem current'><a href='" + url + "pageNumber=" + i
										+ "&pageSize=" + pageSize + "'> " + i
										+ " </a></li>");
							}else{
								value.append("<li class='paginItem'><a href='" + url + "pageNumber=" + i
										+ "&pageSize=" + pageSize + "'> " + i
										+ " </a></li>");
							}
						}
					}
				} else {
					for (int i = 1; i <= 5; i++) {
						if(i == pageNumber){
							value.append("<li class='paginItem current'><a href='" + url + "pageNumber=" + i
									+ "&pageSize=" + pageSize + "'> " + i
									+ " </a></li>");
						}else{
							value.append("<li class='paginItem'><a href='" + url + "pageNumber=" + i
									+ "&pageSize=" + pageSize + "'> " + i
									+ " </a></li>");
						}
					}
				}
				//显示后三个点
				if (totalPage - pageNumber > 3) {
					value.append("<li class='paginItem more'><a href=\"javascript:;void()\">...</a></li>");
				}
				//显示最后一页
				if (pageNumber + 2 < totalPage) {
					value.append("<li class='paginItem'><a href=\""
							+  url + "pageNumber="+totalPage+"&pageSize=" + pageSize 
							+ "\">"+totalPage+"</a></li>");
				}
			} else {
				for (int i = 1; i <= totalPage; i++) {
					if(i == pageNumber){
						value.append("<li class='paginItem current'><a href='" + url + "pageNumber=" + i
								+ "&pageSize=" + pageSize + "'> " + i
								+ " </a></li>");
					}else{
						value.append("<li class='paginItem'><a href='" + url + "pageNumber=" + i
								+ "&pageSize=" + pageSize + "'> " + i
								+ " </a></li>");
					}
				}
			}
			//显示下一页
			if (pageNumber < totalPage) {
				value.append("<li class='paginItem'><a href=\""
						+  url + "pageNumber=" + (pageNumber + 1)+ "&pageSize=" + pageSize 
						+ "\"><span class='pagenxt'></span></a></li>");
			}
			value.append("</ul></div>");
		} else {
			value.append("");
		}
		this.html = value.toString();
	}
	
	
	
	
	
	/**
	 * 后台模板 联系他们分页
	 * @param totalRow 总数量
	 * @param totalPageTo 当前页
	 * @param request 
	 */
	public void formatAdmin(int totalRow, int totalPageTo, HttpServletRequest request) {
		
		
	}
	
	
	
	
	/**
	 * 生成分页html代码
	 */
	@Override
	public String toString() {
		return html;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		if (pageNumber.matches("\\d{1,}")) {
			this.pageNumber = Integer.parseInt(pageNumber);
			this.pageNumber = this.pageNumber == 0 ? 1 : this.pageNumber;
		} else {
			this.pageNumber = 1;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		if (pageSize.matches("\\d{1,}")) {
			this.pageSize = Integer.parseInt(pageSize);
			this.pageSize = this.pageSize == 0 ? 1 : this.pageSize;
		} else {
			this.pageSize = 15;
		}
	}

	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}

}

/**
 * 自定义tableJS
 * 返回JSON数据格式：
 * {
 * 总数: count
 * 数据源：dataList :[{},{},..]
 * }
 * 
 * 初始化属性设置:
 */
var _myDataTables = {}

var _settings = _myDataTables.settings = {
		ro_pagingList 	: [10,20],	// 每页显示数量选择器
		ro_pageSazi 	: 10,		// 每页显示条数
		ro_currentPage	: 1,		// 当前页码
		ro_pageNum  	: 0,		// 起始条数 第一次默认为0
		ro_queryParams 	: '',		// 列表查询参数对象 和controller参数列表一致 pageSazi pageNum 必须设置
		ro_tatol		: 0,		// 总条数 页面返回时设置
		ro_orderBy		: '',		// 排序字段和规则 如 create_time desc
		ro_requestUrl	: '',		// 请求地址
		ro_method		: 'post',	// 请求类型 post get
		ro_async		: false,	// ajax请求是否异步 true 为异步 false 同步
		ro_callback		: '', 		// ajax回调
		/**
		 * 表格的字段 接收一个对象数组 
		 * 对象：{*filed：,*title:,customHtml:, cssText:, isOrder:, order:} 
		 * filed表示取值属性，title为标题, customHtml为自定义显示内容,cssText 表格的样式,isOrder 是否排序 ,order 排序字段
		 * 例 customHtml: function(rowData,index){return ''} 自定义方法  rowData 行数据对象,index 行索引
		 */ 
		ro_columns		: ''		
}

_myDataTables.paginHmlt = "";
_myDataTables.table = "";

//默认查询参数设置
_settings.ro_queryParams =  function createParams(){
	var params = {};
	params.startNum = _settings.ro_pageSazi * (_settings.ro_currentPage - 1);
	params.pageSize = _settings.ro_pageSazi;
	params.orderBy = _settings.ro_orderBy;
	return params;
}

/**
 * 设置排序
 * @param orderFiled 排序字段
 * @returns
 */
function setOrderBy(orderFiled) {
	var columns = _settings.ro_columns;
	
	// 判断字段组数是否设置
	if(isNotEmpty(columns)) {
		if($.isArray(columns) && columns.length > 0) {
			for (var i = 0; i < columns.length; i++) {
				// 判断是否有字段设置排序功能
				if(isNotEmpty(columns[i].isOrder) && columns[i].isOrder) {
					// 判断是否动态修改排序字段
					if(isNotEmpty(orderFiled)) {
						if(orderFiled == columns[i].filed) {
							if(columns[i].order == columns[i].filed) {
								_settings.ro_orderBy = columns[i].filed + " desc";
							} else {
								_settings.ro_orderBy = columns[i].filed;
							}
							columns[i].order = _settings.ro_orderBy;
							break;
						}
					} else {
						if(columns[i].isOorder == true) {
							_settings.ro_orderBy = columns[i].filed;
							break;
						} 
					}
				}
			}
			if(!isNotEmpty(_settings.ro_orderBy)) 
				_settings.ro_orderBy = columns[0].filed;
		}
	}
	
	
}


/**
 * 加载
 * @param table
 */
function loadDataTable(table) {
	_myDataTables.table = table;
	// 初始化参数
	_settings.ro_currentPage = 1;
	_settings.ro_pageNum = 0;
	// 清除数据
	cleanData(table);
	var returnHtml = requestAjax();
	// 加载table
	$(table).find("tbody").append(returnHtml);
	$(table).parent().find("#div_pagin").remove();
	$(table).parent().append(_myDataTables.paginHmlt);
}

/**
 * 重新加载表格
 */
function reloadTable(table) {
	var a = $(table).find("tbody").find("tr").length;
	if (a==0){
		_settings.ro_currentPage = _settings.ro_currentPage - 1;
		if (_settings.ro_currentPage<=0){
			_settings.ro_currentPage = 1;
		}
	}
	// 清除数据
	cleanData(table);
	// 更新数据
	var returnHtml = requestAjax();
	// 加载table
	$(table).find("tbody").append(returnHtml);
	$(table).parent().find("#div_pagin").remove();
	$(table).parent().append(_myDataTables.paginHmlt);
	updatePageFooter()
}


/**
 * 组装数据
 */
function ajaxCallback(data){
	data = strToJson(data)
	if(!data || data.code != '200') {
		console.log('查询数据异常');
		layer.alert(data.msg,{icon: 6});
	} else {
		var count =  data.data.count;	// 总条数
		var dataList = data.data.dataList; // 数据
		
		_settings.ro_tatol = count;
		_myDataTables.paginHmlt = paginInit(count);
		
		return getDatahtml(dataList,_settings.ro_columns);
	}
}

/**
 * 装填动态数据到table
 * @param dataList
 * @param columns
 * @returns {String} 返回HTML内容
 */
function getDatahtml(dataList, columns) {
	// 循环数据 列循环
	var dataHtml = "";
	for (var i = 0; i < dataList.length; i++) {
		// 行数据
		var rowData = dataList[i];
		var trClass = i%2 == 0 ?  "odd" : "even";
		dataHtml += "<tr role='row' class='"+trClass+"'>";
		// 循环列
		for (var j = 0; j < columns.length; j++) {
			// 表格对于的column对象
			var td = columns[j];
			// 显示文本
			var textContent = "";
			if(isNotEmpty(td.cssText)) {
				dataHtml += "<td class='" + td.cssText + "'>"
			} else {
				dataHtml += "<td>"
			}
			// 组装
			if(isNotEmpty(td.filed)){
				if(isNotEmpty(td.customHtml)) {
					textContent = td.customHtml(rowData,i);
				} else {
					if(isNotEmpty(rowData[td.filed])) {
						
						if("browse_count" == td.filed) {
							// console.info(rowData[td.filed])
						}
						textContent = rowData[td.filed];
					}else
						"";
				}
			}
			else {
				if(isNotEmpty(td.customHtml)) {
					textContent = td.customHtml(rowData,i);
				}
			}
			dataHtml += textContent + "</td>";
		}
		dataHtml += "</tr>"
	}
	
	return dataHtml;
}

/**
 * ajax请求
 * @returns
 */
function requestAjax(){
	console.log(_settings.ro_queryParams())
	if(_settings.ro_requestUrl == null || _settings.ro_requestUrl == '') {
		return console.log('请求地址不能为空!')
	}
	var htmlText = "";
	$.ajax({
		type : _settings.ro_method,
		async : _settings.ro_async,
		url : _settings.ro_requestUrl,
		data: _settings.ro_queryParams(),
		success: function(res){
			if(_settings.ro_callback != null && _settings.ro_callback != '' && _settings.ro_callback != 'undefined') {
				var jsonData = _settings.ro_callback(res);
				htmlText = ajaxCallback(jsonData);
			} else {
				htmlText = ajaxCallback(res);
			}
		}
	})
	return htmlText;
}


/**
 * 清除table数据
 * @param table
 */
function cleanData(table) {
	$(table).find("tbody").empty();
}


/**
 * 判断元素是否为空或undefined
 * @param e
 */
function isNotEmpty(e) {
	if(e != null && e != '' && e != 'undefined' && e != "null") 
		return true;
	else  
		return false;
}

/**
 * 错误信息
 * @param msg
 * @returns
 */
function errorMsg(msg) {
	return console.log(msg)
}


/***************************************************
 ******************  分页设置  ***********************
 *************************************************** 
 * 初始化翻页
 * @param count
 * @returns {String}
 */
// function paginInit(count) {
// 	var paginHtml = "<div class='row' id='div_pagin'>";
// 	if(count == 0) {
// 		return paginHtml += "无查询数据</div>";
// 	}
//
// 	paginHtml += "<div class='col-xs-6'>"
// 		+ "<div class='dataTables_info' id='dynamic-table_page' role='status' aria-live='polite'> "
// 		+ "显示 "+ (_settings.ro_pageNum + 1) +" 到 "+ (count < _settings.ro_pageSazi ? count : _settings.ro_currentPage * _settings.ro_pageSazi) +" 条记录  总记录数 " + count
// 		+ "</div> </div>";
// 	// 计算页数
// 	var pages = count / _settings.ro_pageSazi;
// 	if(pages > parseInt(pages))
// 		pages = parseInt(pages) + 1;
//
// 	paginHtml += "<div class='col-xs-6'>" +
// 			"<div class='dataTables_paginate paging_simple_numbers' id='dynamic-table_paginate'>" +
// 			"<span>" +
// 			// "<a class='paginate_button previous disabled' aria-controls='datatable1' data-dt-idx='0' tabindex='0' id='datatable1_previous' onclick='previousPage(this)'>上一页</a>" +
// 			"<li class='paginate_button previous disabled' id='previous' aria-controls='dynamic-table' tabindex='0' id='dynamic-table_previous'><a href='javascript:void(0);' onclick='previousPage(this)'>上一页</a></li>";
// 	for (var i = 1; i <= pages; i++) {
// 		if(i == 1)
// 			paginHtml += "<li class='paginate_button active' aria-controls='dynamic-table' tabindex='0'><a href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+i+"</a></li>";
// 		else
// 			paginHtml += "<li class='paginate_button' aria-controls='dynamic-table' tabindex='0'><a href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+i+"</a></li>";
// 	}
// 	paginHtml += "<li class='paginate_button next' id='next' aria-controls='dynamic-table' tabindex='0' id='dynamic-table_next'><a href='javascript:void(0);' onclick='nextPage(this,"+pages+")'>下一页</a></li>";
// 	paginHtml += "</ul></div></div></div>";
// 	return paginHtml;
// }


function paginInit(count) {
	var paginHtml = "<div class='row' id='div_pagin' style='position: relative;height: 50px'>";
	if(count == 0) {
		return paginHtml += "无查询数据</div>";
	}

	paginHtml += "<div class='col-xs-6' style='position: absolute; top: 10px; left: 15px'>"
		+ "<div class='dataTables_info' id='dynamic-table_page' role='status' aria-live='polite'> "
		+ "显示 "+ (_settings.ro_pageNum + 1) +" 到 "+ (count < _settings.ro_pageSazi ? count : _settings.ro_currentPage * _settings.ro_pageSazi) +" 条记录  总记录数 " + count
		+ "</div> </div>";
	// 计算页数
	var pages = count / _settings.ro_pageSazi;
	if(pages > parseInt(pages))
		pages = parseInt(pages) + 1;

	paginHtml += "<div class='col-xs-6' style='position: absolute; top: 0%; right: 15px'>" +
		"<div class='dataTables_paginate paging_simple_numbers' id='dynamic-table_paginate'>" 
		// "<a class='paginate_button previous disabled' aria-controls='datatable1' data-dt-idx='0' tabindex='0' id='datatable1_previous' onclick='previousPage(this)'>上一页</a>" +
	if (_settings.ro_currentPage == 1){
		paginHtml += "<a class='paginate_button previous disabled' aria-controls='dynamic-table' tabindex='0' data-dt-idx='0' id='previous' href='javascript:void(0);' >上一页</a>" +
		"<span>";
	}else{
		paginHtml += "<a class='paginate_button previous' aria-controls='dynamic-table' tabindex='0' data-dt-idx='0' id='previous' href='javascript:void(0);' onclick='previousPage(this)'>上一页</a>" +
		"<span>";
	}
	
	
	if (pages > 5){// 总页数大于5页 则判断当前页的位置信息
		if (_settings.ro_currentPage > 3) {
			//显示第一页
			paginHtml += "<a class='paginate_button' aria-controls='dynamic-table' tabindex='0' a href='javascript:void(0);' onclick='buttonPage(this,"+1+")'>"+1+"</a>";
			//显示前面三个点
			if(_settings.ro_currentPage > 4 ){
				paginHtml += "<a class='paginate_button' style='cursor: auto;' aria-controls='dynamic-table' tabindex='0'  href='javascript:void(0);'>...</a>";
			}
			if (pages - _settings.ro_currentPage > 1) {
				for (var i = _settings.ro_currentPage - 2; i < _settings.ro_currentPage; i++) {
					if(i == _settings.ro_currentPage){
						paginHtml += "<a class='paginate_button current' aria-controls='dynamic-table' tabindex='0' a href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+i+"</a>";
					}else{
						paginHtml += "<a class='paginate_button' aria-controls='dynamic-table' tabindex='0' href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+i+"</a>";
					}
					
				}
				for (var i = _settings.ro_currentPage; i <= _settings.ro_currentPage + 2; i++) {
					if(i == _settings.ro_currentPage){
						paginHtml += "<a class='paginate_button current' aria-controls='dynamic-table' tabindex='0' a href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+i+"</a>";
					}else{
						paginHtml += "<a class='paginate_button' aria-controls='dynamic-table' tabindex='0' href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+i+"</a>";
					}
				}
			}else{
				for (var i = pages - 4; i <= pages; i++) {
					if(i == _settings.ro_currentPage){
						paginHtml += "<a class='paginate_button current' aria-controls='dynamic-table' tabindex='0' a href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+i+"</a>";
					}else{
						paginHtml += "<a class='paginate_button' aria-controls='dynamic-table' tabindex='0' href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+i+"</a>";
					}
				}
			}
			
		}else{
			for (var i = 1; i <= 5; i++) {
				if(i == _settings.ro_currentPage){
					paginHtml += "<a class='paginate_button current' aria-controls='dynamic-table' tabindex='0' a href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+i+"</a>";
				}else{
					paginHtml += "<a class='paginate_button' aria-controls='dynamic-table' tabindex='0' href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+i+"</a>";
				}
			}
		}
		//显示后三个点
		if (pages - _settings.ro_currentPage > 3) {
			paginHtml += "<a class='paginate_button' style='cursor: auto;' aria-controls='dynamic-table' tabindex='0' href='javascript:void(0);'>...</a>";
		}
		//显示最后一页
		if (_settings.ro_currentPage + 2 < pages) {
			paginHtml += "<a class='paginate_button' aria-controls='dynamic-table' tabindex='0' href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+pages+"</a>";
		}
	}else {
		for (var i = 1; i <= pages; i++) {
			if(i == _settings.ro_currentPage)
				paginHtml += "<a class='paginate_button current' aria-controls='dynamic-table' tabindex='0' a href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+i+"</a>";
			else
				paginHtml += "<a class='paginate_button' aria-controls='dynamic-table' tabindex='0' href='javascript:void(0);' onclick='buttonPage(this,"+pages+")'>"+i+"</a>";
		}
	}
	if (_settings.ro_currentPage == pages){
		
		paginHtml += "</span><a class='paginate_button next disabled' id='next' aria-controls='dynamic-table' tabindex='0' id='dynamic-table_next' href='javascript:void(0);'>下一页</a>";
	}else{
		paginHtml += "</span><a class='paginate_button next' id='next' aria-controls='dynamic-table' tabindex='0' id='dynamic-table_next' href='javascript:void(0);' onclick='nextPage(this,"+pages+")'>下一页</a>";
	}
	
	paginHtml += "</div></div></div>";
	return paginHtml;
}

/**
 * 下一页
 */
function nextPage(el, pages) {
	if(_settings.ro_currentPage < pages) {

		var li = $("#dynamic-table_paginate").find("a[class='paginate_button current']");
		li.attr("class", "paginate_button");
		li.next().attr("class", "paginate_button current");
		_settings.ro_currentPage = parseInt(li.next().text());
		$("#previous").prop("class", "paginate_button previous")
		if(_settings.ro_currentPage == pages) {
			$("#next").prop("class", "paginate_button next disabled")
		}
		reloadTable(_myDataTables.table)
	}
//	updatePageFooter();
}

/**
 * 上一页
 * @param el
 */
function previousPage(el) {
	var li = $("#dynamic-table_paginate").find("a[class='paginate_button current']");
	if(_settings.ro_currentPage > 1 && parseInt(li.text()) > 1) {
		li.attr("class", "paginate_button");
		li.prev().attr("class", "paginate_button current");
		_settings.ro_currentPage = parseInt(li.prev().text());
		$("#next").prop("class", "paginate_button next");
		if(parseInt(li.prev().text()) == 1)
			$("#previous").prop("class", "paginate_button previous disabled")
		reloadTable(_myDataTables.table)
	}
//	updatePageFooter();
}

/**
 * 任意翻页按钮
 * @param el
 * @param pages
 */

function buttonPage(el, pages) {
	var pageNum = parseInt($(el).text());
	//如果是第一页
	if(pageNum == 1) {
		$("#previous").prop("class", "paginate_button previous disabled")
	} else {
		$("#previous").prop("class", "paginate_button previous")
	}
	//如果是最后一页
	if(pageNum == pages) {
		$("#next").prop("class", "paginate_button next disabled")
	} else {
		$("#next").prop("class", "paginate_button next")
	}
	// 清除之前选中的页码样式
	$("#dynamic-table_paginate").find("a[class='paginate_button current']").prop("class", "paginate_button");
	// 设置当前选中的页码样式
	$(el).prop("class", "paginate_button current");
	_settings.ro_currentPage = pageNum;
	reloadTable(_myDataTables.table);
//	updatePageFooter();
}

/**
 * 修改页脚信息显示
 */
function updatePageFooter() {
	if(_settings.ro_tatol == 0) {
		$("#dynamic-table_page").text("无查询数据")
		$("#dynamic-table_page").css("align", "center");
		return $("#dynamic-table_paginate").html("");
	}
	var startNum = ((_settings.ro_currentPage -1 ) * _settings.ro_pageSazi ) + 1 ;
	var endNum = _settings.ro_tatol < _settings.ro_currentPage * _settings.ro_pageSazi ? _settings.ro_tatol : _settings.ro_currentPage * _settings.ro_pageSazi;
	
	// 修改页脚显示
	$("#dynamic-table_page").text("显示 "+ startNum +" 到 "+ endNum +" 条记录  总记录数 " + _settings.ro_tatol);
}




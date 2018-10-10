
$(function(){
	// 每页显示条数
	_settings.ro_pageSazi = 10;
	// 请求地址
	_settings.ro_requestUrl = "bureau/log/query";
	// 排序设置
	_settings.ro_orderBy = "create_time asc";
	// 显示字段数组
	_settings.ro_columns =  [
        {customHtml : 
        	function(rowData,index){
        		return '<label class="ckbox " > <input type="checkbox" ><span></span> </label>';
        	},
         cssText : "center"
        },
        
        {filed:'logId', 
        	customHtml : function(rowData,index){
        		return rowData.logId;
        	}
        },
        {filed:'real_name', 
        	customHtml : function(rowData,index){
        		return rowData.real_name==null?"":rowData.real_name;
        	}
        },
        
//        {filed:'line_price', 
//        	customHtml : function(rowData,index){
//        		return rowData.line_price==null?"":rowData.line_price	;
//			}
//        },
        {filed:'creat_time',
        	customHtml : function(rowData,index){
        		return rowData.creat_time==null?"":timestampToTime(rowData.creat_time) ;
        	}
        },
        {filed:'ip_address',
        	customHtml : function(rowData,index){
        		return rowData.ip_address==null?"":rowData.ip_address ;
        	}
        },
        {filed:'dec',
        	customHtml : function(rowData,index){
        		return rowData.dec==null?"":rowData.dec ;
        	}
        },
        
       
       
        
        //
        // {filed:'status',
        // 	customHtml : function(rowData,index){
        // 		if(rowData.status == 1) { return "<span class='label label-sm label-default'>待审核</span>"}
        // 		if(rowData.status == 2) { return "<span class='label label-sm label-success'>审核通过</span>"}
        // 		if(rowData.status == 3) { return "<span class='label label-sm label-warning'>审核未通过</span>"}
        // 	}
        // },
       
    ];
	console.info(_settings);
	loadDataTable($("#dynamic-table"));
});
/**
 * 刷新
 */
function reCommit(){
	$("input").val("");
	 var SelectArr = $("select")
	  for (var i = 0; i < SelectArr.length; i++) {
		  SelectArr[i].options[0].selected = true; 
	  }
	  
	sreachCommit()
}
/**
 * 查询
 */
function sreachCommit() {
	paramBuffer();
	// 加载数据
	loadDataTable(_myDataTables.table)
}

/**
 * 组装查询参数
 */
function paramBuffer() {
	_settings.ro_queryParams =  function createParams(){
		var params = {};
		params.startNum = _settings.ro_pageSazi * (_settings.ro_currentPage - 1);
		params.pageSize = _settings.ro_pageSazi;
		params.orderBy = _settings.ro_orderBy;
		params.paramMap = {};
		
		if(isNotEmpty($("#paramMap_line_name").val()))
			alert($("#paramMap_line_name").val())
			params.paramMap.line_name = $("#paramMap_line_name").val();
		
		
		return params;
	}
}

/**
 * 设置排序
 * @param filedName
 */
function setOrder(filedName) {
	// 设置排序字段
	setOrderBy(filedName)
	// 重新加载数据
	reloadTable(_myDataTables.table)
}

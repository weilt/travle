
$(function(){
	// 每页显示条数
	_settings.ro_pageSazi = 5;
	// 请求地址
	_settings.ro_requestUrl = "/jsonList";
	// 排序设置
	_settings.ro_orderBy = "create_time desc";
	// 显示字段数组
	_settings.ro_columns =  [
        {customHtml : 
        	function(rowData,index){
        		return '<label class="ckbox " > <input type="checkbox" ><span></span> </label>';
        	},
         cssText : "center"
        },
        {filed:'id', 
        	customHtml : function(rowData,index){
				return rowData.id;
			}
        },
        {filed:'name', 
        	customHtml : function(rowData,index){
				return rowData.name;
			}
        },
        {filed:'number',
        	customHtml : function(rowData,index){
				return rowData.number;
			}
        },
        {filed:'description', 
        	customHtml : function(rowData,index){
				return rowData.description;
			}
        },
        {filed:'user_id', 
        	customHtml : function(rowData,index){
				return rowData.user_id.user_account;
			}
        },
        {filed:'createTime', 
        	customHtml : function(rowData,index){
				return rowData.createTime;
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
        {title:'操作', customHtml:
        	// 自定义函数 用于显示td的内容 rowData 是该行的数据源 index为行索引
        	function(rowData,index){
				var html = "";
				html += '<a href="#" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>修改</div></a>';
				html += '<a href="#" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>删除</div></a>';
				return html;
        	}
        }
    ];
	console.info(_settings);
	loadDataTable($("#dynamic-table"));
});

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
		alert($("#tname").val())
		if(isNotEmpty($("#start_time").val()))
			params.paramMap.start_time = $("#start_time").val();
		if(isNotEmpty($("#end_time").val()))
			params.paramMap.end_time = $("#end_time").val();
		if(isNotEmpty($("#tname").val()))
			params.paramMap.tname = $("#tname").val();
		if(isNotEmpty($("#tselect").val()))
			params.paramMap.tselect = $("#tselect").val();
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

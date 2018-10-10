
$(function(){
	// 每页显示条数
	_settings.ro_pageSazi = 10;
	// 请求地址
	_settings.ro_requestUrl = "bureau/line/query";
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
        
        {filed:'line_id', 
        	customHtml : function(rowData,index){
        		return rowData.line_id;
        	}
        },
        {filed:'line_name', 
        	customHtml : function(rowData,index){
        		return rowData.line_name==null?"":rowData.line_name;
        	}
        },
        
//        {filed:'line_price', 
//        	customHtml : function(rowData,index){
//        		return rowData.line_price==null?"":rowData.line_price	;
//			}
//        },
        {filed:'create_time',
        	customHtml : function(rowData,index){
        		return rowData.create_time==null?"":timestampToTime(rowData.create_time) ;
        	}
        },
        {filed:'sell_num',
        	customHtml : function(rowData,index){
        		return rowData.sell_num==null?"0":rowData.sell_num ;
        	}
        },
        {filed:'line_type',
        	customHtml : function(rowData,index){
        		return rowData.line_type==null?"":rowData.line_type ;
        	}
        },
        
        {filed:'is_hide', 
        	customHtml : function(rowData,index){
				if (rowData.is_hide==1){
					return '<font color = "red">下架</font>'
				}else {
					return '上架'
				}
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
	        	var html = '';
				html += '<a onclick="line_preview('+rowData.line_id+',\'adminHx/cicerone/info\',\'线路预览--（'+rowData.line_name+'）\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>线路预览</div></a>';
				html += '<a href="javascript:edit('+rowData.line_id+',\'bureau/line/update_query\',\'线路-编辑（'+rowData.line_name+'）\','+1+');" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>修改</div></a>';
				if (rowData.is_hide==1){
					html += '<a onclick="deleteAndRecoveryById(0,'+rowData.line_id+',\'bureau/line/delete\',\'确定要上架线路---'+rowData.line_name+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>上架</div></a>';
				}else {
					html += '<a onclick="deleteAndRecoveryById(1,'+rowData.line_id+',\'bureau/line/recovery\',\'确定要下架线路---'+rowData.line_name+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>下架</div></a>';
				}
				return html;
        	}
        }	
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

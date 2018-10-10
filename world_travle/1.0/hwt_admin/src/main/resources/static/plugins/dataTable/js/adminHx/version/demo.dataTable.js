
$(function(){
	// 每页显示条数
	_settings.ro_pageSazi = 10;
	// 请求地址
	_settings.ro_requestUrl = "adminHx/version/query";
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
       
        {filed:'version_name',
        	customHtml : function(rowData,index){
				return rowData.version_name==null?"":rowData.version_name;
			}
        },
        {filed:'version_number',
        	customHtml : function(rowData,index){
        		return rowData.version_number==null?"":rowData.version_number;
        	}
        },
        {filed:'android_or_ios', 
        	customHtml : function(rowData,index){
				if (rowData.android_or_ios==1){
					return '安卓'
				}else {
					return 'ios'
				}
			}
        },
        {filed:'is_not_minimum', 
        	customHtml : function(rowData,index){
        		if (rowData.is_not_minimum==1){
        			return '<font color = "red">是</font>'
        		}else {
        			return '否'
        		}
        	}
        },
        {filed:'create_time',
        	customHtml : function(rowData,index){
        		return rowData.create_time==null?"":rowData.create_time;
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
				html += '<a onclick="versionInfo('+rowData.id+',\'adminHx/version/versionInfo\',\'版本管理-查看（'+rowData.version_number+'）\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>查看详情</div></a>';
				html += '<a href="javascript:edit('+rowData.id+',\'adminHx/version/update\',\'版本管理-编辑（'+rowData.version_number+'）\','+1+');" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>修改</div></a>';
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
		
	
		if(isNotEmpty($("#paramMap_version_name").val()))
			params.paramMap.version_name = $("#paramMap_version_name").val();
		if(isNotEmpty($("#paramMap_version_number").val()))
			params.paramMap.version_number = $("#paramMap_version_number").val();
		
		
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


$(function(){
	// 每页显示条数
	_settings.ro_pageSazi = 10;
	// 请求地址
	_settings.ro_requestUrl = "adminHx/cicerone_type/query";
	// 排序设置
	_settings.ro_orderBy = "is_hide asc ,create_time desc";
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
        {filed:'type_value', 
        	customHtml : function(rowData,index){
        		return rowData.type_value==null?"":rowData.type_value;
        	}
        },
        

        {filed:'type_description',
        	customHtml : function(rowData,index){
        		return rowData.type_description==null?"":rowData.type_description;
        	}
        },
        {filed:'user_account',
        	customHtml : function(rowData,index){
        		return rowData.user_account==null?"":rowData.user_account;
        	}
        },
    
        {filed:'is_hide', 
        	customHtml : function(rowData,index){
				if (rowData.is_hide==0){
					return '有效'
				}else{
					return '<font color="red">无效</font>'
				}
			}
        },
        {filed:'create_time',
        	customHtml : function(rowData,index){
        		return timestampToTime(rowData.create_time) ;
        	}
        },
        {title:'操作', customHtml:
        	// 自定义函数 用于显示td的内容 rowData 是该行的数据源 index为行索引
        	function(rowData,index){
				var html = '';
				html += '<a onclick="edit('+rowData.id+',\'adminHx/cicerone_type/update\',\'导游分类-编辑（'+rowData.type_value+'）\',1)" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>修改</div></a>';
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
		
	
		if(isNotEmpty($("#paramMap_type_value").val()))
			params.paramMap.type_value = $("#paramMap_type_value").val();
		
		
		
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

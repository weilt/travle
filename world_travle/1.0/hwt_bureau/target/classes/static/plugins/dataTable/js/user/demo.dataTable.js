
$(function(){
	// 每页显示条数
	_settings.ro_pageSazi = 10;
	// 请求地址
	_settings.ro_requestUrl = "adminbureau/user/query";
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
        
       
        {filed:'bureau_user_account', 
        	customHtml : function(rowData,index){
        		
        		
        	
        		return rowData.bureau_user_account==null?"":rowData.bureau_user_account;
        		
			}
        },
        {filed:'real_name',
        	customHtml : function(rowData,index){
        		return rowData.real_name == null?"":rowData.real_name;
        	}
        },
        {filed:'remarks',
        	customHtml : function(rowData,index){
        		return rowData.remarks == null?"":rowData.remarks;
        	}
        },
       
        {filed:'description',
        	customHtml : function(rowData,index){
        		return rowData.description==null?"":rowData.description;
        		
        	}
        },
       
        {filed:'phone', 
        	customHtml : function(rowData,index){
				return rowData.phone == null?"":rowData.phone;
			}
        },
       
        {filed:'is_delete', 
        	customHtml : function(rowData,index){
        		if (rowData.is_delete==2){
        			return '<font color = "red">禁用</font>'
        		}else{
        			return '启用'
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
				//html += '<a href="javascript:edit('+rowData.bureau_user_id+',\'adminbureau/user/update_query\',\'用户管理-编辑（'+rowData.real_name+'）\','+1+');" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>修改</div></a>';
				if (rowData.is_legal!=1){
					
					html+='<a onclick="adpower(this,1,'+rowData.bureau_user_id+',\'adminbureau/user/adpower_qurey\',\'用户授权---'+rowData.real_name+'\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>授权</div></a>'
					if (rowData.is_delete==1){
	        			html += '<a onclick="deleteAndRecoveryById(2,'+rowData.bureau_user_id+',\'adminbureau/user/delete\',\'确定要禁用用户---'+rowData.real_name+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>删除</div></a>';
	        		}else{
	        			html += '<a onclick="deleteAndRecoveryById(1,'+rowData.bureau_user_id+',\'adminbureau/user/recovery\',\'确定要恢复用户---'+rowData.real_name+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>恢复</div></a>';
	        		}
				}
				html+= '<a href="javascript:resetPassword('+rowData.bureau_user_id+',\'adminbureau/user/resetPassword\',\'确定要重置用户'+rowData.real_name+'的密码？\');" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>重置密码</div></a>'
				
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
		
	
		if(isNotEmpty($("#paramMap_bureau_user_account").val()))
			params.paramMap.bureau_user_account = $("#paramMap_bureau_user_account").val();
		if(isNotEmpty($("#paramMap_paramMap_real_name").val()))
			params.paramMap.real_name = $("#paramMap_real_name").val();
	
		if(isNotEmpty($("#paramMap_phone").val()))
			params.paramMap.phone = $("#paramMap_phone").val();
		
		if(isNotEmpty($("#paramMap_is_delete").val()))
			params.paramMap.is_delete = $("#paramMap_is_delete").val();
		
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


$(function(){
	// 每页显示条数
	_settings.ro_pageSazi = 10;
	// 请求地址
	_settings.ro_requestUrl = "admin/user/query";
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
        {filed:'user_id', 
        	customHtml : function(rowData,index){
				return rowData.user_id;
			}
        },
       
        {filed:'user_account', 
        	customHtml : function(rowData,index){
        		
        		
        	
        		return rowData.user_account==null?"":rowData.user_account;
        		
			}
        },
        {filed:'real_name',
        	customHtml : function(rowData,index){
        		return rowData.real_name == null?"":rowData.real_name;
        	}
        },
        {filed:'roleName',
        	customHtml : function(rowData,index){
        		return rowData.roleName == null?"":rowData.roleName;
        	}
        },
        {filed:'sex', 
        	customHtml : function(rowData,index){
        		if (rowData.sex==2){
        			return '女'
        		}else if(rowData.sex==1){
        			return '男'
        		}else{
        			
        			return '保密' ;
        		}
        	}
        },
        {filed:'job_position',
        	customHtml : function(rowData,index){
        		return rowData.job_position==null?"":rowData.job_position;
        		
        	}
        },
       
        {filed:'telephone', 
        	customHtml : function(rowData,index){
				return rowData.telephone == null?"":rowData.telephone;
			}
        },
        {filed:'address', 
        	customHtml : function(rowData,index){
        		return rowData.address == null?"":rowData.address;
        	}
        },
        
        {filed:'isenable', 
        	customHtml : function(rowData,index){
        		if (rowData.isenable==2){
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
				html += '<a href="javascript:edit('+rowData.user_id+',\'admin/user/update\',\'用户管理-编辑（'+rowData.user_account+'）\','+1+');" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>修改</div></a>';
				if (rowData.isenable==1){
        			html += '<a onclick="deleteAndRecoveryById(2,'+rowData.user_id+',\'admin/user/delete\',\'确定要禁用用户---'+rowData.user_account+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>删除</div></a>';
        		}else{
        			html += '<a onclick="deleteAndRecoveryById(1,'+rowData.user_id+',\'admin/user/delete\',\'确定要恢复用户---'+rowData.user_account+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>恢复</div></a>';
        		}
				html+= '<a href="javascript:resetPassword('+rowData.user_id+',\'admin/user/resetPassword\',\'确定要重置用户'+rowData.user_account+'的密码？\');" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>重置密码</div></a>'
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
		
	
		if(isNotEmpty($("#paramMap_user_account").val()))
			params.paramMap.user_account = $("#paramMap_user_account").val();
		if(isNotEmpty($("#paramMap_paramMap_real_name").val()))
			params.paramMap.real_name = $("#paramMap_real_name").val();
	
		if(isNotEmpty($("#paramMap_telephone").val()))
			params.paramMap.telephone = $("#paramMap_telephone").val();
		if(isNotEmpty($("#paramMap_sex").val()))
			params.paramMap.sex = $("#paramMap_sex").val();
		if(isNotEmpty($("#paramMap_isenable").val()))
			params.paramMap.isenable = $("#paramMap_isenable").val();
		
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

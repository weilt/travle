
$(function(){
	// 每页显示条数
	_settings.ro_pageSazi = 10;
	// 请求地址
	_settings.ro_requestUrl = "admin/module/query";
	// 排序设置
	_settings.ro_orderBy = "sort asc";
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
        		
        		
        		var a = $(".breadcrumb-item").length;
        		if (a<4){
        			
        			return '<a href="javascrip:void(0);" onclick="searchByParent('+rowData.id+',\''+rowData.name+'\')">'+rowData.name+'</a>'
        		}else{
        			return rowData.name;
        		}
			}
        },
        {filed:'url',
        	customHtml : function(rowData,index){
				return rowData.url==null?"":rowData.url;
			}
        },
        {filed:'parentId',
        	customHtml : function(rowData,index){
        		if (rowData.parentId == 0){
        			return '顶级模块'
        		}else{
        			var rows = $(".breadcrumb-item");
        			var parentName = $(rows[rows.length-1]).text();
        			return parentName;
        		}
        		
        	}
        },
        {filed:'moduleImage',
        	customHtml : function(rowData,index){
        		return rowData.moduleImage == null?"":rowData.moduleImage;
        	}
        },
        {filed:'description', 
        	customHtml : function(rowData,index){
				return rowData.description== null?"":rowData.description;
			}
        },
        {filed:'sort', 
        	customHtml : function(rowData,index){
				return rowData.sort == null?"":rowData.sort;
			}
        },
        {filed:'isHide', 
        	customHtml : function(rowData,index){
        		if (rowData.isHide==2){
        			return '<font color = "red">是</font>'
        		}
        		return '否' ;
        	}
        },
        {filed:'user_account', 
        	customHtml : function(rowData,index){
        		return rowData.user_account == null?"":rowData.user_account;
        	}
        },
        {filed:'createTime', 
        	customHtml : function(rowData,index){
				return rowData.createTime == null?"":rowData.createTime;
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
				html += '<a href="javascript:edit('+rowData.id+',\'admin/module/update\',\'模块管理-编辑（'+rowData.name+'）\','+1+');" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>修改</div></a>';
				if (rowData.isHide==1){
        			html += '<a onclick="deleteAndRecoveryById(2,'+rowData.id+',\'admin/module/delete\',\'确定要删除模块---'+rowData.name+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>删除</div></a>';
        		}else{
        			html += '<a onclick="deleteAndRecoveryById(1,'+rowData.id+',\'admin/module/delete\',\'确定要恢复模块---'+rowData.name+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>恢复</div></a>';
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
		
	
		if(isNotEmpty($("#paramMap_url").val()))
			params.paramMap.url = $("#paramMap_url").val();
		if(isNotEmpty($("#paramMap_parentId").val()))
			params.paramMap.parentId = $("#paramMap_parentId").val();
		
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

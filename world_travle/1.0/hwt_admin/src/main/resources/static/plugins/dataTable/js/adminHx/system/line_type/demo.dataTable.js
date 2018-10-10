
$(function(){
	// 每页显示条数
	_settings.ro_pageSazi = 10;
	// 请求地址
	_settings.ro_requestUrl = "adminHx/line_type/query";
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
        
        {filed:'attribute_id', 
        	customHtml : function(rowData,index){
        		return rowData.attribute_id;
			}
        },
       
        {filed:'attribute_name',
        	customHtml : function(rowData,index){
				return rowData.attribute_name==null?"":rowData.attribute_name;
			}
        },
        {filed:'attribute_dec',
        	customHtml : function(rowData,index){
        		return rowData.attribute_dec==null?"":rowData.attribute_dec;
        	}
        },
       
       
       
       
        {filed:'is_hide', 
        	customHtml : function(rowData,index){
				if (rowData.is_hide==0){
					return '正常'
				}else{
					return '<font color="red">删除</font>'
				}
			}
        },
        
        {filed:'create_time',
        	customHtml : function(rowData,index){
        		
        			return rowData.create_time==null?"":timestampToTime(rowData.create_time);
        		
        		
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
				if (rowData.is_hide==1){
        			html += '<a onclick="deleteAndRecoveryById(1,'+rowData.attribute_id+',\'adminHx/line_tyep/delete\',\'确定要删除分类---'+rowData.attribute_name+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>删除</div></a>';
        		}else{
        			html += '<a onclick="deleteAndRecoveryById(0,'+rowData.attribute_id+',\'adminHx/line_tyep/delete\',\'确定要恢复分类---'+rowData.attribute_name+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>恢复</div></a>';
        		}
				html += '<a onclick="edit_attribute('+rowData.attribute_id+',\'adminHx/line_tyep/update\',\'线路分类-编辑（'+rowData.attribute_name+'）\',1)" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>修改</div></a>';
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
		
	
		if(isNotEmpty($("#paramMap_attribute_name").val()))
			params.paramMap.attribute_name = $("#paramMap_attribute_name").val();
		
		
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

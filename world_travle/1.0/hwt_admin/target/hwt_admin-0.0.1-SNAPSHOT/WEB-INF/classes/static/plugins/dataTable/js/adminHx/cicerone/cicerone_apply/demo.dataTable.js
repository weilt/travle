
$(function(){
	// 每页显示条数
	_settings.ro_pageSazi = 10;
	// 请求地址
	_settings.ro_requestUrl = "adminHx/cicerone_apply/query";
	// 排序设置
	_settings.ro_orderBy = "status asc";
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
        		return rowData.user_id==null?"":rowData.user_id;
        	}
        },
        {filed:'real_name', 
        	customHtml : function(rowData,index){
        		return rowData.real_name==null?"":rowData.real_name;
        	}
        },
        
//        {filed:'sex', 
//        	customHtml : function(rowData,index){
//				if (rowData.sex==1){
//					return '男'
//				}else if (rowData.sex==2){
//					return '女'
//				}else{
//					return '保密'
//				}
//			}
//        },
        {filed:'account', 
        	customHtml : function(rowData,index){
        		return rowData.account==null?"":rowData.account	;
			}
        },
//        {filed:'phone',
//        	customHtml : function(rowData,index){
//        		
//        			return rowData.phone==null?"":rowData.phone;;
//        		
//        		
//        	}
//        },
//        {filed:'service_count',
//        	customHtml : function(rowData,index){
//        		return rowData.service_count ;
//        	}
//        },
        {filed:'create_time',
        	customHtml : function(rowData,index){
        		return timestampToTime(rowData.create_time) ;
        	}
        },
        {filed:'examine_time',
        	customHtml : function(rowData,index){
        		return rowData.examine_time==null?"":timestampToTime(rowData.examine_time) ;
        	}
        },
        
        {filed:'status', 
        	customHtml : function(rowData,index){
				if (rowData.status==2){
					return '通过'
				}else if (rowData.status==3){
					return '未通过'
				}else{
					return '<font color="red">未审核</font>'
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
				html += '<a onclick="cicerone_info('+rowData.user_id+',\'adminHx/cicerone/info\',\'导游资料-查看（'+rowData.real_name+'）\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>导游资料</div></a>';
//				if (rowData.status==2){
//	    			//html += '<a onclick="frozen(2,'+rowData.id+',\'admin/module/frozen\',\'确定要删除模块---'+rowData.name+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>冻结</div></a>';
//	    			html += '<a onclick="deleteAndRecoveryById(3,'+rowData.id+',\'admin/module/frozen\',\'确定要冻结---'+rowData.real_name+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>查看评论</div></a>';
//	    		}
				/*html += '<a onclick="webpageEject(\'adminHx/user/userFriend?id='+rowData.id+'\',\'用户好友-查看（'+rowData.account+'）\',\'80%\',\'80%\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>用户好友</div></a>';*/
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
		
	
		if(isNotEmpty($("#paramMap_real_name").val()))
			params.paramMap.real_name = $("#paramMap_real_name").val();
		if(isNotEmpty($("#paramMap_service_area_city_name").val()))
			params.paramMap.service_area_city_name = $("#paramMap_service_area_city_name").val();
		
		
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

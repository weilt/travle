
$(function(){
	// 每页显示条数
	_settings.ro_pageSazi = 10;
	// 请求地址
	_settings.ro_requestUrl = "bureau/wallet/bill-query";
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
        
        {filed:'order_num', 
        	customHtml : function(rowData,index){
        		return rowData.trans_num==null?"":rowData.trans_num;
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
        
        
        
        {filed:'bill_type', 
        	customHtml : function(rowData,index){
				if (rowData.bill_type==3){
					return '收入'
				}else if (rowData.bill_type == 5){
					return '提现'
				} else if (rowData.bill_type == 6){
					if (rowData.is_get==0){
						return '违约金'
					}else {
						return '违约金-支出'
					}
				}
			}
        },
        {filed:'is_get', 
        	customHtml : function(rowData,index){
        		var haomiao =  new Date().getTime();
        		if (rowData.bill_type==1){
        			return '待确认'
        		}else if (rowData.bill_type == 2 && haomiao < rowData.start_time){
        			return '待开始'
        		}
        	}
        },
        {filed:'operation_amount', 
        	customHtml : function(rowData,index){
        		
        	
        			return rowData.operation_amount
        			
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
	        	
				html += '<a onclick="bill_info('+rowData.bill_id+',\'bureau/wallet/bill-info\',\'明细详情\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>查看详情</div></a>';
				
				
				
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
		
		if(isNotEmpty($("#paramMap_bill_type").val()))
			params.paramMap.bill_type = $("#paramMap_bill_type").val();
		if(isNotEmpty($("#start_time").val()))
			params.paramMap.start_time = $("#start_time").val();
		if(isNotEmpty($("#end_time").val()))
			params.paramMap.end_time = $("#end_time").val();
		
		
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

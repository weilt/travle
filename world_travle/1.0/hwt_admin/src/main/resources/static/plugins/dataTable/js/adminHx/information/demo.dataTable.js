
$(function(){
	// 每页显示条数
	_settings.ro_pageSazi = 10;
	// 请求地址
	_settings.ro_requestUrl = "adminHx/scenic/query";
	// 排序设置
	_settings.ro_orderBy = "spotId asc";
	// 显示字段数组
	_settings.ro_columns =  [
        {customHtml : 
        	function(rowData,index){
        		return '<label class="ckbox " > <input type="checkbox" ><span></span> </label>';
        	},
         cssText : "center"
        },
        {filed:'spotId', 
        	customHtml : function(rowData,index){
				return rowData.spotId==null?"":rowData.spotId;
			}
        },
        {filed:'spotName', 
        	customHtml : function(rowData,index){
        		
        		return rowData.spotName==null?"":rowData.spotName;
			}
        },
        {filed:'country',
        	customHtml : function(rowData,index){
				return rowData.country==null?"":rowData.country;
			}
        },
        {filed:'city', 
        	customHtml : function(rowData,index){
        		return rowData.city==null?"":rowData.city;
			}
        },
        {filed:'openingHours', 
        	customHtml : function(rowData,index){
				var openingHours =  rowData.openingHours==null||rowData.openingHours=="null"?"":rowData.openingHours;
				
				if (openingHours.length>10){
					var a = openingHours.substring(0,10)
					a+="...";
					var _html='';
					_html+='<p title="'+openingHours+'">'+a+'</p>'
					 return  _html;
				}else{
					return openingHours;
				}
				
			}
        },
        {filed:'visitsNum', 
        	customHtml : function(rowData,index){
        		return rowData.visitsNum==null?"":rowData.visitsNum;
        	}
        },
        {filed:'isHide', 
        	customHtml : function(rowData,index){
        		if (rowData.isHide==1){
        			return '<font color = "red">隐藏</font>'
        		}else{
        			return '正常'
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
				html += '<a onclick="scenicInfo('+rowData.spotId+',\'adminHx/scenic/scenicInfo\',\'景点管理-查看（'+rowData.spotName+'）\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>查看详情</div></a>';
				html += '<a href="javascript:edit('+rowData.spotId+',\'adminHx/scenic/update\',\'景点管理-编辑（'+rowData.spotName+'）\','+1+');" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>修改</div></a>';
				if (rowData.isHide==0){
        			html += '<a onclick="deleteAndRecoveryById(1,'+rowData.spotId+',\'adminHx/scenic/delete\',\'确定要隐藏景点---'+rowData.spotName+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>隐藏</div></a>';
        		}else{
        			html += '<a onclick="deleteAndRecoveryById(0,'+rowData.spotId+',\'adminHx/scenic/delete\',\'确定要恢复景点---'+rowData.spotName+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>恢复</div></a>';
        		}
				if (rowData.isHot==0){
					html += '<a onclick="deleteAndRecoveryById(1,'+rowData.spotId+',\'adminHx/scenic/isHot\',\'确定要热门---'+rowData.spotName+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>热门</div></a>';
				}else{
					html += '<a onclick="deleteAndRecoveryById(0,'+rowData.spotId+',\'adminHx/scenic/isHot\',\'确定要取消热门---'+rowData.spotName+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>取消热门</div></a>';
				}
				if (rowData.isRecommend==0){
					html += '<a onclick="deleteAndRecoveryById(1,'+rowData.spotId+',\'adminHx/scenic/isRecommend\',\'确定要推荐---'+rowData.spotName+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>推荐</div></a>';
				}else{
					html += '<a onclick="deleteAndRecoveryById(0,'+rowData.spotId+',\'adminHx/scenic/isRecommend\',\'确定要取消推荐---'+rowData.spotName+'？\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-trash"></i>取消推荐</div></a>';
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
		params.currentPage = _settings.ro_currentPage;
		params.pageSize = _settings.ro_pageSazi;
		params.orderBy = _settings.ro_orderBy;
		params.paramMap = {};
		
	
		if(isNotEmpty($("#paramMap_city").val()))
			params.paramMap.city = $("#paramMap_city").val();
		if(isNotEmpty($("#paramMap_filed").val()))
			params.paramMap.filed = $("#paramMap_filed").val();
		
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



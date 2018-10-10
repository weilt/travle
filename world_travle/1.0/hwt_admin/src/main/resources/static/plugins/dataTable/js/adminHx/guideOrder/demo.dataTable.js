
$(function(){
    // 每页显示条数
    _settings.ro_pageSazi = 10;
    // 请求地址
    _settings.ro_requestUrl = "admin/order/guideQuery";
    // 排序设置
    _settings.ro_orderBy = "status asc, create_time desc";
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
                return rowData.order_num==null?"":rowData.order_num;
            }
        },
        {filed:'dec',
            customHtml : function(rowData,index){
                return rowData.dec==null?"":rowData.dec;
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
        {filed:'payment',
            customHtml : function(rowData,index){
                return "￥" + (rowData.payment==null?"0.00":rowData.payment) ;
            }
        },


        {filed:'status',
            customHtml : function(rowData,index){
                var haomiao =  new Date().getTime();
                if (rowData.status==1){
                    return '已付款'
                }else if (rowData.status == 2 && haomiao <= rowData.start_time){
                    return '待开始'
                }else if (rowData.status == 2 && haomiao >= rowData.start_time){
                    return '待开始'
                }else if (rowData.status == 3 && haomiao >= rowData.start_time){
                    return '已完成'
                }
                // else if (rowData.status == 3){
                // 	return '已完成'
                // }else if ((rowData.status == 4 || rowData.status == 5) && rowData.apply_sales_time != null){
                // 	return '已退款'
                // }else if ((rowData.status == 4 || rowData.status == 5) && rowData.apply_sales_time == null){
                // 	return '已取消'
                // }
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

                    html += '<a onclick="order_details('+rowData.order_id+',\'admin/order/details\',\'订单详情\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>订单详情</div></a>';

                    // if (rowData.status==1){
                    // 	html += '<a onclick="order_confirm('+rowData.order_id+',\'bureau/order/confirm\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>确认订单</div></a>';
                    // 	html += '<a onclick="order_refuse('+rowData.order_id+',\'bureau/order/refuse\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>拒绝订单</div></a>';
                    // }
                    //
                    // if (rowData.status == 2 && new Date().getTime() < rowData.start_time){
                    // 	html += '<a onclick="order_cancel('+rowData.order_id+',\'bureau/order/cancel\')" href="javascrip:void(0);" class="btn btn-sm btn-outline-primary mg-r-5 pd-b-5"><div><i class="fa fa-pencil"></i>取消订单</div></a>';
                    // }

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

        if(isNotEmpty($("#paramMap_status").val()))
            params.paramMap.status = $("#paramMap_status").val();
        if(isNotEmpty($("#paramMap_order_num").val()))
            params.paramMap.order_num = $("#paramMap_order_num").val();
        if(isNotEmpty($("#paramMap_filde").val()))
            params.paramMap.filde = $("#paramMap_filde").val();


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

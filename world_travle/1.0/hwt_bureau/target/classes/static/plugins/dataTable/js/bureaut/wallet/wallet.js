/**
 * 发起提现
 */
function put_forward(){
	if (validation(["forward"],[],[])){
		$.ajax({
	        url : "bureau/wallet/put_forward",
	        type : "POST",
	        data : {forward:$("#forward").val()},
	        success : function (res) {
	        	res = strToJson(res);
	        	jsonState(res);
	            if (res.code =="200"){
	            	layer.alert(res.msg,{icon: 6},function(){
	            		layer.closeAll();
	            		jumpToPage('bureau/wallet/my',this);
	            	});
	            }else{
	            	if(res.msg!=null){
	            		layer.alert(res.msg,{icon: 6});
	            	}else{
	            		layer.alert('调试错误',{icon: 6});
	            	}
	            }
	        }
	    })
		
		
	}else{
		return
	}
}

/**
 * 明细详情
 * @param bill_id
 * @param url
 * @param dec
 */
function bill_info(bill_id, url ,dec){
	$.ajax({
        url : url,
        type : "POST",
        data : {bill_id:bill_id},
        success : function (res) {
        	res = strToJson(res);
        	jsonState(res);
            if (res.code =="200"){
            	var operation_amount = res.data.operation_amount;
            	var balance = res.data.balance;
            	var trans_num = res.data.trans_num;
            	var create_time = res.data.create_time==null?"":timestampToTime(res.data.create_time) ;
            	var bill_type ='';
            	if (res.data.bill_type==3){
            		bill_type =  '收入'
				}else if (res.data.bill_type == 5){
					bill_type =  '提现'
				} else if (res.data.bill_type == 6){
					if (rowData.is_get==0){
						bill_type =  '违约金'
					}else {
						bill_type =  '违约金-支出'
					}
				}
            	
            	var remarks = res.data.remarks;
            	var _html = '';
            	_html+='<div class="money_bigBox">                    '
            	+'	<div class="money_head">                    '
            	+'		明细详情                                '
            	+'	</div>                                      '
            	+'	                                            '
            	+'	<div class="money_content">                 '
            	+'		<div class="money_minBox">              '
            	+'			<div class="money_line">            '
            	+'				<span>操作金额</span>           '
            	+'				<span>'+operation_amount+'</span>             '
            	+'			</div>                              '
            	+'			<div class="money_line">            '
            	+'				<span>类型：</span>             '
            	+'				<span>'+bill_type+'</span>               '
            	+'			</div>                              '
            	+'			<div class="money_line">            '
            	+'				<span>时间：</span>             '
            	+'				<span>'+create_time+'</span'
            	+'			</div>                              '
            	+'			<div class="money_line">            '
            	+'				<span>交易单号：</span>         '
            	+'				<span>'+trans_num+'</span> '
            	+'			</div>                              '
            	+'			<div class="money_line">            '
            	+'				<span>余额：</span>             '
            	+'				<span>'+balance+'</span>             '
            	+'			</div>                              '
            	+'			<div class="money_line">            '
            	+'				<span>备注：</span>             '
            	+'				<span>'+remarks+'</span>   '
            	+'			</div>                              '
            	+'		</div>                                  '
            	+'	</div>                                      '
            	+'</div>                                        '
            
            	fromEject(_html,dec,'80%','80%');
            }else{
            	if(res.msg!=null){
            		layer.alert(res.msg,{icon: 6});
            	}else{
            		layer.alert('调试错误',{icon: 6});
            	}
            }
        }
    })
}

/**
 * 选择状态
 * @param status
 */
function choose_status(status,a) {
	$(".choose-zhl").removeClass("choose-zhl");
	$(a).addClass("choose-zhl")
	status = status==null||status=='undefined'?0:status;
	$("#paramMap_bill_type").val(status);
	sreachCommit();
}
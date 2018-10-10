
/**
 * 选择状态
 * @param status
 */
function choose_status(status,a) {
	$(".choose-zhl").removeClass("choose-zhl");
	$(a).addClass("choose-zhl")
	status = status==null||status=='undefined'?0:status;
	$("#paramMap_status").val(status);
	sreachCommit();
}

/**
 * 订单详情
 * @param order_id
 * @param url
 * @param dec
 */
function order_details(order_id, url, dec){
	
	$.ajax({
        url : url,
        type : "POST",
        data : {order_id:order_id},
        success : function (res) {
        	res = strToJson(res);
        	jsonState(res);
            if (res.code =="200"){
            	var timestamp =new Date().getTime();
            	
            	var orderDetailsVo = res.data;
            	
            	var hwOrderVo = orderDetailsVo.hwOrderVo;
            	var order_id = hwOrderVo.order_id;
            	var hwOrderUserVos = orderDetailsVo.hwOrderUserVos;
            	var hwOrderUserVos_="";
            	for (var i = 0; i < hwOrderUserVos.length; i++) {
            		hwOrderUserVos_ +='<div class="order_line">姓名：'+hwOrderUserVos[i].name+'<span>';
            		var Identity_type = '身份证';
            		var identity_type=hwOrderUserVos[i].identity_type;
            		switch (identity_type) {
                    case ("ID_card"):
                    	Identity_type = '身份证'
                        break;
                    case ("passport"):
                    	Identity_type = '护照'
                        break;
                    case ("Officer_card"):
                    	Identity_type ="军官证"
                        break;
                    case ("Reentry_permit"):
                    	Identity_type ="回乡证"
                    	break;
                    case ("Taiwan_card"):
                    	Identity_type ="台胞证"
                    	break;
                    case ("Hong_Kong_Macau_laissez_passer"):
                    	Identity_type ="港澳通行证"
                    	break;
                   
                    case ("Taiwan_pass"):
                    	Identity_type ="台湾通行证"
                    	break;
                    
                    default:
                }
            		hwOrderUserVos_ +=Identity_type+'：'+hwOrderUserVos[i].identity_num+'</span></div>';
				}
            	var hxOrderInfoVo = orderDetailsVo.hxOrderInfoVo;
            	var hwOrderRefundVo = orderDetailsVo.hwOrderRefundVo;
            	var business_remarks = null;
            	if (hwOrderRefundVo!=null){
            		
            		business_remarks = hwOrderRefundVo.business_remarks;
            	}
            	var start_time = hwOrderVo.start_time;
            	var end_time = hwOrderVo.end_time;
            	var day = Math.round((end_time-start_time)/(24*60*60*1000));
            	var status = hwOrderVo.status;
            	var Status = "待确认";
            	var create_time = hwOrderVo.create_time==null?"":timestampToTime(hwOrderVo.create_time);
            	var payment_time =  hwOrderVo.payment_time==null?"":timestampToTime(hwOrderVo.payment_time);
            	var paymen_type = hwOrderVo.paymen_type;
            	var Paymen_type = '';
            	switch (paymen_type) {
                case (1):
                	Paymen_type ="支付宝"
                    break;
               
                case (2):
                	
                	Paymen_type ="微信"
                
                	break;
                case (3):
                	Paymen_type ="零钱"
                	break;
               
                default:
                	Paymen_type = "其他"
            }
            	var Start_time = (start_time==null?"":timestampToTime(start_time)).split(' ')[0];
            	var adult_num = hwOrderVo.adult_num;
            	var children_num = hwOrderVo.children_num;
            	var dec = hxOrderInfoVo.dec;
            	var end_ = '';
            	end_+='	<div class="btn">                                                                                 '
        		+'		<div>                                                                                         '
        		
        		
            	switch (status) {
                case (1):
                	
                	end_+='			<div class="btns" onclick="order_confirm('+order_id+',\'bureau/order/confirm\')">确认订单</div>                                                          '
                		+'			<div class="btns" onclick="order_refuse('+order_id+',\'bureau/order/refuse\')">拒绝订单</div>                                                          '
                    break;
                case (2):
                	if (start_time > timestamp){
                		end_+='			<div class="btns" onclick="order_cancel('+order_id+',\'bureau/order/cancel\')">取消订单</div>                                                          '
            			Status ="待开始"
            		}else {
            			Status ="进行中"
            		}
                    break;
                case (3):
                	Status ="已完成"
                    break;
                case (4):
                	Status ="退款中"
                	break;
                case (5):
                	if (business_remarks!=null){
                		Status ="已退款"
                	}else {
                		Status ="已取消"
                	}
                	break;
                case (6):
                	Status ="已取消"
                	break;
               
                default:
            }
            	end_+='		</div>                                                                                        '
        		+'	</div>                                                                                            '
            	
            	var _html = ' <link rel="stylesheet" href="/plugins/dataTable/js/bureaut/tourism/css/order.css">';
            	_html+='<div class="bigBox">                                                                                '
        		+'	<div class="head">      订单详情                                                                          '
        		+'		<div class="head-left">(状态：'+Status+')</div>                                                   '
        		+'	</div>                                                                                            '
        		+'	                                                                                                  '
        		+'	<div class="content">                                                                             '
        		+'		<div class="div1">                                                                            '
        		+'			<h1>需求信息</h1>                                                                         '
        		+'			<div class="order_line">订单编号：2018722123456<span>下单时间：'+create_time+'</span></div> '
        		+'			<div class="order_line">线路：'+dec+'</div>         '
        		+'			<div class="order_line">成人数：'+adult_num+'人<span>儿童数：'+children_num+'人</span></div>                                 '
        		+'			<div class="order_line">出发日期：'+Start_time+'<span>天数：'+day+'天</span></div>               '
        		+'			<div class="order_line">其他需求：无</div>                                                      '
        		+'		</div>                                                                                        '
        		+'		<div class="div1">                                                                            '
        		+'			<h1>联系信息</h1>                                                                         '
        		+'			<div class="order_line">姓名：'+hwOrderVo.buyer_name+'<span>手机：'+hwOrderVo.buyer_phone+'</span></div>                      '
        		+'			<div class="order_line">走吧号：无</div>                                                        '
        		+'		</div>                                                                                        '
        		+'		<div class="div1">                                                                            '
        		+'			<h1>出行人信息</h1>                                                                       '
        		+hwOrderUserVos_
        		+'		</div>                                                                                        '
        		+'		<div class="div1">                                                                            '
        		+'			<h1>付款信息</h1>                                                                         '
        		+'			<div class="order_line">付款日期：'+payment_time+'<span>付款方式：'+Paymen_type+'</span></div>'
        		+'			<div class="order_line">实付金额：￥'+hwOrderVo.payment+'<span>订单编号：'+hwOrderVo.order_num+'</span></div>                '
        		+'		</div>                                                                                        '
        		+'	</div>                                                                                            '
        		+'	                                                                                                  '
        		
        		
        		
        		
        		_html+=end_+'</div>'
            
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
 * 订单确认
 */
function order_confirm(order_id, url){
	$.ajax({
        url : url,
        type : "POST",
        data : {order_id:order_id},
        success : function (res) {
        	res = strToJson(res);
        	jsonState(res);
            if (res.code =="200"){
            	layer.alert(res.msg,{icon: 6},function(){
            		layer.closeAll();
            		reloadTable($("#dynamic-table"))
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
}

/**
 * 订单拒绝
 */
function order_refuse(order_id, url){
	reson(order_id,"订单拒绝",url)
}

/**
 * 订单取消
 */
function order_cancel(order_id, url){
	reson(order_id,"订单取消",url)
}

/**
 * 原因弹出
 * @param order_id
 * @param dec
 * @param url
 */
function reson(order_id,dec,url){
	var _html = '<form  id="form">                                                                                                                                                                                         '
		+'      <div class="br-pagebody">                                                                                                                                                                           '
		+'                                                                                                                                                                                                          '
		+'        <div class="br-section-wrapper">                                                                                                                                                                  '
		+'            <!--内容-->                                                                                                                                                                                   '
		+'            <div class="row">                                                                                                                                                                             '
		+'                <p class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>输入原因:</p>                                                                          '
		+'                <div class="col-lg mg-l-10">                                                                                                                                                              '
		+'                    <input class="form-control wd-250" placeholder="输入原因" id="business_remarks" name="business_remarks" type="text">                                                                                                                '
		+'                </div><!-- col -->                                                                                                                                                                        '
		+'                                                                                                                                                                                                          '
		+'            </div>                                                                                                                                                                                        '
		+'		              <input id="order_id" name="order_id" type="hidden" value='+order_id+'>                                                                                                                                                                                      '
		+'                                                                                                                                                                                                          '
		+'            <div class="row mg-t-20" >                                                                                                                                                                    '
		+'                <div class="btn btn-secondary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15" onclick="cancelFormLayer()" >取消</div>                                                                  '
		+'                <div class="btn btn-outline-primary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15 mg-l-10" onclick="sure_order(\''+url+'\',\'form\')">确定</div>    '
		+'            </div>                                                                                                                                                                                        '
		+'                                                                                                                                                                                                          '
		+'        </div>                                                                                                                                                              '
		+'      </div>                                                                                                                                                                         '
		+'</form>'
		fromEject(_html,dec,'50%','50%');
}

/**
 * 确定
 */
function sure_order(url,form){
	
	if (validation(["business_remarks","order_id"],[],[])){
		
		sub_order(url,form);
		
	}else{
		return
	}
	
}
/**
 * 提交操作
 */
function sub_order(url,form){
	$.ajax({
        url : url,
        type : "POST",
        data : $("#"+form).serialize(),
        success : function (res) {
        	res = strToJson(res);
        	jsonState(res);
            if (res.code =="200"){
            	layer.alert(res.msg,{icon: 6},function(){
            		layer.closeAll();
            		reloadTable($("#dynamic-table"))
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
}

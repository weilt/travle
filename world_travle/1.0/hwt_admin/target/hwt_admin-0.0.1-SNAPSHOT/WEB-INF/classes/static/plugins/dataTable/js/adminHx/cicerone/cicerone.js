
/**
 * 导游资料查看
 */
function cicerone_info(id,url,dec){
	$.ajax({
        url : url,
        type : "POST",
        data :{"user_id":id},
        success : function (res) {
        	res = strToJson(res);
            if (res.code =="200"){
            	var row = res.data;
            	var user_id = row.user_id;
            	var real_name = row.real_name==null?"":row.real_name;
            	var sex = row.sex;
            	var birthday = row.birthday==null?"":timestampToTime(row.birthday).split(" ")[0];
            	var cover = row.cover==null?"":row.cover;
            	var about = row.about==null?"":row.about;
            	var phone = row.phone==null?"":row.phone;
            	var identity_no = row.identity_no==null?"":row.identity_no;
            	var identity_positive = row.identity_positive==null?"":row.identity_positive;
            	var identity_negative = row.identity_negative==null?"":row.identity_negative;
            	var identity_hold = row.identity_hold==null?"":row.identity_hold;
            	var service_area_city_name = row.service_area_city_name==null?"":row.service_area_city_name;
            	var service_count = row.service_count;
            	var score = row.score;
            	var cicerone_type = row.cicerone_type==null?"":row.cicerone_type;
            	var autograph = row.autograph==null?"":row.autograph;
            	var status = row.status;
            	var reason = row.reason==null?"":row.reason;
            	var create_time = row.create_time==null?"":timestampToTime(row.create_time);
            	var examine_time = row.examine_time==null?"":timestampToTime(row.examine_time);
            	var account = row.account;
            	var account_phone = row.account_phone;
            	
            	var _html='';
            	_html += '<form >                                                                                                                                                       '
            	+'      <div class="br-pagebody">                                                                                                                               '
            	+'                                                                                                                                                              '
            	+'        <div class="br-section-wrapper">                                                                                                                      '
            	+'            <!--内容-->                                                                                                                                       '
            	+'            <div class="row">                                                                                                                                 '
            	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>用户账号:</p>  '
            	+'                <div class="col-lg mg-l-10">                                                                                                                  '
            	+'                    <input class="form-control wd-250" id="account" readonly="readonly" type="text" value="'+account+'">                                                 '
            	+'                </div><!-- col -->                                                                                                                            '
            	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>用户注册手机号:</p>'
            	+'                <div class="col-lg mg-l-10">                                                                                                                  '
            	+'                     <input class="form-control wd-250" id="account_phone"   readonly="readonly" type="text" value="'+account_phone+'">                                        '
            	+'                </div><!-- col -->                                                                                                                            '
            	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>用户姓名:</p>  '
            	+'                <div class="col-lg mg-l-10">                                                                                                                  '
            	+'                     <input class="form-control wd-250" id="real_name"  readonly="readonly" type="text" value="'+real_name+'">                                             '
            	+'                </div><!-- col -->                                                                                                                            '
            	+'            </div>                                                                                                                                            '
            	+'            <div class="row mg-t-20">                                                                                                                         '
            	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>申请时间:</p>                          '
            	+'                <div class="col-lg mg-l-10">                                                                                                                  '
            	+'                    <input class="form-control wd-50%" id="create_time" readonly="readonly" type="text" value="'+create_time+'">                                             '
            	+'                </div><!-- col -->                                                                                                                            '
            	+'            </div>                                                                                                                                            '
            	+'           <div class="row mg-t-20">                                                                                                                          '
            	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>处理时间:</p>                          '
            	+'                <div class="col-lg mg-l-10">                                                                                                                  '
            	+'                    <input class="form-control wd-50%" id="examine_time"  readonly="readonly" type="text" value="'+examine_time+'">                                           '
            	+'                </div><!-- col -->                                                                                                                            '
            	+'            </div>                                                                                                                                            '
            	+'            <div class="row mg-t-20">                                                                                                                         '
            	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>身份证正面:</p>                        '
            	+'               <div class="col-lg mg-l-10">                                                                                                                   '
            	+'    	           <a href="javascrip:void(0);" onclick="catImage(\''+identity_positive+'\')"><img src="'+identity_positive+'" id="identity_positive" height="80px" width="80px"/></a>                                                               '
            	+'    	       </div>                                                                                                                                           '
            	+'            </div>                                                                                                                                            '
            	+' 			<div class="row mg-t-20">                                                                                                                           '
            	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>身份证反面:</p>                        '
            	+'                 <div class="col-lg mg-l-10">                                                                                                                 '
            	+'    	           <a href="javascrip:void(0);" onclick="catImage(\''+identity_negative+'\')"><img src="'+identity_negative+'" id="identity_negative" height="80px" width="80px"/></a>                                                               '
            	+'    	       </div>                                                                                                                                           '
            	+'            </div>                                                                                                                                            '
            	+'            <div class="row mg-t-20">                                                                                                                         '
            	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>手持身份证:</p>                        '
            	+'                <div class="col-lg mg-l-10">                                                                                                                  '
            	+'    	           <a href="javascrip:void(0);" onclick="catImage(\''+identity_hold+'\')"><img src="'+identity_hold+'" id="identity_hold" height="80px" width="80px"/></a>                                                                   '
            	+'    	       </div>                                                                                                                                           '
            	+'            </div>                                                                                                                                            '
            	
            	if (cicerone_type!=""){
            		_html+='            <div class="row mg-t-20">                                                                                                                         '
                	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>导游类型:</p>                          '
                	+'                <div class="col-lg mg-l-10">                                                                                                                  '
                	+'                    <input class="form-control wd-50%" id="cicerone_type" readonly="readonly" type="text" value="'+cicerone_type+'">                                             '
                	+'                </div><!-- col -->                                                                                                                            '
                	+'            </div> '         
            	}
            	if (service_area_city_name!=""){
            		_html+='            <div class="row mg-t-20">                                                                                                                         '
            			+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>服务城市:</p>                          '
            			+'                <div class="col-lg mg-l-10">                                                                                                                  '
            			+'                    <input class="form-control wd-50%" id="service_area_city_name" readonly="readonly" type="text" value="'+service_area_city_name+'">                                             '
            			+'                </div><!-- col -->                                                                                                                            '
            			+'            </div> '         
            	}
            	_html+='            <div class="row  mg-t-20">                                                                                                                                 '
            	
            	
            		+'                <p style="width: 79.12px;" style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>用户性别:</p>  '
            	+'                <div class="col-lg mg-l-10">                                                                                                                  '
            	+'                    <input class="form-control wd-250" id="sex" readonly="readonly" type="text" value="'
            	var SEX;
            	if(sex==0){
            		SEX = "保密"
            	}else if(sex==1){
            		SEX = "男"
            	}else{
            		SEX = "女"
            	}
            	
            	_html+=SEX;
            	_html+='">                                                 '
            	+'                </div><!-- col -->                                                                                                                            '
            	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>用户生日:</p>'
            	+'                <div class="col-lg mg-l-10">                                                                                                                  '
            	+'                     <input class="form-control wd-250" id="birthday"   readonly="readonly" type="text" value="'+birthday+'">                                        '
            	+'                </div><!-- col -->                                                                                                                            '
            	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>导游电话:</p>  '
            	+'                <div class="col-lg mg-l-10">                                                                                                                  '
            	+'                     <input class="form-control wd-250" id="phone"  readonly="readonly" type="text" value="'+phone+'">                                             '
            	+'                </div><!-- col -->                                                                                                                            '
            	+'            </div>     '   
            	_html+='            <div class="row  mg-t-20">                                                                                                                                 '
            	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>服务次数:</p>  '
            	+'                <div class="col-lg mg-l-10">                                                                                                                  '
            	+'                    <input class="form-control wd-250" id="service_count" readonly="readonly" type="text" value="'+service_count+'">                                                 '
            	+'                </div><!-- col -->                                                                                                                            '
            	+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>点评分数（总数）:</p>'
            	+'                <div class="col-lg mg-l-10">                                                                                                                  '
            	+'                     <input class="form-control wd-250" id="score"   readonly="readonly" type="text" value="'+score+'">                                        '
            	+'                </div><!-- col -->                                                                                                                            '
            	+'            </div>         '   
            	if (cover!=""){
            		_html+='            <div class="row mg-t-20">                                                                                                                         '
            			+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>封面:</p>                          '
            			+'                <div class="col-lg mg-l-10">                                                                                                                   '
                    	+'    	           <a href="javascrip:void(0);" onclick="catImage(\''+cover+'\')"><img src="'+cover+'" id="cover" height="80px" width="80px"/></a>                                                               '
                    	+'    	       </div>   ' 
            			+'            </div> '         
            	}
            	if (autograph!=""){
            		_html+='            <div class="row mg-t-20">                                                                                                                         '
            			+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>个性签名:</p>                          '
            			+'                <div class="col-lg mg-l-10">                                                                                                                  '
            			+'                    <input class="form-control wd-50%" id="autograph" readonly="readonly" type="text" value="'+autograph+'">                                             '
            			+'                </div><!-- col -->                                                                                                                            '
            			+'            </div> '         
            	}
            	if (about!=""){
            		_html+='            <div class="row mg-t-20">                                                                                                                         '
            			+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>他的故事:</p>                          '
            			+'                <div class="col-lg mg-l-10">                                                                                                                  '
            			+about
            			+'                </div><!-- col -->                                                                                                                            '
            			+'            </div> '         
            	}
            	if(status == 1){
            		_html+='            <div class="row mg-t-20">                                                                                                                         '
            			+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>未通过原因:</p>                          '
            			+'                <div class="col-lg mg-l-10">                                                                                                                  '
            			+'                    <input class="form-control wd-50%" id="reason"  type="text" value="">                                             '
            			+'                </div><!-- col -->                                                                                                                            '
            			+'            </div> ' 
            			 +'            <div class="row mg-t-20">                                                                                                                                                           '
            			 +'                <div class="btn btn-secondary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15" onclick="cancelFormLayer()">关闭</div>                                   '
	                		+'                <div class="btn btn-secondary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15" onclick="cicerone_verification(\'adminHx/cicerone_apply/verification\','+user_id+',\'审核未通过\',3)">审核未通过</div>                                   '
	                		+'                <div class="btn btn-outline-primary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15 mg-l-10" onclick="cicerone_verification(\'adminHx/cicerone_apply/verification\','+user_id+',\'审核通过\',2)">审核通过</div>'
	                		+'                                                                                                                                                                                                '
	                		+'            </div>  '               
            	}else if (status == 3){
            		_html+='            <div class="row mg-t-20">                                                                                                                         '
            			+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>未通过原因:</p>                          '
            			+'                <div class="col-lg mg-l-10">                                                                                                                  '
            			+'                    <input class="form-control wd-50%" id="reason" readonly="readonly" type="text" value="'+reason+'">                                             '
            			+'                </div><!-- col -->                                                                                                                            '
            			+'            </div> ' 
            			 +'            <div class="row mg-t-20">                                                                                                                                                           '
            			 +'                <div class="btn btn-secondary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15" onclick="cancelFormLayer()">关闭</div>                                   '
            			 +'            </div>  '  
            	}
            	_html+=' </div><!-- br-section-wrapper --></div><!-- br-pagebody --> </form>';
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
 * 审核
 * @param url
 * @param user_id
 * @param msg
 * @param status
 */
function cicerone_verification(url,user_id,msg,status){
	
	if (status==3){
		if(!(validation(["reason"],[],["审核不通过必须填写原因"]))){
			return;
		}
	}
	
	$.ajax({
        url : url,
        type : "POST",
        data :{"user_id":user_id,"msg":msg,"status":status,"reason":$("#reason").val()},
        success : function (res) {
        	res = strToJson(res);
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


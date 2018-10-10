
/**
 * 导游资料查看
 */
function bureau_info(id,url,dec){
	$.ajax({
        url : url,
        type : "POST",
        data :{"bureau_id":id},
        success : function (res) {
        	res = strToJson(res);
            if (res.code =="200"){
            	var row = res.data;
            	var bureau_id = row.bureau_id;
            	var company_name = row.company_name;
            	var license_no = row.license_no==null?"":row.license_no;
            	var legal_person = row.legal_person==null?"":row.legal_person;
            	var legal_person_phome = row.legal_person_phome==null?"":row.legal_person_phome;
            	var registered_capital = row.registered_capital==null?"":row.registered_capital;
            	var company_time = row.company_time==null?"":timestampToTime(row.company_time);
            	var licen_validity_begin = row.licen_validity_begin==null?"":timestampToTime(row.licen_validity_begin);
            	var licen_validity_end = row.licen_validity_end==null?"":timestampToTime(row.licen_validity_end);
            	var taxpayer_state = row.taxpayer_state==null?"":row.taxpayer_state;
            	var org_no = row.org_no==null?"":row.org_no;
            	var quality_deposit = row.quality_deposit==null?"":row.quality_deposit;
            	var address = row.address==null?"":row.address;
            	var description = row.description==null?"":row.description;
            	var contacts_name = row.contacts_name==null?"":row.contacts_name;
            	var contacts_phome = row.contacts_phome==null?"":row.contacts_phome;
            	var contacts_emil = row.contacts_emil==null?"":row.contacts_emil;
            	var license_url = row.license_url==null?"":row.license_url;
            	var busine_licen_url = row.busine_licen_url==null?"":row.busine_licen_url;
            	var duty_policy_url = row.duty_policy_url==null?"":row.duty_policy_url;
            	var identity_positive = row.identity_positive==null?"":row.identity_positive;
            	var identity_negative = row.identity_negative==null?"":row.identity_negative;
            	var bureau_bank_account = row.bureau_bank_account==null?"":row.bureau_bank_account;
            	var bank_name = row.bank_name==null?"":row.bank_name;
            	
            	var reason = row.reason==null?"":row.reason;
            	var state = row.state;
            	var remark = row.remark==null?"":row.remark;
            	var create_time = row.create_time==null?"":timestampToTime(row.create_time);
            	var examine_time = row.examine_time==null?"":timestampToTime(row.examine_time);
            	
            	
            	var _html='';
            	_html += '<form >                                                                                                                                                       '
            	+'      <div class="br-pagebody">                                                                                                                               '
            	+'                                                                                                                                                              '
            	+'        <div class="br-section-wrapper">                                                                                                                      '
            	+'<!--内容-->                                                                                                                                                            '
            	+'		<div class="row mg-t-20">                                                                                                                                        '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>企业名称:</p>                 '    
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-50%" id="company_name" readonly="readonly" type="text" value="'+company_name+'">                                                           '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                                           '
            	+'		<div class="row mg-t-20">                                                                                                                                        '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>工商营业执照注册号码:</p>     '                
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-50%" id="license_no" readonly="readonly" type="text" value="'+license_no+'">                                                             '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                                           '
            	+'		<div class="row">                                                                                                                                                '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>法人:</p>                     '
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-250" id="legal_person" readonly="readonly" type="text" value="'+legal_person+'">                                                           '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>企业法人手机号:</p>           '
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				 <input class="form-control wd-250" id="legal_person_phome"   readonly="readonly" type="text" value="'+legal_person_phome+'">                                                  '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'																																	                                     '
            	+'		</div>                                                                                                                                                           '
            	+'		<div class="row">                                                                                                                                                '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>注册资本（万元）:</p>         '
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-250" id="registered_capital" readonly="readonly" type="text" value="'+registered_capital+'">                                                     '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>质量保证金(万元):</p>         '
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				 <input class="form-control wd-250" id="quality_deposit"   readonly="readonly" type="text" value="'+quality_deposit+'">                                                     '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'																																	                                     '
            	+'		</div>                                                                                                                                                           '
            	+'		<div class="row mg-t-20">                                                                                                                                        '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>成立时间:</p>                 '    
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-50%" id="company_time" readonly="readonly" type="text" value="'+company_time+'">                                                           '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                                           '
            	+'		<div class="row mg-t-20">                                                                                                                                        '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>营业执照有效时期开始时间:</p> '                    
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-50%" id="licen_validity_begin" readonly="readonly" type="text" value="'+licen_validity_begin+'">                                                   '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                                           '
            	+'		<div class="row mg-t-20">                                                                                                                                        '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>营业执照有效时期到期时间:</p> '                    
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-50%" id="licen_validity_begin" readonly="readonly" type="text" value="'+licen_validity_begin+'">                                 '       
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                                           '
            	+'		<div class="row mg-t-20">                                                                                                                                        '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>组织机构代码:</p>             '        
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-50%" id="org_no" readonly="readonly" type="text" value="'+org_no+'">                                               '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                                           '
            	+'		<div class="row mg-t-20">                                                                                                                                        '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>总部地址:</p>                 '    
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-50%" id="address" readonly="readonly" type="text" value="'+address+'">                                              '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                                           '
            	+'		                                                                                                                                                                 '
            	+'		<div class="row mg-t-20">                                                                                                                                                '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>联系人:</p>                   '
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-250" id="contacts_name" readonly="readonly" type="text" value="'+contacts_name+'">                                                          '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>联系人手机号:</p>             '
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				 <input class="form-control wd-250" id="contacts_phome"   readonly="readonly" type="text" value="'+contacts_phome+'">                                                      '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>联系人邮箱:</p>               '
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				 <input class="form-control wd-250" id="contacts_emil"  readonly="readonly" type="text" value="'+contacts_emil+'">                                                        '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                                           '
            	+'		<div class="row mg-t-20">                                                                                                                                        '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>旅行社银行账户:</p>                     '
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-50%" id="bureau_bank_account" readonly="readonly" type="text" value="'+bureau_bank_account+'">                                                                 '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                                           '
            	+'		<div class="row mg-t-20">                                                                                                                                        '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>旅行社银行名称:</p>                     '
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-50%" id="bank_name" readonly="readonly" type="text" value="'+bank_name+'">                                                                 '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                                           '
            	+'		<div class="row mg-t-20">                                                                                                                                        '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>备注:</p>                     '
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-50%" id="remark" readonly="readonly" type="text" value="'+remark+'">                                                                 '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                                           '
            	+'		<div class="row mg-t-20">                                                                                                                                        '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>申请时间:</p>                 '    
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-50%" id="create_time" readonly="readonly" type="text" value="'+create_time+'">                                                            '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                                           '
            	+'	   <div class="row mg-t-20">                                                                                                                                         '
            	+'			<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>处理时间:</p>                 '    
            	+'			<div class="col-lg mg-l-10">                                                                                                                                 '
            	+'				<input class="form-control wd-50%" id="examine_time"  readonly="readonly" type="text" value="'+examine_time+'">                                                          '
            	+'			</div><!-- col -->                                                                                                                                           '
            	+'		</div>                                                                                                                                            				'

            	
            	
            	
            	_html+='            <div class="row  mg-t-20">                                                                                                                                 '
            	
            	
            		+'                <p style="width: 79.12px;" style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>是否一般纳税人:</p>  '
            	+'                <div class="col-lg mg-l-10">                                                                                                                  '
            	+'                    <input class="form-control wd-250" id="taxpayer_state" readonly="readonly" type="text" value="'
            	var SEX;
            	if(taxpayer_state==0){
            		SEX = "否"
            	
            	}else{
            		SEX = "是"
            	}
            	
            	_html+=SEX;
            	_html+='"></div>    </div>                                             '
            	
            	
            	
            		_html+='            <div class="row mg-t-20">                                                                                                                         '
            			+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>营业执照:</p>                          '
            			+'                <div class="col-lg mg-l-10">                                                                                                                   '
                    	+'    	           <a href="javascrip:void(0);" onclick="catImage(\''+license_url+'\')"><img src="'+license_url+'" id="cover" height="80px" width="80px"/></a>                                                               '
                    	+'    	       </div>   ' 
            			+'            </div> '         
            	
            	
            		_html+='            <div class="row mg-t-20">                                                                                                                         '
            			+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>经营许可证:</p>                          '
            			+'                <div class="col-lg mg-l-10">                                                                                                                   '
            			+'    	           <a href="javascrip:void(0);" onclick="catImage(\''+busine_licen_url+'\')"><img src="'+busine_licen_url+'" id="cover" height="80px" width="80px"/></a>                                                               '
            			+'    	       </div>   ' 
            			+'            </div> '         
        			_html+='            <div class="row mg-t-20">                                                                                                                         '
        				+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>旅行社责任险保单:</p>                          '
        				+'                <div class="col-lg mg-l-10">                                                                                                                   '
        				+'    	           <a href="javascrip:void(0);" onclick="catImage(\''+duty_policy_url+'\')"><img src="'+duty_policy_url+'" id="cover" height="80px" width="80px"/></a>                                                               '
        				+'    	       </div>   ' 
        				+'            </div> '         
					_html+='            <div class="row mg-t-20">                                                                                                                         '
						+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>法人身份证正面:</p>                          '
						+'                <div class="col-lg mg-l-10">                                                                                                                   '
						+'    	           <a href="javascrip:void(0);" onclick="catImage(\''+identity_positive+'\')"><img src="'+identity_positive+'" id="cover" height="80px" width="80px"/></a>                                                               '
						+'    	       </div>   ' 
						+'            </div> '         
					_html+='            <div class="row mg-t-20">                                                                                                                         '
						+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>法人身份证反面:</p>                          '
						+'                <div class="col-lg mg-l-10">                                                                                                                   '
						+'    	           <a href="javascrip:void(0);" onclick="catImage(\''+identity_negative+'\')"><img src="'+identity_negative+'" id="cover" height="80px" width="80px"/></a>                                                               '
						+'    	       </div>   ' 
						+'            </div> '         
            	
            	
            	if(state == 0){
            		_html+='            <div class="row mg-t-20">                                                                                                                         '
            			+'                <p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>未通过原因:</p>                          '
            			+'                <div class="col-lg mg-l-10">                                                                                                                  '
            			+'                    <input class="form-control wd-50%" id="reason"  type="text" value="">                                             '
            			+'                </div><!-- col -->                                                                                                                            '
            			+'            </div> ' 
            			 +'            <div class="row mg-t-20">                                                                                                                                                           '
            			 +'                <div class="btn btn-secondary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15" onclick="cancelFormLayer()">关闭</div>                                   '
	                		+'                <div class="btn btn-secondary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15" onclick="cicerone_verification(\'adminHx/bureaut/verification\','+bureau_id+',\'审核未通过\',2)">审核未通过</div>                                   '
	                		+'                <div class="btn btn-outline-primary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15 mg-l-10" onclick="cicerone_verification(\'adminHx/bureaut/verification\','+bureau_id+',\'审核通过\',1)">审核通过</div>'
	                		+'                                                                                                                                                                                                '
	                		+'            </div>  '               
            	}else if (state == 2){
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
 * @param id
 * @param user_id
 * @param msg
 * @param status
 */
function cicerone_verification(url,id,msg,status){
	
	if (status==3){
		if(!(validation(["reason"],[],["审核不通过必须填写原因"]))){
			return;
		}
	}
	
	$.ajax({
        url : url,
        type : "POST",
        data :{"bureau_id":id,"msg":msg,"status":status,"reason":$("#reason").val()},
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



/**
 * 添加
 * @param url
 */
function add_attribute(url,dec){
	var _html ="";
	_html+='<form id = "form">   '                                                         
	+'	  <div class="br-pagebody">                                                                                                                                   '                                            
	+'																																									'										  
	+'		<div class="br-section-wrapper">                                                                                                                          '                                            
	+'			<!--内容-->                                                                                                                                           '                                            
	+'																																									'										  
	+'			<div class="row mg-t-20">                                                                                                                             '                                            
	+'				<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>名称:</p>          '                                                                
	+'				<div class="col-lg mg-l-10">                                                                                                                      '                                            
	+'					<input class="form-control wd-50%" id="attribute_name" name="attribute_name"  type="text" value="">                                           '                                                    
	+'				</div><!-- col -->                                                                                                                                '                                            
	+'			</div>                                                                                                                                                '                                            
	+'		   <div class="row mg-t-20">                                                                                                                              '                                            
	+'				<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>描述:</p>          '                                                                    
	+'				<div class="col-lg mg-l-10">                                                                                                                      '                                            
	+'					<input class="form-control wd-50%" id="attribute_dec" name="attribute_dec" type="text" value="">                                              '                                      
	+'				</div><!-- col -->                                                                                                                                '                                            
	+'			</div>                                                                                                                                                '                                            
	+'			<div class="row mg-t-20">                                                                                                                             '                                            
	+'				<p  style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>是否隐藏:</p>     '                                                               
	+'				<div class="col-lg-0 mg-t-10 mg-l-20 wd-60">                                                                                                      '                                            
	+'					<label class="rdiobox ">                                                                                                                      '                                            
	+'						<input type="radio" name="is_hide" value="0" checked><span>否</span>                                                                      '                                            
	+'					</label>                                                                                                                                      '                                            
	+'				</div>                                                                                                                                            '                                            
	+'				<div class="col-lg-1 mg-t-10 m-l-0">                                                                                                              '                                            
	+'					<label class="rdiobox">                                                                                                                       '                                            
	+'						<input type="radio" name="is_hide" value="1"><span>是</span>                                                                              '                                            
	+'					</label>                                                                                                                                      '                                            
	+'				</div>                                                                                                                                            '                                            
	+'			</div>                                                                                                                                                '                                            
	+'			<div class="row mg-t-20">                                                                                                                             '                                            
	+'				<div class="btn btn-secondary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15" onclick="cancelFormLayer()">取消</div>     '                                            
	+'				<div class="btn btn-outline-primary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15 mg-l-10" onclick="insert_attribute(\''+url+'\',\'form\')">确定</div>'
	+'			</div>                                                                                                                                                '                                            
	+'																																									'										  
	+'		</div><!-- br-section-wrapper -->                                                                                                                         '                                            
	+'	  </div><!-- br-pagebody -->                                                                                                                                  '                                            
	+'	   </form>    '
	fromEject(_html,dec,'80%','50%');
}
/**
 * 
 * @param url
 * @param from
 */
function insert_attribute(url,form){
	if (validation(["attribute_name"],[],[])){
		
		insert(url,form);
	}else{
		return
	}
}
/**
 * 修改
 * @param id
 * @param url
 * @param dec
 * @param tyep
 */
function edit_attribute(id,url,dec,type){
	var _html = '';
	$.ajax({
        url : url,
        type : "POST",
        data : {"attribute_id":id,"type":type},
        success : function (res) {
        	res = strToJson(res);
            if (res.code =="200"){
            	
            	
            	var attribute = res.data;
            	var attribute_id = attribute.attribute_id;
            	var attribute_name = attribute.attribute_name;
            	var is_hide = attribute.is_hide;
            	var a = '';
            	var b = '';
            	if (is_hide == 0){
            		a = "checked";
            	}else{
            		b = "checked";
            	}
            	var attribute_dec = attribute.attribute_dec==null?"":attribute.attribute_dec;
        		
            	_html+='<form id = "form">                                                                                                                                              '                                                         
            	+'	  <div class="br-pagebody">                                                                                                                                   '                                            
            	+'																																									'										  
            	+'		<div class="br-section-wrapper">                                                                                                                          '                                            
            	+'			<!--内容-->                                                                                                                                           '                                            
            	+'											<input type="hidden"  id="attribute_id"  name="attribute_id" value="'+attribute_id+'"/> <input type="hidden"  name="type" value="2"/>   																														'										  
            	+'			<div class="row mg-t-20">                                                                                                                             '                                            
            	+'				<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>名称:</p>          '                                                                
            	+'				<div class="col-lg mg-l-10">                                                                                                                      '                                            
            	+'					<input class="form-control wd-50%" id="attribute_name" name="attribute_name"  type="text" value="'+attribute_name+'">                                           '                                                    
            	+'				</div><!-- col -->                                                                                                                                '                                            
            	+'			</div>                                                                                                                                                '                                            
            	+'		   <div class="row mg-t-20">                                                                                                                              '                                            
            	+'				<p style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>描述:</p>          '                                                                    
            	+'				<div class="col-lg mg-l-10">                                                                                                                      '                                            
            	+'					<input class="form-control wd-50%" id="attribute_dec" name="attribute_dec" type="text" value="'+attribute_dec+'">                                              '                                      
            	+'				</div><!-- col -->                                                                                                                                '                                            
            	+'			</div>                                                                                                                                                '                                            
            	+'			<div class="row mg-t-20">                                                                                                                             '                                            
            	+'				<p  style="width: 79.12px;" class="tx-11 tx-uppercase tx-spacing-2 mg-b-10 mg-t-10 tx-gray-600"><span class="tx-danger">*</span>是否隐藏:</p>     '                                                               
            	+'				<div class="col-lg-0 mg-t-10 mg-l-20 wd-60">                                                                                                      '                                            
            	+'					<label class="rdiobox ">                                                                                                                      '                                            
            	+'						<input type="radio" name="is_hide" value="0" '+a+'><span>否</span>                                                                      '                                            
            	+'					</label>                                                                                                                                      '                                            
            	+'				</div>                                                                                                                                            '                                            
            	+'				<div class="col-lg-1 mg-t-10 m-l-0">                                                                                                              '                                            
            	+'					<label class="rdiobox">                                                                                                                       '                                            
            	+'						<input type="radio" name="is_hide" value="1"  '+b+'><span>是</span>                                                                              '                                            
            	+'					</label>                                                                                                                                      '                                            
            	+'				</div>                                                                                                                                            '                                            
            	+'			</div>                                                                                                                                                '                                            
            	+'			<div class="row mg-t-20">                                                                                                                             '                                            
            	+'				<div class="btn btn-secondary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15" onclick="cancelFormLayer()">取消</div>     '                                            
            	+'				<div class="btn btn-outline-primary btn-oblong bd-2 pd-x-30 pd-y-10 tx-uppercase tx-bold tx-spacing-1 tx-15 mg-l-10" onclick="update_attribute(\''+url+'\',\'form\')">确定</div>'
            	+'			</div>                                                                                                                                                '                                            
            	+'																																									'										  
            	+'		</div><!-- br-section-wrapper -->                                                                                                                         '                                            
            	+'	  </div><!-- br-pagebody -->                                                                                                                                  '                                            
            	+'	   </form>    '
			    fromEject(_html,dec,'80%','50%');
            }else{
            	layer.alert(res.msg,{icon: 6});
            }
        }
    })
    
}

function update_attribute(url,form){
	if (validation(["attribute_name","attribute_id"],[],[])){
		
		update(url,form);
	}else{
		return
	}
}
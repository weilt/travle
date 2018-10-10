

/**
 * js验证非空信息
 * @param parameter - 数组 - 参数 - 参数是ID
 * @param type - 数据 - 类型
 * @param msg - 数组 - 提示的消息
 */
function validation(parameter,type,msg){
	console.log(msg.length)
	for (var i = 0; i < parameter.length; i++) {
		var o = $('#'+parameter[i]).val();
		//判断类型
		var validateType = type.length <= i ? null : type[i];
		if(validateType == null||validateType == ''||validateType == 'NULL'){//判断为空
			if(o.length == 0){
				if(msg.length < i+1) {
					
					layer.msg("参数不能为空! param is " + parameter[i]);
				} else {
					layer.msg(msg[i]);
				}
				$('#'+parameter[i]).focus();
				return false;
			}
		}else if('EMAIL' == validateType){ //验证邮箱
			if(o.length > 0 && !EMAIL(o)){
				if(msg.length < i+1) {
					layer.msg("邮箱格式错误! param is " + parameter[i]);
				} else {
					layer.msg(msg[i]);
				}
				$('#'+parameter[i]).focus();
				return false;
			}
		}else if('PHONE' == validateType){//验证电话
			if(o.length > 0 && !PHONE(o)){
				if(msg.length < i+1) {
					layer.msg("电话格式错误! param is " + parameter[i]);
				} else {
					layer.msg(msg[i]);
				}
				$('#'+parameter[i]).focus();
				return false;
			}
		}else if('Digit' == validateType){ //验证数字类型
			if(o.length > 0 && !Digit(o)){
				if(msg.length < i+1) {
					layer.msg("数字类型错误! param is " + parameter[i]);
				} else {
					layer.msg(msg[i]);
				}
				$('#'+parameter[i]).focus();
				return false;
			}
		}else if('Decimals' == validateType){  //验证小数类型
			if(o.length > 0 && !Decimals(o)){
				if(msg.length < i+1) {
					layer.msg("小数类型错误! param is " + parameter[i]);
				} else {
					layer.msg(msg[i]);
				}
				$('#'+parameter[i]).focus();
				return false;
			}
		}else if('Max' == validateType){  //验证长度
			if(o.length > 0 && o.length>msg[i]){
				var placeholder = $('#'+parameter[i]).attr('placeholder');
				
				layer.msg(placeholder+"不能超过"+msg[i]+"个字符");
				
				$('#'+parameter[i]).focus();
				return false;
			}
		}else if('Min' == validateType){  //验证长度
			if(o.length > 0 && o.length<msg[i]){
				var placeholder = $('#'+parameter[i]).attr('placeholder');
				
				layer.msg(placeholder+"不能小于"+msg[i]+"个字符");
				
				$('#'+parameter[i]).focus();
				return false;
			}
		}
	}
	return true;
}

/**
 * 验证邮箱 正确返回true,失败返回false
 * @returns
 */
function EMAIL(em){
	var filter=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(filter.test(em)){
		return true;
	}else{
		return false;
	}
}
/**
 * 验证电话 正确返回true,失败返回false
 * @returns
 */
function PHONE(ph){
	var reg = /^1[0-9]{10}$/;
	if(reg.test(ph)){
		return true;
	}else{
		return false;
	}
}
/**
 * 验证数子 正确返回true,失败返回false
 * @returns
 */
function Digit(di){
	var reg = /^[0-9]*$/;
	if(reg.test(di)){
		return true;
	}else{
		return false;
	}
}
/**
 * 验证小数 正确返回true,失败返回false
 * @returns
 */
function Decimals(de){
	var reg = /^(-?\d+)(\.\d+)?$/;
	if(reg.test(de)){
		return true;
	}else{
		return false;
	}
}

/**
 * 返回结果字符串转json
 */
function strToJson(res){
	if ( Object.prototype.toString.call(res) === "[object String]"){
		return JSON.parse(res);
	}
	return res;
}

/**
 * 点击最上面
 * @param a
 * @param id
 */
function reSreachCommit(a,id){
	//移除点击后面的
	$(a).nextAll().remove();
	//清空查询条件
	$("input").val("");
	//传入父id
	$("#paramMap_parentId").val(id);
	
	var parentName;
	if (id==0){
		parentName = "顶级模块";
	}else{
		var rows = $(".breadcrumb-item");
		parentName = $(rows[rows.length-1]).text();
		
	}
	
	$("#addSon").attr("onclick","add("+id+",'"+parentName+"')")
	//查询数据
	sreachCommit();
}

function deleteAndRecoveryById(type,id,url,dec){
	$.ajax({
        url : url,
        type : "POST",
        data :{"id":id,"type":type},
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
/**
 * 通过id删除
 * @param id  id
 * @param url 地址
 * @param dec 描述
 */
function deleteById(a,id,url,dec){
	
	layer.confirm(dec, {
		  btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
		        url : url,
		        type : "POST",
		        data :{"id":id},
		        success : function (res) {
		        	res = strToJson(res);
		            if (res.code =="200"){
		            	$(a).parent().parent().remove();
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
			
			
		}, function(){
		  
		});
}


/**
 * 异步提交表单---更新
 * @param url
 * @param fromId
 */
function update(url, fromId){
	$.ajax({
        url : url,
        type : "POST",
        data : $("#"+fromId).serialize(),
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
/**
 * 页面弹出
 * @param url	地址
 * @param dec	描述
 * @param wide	宽
 * @param high	高
 */
function webpageEject(url,dec,wide,high){
	layer.open({
		  type: 2,
		  title: dec,
		  shadeClose: true,
		  shade: 0.8,
		  area: [wide, high],
		  content: url //iframe的url
		}); 
}
/**
 * 弹出表单
 * @param _html html内容
 * @param dec 描述
 * @param wide 宽
 * @param high 高
 */
function fromEject(_html,dec,wide,high){
	layer.open({
		  type: 1,
		  closeBtn:1,
		  title: dec,
		  area: [wide, high], //宽高
		  content: _html,
		  zIndex:100 //层优先级
		});
}
/**
 * 取消表单layer弹出表单的情况

 */
function cancelFormLayer(){
	layer.closeAll();
}

/**
 * 异步提交表单添加
 * @param url 提交地址
 * @param fromId 表单id
 */
function insert(url, fromId){
	$.ajax({
        url : url,
        type : "POST",
        data : $("#"+fromId).serialize(),
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

/**
 * 时间戳转成日期
 * @param timestamp
 * @returns {String}
 */
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = (date.getDate()<10?'0'+date.getDate():date.getDate()) + ' ';
    h = (date.getHours()<10?'0'+date.getHours():date.getHours()) + ':';
    m = (date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes()) + ':';
    s = (date.getSeconds()<10?'0'+date.getSeconds():date.getSeconds());
    return Y+M+D+h+m+s;
}

/**
 * 查看图片
 * @param url
 */
function catImage(url){
	var _html = '<img src="'+url+'"  style=" margin:  0 auto;display:  block; height: 100%;max-width: 100%;"/>'
	fromEject(_html,'','80%','80%');
}

//页面提交返回数据信息-当前页
/*function jsonState(result){
	if(result.code == 200) {//正常
		if(result.msg != null && result.msg != "") {
			layer.alert(result.msg,{icon: 6},function(index){
				if(result.data.action=="forward") {
					window.location=result.data.url;//直接跳转页面
//					parent.location.reload();//关闭窗口刷新父窗口
				} else if(result.data.action=="refresh") {
					 window.location.reload(); //刷新窗口
				} else if(result.data.action=="noForwardAndRefresh"){
					layer.close(index);
				}else {
					layer.alert("undefined action " + result.data.action);
				}
    		});
		}else{
			if(result.data.action=="forward"){
				window.location = result.data.url;//直接跳转页面
			} else if(result.data.action=="refresh") {
				 window.location.reload(); //刷新窗口
			} else {
				layer.alert("undefined action " + result.data.action);
			}
		} 
	}else if(result.code == 300) {//提示信息
		//layer.alert(result.msg);
		layer.alert(result.msg);
	} else if(result.code == 104){
		layer.msg(result.msg);
	} else if(result.code == 102){
		//登陆超时
		layer.alert("Login timeout",{icon: 6},function(){
			window.location = '/back';//直接跳转页面
		});
	}else{
		layer.alert("调试错误");
	}
}*/


//页面提交返回数据信息-当前页
function jsonState(result){
	if(result.code == 102){
		//登陆超时
		layer.alert("Login timeout",{icon: 6},function(){
			window.location = '/back';//直接跳转页面
		});
	}
}

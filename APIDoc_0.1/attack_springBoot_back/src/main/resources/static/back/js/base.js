
//页面提交返回数据信息-当前页
function jsonState(result){
	if(result.state == 200) {//正常
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
	}else if(result.state == 300) {//提示信息
		//layer.alert(result.msg);
		layer.alert(result.msg);
	} else if(result.state == 104){
		layer.msg(result.msg);
	} else if(result.state == 102){
		//登陆超时
		layer.alert("Login timeout",{icon: 6},function(){
			window.open('/back','_top');//直接跳转页面
		});
	}else{
		layer.alert("调试错误");
	}
	
}

//表单提交 ajax
function submitForm(url, form) {
	if(url.indexOf('?')>-1){
		url = url + "&";
	} else {
		url = url + "?";
	}
	url = url + "from=ajax";
	
	$.ajax({  
	    type: "POST",  
	    url:url, 
	    dataType: "json",
	    data:$("#" + form).serialize(),  
	    async: false,  
	    success: function(result) {  
	        //接收后台返回的结果  
	    	stateJson(result);
	    },
	    error: function(request) {  
	    	layer.alert("返回错误");  
	    }
	 });
	
	/*$.post(url, $("#" + form).serialize(), function(result) {
		stateJson(result);
	}, 'json');*/
}

//表单提交--JSON回调函数
function stateJson(result) { 
	if(result.state == 200) {//正常
		if(result.msg != null && result.msg != "") {
			layer.alert(result.msg,{icon: 6},function(){
				if(result.data.action=="forward") {
//					window.location=result.forwardUrl;//直接跳转页面
					parent.location.reload();//关闭窗口刷新父窗口
				} else if(result.data.action=="refresh") {
					 window.location.reload(); //刷新窗口
				} else if(result.data.action=="noForwardAndRefresh"){
					closes();//关闭窗口
				}else {
					layer.alert("undefined action " + result.data.action);
				}
    		});
		}else{
			if(result.data.action=="forward"){
//				window.location=result.forwardUrl;//直接跳转页面
				parent.location.reload();//关闭窗口刷新父窗口
			} else if(result.data.action=="refresh") {
				 window.location.reload(); //刷新窗口
			} else {
				layer.alert("undefined action " + result.data.action);
			}
		}
	} else if(result.state == 300) {//提示信息
		layer.alert(result.msg);
	} else if(result.state == 104){
		layer.msg(result.msg);
	}else if(result.state == 102){
		//登陆超时
		layer.alert("Login timeout",{icon: 6},function(){
			window.open('/back','_top');//直接跳转页面
		});
	}else{
		layer.alert("调试错误");
	}
}



/**
 * 自定义批量删除按钮
 * @param con_str 要提示的信息
 * @param Id 获取nameID
 * @param o 要去执行路径
 * @param os from id值
 * @return
 */
function deleteConfirm(con_str,Id,o,os){
	var ids = document.getElementsByName(Id);
	if (Id!=null) {
		var a = 0;
		for ( var i = 0; i < ids.length; i++) {
			if(ids[i].checked){
				a++;
			}
		}
		if (a < 1) {
			layer.msg("请选择后再批量操作!",{icon: 6,time:1500});
		} else {
			layer.confirm('您确定要'+con_str+'吗？',function(index){
				submitForm(o,os);
			});
		}
	} else {
		layer.alert("操作失败，请联系管理员或者开发人员!");
	}
}


//关闭当前窗口
function closes(){
	// parent.location.reload(); //关闭窗口且刷新父级窗口
	 var index = parent.layer.getFrameIndex(window.name);
     parent.layer.close(index);   //关闭当前窗口 
}

/*弹出层*/
/*
 * @param o - 请求地址
 * @param win - 宽
 * @param hei - 高
 */
function showPage(o,win,hei,text) {
    layer.open({
        type: 2,
        title:text,
        fix: false, //不固定
        shadeClose: true,
        shade: 0.4,
        skin: 'layui-layer-red', //样式类名
        anim: 0,
        area: [win, hei],
        content: o //iframe的url
    });
}
/*关闭弹出框口*/
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}


/**
 * 计算字符串长度 汉子看成一个字节
 * @param str 字符串
 * @return
 */
function Len(str){  
     var i,sum;  
     sum=0;  
     for(i=0;i<str.length;i++)  
     {  
         if ((str.charCodeAt(i)>=0) && (str.charCodeAt(i)<=255))  
             sum=sum+1;  
         else  
             sum=sum+1;  //加1是把汉子算成一个字节，加2是算成2个字节
     }  
     return sum;  
}  



/**
 * js验证非空信息
 * @param parameter - 数组 - 参数 - 参数是ID
 * @param type - 数据 - 类型
 * @param msg - 数组 - 提示的消息
 */
function validation(parameter,type,msg){
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
 * 同步查询限制
 * @param submitId
 * @param formId
 */
function formSubmit(submitId, formId){
    if (!formId) formId = 'from';
    $(submitId).attr('disabled', 'true').text('loading');
    $('#'+formId).submit();
}

/**
 * 全选和反选兼容性Ok
 * @param obj name的值
 * @param type 1-全选，2-全不选，3-反选，4-要么全选，要么反选
 * @return
 */
function selectAll(obj,type){
   if(type == undefined || type==null&&type==""){
	   type = 3;
   }
   if(obj!=null&&obj!=""){  
	   if(document.getElementsByName(obj)!=undefined && document.getElementsByName(obj).length>0){//getElementsByName函数的作用按名字查找对象，返回一个数组。  
		   var userids = document.getElementsByName(obj);  
	       if(type==1){  //全选
		       for(var i=0;i<userids.length;i++){  
		    	  if(userids[i].checked == false){  
			    	  userids[i].checked = true;  
			      }  
		       }  
	       }else if(type==2){  //"全不选"
		      for(var i=0;i<userids.length;i++){  
		    	  if(userids[i].checked == true){  
		    		  userids[i].checked = false;  
			      }  
		      }  
	       }else if(type == 4){ //要么全选，要么反选
	    	   var b = true;
	    	   for(var i=0;i<userids.length;i++){  
		    	  if(userids[i].checked == true){  
		    		  b = false;
		    		  break;
			      }  
		       }
    		   for(var i=0;i<userids.length;i++){
    			   userids[i].checked = b;  
    		   } 
	       }else{  //"反选"
	    	   for(var i=0;i<userids.length;i++){  
	    		 if(userids[i].checked == true){  
    			 	userids[i].checked = false;  
	    		 }else{  
		    	   userids[i].checked = true;  
			     }  
	    	   }  
     	   }  
	   }  
   }    
}


$(document).ready(function() {
	var $uname = $('.loginuser'); //用户名，
	var $pword = $('.loginpwd');  //密码
	var $checked = $('#checkedTo');  //选中的数据信息
	//获取Cookie中的值,
	var CookieUserPassWord = $.cookie("setGetCookieUserPassWord_liugang");
	if(CookieUserPassWord != null && CookieUserPassWord.length > 0){
		var CookieUserName = $.cookie("setGetCookieUserName_liugang");
		if(CookieUserName != null && CookieUserName.length > 0){
			$uname.val(CookieUserName);
			$pword.val(CookieUserPassWord);
			$checked.attr("checked", true);//记住密码状态
		}
	}
	//回车键快速登录【兼容】
	document.onkeydown = function keyLogin(e) {
		e = e || event;
		if (e.keyCode == 13) { //回车键的键值为13
			$('.loginbtn').click();
		}
	}
	//登录 
	$('.loginbtn').click(function() {
		//获取密码
		var userName = $uname.val();
		var passWord = $pword.val();
		var checked = null;
		//有5种内置的情景类型：warning，error，success，info和question。
		if (userName == "") {
			layer.tips('请输入账号', $uname, {
				tips : [ 4, '#3595CC' ],
				time : 2000
			});
			return false;
		}
		if (passWord == "") {
			layer.tips('请输入密码', $pword, {
				tips : [ 4, '#3595CC' ],
				time : 2000
			});
			return false;
		}
		//选中记住密码
		if ($checked.is(':checked')) {
			// do something
			checked = "checked";
			//添加cookie信息
		}
		$.post("/back/login", {
			'userName' : userName,
			'passWord' : passWord,
			'checked' : checked
		}, function(result) {
			if (result.state == 200) {
				//alert(JSON.stringify(result.data.name));
				var roleName = result.data.name;
				var roleId = result.data.id;
				$.cookie('topRoleName_liugang', roleName); //临时保存的数据信息
				$.cookie('roleId_liugang', roleId);
				if(checked != null && checked.length > 0){
					//只保留7天的cookie
					$.cookie('setGetCookieUserName_liugang', userName, { expires: 7 });
					//密码的保存
					$.cookie('setGetCookieUserPassWord_liugang', passWord, { expires:7});
				}else{
					$.cookie('setGetCookieUserName_liugang', userName);
					//删除之前的 cookie - 密码的保存
					$.cookie('setGetCookieUserPassWord_liugang', null); 
				}
				window.location.href = '/admin/user/main';
			} else {
				layer.msg(result.msg, {
					icon : 6
				});
			}
		}, 'json');
	});
});
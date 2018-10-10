/**
 * 这个是自己自定义的数据信息，
 */
var filePostName = 'uploadFile'; //上传name值
var uploadJson = '/pic/upload'; //上传地址

var editor = KindEditor.editor({
				//指定上传文件参数名称
				filePostName  : filePostName,
				//指定上传文件请求的url。
				uploadJson : uploadJson
				//上传类型，分别为image、flash、media、file
				//dir : "image"
			});

/**
 * 上传图片信息
 * @param urlValId - 返回在文件的值
 * @param imageSrcId - 返回在图标的值
 */
function create_image(urlValId,imageSrcId){
	KindEditor.ready(function(K) {
		editor.loadPlugin('image', function() {
			editor.plugin.imageDialog({
			    showRemote : false,
				fileUrl : K('#'+urlValId).val(),
				clickFn : function(url, title) {
					K('#'+urlValId).val(url);
					if(imageSrcId != null && imageSrcId != ''){
						K('#'+imageSrcId).attr("src",url);
					}
					editor.hideDialog();
				}
			});
		});
	});
}

/**
 * 多图片上传
 * @param type 类型
 */
function create_multiimage(type){
	KindEditor.ready(function(K) {
		editor.loadPlugin('multiimage', function() {
			editor.plugin.multiImageDialog({
				clickFn : function(urlList) {
					console.log(urlList);
					if(type == 1){
						multiimage_1(urlList);
					}else if(type == 2){
						
					}else if(type == 3){
						
					}
					/*KindEditor.each(urlList, function(i, data) {
						//这里是循环 data:{error: 0, message: "上传成功", url:"http://127.0.0.1:8080/0966b80acf.gif"}
						//这里是业务逻辑 逻辑出来可以根据type值来做处理
					});*/
					editor.hideDialog();
				}
			});
		});
	});
}
//这个是 
function multiimage_1(urlList){
	var te = "";
	//这里做逻辑
	KindEditor.each(urlList, function(i, data) {
		//这里是循环 data:{error: 0, message: "上传成功", url:"http://127.0.0.1:8080/0966b80acf.gif"}
		//这里是业务逻辑 逻辑出来可以根据type值来做处理
		te += data.url+",";
	});
	$('#inputIds').val(te);
}

/**
 * 富文本信息
 * @param id
 */
function create_textarea(id){
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[id="'+id+'"]', {
			//指定上传文件参数名称
			filePostName  : filePostName,
			//指定上传文件请求的url。
			uploadJson : uploadJson,
			
			allowFileManager : false,
			themeType : 'simple', //simple,qq,default
			resizeType:1,//是否有拉伸
			urlType : 'domain',
			/*items : ['fullscreen','preview', 'undo', 'redo', 'print', 'cut', 'copy', 'paste','indent',
						'plainpaste','wordpaste','|','justifyleft', 'justifycenter', 'justifyright',
						'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
						'superscript', 'forecolor', 'hilitecolor', 'bold',
						'italic', 'underline', 'strikethrough', 'removeformat', 'hr','lineheight','quickformat','|','selectall', 
						'fontname', 'fontsize', '|','image','multiimage',
					    'baidumap','advtable', 'emoticons', 'link', 'unlink'],*/
			afterCreate : function() {this.sync();},
			afterChange: function(){ this.sync(); },//编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里
	        afterBlur: function(){this.sync();}//编辑器失去焦点后将编辑器内容同步到textarea元素里面
		});
	});
}


/**
 * 上传文件
 * @param urlValId 把值返回给某个 input
 */
function create_insertfile(urlValId){
	KindEditor.ready(function(K) {
		editor.loadPlugin('insertfile', function() {
			editor.plugin.fileDialog({
			    showRemote : false,
				fileUrl : K('#'+urlValId).val(),
				clickFn : function(url, title) {
					K('#'+urlValId).val(url);
					editor.hideDialog();
				}
			});
		});
	});
}


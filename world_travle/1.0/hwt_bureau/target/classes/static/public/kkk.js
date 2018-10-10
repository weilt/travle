var E3 = {  
    // 编辑器参数  
    kingEditorParams : {  
        //指定上传文件参数名称  
        filePostName  : "uploadFile",  
        //指定上传文件请求的url。  
        uploadJson : '/pic/upload',  
        //上传类型，分别为image、flash、media、file  
        dir : "image"  
    },  
      
    init : function(data){  
        // 初始化图片上传组件  
        this.initPicUpload(data);  
    },  
    // 初始化图片上传组件  
    initPicUpload : function(data){  
    	console.log(data);
        $(".picFileUpload").each(function(i,e){  
            var _ele = $(e);  
            _ele.siblings("div.pics").remove();  
            _ele.after('\
        			<div class="pics">\
            			<ul></ul>\
            		</div>');
            
            // 回显图片  
            if(data && data.pics){  
                var imgs = data.pics.split(",");  
                for(var i in imgs){  
                    if($.trim(imgs[i]).length > 0){  
                        _ele.siblings(".pics").find("ul").append("<li><a href='"+imgs[i]+"' target='_blank'><img src='"+imgs[i]+"' width='80' height='50' /></a></li>");  
                    }  
                }  
            }  
            //给“上传图片按钮”绑定click事件  
            $(e).click(function(){  
                var form = $(this).parentsUntil("form").parent("form");  
                //打开图片上传窗口  
                KindEditor.editor(E3.kingEditorParams).loadPlugin('multiimage',function(){  
                    var editor = this;  
                    editor.plugin.multiImageDialog({  
                        clickFn : function(urlList) {  
                            var imgArray = [];  
                            KindEditor.each(urlList, function(i, data) {  
                            	console.log(data);
                                imgArray.push(data.url);  
                                var imageHtml = "";
                                imageHtml +='<div ><a href="javascrip:void(0);" onclick="catImage(\''+data.url+'\')"><img class="imgbox" src="'+data.url+'" /></a>'
           	        			+'<div class="line"><p class="setTop" onclick="addlable(this)">置顶</p><p class="del" onclick="dellabe(this)">删除</p></div>'
           	        			+'</div>'
								
								form.find(".addimgbtn").before(imageHtml);	
                            });  
                            //form.find("[name=image]").val(imgArray.join(","));  
                            editor.hideDialog();  
                        }  
                    });  
                });  
            });  
        });  
    },  
    createEditor : function(select){
    	return KindEditor.create(select, E3.kingEditorParams);
    },
    
    /**
     * 创建一个窗口，关闭窗口后销毁该窗口对象。<br/>
     * 
     * 默认：<br/>
     * width : 80% <br/>
     * height : 80% <br/>
     * title : (空字符串) <br/>
     * 
     * 参数：<br/>
     * width : <br/>
     * height : <br/>
     * title : <br/>
     * url : 必填参数 <br/>
     * onLoad : function 加载完窗口内容后执行<br/>
     * 
     * 
     */
    createWindow : function(params){
    	$("<div>").css({padding:"5px"}).window({
    		width : params.width?params.width:"80%",
    		height : params.height?params.height:"80%",
    		modal:true,
    		title : params.title?params.title:" ",
    		href : params.url,
		    onClose : function(){
		    	$(this).window("destroy");
		    },
		    onLoad : function(){
		    	if(params.onLoad){
		    		params.onLoad.call(this);
		    	}
		    }
    	}).window("open");
    },
    
    closeCurrentWindow : function(){
    	$(".panel-tool-close").click();
    },
    
    /**
     * 初始化单图片上传组件 <br/>
     * 选择器为：.onePicUpload <br/>
     * 上传完成后会设置input内容以及在input后面追加<img> 
     */
    initOnePicUpload : function(){
    	$(".onePicUpload").click(function(){
			var _self = $(this);
			KindEditor.editor(E3.kingEditorParams).loadPlugin('image', function() {
				this.plugin.imageDialog({
					showRemote : false,
					clickFn : function(url, title, width, height, border, align) {
						var input = _self.siblings("input");
						input.parent().find("img").remove();
						input.val(url);
						input.after("<a href='"+url+"' target='_blank'><img src='"+url+"' width='80' height='50'/></a>");
						this.hideDialog();
					}
				});
				
			});
		});
    }
};  
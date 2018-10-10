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
};  
$(window).ready(function(){
				var getData = null;
				var dataIndex = 0;
				var dom = null;
				var toDay = null;
				$(".select-date").css("display" , 'block')
				intDom()
//				console.log($('.adultMoney'))
			})
			
			function getToDay(day){
				return toDay = day
			}
			
			function intDom(){			//		初始化dom
				for(var i=0; i<$('.tabel-li').length; i++){
					if($('.tabel-li').eq(i).text() == toDay){
						return dom = $('.tabel-li').eq(i)
					}
				}
			}
			
			function getDom(d){		//	获取dom
				console.log(d)
				return dom = d
			}
			
			$('#moneyBtn1').click(function(){		//		添加成人价格
				var money = $('#money1').val()
				if(dom.children('.adultMoney').length == 0){
					dom.append('<p class=adultMoney>${money}</p>')
				}else{
					$('.adultMoney').text(money)
				}
			})
			
			$('#moneyBtn2').click(function(){		//		添加儿童价格
				var money = $('#money2').val()
				if(dom.children('.childrenMoney').length == 0){
					dom.append('<p class=childrenMoney>${money}</p>')
				}else{
					$('.childrenMoney').text(money)
				}
			})
			
			function getSelectDate(result){		//这里获取选择的日期
				console.log('result' , result);
			}
			
			$('#monitorInput').bind('input propertychange' , function(e){//		获取数据
				$.ajax({
					type:"POST",
					url:"/bureau/line/scenic_spot",
					async:true,
					data: {scenic: '三庆园'},
					success: function(data){
						console.log(data)
						if(data.code == 200){
							getData = data.data
							$('.box-ul').empty()
							for(var i=0; i<data.data.length; i++){
								$('.box-ul').append('<li class=getIndex data-index=${i}>${data.data[i].name}<i>X</i></li>')
							}
							$('.box-ul').css('display' , 'block')
						}
					},
				});
			})
			
			$(document).on('click' , '.box-ul li>i' , function(e){	//	删除
				e.stopPropagation()
				var parentIndex = $(this).parent().index();
				var index = $(this).parent().attr('data-index');
				var name =getData[index].name
				var nameID = getData[index].name_id
				$('.box-ul li').eq(parentIndex).remove()
				showNameOrId(name , nameID)
			})
			
			$(document).on('click' , '.getIndex' , function(e){	//	点击li	
				var index = $(this).attr('data-index');
				var name =getData[index].name
				var nameID = getData[index].name_id
				$('#monitorInput').val(name)
				$('.box-ul').css('display' , 'none')
				showNameOrId(name , nameID)
			})
			
			function showNameOrId(name , id){
				console.log('name' , name)
				console.log('nameID' , id)
			}
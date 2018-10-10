function drawChart(id , drawType , data ,title){
	var myChart = echarts.init(document.getElementById(id));
	if(drawType == 'bar'){
		var name = [];
		var value = [];
		for(var i=0; i<data.length; i++){
			name.push(data[i].name)
			value.push(data[i].value)
		}
		var option = {
		    title: {
		        text: title
		    },
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: {
		        type: 'value',
		        boundaryGap: [0, 0.01]
		    },
		    yAxis: {
		        type: '',
		        data: name
		    },
		    series: [
		        {
		            name: '',
		            type: 'bar',
		            data: value,
		            label:{
				        show: true,
				        textBorderColor: '#333',
				        textBorderWidth: 1
				    }
		        }
		    ]
	    };
	}else if(drawType == 'pie'){
		var option = {
		    title: {
		      text: title
		    },
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    series: [
		        {
		            name:'今日洗车占比',
		            type:'pie',
		            radius: ['30%', '50%'],
		            avoidLabelOverlap: true,
		            label: {
		                normal: {
		                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
		                    backgroundColor: '#eee',
		                    borderColor: '#aaa',
		                    borderWidth: 1,
		                    borderRadius: 4,
		                    shadowBlur:3,
		                    shadowOffsetX: 2,
		                    shadowOffsetY: 2,
		                    shadowColor: '#999',
		                    padding: [0, 7],
		                    rich: {
		                        a: {
		                            color: '#999',
		                            lineHeight: 22,
		                            align: 'center'
		                        },
		                        // abg: {
		                        //     backgroundColor: '#333',
		                        //     width: '100%',
		                        //     align: 'right',
		                        //     height: 22,
		                        //     borderRadius: [4, 4, 0, 0]
		                        // },
		                        hr: {
		                            borderColor: '#aaa',
		                            width: '100%',
		                            borderWidth: 0.5,
		                            height: 0
		                        },
		                        b: {
		                            fontSize: 16,
		                            lineHeight: 33
		                        },
		                        per: {
		                            color: '#eee',
		                            backgroundColor: '#334455',
		                            padding: [2, 4],
		                            borderRadius: 2
		                        }
		                    }
		                }
		            },
		            data:data

		        }
		    ]
		};
	}
	myChart.setOption(option , 'dark');
	}

function showRanking(id , data){
	var dom = $(id);
	for(var i=0; i<data.length; i++){
		dom.append("<li class='list'><div>"+data[i].name+"</div><div>"+data[i].value+"</div><div>"+data[i].rate+"</div><div>"+ (i+1) +"</div></li>")
	}
}
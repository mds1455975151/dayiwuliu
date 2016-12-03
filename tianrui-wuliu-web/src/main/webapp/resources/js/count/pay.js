$(document).ready(function(){
	$.ajax({
		url : "/count/payLine",//
		data : {
			"pageNo":0,
			"pageSize":10
		},
		type : "post",
		success : function(rs){
			var time = [];
			var data = [];
			if(rs.code=="000000"){
				var ret = rs.data;
				time = [];
				data = [];
				for (var a = 9; a >= 0; a--) {
					time.push(ret[a].showtimeStr);
					data.push(ret[a].adddate)
				}
				//柱状图
		        $('#container').highcharts({
		            title: {
		                text: ' ' //置空
		            },
		            xAxis: {
		                categories: time
		            },
		            yAxis: {
		                title: {
		                    text: ' ' //置空
		                },
		                labels: {
		                	formatter:function (){   
		                		return this.value/10000;   
		                	}
		                }
		            },
		            legend: {
		                enabled:false
		            },
		            series: [{
		                type: 'column',
		                name: '运费',
		                data: data  //柱状图的值
		            }]
		        });

		        // 控制消息标题的长度，多出的用省略号标示
		        $(".data_yfright ul li").each(function(){
		            //字符个数
		            var maxwidth=26;
		            var contr = $(this).find("label");
		            if($(contr).text().length>maxwidth){
		                $(contr).text($(contr).text().substring(0,maxwidth));
		                $(contr).html($(contr).html()+".....");
		            }
		        });
				
			}else{
				alert(rs.error);
			}
		}
	}); 
	window.scrollTo(0,560);
});
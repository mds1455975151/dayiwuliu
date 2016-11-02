$(document).ready(function(){
	$.ajax({
		url : "/count/billLine",//
		data : {},
		type : "post",
		success : function(rs){
			var time = [];
			var data = [];
			if(rs.code=="000000"){
				var ret = rs.data;
				time = [];
				data = [];
				for (var a = 0; a < ret.length; a++) {
					time.push(ret[a].showtimeStr);
					data.push(ret[a].adddate)
				}
				
				//折线图
			    $('#line3').highcharts({
			    	chart: {
			            type: 'areaspline'
			        },
			        title: {
			            text: ' '
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'left',
			            verticalAlign: 'top',
			            x: 50,
			            y: 0,
			            floating: true,
			            borderWidth: 1,
			        },
			        xAxis: {
			            categories: time,
			        },
			        yAxis: {
			            title: {
			                text: ' '
			            }
			        },
			        tooltip: {
			            shared: true,
			            valueSuffix: ' 趟'
			        },
			        credits: {
			            enabled: false
			        },
			        plotOptions: {
			            areaspline: {
			                fillOpacity: 0.5
			            }
			        },
			        series: [{
			            name: '交易量',
			            data: data,

			            color:  "rgba(254,188,72,1)",


			        }]
			    });
				
			}else{
				alert(rs.error);
			}
		}
	});    
	

});
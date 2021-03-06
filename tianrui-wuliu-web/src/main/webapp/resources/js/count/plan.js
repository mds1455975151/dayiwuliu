$(document).ready(function(){
	$.ajax({
		url : "/count/planLine",//
		data : {
			"pageNo":0,
			"pageSize":10
		},
		type : "post",
		success : function(rs){
			if(rs.code=="000000"){
				var add = rs.data.add;
				var sum = rs.data.sum;
				var atime = [];
				var adata = [];
				var stime = [];
				var sdata = [];
				var sumCountPlan;
				var addCountPlan;
				for (var a = 9; a >= 0; a--) {
					atime.push(add[a].showtimeStr);
					adata.push(add[a].adddate);
					addCountPlan = add[a].adddate;
				}
				for (var a = 9; a >= 0; a--) {
					stime.push(sum[a].showtimeStr);
					sdata.push(sum[a].sumdate);
					sumCountPlan= sum[a].sumdate;
				}
				$("#addCountPlan").html((+addCountPlan/10000).toFixed(2));
				$("#sumCountPlan").html((+sumCountPlan/10000).toFixed(2));
				//折线图
				$('#container').highcharts({
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
		                categories: stime,
		            },
		            yAxis: {
		                title: {
		                    text: ' '
		                },
		                labels: {
		                	formatter:function(){
		                		return this.value;
		                	}
		                }
		            },
		            tooltip: {
		                shared: true,
		                valueSuffix: ' 吨'
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
		                name: '总量',
		                data: sdata,
		                color: "rgba(59,186,236,1)",
		                visible:false  //设置不现实


		            }, {
		                name: '新增',
		                data: adata,
		                color: "rgba(175,216,94,1)"
		            }]
		        });
				
			}else{
				alert(rs.error);
			}
		}
	});    
	window.scrollTo(0,750);
});
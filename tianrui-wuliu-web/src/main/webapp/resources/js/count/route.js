$(document).ready(function(){
	$.ajax({
		url : "/count/vehicleLine",//
		data : {
			"pageNo":0,
			"pageSize":10
		},
		type : "post",
		success : function(rs){
			if(rs.code=="000000"){
				var add = rs.data.add;
				var sum = rs.data.act;
				var atime = [];
				var adata = [];
				var stime = [];
				var sdata = [];
				var pais  = parseFloat($("#paivechiclesum").val())-parseFloat($("#paivechicleact").val());
				var paia  = parseFloat($("#paivechicleact").val());
				for (var a = 9; a >= 0; a--) {
					atime.push(add[a].showtimeStr);
					adata.push(add[a].adddate)
				}
				for (var a = 9; a >= 0; a--) {
					stime.push(sum[a].showtimeStr);
					sdata.push(sum[a].sumdate)
				}
				
				//折线图
				$('#indexzx').highcharts({
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
		                }
		            },
		            tooltip: {
		                shared: true,
		                valueSuffix: ' 辆'
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
		                name: '新增',
		                data: adata,
		                color: "rgba(59,186,236,1)"

		            }, {
		                name: '活跃',
		                data: sdata,
		                color: "rgba(255,82,78,1)",
		                visible:false  //设置不现实
		            }]
		        });
				//end
				// 饼图
		        $('#indexpie').highcharts({
		            chart: {
		                plotBackgroundColor: null,
		                plotBorderWidth: null,
		                plotShadow: false,
		                type: 'pie'
		            },
		            title: {
		                text: ' '
		            },
		            tooltip: {
		                pointFormat: ' '
		            },
		            plotOptions: {
		                pie: {
		                    allowPointSelect: true,
		                    cursor: 'pointer',
		                    dataLabels: {
		                        enabled: true,
		                        format: '{point.name}({point.percentage:.0f}%)',
		                        style: {
		                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                        }
		                    }
		                }
		            },
		            series: [{
		                name: 'Brands',
		                colorByPoint: true,
		                data: [{
		                    name: '非活跃',
		                    y: pais,  //新增的总数量
		                    color: "rgba(59,186,236,1)"
		                }, {
		                    name: '活跃',
		                    y: paia,  //活跃的总数量
		                    color: "rgba(255,82,78,1)",
		                }]
		            }]
		        });
		        //end
				
			}else{
				alert(rs.error);
			}
		}
	});    
	window.scrollTo(0,560);
});


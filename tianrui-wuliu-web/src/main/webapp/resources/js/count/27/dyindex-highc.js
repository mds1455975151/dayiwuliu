$(function () {
	paiindex();
	lineindex();
	vehcileindex();
	payindex();
	sumindex();
});
setInterval(paiindex,120000);
setInterval(lineindex,100000);
setInterval(vehcileindex,140000);
setInterval(payindex,150000);
setInterval(sumindex,200000);

function sumindex(){
	$.ajax({
		url : "/adcount/detail",//
		data : {},
		type : "post",
		success : function(rs){
			if(rs.code=="000000"){
				var vehiclesum = rs.data.vehiclesum;
				var routesum = rs.data.routesum;
				$("#vehiclesum").html(vehiclesum[0].data);
				$("#routesum").html(routesum[0].data);
			}
		}
	});
}

//饼图
function paiindex(){
	//屏幕高度
    var htotal = $(window).height();
    $(".fenbu").css("height",htotal);
	////1-各省车辆 2-车型分布 3-货物分布 4-车辆总数 5-线路总数
	$.ajax({
		url : "/27count/detail",//
		data : {"type":"3"},
		type : "post",
		success : function(rs){
			if(rs.code=="000000"){
				var retData = rs.data;
				var pai = [];
				$.each(retData,function(index,item){
					pai.push({
						name: item.remark,
						y: parseFloat(item.data),
						color: item.desc1
					});
				});
//				console.info(pai);
				// 饼图
				$('#huowu').highcharts({
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
				    	enabled: false
				    },
				    plotOptions: {
				        pie: {
				            allowPointSelect: true,
				            cursor: 'pointer',
				            dataLabels: {
				                enabled: true,
//				                format: '{point.name}({point.percentage:.0f}%)',
				                style: {
				                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor),

				                }
				            }
				        }
				    },
				    series: [{
				        name: 'Brands',
				        colorByPoint: true,
				        data: pai
				    }]
				});
			}
		}
		
	});
}
//折线图
function lineindex(){
////1-各省车辆 2-车型分布 3-货物分布 4-车辆总数 5-线路总数 7-运单
	$.ajax({
		url : "/27count/detail",//
		data : {"type":"7"},
		type : "post",
		async: false,
		success : function(rs){
			if(rs.code == "000000"){
				var data = rs.data;
				var xpu = [];
				var ypu = [];
				for (var a = 0; a < data.length; a++) {
					xpu.push(data[a].remark);
					ypu.push(parseFloat(data[a].data));
				}
				//折线图
			    $('#bill').highcharts({
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
			            x: 0,
			            y: 0,
			            floating: true,
			            borderWidth: 1,
			        },
			        xAxis: {
			            categories: xpu,
			        },
			        yAxis: {
			            title: {
			                text: ' '
			            }
			        },
			        tooltip: {
			            shared: true,
			            valueSuffix: '单'
			        },
			        credits: {
			            enabled: false
			        },
			        plotOptions: {
			            areaspline: {
			                fillOpacity: 0.2
			            }
			        },
			        series: [{
			            name: '总量',
			            data: ypu,
			            color: "rgba(255,82,78,1)"


			        }]
			    });
			}
		}	
	});
}
//环装
function vehcileindex(){
////1-各省车辆 2-车型分布 3-货物分布 4-车辆总数 5-线路总数 7-运单
	$.ajax({
		url : "/27count/detail",//
		data : {"type":"2"},
		type : "post",
		async: false,
		success : function(rs){
			if(rs.code=="000000"){
				var data = rs.data;
				var pu = [];
				for (var a = 0; a < data.length; a++) {
					pu.push([
					         data[a].remark,parseFloat(data[a].data)
					         ]);
				}
			}
			// 环图
		    $('#car').highcharts({
		        chart: {
		            type: 'pie',
		            options3d: {
		                enabled: false,
		                alpha: 1
		            }

		        },
		        title: {
		            text: ' '
		        },
		        subtitle: {
		            text: ' '
		        },
		        plotOptions: {
		            pie: {
		            	 size:150,
		                 innerSize: 130,
		                 colors:[
		                     '#12bced',
		                     '#ff524e',
		                     '#27cf8e',
		                     '#e4d65c',
		                     '#f15c80',
		                     '#2b908f',
		                 ],
		            }
		        },
		        series: [{
		            name: '车型分布',
		            data: pu
		        }],
		        tooltip: {
		            enabled: false
		        }
		    });
		}
	});
}

function payindex(){
////1-各省车辆 2-车型分布 3-货物分布 4-车辆总数 5-线路总数 7-运单 8-运费
	$.ajax({
		url : "/27count/detail",//
		data : {"type":"8"},
		type : "post",
		async: false,
		success : function(rs){
			if(rs.code=="000000"){
				var data = rs.data;
				var xpu = [];
				var ypu = [];
				for (var a = 0; a < data.length; a++) {
					xpu.push(data[a].remark);
					ypu.push(parseFloat(data[a].data));
				}
				//柱状图
			    $('#yunfei').highcharts({
			        title: {
			            text: ' ' //置空
			        },
			        xAxis: {
			            categories: xpu
			        },
			        yAxis: {
			            title: {
			                text: ' ' //置空
			            }
			        },
			        legend: {
			            enabled:false
			        },
			        series: [{
			            type: 'column',
			            name: '运费',
			            data: ypu  //柱状图的值
			        }],
			        plotOptions: {
			            series: {
			                borderWidth: 0,
			                dataLabels: {
			                    enabled: true,
			                    data: [20,80,30]
			                }
			            },
			            column: {
			                pointWidth:45//设置柱状图宽度
			            }
			        },
			        tooltip: {
			            enabled: false
			        }

			    });
			}
		}
	});
}

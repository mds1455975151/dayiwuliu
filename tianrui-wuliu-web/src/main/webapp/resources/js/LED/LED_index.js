$(function () {
	shwoPageClass();
   
	goodsTypeShow();//已完成
    //车辆归属地
    cargsShow();
    frequencyShow();
    carShow();//已完成
    billShow();
    payShow();   
});
setInterval(goodsTypeShow,12000);
setInterval(cargsShow,10000);
setInterval(frequencyShow,14000);
setInterval(carShow,13000);
setInterval(billShow,15000);
setInterval(payShow,16000);
/**设置页面样式*/
function shwoPageClass(){
	var screenwid = $(document).width();
    var id_bill = $("#bill");
    if(screenwid > 1 && screenwid <1700){
        id_bill.css({"height":"175px"})
    }
    else if(screenwid > 1700){
        id_bill.css({"height":"195px","width":"450px"})
    }
    var id_pay = $("#pay");
    if(screenwid > 1 && screenwid <1700){
        id_pay.css("height","205px")
    }
    else if(screenwid > 1700){
        id_pay.css("height","220px")
    }
    var id_car = $("#car");
    if(screenwid > 1 && screenwid <1700){
        id_car.css({"height":"205px","width":"280px"});
    }
    else if(screenwid > 1700){
        id_car.css({"height":"220px","width":"300px"});
    }
    var id_goods = $("#goodstype");
    if(screenwid > 1 && screenwid <1700){
        id_goods.css({"height":"205px","width":"260px"});
    }
    else if(screenwid > 1700){
        id_goods.css({"height":"220px","width":"264px"});
    }
}
/** 运费柱状图*/
function payShow(){
	var pu_x = [];
	var pu_y = [];
	$.ajax({
		url:"/27count/detail",
		type:"POST",
		data:{"type":"3"},
		async: false,
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				var carHml = "";
				$.each(data,function(index,item){
					pu_x.push(
						item.remark
					);
					pu_y.push(
						Number(item.data)
					);
				});
			}
		}
	});
	$('#pay').highcharts({
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: ' ' //置空
	    },
	    xAxis: {
	        categories: pu_x,
	        tickWidth: 0,
	        //X轴下面的直线
	        lineWidth:0,
	        labels: {
	            y: 20, //x轴刻度往下移动20px
	            style: {
	                color: '#ffffff',//颜色
	                fontSize:'14px'  //字体
	            }
	        },
	    },
	    yAxis: {
	        title: {
	            text: ' ' //置空
	        },
	        gridLineWidth: 0,
	        labels: {
	            x: -10, //x轴刻度往下移动20px
	            style: {
	                color: '#ffffff',//颜色
	                fontSize:'14px'  //字体
	            }
	        },
	    },
	    tooltip: {
	        enabled: false
	    },
	    credits: {
	        enabled: false
	    },
	    legend: {
	        enabled:false
	    },
	    plotOptions: {
	        column: {
	            //设置柱状图宽度
	            pointWidth:30,
	            borderWidth: 0,
	            dataLabels:{
	                enabled:true, // dataLabels设为true
	                style:{
	                    color:'#D7DEE9'
	                }
	            }
	        }
	    },
	    series: [{
	        name: '运费',
	        data: pu_y,
	    }],
	
	});
}

/** 运量图*/
function billShow(){
	var pu_x = [];
	var pu_y = [];
	$.ajax({
		url:"/27count/detail",
		type:"POST",
		data:{"type":"3"},
		async: false,
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				var carHml = "";
				$.each(data,function(index,item){
					pu_x.push(
						item.remark
					);
					pu_y.push(
						Number(item.data)
					);
				});
			}
		}
	});
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
	        categories: pu_x,
	        lineColor: '#197F07',
	        //X轴下面每个数据之间的分割线
	        tickWidth: 0,
	        //X轴下面的直线
	        lineWidth:0,
	        labels: {
	            y: 25, //x轴刻度往下移动20px
	            style: {
	                color: '#ffffff',//颜色
	                fontSize:'14px'  //字体
	            }
	        },
	
	
	    },
	    yAxis: {
	        title: {
	            text: ''
	        },
	        //X轴横线的颜色
	        //gridLineColor: '#197F07',
	        //X轴横线的宽度，0是不显示
	        gridLineWidth: 0,
	        labels: {
	            x: 10, //x轴刻度往下移动20px
	            style: {
	                color: '#ffffff',//颜色
	                fontSize:'14px'  //字体
	            }
	        },
	    },
	    tooltip: {
	        shared: false,
	        valueSuffix: ''
	    },
	    credits: {
	        enabled: false
	    },
	    plotOptions: {
	        areaspline: {
	            fillOpacity: 0.4,
	            color:'#ffffff',
	        },
	
	    },
	
	    series: [{
	        name: '总量',
	        data: pu_y,
	        color: "rgba(255,82,78,1)",
	//            fillColor:'white'  填充区域的颜色
	
	    }]
	});
}

/** 车辆类型环图*/
function carShow(){
	//1-各省车辆 2-车型分布 3-货物分布 4-车辆总数 5-线路总数 7-运单
	var pu = [];
	$.ajax({
		url:"/27count/detail",
		type:"POST",
		data:{"type":"2"},
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				var carHml = "";
				$("#cat_type_html").empty();
				$.each(data,function(index,item){
					pu.push({
						name: item.remark,
						y: Number(item.data),
				        z: 118.7
					});
					$("#cat_type_html").append("<li><i class='i"+(index+1)+"'></i><label>"+item.remark+"</label></li>")
				});
				
				Highcharts.chart('car', {
				    chart: {
				        type: 'variablepie'
				    },
				    title: {
				        text: ''
				    },
				    credits: {
				        enabled: false
				    },
				    tooltip: {
				        enabled: false
				    },
				    plotOptions: {
				        variablepie: {
				            colors:[
				                '#27c37b',
				                '#37adca',
				                '#bb3e7b',
				                '#f7941d'
				            ],
				            format: '{point.percentage:.0f}%',
				            allowPointSelect: true,
				            cursor: 'pointer',
				            dataLabels: {
				                enabled: true,
				                style : {color:'#ffffff'}
				            }
				        }
				    },
				    series: [{
				        minPointSize: 10,
				        innerSize: '30%',
				        zMin: 0,
				        name: 'countries',
				        color:"#ffffff",
				        data: pu,
				        dataLabels:{
				            distance:30,
				            format: '{point.percentage:.0f}%',
				
				        }
				    }]
				});
			}
		}
	});
	
}

/** 车辆使用率*/
function frequencyShow(){
	
	var color = ["#d920ea","#20d0ea","#ea2076","#ea7620"];
	var pu = [];
	$.ajax({
		url:"/27count/detail",
		type:"POST",
		data:{"type":"3"},
		async: false,
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				var carHml = "";
//				$("#goods_type_html").empty();
				$.each(data,function(index,item){
					pu.push({
						name: item.remark,
						y: Number(item.data),
						color: color[index]
					});
//					$("#goods_type_html").append("<li><i class='i"+(index+1)+"'></i><label>"+item.remark+"</label></li>")
				});
			}
		}
	});
	
	$('#frequency').highcharts({
        chart: {
            type: 'pie',
            options3d: {
                enabled: false,
                alpha: 1
            }

        },
        title: {
            text: '',
        },
        subtitle: {
            text: ' '
        },
        plotOptions: {
            pie: {
                size:105,
                innerSize: 50,
                colors:[
                    '#81d612',
                    '#b02290',
                    '#20d5ea',
                    '#ea7620'
                ],
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: ({point.percentage:.0f}%)',
                    style : {color:'#ffffff'}
                }

            }
        },
        series: [{
            name: '车型分布',
            data: pu
        }],
        tooltip: {
            enabled: false
        },
        credits: {
            enabled: false
        }
    });
}

/** 车辆归属地饼图*/
function cargsShow(){
	
	var color = ["#d920ea","#20d0ea","#ea2076","#ea7620"];
	var pu = [];
	$.ajax({
		url:"/27count/detail",
		type:"POST",
		data:{"type":"3"},
		async: false,
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				var carHml = "";
				
//				$("#goods_type_html").empty();
				$.each(data,function(index,item){
					pu.push({
						name: item.remark,
						y: Number(item.data),
						color: color[index]
					});
//					$("#goods_type_html").append("<li><i class='i"+(index+1)+"'></i><label>"+item.remark+"</label></li>")
				});
			}
		}
	});
	$('#cargs').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: ' '
        },
        credits: {
            enabled: false
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                size:105,
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: ({point.percentage:.0f}%)',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'white'
                    }
                }
            }
        },
        tooltip: {
            enabled: false
        },
        series: [{
            name: 'Brands',
            colorByPoint: true,
            data: pu
        }]
    });
}

/** 货物类别饼图*/
function goodsTypeShow(){
	//1-各省车辆 2-车型分布 3-货物分布 4-车辆总数 5-线路总数 7-运单
	var color = ["#c0ca33","#43a047","#1e88e5","#f4511e","#00acc1","#3949ab"];
	$.ajax({
		url:"/27count/detail",
		type:"POST",
		data:{"type":"3"},
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				var carHml = "";
				var pu = [];
				$("#goods_type_html").empty();
				$.each(data,function(index,item){
					pu.push({
						name: item.remark,
						y: Number(item.data),
						color: color[index]
					});
					$("#goods_type_html").append("<li><i class='i"+(index+1)+"'></i><label>"+item.remark+"</label></li>")
				});
				
				Highcharts.chart('goodstype', {
			        chart: {
			            type: 'pie',
			            options3d: {
			                enabled: true,
			                alpha: 45
			            }
			        },
			        title: {
			            text: ''
			        },
			        plotOptions: {
			            pie: {
			                size:108,
			                innerSize: 50,
			                depth: 35,
			                dataLabels: {
			                    enabled: true,
			                    format: '{point.percentage:.0f}%',
			                    style : {color:'#ffffff'}
			                }
			            }
			        },
			        credits: {
			            enabled: false
			        },
			        series: [{
			            name: '',
			            data: pu
			        }]
			    });
			}
		}
	});
	
}
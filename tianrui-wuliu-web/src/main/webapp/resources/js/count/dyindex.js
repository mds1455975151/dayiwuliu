$(function () {
	dyindex();
	indexMap();
});
//setInterval(dyindex,10000);

function dyindex(){
	//屏幕高度
    var htotal = $(window).height();
    $(".fenbu").css("height",htotal);
	//车辆类型
	var vehicletype;
	//货物类型
	var cargotype;
	//路线总和
	var routesum;
	//车辆总和
	var vehiclesum;
	
	$.ajax({
		url : "/adcount/detail",//
		data : {},
		type : "post",
		success : function(rs){
			if(rs.code=="000000"){
				vehicletype = rs.data.vehicletype;
				cargotype = rs.data.cargotype;
				routesum = rs.data.routesum;
				vehiclesum = rs.data.vehiclesum;
				$("#vehiclesum").html(vehiclesum[0].data);
				$("#routesum").html(routesum[0].data);
			}
			
			//柱状图
			var cgtype = [];
			var cgdata = [];
			for (var a = 0; a < cargotype.length; a++) {
				cgtype.push(cargotype[a].remark);
				cgdata.push(parseFloat(cargotype[a].data));
			}
			$('#huowu').highcharts({
				title: {
					text: ' ' //置空
				},
				xAxis: {
					categories: cgtype
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
					data: cgdata  //柱状图的值
				}],
				plotOptions: {
					series: {
						borderWidth: 0,
						dataLabels: {
							enabled: true,
							data: [20,80,30]
						}
					}
				}
				
			});
			var vcdata = [];
			for (var a = 0; a < vehicletype.length; a++) {
				var d = [];
				d.push(vehicletype[a].remark);
				d.push(parseFloat(vehicletype[a].data));
				
				vcdata.push(d);
			}
			// 饼图
			$('#car').highcharts({
				chart: {
					type: 'pie',
					options3d: {
						enabled: true,
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
						innerSize: 150,
						depth: 14
					}
				},
				series: [{
					name: '车型分布',
					data: vcdata
				}]
			});
			
			
		    
		}
		
	});
}

function indexMap(){
	$.ajax({
		url : "/adcount/detailMap",//
		data : {},
		type : "post",
		success : function(rs){
			
			var vehicle,vehicleRs;
			vehicleRs=rs.data||[];
			
			//各省车辆
			vehicle={};
			$.each(vehicleRs,function(index,item){
				vehicle[item.remark]=item.data;
			});
			 //地图
		    Highcharts.setOptions({
		        lang:{
		            drillUpText:"返回 > {series.name}"
		        }
		    });
		    var data = Highcharts.geojson(Highcharts.maps['countries/cn/custom/cn-all-china']),small = $('#container').width() < 800;

		    // 给城市设置随机数据
		    $.each(data, function (i) {
//		        this.drilldown = this.properties['drill-key'];
//		        this.value = vehicle[this.drilldown];
		    	 this.drilldown = this.properties['drill-key'];
		         this.value = vehicle[this.drilldown];
		    });
		    function getPoint(e){
		        console.log(e.point.name);
		    }
		    function getShow(e){
		        alert(1);
		    }
			
			//初始化地图
		    $('#container').highcharts('Map', {

		        chart : {
		            spacingBottom:30,

		            events: {
		                drilldown: function (e) {

//		                    this.setTitle(null, { text: cname });
		                },
		                drillup: function () {
		                    this.setTitle(null, { text: '中国' });
		                }
		            }
		        },
		        tooltip: {
		            formatter:function(){
		                var htm="各省运输路线数<br/>";

		                if(this.point.drilldown){
		                    htm+=this.point.properties["cn-name"];
		                }else{
		                    htm+=this.point.name;
		                }
		                htm+=":"+this.point.value;
		                return htm;

		            }
		        },
		        credits:{
		            href:"javascript:goHome()",
		            text:" "
		        },
		        title : {
		            text : ' '
		        },

		        subtitle: {
		            text: ' ',
		        },

		        legend: small ? {} : {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle'
		        },
		        colorAxis: {

		            min: 0,
		            minColor: '#2853bb',
		            maxColor: '#030d24',
		            labels:{
		                style:{
		                    "color":"red","fontWeight":"bold"
		                }

		            }
		        },

		        mapNavigation: {
		            enabled: true,
		            buttonOptions: {
		                verticalAlign: 'bottom'
		            }
		        },

		        plotOptions: {
		            map: {
		                states: {
		                    hover: {
		                        color: '#048ddd'
		                    }
		                }
		            }
		        },

		        series : [{
		            data : data,
		            name: '中国',
		            dataLabels: {
		                enabled: true,
		                format: '{point.properties.cn-name}'
		            }
		        }],

		        drilldown: {
		            activeDataLabelStyle: {
		                color: '#ffffff',
		                textDecoration: 'none',
		                background:'none',
		                font:'normal',
		            },
		            drillUpButton: {
		                relativeTo: 'spacingBox',
		                position: {
		                    x: 0,
		                    y: 60
		                }
		            }
		        }
		    });
		}
		
	});
}



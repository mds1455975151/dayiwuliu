/**
 * Created by Administrator on 2017/1/10 0010.
 */
var geoCoordMap = [] ; 
var flyData = [];
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
var option = null;

getRoute();
setInterval(getRoute,600000);
function getRoute(){
	$.ajax({
		url:"/LEDCount/queryRouteData",
		type:"POST",
		data:{"dataType":"1",
			"ledType":"11",
			"pageNO":0,
			"pageSize":12
		},
		async: false,
		success:function(ret){
			if(ret.code==000000){
				flyData = [];
				geoCoordMap = [];
				geoCoordMap = ret.data.geoCoordMap;
				var rzData = ret.data.flyData;
				for (var a = 0; a < rzData.length; a++) {
					var p = [];
					p.push(rzData[a]);
					flyData.push([rzData[a][0].name, p,rzData[a][0].value,rzData[a][1].name]);
				}
				showRouteMap();
			}else{
				return;
			}
		}
	});
}

function showRouteMap(){
	//数据处理完毕
	var planePath='path://M395.52 234.665h595.392c18.304 0 33.088 15.616 33.088 34.944v453.824c0 19.264-14.784 34.88-33.088 34.88h-595.392c-18.24 0-33.088-15.616-33.088-34.88v-453.824c0-19.328 14.848-34.944 33.088-34.944zM261.184 374.313c0 0 58.816-17.472 58.816 52.352v256c0 0 5.248 74.56-56.768 74.56h-101.312c0 0-97.152 7.616-97.152 101.44 0 0-2.048 39.296-35.136 39.296 0 0-29.632 0-29.632-32.704v-218.24c0 0 6.912-34.88 27.584-65.472l70.272-137.472c0 0 16.512-69.824 66.176-69.824 18.56 2.24 97.152 0.064 97.152 0.064zM196.032 793.961c55.36 0 100.288 44.48 100.288 102.912s-44.928 105.792-100.288 105.792c-55.36 0-97.536-47.36-97.536-105.792 0.064-58.432 42.176-102.912 97.536-102.912zM329.408 793.193h396.928c0 0 30.976 42.56 30.976 70.912h-394.88c0 0-33.024-25.088-33.024-70.912zM890.688 793.193c54.208 0 98.176 46.4 98.176 103.616s-43.968 103.616-98.176 103.616-98.176-46.4-98.176-103.616c-0.064-57.216 43.904-103.616 98.176-103.616z';
	
	var convertData = function (data) {
	    var res = [];
	    for (var i = 0; i < data.length; i++) {
	        var dataItem = data[i];
	        var fromCoord = geoCoordMap[dataItem[0].name];
	        var toCoord = geoCoordMap[dataItem[1].name];
	        if (fromCoord && toCoord) {
	            res.push({
	                fromName: dataItem[0].name,
	                toName: dataItem[1].name,
	                coords: [fromCoord, toCoord]
	            });
	        }
	    }
	    return res;
	};
	
	var series = [];
	flyData.forEach(function (item, i) {
		var color = getColor();
		series.push({
	        name: item[0],
	        type: 'effectScatter',
	        coordinateSystem: 'geo',
	        zlevel: 2,
	        rippleEffect: {
	            brushType: 'stroke'
	        },
	        label: {
	            normal: {
	                show: true,
	                position: 'right',
	                formatter: '{b}'
	            }
	        },
	        symbolSize: 10,
	        itemStyle: {
	            normal: {
	                color: color[i]
	            }
	        },
	        data: item[1].map(function (dataItem) {
	            return {
	                name: dataItem[0].name,
	                value: geoCoordMap[dataItem[0].name]
	            };
	        })
	    },
	    {
	            name: item[0] + '',
	            type: 'lines',
	            zlevel: 1,
	            effect: {
	                show: true,
	                period: 6,
	                trailLength: 0.7,
	                color: '#fff',
	                //飞机底部的白色背景大小
	                symbolSize: 3
	            },
	            lineStyle: {
	                normal: {
	                    color: color,
	                    width: 0,
	                    curveness: 0.2
	                }
	            },
	            data: convertData(item[1])
	        },
	        {
	            //这个是两点之间的线，鼠标滑过弹出的层
	            name: item[0]+'-'+item[3]+'<br>'+item[2],
	            type: 'lines',
	            zlevel: 2,
	            effect: {
	                show: true,
	                period: 6,
	                trailLength: 0,
	                symbol: planePath,
	                //飞机的大小
	                symbolSize: 13
	            },
	            lineStyle: {
	                normal: {
	                    color: color,
	                    //两点之间的线宽度
	                    width: 1.5,
	                    opacity: 0.4,
	                    curveness: 0.2
	                }
	            },
	            data: convertData(item[1])
	        },
	        {
	            //这个是路线终点数据弹出层
	            name: '',
	            type: 'effectScatter',
	            coordinateSystem: 'geo',
	            zlevel: 2,
	            rippleEffect: {
	                brushType: 'stroke'
	            },
	            label: {
	                normal: {
	                    show: true,
	                    position: 'right',
	                    formatter: '{b}'
	                }
	            },
	            symbolSize: function (val) {
	                //val[2]就是value的值,加判断设置地图圆点的最大值
	                if(val[2]/8 < 15){
	                    return val[2] / 8;
	                }
	                else{
	                    return 15;
	                }
	            },
	            itemStyle: {
	                normal: {
	                    color: color
	                }
	            },
	            data:item[1].map(function (dataItem) {
	                return {
	                    name: dataItem[1].name,
	                    value: geoCoordMap[dataItem[1].name]
	                };
	            })
	        });
	});
	function dataItem(item){
		return {
	        name: item.name,
	        value: geoCoordMap[item.name].concat([item.value])
	    };
	}
	option = {
	    backgroundColor: '',
	    title : {
	        text: '',
	        //subtext: '数据覆盖率',
	        left: 'center',
	        textStyle : {
	            color: '#fff'
	        }
	    },
	    tooltip : {
	        trigger: 'item',
	        backgroundColor:'rgba(255,255,255,0.3)',
	        //这是地图弹出层的提示信息数据
	        formatTooltip:
	            function(params) {
	                var res = params.name+'<br/>';
	                var myseries = option.series;
	                for (var i = 0; i < myseries.length; i++) {
	                    res+= myseries[i].name;
	                    for(var j=0;j<myseries[i].data.length;j++){
	                        if(myseries[i].data[j].name==params.name){
	                            res+=' : '+myseries[i].data[j].value+'</br>';
	                        }
	                    }
	                }
	                return res;
	            }
	    },
	    legend: {
	        orient: 'vertical',
	        top: 'bottom',
	        left: 'right',
	        //data:['北京 Top10', '上海 Top10', '广州 Top10'],
	        textStyle: {
	            color: '#fff'
	        },
	        selectedMode: 'single'
	    },
	    geo: {
	        map: 'china',
	        label: {
	            emphasis: {
	                show: false
	            }
	        },
	        roam: true,
	        itemStyle: {
	            normal: {//选取前颜色
	                areaColor: '#03102d',
	                borderWidth: 2,
	                borderColor: '#5b8fd1',
	                opacity:0.7
	            },
	            emphasis: {//选取后颜色
	                areaColor: '#254ea9'
	            }
	        }
	    },
	    series: series
	};
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
}
function getColor(){
    var colorValue="0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f";
    var colorArray = colorValue.split(",");
    var color="#";//定义一个存放十六进制颜色值的字符串变量，先将#存放进去
    for(var i=0;i<6;i++){
        color+=colorArray[Math.floor(Math.random()*16)];
    }
    return color;
}

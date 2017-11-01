<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>index</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/tjled/css/dystyle.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/tjled/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/tjled/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/tjled/css/index.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/tjled/css/reset.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div class="tongji">
    <!-- 顶部logo-->
    <div class="tj_logo">
        <img src="${trRoot}/tianrui/tjled/images/logo.png">
    </div>
    <!-- 统计整体-->
    <div class="tj_cont">
        <!-- 5个统计数据-->
        <div class="tj_data">
            <ul>
                <li>
                    <p>累计总运费</p>
                    <h4>370,000万吨</h4>
                </li>
                <li>
                    <p>累计总运量</p>
                    <h4>370,000万吨</h4>
                </li>
                <li>
                    <p>合作车辆总数</p>
                    <h4>370,000万吨</h4>
                </li>
                <li>
                    <p>活跃车辆总数</p>
                    <h4>370,000万吨</h4>
                </li>
                <li>
                    <p>交易总量</p>
                    <h4>370,000万吨</h4>
                </li>
            </ul>
        </div>
        <!-- 数据下面整体图形-->
        <div class="tj_img">
            <div class="tj_imgtop">
                <!-- 左边运量、车辆类型-->
                <div class="tj_left">
                    <div class="bill">
                        <p class="titlebg">运量 </p>
                        <h4>当日运量：128500万元 </h4>
                        <div id="bill" class="billbg " style="width: 456px; height: 185px; "></div>
                    </div>
                    <div class="car">
                        <p class="titlebg">车辆类型</p>
                        <div id="car" style="width: 280px; height: 220px;float: left">
                        </div>
                        <div class="car_type">
                            <ul>
                                <li>
                                    <i class="i1"></i>
                                    <label>半挂车</label>
                                </li>
                                <li>
                                    <i class="i2"></i>
                                    <label>半挂车</label>
                                </li>
                                <li>
                                    <i class="i3"></i>
                                    <label>半挂车</label>
                                </li>
                                <li>
                                    <i class="i4"></i>
                                    <label>半挂车</label>
                            </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- 中间地图-->
                <div class="tj_mid">
                    <div id="container" style="width: 800px;height:550px;">
                    </div>
                </div>
                <!-- 右边运费、货物类别-->
                <div class="tj_right">
                    <div class="yunfei">
                        <div class="tj_rtt">
                            <p class="titlebg">运费</p>
                        </div>
                        <h4>当日运费：128500万元 </h4>
                        <div id="pay" class="yfbg" style="width: 450px; height: 220px; "></div>
                    </div>
                    <div class="goods">
                        <div class="tj_rtt">
                            <p class="titlebg">货物类别</p>
                        </div>
                        <div class="goods_type">
                            <ul>
                                <li>
                                    <i class="i1"></i>
                                    <label>硫酸渣</label>
                                </li>
                                <li>
                                    <i class="i2"></i>
                                    <label>粉煤炭</label>
                                </li>
                                <li>
                                    <i class="i3"></i>
                                    <label>水泥</label>
                                </li>
                                <li>
                                    <i class="i4"></i>
                                    <label>熟料</label>
                                </li>
                                <li>
                                    <i class="i5"></i>
                                    <label>石灰石</label>
                                </li>
                                <li>
                                    <i class="i6"></i>
                                    <label>原煤</label>
                                </li>
                            </ul>
                        </div>
                        <div id="goodstype" style="width: 250px; height: 200px;float:left">
                        </div>
                    </div>
                </div>
            </div>
            <!-- 数据最下面车辆归属地、货主、车主-->
            <div class="tj_imgbottom">
                <!-- 左边车辆归属地-->
                <div class="tj_btoleft">
                    <div class="car_gs">
                        <p class="titlebg">车辆归属地 </p>
                        <div id="cargs" style="width: 380px; height:220px; "></div>
                    </div>
                </div>
                <!-- 中间货主、车主-->
                <div class="tj_btomid">
                    <div class="btleft">
                        <h4>货主</h4>
                        <ul>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                        </ul>
                    </div>
                    <div class="btright">
                        <h4>车主</h4>
                        <ul>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                            <li>
                                <label>半挂车</label>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- 右边车辆使用频率-->
                <div class="tj_btoright">
                    <div class="yunfei">
                        <div class="tj_rtt">
                            <p class="titlebg">车辆使用频率</p>
                        </div>
                        <div id="frequency" style="width:400px; height: 220px; "></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${trRoot}/tianrui/tjled/js/jquery-1.11.1.js"></script>
<script src="${trRoot}/tianrui/tjled/js/highcharts.js"></script>
<script src="${trRoot}/tianrui/tjled/js/variable-pie.js"></script>
<script src="${trRoot}/tianrui/tjled/js/highcharts-3d.js"></script>
<!--地图-->
<script src="${trRoot}/tianrui/tjled/js/echarts.js"></script>
<script src="${trRoot}/tianrui/tjled/js/china.js"></script>
<script src="${trRoot}/tianrui/tjled/js/indexditu.js"></script>
<script>
$(function () {

    // 货物类别饼图
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
            data: [{
                name: '',
                y: 50,
                color: "#c0ca33"
            }, {
                name: '',
                y:80,
                color: "#43a047",
            }, {
                name: '',
                y: 100,
                color: "#1e88e5",
            }, {
                name: '',
                y: 200,
                color: "#f4511e",
            }, {
                name: '',
                y: 200,
                color: "#00acc1",
            }, {
                name: '',
                y: 200,
                color: "#3949ab",
            }]
        }]
    });

    //车辆归属地饼图
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
            data: [{
                name: '延安',
                y: 50,
                color: "#d920ea"
            }, {
                name: '许昌市',
                y:80,
                color: "#20d0ea",
            }, {
                name: '南阳市',
                y: 100,
                color: "#ea2076",
            }, {
                name: '平顶山市',
                y: 200,
                color: "#ea7620",
            }]
        }]
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
                size:120,
                innerSize: 60,
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
            data: [
                ['1次以下', 300],
                ['2-9次', 210],
                ['10-99次', 10],
                ['100次以上', 10]
            ]
        }],
        tooltip: {
            enabled: false
        },
        credits: {
            enabled: false
        }
    });


// 车辆类型环图
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
//        headerFormat: '',
//        pointFormat: '<span style="color:{point.color}">\u25CF</span> <b> {point.name}</b><br/>' +
//        '市: <b>{point.y}</b><br/>' +
//        'Population density (people per square km): <b>{point.z}</b><br/>'
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
        data: [{
            name: 'Spain',
            y: 505370,
            z: 92.9,
        }, {
            name: 'France',
            y: 551500,
            z: 118.7,
        }, {
            name: 'Poland',
            y: 312685,
            z: 124.6
        },{
            name: 'Germany',
            y: 357022,
            z: 235.6
        }],
        dataLabels:{
            distance:30,
            format: '{point.percentage:.0f}%',

        }
    }]
});

//运量图
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
        categories: ["2017-06","2017-06","2017-06","2017-07","2017-08"],
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
        data: [3200,1000,1881,2354,9600],
        color: "rgba(255,82,78,1)",
//            fillColor:'white'  填充区域的颜色

    }]
});

//运费柱状图
$('#pay').highcharts({
    chart: {
        type: 'column'
    },
    title: {
        text: ' ' //置空
    },
    xAxis: {
        categories: ['2017-06', '2017-06', '2017-06', '2017-06', '2017-06'],
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
        data: [{ y: 10, color: '#1fbdda', name: '5' }, { y: 23, color: '#13b88b', name: '5' },
            { y:45, color: '#1fbdda', name: '5' }, { y:70, color: '#13b88b', name: '20' },
            { y: 55, color: '#1fbdda', name: '5' }],

    }],

});

});
</script>

</body>
</html>
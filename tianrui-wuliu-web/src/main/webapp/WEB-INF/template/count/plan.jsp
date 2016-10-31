<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>index</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/dystyle.css" rel="stylesheet">

</head>
<body>
<!-- head -->
<jsp:include page="head/conhead.jsp"></jsp:include>
<!-- head -->

<div class="data_yunying wrap">
    <h5 class="fbold">运营数据</h5>
    <div class="data_road">
        <div class="data_roadtit">
            <label>货源总量</label>
            <div class="data_roadshow">
                <span>总量</span>
                <img src="images/roadlan.png">
                <span>新增</span>
                <img src="images/roadlv.png">
            </div>
        </div>
        <div class="data_roadall">
            <div class="goods_chart">
                <h5>吨</h5>
                <!--折线图-->
                <div id="container" style="width: 988px; height:250px;">
                </div>
                <!--折线图-->
                <div class="goods_time">
                    <label>时间</label>
                </div>
            </div>
            <div class="data_roadr">
                <div class="data_roadrline border_goods">
                    <h5>货源总量</h5>
                    <p>
                        <label class="colorgreen">86456452</label>
                        <span>吨</span>
                    </p>
                </div>
                <div class="data_roadrline">
                    <h5>新增货源量</h5>
                    <p>
                        <label class="colorred">864512256452</label>
                        <span>吨</span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="bghui">
    <div class="wrap foot">
        <div class="footcont">
            <p>中原大易科技 Copyright © 2016 豫ICP备12615289号-86</p>
        </div>
        <div class="footimg">
            <p>安卓客户端</p>
            <img src="images/erw.png">
        </div>
    </div>

</div>
<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script src="${trRoot}/tianrui/js/highcharts.js"></script>
<script src="${trRoot}/tianrui/js/highcharts-map.js"></script>
<script src="${trRoot}/tianrui/js/highcharts-data.js"></script>
<script src="${trRoot}/tianrui/js/highcharts-drilldown.js"></script>
<script type="text/javascript" src="http://sandbox.runjs.cn/uploads/rs/228/zroo4bdf/cn-china-by-peng8.js"></script>

<script>
    $(document).ready(function(){
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
                categories: ["11月28","11月29","11月30","12月01","12月01","12月01","12月01"],
            },
            yAxis: {
                title: {
                    text: ' '
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
                data: [1, 30, 4,180, 3, 5, 400],
                color: "rgba(59,186,236,1)",


            }, {
                name: '新增',
                data: [300, 40, 3, 50, 4, 500, 120],
                color: "rgba(175,216,94,1)"
            }]
        });

    });
</script>

</body>
</html>
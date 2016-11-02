<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>vehicle</title>
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
            <label>车辆总数</label>
            <div class="data_roadshow">
                <span>新增车辆</span>
                <img src="${trRoot}/tianrui/images/roadlan.png">
                <span>活跃车辆</span>
                <img src="${trRoot}/tianrui/images/roadred.png">
            </div>
        </div>
        <div class="data_roadall">
            <div class="goods_chart">
                <h5>趟</h5>
                <!--折线图-->
                <div id="line2" style="width: 988px; height:250px;">
                </div>
                <!--折线图-->
                <div class="goods_time">
                    <label>时间</label>
                </div>
            </div>
            <div class="data_roadr">
                <div class="data_roadcar">
                    <h4>活跃车辆</h4>
                    <div>
                        <label class="colorblue">1000</label>
                        <span>辆</span>
                    </div>
                    <div class="car_pie">
                        <p>比例</p>
                        <div class="border_pie">
                            <!--饼-->
                            <div  id="mypie" style="width: 350px; height:200px;">
                            </div>
                            <!--饼-->
                        </div>

                    </div>
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
<script type="text/javascript" src="/resources/js/count/vehicle.js"></script>
<script>
    $(document).ready(function(){
        //折线图
        
        
    });
</script>

</body>
</html>
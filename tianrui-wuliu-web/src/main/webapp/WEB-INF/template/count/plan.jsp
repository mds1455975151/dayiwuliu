<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>大易物流</title>
    <meta name="keywords" content="大易物流"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/dystyle.css" rel="stylesheet">
	<link href="${trRoot}/tianrui/css/huoyun.css" rel="stylesheet">
	<link href="${trRoot}/tianrui/css/swiper.min.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/banner-animate.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/animate.min.css" rel="stylesheet">
</head>
<body>
<!-- head -->
<jsp:include page="head/conhead.jsp"></jsp:include>
<!-- head -->

<div class="data_yunying wrap">
    <div class="data_road">
        <div class="data_roadtit">
            <label>货源计划总量</label>
            <div class="data_roadshow">
                <span>总量</span>
                <img src="${trRoot}/tianrui/images/roadlan.png">
                <span>新增</span>
                <img src="${trRoot}/tianrui/images/roadlv.png">
            </div>
        </div>
        <div class="data_roadall">
            <div class="goods_chart">
                <h5>吨</h5>
                <!--折线图-->
                <div id="container" style="width: 920px; height:250px;">
                </div>
                <!--折线图-->
                <div class="goods_time">
                    <label>时间</label>
                </div>
            </div>
            <div class="data_roadr">
<!--                 <div class="data_roadrline border_goods">
                    <h5>货源计划总量</h5>
                    <p>
                        <label class="colorgreen" id="sumCountPlan"></label>
                        <span>万吨</span>
                    </p>
                </div> -->
                <div class="data_roadrline">
                    <h5>昨日新增计划量</h5>
                    <p>
                        <label class="colorred" id="addCountPlan"></label>
                        <span>万吨</span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<!--地图下面加上合作-->
<div class="coperation">
    <div class="data_title">
	     <h3>合作伙伴</h3>
	     <div class="blueline"></div>
	     <img src="${trRoot}/tianrui/images/copartner.png">
    </div>
    <div class="cop_solo">
        <img src="${trRoot}/tianrui/images/hb1.jpg">
    </div>
    <div class="cop_solo">
        <img src="${trRoot}/tianrui/images/hb2.jpg">
    </div>
    <div class="cop_solo">
        <img src="${trRoot}/tianrui/images/hb3.jpg">
    </div>
    <div class="cop_solo">
        <img src="${trRoot}/tianrui/images/hb4.jpg">
    </div>
    <div class="cop_solo">
        <img src="${trRoot}/tianrui/images/hb5.jpg">
    </div>
    <div class="cop_solo">
        <img src="${trRoot}/tianrui/images/hb6.jpg">
    </div>
    <div class="cop_solo">
        <img src="${trRoot}/tianrui/images/hb7.jpg">
    </div>
    <div class="cop_solo">
        <img src="${trRoot}/tianrui/images/hb8.jpg">
    </div>
</div>
<jsp:include page="head/foot.jsp"></jsp:include>

<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script src="${trRoot}/tianrui/js/highcharts.js"></script>
<script src="${trRoot}/tianrui/js/highcharts-map.js"></script>
<script src="${trRoot}/tianrui/js/highcharts-data.js"></script>
<script src="${trRoot}/tianrui/js/highcharts-drilldown.js"></script>
<script type="text/javascript" src="http://sandbox.runjs.cn/uploads/rs/228/zroo4bdf/cn-china-by-peng8.js"></script>
<script type="text/javascript" src="/resources/js/count/plan.js?0609"></script>
<script src="${trRoot}/tianrui/js/swiper.min.js"></script>
<script type="text/javascript" src="/resources/js/count/showmessage.js?12162"></script>
<script type="text/javascript">
var trRoot = '${trRoot}';
</script>

</body>
</html>
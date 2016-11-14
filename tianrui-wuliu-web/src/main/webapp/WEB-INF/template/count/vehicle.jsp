<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
                <h5>辆</h5>
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
                    	
                        <label class="colorblue"><fmt:formatNumber value="${actvehicle.sumdate }" type="number" pattern="0" /></label>
                        <input type="hidden" id ="paivechiclesum" value="<fmt:formatNumber value="${vehicle.sumdate }" type="number" pattern="0" />">
                        <input type="hidden" id ="paivechicleact" value="<fmt:formatNumber value="${actvehicle.sumdate }" type="number" pattern="0" />">
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
<jsp:include page="head/foot.jsp"></jsp:include>

<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script src="${trRoot}/tianrui/js/highcharts.js"></script>
<script src="${trRoot}/tianrui/js/highcharts-map.js"></script>
<script src="${trRoot}/tianrui/js/highcharts-data.js"></script>
<script src="${trRoot}/tianrui/js/highcharts-drilldown.js"></script>
<script type="text/javascript" src="/resources/js/count/vehicle.js"></script>

</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <label>运输路线</label>
        </div>
        <div class="data_roadall">
            <div class="data_roadl">
                <div id="container" style="height: 500px; min-width: 310px; max-width: 800px; margin: 0 auto"></div>
            </div>
            <div class="data_roadm">
                <div class="data_percent">
                    <div class="data_pline">
                        <label id="remarkname">河南</label>
                        <span class="coloryello" id="sumroutedate">9个</span>
                    </div>
                </div>
                <div class="data_big">
                    <h5>运输最热地点</h5>
                    <c:forEach items="${routemax }" var="aa">
                    <p> ${aa.desc1 } ------ ${aa.sumdate } 个</p>
                   	</c:forEach>
                </div>
            </div>
            <div class="data_roadr mt60">
                <div class="data_roadrt border_goods">
                    <h5>运输路线总数</h5>
                    <p>
                        <label class="colorgreen">${route.sumdate }</label>
                        <span>个</span>
                    </p>
                </div>
                <div class="data_roadrb">
                    <h5>本月新增</h5>
                    <p>
                        <label class="colorred">${route.sumdate - monthroute.sumdate}</label>
                        <span>个</span>
                    </p>
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
<script type="text/javascript" src="${trRoot}/tianrui/js/highcharts-cn-china.js" charset="utf-8"></script>
<script type="text/javascript" src="/resources/js/count/route.js"></script>
</body>
</html>
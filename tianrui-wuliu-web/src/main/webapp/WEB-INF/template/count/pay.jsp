<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
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
    <div class="data_yfall">
        <div class="data_yfleft data_road">
                <div class="data_roadtit">
                    <label>运费</label>
                </div>
                <div class="data_yfcont">
                    <div class="data_yunfei">
                        <h5>运费总和</h5>
                        <label class="colorjy">￥
						<fmt:formatNumber value="${pay.sumdate/10000 }" pattern="#.##" minFractionDigits="2" />
						</label>万
                    </div>
                    <div class="clear"></div>
                    <div class="yunf_chart">
                   	 	<h5>万</h5>
                        <!--折线图-->
                        <div id="container" style="width: 760px; height: 280px; "></div>
                        <!--折线图-->
                        <div class="yf_border"></div>
                    </div>
                </div>
        </div>
        <div class="data_yfright data_road">
            <div class="data_roadtit">
                <label>最新消息动态</label>
            </div>
            <ul>
            <c:forEach items="${paybill }" var="pb">
            	<li><label>${pb.showtimeStr }完成<fmt:formatNumber type="number" value="${pb.adddate }" maxFractionDigits="0"/>批订单</label><span>${pb.showtimeStr }</span></li>
            </c:forEach>
            </ul>
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
<script type="text/javascript" src="http://sandbox.runjs.cn/uploads/rs/228/zroo4bdf/cn-china-by-peng8.js"></script>
<script type="text/javascript" src="/resources/js/count/pay.js?12.3"></script>
<script src="${trRoot}/tianrui/js/swiper.min.js"></script>
<script type="text/javascript" src="/resources/js/count/showmessage.js?12162"></script>
<script type="text/javascript">
var trRoot = '${trRoot}';
</script>
</body>
</html>
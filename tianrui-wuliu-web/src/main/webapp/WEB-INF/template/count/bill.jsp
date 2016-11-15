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
            <label>交易量</label>
        </div>
        <div class="data_roadall">
            <div class="goods_chart">
                <h5>元</h5>
                <!--折线图-->
                <div id="line3" style="width: 988px; height:250px;"></div>
                <!--折线图-->
                <div class="goods_time">
                    <label>时间</label>
                </div>
            </div>
            <div class="data_roadr">
                <div class="data_jiaoyi border_goods">
                    <h5>昨天成交运单</h5>
                    <label class="coloryello" id="ytodaybill">单</label>
                </div>
                <div class="data_jiaoyi border_goods">
                    <h5>今天成交运单</h5>
                    <label class="colorred" id="todaybill">单</label>
                </div>
                <div class="data_jiaoyi">
                    <h5>运单总量</h5>
                    <label class="colorjy">${bill.sumdate }单</label>
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
<script type="text/javascript" src="/resources/js/count/bill.js"></script>

</body>
</html>
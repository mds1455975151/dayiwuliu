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

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<!-- head -->
<jsp:include page="head/conhead.jsp"></jsp:include>
<!-- head -->
<div class="data_yunying wrap">
    <h5 class="fbold">运营数据</h5>
    <div class="data_yfall">
        <div class="data_yfleft data_road">
                <div class="data_roadtit">
                    <label>运费</label>
                </div>
                <div class="data_yfcont">
                    <div class="data_yunfei">
                        <h5>运费总和</h5>
                        <label class="colorjy">￥${pay.sumdate }</label>
                    </div>
                    <div class="clear"></div>
                    <div class="yunf_chart">
                        <h5>元</h5>
                        <!--折线图-->
                        <div id="container" style="width: 790px; height: 280px; "></div>
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
                <li><label>2016年9月18号15:32完成56批订单</label><span>2016-09-18</span></li>
                <li><label>2016年9月18号15:32完成56批订单</label><span>2016-09-18</span></li>
                <li><label>2016年9月18号15:32完成56批订单</label><span>2016-09-18</span></li>
                <li><label>2016年9月18号15:32完成56批订单</label><span>2016-09-18</span></li>
                <li><label>2016年9月18号</label><span>2016-09-18</span></li>
                <li><label>2016年9月18号15:32完成56批订单</label><span>2016-09-18</span></li>
                <li><label>2016年9月18号15:32完成56批订单</label><span>2016-09-18</span></li>
                <li><label>2016年9月18号15:32完成56批订单</label><span>2016-09-18</span></li>
                <li><label>司机张师傅2016年9月18号15:32完成56批订单</label><span>2016-09-18</span></li>
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
<script type="text/javascript" src="/resources/js/count/pay.js"></script>
</body>
</html>
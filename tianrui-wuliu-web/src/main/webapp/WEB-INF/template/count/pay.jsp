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
                        <label class="colorjy">￥86,456,452</label>
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
    $(document).ready(function () {
        //柱状图
        $('#container').highcharts({
            title: {
                text: ' ' //置空
            },
            xAxis: {
                categories: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            },
            yAxis: {
                title: {
                    text: ' ' //置空
                }
            },
            legend: {
                enabled:false
            },
            series: [{
                type: 'column',
                name: '运费',
                data: [30, 2, 18, 3, 4,20,80,30,60,12,77,66]  //柱状图的值
            }]
        });

        // 控制消息标题的长度，多出的用省略号标示
        $(".data_yfright ul li").each(function(){
            //字符个数
            var maxwidth=26;
            var contr = $(this).find("label");
            if($(contr).text().length>maxwidth){
                $(contr).text($(contr).text().substring(0,maxwidth));
                $(contr).html($(contr).html()+".....");
            }
        });
    });
</script>

</body>
</html>
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

    <link href="${trRoot}/tianrui/tj/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/tj/css/dystyle.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/tj/css/bootstrap.css" rel="stylesheet">

</head>
<body>

<div class="tongji">
    <div class="tj_logo">
        <img src="${trRoot}/tianrui/tj/images/logo.png">
    </div>
    <div class="tj_cont">
        <div class="tj_left">
            <div class="goods">
                <h4>货物分布</h4>
                <div id="huowu" class="zhuimg" style="width: 366px; height: 293px; "></div>
            </div>
            <div class="car">
                <h4>车型分布</h4>
                <div id="car" style="width: 580px; height: 260px; ">
                </div>
            </div>
        </div>
        <div class="tj_right">
            <div class="tj_data">
                <label>活跃车辆总数</label><span id="vehiclesum">2000</span><label>辆</label>
                <label class="ml150">运输线路</label><span id="routesum">200</span><label>条</label>
            </div>
            <div class="fenbu_map">
                <div id="container" style="width: 980px;height:800px;">
                </div>
            </div>
        </div>
    </div>
    <div class="tj_table">
        <h4>物流平台运输线路统计<input type="hidden" id="showvalue"></h4>
        <table class="table table-bordered" id="table_0">
            <thead>
            <tr>
                <th rowspan="2">运输业务	</th>
                <th rowspan="2">运输地点	</th>
                <th colspan="2">卫辉</th>
                <th colspan="2">汝州 </th>
                <th colspan="2">南召</th>
                <th colspan="2">荥阳</th>
                <th colspan="2"> 光山</th>
                <th colspan="2"> 禹州</th>
                <th colspan="2"> 萧县</th>
            </tr>
            <tr>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>

            </tr>
            </thead>
            <tbody id="html_table_0">
            
            </tbody>
        </table>
        
        <table class="table table-bordered"  id="table_1">
            <thead>
            <tr>
                <th rowspan="2">运输业务	</th>
                <th rowspan="2">运输地点	</th>
                <th colspan="2">卫辉</th>
                <th colspan="2">汝州 </th>
                <th colspan="2">南召</th>
                <th colspan="2">荥阳</th>
                <th colspan="2"> 光山</th>
                <th colspan="2"> 禹州</th>
                <th colspan="2"> 萧县</th>
            </tr>
            <tr>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>

            </tr>
            </thead>
            <tbody id="html_table_1">
            </tbody>
        </table>
       
        <table class="table table-bordered"  id="table_2">
            <thead>
            <tr>
                <th rowspan="2">运输业务	</th>
                <th rowspan="2">运输地点	</th>
                <th colspan="2">中牟</th>
                <th colspan="2">商丘 </th>
                <th colspan="2">周口</th>
                <th colspan="2">姚电</th>
                <th colspan="2"> 许昌</th>
                <th colspan="2"> 诚兴</th>
                <th colspan="2"> 威企</th>
            </tr>
            <tr>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>

            </tr>
            </thead>
            <tbody id="html_table_2">
            </tbody>
        </table>
        <table class="table table-bordered"  id="table_3">
            <thead>
            <tr>
                <th rowspan="2">运输业务	</th>
                <th rowspan="2">运输地点	</th>
                <th colspan="2">中牟</th>
                <th colspan="2">商丘 </th>
                <th colspan="2">周口</th>
                <th colspan="2">姚电</th>
                <th colspan="2"> 许昌</th>
                <th colspan="2"> 诚兴</th>
                <th colspan="2"> 威企</th>
            </tr>
            <tr>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>

            </tr>
            </thead>
            <tbody id="html_table_3">
            </tbody>
        </table>
        <table class="table table-bordered" id="table_4">
            <thead>
            <tr>
                <th rowspan="2">运输业务	</th>
                <th rowspan="2">运输地点	</th>
                <th colspan="2">辽中</th>
                <th colspan="2">阜新 </th>
                <th colspan="2">徐州</th>
                <th colspan="2">亳州</th>
                <th colspan="2">荥阳</th>
                <th colspan="2"> 马市坪</th>
                <th colspan="2"> 鲁山</th>
            </tr>
            <tr>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>

            </tr>
            </thead>
            <tbody id="html_table_4">
            </tbody>
        </table>
        <table class="table table-bordered"  id="table_5">
            <thead>
            <tr>
                <th rowspan="2">运输业务	</th>
                <th rowspan="2">运输地点	</th>
                <th colspan="2">辽中</th>
                <th colspan="2">阜新 </th>
                <th colspan="2">徐州</th>
                <th colspan="2">亳州</th>
                <th colspan="2">荥阳</th>
                <th colspan="2"> 马市坪</th>
                <th colspan="2"> 鲁山</th>
            </tr>
            <tr>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
                <th >运距（公里）</th>
                <th>年次车数 </th>
            </tr>
            </thead>
            <tbody id="html_table_5">
            </tbody>
        </table>
    </div>


</div>

<script type="text/javascript" src="${trRoot}/tianrui/tj/js/jquery-1.11.1.js"></script>
<script src="${trRoot}/tianrui/tj/js/highcharts.js"></script>
<script src="${trRoot}/tianrui/tj/js/highcharts-map.js"></script>
<script src="${trRoot}/tianrui/tj/js/highcharts-data.js"></script>
<script src="${trRoot}/tianrui/tj/js/highcharts-drilldown.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/tj/js/highcharts-cn-china.js" charset="utf-8"></script>
<script type="text/javascript" src="/resources/js/count/dyindex.js"></script>
<script type="text/javascript" src="/resources/js/count/setInterval.js"></script>

</body>
</html>
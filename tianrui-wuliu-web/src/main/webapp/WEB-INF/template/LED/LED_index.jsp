<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>index</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/tjled/css/dystyle.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/tjled/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/tjled/css/base.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div class="tongji">
    <!-- 顶部logo-->
    <div class="tj_logo">
        <img src="${trRoot}/tianrui/tjled/images/logo.png">
    </div>
    <!-- 统计整体-->
    <div class="tj_cont">
        <!-- 5个统计数据-->
        <div class="tj_data">
            <ul>
                <li>
                    <p>累计总运费</p>
                    <h4 id="zyf">370,000万吨</h4>
                </li>
                <li>
                    <p>累计总运量</p>
                    <h4 id="qsl">370,000万吨</h4>
                </li>
                <li>
                    <p>合作车辆总数</p>
                    <h4 id="clzs">3万吨</h4>
                </li>
                <li>
                    <p>活跃车辆总数</p>
                    <h4 id="hycl">370,000万吨</h4>
                </li>
                <li>
                    <p>交易总量</p>
                    <h4 id="ydzs">370,000万吨</h4>
                </li>
            </ul>
        </div>
        <!-- 数据下面整体图形-->
        <div class="tj_img">
            <div class="tj_imgtop">
                <!-- 左边运量、车辆类型-->
                <div class="tj_left">
                    <div class="bill">
                        <p class="titlebg">运量 </p>
                        <h4>当日运量：<span id="dtyl"></span></h4>
                        <div id="bill" class="billbg" style="width: 100%;"></div>
                    </div>
                    <div class="car">
                        <p class="titlebg">车辆类型</p>
                        <div id="car" style="float: left">
                        </div>
                        <div class="car_type">
                            <ul id="cat_type_html">
                                <li>
                                    <i class="i1"></i>
                                    <label>半挂车</label>
                                </li>
                                <li>
                                    <i class="i2"></i>
                                    <label>半挂车</label>
                                </li>
                                <li>
                                    <i class="i3"></i>
                                    <label>半挂车</label>
                                </li>
                                <li>
                                    <i class="i4"></i>
                                    <label>半挂车</label>
                            	</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- 中间地图-->
                <div class="tj_mid">
                    <div id="container" style="width:100%;height:580px;">
                    </div>
                </div>
                <!-- 右边运费、货物类别-->
                <div class="tj_right">
                    <div class="yunfei">
                        <div class="tj_rtt">
                            <p class="titlebg">运费</p>
                        </div>
                        <h4>当日运费：<span id="dtyf"></span> </h4>
                        <div id="pay" class="yfbg" style="width:95%; "></div>
                    </div>
                    <div class="goods">
                        <div class="tj_rtt">
                            <p class="titlebg">货物类别</p>
                        </div>
                        <div class="goods_type">
                            <ul id="goods_type_html">
                                <li>
                                    <i class="i1"></i>
                                    <label>硫酸渣琉</label>
                                </li>
                                <li>
                                    <i class="i2"></i>
                                    <label>粉煤炭</label>
                                </li>
                                <li>
                                    <i class="i3"></i>
                                    <label>水泥</label>
                                </li>
                                <li>
                                    <i class="i4"></i>
                                    <label>熟料</label>
                                </li>
                                <li>
                                    <i class="i5"></i>
                                    <label>石灰石</label>
                                </li>
                                <li>
                                    <i class="i6"></i>
                                    <label>原煤</label>
                                </li>
                            </ul>
                        </div>
                        <div id="goodstype" style="float:left">
                        </div>
                    </div>
                </div>
            </div>
            <!-- 数据最下面车辆归属地、货主、车主-->
            <div class="tj_imgbottom">
                <!-- 左边车辆归属地-->
                <div class="tj_btoleft">
                    <div class="car_gs">
                        <p class="titlebg">车辆归属地 </p>
                        <div id="cargs" style="width: 100%; height:200px; "></div>
                    </div>
                </div>
                <!-- 中间货主、车主-->
                <div class="tj_btomid">
                    <div class="btleft fl" id="myscroll1">
                        <h4>货主</h4>
                        <ul id="ownerThml">
                        </ul>
                    </div>
                    <div class="btleft fr" id="myscroll2">
                        <h4>车主</h4>
                        <ul id="venderHtml">
                        
                        </ul>
                    </div>
                </div>
                <!-- 右边车辆使用频率-->
                <div class="tj_btoright">
                    <div class="yunfei">
                        <div class="tj_rtt">
                            <p class="titlebg">车辆使用频率</p>
                        </div>
                        <div id="frequency" style="width:100%; height: 200px; "></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${trRoot}/tianrui/tjled/js/jquery-1.11.1.js"></script>
<script src="${trRoot}/tianrui/tjled/js/highcharts.js"></script>
<script src="${trRoot}/tianrui/tjled/js/variable-pie.js"></script>
<script src="${trRoot}/tianrui/tjled/js/highcharts-3d.js"></script>
<!--地图-->
<script src="${trRoot}/tianrui/tjled/js/echarts.js"></script>
<script src="${trRoot}/tianrui/tjled/js/china.js"></script>
<script src="/resources/js/LED/indexditu.js?111522"></script>
<script src="/resources/js/LED/LED_index.js?11085"></script>
<script type="text/javascript">
$.fn.extend({
    Scroll:function(opt,callback){
        //参数初始化
        var timerId;
        if(!opt) var opt={};
        var _this=this.eq(0).find("ul:first");
        var lineH=_this.find("li:first").height(), //获取行高
                line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10), //每次滚动的行数，默认为一屏，即父容器高度
                speed=opt.speed?parseInt(opt.speed,10):500, //卷动速度，数值越大，速度越慢（毫秒）
                timer=opt.timer?parseInt(opt.timer,10):3000; //滚动的时间间隔（毫秒）
        if(line==0) line=1;
        var upHeight=0;
        //滚动函数
        var scrollUp=function(){
            _this.animate({marginTop:upHeight},speed,function(){
                var i =1;
                for(i=1;i <= line;i++){
                    _this.find("li:first").appendTo(_this);
                }
                _this.css({marginTop:0});
            });
        }
        //鼠标事件绑定
        _this.hover(function(){
            if (timerId) {
                clearInterval(timerId);
            }
        },function(){
            timerId=setInterval(function() {
                scrollUp();
            },timer);
        }).mouseout();
    }
});
$("#myscroll1").Scroll({line:4,speed:3000,timer:3000});
$("#myscroll2").Scroll({line:4,speed:3000,timer:3000});
</script>
</body>
</html>
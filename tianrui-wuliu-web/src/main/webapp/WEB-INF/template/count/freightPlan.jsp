<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>货运计划</title>
    <meta name="keywords" content="大易物流"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/dystyle.css" rel="stylesheet">
	<link href="${trRoot}/tianrui/css/huoyun.css" rel="stylesheet">
	<link rel="stylesheet" href="/resources/css1/banner01.css">
    <link rel="stylesheet" href="/resources/css1/animate.min.css">
</head>
<body>
<!-- head -->
<div class="bghygray">
    <div class="wrap1400">
        <label><a href="/publicMember/registerPage" class="colorred"> [免费注册]</a></label>
        <label><a href="/count/route">请[登录]</a></label>
        <label>欢迎来到大易物流平台！</label>
    </div>
</div>
<div class="w1200">
    <div class="hynav">
        <img src="${trRoot}/tianrui/images/hylogo.png">
        <ul>
        	<li ><a href="/count/route"><h4>首页</h4></a></li>
            <li ><a href="/count/freightDemand"><h4>货源中心</h4></a></li>
            <li class="withbg"><a href="/count/freightPlan"><h4>货运计划</h4></a></li>
            <li><a href="/count/onWaybill"><h4>在途运单</h4></a></li>
        </ul>
    </div>
</div>
<!-- head -->
<div class="" style="background-color: #4b01a6;">
    <div class="bannerBox">
        <div class="Bj">
            <img src="/resources/images1/66.png"  id="img6">
            <img src="/resources/images1/dayiwul.png" class="animated bounceIn" id="img1">
            <img src="/resources/images1/yun03.png"  id="img7">
            <img src="/resources/images1/yun01.png"  id="img2">
            <img src="/resources/images1/yun02.png"  id="img3">
            <img src="/resources/images1/xiaoyuan01.png"  id="img4">
            <img src="/resources/images1/xiaoyuan02.png" id="img5">
            <div class="chengjaio">52,018.5156万元</div>
        </div>

    </div>
</div>
<div class="bghycont" id="freightPlanlist">

    <div class="hycontlist">
        <div class="divblue"></div>
        <div class="w1200">
            <div class="hyaddr">
                <div class="hyaddrl fl">
                    <img src="${trRoot}/tianrui/images/hybegin.png">
                    <span>河南省汝州市</span>
                </div>
                <div class="hyaddrr fr">
                    <img src="${trRoot}/tianrui/images/hyend.png">
                    <span>河南省汝州市</span>
                </div>
            </div>
            <div class="hyroad">
                <div class="hyroad_solo fl">
                    <div class="leftfont">
                    </div>
                    <div class="lefttip">
                        <p>河南省郑州市郑东新区商务外环与九如路交叉口20号海联大厦11楼1101室</p>
                    </div>
                </div>
                <div class="hyroadmid fl">
                    <div class="line1">
                        <div class="hyroad_add fl">
                            <img src="${trRoot}/tianrui/images/hypeople.png">
                            <span>承运人：中原大易物流有限公司</span>
                        </div>
                        <div class="fr">
                            <img src="${trRoot}/tianrui/images/hytime.png">
                            <span>2017-12-5 至 2017-12-30</span>
                        </div>
                    </div>
                    <div class="line2">
                        <img src="${trRoot}/tianrui/images/hyline.png">
                    </div>
                    <div class="line3">
                        <div class="huo fl">
                            <img src="${trRoot}/tianrui/images/hygoods.png">
                            <span>熟料500000吨</span>
                        </div>
                        <div class="price fl">
                            <label>60元/吨</label>
                        </div>
                        <div class="juli fr">
                            <img src="${trRoot}/tianrui/images/hygps.png">
                            <span>6000公里</span>
                        </div>
                    </div>
                </div>
                <div class="hyroad_solo fr">
                    <div class="rightfont">
                    </div>
                    <div class="righttip">
                        <p>河南省郑州市郑东新区商务外环与九如路交叉口20号海联大厦11楼1101室</p>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="hycontlist">
        <div class="divgreen"></div>
        <div class="w1200">
            <div class="hyaddr">
                <div class="hyaddrl fl">
                    <img src="${trRoot}/tianrui/images/hybegin.png">
                    <span>河南省汝州市</span>
                </div>
                <div class="hyaddrr fr">
                    <img src="${trRoot}/tianrui/images/hyend.png">
                    <span>河南省汝州市</span>
                </div>
            </div>
            <div class="hyroad">
                <div class="hyroad_solo fl">
                    <div class="leftfont">
                    </div>
                    <div class="lefttip">
                        <p>河南省郑州市郑东新区商务外环与九如路交叉口20号海联大厦11楼1101室</p>
                    </div>
                </div>
                <div class="hyroadmid fl">
                    <div class="line1">
                        <div class="hyroad_add fl">
                            <img src="${trRoot}/tianrui/images/hypeople.png">
                            <span>承运人：中原大易物流有限公司</span>
                        </div>
                        <div class="fr">
                            <img src="${trRoot}/tianrui/images/hytime.png">
                            <span>2017-12-5 至 2017-12-30</span>
                        </div>
                    </div>
                    <div class="line2">
                        <img src="${trRoot}/tianrui/images/hyline.png">
                    </div>
                    <div class="line3">
                        <div class="huo fl">
                            <img src="${trRoot}/tianrui/images/hygoods.png">
                            <span>熟料500000吨</span>
                        </div>
                        <div class="price fl">
                            <label>60元/吨</label>
                        </div>
                        <div class="juli fr">
                            <img src="${trRoot}/tianrui/images/hygps.png">
                            <span>6000公里</span>
                        </div>
                    </div>
                </div>
                <div class="hyroad_solo fr">
                    <div class="rightfont">
                    </div>
                    <div class="righttip">
                        <p>河南省郑州市郑东新区商务外环与九如路交叉口20号海联大厦11楼1101室</p>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<!--去接单按钮-->
<div class="hy_jiedan">
    <a href="/count/freightDemand">
        <h4>去接单</h4>
    </a>
</div>
<jsp:include page="head/foot.jsp"></jsp:include>
<script type="text/javascript">
var trRoot ="${trRoot}";
</script>
<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script type="text/javascript" src="/resources/js/count/freightPlan.js?12.3" ></script>
</body>
</html>
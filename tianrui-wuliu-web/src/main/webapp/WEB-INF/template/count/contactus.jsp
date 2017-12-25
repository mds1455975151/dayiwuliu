<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>联系我们</title>
    <meta name="keywords" content="大易物流"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/dystyle.css" rel="stylesheet">
    

</head>
<body>
<!-- head -->
<div class="bghui">
    <div class="wrap">
        <!--登录头部行begin-->
        <div class="header_tj">
            <div class="header_left">
                <label class="mr10">大易物流平台</label>
                <a href="/count/route"><label> 请[登录]</label></a>
                <a href="/publicMember/registerPage" class="colorreg"><label> [免费注册]</label></a>
            </div>
            <div class="header_right">
                <ul class="about_head">
                    <li class="select"><a href="/count/contactUs">联系我们</a></li>
                    <li><a href="/count/aboutUs">关于我们</a></li>
                </ul>
            </div>
        </div>
        <!--登录头部行end-->
    </div>
    <div class="clear"></div>
</div>
<!-- head -->

<div class="bgwhite">
    <div class="wrap">
        <div class="contact">
            <h3>联系我们</h3>
            <p>中原大易科技有限公司</p>
            <p>地址：地址：河南省汝州市广成东路天瑞大厦</p>
            <p>邮编：467599</p>
            <p>电话：0375-6890116</p>
            <img src="${trRoot}/tianrui/images/contact.jpg">
        </div>
    </div>
</div>
<jsp:include page="head/foot.jsp"></jsp:include>

<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
</body>
</html>
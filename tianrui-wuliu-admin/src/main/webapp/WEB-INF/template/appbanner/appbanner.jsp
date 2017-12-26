<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>银行卡管理</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
     <link href="${stylesRoot }/appbanner1.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css"  rel="stylesheet">
    <link href="${stylesRoot }/imgcut.css" rel="stylesheet">
    <link href="${trRoot}/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
	
</head>
<body>

<div class="container-fluid">
	<input type="hidden" id="recPageNo" value="${pageNo }">
    <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
    <div class="appBannerBox">
    <div class="nav"></div>
    <div class="bottomBox">
        <div class="left">
            <div class="leftNav">
                <img style="vertical-align:middle;" src="${trRoot}/tianrui/images/phoneTel.png">
                <span>Appbanner管理</span>
            </div>
        </div>
        <div class="right">
            <div class="top">
                <span>Appbanner管理</span>
            </div>
            <div class="bottom">
                <div style="margin:30px 0 20px 10px;">
                    <span>新增</span>
                    <span>发布</span>
                </div>
                <div class="main">
                    <div class="infoBox">
                        <div class="border">
                            <div class="info">
                                <img src="${trRoot}/tianrui/images/bannerhy1.png">
                                <p>名称：货源中心数据总览</p>
                                <p>链接：http://172.19.74:8090/user/user?menuId=2</p>
                                <p>发布状态：<span class="status blue">已发布</span></p>
                            </div>
                        </div>
                        <div class="border">
                            <div class="info">
                                <img src="${trRoot}/tianrui/images/bannerhy4.jpg">
                                <p class="countBox1">
                                    名称：在途运单数据总览
                                <div class="count" id="ee">启用</div>
                                <div class="count2">删除</div>
                                </p>
                                <p>链接：http://172.19.74:8090/user/user?menuId=2</p>
                                <p>发布状态：<span class="status red">待发布</span></p>
                            </div>
                        </div>
                        <div class="border">
                            <div class="info">
                                <img src="${trRoot}/tianrui/images/bannerhy2.jpg">
                                <p class="countBox2">
                                    名称：在途运单数据总览
                                     <div class="count1">禁用</div>
                                </p>
                                <p>链接：http://172.19.74:8090/user/user?menuId=2</p>
                                <p>发布状态：<span class="status blue">已发布</span></p>
                            </div>
                        </div>
                        <div class="border">
                            <div class="info">
                                <img src="${trRoot}/tianrui/images/bannerhy3.jpg">
                                <p class="countBox3">
                                    	名称：在途运单数据总览
                                <div class="count">启用</div>
                                <div class="count2">删除</div>
                                </p>
                                <p>链接：http://172.19.74:8090/user/user?menuId=2</p>
                                <p>发布状态：<span class="status red">待发布</span></p>
                            </div>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="more">更多</div>
                    <div class="button">发布</div>
                </div>
            </div>
        </div>
    </div>
</div>
   
     
    
</div>
	

<%@include file="../common/footer.jsp" %>
<script type="text/javascript">
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="${trRoot}/js/fileinput.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
</body>
</html>
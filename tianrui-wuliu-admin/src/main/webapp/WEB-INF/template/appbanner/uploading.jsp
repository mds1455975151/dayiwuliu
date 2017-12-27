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
     <link href="${stylesRoot }/uploading.css" rel="stylesheet">
      <link href="${stylesRoot }/cy_cropper.css" rel="stylesheet">
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
 	<div class="uploadingBox">
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
                    <div style="margin:30px 0 20px 10px;">banner图新增</div>
                    <div class="main">
                        <div class="upload">
                            <div id="content">
                                <form id="NewQyq">
                                    <div class="SeeCont">
                                        <div class="SeeImg image_container">
                                            <img class="myimg" src='' />
                                        </div><br><br>
                                        <button class="TxText xzBtn" id="imgReplaceBtn" type="button">上传图片</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="info"><div class="left1">名称：</div><div class="input"></div></div><br>
                        <div class="info"><div class="left1">链接：</div><div class="input"></div></div>
                        <div class="clear"></div>
                        <div class="button">添加</div>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    
    
</div>
	

<%@include file="../common/footer.jsp" %>
<script type="text/javascript">
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/upImg.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
<script>
	upImg(1/1);
</script>
</body>
</html>
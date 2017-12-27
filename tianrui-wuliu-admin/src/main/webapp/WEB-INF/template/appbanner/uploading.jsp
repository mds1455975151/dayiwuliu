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
     <link href="/resources/css/uploading.css" rel="stylesheet">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css"  rel="stylesheet">
    <link href="${stylesRoot }/imgcut.css" rel="stylesheet">
    <link href="${trRoot}/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
	<style>
		.bottomBox{
			bottom:auto !important;		
		}
	</style>
</head>
<body>

<div class="container-fluid">
	<input type="hidden" id="recPageNo" value="${pageNo }">
    <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
                <!--后台右侧布局begin-->
             <div class="bottomBox1" style="width:80%;overflow:visible;bottom:auto;right:10px">
          
            <div class="right" style="width:100%">
                <div class="top">
                    <span>Appbanner管理</span>
                </div>
                <div class="bottom">
                    <div style="margin:30px 0 20px 10px;">banner图新增</div>
                    <div class="main">
                        <div class="upload">
                        	<img id="preview"  border="0" src="${trRoot}/images/upload.png"><br>
                        	<form enctype="multipart/form-data">
							<div class="btn upload"><a href="#" class="astyle"><input type="file" value="上传图片" name="image_file" id="image_file" class="upload_pic" onchange="fileSelected('preview','image_file');">上传图片</a> </div>
							</form>
                        </div>
                        <div class="info"><div class="left1">名称：</div><input class="input" id="nameUp"></div><br>
                        <div class="info"><div class="left1">链接：</div><div class="input" id="lianjie"></div></div>
                        <div class="clear"></div>
                        <div class="button" id="bottonUp">添加</div>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
            
            <!--后台右侧布局end-->
            </div>
            <!--后台整体布局end-->
    </div>
    <!--侧边栏end-->
</div>
<!--查看详情begin-->
<!--查看详情end-->
<!--审核begin-->
<!--审核详情end-->
<script type="text/javascript" src="/resources/js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<%@include file="../common/footer.jsp" %>
<script type="text/javascript">

</script>

<script type="text/javascript" src="${trRoot}/js/fileinput.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput_locale_zh.js"></script>
</body>
</html>
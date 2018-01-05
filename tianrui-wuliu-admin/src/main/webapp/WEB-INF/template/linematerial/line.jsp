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
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">
	<link href="/resources/css/material.css" rel="stylesheet">
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
.right .bottom .main{
    min-height:625px;
    width: 98%;
    margin: 0 auto;
    background-color: #f4f2f3;
}
.nameSelectBox{
	padding:20px;
}
#nameSelect{
			border:none;
            appearance:none;
            -moz-appearance:none;    
            -webkit-appearance:none; 
            width:130px;
            height:30px;  
             padding-right: 10px;
            overflow: hidden;
            background: url(/resources/css/arrow2.png) no-repeat right;
}
.right .bottom .queding{
    width:120px;
    height: 50px;
    line-height: 50px;
    text-align: center;
    border-radius: 10px;
    background-color: #2ca0fa;
    color: white;
    margin: 55px auto;
    cursor: pointer;
}
/* 效果CSS开始 */
.searchInput{border: 1px solid #ccc;width: 100px;height:25px;border-radius: 10px;margin: 10px;outline: none;}
.searchBtn{height:25px;width:50px;color: #fff;background-color: #2ca0fa;border: none;border-radius: 5px;}
.kePublic{height:415px; border-bottom: 1px solid #ccc;background-color: #f4f2f3}
.selectbox{width:100%;height:220px;margin:0px auto;}
.selectbox div{float:left;}
.selectbox .select-bar{border-right: 1px solid #ccc;}
.selectbox .select-bar1{border-left: 1px solid #ccc;}
.selectbox .select-bar #select1 option{}
.selectbox .select-bar #select1 option span .checkBox{}
.selectbox .select-bar select{width:400px;height:300px;padding:4px;font-size:14px;border: none;}
.btn-bar {margin:20px;margin-top: 140px;}
.btn-bar p{margin:16px;}
.selectbox .select-bar #select1{border-top: 1px solid #ccc;border-right: 1px solid #ccc;outline: none;background-color: #f4f2f3}
.selectbox .select-bar #select2{border-top: 1px solid #ccc;border-right: 1px solid #ccc;border-left:1px solid #ccc;outline: none;background-color: #f4f2f3}
/* 效果CSS结束 */
</style>
</head>
<body>

<div class="container-fluid">
	<input type="hidden" id="recPageNo" value="${pageNo }">
    <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
                <!--后台右侧布局begin-->
  					 <div class="bottomBox">
        <div class="right">
            <div class="top">
                <span>车机轨迹路线设置</span>
            </div>
            <div class="bottom">
                <div class="main">
                <div class="nameSelectBox">
                <span>物料名称：</span>
                <select  id="nameSelect" onchange="changeSelectName()" >
                </select>
                </div>
               
                    <div class="kePublic">
                        <!--效果html开始-->
                        <div class="selectbox">
                            <div class="select-bar">
                                <p style="margin-left:23px;padding:30px 0 10px 0;">待选路线</p>
                                <p style="margin-left:15px;"><input type="text" id="cargoName" class="searchInput"><input type="button" onclick="queryWaitMate()" class="searchBtn" value="搜索"></p>
                                <select multiple="multiple" id="select1">
                                </select>
                            </div>

                            <div class="btn-bar">
                                <p><span id="add"><img style="cursor: pointer" src="/resources/images/jiantou1%20(2).png" alt=""></span></p>
                                <p><span id="remove"><img style="cursor: pointer" src="/resources/images/jiantou1%20(1).png" alt=""></span></p>
                            </div>
                            <div class="select-bar select-bar1">
                                <p style="margin-left:23px;padding:30px 0 10px 0;">已选路线</p>
                                <p style="margin-left:15px;"><input type="text" id="cargoName1"  class="searchInput"><input onclick="querySelecedMaterial()"  type="button" class="searchBtn" value="搜索"></p>
                                <select multiple="multiple" id="select2"></select>
                            </div>
                        </div>
                        <!--效果html结束-->
                        <div class="clear"></div>
                    </div>
                    <div class="queding"  >确定</div>
                </div>
            </div>
        </div>
    </div>
  <!--后台右侧布局end-->
</div>

<script type="text/javascript" src="/resources/js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="/resources/js/line.js"></script>
<%@include file="../common/footer.jsp" %>
<script type="text/javascript" src="${trRoot}/js/fileinput.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput_locale_zh.js"></script>
</body>
</html>
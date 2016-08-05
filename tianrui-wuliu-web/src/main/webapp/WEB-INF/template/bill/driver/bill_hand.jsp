<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>运单中心</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/imgcut.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css"  rel="stylesheet">
	<style type="text/css">
	.sj_search{
	    padding: 15px 20px;
	    font-size: 13px;
	
	}
	.sj_search li{
	    width: 280px;
	    display: inline-block;
	}
	.sj_search li input{
	    width: 180px;
	    display: inline-block;
	}
	.sj_left, .sj_right{
	    padding: 15px;
	    width:350px;
	    float: left;
	 }
	.sj_left h4,.sj_right h4{
	    font-size: 16px;
	}
	.venderList, .driverList{
	    border: 1px solid #f4f4f4;
	    width:300px;
	    float: left;
	 }
	.venderList ul, .driverList ul{
	    padding:8px 0px;
	    height: 400px;
	    overflow-y: scroll;
	}
	.venderList ul li, .driverList ul li{
	   font-size: 13px;
	    line-height: 26px;
	    text-indent: 15px;
	    cursor: pointer;
	}
	.venderList ul li.active,.venderList ul li:focus,.venderList ul li:hover{
	    color: #ffffff;
	    background: #0093dd;
	}
	.driverList ul li.active,.driverList ul li:focus,.driverList ul li:hover{
	    color: #ffffff;
	    background: #fb841b;
	}
	.sj_total{
	    overflow: hidden;
	    width: 700px;
	}
	.sj_total input[type="button"]{
	    display: block;
	    width: 140px;
	    margin: 15px auto;
	}
	</style>
</head>
<body>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
<!--Header-->
<!--内容部分begin-->
<div class="bghui">
<div class="container">
    <!--网站位置-->
    <div class="row">
            <div class="rz_line">
                <label>当前位置：运单</label><span>></span> <label>司机交班</label>
            </div>
    </div>
    <div class="row">
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心左侧end-->
   <!--个人中心右侧begin-->
        <div class="rz_right detailDiv">
            <div class=" bgblue">
                <h2>司机交班</h2>
            </div>
            <!-- 司机换班内容begin -->
  			<c:choose>
	            <c:when test="${not empty list }">
		            <!-- <div class="sj_search">
		                <ul>
		                    <li>
		                        <label>司机姓名：</label>
		                        <input type="text" placeholder="请输入司机名">
		                    </li>
		
		                    <li>
		                        <button type="submit" class="btn btnyello">搜索</button>
		                    </li>
		                </ul>
		            </div> -->
		            <div class="sj_left">
		            <h4>请选择所属车主</h4>
	            	<div class="venderList">
		            	<ul>
		            		<c:forEach items="${list }" var="m">
		            			<li item="${m.memberid }" class="">${empty m.username ? m.companyname : m.username }</li>
		            		</c:forEach>
		            	</ul>
		            </div>
		            </div>
            		<div class="sj_right">
            		<h4>请选择交接司机</h4>
		            <div class="driverList"></div>
		            </div>
		            <c:if test="${not empty list }">
			            <div class="sj_total">
			        		<input type="button" item="${isHand }" class="handBtn btn btnblue" value="申请交班"/>
			            </div>
		        	</c:if>
	            </c:when>
	            <c:otherwise>
		            <div class="style_404">
			            <div class="find_none">
			            	<i class="iconfont icon-shibai"></i><h4>您暂无与车辆绑定！</h4>
			            </div>
		            </div>
	            </c:otherwise>
            </c:choose>
            <!-- 司机换班内容end -->
        </div>
        <!--个人中心右侧end-->
        </div>
</div>
</div>
<!--内容部分end-->

<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<!--底部end-->
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="/resources/js/bill/bill_hand.js" ></script>
	
</body>
</html>
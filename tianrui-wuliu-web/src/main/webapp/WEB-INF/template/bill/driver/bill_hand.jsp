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
	.venderList {
		width: 200px;
		height: 400px;
		float: left;
	}
	.driverList{
		width:300px;
		height: 400px;
		float: left;
	}
	li.active {
	    background: #a2e0ff;
	}
	.venderList li,
	.driverList li {
	    height: 25px;
	    line-height: 25px;
	    padding-left: 10px;
	    cursor: pointer;
	}
	.venderList li:hover {
	    background: #a2e0ff;
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
            <div class="goods_box">
           		<c:choose>
            		<c:when test="${empty isAccept }">
            			<c:choose>
				            <c:when test="${not empty list }">
				            	<div class="venderList">
					            	<ul>
					            		<c:forEach items="${list }" var="m">
					            			<li item="${m.memberid }" class="">${empty m.username ? m.companyname : m.username }</li>
					            		</c:forEach>
					            	</ul>
					            </div>
					            <div class="driverList"></div>
					            <c:if test="${not empty list }">
					        		<input type="button" item="${isHand }" class="handBtn" value="申请交班"/>
					        	</c:if>
				            </c:when>
				            <c:otherwise>
				            	<span>您暂无与车辆绑定</span>
				            </c:otherwise>
			            </c:choose>
					</c:when>
            		<c:otherwise>
            			<ul class="isAcceptUl" startid="${isAccept[0].startid }" starter="${isAccept[0].starter }" starttele="${isAccept[0].starttele }">
			            	<c:forEach items="${isAccept }" var="s">
			            		<li><span>${s.starter }</span><span>${s.starttele }</span><span>--${s.starttime }--</span><span>订单(${s.billid })</span><span>申请与您交班。</span></li>
			            	</c:forEach>
		            	</ul>
		            	<c:if test="${not empty isAccept }">
			            	<input type="button" class="agreeBtn" value="同意"/>
			            	<input type="button" class="RefuseBtn" value="拒绝"/>
		            	</c:if>
            		</c:otherwise>
            	</c:choose>
       		</div>
            
            
            
            	<%-- <c:choose>
            		<c:when test="${empty isAccept }">
            			<ul class="handDriverUl">
			            	<c:forEach items="${list }" var="d">
			            		<li><span><input type="radio" name="hand" value="${d.driverid }"/></span><span>${d.drivername }</span><span>${d.drivertel }</span></li>
			            	</c:forEach>
		            	</ul>
		            	<input type="button" item="${isHand }" class="handBtn" />
            		</c:when>
            		<c:otherwise>
            			<ul class="isAcceptUl" startid="${isAccept[0].startid }" starter="${isAccept[0].starter }" starttele="${isAccept[0].starttele }">
			            	<c:forEach items="${isAccept }" var="s">
			            		<li><span>${s.starter }</span><span>${s.starttele }</span><span>--${s.starttime }--</span><span>订单(${s.billid })</span><span>申请与您交班。</span></li>
			            	</c:forEach>
		            	</ul>
		            	<input type="button" class="agreeBtn" value="同意"/>
		            	<input type="button" class="RefuseBtn" value="拒绝"/>
            		</c:otherwise>
            	</c:choose> --%>
           <!--  </div> -->
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
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大易物流平台-个人详情</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="${trRoot}/tianrui/css/imgcut.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/fileinput.css" media="all"
	rel="stylesheet" type="text/css" />
<!-- 引用公共header部分 -->
<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
<!--内容部分begin-->
<div class="bghui">
	<div class="container">
		<!--网站位置-->
		<div class="row">
			<div class="rz_line">
				<label>首页</label><span>></span> <label>账号</label><span>></span><label>个人中心</label>
			</div>
		</div>
		<div class="row">
			<!--个人中心左侧begin-->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
			<!--个人中心左侧end-->
			<!--个人中心右侧begin-->
			<div class="rz_right">
				<div class=" bgblue">
					<h2>个人详情</h2>
				</div>
				<div class="mycar_dt">
					<div class="mycar_dtsolo">
						<label>会员账号:${member.cellphone }</label> 
			   		</div>
					<div class="mycar_dtsolo">
						<label>姓名:${member.username }</label>
					</div>
					<div class="mycar_dtsolo">
						<label>性别:<c:if test="${member.sex eq 'xy'}">
								男
							</c:if>
							<c:if test="${member.sex eq 'xx'}">
								女
							</c:if></label>
					</div>
					<div class="mycar_dtsolo">
						<label>证件号码:${member.idcard }</label> 
					</div>
					<div class="mycar_dtsolo">
						<label>联系电话:${member.telphone }</label> 
					</div>
					<div class="mycar_dtsolo">
						<label>出生日期:${member.birthday }</label>
					</div>
					<div class="clear"></div>
					
					<div class="mycar_div">
						<p>身份证</p>
	                    <img style="width: 420px" src="${member.idcardimage }">
					</div>
					<div class="mycar_div">
						<p>道路运输经营许可证</p>
						 <c:if test="${member.rtblimgurl eq null}">
	                   	<span style="color: red">未上传</span>
	                   </c:if>
	                    <img style="width: 420px" src="${member.rtblimgurl }">
					</div>
				</div>
				<!--个人中心右侧end-->
			</div>
		</div>
	</div>
	<!--内容部分end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="/resources/js/common/member/header_busi.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>

	</body>
</html>

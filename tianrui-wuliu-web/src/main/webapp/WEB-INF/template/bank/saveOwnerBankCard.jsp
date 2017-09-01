<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>添加银行卡</title>
	    <meta name="keywords" content=" 天瑞"/>
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link href="${trRoot}/tianrui/css/imgcut.css" rel="stylesheet">
	    <link href="${trRoot}/tianrui/css/select2.css" rel="stylesheet">
		<link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="${trRoot}/tianrui/js/My97DatePicker/WdatePicker.js"></script>
	    
    
	<!-- 引用公共header部分 -->
	<jsp:include page="../common/member/header_busi.jsp"></jsp:include>
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
			        <jsp:include page="../common/member/left_busi.jsp"></jsp:include>
			        <!--个人中心左侧end-->
		            <!--个人中心右侧begin-->
		            <div class="rz_right">
		             	<div class=" bgblue">
							<h2>添加车主银行卡</h2>
						</div>
						<!-- 个人车辆begin -->
						<form id="member_bank">
						<div class="car_box">
						<c:forEach items="${list}" var="user" varStatus="vs">
							 <tr class="mescard border_gray">  
					             <td>  
					                <s:property value="#vs.index+1"/>  
					             </td>  
					             <td align="right" width="70px;">车主：</td> 
					             <td align="left" width="80px;">${user.idname}</td>  
					             <td align="left" width="180px;">${user.telphone}</td>  
					             <td  align="right" width="100px;">银行卡号</td> 
					             <td width="280px;"><span class="cardnum" >${user.bankcard}</span></td>  
								 <td align = "center"><button type="button" class="btn btnyello" id="member_bank_add" onclick="add('${user.creater}')">添加</button></td> 
					         </tr><br/>  
						 </c:forEach> 
						 </div> 
						</form>
					</div>
		            <!--个人中心右侧end-->
		        </div>
		    </div>
		</div>
		<!--内容部分end-->
			<!-- 引用公共footer部分 -->
		<jsp:include page="../common/member/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
		<script type="text/javascript"> var trRoot = "${trRoot}";</script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/select2.js"></script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>
		<script type="text/javascript" src="/resources/js/bank/saveOwnerBankCard.js?0352"></script>
		
		
	</body>
</html>

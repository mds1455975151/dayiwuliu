<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的报表</title>
<meta name="keywords" content="天瑞" />
<meta name="description" content="">
<meta name="author" content="">

<link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/tr-media.css" rel="stylesheet">
<script language="javascript" type="text/javascript" src="${trRoot }/tianrui/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
	<div class="bghui">
		<div class="container">
			<div class="row">
				<div class="rz_line">
					<label>当前位置：我的报表</label><span>></span><label>报表详情</label>
				</div>
			</div>
			<div class="row">
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
				<div class="rz_right">
	                <div class=" bgblue">
	                    <h2>报表详情(货主)</h2>
	                </div>
	                <!-- 报表详情内容begin -->
	                <div class="bb_dtail">
	                    <div class="bb_dtable">
	                        <table class="table ">
	                            <thead>
	                            <tr>
	                                <th>计划开始日期</th>
	                                <th>计划结束日期</th>
	                                <th>计划单号</th>
	                                <th>计划量</th>
	                            </tr>
	                            </thead>
	                            <tbody>
	                            <tr>
	                                <td>${v_bill.planstarttimeStr }</td>
	                                <td>${v_bill.planendtimeStr }</td>
	                                <td>${v_bill.planCode }</td>
	                                <td>
	                                	<c:if test="${not empty v_bill.totalplanned }">
	                                		${v_bill.totalplanned }吨
	                                	</c:if>
	                                </td>
	                            </tr>
	                            </tbody>
	                            <thead>
	                            <tr>
	                                <th>车主</th>
	                                <th>车牌号</th>
	                                <th>运单号</th>
	                                <th>货物名称</th>
	                            </tr>
	                            </thead>
	                            <tbody>
	                            <tr>
	                                <td>${v_bill.vendername }</td>
	                                <td>${v_bill.vehicleno }</td>
	                                <td>${v_bill.waybillno }</td>
	                                <td>${v_bill.cargoname }</td>
	                            </tr>
	                            </tbody>
	                            <thead>
	                            <tr>
	                                <th>预提量</th>
	                                <th>签收量</th>
	                                <th>含税单价</th>
	                                <th>含税金额</th>
	                            </tr>
	                            </thead>
	                            <tbody>
	                            <tr>
	                                <td>
	                                	<c:if test="${not empty v_bill.weight }">
	                                		${v_bill.weight }吨
	                                	</c:if>
	                                </td>
	                                <td>
	                                	<c:if test="${not empty v_bill.trueweight }">
		                                	${v_bill.trueweight }吨
		                                </c:if>
	                                </td>
	                                <td>
	                                	<c:if test="${not empty v_bill.price }">
	                                		<fmt:formatNumber type="number" value="${v_bill.price }" maxFractionDigits="2"/>元
		                                </c:if>
	                                </td>
	                                <td>
	                                	<c:if test="${not empty v_bill.trueweight && not empty v_bill.price }">
	                                		<fmt:formatNumber type="number" value="${v_bill.trueweight*v_bill.price }" maxFractionDigits="2"/>元
		                                </c:if>
	                                </td>
	                            </tr>
	                            </tbody>
	                            <thead>
	                            <tr>
	                                <th>司机姓名</th>
	                                <th>运单状态</th>
	                                <th>路线</th>
	                            </tr>
	                            </thead>
	                            <tbody>
	                            <tr>
	                                <td>${v_bill.drivername }</td>
	                                <td>
	                                	<c:if test="${v_bill.billStatus eq '1' }">待提货</c:if>
	                                	<c:if test="${v_bill.billStatus eq '2' }">运输中</c:if>
	                                	<c:if test="${v_bill.billStatus eq '5' }">待签收</c:if>
	                                	<c:if test="${v_bill.billStatus eq '6' }">
	                                		<c:choose>
	                                			<c:when test="${v_bill.payStatus eq '2' }">待付款</c:when>
	                                			<c:when test="${v_bill.payStatus eq '3' }">支付完成</c:when>
												<c:otherwise>待运费确认</c:otherwise>
	                                		</c:choose>
	                                	</c:if>
	                                </td>
	                                <td>${v_bill.routename }</td>
	                            </tr>
	                            </tbody>
	                        </table>
	                    </div>
	                    <button class="btn btnyello mr20 backBtn" type="submit">返回</button>
	                </div>
	                <!-- 报表详情内容end -->
	            </div>
			</div>
		</div>
	</div>
	<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resources/js/common/member/header_busi.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#ownerreport').addClass("selected");
			$('.backBtn').off('click').on('click',function(){
				window.location.href='/trwuliu/ownerreport/main';
			});
		});
	</script>
</body>
</html>
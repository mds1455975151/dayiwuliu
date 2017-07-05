<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账单详情</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">

<link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/imgcut.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/tr-media.css" rel="stylesheet">
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
					<label>当前位置：账单</label><span>></span> <label>账单详情</label>
				</div>
			</div>
			<div class="row">
				<!-- 引用公共left部分 -->
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
				<!--个人中心左侧end-->
				<!--个人中心右侧begin-->
				<div class="rz_right detaildiv">
					<div class=" bgblue">
						<h2>账单详情</h2>
					</div>
					<!-- 货源计划内容begin -->
					<div class="goods_box">
						<div class="goods_line">
							<div class="plan_table">
								<table class="table ">
									<thead>
										<tr>
											<th>账单号</th>
											<th>发票类型</th>
											<th>支付状态</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${pay.code }</td>
											<td>${pay.invoiceName}</td>
											<td>${pay.payInvoiceStatus }</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>应付金额</th>
											<th>已付金额</th>
											<th>待付金额</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${pay.amountPayable }元</td>
											<td>${pay.paidAmount }元</td>
											<td>${pay.amountPayable - pay.paidAmount }元</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>收款人名称</th>
											<th>收款人账号</th>
											<th>银行卡号</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${pay.payeeName }</td>
											<td>${pay.payeeAccount }</td>
											<td>${pay.payeeBankCardNumber }</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>总额</th>
											<th>扣重扣杂</th>
											<th>扣款</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<c:if test="${pay.backstageBillTotalPrice eq 0 }">
												<td>${pay.receptionBillTotalPrice }元</td>
												<td>${pay.receptionDeductWeightMisc }元</td>
												<td>${pay.receptionDeductOther }元</td>
											</c:if>
											<c:if test="${pay.backstageBillTotalPrice ne 0 }">
												<td>${pay.backstageBillTotalPrice }元</td>
												<td>${pay.backstageDeductWeightMisc }元</td>
												<td>${pay.backstageDeductMoney }元</td>
											</c:if>
											
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>油卡</th>
											<th>其它扣款</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<c:if test="${pay.backstageBillTotalPrice eq 0 }">
												<td>${pay.receptionDeductOilCard }元</td>
												<td>${pay.receptionDeductOther }元</td>
											</c:if>
											<c:if test="${pay.backstageBillTotalPrice ne 0 }">
												<td>${pay.backstageDeductOilCard }元</td>
												<td>${pay.backstageDeductOther }元</td>
											</c:if>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- 货源计划内容end -->
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
	<script type="text/javascript"
		src="/resources/js/common/member/header_busi.js"></script>
	<script type="text/javascript">
		var trRoot = "${trRoot}";
		//弹出框样式更改
	    var headcss = $("#bdView .modal-header");
	    headcss.css({
	        "border":"none",
	    });
	    headcss.find("button").css({
	        "color":"#ffffff"
	    });
	    headcss.find(".close").css({
	        "opacity":"0.8"
	    });
	    var dialogcss = $("#bdView .modal-content");
	    dialogcss.css({
	        "background":"#333333"
	    });
	    var footcss = $("#bdView .modal-footer");
	    footcss.css({
	        "border":"none",
	    });
	</script>
</body>
</html>
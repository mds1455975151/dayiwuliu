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
<title>运单中心</title>
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
					<label>当前位置：运单</label><span>></span> <label>开票运单</label>
				</div>
			</div>
			<div class="row">
				<!-- 引用公共left部分 -->
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
				<!--个人中心左侧end-->
				<!--个人中心右侧begin-->
				<div class="rz_right detaildiv">
					<div class=" bgblue">
						<h2>开票运单</h2>
					</div>
					<!-- 货源计划内容begin -->
					<div class="goods_box">
						<div class="goods_line">
							<div class="plan_table">
								<table class="table ">
									<thead>
										<tr>
											<th>运单号</th>
											<th>货物名称</th>
											<th>计价单位</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.billno}</td>
											<td>${bill.hpmc}</td>
											<td>${bill.dw}</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>起运地</th>
											<th>目的地</th>
											<th>结算里程数</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.qycs }</td>
											<td>${bill.mdcs }</td>
											<td>${bill.lc}</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>收货人</th>
											<th>联系手机</th>
											<th>运输量</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.shr }</td>
											<td>${bill.lxsj }</td>
											<td>${bill.sl }</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>要求提货日期</th>
											<th>要求到货日期</th>
											<th>总运费</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.yqthrq }</td>
											<td>${bill.yqdhrq }</td>
											<td>${bill.yf}元</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>车辆</th>
											<th>司机(安联)</th>
											<th>联系方式</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.cph }</td>
											<td>${bill.sj }</td>
											<td>${bill.drivertel }</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>承运方</th>
											<th>运单状态</th>
											<th>支付对象</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.systemShipper}</td>
											<td>${bill.status }</td>
											<td>
											<c:if test="${bill.payment eq '1' }">司机</c:if>
											<c:if test="${bill.payment eq '2' }">车主</c:if>
											</td>
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
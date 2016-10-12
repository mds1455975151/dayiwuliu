<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script language="javascript" type="text/javascript"
	src="${trRoot }/tianrui/My97DatePicker/WdatePicker.js"></script>
<!--这个日历控件js必须放头部-->
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
					<label>首页</label><span>></span> <label>账号</label><span>></span><label>个人中心</label>
				</div>
			</div>
			<div class="row">
				<!-- 引用公共left部分 -->
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
				<!--个人中心右侧begin-->
				<div class="car_right">
					<div class="report_scroll">
						<div class="report_title">
							<h2>我的报表</h2>
							<div class="report_oprate">
								<ul>
									<li id="exportReport" class="active"><i class="icononline">&#xe611;</i>
										<p>导出</p></li>
									<li id=""><i class="icononline">&#xe612;</i>
										<p>打印</p></li>
									<li id="backSearch"><i class="icononline">&#xe613;</i>
										<p>返回</p></li>
								</ul>
							</div>
						</div>
						<div class="report_tbox">
							<div class="report_tcont">
								<!--明细表begin-->
								<div id="reportContains" class="report_table">
									
								</div>
								<!--明细表end-->
							</div>
						</div>
					</div>
				</div>
				<!--个人中心右侧end-->
			</div>
		</div>
	</div>
	<!--内容部分end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="${trRoot}/tianrui/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="/resources/js/common/member/header_busi.js"></script>
	<script type="text/javascript"
		src="/resources/ui/report/jquery-report.js"></script>
	<script type="text/javascript"
		src="/resources/ui/exportExcel/jquery.base64.js"></script>
	<script type="text/javascript"
		src="/resources/ui/exportExcel/tableExport.js"></script>
	<script type="text/javascript"
		src="/resources/js/report/owner/report.js"></script>
	<script type="text/javascript">
		var data = '${list}';
		if(data){
			data = JSON.parse(data);
		}
		var item = '${item}';
		var groups = '${groups}';
		if(groups){
			groups = JSON.parse(groups);
		}
		console.info(groups);
		var statistical = '${statistical}';
		if(statistical){
			statistical = JSON.parse(statistical);
		}
		var summation = '${summation}';
		if(summation){
			summation = JSON.parse(summation);
		}
		var subtotal = '${subtotal}';
		if(subtotal){
			subtotal = JSON.parse(subtotal);
		}
		$(function(){
			PAGE.main.init();
		});
	</script>
</body>
</html>
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
									<li class="active"><i class="icononline">&#xe611;</i>
										<p>导出</p></li>
									<li><i class="icononline">&#xe612;</i>
										<p>打印</p></li>
									<li><i class="icononline">&#xe613;</i>
										<p>返回</p></li>
								</ul>
							</div>
						</div>
						<div class="report_tbox">
							<div class="report_tcont">
								<!--明细表begin-->
								<div class="report_table">
									<table class="table table-bordered tablenone">
										<thead>
											<tr>
												<th>时间</th>
												<th>车牌号</th>
												<th>物料</th>
												<th>起运地</th>
												<th>目的地</th>
												<th>单价</th>
												<th>承运商</th>
												<th>车数</th>
												<th>结算数量</th>
												<th>运单编号</th>
												<th>磅单号</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td rowspan="6">2016-03-12 08:12</td>
												<td rowspan="2">豫A56551</td>
												<td>熟料</td>
												<td>河南省郑州市河南省郑州市</td>
												<td>河南省郑州市</td>
												<td>1550.00</td>
												<td>河南省郑州市</td>
												<td>2255</td>
												<td>154200</td>
												<td>415644555145</td>
												<td>456456456</td>
											</tr>
											<tr>
												<td>熟料熟料</td>
												<td>河南省郑州市</td>
												<td>河南省郑州市</td>
												<td>1550.00</td>
												<td>河南省郑州市</td>
												<td>22</td>
												<td>154200</td>
												<td>4156455145</td>
												<td>456456456</td>
											</tr>
											<tr class="withbg">
												<td>小计：</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td>22</td>
												<td>154200</td>
												<td></td>
												<td></td>
											</tr>
											<tr>
												<td rowspan="2">豫A56551</td>
												<td>熟料</td>
												<td>河南省郑州市河南省郑州市</td>
												<td>河南省郑州市</td>
												<td>1550.00</td>
												<td>河南省郑州市</td>
												<td>2255</td>
												<td>154200</td>
												<td>415644555145</td>
												<td>456456456</td>
											</tr>
											<tr>
												<td>熟料熟料</td>
												<td>河南省郑州市</td>
												<td>河南省郑州市</td>
												<td>1550.00</td>
												<td>河南省郑州市</td>
												<td>22</td>
												<td>154200</td>
												<td>4156455145</td>
												<td>456456456</td>
											</tr>
											<tr class="withbg">
												<td>小计：</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td>22</td>
												<td>154200</td>
												<td></td>
												<td></td>
											</tr>
											<tr class="withbg">
												<td>合计：</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td>22</td>
												<td>154200</td>
												<td></td>
												<td></td>
											</tr>
										</tbody>
									</table>
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
		src="/resources/js/report/owner/report.js"></script>
	<script type="text/javascript">
		var data = JSON.parse('${list}');
		var groups = JSON.parse('${groups}');
		var statistical = JSON.parse('${statistical}');
		var summation = JSON.parse('${summation}');
		var subtotal = JSON.parse('${subtotal}');
	</script>
</body>
</html>
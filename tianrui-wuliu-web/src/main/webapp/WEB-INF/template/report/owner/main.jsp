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
<title>index</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">

<link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/imgcut.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/tr-media.css" rel="stylesheet">
<script language="javascript" type="text/javascript"
	src="${trRoot }/tianrui/My97DatePicker/WdatePicker.js"></script>

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
					<label>当前位置：运单</label><span>></span> <label>我的报表</label>
				</div>
			</div>
			<div class="row">
				<!-- 引用公共left部分 -->
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
				<!--个人中心左侧end-->
				<!--个人中心右侧begin-->
				<div class="car_right">
					<div class="car_title bgblue">
						<h2>我的报表</h2>
						<!--切换begin-->
						<ul class="report_menu">
							<li class="dayReport select">日报表</li>
							<li class="monthReport">月报表</li>
						</ul>
						<!--切换begin-->
					</div>
					<div class="report_tbox">

						<!--日报表begin-->
						<div class="report_tcont day_report_tcont">
							<div class="report_total">
								<!--日报表过滤条件begin-->
								<div class="report_left">
									<div class="report_ltit">
										<h3>过滤条件</h3>
									</div>
									<div class="report_lsearch">
										<h5>开始时间：</h5>
										<input type="text" id="starttime"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
											class="Wdate_report" />
										<h5>结束时间：</h5>
										<input type="text" id="endtime"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
											class="Wdate_report" />
										<h5>承认商：</h5>
										<input type="text" />
										<h5>物料：</h5>
										<input type="text" />
										<h5>起运地：</h5>
										<input type="text" />
										<h5>目的地：</h5>
										<input type="text" />
										<button type="submit" class="btn btnblue fr">重置</button>
									</div>
								</div>
								<!--日报表过滤条件end-->

								<!--日报表报表规则begin-->
								<div class="report_right">
									<div class="report_ltit">
										<h3>报表规则</h3>
									</div>
									<div class="report_rtop">
										<div class="report_wait ">
											<h5>待选字段列表</h5>
											<ul class="report_rtopul">
												<li>时间</li>
												<li>承运商</li>
												<li>物料</li>
												<li>车牌号</li>
												<li>起运地</li>
												<li>目的地</li>
											</ul>
										</div>
										<div class="report_switch fl">
											<div class="report_sw1">
												<img src="${imagesRoot}/tgup.png">
											</div>
											<div class="report_sw2">
												<img src="${imagesRoot}/tgdown.png">
											</div>
										</div>
										<div class="report_seled fl">
											<h5>分组列表</h5>
											<table class="table table-bordered table-hover"
												id="report_ttop">
												<thead>
													<tr>
														<th>分组字段名</th>
														<th>是否小计</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
										<div class="report_updown fl">
											<div class="imgup fl">
												<img src="${imagesRoot}/jup.png">
											</div>
											<div class="imgdown fl">
												<img src="${imagesRoot}/jdown.png">
											</div>
										</div>
									</div>
									<div class="clear"></div>
									<div class="report_rbot">
										<div class="report_wait">
											<ul class="report_rdownul">
												<li>单价</li>
												<li>车数</li>
												<li>结算数量</li>
											</ul>
										</div>
										<div class="report_switch fl">
											<div class="report_sw3">
												<img src="${imagesRoot}/tgup.png">
											</div>
											<div class="report_sw4">
												<img src="${imagesRoot}/tgdown.png">
											</div>
										</div>
										<div class="report_botmag">
											<div class="report_seled">
												<h5>统计列表</h5>
												<table class="table table-bordered tablenone"
													id="report_tbot">
													<thead>
														<tr>
															<th>字段名</th>
															<th>类型</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>


										<div class="report_show">
											<input type="checkbox"><label class="ml5">显示合计</label>
										</div>
									</div>
								</div>
								<!--日报表报表规则end-->
								<div class="clear"></div>
								<button type="submit" class="btn btnblue fr" id="report">生成报表</button>
							</div>
						</div>
						<!--日报表end-->

						<!--月报表begin-->
						<div class="report_tcont month_report_tcont hide">
							<div class="report_total">
								<!--日报表过滤条件begin-->
								<div class="report_left">
									<div class="report_ltit">
										<h3>过滤条件</h3>
									</div>
									<div class="report_lsearch">
										<h5>开始时间：</h5>
										<input type="text"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
											class="Wdate_report" />
										<h5>结束时间：</h5>
										<input type="text"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
											class="Wdate_report" />
										<h5>承认商：</h5>
										<input type="text" />
										<h5>物料：</h5>
										<input type="text" />
										<h5>起运地：</h5>
										<input type="text" />
										<h5>目的地：</h5>
										<input type="text" />
										<button type="submit" class="btn btnblue fr">重置</button>
									</div>
								</div>
								<!--日报表过滤条件end-->

								<!--日报表报表规则begin-->
								<div class="report_right">
									<div class="report_ltit">
										<h3>报表规则</h3>
									</div>
									<div class="report_rtop">
										<div class="report_wait ">
											<h5>待选字段列表</h5>
											<ul class="report_rtopul">
												<li>时间</li>
												<li>承运商</li>
												<li>物料</li>
												<li>车牌号</li>
												<li>起运地</li>
												<li>目的地</li>
											</ul>
										</div>
										<div class="report_switch fl">
											<div class="report_sw1">
												<img src="${imagesRoot}/tgup.png">
											</div>
											<div class="report_sw2">
												<img src="${imagesRoot}/tgdown.png">
											</div>
										</div>
										<div class="report_seled fl">
											<h5>分组列表</h5>
											<table class="table table-bordered table-hover"
												id="report_ttop">
												<thead>
													<tr>
														<th>分组字段名</th>
														<th>是否小计</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
										<div class="report_updown fl">
											<div class="imgup fl">
												<img src="${imagesRoot}/jup.png">
											</div>
											<div class="imgdown fl">
												<img src="${imagesRoot}/jdown.png">
											</div>
										</div>
									</div>
									<div class="clear"></div>
									<div class="report_rbot">
										<div class="report_wait">
											<ul class="report_rdownul">
												<li>单价</li>
												<li>车数</li>
												<li>结算数量</li>
											</ul>
										</div>
										<div class="report_switch fl">
											<div class="report_sw3">
												<img src="${imagesRoot}/tgup.png">
											</div>
											<div class="report_sw4">
												<img src="${imagesRoot}/tgdown.png">
											</div>
										</div>
										<div class="report_botmag">
											<div class="report_seled">
												<h5>统计列表</h5>
												<table class="table table-bordered tablenone"
													id="report_tbot">
													<thead>
														<tr>
															<th>字段名</th>
															<th>类型</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>


										<div class="report_show">
											<input type="checkbox"><label class="ml5">显示合计</label>
										</div>
									</div>
								</div>
								<!--日报表报表规则end-->
								<div class="clear"></div>
								<button type="submit" class="btn btnblue fr">生成报表</button>
							</div>
							<!--月报表end-->
						</div>
					</div>
					<!--个人中心右侧end-->
				</div>
			</div>
		</div>
		<!--内容部分end-->

		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
		<!--底部end-->
		<script type="text/javascript"
			src="${trRoot}/tianrui/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/wfljs.js"></script>
		<script type="text/javascript"
			src="/resources/js/common/member/header_busi.js"></script>
		<script type="text/javascript"
			src="/resources/js/report/owner/main.js"></script>
		<script>
			// 明细tab切换
			var $tab_li = $('.report_menu li');
			$tab_li.click(function() {
				$(this).addClass('select').siblings().removeClass('select');
				var index = $tab_li.index(this);
				$('.report_tbox > .report_tcont').eq(index).show().siblings()
						.hide();
			});
		</script>
</body>
</html>
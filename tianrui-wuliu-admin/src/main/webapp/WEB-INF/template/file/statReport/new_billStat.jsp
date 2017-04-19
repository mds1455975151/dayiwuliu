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
<link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
<link href="${stylesRoot }/base.css" rel="stylesheet">
<link href="${stylesRoot }/style.css" rel="stylesheet">
<link href="${stylesRoot }/tr-media.css" rel="stylesheet">
<link href="${stylesRoot }/easyTree.css" rel="stylesheet">
<link href="${stylesRoot }/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<link href="${stylesRoot}/jquery-ui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
<link rel="Shortcut Icon" href="${imagesRoot}/favicon.ico" type="image/x-icon">
<!--这个日历控件js必须放头部-->
<script language="javascript" type="text/javascript" src="${scriptsRoot}/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
		<!--后台右侧布局begin-->
		<input type="hidden" id="pageNo" value="${empty pageNo?1:pageNo }">
		<div class="col-md-10 ">
			<div class="ht_content">
				<div id="content-header">
					<h3>平台运单报表</h3>
				</div>
				<!--查询框begin-->
				<div class="row">
					<div class="col-md-12">
						<div class="contuser_search">
							<div class="ht_div">
								<label>车主姓名：</label> <input id="" type="text" placeholder="请输入车主姓名">
							</div>
							<div class="ht_div">
								<label>车牌号：</label> <input id="" type="text" placeholder="请输入车牌号">
							</div>
							<div class="ht_div">
								<label>物料：</label>
								<select>
									<option>111</option>
								</select>
							</div>
							<div class="ht_div">
								<label>发货组织：</label> 
								<select>
									<option>222</option>
								</select>	
							</div>
							<div class="ht_div">
								<label>运单状态：</label> 
								<select>
									<option>222</option>
								</select>	
							</div>
							<div class="ht_div">
								<label>业务日期：</label>
								<input id="starttime" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/>
							</div>
							<div class="ht_divbtn">
								<button class="btn btnblue search" type="submit">搜索</button>
								<button class="btn btngreen reset" type="submit">重置</button>
							</div>
						</div>
					</div>
				</div>
				<!--查询框end-->
				<div class="row mt15">
					<div class="col-md-12">
						<div class="content-user" style="width: 1544px;">
							<div class="pro_opra">
								<button class="btn btnblue exportReport">导出</button>
								<button class="btn btnorange printReport">打印</button>
								<div class="pro_opra_r">
									<label>总计：</label>
									<div class="pro_opra_rstl pro_opra_rstl1">
										<p>计划总量</p>
										<p id="totalSum"></p>
									</div>
									<div class="pro_opra_rstl pro_opra_rstl2">
										<p>实际执行总量</p>
										<p id="completedSum"></p>
									</div>
									<div class="pro_opra_rstl pro_opra_rstl3">
										<p>含税总金额</p>
										<p id="completePriceSum"></p>
									</div>
								</div>
							</div>
							<!--用户表格begin-->
							<table id="planReport" class="table table-bordered">
								<thead>
									<tr>
										<th>序号</th>
										<th>业务日期</th>
	                                    <th>计划单号</th>
	                                    <th>运单号</th>
	                                    <th>发货方</th>
	                                    <th>收货方</th>
	                                    <th>车主姓名</th>
	                                    <th>车牌号</th>
	                                    <th>司机姓名</th>
	                                    <th>货物名称</th>
	                                    <th>开始时间</th>
	                                    <th>结束时间</th>
	                                    <th>提货数量</th>
	                                    <th>运输路线</th>
	                                    <th>计划总量</th>
	                                    <th>卸货量</th>
	                                    <th>执行总量</th>
	                                    <th>运单状态</th>
									</tr>
								</thead>
								<tbody id="innerHml">
								</tbody>
							</table>
							<!--用户表格end-->
						</div>
						<!-- 分页部分  开始-->
				            <div class="row pr20 fr">
								<%@include file="../../common/pagination.jsp" %>
				            </div>
				         <!-- 分页部分 结束 -->
						</div>
					</div>
				</div>
			</div>
			<!--后台右侧布局end-->
		</div>
		<!--后台整体布局end-->
		<!--侧边栏end-->
	</div>


	<!--修改密码end-->
	<%@include file="../../common/footer.jsp"%>
	<script type="text/javascript">
		var CONTEXTPATH = "${contextPath}";
		var imagesRoot = "${imagesRoot}";
	</script>
	<script type="text/javascript" src="${scriptsRoot}/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${scriptsRoot}/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery.pagination.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript" src="/resources/js/statReport/new_billStat.js?0419"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.search').trigger('click');
			// 表格列宽度手动调整
			$("table").resizableColumns({});
		});
	</script>
</body>
</html>
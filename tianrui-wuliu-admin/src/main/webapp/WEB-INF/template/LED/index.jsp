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
<title>LED展示管理</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
<link href="${stylesRoot }/base.css" rel="stylesheet">
<link href="${stylesRoot }/style.css" rel="stylesheet">
<link href="${stylesRoot }/tr-media.css" rel="stylesheet">
<link href="${stylesRoot }/easyTree.css" rel="stylesheet">
<link href="${stylesRoot }/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<link href="${stylesRoot}/jquery-ui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${stylesRoot }/pagination/pagination.css" />
<link rel="Shortcut Icon" href="${imagesRoot}/favicon.ico"
	type="image/x-icon">
<!--这个日历控件js必须放头部-->
<script language="javascript" type="text/javascript"
	src="${scriptsRoot}/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="container-fluid">
		 <input type="hidden" id="recPageNo" value="${pageNo }">
		<jsp:include page="../common/header.jsp" flush="false"></jsp:include>
		<!--后台右侧布局begin-->
		<div class="col-md-10 ">
			<div class="ht_content">
				<div id="content-header">
					<h3>LED展示管理</h3>
				</div>
				<!--查询框begin-->
				<div class="row">
					<div class="col-md-12">
						<div class="contuser_search">
							<div class="ht_div">
								<label>类型：</label> 
								<select id="ledType">
									<option value="1">运量</option>
									<option value="2">货物类型</option>
									<option value="3">车辆类型</option>
									<option value="4">车辆归属地</option>
									<option value="5">车辆使用频率</option>
									<option value="6">运费统计</option>
									<option value="7">车主</option>
									<option value="8">货主</option>
									<option value="9">head统计</option>
									<option value="10">当天统计</option>
								</select>
							</div>
							<div class="ht_div">
								<label>类别：</label> 
								<select id="dataType">
									<option value="1">正式数据</option>
									<option value="2">测试数据</option>
								</select>
							</div>
							<div class="ht_divbtn">
								<button class="btn btnblue search" onclick="init(0)"
									type="submit">搜索</button>
								<button class="btn btngreen reset" onclick="reset()"
									type="submit">重置</button>
							</div>
						</div>
					</div>
				</div>
				<!--查询框end-->
				<div class="pro_opra">
					<button class="btn btnblue exportReport">
					LED当前展示数据:
					<c:if test="${data.stimestr eq '1'}">
						正式
					</c:if>
					<c:if test="${data.stimestr eq '2'}">
						测式
					</c:if></button>
					<input type="hidden" id="stimestr" value="${data.stimestr }">
					<button class="btn btnorange uptConfig stm1">正式</button>
					<button class="btn btnorange uptConfig stm2">测式</button>
				</div>
				<div class="row mt15">
					<div class="col-md-12">
						<div class="content-user"
							style="overflow-x: scroll">
							<!--用户表格begin-->
							<table id="planReport" style="white-space: nowrap"
								class="table table-bordered">
								<thead>
									<tr>
										<th>序号</th>
										<th>类别</th>
										<th>类型</th>
										<th>备注</th>
										<th>数值</th>
										<th>请求时间</th>
									</tr>
								</thead>
								<tbody id="innerHtml">
								</tbody>
							</table>
							<!--用户表格end-->
						</div>
						<!-- 分页部分  开始-->
						<div class="row pr20 fr">
							<%@include file="../common/pagination.jsp"%>
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
	<%@include file="../common/footer.jsp"%>
	<script type="text/javascript" src="${scriptsRoot}/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${scriptsRoot}/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery.pagination.js"></script>
	<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript" src="/resources/js/LED/index.js?1110"></script>
	
</body>
</html>
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
								<label>货物名称：</label> <input id="cargoname" type="text" placeholder="请输入货物名称">
							</div>
							<c:if test="${empty orgCode or orgCode eq '0000' }">
								<div class="ht_div">
									<label>组织名称：</label> <input id="orgid" type="text" placeholder="请选择组织名称">
								</div>
							</c:if>
							<div class="ht_div">
								<label>计划单号：</label> <input id="plancode" type="text" placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>日期：</label> <input id="starttime" type="text"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i> <input
									id="endtime" type="text"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
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
	                                    <th>计划单号</th>
	                                    <th>运单号</th>
	                                    <th>货主名称</th>
	                                    <th>车主名称</th>
	                                    <th>车牌号</th>
	                                    <th>司机姓名</th>
	                                    <th>运输货物</th>
	                                    <th>运单开始时间</th>
	                                    <th>运单结束时间</th>
	                                    <th>运输路线</th>
	                                    <th>预提数量</th>
	                                    <th>实际执行量</th>
	                                    <th>含税单价 </th>
	                                    <th>含税金额</th>
	                                    <th>运单执行情况</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							<!--用户表格end-->
						</div>
						<!--分页效果开始-->
						<div class="page_wrap">
							<div class="page_date">
								<label>数据共：</label><i id="total">0</i><label>条</label>
							</div>
							<div class="page_date">
								<label>跳到第：</label>
								<input id="jumpPageNo" type="text" value="1"> <label>页</label>
								<button id="jumpPageNoBtn" class="btn btn-default">确定</button>
							</div>
							<div class="page_select">
								<label>每页记录：</label>
								<select id="pageSize" class="form-control">
									<option value="10">10</option>
									<option value="20">20</option>
									<option value="30">30</option>
								</select>
							</div>
							<div class="page_btn" id="pagination"></div>
							<!--分页效果结束-->
						</div>
					</div>
				</div>
			</div>
			<!--后台右侧布局end-->
		</div>
		<!--后台整体布局end-->
		<!--侧边栏end-->
	</div>

	<!--个人账户begin-->
	<div class="modal fade" id="account" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">个人信息修改</h4>
				</div>
				<div class="modal-body">
					<div class="juesemodal">
						<p>
							<label>用户名：</label><span>adsgdgweg</span>
						</p>
						<p>
							<label>姓名：</label><input type="text" value="王大大">
						</p>
						<div class="modal_note">
							<h4>姓名不能为空，不超过20个字符</h4>
						</div>
						<p>
							<label>手机号：</label><input type="text" value="1583652415">
						</p>
						<div class="modal_note">
							<h4>请输入11位手机号</h4>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--个人账户end-->
	<!--修改密码begin-->
	<div class="modal fade" id="password" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">修改密码</h4>
				</div>
				<div class="modal-body">
					<div class="juesemodal">
						<p>
							<label>当前密码：</label><input type="password" value="王大大">
						</p>
						<p>
							<label>新密码：</label><input type="password">
						</p>
						<p>
							<label>确认密码：</label><input type="password">
						</p>
						<div class="modal_note">
							<h4>两次密码不一致</h4>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--退出begin-->
	<div class="modal fade" id="exit" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">提示</h4>
				</div>
				<div class="modal-body">
					<h4>您确定要退出此账户吗？</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--退出end-->

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
	<script type="text/javascript" src="/resources/js/statReport/billStat.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.search').trigger('click');
			// 表格列宽度手动调整
			$("table").resizableColumns({});
		});
	</script>
</body>
</html>
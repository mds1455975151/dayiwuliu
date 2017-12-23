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
<title>待付运费</title>
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
					<h3>异常管理</h3>
				</div>
				<!--查询框begin-->
				<div class="row">
                    <div class="col-md-12">
                        <div class="ht_div">
                            <label>司机姓名：</label> <input id="drivername" type="text" placeholder="请输入司机姓名">
                        </div>
                        <div class="ht_div">
                            <label>车牌号：</label> <input id="carnum" type="text" placeholder="请输入车牌号">
                        </div>
                        <div class="ht_div" >
                            <label>司机账号：</label> <input id="drivernum" type="text" placeholder="请输入司机账号">
                        </div>
                        <div class="ht_div" >
                            <label>联系电话：</label> <input id="drivertel" type="text" placeholder="请输入联系电话">
                        </div>
                        <div class="ht_div">
                           <label>异常类型：</label>
                           <select class="form-control" id="zhbusi">
                               <option value="">请选择</option>
                               <option value="-1">全部</option>
                               <option value="0">轨迹异常</option>
                           </select>
                       </div>
                       <div class="ht_div">
                           <label>处理状态：</label>
                           <select class="form-control" id="zhbusi">
                               <option value="">请选择</option>
                               <option value="-1">全部</option>
                               <option value="0">未修复</option>
                               <option value="0">已修复</option>
                           </select>
                       </div>
                        <div class="ht_div">
							<label>预警时间:</label>
						 <input id="starttime" type="text" 
						 onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\');}',dateFmt:'yyyy-MM-dd'})"
								class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i>
						<input id="endtime" type="text"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\');}',dateFmt:'yyyy-MM-dd'})"
								class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
						</div>
                        <div class="ht_divbtn">
                            <button class="btn btnblue search" onclick="init(0)" type="submit">搜索</button>
                            <button class="btn btngreen reset" onclick="reset()" type="submit">重置</button>
                        </div>
                    </div>
                </div>
				<!--查询框end-->
				<div class="row mt15">
					<div class="col-md-12">
					   <div class="tabelwidscroll">
						 <div class="content-user">
							<!--用户表格begin-->
							<table id="abertable" class="table table-bordered">
								<thead>
								  <tr>
                                    	<th>序号</th>
                                        <th>异常类型</th>
                                        <th>车牌号</th>
                                        <th>运单号</th>
                                        <th>司机姓名</th>
                                        <th>司机账号</th>
                                        <th>联系电话</th>
                                        <th>预警时间</th>
                                        <th>失联时间</th>
                                        <th>重联时间</th>
                                        <th>处理状态</th>
                                        <th>处理人</th>
                                        <th>修复时间</th>
                                        <th>推送消息</th>
                                        <th>短信通知</th>
                                        <th>电话通知</th>
                                        <th>操作</th>
                                  </tr>
								</thead>
								<tbody id="innerHtml">
								</tbody>
							</table>
							<!--用户表格end-->
						 </div>
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
	<script type="text/javascript" src="/resources/js/money/dfindex.js?1206"></script>
</body>
</html>
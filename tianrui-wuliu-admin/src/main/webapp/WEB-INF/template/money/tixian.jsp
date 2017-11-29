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
<title>提现管理</title>
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
				<div class="bag_tab">
                    <ul>
                        <li class="withdrawststus select" type="1">提现中</li>
                        <li class="withdrawststus " type="2">提现成功</li>
                        <li class="withdrawststus " type="3">提现失败</li>
                    </ul>
                </div>
                       <div class="bag_tabbox">
                        <!--tab切换的内容-->
                        <div class="bag_tabcont">
                                             <!--查询框begin-->
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="ht_div">
                                        <label>提现银行：</label> <input type="text" placeholder="请输入运单号">
                                    </div>
                                    <div class="ht_div">
                                        <label>登录手机号：</label> <input type="text" placeholder="请输入货物名称">
                                    </div>
                                    <div class="ht_div" name="mytable" style="">
                                        <label>身份证号：</label> <input type="text" placeholder="请输入车牌号">
                                    </div>
                                    <div class="ht_div" name="mytable" style="">
                                        <label>流水号：</label> <input type="text" placeholder="请输入车牌号">
                                    </div>
                                    <div class="ht_div" name="mytable" style="">
                                        <label>支付渠道：</label>
                                        <select class="form-control">
                                            <option value="">请选择</option>
                                            <option value="-1">全部</option>
                                            <option value="0">NC</option>
                                            <option value="1">其他</option>
                                        </select>
                                    </div>
                                    <div class="ht_div" name="mytable" style="">
                                        <label>提现申请时间：</label>
                                        <select class="form-control">
                                            <option value="">请选择</option>
                                            <option value="-1">今天</option>
                                            <option value="0">最近7天</option>
                                            <option value="1">最近30天</option>
                                            <option value="2">开始时间</option>
                                            <option value="3">结束时间</option>
                                        </select>
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
						<div class="content-user">
							<!--用户表格begin-->
							<table id="planReport" style="white-space: nowrap"
								class="table table-bordered">
								<thead>
								<tr>
                                                <th>序号</th>
                                                <th>姓名</th>
                                                <th>登录手机号</th>
                                                <th>身份证号</th>
                                                <th>提现申请时间</th>
                                                <th>流水号</th>
                                                <th>提现金额</th>
                                                <th>支付渠道</th>
                                                <th>提现银行名称</th>
                                                <th>提现银行卡号</th>
                                                <th>可用余额</th>
                                                <th class="tbshow type_2">到账时间</th>
                                                <th class="tbshow type_2">实际到账金额</th>
                                                <th class="tbshow type_3">失败时间</th>
                                                <th class="tbshow type_3">失败原因</th>
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
	<script type="text/javascript" src="/resources/js/money/tixian.js?1127"></script>
</body>
</html>
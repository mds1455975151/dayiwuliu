<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
<script language="javascript" type="text/javascript" src="${trRoot }/tianrui/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
	.goods_more {
	    cursor: pointer;
	}
	.goods_more:hover {
	    background: #e4e4e4;
	}
	.goods_more:active {
	    background: #d0d0d0;
	}
	.table {
	    margin: 0px!important;
	}
	.nodata {
		margin-bottom: 0px!important;
	}
</style>
</head>
<body>
	<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
	<div class="bghui">
		<div class="container">
			<div class="row">
				<div class="rz_line">
					<label>当前位置：运单</label><span>></span> <label>我的报表</label>
				</div>
			</div>
			<div class="row">
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
				<div class="car_right">
					<div class="car_title bgblue">
						<h2>我的报表</h2>
					</div>
					<div class="bb_search">
						<div class="bb_line">
							<div class="bb_sline">
								<label>开始时间：</label>
								<input id="starttime" type="text" placeholder="开始时间" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\');}',dateFmt:'yyyy-MM-dd'})" class="Wdate_bb" style="width: 150px" readonly/>
							</div>
							<div class="bb_sline">
								<label>运单号：</label>
								<input id="waybillno" type="text" placeholder="请输入运单号">
							</div>
							<div class="bb_sline">
								<label>路线：</label>
								<input id="routename" type="text" placeholder="请输入路线"/>
							</div>
						</div>
						<div class="bb_line">
							<div class="bb_sline">
								<label>结束时间：</label>
								<input id="endtime" type="text" placeholder="结束时间" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\');}',dateFmt:'yyyy-MM-dd'})" class="Wdate_bb" style="width: 150px" readonly/>
							</div>
							<div class="bb_sline">
								<label>货物名称：</label>
								<input id="cargoname" type="text" placeholder="请输入货物名称">
							</div>
							<div class="bb_sline">
								<label>运单状态：</label>
								<select id="billStatus" class="form-control">
									<option value="">全部</option>
									<option value="1">待提货</option>
									<option value="2">运输中</option>
									<option value="5">待签收</option>
									<option value="6">已完成</option>
								</select>
							</div>
							<button type="submit" class="btn btnblue resetBtn">重置</button>
							<button type="submit" class="btn btnyello searchBtn">搜索</button>
						</div>
					</div>
					<div class="baob_table">
						<div class="nodata hide">
							<img src="${trRoot}/tianrui/images/none_bill.png">
							<h3>未发现货物运单！</h3>
						</div>
						<table id="report" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>运单日期</th>
									<th>运单号</th>
									<th>货物名称</th>
									<th>路线</th>
									<th>预提量</th>
									<th>签收量</th>
									<th>运单状态</th>
								</tr>
							</thead>
							<tbody>
								
							</tbody>
						</table>
					</div>
					<div class="goods_more pageMore" pageno="1">
	                     <h4>查看更多</h4>
	                 </div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resources/js/common/member/header_busi.js"></script>
	<script type="text/javascript" src="/resources/js/report/driver/main.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#driverreport').addClass("selected");
			PAGE.mod.main.init();
		});
	</script>
</body>
</html>
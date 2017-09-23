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
<title>平台运单报表</title>
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
                                 <label>运单类型：</label>
                                 <select id="billType"  class="form-control">
                                     <option value="">请选择</option>
                                     <option value="dy">大易运单</option>
                                     <option value="al">安联运单</option>
                                 </select>
                             </div>
							<div class="ht_div">
								<label>计划单号：</label> <input id="planNo" type="text" placeholder="请输入运单路线">
							</div>
							<div class="ht_div">
								<label>运单号：</label> <input id="billNo" type="text" placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>货物名称：</label> <input id="cargoName" type="text" placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>车牌号：</label> <input id="vehicleNo" type="text" placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>路线：</label> <input id=routeName type="text" placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>运单状态：</label> <input id="billStatus" type="text" placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>司机姓名：</label> <input id="driverName" type="text" placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>支付对象：</label> <input id="payMent" type="text" placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>发货方：</label> <input id="sendMan" type="text" placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>发货人：</label> <input id="sendPersion" type="text" placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>收货方：</label> <input id="receiptMan" type="text" placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>签收人：</label> <input id="receiptPersion" type="text" placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>业务日期：</label> <input id="businessTimeStart" type="text"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i> <input
									id="businessTimeEnd" type="text"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
							</div>
							<div class="ht_div">
								<label>运单创建时间：</label> <input id="billCreaterTimeStart" type="text"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i> <input
									id="billCreaterTimeEnd" type="text"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
							</div>
							<div class="ht_div">
								<label>接受运单时间：</label> <input id="acceptTimeStart" type="text"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i> <input
									id="acceptTimeEnd" type="text"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
							</div>
							<div class="ht_div">
								<label>提货确认时间：</label> <input id="pickupTimeStart" type="text"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i> <input
									id="pickupTimeEnd" type="text"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
							</div>
							<div class="ht_div">
								<label>卸货确认时间：</label> <input id="unloadTimeStart" type="text"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i> <input
									id="unloadTimeEndtime" type="text"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
							</div>
							<div class="ht_div">
								<label>签收运单时间：</label> <input id="signTimeStart" type="text"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i> <input
									id="signTimeEnd" type="text"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
							</div>
						</div>
						<div class="contuser_search">
							<div class="ht_divbtn">
								<button class="btn btnblue search" onclick="init(0)" type="submit">搜索</button>
								<button class="btn btngreen reset" onclick="reset()" type="submit">重置</button>
							</div>
						</div>
					</div>
				</div>
				<!--查询框end-->
				<div class="row mt15">
					<div class="col-md-12">
						<div class="content-user" style="overflow-x:scroll;width: 1544px;">
							<div class="pro_opra">
								<button class="btn btnblue exportReport">导出</button>
								<button class="btn btnorange printReport">打印</button>
							</div>
							<!--用户表格begin-->
							<table id="planReport" style="white-space: nowrap" class="table table-bordered">
								<thead>
									<tr>
										<th>运单类型</th>
	                                    <th>业务日期</th>
	                                    <th>计划单号</th>
	                                    <th>运单号</th>
	                                    <th>发货方</th>
	                                    <th>发货人</th>
	                                    <th>收货方</th>
	                                    <th>签收人</th>
	                                    <th>车牌号</th>
	                                    <th>货物名称</th>
	                                    <th>路线</th>
	                                    <th>运距</th>
	                                    <th>车主派单量</th>
	                                    <th>提货榜单净重 </th>
	                                    <th>卸货榜单净重</th>
	                                    <th>签收量</th>
	                                    <th>运单状态</th>
	                                    <th>司机姓名</th>
	                                    <th>支付对象</th>
	                                    <th>运单创建时间</th>
	                                    <th>接受运单时间</th>
	                                    <th>提货确认时间</th>
	                                    <th>卸货确认时间</th>
	                                    <th>签收运单时间</th>
									</tr>
								</thead>
								<tbody id="innerHtml">
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
	<script type="text/javascript" src="${scriptsRoot}/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${scriptsRoot}/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery.pagination.js"></script>
	<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript" src="/resources/js/statReport/billAll.js"></script>
</body>
</html>
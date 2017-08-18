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
<title>运单报表统计</title>
<meta name="keywords" content=" 天瑞" />
<link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
<link href="${stylesRoot }/base.css" rel="stylesheet">
<link href="${stylesRoot }/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
<link href="${stylesRoot}/select2.css" rel="stylesheet">
<!--这个日历控件js必须放头部-->
<script language="javascript" type="text/javascript" src="${scriptsRoot}/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
		<!--后台右侧布局begin-->
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
								<label>运单号：</label> <input id="waybillno" type="text" placeholder="">
							</div>
							<div class="ht_div">
								<label>计划号：</label> <input id="plancode" type="text" placeholder="">
							</div>
							<div class="ht_div">
								<label>车主姓名：</label> <input id="remarkname" type="text" placeholder="请输入车主姓名">
							</div>
							<div class="ht_div">
								<label>车牌号：</label> <input id="vehicleno" type="text" placeholder="请输入车牌号">
							</div>
							<div class="ht_div">
								<label>货物名称：</label>
								<input id="cargoname" type="text" placeholder="请输入货物名称">
							</div>
						</div>
						<div class="contuser_search">
							<div class="ht_div">
								<label>提货地：</label>
								<select id="t_position">
									<option value="">请选择</option>
									<option value="1">有提货地</option>
								</select>
							</div>
							<div class="ht_div">
								<label>到货地：</label>
								<select id="d_position">
									<option value="">请选择</option>
									<option value="1">有到货地</option>
								</select>
							</div>
							<div class="ht_div">
								<label>货运时间：</label>
								<select id="interTimeStr">
									<option value="">请选择</option>
									<option value="interTime < 30*60*1000">30分钟以内</option>
									<option value="interTime < 60*60*1000 and interTime >= 30*60*1000">30~60分钟</option>
									<option value="interTime < 1.5*60*60*1000 and interTime >= 1*60*60*1000">1~1.5小时</option>
									<option value="interTime < 2*60*60*1000 and interTime >= 1.5*60*60*1000">1.5~2小时</option>
									<option value="interTime < 3*60*60*1000 and interTime >= 2*60*60*1000">2~3小时</option>
									<option value="interTime < 5*60*60*1000 and interTime >= 3*60*60*1000">3~5小时</option>
									<option value="interTime < 8*60*60*1000 and interTime >= 5*60*60*1000">5~8小时</option>
									<option value="interTime < 10*60*60*1000 and interTime >= 8*60*60*1000">8~10小时</option>
									<option value="interTime < 12*60*60*1000 and interTime >= 10*60*60*1000">10~12小时</option>
									<option value="interTime < 24*60*60*1000 and interTime >= 12*60*60*1000">12~24小时</option>
									<option value="interTime < 1.5*24*60*60*1000 and interTime >= 24*60*60*1000">1~1.5天</option>
									<option value="interTime < 2*24*60*60*1000 and interTime >= 1.5*24*60*60*1000">1.5~2天</option>
									<option value="interTime < 3*24*60*60*1000 and interTime >= 2*24*60*60*1000">2~3天</option>
									<option value="interTime >= 3*24*60*60*1000">3天以上</option>
								</select>
							</div>
							<div  class="ht_div">
								<label>货运距离：</label>
								<select id="interDistanceStr">
									<option value="">请选择</option>
									<option value="interDistance < 5000">5km以内</option>
									<option value="interDistance >= 5000 and interDistance < 10000">5~10km</option>
									<option value="interDistance >= 10000 and interDistance < 15000">10~15km</option>
									<option value="interDistance >= 15000 and interDistance < 20000">15~20km</option>
									<option value="interDistance >= 20000 and interDistance < 30000">20~30km</option>
									<option value="interDistance >= 30000 and interDistance < 40000">30~40km</option>
									<option value="interDistance >= 40000 and interDistance < 50000">40~50km</option>
									<option value="interDistance >= 50000 and interDistance < 60000">50~60km</option>
									<option value="interDistance >= 60000 and interDistance < 80000">60~80km</option>
									<option value="interDistance >= 80000 and interDistance < 100000">80~100km</option>
									<option value="interDistance >= 100000 and interDistance < 120000">100~120km</option>
									<option value="interDistance >= 120000 and interDistance < 140000">120~140km</option>
									<option value="interDistance >= 140000 and interDistance < 160000">140~160km</option>
									<option value="interDistance >= 160000 and interDistance < 180000">160~180km</option>
									<option value="interDistance >= 180000 and interDistance < 200000">180~200km</option>
									<option value="interDistance > 200000">200km以上</option>
								</select>
							</div>
							<div class="ht_div">
								<label>运单状态：</label> 
								<select id="status">
									<option value="">请选择</option>
	                                <option value="-1">车主回收</option>
	                                <option value="0">司机未确认</option>
	                                <option value="1">司机已接受</option>
	                                <option value="2">司机已装货</option>
	                                <option value="3">司机运输中</option>
	                                <option value="4">司机已到达</option>
	                                <option value="5">司机已卸货</option>
	                                <option value="6">已签收</option>
	                                <option value="7">司机拒绝接单</option>
								</select>	
							</div>
							<div class="ht_div">
								<label>运单类型：</label> 
								<select id="bill_type">
									<option value="anlian">开票运单</option>									 
	                                <option value="wuliu">普通运单</option>
								</select>	
							</div>
						</div>
						<div class="contuser_search">
							<div class="ht_div">
								<label>签收时间：</label>
								<input id="ownerSigntime_0Str" type="text"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'ownerSigntime_1Str\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i> 
									<input
									id="ownerSigntime_1Str" type="text"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'ownerSigntime_0Str\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
							</div>
							<div class="ht_div">
								<label>业务日期：</label>
								<input id="starttimeStr" type="text"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtimeStr\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i> 
									<input
									id="endtimeStr" type="text"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttimeStr\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
							</div>
						</div>	
						<div class="contuser_search">
							<div class="ht_div">
								<label>发货组织：</label> 
								<select id="orgid" class="select2" multiple="multiple" style=" width: 250px">
									<c:forEach items="${orgin }" var="sa">
										<option value="${sa.id }">${sa.organizationname }</option>
									</c:forEach>
								</select>	
							</div>
						</div>
							<div class="ht_divbtn">
								<button class="btn btnblue search" type="button">搜索</button>
								<button class="btn btngreen reset" type="button" onclick="regist()">重置</button>
							</div>
					</div>
				</div>
				<!--查询框end-->
				<div class="row mt15">
					<div class="col-md-12">
						<div class="content-user" style="width: 1544px;">
							<div class="pro_opra">
								<button class="btn btnblue billReport_">导出</button>
								<button class="btn btnorange printReport">打印</button>
							</div>
							<!--用户表格begin-->
							<div style="overflow-x:scroll;">
								<table id="anlianBillReport" class="table table-bordered" style="white-space: nowrap">
									<thead>
										<tr>
											<th>序号</th>
											<th>运单类型</th>
											<th>业务日期</th>
		                                    <th>计划单号</th>
		                                    <th>运单号</th>
		                                    <th>发货方</th>
		                                    <th>收货方</th>
		                                    <th>车主姓名</th>
		                                    <th>车牌号</th>
		                                    <th>司机姓名</th>
		                                    <th>货物名称</th>
		                                    <th>间隔分钟数</th>
		                                    <th>间隔距离</th>
		                                    <th>提货时间</th>
		                                    <th>到货时间</th>
		                                    <th>签收时间</th>
		                                   	<th>提货地偏差</th>
		                                   	<th>卸货地偏差</th>
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
							</div>
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
		
		<!--查看详情begin-->
	<div class="modal fade" id="vehicledetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">车辆详情</h4>
	            </div>
	            <div class="modal-body" style=" " id="vehicledetailhml">
	               
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--查看详情end-->
		
		<!--查看详情begin-->
	<div class="modal fade" id="Billdetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">运单详情</h4>
	            </div>
	            <div class="modal-body" style=" " id="Billdetailhml">
	               
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--查看详情end-->
		
	<!--查看详情begin-->
	<div class="modal fade" id="Plandetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">计划详情</h4>
	            </div>
	            <div class="modal-body" style=" " id="Plandetailhml">
	               
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--查看详情end-->

	<!--修改密码end-->
	<%@include file="../../common/footer.jsp"%>
	<script type="text/javascript">
		var CONTEXTPATH = "${contextPath}";
		var imagesRoot = "${imagesRoot}";
	</script>
	<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery.pagination.js"></script>
	<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/select2.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript" src="/resources/js/statReport/new_billStat.js?06071"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.search').trigger('click');
			// 表格列宽度手动调整
			$("table").resizableColumns({});
		});
	</script>
</body>
</html>
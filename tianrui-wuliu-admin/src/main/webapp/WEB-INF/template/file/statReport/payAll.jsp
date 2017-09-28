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
<title>平台账单报表</title>
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
		<jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
		<!--后台右侧布局begin-->
		<!-- <input type="hidden" id="pageNo" value="${empty pageNo?0:pageNo }"> -->
		<div class="col-md-10 ">
			<div class="ht_content">
				<div id="content-header">
					<h3>平台账单报表</h3>
				</div>
				<!--查询框begin-->
				<div class="row">
					<div class="col-md-12">
						<div class="contuser_search">
							<div class="ht_div">
								<label>货物名称：</label> <input id="cargoName" type="text"
									placeholder="请输入货物名称">
							</div>
							<div class="ht_div">
								<label>账单编号：</label> <input id="payCode" type="text"
									placeholder="请输入账单编号">
							</div>
							<div class="ht_div">
								<label>计划单号：</label> <input id="planCode" type="text"
									placeholder="请输入计划单号">
							</div>
							<div class="ht_div">
								<label>发货方：</label> <input id="sendMan" type="text"
									placeholder="请输入发货方">
							</div>
							<div class="ht_div">
								<label>发货人：</label> <input id="sendPersian" type="text"
									placeholder="请输入发货人">
							</div>
							<div class="ht_div" name="mytable" style="display: none">
								<label>账单日期：</label> <input id="paystarttime" type="text"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'payendtime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期"
									readonly /> <i>-</i> <input id="payendtime" type="text"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'paystarttime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择结束日期"
									readonly />
							</div>
							<div class="ht_div" name="mytable" style="display: none">
								<label>运单日期：</label> <input id="billstarttime" type="text"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'billendtime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期"
									readonly /> <i>-</i> <input id="billendtime" type="text"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'billstarttime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择结束日期"
									readonly />
							</div>
							<div class="ht_div" name="mytable" style="display: none">
								<label>运输路线：</label> <input id="routeName" type="text"
									placeholder="请输入运单路线">
							</div>
							<div class="ht_div" name="mytable" style="display: none">
								<label>车主：</label> <input id="venderName" type="text"
									placeholder="请输入车主">
							</div>
							<div class="ht_div" name="mytable" style="display: none">
								<label>收货方：</label> <input id="receiptMan" type="text"
									placeholder="请输入收货方">
							</div>
							<div class="ht_div" name="mytable" style="display: none">
								<label>签收人：</label> <input id="receiptPerson" type="text"
									placeholder="请输入签收人">
							</div>
							<div class="ht_div" name="mytable" style="display: none">
								<label>支付状态：</label> <select id="payStatus" class="form-control">
									<option value="">请选择</option>
									<option value="0">未支付</option>
									<option value="1">支付中</option>
									<option value="2">已支付</option>
									<option value="3">支付失败</option>
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
					<div class="row">
						<div class="col-md-12">
							<div class="position_more">
								<div class="c_Screen">
									<label>更多筛选条件</label> <span><img src="images/more2.png"
										id="gengduo"></span>
								</div>
							</div>

						</div>
					</div>
				</div>
				<!--查询框end-->
				<div class="row mt15">
					<div class="col-md-12">
						<div class="content-user"
							style="overflow-x: scroll; width: 1544px;">
							<div class="pro_opra">
								<button class="btn btnblue exportReport">导出</button>
								<button class="btn btnorange printReport">打印</button>
							</div>
							<!--用户表格begin-->
							<table id="planReport" style="white-space: nowrap"
								class="table table-bordered">
								<thead>
									<tr>
										<th>账单日期</th>
										<th>账单编号</th>
										<th>支付对象</th>
										<th>计划单号</th>
										<th>路线</th>
										<th>发货方</th>
										<th>发货人</th>
										<th>车主</th>
										<th>收货方</th>
										<th>签收人</th>
										<th>车牌号</th>
										<th>运单日期</th>
										<th>运单号</th>
										<th>货物名称</th>
										<th>签收量</th>
										<th>含税单价</th>
										<th>总价</th>
										<th>油卡</th>
										<th>扣重扣杂</th>
										<th>扣款</th>
										<th>其他款项</th>
										<th>应付金额</th>
										<th>付款金额</th>
										<th>支付状态</th>
										<th>付款方式</th>
										<th>收款人</th>
										<th>银行名称</th>
										<th>收款账户</th>
									</tr>
								</thead>
								<tbody id="innerHtml">
								</tbody>
							</table>
							<!--用户表格end-->
						</div>
						<!-- 分页部分  开始-->
						<div class="row pr20 fr">
							<%@include file="../../common/pagination.jsp"%>
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
	<!--查看详情begin-->
	<div class="modal fade" id="vehicledetail" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">车辆详情</h4>
				</div>
				<div class="modal-body" style="" id="vehicledetailhml"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--查看详情end-->

	<!--查看详情begin-->
	<div class="modal fade" id="Billdetail" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">运单详情</h4>
				</div>
				<div class="modal-body" style="" id="Billdetailhml"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--查看详情end-->
	<!-- 账单详情开始 -->
	<div class="modal fade" id="pay_detail" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				 <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >账单详情</h4>
            </div>
				<div class="modal-body">
					<div class="chezhu_box" style="height: 300px">
	                    <table class="table">
	                        <thead>
	                        <tr>
	                            <th>序号</th>
	                            <th>运单号</th>
	                            <th>发票类型</th>
	                            <th>货物名称</th>
	                            <th>支付对象</th>
	                            <th>账单详情</th>
	                        </tr>
	                        </thead>
	                        <tbody id="paylist">
	                        </tbody>
	                    </table>
	                </div>

                <div class="clear"></div>
				
				</div>
				<div class="modal-footer">
					<input type="hidden"  id="pageNo"/>
					<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end -->
	<!--查看详情begin-->
	<div class="modal fade" id="Plandetail" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">计划详情</h4>
				</div>
				<div class="modal-body" style="" id="Plandetailhml"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--查看详情end-->

	<!--修改密码end-->
	<%@include file="../../common/footer.jsp"%>
	<script type="text/javascript"
		src="${scriptsRoot}/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript"
		src="${scriptsRoot}/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="${scriptsRoot}/jquery.pagination.js"></script>
	<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
	<script type="text/javascript"
		src="${scriptsRoot}/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript"
		src="${scriptsRoot}/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript"
		src="/resources/js/statReport/payAll.js?09.37"></script>
	<script type="text/javascript">
		$(function() {

			$(".c_Screen").on('click', function() {
				var div_hs = $("div[name$='mytable']");
				div_hs.toggle();
				if (div_hs.css("display") == 'none') {
					$("#gengduo").attr('src', "${imagesRoot }/more2.png");
				} else {
					$("#gengduo").attr('src', "${imagesRoot }/more1.png");
				}
			});

			function show_hidden(obj) {
				if (obj.css("display") == 'block') {
					obj.css("display", 'none');

				} else if (obj.css("display") == 'none') {
					obj.css("display", 'block');
				}
			}
		});
	</script>
</body>
</html>
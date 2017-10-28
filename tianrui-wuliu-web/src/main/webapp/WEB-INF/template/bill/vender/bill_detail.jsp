<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运单中心</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">

<link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/imgcut.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/tr-media.css" rel="stylesheet">
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
					<label>当前位置：运单</label><span>></span> <label>我运输的运单</label>
				</div>
			</div>
			<div class="row">
				<!-- 引用公共left部分 -->
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
				<!--个人中心左侧end-->
				<!--个人中心右侧begin-->
				<div class="rz_right detaildiv">
					<div class=" bgblue">
						<h2>运单详情(车主)</h2>
					</div>
					<!-- 货源计划内容begin -->
					<div class="goods_box">
						<div class="goods_line">
							<div class="plan_table">
								<input type="hidden" value="${bill.id}" id="billId" /> <input
									type="hidden" value="${bill.desc4}" id="isAppoint" />
								<table class="table ">
									<thead>
										<tr>
											<th>运单号</th>
											<th>货物名称</th>
											<th>计划编码</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.waybillno}</td>
											<td>${bill.cargoname}</td>
											<td>${bill.plancode}</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>起运地</th>
											<th>目的地</th>
											<th>结算里程数</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.startcity }</td>
											<td>${bill.endcity }</td>
											<td>${bill.distance}</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>发货方</th>
											<th>收货方</th>
											<th>承运方</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.shipper }</td>
											<td>${bill.consignee }</td>
											<td>${bill.systemShipper}</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>发货人</th>
											<th>收货人</th>
											<th>运输量</th>
										</tr>
									</thead>
									<tbody>
										<tr class="bill_td">
											<td>
												<h4>${bill.consignorname }</h4>
												<h4>${bill.consignortel }</h4>
											</td>
											<td>
												<h4>${bill.receivername }</h4>
												<h4>${bill.receivertel }</h4>
											</td>
											<td>
											<h4>计划量:${bill.weight }${bill.desc1}</h4>
											<c:if test="${bill.status==6 }">
											<h4>实际量:${bill.trueweight }${bill.desc1}</h4>
											</c:if>
											</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>开始时间</th>
											<th>结束时间</th>
											<th>含税单价</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.starttime }</td>
											<td>${bill.endtime }</td>
											<td>${bill.price}元</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>车辆</th>
											<th>司机</th>
											<th>支付对象</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.vehicleno }</td>
											<td><h4>${bill.drivername }</h4><h4>${bill.drivertel }</h4></td>
											<td>
											<c:if test="${bill.payment eq '1' }">
												司机
											</c:if>
											<c:if test="${bill.payment eq '2' }">
												车主
											</c:if>
											</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>总趟数</th>
											<th>税率</th>
											<th>
												<c:if test="${bill.status==6}">
													运单总价
												</c:if>
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>共${bill.totalnumber }趟</td>
											<td><c:if test="${not empty bill.tallage }">
													<fmt:formatNumber type="number" value="${bill.tallage }"
														maxFractionDigits="0"></fmt:formatNumber>%
                                			</c:if></td>
											<td>
												<c:if test="${bill.status==6}">
													<fmt:formatNumber type="number" value="${bill.price*bill.trueweight}" pattern="0.00" maxFractionDigits="2"/>元
												</c:if>
											</td>
										</tr>
									</tbody>
									<!-- 磅单图片 -->
									<c:if test="${bill.status==2 || bill.status==3 || bill.status==4 || bill.status==5 || bill.status==6}">
										<thead>
				                            <tr>
				                                <th>查看磅单</th>
				                                <th class="underline colorblue"><a id="THBD" item="${bill.pickupimgurl }" psweight="${bill.pickupweight }">提货磅单</a></th>
				                                <c:if test="${bill.status==5 || bill.status==6}">
				                                	<th class="underline colorblue"><a id="XHBD" item="${bill.signimgurl }" psweight="${bill.signweight }">卸货磅单</a></th>
				                                </c:if>
				                            </tr>
			                            </thead>
									</c:if>
									<!-- 磅单图片 -->
								</table>
								<c:if test="${ bill.refuseType !=null}">
									<div class="callback_dtl">
										<label>拒绝原因：</label> <span>${ bill.refuseType}</span>
									</div>
									<div class="callback_dtl">
										<label>客户备注：</label>
										<div>${ bill.refuseReson}</div>
									</div>
								</c:if>
							</div>
						</div>
						<div class="goods_foot">
							<c:if test="${bill.status ==0}">
								<button class="btn btnblue cancleBtn" type="button">收回运单</button>
							</c:if>
							<c:if test="${bill.status ==7 || bill.status==-1}">
								<button class="btn btnyello mr20 editBtn" type="button">修改运单</button>
								<button class="btn btnblue delBtn" type="button">删除</button>
							</c:if>
						</div>
					</div>
					<!-- 货源计划内容end -->
				</div>
				<!--个人中心右侧end-->
			</div>
		</div>
	</div>
	<!--内容部分end-->
	
	<div class="modal fade" id="bdView" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document" style="width: 1200px;">
	        <div class="modal-content" style="width: 1200px;">
	            <div class="modal-header" >
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	            </div>
	            <div class="modal-body" style=" ">
	                <div class="bd_showimg">
	                    <a target="_blank"><img id="bdImg"/></a>
	                </div>
					<div style="text-align: center;padding-top: 20px;font-size: 20px;"><label id="psweight" style="color: white;"></label></div>
	            </div>
	            <div class="modal-footer" style="text-align: center;">
	                <div class="bd_altbtn">
	                    <button type="button" class="btn " data-dismiss="modal">关闭</button>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>

	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
	<!--底部end-->
	<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
	<script type="text/javascript"
		src="/resources/js/common/member/header_busi.js"></script>
	<script type="text/javascript"
		src="/resources/js/bill/vender_detail.js?0105"></script>
	<script type="text/javascript">
		var trRoot = "${trRoot}";
		//弹出框样式更改
	    var headcss = $("#bdView .modal-header");
	    headcss.css({
	        "border":"none",
	    });
	    headcss.find("button").css({
	        "color":"#ffffff"
	    });
	    headcss.find(".close").css({
	        "opacity":"0.8"
	    });
	    var dialogcss = $("#bdView .modal-content");
	    dialogcss.css({
	        "background":"#333333"
	    });
	    var footcss = $("#bdView .modal-footer");
	    footcss.css({
	        "border":"none",
	    });
	</script>
</body>
</html>
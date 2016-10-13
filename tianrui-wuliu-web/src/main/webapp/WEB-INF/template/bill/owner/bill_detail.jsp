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
					<label>当前位置：运单</label><span>></span> <label>我发起的运单</label>
				</div>
			</div>
			<div class="row">
				<!-- 引用公共left部分 -->
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
				<!--个人中心左侧end-->
				<!--个人中心右侧begin-->
				<div class="rz_right">
					<div class=" bgblue">
						<h2>运单详情(货主)</h2>
					</div>
					<!-- 货源计划内容begin -->
					<div class="goods_box detaildiv">
						<div class="goods_line">
							<div class="plan_table">
								<input type="hidden" value="${bill.id}" id="billId" />
								<input type="hidden" value="${bill.weight}" id="weight" />
								<input type="hidden" value="${bill.planweight}" id="planweight" />
								<table class="table ">
									<thead>
										<tr>
											<th>运单号</th>
											<th>货物名称</th>
											<th>计价单位</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.waybillno}</td>
											<td>${bill.cargoname}</td>
											<td>${bill.priceunits}</td>
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
											<h4>计划重量:${bill.weight }${bill.desc1}</h4>
											<c:if test="${bill.status==6 }">
											<h4>实际重量:${bill.trueweight }${bill.desc1}</h4>
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
											<th>联系方式</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.vehicleno }</td>
											<td>${bill.drivername }</td>
											<td>${bill.drivertel }</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>税率</th>
											<th>车主</th>
											<th>车主电话</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><c:if test="${not empty bill.tallage }">
													<fmt:formatNumber type="number" value="${bill.tallage }"
														maxFractionDigits="0"></fmt:formatNumber>%
                                	</c:if></td>
											<td>${bill.venderName }</td>
											<td>${bill.venderTel }</td>
										</tr>
									</tbody>
									<!-- 磅单图片 -->
									<c:if
										test="${bill.status==2 || bill.status==3 || bill.status==4 || bill.status==5 || bill.status==6}">
										<thead>
											<tr>
												<c:if test="${not empty bill.pickupimgurl}">
													<th>提货磅单</th>
												</c:if>
												<c:if test="${bill.status==5 || bill.status==6}">
													<th>卸货磅单</th>
												</c:if>
												<c:if test="${bill.status==6}">
													<th>运单总价</th>
												</c:if>
											</tr>
										</thead>
										<tbody>
											<tr>
												<c:if test="${not empty bill.pickupimgurl}">
													<td><a href="${bill.pickupimgurl }" target="_blank">查看磅单</a></td>
												</c:if>
												<c:if test="${bill.status==5 || bill.status==6}">
													<td><a href="${bill.signimgurl }" target="_blank">查看磅单</a></td>
												</c:if>
												<c:if test="${bill.status==6}">
													<td><fmt:formatNumber type="number" value="${bill.price*bill.trueweight}" pattern="0.00" maxFractionDigits="2"/>元</td>
												</c:if>
											</tr>
										</tbody>
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
							<c:if test="${bill.status ==5}">
								<button class="btn btnblue signBtn" type="button">签收确认</button>
							</c:if>
							<c:if test="${bill.status ==7 || bill.status==-1}">
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

	<!-- 签收modal -->

	<div class="modal fade" id="signModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id=" ">运单签收</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="hidid" />
					<div class="bdimg">
						<div class="modal-body">
			                <ul class="nav nav-tabs">
			                    <li class="active"><a href="#single" data-toggle="tab">提货磅单</a></li>
			                    <li><a href="#multiple" data-toggle="tab">卸货磅单</a></li>
			                </ul>
			                <div class="tab-content">
			                    <div class="tab-pane fade in active" id="single">
			                        <div class="bdimg">
			                        	<c:choose>
			                        		<c:when test="${empty bill.pickupimgurl}">
			                        			<div class="bd_note">
				                        			<label>司机未上传磅单</label>
								                </div>
			                        		</c:when>
			                        		<c:otherwise>
												<img src="${bill.pickupimgurl}" id="qhbdImgUrl" >
			                        		</c:otherwise>
			                        	</c:choose>
			                        </div>
			                    </div>
			                    <div class="tab-pane fade" id="multiple">
			                        <div class="bdimg">
										<img src="${bill.signimgurl }" id="bdimgurl">
			                        </div>
			                    </div>
			                </div>
			            </div>
						<p>
							<label>磅单重量：</label><input type="text" id="weighttext">
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary signsubmitbtn">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 签收moal结束 -->



	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
	<!--底部end-->
	<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
	<script type="text/javascript"
		src="/resources/js/common/member/header_busi.js"></script>
	<script type="text/javascript" src="/resources/js/bill/owner_detail.js?11"></script>

</body>
</html>
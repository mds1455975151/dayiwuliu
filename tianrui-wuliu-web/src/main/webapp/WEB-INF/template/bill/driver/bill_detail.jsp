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
				<div class="rz_right detailDiv">
					<div class=" bgblue">
						<h2>运单详情(驾驶员)</h2>
					</div>
					<!-- 货源计划内容begin -->
					<div class="goods_box">
						<div class="goods_line">
							<div class="plan_table">
								<input type="hidden" value="${bill.id}" id="billId" /> <input
									type="hidden" value="${bill.type}" id="billType" />
								<table class="table ">
									<thead>
										<tr>
											<th>运单号</th>
											<th>车主</th>
											<th>车主电话</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.waybillno}</td>
											<td>${bill.venderName}</td>
											<td>${bill.venderTel}</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>货物名称</th>
											<th>起运地</th>
											<th>目的地</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.cargoname}</td>
											<td>${bill.startcity }</td>
											<td>${bill.endcity }</td>
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
											<td style="padding-top: 20px;">${bill.weight }${bill.desc1}
											</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>开始时间</th>
											<th>结束时间</th>
											<!--   <th>含税单价 </th> -->
											<th>车辆</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.starttime }</td>
											<td>${bill.endtime }</td>
											<%--  <td>${bill.price}元 </td> --%>
											<td>${bill.vehicleno }</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>司机</th>
											<th>联系方式</th>
											<th>总趟数</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.drivername }</td>
											<td>${bill.drivertel }</td>
											<td>共${bill.totalnumber }趟</td>
										</tr>
									</tbody>
									<!-- 榜单图片 -->
									<c:if
										test="${bill.status==2 || bill.status==3 || bill.status==4 || bill.status==5 || bill.status==6}">
										<thead>
											<tr>
												<c:if test="${not empty bill.pickupimgurl}">
													<th>提货榜单</th>
												</c:if>
												<c:if test="${bill.status==5 || bill.status==6}">
													<th>卸货磅单</th>
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
											</tr>
										</tbody>
									</c:if>
									<!-- 榜单图片 -->
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
						<c:if test="${bill.driverdelflag eq 0 }">
							<div class="goods_foot">
								<c:if test="${bill.status ==0}">
									<button class="btn btnyello mr20 acceptBtn" type="button">接收运单</button>
									<c:if test="${bill.type != 2 }">
										<button class="btn btnblue refuseBtn" type="button">拒绝运单</button>
									</c:if>
								</c:if>
								<c:if test="${bill.status ==1}">
									<button class="btn btnblue pickupBtn" type="button">提货确认</button>
								</c:if>
								<%-- <c:if test="${bill.status ==2}">
			                    	<button class="btn btnblue departureBtn" type="button">装货完成确认</button>
			                	</c:if>
			                	<c:if test="${bill.status ==3}">
			                    	<button class="btn btnblue arrivedBtn" type="button">到达目的地确认</button>
			                	</c:if>
			                	<c:if test="${bill.status ==4}"> --%>
								<c:if test="${bill.status ==2}">
									<button class="btn btnblue dischargeBtn" type="button">卸货确认</button>
								</c:if>
								<c:if test="${bill.status ==7 || bill.status==8}">
									<button class="btn btnblue delBtn" type="button">删除</button>
								</c:if>
							</div>
						</c:if>
					</div>
					<!-- 货源计划内容end -->
				</div>
				<!--个人中心右侧end-->
			</div>
		</div>
	</div>
	<!--内容部分end-->
	<!-- 拒绝modal -->

	<div class="modal fade" id="refuseModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id=" ">运单拒绝</h4>
				</div>
				<div class="modal-body">
					<div class="bill_rcont">
						<div class="bii_refuse">
							<label> 请选择拒绝运单理由：</label>
							<div class="bill_Rradio ">
								<p>
									<input type="radio" value="货太多了接不了" name="refuseCheckbox"
										class="refuseCheckbox"><span>货太多了接不了</span>
								</p>
								<p>
									<input type="radio" value="车辆暂时无法使用" name="refuseCheckbox"
										class="refuseCheckbox"><span>车辆暂时无法使用</span>
								</p>
								<p>
									<input type="radio" value="已有货，不用了" name="refuseCheckbox"
										class="refuseCheckbox"><span>已有货，不用了</span>
								</p>
								<p>
									<input type="radio" value="其他" name="refuseCheckbox"
										class="refuseCheckbox"><span>其他</span>
								</p>
							</div>
						</div>
						<div class="bii_refuse">
							<label> 备注：</label>
							<div class="bill_Rradio ">
								<textarea class="form-control refuseContent" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary refusesubmitbtn">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 拒绝moal结束 -->

	<!--磅单begin-->
	<div class="modal fade" id="upbangdan" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">磅单图片上传</h4>
				</div>
				<div class="modal-body">
					<!--磅单图片上传弹出-->
					<input type="hidden" id="imgdata" /> <input id="urlReq"
						type="hidden" />
					<div class="bd_alert">
						<div class="bangdan_note">
							<label>磅单图片：</label>
							<button class="btn btnbd bdup">点此上传</button>
							<div class="bangdan_img">
								<img src="${trRoot}/tianrui/images/bd.png">
							</div>
							<p>注：图片上传按照上面样式，大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</p>
						</div>
						<!--磅单图片begin-->
						<div class="bangdan_upload">
							<!--图片裁剪框-->
							<div class="imageBox imageBox_bd">
								<div class="thumbBox"></div>
								<div class="spinner" style="display: none">Loading...</div>
							</div>
							<!--图片裁剪框end-->
							<!--操作按钮begin-->
							<div class="action wbd">
								<div class="new-contentarea tc">
									<a href="javascript:void(0)" class="upload-img"> <label
										for="upload-file">选择图片</label>
									</a> <input type="file" class="" name="upload-file"
										id="upload-file" />
								</div>
								<button id="btncancel" class="Btnsty_peyton peytonbg1 tx_cancel">
									<i class="iconfont icon-huitui"></i>
								</button>
								<button id="btnCrop" class="Btnsty_peyton peytonbg1">
									裁切</button>
								<button id="btnZoomIn" class="Btnsty_peyton peytonbg1">
									<i class="iconfont icon-bf-add"></i>
								</button>
								<button id="btnZoomOut" class="Btnsty_peyton peytonbg1">
									<i class="iconfont icon-jianhao"></i>
								</button>
							</div>
							<!--操作按钮end-->
							<div class="tx_shouqi">
								<img src="${trRoot}/tianrui/images/jtup.png">
								<h4>收起</h4>
							</div>
						</div>
						<!--磅单图片end-->
					</div>
					<!--磅单图片上传弹出-->

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary departsubmitbtn">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--磅单end-->


	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
	<!--底部end-->
	<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
	<script type="text/javascript"
		src="/resources/js/common/member/header_busi.js"></script>
	<script type="text/javascript"
		src="/resources/js/bill/driver_detail.js"></script>

</body>
</html>
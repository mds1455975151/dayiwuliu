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
<link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
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
									<input type="hidden" value="${bill.frebilltype }" id="frebilltype"/>
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
											<td id="waybillno">${bill.waybillno}</td>
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
											<td><span id="startcity">${bill.startcity }</span></td>
											<td><span id="endcity">${bill.endcity }</span></td>
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
											<th>支付对象</th>
											<th>总趟数</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><h4>${bill.drivername }</h4>
											<h4>${bill.drivertel }</h4></td>
											<td><c:if test="${bill.payment eq '1' }">
												司机
											</c:if>
											<c:if test="${bill.payment eq '2' }">
												车主
											</c:if></td>
											<td>共${bill.totalnumber }趟</td>
										</tr>
									</tbody>
									<c:if test="${bill.payment eq '1' }">
									<thead>
									<tr>
										<th>税率</th>
										<th>收款人</th>
										<th>银行卡</th>
									</tr>
									</thead>
									<tbody>
										<tr>
											<td>
											<c:if test="${not empty bill.tallage }">
													<fmt:formatNumber type="number" value="${bill.tallage }"
														maxFractionDigits="0"></fmt:formatNumber>%
		                                	</c:if>
		                                	</td>
		                                	<td>
											${bill.bankOwnerName }<br>
											${bill.bankOwnerPhone }
											</td>
		                                	<td>
		                                	${bill.bankCard }<br>
		                                	<a onclick="findMyBank();">更换银行卡</a>
		                                	</td>
										</tr>
									</tbody>
									</c:if>
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
						<c:if test="${bill.driverdelflag eq 0 }">
							<div class="goods_foot">
								<c:if test="${bill.status ==0}">
									<button class="btn btnyello mr20 acceptBtn" type="button">接收运单</button>
									<c:if test="${bill.type != 2 }">
										<button class="btn btnblue refuseBtn" type="button">拒绝运单</button>
									</c:if>
								</c:if>
								<%--<c:if test="${bill.status ==1}">
									<button class="btn btnblue pickupBtn" type="button">提货确认</button>
								</c:if>--%>
								<%-- <c:if test="${bill.status ==2}">
			                    	<button class="btn btnblue departureBtn" type="button">装货完成确认</button>
			                	</c:if>
			                	<c:if test="${bill.status ==3}">
			                    	<button class="btn btnblue arrivedBtn" type="button">到达目的地确认</button>
			                	</c:if>
			                	<c:if test="${bill.status ==4}"> --%>
								<%--<c:if test="${bill.status ==2}">
									<button class="btn btnblue dischargeBtn" type="button">卸货确认</button>
								</c:if>--%>
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
	<!-- 更换银行卡 -->
	<div class="modal fade" id="bank_card" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">更换银行卡</h4>
	            </div>
	            <div class="modal-body">
	                <div class="bill_rcont">
		                <div class="car_scroll">
		                    <ul id="bank_list">
	
		                    </ul> 
		                </div>
	                </div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- 更换银行卡 -->
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
	
	<!--磅单未上传begin-->
	<div class="modal fade" id="bdts" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">友情提示</h4>
	            </div>
	            <div class="modal-body">
	                <div class="bd_note">
	                    <i class="icononline">&#xe615;</i><label>您的磅单还未上传，是否现在上传？</label>
	                </div>
	
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" id="uploadImg">上传</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--磅单未上传end-->
	
	<!--重新上传begin-->
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
	            <div class="modal-footer">
	                <div class="bd_altbtn">
	                    <button type="button" class="btn" id="againUploadImg">重新上传</button>
	                    <button type="button" class="btn " data-dismiss="modal">关闭</button>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<!--重新上传end-->

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
					<input id="urlReq" type="hidden" />
					<input id="bdType" type="hidden" />
					<div class="bd_alert">
						<div class="bangdan_note">
							<label>磅单示例图片：</label>
						</div>
						<!--磅单图片begin-->
	                    <div class="img_upload">
							<input id="file_bd" class="file" type="file">
							<span class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
						</div>
	                    <!--磅单图片end-->
	                    <div style="margin-top: 10px;">
	                    	<label id="stateWeightLabel">提货量：</label><input id="stateWeight" type="text" maxlength="6" style="margin-left: 5px;"/>
	                    </div>
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
	<script type="text/javascript" src="/resources/js/common/member/header_busi.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>
	<script type="text/javascript" src="/resources/js/bill/driver_detail.js?0916"></script>
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
	    var footcss = $("#bdView .bd_altbtn button:last-child ");
	    footcss.css({
	        "margin-left":"40px",
	    });
	</script>
</body>
</html>
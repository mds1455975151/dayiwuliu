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
					<label>当前位置：运单</label><span>></span> <label>开票运单</label>
				</div>
			</div>
			<div class="row">
				<!-- 引用公共left部分 -->
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
				<!--个人中心左侧end-->
				<!--个人中心右侧begin-->
				<div class="rz_right detaildiv">
					<div class=" bgblue">
						<h2>开票运单</h2>
						<input type="hidden" id="billId" value="${bill.id }">
					</div>
					<!-- 货源计划内容begin -->
					<div class="goods_box">
						<div class="goods_line">
							<div class="plan_table">
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
											<td>${bill.billno}</td>
											<td>${bill.hpmc}</td>
											<td>${bill.planCode}</td>
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
											<td>${bill.qycs }</td>
											<td>${bill.mdcs }</td>
											<td>${bill.lc}</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>收货人</th>
											<th>联系手机</th>
											<th>运输量</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.shr }</td>
											<td>${bill.lxsj }</td>
											<td>${bill.sl }${bill.dw}</td>
										</tr>
									</tbody>
									
									<thead>
										<tr>
											<th>车主</th>
											<th>车主电话</th>
											<th>要求提货日期</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.vendername }</td>
											<td>${bill.vendertel }</td>
											<td>${bill.yqthrq }</td>
										</tr>
									</tbody>
									
									<thead>
										<tr>
											<th>货主</th>
											<th>货主电话</th>
											<th>要求到货日期</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.ownername }</td>
											<td>${bill.ownertel }</td>
											<td>${bill.yqdhrq }</td>
										</tr>
									</tbody>
									
									<thead>
										<tr>
											<th>车辆</th>
											<th>司机(安联)</th>
											<th>联系方式</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.cph }</td>
											<td>${bill.sj }-${bill.drivername }</td>
											<td>${bill.drivertel }</td>
										</tr>
									</tbody>
									<thead>
										<tr>
											<th>承运方</th>
											<th>运单状态</th>
											<th>支付对象</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.systemShipper}</td>
											<td>${bill.status }</td>
											<td>
											<c:if test="${bill.payment eq '1' }">司机</c:if>
											<c:if test="${bill.payment eq '2' }">车主</c:if>
											</td>
										</tr>
									</tbody>
									<c:if test="${bill.payment eq '1' }">
									<thead>
										<tr>
											<th>收款人</th>
											<th>收款账号</th>
											<th>银行卡</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.bankOwnerName }</td>
											<td>${bill.bankOwnerPhone }</td>
											<td>${bill.bankCard }<br>
		                                	<a onclick="findMyBank();">更换银行卡</a></td>
											
										</tr>
									</tbody>
									</c:if>
									
									<thead>
										<tr>
											<th>总运费</th>
											<th>提货磅单</th>
											<th>提货量</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${bill.yf}元</td>
											<c:if test="${not empty bill.pickupimgurl}">
												<td><a href="/imageView/index?imageUrl=${bill.pickupimgurl}" target="_blank">查看磅单</a></td>
											</c:if>
												<td>${bill.pickupweight}</td>
										</tr>
									</tbody>
									
									<thead>
										<tr>
											<th>卸货磅单</th>
											<th>卸货量</th>
											<th>签收量</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<c:if test="${not empty bill.signimgurl}">
												<td><a href="/imageView/index?imageUrl=${bill.signimgurl}" target="_blank">查看磅单</a></td>
											</c:if>
												<td>${bill.signweight}</td>
												<td>${bill.trueweight}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
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

	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
	<!--底部end-->
	<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
	<script type="text/javascript"
		src="/resources/js/common/member/header_busi.js"></script>
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
	<script type="text/javascript">
	/** 查询银行卡*/
	function findMyBank(){
		$('#bank_card').modal();
		$("#bank_list").empty();
		$.ajax({
			url:"/trwuliu/bank/card/find",
			type:"post",
			data:{bankautid:"1"},
			success:function(ret){
				var data = ret.data.list;
				for (var a = 0; a < data.length; a++) {
					var bankType = "我的银行卡";
					if(data[a].type=="0"){
						bankType = "引用银行卡";
					}
					var hml = "<li>" +
							"<label style='width: 60px;'>"+data[a].idname+"</label>" +
							"<label style='width: 120px;'>"+data[a].telphone+"</label>" +
							"<label style='width: 160px;'>"+data[a].bankcard+"</label>" +
							"<label style='width: 120px;'>"+bankType+"</label>" +
							"<label style='width: 60px;'>" +
							"<button class='btn btnyello' onclick=\"uptBillBank('"+data[a].id+"','"+data[a].type+"','"+data[a].bankcard+"')\" style='width: 56px;height: 36px'>更换</button>" +
							"</label>" +
							"</li>";
					$("#bank_list").append(hml);
				}
			}
		});
	}
	function uptBillBank(id,type,bankcard){
		if(type == "0"){
			confirm("更换银行卡确认","该银行卡为引用车主银行卡，确定设置"+bankcard+"为支付银行卡吗？",function(){
				$.ajax({
					url:"/trwuliu/billAnlian/uptBankCard",
					data:{"bankId":id,
						"bankType":"0",
						"billId":$("#billId").val()},
						type:"POST",
						success:function(ret){
							if(ret.code==000000){
								window.location.href=location;
							}else{
								alert(ret.error);
							}
						}
				});
			});
		}else{
			confirm("更换银行卡确认","确定设置"+bankcard+"为支付银行卡吗？",function(){
				$.ajax({
					url:"/trwuliu/billAnlian/uptBankCard",
					data:{"bankId":id,
						"bankType":"1",
						"billId":$("#billId").val()},
						type:"POST",
						success:function(ret){
							if(ret.code==000000){
								window.location.href=location;
							}else{
								alert(ret.error);
							}
						}
				});
			});
			
		}
	}
	</script>
</body>
</html>
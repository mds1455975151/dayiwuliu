<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大易物流平台-车辆开票认证</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="${trRoot}/tianrui//css/imgcut.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/fileinput.css" media="all"
	rel="stylesheet" type="text/css" />
<!-- 引用公共header部分 -->
<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
<!--内容部分begin-->
<div class="bghui">
	<div class="container">
		<!--网站位置-->
		<div class="row">
			<div class="rz_line">
				<label>首页</label><span>></span> <label>账号</label><span>></span><label>个人中心</label>
			</div>
		</div>
		<div class="row">
			<!--个人中心左侧begin-->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
			<!--个人中心左侧end-->
			<!--个人中心右侧begin-->
			<div class="rz_right">
				<div class=" bgblue">
					<h2>开票认证修改</h2>
				</div>
				<!-- 个人车辆begin -->
				<form id="vehickeTicket">
				<div class="car_box">
					<input type="hidden" name="id" value="${ticket.id }">
					<input type="hidden" name="vehicleid" value="${ticket.vehicleid }">
					<div class="reg_tel">
						<label> <span style="color: red">*</span>使用性质：</label> 
						<c:if test="${ticket.nature == 1 }">
						<input type="radio" value="1" checked="checked" name="nature">营运
						<input type="radio" value="2" name="nature">非营运
						</c:if>
						<c:if test="${ticket.nature == 2 }">
						<input type="radio" value="1" name="nature">营运
						<input type="radio" value="2" checked="checked" name="nature">非营运
						</c:if>
						<p id="message_xingzhi"></p>
					</div>
					<div class="reg_tel">
						<label> <span style="color: red">*</span>总质量：</label> 
						<input type="text" id="veh_quality" value="${ticket.quality }" name="quality">千克
						<p id=""></p>
					</div>
					<div class="reg_tel">
						<label> <span style="color: red">*</span>所有人：</label> 
						<input type="text" value="${ticket.owner }" id="veh_owner" name="owner">
						<p id=""></p>
					</div>
					<div class="reg_tel">
						<label> <span style="color: red">*</span>证件号码：</label> 
						<input type="text" value="${ticket.idcard }" id="veh_idcard" name="idcard">
						<p id=""></p>
					</div>
					<div class="reg_tel">
						<label> <span style="color: red">*</span>登记证书编号：</label> 
						<input type="text" value="${ticket.certificateno }" id="veh_certificateno" name="certificateno">
						<p id=""></p>
					</div>
					<div class="reg_tel">
						<label> <span style="color: red">*</span>检验有效期止：</label> 
						<input type="text" value="${ticket.expirydata }" id="veh_expirydata" placeholder="日期格式：yyyy-MM-dd" name="expirydata">
						<p id=""></p>
					</div>
					<div class="reg_tel">
						<label> <span style="color: red">*</span>车辆识别码：</label> 
						<input type="text" value="${ticket.identification }" id="veh_identification" name="identification">
						<p id=""></p>
					</div>
					<div class="reg_tel">
						<label> <span style="color: red">*</span>发动机号：</label> 
						<input type="text" value="${ticket.motor }" id="veh_motor" name="motor">
						<p id=""></p>
					</div>
					<input type="hidden" value="${ticket.motorno }" id="veh_motorno" name="motorno">
					<div class="car_photo">
						<div class="car_addbtn">
							<button type="button" class="btn btnyello" id="vehicle_ticket_upt">修改</button>
							<button type="button" class="btn btnblue" id="">取消</button>
						</div>
					</div>
				</div>
				</form>
			</div>
		</div>
		<!-- 个人车辆end -->
	</div>
	<!--个人中心右侧end-->
</div>
<!--上传进度条-->
<a id="showload" data-toggle="modal" data-target="#detail"></a>
<div class="modal fade" id="detail" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width: 400px;">
		<div class="upmodal">
			<div class="modal-content">
				<div class="modal-body">
					<div class="upload">
						<img src="${trRoot}/tianrui/images/upload.gif">
						<div class="upload_font">
							<img src="${trRoot}/tianrui/images/sc.png">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--上传进度条end-->
<!--内容部分end-->
<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js"></script>
<script type="text/javascript" src="/resources/js/member/vehicle/kaipiaoupt.js?01.11"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
</html>

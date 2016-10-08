<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天瑞物流平台-修改车辆</title>
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
					<h2>修改车辆</h2>
				</div>
				<!-- 个人车辆begin -->
				<div class="car_box">
					<div class="reg_tel">
						<input type="hidden" id="vehicleid" value="${vehicle.id }">
						<label>车牌号码：</label> <input type="text"
							value="${vehicle.vehiclePrefix }${vehicle.vehicleNo}"
							id="vehicle_add_vehiNo">
						<p id="message_vehiNo"></p>
					</div>
					<div class="reg_tel">
						<label>车辆类型：</label> <select class="form-control w350"
							id="vehicle_add_vehiType">

							<option value="0">请选择</option>
							<option
								<c:if test="${vehicle.vehicleType eq '5' }">selected="true"</c:if>
								value="5">半挂车</option>
							<option
								<c:if test="${vehicle.vehicleType eq '1' }">selected="true"</c:if>
								value="1">厢式</option>
							<option
								<c:if test="${vehicle.vehicleType eq '2' }">selected="true"</c:if>
								value="2">板车</option>
							<option
								<c:if test="${vehicle.vehicleType eq '3' }">selected="true"</c:if>
								value="3">冷藏</option>
							<option
								<c:if test="${vehicle.vehicleType eq '4' }">selected="true"</c:if>
								value="4">散装罐车</option>
						</select>
						<div class="clear"></div>
						<p id="message_vehiType"></p>
					</div>
					<div class="reg_tel">
						<label>车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长：</label> <input
							type="text" placeholder="" value="${vehicle.vehiLength }"
							id="vehicle_add_vehiLength"> 米
						<p id="message_vehiLength"></p>
					</div>
					<div class="reg_tel">
						<label>载&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重：</label> <input
							type="text" placeholder="" value="${vehicle.vehiWeight }"
							id="vehicle_add_vehiWeight"> 吨
						<p id="message_vehiWeight"></p>
					</div>
					<div class="reg_tel">
						<label>所有人姓名：</label> <input type="text"
							value="${vehicle.vehiOwnerName }" id="vehicle_add_vehiOwnerName">
						<p id="message_vehiOwnerName"></p>
					</div>
					<div class="reg_tel">
						<label>联系电话：</label> <input type="text"
							value="${vehicle.vehiOwnerTel }" maxlength="11"
							id="vehicle_add_vehiTel">
						<p id="message_vehiTel"></p>
					</div>
					<div class="reg_tel">
						<label>车辆照片：</label>
						<div class="rz_persontab">
							<div class="samples">
								<a href="${vehicle.vehiHeadImgPath }" target="_blank"> <img
									class="cel" width="240" src="${vehicle.vehiHeadImgPath }">
								</a>
							</div>
							<div class="img_upload">
								<input id="file_cel" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
							</div>
						</div>
					</div>
					<div class="reg_tel">
						<label>行驶证：</label>
						<div class="rz_persontab">
							<div class="samples">
								<a href="${vehicle.vehiLicenseImgPath }" target="_blank"> <img
									class="xsz" width="120" src="${vehicle.vehiLicenseImgPath }">
								</a>
							</div>
							<div class="img_upload">
								<input id="file_xsz" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
							</div>
						</div>
					</div>
					<div class="car_photo">
						<div class="car_addbtn">
							<button type="submit" class="btn btnyello" id="vehicle_addBtn">修改</button>
							<button type="submit" class="btn btnblue" id="vehicle_cancelBtn">取消</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 个人车辆end -->
		</div>
		<!--个人中心右侧end-->
	</div>
</div>
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
<script type="text/javascript"
	src="/resources/js/common/member/header_busi.js"></script>
<script type="text/javascript">
	var trRoot = "${trRoot}/tianrui/images/";
</script>
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript"
	src="/resources/js/member/vehicle/updateVehiclePage.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
<script type="text/javascript"
	src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>

<script type="text/javascript">
	$("#file_cel")
			.fileinput({
				language : 'zh',
				showUpload : false,
				dropZoneEnabled : false,
				maxFileCount : 1,
				//      minImageWidth: 50, //图片的最小宽度
				//	    minImageHeight: 50,//图片的最小高度
				//      maxImageWidth: 600,//图片的最大宽度
				//	    maxImageHeight: 600,//图片的最大高度
				maxFileSize : 5120,//单位为kb，如果为0表示不限制文件大小
				resizeImage : true,
				showCaption : true,
				showPreview : true,
				allowedFileExtensions : [ 'jpg', 'png', 'jpeg' ]
			// 支持的图片类型
			})
			.on(
					'fileuploaderror',
					function(event, data, previewId, index) {
						var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
						console.log(data);
						console.log('File upload error');
					})
			.on('fileerror', function(event, data) {
				console.log(data.id);
				console.log(data.index);
				console.log(data.file);
				console.log(data.reader);
				console.log(data.files);
			})
			.on(
					'fileuploaded',
					function(event, data, previewId, index) {
						var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
						console.log('File uploaded triggered');
					});
	$("#file_xsz")
			.fileinput({
				language : 'zh',
				showUpload : false,
				dropZoneEnabled : false,
				maxFileCount : 1,
				//      minImageWidth: 50, //图片的最小宽度
				//	    minImageHeight: 50,//图片的最小高度
				//      maxImageWidth: 600,//图片的最大宽度
				//	    maxImageHeight: 600,//图片的最大高度
				maxFileSize : 5120,//单位为kb，如果为0表示不限制文件大小
				resizeImage : true,
				showCaption : true,
				showPreview : true,
				allowedFileExtensions : [ 'jpg', 'png', 'jpeg' ]
			// 支持的图片类型
			})
			.on(
					'fileuploaderror',
					function(event, data, previewId, index) {
						var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
						console.log(data);
						console.log('File upload error');
					})
			.on('fileerror', function(event, data) {
				console.log(data.id);
				console.log(data.index);
				console.log(data.file);
				console.log(data.reader);
				console.log(data.files);
			})
			.on(
					'fileuploaded',
					function(event, data, previewId, index) {
						var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
						console.log('File uploaded triggered');
					});
</script>
</body>
</html>

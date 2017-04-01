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
<title>大易物流平台-修改车辆</title>
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
						<label><span style="color: red">*</span>车牌号码：</label> <input type="text"
							value="${vehicle.vehiclePrefix }${vehicle.vehicleNo}"
							id="vehicle_add_vehiNo">
						<p id="message_vehiNo"></p>
					</div>
					<div class="reg_tel">
						<label><span style="color: red">*</span>道路运输证号：</label>
						<input type="text" id="vehicle_add_roadtransportcode" value="${vehicle.roadtransportcode }"> 
						<p id="message_roadtransportcode"></p>
						<!-- 
						<div class="rz_persontab">
							<div class="samples">
								<img class="ysz" style="max-height: 240px;" src="${vehicle.roadtransportimage }">
							</div>
							<div class="img_upload">
								<input id="file_ysz" onchange="yszfile()" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
							</div>
							<input type="hidden" id="file_ysz_img">
						</div>
						 -->
					</div>
					<div class="reg_tel">
						<label><span style="color: red">*</span>经营许可证号：</label> <input type="text" id="vehicle_add_opercode" value="${vehicle.opercode }">
						<p id="message_opercode"></p>
						<div class="rz_persontab">
							<div class="samples">
								<a href="${vehicle.operimage }" target="_blank"> <img
									class="xkz" width="240" src="${vehicle.operimage }">
								</a>
							</div>
							<div class="img_upload">
								<input id="file_xkz" onchange="xkzfile()" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
							</div>
							<input type="hidden" id="file_xkz_img">
						</div>
					</div>
					<div class="reg_tel">
						<label><span style="color: red">*</span>车辆类型：</label> <select class="form-control w350"
							id="vehicle_add_vehiType">

							<option value="0">请选择</option>
							<c:forEach items="${vt }" var="type">
							<option value="${type.wlcode }" 
							<c:if test="${vehicle.vehicleType eq type.wlcode }">selected="true"</c:if>
							>${type.wlname }</option>
							</c:forEach>
						</select>
						<div class="clear"></div>
						<p id="message_vehiType"></p>
					</div>
					<div class="reg_tel">
						<label><span style="color: red">*</span>车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长：</label> <input
							type="text" placeholder="" value="${vehicle.vehiLength }"
							id="vehicle_add_vehiLength"> 米
						<p id="message_vehiLength"></p>
					</div>
					<div class="reg_tel">
						<label><span style="color: red">*</span>载&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重：</label> <input
							type="text" placeholder="" value="${vehicle.vehiWeight }"
							id="vehicle_add_vehiWeight"> 吨
						<p id="message_vehiWeight"></p>
					</div>
					<div class="reg_tel">
						<label><span style="color: red">*</span>所有人姓名：</label> <input type="text"
							value="${vehicle.vehiOwnerName }" id="vehicle_add_vehiOwnerName">
						<p id="message_vehiOwnerName"></p>
					</div>
					<div class="reg_tel">
						<label><span style="color: red">*</span>联系电话：</label> <input type="text"
							value="${vehicle.vehiOwnerTel }" maxlength="11"
							id="vehicle_add_vehiTel">
						<p id="message_vehiTel"></p>
					</div>
					<div class="reg_tel">
						<label><span style="color: red">*</span>车辆照片：</label>
						<div class="rz_persontab">
							<div class="samples">
								<a href="${vehicle.vehiHeadImgPath }" target="_blank"> <img
									class="cel" width="240" src="${vehicle.vehiHeadImgPath }">
								</a>
							</div>
							<div class="img_upload">
								<input id="file_cel" onchange="celfile()" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
							</div>
							<input type="hidden" id="file_cel_img">
						</div>
					</div>
					<div class="reg_tel">
						<label><span style="color: red">*</span>行驶证：</label>
						<div class="rz_persontab">
							<div class="samples">
								<a href="${vehicle.vehiLicenseImgPath }" target="_blank"> <img
									class="xsz" width="240" src="${vehicle.vehiLicenseImgPath }">
								</a>
							</div>
							<div class="img_upload">
								<input id="file_xsz" onchange="xszfile()" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
							</div>
							<input type="hidden" id="file_xsz_img">
						</div>
					</div>
					
					<div class="reg_tel">
						<label>车辆登记证：</label> 
						<p id="message_registcode"></p>
						<div class="rz_persontab">
							<div class="samples">
								<a href="${vehicle.registimage }" target="_blank"> <img
									class="djz" width="240" src="${vehicle.registimage }">
								</a>
							</div>
							<div class="img_upload">
								<input id="file_djz" onchange="djzfile()" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
							</div>
							<input type="hidden" id="file_djz_img">
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
	src="/resources/js/member/vehicle/updateVehiclePage.js?0401"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
<script type="text/javascript"
	src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>

<script type="text/javascript">
	$("#file_sfz,#file_cel,#file_xsz,#file_ysz,#file_xkz,#file_djz")
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

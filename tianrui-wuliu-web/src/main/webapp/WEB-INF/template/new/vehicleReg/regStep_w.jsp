<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天瑞物流_车辆信息</title>
<link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/tr-media.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${trRoot}/tianrui/js/My97DatePicker/WdatePicker.js"></script>

<style type="text/css">
.car_btnnext {
	margin-left: 90px;
	width: 350px;
}
</style>
</head>
<body>
	<!--Header-->
	<div class="bghui">
		<div class="container">
			<!--登录头部行begin-->
			<div class="header">
				<div class="header_left">
					<a><i class=" iconfont icon-hert"></i><label>收藏</label></a> <a><i
						class=" iconfont icon-shouji"></i><label>手机版</label></a> <a
						href="/trwuliu/Member/chooseRole"><i
						class=" iconfont icon-huiyuan"></i><label>切换角色</label></a>
				</div>
				<div class="header_right">
					<label id="header_welcome">欢迎来到天瑞物流 </label> <label
						id="header_loginHref"> <a href="/publicMember/loginPage">请登录</a></label>
					<label id="header_registerHref"> <a
						href="/publicMember/registerPage">免费注册</a></label>
				</div>
			</div>
			<!--登录头部行end-->
		</div>
	</div>
	<div class="bgblue">
		<div class="container">
			<!--导航条begin-->
			<div class="navreg">
				<img src="${trRoot}/tianrui/images/logoreg.png">
			</div>
		</div>
	</div>
	<!--Header-->
	<!--登录内容部分begin-->
	<div class="bghui">
		<div class="container">

			<div class="row bgwhite  mt20 mb20 p15">
				<div class="reg_head border_qhui ">
					<h3>完全认证</h3>
				</div>
				<div class="reg_zhaohui">
					<ul>
						<li class="vehicel_info" style="width: 49%;">
							<div style="float: right;">
								<img id="vehicel_img" src="${trRoot}/tianrui/images/zhmm11.jpg">
								<h4 id="vehicel_class" class="colorblue">车辆信息</h4>
							</div>
						</li>
						<li class="info_info" style="width: 49%;">
							<div style="float: left;">
								<img id="info_img" src="${trRoot}/tianrui/images/zhmm22.jpg">
								<h4 id="info_class">认证信息</h4>
							</div>
						</li>
					</ul>	
				</div>
				<div class="car_rzbox">
					<div class="car_rztab">
						<ul>
							<li id="add_class_w" class="select">完全认证</li>
						</ul>
						<div class="car_rzborder"></div>
					</div>
					<form id="vehicleRegStep">
					<div class="car_rztabbox">
						<input type="hidden" value="1" name="authType" id="authType_req">
						<!--tab切换的内容  车辆信息-->
						<div class="cg_tabcont vehicle_class">
							<div class="rz_carbox">
								
								<div class="rz_carsolo renzhen">
									<label><span style="color: red">*</span>车牌号码:</label>
									<div class="rz_carsolocont">
										<input name="vehicleno" value="${vehicle.vehicleno }" id="vehicleNo_req" type="text">
										<br>
										<span style="color: red" id="vehicleNo_massage"></span>
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label><span style="color: red">*</span>车辆类型:</label>
									<div class="rz_carsolocont">
										<select class="form-control w350" value="${vehicle.vehicletype }" name="vehicletype" id="vehicleType_req">
											<option value="">请选择</option>
											<c:forEach items="${vt }" var="type">
											<option value="${type.wlcode }">${type.wlname }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label><span style="color: red">*</span>车长:</label>
									<div class="rz_carsolocont">
										<input type="text" value="${vehicle.vehiclelen }" name="vehiclelen" id="vehicleLen_req">米
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label>车宽:</label>
									<div class="rz_carsolocont">
										<input type="text" value="" name="vehicleWide" id="vehicleWide_req">米
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label>车高:</label>
									<div class="rz_carsolocont">
										<input type="text" value="" name="vehicleHigh" id="vehicleHigh_req">米
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label><span style="color: red">*</span>载重:</label>
									<div class="rz_carsolocont">
										<input type="text" value="${vehicle.vehicleload }" name="vehicleload" id="vehicleLoad_req">吨
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label><span style="color: red">*</span>所有人:</label>
									<div class="rz_carsolocont">
										<input type="text" value="${vehicle.vehicleowner }" name="vehicleowner" id="vehicleOwner_req">
									</div>
								</div>
								<div class="rz_carsolo">
									<label><span style="color: red">*</span>联系方式:</label>
									<div class="rz_carsolocont">
										<input type="text" value="" name="vehicleOwnerTel" id="vehicleOwnerTel_req">
									</div>
								</div>
								<div class="rz_carsolo">
									<label><span style="color: red">*</span>随车电话:</label>
									<div class="rz_carsolocont">
										<input type="text" value="${vehicle.vehiclemobile }" name="vehiclemobile" id="vehicleMobile_req">
									</div>
								</div>
							</div>
							<div class="">
								<button type="button" class="btn btnblue info_info">下一步</button>
							</div>
						</div>
						<!--tab切换的内容end-->
						
						<!--tab切换的内容 认证信息-->
						<div class="cg_tabcont info_class">
							<div class="rz_carbox">
								<div class="rz_carsolo">
									<label><span class="authType_style" style="color: red">*</span>道路运输证号:</label>
									<div class="rz_carsolocont">
										<input type="text" value="${vehicle.roadtransportno }" name="roadtransportno" id="roadTransportNo_req">
									</div>
								</div>
								<div class="rz_carsolo">
									<label><span class="authType_style" style="color: red">*</span>经营许可证号:</label>
									<div class="rz_carsolocont">
										<input type="text" value="${vehicle.taxilicenseno }" name="taxilicenseno" id="taxiLicenseNo_req">
										<!--身份证默认图片-->
										<div class="car_showimg">
											<img class="jyxkz" src="${trRoot}/tianrui/images/yyzsl.jpg">
										</div>
										<div class="img_upload mt10">
												<input id="taxiLicenseImg_req" onchange="fileupload('taxiLicenseImg_req','jyxkz')" class="file" type="file">
												<input type="hidden" name="taxiLicenseImg" id="taxiLicenseImg_req_str">
										</div>
										<h4 class="colorred">图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</h4>
									</div>
								</div>
								<div class="rz_carsolo">
									<label><span class="authType_style" style="color: red">*</span>经营许可证有效期:</label>
									<div class="rz_carsolocont">
										<input type="text" value="" name="" id="taxiLicenseTerm_req">
									</div>
								</div>
								<div class="rz_carsolo">
									<label><span style="color: red">*</span>车辆照片:</label>
									<div class="rz_carsolocont">
										<input type="hidden" name="vehicleImg" id="vehicleImg_req_str">
										<!--身份证默认图片-->
										<div class="car_showimg">
											<img class="clzp" src="${trRoot}/tianrui/images/democar.jpg">
										</div>
										<div class="img_upload mt10">
												<input id="vehicleImg_req" onchange="fileupload('vehicleImg_req','clzp')" class="file" type="file">
										</div>
										<h4 class="colorred">图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</h4>
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label><span class="authType_style" style="color: red">*</span>行驶证:</label>
									<div class="rz_carsolocont">
										<input type="hidden" name="drivingLicenseImg" id="drivingLicenseImg_req_str">
										<!--身份证默认图片-->
										<div class="car_showimg">
											<img class="xszzp" src="${trRoot}/tianrui/images/demoxsz.jpg">
										</div>
										<div class="img_upload mt10">
												<input id="drivingLicenseImg_req" onchange="fileupload('drivingLicenseImg_req','xszzp')" class="file" type="file">
										</div>
										<h4 class="colorred">图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</h4>
									</div>
								</div>
								<div class="rz_carsolo">
									<label>车辆登记证:</label>
									<div class="rz_carsolocont">
										<input type="hidden" name="vehicleGradeImg" id="vehicleGradeImg_req_str">
										<!--身份证默认图片-->
										<div class="car_showimg">
											<img class="cldjz" src="${trRoot}/tianrui/images/carinfo.jpg">
										</div>
										<div class="img_upload mt10">
												<input id="vehicleGradeImg_req" onchange="fileupload('vehicleGradeImg_req','cldjz')" class="file" type="file">
										</div>
										<h4 class="colorred">图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</h4>
									</div>
								</div>
								<div class="">
								<button type="button" class="btn btnblue vehicel_info">上一步</button>
								<button type="button" class="btn btnblue saveVehicleReg">确定</button>
								</div>
							</div>
						</div>
				<!-- end -->
					</div>
				</form>
				</div>
			</div>
		</div>
	</div>
	<!--登录内容部分end-->

	<div class="bgblack">
		<div class="container">
			<div class="foot">
				<div class="foot_logo">
					<img src="${trRoot}/tianrui/images/logobig.png">
				</div>
				<div class="foot_line mt20">
					<img title="货主android" width="130" height="130"
						src="${trRoot}/tianrui/images/huozhu.png">
				</div>
				<div class="foot_line mt20">
					<img title="android" width="130" height="130"
						src="${trRoot}/tianrui/images/erandroid.png">
				</div>
				<div class="foot_er mt20">
					<img title="IOS" width="130" height="130"
						src="${trRoot}/tianrui/images/erios.png">
				</div>
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
	<!--底部end-->
	<script type="text/javascript"
		src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
    <script type="text/javascript" src="/resources/js/new/vehicleReq/reqStep_w.js?0521"></script>
    <script type="text/javascript">
    var trRoot = "${trRoot}";
    </script>
    <script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>
    <script type="text/javascript">
		$("#file_jsz").fileinput({
			language : 'zh',
			showUpload : false,
			dropZoneEnabled : false,
			maxFileCount : 1,
//       	minImageWidth: 50, //图片的最小宽度
//	  	 	minImageHeight: 50,//图片的最小高度
//   	  	maxImageWidth: 600,//图片的最大宽度
//	 	  	maxImageHeight: 600,//图片的最大高度
			maxFileSize : 5120,//单位为kb，如果为0表示不限制文件大小
			resizeImage : true,
			showCaption : true,
			showPreview : true,
			allowedFileExtensions : [ 'jpg', 'png', 'jpeg' ]// 支持的图片类型
		}).on('fileuploaderror',function(event, data, previewId, index) {
			var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
			console.log(data);
			console.log('File upload error');
		}).on('fileerror', function(event, data) {
			console.log(data.id);
			console.log(data.index);
			console.log(data.file);
			console.log(data.reader);
			console.log(data.files);
		}).on('fileuploaded',function(event, data, previewId, index) {
			var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
			console.log('File uploaded triggered');
		});
		$('.insertType').off('click').on('click',function(){
			var text = '';
			$('#licenseType').find('input:checked').next('label').each(function(){
				text += ','+$(this).text();
			});
			$('#drivinglicensetype').html(text.substring(1,text.length));
			$("#driverCardType_req").val(text.substring(1,text.length));
			$('#car_zhunjia').modal('hide');
		});
	</script>

</body>
</html>
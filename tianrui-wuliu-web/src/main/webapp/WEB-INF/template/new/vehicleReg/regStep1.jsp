<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
					<h3>车辆注册</h3>
				</div>
				<div class="reg_zhaohui">
					<ul>
						<li class="vehicel_info"><img id="vehicel_img" src="${trRoot}/tianrui/images/zhmm11.jpg">
							<h4 id="vehicel_class" class="colorblue">车辆信息</h4></li>
						<li class="info_info"><img id="info_img" src="${trRoot}/tianrui/images/zhmm22.jpg">
							<h4 id="info_class">认证信息</h4></li>
						<li class="driver_info"><img id="driver_img" src="${trRoot}/tianrui/images/zhmm32.jpg">
							<h4 id="driver_class">驾驶员信息</h4></li>
					</ul>
				</div>
				<div class="car_rzbox">
					<div class="car_rztab">
						<ul>
							<li class="select">完全认证</li>
							<li>临时认证</li>
						</ul>
						<div class="car_rzborder"></div>
					</div>
					<div class="car_rztabbox">
						<!--tab切换的内容  车辆信息-->
						<div class="cg_tabcont vehicle_class">
							<div class="rz_carbox">
								<div class="rz_carsolo">
									<label>车牌号:</label>
									<div class="rz_carsolocont">
										<input name="vehicleNo" id="vehicleNo_req" type="text">
									</div>
								</div>
								<div class="rz_carsolo">
									<label>车辆类型:</label>
									<div class="rz_carsolocont">
										<select class="form-control w350" name="vehicleType" id="vehicleType_req">
											<option>板车</option>
										</select>
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label>车长:</label>
									<div class="rz_carsolocont">
										<input type="text" name="vehicleLen" id="vehicleLen_req">
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label>车宽:</label>
									<div class="rz_carsolocont">
										<input type="text" name="vehicleWide" id="vehicleWide_req">
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label>车高:</label>
									<div class="rz_carsolocont">
										<input type="text" name="vehicleHigh" id="vehicleHigh_req">
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label>载重:</label>
									<div class="rz_carsolocont">
										<input type="text" name="vehicleLoad" id="vehicleLoad_req">
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label>所有人:</label>
									<div class="rz_carsolocont">
										<input type="text" name="vehicleOwner" id="vehicleOwner_req">
									</div>
								</div>
								<div class="rz_carsolo">
									<label>联系方式:</label>
									<div class="rz_carsolocont">
										<input type="text" name="vehicleOwnerTel" id="vehicleOwnerTel_req">
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
									<label>道路运输证号:</label>
									<div class="rz_carsolocont">
										<input type="text" name="roadTransportNo" id="roadTransportNo_req">
									</div>
								</div>
								<div class="rz_carsolo">
									<label>经营许可证号:</label>
									<div class="rz_carsolocont">
										<input type="text" name="taxiLicenseNo" id="taxiLicenseNo_req">
										<!--身份证默认图片-->
										<div class="car_showimg">
											<img src="${trRoot}/images/sfz.png">
										</div>
										<div class="img_upload mt10">
											<form enctype="multipart/form-data">
												<input id=" " class="file" type="file">
												<input type="hidden" name="taxiLicenseImg" id="taxiLicenseImg_req">
											</form>
										</div>
										<h4 class="colorred">图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</h4>
									</div>
								</div>
								<div class="rz_carsolo">
									<label>经营许可证有效期:</label>
									<div class="rz_carsolocont">
										<input type="text" name="taxiLicenseTerm" id="taxiLicenseTerm_req">
									</div>
								</div>
								<div class="rz_carsolo">
									<label>车辆照片:</label>
									<div class="rz_carsolocont">
										<input type="hidden" name="vehicleImg" id="vehicleImg_req">
										<!--身份证默认图片-->
										<div class="car_showimg">
											<img src="${trRoot}/images/sfz.png">
										</div>
										<div class="img_upload mt10">
											<form enctype="multipart/form-data">
												<input id=" " class="file" type="file">
											</form>
										</div>
										<h4 class="colorred">图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</h4>
									</div>
								</div>
								
								<div class="rz_carsolo">
									<label>行驶证:</label>
									<div class="rz_carsolocont">
										<input type="hidden" name="drivingLicenseImg" id="drivingLicenseImg_req">
										<!--身份证默认图片-->
										<div class="car_showimg">
											<img src="${trRoot}/images/yyzsl.jpg">
										</div>
										<div class="img_upload mt10">
											<form enctype="multipart/form-data">
												<input id="file_sfz" class="file" type="file">
											</form>
										</div>
										<h4 class="colorred">图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</h4>
									</div>
								</div>
								<div class="rz_carsolo">
									<label>车辆登记证:</label>
									<div class="rz_carsolocont">
										<input type="hidden" name="vehicleGradeImg" id="vehicleGradeImg_req">
										<!--身份证默认图片-->
										<div class="car_showimg">
											<img src="${trRoot}/images/sfz.png">
										</div>
										<div class="img_upload mt10">
											<form enctype="multipart/form-data">
												<input id=" " class="file" type="file">
											</form>
										</div>
										<h4 class="colorred">图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</h4>
									</div>
								</div>
								<div class="">
								<button type="button" class="btn btnblue vehicel_info">上一步</button>
								<button type="button" class="btn btnblue driver_info">下一步</button>
								</div>
							</div>
						</div>
						<!--tab切换的内容end-->
						
						<!--tab切换的内容 司机信息-->
				<div class="cg_tabcont driver_class">
				<div class="rz_carbox">
					<div class="reg_tel">
						<label><i style="color: red">*</i>姓名:</label> 
						<input type="text" placeholder="请输入姓名"	 name="driverName" id="driverName_req">
					</div>
					<div class="reg_tel">
						<label><i style="color: red">*</i>性别:</label>
						<input type="radio" checked name="driverSex" value="xy">男
						<input type="radio" name="driverSex" value="xx">女
					</div>
					<div class="reg_tel">
						<label><i style="color: red">*</i>身份证号/驾驶证号:</label> 
						<input type="text" placeholder="请输入证件号码" name="driverIdCard" id="driverIdCard_req">
					</div>
					<div class="reg_tel">
						<label><i style="color: red">*</i>出生日期:</label>
						<input id="driverBirthDate_req" type="text" name="driverBirthDate"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择日期" readonly/>
					</div>
					<!--手机输入end-->
					<div class="reg_tel">
						<label><i style="color: red">*</i>联系电话:</label> <input type="text" name="driverLinkTel" id="driverLinkTel_req">
					</div>
					<div class="reg_tel">
						<label><i style="color: red">*</i>身份证地址:</label> 
						<input type="text" placeholder="请输入身份证地址" name="driverIdCardAddr" id="driverIdCardAddr_req">
						<p id="massage_idcardaddress"></p>
					</div>
					
					<div class="reg_tel">
						<label><i style="color: red">*</i>初次领证日期:</label>
						<input id="driverCardFirstlicens_req" name="driverCardFirstlicens" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择日期" readonly/>
						<p id="massage_firstlicens"></p>
					</div>
					
					<div class="reg_tel">
						<label><i style="color: red">*</i>发证机关:</label> 
						<input type="text" placeholder="请输入驾驶证发证机关" id="driverCardLicenceorg_req" name="driverCardLicenceorg">
						<p id="massage_licenceorg"></p>
					</div>
					
					<div class="reg_tel">
						<label><i style="color: red">*</i>驾驶证注册日期:</label> 
						<input id="driverCardRegDate_req" type="text" name="driverCardRegDate"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择日期" readonly/>
						<p id="massage_starttime"></p>
					</div>
					
					<div class="reg_tel">
						<label><i style="color: red">*</i>有效年限:</label> 
						<input type="text" placeholder="请输入有效年限" id="driverCardUsefullife_req" name="driverCardUsefullife">
						<p id="massage_usefullife"></p>
					</div>
					
					<div class="reg_tel">
						<label><i style="color: red">*</i>准驾车型：</label> 
						<button class="btn btn-default" data-toggle="modal"
									data-target="#car_zhunjia">请选择</button>
									<span id="drivinglicensetype"></span>
					</div>
					<div class="rz_personline">
						<label><i style="color: red">*</i>驾驶证:</label>
						<div class="rz_persontab">
							<div class="samples">
								<img class="jsz" src="${trRoot}/tianrui/images/jz.png">
							</div>
							<div class="img_upload">
								<input id="driverCardImg_req" onchange="fileupload('driverCardImg_req','jsz')" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
								<input type="hidden" id="driverCardImg_req_str" value="driverCardImg" >
							</div>
						</div>
					</div>
					<div class="rz_personline">
						<label><i style="color: red">*</i>身份证正面:</label>
						<div class="rz_persontab">
							<div class="samples">
								<img class="sfz_A" src="${trRoot}/tianrui/images/sfz.png">
							</div>
							<div class="img_upload">
								<input id="driverIdCard_A_req" onchange="fileupload('driverIdCard_A_req','sfz_A')" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
								<input type="hidden" id="driverIdCard_A_req_str" name="driverIdCard_A" value="" >
							</div>
						</div>
					</div>
					<div class="rz_personline">
						<label><i style="color: red">*</i>身份证反面:</label>
						<div class="rz_persontab">
							<div class="samples">
								<img class="sfz_B" style="width: 226px;height: 132px" src="${trRoot}/tianrui/images/sfz_b.jpg">
							</div>
							<div class="img_upload">
								<input id="driverIdCard_B_req" onchange="fileupload('driverIdCard_B_req','sfz_B')" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
								<input type="hidden" id="driverIdCard_B_req_str" name="driverIdCard_B" value="" >
							</div>
						</div>
					</div>
					
					<div class="">
					<button type="button" class="btn btnblue info_info">上一步</button>
					<button type="submit" class="btn btnblue">确定</button>
					</div>
				</div>
				</div>
				<!-- end -->
					</div>
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
    <script type="text/javascript" src="/resources/js/new/vehicleReq/reqStep1.js?0519"></script>
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
			$('#car_zhunjia').modal('hide');
		});
	</script>

</body>
</html>
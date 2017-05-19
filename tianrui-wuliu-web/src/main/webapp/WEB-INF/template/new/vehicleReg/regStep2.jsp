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
<title>天瑞物流_认证信息</title>
<link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/tr-media.css" rel="stylesheet">
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
						<li><a href="/common/vehicleReg/regStep1"><img src="${trRoot}/tianrui/images/zhmm12.jpg">
							<h4>车辆信息</h4></a></li>
						<li><a href="/common/vehicleReg/regStep2"><img src="${trRoot}/tianrui/images/zhmm21.jpg">
							<h4 class="colorblue">认证信息</h4></a></li>
						<li><a href="/common/vehicleReg/regStep3"><img src="${trRoot}/tianrui/images/zhmm32.jpg">
							<h4>驾驶员信息</h4></a></li>
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
						<!--tab切换的内容 认证信息-->
						<div class="cg_tabcont info_class">
							<div class="rz_carbox">
								<div class="rz_carsolo">
									<label>道路运输证号:</label>
									<div class="rz_carsolocont">
										<input type="text">
										<!--身份证默认图片-->
									</div>
								</div>
								<div class="rz_carsolo">
									<label>经营许可证号:</label>
									<div class="rz_carsolocont">
										<input type="text">
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
									<label>经营许可证有效期:</label>
									<div class="rz_carsolocont">
										<input type="text">
									</div>
								</div>
								<div class="rz_carsolo">
									<label>车辆照片:</label>
									<div class="rz_carsolocont">
										<input type="text">
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
										<input type="text">
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
										<input type="text">
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
								<div class="car_btnnext">
								<button type="submit" class="btn btnblue">下一步</button>
							</div>
							</div>
						</div>
						<!--tab切换的内容end-->
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
	<!--底部end-->
	<script type="text/javascript"
		src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>

</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天瑞物流</title>
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
					<label id="header_welcome">欢迎来到天瑞物流 </label> 
					<label id="header_loginHref"> <a href="/publicMember/loginPage">请登录</a></label>
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
						<li><img src="${trRoot}/tianrui/images/zhmm11.jpg">
							<h4 class="colorblue">车辆信息</h4></li>
						<li><img src="${trRoot}/tianrui/images/zhmm22.jpg">
							<h4>认证信息</h4></li>
						<li><img src="${trRoot}/tianrui/images/zhmm32.jpg">
							<h4>驾驶员信息</h4></li>
					</ul>
				</div>
				<div class="car_box">
					<div class="reg_tel">
						<label><i class="colorred">*</i>车牌号：</label> <input type="text">
						<button class="btn btnblue">获取车牌</button>
						<p>不能为空</p>
					</div>
					<!--手机输入end-->
					<div class="reg_tel">
						<label><i class="colorred">*</i>随车电话：</label> <input type="text"
							placeholder="请输入手机号">
						<p>不能为空</p>
					</div>
					<div class="reg_tel">
						<label><i class="colorred">*</i>车辆类型：</label> <select
							class="form-control w350">
							<option>111</option>
							<option>111</option>
							<option>111</option>
						</select>
						<div class="clear"></div>
						<p>不能为空</p>
					</div>
					<div class="reg_tel">
						<label><i class="colorred">*</i>车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长：</label>
						<input type="text" placeholder=""> 米
						<p>好好好</p>
					</div>
					<div class="reg_tel">
						<label><i class="colorred">*</i>载&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重：</label>
						<input type="text" placeholder=""> 吨
					</div>
					<div class="reg_tel">
						<label><i class="colorred">*</i>所有人姓名：</label> <input type="text">
					</div>
					<div class="reg_tel">
						<label><i class="colorred">*</i>联系电话：</label> <input type="text">
					</div>
					<div class="car_btnnext">
						<button type="submit" class="btn btnblue">下一步</button>
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
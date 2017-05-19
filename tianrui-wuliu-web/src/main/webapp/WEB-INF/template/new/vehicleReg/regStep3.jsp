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
						<li><a href="/common/vehicleReg/regStep1"><img src="${trRoot}/tianrui/images/zhmm12.jpg">
							<h4 >车辆信息</h4></a></li>
						<li><a href="/common/vehicleReg/regStep2"><img src="${trRoot}/tianrui/images/zhmm22.jpg">
							<h4>认证信息</h4></a></li>
						<li><a href="/common/vehicleReg/regStep3"><img src="${trRoot}/tianrui/images/zhmm31.jpg">
							<h4 class="colorblue">驾驶员信息</h4></a></li>
					</ul>
				</div>
				<!--tab切换的内容 司机信息-->
				<div class="cg_tabcont driver_class">
				<div class="car_box">
					<div class="reg_tel">
						<label><i style="color: red">*</i>姓名:</label> <input type="text" placeholder="请输入姓名"
							id="perAuthen_name">
						<p id="message_perAuthenName"></p>
					</div>
					<div class="reg_tel">
						<label><i style="color: red">*</i>性别:</label>
						<input type="radio" checked name="sex" value="xy">男
						<input type="radio" name="sex" value="xx">女
						<p id=""></p>
					</div>
					<div class="reg_tel">
						<label><i style="color: red">*</i>身份证号/驾驶证号:</label> <input type="text"
							placeholder="请输入证件号码" id="perAuthen_id">
						<p id="massage_perAuthen_id"></p>
					</div>
					<div class="reg_tel">
						<label><i style="color: red">*</i>出生日期:</label>
						<input id="per_birthday" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择日期" readonly/>
						<p id="massage_birthday"></p>
					</div>
					<!--手机输入end-->
					<div class="reg_tel">
						<label><i style="color: red">*</i>联系电话:</label> <input type="text"
							  id="perAuthen_tel">
						<p id="message_perAuthenTel"></p>
					</div>
					<div class="reg_tel">
						<label><i style="color: red">*</i>身份证地址:</label> <input type="text"
							placeholder="请输入身份证地址" id="per_idcardaddress">
						<p id="massage_idcardaddress"></p>
					</div>
					
					<div class="reg_tel">
						<label><i style="color: red">*</i>初次领证日期:</label>
						<input id="per_firstlicens" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择日期" readonly/>
						<p id="massage_firstlicens"></p>
					</div>
					
					<div class="reg_tel">
						<label><i style="color: red">*</i>发证机关:</label> <input type="text"
							placeholder="请输入驾驶证发证机关" id="per_licenceorg">
						<p id="massage_licenceorg"></p>
					</div>
					
					<div class="reg_tel">
						<label><i style="color: red">*</i>驾驶证注册日期:</label> 
						<input id="per_starttime" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择日期" readonly/>
						<p id="massage_starttime"></p>
					</div>
					
					<div class="reg_tel">
						<label><i style="color: red">*</i>有效年限:</label> <input type="text"
							placeholder="请输入有效年限" id="per_usefullife">
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
								<input id="file_jsz" onchange="fileupload('file_jsz','jsz')" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
								<input type="hidden" id="file_jsz_str" value="" >
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
								<input id="file_shenfenzheng_A" onchange="fileupload('file_shenfenzheng_A','sfz_A')" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
								<input type="hidden" id="file_shenfenzheng_A_str" value="" >
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
								<input id="file_shenfenzheng_B" onchange="fileupload('file_shenfenzheng_B','sfz_B')" class="file" type="file"> <span
									class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
								<input type="hidden" id="file_shenfenzheng_B_str" value="" >
							</div>
						</div>
					</div>
					<div class="car_btnnext">
						<button type="submit" class="btn btnblue">下一步</button>
					</div>
				</div>
				</div>
				<!-- end -->
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
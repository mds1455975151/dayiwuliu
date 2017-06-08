<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>添加银行卡</title>
	    <meta name="keywords" content=" 天瑞"/>
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link href="${trRoot}/tianrui/css/imgcut.css" rel="stylesheet">
		<link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="${trRoot}/tianrui/js/My97DatePicker/WdatePicker.js"></script>
	    
    
	<!-- 引用公共header部分 -->
	<jsp:include page="../common/member/header_busi.jsp"></jsp:include>
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
			        <jsp:include page="../common/member/left_busi.jsp"></jsp:include>
			        <!--个人中心左侧end-->
		            <!--个人中心右侧begin-->
		            <div class="rz_right">
		             	<div class=" bgblue">
							<h2>添加银行卡</h2>
						</div>
						<!-- 个人车辆begin -->
						<form id="member_bank">
						<div class="car_box">
							<div class="reg_tel">
								<label> <span style="color: red">*</span>银行卡账户:</label> 
								<input type="text" id="bankcard_req" name="bankcard"> 
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>持卡人名称:</label> 
								<input type="text" id="idname_req" name="idname">
								<p id=""></p>
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>开户行名称:</label> 
								<input type="text" id="bankname_req" name="bankname">
								<p id=""></p>
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>银行卡照片:</label> 
								<div class="rz_persontab">
										<input type="hidden" name="bankimg" id="bankimg_req_str">
										<!--身份证默认图片-->
										<div class="car_showimg">
											<img class="clzp" style="width: 350px" src="${trRoot}/tianrui/images/timg.jpg">
										</div>
										<div class="img_upload mt10">
												<input id="bankimg_req" onchange="fileupload('bankimg_req','clzp')" class="file" type="file">
										</div>
										<h4 class="colorred">图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</h4>
									</div>
							</div>
							<div class="car_photo">
								<div class="car_addbtn">
									<button type="button" class="btn btnyello" id="member_bank_add">添加</button>
								</div>
							</div>
						</div>
						</form>
					</div>
		            <!--个人中心右侧end-->
		        </div>
		    </div>
		</div>
		<!--内容部分end-->
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
		<!-- 引用公共footer部分 -->
		<jsp:include page="../common/member/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
		<script type="text/javascript"> var trRoot = "${trRoot}";</script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>
		<script type="text/javascript" src="/resources/js/bank/saveBankCard.js"></script>
	</body>
</html>

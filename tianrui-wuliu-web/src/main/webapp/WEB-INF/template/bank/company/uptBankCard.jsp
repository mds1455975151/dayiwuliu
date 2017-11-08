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
	    <title>银行卡重新认证</title>
	    <meta name="keywords" content=" 天瑞"/>
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link href="${trRoot}/tianrui/css/imgcut.css" rel="stylesheet">
	    <link href="${trRoot}/tianrui/css/select2.css" rel="stylesheet">
		<link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="${trRoot}/tianrui/js/My97DatePicker/WdatePicker.js"></script>
	    
    
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
							<h2>重新认证</h2>
						</div>
						<!-- 个人车辆begin -->
						<form id="member_bank">
						<div class="car_box">
							<div class="reg_tel">
								<label> <span style="color: red">*</span>企业银行账户:</label> 
								<input id="bankId" type="hidden" name="id" value="${bankid }"> 
								<input type="text" id="bankcard_req" value="${bankcard }" name="bankcard"> 
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>银行名称:</label> 
								<!-- 
								<input type="text" id="bankname_req" name="bankname" readonly="readonly"> 
								 -->
								<div class="select_classs">
									<select style="width: 350px" class="form-control select2" id="selectBankName">
									
									</select>
								</div>
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>企业账户名:</label> 
								<input type="text" id="idname_req" readonly="readonly" name="idname" value="${info.companyname }">
								<p id=""></p>
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>营业执照号:</label> 
								<input type="text" id="idcard_req" readonly="readonly" name="idcard" value="${info.companycode }">
								<p id=""></p>
							</div>
							<div class="reg_tel">
								<input type="hidden" id="showType" value="2">
								<label> <span style="color: red">*</span>开户行名称:</label> 
								<div class="text_class">
									<input type="text" id="desc1_text"> 
									<button type="button" class="btn btnblue text_but">选择</button>
								</div>
								<input type="hidden" id="desc1_req" name="desc1"> 
								<div class="select_class">
									<select style="width: 350px" class="form-control select2" id="desc1_select">
										<option>请选择</option>
										
									</select>
									<button type="button" class="btn btnblue text_sele">编辑</button>
								</div>
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>银行开户许可证:</label> 
								<div class="rz_persontab">
										<input type="hidden" name="bankimg" id="bankimg_req_str">
										<!--身份证默认图片-->
										<div class="car_showimg">
											<img class="clzp" style="width: 350px" src="${trRoot}/tianrui/images/bank_khxkz.jpg">
										</div>
										<div class="img_upload mt10">
												<input id="bankimg_req" onchange="fileupload('bankimg_req','clzp')" class="file" type="file">
										</div>
										<h4 class="colorred">图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</h4>
									</div>
							</div>
							<div class="car_photo">
								<div class="car_addbtn">
									<button type="button" class="btn btnyello" id="member_bank_add">重新认证</button>
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
		<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
		<script type="text/javascript"> var trRoot = "${trRoot}";</script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/select2.js"></script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>
		<script type="text/javascript" src="/resources/js/bank/company/uptBankCard.js?1108"></script>
		<script type="text/javascript">
		$('#venderBank').addClass("selected");
		$("#bankimg_req")
		.fileinput({
			language : 'zh',
			showUpload : false,
			dropZoneEnabled : false,
			maxFileCount : 1,
			//      minImageWidth: 50, //图片的最小宽度
			//	    minImageHeight: 50,//图片的最小高度
			//      maxImageWidth: 600,//图片的最大宽度
			//	    maxImageHeight: 600,//图片的最大高度
			maxFileSize : 5000,//单位为kb，如果为0表示不限制文件大小
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
				})
		.on('fileerror', function(event, data) {
		})
		.on(
				'fileuploaded',
				function(event, data, previewId, index) {
					var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
				});
		
		index();
		</script>
	</body>
</html>

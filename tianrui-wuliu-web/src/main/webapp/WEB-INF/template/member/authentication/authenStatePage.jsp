<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天瑞物流平台-司机认证</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="${trRoot}/tianrui/css/imgcut.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
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
					<h2>司机认证</h2>
				</div>
				<div class="rz_box">
					<div class="reg_tel">
						<label><i>*</i>姓名:</label> <input type="text" placeholder="请输入姓名"
							id="perAuthen_name">
						<p id="message_perAuthenName"></p>
					</div>
					<!--手机输入end-->
					<div class="reg_tel">
						<label><i>*</i>证件号码:</label> <input type="text"
							placeholder="请输入证件号码" id="perAuthen_id">
						<p id="message_perAuthenId"></p>
					</div>
					<div class="reg_tel">
						<label><i>*</i>联系电话:</label> <input type="text"
							disabled="disabled" placeholder="系统自动获取" id="perAuthen_tel">
						<p id="message_perAuthenTel"></p>
					</div>
					<div class="rz_personline">
						<label><i class="coryel">*</i>驾驶证:</label>
						<div class="rz_persontab">
							<div class="samples">
								<img src="${trRoot}/tianrui/images/jz.png">
							</div>
							<div class="img_upload">
								<input id="file_jsz" class="file" type="file"> <span
									class="annotation">* 图片大小不超过2M，限上传1张，只支持JPG、JPEG、PNG格式</span>
							</div>
						</div>
					</div>
					<div class="rz_combtn ml84" style="margin-top: 10px;">
						<input type="checkbox" checked="checked" id="perAuthen_checkbox"><label>我已经阅读并同意<a
							target="_blank" id="perAuthen_protocol">《天瑞物流平台服务协议》</a></label>
						<button type="submit" class="btn btn-block" id="perAuthen_button">申请认证</button>
					</div>
				</div>
				<!--个人中心右侧end-->
			</div>
		</div>
	</div>
	<!--内容部分end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="/resources/js/common/member/header_busi.js"></script>
	<script type="text/javascript" src="/resources/js/member/authentication/authenStatePage.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
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
			maxFileSize : 5000,//单位为kb，如果为0表示不限制文件大小
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
	</script>
	</body>
</html>

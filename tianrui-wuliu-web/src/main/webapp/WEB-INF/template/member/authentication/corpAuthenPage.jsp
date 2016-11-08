<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>天瑞物流平台-企业认证</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${trRoot}/tianrui/css/imgcut.css"  rel="stylesheet">
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
               <h2>企业认证</h2>
           </div>
           <div class="rz_box">
               <div class="reg_tel">
                   <label><i>*</i>企业名称:</label>
                   <input type="text" placeholder="请输入企业名称" id="corpAuthen_name">
                   <p id="message_corpAuthenName"></p>
               </div>
               <!--手机输入end-->
               <div class="reg_tel">
                   <label><i>*</i>公司地址:</label>
                   <input type="text" placeholder="请输入公司地址" id="corpAuthen_address">
                   <p id="message_corpAuthenAddress"></p>
               </div>
               <div class="reg_tel">
                   <label><i>*</i>公司联系人:</label>
                   <input type="text" placeholder="请输入公司联系人" id="corpAuthen_linkman">
                   <p id="message_corpAuthenLinkman"></p>
               </div>
               <div class="reg_tel">
                   <label><i>*</i>联系人电话:</label>
                   <input type="text" placeholder="请输入联系人电话" id="corpAuthen_tel">
                   <p id="message_corpAuthenTel"></p>
               </div>
               <div class="reg_tel">
                   <label><i>*</i>营业执照号:</label>
                   <input type="text" placeholder="请输入营业执照号" id="corpAuthen_code">
                   <p id="message_corpAuthencode"></p>
               </div>
               <div class="rz_personline">
                   <label><i>*</i>营业执照:</label>
                   <div class="rz_persontab">
						<div class="samples">
							<img src="${trRoot}/tianrui/images/zhizhao.png">
						</div>
						<div class="img_upload mt10">
							<input id="file_yyzz" class="file" type="file">
							<span class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
						</div>
					</div>
               </div>
               
               <div class="reg_tel">
					<label>道路运输经营许可证：</label> <input type="text" id="rtblno">
					<div class="rz_persontab">
						<div class="samples">
							<img class="xkz" style="max-height: 240px;" src="${trRoot}/tianrui/images/yyz.jpg">
						</div>
						<div class="img_upload mt10">
							<input id="rtblimg" class="file" type="file"> <span
								class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
						</div>
					</div>
				</div>  
               <!--企业认证模块-->
               <div class="rz_combtn ml84" style="margin-top: 10px;">
	               <input type="checkbox" checked="checked" id="corpAuthen_checkbox"><label>我已经阅读并同意<a target="_blank" id="corpAuthen_protocol">《天瑞物流平台服务协议》</a></label>
	               <button type="submit" class="btn btn-block" id="corpAuthen_button">申请认证</button>
           	   </div>
               <!--企业认证模块end-->
          </div>
          <!--个人中心右侧end-->
       </div>
	</div>
</div>
	<!--内容部分end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
	<script type="text/javascript" src="/resources/js/member/authentication/corpAuthenPage.js?2" ></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>
	<script type="text/javascript">
		$("#file_yyzz").fileinput({
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
		}).on('fileerror', function(event, data) {
		}).on('fileuploaded',function(event, data, previewId, index) {
			var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
		});
	</script>
</body>
</html>

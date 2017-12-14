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
<title>大易物流平台-加盟协议</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="${trRoot}/tianrui//css/imgcut.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<style>
        .textBox{
            border: 1px solid #ccc;
            font-size: 14px;
            font-family: "微软雅黑";
            line-height: 40px;
            margin-top: 10px;
        }
</style>
<script language="javascript" type="text/javascript" src="${trRoot}/tianrui/js/My97DatePicker/WdatePicker.js"></script>

<!-- 引用公共header部分 -->
<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
<!--内容部分begin-->
<div class="bghui">
	<div class="container">
		<div class="row">
            <div class="textBox">
            <div style="width:95%;margin:0 auto;">
                <p style="text-align: center;font-size: 24px;">车辆加盟协议</p>
                <p style="color: deepskyblue">甲方：${user.realName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乙方：中原大易科技有限公司</p>
                <p>乙方系国家批准的可经营无车承运人业务的高科技企业，其平台上有大宗货源信息，甲方系道路运输户，为了解决甲方货源问题，经双方友好协商特签定本协议：</p>
                <p>一、甲方自愿加盟乙方无车承运人平台，同意将车辆在乙方平台上注册；乙方同意甲方及其车辆的加盟，并负责将自己平台上的货源推荐给甲方，并向甲方提供其他增值服务，甲方服从乙方平台管理。</p>
                <p>二、该车辆所有权为甲方所有，乙方无权对甲方的车辆进行处置。</p>
                <p>三、乙方不收甲方任何加盟费用，车辆的经营活动，由甲方自主经营，自负盈亏，并遵守国家的的交规法规。在营运中所发生的违法行为及一切交通事故，所有费用及责任由甲方全部承担，乙方不承担任何经济责任及连带责任。甲方因交通肇事，刑事犯罪，严重违法违纪，自行承担事故处理费和有关部门罚款及赔偿款。<br></p>
                <p>四、甲方加盟期间，甲方承担运输劳务，乙方承担甲方为乙方运输产生的油料、过路过桥、维修费用【需经乙方确认】，采取实报实销的方式，其余经营费用（包含但不限于保险费，年检费等）均由甲方自行承担。</p>
                <p> 五、乙方对甲方做到业务服务、发票服务、货源服务及金融服务，做到全程透明化，信息化，节约化。</p>
                <p>六、甲方车辆使用不具备运营条件时，或甲方不合理使用车辆时候，乙方有权要求予以整改，甲方拒不整改的，乙方有权停止办理车辆一切服务，并有权单方面解除加盟关系。</p>
                <p>七、乙方不承担车辆使用过程中可能出现的任何法律后果或经济损失。</p>
                <p>八、加盟期限内，甲方如需转让，转租或抵押车辆，需书面通知乙方。</p>
                <p>九、如因本协议发生争议，纠纷双方协商解决，无法协商双方均应向乙方所在地法院提起诉讼。</p>
                <p>十、本协议经甲方车辆在乙方平台注册确认后即生效。</p>
                </div>
            </div>
        </div>
	</div>
</div>
<!--内容部分end-->
<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript">
	var trRoot = "${trRoot}/tianrui/images/";
</script>
<script type="text/javascript"
	src="/resources/js/common/member/header_busi.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript"
	src="/resources/js/member/vehicle/addVehiclePage.js?09.15"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
<script type="text/javascript"
	src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>

<script type="text/javascript">
	var myDate = new Date(); 
	myDate.setDate(myDate.getDate()+1);
	$("#starttimeStr").val(myDate.toLocaleDateString());
	
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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>运价确认</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css" rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css" rel="stylesheet">
    <link href="${trRoot}/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${stylesRoot }/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="Shortcut Icon" href="${imagesRoot}/favicon.ico" type="image/x-icon">
    <link href="${stylesRoot}/imgcut.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
</head>
<body>

<div class="container-fluid">
	<!--公共头部begin-->
    <jsp:include page="../../../common/header.jsp" flush="false"></jsp:include>
    <!--后台左侧布局end-->
    <!--后台右侧布局begin-->
    <div class="col-md-10 ">
        <div class="ht_content">
            <div id="content-header">
                <h3>运价确认</h3>
            </div>
            <!--查询框begin-->
            <div class="row">
                <div class="col-md-12">
                    <div class="contuser_search">
                        <div class="ht_div">
                            <label>运单号：</label>
                            <input type="text" id="code" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>发票类型：</label>
                            <input type="text" id="invoiceName" placeholder=" ">
                        </div>
                        <div class="ht_divbtn">
                            <button class="btn btnblue " onclick="searchMember();" type="submit">搜索</button>
                        </div>
                    </div>
                </div>
            </div>
            <!--查询框end-->
            <div class="row mt15">
                <div class="col-md-12">
                    <div class="content-user">
                        <div class="content-tou">
                            <button data-toggle="modal" data-target="#addModal"><i
                                    class="glyphicon glyphicon-plus"></i><span>新增</span></button>
                        </div>
                        <!--用户表格begin-->
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>运单号</th>
                                <th>发票类型</th>
                                <th>货物名称</th>
                                <th>支付对象</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="innerHml">
                            </tbody>
                        </table>
                        <!--用户表格end-->
                    </div>
                </div>
            </div>
           <!-- 分页部分  开始-->
			 <div class="row pr20 fr">
				<%@include file="../../../common/pagination.jsp" %>
	         </div>
			<!-- 分页部分 结束 -->
        </div>
    </div>
    <!--后台右侧布局end-->
</div>
<!--后台整体布局end-->
</div>
<!--侧边栏end-->
</div>

<div class="modal fade" id="audit" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">运价确认</h4>
				</div>
				<div class="modal-body">
					<div class="juesemodal payMoney" style="margin-bottom: 0px;">
						<div class="model_width">
							<label>账单总额：</label>
							<input type="text" id="billTotalPrice" readonly="readonly">
						</div>
						<div class="model_width">
							<label>应付金额：</label>
							<input type="text" id="amountPayable" readonly="readonly">
						</div>
						<div class="model_width">
							<label>油卡：</label>
							<input type="text" class="total_price_count" id="deductOilCard">
						</div>
						<div class="model_width">
							<label>扣重扣杂：</label>
							<input type="text" class="total_price_count" id="deductWeightMisc">
						</div>
						<div class="model_width">
							<label>扣款：</label>
							<input type="text" class="total_price_count" id="deductMoney">
						</div>
						<div class="model_width">
							<label>其他款项：</label>
							<input type="text" class="total_price_count" id="deductOther">
							<input type="hidden" id="payId">
						</div>
		                <div class="reg_tel" style="width: 450px;margin: 0 auto;">
		                    <label>附件：</label>
		                    <span id="appendix"></span>
		                    <div class="img_upload">
								<input id="file_fj" onchange="fileUpload('fj')" name="file" class="file" type="file">
								<input type="hidden" id="pay_fj">
								<span class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
							</div>
		                </div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="auditCommit">确定</button>
					<button type="button" class="btn btn-default closeAudit" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
<!--删除end-->
<%@include file="../../../common/footer.jsp" %>
<script type="text/javascript">
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="<%=basePath%>/resources/js/payInvoice/pay/file_payDetail.js?07030" ></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput_locale_zh.js"></script>
<script type="text/javascript">
		$("#file_fj").fileinput({
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
	</script>
</body>
</html>
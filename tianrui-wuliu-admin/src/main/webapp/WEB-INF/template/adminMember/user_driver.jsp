<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>司机档案管理</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css"  rel="stylesheet">
    <link href="${stylesRoot }/imgcut.css" rel="stylesheet">
    <link href="${trRoot}/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
</head>
<body>

<div class="container-fluid">
	<input type="hidden" id="recPageNo" value="${pageNo }">
    <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
                <!--后台右侧布局begin-->
            <div class="col-md-10 ">
                <div class="ht_content">
                    <div id="content-header">
                        <h3>司机管理</h3>
                    </div>
                    <!--查询框begin-->
                    <div class="row">
                            <div class="col-md-12">
                                <div class="contuser_search">
                                    <div class="ht_div">
                                        <label>司机姓名：</label>
                                        <input type="text" onchange="firstPage();" id="username" placeholder=" ">
                                    </div>
                                    <div class="ht_div">
                                        <label>司机账号：</label>
                                        <input type="text" onchange="firstPage();" id="cellphone" placeholder=" ">
                                    </div>
                                    <div class="ht_div">
                                        <label>档案状态：</label>
                                        <select id="status" onchange="firstPage();" class="form-control">
                                            <option value="">请选择</option>
                                            <option value="1">启用</option>
                                            <option value="0">停用</option>
                                        </select>
                                    </div>
                                    <div class="ht_div">
                                        <label>认证状态:</label>
                                        <select class="form-control" onchange="firstPage();" id="perCheckStatus">
                                            <option value="">请选择</option>
                                            <option value="2">认证中</option>
                                            <option value="3">认证失败</option>
                                            <option value="1">认证成功</option>
                                        </select>
                                    </div>
                                    <div class="ht_div">
                                        <label>NC审核状态 :</label>
                                        <select class="form-control" onchange="firstPage();" id="ncStatus">
                                            <option value="">请选择</option>
                                            <option value="1">供应商不存在</option>
                                            <option value="2">未审核</option>
                                            <option value="3">审核未通过</option>
                                            <option value="4">审核中</option>
                                            <option value="5">审核通过，但组织未分配</option>
                                            <option value="6">审核通过，且组织已分配</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="contuser_search">
                                    <div class="ht_div">
                                        <label>注册时间：</label>
                                        <input type="text" id="restimefor" onchange="firstPage();"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" class="Wdate" style="width:160px"/>
                                        <i>-</i>
                                        <input type="text" id="restimeend" onchange="firstPage();" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" class="Wdate" style="width:160px"/>
                                    </div>
                                    <div class="ht_div">
                                        <label>认证时间：</label>
                                        <input type="text" id="subtimefor" onchange="firstPage();" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" class="Wdate" style="width:160px"/>
                                        <i>-</i>
                                        <input type="text" id="subtimeend" onchange="firstPage();" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" class="Wdate" style="width:160px"/>
                                    </div>
                                    <div class="ht_divbtn">
                                        <button class="btn btnblue " onclick="driverSearch();" type="button">搜索</button>
                                        <button class="btn btngreen" onclick="clearSearch();" type="submit">重置</button>
                                    </div>
                                </div>
                        </div>
                    </div>
                    <!--查询框end-->
                    <div class="row mt15">
                         <div class="col-md-12">
                             <div class="content-user">
                                <!-- 
                                 <div class="content-tou">
                                     <a href=" ">
                                         <button><i class="iconfont icon-daoru06"></i>导入</button>
                                     </a>
                                     <button><i class="iconfont icon-daochu1"></i><span>导出</span></button>
                                 </div>
                                 -->
                                 <!--用户表格begin-->
                                 <table id="sample2" class="table table-bordered"   data-options="">
                                     <thead>
                                     <tr>
                                         <th>序号</th>
                                         <th>司机账号 </th>
                                         <th>安联账号 </th>
                                         <th>司机姓名 </th>
                                         <th>联系方式</th>
                                         <th>驾驶证号 </th>
                                         <th>认证状态</th>
                                         <th>推送状态 </th>
                                         <th>NC审核状态 </th>
                                         <th>注册时间 </th>
                                         <th>认证时间 </th>
                                         <th>操作 </th>
                                     </tr>
                                     </thead>
                                     <tbody id="innerHml">
                                     </tbody>
                                 </table>
                                 <!--用户表格end-->
                                 <!-- 分页部分  开始-->
						            <div class="row pr20 fr">
										<%@include file="../common/pagination.jsp" %>
						            </div>
						         <!-- 分页部分 结束 -->
                             </div>
                         </div>
                    </div>
                </div>
            </div>
                <!--后台右侧布局end-->
            </div>
            <!--后台整体布局end-->
    </div>
    <!--侧边栏end-->
</div>

<!--停用begin-->
<div class="modal fade" id="tingyong" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >提示</h4>
            </div>
            <div class="modal-body">
                <h4><span id="satus"></span></h4>
                <input type="hidden" id="memid" value="">
                <input type="hidden" id="statustype" value="">
            </div>
            <div class="modal-footer">
                <button type="button" onclick="changeType();" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--停用end-->
<!--修改照片begin-->
<div class="modal fade" id="againPice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">修改照片</h4>
            </div>
	            <div class="modal-body">
	                <span id="showcode">
			     		证书编号：<input id="uptmembercode" type="text">
			        </span>
	                <div class="reg_tel">
	                    <label></label>
	                    <div class="img_upload">
							<input id="file_yyzz" name="file" class="file" type="file">
							<input type="hidden" name="id" id="uptmemberid">
							<input type="hidden" name="type" id="uptmembertype">
							<span class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
						</div>
	                </div>
	            </div>
            <div class="modal-footer">
                <button type="button" onclick="uploadfile()" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--修改照片end-->
<!--查看详情begin-->
<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >司机信息详情</h4>
            </div>
            <div class="modal-body" id="detailid" style=" ">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--查看详情end-->
<!--安联认证begin-->
<div class="modal fade" id="anlian" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >安联认证</h4>
            </div>
            <form id="anlian_form">
            <div class="modal-body" style=" ">
            	<div class="usermodal userlabel3">
	            	<div class="model_width">
						<label>司机账号：</label>
						<input type="text" id="anlian_phone" readonly="readonly">
						<input type="hidden" id="anlian_id" name="id">
					</div>
					<div class="model_width">
						<label>司机性别：</label>
						<input type="radio" name="sex" checked="checked" value="xx"> 女
						<input type="radio" name="sex" value="xy"> 男
					</div>
					<div class="model_width">
						<label>出生日期：</label>
						<span>
						<input type="text" placeholder="yyyy-MM-dd" name="birthday" id="anlian_birthday">
						</span>
					</div>
					<div class="model_width">
						<label>身份证地址：</label>
						<input type="text" name="idcardaddress" id="anlian_address">
					</div>
					<div class="model_width">
						<label>初次领证日期：</label>
						<input type="text" placeholder="yyyy-MM-dd" name="firstlicens" id="anlian_firstlicens">
					</div>
					<div class="model_width" >
						<label>发证机关：</label>
						<input type="text" name="licenceorg" id="anlian_licenceorg">
					</div>
					<div class="model_width">
						<label>有效年限：</label>
						<input type="text" name="usefullife" id="anlian_usefullife">
					</div>
					<div class="model_width">
						<label>驾驶证注册日期：</label>
						<input type="text" placeholder="yyyy-MM-dd" name="starttime" id="anlian_starttime">
					</div>
					<div class="model_width">
						<label>准驾车型：</label>
						<input type="text" name="licenseType" id="anlian_licenseType">
					</div>
				</div>
            </div>
            </form>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary anlian_renzheng">确定</button>
                <button type="button" id="alhide" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--安联认证end-->
<%@include file="../common/footer.jsp" %>
<script type="text/javascript">
    $(function(){
        //表格列宽度调整
        $("table").resizableColumns({});

        // 点击修改头像按钮，图片裁剪框显示出来
        $(".tx_contr").on('click', function () {
            $(".acc_touxiang").show();
        });
        // 修改头像的收起按钮
        $(".tx_shouqi").on('click', function () {
            $(".acc_touxiang").hide();
        });
        // 图片裁切块的大小自定义，margin-top是height一半，margin-left是width一半
        var thumb = $(".imgBox_file .thumbBox");
        thumb.height(150);
        thumb.width(150);
        thumb.css({ "margin-top": -75, "margin-left": -75 });
        // 给cropbox.js传参
        var options =
        {
            thumbBox: '.thumbBox',
            spinner: '.spinner',
            imgSrc: ''
        };
        var cropper = $('.imgBox_file').cropbox(options);
        // 文件上传按钮操作
        $('#upload-file').on('change', function () {
            var reader = new FileReader();
            reader.onload = function (e) {
                options.imgSrc = e.target.result;
                cropper = $('.imgBox_file').cropbox(options);
            };
            reader.readAsDataURL(this.files[0]);
            this.files = [];
        });
        $('#upload-file2').on('change', function () {
            var reader = new FileReader();
            reader.onload = function (e) {
                options.imgSrc = e.target.result;
                cropper = $('.imgBox_file').cropbox(options);
            };
            reader.readAsDataURL(this.files[0]);
            this.files = [];
        });
        // 裁切按钮操作
        $('#btnCrop').on('click', function () {
            var img = cropper.getDataURL();
            $('.user_oldtx').html('');
            $('.user_oldtx').append('<img src="' + img + '" align="absmiddle" style="box-shadow:0px 0px 12px #7E7E7E;">');

            $(".tx_cancel").on('click', function () {
                $(".acc_touxiang").hide();
            });
        });
        // 图片放大按钮操作
        $('#btnZoomIn').on('click', function () {
            cropper.zoomIn();
        });
        // 图片缩小按钮操作
        $('#btnZoomOut').on('click', function () {
            cropper.zoomOut();
        });
    });
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="${trRoot}/js/fileinput.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
<script type="text/javascript" src="/resources/js/adminMember/user_driver.js?1234" ></script>
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
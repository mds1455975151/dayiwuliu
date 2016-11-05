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
    <title>平台车辆管理</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css" rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css" rel="stylesheet">
    <link href="${trRoot}/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
</head>
<body>

<div class="container-fluid">
     <input type="hidden" id="recPage" value="${pageNo }">
     <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
            <!--后台右侧布局begin-->
           <div class="col-md-10 ">
                <div class="ht_content">
                    <div id="content-header">
                        <h3>车辆管理</h3>
                    </div>
                    <!--查询框begin-->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="contuser_search">
                                <div class="ht_div">
                                    <label>车牌号前缀：</label>
                                    <input type="text" id="prefix" placeholder=" ">
                                </div>
                                <div class="ht_div">
                                    <label>车牌号：</label>
                                    <input type="text" id="vehicleno" placeholder=" ">
                                </div>
                                <div class="ht_div">
                                    <label>所有人姓名：</label>
                                    <input type="text" id="userName" placeholder=" ">
                                </div>
                                <div class="ht_div">
                                    <label>联系方式：</label>
                                    <input type="text" id="telphone" placeholder=" ">
                                </div>
                                <div class="ht_div">
                                    <label>车主姓名：</label>
                                    <input type="text" id="ownername" placeholder=" ">
                                </div>
                                <div class="ht_div">
                                    <label>车主电话：</label>
                                    <input type="text" id="ownerphone" placeholder=" ">
                                </div>
                            </div>
                            <div class="contuser_search">
                                <div class="ht_divbtn">
                                    <button class="btn btnblue " onclick="loadSearch()" type="button">搜索</button>
                                    <button class="btn btngreen" onclick="clearSearch()" type="button">重置</button>
                                </div>
                            </div>
                    </div>
                    </div>
                    <!--查询框end-->
                    <div class="row mt15">
                        <div class="col-md-12">
                            <div class="content-user">

                                <!--用户表格begin-->
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>车牌号</th>
                                        <th>所有人姓名</th>
                                        <th>联系方式</th>
                                        <th>车主姓名</th>
                                        <th>车主联系方式</th>
                                        <th>车型</th>
                                        <th>载重（吨）</th>
                                        <th>认证状态</th>
                                        <th>认证时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="innerhml">
                                    
                                    </tbody>
                                </table>
                                <!--用户表格end-->
                            </div>
                        </div>
                    </div>
                        <div class="clear"> </div>
                    <!-- 分页部分  开始-->
						            <div class="row pr20 fr">
										<%@include file="../common/pagination.jsp" %>
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
<!--查看详情begin-->
<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >车辆信息详情</h4>
            </div>
            <div class="modal-body" id="detailid" style="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--查看详情end-->
<!--修改begin-->
<div class="modal fade" id="updateDeatil" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >车辆信息修改</h4>
            </div>
            <div class="modal-body" id="uptdetailid" style="">
            	
            </div>
            
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" id="closeupt" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--修改end-->

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
			     		证书编号：<input id="code" type="text">
			        </span>
	                <div class="reg_tel">
	                    <label></label>
	                    <div class="img_upload">
							<input id="file_yyzz" name="file" class="file" type="file">
							<input type="hidden" name="id" id="vehicid">
							<input type="hidden" name="type" id="vehictype">
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

<!--删除begin-->
<div class="modal fade" id="dele" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">物料删除</h4>
            </div>
            <div class="modal-body">
                <h4>确定要删除此条信息吗?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--删除end-->
<!--停用begin-->
<div class="modal fade" id="tingyong" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >提示</h4>
            </div>
            <div class="modal-body">
                <h4>确定要停用吗</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--停用end-->
<!--启用begin-->
<div class="modal fade" id="qiyong" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >提示</h4>
            </div>
            <div class="modal-body">
                <h4>确定要启用吗</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--启用end-->
<%@include file="../common/footer.jsp" %>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>
<script type="text/javascript">
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="${trRoot}/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/js/bootstrap.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="/resources/js/adminMember/car_manager.js?11.5" ></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
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
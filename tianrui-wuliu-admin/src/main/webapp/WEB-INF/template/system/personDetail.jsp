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
    <title>会员合并</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<link href="${trRoot}/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${trRoot}/css/cropper.css" rel="stylesheet">
	<link href="${trRoot}/css/cycss.css" rel="stylesheet">
	<script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
</head>
<body>

<div class="container-fluid">
    <input type="hidden" id="recPageNo" value="${pageNo }">
    <input type="hidden" id="userCellPhone" value="${cellPhone }">
    <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
                <!--后台右侧布局begin-->
            <div class="col-md-10 ">
                <div class="ht_content">
                    <div id="content-header">
                        <h3>个人用户管理</h3>
                    </div>
                    <!--查询框begin-->
                   <form id="searchid">
                   	<input type="hidden" name="menuId" value="${menuId }">
                   	<input type="hidden" name="pageNo" value="1">
                   	<input type="hidden" name="pageSize" value="10">
                            
                    </div>
                    </form>
                    <!--查询框end-->
                    <div class="row mt15">
                         <div class="col-md-12">
                             <div class="content-user" style="overflow-x:scroll;">
                                 <!--用户表格begin-->
                                 <table id="sample2" class="table table-bordered" style="white-space: nowrap;" data-options="">
                                     <thead>
                                     <tr>
                                         <th>会员名 </th>
                                         <th>会员账号 </th>
                                         <th>注册时间</th>
                                         <th>提交时间</th>
                                         <th>会员状态 </th>
                                         <th>认证状态 </th>
                                         <th>推送状态 </th>
                                         <th>NC审核状态 </th>
                                         <th>操作 </th>
                                         <th>审核人</th>
                                         <th>审核时间</th>
                                         <th>最后登录时间</th>
                                     </tr>
                                     </thead>
                                    
                                     <tbody id="innerHml">
                                    	
                                     </tbody>
                                 </table>
                                 <!--用户表格end-->
                             </div>
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
            <!--后台整体布局end-->
    <!--侧边栏end-->
<!--查看详情begin-->
<div class="modal fade" id="detail" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >注册会员详情</h4>
            </div>
            <div class="modal-body" style=" " id="detailid">
               
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--查看详情end-->

<!--失败原因begin-->
<div class="modal fade yy" id="yuanyin" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >失败原因</h4>
            </div>
            <div class="modal-body" id="errorMassage">
            		
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="empty()" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--失败原因end-->
<!--修改照片begin-->
<div class="modal fade" id="againPice" role="dialog" aria-labelledby="myModalLabel">
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
	                <!-- <div class="reg_tel">
	                    <label></label>
	                    <div class="img_upload">
							<input id="file_yyzz" name="file" class="file" type="file">
							<input type="hidden" name="id" id="uptmemberid">
							<input type="hidden" name="type" id="uptmembertype">
							<span class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
						</div>
	                </div> -->
	                 <jsp:include page="../common/upload.jsp" flush="false"></jsp:include>
	            </div>
	            
            <div class="modal-footer">
                <button type="button" onclick="uploadfile()" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--修改照片end-->
<!--停用begin-->
<div class="modal fade" id="qiyong" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >提示</h4>
            </div>
            <div class="modal-body">
                <h4><span id="satus">确定要启用吗</span></h4>
                <input type="hidden" id="statustype" value="">
                <input type="hidden" id="memid" value="">
                <input type="hidden" id="pageNo" value="">
            </div>
            <div class="modal-footer">
                <button type="button" onclick="changeType()" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--停用end-->
<!--删除begin-->
<div class="modal fade" id="dele" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">会员删除</h4>
            </div>
            <div class="modal-body">
            	<input type="hidden" id="memberid" value="">
                <h4>确定要删除此条信息吗?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="deleteMember()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--删除end-->
<jsp:include page="../common/footer.jsp" flush="false"></jsp:include>

<script type="text/javascript">
	var CONTEXTPATH="${contextPath}";
	var imagesRoot="${imagesRoot }";
    $(function(){
        $("table").resizableColumns({});
    });

</script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="${trRoot}/js/cropper.js" ></script>
<script type="text/javascript" src="/resources/js/adminMember/navigation.js" ></script>
<script type="text/javascript" src="/resources/js/personDetail.js?1222" ></script>
<script type="text/javascript">
    //upImg();
     $(function(){
      $(document).ready(function () {
          // 表格列宽度手动调整
      //    $("table").resizableColumns({});

          $(".rz_persontab").off("click").on("click",function(){
        	  $('#detail').css("z-index",1000);
              $('#detail2').css("z-index",1001);
              $('.modal-backdrop').css("z-index",200);
              $('.cropperBox').css("z-index",1500);

          });
          upImg();
      });
  })

</script>
<script type="text/javascript">
		$("#file_yyzz").fileinput({
			language : 'zh',
			showUpload : false,
			dropZoneEnabled : false,
			maxFileCount : 1,
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
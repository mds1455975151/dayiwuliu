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
    <title>会员管理</title>
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
                    <div class="row">
                            <div class="col-md-12">
                                <div class="contuser_search">
                                    <div class="ht_div">
                                        <label>会员名称:</label>
                                        <input type="text"  value="${member.userName }" id="userName" placeholder=" ">
                                    </div>
                                    <div class="ht_div">
                                        <label>会员账号:</label>
                                        <input type="text"  value="${member.cellPhone }" id="cellPhone" placeholder=" ">
                                    </div>
                                   	<div class="ht_div">
                                        <label>会员类别:</label>
                                        <select class="form-control" id="personalType">
                                            <option value="">请选择</option>
                                            <option value="2">个人用户</option>
                                            <option value="1">企业用户</option>
                                        </select>
                                    </div>
                                   	
                                   	<div class="ht_div">
                                        <label>认证状态:</label>
                                        <select class="form-control" id="perCheckStatus">
                                            <option value="">请选择</option>
                                            <option value="2">认证中</option>
                                            <option value="3">认证失败</option>
                                            <option value="1">认证通过</option>
                                        </select>
                                    </div>
                                    <div class="ht_div">
                                        <label>NC审核状态 :</label>
                                        <select class="form-control"  id="ncStatus">
                                            <option value="">请选择</option>
                                            <option value="1">供应商不存在</option>
                                            <option value="2">未审核</option>
                                            <option value="3">审核未通过</option>
                                            <option value="4">审核中</option>
                                            <option value="5">审核通过，但组织未分配</option>
                                            <option value="6">审核通过，且组织已分配</option>
                                        </select>
                                    </div>
                                <div class="contuser_search">
                                 	<div class="ht_div">
                                        <label>会员状态:</label>
                                        <select class="form-control" name="status" id="status">
                                            <option value="">请选择</option>
                                            <option value="1">启用</option>
                                            <option value="0">停用</option>
                                        </select>
                                  	</div>
                                  	<div class="ht_divbtn">   
                                    <button class="btn btn-primary btn-sm" type="button" onclick="searchSubmit()">搜索</button>
                                    <button class="btn btn-success btn-sm" type="button" onclick="memberReset()">重置</button>
                                	</div>
                                </div>
                        </div>
                    </div>
                    </form>
                    <!--查询框end-->
                    <div class="row mt15">
                         <div class="col-md-12">
                             <div class="content-user">
                                 <!--用户表格begin-->
                                 <table id="sample2" class="table table-bordered"   data-options="">
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
<!--查看详情begin-->
<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
<div class="modal fade yy" id="yuanyin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
<div class="modal fade" id="qiyong" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
<div class="modal fade" id="dele" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
<script type="text/javascript" src="/resources/js/adminMember/user_person.js?0174" ></script>
<script type="text/javascript">
    //upImg();
     $(function(){
      $(document).ready(function () {
          // 表格列宽度手动调整
      //    $("table").resizableColumns({});

          $(".rz_persontab").off("click").on("click",function(){
//            $('#detail').modal('hide');//第一层先隐藏
//              $('#againPice').css("z-index",1);
//              $('.modal-backdrop').css("z-index",0);
//              $('.cropperBox').css("z-index",999);
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
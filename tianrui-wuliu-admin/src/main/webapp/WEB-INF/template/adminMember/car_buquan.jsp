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
    <title>车辆信息补全</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css"  rel="stylesheet">
    <link href="${trRoot}/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${stylesRoot }/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<body>

<div class="container-fluid">
    <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
     		<input type="hidden" id="pageNo" value="${pageNo }">
                <!--后台右侧布局begin-->
            <div class="col-md-10 ">
                <form id="form_vehicle">
                <div class="ht_content">
                    <div class="user_shtitle mt10">
                        <h3>车辆信息补全</h3>
                    </div>
                    <!--车辆信息begin-->
                    <div class="rz_box">
                            <div class="reg_tel">
                                <input type="hidden" name="id" id="veh_id" value="${Vehicle.id }">
                                <label><i class="colorred">*</i>车牌号前缀：</label>
                                <input type="text" name="vehiclePrefix" id="veh_vehiclePrefix" value="${Vehicle.vehicleprefix }">
                                <span></span>
							</div>                           
                            <div class="reg_tel">
                                <label><i class="colorred">*</i>车牌号：</label>
                               	<input type="text" name="vehicleNo" id="veh_vehicleNo" value="${Vehicle.vehicleno }">
                                <span></span>
                            </div>                           
                            <div class="reg_tel">
                                <label><i class="colorred">*</i>所有人姓名：</label>
                                <input type="text" name="vehiOwnerName" id="veh_vehiOwnerName" value="${Vehicle.userName }">
                                <span></span>
                            </div>                           
                            <div class="reg_tel">
                                <label><i class="colorred">*</i>联系方式：</label>
                                <input type="text" name="vehiOwnerTel" id="veh_vehiOwnerTel" value="${Vehicle.telphone }">
                                <span></span>
                            </div>                           
                            <div class="reg_tel">
                                <label><i class="colorred">*</i>车辆类型：</label> 
                                <select class="form-control w350" name="vehicleType" id="veh_vehicleType">
									<option value="0">请选择</option>
									<c:forEach items="${vt }" var="type">
									<option value="${type.wlcode }" 
									<c:if test="${Vehicle.vehicletype eq type.wlcode }">selected="true"</c:if>
									>${type.wlname }</option>
									</c:forEach>
								</select>
							</div>                           
                            <div class="reg_tel">
                                <label><i class="colorred">*</i>载重(吨)：</label>
                                <input type="text" name="vehiWeight" id="veh_vehiWeight" value="${Vehicle.vehiweight }">
                                <span></span>
                           	</div>                           
                            <div class="reg_tel">
                                <label><i class="colorred">*</i>长度(米)：</label>
                                <input type="text" name="vehiLength" id="veh_vehiLength" value="${Vehicle.vehilength }">
                                <span></span><br>
                            </div>
                    </div>
                    <!--车辆信息end-->
                    <div class="user_shtitle mt20">
                        <h3>认证文件</h3>
                    </div>
                    <div class="row ">
                        <div class="rz_info">
                            <div class="rz_imgshow" >
                                <label><i class="colorred">*</i>行驶证照片：</label>
                                <input type="hidden" name="vehiLicenseImgPath" id="veh_vehiLicenseImgPath" value="${Vehicle.vehilicenseimgpath }">
                                <c:if test="${Vehicle.vehilicenseimgpath ne '' }">
                                <span><a href="/imageView/index?imageUrl=${Vehicle.vehilicenseimgpath }" target="_blank">
                                <img height="200" src="${Vehicle.vehilicenseimgpath }"></a>
                                </span>
                                </c:if>
				                <div class="reg_tel">
				                    <label></label>
				                    <div class="img_upload">
										<input id="file_vehiLicenseImgPath" onchange="fileUpload('vehiLicenseImgPath')" name="file" class="file file_pic" type="file">
										<span class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
									</div>
				                </div>
                            </div>
                         </div>   
                         <div class="rz_info">
                            <div class="rz_imgshow" >
                                <label><i class="colorred">*</i>车辆照片：</label>
                                <input type="hidden" name="vehiHeadImgPath" id="veh_vehiHeadImgPath" value="${Vehicle.vehiheadimgpath }">
                                <c:if test="${Vehicle.vehiheadimgpath ne ''}">
                                <span><a href="/imageView/index?imageUrl=${Vehicle.vehiheadimgpath }" target="_blank">
                                <img height="200" src="${Vehicle.vehiheadimgpath }"></a>
                                </span>
                                </c:if>
                            	<div class="modal-body">
				                    <label></label>
				                    <div class="img_upload">
										<input id="file_vehiHeadImgPath" onchange="fileUpload('vehiHeadImgPath')" name="file" class="file file_pic" type="file">
										<span class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
									</div>
				                </div>
                            </div>
                         </div>
                      </div>
                    <div class="user_shtitle mt20">
                    </div>
                      <div class="row ">
                         <div class="rz_info">
                            <div class="rz_imgshow" >
                                <label><i class="colorred">*</i>营运证号：</label>
                                <input id="veh_opercode" name="opercode" type="text" value="${Vehicle.opercode }"><br>
                               	<input type="hidden" name="operimage" id="veh_operimage" value="${Vehicle.operimage }">
                               	<c:if test="${Vehicle.operimage ne ''}">
                                <span>
                                <a href="/imageView/index?imageUrl=${Vehicle.operimage }" target="_blank">
                                <img height="200" style="margin-left:70px" src="${Vehicle.operimage }"></a>
                                </span>
                               	</c:if>
                            	<div class="modal-body">
				                <div class="reg_tel">
				                    <label></label>
				                    <div class="img_upload">
										<input id="file_operimage" onchange="fileUpload('operimage')" name="file" class="file file_pic" type="file">
										<span class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
									</div>
				                </div>
				               </div>
                            </div>
                          </div>
                          <div class="rz_info">
                            <div class="rz_imgshow" >
                                <label><i class="colorred">*</i>车辆登记证：</label>
                                <input type="hidden" name="registimage" id="veh_registimage" value="${Vehicle.registimage }">
                                <c:if test="${Vehicle.registimage ne ''}">
                                <a href="/imageView/index?imageUrl=${Vehicle.registimage }" target="_blank">
                                <img height="200" style="margin-left:70px" src="${Vehicle.registimage }"></a>
                                </c:if>
                            	<div class="modal-body">
					                <div class="reg_tel">
					                    <label></label>
					                    <div class="img_upload">
											<input id="file_registimage" onchange="fileUpload('registimage')" name="file" class="file file_pic" type="file">
											<span class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
										</div>
					                </div>
					            </div>
                            </div>
                         </div>
                    </div>
                    <div class="row ">
                            <div class="person_button">
                                <a data-toggle="modal" data-target="#pass">
                                    <button class="btn btn-success btn-sm" type="button">确定修改</button>
                                    </a>
                            </div>
                    </div>
                </div>
                </form>
            </div>
                <!--后台右侧布局end-->
            </div>
            <!--后台整体布局end-->
    <!--侧边栏end-->
<!--通过begin-->
<div class="modal fade" id="pass" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >车辆信息补全</h4>
            </div>
            <div class="modal-body">
                确定补全车辆信息吗？
            </div>
            <div class="modal-footer">
                <button type="button" onclick="updateCar()" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--通过end-->
<%@include file="../common/footer.jsp" %>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="/resources/js/adminMember/car_buquan.js?0226" ></script>
<script type="text/javascript">
    $(function(){
        $("table").resizableColumns({});

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
    });
    var CONTEXTPATH="${contextPath}";
</script>
<script type="text/javascript">
		$(".file_pic").fileinput({
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
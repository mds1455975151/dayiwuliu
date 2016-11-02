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
    <title>车辆审核</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css"  rel="stylesheet">
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
                <div class="ht_content">
                    <div class="user_shtitle mt10">
                        <h3>车辆基本信息</h3>
                    </div>
                    <!--车辆信息begin-->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="person_cont">
                                <input type="hidden" id="vehicleid" value="${Vehicle.id }">
                                <label>车牌号前缀：</label><span>${Vehicle.vehicleprefix }</span>
                                <label>车牌号：</label><span>${Vehicle.vehicleno }</span>
                                <label>所有人姓名：</label><span>${Vehicle.userName }</span>
                                <label>联系方式：</label><span>${Vehicle.telphone }</span>
                                <label>车型：</label><span>
                                <c:if test="${Vehicle.vehicletype eq '1' }">
                                		箱式
                                </c:if>
                                <c:if test="${Vehicle.vehicletype eq '2' }">
                                		车板
                                </c:if>
                                <c:if test="${Vehicle.vehicletype eq '3' }">
                                 		冷藏
                                </c:if>
                                <c:if test="${Vehicle.vehicletype eq '4' }">
                                 		散装罐车
                                </c:if>
                                <c:if test="${Vehicle.vehicletype eq '5' }">
                                 		半挂车
                                </c:if>
                                <c:if test="${Vehicle.vehicletype eq '6' }">
                                 		重型自卸货车
                                </c:if>
                                </span>
                                <label>载重(吨)：</label><span>${Vehicle.vehiweight }</span>
                                <label>长度(米)：</label><span>${Vehicle.vehilength }</span><br>
                                <label>认证状态：</label><span>
                                <c:if test="${Vehicle.status eq -1 }">认证失败</c:if>
                                <c:if test="${Vehicle.status eq 0 }">未认证</c:if>
                                <c:if test="${Vehicle.status eq 1 }">认证成功</c:if>
                                <c:if test="${Vehicle.status eq 2 }">认证中</c:if>
                                </span>
                                <label>认证时间：</label><span>${Vehicle.createtimeStr }</span>
                            </div>
                            <input type="hidden" id="statue" value="${Vehicle.status }">
                        	<input type="hidden" id="memberid" value="${Vehicle.memberid }">
                        </div>
                    </div>
                    <!--车辆信息end-->
                    <div class="user_shtitle mt20">
                        <h3>认证文件</h3>
                    </div>
                    <div class="row ">
                        <div class="person_cont">
                            <div class="person_img">
                                <label>行驶证照片：</label><span><a href="${Vehicle.vehilicenseimgpath }" target="_blank"><img height="200" src="${Vehicle.vehilicenseimgpath }"></a></span>
                            </div>
                            <div class="person_img">
                                <label>车辆照片：</label><span><a href="${Vehicle.vehiheadimgpath }" target="_blank"><img height="200" src="${Vehicle.vehiheadimgpath }"></a></span>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                            <div class="person_button">
                                <a data-toggle="modal" data-target="#pass">
                                    <button class="btn btn-success btn-sm" type="button">通过</button>
                                    </a>
                                <a data-toggle="modal" data-target="#passno">
                                <button class="btn btn-danger btn-sm" type="button">不通过</button>
                                    </a>
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
<!--通过begin-->
<div class="modal fade" id="pass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >个人用户审核</h4>
            </div>
            <div class="modal-body">
                确定通过审核吗？
            </div>
            <div class="modal-footer">
                <button type="button" onclick="shenheCar(1)" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--通过end-->
<!--不通过begin-->
<div class="modal fade" id="passno" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >个人用户审核</h4>
            </div>
            <div class="modal-body">
                    <div class="user_shenno">
                        <h4>请输入不通过审核原因：</h4>
                        <textarea class="form-control" id="massage" rows="3"></textarea>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="shenheCar(-1)"  class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--不通过end-->
<%@include file="../common/footer.jsp" %>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
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
<script type="text/javascript" src="/resources/js/adminMember/car_shenhe.js?11.2" ></script>

</body>
</html>
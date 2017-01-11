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
    <title>司机审核</title>
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
   <input type="hidden" id="pageNo" value="${pageNo }">
   <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
                <!--后台右侧布局begin-->
            <div class="col-md-10 ">
                <div class="ht_content">
                    <div class="user_shtitle mt10">
                        <h3>基本信息</h3>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="person_cont">
                                <label>司机账号：</label><span>${memberInfo.cellphone }</span><br>
                                <label>司机姓名：</label><span>${memberInfo.username }</span><br>
                                <label>联系方式：</label><span>${memberInfo.telphone }</span><br>
                                <label>驾驶证号：</label><span>${memberInfo.idcard }</span><br>
                                <label>准驾车型：</label><span>${memberInfo.licenseType }</span><br>
                               
                               <c:if test="${memberInfo.sex eq 'xx'}">
                               <label>性别：</label><span>女</span><br>
                               </c:if>
                               <c:if test="${memberInfo.sex eq 'xy'}">
                               <label>性别：</label><span>男</span><br>
                               </c:if>
                               
                               <label>出生日期：</label><span>${memberInfo.birthday }</span><br>
                               <label>初次领证日期：</label><span>${memberInfo.firstlicens }</span><br>
                               <label>发证机关：</label><span>${memberInfo.licenceorg }</span><br>
                               <label>驾驶证注册日期：</label><span>${memberInfo.starttime }</span><br>
                               <label>有效年限：</label><span>${memberInfo.usefullife }</span><br>
                               <label>身份证地址：</label><span>${memberInfo.idcardaddress }</span><br>
                               
                               
                               
                               
                                <c:if test="${memberInfo.driverpercheck eq '0'}">
                                <label>认证状态：</label><span>未认证</span><br>
                                </c:if>
                                <c:if test="${memberInfo.driverpercheck eq '1'}">
                                <label>认证状态：</label><span>认证通过</span><br>
                                </c:if>
                                <c:if test="${memberInfo.driverpercheck eq '2'}">
                                <label>认证状态：</label><span>认证中</span><br>
                                </c:if>
                                <c:if test="${memberInfo.driverpercheck eq '3'}">
                                <label>认证状态：</label><span>认证失败</span><br>
                                </c:if>
                                <label>注册时间：</label><span>${memberInfo.registtimeStr }</span><br>
                                <label>认证时间：</label><span>${memberInfo.submittimeStr }</span><br>
                            </div>
                        </div>
                    </div>
                    <div class="user_shtitle mt20">
                        <h3>认证文件</h3>
                    </div>
                    <div class="row ">
                        <div class="person_cont">
                            <label>驾驶证照片：</label>
                            <span> 
                            <a href="/imageView/index?imageUrl=${memberInfo.driverimage }" target="_blank">
                            <img height="200" src="${memberInfo.driverimage }">
                            </a>
                            </span>
                       		<input type="hidden" id="infoid" value="${memberInfo.id }">
                        </div>
                    </div>
                    <div class="row ">
                        <div class="person_cont">
                            <div class="person_button">
                                <a data-toggle="modal" data-target="#pass">
                                    <button class="btn btn-success btn-sm" type="submit">通过</button>
                                    </a>
                                <a data-toggle="modal" data-target="#passno">
                                <button class="btn btn-danger btn-sm" type="submit">不通过</button>
                                    </a>
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
                <button type="button" onclick="adminReview('1')" class="btn btn-primary">确定</button>
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
                        <textarea class="form-control" id="massages" rows="3"></textarea>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="adminReview('3')" class="btn btn-primary">确定</button>
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
<script type="text/javascript" src="/resources/js/adminMember/driver_shenhe.js?11.22" ></script>
</body>
</html>
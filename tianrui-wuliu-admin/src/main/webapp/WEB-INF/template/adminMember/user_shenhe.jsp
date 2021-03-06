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
    <title>会员审核</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
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
                        <div class="person_cont" id="memberDiv" ><!-- 1：企业，2：个人 -->
                            <c:if test="${memberInfo.userpercheck eq '2' }">
                            <label>会员类别：</label><span>个人用户</span><br>
                            <label>会员账号：</label><span>${memberInfo.cellphone }</span><br>
                            <label>会员姓名：</label><span>${memberInfo.username }</span><br>
                            <c:if test="${memberInfo.sex eq 'xx'}">
                            <label>性别：</label><span>女</span><br>
                            </c:if>
                            <c:if test="${memberInfo.sex eq 'xy'}">
                            <label>性别：</label><span>男</span><br>
                            </c:if>
                               
                            <label>出生日期：</label><span>${memberInfo.birthday }</span><br>
                            <label>联系方式：</label><span>${memberInfo.telphone }</span><br>
                            <label>身份证号：</label><span>${memberInfo.idcard }</span><br>
                            </c:if>
                            <c:if test="${memberInfo.companypercheck eq '2' }">
                            <label>会员类别：</label><span>企业用户</span><br>
                            <label>会员账号：</label><span>${memberInfo.cellphone }</span><br>
                            <label>联系人：</label><span>${memberInfo.companycontact }</span><br>
                            <label>联系人电话：</label><span>${memberInfo.companytel }</span><br>
                            <label>公司名称：</label><span>${memberInfo.companyname }</span><br>
                            <label>营业执照号码：</label><span>${memberInfo.companycode }</span><br>
                            </c:if>
                            <label>注册时间：</label><span>${memberInfo.registtimeStr }</span><br>
                            <label>认证时间：</label><span>${memberInfo.submittimeStr }</span><br>
                            <label>道路运输经营许可证号：</label><span>${memberInfo.rtblno }</span><br>
                        </div>
                        <c:if test="${memberInfo.companypercheck eq '2' }">
                        <div class="person_cont" id="memberaddressDiv">
                            <label>公司所在地：</label><span>${memberInfo.companyAddress }</span><br>
                        </div>
                        </c:if>
                    </div>
                    <div class="user_shtitle mt20">
                        <h3>认证文件</h3>
                    </div>
                    <div class="row ">
                        <div class="person_cont">
                            <label>证件照：</label><span id="imgid">
                            <c:if test="${memberInfo.companypercheck eq '2' }">
                            <a href="/imageView/index?imageUrl=${memberInfo.licenseImagePath }" target="_blank">
                            <img height="200" src="${memberInfo.licenseImagePath }">
                            </a>
                            </c:if>
                            <c:if test="${memberInfo.userpercheck eq '2' }">
                            <a href="/imageView/index?imageUrl=${memberInfo.idcardimage }" target="_blank">
                            <img height="200" src="${memberInfo.idcardimage }">
                            </a>
                            </c:if>
                            </span>
                        </div>
                        <div class="person_cont">
                            <label>道路运输经营许可证：</label>
                            <span id="imgid">
                            <c:if test="${not empty memberInfo.rtblimgurl }">
	                            <a href="/imageView/index?imageUrl=${memberInfo.rtblimgurl }" target="_blank">
	                            	<img height="200" src="${memberInfo.rtblimgurl }">
	                            </a>
                            </c:if>
                            </span>
                        </div>
                        <input type="hidden" id="companypercheck" value="${memberInfo.companypercheck}">
						<input type="hidden" id="userpercheck" value="${memberInfo.userpercheck}">
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
<div class="modal fade" id="pass" role="dialog" aria-labelledby="myModalLabel">
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
                <button type="button" onclick="adminReview('1')" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--通过end-->
<!--不通过begin-->
<div class="modal fade" id="passno" role="dialog" aria-labelledby="myModalLabel">
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
                <button type="button" onclick="adminReview('3')" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--不通过end-->
<input type="hidden" id="infoid" value="${memberInfo.id }">
<input type="hidden" id="companypercheck" value="${memberInfo.companypercheck}">
<input type="hidden" id="userpercheck" value="${memberInfo.userpercheck}">

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
<script type="text/javascript" src="/resources/js/adminMember/navigation.js" ></script>
<script type="text/javascript" src="/resources/js/adminMember/user_shenhe.js?12.14" ></script>

</body>
</html>
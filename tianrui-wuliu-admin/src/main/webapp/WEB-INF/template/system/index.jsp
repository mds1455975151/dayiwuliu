<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.tianrui.service.admin.bean.Users" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Users user = (Users) request.getSession().getAttribute("session_user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>天瑞物流平台后台管理系统-首页</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/font-awesome.css" rel="stylesheet" />
    <link rel="Shortcut Icon" href="${imagesRoot }/favicon.ico" type="image/x-icon">
    <link href="${stylesRoot }/imgcut.css" rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css" rel="stylesheet">
    <link href="${stylesRoot }/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="container-fluid">
    <!--公共头部begin-->
    <div class="row"><input type="hidden" id="menuId" value="${menuId}">
        <div class="col-md-2 bghead">
            <div class="header_logo">
                <img src="${imagesRoot }/logo2.png">
            </div>
        </div>
        <div class="col-md-10 bghead">
            <!--close-top-serch-->
            <!--start-top-Header-menu-->
            <div id="user-nav" class="">
                <ul class="headnav">
                </ul>
            </div>
            <!--close-top-Header-menu-->
        </div>
    </div>
    <!--公共头部end-->
    <div class="row">
        <div class="ht_index">
            <div class=" ht_indexcont">
                <!--内容begin-->
                <div class="ht_indexd">
                    <h3>您好，<%= user.getUsertype() %></h3>
                    <h2>欢迎登陆天瑞物流平台后台</h2>
                    <p><label>您的组织：</label><%= user.getOrgname() %></p>
                    <p><label>您的手机号：</label><%= user.getTelnum() %></p>
                    <p><label>您的登录账号：</label><%= user.getAccount() %></p>
                    <div class="htqx_line">
                        <label>您的权限：</label>
                        <div class="htqxr" id="role">
                        </div>
                    </div>
                </div>
                <!--内容end-->
            </div>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
<script type="text/javascript">
	var CONTEXTPATH="${path}";
	$(function() { 
		var userId = <%= user.getId() %>;
		// 获取用户角色
		$.ajax({
			url : CONTEXTPATH + '/user/getRoleByUserID',// 跳转到 action
			data : {id:userId},
			type : "post",
			success : function(result) {
				if(result &&result.code=="000000"  ){
					var data = result.data;
					if(!data || data.length == 0){
						$("#role").append("<span  >您尚未被分配角色，请联系管理员！</span>");
					}
					for (var i = 0; i < data.length; i++) {
							var userRole = "【"+data[i].name+"】";
							if((i + 1)%2 == 0){
								$("#role").append("<span>"+userRole+"</span><br>");
							}else{
								$("#role").append("<span>"+userRole+"</span>");
							}
					}
				}
			}
		});
	});
</script>
</body>
</html>
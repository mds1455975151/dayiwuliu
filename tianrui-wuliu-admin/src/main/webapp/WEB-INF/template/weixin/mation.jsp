<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <title>用户详情</title>
    <link href="${trRoot }/weixin/css/base.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/style.css" rel='stylesheet' type='text/css'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="padding8">
    <div class="lg_tabcont">
       
        <div class="ht_indexd">
            <h3>您好，${session_user.usertype}</h3>
            <h2>欢迎登陆天瑞物流平台后台</h2>
            <p><label>您的组织：</label>${session_user.orgname}</p>
            <p><label>您的手机号：</label>${session_user.telnum}</p>
            <p><label>您的登录账号：</label>${session_user.account}</p>
        </div>
		<input type="hidden" id="userid" value="${session_user.id}">
		
		<input type="hidden" id="openid" value="${session_user.desc4}">
    </div>
    <div class="lg_submit">
        <button class="btn btnblue excitlogin" >退出登录</button>
    </div>
</div>
<script src="${trRoot }/weixin/js/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(".excitlogin").on("click",function(){
	var openid = $("#openid").val();
	$.ajax({
		type:"post",
		data:{"id":$("#userid").val()},
		url:"/weixin/page/excitLogin",
		success:function(ret){
			if(ret.code=="000000"){
				window.location.href="/weixin/login/loginPage?state=member&openid="+openid;
			}
		}
	});
});
</script>
</body>
</html>



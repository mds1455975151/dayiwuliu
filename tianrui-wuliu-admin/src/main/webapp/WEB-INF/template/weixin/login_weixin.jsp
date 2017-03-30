<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link href="${trRoot }/weixin/css/base.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/style.css" rel='stylesheet' type='text/css'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="padding8">
    <div class="lg_tabcont">
        <div class="lg_tabline">
            <i class="iconfont icon-yonghuming colorlg">&#xe60f;</i>
            <input type="text" id="wx_username" placeholder="请输入手机号">
        </div>
        <div class="lg_tabline">
            <i class="iconfont icon-mima colorlg">&#xe610;</i>
            <input type="text" id="wx_password" placeholder="请输入密码">
            <input type="hidden" id="wx_openid" value="${openid }">
            <input type="hidden" id="wx_state" value="${state }">
        </div>
    </div>
    <div class="lg_submit">
        <button class="btn btnblue wxlongin" >登录</button>
    </div>
</div>
<script src="${trRoot }/weixin/js/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(".wxlongin").on("click",function(){
	var wx_username = $("#wx_username").val();
	var wx_password = $("#wx_password").val();
	var wx_openid = $("#wx_openid").val();
	var wx_state = $("#wx_state").val();
	if(wx_username == ""){
		alert("登录名称不能为空");
		return;
	}
	if(wx_password == ""){
		alert("密码不能为空");
		return;
	}
	if(wx_openid == ""){
		alert("请在微信上登录");
		return;
	}
	$.ajax({
		type: "POST",
		url:'/weixin/login/loginin',// 跳转到 action
		data:{"username":wx_username,
			"password":wx_password,
			"openid":wx_openid,
			"state":wx_state
		},
		success: function(rs) {
			if(rs.code=="000000"){
				window.location.href="/weixin/login/loginSuccess?state="+wx_state+"&openid="+wx_openid;
			}else{
				alert(rs.error);
			}
		}
	});
});
    
</script>
</body>
</html>



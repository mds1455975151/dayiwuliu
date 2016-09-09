<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>天瑞物流平台后台管理系统</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/font-awesome.css" rel="stylesheet" />
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body onkeydown="keyLogin();">
<!--Header-part-->
<div class="container">
    <div class="login_logo">
        <img src="${imagesRoot }/logo.png">
    </div>
</div>
<!--close-Header-part-->
<!--Headercont-part-->
<div class="login_cont">
<div class="container">
    <div class="login_map">
    <div class="row">
                <img src="${imagesRoot }/g1.png">
        <!--验证框beigin-->
			 <div class="loginbox">
                    <div class="h3bg">
                        <h3 class="lh200">用户登录</h3>
                    </div>
                        <!--手机输入beigin-->
                        <div class="login_tel">
                                <div class="loginint">
                                        <input type="text" maxlength="11" id="loginAcct" placeholder="请输入账号" >
                                </div>
                                    <div class="login_hang1">
                                        <i class="glyphicon glyphicon-phone"></i>
                                    </div>
                        </div>
                        <!--手机输入end-->
                        <!--密码beigin-->
                        <div class="login_tel">
                            <div class="loginint">
                                 <input type="password" id="password"  maxlength="16"  placeholder="请输入密码" >
                            </div>
                            <div class="login_hang1">
                                <i class="glyphicon glyphicon-lock"></i>
                            </div>
                        </div>
                        <!--密码end-->
                        <div class="login_yz">
                                <input id="pass" maxlength="8" type="text" placeholder="验证码">
                            <button onclick="javascript:getValCode();" type="submit" >发送验证码</button>
                        </div>
                         <!--提示信息beigin-->
                        <div class="note">
                            <h4><span class="error" id="message"></span></h4>
                        </div>
                        <!--提示信息end-->
                        <button class="btn btn-block btnlg" type="button"  id="login">登录</button>
                </div>
        <!--验证框end-->
        </div>
    </div>
</div>
</div>
<!--close-Headercont-part-->
<div class="login_foot">
    <h4>版权所有@天瑞集团 2016-2017</h4>
</div>
 	<script type="text/javascript" src="${scriptsRoot }/jquery-1.11.1.js"></script>
 	<script type="text/javascript">
		var CONTEXTPATH="${contextPath}";
	</script>
    <script type="text/javascript" src="${scriptsRoot }/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/js/login/login.js" ></script>
</body>
</html>

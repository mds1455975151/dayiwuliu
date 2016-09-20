<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>天瑞物流平台-注册</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

<jsp:include page="../common/member/header_busi.jsp"></jsp:include>
<!--登录内容部分begin-->
<div class="bghui">
<div class="container">
    <div class="row bgwhite p15">
        <div class="reg_head border_qhui">
         <h3>注册账号</h3>
        </div>
        <div class="regbox ">
        <!--登录框beigin-->
                <div class="regleft">
                	<div class="regis_in">
	                    <!--手机输入beigin-->
	                    <div class="reg_tel">
	                        <label><i>*</i>手机号:</label>
	                         <input type="text" maxlength="11" placeholder="请输入手机号" id="register_tel">
	                         <p class="reg_hui" id="message_tel">请输入大陆手机号，用于登陆、找回密码</p>
	                    </div>
	                    <!--手机输入end-->
	                    <div class="reg_tel">
	                        <label><i>*</i>密码:</label>
	                        <input type="password" placeholder="请输入密码" id="register_firstpsw">
	                        <p class="reg_hui" id="message_firstpsw">密码为字母、数字或者英文符号，6-20位，区分大小写</p>
	                    </div>
	                    <div class="reg_tel">
	                        <label><i>*</i>确认密码:</label>
	                        <input type="password" placeholder="请再次输入密码" id="register_confirmpsw">
	                        <p id="message_confirmpsw"></p>
	                    </div>
	                    <div class="reg_yz">
	                        <label><i>*</i>短信验证码:</label>
	                        <input type="text" placeholder="请输入短信验证码" id="register_idcode">
	                        <button class="btn " id="idcode_button">获取验证码</button>
	                        <p id="message_idcode"></p>
	                    </div>
						<!--  
	                    <div class="reg_yz">
	                        <label><i>*</i>邀请码:</label>
	                        <input type="text" placeholder="请输入邀请码" id="register_invitecode">
	                    </div>
	                   -->
                    </div>
                    <div class="reg_yz">
                        <label></label>
                        <input type="checkbox" checked="checked"><span><a target="_blank" id="register_protocol"> 阅读并同意《天瑞物流平台服务协议》</a></span>
                    </div>
                        <button class="btn btnmid" type="submit" id="register_button">立即注册</button>

                </div>
            <div class="reg_right">
                <p>已有天瑞账号？</p>
                <p><a href="/publicMember/loginPage">立即登录</a></p>
                <img src="${trRoot}/tianrui/images/g1.png">
            </div>
        <!--登录框end-->
        </div>
    </div>
</div>
</div>
<!--登录内容部分end-->
<!-- 引用公共footer部分 -->
<jsp:include page="../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript">var PATH = "${path}";</script>
<script type="text/javascript" src="/resources/js/utils/md5.js" ></script>
<script type="text/javascript" src="/resources/js/member/registerPage.js" ></script>
</body>
</html>

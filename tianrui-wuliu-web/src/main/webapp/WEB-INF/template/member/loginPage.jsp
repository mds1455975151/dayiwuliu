<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>天瑞物流平台-登录</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

<jsp:include page="../common/member/header_busi_nlogin.jsp"></jsp:include>
<!--登录内容部分begin-->
<div class="bghui">
<div class="container">
    <div class="row">
        <div class="login_map">
            <div class="login_left">
                <img src="${trRoot}/tianrui/images/g1.png">
            </div>
        <!--登录框beigin-->
                <div class="loginbox">
                        <!--手机输入beigin-->
                            <div class="login_tel">
                                <h4>手机号</h4>
                                 <input type="text" maxlength="11" placeholder="请输入手机号" id="login_tel">
                                 <p id="message_loginTel"></p>
                                <h4>密码</h4>
                                <input type="password" placeholder="请输入密码" maxlength="16" id="login_psw">
                                <p id="message_loginPsw"></p>
                            </div>
                    <p class="login_mima">
                      <!--   <input type="checkbox"><label> 记住密码</label> -->
                        <label> <a href="/publicMember/lostPass" >忘记密码?</a></label>
                    </p>
                        <!--手机输入end-->
                        <!--提示信息beigin-->
                        <div class="note">
                            <h4><span id="message_loginError"></span></h4>
                        </div>
                        <!--提示信息end-->
                        <button class="btn btn-block btnyello" type="submit" id="login_button"  style="margin-top: 30px;">登录</button>
                </div>
        <!--登录框end-->
        </div>
    </div>
</div>
</div>
<!--登录内容部分end-->
<!-- 引用公共footer begin -->
<jsp:include page="../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript">
	var PATH = "${path}";
</script>
<script type="text/javascript" src="/resources/js/utils/md5.js" ></script>
<script type="text/javascript" src="/resources/js/member/loginPage.js" ></script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>index</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
<!-- 引用公共header部分 -->
<jsp:include page="../common/member/header_busi.jsp"></jsp:include>
<!--登录内容部分begin-->
<div class="bghui">
<div class="container">

    <div class="row bgwhite  mt20 mb20 p15">
        <div class="reg_head border_qhui ">
         <h3>找回密码</h3>
        </div>
        <div class="reg_zhaohui">
            <ul>
                <li><img src="${trRoot}/tianrui/images/zhmm11.jpg"><h4>1.验证身份</h4></li>
                <li><img src="${trRoot}/tianrui/images/zhmm22.jpg"><h4>2.重新设置密码</h4></li>
                <li><img src="${trRoot}/tianrui/images/zhmm32.jpg"><h4>3.修改成功</h4></li>
            </ul>
        </div>
        <div class="regbox5">
        <!--登录框beigin-->
            <div class="reg_yz">
                <label><i>*</i>验证方式:</label>
                <span>手机验证</span>
            </div>
            <div class="reg_yz">
                <label><i>*</i>输入手机号:</label>
                <input type="text" placeholder="请输入手机号" maxlength="11" id="accountTel" >
                <button class="btn btn_yz" id="getLostCode" >获取验证码</button>
                 <p class="reg_hui" id="message_tel">请输入大陆手机号，找回密码</p>
            </div>
            <div class="reg_yz">
                <label><i>*</i>短信验证码:</label>
                <input type="text" maxlength="8" id="resetPassCode" >
            </div>
             <button class="btn boxbutton btnyello" type="button" id="resetPass" >下一步</button>
        <!--登录框end-->
        </div>
    </div>
</div>
</div>
<!--登录内容部分end-->
<!-- 引用公共footer部分 -->
<jsp:include page="../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/member/registerPage.js" ></script>
</body>
</html>
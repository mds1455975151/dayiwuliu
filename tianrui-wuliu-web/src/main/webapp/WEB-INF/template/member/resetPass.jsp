<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>重置密码</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
<!-- 引用公共header部分 -->
<jsp:include page="../common/member/header_busi.jsp"></jsp:include>
<!--内容部分begin-->
<div class="bghui">
<div class="container">

    <div class="row bgwhite  mt20 mb20 p15">
        <div class="reg_head border_qhui ">
         <h3>找回密码</h3>
        </div>
        <div class="reg_zhaohui">
            <ul>
                <li><img src="${trRoot}/tianrui/images/zhmm12.jpg"><h4>1.验证身份</h4></li>
                <li><img src="${trRoot}/tianrui/images/zhmm21.jpg"><h4>2.重新设置密码</h4></li>
                <li><img src="${trRoot}/tianrui/images/zhmm32.jpg"><h4>3.修改成功</h4></li>
            </ul>
        </div>
        <div class="reg_zh">
            <div class="row text-center">
                <h4>新密码格式： 	6-14位数字、字母的密码，建议字符组合，不允许空格，区分大小写：</h4>
            </div>
            <div class="regbox5">
                <!--登录框beigin-->
                <input type="hidden" id="accountTel" value="${tel}" >
                <input type="hidden" id="registerCode" value="${registerCode}" >
                <div class="reg_yz">
                    <label><i>*</i>新密码:</label>
                    <input type="password" maxlength="20" id="register_p" >
                </div>
                <div class="reg_yz">
                    <label><i>*</i>密码确认:</label>
                    <input type="password"  maxlength="20" id="register_pass" >
                     <p id="message1"></p>
                </div>
                <button class="btn boxbutton btnyello" type="button" id="resetP" >提交</button>
                <!--登录框end-->
            </div>
        </div>
    </div>
</div>
</div>
<!--登录内容部分end-->
<!-- 引用公共footer部分 -->
<jsp:include page="../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/utils/md5.js" ></script>
<script type="text/javascript" src="/resources/js/member/registerPage.js" ></script>

</body>
</html>
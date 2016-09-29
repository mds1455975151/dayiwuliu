<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改密码</title>
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
         <h3>修改密码</h3>
        </div>
        <div class="reg_zhaohui">
            <ul>
                <li><img id="image1" src="${trRoot}/tianrui/images/zhmm11.jpg"><h4>1.验证密码</h4></li>
                <li><img id="image2" src="${trRoot}/tianrui/images/zhmm22.jpg"><h4>2.重新设置密码</h4></li>
                <li><img id="image3" src="${trRoot}/tianrui/images/zhmm32.jpg"><h4>3.修改成功</h4></li>
            </ul>
        </div>
        <div class="regbox5" id="uptpass1">
        <!--验证密码beigin-->
            <div class="reg_yz">
                <label><i>*</i>输入原密码:</label>
                <input type="hidden" id="oldpass" value="${password }">
                <input type="password" placeholder="输入原密码" maxlength="11" id="password" >
                 <p class="reg_hui" id="errorMassage">输入原密码</p>
            </div>
            <button class="btn boxbutton btnyello" type="button" onclick="checkPass()">下一步</button>
        <!--验证密码end-->
        </div>
        <div class="regbox5" id="uptpass2">
        <!--修改密码beigin-->
            <div class="reg_yz">
                <label><i>*</i>输入新密码:</label>
                <input type="password" id="newpassword" placeholder="输入新密码">
            </div>
            <div class="reg_yz">
                <label><i>*</i>确认密码:</label>
                <input type="password" id="takepassword" placeholder="确认密码">
                <p class="reg_hui" id="checkMassage"></p>
            </div>
            <button class="btn boxbutton btnyello" onclick="uptPassWord()" type="button">下一步</button>
        <!--修改密码end-->
        </div>
        <div class="regbox5" id="uptpass3">
        <!--修改密码beigin-->
        <div class="reg_succont2">
                    <div class="reg_succ ">
                        <div> <img src="${trRoot}/tianrui/images/succ.png"><i>恭喜，密码修改成功</i></div>
                        <div><a href="/publicMember/index"><h3>点此立即去登陆</h3></a></div>
                    </div>
                </div>
        <!--修改密码end-->
        </div>
    </div>
</div>
</div>
<!--登录内容部分end-->
<!-- 引用公共footer部分 -->
<jsp:include page="../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript">var trRoot = "${trRoot}"</script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="/resources/js/utils/md5.js" ></script>
<script type="text/javascript" src="/resources/js/member/uptPass.js" ></script>
</body>
</html>
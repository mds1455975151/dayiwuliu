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
                <li><img src="${trRoot}/tianrui/images/zhmm12.jpg"><h4>1.验证身份</h4></li>
                <li><img src="${trRoot}/tianrui/images/zhmm22.jpg"><h4>2.重新设置密码</h4></li>
                <li><img src="${trRoot}/tianrui/images/zhmm31.jpg"><h4>3.修改成功</h4></li>
            </ul>
        </div>
        <div class="reg_zh">
            <div class="regbox5">
                <div class="reg_succont2">
                    <div class="reg_succ ">
                        <div> <img src="${trRoot}/tianrui/images/succ.png"><i>恭喜，新密码修改成功</i></div>
                        <div><a href="/trwuliu/Member/authenPage"><h3>点此立即去认证</h3></a></div>
                        <div><a href="/publicMember/index"><label>暂不认证，前往首页</label></a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!--登录内容部分end-->
<!-- 引用公共footer部分 -->
<jsp:include page="../common/member/footer_busi.jsp"></jsp:include>

</body>
</html>
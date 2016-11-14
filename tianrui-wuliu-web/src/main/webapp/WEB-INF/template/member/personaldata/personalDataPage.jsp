<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>大易物流平台-个人资料</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    
    <link href="${trRoot}/tianrui/css/imgcut.css"  rel="stylesheet">
    
<!-- 引用公共header部分 -->
<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
<!--内容部分begin-->
<div class="bghui">
<div class="container">
    <!--网站位置-->
    <div class="row">
            <div class="rz_line">
                <label>首页</label><span>></span> <label>账号</label><span>></span><label>个人中心</label>
            </div>
    </div>
    <div class="row">
       <!--个人中心左侧begin-->
        <jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心左侧end-->
        <!--个人中心右侧begin-->
        <div class="rz_right">
            <div class=" bgblue">
                <h2>个人资料</h2>
            </div>
            <div class="rz_box2">
                <div class="reg_tel">
                    <label>姓名</label>
                    <input type="text" placeholder="实名认证后自动获取" id="personal_name" value="${member.userName }" readonly><p id="message_personalName"></p>
                </div>
                <div class="reg_tel">
                    <label>性别</label>
                    <div class="geren_sex">
                        <label class="radio-inline">
                            <input type="radio" name="personal_sex" value="xy" <c:if test="${member.sex eq 'xy' }">checked</c:if>> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="personal_sex" value="xx" <c:if test="${member.sex eq 'xx' }">checked</c:if>> 女
                        </label>
                    </div>
                </div>
                <div class="reg_tel">
                    <label>昵称</label>
                    <input type="text" placeholder="请输入昵称" value="${member.nickname }" id="personal_nickname">
                </div>
                <div class="reg_tel">
                    <label>手机号</label>
                    <input type="text" id="personal_tel" value="${member.cellPhone}" readonly>
                </div>
                <div class="reg_tel">
                    <label>当前头像</label>
                    <div class="user_oldtx">
                    <!-- 
                    <c:if test="${member.avatarspath ne '' }">
                        <img id="currentImg" src="${member.avatarspath}" class="img-rounded">
                    </c:if>
                     -->
                     <c:if test="${member.avatarspath eq null }">
                        <img id="currentImg" src="${trRoot}/tianrui/images/tx.jpg" class="img-rounded">
                     </c:if>
                     <c:if test="${member.avatarspath ne null }">
                     	<img id="currentImg" src="${member.avatarspath}" class="img-rounded">
                     </c:if>
                    </div>
                    <input type="hidden" id="currentImgBack">
                </div>
                <div class="acctouxiang">
                    <button class="btn byellow tx_contr">点此修改头像</button>
                    <em>图片大小不超过3M，限上传1张，支持JPG、JPEG、PNG格式</em>
                </div>
                <!--修改头像begin-->
                <div class="acc_touxiang hide">
                    <div class="imageBox imageBox_tx">
                        <div class="thumbBox"></div>
                        <div class="spinner" style="display: none">Loading...</div>
                    </div>
                    <!--操作按钮begin-->
                    <div class="action wtx">
                        <!-- <input type="file" id="file" style=" width: 200px">-->
                        <div class="new-contentarea tc">
                            <a href="javascript:void(0)" class="upload-img">
                                <label for="upload-file">选择图片</label>
                            </a>
                            <input type="file" class="" name="upload-file" id="upload-file" />
                        </div>
                        <button id="btncancel" class="Btnsty_peyton peytonbg1 tx_cancel" ><i class="iconfont icon-huitui"></i></button>
                        <button id="btnCrop"  class="Btnsty_peyton peytonbg1" >裁切 </button>
                        <button id="btnZoomIn" class="Btnsty_peyton peytonbg1"><i class="iconfont icon-bf-add"></i></button>
                        <button id="btnZoomOut" class="Btnsty_peyton peytonbg1"><i class="iconfont icon-jianhao"></i></button>
                    </div>
                    <!--操作按钮end-->
                    <div class="tx_shouqi">
                        <img src="${trRoot}/tianrui/images/jtup.png">
                        <h4>收起</h4>
                    </div>
                </div>
                <!--修改头像end-->
            </div>
            <div class="tx_edit">
                <button type="submit" class="btn" id="personal_button">确认修改</button>
            </div>

        </div>
        <!--个人中心右侧end-->
        </div>
</div>
</div>
<!--内容部分end-->
<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js" ></script>
<script type="text/javascript" src="/resources/js/member/personaldata/personalDataPage.js" ></script>
</body>
</html>

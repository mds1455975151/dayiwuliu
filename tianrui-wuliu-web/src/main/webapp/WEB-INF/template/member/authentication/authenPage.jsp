<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>大易物流平台-实名认证</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    
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
                <h2>实名认证</h2>
            </div>
            <div class="rz_rline border_gray" id="perAuthen_div">
                <label>个人认证</label>
                <button type="button" class="btn  btn-primary btn-sm ml30"  disabled="disabled" id="perAuthen_status">未认证</button>
                <button type="button" class="btn  btn-primary btn-sm fr mr50" id="toPerAuthen_button">申请认证</button>
            </div>
            <div class="rz_rline border_gray" id="drivAuthen_div">
                <label>司机认证</label>
                <button type="button" class="btn  btn-primary btn-sm ml30 "  disabled="disabled" id="drivAuthen_status">未认证</button>
                <button type="button" class="btn  btn-primary btn-sm fr mr50" id="todrivAuthen_button">申请认证</button>
            </div>
            <div class="rz_rline border_gray" id="corpAuthen_div">
                <label>企业认证</label>
                <button type="button" class="btn  btn-primary btn-sm ml30 "  disabled="disabled" id="corpAuthen_status">未认证</button>
                <button type="button" class="btn  btn-primary btn-sm fr mr50" id="toCorpAuthen_button">申请认证</button>
            </div>
            <div class="rz_rline2 border_gray">
                <h3>重要声明</h3>
                <p>1、您的资料可能会被提交到国家公安机关及其下属单位进行证件真实性核实，我们不会将您的证件资料用作商业用途或非法提供给他人，但是如果出现法律纠纷它可能作为相关证据使用。
                    2、身份认证/驾驶证一旦通过，不能取消、删除、修改，请保管好您的密码，避免信息被违法分子所用。
                    3、企业身份认证一旦通过将与您对应的唯一帐户进行绑定，企业身份具有唯一性。</p>
            </div>
            <div class="rz_rline2 border_gray">
                <div class="rz_linecont1" >
                    <h3>个人认证示例</h3>
                    <div class="">
                        <p>
    需要本人身份证/驾驶证正面照片，注意以下几点：<br> 1、保证证件清晰不模糊。<br>
     2、需要完整的身份证正面，保证身份证边缘处于图片内部，姓名，证件号码，照片清晰。<br>
      3、可以将照片向前伸，将证件靠近相机镜头，对焦在证件上。 

</p>
                    </div>
                </div>
                <div class="rz_allimg">
               		 <img src="${trRoot}/tianrui/images/sfz.png"  >
                 	 <img src="${trRoot}/tianrui/images/jsz.jpg"  >
                 </div>
            </div>
            <div class="rz_rline2 border_gray">
                <div class="rz_linecont2">
                    <h3>企业认证示例</h3>
                    <div class="">
                        <p>
    	需要企业营业执照最新版本，注意以下几点：<br>
     1、最好上传企业营业执照的彩色电子版。 <br>
     2、营业执照复印件电子版需要加盖红色鲜章，并标注作物流资质认证用途。 <br>
     3、保证营业执照是最新版本，在工商局能够查询到，并且和执照上显示的信息一致。<br>
      4、拍照时营业执照纸张边界应处于照片内，右上角“编号”需显示出来。<br>
       5、电子版需要保证营业执照上的所有文字、二维码和登记机关签章都清晰可辨。 

			</p>
                    </div>
                </div>
                <img src="${trRoot}/tianrui/images/rz2.png" class="ml50">
            </div>
            <div class="rz_rline2 border_gray">
                <h3>后续还有什么</h3>
                <p>1、我们有专门的工作人员为您提交的资料进行核对认证工作，不出意外的话，您可以在3个工作日内收到审核结果的短信提示。<br>
				2、如果您没有通过资质认证，你可以查询实名认证的失败原因进行信息调整。 </p>
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
<script type="text/javascript" src="/resources/js/member/authentication/authenPage.js?0104" ></script>

</body>
</html>

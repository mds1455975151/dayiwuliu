<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>天瑞物流平台-企业认证</title>
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
               <h2>企业认证</h2>
           </div>
           <div class="rz_box">
               <div class="reg_tel">
                   <label><i>*</i>企业名称:</label>
                   <input type="text" placeholder="请输入企业名称" id="corpAuthen_name">
                   <p id="message_corpAuthenName"></p>
               </div>
               <!--手机输入end-->
               <div class="reg_tel">
                   <label><i>*</i>公司地址:</label>
                   <input type="text" placeholder="请输入公司地址" id="corpAuthen_address">
                   <p id="message_corpAuthenAddress"></p>
               </div>
               <div class="reg_tel">
                   <label><i>*</i>公司联系人:</label>
                   <input type="text" placeholder="请输入公司联系人" id="corpAuthen_linkman">
                   <p id="message_corpAuthenLinkman"></p>
               </div>
               <div class="reg_tel">
                   <label><i>*</i>联系人电话:</label>
                   <input type="text" placeholder="请输入联系人电话" id="corpAuthen_tel">
                   <p id="message_corpAuthenTel"></p>
               </div>
               <div class="reg_tel">
                   <label><i>*</i>营业执照号:</label>
                   <input type="text" placeholder="请输入营业执照号" id="corpAuthen_code">
                   <p id="message_corpAuthencode"></p>
               </div>
               <div class="reg_tel">
                   <label><i>*</i>营业执照:</label>
                   <!--营业执照默认图片-->
                   <div class="user_oldtx ">
                       <img id="busiLicenseImg" src="${trRoot}/tianrui/images/zhizhao.png" class="img-rounded">
                   </div>
                   <!-- 隐藏项：图片，剪切后回退用 -->
				   <input type="hidden" id="busiLicenseImgBack_1">
				   <input type="hidden" id="busiLicenseImgBack_2">
				   <input type="hidden" id="busiLicenseImgBack_3">
               </div>
               <!--企业认证模块-->
               <div class="acctouxiang2">
                   <button class="btn byellow tx_contr">上传图片</button>
                   <em>图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</em>
                   <!--企业认证修改头像begin-->
                   <div class="acc_txqiyerz">
                       <div class="imageBox imgBox_company">
                           <div class="thumbBox"></div>
                           <div class="spinner" style="display: none">Loading...</div>
                       </div>
                       <!--操作按钮begin-->
                       <div class="action wcompany">
                           <!-- <input type="file" id="file" style=" width: 200px">-->
                           <div class="new-contentarea tc">
                               <a href="javascript:void(0)" class="upload-img">
                                   <label for="upload-file">选择图片</label>
                               </a>
                               <input type="file" class="" name="upload-file" id="upload-file" />
                           </div>
                           <button id="btncancel" class="Btnsty_peyton peytonbg1 tx_cancel" ><i class="iconfont icon-huitui"></i></button>
                           <button  id="btnCrop"  class="Btnsty_peyton peytonbg1" > 裁切 </button>
                           <button  id="btnZoomIn" class="Btnsty_peyton peytonbg1"  > <i class="iconfont icon-bf-add"></i></button>
                           <button id="btnZoomOut" class="Btnsty_peyton peytonbg1" > <i class="iconfont icon-jianhao"></i></button>
                       </div>
                       <!--操作按钮end-->
                       <div class="tx_shouqi">
                           <img src="${trRoot}/tianrui/images/jtup.png">
                           <h4>收起</h4>
                       </div>
                   </div>
                   <!--企业认证修改头像end-->
                   <div class="rz_com_note"><h4>提示：请参考营业执照模板上图片样式上传您的营业执照</h4></div>
		           <div class="rz_combtn">
		               <input type="checkbox" checked="checked" id="corpAuthen_checkbox"><label>我已经阅读并同意<a target="_blank" id="corpAuthen_protocol">《天瑞物流平台服务协议》</a></label>
		               <button type="submit" class="btn btn-block" id="corpAuthen_button">申请认证</button>
		           </div>
               <!--企业认证模块end-->
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
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript" src="/resources/js/member/authentication/corpAuthenPage.js" ></script>

</body>
</html>

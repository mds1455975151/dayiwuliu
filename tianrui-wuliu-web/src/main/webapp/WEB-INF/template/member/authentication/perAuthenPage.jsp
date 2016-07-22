<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>天瑞物流平台-个人认证</title>
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
               <h2>个人认证</h2>
           </div>
           <div class="rz_box">
               <div class="reg_tel">
                   <label><i>*</i>姓名:</label>
                   <input type="text" placeholder="请输入姓名" id="perAuthen_name">
                   <p id="message_perAuthenName"></p>
               </div>
               <!--手机输入end-->
               <div class="reg_tel">
                   <label><i>*</i>证件号码:</label>
                   <input type="text" placeholder="请输入身份证号或驾驶证号" id="perAuthen_id">
                   <p id="message_perAuthenId"></p>
               </div>
               <div class="reg_tel">
                   <label><i>*</i>联系电话:</label>
                   <input type="text" disabled="disabled" placeholder="请输入联系电话" id="perAuthen_tel">
                   <p id="message_perAuthenTel"></p>
               </div>
               <div class="rz_personline">
                   <label><i class="coryel">*</i>身份证/驾照:</label>
                   <div class="rz_persontab">
                       <!--tab切换标题-->
                       <ul class="rz_personmenu">
                           <li class="rz_p1" style="cursor: pointer;">选择上传身份证</li>
                           <li class="rz_p2" style="cursor: pointer;">选择上传驾驶证</li>
                       </ul>
                       <!--tab切换标题end-->
                       <div style="font-size: 12px;color: #828282;">
                           <h4>图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</h4>
                       </div>
                       <!--tab切换的内容 begin-->
                       <div class="rz_personbox">
                           <!--身份证上传内容begin-->
                           <div class="rz_personcont hide">
                               <!--身份证默认图片-->
                               <div class="user_oldtx">
                                   <img id="idCardImg" src="${trRoot}/tianrui/images/sfz.png">
                               </div>
							   <!-- 隐藏项：图片，剪切后回退用 -->
							   <input type="hidden" id="idCardImgBack_1">
							   <input type="hidden" id="idCardImgBack_2">
							   <input type="hidden" id="idCardImgBack_3">
                               <!--身份证修改头像begin-->
                               <div class="rz_persontx">
                                   <div class="imageBox imgBox350 imgBox_pers">
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
                                       <button id="btncancel_p1" class="Btnsty_peyton peytonbg1" ><i class="iconfont icon-huitui"></i></button>
                                       <button  id="btnCrop_p1"  class="Btnsty_peyton peytonbg1" > 裁切 </button>
                                       <button  id="btnZoomIn_p1" class="Btnsty_peyton peytonbg1"  > <i class="iconfont icon-bf-add"></i></button>
                                       <button id="btnZoomOut_p1" class="Btnsty_peyton peytonbg1" > <i class="iconfont icon-jianhao"></i></button>
                                   </div>
                                   <!--操作按钮end-->
                                   <div class="tx_shouqi carshouq1">
                                       <img src="${trRoot}/tianrui/images/jtup.png">
                                       <h4>收起</h4>
                                   </div>
                               </div>
                       		   <div class="rz_com_note"><h4>提示：图片裁剪后的格式请参考上图身份证规格</h4></div>
                       		   <!--身份证修改头像end-->
                           </div>
                           <!--身份证上传内容end-->

                           <!--驾照上传内容begin-->
                           <div class="rz_personcont hide">
                               <div class="user_oldtx2">
							       <img id="driverCardImg" src="${trRoot}/tianrui/images/jz.png">
                               </div>
                               <!-- 隐藏项：图片，剪切后回退用 -->
							   <input type="hidden" id="driverCardImgBack_1">
							   <input type="hidden" id="driverCardImgBack_2">
							   <input type="hidden" id="driverCardImgBack_3">
                               <!--驾照修改头像begin-->
                               <div class="rz_persontx2 ">
                                   <div class="imageBox imgBox350 imgBox_pers2">
                                       <div class="thumbBox"></div>
                                       <div class="spinner" style="display: none">Loading...</div>
                                   </div>
                                   <!--操作按钮begin-->
                                   <div class="action wcompany">
                                       <!-- <input type="file" id="file" style=" width: 200px">-->
                                       <div class="new-contentarea tc">
                                           <a href="javascript:void(0)" class="upload-img">
                                               <label for="upload-file_p2">选择图片</label>
                                           </a>
                                           <input type="file" class="" name="upload-file" id="upload-file_p2" />
                                       </div>
                                       <button id="btncancel_p2" class="Btnsty_peyton peytonbg1" ><i class="iconfont icon-huitui"></i></button>
                                       <button  id="btnCrop_p2"  class="Btnsty_peyton peytonbg1" > 裁切 </button>
                                       <button  id="btnZoomIn_p2" class="Btnsty_peyton peytonbg1"  > <i class="iconfont icon-bf-add"></i></button>
                                       <button id="btnZoomOut_p2" class="Btnsty_peyton peytonbg1" > <i class="iconfont icon-jianhao"></i></button>
                                   </div>
                                   <!--操作按钮end-->
                                   <div class="tx_shouqi carshouq2">
                                       <img src="${trRoot}/tianrui/images/jtup.png">
                                       <h4>收起</h4>
                                   </div>
                               </div>
                               <div class="rz_com_note"><h4>提示：图片裁剪后的格式请参考上图驾驶证规格</h4></div>
                               <!--驾照修改头像end-->
                           </div>
                           <!--驾照上传内容end-->
                       </div>
                       <!--tab切换的内容 end-->
                   </div>
               </div>
           <div class="rz_combtn ml84">
               <input type="checkbox" checked="checked" id="perAuthen_checkbox"><label>我已经阅读并同意<a target="_blank" id="perAuthen_protocol">《天瑞物流平台服务协议》</a></label>
               <button type="submit" class="btn btn-block" id="perAuthen_button">申请认证</button>
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
<script type="text/javascript" src="/resources/js/member/authentication/perAuthenPage.js" ></script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>大易物流平台-审核结果</title>
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
	               <h2>资质审核</h2>
	           </div>
	           <div class=" rz_toubu">
	               <div class="rz_toububg1">
	                   <div class="rz_toububg2">
	                       <i class="iconfont icon-chahao"></i>
	                   </div>
	               </div>
	               <div class="rz_toubuz">
	                   <p id="percheckType">车辆开票认证</p>
	                   <p style="color:#a5a2a5;">认证失败</p>
	                   </div>
	           </div>
	            <div class="rz_cont">
                    <div class="rz_shenhe">
                        <div class="rz_status">
                            <i class="iconfont icon-wenjian"></i>
                            <label>认证失败原因：</label>
                        </div>
                        <p>
                            ${ticket.desc4 }
                        </p>
                    </div>
                    <div class="rz_shenhe ">
                        <div class="rz_failback">
                            <a >
                                <button class="btn " onclick="window.location.href='/trwuliu/Member/vehicleticket/kaipiaoUpdate?vehicleid=${ticket.vehicleid }'" id="authen_fail_return">重新认证</button>
                            </a>
                        </div>
                    </div>
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

</body>
</html>

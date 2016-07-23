<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>天瑞物流平台-审核结果</title>
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
	        <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
	        <!--个人中心右侧begin-->
	        <div class="rz_right">
	            <div class=" bgblue">
	                <h2>资质审核</h2>
	            </div>
	            <div class=" rz_toubu">
	                <span><a href=”#” onClick="javascript :history.back(-1);"><i class="iconfont icon-zuo"></i>认证状态</a></span><h4>/查看详情</h4>
	            </div>
	            <div class=" rz_scont" id="authenDetail_div">
	                <h4 class="border_gray">我的认证订单</h4>
	                <!-- if(审核中) then(数据) -->
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
<script type="text/javascript" src="/resources/js/member/authentication/authenListPage.js" ></script>

</body>
</html>

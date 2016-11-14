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
	            </div>
	            <div class="rz_scont2 ">
	                <div class="row mb10">
	                    <div class="w100 fl">
	                        <label>商品名称</label>
	                    </div>
	                    <div class="w500 fl">
	                        <label id="authenDetail_name"><!-- for data 个人认证/企业认证 --></label>
	                    </div>
	                </div>
	                <div class="row mb10">
	                    <div class="w100 fl">
	                        <label>认证状态</label>
	                    </div>
	                    <div class="w500 fl">
	                        <p id="authenDetail_content_p1"><!-- for data 审核中/认证完成 --></p>
	                        <p>第三方审核机构：河南XX</p>
	                        <p>认证工作时间：周一-周五8:00</p>
	
	                    </div>
	                </div>
	                <div class="row mb10">
	                    <div class="w100 fl">
	                        <label>提交时间</label>
	                    </div>
	                    <div class="w500 fl">
	                        <label id="authenDetail_creattime"><!-- for data --></label>
	                    </div>
	                </div>
	                <div class="row mb10">
	                    <div class="w100 fl">
	                        <label>认证时间</label>
	                    </div>
	                    <div class="w500 fl">
	                        <label id="authenDetail_audittime"><!-- for data --></label>
	                    </div>
	                </div>
	                <div class="row mb10">
	                    <div class="w100 fl">
	                        <label>订单号</label>
	                    </div>
	                    <div class="w500 fl">
	                        <label id="authenDetail_no"><!-- for data --></label>
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
<script type="text/javascript" src="/resources/js/member/authentication/authenDetailPage.js" ></script>

</body>
</html>

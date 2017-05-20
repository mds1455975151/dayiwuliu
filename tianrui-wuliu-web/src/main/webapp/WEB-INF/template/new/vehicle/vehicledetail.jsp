<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>new-我的运力</title>
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
                <div class="car_title bgblue">
                    <h2>我的车辆</h2>
                </div>
                <div class="mycar_dt">
                    <div style="width: 100%;margin-bottom: 30px;">
                        <div class="mycar_dtsolo">
                            <label>车牌号：豫s551</label>
                        </div>
                        <div class="mycar_dtsolo">
                            <label>认证时间：20154-122-2221-2121220</label>
                        </div>
                        <div class="mycar_dtsolo">
                            <label>随车电话：4545451122</label>
                        </div>
                        <div class="mycar_dtsolo">
                            <label>车辆类型：</label>
                        </div>
                        <div class="mycar_dtsolo">
                            <label>车长：5454</label>
                        </div>
                        <div class="mycar_dtsolo">
                            <label>载重：4512吨</label>
                        </div>
                        <div class="mycar_dtsolo">
                            <label>所有人姓名：4512吨</label>
                        </div>
                        <div class="mycar_dtsolo">
                            <label>认证状态：</label>
                            <span class="coloryello">认证成功</span>
                        </div>
                        <div class="mycar_dtsolo">
                            <label>联系电话：4512吨</label>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <div class="mycar_div">
                        <p>营运证号：证书编号：454545</p>
                        <img src="../images/yyzsl.jpg">
                    </div>
                    <div class="mycar_div">
                        <p>营运证号：证书编号：454545</p>
                        <img src="../images/yyzsl.jpg">
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
		<script type="text/javascript"> var trRoot = "${trRoot}";</script>
	</body>
</html>

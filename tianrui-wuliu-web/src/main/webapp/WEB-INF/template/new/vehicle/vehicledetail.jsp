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
	                            <label>车牌号：${vehicle.vehicleNo }</label>
	                        </div>
	                        <div class="mycar_dtsolo">
	                            <label>认证时间：${vehicle.authTime }</label>
	                        </div>
	                        <div class="mycar_dtsolo">
	                            <label>随车电话：${vehicle.vehicleMobile }</label>
	                        </div>
	                        <div class="mycar_dtsolo">
	                            <label>车辆类型：${vehicle.vehicleType }</label>
	                        </div>
	                        <div class="mycar_dtsolo">
	                            <label>车长：${vehicle.vehicleLen }</label>
	                        </div>
	                        <div class="mycar_dtsolo">
	                            <label>载重：${vehicle.vehicleLoad }</label>
	                        </div>
	                        <div class="mycar_dtsolo">
	                            <label>所有人姓名：${vehicle.vehicleOwner }</label>
	                        </div>
	                        <div class="mycar_dtsolo">
	                            <label>联系方式：${vehicle.vehicleOwnerTel }</label>
	                        </div>
	                        <div class="mycar_dtsolo">
	                            <label>认证类型：</label>
	                            <span class="coloryello">${vehicle.authstatus }</span>
	                        </div>
	                    </div>
	                    <div class="clear"></div>
	                    <div class="mycar_div">
	                        <p>车辆图片</p>
	                        <img src="${vehicle.vehicleImg }">
	                    </div>
	                    <div class="mycar_div">
	                        <p>营运证号：道路运输证号：${vehicle.roadTransportNo }</p>
	                        <p>营运证号：营运证号：${vehicle.taxiLicenseNo }</p>
	                        <img src="${vehicle.taxiLicenseImg }">
	                    </div>
	                    <div class="mycar_div">
	                        <p>行驶证：${vehicle.drivingLicenseNo }</p>
	                        <img src="${vehicle.drivingLicenseImg }">
	                    </div>
	                    <div class="mycar_div">
	                        <p>车辆等级证：${vehicle.vehicleGradeNo }</p>
	                        <img src="${vehicle.vehicleGradeImg }">
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

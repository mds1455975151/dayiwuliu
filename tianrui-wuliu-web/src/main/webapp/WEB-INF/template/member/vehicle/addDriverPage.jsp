<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>天瑞物流平台-添加司机</title>
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
		                    <h2>添加司机</h2>
		                </div>
		                <!-- 个人车辆begin -->
		                <div class="car_box" id="car_box">
		               		<div class="reg_tel">
		                        <label>司机账号：</label>
		                        <input type="text" id="driver_tel">
		                        <!-- 隐藏项：司机账号备份、司机主键、防止重复添加Flg -->
		                        <input type="hidden" id="driver_tel_back">
		                        <input type="hidden" id="driverId">
		                        <input type="hidden" id="isResultChangedFlg">
		                        <button type="submit" class="btn btnyello chezhu_btn" id="driver_search">搜索</button>
		                    </div>
		                    <div class="reg_tel" id="div_name">
		                        <label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
		                        <input type="text" id="driver_name" readonly>
		                    </div>
		                    <div class="reg_tel" id="div_remarkName">
		                        <label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
		                        <input type="text" placeholder="" id="dirver_remarkName">
		                    </div>
		                    <div class="reg_tel" id="div_dirverCard">
		                        <label>驾驶证：</label>
		                    </div>
		
		                    <!--行驶证照片内容begin-->
		                    <div class="car_photo">
		                        <div class="user_oldtx2 mb20" id="div_dirverImg">
									<img  
										align="absmiddle" style="width:166px; box-shadow:0px 0px 12px #7E7E7E;" id="driver_img">
		                        </div>
		                        <div class="car_addbtn" id="car_addbtn">
		                            <button type="submit" class="btn btnyello" id="driver_addBtn">添加</button>
		                            <button type="submit" class="btn btnblue" id="driver_cancelBtn">取消</button>
		                        </div>
		                    </div>
		                </div>
		                <!-- 个人车辆end -->
		            </div>
		            <!--个人中心右侧end-->
		        </div>
		    </div>
		</div>
		<!--内容部分end-->
		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
		<script type="text/javascript" src="/resources/js/member/vehicle/addDriverPage.js" ></script>
	</body>
</html>

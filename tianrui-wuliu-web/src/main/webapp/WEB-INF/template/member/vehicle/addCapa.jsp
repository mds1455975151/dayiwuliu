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
		                    <h2>添加运力</h2>
		                </div>
		                <!-- 添加运力begin -->
		                <div class="car_box">
		                    <div class="reg_tel">
		                        <label>车牌号：</label>
		                        <input id="vhicleno" type="text">
		                        <button type="button" onclick="onSearch()" class="btn btnyello chezhu_btn">搜索</button>
		                    </div>
		                    <!-- 点上面搜索后，此处内容才显示begin -->
		                    <div id="showtext" class="">
		                        <div class="reg_tel">
		                            <label>司机姓名：</label>
		                            <input type="hidden" id="vehicleowner" readonly="readonly">
		                            <input type="hidden" id="vehicleid" readonly="readonly">
		                            <input type="text" id="drivername" value="" readonly="readonly">
		                        </div>
		                        <div class="reg_tel">
		                            <label>司机电话：</label>
		                            <input type="text" id=drivertel value="" readonly="readonly">
		                        </div>
		                        <div class="reg_tel">
		                            <label>车辆类型：</label>
		                            <input type="text" id=vehicletype value="" readonly="readonly">
		                        </div>
		                    </div>
		                    <!-- 此处搜索内容显示end -->
		                    <div class="car_photo">
		                        <div class="car_addbtn">
		                            <button type="submit" onclick="addCapa();" class="btn btnyello">添加</button>
		                            <button type="submit" class="btn btnblue">取消</button>
		                        </div>
		                    </div>
		                </div>
		                <!-- 添加运力end -->
		            </div>
		            <!--个人中心右侧end-->
		        </div>
		    </div>
		</div>
		<!--内容部分end-->
		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
		<script type="text/javascript" src="/resources/js/member/vehicle/addCapa.js" ></script>
	</body>
</html>

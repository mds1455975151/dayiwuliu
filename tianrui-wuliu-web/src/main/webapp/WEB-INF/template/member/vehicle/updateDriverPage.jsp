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
	    <title>天瑞物流平台-修改车辆</title>
	    <meta name="keywords" content=" 天瑞"/>
	    <meta name="description" content="">
	    <meta name="author" content="">
    	<link href="${trRoot}/tianrui//css/imgcut.css" rel="stylesheet">
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
		                    <h2>修改车辆</h2>
		                </div>
		                <!-- 个人车辆begin -->
		                <div class="car_box">
		                    <div class="reg_tel">
		                        <input type="hidden" id="vehicleid" value="${vehicle.id }">
		                        <label>车牌号码：</label>
		                        <input type="text" value="${vehicle.vehiclePrefix }${vehicle.vehicleNo}" id="vehicle_add_vehiNo">
		                        <p id="message_vehiNo"></p>
		                    </div>
		                    <div class="reg_tel">
		                        <label>车辆类型：</label>
		                        <select class="form-control w350" id="vehicle_add_vehiType">
		                            
		                            <option value="0">请选择</option>
		                            <option <c:if test="${vehicle.vehicleType eq '1' }">selected="true"</c:if> value="1" >厢式</option>
		                            <option <c:if test="${vehicle.vehicleType eq '2' }">selected="true"</c:if> value="2" >板车</option>
		                            <option <c:if test="${vehicle.vehicleType eq '3' }">selected="true"</c:if> value="3" >冷藏</option>
		                        </select>
		                        <div class="clear"></div>
		                        <p id="message_vehiType"></p>
		                    </div>
		                    <div class="reg_tel">
		                        <label>车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长：</label>
		                        <input type="text" placeholder="" value="${vehicle.vehiLength }" id="vehicle_add_vehiLength">
		                        米
		                        <p id="message_vehiLength"></p>
		                    </div>
		                    <div class="reg_tel">
		                        <label>载&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重：</label>
		                        <input type="text" placeholder="" value="${vehicle.vehiWeight }" id="vehicle_add_vehiWeight">
		                        吨
		                        <p id="message_vehiWeight"></p>
		                    </div>
		                    <div class="reg_tel">
		                        <label>所有人姓名：</label>
		                        <input type="text" value="${vehicle.vehiOwnerName }" id="vehicle_add_vehiOwnerName">
		                        <p id="message_vehiOwnerName"></p>
		                    </div>
		                    <div class="reg_tel">
		                        <label>联系电话：</label>
		                        <input type="text" value="${vehicle.vehiOwnerTel }" id="vehicle_add_vehiTel">
		                        <p id="message_vehiTel"></p>
		                    </div>
		                    <div class="reg_tel">
		                        <label>车辆照片：</label>
		                        <button class="btn  byellow carbtn">上传</button>
		                        <em>图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</em>
		                    </div>
		                    <!--车辆照片内容begin-->
		                    <div class="car_photo">
		                        <!--车辆照片裁切后内容在这里显示-->
		                        <div class="user_oldtx1 mb20">
		                        	<img src="${vehicle.vehiHeadImgPath }">
		                        </div>
		                        <!-- 隐藏项：图片，剪切后回退用 -->
								<input type="hidden" id="vehiImgBack_1">
								<input type="hidden" id="vehiImgBack_2">
								<input type="hidden" id="vehiImgBack_3">
		                        <!--车辆照片修改begin-->
		                        <div class="car_edit">
		                            <div class="imageBox imgBox350 imgBox_car">
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
		                                    <input type="file" class="" name="upload-file" id="upload-file"/>
		                                </div>
		                                <button id="btncancel_p1" class="Btnsty_peyton peytonbg1"><i
		                                        class="iconfont icon-huitui"></i></button>
		                                <button id="btnCrop_p1" class="Btnsty_peyton peytonbg1"> 裁切</button>
		                                <button id="btnZoomIn_p1" class="Btnsty_peyton peytonbg1"><i
		                                        class="iconfont icon-bf-add"></i></button>
		                                <button id="btnZoomOut_p1" class="Btnsty_peyton peytonbg1"><i
		                                        class="iconfont icon-jianhao"></i></button>
		                            </div>
		                            <!--操作按钮end-->
		                            <div class="tx_shouqi carshouq1">
		                                <img src="${trRoot}/tianrui/images/jtup.png">
		                                <h4>收起</h4>
		                            </div>
		                        </div>
		                        <!--车辆照片修改end-->
		                    </div>
		                    <!--车辆照片内容begin-->
		
		                    <div class="reg_tel">
		                        <label>行驶证：</label>
		                        <button class="btn  byellow jszbtn">上传</button>
		                        <em>图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</em>
		                    </div>
		
		                    <!--行驶证照片内容begin-->
		                    <div class="car_photo">
		                        <div class="user_oldtx2 mb20">
		                        	<img src="${vehicle.vehiLicenseImgPath }">
		                        </div>
		                        <!-- 隐藏项：图片，剪切后回退用 -->
								<input type="hidden" id="vehiLiceImgBack_1">
								<input type="hidden" id="vehiLiceImgBack_2">
								<input type="hidden" id="vehiLiceImgBack_3">
		                        <!--行驶证照片修改begin-->
		                        <div class="car_edit2">
		                            <div class="imageBox imgBox350 imgBox_car2">
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
		                                    <input type="file" class="" name="upload-file" id="upload-file_p2"/>
		                                </div>
		                                <button id="btncancel_p2" class="Btnsty_peyton peytonbg1"><i
		                                        class="iconfont icon-huitui"></i></button>
		                                <button id="btnCrop_p2" class="Btnsty_peyton peytonbg1"> 裁切</button>
		                                <button id="btnZoomIn_p2" class="Btnsty_peyton peytonbg1"><i
		                                        class="iconfont icon-bf-add"></i></button>
		                                <button id="btnZoomOut_p2" class="Btnsty_peyton peytonbg1"><i
		                                        class="iconfont icon-jianhao"></i></button>
		                            </div>
		                            <!--操作按钮end-->
		                            <div class=" tx_shouqi carshouq2">
		                                <img src="${trRoot}/tianrui/images/jtup.png">
		                                <h4>收起</h4>
		                            </div>
		                        </div>
		                        <!--行驶证照片修改end-->
		                        <!--行驶证照片内容begin-->
		                        <div class="car_addbtn">
		                            <button type="submit" class="btn btnyello" id="vehicle_addBtn">修改</button>
		                            <button type="submit" class="btn btnblue" id="vehicle_cancelBtn">取消</button>
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
		<script type="text/javascript">var trRoot = "${trRoot}/tianrui/images/";</script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js" ></script>
		<script type="text/javascript" src="/resources/js/member/vehicle/updateVehiclePage.js" ></script>
		
	</body>
</html>

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
	    <title>我的车辆</title>
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
		                    <!-- 开票认证    开票认证失败  完全认证成功 -->
		                    <c:if test="${(vehicle.authType=='3' && vehicle.authstatus=='3')||(vehicle.authType=='2' && vehicle.authstatus=='1')}">
			                    <a href="ticketAuthView"><span>开票认证</span></a>
		                    </c:if>
		                    <!-- 完全认证  完全认证失败  临时认证成功 -->
		                    <c:if test="${(vehicle.authType=='2' && vehicle.authstatus=='3')||(vehicle.authType=='1' && vehicle.authstatus=='1')}">
			                    <a href="/trwuliu/vehicle/new/vheicle_w"><span>完全认证</span></a>
		                    </c:if>
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
	                        <div class="mycar_dtsolo"><input type="hidden" id="vehicleType" value="${vehicle.vehicleType }">
	                            <label>车辆类型：<span id="vehicleType_req"></span></label>
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
	                            <label>认证状态：</label>
	                            <span class="coloryello">${vehicle.authTypeStr }${vehicle.authstatusStr }</span>
	                        </div>
	                          <!-- 开票认证信息 -->
	                    <c:if test="${vehicle.authType=='3'}" >
	                       <div class="mycar_dtsolo">
	                            <label>使用性质：${vehicle.nature }</label>
	                       </div>
	                       <div class="mycar_dtsolo">
	                            <label>总质量：${vehicle.quality }</label>
	                       </div>
	                       <div class="mycar_dtsolo">
	                            <label>登记证书编号：${vehicle.certificateno }</label>
	                       </div>
	                       <div class="mycar_dtsolo">
	                            <label>证件号码：${vehicle.idcardno }</label>
	                       </div>
	                       <div class="mycar_dtsolo">
	                            <label>检验有效期止：${vehicle.expirydata }</label>
	                       </div>
	                       <div class="mycar_dtsolo">
	                            <label>车辆识别码：${vehicle.identification }</label>
	                       </div>
	                       <div class="mycar_dtsolo">
	                            <label>发动机号：${vehicle.motor }</label>
	                       </div>
	                    </c:if>
	                    <!--开票认证信息end  -->
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
	                        <p>车辆登记证：${vehicle.vehicleGradeNo }</p>
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
		<script type="text/javascript">
		$(function(){
			var vehicleType = $("#vehicleType").val();
			$("#vehicleType_req").html(checkVehicleType(vehicleType));
		});
			/** 车辆类型处理*/
			function checkVehicleType(type){
				switch (type) {
				case "1":
					return "厢式"
					break;
				case "2":
					return "板车"
					break;
				case "3":
					return "冷藏"
					break;
				case "4":
					return "散装罐车"
					break;
				case "5":
					return "半挂车"
					break;
				case "6":
					return "重型自卸货车"
					break;
				case "7":
					return "轻型自卸货车"
					break;
				case "8":
					return "三轮农用运输"
					break;
				case "9":
					return "四轮农用普通货车"
					break;
				case "10":
					return "四轮农用自卸车"
					break;
				case "11":
					return "小型轮式拖拉机"
					break;
				case "12":
					return "大型轮式拖拉机"
					break;

				default:
					return "暂无此类型"
					break;
				}
			}
		</script>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>大易物流平台-我的车辆</title>
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
			        <div class="car_right">
			            <div class="car_title bgblue">
			                <h2>我的车辆</h2>
			                <a href="${path}/trwuliu/Member/myVehicle/addLinVehiclePage"><span>临时认证</span></a>
			                <a href="${path}/trwuliu/Member/myVehicle/addVehiclePage"><span>完全认证</span></a>
			            </div>
			            <div class="car_search">
			                <ul>
			                    <li>
			                        <div class="addr-box">
			                            <p >姓名/手机号：</p>
			                            <input class="em" type="text" id="nameOrTel"/>
			                        </div>
			
			                    </li>
			                    <li>
			                        <div class="addr-box">
			                            <p >车牌前缀：</p>
			                            <input class="em" type="text" id="vehiFix"/>
			                        </div>
			
			                    </li>
			                    <li>
			                        <div class="addr-box">
			                            <p >车牌号：</p>
			                            <input class="em" type="text" id="vehiNo"/>
			                        </div>
			
			                    </li>
			                    <li>
			                        <div class="addr-box">
			                            <p >车型：</p>
			                            <select class="form-control" id="vehiType">
			                                <option value="0">请选择</option>
				                            <c:forEach items="${vt }" var="type">
											<option value="${type.wlcode }">${type.wlname }</option>
											</c:forEach>
			                            </select>
			                        </div>
			                    </li>
			                    <li>
			                        <button type="submit" class="btn btnyello" id="vehicle_searchBtn">搜索车辆</button>
			                    </li>
			                </ul>
			            </div>
			            <div style="height: 15px;background: #f0f0f0;"></div>
			            <div class="car_shuliang">
			                <h4 id="vehicle_count"></h4>
			            </div>
			            <div id="vehicle_none">
				            <div class="car_mycar" >
				                <table class="table table-hover" >
				                    <thead>
				                    <tr>
				                        <th colspan="2">车辆信息	</th>
				                        <th >车辆状态</th>
				                        <th >认证信息</th>
				                        <th >认证方式</th>
				                        <th> 操作</th>
				                        <th> 开票认证</th>
				                        <th> 认证信息</th>
				                    </tr>
				                    </thead>
				                    <tbody id="vehicle_tbody">
				                    
				                    	<!-- TO GET DATA! -->
				                    	
				                    </tbody>
				                </table>
				                <div class="goods_more pageMore" style="display:hide">
					                     <h4 onclick="addPage();">查看更多</h4>
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
		
		<div class="car_bdbox" style="display: none;" id="bindDiv">
	        <i class="iconfont icon-shibai"></i>
	        <div class="car_bdalert">
	            <input type="text" placeholder="司机姓名 " id="car_bdbox_driverName">
	            <input type="text" placeholder=" 联系方式" id="car_bdbox_driverTel">
	            <!-- 隐藏项：车辆司机主键、车辆主键、车辆类型名、车牌号 -->
	            <input type="hidden" id="car_bdbox_vehiDriverId">
	            <input type="hidden" id="car_bdbox_vehiId">
	            <input type="hidden" id="car_bdbox_vehiTypeName">
	            <input type="hidden" id="car_bdbox_vehiNo">
	             <input type="hidden" id="car_bdbox_alstatus">
	            <button class="btn btnyello" type="submit" onclick="getTheDriverOutOfRange();" >搜索</button>
	        </div>
	        <div class="car_scroll">
	            <ul id="driverUl">
	                
					<!-- TO GET DATA! -->

	            </ul>
	        </div>
	    </div>
		
		<!--车辆删除begin-->
		<div class="modal fade" id="deleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
		                        aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title" id=" ">删除车辆</h4>
		            </div>
		            <div class="modal-body">
		                <h4>您确定要删除吗？</h4>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-primary">确定</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            </div>
		        </div>
		    </div>
		</div>
		<!--车辆删除end-->
		
		<!-- 弹框begin -->
		<div class="modal fade" style="z-index:1055;" id="vehicleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document" style="width:400px;">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title" id="modal_del_title" id="modal_common_title">提示信息</h4>
		            </div>
		            <div class="modal-body">
		            	<h6>该司机已在安联系统存在，请核对并补充以下信息：</h6>
		            	<input type="hidden" id="driver_id">
		            	<input type="hidden" id="vehicle_id">
		            	<h4>司机姓名：<span id="driver_name"></span></h4>
		            	<h4>司机账号：<span id="driver_cellphone"></span></h4>
		            	<h4>司机身份证号：<span id="driver_idcard"></span></h4>
		            	<h4>车牌号：<span id="vehicle_no"></span></h4>
		            	<h4>司机安联账号：<input type="text" id="al_driver_id"></h4>
		            	<h4 style="color: red"><span id="error_massage"></span></h4>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-primary" onclick="saveAldriverVehicle()">确定</button>
		                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
		            </div>
		        </div>
		    </div>
		</div>
		<!-- 弹框end -->
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
		<script type="text/javascript" src="/resources/js/member/vehicle/myVehiclePage.js?09.16" ></script>
	</body>
</html>

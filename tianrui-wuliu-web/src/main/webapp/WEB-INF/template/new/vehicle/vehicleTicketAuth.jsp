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
	    <title>开票认证</title>
	    <meta name="keywords" content=" 天瑞"/>
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link href="${trRoot}/tianrui/css/imgcut.css" rel="stylesheet">
		<link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="${trRoot}/tianrui/js/My97DatePicker/WdatePicker.js"></script>
	    
    
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
							<h2>开票认证</h2>
						</div>
						<!-- 个人车辆begin -->
						<form id="vehickeTicket">
						<div class="car_box">
							<div class="reg_tel">
								<label> <span style="color: red">*</span>使用性质：</label> 
								<input type="radio" value="1" checked="checked" name="nature">营运
								<input type="radio" value="2" name="nature">非营运
								<p id="message_xingzhi"></p>
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>总质量：</label> 
								<input type="text" id="veh_quality" name="quality">千克
								<p id=""></p>
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>证件号码：</label> 
								<input type="text" id="veh_idcard" name="idcardno">
								<p id=""></p>
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>登记证书编号：</label> 
								<input type="text" id="veh_certificateno" name="certificateno">
								<p id=""></p>
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>检验有效期止：</label> 
								<input id="veh_expirydata" type="text" name="expirydata"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
											class="Wdate" style="width: 160px" placeholder="请选择日期" readonly/>
								<p id=""></p>
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>车辆识别码：</label> 
								<input type="text" id="veh_identification" name="identification">
								<p id=""></p>
							</div>
							<div class="reg_tel">
								<label> <span style="color: red">*</span>发动机号：</label> 
								<input type="text" id="veh_motor" name="motor">
								<p id=""></p>
							</div>
							<input type="hidden" id="veh_motorno" name="motorno">
							<div class="car_photo">
								<div class="car_addbtn">
									<button type="button" class="btn btnyello" id="vehicle_ticket_add">添加</button>
								</div>
							</div>
						</div>
						</form>
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
		$(function (){
			$('#myVehiclePage').addClass('selected');
		});
		$("#vehicle_ticket_add").on("click",function(){
			
			var veh_quality = $("#veh_quality").val();
			if($.trim(veh_quality)==""){
				alert("总质量不能为空");
				return;
			}
		/*	var veh_owner = $("#veh_owner").val();
			if($.trim(veh_owner)==""){
				alert("所有人不能为空");
				return;
			}*/
			var veh_idcard = $("#veh_idcard").val();
			if($.trim(veh_idcard)==""){
				alert("证件号码号不能为空");
				return;	
			}
			var veh_certificateno = $("#veh_certificateno").val();
			if($.trim(veh_certificateno)==""){
				alert("登记证书编号不能为空");
				return;	
			}
			
			//1900-2099
			var regexp = /^([1][9][0-9][0-9]|[2][0][0-9][0-9])(\-)([0][1-9]|[1][0-2])(\-)([0-2][0-9]|[3][0-1])$/;
			
			var veh_expirydata = $("#veh_expirydata").val();
			if(!regexp.test(veh_expirydata)){
				alert("检验有效期止时间格式有误");
				return;
			}
			var veh_identification = $("#veh_identification").val();
			if($.trim(veh_identification)==""){
				alert("车辆识别码不能为空");
				return;	
			}
			var veh_motor = $("#veh_motor").val();
			if($.trim(veh_motor)==""){
				alert("发动机号不能为空");
				return;	
			}
			$("#veh_motorno").val(veh_motor);
			
			confirm("操作提示","开票认证通过的车辆绑定开票认证通过的司机后无法解绑,是否确认操作?",function(){
				$("#vehicle_ticket_add").attr("disabled",true);
				 $.ajax({
					url : '/trwuliu/vehicle/new/ticketAtuh',// 跳转到 action
					data : $('#vehickeTicket').serialize(),
					type : "post",
					success : function(result) {
						if(result.code=="000000"){
							alert("操作成功");
							window.location.href="/trwuliu/vehicle/new/vehicledetail";
						}else{
							alert(result.error);
						}
						$("#vehicle_ticket_add").attr("disabled",false);
					}
				}); 
			});
		});
		</script>
	</body>
</html>

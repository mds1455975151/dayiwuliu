$(function(){
	$(".vehicle_class").show();
	$(".info_class").hide();
	$(".driver_class").hide();
});

//车辆信息点击事件
$(".vehicel_info").on("click",function(){
	$("#vehicel_img").attr('src',trRoot+"/tianrui/images/zhmm11.jpg"); 
	$("#info_img").attr('src',trRoot+"/tianrui/images/zhmm22.jpg"); 
	$("#driver_img").attr('src',trRoot+"/tianrui/images/zhmm32.jpg"); 
	
	$("#vehicel_class").addClass("colorblue");
	$("#info_class").removeClass("colorblue");
	$("#driver_class").removeClass("colorblue");
	
	$(".vehicle_class").show();
	$(".info_class").hide();
	$(".driver_class").hide();
});
//认证信息点击事件
$(".info_info").on("click",function(){
	if(vehicle_test()){
		$("#vehicel_img").attr('src',trRoot+"/tianrui/images/zhmm12.jpg"); 
		$("#info_img").attr('src',trRoot+"/tianrui/images/zhmm21.jpg"); 
		$("#driver_img").attr('src',trRoot+"/tianrui/images/zhmm32.jpg"); 
		
		$("#vehicel_class").removeClass("colorblue");
		$("#info_class").addClass("colorblue");
		$("#driver_class").removeClass("colorblue");
		
		$(".vehicle_class").hide();
		$(".info_class").show();
		$(".driver_class").hide();
	}
});
//驾驶员信息点击事件
$(".driver_info").on("click",function(){
	$("#vehicel_img").attr('src',trRoot+"/tianrui/images/zhmm12.jpg"); 
	$("#info_img").attr('src',trRoot+"/tianrui/images/zhmm22.jpg"); 
	$("#driver_img").attr('src',trRoot+"/tianrui/images/zhmm31.jpg"); 
	
	$("#vehicel_class").removeClass("colorblue");
	$("#info_class").removeClass("colorblue");
	$("#driver_class").addClass("colorblue");
	
	$(".vehicle_class").hide();
	$(".info_class").hide();
	$(".driver_class").show();
	if(vehicle_test()&&info_test()){
	}
});
/** 验证车辆信息是否完整*/
function vehicle_test(){
	if($("#vehicleNo_req").val()==""){
		alert("车牌号不能为空");
		return false;
	}
	if($("#vehicleType_req").val()==""){
		alert("请选择车辆类型");
		return false;
	}
	if($("#vehicleLen_req").val()==""){
		alert("车长不能为空");
		return false;
	}
	if($("#vehicleLoad_req").val()==""){
		alert("载重不能为空");
		return false;
	}
	if($("#vehicleOwner_req").val()==""){
		alert("所有人不能为空");
		return false;
	}
	if($("#vehicleOwnerTel_req").val()==""){
		alert("所有人联系方式不能为空");
		return false;
	}
	return true;
}
/** 验证信息是否完整*/
function info_test(){
	
	if($("#roadTransportNo_req").val()==""){
		alert("道路运输证号不能为空");
		return false;
	}
	if($("#taxiLicenseNo_req").val()==""){
		alert("经营许可证号不能为空");
		return false;
	}
	if($("#taxiLicenseImg_req").val()==""){
		alert("请上传经营许可证照片");
		return false;
	}
	if($("#taxiLicenseTerm_req").val()==""){
		alert("经营许可证有效期不能为空");
		return false;
	}
	if($("#vehicleImg_req").val()==""){
		alert("请上传车辆照片");
		return false;
	}
	if($("#drivingLicenseImg_req").val()==""){
		alert("请上传行驶证照片");
		return false;
	}
	return true;
}
/** 验证司机信息是否完整*/
function driver_test(){
	if($("#driverName_req").val()==""){
		alert("司机姓名不能为空");
		return false;
	}
	if($("#driverIdCard_req").val()==""){
		alert("司机身份证/驾驶证不能为空");
		return false;
	}
	if($("#driverBirthDate_req").val()==""){
		alert("司机出生日期不能为空");
		return false;
	}
	if($("#driverLinkTel_req").val()==""){
		alert("司机联系电话不能为空");
		return false;
	}
	if($("#driverIdCardAddr_req").val()==""){
		alert("司机身份证地址不能为空");
		return false;
	}
	if($("#driverCardFirstlicens_req").val()==""){
		alert("司机初次领证日期不能为空");
		return false;
	}
	if($("#driverCardLicenceorg_req").val()==""){
		alert("发证机关不能为空");
		return false;
	}
	if($("#driverCardRegDate_req").val()==""){
		alert("驾驶证注册日期不能为空");
		return false;
	}
	if($("#driverCardUsefullife_req").val()==""){
		alert("有效年限不能为空");
		return false;
	}
	
	if($("#driverCardImg_req_str").val()==""){
		alert("请上传驾驶证照片");
		return false;
	}
	
	if($("#driverIdCard_A_req_str").val()==""){
		alert("请上传身份证正面");
		return false;
	}
	
	if($("#driverIdCard_B_req_str").val()==""){
		alert("请上传身份证反面");
		return false;
	}
	return true;
}


//图片上传
function fileupload(id,remove){
	var file = $("#"+id)[0].files[0];
	var formData = new FormData();
	formData.append("file",file);
	// 后台处理
	$.ajax({
		url : '/upload/add',// 跳转到 action
		data : formData, 
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		type : "post",
		beforeSend : function() {
	        //请求前的处理
			$('#detail').modal({backdrop: 'static', keyboard: false});
			$("#showload").click();
		},
		success : function(result) {
			var ret = result.code;
			var msg = result.error;
			// 错误信息
			if (ret == 000000) {
				$("#"+id+"_str").val(result.data);
				$("."+remove).remove();
				$('#detail').modal("hide");
			}
		}
	});
}







$(function(){
	index_();
});
//页面初始状态
function index_(){
	$(".vehicle_class").show();
	$(".info_class").hide();
}

//完全认证点击事件
$("#add_class_w").on("click",function(){
	//点击后台进入车辆信息显示层
	$(".vehicel_info").click();
	//临时/完全认证样式控制
	$("#add_class_w").addClass("select");
	$("#add_class_l").removeClass("select");
	//隐藏input 控制车辆认证类型
	$("#authType_req").val("2");
	//显示输入框前小红点
	$(".authType_style").show();
	//完全认证输入框显示
	$(".renzhen").show();
	//临时认证输入框隐藏
	$(".lin_renzhen").hide();
});

//车辆信息点击事件
$(".vehicel_info").on("click",function(){
	$("#vehicel_img").attr('src',trRoot+"/tianrui/images/zhmm11.jpg"); 
	$("#info_img").attr('src',trRoot+"/tianrui/images/zhmm22.jpg"); 
	
	$("#vehicel_class").addClass("colorblue");
	$("#info_class").removeClass("colorblue");
	
	$(".vehicle_class").show();
	$(".info_class").hide();
});
//认证信息点击事件
$(".info_info").on("click",function(){
	if(vehicle_test()){
		$("#vehicel_img").attr('src',trRoot+"/tianrui/images/zhmm12.jpg"); 
		$("#info_img").attr('src',trRoot+"/tianrui/images/zhmm21.jpg"); 
		
		$("#vehicel_class").removeClass("colorblue");
		$("#info_class").addClass("colorblue");
		
		$(".vehicle_class").hide();
		$(".info_class").show();
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
	if($("#vehicleMobile_req").val()==""){
		alert("随车电话不能为空");
		return false;
	}
	return true;
}
/** 验证信息是否完整*/
function info_test(){
	//1-完全认证 2-临时认证
	var type=$("#authType_req").val();
	if(type=="1"&&$("#roadTransportNo_req").val()==""){
		alert("道路运输证号不能为空");
		return false;
	}
	if(type=="1"&&$("#taxiLicenseNo_req").val()==""){
		alert("经营许可证号不能为空");
		return false;
	}
	if(type=="1"&&$("#taxiLicenseImg_req_str").val()==""){
		alert("请上传经营许可证照片");
		return false;
	}
	if(type=="1"&&$("#taxiLicenseTerm_req").val()==""){
		alert("经营许可证有效期不能为空");
		return false;
	}
	if($("#vehicleImg_req_str").val()==""){
		alert("请上传车辆照片");
		return false;
	}
	if(type=="1"&&$("#drivingLicenseImg_req_str").val()==""){
		alert("请上传行驶证照片");
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

$(".saveVehicleReg").on("click",function(){
	$.ajax({
		url:"/common/vehicleReg/saveVehicleRegStep",
		data:$('#vehicleRegStep').serialize(),
		type:"post",
		success:function(ret){
			if(ret.code=="000000"){
				alert("添加成功");
			}else{
				alert(ret.error);
			}
		}
	});
});

/** 验证车牌号*/
$(".vehicleNo_req_rz").on("blur",function(){
	$("#vehicleNo_req").val($(this).val());
	$("#vehicleNo_req").click();
});

/** 验证车牌号*/
$("#vehicleNo_req").on("click",function(){
	//各个省份简称
	var CityNo="京津沪申渝冀晋辽吉黑苏浙皖闽赣鲁豫鄂湘粤琼川黔贵滇云陕秦甘陇青藏桂蒙宁新港澳台";
	// 车牌号正则表达式
	var vehiReg = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	// 车牌号输入值
	var vehiNo = $("#vehicleNo_req").val();
	
	if(CityNo.indexOf(vehiNo.substr(0,1))==-1){
		$("#vehicleNo_massage").html("车牌号省份不合法，请重新输入！");
	}else if (vehiNo == "") {
		$("#vehicleNo_massage").html("车牌号不能为空！");
	} else if (!vehiReg.test(vehiNo)) {
		$("#vehicleNo_massage").html("车牌号不合法，请重新输入！");
	} else {
		$.ajax({
			url : '/common/vehicleReg/checkVehicleNo',// 跳转到 action 
			data : {
				vehicleNo: vehiNo
			},
			type : "post",
			success : function(result){
				if (result.code != "000000") {
					$("#vehicleNo_massage").html("车牌号已存在，请勿重复添加！");
				} else {
					$("#vehicleNo_massage").html("");
				}
			}
		});
	}
});
//清除车牌号信息
function cleanVehicleNO(){
	$(".vehicleNo_req_rz").val("");
	$("#vehicleNo_req").val("");
	$("#vehicleNo_massage").html("");
}



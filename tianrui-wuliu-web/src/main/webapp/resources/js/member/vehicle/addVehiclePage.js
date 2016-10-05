/**
 * ==================================================================
 * 添加车辆功能与前后台交互 v1.0
 * ==================================================================
 * @author guyuke
 * @time 2016.06.13
 */

/** 车辆图片裁剪次数 */
 var vehiImgCount = 0;
/** 行驶证图片裁剪次数 */
 var vehiLiceCount = 0;

// 初始化处理
$(function() { 
	
    // 左侧导航选中效果
	$('#myVehiclePage').addClass('selected');
	
});

// 车牌号失去焦点事件
$("#vehicle_add_vehiNo").on("blur", function() {
	
	// 车牌号正则表达式
	var vehiReg = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	// 车牌号输入值
	var vehiNo = $("#vehicle_add_vehiNo").val();
	if (vehiNo == "") {
		$("#message_vehiNo").html("车牌号不能为空！");
	} else if (!vehiReg.test(vehiNo)) {
		$("#message_vehiNo").html("车牌号不合法，请重新输入！");
		/*$("#vehicle_add_vehiNo").focus();*/
	} else {
		$.ajax({
			url : PATH + '/trwuliu/Member/myVehicle/getMyVehicle',// 跳转到 action
			data : {
				//memberId: member_id,
				vehiWholeNo: vehiNo,
				status:'1'
			},
			type : "post",
			success : function(result){
				if (result.data != null && result.data.length > 0) {
					$("#message_vehiNo").html("车牌号已存在，请勿重复添加！");
					/*$("#vehicle_add_vehiNo").focus();*/
				} else {
					$("#message_vehiNo").html("");
				}
			}
		});
	}
});

// 车型失去焦点事件
$("#vehicle_add_vehiType").on("blur", function() {
	if ($("#vehicle_add_vehiType option:selected").val() != "0") {
		$("#message_vehiType").html("");
	}
});

// 车型名失去焦点事件
$("#vehicle_add_vehiTypeName").on("blur", function() {
	if ($("#vehicle_add_vehiTypeName").val() != "") {
		$("#message_vehiTypeName").html("");
	}
});

// 车长失去焦点事件
$("#vehicle_add_vehiLength").on("blur", function() {
	if ($("#vehicle_add_vehiLength").val() != "") {
		$("#message_vehiLength").html("");
	}
});

// 车重失去焦点事件
$("#vehicle_add_vehiWeight").on("blur", function() {
	if ($("#vehicle_add_vehiWeight").val() != "") {
		$("#message_vehiWeight").html("");
	}
});

// 所有者姓名失去焦点事件
$("#vehicle_add_vehiOwnerName").on("blur", function() {
	if ($("#vehicle_add_vehiOwnerName").val() != "") {
		$("#message_vehiOwnerName").html("");
	}
});

// 联系电话失去焦点事件
$("#vehicle_add_vehiTel").on("blur", function() {
	if ($("#vehicle_add_vehiTel").val() != "") {
		$("#message_vehiTel").html("");
	}
});

//【添加】按钮点击事件
$("#vehicle_addBtn").click(function() {
	
	// 车牌号
	var vehiNo = $("#vehicle_add_vehiNo").val();
	// 车型
	var vehiType = $("#vehicle_add_vehiType option:selected").val();
	// 车型名
	var vehiTypeName = $("#vehicle_add_vehiType option:selected").text();
	// 车长
	var vehiLength = $("#vehicle_add_vehiLength").val();
	// 车重
	var vehiWeight = $("#vehicle_add_vehiWeight").val();
	// 所有者姓名
	var vehiOwnerName = $("#vehicle_add_vehiOwnerName").val();
	// 联系电话
	var vehiTel = $("#vehicle_add_vehiTel").val();
	// 车辆图片路径
	var vehiImgPath = $("#vehicle").attr("src");
	// 行驶证图片路径
	var vehiLiceImgPath = $("#vehiLicense").attr("src");
	
	var flag = false;
	if (vehiNo == "") {
		$("#message_vehiNo").html("车牌号不能为空！");
		$("#vehicle_add_vehiNo").focus();
		flag = true;
	}
	if (vehiType == "0") {
		$("#message_vehiType").html("请选择合适的车型！");
		flag = true;
	}
	if (vehiLength == "") {
		$("#message_vehiLength").html("请输入车长！");
		flag = true;
	}
	if (vehiWeight == "") {
		$("#message_vehiWeight").html("请输入车重！");
		flag = true;
	}
	
	// 姓名，2个汉字以上，包含少数民族
	var nameReg =  /^\s*[\u4e00-\u9fa5]{1,}[\u4e00-\u9fa5.·]{0,15}[\u4e00-\u9fa5]{1,}\s*$/;
	if (vehiOwnerName == "") {
		$("#message_vehiOwnerName").html("请输入所有者姓名！");
		flag = true;
	} else if (!nameReg.test(vehiOwnerName)) {
		$("#message_vehiOwnerName").html("请输入合法的汉字姓名！");
		flag = true;
	}
	
	if (vehiTel == "") {
		$("#message_vehiTel").html("请输入联系电话！");
		flag = true;
	}
	if (vehiImgPath == undefined) {
		$("#modal_common_content").html("请上传车辆图片！");
		$("#commonModal").modal();
		flag = true;
	}
	if (vehiLiceImgPath == undefined) {
		$("#modal_common_content").html("请上传行驶证图片！");
		$("#commonModal").modal();
		flag = true;
	}
	
	if (flag) {
		return;
	}
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehicle/saveMyVehicle',// 跳转到 action
		data : {
			memberId: member_id,
			vehiWholeNo: vehiNo,
			vehicleType: vehiType,
			vehicleTypeName: vehiTypeName,
			vehiLength: vehiLength,
			vehiWeight: vehiWeight,
			vehiHeadImgPath: vehiImgPath,
			vehiOwnerName:vehiOwnerName,
			vehiOwnerTel:vehiTel,
			vehiLicenseImgPath: vehiLiceImgPath
		},
		type : "post",
		beforeSend : function() {
	        //请求前的处理
			$('#detail').modal({backdrop: 'static', keyboard: false});
			$("#showload").click();
		},
		success : function(result) {
			if (result.code == "000000") {
				window.location.href = PATH + "/trwuliu/Member/myVehicle/myVehiclePage";
			} else {
				$('#detail').modal("hide");
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
		}
	});
});

// 【取消】按钮点击事件
$("#vehicle_cancelBtn").click(function() {
	
	// 跳转我的车辆画面
	window.location.href = PATH + "/trwuliu/Member/myVehicle/myVehiclePage";
});


/**
 * ==================================================================
 * 添加车辆功能与前后台交互 v1.0
 * ==================================================================
 * @author guyuke
 * @time 2016.06.13
 */

// 初始化处理
$(function() { 
	
    // 左侧导航选中效果
	$('#myVehiclePage').addClass('selected');
	getVehilceNo();
	//清空file值
	$("#file_xkz").val("");
	$("#file_xkz_img").val("");
	$("#file_cel").val("");
	$("#file_cel_img").val("");
	$("#file_xsz").val("");
	$("#file_xsz_img").val("");
	$("#file_djz").val("");
	$("#file_djz_img").val("");
	
});
var flag ;
function getVehilceNo(){
	$("#message_vehiNo").html("");
	$("#lin_vehicle").show();
	$("#you_vehicle").hide();
	flag = "lin";
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehicle/getVehicleNo',// 跳转到 action
		data : {},
		type : "post",
		success : function(result) {
			if (result.code == "000000") {
				$("#vehicle_add_vehiNo").val(result.data);
			} 
		}
	});
}

function VehilceNo_yuan(){
	flag = "yuan";
	$("#lin_vehicle").hide();
	$("#you_vehicle").show();
}

var flagvehNo = true;

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
var flagLeng = true;
$("#vehicle_add_vehiLength").on("blur", function() {
	if ($("#vehicle_add_vehiLength").val() != "") {
		if(!isNaN($("#vehicle_add_vehiLength").val())){
			$("#message_vehiLength").html("");
			flagLeng = true;
		}else{
			$("#message_vehiLength").html("请输入数字");
			flagLeng = false;
		}
	}else{
		$("#message_vehiLength").html("车长不能为空");
		flagLeng = false;
	}
});
var flagWeight = true;
// 车重失去焦点事件
$("#vehicle_add_vehiWeight").on("blur", function() {
	if ($("#vehicle_add_vehiWeight").val() != "") {
		if( !isNaN($("#vehicle_add_vehiWeight").val())){
			$("#message_vehiWeight").html("");
			flagWeight = true;
		}else{
			$("#message_vehiWeight").html("请输入数字");
			flagWeight = false;
		}
	}else{
		$("#message_vehiWeight").html("车重不能为空");
		flagWeight = false;
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

//验证车牌号
$("#vehicle_add_vehiNo_2").on("blur", function() {
	// 车牌号
	var vehiNo = "";
	if(flag == "yuan"){
		vehiNo = $("#vehicle_add_vehiNo_2").val();
	}else if(flag == "lin"){
		vehiNo = $("#vehicle_add_vehiNo").val();
	}
	//车牌号正则表达式
	var vehiReg = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	if (!$.trim(vehiNo)) {
		$("#message_vehiNo").html("车牌号不能为空！");
		$("#vehicle_add_vehiNo").focus();
		$("#vehicle_add_vehiNo_2").focus();
		return;
	}else if (!vehiReg.test(vehiNo)) {
		$("#message_vehiNo").html("车牌号不合法，请重新输入！");
		$("#vehicle_add_vehiNo").focus();
		$("#vehicle_add_vehiNo_2").focus();
		return;
	}else {
		$.ajax({
			url : PATH + '/trwuliu/Member/myVehicle/vehicleVerify',// 跳转到 action 
			data : {
				vheicleFix: vehiNo.substr(0,2),
				vehicleNo:vehiNo.substr(2,7)
			},
			type : "post",
			success : function(result){
				if (result.code != "000000") {
					$("#message_vehiNo").html("车牌号已存在，请勿重复添加！");
					flagvehNo = false;
				} else {
					$("#message_vehiNo").html("");
					flagvehNo = true;
				}
			}
		});
	} 
});

//【添加】按钮点击事件
$("#vehicle_addBtn").click(function() {
	// 车牌号
	var vehiNo = "";
	if(flag == "yuan"){
		vehiNo = $("#vehicle_add_vehiNo_2").val();
	}else if(flag == "lin"){
		vehiNo = $("#vehicle_add_vehiNo").val();
	}
	
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
	//身份证号
	var identitycode = $('#vehicle_add_identitycode').val();
	//身份证图片
//	var file_sfz = $('#file_sfz')[0].files[0];
	// 车辆图片路径
	var file_cel = $("#file_cel_img").val();
	// 行驶证图片路径
	var file_xsz = $("#file_xsz_img").val();
	//道路运输证号
	var roadtransportcode = $('#vehicle_add_roadtransportcode').val();
	//道路运输证图片
//	var file_ysz = $('#file_ysz_img').val();
	//运营许可证号
	var opercode = $('#vehicle_add_opercode').val();
	//运营许可证图片
	var file_xkz = $('#file_xkz_img').val();
	//机动车登记证号
	var registcode = $('#vehicle_add_registcode').val();
	//机动车登记证图片
	var file_djz = $('#file_djz_img').val();
	
	// 车牌号正则表达式
	var vehiReg = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	
	if (!$.trim(vehiNo)) {
		$("#message_vehiNo").html("车牌号不能为空！");
		$("#vehicle_add_vehiNo").focus();
		$("#vehicle_add_vehiNo_2").focus();
		return;
	}else if (!vehiReg.test(vehiNo)) {
		$("#message_vehiNo").html("车牌号不合法，请重新输入！");
		$("#vehicle_add_vehiNo").focus();
		$("#vehicle_add_vehiNo_2").focus();
		return;
	} 
	if(!flagvehNo||!flagWeight||!flagLeng){
		$("#modal_common_content").html("数据有误请仔细查看重新输入");
		$("#commonModal").modal();
		$("#vehicle_add_vehiWeight").focus();
		return;
	}
	if (!$.trim(vehiType) || $.trim(vehiType) == "0") {
		$("#message_vehiType").html("请选择合适的车型！");
		$("#vehicle_add_vehiType").focus();
		return;
	}
	if (!$.trim(vehiLength)) {
		$("#message_vehiLength").html("请输入车长！");
		$("#vehicle_add_vehiLength").focus();
		return;
	}
	if (!$.trim(vehiWeight)) {
		$("#message_vehiWeight").html("请输入车重！");
		$("#vehicle_add_vehiWeight").focus();
		return;
	}
	// 姓名，2个汉字以上，包含少数民族
	var nameReg =  /^\s*[\u4e00-\u9fa5]{1,}[\u4e00-\u9fa5.·]{0,15}[\u4e00-\u9fa5]{1,}\s*$/;
	if (vehiOwnerName == "") {
		$("#message_vehiOwnerName").html("请输入所有者姓名！");
		$("#vehicle_add_vehiOwnerName").focus();
		return;
	} else if (!nameReg.test(vehiOwnerName)) {
		$("#message_vehiOwnerName").html("请输入合法的汉字姓名！");
		$("#vehicle_add_vehiOwnerName").focus();
		return;
	}
	
	var f = /^1[34578]\d{9}$/;
	if (!f.test(vehiTel)) {
		$("#message_vehiTel").html("请输入联系电话！");
		$("#vehicle_add_vehiTel").focus();
		return;
	}
	
	if (file_cel == "") {
		$("#modal_common_content").html("请上传车辆图片！");
		$("#file_cel_img").focus();
		$("#commonModal").modal();
		return;
	}
	var formData = new FormData();
	formData.append("drivingTime",$("#drivingTime").val());
	formData.append("memberId",member_id);
	formData.append("vehiWholeNo",vehiNo);
	formData.append("vehicleType",vehiType);
	formData.append("vehicleTypeName",vehiTypeName);
	formData.append("vehiLength",vehiLength);
	formData.append("vehiWeight",vehiWeight);
	formData.append("vehiHeadImgPath",file_cel);
	formData.append("vehiOwnerName",vehiOwnerName);
	formData.append("vehiOwnerTel",vehiTel);
	formData.append("vehiLicenseImgPath",file_xsz);
	formData.append("operimage",file_xkz);
	formData.append("opercode",opercode);
	formData.append("registimage",file_djz);
//	formData.append("roadtransportimage",file_ysz);
	formData.append("roadtransportcode",roadtransportcode);
	
	formData.append("vehiWidth",$("#vehicle_add_vehiWidth").val());
	formData.append("vehiHeight",$("#vehicle_add_vehiHeight").val());
	formData.append("desc3",$("#vehicle_add_desc3").val());
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehicle/saveLinVehicle',// 跳转到 action
		data : formData,
		type : "post",
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
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
//运营许可证图片
function xkzfile(){
	//运营许可证图片
	var file_xkz = $('#file_xkz')[0].files[0];
	if (!file_xkz) {
		$("#modal_common_content").html("请上传营运证图片！");
		$("#commonModal").modal();
		return;
	}
	var formData = new FormData();
	formData.append("file",file_xkz);
	$.ajax({
		url : PATH + '/upload/add',// 跳转到 action
		data : formData,
		type : "post",
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		beforeSend : function() {
	        //请求前的处理
			$('#detail').modal({backdrop: 'static', keyboard: false});
			$("#showload").click();
		},
		success : function(result) {
			if (result.code == "000000") {
				$("#file_xkz_img").val(result.data);
				alert("上传成功");
				$('.xkz').remove();
				$('#detail').modal("hide");
			}else{
				alert(result.error);
			}
		}
	});
}
//车辆图片路径
function celfile(){
	// 车辆图片路径
	var file_cel = $("#file_cel")[0].files[0];
	
	if (!file_cel) {
		$("#modal_common_content").html("请上传车辆照片！");
		$("#commonModal").modal();
		return;
	}
	var formData = new FormData();
	formData.append("file",file_cel);
	$.ajax({
		url : PATH + '/upload/add',// 跳转到 action
		data : formData,
		type : "post",
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		beforeSend : function() {
	        //请求前的处理
			$('#detail').modal({backdrop: 'static', keyboard: false});
			$("#showload").click();
		},
		success : function(result) {
			$("#file_cel_img").val("");
			if (result.code == "000000") {
				$("#file_cel_img").val(result.data);
				alert("上传成功");
				$('.cel').remove();
				$('#detail').modal("hide");
			}else{
				alert(result.error);
			}
		}
	});
}
//行驶证图片路径
function xszfile(){
	// 行驶证图片路径
	var file_xsz = $("#file_xsz")[0].files[0];
	
	if (!file_xsz) {
		$("#modal_common_content").html("请上传行驶证图片！");
		$("#commonModal").modal();
		return;
	}
	var formData = new FormData();
	formData.append("file",file_xsz);
	$.ajax({
		url : PATH + '/upload/add',// 跳转到 action
		data : formData,
		type : "post",
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		beforeSend : function() {
	        //请求前的处理
			$('#detail').modal({backdrop: 'static', keyboard: false});
			$("#showload").click();
		},
		success : function(result) {
			$("#file_xsz_img").val("");
			if (result.code == "000000") {
				$("#file_xsz_img").val(result.data);
				alert("上传成功");
				$('.xsz').remove();
				$('#detail').modal("hide");
			}else{
				alert(result.error);
			}
		}
	});
}
//登记证图片
function djzfile(){
	//机动车登记证图片
	var file_djz = $('#file_djz')[0].files[0];
	
	if (!file_djz) {
		$("#modal_common_content").html("请上传车辆登记证图片！");
		$("#commonModal").modal();
		return;
	}
	var formData = new FormData();
	formData.append("file",file_djz);
	$.ajax({
		url : PATH + '/upload/add',// 跳转到 action
		data : formData,
		type : "post",
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		beforeSend : function() {
	        //请求前的处理
			$('#detail').modal({backdrop: 'static', keyboard: false});
			$("#showload").click();
		},
		success : function(result) {
			$("#file_djz_img").val("");
			if (result.code == "000000") {
				$("#file_djz_img").val(result.data);
				alert("上传成功");
				$('.djz').remove();
				$('#detail').modal("hide");
			}else{
				alert(result.error);
			} 
		}
	});
}
//道路运输证图片
function yszfile(){
	//机动车登记证图片
	var file_ysz = $('#file_ysz')[0].files[0];
	
	if (!file_ysz) {
		$("#modal_common_content").html("请上传道路运输证图片！");
		$("#commonModal").modal();
		return;
	}
	var formData = new FormData();
	formData.append("file",file_ysz);
	$.ajax({
		url : PATH + '/upload/add',// 跳转到 action
		data : formData,
		type : "post",
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		beforeSend : function() {
	        //请求前的处理
			$('#detail').modal({backdrop: 'static', keyboard: false});
			$("#showload").click();
		},
		success : function(result) {
			$("#file_ysz_img").val("");
			if (result.code == "000000") {
				$("#file_ysz_img").val(result.data);
				alert("上传成功");
				$('.ysz').remove();
				$('#detail').modal("hide");
			}else{
				alert(result.error);
			} 
		}
	});
}
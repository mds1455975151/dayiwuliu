//车辆审核
function updateCar(){
	var veh_id = $("#veh_id").val();
	if(veh_id == ""){
		alert("数据有误，请稍候再试");
		return;
	}
	var veh_vehiclePrefix = $("#veh_vehiclePrefix").val();
	if(veh_vehiclePrefix == ""){	
		alert("车牌号前缀不能为空");
		return;
	}
	var veh_vehicleNo = $("#veh_vehicleNo").val();
	if(veh_vehicleNo == ""){	
		alert("车牌号不能为空");
		return;
	}
	var veh_vehiOwnerName = $("#veh_vehiOwnerName").val();
	if(veh_vehiOwnerName == ""){	
		alert("所有人姓名不能为空");
		return;
	}
	var veh_vehiOwnerTel = $("#veh_vehiOwnerTel").val();
	if(veh_vehiOwnerTel == ""){	
		alert("联系方式不能为空");
		return;
	}
	var veh_vehicleType = $("#veh_vehicleType").val();
	if(veh_vehicleType == "0"){	
		alert("车辆类型不能为空");
		return;
	}
	var veh_vehiWeight = $("#veh_vehiWeight").val();
	if(veh_vehiWeight == ""){	
		alert("载重不能为空");
		return;
	}
	var veh_vehiLength = $("#veh_vehiLength").val();
	if(veh_vehiLength == ""){	
		alert("长度不能为空");
		return;
	}
	var veh_vehiLicenseImgPath = $("#veh_vehiLicenseImgPath").val();
	if(veh_vehiLicenseImgPath == ""){	
		alert("请重新上传行驶证照片");
		return;
	}
	var veh_vehiHeadImgPath = $("#veh_vehiHeadImgPath").val();
	if(veh_vehiHeadImgPath == ""){	
		alert("请重新上传车辆照片");
		return;
	}
	var veh_opercode = $("#veh_opercode").val();
	if(veh_opercode == ""){	
		alert("营运证号不能为空");
		return;
	}
	var veh_operimage = $("#veh_operimage").val();
	if(veh_operimage == ""){	
		alert("请重新上传营运证");
		return;
	}
	var veh_registimage = $("#veh_registimage").val();
	if(veh_registimage == ""){	
		alert("请重新上传车辆登记证");
		return;
	}
	var pageNo = $("#pageNo").val();
	$.ajax({
		url: CONTEXTPATH+'/AdminMember/carBUquan',
		data:$('#form_vehicle').serialize(),
		type:"post",
		success: function(ret){
			if(ret.code=="000000"){
				window.location.href="/AdminMember/carManager?menuId=3&pageNo="+pageNo;
			}else{
				alert(ret.error);
			}
		}
	});
}
function fileUpload(type){
	var file = $("#file_"+type)[0].files[0];
	var formData = new FormData();
	formData.append("file",file);
	$.ajax({
		type:"post",
		url:"/upload/add",
		data:formData,
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		success: function(rs) {
			if(rs.code=="000000"){
				$("#veh_"+type).val(rs.data);
			}else{
				alert(rs.error);
			}
		}
	});
}
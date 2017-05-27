$(function (){
	$('#myVehiclePage').addClass('selected');
});

$("#vehicle_ticket_add").on("click",function(){
	if($("#taxiLicenseNo_req").val()==""){
		alert("请输入经营许可证号");
		return;
	}
	if($("#taxiLicenseImg_req").val()==""){
		alert("请上传经营许可证图片");
		return;
	}
	if($("#roadTransportNo_req").val()==""){
		alert("请输入道路运输证号");
		return;
	}
	if($("#vehicleImg_req_str").val()==""){
		alert("请上传车辆照片");
		return;
	}
	if($("#drivingLicenseImg_req_str").val()==""){
		alert("请上传行驶证照片");
		return;
	}
	if($("#vehicleGradeImg_req_str").val()==""){
		alert("请上传车辆登记证照片");
		return;
	}
	$.ajax({
		url:"/trwuliu/vehicle/new/vehicle_w_save",
		type:"post",
		data:$('#vehickeTicket').serialize(),
		success:function(ret){
			if(ret.code=="000000"){
				alert("添加成功");//
				window.location.href="/trwuliu/vehicle/new//vehicledetail";
			}
		}
	});
});

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
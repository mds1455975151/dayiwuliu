$(function (){

});

$("#personal_button").on("click",function(){
	if($("#driverName_req").val()==""){
		alert("请输入姓名");
		return;
	}
	if($("#driverBirthDate_req").val()==""){
		alert("请选择出生日期");
		return;
	}
	if($("#driverLinkTel_req").val()==""){
		alert("请输入联系电话");
		return;
	}
	if($("#driverIdCard_req").val()==""){
		alert("请输入证件号");
		return;
	}
	if($("#driverIdCardAddr_req").val()==""){
		alert("请输入身份证地址");
		return;
	}
	if($("#driverCardFirstlicens_req").val()==""){
		alert("请选择初次领证日期");
		return;
	}
	if($("#driverCardLicenceorg_req").val()==""){
		alert("请输入发证机关");
		return;
	}
	if($("#driverCardRegDate_req").val()==""){
		alert("请选择驾驶证注册日期");
		return;
	}
	if($("#driverCardUsefullife_req").val()==""){
		alert("请输入有效年限");
		return;
	}
	if($("#driverCardType_req").val()==""){
		alert("请选择准驾车型");
		return;
	}
	if($("#driverCardImg_req_str").val()==""){
		alert("请上传驾驶证图片");
		return;
	}
	$.ajax({
		url:"/trwuliu/vehicle/new/driverAdd",
		type:"post",
		data:$('#driver_save').serialize(),
		success:function(ret){
			if(ret.code=="000000"){
				alert("添加成功");
				window.location.href="/trwuliu/vehicle/new/driverpage";
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

$("#member_bank_add").on("click",function(){
	if($("#bankcard_req").val()==""){
		alert("银行卡账户不能为空");
		return;
	}
	if($("#idname_req").val()==""){
		alert("持卡人名称不能为空");
		return;
	}
	if($("#bankname_req").val()==""){
		alert("开户行名称不能为空");
		return;
	}
	if($("#bankimg_req_str").val()==""){
		alert("请上传银行卡照片");
		return;
	}
	$.ajax({
		url:"/trwuliu/bank/card/save",
		type:"post",
		data:$('#member_bank').serialize(),
		success:function(ret){
			if(ret.code=="000000"){
				alert("添加成功");
			}else{
				alert(ret.error)
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
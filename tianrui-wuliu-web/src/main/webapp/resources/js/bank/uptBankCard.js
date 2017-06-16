$(function(){
	$(".text_class").hide();
	$(".select_class").show();
	$("#showType").val("2");
});
//编辑按钮点击事件
$(".text_sele").on("click",function(){
	$("#showType").val("1");
	$(".text_class").show();
	$(".select_class").hide();
});

//选择按钮点击事件
$(".text_but").on("click",function(){
	$("#showType").val("2");
	$(".text_class").hide();
	$(".select_class").show();
});
$("#member_bank_add").on("click",function(){
	
	if($("#showType").val()=="1"){
		var dd = $("#desc1_text").val();
		if(dd==""){
			alert("请输入开户行");
			return;
		}
		$("#desc1_req").val(dd);
	}
	if($("#showType").val()=="2"){
		var ff = $("#desc1_select").val();
		if(ff=="请选择"){
			alert("请选择开户行");
			return;
		}
		$("#desc1_req").val(ff);
	}
	
	if($("#bankcard_req").val()==""){
		alert("银行卡账户不能为空");
		return;
	}
	if($("#idname_req").val()==""){
		alert("持卡人名称不能为空");
		return;
	}
	if($("#idcard_req").val()==""){
		alert("身份证号不能为空");
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
		url:"/trwuliu/bank/card/uptAutid",
		type:"post",
		data:$('#member_bank').serialize(),
		success:function(ret){
			if(ret.code=="000000"){
				window.location.href="/trwuliu/bank/card/page";
			}else{
				alert(ret.error)
			}
		}
	});
	
});
$(".select2").select2(); 
$("#bankcard_req").on("blur",function(){
	index();
});
function index(){
	$.ajax({
		url:"/trwuliu/bank/card/bankCardType",
		type:"post",
		data:{"bankcode":$("#bankcard_req").val()},
		success:function(ret){
			$("#bankname_req").val(ret.data);
			bankAddress(ret.data);
		}
	});
}
function bankAddress(type){
	$.ajax({
		url:"/trwuliu/bank/card/findAddress",
		data:{"name":type},
		type:"post",
		success:function(ret){
			var data = ret.data;
			$("#desc1_select").empty();
			$("#desc1_select").append("<option>请选择</option>");
			for (var a = 0; a < data.length; a++) {
				$("#desc1_select").append("<option value='"+data[a].name+"'>"+data[a].name+"</option>");
			}
		}
	});
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
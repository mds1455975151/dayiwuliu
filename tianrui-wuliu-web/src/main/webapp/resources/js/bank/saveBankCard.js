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

function validate(params){
	if(!params.bankcard){
		alert("银行卡账户不能为空");
		return;
	}
	if(!params.bankSubbranchId && !params.bankSubbranchName){
		alert("请选择或输入开户行名称");
		return;
	}
	if(!params.bankimg){
		alert("请上传银行卡照片");
		return;
	}
	return params;
}

$("#member_bank_add").on("click",function(){
	if($("#showType").val()=="1"){
		var dd = $("#desc1_text").val();
		$("#desc1_req").removeAttr('bankSubbranchId').val(dd);
	}
	if($("#showType").val()=="2"){
		var ff = $("#desc1_select").val(); ff = $.trim(ff);
		$("#desc1_req").attr('bankSubbranchId', ff).val('');
	}
	var bankcarkno = $("#bankcard_req").val(); bankcarkno = $.trim(bankcarkno);
	var bankSubbranchId = $("#desc1_req").attr('bankSubbranchId'); bankSubbranchId = $.trim(bankSubbranchId);
	var bankSubbranchName = $("#desc1_req").val(); bankSubbranchName = $.trim(bankSubbranchName);
	var bankimg = $("#bankimg_req_str").val(); bankimg = $.trim(bankimg);
	
	
	var bankname = $("#selectBankName").val(); bankname=$.trim(bankname);
	
	var params = {
			desc4: 1,
			bankcard: bankcarkno,
			bankSubbranchId: bankSubbranchId,
			bankSubbranchName: bankSubbranchName,
			bankimg: bankimg,
			bankTypeId:bankname
	};
	if (validate(params)) {
		$.ajax({
			url:"/trwuliu/bank/card/save",
			type:"post",
			data:params,
			success:function(ret){
				if(ret.code=="000000"){
					window.location.href="/trwuliu/bank/card/page";
				}else{
					alert(ret.error)
				}
			}
		});
	}
});

$(".select2").select2(); 
$("#selectBankName").on("blur",function(){
	$.ajax({
		url:"/trwuliu/bank/card/bankCardOnly",
		type:"post",
		data:{"bankcode":$("#bankcard_req").val()},
		success:function(ret){
				
		}
	});
});
window.onload=selectBankName;
function selectBankName(){
	$.ajax({
		url:"/trwuliu/bank/card/bankCardType",
		type:"post",
		success:function(ret){
				var data = ret.data;
				$("#selectBankName").empty();
				$("#selectBankName").append("<option value=''>请选择</option>");
				for (var a = 0; a < data.length; a++) {
					$("#selectBankName").append("<option value='"+data[a].id+"'>"+data[a].name+"</option>");
				}
			}
		});
}
//联行号
$("#desc1_select").change(function(){	
	var id = $("#desc1_select").val();
	$.ajax({
		url:"/trwuliu/bank/card/findBankNum",
		type:"post",
		data:{"id":id},
		success:function(ret){
			var data = ret.data;
			$("#bankLineNumber").empty();
			$("#bankLineNumber").val(data.bankLineNumber);
			
		}
	});
});
//开户行名称
$("#selectBankName").change(function(){
	var id=$("#selectBankName option:selected").val();
	$.ajax({
		url:"/trwuliu/bank/card/findAddress",
		type:"post",
		data:{"id":id},
		success:function(ret){
			var data = ret.data;
			$("#desc1_select").empty();
			$("#desc1_select").append("<option value=''>请选择</option>");
			for (var a = 0; a < data.length; a++) {
				$("#desc1_select").append("<option value='"+data[a].id+"'>"+data[a].name+"</option>");
				$("#bankLineNumber").val(data[a].bankLineNumber );
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
/**
 * ==================================================================
 * 会员注册功能与后台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.04.25
 */

// 初始化处理
$(function() { 
	// 阅读协议绑定跳转链接
	$("#register_protocol").attr("href", PATH + "/publicMember/protocol");
});

// 验证手机号
$("#register_tel").blur(function () {
	// 手机号11位并且前三位只能为大陆手机号段
	var reg = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
	var tel = $("#register_tel").val();
	$("#message_tel").removeClass("reg_hui");
	if (!reg.test(tel)) {
		$("#message_tel").html("请输入正确的11位手机号");
		return ;
	}
	
	$("#message_tel").addClass("reg_hui");
	$("#message_tel").html("请输入大陆手机号，用于登陆、找回密码");
});

// 验证第一个输入密码
$("#register_firstpsw").blur(function () {
	// 密码只能为字母或数字
	var reg = /^[A-Za-z0-9]+$/;
	var firstpsw = $("#register_firstpsw").val();
	var confirmpsw = $("#register_confirmpsw").val();
	$("#message_firstpsw").removeClass("reg_hui");
	
	if (confirmpsw != "" && firstpsw != confirmpsw) {
		$("#message_firstpsw").html("两次输入的密码不一致,请重新输入...");
		return;
	} else if ($.trim(firstpsw) == "") {
		$("#message_firstpsw").html("密码不能为空,请输入...");
		return;
	} else if (!reg.test(firstpsw)) {
		$("#message_firstpsw").html("密码只能是数字或字母,请重新输入...");
		return;
	} else if ($.trim(firstpsw).length < 6) {
		$("#message_firstpsw").html("密码长度少于6位,请重新输入...");
		return;
	} else if ($.trim(firstpsw).length > 20) {
		$("#message_firstpsw").html("密码长度大于20位,请重新输入...");
		return;
	}
	
	$("#message_firstpsw").addClass("reg_hui");
	$("#message_firstpsw").html("密码为字母、数字或者英文符号，6-20位，区分大小写!");
});

// 验证第二个输入密码
$("#register_confirmpsw").blur(function () {
	var reg = /^[A-Za-z0-9]+$/;
	var confirmpsw = $("#register_confirmpsw").val();
	var firstpsw = $("#register_firstpsw").val();
	
	if (firstpsw != "" && confirmpsw != firstpsw) {
		$("#message_confirmpsw").html("两次输入的密码不一致,请重新输入...");
		return;
	} else if ($.trim(confirmpsw) == "") {
		$("#message_confirmpsw").html("密码不能为空,请输入...");
		return;
	} else if (!reg.test(confirmpsw)) {
		$("#message_confirmpsw").html("密码只能是数字或字母,请重新输入...");
		return;
	} else if ($.trim(confirmpsw).length < 6) {
		$("#message_confirmpsw").html("密码长度少于6位,请重新输入...");
		return;
	} else if ($.trim(confirmpsw).length > 20) {
		$("#message_confirmpsw").html("密码长度大于20位,请重新输入...");
		return;
	}
	$("#message_confirmpsw").empty();
});

//【获取验证码】按钮绑定点击事件
$("#idcode_button").click(function() {
	var tel = $("#register_tel").val();
	if (tel == "") {
		$("#message_tel").removeClass("reg_hui");
		$("#message_tel").html("请输入手机号");
		return;
	}
	$.ajax({
		url : PATH + '/publicMember/registerCode',// 跳转到 action
		data : {telnum:tel,type:"0"},
		type : "post",
		success : function(result) {
			if( result && result.code =="000000" ){
				$("#modal_common_content").html("手机验证已发送至手机！");
			}else if(result && result.error){
				$("#modal_common_content").html(result.error);
			}
			$("#commonModal").modal();
		}
	});
});

//【立即注册】按钮绑定点击事件
$("#register_button").click(function() {
	if(!$("input[type='checkbox']").is(':checked')){
		$("#modal_common_content").html("请同意天瑞物流平台服务协议");
		$("#commonModal").modal();
		return;
	}
	// 手机
	var tel = $("#register_tel").val();
	// 密码
	var psw = $("#register_firstpsw").val();
	// 确认密码
	var confirmPsw = $("#register_confirmpsw").val();
	// 短信验证码
	var idCode = $("#register_idcode").val();
	// 邀请码
	var inviteCode = $("#register_invitecode").val();
	if (tel == "") {
		$("#modal_common_content").html("手机号不能为空！");
		$("#commonModal").modal();
		return;
	} else if (psw == "" || confirmPsw == "") {
		$("#modal_common_content").html("密码不能为空！");
		$("#commonModal").modal();
		return;
	} else if (idCode == "") {
		$("#modal_common_content").html("短信验证码不能为空！");
		$("#commonModal").modal();
		return;
	}
	var md5Psw = hex_md5($.trim(psw));
	$.ajax({
		url : PATH + '/publicMember/register',// 跳转到 action
		data : {telnum:tel, passWord:md5Psw, registerCode:idCode, referrerTel:inviteCode},
		type : "post",
		success : function(result) {
			if( result && result.code =="000000" ){
				window.location.href = PATH + "/trwuliu/Member/authenPage";
			}else if(result && result.error){
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
		}
	});
});

//【获取验证码】按钮绑定点击事件(忘记密码)
$("#getLostCode").click(function() {
	var tel = $("#accountTel").val();
	if (tel == "") {
		$("#message_tel").removeClass("reg_hui");
		$("#message_tel").html("请输入手机号");
		return;
	}
	$.ajax({
		url : PATH + '/publicMember/registerCode',// 跳转到 action
		data : {telnum:tel,type:"1"},
		type : "post",
		success : function(result) {
			if( result && result.code =="000000" ){
				$("#modal_common_content").html("手机验证已发送至手机！");
			}else if(result && result.error){
				$("#modal_common_content").html(result.error);
			}
			$("#commonModal").modal();
		}
	});
});

// 忘记密码【下一步】按钮绑定点击事件
$("#resetPass").click(function() {
	// 手机
	var tel = $("#accountTel").val();
	// 短信验证码
	var idCode = $("#resetPassCode").val();
	if (tel == "") {
		$("#modal_common_content").html("手机号不能为空！");
		$("#commonModal").modal();
		return;
	} else if (idCode == "") {
		$("#modal_common_content").html("短信验证码不能为空！");
		$("#commonModal").modal();
		return;
	}
	$.ajax({
		url : PATH + '/publicMember/resetPass',// 跳转到 action
		data : {telnum:tel,registerCode:idCode},
		type : "post",
		success : function(result) {
			if( result && result.code =="000000" ){
				window.location.href = PATH + "/publicMember/resetPassPage?tel="+tel +"&registerCode="+idCode;
			}else if(result && result.error){
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
		}
	});
});

//【重置密码】按钮绑定点击事件
$("#resetP").click(function() {
    var reg = /^[A-Za-z0-9]+$/;
	// 手机
	var tel = $("#accountTel").val();
	// 密码
	var psw = $("#register_p").val();
	// 验证码
	var registerCode = $("#registerCode").val();
	// 确认密码
	var confirmPsw = $("#register_pass").val();
	if (tel == "") {
		$("#modal_common_content").html("重置密码手机号不能为空！");
		$("#commonModal").modal();
		return;
	} else if (psw == "" || confirmPsw == "") {
		$("#modal_common_content").html("密码不能为空！");
		$("#commonModal").modal();
		return;
	} 
	if(!reg.test(psw)) {
		$("#message1").html("密码只能是数字或字母,请重新输入...");
		return ;
	}else if(psw.length < 6){
		$("#message1").html("密码长度少于6位,请重新输入...");
		return ;
	}
	if (psw != confirmPsw) {
		$("#message1").html("密码和确认密码不一致，请重新输入...");
		return ;
	}
	var md5Psw = hex_md5($.trim(psw));
	$.ajax({
		url : PATH + '/publicMember/updatePass',// 跳转到 action
		data : {telnum:tel, 
		    	registerCode:registerCode,
		    	passWord:md5Psw},
		type : "post",
		success : function(result) {
			if( result && result.code =="000000" ){
				window.location.href = PATH + "/publicMember/resetpsu";
			}else if(result && result.error){
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
		}
	});
});

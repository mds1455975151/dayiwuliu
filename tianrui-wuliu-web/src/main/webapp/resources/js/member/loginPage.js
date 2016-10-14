/**
 * ==================================================================
 * 会员登录功能与后台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.04.25
 */

// 初始化处理
$(function() { 
	// 界面导航选中状态
	$("#header_home").siblings().removeClass("active");
	$("#header_home").addClass("active");
});

/*// 验证手机号
$("#login_tel").blur(function () {
	// 获取输入的手机号
	var login_tel = $("#login_tel").val();
	
	if (login_tel == "") {
		$("#message_loginTel").html("手机号不能为空");
		return ;
	}
});

// 验证密码
$("#login_psw").blur(function () {
	// 获取输入的密码
	var login_psw = $("#login_psw").val();
	
	if (login_psw == "") {
		$("#message_loginPsw").html("密码不能为空");
		return ;
	}
});*/

// 个人登录按钮事件
$("#login_button").click(function() {
	// 获取输入的手机号
	var login_tel = $("#login_tel").val();
	// 获取输入的密码
	var login_psw = $("#login_psw").val();
	
	if (login_tel == "") {
		$("#message_loginError").html("手机号不能为空");
		return ;
	} else if (login_psw == "") {
		$("#message_loginError").html("密码不能为空");
		return ;
	}
	
	// 密码加密
	var md5Psw = hex_md5($.trim(login_psw));
	
	$.ajax({
		url : PATH + '/publicMember/login',// 跳转到 action
		data : {
					telnum:login_tel, 
					passWord:md5Psw, 
			   },
		type : "post",
		success : function(result) {
			if( result && result.code =="000000" ){
				var _role=result.data;
				var url=PATH + "/trwuliu/Member/chooseRole";
				if( _role!=null ){
					url=PATH+"/trwuliu/Member/message/message";	
				}
				window.location.href = url;
			}else if(result && result.error){
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
		}
	});
	
});

// 企业认证按钮事件
$("#toCorpAuthen_button").click(function() {
	window.location.href = PATH + "/trwuliu/Member/corpAuthenPage";
});

function keyLogin(){
		if (event.keyCode==13){//回车键的键值为13
			document.getElementById("login_button").click();  //调用登录按钮的登录事件
		}   
}
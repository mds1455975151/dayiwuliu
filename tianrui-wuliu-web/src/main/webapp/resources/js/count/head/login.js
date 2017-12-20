


function loginIn() {
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
		url : '/publicMember/login',// 跳转到 action
		data : {
					telnum:login_tel, 
					passWord:md5Psw, 
			   },
		type : "post",
		success : function(result) {
			if( result && result.code =="000000" ){
				var _role=result.data;
				var url="/trwuliu/Member/chooseRole";
				if( _role!=null ){
					url="/trwuliu/Member/message/message";	
				}
				window.location.href = url;
				$(".login").css("display","none");
			}else if(result && result.error){
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
		}
	});
}



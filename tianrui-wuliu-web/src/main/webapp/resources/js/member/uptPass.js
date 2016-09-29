
var flag = false;

$(function(){
	$("#uptpass1").show(); 
	$("#uptpass2").hide();
	$("#uptpass3").hide();
});

//验证密码
function checkPass(){
	var oldpass = $("#oldpass").val();
	var pass = $("#password").val();
	var md5Psw = hex_md5($.trim(pass));
	if(oldpass.toLowerCase() == md5Psw.toLowerCase()){
		$("#errorMassage").html("验证通过");
		$("#image1").attr("src", trRoot+"/tianrui/images/zhmm12.jpg"); 
		$("#image2").attr("src", trRoot+"/tianrui/images/zhmm21.jpg");
		uptPass();
		flag = true;
	}else{
		$("#errorMassage").html("密码错误");
	}
}
//修改密码
function uptPass(){
	$("#uptpass1").hide();
	$("#uptpass2").show();
	$("#uptpass3").hide();
}

function uptPassWord(){
	$("#uptpass1").hide();
	$("#uptpass3").hide();
	$("#uptpass2").show();
	var newpass = $("#newpassword").val();
	var takepass = $("#takepassword").val();
	if(newpass != takepass){
		alert("两次输入密码不一致");
		return;
	}
	var md5Psw = hex_md5($.trim(takepass));
	$.ajax({
		url : PATH + '/trwuliu/Member/uptPassword',
		data : {
			"passWord": md5Psw
		},
		type : "post",
		success : function(result) {
			if(result.code == "000000"){
				$("#image3").attr("src", trRoot+"/tianrui/images/zhmm31.jpg");//选中
				$("#image2").attr("src", trRoot+"/tianrui/images/zhmm22.jpg");
				$("#image1").attr("src", trRoot+"/tianrui/images/zhmm12.jpg");
				$("#uptpass3").show();
				$("#uptpass2").hide();
				$("#uptpass1").hide();
			}
		}
	});
	
}
//$("#image1").attr("src", trRoot+"/tianrui/images/zhmm11.jpg");//选中 
//$("#image1").attr("src", trRoot+"/tianrui/images/zhmm12.jpg"); 
//$("#image2").attr("src", trRoot+"/tianrui/images/zhmm21.jpg");//选中
//$("#image2").attr("src", trRoot+"/tianrui/images/zhmm22.jpg"); 
//$("#image3").attr("src", trRoot+"/tianrui/images/zhmm31.jpg");//选中
//$("#image3").attr("src", trRoot+"/tianrui/images/zhmm32.jpg"); 
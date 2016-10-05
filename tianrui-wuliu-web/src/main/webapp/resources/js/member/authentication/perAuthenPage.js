/**
 * ==================================================================
 * 会员注册功能与后台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.04.25
 */
// 身份证裁剪次数
 var idCardCount = 0;
// 驾驶证裁剪次数
 var driverCardCount = 0;
// 初始化处理
$(function() { 
    // 左侧导航选中效果
	$('#authenPage').addClass('selected');
	
	// 调用cropbox.js方法
	invokeCropBoxMethod();
	
	// 阅读协议绑定跳转链接
	$("#perAuthen_protocol").attr("href", PATH + "/publicMember/protocol");
	// 联系人电话
	$("#perAuthen_tel").val(cellPhone);
});

// 验证名字
$("#perAuthen_name").blur(function() {
	// 姓名，2个汉字以上，包含少数民族
	var reg =  /^\s*[\u4e00-\u9fa5]{1,}[\u4e00-\u9fa5.·]{0,15}[\u4e00-\u9fa5]{1,}\s*$/;
	// 输入的姓名
	var perAuthen_name = $("#perAuthen_name").val();
	
	$("#message_perAuthenName").empty();
	if (perAuthen_name == "") {
		$("#message_perAuthenName").html("姓名不能为空！");
	} else if (!reg.test(perAuthen_name)) {
		$("#message_perAuthenName").html("请输入正确的中文姓名！");
	}
});

// 验证身份证号
$("#perAuthen_id").blur(function() {
	// 旧式身份证号（15位）
	var regId_old = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/; 
	// 新式身份证号（18位）
	var regId_new = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/; 
	// 新式身份证验证最后位字母
	var regId2 = /^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;
	// 输入的身份证号
	var perAuthen_id = $("#perAuthen_id").val();
	
	$("#message_perAuthenId").empty();
	if (perAuthen_id == "") {
		$("#message_perAuthenId").html("身份证号不能为空！");
	} else if (!regId_old.test(perAuthen_id) && !regId_new.test(perAuthen_id) && !regId2.test(perAuthen_id)) {
		$("#message_perAuthenId").html("请输入正确的身份证号！");
	}

});

// 验证联系电话
$("#perAuthen_tel").blur(function() {
	// 11位手机或者座机
	var reg = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$|(^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$)/; 
	// 输入的联系电话
	var perAuthen_tel = $("#perAuthen_tel").val();
	
	$("#message_perAuthenTel").empty();
	if (perAuthen_tel == "") {
		$("#message_perAuthenTel").html("联系电话不能为空！");
	} else if (!reg.test(perAuthen_tel)) {
		$("#message_perAuthenTel").html("请输入正确的联系电话！");
	}

});

//【申请认证】按钮绑定点击事件
$("#perAuthen_button").click(function() {
	// 姓名
	var perAuthen_name = $("#perAuthen_name").val();
	// 身份证号
	var perAuthen_id = $("#perAuthen_id").val();
	// 联系电话
	var perAuthen_tel = $("#perAuthen_tel").val();
	
	var file_jsz = $("#file_jsz")[0].files[0];
	
	var type = "";
	var s = $(".rz_p1").is(".select");//身份证
	var b = $(".rz_p2").is(".select");//驾驶证
	if($(".rz_p1").is(".select")){
		type = "1"
	}else if($(".rz_p2").is(".select")){
		type = "2"
	}else{
		$("#modal_common_content").html("请选择认证类型");
		$("#commonModal").modal();
		return;
	}
	if(!file_jsz){
		$("#modal_common_content").html("请选择图片");
		$("#commonModal").modal();
		return;
	}
	if (perAuthen_name == "") {
		$("#message_perAuthenName").html("姓名不能为空！");
		return;
	} else if (perAuthen_id == "") {
		$("#message_perAuthenId").html("身份证号不能为空！");
		return;
	} else if (perAuthen_tel == "") {
		$("#message_perAuthenTel").html("联系电话不能为空！");
		return;
	}  else if (!$("#perAuthen_checkbox").is(":checked")) {
		$("#modal_common_content").html("请阅读并同意《天瑞物流平台》的协议！");
		$("#commonModal").modal();
		return;
	}
	var formData = new FormData();
	formData.append("file",file_jsz);
	formData.append("userName",perAuthen_name);
	formData.append("id",member_id);
	formData.append("identityCard",perAuthen_id);
	formData.append("telphone",perAuthen_tel);
	formData.append("type",type);
	// 后台处理
	$.ajax({
		url : PATH + '/trwuliu/Member/personalAuthentication',// 跳转到 action
		data : formData,
		type : "post",
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		success : function(result) {
			var ret = result.code;
			var msg = result.error;
			// 错误信息
			if (ret != 000000) {
				$("#modal_common_content").html(msg);
				$("#commonModal").modal();
			} else {
				// 跳转至审核状态画面
				window.location.href = PATH + "/trwuliu/Member/authenPage";
			}
		}
	});
});

function invokeCropBoxMethod() {
    // 身份证驾照上传的按钮tab切换
    var $tab_li = $('.rz_persontab ul li');
    $tab_li.click(function(){
        $(this).addClass('select').siblings().removeClass('select');
    });
}



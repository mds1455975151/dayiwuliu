/**
 * ==================================================================
 * 企业认证功能与后台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.04.25
 */
// 用于裁剪次数的记录
var count = 0 ;
// 初始化处理
$(function() { 
    // 左侧导航选中效果
	$('#authenPage').addClass('selected');

	// 阅读协议绑定跳转链接
	$("#corpAuthen_protocol").attr("href", PATH + "/publicMember/protocol");
	// 联系人电话
	$("#corpAuthen_tel").val(cellPhone);
	
	// 把默认图片地址赋值予隐藏项
	$("#busiLicenseImgBack_1").val($("#busiLicenseImg").attr("src"));
	$("#busiLicenseImgBack_2").val($("#busiLicenseImg").attr("src"));
	$("#busiLicenseImgBack_3").val($("#busiLicenseImg").attr("src"));
});

// 验证企业名称
$("#corpAuthen_name").blur(function() {
	// 获取输入的企业名称
	var corpAuthen_name = $("#corpAuthen_name").val();
	$("#message_corpAuthenName").empty();
	
	if (corpAuthen_name == "") {
		$("#message_corpAuthenName").html("企业名称不能为空！");
	}
});

// 验证公司地址
$("#corpAuthen_address").blur(function() {
	// 获取输入的公司地址
	var corpAuthen_address = $("#corpAuthen_address").val();
	$("#message_corpAuthenId").empty();
	
	if (corpAuthen_address == "") {
		$("#message_corpAuthenId").html("公司地址不能为空！");
	}

});

// 验证公司联系人
$("#corpAuthen_linkman").blur(function() {
	// 姓名，2个汉字以上，包含少数民族
	var reg =  /^\s*[\u4e00-\u9fa5]{1,}[\u4e00-\u9fa5.·]{0,15}[\u4e00-\u9fa5]{1,}\s*$/;
	// 输入的姓名
	var corpAuthen_linkman = $("#corpAuthen_linkman").val();
	
	$("#message_corpAuthenLinkman").empty();
	if (corpAuthen_linkman == "") {
		$("#message_corpAuthenLinkman").html("公司联系人不能为空！");
	} else if (!reg.test(corpAuthen_linkman)) {
		$("#message_corpAuthenLinkman").html("请输入正确的中文姓名！");
	}
});

// 验证联系人电话
$("#corpAuthen_tel").blur(function() {
	// 11位手机或者座机
	var reg = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$|(^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$)/; 
	// 输入的联系电话
	var corpAuthen_tel = $("#corpAuthen_tel").val();
	
	$("#message_corpAuthenTel").empty();
	if (corpAuthen_tel == "") {
		$("#message_corpAuthenTel").html("联系人电话不能为空！");
	} else if (!reg.test(corpAuthen_tel)) {
		$("#message_corpAuthenTel").html("请输入正确的联系人电话！");
	}

});

//【申请认证】按钮绑定点击事件
$("#corpAuthen_button").click(function() {
	// 企业名称
	var corpAuthen_name = $("#corpAuthen_name").val();
	// 公司地址
	var corpAuthen_address = $("#corpAuthen_address").val();
	// 公司联系人
	var corpAuthen_linkman = $("#corpAuthen_linkman").val();
	// 联系人电话
	var corpAuthen_tel = $("#corpAuthen_tel").val();
	// 营业执照图片路径
	var file_yyzz = $("#file_yyzz")[0].files[0];
	
	var companycode = $("#corpAuthen_code").val();
	if($('.file-input').hasClass('has-error')){
		alert('图片格式不正确，请重新选择图片！');
		return;
	}
	if (corpAuthen_name == "") {
		$("#message_corpAuthenName").html("企业名称不能为空！");
		return;
	} else if (corpAuthen_address == "") {
		$("#message_corpAuthenId").html("公司地址不能为空！");
		return;
	} else if (corpAuthen_linkman == "") {
		$("#message_corpAuthenTel").html("公司联系人不能为空！");
		return;
	} else if (corpAuthen_tel == "") {
		$("#message_corpAuthenTel").html("联系人电话不能为空！");
		return;
	} else if (!$("#corpAuthen_checkbox").is(":checked")) {
		$("#modal_common_content").html("请阅读并同意《天瑞物流平台》的协议！");
		$("#commonModal").modal();
		return;
	} else if(!file_yyzz){
		$("#modal_common_content").html("请上传营业执照图片");
		$("#commonModal").modal();
		return;
	} else if(companycode == ""){
		$("#message_corpAuthencode").html("营业执照号码不能为空！");
		return;
	}
	var formData = new FormData();
	formData.append("id",member_id);
	formData.append("companyName",corpAuthen_name);
	formData.append("companyAddress",corpAuthen_address);
	formData.append("companyContact",corpAuthen_linkman);
	formData.append("contactTel",corpAuthen_tel);
	formData.append("companycode",companycode);
	formData.append("file",file_yyzz);
	// 后台处理
	$.ajax({
		url : PATH + '/trwuliu/Member/enterpriseAuthentication',// 跳转到 action
		data : formData, 
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		type : "post",
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

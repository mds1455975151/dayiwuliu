/**
 * ==================================================================
 * 实名认证功能中的认证状态与前台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.05.03
 */

// 驾驶证裁剪次数
var driverCardCount = 0;

// 初始化处理
$(function() { 
    // 左侧导航选中效果
	$('#authenPage').addClass('selected');

	// 界面根据认证状态变动
	/*setPageByStatus();*/
	
	// 后台处理
	$.ajax({
		url : PATH + '/trwuliu/Member/memberInfoMassage',
		data : {
			id: member_id
		},
		type : "post",
		success : function(result) {
			var data = result.data;
			// 证件号码
			$("#perAuthen_id").val(data.identityCard);
		}
	});
	
	// 联系人电话
	$("#perAuthen_tel").val(cellPhone);
	// 姓名
	$("#perAuthen_name").val(_userName == "null" ? "" : _userName);
	
	// 把驾驶证默认图片地址赋值予隐藏项
	$("#driverCardImgBack_1").val($("#driverCardImg").attr("src"));
	$("#driverCardImgBack_2").val($("#driverCardImg").attr("src"));
	$("#driverCardImgBack_3").val($("#driverCardImg").attr("src"));
});

/** ==========================原页面方法==========================  */
/*// 【查看详情】按钮事件
$("#authen_state_detail").click(function() {
	window.location.href = PATH + "/trwuliu/Member/authenDetailPage";
});

// 界面根据认证状态变动
function setPageByStatus() {
	// 企业认证
	if ("1" == userType) {
		$("#authen_state_name").empty();
		$("#authen_state_name").html("企业认证");
		$("#authen_state_status").empty();
		if (2 == perCheckStatus) {
			$("#authen_state_status").html("审核中");
		} else if (1 == perCheckStatus) {
			$("#authen_state_status").html("认证成功");
		} else if (3 == perCheckStatus) {
			$("#authen_state_status").html("认证失败");
		}
	
	// 个人认证
	} else if ("2" == userType) {
		$("#authen_state_name").empty();
		$("#authen_state_name").html("个人认证");
		$("#authen_state_status").empty();
		if (2 == perCheckStatus) {
			$("#authen_state_status").html("审核中");
		} else if (1 == perCheckStatus) {
			$("#authen_state_status").html("认证成功");
		} else if (3 == perCheckStatus) {
			$("#authen_state_status").html("认证失败");
		}
	}
}*/

//【申请认证】按钮绑定点击事件
$("#perAuthen_button").click(function() {
	// 姓名
	var perAuthen_name = $("#perAuthen_name").val();
	// 身份证号
	var perAuthen_id = $("#perAuthen_id").val();
	// 联系电话
	var perAuthen_tel = $("#perAuthen_tel").val();
	//准驾车型
	var drivinglicensetype = $("#drivinglicensetype").text();
	// 驾驶证图片路径
	var file_jsz = $("#file_jsz")[0].files[0];
	//道路许可证
	var file_rtbl=$("#rtblimg")[0].files[0];
	var rtblno=$("#rtblno").val();
	
	if( rtblno || file_rtbl  ){
		if(!rtblno ){
			alert("道路运输经营许可证号不能为空");
			return ;
		}
		if(!file_rtbl){
			alert("道路运输经营许可证图片不能为空");
			return ;
		}
	}
	var type = "";
	if (file_jsz) {
		type = "2";
	}else {
		alert("请上传驾驶证图片！");
		return;
	}
	if($('.file-input').hasClass('has-error')){
		alert('图片格式不正确，请重新选择图片！');
		return;
	}
	if (!$("#perAuthen_checkbox").is(":checked")) {
		alert("请阅读并同意《大易物流平台》的协议！");
		return;
	}
	var formData = new FormData();
	formData.append("file",file_jsz);
	formData.append("userName",perAuthen_name);
	formData.append("id",member_id);
	formData.append("identityCard",perAuthen_id);
	formData.append("telphone",perAuthen_tel);
	formData.append("type",type);
	formData.append("licenseType",drivinglicensetype);
	formData.append("rtblimg",file_rtbl);
	formData.append("rtblno",rtblno);
	// 后台处理
	$.ajax({
		url : PATH + '/trwuliu/Member/personalAuthentication',// 跳转到 action
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
				// 跳转至认证首页
				window.location.href = PATH + "/trwuliu/Member/authenPage";
			}
		}
	});
});
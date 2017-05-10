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
//身份证失去焦点事件
$("#perAuthen_id").on("blur",function(){
	var idcard = $("#perAuthen_id").val();
	if(idcard.length==18){
		var year = idcard.substring(6,10);
		var month = idcard.substring(10,12);
		var date = idcard.substring(12,14);
		$("#per_birthday").val(year+"-"+month+"-"+date);
	}else{
		$('#perAuthen_id').focus();
	}
});


//【申请认证】按钮绑定点击事件
$("#perAuthen_button").click(function() {
	//清空文字提示
	massageClear();
	// 姓名
	var perAuthen_name =$.trim($("#perAuthen_name").val());
	// 身份证号
	var perAuthen_id = $.trim($("#perAuthen_id").val());
	// 联系电话
	var perAuthen_tel = $.trim($("#perAuthen_tel").val());
	//准驾车型
	var drivinglicensetype = $.trim($("#drivinglicensetype").text());
	// 驾驶证图片路径
	var file_jsz = $("#file_jsz_str").val();
	
	var sex = $("input[type='radio']:checked").val();
	var birthday = $.trim($("#per_birthday").val());
	var firstlicens = $.trim($("#per_firstlicens").val());
	var licenceorg = $.trim($("#per_licenceorg").val());
	var starttime = $.trim($("#per_starttime").val());
	var usefullife = $.trim($("#per_usefullife").val());
	var idcardaddress = $.trim($("#per_idcardaddress").val());
	
	if(perAuthen_name==""){
		$("#message_perAuthenName").html("司机姓名不能为空");
		$('#perAuthen_name').focus();
		return;
	}
	
	if(perAuthen_id==""){
		$("#massage_perAuthen_id").html("身份证号不能为空");
		$('#perAuthen_id').focus();
		return;
	}
	
	if(birthday==""){
		$("#massage_birthday").html("出生日期不能为空");
		$('#per_birthday').focus();
		return;
	}
	if(firstlicens == ""){
		$("#massage_firstlicens").html("初次领证日期不能为空");
		$('#per_firstlicens').focus();
		return;
	}
	if(licenceorg==""){
		$("#massage_licenceorg").html("发证机关不能为空");
		$('#per_licenceorg').focus();
		return;
	}
	if(starttime==""){
		$("#massage_starttime").html("驾驶证注册日期不能为空");
		$('#per_starttime').focus();
		return;
	}
	if(usefullife==""){
		$("#massage_usefullife").html("有效年限不能为空");
		$('#per_usefullife').focus();
		return;
	}
	if(idcardaddress==""){
		$("#massage_idcardaddress").html("身份证地址不能为空");
		$('#per_idcardaddress').focus();
		return;
	}
	
	var type = "";
	if (file_jsz!="") {
		type = "2";
	}else {
		alert("请重新上传驾驶证图片！");
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
	if(drivinglicensetype == ""){
		alert("请选择准驾车型");
		return;
	}
	//1900-2099
	var regexp = /^([1][9][0-9][0-9]|[2][0][0-9][0-9])(\-)([0][1-9]|[1][0-2])(\-)([0-2][0-9]|[3][0-1])$/;
	if(!regexp.test(birthday)){
		$("#massage_birthday").html("出生日期格式有误");
		$('#per_birthday').focus();
		return;
	}
	if(!regexp.test(firstlicens)){
		$("#massage_firstlicens").html("初次领证日期格式有误");
		$('#per_firstlicens').focus();
		return;
	}
	if(!regexp.test(starttime)){
		$("#massage_starttime").html("驾驶证注册日期格式有误");
		$('#per_starttime').focus();
		return;
	}
	if($("#file_shenfenzheng_A_str").val()==""){
		alert("请上传身份证正面照片");
		return;
	}
	if($("#file_shenfenzheng_B_str").val()==""){
		alert("请上传身份证反面面照片");
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
	
	formData.append("sex",sex);
	formData.append("birthday",birthday);
	formData.append("firstlicens",firstlicens);
	formData.append("licenceorg",licenceorg);
	formData.append("starttime",starttime);
	formData.append("usefullife",usefullife);
	formData.append("idcardaddress",idcardaddress);
	//身份证正面
	formData.append("positive",$("#file_shenfenzheng_A_str").val());
	//身份证反面
	formData.append("opposite",$("#file_shenfenzheng_B_str").val());
	
	$(this).addClass('disabled');
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

//清空文字提示
function massageClear(){
	$("#massage_birthday").html("");
	$("#massage_firstlicens").html("");
	$("#massage_licenceorg").html("");
	$("#massage_starttime").html("");
	$("#massage_usefullife").html("");
	$("#massage_idcardaddress").html("");
	$("#message_perAuthenName").html("");
	$("#massage_perAuthen_id").html("");
}

//图片上传
function fileupload(id,remove){
	var file = $("#"+id)[0].files[0];
	var formData = new FormData();
	formData.append("file",file);
	// 后台处理
	$.ajax({
		url : PATH + '/upload/add',// 跳转到 action
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

$(".timeStr").blur(function(){
	var regexp = /^([1][9][0-9][0-9]|[2][0][0-9][0-9])(\-)([0][1-9]|[1][0-2])(\-)([0-2][0-9]|[3][0-1])$/;
	var str = $(this).val();
	if(!regexp.test(str)){
		alert("时间格式有误");
	}
});

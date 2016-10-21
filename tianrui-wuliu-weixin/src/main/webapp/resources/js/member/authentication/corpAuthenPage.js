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

	// 调用cropbox.js方法
	invokeCropBoxMethod();
	
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
	var reg = /^1[0-9]{10}$/; 
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
	var busiLicensePath = $("#busiLicense").attr("src");
	
	var companycode = $("#corpAuthen_code").val();
	
	var imgPath = "";
	if (busiLicensePath != undefined) {
		imgPath = busiLicensePath;
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
	} else if(imgPath == ""){
		$("#modal_common_content").html("请上传营业执照图片");
		$("#commonModal").modal();
		return;
	} else if(companycode == ""){
		$("#message_corpAuthencode").html("营业执照号码不能为空！");
		return;
	}
	
	// 后台处理
	$.ajax({
		url : PATH + '/trwuliu/Member/enterpriseAuthentication',// 跳转到 action
		data : {
					id:member_id, 
					companyName:corpAuthen_name, 
					companyAddress:corpAuthen_address, 
					companyContact:corpAuthen_linkman, 
					contactTel:corpAuthen_tel,
					companycode:companycode,
					imgStr:imgPath
				},
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

// 调用cropbox.js方法
function invokeCropBoxMethod() {
    // 点击选择上传图片按钮，图片裁剪框显示出来
    $(".tx_contr").on('click',function(){
        $(".acc_txqiyerz").show();
    });
    // 营业照上传的收起按钮
    $(".tx_shouqi").on('click',function(){
        $(".acc_txqiyerz").hide();
    });

    var thumbcom = $(".imgBox_company .thumbBox");
    thumbcom.height(114);
    thumbcom.width(200);
    thumbcom.css({ "margin-top": -57, "margin-left": -100 });

    var options =
    {
        thumbBox: '.thumbBox',
        spinner: '.spinner',
        imgSrc: 'images/img1.jpg'
    };
    var cropper = $('.imgBox_company').cropbox(options);
    // 文件上传按钮操作
    $('#upload-file').on('change', function(){
        var reader = new FileReader();
        reader.onload = function(e) {
            options.imgSrc = e.target.result;
            cropper = $('.imgBox_company').cropbox(options);
        };
        reader.readAsDataURL(this.files[0]);
        this.files = [];
    });
    // 裁切按钮操作
    $('#btnCrop').on('click', function(){
        var img = cropper.getDataURL();
        $('.user_oldtx').html('');
        $('.user_oldtx').append('<img id="busiLicense" src="'+img+'" align="absmiddle" style="box-shadow:0px 0px 12px #7E7E7E;">');
        // 裁剪次数自增
        count++;
        $("#busiLicenseImgBack_2").val($("#busiLicenseImgBack_3").val());
        $("#busiLicenseImgBack_3").val($("#busiLicense").attr("src"));
        $(".tx_config").on('click',function(){
            $(".acc_touxiang").hide();

        });
    });
    
    // 回退按钮
    $(".tx_cancel").on('click',function() {
    	// 裁剪次数自减
    	count--;
    	if (count <= 0) {
    		count = 0;
    		$('.user_oldtx').empty();
        	$('.user_oldtx').append('<img id="busiLicenseImg" src=' + $('#busiLicenseImgBack_1').val() + ' class="img-rounded">');
    	} else {
    		$("#busiLicense").removeAttr("src");
    		$("#busiLicense").attr("src", $("#busiLicenseImgBack_2").val());
    	}
    });
    
    // 营业照上传图片放大按钮操作
    $('#btnZoomIn').on('click', function(){
        cropper.zoomIn();
    });
    // 营业照上传图片缩小按钮操作
    $('#btnZoomOut').on('click', function(){
        cropper.zoomOut();
    });
}

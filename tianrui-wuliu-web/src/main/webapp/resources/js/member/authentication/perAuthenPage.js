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
	
	// 把身份证默认图片地址赋值予隐藏项
	$("#idCardImgBack_1").val($("#idCardImg").attr("src"));
	$("#idCardImgBack_2").val($("#idCardImg").attr("src"));
	$("#idCardImgBack_3").val($("#idCardImg").attr("src"));
	
	// 把驾驶证默认图片地址赋值予隐藏项
	$("#driverCardImgBack_1").val($("#driverCardImg").attr("src"));
	$("#driverCardImgBack_2").val($("#driverCardImg").attr("src"));
	$("#driverCardImgBack_3").val($("#driverCardImg").attr("src"));
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
	// 身份证图片路径
	var idCardPath = $("#idCard").attr("src");
	// 驾驶证图片路径
	var driverCardPath = $("#driverCard").attr("src");
	
	var imgPath = "";
	var type = "";
	if (idCardPath != undefined) {
		imgPath = idCardPath;
		type = "1";
	} else if (driverCardPath != undefined) {
		imgPath = driverCardPath;
		type = "2";
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
	} else if(imgPath == ""){
		$("#modal_common_content").html("请上传身份证/驾驶证图片！");
		$("#commonModal").modal();
		return;
	}
	
	// 后台处理
	$.ajax({
		url : PATH + '/trwuliu/Member/personalAuthentication',// 跳转到 action
		data : {
					userName: perAuthen_name, 
					id: member_id, 
					identityCard: perAuthen_id, 
					telphone: perAuthen_tel, 
					imgStr: imgPath,
					type: type
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
    // 身份证驾照上传的按钮tab切换
    var $tab_li = $('.rz_persontab ul li');
    $tab_li.click(function(){
        $(this).addClass('select').siblings().removeClass('select');
        var index = $tab_li.index(this);
        $('.rz_personbox > .rz_personcont').eq(index).show().siblings().hide();
    });
    
    /** 以下是身份证上传 */
    // 点击选择上传身份证按钮，图片裁剪框显示出来
    $(".rz_p1").on('click',function(){
        $(".rz_persontx").show();
        $('.user_oldtx2').empty();
        $('.user_oldtx2').append("<img src=" + trRoot + "/tianrui/images/jz.png>");
    });
    // 身份证上传的收起按钮
    $(".carshouq1").on('click',function(){
        $(".rz_persontx").hide();
    });
    // 驾照上传的收起按钮
    $(".carshouq2").on('click',function(){
        $(".rz_persontx2").hide();
    });
    // 图片裁切块的大小自定义，margin-top是height一半，margin-left是width一半
    var thumbcom = $(".imageBox .thumbBox");
        thumbcom.height(114);
        thumbcom.width(200);
        thumbcom.css({ "margin-top": -57, "margin-left": -100 });
    // 给cropbox.js传参
    var options =
    {
        thumbBox: '.thumbBox',
        spinner: '.spinner'/*,*/
        /*imgSrc: 'images/img1.jpg'*/
    };
    var cropper = $('.imgBox_pers').cropbox(options);
    // 文件上传按钮操作
    $('#upload-file').on('change', function(){
        var reader = new FileReader();
        reader.onload = function(e) {
            options.imgSrc = e.target.result;
            cropper = $('.imgBox_pers').cropbox(options);
        };
        reader.readAsDataURL(this.files[0]);
        this.files = [];
    });
    // 裁切按钮操作
    $('#btnCrop_p1').on('click', function(){
        var img = cropper.getDataURL();
        $('.user_oldtx').html('');
        $('.user_oldtx').append('<img id="idCard" src="' + img + '" align="absmiddle" style="width:228px;box-shadow:0px 0px 12px #7E7E7E;">');
        // 裁剪次数自增
        idCardCount++;
        $("#idCardImgBack_2").val($("#idCardImgBack_3").val());
        $("#idCardImgBack_3").val($("#idCard").attr("src"));
    });
    
    // 图片后退按钮，点击.user_oldtx显示刚被删除的照片
    $("#btncancel_p1").on('click',function(){
    	// 裁剪次数自减
    	idCardCount--;
    	if (idCardCount <= 0) {
    		idCardCount = 0;
    		$('.user_oldtx').empty();
        	$('.user_oldtx').append('<img id="idCardImg" src=' + $('#idCardImgBack_1').val() + '>');
    	} else {
    		$("#idCard").removeAttr("src");
    		$("#idCard").attr("src", $("#idCardImgBack_2").val());
    	}
    });
    
    // 身份证上传图片放大按钮操作
    $('#btnZoomIn_p1').on('click', function(){
        cropper.zoomIn();
    });
    // 身份证上传图片缩小按钮操作
    $('#btnZoomOut_p1').on('click', function(){
        cropper.zoomOut();
    });
    
    /** 以下是驾照上传 */
    // 点击选择上传驾照按钮，图片裁剪框显示出来
    $(".rz_p2").on('click',function(){
        $(".rz_persontx2").show();
        $('.user_oldtx').empty();
        $('.user_oldtx').append("<img src=" + trRoot + "/tianrui/images/sfz.png>");
    });
    var cropper2 = $('.imgBox_pers2').cropbox(options);
    // 文件上传按钮操作
    $('#upload-file_p2').on('change', function(){
        var reader = new FileReader();
        reader.onload = function(e) {
            options.imgSrc = e.target.result;
            cropper2 = $('.imgBox_pers2').cropbox(options);
        };
        reader.readAsDataURL(this.files[0]);
        this.files = [];
    });
    // 裁切按钮操作
    $('#btnCrop_p2').on('click', function(){
        var img = cropper2.getDataURL();
        $('.user_oldtx2').html('');
        $('.user_oldtx2').append('<img id="driverCard" src="' + img + '" align="absmiddle" style="width:228px;box-shadow:0px 0px 12px #7E7E7E;">');
        // 裁剪次数自增
        driverCardCount++;
        $("#driverCardImgBack_2").val($("#driverCardImgBack_3").val());
        $("#driverCardImgBack_3").val($("#driverCard").attr("src"));
    });
    
    // 图片后退按钮，点击.user_oldtx2显示刚被删除的照片
    $("#btncancel_p2").on('click',function(){
    	// 裁剪次数自减
    	driverCardCount--;
    	if (driverCardCount <= 0) {
    		driverCardCount = 0;
    		$('.user_oldtx2').empty();
        	$('.user_oldtx2').append('<img id="driverCardImg" src=' + $('#driverCardImgBack_1').val() + '>');
    	} else {
    		$("#driverCard").removeAttr("src");
    		$("#driverCard").attr("src", $("#driverCardImgBack_2").val());
    	}
    });
    
    // 驾照上传图片放大按钮操作
    $('#btnZoomIn_p2').on('click', function(){
        cropper2.zoomIn();
    });
    // 驾照上传图片缩小按钮操作
    $('#btnZoomOut_p2').on('click', function(){
        cropper2.zoomOut();
    });
}



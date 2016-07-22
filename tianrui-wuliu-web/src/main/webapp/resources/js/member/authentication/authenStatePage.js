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
	
	// 调用cropbox.js方法
	invokeCropBoxMethod();
	
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
	// 驾驶证图片路径
	var driverCardPath = $("#driverCard").attr("src");
	
	var imgPath = "";
	var type = "";
	if (driverCardPath != undefined) {
		imgPath = driverCardPath;
		type = "2";
	}
	
	if (!$("#perAuthen_checkbox").is(":checked")) {
		$("#modal_common_content").html("请阅读并同意《天瑞物流平台》的协议！");
		$("#commonModal").modal();
		return;
	} else if(imgPath == ""){
		$("#modal_common_content").html("请上传驾驶证图片！");
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
				// 跳转至认证首页
				window.location.href = PATH + "/trwuliu/Member/authenPage";
			}
		}
	});
});

//调用cropbox.js方法
function invokeCropBoxMethod() {
    
    // 身份证驾照上传的按钮tab切换
    var $tab_li = $('.rz_persontab ul li');
    $tab_li.click(function(){
        $(this).addClass('select').siblings().removeClass('select');
        var index = $tab_li.index(this);
        $('.rz_personbox > .rz_personcont').eq(index).show();
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

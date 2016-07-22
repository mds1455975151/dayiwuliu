/**
 * ==================================================================
 * 个人资料功能与前后台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.05.10
 */

// 初始化处理
$(function() { 
    // 左侧导航选中效果
	$('#personalDataPage').addClass('selected');
	// 调用cropbox.js方法
	invokeCropBoxMethod();
});

/** 代码修正，姓名设为readonly后删除，1年后即2017.06.01删除此行代码 */
/*// 验证名字
$("#personal_name").blur(function() {
	// 姓名，2个汉字以上，包含少数民族
	var reg =  /^\s*[\u4e00-\u9fa5]{1,}[\u4e00-\u9fa5.·]{0,15}[\u4e00-\u9fa5]{1,}\s*$/;
	// 输入的姓名
	var personal_name = $("#personal_name").val();
	
	$("#message_personalName").empty();
	if (personal_name == "") {
		$("#message_personalName").html("姓名不能为空！");
	} else if (!reg.test(personal_name)) {
		$("#message_personalName").html("请输入正确的中文姓名！");
	}
});*/

//【确认修改】按钮绑定点击事件
$("#personal_button").click(function() {
	// 姓名
	var personal_name = $("#personal_name").val();
	// 性别
	var personal_sex = $("input[name='personal_sex']:checked").val();
	// 昵称
	var personal_nickname = $("#personal_nickname").val();
	// 图片
	var targetImg = $("#targetImg").attr("src");
	
	/*if (personal_name == "" || $("#message_personalName").html() != "") {
		$("#message_personalName").html("姓名不能为空！");
		return;
	}*/
	
	// 没有上传图片
	if (targetImg == undefined) {
		targetImg = "";
	}
	
	$.ajax({
		url : PATH + '/trwuliu/Member/updateMember',// 跳转到 action
		data : {
					userName: personal_name, 
					phone: cellPhone,
					loginName: personal_nickname,
					imgstr: targetImg,
					sex: personal_sex
				},
		type : "post",
		success : function(result) {
			var ret = result.ret;
			var msg = result.msg;
			// 错误信息
			if (ret == 1) {
				$("#modal_common_content").html(msg);
				$("#commonModal").modal();
			} else {
				// TODO: waiting for the page!
				window.location.href = PATH + "/trwuliu/Member/personalDataPage";
			}
		}
	});
});

//调用cropbox.js方法
function invokeCropBoxMethod() {
    // 点击修改头像按钮，图片裁剪框显示出来
    $(".tx_contr").on('click',function(){
        $(".acc_touxiang").show();
    });
    // 修改头像的收起按钮
    $(".tx_shouqi").on('click',function(){
        $(".acc_touxiang").hide();
    });
    // 图片裁切块的大小自定义，margin-top是height一半，margin-left是width一半
    var thumb = $(".imageBox_tx .thumbBox");
    thumb.height(150);
    thumb.width(150);
    thumb.css({ "margin-top": -75, "margin-left": -75 });
    // 给cropbox.js传参
    var options =
    {
        thumbBox: '.thumbBox',
        spinner: '.spinner',
        imgSrc: ''
    };
    var cropper = $('.imageBox_tx').cropbox(options);
    // 文件上传按钮操作
    $('#upload-file').on('change', function(){
        var reader = new FileReader();
        reader.onload = function(e) {
            options.imgSrc = e.target.result;
            cropper = $('.imageBox_tx').cropbox(options);
        };
        reader.readAsDataURL(this.files[0]);
        this.files = [];
    });
    // 裁切按钮操作
    $('#btnCrop').on('click', function(){
        var img = cropper.getDataURL();
        $('.user_oldtx').empty();
        $('.user_oldtx').append('<img id="targetImg" src="'+img+'" align="absmiddle" style="width:100px;box-shadow:0px 0px 12px #7E7E7E;">');
    });
    
    // 回退按钮
    $('#btncancel').on('click', function(){
    	$('.user_oldtx').empty();
    	$('.user_oldtx').append('<img id="currentImg" src=' + $('#currentImgBack').val() + ' class="img-rounded">');
    });
    
    // 图片放大按钮操作
    $('#btnZoomIn').on('click', function(){
        cropper.zoomIn();
    });
    // 图片缩小按钮操作
    $('#btnZoomOut').on('click', function(){
        cropper.zoomOut();
    });
}



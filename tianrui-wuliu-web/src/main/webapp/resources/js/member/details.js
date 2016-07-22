/**
 * ==================================================================
 * 会员信息完善功能与后台交互 v1.0
 * ==================================================================
 * @author tank
 * @time 2016.04.30
 */
// 初始化处理
$(function() { 
    // 左侧导航选中效果
	$('#detailsPage').addClass('selected');
	if (name != "") {
	    // 导航信息
		$("#header_welcome").html(name + "，欢迎来到天瑞物流！");
		$("#header_loginHref").hide();
		$("#header_registerHref").hide();
		$("#header_logout").show();
	}
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
        imgSrc: ' '
    }
    var cropper = $('.imageBox_tx').cropbox(options);
    // 文件选择按钮操作
    $('#upload-file').on('change', function(){
        var reader = new FileReader();
        reader.onload = function(e) {
            options.imgSrc = e.target.result;
            cropper = $('.imageBox_tx').cropbox(options);
        }
        reader.readAsDataURL(this.files[0]);
        this.files = [];
    })
    var img ;
    // 裁切按钮操作
    $('#btnCrop').on('click', function(){
    	cropper = $('.imageBox_tx').cropbox(options);
    	img = cropper.getDataURL();
        $('.user_oldtx').html('');
        $('.user_oldtx').append('<img src="'+img+'" align="absmiddle" style="width:100px;box-shadow:0px 0px 12px #7E7E7E;">');

        $(".tx_cancel").on('click',function(){
            $(".acc_touxiang").hide();
        });
    })
    // 图片放大按钮操作
    $('#btnZoomIn').on('click', function(){
        cropper.zoomIn();
    })
    // 图片缩小按钮操作
    $('#btnZoomOut').on('click', function(){
        cropper.zoomOut();
    })
    
  //修改个人信息按钮事件
    $("#updateDetails").click(function() {
    	// 获取平台昵称
    	var userName = $("#userName").val();
    	// 获取联系手机号
    	var phone = $("#phone").val();
    	//获取sex按钮集合
    	var sexlist = document.getElementsByName("sex");
    	
    	var sex = "";
    	//判断
    	for(var i = 0;i<sexlist.length;i++){
	    	if(sexlist[i].checked==true){
	    		sex = sexlist[i].value;
	    		break;
	    	}
    	}
    	$.ajax({
    		url : PATH + '/trwuliu/Member/updateMember',// 跳转到 action
    		data : {
    					userName:userName, 
    					phone:phone, 
    					imgstr :img,
    					sex : sex
    			   },
    		type : "post",
    		success : function(result) {
    			if( result && result.code =="000000" ){
    				$("#modal_common_content").html("个人资料修改成功");
    				$("#commonModal").modal();
    			}else if(result && result.error){
    				$("#modal_common_content").html(result.error);
    				$("#commonModal").modal();
    			}
    		}
    	});
    	
    });
});
//清空剪切的图片
function revertImage(){
	var hml = "<div class='imageBox imageBox_tx' style=''>"+
	"<div class='thumbBox' style='height: 152px; width: 152px; margin-top: -75px; margin-left: -75px;'></div>"+
	"<div class='spinner' style=''>Loading...</div>"+
	"</div>";
    document.getElementById("revert").innerHTML =hml;
}

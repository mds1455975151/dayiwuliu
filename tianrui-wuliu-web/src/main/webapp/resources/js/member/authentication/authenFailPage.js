/**
 * ==================================================================
 * 实名认证功能中的认证状态与前台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.05.03
 */

// 初始化处理
$(function() { 
	percheckType();
	// 左侧导航选中效果
	$('#authenPage').addClass('selected');
	// 后台处理
	$.ajax({
		url : PATH + '/trwuliu/Member/memberInfoMassage',
		data : {
			id: member_id
		},
		type : "post",
		success : function(result) {
			var data = result.data;
			$("#authen_memo").html(data.rejectReason);
			$("#authen_submitTime").html(data.submitDateStr);
			$("#authen_auditTime").html(data.auditTimeStr);
		}
	});
	
});
function percheckType(){
	if ("3" == companypercheck ) {
		$("#percheckType").html("企业认证");
	} else if ("3" == userpercheck ) {
		$("#percheckType").html("个人认证");
	}else if ("3" == driverpercheck){
		$("#percheckType").html("司机认证");
	}
}
/*// 【查看详情】按钮事件
$("#authen_fail_detail").click(function() {
	window.location.href = PATH + "/trwuliu/Member/authenDetailPage";
});*/

// 【返回个人/企业认证】按钮事件
$("#authen_fail_return").click(function() {
	// 企业认证
	if ("3" == companypercheck ) {
		window.location.href = PATH + "/trwuliu/Member/corpAuthenPage";
	// 个人认证
	} else if ("3" == userpercheck ) {
		window.location.href = PATH + "/trwuliu/Member/perAuthenPage";
	//司机认证
	}else if ("3" == driverpercheck){
		window.location.href = PATH + "/trwuliu/Member/authenStatePage";
	}
});


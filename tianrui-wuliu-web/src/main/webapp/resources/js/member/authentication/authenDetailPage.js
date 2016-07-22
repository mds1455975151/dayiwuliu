/**
 * ==================================================================
 * 实名认证功能中的认证明细与前台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.05.03
 */

// 初始化处理
$(function() { 
    // 左侧导航选中效果
	$('#authenPage').addClass('selected');

	// 界面根据认证状态变动
	setPageByStatus();
});

// 界面根据认证状态变动
function setPageByStatus() {
	// 审核中
	if (2 == perCheckStatus) {
		$.ajax({
			url : PATH + '/trwuliu/Member/memberInfoByid',// 跳转到 action
			data : {
						id:memberId
				   },
			type : "post",
			success : function(result) {
				var data = result;
				var memberInfo = data.data;
				var ret = data.code;
				// success
				if (ret == "000000") {
					// 企业认证
					if (userType == "1") {
						$("#authenDetail_name").html("企业认证");
						
					// 个人认证
					} else if (userType == "2") {
						$("#authenDetail_name").html("个人认证");
					}
					
					if (2 == perCheckStatus) {
						$("#authenDetail_content_p1").html("审核中");
					}
					$("#authenDetail_creattime").html(memberInfo.submitDate);
					$("#authenDetail_audittime").html("-");
					$("#authenDetail_no").html(memberInfo.infoCode);
				} else {
					$("#modal_common_content").html("出错啦！");
					$("#commonModal").modal();
				}
			}
		});
	// 认证成功或认证失败
	} else if (1 == perCheckStatus || 3 == perCheckStatus) {
		$.ajax({
			url : PATH + '/trwuliu/Member/authenticationInfoByid',// 跳转到 action
			data : {
						id:memberId
				   },
			type : "post",
			success : function(result) {
				var data = result;
				var memberInfo = data.data;
				var ret = data.code;
				// success
				if (ret == "000000") {
					// 企业认证
					if (userType == "1") {
						$("#authenDetail_name").html("企业认证");
						
					// 个人认证
					} else if (userType == "2") {
						$("#authenDetail_name").html("个人认证");
					}
					
					if (1 == perCheckStatus) {
						$("#authenDetail_content_p1").html("认证成功");
					} else if (3 == perCheckStatus) {
						$("#authenDetail_content_p1").html("认证失败");
					}
					
					$("#authenDetail_creattime").html(memberInfo.submitDate);
					$("#authenDetail_audittime").html(memberInfo.auditTime);
					$("#authenDetail_no").html(memberInfo.infoCode);
				} else {
					$("#modal_common_content").html("出错啦！");
					$("#commonModal").modal();
				}
			}
		});
	}
}

/**
 * ==================================================================
 * 实名认证功能与后台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.04.25
 */

// 初始化处理
$(function() { 
    // 左侧导航选中效果
	$('#authenPage').addClass('selected');

	// 界面根据认证状态变动
	setPageByStatus();
});

// 个人认证按钮事件
$("#toPerAuthen_button").click(function() {
	if (0 == userpercheck) {
		window.location.href = PATH + "/trwuliu/Member/perAuthenPage";
	} else if (2 == userpercheck || 1 == userpercheck) {
		window.location.href = PATH + "/trwuliu/Member/authenStatePage";
	} else if (3 == userpercheck) {
		window.location.href = PATH + "/trwuliu/Member/authenFailPage";
	}
});

// 企业认证按钮事件
$("#toCorpAuthen_button").click(function() {
	if (0 == companypercheck) {
		window.location.href = PATH + "/trwuliu/Member/corpAuthenPage";
	} else if (2 == companypercheck || 1 == companypercheck) {
		window.location.href = PATH + "/trwuliu/Member/authenStatePage";
	} else if (3 == companypercheck) {
		window.location.href = PATH + "/trwuliu/Member/authenFailPage";
	}
});
// 司机认证按钮事件
$("#todrivAuthen_button").click(function() {
	if (0 == driverpercheck) {
		window.location.href = PATH + "/trwuliu/Member/authenStatePage";
	} else if (2 == driverpercheck || 1 == companypercheck) {
		window.location.href = PATH + "/trwuliu/Member/authenStatePage";
	} else if (3 == driverpercheck) {
		window.location.href = PATH + "/trwuliu/Member/authenFailPage";
	}
});

// 界面根据认证状态变动
function setPageByStatus() {
	
	if (0 == userpercheck) {
		$("#perAuthen_status").html("未认证");
		$("#perAuthen_status").removeClass();
		$("#perAuthen_status").addClass("btn btn-rznone  btn-sm ml30");
		$("#toPerAuthen_button").html("申请认证");
	} 
	if(0 == companypercheck){
		$("#corpAuthen_status").html("未认证");
		$("#corpAuthen_status").removeClass();
		$("#corpAuthen_status").addClass("btn btn-rznone  btn-sm ml30");
		$("#toCorpAuthen_button").html("申请认证");
	}
	if(0 == driverpercheck){
		$("#drivAuthen_status").html("未认证");
		$("#drivAuthen_status").removeClass();
		$("#drivAuthen_status").addClass("btn btn-rznone  btn-sm ml30");
		$("#todrivAuthen_button").html("申请认证");
	}
	
	if(0 != userpercheck || 0 != driverpercheck){
		$("#corpAuthen_div").remove();
	}else if(0 != companypercheck){
		$("#perAuthen_div").remove();
		$("#drivAuthen_div").remove();
	}
	
	if(1 == companypercheck){
		$("#perAuthen_div").remove();
		$("#drivAuthen_div").remove();
	}else if(1 == driverpercheck){
		$("#perAuthen_div").remove();
		$("#corpAuthen_div").remove();
	}else if(1 == userpercheck){
		$("#corpAuthen_div").remove();
	}
	
	if(2 == companypercheck){
		$("#perAuthen_div").remove();
		$("#drivAuthen_div").remove();
	}else if(2 == userpercheck){
		$("#drivAuthen_div").remove();
		$("#corpAuthen_div").remove();
	}else if(2 == driverpercheck){
		$("#perAuthen_div").remove();
		$("#corpAuthen_div").remove();
	}
	
	// 企业认证
	if (2 == companypercheck) {
		$("#perAuthen_div").remove();
		$("#corpAuthen_status").html("认证中");
		$("#corpAuthen_status").removeClass();
		$("#corpAuthen_status").addClass("btn  btn-primary btn-sm ml30");
		$("#toCorpAuthen_button").hide();
	} else if (1 == companypercheck) {
		$("#corpAuthen_status").html("企业认证成功");
		$("#corpAuthen_status").removeClass();
		$("#corpAuthen_status").addClass("btn  btn-rzsucc btn-sm ml30");
		$("#toCorpAuthen_button").hide();
	} else if (3 == companypercheck) {
		$("#corpAuthen_status").html("认证失败");
		$("#corpAuthen_status").removeClass();
		$("#corpAuthen_status").addClass("btn  btn-rzfail btn-sm ml30");
		$("#toCorpAuthen_button").html("查看原因");
	}
	// 个人认证
	if (2 == userpercheck) {
		$("#corpAuthen_div").remove();
		$("#perAuthen_status").html("认证中");
		$("#perAuthen_status").removeClass();
		$("#perAuthen_status").addClass("btn  btn-primary btn-sm ml30");
		$("#toPerAuthen_button").hide();
	} else if (1 == userpercheck) {
		// 判断用户认证信息，如果已经认证成为司机，显示《司机认证成功》
		$("#toPerAuthen_button").hide();
		$("#perAuthen_status").html("个人认证成功");
		$("#toPerAuthen_button").html("司机认证");
		$("#perAuthen_status").removeClass();
		$("#perAuthen_status").addClass("btn  btn-rzsucc btn-sm ml30");
		
	} else if (3 == userpercheck) {
		$("#perAuthen_status").html("认证失败");
		$("#perAuthen_status").removeClass();
		$("#perAuthen_status").addClass("btn  btn-rzfail btn-sm ml30");
		$("#toPerAuthen_button").html("查看原因");
	}
	//司机认证
	if (2 == driverpercheck) {
		$("#drivAuthen_status").html("认证中");
		$("#drivAuthen_status").removeClass();
		$("#drivAuthen_status").addClass("btn  btn-primary btn-sm ml30");
		$("#todrivAuthen_button").hide();
	} else if (1 == driverpercheck) {
		$("#drivAuthen_status").html("司机认证成功");
		$("#drivAuthen_status").removeClass();
		$("#drivAuthen_status").addClass("btn  btn-rzsucc btn-sm ml30");
		$("#todrivAuthen_button").hide();
	} else if (3 == driverpercheck) {
		$("#drivAuthen_status").html("认证失败");
		$("#drivAuthen_status").removeClass();
		$("#drivAuthen_status").addClass("btn  btn-rzfail btn-sm ml30");
		$("#todrivAuthen_button").html("查看原因");
	}
}

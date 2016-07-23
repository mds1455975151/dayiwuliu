/**
 * ==================================================================
 * 添加车主功能与前后台交互 v1.0
 * ==================================================================
 * @author guyuke
 * @time 2016.06.06
 */

// 初始化处理
$(function() { 
	
    // 左侧导航选中效果
	$('#myVehiOwnerPage').addClass('selected');
	
	// 暂时删除隐藏数据
	$("#div_result").hide();
	
});

// 【搜索】按钮点击事件
$("#owner_searchBtn").click(function() {
	
	// 司机账号
	var owner_account = $("#owner_account").val();
	
	// 验证手机号
	// 手机号11位并且前三位只能为大陆手机号段
	var reg = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
	if (!reg.test(owner_account)) {
		$("#modal_common_content").html("请输入正确的11位手机账号！");
		$("#commonModal").modal();
		return ;
	}
	
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehiOwner/getInfoOutOfOwnerRange',// 跳转到 action
		data : {
			memberId: member_id,
			ownerTel: owner_account
		},
		type : "post",
		success : function(result){
			appendContentToBody(result);
		}
	});
});

/**
 * 搜索功能使用的循环函数体，用于附加动态表体内容至<div/>标签
 * 
 * @param data 动态获取的数据
 * @return null
 * @author guyuke
 * @time 2016.06.05
 */
function appendContentToBody(result) {
	
	var data = result.data;
	var code = result.code;
	var error = result.error;
	// 暂时隐藏数据
	$("#div_result").hide();
	// 清空数据
	$("#owner_name").html("");
	$("#owner_aaa").html("");
	$("#owner_tel").html("");
	
	// 数据为空时
	if (code != "000000") {
		$("#modal_common_content").html(error);
		$("#commonModal").modal();
		// 隐藏项账号清空
		$("#owner_account_back").val("");
		$("#isResultChangedFlg").val("0");
		
	// 有数据信息时
	} else {
		// 企业用户
		if (data.companypercheck == "1") {
			$("#th_name").html("企业名称");
			$("#th_cardName").html("营业执照号");
			// 企业名
			$("#owner_name").html(data.companyName);
			// 营业执照号
			$("#owner_cardNo").html(data.companycode);
			// 电话
			$("#owner_tel").html(data.cellPhone);
			
		// 个人用户
		} else if (data.userpercheck == "1") {
			$("#th_name").html("姓名");
			$("#th_cardName").html("身份证号");
			// 姓名
			$("#owner_name").html(data.userName);
			// 身份证号
			$("#owner_cardNo").html(data.identityCard);
			// 电话
			$("#owner_tel").html(data.cellPhone);
		} else if(data.driverpercheck == "1"){
			$("#th_name").html("姓名");
			$("#th_cardName").html("驾驶证号");
			// 姓名
			$("#owner_name").html(data.userName);
			// 身份证号
			$("#owner_cardNo").html(data.identityCard);
			// 电话
			$("#owner_tel").html(data.cellPhone);
		}
		
		$("#div_result").slideDown(1000);
		
		// 隐藏项账号、车主主键、防止重复添加Flg
		$("#owner_account_back").val(data.cellPhone);
		$("#ownerId").val(data.id);
		$("#isResultChangedFlg").val("1");
	}
}

//【添加】按钮点击事件
$("#owner_addBtn").click(function() {
	
	// 司机账号
	var owner_account = $("#owner_account").val();
	// 司机账号备份值
	var owner_account_back = $("#owner_account_back").val();
	// 司机主键
	var ownerId = $("#ownerId").val();
	// 姓名
	var owner_name = $("#owner_name").html();
	// 电话
	var owner_tel = $("#owner_tel").html();
	if (owner_account == "") {
		$("#modal_common_content").html("车主账号不能为空！");
		$("#commonModal").modal();
	} else if (owner_account != "" && owner_account_back == "") {
		$("#modal_common_content").html("请点击搜索按钮查询后进行添加！");
		$("#commonModal").modal();
	} else if (owner_account != owner_account_back) {
		$("#modal_common_content").html("车主账号输入值与查询结果不符，请重新搜索！");
		$("#commonModal").modal();
	} else if ($("#isResultChangedFlg").val() != "1"){
		$("#modal_common_content").html("请勿重复添加同一账号！");
		$("#commonModal").modal();
	} else {
		$.ajax({
			url : PATH + '/trwuliu/Member/myVehiOwner/saveMyVehicleOwner',// 跳转到 action
			data : {
				memberId: member_id,
				ownerId: ownerId,
				ownerName: owner_name,
				ownerTel: owner_tel
//				userName: userName
			},
			type : "post",
			success : function(result){
				if (result.code == "000000") {
					$("#modal_common_content").html("添加成功！");
					$("#commonModal").modal();
					// 防止重复添加Flg
					$("#isResultChangedFlg").val("0");
					
					// 延时关闭跳转
					setTimeout(function(){$("#commonModal").modal("hide");},3000);
					$("#commonModal").on("hidden.bs.modal", function() {
						window.location.href = PATH + "/trwuliu/Member/myVehiOwner/myVehiOwnerPage";
					});
				} else {
					$("#modal_common_content").html("添加失败，系统出错！");
					$("#commonModal").modal();
				}
			}
		});
	}
});

// 【取消】按钮点击事件
$("#owner_cancelBtn").click(function() {
	
	// 跳转我的车主画面
	window.location.href = PATH + "/trwuliu/Member/myVehiOwner/myVehiOwnerPage";
});



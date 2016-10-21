/**
 * ==================================================================
 * 添加司机功能与前后台交互 v1.0
 * ==================================================================
 * @author guyuke
 * @time 2016.06.03
 */

// 初始化处理
$(function() { 
	
    // 左侧导航选中效果
	$('#myDriverPage').addClass('selected');
	
	// 暂时删除隐藏数据
	$("#div_name").hide();
	$("#div_remarkName").hide();
	$("#div_dirverCard").hide();
	$("#div_dirverImg").hide();
	
});

// 【搜索】按钮点击事件
$("#driver_search").click(function() {
	
	// 司机账号
	var driver_tel = $("#driver_tel").val();
	
	// 验证手机号
	// 手机号11位并且前三位只能为大陆手机号段
	var reg = /^1[0-9]{10}$/;
	if (!reg.test(driver_tel)) {
		$("#modal_common_content").html("请输入正确的11位手机账号！");
		$("#commonModal").modal();
		return ;
	}
	
	$.ajax({
		url : PATH + '/trwuliu/Member/myDriver/getInfoOutOfDriverRange',// 跳转到 action
		data : {
			memberId: member_id,
			driverTel: driver_tel
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
 * @param result 动态获取的数据
 * @return null
 * @author guyuke
 * @time 2016.06.05
 */
function appendContentToBody(result) {
	
	var data = result.data;
	var code = result.code;
	// 暂时隐藏数据
	$("#div_name").hide();
	$("#div_remarkName").hide();
	$("#div_dirverCard").hide();
	$("#div_dirverImg").hide();
	// 清空数据
	$("#driver_name").val("");
	$("#dirver_remarkName").val("");
	$("#driver_img").removeAttr("src");
	
	// 数据为空时
	if (code != "000000") {
		$("#modal_common_content").html(result.error);
		$("#commonModal").modal();
		// 隐藏项账号清空
		$("#driver_tel_back").val("");
		// 防止重复添加Flg
		$("#isResultChangedFlg").val("0");
	// 有数据信息时
	} else {
		// 姓名userName
		$("#driver_name").val(data.userName);
		// 驾驶证图片
		$("#driver_img").attr("src", data.driveImagePath);
		$("#div_name").show(1000);
		$("#div_remarkName").show(1000);
		$("#div_dirverCard").show(1000);
		$("#div_dirverImg").show(1000);
		// 隐藏项账号、司机主键
		$("#driver_tel_back").val(data.cellPhone);
		$("#driverId").val(data.id);
		// 防止重复添加Flg
		$("#isResultChangedFlg").val("1");
	}
}

//【添加】按钮点击事件
$("#driver_addBtn").click(function() {
	
	// 司机账号
	var driver_tel = $("#driver_tel").val();
	// 司机账号备份值
	var driver_tel_back = $("#driver_tel_back").val();
	// 司机主键
	var driverId = $("#driverId").val();
	// 姓名
	var driverName = $("#driver_name").val();
	// 备注名
	var remarkName = $("#dirver_remarkName").val();
	if (driver_tel == "") {
		$("#modal_common_content").html("司机账号不能为空！");
		$("#commonModal").modal();
	} else if (driver_tel != "" && driver_tel_back == "") {
		$("#modal_common_content").html("请点击搜索按钮查询后进行添加！");
		$("#commonModal").modal();
	} else if (driver_tel != driver_tel_back) {
		$("#modal_common_content").html("司机账号输入值与查询结果不符，请重新搜索！");
		$("#commonModal").modal();
	} else if ($("#isResultChangedFlg").val() != "1"){
		$("#modal_common_content").html("请勿重复添加同一账号！");
		$("#commonModal").modal();
	} else {
		$.ajax({
			url : PATH + '/trwuliu/Member/myDriver/saveMyDriver',// 跳转到 action
			data : {
				memberId: member_id,
				driverId: driverId,
				driverName: driverName,
				driverTel: driver_tel,
				remarkName: remarkName,
				userName: "明克"
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
						window.location.href = PATH + "/trwuliu/Member/myDriver/myDriverPage";
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
$("#driver_cancelBtn").click(function() {
	
	// 跳转我的司机画面
	window.location.href = PATH + "/trwuliu/Member/myDriver/myDriverPage";
});



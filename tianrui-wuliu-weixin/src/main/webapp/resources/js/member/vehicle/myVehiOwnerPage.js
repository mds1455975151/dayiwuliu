/**
 * ==================================================================
 * 我的车主功能与前后台交互 v1.0
 * ==================================================================
 * @author guyuke
 * @time 2016.06.06
 */

// 表体行编号
var rowIndex = 0;

// 初始化处理
$(function() { 
    // 左侧导航选中效果
	$('#myVehiOwnerPage').addClass('selected');
	
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehiOwner/getMyVehiOwner',// 跳转到 action
		data : {
			memberId: memberId
		},
		type : "post",
		success : function(result) {
			var data = result.data;
			appendContentToBody(data, 0);
		}
	});
	
});

/**
 * 搜索功能使用的循环函数体，用于附加动态表体内容至<body/>标签
 * 
 * @param data 动态获取的数据
 * @param flag 0:搜索 1：新增
 * @return null
 * @author guyuke
 * @time 2016.06.05
 */
function appendContentToBody(data, flag) {
	// 搜索查询时清空表体，防止表体重复附加数据
	if (flag == 0) {
		$("#vehiOwner_tbody").empty();
		rowIndex = 0;
	}
	// 数据为空时
	if (data == null || data.length <= 0) {
		var hml = "";
		$(".goods_more").hide();
		hml+= '<div class="nodata">';
		hml+= '<img src="'+trImgRoot+'/none_owner.png">';
		if (flag == 0) {
			hml+= '<h3>您查询的车主不存在！换个条件试试吧！</h3>';
		}else{
			hml+= '<h3>您还未添加车主！赶快添加车主给他发货吧！</h3>';
		}
		hml+= '</div>';
		document.getElementById("vehicle_none").innerHTML = hml;
	// 有数据信息时
	} else {
		if (data.length > 99) {
			// 显示查看更多
			$(".goods_more").show(1000);
		} else {
			// 隐藏查看更多
			$(".goods_more").hide();
		}
		
		for (var i = 0; i < data.length; i++) {
			// 表体行号++
			rowIndex++;
			/** <tr> */
			var tr1 = $("<tr></tr>").attr("id","rowIndex" + rowIndex);
				/** <td> */
				var td1 = $("<td></td>").append(data[i].ownerName);
				/** <td> */
				var td2 = $("<td></td>").append(data[i].ownerTel);
				/** <td> */
				var td3 = $("<td></td>");
					// 状态："已拒绝"
				    if (data[i].status == "2") {
				    	/** <href> */
						var href3_1 = $("<a></a>")
					                   .attr("style", "color: #b3b3b3")
					                    .attr("data-toggle", "modal")
					                     .attr("data-target", "#refuse")
					                      .append(getStatusByCode(data[i].status));
						td3.append(href3_1);
					} else {
						td3.append(getStatusByCode(data[i].status));
					}
				/** <td> */
				var td4 = $("<td></td>").addClass("chezhu_mybtn");
					// 状态："已同意"
					if (data[i].status == "1") {
						// 启用
						if (data[i].isEnabled == "1") {
							/** <button> */
							var button4_1 = $("<button></button>")
											     .addClass("btn btnyello")
												  .html("显示车辆")
												   .attr("onclick", "vehicleDisplay('" + data[i].ownerId + "')");
							td4.append(button4_1);
						}
						/** <href> */
						var href4_1 = $("<a></a>")
						               .html(getNameByEnabledFlg(data[i].isEnabled))
						                .attr("href", "javascript:void(0);")
						                 .attr("onclick", 
						                		 "vehiOwnerEnDisable('" 
						                		   + data[i].id + "','" 
									                + data[i].isEnabled + "')");
						
						td4.append(href4_1);
					}
					/** <href> */
					var href4_2 = $("<a></a>")
					               .html("删除")
					                .attr("href", "javascript:void(0);")
					                 .attr("onclick", 
					                		 "vehiOwnerDel('" 
					                		   + data[i].id + "','rowIndex"
						                        + rowIndex + "')");
					td4.append(href4_2);
					
				/*TODO:测试用------BEGIN-------*/
				if (flag != 0) {
					td1.css("color", "red");
				}
				/*TODO:测试用------END-------*/
				
				tr1.append(td1).append(td2).append(td3).append(td4);
			
			if (flag == 0) {
				// 搜索查询时表体正常附加数据
				$("#vehiOwner_tbody").append(tr1);
			} else if (flag == 1) {
				// 新增时新增数据附加至表体第一行
				$("#vehiOwner_tbody").prepend(tr1);
			}
		}
	}
}

//『搜索』按钮点击事件
$("#vehiOwner_search").click(function() {
	
	// 搜索框输入的内容
	var searchText = $("#searchText").val();
	// 车主名字
	var ownerName = null;
	// 车主电话
	var ownerTel = null;
	
	if (searchText != null && searchText != "") {
		// 验证手机号,手机号11位并且前三位只能为大陆手机号段
		var regTel = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
		// 姓名，2个汉字以上，包含少数民族
		var regName =  /^\s*[\u4e00-\u9fa5]{1,}[\u4e00-\u9fa5.·]{0,15}[\u4e00-\u9fa5]{1,}\s*$/;
		if (!regTel.test(searchText) && !regName.test(searchText)) {
			$("#modal_common_content").html("请输入正确的汉字姓名或11位手机账号！");
			$("#commonModal").modal();
			return ;
		} else if (regTel.test(searchText)) {
			ownerTel = searchText;
		} else if (regName.test(searchText)) {
			ownerName = searchText;
		}
	}
	
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehiOwner/getMyVehiOwner',// 跳转到 action
		data : {
			memberId: memberId,
			ownerTel: ownerTel,
			ownerName: ownerName
		},
		type : "post",
		success : function(result) {
			if(result.code = "000000"){
				appendContentToBody( result.data, 0);
			}else {
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
			
		}
	});
	
});

/**
 * 表体行『显示车辆』按钮点击事件
 * 
 * @param           当前车主主键
 * @return false
 * @author guyuke
 * @time 2016.06.07
 */
function vehicleDisplay(id) {
	
	// 传递车主主键至隐藏项
	$("#modal_detail_id").val(id);
	
	// 弹出模态框
	$("#detailModal").modal();
	
}

// 表体行『显示车辆』功能打开模态框后事件
$('#detailModal').on('shown.bs.modal', function (e) {
	
	// 车牌号
	$("#modal_detail_vehiNo").val("");
	// 司机姓名
	$("#modal_detail_driverName").val("");
	// 司机电话
	$("#modal_detail_driverTel").val("");
	
	$("#modal_detail_search").trigger("click");
});

/**
 * 『显示车辆』模态框搜索功能使用的循环函数体，用于附加动态表体内容至<div/>标签
 * 
 * @param data 动态获取的数据
 * @return null
 * @author guyuke
 * @time 2016.06.07
 */
function appendContentToDetailModal(data) {
	
	// 清空div
	$("#modal_detail_div").empty();
	
	// 循环体
	for (var i = 0; i < data.length; i++) {
		/** <div> */
		var div1 = $("<div></div>").addClass("chezhu_modal_line");
			/** <label> */
			var label1_1 = $("<label></label>").append(data[i].vehicleNo);
			/** <label> */
			var label1_2 = $("<label></label>").append(data[i].driverName);
			/** <span> */
			var span1_1 = $("<span></span>").append(data[i].driverTel);
			div1.append(label1_1).append(label1_2).append(span1_1);
		$("#modal_detail_div").append(div1);
	}
}

//【显示车辆】模态框中『搜索』按钮点击事件
$("#modal_detail_search").click(function() {
	
	// 车主用户主键
	var ownerId = $("#modal_detail_id").val();
	// 车牌号
	var vehicleNo = $("#modal_detail_vehiNo").val();
	// 司机姓名
	var driverName = $("#modal_detail_driverName").val();
	// 司机电话
	var driverTel = $("#modal_detail_driverTel").val();
	
	if (driverName != null && driverName != "") {
		// 验证姓名，2个汉字以上，包含少数民族
		var regName =  /^\s*[\u4e00-\u9fa5]{1,}[\u4e00-\u9fa5.·]{0,15}[\u4e00-\u9fa5]{1,}\s*$/;
		if (!regName.test(driverName)) {
			$("#modal_common_content").html("请输入正确的汉字姓名！");
			$("#commonModal").modal();
			return ;
		}
	}
	
	if (driverTel != null && driverTel != "") {
		// 验证手机号,手机号11位并且前三位只能为大陆手机号段
		var regTel = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
		if (!regTel.test(driverTel)) {
			$("#modal_common_content").html("请输入正确的11位手机账号！");
			$("#commonModal").modal();
			return ;
		}
	}
	
	// 清空div
	$("#modal_detail_div").empty();
	
	$.ajax({
		url : PATH + '/trwuliu/Member/vehiDriver/getVehiDriver',// 跳转到 action
		data : {
			memberId: ownerId,
			vehicleNo: vehicleNo,
			driverName: driverName,
			driverTel: driverTel
		},
		type : "post",
		success : function(result) {
			var data = result.data;
			appendContentToDetailModal(data);
		}
	});
	
});

/**
 * 表体行中『开启/关闭』点击事件
 * 
 * @param ... 传递参数
 * @return null
 * @author guyuke
 * @time 2016.06.05
 */
function vehiOwnerEnDisable(id, isEnabledFlg) {
	// 主键
	$("#modal_enDisable_id").val(id);
	// 开启关闭flg
	$("#modal_enDisable_flg").val(isEnabledFlg);
	
	// title、content
	if (isEnabledFlg == "1") {
		$("#modal_enDisable_title").html("关闭");
		$("#modal_enDisable_content").html("您确定关闭吗？<br/><br/>关闭后将不能给该车主名下的司机直接派活！");
	} else {
		$("#modal_enDisable_title").html("开启");
		$("#modal_enDisable_content").html("您好，开启后您可以直接给该车主名下的司机派活。");
	}
	
	$("#enDisableModal").modal();
}

// 表体行中『开启/关闭』功能的『确定』按钮事件
$("#modal_enDisable_OK").click(function() {
	
	// 主键
	var id = $("#modal_enDisable_id").val();
	// 开启关闭flg
	var enableFlg = $("#modal_enDisable_flg").val();
	
	if (enableFlg == "1") {
		enalbeFlg = "0";
	} else {
		enalbeFlg = "1";
	}
	
	// 隐藏模态框
	$("#enDisableModal").modal('hide');
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehiOwner/updateMyVehicleOwner',// 跳转到 action
		data : {
			 id: id,
		     isEnabled: enalbeFlg
		},
		type : "post",
		success : function(result) {
			if (result.code == "000000") {
				$("#modal_common_content").html("修改完成！");
				$("#vehiOwner_search").trigger("click");
			} else {
				$("#modal_common_content").html("修改失败！");
			}
			$("#commonModal").modal();
		}
	});
});

/**
 * 表体行中『删除』点击事件
 * 
 * @param id        主键
 * @param vrowIndex 行号
 * @return null
 * @author kowuka
 * @time 2016.06.05
 */
function vehiOwnerDel(id, vrowIndex) {
	// 赋值隐藏项项
	$("#modal_del_id").val(id);
	$("#modal_del_rowIndex").val(vrowIndex);
	
	$("#delModal").modal('show');
	
}

// 表体行『删除』功能的『确定』按钮事件
$("#modal_del_yes").click(function() {
	
	var id = $("#modal_del_id").val();
	var vrowIndex = $("#modal_del_rowIndex").val();
	
	// 模态框隐藏
	$("#delModal").modal('hide');
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehiOwner/deleteMyVehicleOwner',// 跳转到 action
		data : {
			id: id
		},
		type : "post",
		success : function(result) {
			if (result.code == "000000") {
				$("#modal_common_content").html("删除完成！");
				$("#" + vrowIndex).remove();
			} else {
				$("#modal_common_content").html("删除失败！");
			}
			$("#commonModal").modal();
		}
	});
});

/**
 * 根据状态码获取状态名称
 * 
 * @param statusCode      状态代码
 * @return 状态名称
 * @author guyuke
 * @time 2016.06.07
 */
function getStatusByCode(statusCode) {
	var statusName = "";
	if (statusCode == "0") {
		statusName = "待确认";
	} else if (statusCode == "1") {
		statusName = "已同意";
	} else if (statusCode == "2") {
		statusName = "已拒绝";
	}
	
	return statusName;
}

/**
 * 根据isEnabled值判断开启/关闭
 * 
 * @param isEnabled      
 * @return 开启/关闭
 * @author guyuke
 * @time 2016.06.07
 */
function getNameByEnabledFlg(isEnabledCode) {
	var statusName = "";
	if (isEnabledCode == "1") {
		statusName = "关闭  ";
	} else {
		statusName = "开启  ";
	}
	
	return statusName;
}


/**
 * ==================================================================
 * 我的车辆功能与前后台交互 v1.0
 * ==================================================================
 * @author guyuke
 * @time 2016.06.07
 */

/** 表体行编号 */
var rowIndex = 0;
/** 司机绑定窗口 */
var bindDiv = $("#bindDiv");
/** 我的未绑定司机信息 */
var driverList = null;

var pageNo = 1;

// 初始化处理
$(function() { 
    // 左侧导航选中效果
	$('#myVehiclePage').addClass('selected');
	
	vehiAndDriver();
	// 获取绑定外司机的数据
	getTheDriverOutOfRange();
	
});

function vehiAndDriver(){
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehicle/getVehiAndDriver',// 跳转到 action
		data : {
			memberId: member_id,
		},
		type : "post",
		success : function(result) {
			appendContentToBody(result, 0);
		}
	});
}
function addPage(){
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehicle/getVehiAndDriver',// 跳转到 action
		data : {
			memberId: member_id,
			pageNo : pageNo,
		},
		type : "post",
		success : function(result) {
			appendContentToBody(result, 1);
		}
	});
}

/**
 * 搜索功能使用的循环函数体，用于附加动态表体内容至<body/>标签
 * 
 * @param data 动态获取的数据
 * @param flag 0:搜索 1：新增
 * @author guyuke
 * @time 2016.06.07
 */
function appendContentToBody(result, flag) {
	
	var data = result.data.list;
	var total = result.data.total;
	pageNo = result.data.pageNo;
	if(total / 10 < pageNo){
		$(".pageMore").hide();
	}
	pageNo = pageNo + 1;
	// 搜索查询时清空表体，防止表体重复附加数据
	if (flag == 0) {
		$("#vehicle_tbody").empty();
		rowIndex = 0;
	}
	// 数据为空时
	if (total == 0) {
		var hml = "";
		hml+= '<div class="nodata">';
		hml+= '<img src="'+trImgRoot+'/none_car.png">';
		if (flag == 0) {
			hml+= '<h3>您查询的车辆不存在！换个条件试试吧！</h3>';
		}else {
			hml+= '<h3>您还未添加车辆！赶快添加车辆接活吧！</h3>';
		}
		hml+= '</div>';
		document.getElementById("vehicle_none").innerHTML = hml;
	// 有数据信息时
	} else {
		$("#vehicle_count").html("共" + data.length + "辆车");
		for (var i = 0; i < data.length; i++) {
			// 表体行号++
			rowIndex++;
			/** <tr> */
			var tr1 = $("<tr></tr>").attr("id","rowIndex" + rowIndex);
				/** <td> */
				var td1 = $("<td></td>");
					/** <img> */
					var img1 = $("<img></img>").attr("src", data[i].vehiHeadImgPath);
					/** <div> */
					var div1 = $("<div></div>").addClass("car_cont1");
						/** <p> */
						var p1_1 = $("<p></p>");
							/** <i> */
							var i1 = $("<i></i>").append(data[i].vehiPrefix + data[i].vehiNo);
							p1_1.append(i1);
						/** <p> */
						var p1_2 = $("<p></p>");
							/** <i> */
							var i2 = $("<i></i>").append(data[i].driverName);
							/** <em> */
							var em2 = $("<em></em>").append(data[i].driverTel);
							p1_2.append(i2).append(em2);
						/** <p> */
						var p1_3 = $("<p></p>").append(data[i].vehiTypeName);
							/** <span> */
							var span1 = $("<span></span>").append("|");
							p1_3.append(span1).append(data[i].vehiLength + "米");
							/** <span> */
							var span2 = $("<span></span>").append("|");
							p1_3.append(span2).append(data[i].vehiWeight + "吨");
						div1.append(p1_1).append(p1_2).append(p1_3);
					td1.append(img1).append(div1);
				/** <td> */
				var td2 = $("<td></td>").append(getStatusByCode(data[i].status));
				/** <td> */
				var bs = "";
				if(data[i].billstatus == 2){
					bs = "发货中";
				}else if(data[i].billstatus == 3){
					bs = "运输中";
				}else if(data[i].billstatus == 4){
					bs = "卸货中";
				}else{
					bs = "空闲";
				}
				var td3 = $("<td></td>").append(bs);
				/** <td> */
				var td4 = $("<td></td>").attr("id","rowIndex" + rowIndex + "_td4");
					if (data[i].status == "-1") {
						td4.addClass("f12 bill_lineh2");
						/** <button> */	
						var button1 = $("<button onclick=\"faildetails('"+data[i].id+"')\"></button>")
										.addClass("btn btnyello")
										 .append("查看失败原因");
						td4.append(button1);
					} else if (data[i].status == "0") {
						td4.addClass("car_mycarline");
					} else if (data[i].status == "1") {
						
						var drid = data[i].vehiDriverId
						if(drid != undefined){
							td4.addClass("f12 bill_lineh2");
							/** <button> */	
							var button1 = $("<button onclick=\"revokeDriver('"+data[i].vehiDriverId+"','"+data[i].billstatus+"')\"></button>")
											.addClass("btn btnyello")
											 .append("取消绑定");
							td4.append(button1);
						}else{
							td4.addClass("car_mycarline");
							var p4_2 = $("<p></p>")
							.append("绑定司机")
							.attr("onclick", 
									"vehiBind('rowIndex" 
									+ rowIndex + "_td4','"
									+ data[i].vehiDriverId + "','"
									+ data[i].vehiId + "','"
									+ data[i].vehiPrefix + data[i].vehiNo + "','"
									+ data[i].vehiTypeName +  "')");
							td4.append(p4_2);
						}
						
					} else if (data[i].status == "2") {
						td4.addClass("f12 bill_lineh2");
						/** <button> */	
						var button1 = $("<button></button>")
										.addClass("btn btnyello")
										 .append("认证中");
						td4.append(button1);
					} 
				tr1.append(td1).append(td2).append(td3).append(td4);
			
			if (flag == 0) {
				// 搜索查询时表体正常附加数据
				$("#vehicle_tbody").append(tr1);
			} else if (flag == 1) {
				// 新增时新增数据附加至表体第一行
//				$("#vehicle_tbody").prepend(tr1);
				$("#vehicle_tbody").append(tr1);
			}
		}
	}
}
//取消绑定
function revokeDriver(id,billstatus){
	if(billstatus == 2 || billstatus == 3 || billstatus == 4 ){
		alert("非空闲车辆不能解除绑定");
		return;
	}
	$.ajax({
		url : PATH + '/trwuliu/Member/vehiDriver/deleteVehiDriver',// 跳转到 action
		data : {
			id:id
		},
		type : "post",
		success : function(result) {
			if(result.code=="000000"){
				vehiAndDriver();
				getTheDriverOutOfRange();
			}
		}
	});
}

// 表头『搜索车辆』按钮点击事件
$("#vehicle_searchBtn").click(function() {
	
	// 司机姓名或电话
	var nameOrTel = $("#nameOrTel").val();
	// 司机名字
	var driverName = null;
	// 司机电话
	var driverTel = null;
	
	if (nameOrTel != null && nameOrTel != "") {
		// 验证手机号,手机号11位并且前三位只能为大陆手机号段
		var regTel = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
		// 姓名，2个汉字以上，包含少数民族
		var regName =  /^\s*[\u4e00-\u9fa5]{1,}[\u4e00-\u9fa5.·]{0,15}[\u4e00-\u9fa5]{1,}\s*$/;
		if (!regTel.test(nameOrTel) && !regName.test(nameOrTel)) {
			$("#modal_common_content").html("请输入正确的汉字姓名或11位手机账号！");
			$("#commonModal").modal();
			return ;
		} else if (regTel.test(nameOrTel)) {
			driverTel = nameOrTel;
		} else if (regName.test(nameOrTel)) {
			driverName = nameOrTel;
		}
	}
	
	// 车牌号
	var vehiNo = $("#vehiNo").val();
	if (vehiNo != "" && vehiNo.length < 2) {
		$("#modal_common_content").html("请输入正确车牌号！");
		$("#commonModal").modal();
		return ;
	}
	// 车长
	var vehiLength = $("#vehiLength").val();
	// 车型
	var vehiType = $("#vehiType option:selected").val();
	if (vehiType == "0") {
		vehiType = null;
	}
	
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehicle/getVehiAndDriver',// 跳转到 action
		data : {
			memberId: member_id,
            vehiWholeNo: vehiNo, 
            vehiLenth: vehiLength, 
            vehiType: vehiType,
            driverName: driverName,
            driverTel: driverTel
		},
		type : "post",
		success : function(result) {
//			var flag = 1;
//			if(nameOrTel || vehiNo || vehiLength || vehiType){
//				flag = 0;
//			}
			appendContentToBody(result, 0);
		}
	});
	
});

/**
 * 表体行『绑定司机』事件
 * 
 * @param vrowIndex_td td对应ID
 * @param vehiId       车辆主键
 * @param vehiNo 	   车牌号
 * @param vehiTypeName 车型名
 * @author guyuke
 * @time 2016.06.12
 */
function vehiBind(vrowIndex_td, vehiDriverId, vehiId, vehiNo, vehiTypeName) {
	
	/** 隐藏项：车辆司机表主键 */
	if (vehiDriverId == "undefined") {
		$("#car_bdbox_vehiDriverId").val("");
	} else {
		$("#car_bdbox_vehiDriverId").val(vehiDriverId);
	}
	/** 隐藏项：车辆主键 */
	$("#car_bdbox_vehiId").val(vehiId);
	/** 隐藏项：车牌号 */
	$("#car_bdbox_vehiNo").val(vehiNo);
	/** 隐藏项：车辆类型名 */
	$("#car_bdbox_vehiTypeName").val(vehiTypeName);
	
	// 暂时解除绑定关系
	bindDiv.detach();
	
	// 重新建立绑定关系
	$("#" + vrowIndex_td).append(bindDiv);
	bindDiv.show();
	$("#driverUl").empty();
	if (driverList == null || driverList.length <= 0) {
		/** <li> */	
		var li1 = $("<li></li>");
			/** <label> */	
			var label1 = $("<label></label>").append("没有可用的司机信息！");
			li1.append(label1);
		$("#driverUl").append(li1);
	} else {
		for (var i=0; i<driverList.length; i++) {
			var data = driverList[i];
			appendContentToUl(data, i, vehiDriverId, vehiId, vehiNo, vehiTypeName);
		}
		
		// 司机信息列表某一项选中
	    var chren = $('.car_mycar .car_bdbox ul li');
	    chren.click(function(){
	        $(this).addClass('select').siblings().removeClass('select');
	    });
	}
	
	// 绑定信息框关闭按钮
    var cyclose = $('.car_bdbox i');
    cyclose.click(function(){
        $(".car_mycar .car_bdbox").hide();
    });
    
    return false;
}

/**
 * 搜索功能使用的循环函数体，用于附加动态表体内容至<body/>标签
 * 
 * @param data 动态获取的数据
 * @author guyuke
 * @time 2016.06.07
 */
function appendContentToUl(data, varI, vehiDriverId, vehiId, vehiNo, vehiTypeName) {
	
	/** =======================此处坑爹，隐藏项不能及时更新 =======================*/
	/** 隐藏项：车辆司机表主键 *//*
	var vehiDriverId = $("#car_bdbox_vehiDriverId").val();
	*//** 隐藏项：车辆主键 *//*
	var vehiId = $("#car_bdbox_vehiId").val();
	*//** 隐藏项：车牌号 *//*
	var vehiNo = $("#car_bdbox_vehiNo").val();
	*//** 隐藏项：车辆类型名 *//*
	var vehiTypeName = $("#car_bdbox_vehiTypeName").val();*/
	
	/** <li> */	
	var li1 = $("<li></li>");
		/** <input> *//*	
		var input1 = $("<input type='checkbox'/>");*/
		/** <label> */	
		var label1 = $("<label></label>").append(data.drivername);
		/** <label> */	
		var label2 = $("<label></label>").append(data.drivertel);
		/** <span> */	
		var span1 = $("<span></span>").attr("id", "driverUl_li_span" + varI)
					   .append("绑定")
		                .attr("onclick", 
		            		   "driverBind('" + vehiDriverId + "','"
		                					   + vehiId + "','"
		            		   				    + data.driverid + "','"
			            		   				 + vehiNo + "','"
			            		   				  + vehiTypeName + "','"
			            		   				   + data.drivername + "','"
			            		   				    + data.drivertel + "','"
			            		   				     + data.remarkname + "',"
			            		   				      + varI + ")");
		li1/*.append(input1)*/.append(label1).append(label2).append(span1);
	$("#driverUl").append(li1);
}
/**
 * 查看审核失败详情
 */
function faildetails(id){
	window.location.href = PATH + "/trwuliu/Member/myVehicle/vehicleFilePage?id="+id;
}
/**
 * 『绑定司机』中『绑定』事件
 * 
 * @param vehiId       车辆主键
 * @param driverid     司机ID
 * @param vehiNo 	   车牌号
 * @param vehiTypeName 车型名
 * @param drivername   司机名字
 * @param drivertel    司机电话
 * @param remarkname   司机备注名
 * @param vListNo      全局变量driverList中的位置
 * @return null
 * @author guyuke
 * @time 2016.06.13
 */
function driverBind(vehiDriverId, vehiId, driverid, vehiNo, vehiTypeName, 
					        drivername, drivertel, remarkname, vListNo) {
	
	// 删除原有信息(如果有)，并绑定新信息
	$.ajax({
		url : PATH + '/trwuliu/Member/vehiDriver/delAndSaveVehiDriver',
		data : {
			memberId: member_id,
			vehiDriverId: vehiDriverId,
	        driverId: driverid, 
	        driverName: drivername, 
	        driverTel: drivertel, 
	        driverRemark: remarkname, 
	        vehicleId: vehiId, 
	        vehicleNo: vehiNo, 
	        vehiTypeName: vehiTypeName, 
	        vehiRemark: null
		},
		type : "post",
		success : function(result) {
			if (result.code == "000000") {
				// 此行代码留作后用
				/*driverList.splice(vListNo, 1);
				$("#driverUl_li_span" + vListNo).removeAttr("onclick").html("已绑定");*/
				
				// 刷新绑定外司机的数据
				getTheDriverOutOfRange();
				
				$(".car_mycar .car_bdbox").hide();
				$("#modal_common_content").html("绑定成功！");
				$("#commonModal").modal();
				
				// 延时关闭
				setTimeout(function(){$("#commonModal").modal("hide");},1000);
				$("#commonModal").on("hidden.bs.modal", function() {
					$("#vehicle_searchBtn").trigger("click");
				});
				
			} else {
				$("#modal_common_content").html("绑定失败，系统出错！");
				$("#commonModal").modal();
			}
		}
	});
	
	return false;
}

//『绑定司机』中『搜索』按钮点击事件
$("#car_bdbox_searchBtn").click(function() {
	
	// 司机姓名
	var driverName = $("#car_bdbox_driverName").val();
	// 司机电话
	var driverTel = $("#car_bdbox_driverTel").val();
	
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
	
	// 查询操作
	var hasDataFlg = false;
	$("#driverUl").empty();
	if (driverName == "" && driverTel == "") {
		for (var i=0; i<driverList.length; i++) {
			appendContentToUl(driverList[i], i);
			hasDataFlg = true;
		}
	} else if (driverName != "" && driverTel == "") {
		for (var i=0; i<driverList.length; i++) {
			var j = 0;
			if (driverList[i].drivername == driverName) {
				appendContentToUl(driverList[i], j);
				hasDataFlg = true;
				j++;
			}
		}
	} else if (driverName == "" && driverTel != "") {
		for (var i=0; i<driverList.length; i++) {
			var j = 0;
			if (driverList[i].drivertel == driverTel) {
				appendContentToUl(driverList[i], j);
				hasDataFlg = true;
				j++;
			}
		}
	} else if (driverName != "" && driverTel != "") {
		for (var i=0; i<driverList.length; i++) {
			var j = 0;
			if (driverList[i].drivername == driverName && 
					driverList[i].drivertel == driverTel) {
				appendContentToUl(driverList[i], j);
				hasDataFlg = true;
				j++;
			}
		}
	}
	
	if (!hasDataFlg) {
		/** <li> */	
		var li1 = $("<li></li>");
			/** <label> */	
			var label1 = $("<label></label>").append("没有可用的司机信息！");
			li1.append(label1);
		$("#driverUl").append(li1);
	}
	
});

/**
 * 获取绑定外司机的数据
 * 
 * @return driverList
 * @author guyuke
 * @time 2016.06.15
 */
function getTheDriverOutOfRange() {
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehicle/getMyDriverOutOfRange',// 跳转到 action
		data : {
			memberId: member_id
		},
		type : "post",
		success : function(result) {
			driverList = result.data;
		}
	});
}

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
	if (statusCode == "-1") {
		statusName = "认证失败";
	} else if (statusCode == "0") {
		statusName = "未认证";
	} else if (statusCode == "1") {
		statusName = "认证成功";
	} else if (statusCode == "2") {
		statusName = "认证中";
	}
	
	return statusName;
}


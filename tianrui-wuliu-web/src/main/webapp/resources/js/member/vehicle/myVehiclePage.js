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
	$("#vehicle_count").html("共" + total + "辆车");
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
		document.getElementById("vehicle_tbody").innerHTML = hml;
	// 有数据信息时
	} else {
		//document.getElementById("vehicle_none").innerHTML = "";
		for (var i = 0; i < data.length; i++) {
			// 表体行号++
			rowIndex++;
			/** <tr> */
			var tr1 = $("<tr></tr>").attr("id","rowIndex" + rowIndex);
				/** <td> */
				var td1_1 = $("<td></td>");
				var div_001 = $("<div></div>").addClass("car_myimg");
				/** <img> */
				var img1 = $("<img></img>").attr("src", data[i].vehiHeadImgPath);
				div_001.append(img1);
				td1_1.append(div_001);
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
					//安联开票
					if(data[i].aldriverid){
						p1_2.append($("<p></p>").append(data[i].aldriverid));
					}
				/** <p> */
				var p1_3 = $("<p></p>").append(data[i].vehiTypeName);
					/** <span> */
					var span1 = $("<span></span>").append("|");
					p1_3.append(span1).append(data[i].vehiLength + "米");
					/** <span> */
					var span2 = $("<span></span>").append("|");
					p1_3.append(span2).append(data[i].vehiWeight + "吨");
				div1.append(p1_1).append(p1_2).append(p1_3);
				var td1_2 = $("<td></td>").append(div1);
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
				}else if(data[i].billstatus == 5){
					bs = "空闲";
				}
				var td3 = $("<td></td>").append(bs);
				var dc2 ="";
				if(data[i].desc2==1){
					dc2 = "临时认证";
				}else if(data[i].desc2==2){
					dc2 = "完全认证";
				}
				var td3_1 = $("<td></td>").append(dc2);
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
							
							var p4_2 = $("<button class='btn btn_blueborder'></button>")
							.append("绑定司机")
							.attr("onclick", 
									"vehiBind('rowIndex" 
									+ rowIndex + "_td4','"
									+ data[i].vehiDriverId + "','"
									+ data[i].vehiId + "','"
									+ data[i].vehiPrefix + data[i].vehiNo + "','"
									+ data[i].desc1 + "','"
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
					
				var button5 = "";
				//非空闲车辆 非认证成功 不能进行开票认证 临时车辆 
				if(data[i].status == "1"&&data[i].billstatus == 5&&data[i].desc2 == 2){
					if(data[i].desc1 == "0"||data[i].desc1 == undefined){
						button5 = $("<button  onclick=\"kaipiaoView('"+data[i].id+"','"+data[i].driverTel+"')\"></button>")
						.addClass("btn btnyello")
						.append("开票认证");
					}else if(data[i].desc1 == "1"){
						button5 = $("<button ></button>")
						.addClass("btn btnyello")
						.append("开票认证成功");
					}else if(data[i].desc1 == "2"){
						button5 = $("<button ></button>")
						.addClass("btn btnyello")
						.append("开票认证中");
					}else if(data[i].desc1 == "-1"){
						button5 = $("<button  onclick=\"kaipiaoUpt('"+data[i].id+"','"+data[i].driverTel+"')\"></button>")
						.addClass("btn btnyello")
						.append("开票认证失败");
					}	
				}
				var td5 = $("<td></td>");
				    td5.addClass("f12 bill_lineh2");
				    td5.append(button5);
				
				var button6 = "";
				//1临时车辆 2认证车辆
				if(data[i].desc2 == 2){
					button6 = "信息齐全";
				}else if(data[i].desc2 == 1){
					button6 = $("<button  onclick=\"buquanxinxi('"+data[i].id+"')\"></button>")
					.addClass("btn btnyello")
					.append("补全信息");
				}
				var td6 = $("<td></td>");
				    td6.addClass("f12 bill_lineh2");
				    td6.append(button6);
				
				
				tr1.append(td1_1).append(td1_2).append(td3).append(td2).append(td3_1).append(td4).append(td5).append(td6);
			
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
//开票认证 页面跳转
function kaipiaoView(id,driverTel){
	if(driverTel == 'undefined'){
		window.location.href = "/trwuliu/Member/vehicleticket/kaipiaoPage?id="+id;
	}else{
		alert("绑定司机车辆，不能进行开票认证");
	}
}
function kaipiaoUpt(id,driverTel){
	if(driverTel == 'undefined'){
		window.location.href = "/trwuliu/Member/vehicleticket/filePage?vehicleid="+id;
	}else{
		alert("绑定司机车辆，不能进行开票认证");
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
			}else{
				alert(result.error);
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
		// 验证手机号,手机号11位并且前1位只能为1
		var regTel = /^1\d{10}$/;
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
	// 车牌前缀
	var vehiFix = $("#vehiFix").val();
	// 车长
	//var vehiLength = $("#vehiLength").val();
	// 车型
	var vehiType = $("#vehiType option:selected").val();
	if (vehiType == "0") {
		vehiType = null;
	}
	
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehicle/getVehiAndDriver',// 跳转到 action
		data : {
			memberId: member_id,
			vehiNo: vehiNo, 
			vehiFix: vehiFix, 
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
function vehiBind(vrowIndex_td, vehiDriverId, vehiId, vehiNo,alstatus, vehiTypeName) {
	
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
	/** 隐藏车辆安联认证状态*/
	$("#car_bdbox_alstatus").val(alstatus);
	//  -1 认证失败 0 未认证 1 认证成功 2 认证中
	if(alstatus==2){
		alert("车辆开票认证中,不能绑定司机");
		return;
	}
	
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
		if(data.count!=0){
			var span1 = $("<span class='coloryello'></span>").append("已绑定")
		}else{
			var span1 = $("<button class='btn btnblue'></button>").attr("id", "driverUl_li_span" + varI)
			.append("绑定")
			.attr("onclick", 
					"driverBind('" + vehiDriverId + "','"
					+ vehiId + "','"
					+ data.driverid + "','"
					+ vehiNo + "','"
					+ vehiTypeName + "','"
					+ data.drivername + "','"
					+ data.drivertel + "','"
					+ data.remarkname + "','"
					+ data.aldriverid + "',"
					+ varI + ")");
		}
		/** <span> */	
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
 * 查看审核失败详情
 */
function buquanxinxi(id){
	window.location.href = PATH + "/trwuliu/Member/myVehicle/updateLinPage?id="+id;
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
					        drivername, drivertel, remarkname, aldriverid, vListNo) {
	
	var status = $("#car_bdbox_alstatus").val();
	//车辆，司机 均安联认证成功
	if(status == 1 && aldriverid != ""){
		$(".car_mycar .car_bdbox").hide();
		confirm("操作提示","车辆，司机均安联认证通过，绑定后无法解绑,是否确认操作?",function(){
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
						
						$("#modal_common_content").html("绑定成功！");
						$("#commonModal").modal();
						
						// 延时关闭
						setTimeout(function(){$("#commonModal").modal("hide");},1000);
						$("#commonModal").on("hidden.bs.modal", function() {
							$("#vehicle_searchBtn").trigger("click");
						});
						
					} else {
						//TODO
						if(result.error=='IDHadBeenRegistered'){
							al_driver_bding(driverid,vehiId,drivername,drivertel,vehiNo,result.data);
						}else{
							$("#modal_common_content").html("绑定失败，请联系后台管理员...");
							$("#commonModal").modal();
						}
					}
				}
			});
			
		});
	}else{
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
					$("#modal_common_content").html(result.error);
					$("#commonModal").modal();
				}
			}
		});
	}
	return false;
}


/**
 * 获取绑定外司机的数据
 * 
 * @return driverList
 * @author guyuke
 * @time 2016.06.15
 */
function getTheDriverOutOfRange() {
	driverList = null;
	$("#driverUl").empty();
	// 司机姓名
	var driverName = $("#car_bdbox_driverName").val();
	// 司机电话
	var driverTel = $("#car_bdbox_driverTel").val();
	$.ajax({
		url : PATH + '/trwuliu/Member/myVehicle/getMyDriverOutOfRange',// 跳转到 action
		data : {
			memberId: member_id,
			driverName:$.trim(driverName),
			driverTel:$.trim(driverTel)
		},
		type : "post",
		success : function(result) {
			driverList = result.data;
			for (var a = 0; a < driverList.length; a++) {
				appendContentToUl(driverList[a], a);
			}
		}
	});
}
//安联车辆司机绑定
function al_driver_bding(driverid,vehiId,drivername,drivertel,vehiNo,idcard){
	$("#driver_id").val(driverid);
	$("#vehicle_id").val(vehiId);
	$("#driver_name").html(drivername);
	$("#driver_cellphone").html(drivertel);
	$("#driver_idcard").html(idcard);
	$("#vehicle_no").html(vehiNo);
	$("#error_massage").html("");
	$("#al_driver_id").val("");
	$("#vehicleModal").modal();
}

function saveAldriverVehicle(){
	var driverid = $("#driver_id").val();
	var vheicleid = $("#vehicle_id").val();
	var drivername = $("#driver_name").html();
	var alid = $("#al_driver_id").val();
	if(alid.length!=8){
		$("#error_massage").html("错误提示：安联账号长度为8位");
		return;
	}
	if(alid.substring(0,1)!="d"&&alid.substring(0,1)!="D"){
		$("#error_massage").html("错误提示：安联账号首字母以D开头");
		return;
	}
	$.ajax({
		url :'/trwuliu/Member/vehiDriver/alSaveDriverVehicle',// 跳转到 action
		data : {
			"driverId": driverid,
			"vehicleId":$.trim(vheicleid),
			"driverName":$.trim(drivername),
			"alDriverid":alid
		},
		type : "post",
		success : function(result) {
			if(result.code=="000000"){
				// 刷新绑定外司机的数据
				getTheDriverOutOfRange();
				$("#vehicleModal").modal("hide");
				$("#modal_common_content").html("绑定成功！");
				$("#commonModal").modal();
				
				// 延时关闭
				setTimeout(function(){$("#commonModal").modal("hide");},1000);
				$("#commonModal").on("hidden.bs.modal", function() {
					$("#vehicle_searchBtn").trigger("click");
				});
			}else{
				$("#error_massage").html("操作异常，请稍候再试...");
			}
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


/**
 * ==================================================================
 * 我的司机功能与前后台交互 v1.0
 * ==================================================================
 * @author guyuke
 * @time 2016.06.03
 */

// div块编号
var divNo = 0;

//页码
var pageNo = 1;
//每页条数
var pageSize = 4;

// 初始化处理
$(function() { 
    // 左侧导航选中效果
	$('#myDriverPage').addClass('selected');
	$('#searchDriver').off('click').on('click',function(){
		pageNo = 1;
		searchDriver(pageNo, pageSize, 0);
	});
	searchDriver(pageNo, pageSize, 0);
});

function getMyDriver(){
	$.ajax({
		url : PATH + '/trwuliu/Member/myDriver/getMyDriver',// 跳转到 action
		data : {
			memberId: memberId
		},
		type : "post",
		success : function(result) {
			if(result && result.code=="000000") {
				var data = result.data;
				appendContentToBody(data, 0);
			}
		}
	});
}
function searchDriver(pagenum, pagesize, flag){
	var drivername = $("#drivername").val();
	var drivertell = $("#drivertell").val();
	$.ajax({
		url : PATH + '/trwuliu/Member/myDriver/getMyDriverByPage',// 跳转到 action
		data : {
			memberId: memberId,
			driverName:drivername,
			driverTel:drivertell,
			pageNo:pagenum,
			pageSize:pagesize
		},
		type : "post",
		success : function(result) {
			if(result && result.code=="000000") {
				var data = result.data.list;
				if(data && data.length > 0){
					if(data.length == 4){
						$('.pageMore').show();
					}else{
						$('.pageMore').hide();
					}
					appendContentToBody(data, flag);
				}else{
					$('.pageMore').hide();
				}
			}
		}
	});
}
function moreDriver(){
	if(($('#driver_div').children().length)%2 == 0){
		searchDriver(++pageNo,pageSize,1);
	}else{
		$('#driver_div').children(':last').remove();
		searchDriver(pageNo,pageSize,1);
	}
}
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
		$("#driver_div").empty();
		divNo = 0;
	}
	
	// 数据为空时
	if (data == null || data.length <= 0) {
		var hml = "";
		$(".goods_more").hide();
		hml+= '<div class="nodata">';
		hml+= '<img src="'+trImgRoot+'/none_drive.png">';
		hml+= '<h3>暂未找到司机！赶快添加司机给他派单吧！</h3>';
		hml+= '</div>';
		document.getElementById("driver_div").innerHTML = hml;
	// 有数据信息时
	} else {
		for (var i = 0; i < data.length; i++) {
			// 表体行号++
			divNo++;
			
			/** <div> */
			var div1 = $("<div ></div>").attr("id","divNo" + divNo).addClass("driver_solo");
				/** <h4> */
			    var h4 = $("<h4></h4>").append(data[i].drivername);
			    /** <div> */
			    var div1_1 = $("<div></div>").addClass("driver_cont");
			    	/** <p> */
			    var remarkname = data[i].remarkname;
			    if(data[i].remarkname == undefined){
			    	remarkname = "";
			    }
			    var p1 = $("<p></p>").append("备注名：" + remarkname);
			    	/** <p> */
			    	var p2 = $("<p></p>").append("手&nbsp;&nbsp;&nbsp;机：" + data[i].drivertel);
			    	/** <p> */
			    	var p3 = $("<p></p>").append("状&nbsp;&nbsp;&nbsp;态：" + getStatusByCode(data[i].status));
			    	div1_1.append(p1).append(p2).append(p3);
			    	
			    	// 状态："已同意"
			    	if (data[i].status == "1") {
			    		/** <button> */
			    		var button1 = $("<button></button>")
							    		.addClass("btn btnblue")
							    		 .append("修改")
							    		  .attr("onclick", "driverEdit('" + data[i].id + "','" 
													    				   + data[i].drivername + "','" 
													    				    + data[i].drivertel + "','" 
													    				     + data[i].remarkname + "')");
			    		div1_1.append(button1);
			    	}
			    	/** <button> */
			    	var button2 = $("<button></button>")
			    	                .addClass("btn btnyello")
			    	                 .append("删除")
			    	                  .attr("onclick", "driverDel('" + data[i].id + "','divNo"
											                          + divNo + + "','"
											                           + data[i].drivertel + "')");
			    	                  
			    	div1_1.append(button2);
			    div1.append(h4).append(div1_1);
			$("#driver_div").append(div1);
		}
	}
}

/**
 * 表体行中『修改』点击事件
 * 
 * @param ... 传递参数
 * @return null
 * @author guyuke
 * @time 2016.06.05
 */
function driverEdit(id, driverName, driverTel, remarkName) {
	// 主键
	$("#modal_edit_id").val(id);
	// 司机名字
	$("#modal_edit_driverName").val(driverName);
	// 司机电话
	$("#modal_edit_driverTel").val(driverTel);
	// 备注名
	$("#modal_edit_remarkName").val(remarkName);
	
	$("#editModal").modal();
}

// 表体行中『修改』功能的『保存』按钮事件
$("#modal_edit_save").click(function() {
	
	// 主键
	var id = $("#modal_edit_id").val();
	// 备注名
	var remarkName = $("#modal_edit_remarkName").val();
	
	// 隐藏模态框
	$("#editModal").modal('hide');
	$.ajax({
		url : PATH + '/trwuliu/Member/myDriver/updateMyDriver',// 跳转到 action
		data : {
			 id: id,
		     remarkName: remarkName
		},
		type : "post",
		success : function(result) {
			if (result.data > 0) {
				$("#modal_common_content").html("修改完成！");
			} else {
				$("#modal_common_content").html("修改失败！");
			}
			$("#commonModal").modal();
			
			// TODO：重新查询，方法待定
			$.ajax({
				url : PATH + '/trwuliu/Member/myDriver/getMyDriver',// 跳转到 action
				data : {
					memberId: memberId
				},
				type : "post",
				success : function(result) {
					if(result && result.code=="000000") {
						var data = result.data;
						appendContentToBody(data, 0);
					}
				}
			});
		}
	});
});

/**
 * 表体行中『删除』点击事件
 * 
 * @param id        主键
 * @param vdivNo    行号
 * @return null
 * @author kowuka
 * @time 2016.06.05
 */
function driverDel(id, vdivNo, driverTel) {
	$("#modal_del_id").val(id);
	$("#modal_del_divNo").val(vdivNo);
	$("#modal_del_driverTel").val(driverTel);
	
	$("#delModal").modal('show');
	
}

// 表体行『删除』功能的『确定』按钮事件
$("#modal_del_yes").click(function() {
	
	var id = $("#modal_del_id").val();
	var vdivNo = $("#modal_del_divNo").val();
	var driverTel = $("#modal_del_driverTel").val();
	
	// 模态框隐藏
	$("#delModal").modal('hide');
	$.ajax({
		url : PATH + '/trwuliu/Member/myDriver/deleteMyDriver',// 跳转到 action
		data : {
			id: id
		},
		type : "post",
		success : function(result) {
			if (result.code == "000000") {
				$("#modal_common_content").html("删除完成！");
				getMyDriver();
			} else {
				$("#modal_common_content").html(result.error);
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
		statusName = "待回复";
	} else if (statusCode == "1") {
		statusName = "已同意";
	} else if (statusCode == "2") {
		statusName = "已拒绝";
	} else if (statusCode == "3") {
		statusName = "已失效";
	}
	
	return statusName;
}



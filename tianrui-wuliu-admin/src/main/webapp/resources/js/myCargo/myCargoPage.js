/**
 * ==================================================================
 * 我的货物功能与后台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.05.22
 */
/** 裁剪次数 */
var count = 0;
/** 表体行号 */
var rowIndex = 0;
/** 启用 */
var enDisable = "启用";
// 条件中『搜索』按钮功能点击事件
$("#myCargo_search").click(function() {
	displayData(0);
});
function displayData(pageNo){
	// 物料编码
	var myCargo_materCode = $("#myCargo_materCode").val();
	// 物料名称
	var myCargo_materName = $("#myCargo_materName").val();
	// 主计量单位
	var myCargo_mainMeasUnit = $("#myCargo_mainMeasUnit option:selected").text();
	if (myCargo_mainMeasUnit == "请选择") {
		myCargo_mainMeasUnit = null;
	}
	// 状态
	var myCargo_state = $("#myCargo_state option:selected").val();
	if (myCargo_state == "-1") {
		myCargo_state = null;
	}
	var pageSize=$("#pageSize").val();
	$.ajax({
		url : CONTEXTPATH + '/myCargo/getMyCargoInfo',// 跳转到 action
		data : {
			 pageNo :(pageNo + 1),
			 pageSize:pageSize,
		     orgId: orgId,
		     materCode: myCargo_materCode,
		     materName: myCargo_materName,
		     measUnit: myCargo_mainMeasUnit,
		     state: myCargo_state
		},
		type : "post",
		success : function(result) {
			if(result && result.code=="000000") {
				var data = result.data.list;
				$("#totalRecords").html(result.data.total);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(result.data.total == 0) {
			    	$("#totalPages").html(1);
			    	$("#myCargo_tbody").empty();
			    	var html;
			    	if(myCargo_materCode || myCargo_materName || myCargo_state){
			    		html +='<td colspan="12">';
			    		html +='<div class="ht_none">';
			    		html +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
			    		html +='<div>';
			    		html +='<h3>唉呀！查询不到您要找的内容</h3>';
			    		html +='<p>换个查询条件试试吧？</p>';
			    		html +='</div>';
			    		html +='</div>';
			    		html +='</td>';
			    	}else{
			    		html +=' <td colspan="12">';
			    		html +='<div class="ht_none">';
			    		html +='<img src="'+imagesRoot+'/none1.png" class="ht_nimg2">';
			    		html +='<div>';
			    		html +='<h3>组织下暂无可用物料！？</h3>';
			    		html +='<p>赶快动动手指加上吧！</p>';
			    		html +='</div>';
			    		html +='</div>';
			    		html +='</td>';
			    	}
			    	$("#myCargo_tbody").html(html);
			    }else {
			    	$("#totalPages").html(parseInt((result.data.total-1)/pageSize+1));  
			    	appendContentToBody(data, 0);
			    }   
				$("#pagination").pagination(result.data.total, {   
				    callback: pageCallback,   
				    prev_text: '上一页',   
				    next_text: '下一页',   
				    items_per_page:pageSize,   
				    num_display_entries:4,   
				    current_page:pageNo,   
				    num_edge_entries:1, 
				    maxentries: result.data.total, 
				    link_to:"javascript:void(0)"
				}); 
			}else{
				alert(result.error);
			}
		}
	});
}
/**
 * 搜索功能使用的循环函数体，用于附加动态表体内容至<body/>标签
 * 
 * @param data 动态获取的数据
 * @param flag 0:搜索 1：新增
 * @return null
 * @author kowuka
 * @time 2016.04.24
 */
function appendContentToBody(data, flag) {
	
	// 搜索查询时清空表体，防止表体重复附加数据
	if (flag == 0) {
		$("#myCargo_tbody").empty();
		rowIndex = 0;
	}
	
	// 数据为空时
	if (data == null || data.length <= 0) {
		var tr1 = $("<tr></tr>");
		// 修改列数
		var td1 = $("<td colspan='7'></td>");
		var div1 = $("<div class='ht404'></div>");
		//var a1= $("<a></a>").append($("<img></img>").attr("src", imagesRoot + "/404_icon.png"));
		var div1_1 = $("<div></div>").append($("<h1></h1>").html("哎呀!"))
		                              .append($("<p></p>").html("您正在寻找的内容在本地球找不到，再换个条件试试？"));
		
		div1.append(div1_1);
		td1.append(div1);
		tr1.append(td1);
		$("#myCargo_tbody").append(tr1);
		
	// 有数据信息时
	} else {
		for (var i = 0; i < data.length; i++) {
			// 表体行号++
			rowIndex++;
			// 序号
			var input1 = $("<input></input>").attr("type","checkbox").attr("name","user").attr("value", data[i].id);
			var td1 = $("<td></td>").addClass("check-col").attr("field", "ck");
			// 物料编码、物料名称、物料状态、主计量单位、状态
			var td5 = $("<td></td>").append(data[i].materCode);
			var td6 = $("<td></td>").append(data[i].materName);
			var td7 = $("<td></td>").append(getStatusByCode(data[i].materState));
			var td8 = $("<td></td>").append(data[i].measUnit);
			var td9 = $("<td id='rowIndex" + rowIndex + "_state'" + "></td>").append(getStatusByCode(data[i].state));
			var td10 = $("<td></td>");
			
			var href1 = $("<a></a>")
							.attr("href", "javascript:void(0);")
							 .attr("onclick", "detailDisplay('" + data[i].id + "','" 
										                         + data[i].orgName + "','" 
										                          + data[i].orgType + "','" 
										                           + data[i].state + "','" 
										                            + data[i].materCode + "','" 
										                             + data[i].materName + "','" 
										                              + data[i].materClass + "','" 
										                               + data[i].spec + "','" 
										                                + data[i].model + "','" 
										                                 + data[i].materMNCode + "','" 
										                                  + data[i].measUnit + "')")
										.html("【查看详情】");
			// 显示启用/停用
			if (data[i].state == "1") {
				enDisable = "停用";
			} else {
				enDisable = "启用";
			}
			var href2 = $("<a id='rowIndex" + rowIndex + "_enDisable'" + "></a>")
							.attr("href", "javascript:cargoEnDisable('" + data[i].id + "','"
																	     + data[i].state + "','rowIndex"
																	      + rowIndex + "')")
										.html("【" + enDisable+"】");
			var href3 = $("<a></a>")
			               .attr("href", "javascript:void(0);")
			                .attr("onclick", "cargoEdit('" + data[i].id + "','" 
					                                        + data[i].materCode + "','" 
					                                         + data[i].materName + "','" 
					                                          + data[i].materClass + "','" 
						                                       + data[i].spec + "','" 
						                                        + data[i].model + "','" 
						                                         + data[i].materMNCode + "','" 
						                                          + data[i].measUnit + "','rowIndex"
						                                           + rowIndex + "')")
						                .html("【修改 】");

			var href4 = $("<a></a>")
			                .attr("href", "javascript:cargoDelete('" + data[i].id + "','rowIndex"
																	  + rowIndex + "')")
						                .html("【删除】");

			var span1 = $("<span></span>").append(href1);//查看详情
			var span2 = $("<span></span>").append(href2);//启用、停用
			var span3 = $("<span></span>").append(href3);//修改
			var span4 = $("<span></span>").append(href4);//删除
			
			/*TODO:测试用------BEGIN-------*/
			if (flag != 0) {
				td1.css("color", "red");
			}
			/*TODO:测试用------END-------*/
			if(data[i].count > 0){
				td10.append(span2)
			}else {
				td10.append(span2).append(span4);
			}
			if(data[i].state == "1"){
				td1.append(input1);
			}
			var tr = $("<tr></tr>").attr("id","rowIndex" + rowIndex).append(td1)
			                        /*.append(td2).append(td3)*/.append(td5)
						             .append(td6).append(td8).append(td9)
						              .append(td10);
			
			if (flag == 0) {
				// 搜索查询时表体正常附加数据
				$("#myCargo_tbody").append(tr);
			} else if (flag == 1) {
				// 新增时新增数据附加至表体第一行
				$("#myCargo_tbody").prepend(tr);
			}
		}
	}
}

// 『重置』按钮点击事件
$("#myCargo_reset").click(function() {
	// 物料编码
	$("#myCargo_materCode").val("");
	// 物料名称
	$("#myCargo_materName").val("");
	// 物料状态
	$("#myCargo_materState").val("-1");
	// 状态
	$("#myCargo_state").val("-1");
});

var materNameData = new Array();
// 我的货物表头『新增』功能打开模态框后事件
$('#addModal').on('shown.bs.modal', function (e) {
	$("#modal_add_materName").val("");
	$.ajax({
		url : CONTEXTPATH + '/fileCargo/getCargoInfo',// 跳转到 action
		data : {
			state: "1",
			pageSize:198
		},
		type : "post",
		success : function(result) {
			var data = result.data.list;
			for (var i=0; i<data.length; i++) {
				var orgObj =  new Object();
				orgObj.value = data[i].id;
				orgObj.label = data[i].materName;
				materNameData[i] = orgObj;
			}
			$("#modal_add_materName").autocomplete({
				minLength: 0,
		        source: materNameData,
		        select: function( event, ui ) {
		            $( "#modal_add_materName" ).val( ui.item.label );
		            $( "#modal_add_materId" ).val( ui.item.value );
		            return false;
		        }
		    });
		}
	});
});  

// 我的货物表头『新增』功能模态框的『保存』按钮事件
$("#modal_add_save").click(function() {
	// 物料名称
	var myCargo_materName = $("#modal_add_materName").val();
	var modal_add_materId = $("#modal_add_materId").val();
	
	if (myCargo_materName == "") {
		$("#modal_common_content").html("物料名称不能为空,请输入！");
		$("#commonModal").modal('show');
		return;
		
	} 
	var cargoflag = false;
	//判断物料信息是否正确
	for(var i=0;i<materNameData.length;i++){
		if(myCargo_materName == materNameData[i].label && modal_add_materId ==materNameData[i].value){
			cargoflag = true;
		}
	}
	if(!cargoflag){
		$("#modal_common_content").html("你输入的物料名称不存在，请选择...");
		$("#commonModal").modal();
		return;
	}else {
		$.ajax({
			url : CONTEXTPATH + '/myCargo/getMyCargoInfo',// 跳转到 action
			data : {
			     orgId: orgId,
			     materId: modal_add_materId
			},
			type : "post",
			success : function(result) {
				if(result && result.code == "000000") {
					var data = result.data.list;
					if (data != null && data.length > 0) {
						/**=======  IE8的崩溃是由于此处引起Maximum call stack size exceeded.  =======*/
						$("#modal_common_content").html("该物料不能重复新增！");
						$("#commonModal").modal('show');
					} else {
						$.ajax({
							url : CONTEXTPATH + '/myCargo/saveMyCargoInfo',// 跳转到 action
							data : {
							     orgId: orgId,
							     materid: modal_add_materId
							},
							type : "post",
							success : function(result) {
								if (result.code == "000000") {
									$("#myCargo_reset").trigger("click");
									$("#myCargo_search").trigger("click");
									// 隐藏模态框
									$("#addModal").modal('hide');
								} else {
									$("#modal_common_content").html("物料新增失败！");
									$("#commonModal").modal('show');
								}
							}
						});
					}

				} else {
					$("#modal_common_content").html("物料名称不存在！");
					$("#commonModal").modal('show');
				}
			}
		});
	}
});

/**
 * 表体行【查看详情】按钮点击事件
 * 
 * @param ... 传递参数
 * @return false
 * @author kowuka
 * @time 2016.05.23
 */
function detailDisplay(id, orgName, orgType, state,
		                materCode, materName, materClass,
		                 spec, model, materMNCode, mainMeasUnit) {
	// var data = eval('(' + paramObj + ')'); 
	/*// 组织名称
	$("#modal_detail_orgName").html(orgName == undefined ? orgName : "");
	// 组织类型
	$("#modal_detail_orgType").html(orgType == undefined ? orgType : "");
	*/
	// 物料编码
	$("#modal_detail_materCode").html(materCode);
	// 物料名称
	$("#modal_detail_materName").html(materName);
	// 物料类别
	$("#modal_detail_materClass").html(materClass);
	// 状态
	$("#modal_detail_state").html(state);
	// 货物规格
	$("#modal_detail_spec").html(spec);
	// 货物型号
	$("#modal_detail_model").html(model);
	// 物料助记码
	if (materMNCode == undefined) {
		$("#modal_detail_materMNCode").val("");
	} else {
		$("#modal_detail_materMNCode").html(materMNCode);
	}
	// 主计量单位
	$("#modal_detail_mainMeasUnit").html(mainMeasUnit);
	
	$("#detail").modal();
	
	return false;
}

/**
 * 表体行【启用/停用】按钮点击事件
 * 
 * @param ... 传递参数
 * @return false
 * @author kowuka
 * @time 2016.05.23
 */
function cargoEnDisable(id, state, vRowIndex) {
	
	// id、状态、行号
	$("#modal_endisable_id").val(id);
	$("#modal_endisable_rowIndex").val(vRowIndex);
	if (state == "1") {
		$("#modal_endisable_content").html("停用后关联的下游单据将无法使用！你确定要停用选择的物料？");
		$("#modal_endisable_state").val("0");
	} else {
		$("#modal_endisable_content").html("您确定启用吗？");
		$("#modal_endisable_state").val("1");
	}
	
	// 打开模态框
	$("#enDisableModal").modal();
}

// 表体中【启用/停用】模态框【确定】按钮操作
$("#modal_endisable_button").click(function() {
	
	// id、状态、行号
	var id = $("#modal_endisable_id").val();
	var state = $("#modal_endisable_state").val();
	//var vRowIndex = $("#modal_endisable_rowIndex").val();
	
	// 隐藏模态框
	$("#enDisableModal").modal('hide');
	
	$.ajax({
		url : CONTEXTPATH + '/myCargo/updateMyCargoInfo',// 跳转到 action
		data : {
			 id: id,
			 state: state,
			 userName: userName
		},
		type : "post",
		success : function(result) {
			if (result.code == "000000") {
				$("#modal_common_content").html(getStatusByCode(state) + "完成！");
			} else {
				$("#modal_common_content").html(getStatusByCode(state) + "失败！"+result.error);
			}
			$("#commonModal").modal();
			
			/*if (state == "停用") {
				$("#" + vRowIndex + "_enDisable").html("启用");
			} else {
				$("#" + vRowIndex + "_enDisable").html("停用");
			}
			$("#" + vRowIndex + "_state").html(state);*/
			$("#myCargo_search").trigger("click");
		}
	});
});

/**
 * 表体行中『修改』点击事件
 * 
 * @param ... 传递参数
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function cargoEdit(id, materCode, materName, materClass,
				         spec, model, materMNCode, mainMeasUnit) {
	// 主键
	$("#modal_edit_id").val(id);
	// 物料编码
	$("#modal_edit_materCode").val(materCode);
	// 物料名称
	$("#modal_edit_materName").val(materName);
	// 物料类别
	$("#modal_edit_materClass option").each(function(){
		if ($(this).text() == materClass) {
			$(this).attr('selected', true);
		}
	});
	// 货物规格
	$("#modal_edit_spec").val(spec);
	// 货物型号
	$("#modal_edit_model").val(model);
	// 物料助记码
	if (materMNCode == undefined) {
		$("#modal_edit_materMNCode").val("");
	} else {
		$("#modal_edit_materMNCode").val(materMNCode);
	}
	// 主计量单位
	$("#modal_edit_mainMeasUnit option").each(function(){
		if ($(this).text() == mainMeasUnit) {
			$(this).attr('selected', true);
		}
	});
	
	$("#edit_mate").modal();
}

// 我的货物表体行『修改』功能打开模态框后事件
$('#edit_mate').on('shown.bs.modal', function (e) {
	
	// 主键
	var id = $("#modal_edit_id").val();
	
	// 裁剪次数初始化
	count = 0;
	
	// 调用cropbox.js方法
	invokeCropBoxEdit();
	
	// 根据id查询我的货物信息
	$.ajax({
		url : CONTEXTPATH + '/myCargo/getMyCargoInfo',// 跳转到 action
		data : {
		     id: id,
		     userName: userName
		},
		type : "post",
		success : function(result) {
			if( result && result.code=="000000"  ){
				var data = result.data.list;
				
				if ($("#editCargoTarget").attr("src") == undefined) {
					$("#edit_cargoImg").removeAttr("src");
					if (data == undefined || data[0].imgPath == undefined) {
						$("#edit_cargoImg").attr("src", imagesRoot + "/mei.jpg");
						$("#edit_imgCompare_back").attr("src", imagesRoot + "/mei.jpg");
					} else {
						$("#edit_cargoImg").attr("src", data[0].imgPath);
						$("#edit_imgCompare_back").attr("src", data[0].imgPath);
					}
				} else {
					$("#editCargoTarget").removeAttr("src");
					$("#editCargoTarget").attr("src", data[0].imgPath);
					$("#edit_imgCompare_back").attr("src", data[0].imgPath);
				}
				
				// 把货物图片地址赋值予隐藏项
				$("#edit_cargoImgBack_1").empty().val(data[0].imgPath);
				$("#edit_cargoImgBack_2").empty().val(data[0].imgPath);
				$("#edit_cargoImgBack_3").empty().val(data[0].imgPath);
			}
		}
	});
}); 

// 表体行中『修改』功能的『保存』按钮事件
$("#modal_edit_save").click(function() {
	// 主键
	var id = $("#modal_edit_id").val();
	// 物料编码
	var myCargo_materCode = $("#modal_edit_materCode").val();
	// 物料名称
	var myCargo_materName = $("#modal_edit_materName").val();
	// 物料类别
	var myCargo_materClass = $("#modal_edit_materClass option:selected").text();
	// 货物规格
	var myCargo_spec = $("#modal_edit_spec").val();
	// 货物型号
	var myCargo_model = $("#modal_edit_model").val();
	// 物料助记码
	var myCargo_materMNCode = $("#modal_edit_materMNCode").val();
	// 主计量单位
	var myCargo_mainMeasUnit = $("#modal_edit_mainMeasUnit option:selected").text();
	// 图片地址
	var myCargo_imgPath = null;
	if ($("#editCargoTarget").attr("src") == undefined) {
		myCargo_imgPath = $("#cargoTarget").attr("src");
	} else {
		myCargo_imgPath = $("#editCargoTarget").attr("src");
	}
	
	// 判断图片是否更换
	if (myCargo_imgPath == $("#edit_imgCompare_back").attr("src")) {
		myCargo_imgPath = null;
	}
	
	// 隐藏模态框
	$("#edit_mate").modal('hide');
	$.ajax({
		url : CONTEXTPATH + '/myCargo/updateMyCargoInfo',// 跳转到 action
		data : {
			 id: id,
		     materCode: myCargo_materCode,
		     materName: myCargo_materName,
		     materClass: myCargo_materClass,
		     state: null,
		     spec: myCargo_spec,
		     model: myCargo_model,
		     materMNCode: myCargo_materMNCode,
		     mainMeasUnit: myCargo_mainMeasUnit, 
		     imgPath: myCargo_imgPath,
		     userName: userName
		},
		type : "post",
		success : function(result) {
			if (result.data > 0) {
				$("#modal_common_content").html("修改完成！");
			} else {
				$("#modal_common_content").html("修改失败！");
			}
			$("#commonModal").modal();
			$("#myCargo_search").trigger("click");
			/*var tdArray = $("#" + rowIndex).children();
			$(tdArray[1]).empty().append(roleNumber);
			$(tdArray[2]).empty().append(roleName);
			$(tdArray[3]).empty().append(roleDescription);*/
		}
	});
});

/**
 * 表体行中『删除』点击事件
 * 
 * @param id 角色编号
 * @return null
 * @author kowuka
 * @time 2016.05.25
 */
function cargoDelete(id, rowIndex) {
	$("#modal_del_id").val(id);
	$("#modal_del_rowIndex").val(rowIndex);
	
	$("#dele_mate").modal('show');
	
}

// 表体行『删除』功能的『确定』按钮事件
$("#modal_del_button").click(function() {
	
	var id = $("#modal_del_id").val();
	var rowIndex = $("#modal_del_rowIndex").val();
	
	// 模态框隐藏
	$("#dele_mate").modal('hide');
	$.ajax({
		url : CONTEXTPATH + '/myCargo/deleteMyCargoInfo',// 跳转到 action
		data : {
			id: id
		},
		type : "post",
		success : function(result) {
			if (result.data > 0) {
				$("#modal_common_content").html("删除完成！");
				$("#" + rowIndex).remove();
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
 * @time 2016.06.15
 */
function getStatusByCode(statusCode) {
	var statusName = "";
	if (statusCode == "1") {
		statusName = "启用";
	} else {
		statusName = "停用";
	}
	
	return statusName;
}

/**
 *『批量停用』功能点击事件
 * 
 * @param null
 * @return null
 * @author tank
 * @time 2016.07.05
 */
$("#batchDisable").click(function() {
	// 获取表体所有checkbox被选中数据的用户ID
	var checkedCargoId = "";
	$("#myCargo_tbody input[type=checkbox]:checked").each(function() {
		if($(this).val() != ""){
			if (checkedCargoId != "") {
				checkedCargoId += ",'" + $(this).val() +"'";
			} else {
				checkedCargoId += "'" + $(this).val() +"'";
			}
		}
	});
	if(checkedCargoId == ""){
		alert("请选择要停用的物料！");
		return;
	}
	if(window.confirm('停用后关联的下游单据将无法使用！你确定要停用选择的物料？')){
		$.ajax({
			url : CONTEXTPATH + '/myCargo/updateStatus',// 跳转到 action
			data : {
				id: checkedCargoId
			},
			type : "post",
			success : function(result) {
				$("#modal_common_content").html("批量停用完成！");
				$("#commonModal").modal();
				$("#myCargo_search").trigger("click");
			}
		});
    }else{
        return false;
    }
});
/**
 * ==================================================================
 * 货物档案功能与后台交互 v1.0
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
/** 组织信息 */
var orgArray = new Array();

// 初始化处理
$(function() { 
	
	// 初始化设置组织ID、组织名称
	// 组织ID
	if(orgId != "null"){
		$("#cargo_orgId").val(orgId);
	}
	if(orgName != "null"){
		// 组织名称
		$("#cargo_orgName").val(orgName);
	}
	
	// 进入页面默认查询
	$("#cargo_search").trigger("click");
	
	// 获取组织信息，用作模糊查询用
	$.ajax({
		url : CONTEXTPATH + '/Organization/query',// 跳转到 action
		data : {},
		type : "post",
		success : function(result) {
			var data = result.data;
			for (var i=0; i<data.length; i++) {
				var orgObj =  new Object();
				orgObj.value = data[i].id;
				orgObj.label = data[i].organizationname;
				orgArray[i] = orgObj;
			}
			$("#cargo_orgName").autocomplete({
				minLength: 0,
		        source: orgArray,
		        select: function( event, ui ) {
		            $( "#cargo_orgName" ).val( ui.item.label );
		            $( "#cargo_orgId" ).val( ui.item.value );
		            return false;
		        }
		    });
		}
	});
});

// 条件中『搜索』按钮功能点击事件
$("#cargo_search").click(function() {
	displayData(0);
});
function displayData(pageNo){
	// 组织ID
	var cargo_orgId = $("#cargo_orgId").val();
	// 组织名称
	var cargo_orgName = $("#cargo_orgName").val();
	// 物料编码
	var cargo_materCode = $("#cargo_materCode").val();
	// 物料名称
	var cargo_materName = $("#cargo_materName").val();
	// 物料类别
	var cargo_materClass = $("#cargo_materClass option:selected").text();
	if (cargo_materClass == "全部") {
		cargo_materClass = null;
	}
	// 货物规格
	var cargo_spec = $("#cargo_spec").val();
	// 货物型号
	var cargo_model = $("#cargo_model").val();
	// 物料助记码
	var cargo_materMNCode = $("#cargo_materMNCode").val();
	// 主计量单位
	var cargo_mainMeasUnit = $("#cargo_mainMeasUnit option:selected").text();
	if (cargo_mainMeasUnit == "请选择") {
		cargo_mainMeasUnit = null;
	}
	
	// 设置组织ID与组织名称清空关系
	if (cargo_orgName == "") {
		cargo_orgId = null;
	}
	var pageSize=$("#pageSize").val();
	// 按我的货物信息进行查询
	if (cargo_orgId != null && cargo_orgId != "") {
		$.ajax({
			url : CONTEXTPATH + '/myCargo/getMyCargoInfo',// 跳转到 action
			data : {
				 pageNo :(pageNo + 1),
				 pageSize:pageSize,
			     orgId: cargo_orgId,
			     materCode: cargo_materCode,
			     materName: cargo_materName,
			     materState: null,
			     measUnit: cargo_mainMeasUnit,
			     state: null
			},
			type : "post",
			success : function(result) {
				if(result && result.code=="000000") {
					var data = result.data.list;
					$("#totalRecords").html(result.data.total);
			    	document.getElementById("goPage").value = pageNo+1;
				    if(result.data.total == 0) {
				    	$("#totalPages").html(1);  
				    }else {
				    	$("#totalPages").html(parseInt((result.data.total-1)/pageSize+1));  
				    }   
				    appendContentToBody(data, 0);
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
				}
			}
		});
		
	// 按货物档案信息进行查询
	} else {
		$.ajax({
			url : CONTEXTPATH + '/fileCargo/getCargoInfo',// 跳转到 action
			data : {
				 pageNo :(pageNo + 1),
				 pageSize:pageSize,
			     materCode: cargo_materCode,
			     materName: cargo_materName,
			     materClass: cargo_materClass,
			     spec: cargo_spec,
			     model: cargo_model,
			     materMNCode: cargo_materMNCode,
			     mainMeasUnit: cargo_mainMeasUnit
			},
			type : "post",
			success : function(result) {
				if(result && result.code=="000000") {
					var data = result.data.list;
					$("#totalRecords").html(result.data.total);
			    	document.getElementById("goPage").value = pageNo+1;
				    if(result.data.total == 0) {
				    	$("#totalPages").html(1);  
				    	$("#cargo_tbody").empty();
				    	var html;
				    	if(cargo_materCode || cargo_materName || cargo_materMNCode ||cargo_materClass){
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
				    		html +='<h3>系统暂无可用货物档案！？</h3>';
				    		html +='<p>赶快动动手指加上吧！</p>';
				    		html +='</div>';
				    		html +='</div>';
				    		html +='</td>';
				    	}
				    	$("#cargo_tbody").html(html);
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
				}
			}
		});
	}
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
		$("#cargo_tbody").empty();
		rowIndex = 0;
	}

	// 数据为空时
	if (data == null || data.length <= 0) {
		var tr1 = $("<tr></tr>");
		var td1 = $("<td colspan='9'></td>");
		var div1 = $("<div></div>");
		var div1_1 = $("<div></div>").append($("<h1></h1>").html("哎呀"))
		                              .append($("<p></p>").html("您正在寻找的内容在本地球找不到，再换个条件试试？"));
		
		div1.append(div1_1);
		td1.append(div1);
		tr1.append(td1);
		$("#cargo_tbody").append(tr1);
		
	// 有数据的情况
	} else {
		for (var i = 0; i < data.length; i++) {
			// 表体行号++
			rowIndex ++;
			// 序号、组织名称、组织类型、状态
			var input1 = $("<input></input>").attr("type","checkbox").attr("name","user").attr("value", data[i].id);
			var td1 = $("<td></td>").addClass("check-col").attr("field", "ck");
			var td4 = $("<td id='rowIndex" + rowIndex + "_state'" + "></td>").append(getStatusByCode(data[i].state));
			// 物料编码、物料名称、物料类别、状态、规格、型号、物料助记码
			var td5 = $("<td></td>").append(data[i].materCode);
			var td6 = $("<td></td>").append(data[i].materName);
			var td2 = $("<td></td>").append(data[i].measUnit);
			var td7 = $("<td></td>").append(data[i].materClass);
			var payType = '';
			switch (data[i].payType) {
			case '0':
				payType = "在线支付";
				break;
			case '1':
				payType = "发票单支付";
				break;
			default:
				break;
			}
			var td12 = $("<td></td>").append(payType);
			var td8 = $("<td></td>").append(data[i].spec);
			var td9 = $("<td></td>").append(data[i].model);
			var td10 = $("<td></td>").append(data[i].materMNCode);
			var td11 = $("<td></td>");
			
			// 构建obj字符串作为参数保护使用
			/*var paramObj = {"id": data[i].id, "orgName": data[i].orgName, "orgType": data[i].orgType, 
					         "orgState": data[i].orgState, "materName": data[i].materName, "materClass": data[i].materClass, 
					          "spec": data[i].spec, "model": data[i].model, "materMNCode": data[i].materMNCode};*/
			/*var paramObj = [data[i].id, data[i].materName, data[i].materClass, 
					          data[i].spec, data[i].model, data[i].materMNCode];*/
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
										                                  + payType + "','" 
										                                   + data[i].measUnit + "')")
										.html(" 【查看详情】 ");
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
						                .html("【修改】 ");

			var href4 = $("<a></a>")
			                .attr("href", "javascript:cargoDelete('" + data[i].id + "','rowIndex"
																	  + rowIndex + "')")
						                .html("【删除 】");

			var span1 = $("<span></span>").append(href1);
			var span2 = $("<span></span>").append(href2);
			var span3 = $("<span></span>").append(href3);
			var span4 = $("<span></span>").append(href4);
			
			if (flag != 0) {
				td1.css("color", "red");
			}
			if(data[i].count > 0){
				td11.append(span1).append(span2);
			}else {
				td11.append(span1).append(span2).append(span3).append(span4);
			}
			if(data[i].state == "1"){
				td1.append(input1);
			}
			var tr = $("<tr></tr>").attr("id","rowIndex" + rowIndex).append(td1).append(td5)
						             .append(td6).append(td2).append(td7).append(td4).append(td12).append(td8).append(td9)
						              .append(td10).append(td11);
			
			if (flag == 0) {
				// 搜索查询时表体正常附加数据
				$("#cargo_tbody").append(tr);
			} else if (flag == 1) {
				// 新增时新增数据附加至表体第一行
				$("#cargo_tbody").prepend(tr);
			}
		}
	}
}

// 『重置』按钮点击事件
$("#cargo_reset").click(function() {
	// 组织ID
	$("#cargo_orgId").val("");
	// 组织名称
	$("#cargo_orgName").val("");
	// 物料编码
	$("#cargo_materCode").val("");
	// 物料名称
	$("#cargo_materName").val("");
	// 物料类别
	$("#cargo_materClass").val("");
	// 货物规格
	$("#cargo_spec").val("");
	// 货物型号
	$("#cargo_model").val("");
	// 物料助记码
	$("#cargo_materMNCode").val("");
	// 主计量单位
	$("#cargo_mainMeasUnit").val("");
	
});

// 货物档案表头『新增』功能打开模态框后事件
$('#addModal').on('shown.bs.modal', function (e) {
	
	// 裁剪次数初始化
	count = 0;
	
	// 调用cropbox.js方法
	invokeCropBoxAdd();
	
	// 把默认图片地址赋值予隐藏项
	if ($("#addCargoTarget").attr("src") == undefined) {
		$("#add_cargoImg").removeAttr("src");
		$("#add_cargoImg").attr("src", imagesRoot + "/mei.jpg");
	} else {
		$("#addCargoTarget").removeAttr("src");
		$("#addCargoTarget").attr("src", imagesRoot + "/mei.jpg");
		
	}
	$("#add_cargoImgBack_1").empty().val(imagesRoot + "/mei.jpg");
	$("#add_cargoImgBack_2").empty().val(imagesRoot + "/mei.jpg");
	$("#add_cargoImgBack_3").empty().val(imagesRoot + "/mei.jpg");
	$("#add_imgCompare_back").empty().val(imagesRoot + "/mei.jpg");
	
});  

// 货物档案表头『新增』功能模态框的『保存』按钮事件
$("#modal_add_save").click(function() {
	// 物料编码
	var cargo_materCode = $.trim($("#modal_add_materCode").val());
	// 物料名称
	var cargo_materName = $.trim($("#modal_add_materName").val());
	// 物料类别
	var cargo_materClass = $("#modal_add_materClass option:selected").text();
	// 货物规格
	var cargo_spec = $.trim($("#modal_add_spec").val());
	// 货物型号
	var cargo_model = $.trim($("#modal_add_model").val());
	// 物料助记码
	var cargo_materMNCode = $.trim($("#modal_add_materMNCode").val());
	// 主计量单位
	var cargo_mainMeasUnit = $("#modal_add_mainMeasUnit option:selected").text();
	// 主计量单位
	var cargo_payType = $("#modal_add_payType").val();
	// 图片地址
	var cargo_imgPath = null;
	if (cargo_imgPath != $("#add_imgCompare_back").val()) {
		cargo_imgPath = $("#addCargoTarget").attr("src");
	}
	if( cargo_materCode ==""){
		$("#modal_common_content").html("物料编码不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}else if( cargo_materName ==""){
		$("#modal_common_content").html("物料名称不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}else if( cargo_materClass =="请选择"){
		$("#modal_common_content").html("请选择物料类别！");
		$("#commonModal").modal();
		return;
	}else if( cargo_mainMeasUnit =="请选择"){
		$("#modal_common_content").html("请选择计量单位");
		$("#commonModal").modal();
		return;
	}
	$.ajax({
		url : CONTEXTPATH + '/fileCargo/saveCargoInfo',// 跳转到 action
		data : {
		     materCode: cargo_materCode,
		     materName: cargo_materName,
		     materClass: cargo_materClass,
		     spec: cargo_spec,
		     model: cargo_model,
		     materMNCode: cargo_materMNCode,
		     mainMeasUnit: cargo_mainMeasUnit, 
		     payType: cargo_payType, 
		     imgPath: cargo_imgPath,
		     userName: userName
		},
		type : "post",
		success : function(result) {
			if (result && result.code=="000000" ) {
				$("#cargo_reset").trigger("click");
				$("#cargo_search").trigger("click");
				// 隐藏模态框
				$("#addModal").modal('hide');
			} else {
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
		}
	});
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
		                 spec, model, materMNCode, payType, mainMeasUnit) {
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
	$("#modal_detail_state").html(getStatusByCode(state));
	// 货物规格
	$("#modal_detail_spec").html(spec);
	// 货物型号
	$("#modal_detail_model").html(model);
	// 物料助记码
	if (materMNCode == undefined) {
		$("#modal_detail_materMNCode").html("");
	} else {
		$("#modal_detail_materMNCode").html(materMNCode);
	}
	// 主计量单位
	$("#modal_detail_mainMeasUnit").html(mainMeasUnit);
	// 支付类型
	$("#modal_detail_payType").html(payType);
	// 根据id查询货物档案信息
	$.ajax({
		url : CONTEXTPATH + '/fileCargo/getCargoInfo',// 跳转到 action
		data : {
		     id: id
		},
		type : "post",
		success : function(result) {
			if( result && result.code=="000000"  ){
				var data = result.data.list;
				$("#modal_detail_img").attr("src", data[0].imgPath);
			}
		}
	});
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
		url : CONTEXTPATH + '/fileCargo/updateCargoInfo',// 跳转到 action
		data : {
			 id: id,
			 state: state,
			 userName: userName
		},
		type : "post",
		success : function(result) {
			if (result && result.code == "000000") {
				$("#cargo_search").trigger("click");
			} else {
				$("#modal_common_content").html(getStatusByCode(state) + "失败！"+result.error);
				$("#commonModal").modal();
			}
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

// 货物档案表体行『修改』功能打开模态框后事件
$('#edit_mate').on('shown.bs.modal', function (e) {
	
	// 主键
	var id = $("#modal_edit_id").val();
	
	// 裁剪次数初始化
	count = 0;
	
	// 调用cropbox.js方法
	invokeCropBoxEdit();
	
	// 根据id查询货物档案信息
	$.ajax({
		url : CONTEXTPATH + '/fileCargo/getCargoInfo',// 跳转到 action
		data : {
		     id: id
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
	var cargo_materCode = $("#modal_edit_materCode").val();
	// 物料名称
	var cargo_materName = $("#modal_edit_materName").val();
	// 物料类别
	var cargo_materClass = $("#modal_edit_materClass option:selected").text();
	// 货物规格
	var cargo_spec = $("#modal_edit_spec").val();
	// 货物型号
	var cargo_model = $("#modal_edit_model").val();
	// 物料助记码
	var cargo_materMNCode = $("#modal_edit_materMNCode").val();
	// 主计量单位
	var cargo_mainMeasUnit = $("#modal_edit_mainMeasUnit option:selected").text();
	// 主计量单位
	var cargo_payType = $("#modal_edit_payType").val();
	// 图片地址
	var cargo_imgPath = null;
	if ($("#editCargoTarget").attr("src") == undefined) {
		cargo_imgPath = $("#cargoTarget").attr("src");
	} else {
		cargo_imgPath = $("#editCargoTarget").attr("src");
	}
	
	// 判断图片是否更换
	if (cargo_imgPath == $("#edit_imgCompare_back").attr("src")) {
		cargo_imgPath = null;
	}
	if( cargo_materCode ==""){
		$("#modal_common_content").html("物料编码不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}else if( cargo_materName ==""){
		$("#modal_common_content").html("物料名称不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}else if( cargo_materClass =="请选择"){
		$("#modal_common_content").html("请选择物料类别！");
		$("#commonModal").modal();
		return;
	}else if( cargo_mainMeasUnit =="请选择"){
		$("#modal_common_content").html("请选择计量单位");
		$("#commonModal").modal();
		return;
	}
	$.ajax({
		url : CONTEXTPATH + '/fileCargo/updateCargoInfo',// 跳转到 action
		data : {
			 id: id,
		     materCode: cargo_materCode,
		     materName: cargo_materName,
		     materClass: cargo_materClass,
		     state: "",
		     spec: cargo_spec,
		     model: cargo_model,
		     materMNCode: cargo_materMNCode,
		     mainMeasUnit: cargo_mainMeasUnit, 
		     payType: cargo_payType, 
		     imgPath: cargo_imgPath,
		     userName: userName
		},
		type : "post",
		success : function(result) {
			if (result && result.code=="000000" ) {
				$("#cargo_search").trigger("click");
				// 隐藏模态框
				$("#edit_mate").modal('hide');
			} else {
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
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
		url : CONTEXTPATH + '/fileCargo/deleteCargoInfo',// 跳转到 action
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
 * 新增时调用cropBox.js
 * 
 * @author kowuka
 * @time 2016.05.25
 */
function invokeCropBoxAdd() {
	
    // 表格列宽度手动调整
    $("table").resizableColumns({});

    // 点击修改头像按钮，图片裁剪框显示出来
    $("#modal_add_imgButton").on('click', function () {
        $("#modal_add_imgBox").show();
    });
    // 修改头像的收起按钮
    $("#modal_add_packUp").on('click', function () {
        $("#modal_add_imgBox").hide();
    });
    // 图片裁切块的大小自定义，margin-top是height一半，margin-left是width一半
    var thumb = $("#modal_add_imgBoxFile #modal_add_thumbBox");
    thumb.height(150);
    thumb.width(150);
    thumb.css({ "margin-top": -75, "margin-left": -75 });
    // 给cropbox.js传参
    var options =
    {
        thumbBox: /*'.thumbBox'*/'#modal_add_thumbBox',
        spinner: /*'.spinner'*/'#modal_add_spinner'
        /*imgSrc: imagesRoot + '/img3.jpg'*/
    };
    var cropper = $('#modal_add_imgBoxFile').cropbox(options);
    // 文件上传按钮操作
    $('#mondal_add_uploadFile').on('change', function () {
        var reader = new FileReader();
        reader.onload = function (e) {
            options.imgSrc = e.target.result;
            cropper = $('#modal_add_imgBoxFile').cropbox(options);
        };
        reader.readAsDataURL(this.files[0]);
        this.files = [];
    });
    // 裁切按钮操作
    $('#modal_add_btnCrop').on('click', function () {
        var img = cropper.getDataURL();
        
    	$('#modal_add_img').html('');
        $('#modal_add_img').append('<img id="addCargoTarget" src="' + img + '" align="absmiddle" style="box-shadow:0px 0px 12px #7E7E7E;">');
        // 裁剪次数自增
        count++;
        $("#add_cargoImgBack_2").val($("#add_cargoImgBack_3").val());
        $("#add_cargoImgBack_3").val($("#addCargoTarget").attr("src"));
    });
    
    // 回退按钮操作
    $("#modal_add_btncancel").on('click', function () {
    	// 裁剪次数自减
    	count--;
    	if (count <= 0) {
    		count = 0;
			$('#modal_add_img').empty();
        	$('#modal_add_img').append('<img id="add_cargoImg" src=' + $('#add_cargoImgBack_1').val() + 'class="img-rounded">');
    	
    	} else {
    		
			$("#addCargoTarget").removeAttr("src");
    		$("#addCargoTarget").attr("src", $("#add_cargoImgBack_2").val());
    		
    	}
    });
    
    // 图片放大按钮操作
    $('#modal_add_btnZoomIn').on('click', function () {
        cropper.zoomIn();
    });
    // 图片缩小按钮操作
    $('#modal_add_btnZoomOut').on('click', function () {
        cropper.zoomOut();
    });
}

/**
 * 修改时调用cropBox.js
 * 
 * @param flag "0"：新增，"1"：修改
 * @return null
 * @author kowuka
 * @time 2016.05.25
 */
function invokeCropBoxEdit() {
	
    // 表格列宽度手动调整
    $("table").resizableColumns({});

    // 点击修改头像按钮，图片裁剪框显示出来
    $("#modal_edit_imgButton").on('click', function () {
    	$("#modal_edit_imgBox").show();
    });
    // 修改头像的收起按钮
    $("#modal_edit_packUp").on('click', function () {
    	$("#modal_edit_imgBox").hide();
    });
    // 图片裁切块的大小自定义，margin-top是height一半，margin-left是width一半
    var thumb = $("#modal_edit_imgBoxFile #modal_edit_thumbBox");
    thumb.height(150);
    thumb.width(150);
    thumb.css({ "margin-top": -75, "margin-left": -75 });
    // 给cropbox.js传参
    var options =
    {
        thumbBox: '#modal_edit_thumbBox',
        spinner: '#modal_edit_spinner'
        /*imgSrc: imagesRoot + '/img3.jpg'*/
    };
    var cropper = $('#modal_edit_imgBoxFile').cropbox(options);
    // 文件上传按钮操作
    $('#modal_edit_uploadFile').on('change', function () {
        var reader = new FileReader();
        reader.onload = function (e) {
            options.imgSrc = e.target.result;
        	cropper = $('#modal_edit_imgBoxFile').cropbox(options);
            //cropper = $('.imgBox_file').cropbox(options);
        };
        reader.readAsDataURL(this.files[0]);
        this.files = [];
    });
    // 裁切按钮操作
    $('#modal_edit_btnCrop').on('click', function () {
        var img = cropper.getDataURL();
        
    	$('#modal_edit_img').html('');
        $('#modal_edit_img').append('<img id="editCargoTarget" src="' + img + '" align="absmiddle" style="box-shadow:0px 0px 12px #7E7E7E;">');
        // 裁剪次数自增
        count++;
        $("#edit_cargoImgBack_2").val($("#edit_cargoImgBack_3").val());
        $("#edit_cargoImgBack_3").val($("#editCargoTarget").attr("src"));
        
    });
    
    // 回退按钮操作
    $("#modal_edit_btncancel").on('click', function () {
    	// 裁剪次数自减
    	count--;
    	if (count <= 0) {
    		count = 0;
    		
			$('#modal_edit_img').empty();
        	$('#modal_edit_img').append('<img id="edit_cargoImg" src=' + $('#edit_cargoImgBack_1').val() + 'class="img-rounded">');
    	
    	} else {
    		
			$("#editCargoTarget").removeAttr("src");
    		$("#editCargoTarget").attr("src", $("#edit_cargoImgBack_2").val());
    	}
    });
    
    // 图片放大按钮操作
    $('#modal_edit_btnZoomIn').on('click', function () {
        cropper.zoomIn();
    });
    // 图片缩小按钮操作
    $('#modal_edit_btnZoomOut').on('click', function () {
        cropper.zoomOut();
    });
}

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
	$("#cargo_tbody input[type=checkbox]:checked").each(function() {
		if($(this).val()!=""){
			if (checkedCargoId != "") {
				checkedCargoId += ",'" + $(this).val() +"'";
			} else {
				checkedCargoId += "'" + $(this).val() +"'";
			}
		}
	});
	if(checkedCargoId == ""){
		alert("请选择要停用的物料档案！");
		return;
	}
	if(window.confirm('停用后关联的下游单据将无法使用！你确定要停用选择的物料？')){
		$.ajax({
			url : CONTEXTPATH + '/fileCargo/updateStatus',// 跳转到 action
			data : {
				id: checkedCargoId
			},
			type : "post",
			success : function(result) {
				$("#modal_common_content").html("批量停用完成！");
				$("#" + rowIndex).remove();
				$("#commonModal").modal();
				$("#cargo_search").trigger("click");
			}
		});
    }else{
        return false;
    }
});

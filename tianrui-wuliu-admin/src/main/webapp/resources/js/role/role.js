/**
 * ==================================================================
 * 角色管理功能与后台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.04.12
 */

/** 表体行号 */
var rowIndex = 0;

/**
 * 角色管理中『搜索』功能点击事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
$("#role_search").click(function() {
	displayData(0);
});
function displayData(pageNo){
	// 角色编码
	var role_search_no = $("#role_search_no").val();
	// 角色名称
	var role_search_name = $("#role_search_name").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url : CONTEXTPATH + '/role/queryByPage',// 跳转到 action
		data : {
			pageNo :(pageNo + 1),
			pageSize:pageSize,
			roleName:role_search_name,
			roleNumber:role_search_no
		},
		type : "post",
		success : function(result) {
			if( result && result.code=="000000"  ){
				$("#totalRecords").html(result.data.total);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(result.data.total == 0) {
			    	$("#totalPages").html(1); 
			     	$("#role_tbody").empty();
			    	var html;
			    	if(role_search_no || role_search_name ){
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
			    		html +='<h3>系统暂无可用角色！？</h3>';
			    		html +='<p>赶快动动手指加上吧！</p>';
			    		html +='</div>';
			    		html +='</div>';
			    		html +='</td>';
			    	}
			    	$("#role_tbody").html(html);
			    }else {
			    	$("#totalPages").html(parseInt((result.data.total-1)/pageSize+1));  
			    	appendContentToBody(result.data.list || [], 0, null);
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
 * @param data 动态获取的数据
 * @param flag 0:搜索 1：新增
 * @return null
 * @author kowuka
 * @time 2016.04.24
 */
function appendContentToBody(data, flag, vrowIndex) {
	
	// 搜索查询时清空表体，防止表体重复附加数据
	if (flag == 0) {
		$("#role_tbody").empty();
		rowIndex = 0;
	}
	for (var i = 0; i < data.length; i++) {
		// 表体行号++
		rowIndex++;
		var input1 = $("<input></input>")
						.attr("type","checkbox")
							.attr("value", data[i].id)
								.attr("name", "user");
		var td1 = $("<td></td>").addClass("check-col");
		var td2 = $("<td></td>").append(data[i].number);
		var td3 = $("<td></td>").append(data[i].name);
		var td4 = $("<td></td>").append(data[i].description);
		var td5 = $("<td></td>").append(data[i].usercounts);
		var td6 = $("<td></td>");
		
		var href1 = $("<a></a>")
						//.attr("data-toggle", "modal")
							//.attr("data-target", "#addModal")
								.attr("href", "javascript:roleEdit('" + data[i].number 
																	   + "','" + data[i].name 
																		+ "','" + data[i].description 
																		 + "','" + data[i].id 
																		  + "','rowIndex" + rowIndex + "')")
									.html("【修改 】");
		
		var href2 = $("<a></a>")
						//.attr("data-toggle", "modal")
							//.attr("data-target", "#deleModal")
								.attr("href", "javascript:roleDelete('" + data[i].id
																		 + "','删除','rowIndex"
																		  + rowIndex + "')")
									.html("【删除】");
		var href3 = $("<a></a>")
						//.attr("data-toggle", "modal")
							//.attr("data-target", "#setyemian")
							   .attr("href", "javascript:pagePermissionSet('" + data[i].id + "','" + data[i].name + "')")
								.html("【设置页面权限 】");
		/*
		 
		var href4 = $("<a></a>")
						.attr("data-toggle", "modal")
							.attr("data-target", "#setquanxian")
								.html(" 设置数据权限 ");
		 */
		var href5 = $("<a></a>")
						//.attr("data-toggle", "modal")
							//.attr("data-target", "#detail")
								.attr("href", "javascript:detailDisplay('" + data[i].id + "','" + data[i].name + "')")
									.html("【角色权限】 ");
		var href6 = $("<a></a>")
						//.attr("data-toggle", "modal")
							//.attr("data-target", "#copyModal")
								.attr("href", "javascript:roleCopy('" + data[i].number 
																	   + "','" + data[i].name 
																		+ "','" + data[i].desc 
																		 + "','" + data[i].id
																		  + "','rowIndex" + rowIndex + "')")
								    .html("【复制】");
		var span1 = $("<span></span>").append(href1);
		var span2 = $("<span></span>").append(href2);
		var span3 = $("<span></span>").append(href3);
		//var span4 = $("<span></span>").append(href4);
		var span5 = $("<span></span>").append(href5);
		var span6 = $("<span></span>").append(href6);
		
		td1.append(input1);
		/*TODO:测试用------BEGIN-------*/
		if (flag != 0) {
			td2.css("color", "red");
		}
		/*TODO:测试用------END-------*/
		td6.append(span5).append(span1).append(span3).append(span6).append(span2);
		
		var tr = $("<tr></tr>").attr("id","rowIndex" + rowIndex).append(td1).append(td2).append(td3)
					.append(td4).append(td5).append(td6);
		
		if (flag == 0) {
			// 搜索查询时表体正常附加数据
			$("#role_tbody").append(tr);
		} else if (flag == 1) {
			// 新增时新增数据附加至表体第一行
			$("#role_tbody").prepend(tr);
		} else {
			// copy时新增数据附加至父级节点后方
			$("#" + vrowIndex).after(tr);
		}
	}
}

/**
 * 角色管理中『新增』功能的『保存』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#add_save").click(function() {
	var roleName = $("#roleName").val();
	var roleNumber = $("#roleNumber").val();
	var roleDescription = $("#roleDescription").val();
	if( roleName ==""){
		$("#modal_common_content").html("角色名称不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}else if( roleNumber ==""){
		$("#modal_common_content").html("角色编号不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}
	$.ajax({
		url : CONTEXTPATH + '/role/add',// 跳转到 action
		data : {roleName:roleName,roleNumber:roleNumber,roleDescription:roleDescription},
		type : "post",
		success : function(result) {
			if( result && result.code=="000000" ){
				$("#role_reset").trigger("click");
				$("#role_search").trigger("click");
				$("#addModal").modal('hide');
			}else{
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
		}
	});
});

/**
 * 『新增』功能的『保存』内函数，用于查询并把新增数据附加至<body/>标签内第一行
 * @param user_code 用户编码
 * @param user_name 用户名称
 * @param user_tel  电话
 * @return null
 * @author kowuka
 * @time 2016.04.24
 */
function prependAddedData(roleName, roleNumber, roleDesc, vrowIndex) {
	$.ajax({
		url : CONTEXTPATH + '/role/queryByPage',// 跳转到 action
		data : {roleName:roleName,roleNumber:roleNumber},
		type : "post",
		success : function(result) {
			var data = result.data;
			if (vrowIndex == null) {
				// 附加新增数据至表体第一行
				appendContentToBody(data, 1, vrowIndex);
			} else {
				// 附加新增数据至copy的父节后方
				appendContentToBody(data, 2, vrowIndex);
			}
		}
	});
}

/**
 * 角色管理中『批量删除』功能点击事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#batchDelete").click(function() {
	var checkedRoleId = "";
	$("#role_tbody input[type=checkbox]:checked").each(function() {
		if (checkedRoleId != "") {
			checkedRoleId = checkedRoleId + "," + $(this).val();
		} else {
			checkedRoleId = checkedRoleId + $(this).val();
		}
	});
	roleDelete(checkedRoleId, "批量删除");
});

/**
 * 角色管理表体行中『修改』点击事件
 * 
 * @param roleNumber 角色编码
 * @param roleName  角色名称
 * @param roleDesc  角色描述
 * @param roleId    角色ID
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function roleEdit(roleNumber, roleName, roleDesc, roleId, rowIndex) {
	$("#edit_roleName").val(roleName);
	$("#edit_roleNumber").val(roleNumber);
	$("#edit_roleDescription").val(roleDesc);
	$("#edit_roleId").val(roleId);
	$("#edit_rowno").val(rowIndex);
	
	$("#editModal").modal();
}

/**
 * 角色管理表体行『修改』功能的『保存』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#edit_save").click(function() {
	var roleName = $("#edit_roleName").val();
	var roleNumber = $("#edit_roleNumber").val();
	var roleDescription = $("#edit_roleDescription").val();
	var roleId = $("#edit_roleId").val();
	var rowIndex = $("#edit_rowno").val();
	if( roleName ==""){
		$("#modal_common_content").html("角色名称不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}else if( roleNumber ==""){
		$("#modal_common_content").html("角色编号不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}
	$.ajax({
		url : CONTEXTPATH + '/role/update',// 跳转到 action
		data : {roleName:roleName, roleNumber:roleNumber, roleDescription:roleDescription, id:roleId},
		type : "post",
		success : function(result) {
			if( result && result.code=="000000" ){
				$("#editModal").modal('hide');
				$("#role_search").trigger("click");
			}else{
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
			
		}
	});
});

/**
 * 角色管理表头『批量删除』、表体行中『删除』点击事件
 * 
 * @param roleId 角色编号
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function roleDelete(roleId, title, rowIndex) {
	$("#delete_title").html(title);
	$("#delete_roleId").val(roleId);
	$("#delete_rowno").val(rowIndex);
	
	if (title == "批量删除") {
		$("#delete_flag").val("0");
	} else {
		$("#delete_flag").val("1");
	}
	
	$("#deleModal").modal('show');
	
}

/**
 * 角色管理表头『批量删除』、表体行『删除』功能的『确定』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#delete_confirm").click(function() {
	var roleId = $("#delete_roleId").val();
	var delFlag = $("#delete_flag").val();
	$("#deleModal").modal('hide');
	$.ajax({
		url : CONTEXTPATH + '/role/delete',// 跳转到 action
		data : {idstr:roleId},
		type : "post",
		success : function(result) {
			if( result && result.code=="000000" ){
				$("#role_search").trigger("click");
			}else{
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
			
		}
	});
});

/**
 * 角色管理中『设置页面权限』点击事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function pagePermissionSet(roleID, roleName) {
	$("#setyemian_roleID").val(roleID);
	$("#pagePermission_name").html("角色名称：" + roleName);
	$('#setyemian').modal();
}

/**
 * 角色管理中『设置页面权限』功能打开模态框后事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
$('#setyemian').on('shown.bs.modal', function (e) {
	var roleID = $("#setyemian_roleID").val();
	var div = $("#pagePermission_div");
	div.empty();
	div.append("data getting...");
	$.ajax({
		url : CONTEXTPATH + '/mainMenu/getMenuByRoleId',// 跳转到 action
		data : {roleId:roleID},
		type : "post",
		success : function(result) {
			var menuList;
			if(result &&result.code=="000000"){
				menuList = result.data;
			}else {
				div.empty();
				div.append("数据错误...");
				return;
			}
			// div
			var ul = $("<ul></ul>");
			// level1
			var i1 = $("<i></i>").addClass("iconf-folder-open");
			var input1 = $("<input></input>")
							.attr("type", "checkbox")
								.attr("name", "level1")
									.attr("value", "")
										.attr("checked", "true");
			var span1 = $("<span></span>").append(i1).append(input1).append("大易物流平台");
			var li1 = $("<li></li>").append(span1);
			var ul1 = $("<ul></ul>");
			for (var i = 0; i < menuList.length; i++) {
				if(menuList[i].afternodename == "首页"){
					continue;
				}
				if (menuList[i].afternodename != "您有消息") {
					// level2
					var i2 = $("<i></i>").addClass("iconf-minus-sign");
					var input2 = $("<input></input>")
									.attr("type", "checkbox")
										.attr("name", "level2")
											.attr("value", menuList[i].id);
					if (menuList[i].desc2 == "yes") {
						input2.attr("checked", "true");
					}
					var span2 = $("<span></span>").append(i2).append(input2).append(menuList[i].afternodename);
					var li2 = $("<li></li>").append(span2);
					var ul2 = $("<ul></ul>");
					var nodesList = menuList[i].nodeList;
					for (var j = 0; j < nodesList.length; j++) {
						var i3 = $("<i></i>").addClass("iconf-leaf");
						var input3 = $("<input></input>")
										.attr("type", "checkbox")
											.attr("name", "level3")
												.attr("value", nodesList[j].id);
						if (nodesList[j].desc2 == "yes") {
							input3.attr("checked", "true");
						}
						var span3 = $("<span></span>").append(i3).append(input3).append(nodesList[j].afternodename);
						var li3 = $("<li></li>").append(span3);
						ul2.append(li3);
					}
					li2.append(ul2);
					ul1.append(li2);
				}
			}
			li1.append(ul1);
			ul.append(li1);
			div.empty();
			div.append(ul);
			//树形菜单为节点添加展开，关闭的操作
		    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
		    $('.tree li.parent_li > span >i').on('click', function (e) {
		        var children = $(this).parent().parent('li.parent_li').find(' > ul > li');
		        if (children.is(":visible")) {
		            children.hide('fast');
		            $(this).attr('title', 'Expand this branch').find(' > i').addClass('iconf-plus-sign').removeClass('iconf-minus-sign');
		        } else {
		            children.show('fast');
		            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('iconf-minus-sign').removeClass('iconf-plus-sign');
		        }
		        e.stopPropagation();
		    });
		    
		    //树形菜单全选取消功能
		    $(".tree ul li").find("input[type='checkbox']").on("click",function(){
		    	//console.log($(this).parentsUntil(".tree","li").html());
		        var childInput=$(this).parentsUntil("ul","li").find("input[type='checkbox']");
		        if($(this).is(":checked")){
		            childInput.prop("checked",true);
		        }
		        else{
		            childInput.prop("checked",false);
		        }
		    });
		    
		    // 当第三级节点的checkbox给予其父级checkbox以选中状态
		    $(".tree [name=level3]:checkbox").on("click",function(){
		    	//console.log($(this).parentsUntil(".tree","li").html());
		    	// 获取当前节点的父级checkbox
		        var parentCheckBox = $(this).parent().parent().parent().parent().children("span").children("input");
		        if($(this).is(":checked")) {
		        	// 父级checkbox未选中时设置选中
		        	if (!parentCheckBox.is(":checked")) { 
		        		parentCheckBox.prop("checked",true);
		        	}
		        }
		    });
		}
	});
});

/**
 * 角色管理『设置页面权限』功能的『保存』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#pagePerSave").click(function() {
	var checkedMenuId = "";
	$("#pagePermission_div input[type=checkbox]:checked").each(function() {
		if (checkedMenuId != "") {
			checkedMenuId = checkedMenuId + "," + $(this).val();
		} else {
			checkedMenuId = checkedMenuId + $(this).val();
		}
		
	});
	
	var roleId = $("#setyemian_roleID").val();
	$("#setyemian").modal('hide');
	$.ajax({
		url : CONTEXTPATH + '/role/grantMenuPermissions',// 跳转到 action
		data : {menuIds:checkedMenuId, roleId:roleId},
		type : "post",
		success : function(result) {
			if( result &&result.code=="000000"){
				$("#role_search").trigger("click");
			
			}else {
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
		}
	});
	
	$('#setyemian').modal('hide');
});

/**
 * 角色管理中『详情』点击事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function detailDisplay(roleID, roleName) {
	$("#detail_name").html("角色名称：" + roleName);
	$("#modal_detail_id").val(roleID);
	$('#detail').modal();
}

/**
 * 角色管理中『详情』功能打开模态框后事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
$('#detail').on('shown.bs.modal', function (e) {
	var roleID = $("#modal_detail_id").val();
	var div = $("#detail_div");
	div.empty();
	div.append("data getting...");
	$.ajax({
		url : CONTEXTPATH + '/mainMenu/getMenuByRoleId',// 跳转到 action
		data : {roleId:roleID},
		type : "post",
		success : function(result) {
			var menuList ;
			if(result &&result.code=="000000"){
				menuList= result.data
			}else {
				div.empty();
				div.append("数据错误...");
				return;
			}
			// div
			var ul = $("<ul></ul>");
			// level1
			var i1 = $("<i></i>").addClass("iconf-folder-open");
			var span1 = $("<span></span>").append(i1).append("大易物流平台");
			var li1 = $("<li></li>").append(span1);
			var ul1 = $("<ul></ul>");
			for (var i = 0; i < menuList.length; i++) {
				if (menuList[i].afternodename != "您有消息") {
					if (menuList[i].desc2 == "yes") {
						// level2
						var i2 = $("<i></i>").addClass("iconf-minus-sign");
						var span2 = $("<span></span>").append(i2).append(menuList[i].afternodename);
						var li2 = $("<li></li>").append(span2);
						var ul2 = $("<ul></ul>");
						var nodesList = menuList[i].nodeList;
						for (var j = 0; j < nodesList.length; j++) {
							if (nodesList[j].desc2 == "yes") {
								var i3 = $("<i></i>").addClass("iconf-leaf");
								var span3 = $("<span></span>").append(i3).append(nodesList[j].afternodename);
								var li3 = $("<li></li>").append(span3);
								ul2.append(li3);
							}
						}
						li2.append(ul2);
						ul1.append(li2);
					}
				}
			}
			li1.append(ul1);
			ul.append(li1);
			div.empty();
			div.append(ul);
		    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
		    $('.tree li.parent_li > span >i').on('click', function (e) {
		        var children = $(this).parent().parent('li.parent_li').find(' > ul > li');
		        if (children.is(":visible")) {
		            children.hide('fast');
		            $(this).attr('title', 'Expand this branch').find(' > i').addClass('iconf-plus-sign').removeClass('iconf-minus-sign');
		        } else {
		            children.show('fast');
		            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('iconf-minus-sign').removeClass('iconf-plus-sign');
		        }
		        e.stopPropagation();
		    });
		}
	});
});

/**
 * 角色管理表体行中『复制』点击事件
 * 
 * @param roleNumber 角色编码
 * @param roleName  角色名称
 * @param roleDesc  角色描述
 * @param roleId    角色ID
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function roleCopy(roleNumber, roleName, roleDesc, copyedRoleId, copyedRowIndex) {
	$("#copiedName").val(roleName);
	$("#copiedRoleName").val(roleName);
	$("#copiedRoleNum").val(roleNumber);
	$("#copiedRoleDesc").val(roleDesc);
	$("#copiedRoleId").val(copyedRoleId);
	$("#copiedRowno").val(copyedRowIndex);
	
	$("#copyModal").modal();
}

/**
 * 角色管理表体行『修改』功能的『保存』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#copy_save").click(function() {
	var roleName = $("#copiedRoleName").val();
	var roleNumber = $("#copiedRoleNum").val();
	var roleDescription = $("#copiedRoleDesc").val();
	var copiedRoleId = $("#copiedRoleId").val();
	var copiedRowIndex = $("#copiedRowno").val();
	if( roleName ==""){
		$("#modal_common_content").html("角色名称不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}else if( roleNumber ==""){
		$("#modal_common_content").html("角色编号不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}
	$.ajax({
		url : CONTEXTPATH + '/role/copy',// 跳转到 action
		data : {roleName:roleName, roleNumber:roleNumber, roleDescription:roleDescription, id:copiedRoleId},
		type : "post",
		success : function(result) {
			if( result &&result.code=="000000"){
				$("#copyModal").modal('hide');
				$("#role_search").trigger("click");
			
			}else {
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
		}
	});
});


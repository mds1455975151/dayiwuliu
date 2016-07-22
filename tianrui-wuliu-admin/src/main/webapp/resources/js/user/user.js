/**
 * ==================================================================
 * 用户管理功能与后台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.04.12
 */

/** 表体行号 */
var rowIndex = 0;
/** 组织信息 */
var orgArray = new Array();

$(function() { 
	
	// 获取keyonh组织信息，用作模糊查询用
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
			$("#user_orgName").autocomplete({
				minLength: 0,
				max:3,
		        source: orgArray,
		        /*focus: function( event, ui ) {
		            $( "#user_orgName" ).val( ui.item.label );
		            return false;
		        },*/
		        select: function( event, ui ) {
		            $( "#user_orgName" ).val( ui.item.label );
		            $( "#user_orgId" ).val( ui.item.value );
		            return false;
		        }
		    });
		}
	});
});


/**
 * 用户管理中『搜索』功能点击事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
$("#user_search").click(function() {
	displayData(0);
});
function displayData(pageNo){
	// 用户编码
	var user_code = $("#user_code").val();
	// 用户名称
	var user_name = $("#user_name").val();
	// 手机
	var user_tel = $("#user_tel").val();
	var orgId = $("#user_orgId").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url : CONTEXTPATH + '/user/queryUser',// 跳转到 action
		data : {
			pageNo :(pageNo + 1),
			pageSize:pageSize,
			username:user_code, 
			name:user_name, 
			tel:user_tel,
			orgId:orgId
		},
		type : "post",
		success : function(result) {
			if( result && result.code=="000000" ){
				$("#totalRecords").html(result.data.total);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(result.data.total == 0) {
			    	$("#totalPages").html(1);  
			    	$("#user_tbody").empty();
			    	var html;
			    	if(user_code || user_name || user_tel ||orgId){
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
			    		html +='<h3>系统暂无可用用户！？</h3>';
			    		html +='<p>赶快动动手指加上吧！</p>';
			    		html +='</div>';
			    		html +='</div>';
			    		html +='</td>';
			    	}
			    	$("#user_tbody").html(html);
			    }else {
			    	$("#totalPages").html(parseInt((result.data.total-1)/pageSize+1));  
			    	appendContentToBody(result.data, 0);
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
 * 用户管理中『重置』功能点击事件
 * 
 * @author tank
 * @time 2016.05.21
 */
$("#user_reset").click(function() {
	// 用户编码
	$("#user_code").val("");
	// 用户名称
	$("#user_name").val("");
	// 手机
	$("#user_tel").val("");
	
	$("#user_orgId").val("");
	
	$("#user_orgName").val("");
});

/**
 * 搜索功能使用的循环函数体，用于附加动态表体内容至<body/>标签
 * @param data 动态获取的数据
 * @param flag 0:搜索 1：新增
 * @return null
 * @author kowuka
 * @time 2016.04.24
 */
function appendContentToBody(data, flag) {
	// 搜索查询时清空表体，防止表体重复附加数据
	if (flag == 0) {
		$("#user_tbody").empty();
		rowIndex = 0;
	}
	var datalist =data.list || [];
	for (var i = 0; i < datalist.length; i++) {
		// 表体行号++
		rowIndex++;
		var input1 = $("<input></input>")
						.attr("type","checkbox")
							.attr("name","user")
								.attr("value", datalist[i].id);
		var td1 = $("<td></td>").addClass("check-col").attr("field", "ck");
		var td2 = $("<td></td>").attr("field", "itCode").append(datalist[i].account);
		var td3 = $("<td></td>").attr("field", "name").append(datalist[i].name);
		var td4 = $("<td></td>").attr("field", "orgname").append(datalist[i].orgname);
		var td5 = $("<td></td>").attr("field", "accountStatus").append(datalist[i].status);
		var td6 = $("<td></td>").attr("field", "roles").append(datalist[i].role);
		var td7 = $("<td></td>").attr("field", "operation");
		
		var href1 = $("<a></a>")
						.attr("href", "javascript:permissionEdit('"
										  + datalist[i].id + "','" 
											+ datalist[i].account + "','" 
											  + datalist[i].tel + "','" 
											    + datalist[i].status + "','" 
											      + datalist[i].name + "','rowIndex" 
												    + rowIndex + "')")
							.html("【修改用户】");
		
		var href2 = $("<a></a>")
						.attr("href", "javascript:permissionDel('"
												+ datalist[i].id
												  + "','删除','rowIndex" + rowIndex + "')")
							.html("【删除】");
		var span1 = $("<span></span>").append(href1);
		var span2 = $("<span></span>").append(href2);
		
		if (flag == 1) {
			td2.css("color", "red");
		}
		if(datalist[i].account == "admin" ||datalist[i].account=="super"||datalist[i].account=="root"){
			//td7.append(span1).append(span2);
		}else {
			td1.append(input1);
			td7.append(span1).append(span2);
		}
		
		var tr = $("<tr></tr>").attr("id","rowIndex" + rowIndex).append(td1).append(td2).append(td3)
					.append(td4).append(td5).append(td6).append(td7);
		
		if (flag == 0) {
			// 搜索查询时表体正常附加数据
			$("#user_tbody").append(tr);
		} else {
			// 新增时新增数据附加至表体第一行
			$("#user_tbody").prepend(tr);
		}
		
	}
}

/**
 * 用户管理中『新增』功能打开模态框后事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
$('#addModal').on('shown.bs.modal', function (e) {
	// 用户编码
	$("#modal_add_account").val("");
	// 用户名称
	$("#modal_add_name").val("");
	// 电话
	$("#modal_add_tel").val("");
	// 组织信息
	$("#modal_add_orgName").autocomplete({
		minLength: 0,
		max:3,
        source: orgArray,
        select: function( event, ui ) {
            $( "#modal_add_orgName" ).val( ui.item.label );
            $( "#modal_add_orgId" ).val( ui.item.value );
            return false;
        }
    });
	
	// 清空原有用户状态，并默认选择用户状态为有效
	$("input[name='modal_add_status']").removeAttr("checked");
	$("input[name='modal_add_status']:eq(0)").attr("checked", "checked");
	// 由于使用removeAttr("checked")，故模拟点击事件，以免出现radio不能正常选中
	$("input[name='modal_add_status']:eq(0)").click();
	// 清空左右角色栏数据
	$("#modal_add_left").empty();
	$("#modal_add_right").empty();
	$.ajax({
		url : CONTEXTPATH + '/role/query',// 跳转到 action
		data : {},
		type : "get",
		success : function(result) {
			if( !result  || result.code != "000000" ){
				alert(result.error);
			}
			var data = result.data;
			for (var i = 0; i < data.length; i++) {
				var href = $("<a></a>").append(data[i].name);
				// 为角色栏里绑定单击事件与双击事件
				var li = $("<li></li>").attr("id","addRole" + data[i].id)
											.attr("onclick", "javascript:clickElements('addRole" + data[i].id + "')")
												.attr("ondblclick", "javascript:dblClickElements('addRole" + data[i].id + "')")
													.append(href);
				
				$("#modal_add_left").append(li);
			}
		}
	});
});  

/**
 * 用户管理中『批量删除』功能点击事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#batchDelete").click(function() {
	// 获取表体所有checkbox被选中数据的用户ID
	var checkedUserId = "";
	$("#user_tbody input[type=checkbox]:checked").each(function() {
		if (checkedUserId != "") {
			checkedUserId = checkedUserId + "," + $(this).val();
		} else {
			checkedUserId = checkedUserId + $(this).val();
		}
	});
	permissionDel(checkedUserId, "批量删除");
});

/**
 * 用户管理中『新增』功能的『保存』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#modal_add_save").click(function() {
	// 获取模态框中右侧被选中数据的角色ID
	var selectedRoleId = "";
	$(".qxalert1 .checkout .right_ul li").each(function() {
		if (selectedRoleId != "") {
			selectedRoleId = selectedRoleId + "," + $(this).attr("id").substring(7);
		} else {
			selectedRoleId = selectedRoleId + $(this).attr("id").substring(7);
		}
	});
	var reg = /^1[3|4|5|7|8]\d{9}$/;
	// 用户账号
	var account = $.trim($("#modal_add_account").val());
	// 用户名称
	var name = $.trim($("#modal_add_name").val());
	// 手机
	var tel = $.trim($("#modal_add_tel").val());
	// 用户状态
	var status = $("input[name='modal_add_status']:checked").val();
	// 组织ID
	var orgId = $("#modal_add_orgId").val();
	// 组织名称
	var orgName = $("#modal_add_orgName").val();
	if( account ==""){
		$("#modal_common_content").html("账号不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}else if( name ==""){
		$("#modal_common_content").html("姓名不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}else if( tel ==""){
		$("#modal_common_content").html("手机号不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}else if( orgName ==""){
		$("#modal_common_content").html("组织不能为空，请选择...");
		$("#commonModal").modal();
		return;
	}else if(!reg.test(tel)){
		$("#modal_common_content").html("手机号输入不合法,请重新输入...");
		$("#commonModal").modal();
		return;
	}
	var orgflag = false;
	//判断组织信息是否正确
	for(var i=0;i<orgArray.length;i++){
		if(orgName == orgArray[i].label && orgId ==orgArray[i].value){
			orgflag = true;
		}
	}
	if(!orgflag){
		$("#modal_common_content").html("你输入的组织不存在，请选择...");
		$("#commonModal").modal();
		return;
	}
	$.ajax({
		url : CONTEXTPATH + '/user/savevUser',// 跳转到 action
		data : {
			username: account,
			name: name,
			status: status,
			tel: tel,
			roleIds: selectedRoleId,
			orgId: orgId,
			orgName: orgName
		},
		type : "post",
		success : function(result) {
			if( result && result.code =="000000" ){
				$("#user_reset").trigger("click");
				$("#user_search").trigger("click");
				// 关闭模态框
				$('#addModal').modal('hide');
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
function prependAddedData(user_code, user_name, user_tel) {
	$.ajax({
		url : CONTEXTPATH + '/user/queryUser',// 跳转到 action
		data : {username:user_code, name:user_name, tel:user_tel},
		type : "post",
		success : function(result) {
			if( result && result.code =="000000" ){
				$("#user_search").trigger("click");
			}else{
				alert(result.error);
			}
		}
	});
}

/**
 * 用户管理表体行中『修改权限』点击事件
 * 
 * @param userAccount 账户
 * @param userName    名称
 * @param userTel     电话
 * @param userStatus  状态
 * @param userId      用户ID
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function permissionEdit(userId, userAccount, userTel, userStatus, userName, rowIndex) {
	$("#modal_edit_account").val(userAccount);
	$("input[name='modal_edit_status']").removeAttr("checked");
	if (userStatus == "有效") {
		$("input[name='modal_edit_status']").eq(0).attr("checked","checked");
		$("input[name='modal_edit_status']").eq(0).click();
	} else {
		$("input[name='modal_edit_status']").eq(1).attr("checked","checked");
		$("input[name='modal_edit_status']").eq(1).click();
	}
	$("#modal_edit_tel").val(userTel);
	$("#modal_edit_name").val(userName);
	$("#modal_edit_id").val(userId);
	$("#modal_edit_rowno").val(rowIndex);
	
	$("#editModal").modal();
}

/**
 * 用户管理表体行中『修改权限』模态框打开后事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
$('#editModal').on('shown.bs.modal', function (e) {
	var userId = $("#modal_edit_id").val();
	$("#modal_edit_left").empty();
	$("#modal_edit_right").empty();
	$("#modal_edit_resetPassword").removeAttr("checked");
	var li_hidden = $("<li style='display:none'></li>").append("data getting...");
	var li_hidden1 = $("<li style='display:none'></li>").append("data getting...");
	$("#modal_edit_left").append(li_hidden);
	$("#modal_edit_right").append(li_hidden1);
	li_hidden.show();
	li_hidden1.show();
	$.ajax({
		url : CONTEXTPATH + '/user/getUserRole',// 跳转到 action
		data : {id:userId},
		type : "post",
		success : function(result) {
			if(result &&result.code=="000000"  ){
				var data = result.data;
				for (var i = 0; i < data.length; i++) {
					if (data[i].own == "0") {
						var href0 = $("<a></a>").append(data[i].name);
						var li0 = $("<li></li>").attr("id","editRole" + data[i].id)
						.attr("onclick", "javascript:clickElements('editRole" + data[i].id + "')")
						.attr("ondblclick", "javascript:dblClickElements('editRole" + data[i].id + "')")
						.append(href0);
						$("#modal_edit_left").append(li0);
					} else {
						var href1 = $("<a></a>").append(data[i].name);
						var li1 = $("<li></li>").attr("id","editRole" + data[i].id)
						.attr("onclick", "javascript:clickElements('editRole" + data[i].id + "')")
						.attr("ondblclick", "javascript:dblClickElements('editRole" + data[i].id + "')")
						.append(href1);
						$("#modal_edit_right").append(li1);
					}
				}
			}
			li_hidden1.hide();
			li_hidden.hide();
		}
	});
});

/**
 * 用户管理表体行『修改』功能中的单击选中事件
 * 
 * @param elements    be选中ed元素
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
function clickElements(elements) {
	$("#" + elements).toggleClass("seled");
}

/**
 * 菜单管理表体行『修改权限』功能中的双击事件
 * 
 * @param elements    be选中ed元素
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
function dblClickElements(elements) {
	var subElements = elements.substring(0,elements.indexOf("Role"));
	if($("#" + elements).parent("ul").hasClass("left_ul")){
		if (subElements == "add") {
			toRight1($("#" + elements));
		} else {
			toRight3($("#" + elements));
		}
    } else {
    	if (subElements == "add") {
    		toLeft1($("#" + elements));
    	} else {
    		toLeft3($("#" + elements));
    	}
    }
}

/**
 * 用户管理中『修改权限』功能的『保存』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#modal_edit_save").click(function() {
	var selectedRoleId = "";
	$(".qxalert3 .checkout .right_ul li").each(function() {
		if (selectedRoleId != "") {
			if ($(this).attr("id")) {
				selectedRoleId = selectedRoleId + "," + $(this).attr("id").substring(8);
			}
		} else {
			if ($(this).attr("id")) {
				selectedRoleId = selectedRoleId + $(this).attr("id").substring(8);
			}
		}
	});
	
	var account = $("#modal_edit_account").val();
	var name = $("#modal_edit_name").val();
	var tel = $("#modal_edit_tel").val();
	var id = $("#modal_edit_id").val();
	var rowIndex = $("#modal_edit_rowno").val();
	var ifReset = "0";
	if ($("#modal_edit_resetPassword").is(":checked")) {
		ifReset = "1";
	}
	var status = $("input[name='modal_edit_status']:checked").val();
	var reg = /^1[3|4|5|7|8]\d{9}$/;
	if( name ==""){
		$("#modal_common_content").html("姓名不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}else if( tel ==""){
		$("#modal_common_content").html("手机号不能为空，请输入...");
		$("#commonModal").modal();
		return;
	}else if(!reg.test(tel)){
		$("#modal_common_content").html("手机号输入不合法,请重新输入...");
		$("#commonModal").modal();
		return;
	}
	$.ajax({
		url : CONTEXTPATH + '/user/updateUser',// 跳转到 action
		data : {username:account, name:name, status:status, tel:tel, id:id, ifReset:ifReset, roleIds:selectedRoleId},
		type : "post",
		success : function(result) {
			if( result && result.code=="000000" ){
				$('#editModal').modal('hide');
				$("#user_search").trigger("click");
			}else{
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
		}
	});
	
});

/**
 * 用户管理表体行中『删除权限』点击事件
 * 
 * @param roleId 角色编号
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function permissionDel(userId, title, rowIndex) {
	$("#modal_del_title").html(title);
	$("#modal_del_id").val(userId);
	if (title == "批量删除") {
		$("#modal_del_flag").val("0");
	} else {
		$("#modal_del_flag").val("1");
	}
	$("#modal_del_rowno").val(rowIndex);
	
	$("#deleModal").modal('show');
	
}

/**
 * 用户管理表体行『删除权限』功能的『确定』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#modal_del_confirm").click(function() {
	$("#deleModal").modal('hide');
	var userId = $("#modal_del_id").val();
	var delFlag = $("#modal_del_flag").val();
	$.ajax({
		url : CONTEXTPATH + '/user/deleteUser',// 跳转到 action
		data : {idstr:userId},
		type : "post",
		success : function(result) {
			if( result && result.code=="000000" ){
				$("#user_search").trigger("click");
			}else{
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
			
		}
	});
});


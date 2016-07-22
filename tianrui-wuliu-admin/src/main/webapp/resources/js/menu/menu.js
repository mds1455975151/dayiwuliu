/**
 * ============================================================
 * 菜单管理功能与后台交互
 * ============================================================
 * 
 * @author kowuka 
 * @time 2016.04.12
 */

/**
 * 菜单管理中『搜索』功能点击事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
$(function() {
	$.ajax({
		url : CONTEXTPATH + '/mainMenu/queryMainMenu.json',// 跳转到 action
		data : {},
		type : "post",
		success : function(result) {
			if( !result || result.code!="000000"){
				return ;
			}
			var menuList = result.data||[];
			$("#menu_tbody").empty();
			var n = 1;
			for (var i = 0; i < menuList.length; i++) {
				getMenuListArea(menuList, i, 0, n);
				n++;
				var nodeList = menuList[i].nodeList||[];
				for (var j = 0; j < nodeList.length; j++) {
					getMenuListArea(nodeList, j, 1, n);
					n++;
				}
			}
		    //表格列宽度拖拽js调用
		    $("table").resizableColumns({});

		    //菜单表格中的多行表格js调用
		    var option = {
		        theme: 'vsStyle',
		        expandLevel: 1,
		        column: 1,
		        beforeExpand: function ($treeTable, id) {
		            //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
		            if ($('.' + id, $treeTable).length) {
		                return;
		            }
		            //这里的html可以是ajax请求
		            var html = '<tr id="12" pId="6"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
		                    + '<tr id="13" pId="6"><td>5.2</td><td>动态的内容</td></tr>';
		            $treeTable.addChilds(html);
		        },
		        onSelect: function ($treeTable, id) {
		            window.console && console.log('onSelect:' + id);
		        }
		    };
		    $('#treeTable1').treeTable(option);
		}
	});
});

/**
 * 菜单管理中『搜索』功能点击事件
 * 
 * @param menuList     菜单List
 * @param i            循环变量
 * @param flag         父子菜单判定flag
 * @param n            行号
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function getMenuListArea(menuList, i, flag, n) {
	var span2 = $("<span></span>").attr("controller","true").append(menuList[i].name);
	
	var td1 = $("<td></td>").append(n);
	var td2 = $("<td></td>").attr("style", "width: 200px;").append(menuList[i].afternodename);
	var td3 = $("<td></td>").attr("field", "name").append(menuList[i].afternodename);
	var td4 = $("<td></td>").attr("field", "accountStatus").append(menuList[i].afterurl);
	var td5 = $("<td></td>").attr("field", "roles").append(menuList[i].order1);
	var td6 = $("<td></td>").attr("field", "roles").append("YES");
	var td7 = $("<td></td>").attr("field", "operation");
	
	var href7_1 = $("<a></a>")//.attr("data-toggle", "modal")
			                    //.attr("data-target", "#addmenu")
			                     .attr("href", "javascript:menuAdd2('"
												  + menuList[i].id + "','" 
													+ menuList[i].afternodename + "','" 
													  + menuList[i].url + "','" 
													    + menuList[i].order1 + "')")
			                      .html("【新增】");
	
	var href7_2 = $("<a></a>")//.attr("data-toggle", "modal")
							    //.attr("data-target", "#eidtModal")
				                 .attr("href", "javascript:menuEdit('" + menuList[i].id 
												   + "','" + (menuList[i].afternodename || "") 
									                + "','" + (menuList[i].afterurl || " ")
										             + "','" + menuList[i].order1 + "')")
								  .html("【修改】");
	
	var href7_3 = $("<a></a>")//.attr("data-toggle", "modal")
			                   //.attr("data-target", "#deleModal")
			                    .attr("href", "javascript:menuDel('" + menuList[i].id + "')")
			                      .html("【删除 】");
	
	var href7_4 = $("<a></a>")//.attr("data-toggle", "modal")
							   //.attr("data-target", "#setyemian")
							    .attr("href", "javascript:acPermissionSet('"
												   + menuList[i].id + "','" 
													+ (menuList[i].afternodename||" " )+ "')")
							      .html("【设置访问权限】");
	
	var span7_1 = $("<span></span>").append(href7_1);
	var span7_2 = $("<span></span>").append(href7_2);
	var span7_3 = $("<span></span>").append(href7_3);
	var span7_4 = $("<span></span>").append(href7_4);
	
	td2.append(span2);
	if(menuList[i].afternodename == "首页"){
		td7.append();
	}else if(flag == 1){
		td7.append(span7_2).append(span7_3).append(span7_4);
	}else{
		td7.append(span7_1).append(span7_2).append(span7_3).append(span7_4);
	}
	
	var tr = $("<tr></tr>").attr("id", "menu_" + menuList[i].id)
	.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7);
	if (flag != 0) {
		tr.attr("pid", "menu_" +menuList[i].parentnodeid);
	}
	
	$("#menu_tbody").append(tr);
	
}

/**
 * 菜单管理中头部『新增』功能的『保存』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#menu_add_save").click(function() {
	var menuName = $("#menu_add_name").val();
	var menuPid = $("#menu_add_pid option:selected").val();
	var menuUrl = $("#menu_add_url").val();
	var menuOrder = $("#menu_add_order").val();
	$("#addmenu").modal('hide');
	$.ajax({
		url : CONTEXTPATH + '/mainMenu/add',// 跳转到 action
		data : {parentMenuId:menuPid,menuName:menuName,url:menuUrl,order:menuOrder},
		type : "post",
		success : function(result) {
			if( result &&result.code=="000000" ){
				alert("新增成功")
				window.location.reload();
			}else{
				alert(result.error);
			}
		}
	});
});

/**
 * 菜单管理中头部『新增』模态框打开后事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
$('#addmenu').on('shown.bs.modal', function (e) {
	//$("#modal_add_pid").empty();
	//setOption($("#modal_add_pid"));
});

/**
 * 菜单管理中表体行『修改』模态框打开后事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
$('#editModal').on('shown.bs.modal', function (e) {
	//$("#modal_edit_pid").empty();
	//setOption($("#modal_edit_pid"));
});

/**
 * 菜单管理中表体行『修改』模态框获取下拉菜单
 * 
 * @param vselect    option
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function setOption(vselect) {
	$.ajax({
		url : CONTEXTPATH + '/mainMenu/queryMainMenu.json',// 跳转到 action
		data : {},
		type : "post",
		success : function(result) {
			var menuList = result.menuList || [];
			for (var i = 0; i < menuList.length; i++) {
				var outGroup = $("<optgroup></optgroup>").attr("label", menuList[i].afternodename).attr("value", menuList[i].id);
				var nodeList = menuList[i].nodesList;
				for (var j = 0; j < nodeList.length; j++) {
					var option = $("<option></option>").attr("value", nodeList[j].id).text(nodeList[j].afternodename);
					outGroup.append(option);
				}
				vselect.append(outGroup);
			}
			
			 //菜单的新增弹窗select添加二级选项
	        $("[name='modal_add_pid']").selectbox({
	            effect: "fade"
	        }); 
	        //菜单的新增弹窗select添加二级选项
	        $("[name='modal_edit_pid']").selectbox({
	            effect: "fade"
	        });  
	        $('.level_sel li:has(span)').addClass('menu_logo');

	        var tubiao = $("<i></i>");
	        tubiao.addClass("icon-folder-open");
	        $('.level_sel li span').prepend(tubiao);
	        $('.level_sel li > span').on('click', function (e) {
	            var children = $(this).parent('.level_sel ul li.menu_logo').nextUntil("li.menu_logo");
	            var zhi = $(this).text();
	            $('.level_sel a.sbSelector').text(zhi);

	            if (children.is(":visible")) {
	                children.hide('fast');
	            } else {
	                children.show('fast');
	            }
	            e.stopPropagation();
	        });
		}
	});
}

/**
 * 菜单管理中表体行『新增』点击事件
 * 
 * @param menuId      菜单ID
 * @param menuName    菜单名称
 * @param menuUrl     菜单URL
 * @param menuOrder   菜单Order
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function menuAdd2(menuId, menuName, menuUrl, menuOrder) {
	$("#menu_add2_name").val();
	$("#menu_add2_url").val();
	$("#menu_add2_order").val();
	
	$("#menu_add2_parentName").val(menuName);
	$("#menu_add2_id").val(menuId);
	
	$("#addmenu2").modal('show');
}

/**
 * 菜单管理中『新增』功能的『保存』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#menu_add2_save").click(function() {
	var menuName = $("#menu_add2_name").val();
	var menuPid = $("#menu_add2_id").val();
	var menuUrl = $("#menu_add2_url").val();
	var menuOrder = $("#menu_add2_order").val();
	$("#addmenu2").modal('hide');
	$.ajax({
		url : CONTEXTPATH + '/mainMenu/add',// 跳转到 action
		data : {parentMenuId:menuPid,menuName:menuName,url:menuUrl,order:menuOrder},
		type : "post",
		success : function(result) {
			if( result &&result.code=="000000" ){
				alert("新增成功")
				window.location.reload();
			}else{
				alert(result.error);
			}
		}
	});
});

/**
 * 菜单管理表体行中『修改』点击事件
 * 
 * @param menuId      菜单ID
 * @param menuName    菜单名称
 * @param menuUrl     菜单URL
 * @param menuOrder   菜单Order
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function menuEdit(menuId, menuName, menuUrl, menuOrder) {
	$("#menu_edit_name").val(menuName);
	$("#menu_edit_id").val(menuId);
	$("#menu_edit_url").val(menuUrl || "");
	$("#menu_edit_order").val(menuOrder);
	
	$("#editModal").modal();
}

/**
 * 菜单管理表体行『修改』功能的『保存』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#menu_edit_save").click(function() {
	var menuName = $("#menu_edit_name").val();
	var menuId = $("#menu_edit_id").val();
	var menuUrl = $("#menu_edit_url").val();
	var menuOrder = $("#menu_edit_order").val();
	$("#editModal").modal('hide');
	$.ajax({
		url : CONTEXTPATH + '/mainMenu/update',// 跳转到 action
		data : {menuId:menuId, menuName:menuName, url:menuUrl, order:menuOrder},
		type : "post",
		success : function(result) {
			if( result &&result.code=="000000" ){
				alert("修改成功");
				window.location.reload();
			}else{
				alert(result.error);
			}
		}
	});
});

/**
 * 菜单管理表体行中『删除』点击事件
 * 
 * @param roleId 菜单编号
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function menuDel(menuId) {
	$("#modal_del_menuId").val(menuId);
	
	$("#deleModal").modal('show');
	
}

/**
 * 菜单管理表体行『删除』功能的『确定』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#modal_del_confirm").click(function() {
	var menuId = $("#modal_del_menuId").val();
	$("#deleModal").modal('hide');
	$.ajax({
		url : CONTEXTPATH + '/mainMenu/delete',// 跳转到 action
		data : {menuId:menuId},
		type : "get",
		success : function(result) {
			if( result &&result.code=="000000" ){
				alert("删除成功")
				window.location.reload();
			}else{
				alert(result.error);
			}
		}
	});
});

/**
 * 菜单管理表体行中『设置访问权限』点击事件
 * 
 * @param menuId      菜单ID
 * @param menuName    菜单名称
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
function acPermissionSet(menuId, menuName) {
	$("#modal_set_id").val(menuId);
	$("#modal_set_title").html("菜单名称：" + menuName);
	
	$("#setyemian").modal();
}

/**
 * 菜单管理表体行中『设置访问权限』模态框打开后事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.13
 */
$('#setyemian').on('shown.bs.modal', function (e) {
	var menuId = $("#modal_set_id").val();
	$("#modal_set_left").empty();
	$("#modal_set_right").empty();
	var li_hidden = $("<li style='display:none text-align:center'></li>").append("data getting...");
	var li_hidden1 = $("<li style='display:none'></li>").append("data getting...");
	$("#modal_set_left").append(li_hidden);
	$("#modal_set_right").append(li_hidden1);
	li_hidden.show();
	li_hidden1.show();
	$.ajax({
		url : CONTEXTPATH + '/mainMenu/getRoleByMenuId',// 跳转到 action
		data : {menuId:menuId},
		type : "GET",
		success : function(result) {
			var data = result.data;
			for (var i = 0; i < data.length; i++) {
				if (data[i].allow == "no") {
					var href0 = $("<a></a>").append(data[i].name);
					var li0 = $("<li></li>").attr("id","editRole" + data[i].id)
									.attr("dataid",data[i].id)
									.attr("onclick", "javascript:clickElements('editRole" + data[i].id + "')")
										.attr("ondblclick", "javascript:dblClickElements('editRole" + data[i].id + "')")
											.append(href0);
					$("#modal_set_left").append(li0);
				} else {
					var href1 = $("<a></a>").append(data[i].name);
					var li1 = $("<li></li>").attr("id","editRole" + data[i].id)
									.attr("dataid", data[i].id)
									.attr("onclick", "javascript:clickElements('editRole" + data[i].id + "')")
										.attr("ondblclick", "javascript:dblClickElements('editRole" + data[i].id + "')")
											.append(href1);
					$("#modal_set_right").append(li1);
				}
			}
			li_hidden.hide();
			li_hidden1.hide();
		}
	});
});

/**
 * 菜单管理表体行『设置访问权限』功能的『保存』按钮事件
 * 
 * @param null
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
$("#modal_set_save").click(function() {
	var selectedRoleId ="";
	$(".qxalert4 .checkout .right_ul li").each(function() {
			if($(this).attr("dataid")){
				selectedRoleId += $(this).attr("dataid")+",";
			}
	});
	
	var menuId = $("#modal_set_id").val();
	
	$('#setyemian').modal('hide');
	
	$.ajax({
		url : CONTEXTPATH + '/mainMenu/grantMenuToRole',// 跳转到 action
		data : {menuId:menuId, roleIds:selectedRoleId},
		type : "post",
		success : function(result) {
			if( result &&result.code=="000000" ){
				alert("设置成功")
				window.location.reload();
			}else{
				alert(result.error);
			}
		}
	});
	
});

/**
 * 菜单管理表体行『设置访问权限』功能中的单击选中事件
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
 * 菜单管理表体行『设置访问权限』功能中的双击事件
 * 
 * @param elements    be选中ed元素
 * @return null
 * @author kowuka
 * @time 2016.04.12
 */
function dblClickElements(elements) {
	if($("#" + elements).parent("ul").hasClass("left_ul")){
        toRight4($("#" + elements));
    }
    else{
        toLeft4($("#" + elements));
    }
}


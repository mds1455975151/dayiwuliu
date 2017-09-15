/**
 * 組織檔案中新增功能的保存按钮事件
 */
$("#add_save").click(function() {
	var organizationname = $("#organizationname_add").val();
	var organizationtype = $("#organizationtype_add option:selected").val();
	var organizationno = $("#organizationno_add").val();
	var status = $("#status_add option:selected").val();
	if(organizationname==""){
		alert("组织名称不能为空");
		return
	}
	if(organizationtype==""){
		alert("组织类型不能为空");
		return
	}
	if(organizationno==""){
		alert("组织编码不能为空");
		return
	}
	if(status==""){
		alert("组织状态不能为空");
		return
	}
	$.ajax({
		url : CONTEXTPATH + '/Organization/add',// 跳转到 action
		data : {organizationname:organizationname,organizationtype:organizationtype,organizationno:organizationno,status:status},
		type : "post",
		success : function(result) {
			if( result && result.code =="000000" ){
				Add_reset();
				organSearch();
				$("#addModal").modal('hide');
			}else{
				$("#modal_common_content").html(result.error);
				$("#commonModal").modal();
			}
		}
	});
});


/**
 * 修改档案启用状态
 */
function changeOrgStatus(id,pageNo){
	var str = "	是否确认要修改？";
	if(confirm(str)){
		$.ajax({
			url:CONTEXTPATH+'/Organization/orgprohibition',
			data:{"id":id },
			type:"get",
			success: function(result) {
				if( result && result.code =="000000" ){
					$("#org_search").trigger("click");
					displayData(parseInt(pageNo));
				}else{
					alert(result.error);
				}
			}
		});
	}
}
/**
 * 删除组织
 */
function deleteById(id,pageNo){
	var str = "	是否确认要删除该组织？";
	if(confirm(str)){
		$.ajax({
			url:CONTEXTPATH+'/Organization/deleteOrgById',
			data:{"id":id },
			type:"get",
			success: function(result) {
				if( result && result.code =="000000" ){
					$("#org_search").trigger("click");
					displayData(parseInt(pageNo));
				}else{
					alert(result.error);
				}
			}
		});
	}
}

/**
 * 修改组织档案
 */
function OrgUpdate(){
	var id = $("#id_edit").val();
	var organizationname = $("#organizationname_edit").val();
	var organizationtype = $("#organizationtype_edit").val();
	var organizationno = $("#organizationno_edit").val();
	var status = $("#status_edit").val();
	var pageNo =$("#pageNo").val();
	if(organizationname==""){
		alert("组织名称不能为空");
		return
	}
	if(organizationtype==""){
		alert("组织类型不能为空");
		return
	}
	if(organizationno==""){
		alert("组织编码不能为空");
		return
	}
	if(status==""){
		alert("组织状态不能为空");
		return
	}
	$.ajax({
		url : CONTEXTPATH + '/Organization/update',// 跳转到 action
		data : {id:id,
			organizationname:organizationname,
			organizationtype:organizationtype,
			organizationno:organizationno,
			status:status},
		type : "post",
		success : function(result) {
			if(result.code!="000000"){
				alert(result.error);
			}else{
				//$("#org_reset").trigger("click");
//				$("#org_search").trigger("click");
				// 关闭模态框
				$('#edit_org').modal('hide');
				displayData(parseInt(pageNo));
			}
		}
	});
}
/**
 * 根据ID查询
 * @param id
 */
function selectById(id,pageNo){
	$.ajax({
		url : CONTEXTPATH + '/Organization/findById',// 跳转到 action
		data : {"id":id},
		type : "post",
		success : function(result) {
			var data = result.data;
			if(result.code!="000000"){
				alert(result.error);
			}else{
				document.getElementById("id_edit").value = data.id;
				document.getElementById("organizationname_edit").value = data.organizationname;
				document.getElementById("organizationtype_edit").value = data.organizationtype;
				document.getElementById("organizationno_edit").value = data.organizationno;
				document.getElementById("status_edit").value = data.status;
				$("#pageNo").val(pageNo);
			}
		}
	});
}
/**
 * 搜索
 */
function organSearch(){
	displayData(0);
}
function displayData(pageNo){
	// 组织名称
	var organizationname = $("#organizationname_search").val();
	// 组织类型
	var organizationtype = $("#organizationtype_search option:selected").val();
	// 组织编码
	var organizationno = $("#organizationno_search").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url : CONTEXTPATH + '/Organization/queryOrgByPage',// 跳转到 action
		data : {
			pageNo :(pageNo + 1),
			pageSize:pageSize,
			organizationname:organizationname,
			organizationtype:organizationtype, 
			organizationno:organizationno
		},
		type : "post",
		success : function(result) {
			var hml = "";
			if(result.code!="000000"){
				alert("查询失败");
			}else {
				$("#totalRecords").html(result.data.total);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(result.data.total == 0) {
			    	$("#totalPages").html(1);  
			    	if(organizationname || organizationtype || organizationno ){
			    		hml +='<td colspan="12">';
			    		hml +='<div class="ht_none">';
			    		hml +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
			    		hml +='<div>';
			    		hml +='<h3>唉呀！查询不到您要找的内容</h3>';
			    		hml +='<p>换个查询条件试试吧？</p>';
			    		hml +='</div>';
			    		hml +='</div>';
			    		hml +='</td>';
			    	}else{
			    		hml +=' <td colspan="12">';
			    		hml +='<div class="ht_none">';
			    		hml +='<img src="'+imagesRoot+'/none1.png" class="ht_nimg2">';
			    		hml +='<div>';
			    		hml +='<h3>系统暂无可用组织档案！？</h3>';
			    		hml +='<p>赶快动动手指加上吧！</p>';
			    		hml +='</div>';
			    		hml +='</div>';
			    		hml +='</td>';
			    	}
			    }else {
			    	$("#totalPages").html(parseInt((result.data.total-1)/pageSize+1));  
				    if(result.data && result.data.list){
				    	var data = result.data.list ;
						for (var i = 0; i < data.length; i++) {
							var s = i +1 ;
							var stat = "";
							var stat1 = "";
							var type = "";
							if(data[i].status == "1"){
								stat="禁用";
								stat1="启用";
							}else if(data[i].status == "2"){
								stat="启用";
								stat1="禁用"
							}
							if(data[i].organizationtype =="1"){
								type="天瑞旗下"
							}else if(data[i].organizationtype =="2"){
								type="山水旗下"
							}else if(data[i].organizationtype =="3"){
								type="社会"
							}
							hml +="<tr><td >"+s+"</td>"+
							"<td>"+data[i].organizationname+"</td>"+
							"<td>"+data[i].organizationno+"</td>"+
							"<td>"+type+"</td>"+
							"<td>"+stat1+"</td>"+
							"<td>"+data[i].creator+"</td>"+
							"<td>"+data[i].createtimeStr+"</td>";
							if(data[i].organizationno == 0000){
								hml +="<td ></td></tr>";
							}else {
								
								hml += "<td ><span><a data-toggle='modal' onclick=\"changeOrgStatus('"+data[i].id+"','"+(pageNo)+"')\" data-target='#tingyong' >【"+stat+"】</a></span>";
								if(data[i].count == 0){
									hml +="<span onclick=\"selectById('"+data[i].id+"','"+(pageNo)+"')\"><a data-toggle='modal' "+
									" data-target='#edit_org'>【修改】</a></span>"+
									"<span onclick=\"deleteById('"+data[i].id+"','"+(pageNo)+"')\"> <a>【删除】</a></span>";

								}
								hml +="</td></tr>";
							}
							
						}
				    }
			    }   
				document.getElementById("org_tbody").innerHTML = hml;
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
/**
 * 重置新增框内容
 */
function Add_reset(){
	document.getElementById("organizationname_add").value="";
	//document.getElementById("organizationtype_add").value="";
	document.getElementById("organizationno_add").value="";
	//document.getElementById("status_add").value="";
}
/**
 * 重置搜索框内容
 */
$("#org_reset").click(function() {
	document.getElementById("organizationname_search").value="";
	document.getElementById("organizationtype_search").value="";
	document.getElementById("organizationno_search").value="";
});
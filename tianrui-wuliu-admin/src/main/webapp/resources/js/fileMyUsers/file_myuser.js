var data ;
/**
 * 查询列表
 */
function searchMember(){
	displayData(0);
}
function displayData(pageNo){
	var cellPhone = $("#cellPhone").val();
	var membername = $("#membername").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+"/file/filemyuser/findOrgMember",
		data:{
			"membertel":cellPhone,
			"membername":membername,
			"pageNo":(pageNo + 1),
			"pageSize":pageSize
		},
		type:"post",
		success:function(result){
			if(result.code=="000000"){
				data = result.data.list;
				$("#totalRecords").html(result.data.count);
		    	document.getElementById("goPage").value = pageNo+1;
		    	var hml = "";
			    if(result.data.count == 0) {
			    	$("#totalPages").html(1);  
			    	if(cellPhone){
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
			    		hml +='<h3>组织下暂无会员！？添加会员后，会员才能发布计划！</h3>';
			    		hml +='<p>赶快动动手指加上吧！</p>';
			    		hml +='</div>';
			    		hml +='</div>';
			    		hml +='</td>';
			    	}
			    }else {
			    	$("#totalPages").html(parseInt((result.data.count-1)/pageSize+1)); 
			    	for (var a = 0; a < data.length; a++) {
						var d = a+1;
						hml +=
							"<tr><td>"+d+"</td>"+
							"<td>"+data[a].membertel+"</td>"+
							"<td>"+data[a].membername+"</td>"+
							"<td>"+(data[a].memberdesc || "")+"</td>"+
							"<td>"+data[a].createtimeStr+"</td>"+
							"<td>"+data[a].creator+"</td>"+
							"<td>" +
							"<span><a data-toggle='modal' onclick=\"update('"+a+"','"+pageNo+"')\" data-target='#edit'>【修改】</a></span>"+
							"<span><a data-toggle='modal' onclick=\"delect('"+data[a].id+"','"+pageNo+"')\" data-target='#dele'>【删除】</a></span></td></tr>";
					}
			    }   
				document.getElementById("innerHml").innerHTML=hml;
				$("#pagination").pagination(result.data.count, {   
				    callback: pageCallback,   
				    prev_text: '上一页',   
				    next_text: '下一页',   
				    items_per_page:pageSize,   
				    num_display_entries:4,   
				    current_page:pageNo,   
				    num_edge_entries:1, 
				    maxentries: result.data.count, 
				    link_to:"javascript:void(0)"
				}); 
			}else{
				alert(result.error);
			}
		}
	});
}

/**
 * 获取删除id
 * @param id
 */
function delect(id,pageNo){
	document.getElementById("detid").value = id;
	$("#pageNo").val(pageNo);
}

function deleteOrgMember(){
	var id = $("#detid").val();
	var pageNo =$("#pageNo").val();
	$.ajax({
		url:CONTEXTPATH+"/file/filemyuser/deleteOrgMember",
		data:{"id":id
		},
		type:"post",
		success:function(ret){
			type = ret.code;
			if(ret.code!="000000"){
				alert(ret.error);
			}else{
				document.getElementById("detid").value = "";
				displayData(parseInt(pageNo));
			}
		}
	});
}
/**
 * 查看详情
 * @param a
 */
function detalt(a){
	var d = data[a].membertel;
	var s = data[a].memberdesc;
	document.getElementById("memberTel").innerHTML=d;
	
	document.getElementById("memberDesc").innerHTML=s;
}
/**
 * 修改
 * @param a
 */
function update(a,pageNo){
	var id = data[a].id;
	var membertel = data[a].membertel;
	var memberdesc = data[a].memberdesc;
	if(memberdesc == undefined){
		memberdesc = "";
	}
	document.getElementById("uptmemberTel").value=membertel;
	document.getElementById("uptmembername").value=data[a].membername;
	document.getElementById("uptmemberDesc").value=memberdesc;
	document.getElementById("uptid").value=id;
	$("#pageNo").val(pageNo);
}
function updateOrgMember(){
	var id = $("#uptid").val();
	var pageNo = $("#pageNo").val();
	var memberdesc = $("#uptmemberDesc").val();
	$.ajax({
		url:CONTEXTPATH+"/file/filemyuser/updateOrgMember",
		data:{"id":id,
			"memberdesc":memberdesc
		},
		type:"post",
		success:function(ret){
			type = ret.code;
			if(ret.code!="000000"){
				alert(ret.error);
			}else{
				displayData(parseInt(pageNo));
				document.getElementById("updateclick").click();
			}
		}
	});
}
/**
 * 新增
 */
function saveorgMember(){
	var cellPhone = $("#addcellphone").val();
	var massage =$("#massage").val();
	var memberName = $("#memberName").val();
	if(cellPhone == "" || memberName == ""){
		alert("数据不能为空");
		return;
	}
	$.ajax({
		url:CONTEXTPATH+"/file/filemyuser/saveOrgMember",
		data:{"cellPhone":cellPhone,
			"memberName":memberName,
			"massage":massage
		},
		type:"post",
		success:function(ret){
			type = ret.code;
			if(ret.code!="000000"){
				document.getElementById("error").innerHTML=ret.error;
			}else{
				document.getElementById("error").innerHTML="";
				searchMember();
				resetvalue();
				document.getElementById("addclick").click();
			}
		}
	});
}
/** 重置*/
function resetvalue(){
	$("#cellPhone").val("");
	$("#addcellphone").val("");
	$("#error").html("");
	$("#memberName").val("");
	$("#massage").val("");
	$("#membername").val("");
	displayData(0);
}
function searchPhone(){
	var phone = $("#addcellphone").val();
	$.ajax({
		url:CONTEXTPATH+"/file/filemyuser/findPhone",
		data:{"phone":phone
		},
		type:"post",
		success:function(ret){
			if(ret.code!="000000"){
				document.getElementById("error").innerHTML=ret.error;
			}else{
				document.getElementById("error").innerHTML="";
				var data = ret.data;
				var userName = data.userName;
				var companyName = data.companyName;
				$("#memberName").val(userName==undefined?companyName:userName);
//				searchMember();
//				document.getElementById("addclick").click();
			}
		}
	});
}
var data ;
/**
 * 查询列表
 */
function searchMember(){
	displayData(0);
}
function displayData(pageNo){
	var membername =$("#membername").val();
	var cellPhone = $("#cellPhone").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:"/file/fileOrgSigner/find",
		data:{
			"cellphone":cellPhone,
			"membername":membername,
			"pageNo":(pageNo),
			"pageSize":pageSize
		},
		type:"post",
		success:function(result){
			if(result.code=="000000"){
				data = result.data.list;
				$("#totalRecords").html(result.data.total);
		    	document.getElementById("goPage").value = pageNo+1;
		    	var hml = "";
			    if(result.data.total == 0) {
			    	$("#totalPages").html(1);  
			    		hml +='<td colspan="12">';
			    		hml +='<div class="ht_none">';
			    		hml +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
			    		hml +='<div>';
			    		hml +='<h3>唉呀！查询不到您要找的内容</h3>';
			    		hml +='<p>换个查询条件试试吧？</p>';
			    		hml +='</div>';
			    		hml +='</div>';
			    		hml +='</td>';
			    }else {
			    	$("#totalPages").html(parseInt((result.data.total-1)/pageSize+1)); 
			    	for (var a = 0; a < data.length; a++) {
						var d = a+1;
						hml +=
							"<tr><td>"+d+"</td>"+
							"<td>"+data[a].cellphone+"</td>"+
							"<td>"+data[a].membername+"</td>"+
							"<td>"+(data[a].remark || "")+"</td>"+
							"<td>"+new Date(data[a].createtime).format("yyyy-MM-dd hh:mm:ss")+"</td>"+
							"<td>"+data[a].creater+"</td>"+
							"<td>" +
							"<span><a data-toggle='modal' onclick=\"update('"+a+"')\" data-target='#edit'>【修改】</a></span>"+
							"<span><a data-toggle='modal' onclick=\"delect('"+data[a].id+"')\" data-target='#dele'>【删除】</a></span></td></tr>";
					}
			    }   
				document.getElementById("innerHml").innerHTML=hml;
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
 * 获取删除id
 * @param id
 */
function delect(id){
	document.getElementById("detid").value = id;
}

function deleteOrgMember(){
	var id = $("#detid").val();
	$.ajax({
		url:"/file/fileOrgSigner/delete",
		data:{"id":id
		},
		type:"post",
		success:function(ret){
			type = ret.code;
			if(ret.code!="000000"){
				alert(ret.error);
			}else{
				document.getElementById("detid").value = "";
				searchMember();
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
function update(a){
	var id = data[a].id;
	var membertel = data[a].cellphone;
	var memberdesc = data[a].remark;
	if(memberdesc == undefined){
		memberdesc = "";
	}
	document.getElementById("uptmemberTel").value=membertel;
	document.getElementById("uptmembername").value=data[a].membername;
	document.getElementById("uptmemberDesc").value=memberdesc;
	document.getElementById("uptid").value=id;
}
function updateOrgMember(){
	var id = $("#uptid").val();
	var memberdesc = $("#uptmemberDesc").val();
	$.ajax({
		url:"/file/fileOrgSigner/upt",
		data:{"id":id,
			"remark":memberdesc
		},
		type:"post",
		success:function(ret){
			type = ret.code;
			if(ret.code!="000000"){
				alert(ret.error);
			}else{
				searchMember();
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
	var memberid = $("#req_memberId").val();
	if(cellPhone == "" || memberName == ""){
		alert("数据不能为空");
		return;
	}
	$.ajax({
		url:"/file/fileOrgSigner/save",
		data:{"cellphone":cellPhone,
			"memberid":memberid,
			"membername":memberName,
			"remark":massage
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
		url:"/file/fileOrgSigner/findPhone",
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
				$("#req_memberId").val(data.id);
//				searchMember();
//				document.getElementById("addclick").click();
			}
		}
	});
}
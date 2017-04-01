var list ;
function searchFile(){
	displayData(0);
}
function displayData(pageNo){
	var searchKey = $("#searchKey").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+"/admin/waybill/findJTBBill",
		data:{
			"billNo":searchKey,
			"no":(pageNo),
			"size":pageSize
		},
		type:"post",
		success:function(ret){
			if(ret.code=="000000"){
				$("#totalRecords").html(ret.data.total);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(ret.data.total == 0) {
			    	$("#totalPages").html(1); 
			    }else {
			    	$("#totalPages").html(parseInt((ret.data.total-1)/pageSize+1));  
			    }   
				list = ret.data.list;
				if(ret.data.list ){
					innerHTML(ret.data.list);
				}else{
					var hml="";
					hml +='<td colspan="12">';
			    	hml +='<div class="ht_none">';
			    	hml +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
			    	hml +='<div>';
			    	hml +='<h3>唉呀！查询不到您要找的内容</h3>';
			    	hml +='<p>换个查询条件试试吧？</p	>';
			    	hml +='</div>';
			    	hml +='</div>';
			    	hml +='</td>';
			    	document.getElementById("innhml").innerHTML=hml;
				}
				$("#pagination").pagination(ret.data.total, {   
				    callback: pageCallback,   
				    prev_text: '上一页',   
				    next_text: '下一页',   
				    items_per_page:pageSize,   
				    num_display_entries:4,   
				    current_page:pageNo,   
				    num_edge_entries:1, 
				    maxentries: ret.data.total, 
				    link_to:"javascript:void(0)"
				}); 
			}
		}
	});
}

function innerHTML(data){
	var hml = "";
	for (var a = 0; a < data.length; a++) {
		var d = a+1;
		var createtimeStr = data[a].createtimeStr==undefined?"":data[a].createtimeStr;
		var jtb = "";
		if(data[a].jtb=="1"){
			jtb = "已推送";
		}else{
			jtb = "未推送";
		}
		hml +="<tr><td>"+d+"</td>"+
		"<td>"+data[a].waybillno+"</td>"+
		"<td>"+data[a].vehicleno+"</td>"+
		"<td>"+jtb+"</td>"+
		"<td>"+data[a].creatimeStr+"</td>"+
		"<td><span><a data-toggle='modal' onclick=\"details('"+data[a].id+"','"+data[a].waybillno+"')\" data-target='#detail'>【提交】</a></span>"+
		"</td>";
	}
	document.getElementById("innhml").innerHTML=hml;
}

function details(id,billno){
	$("#billId").val(id);
	$("#billNo").html(billno);
}

$("#putJtb").on("click",function(){
	var id = $("#billId").val();
	if(id==""){
		alert("网络异常请稍候再试");
	}
	$.ajax({
		url:"/admin/waybill/putJtbBill",
		data:{"id":id},
		type:"post",
		success:function(ret){
			if(ret.code=="000000"){
				$("#billId").val("");
				alert("操作成功");
				searchFile();
			}else{
				alert(ret.error);
			}
		}
	});
	
});



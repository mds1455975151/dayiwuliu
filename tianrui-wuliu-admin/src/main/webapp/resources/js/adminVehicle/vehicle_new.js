
function loadSearch(){
	displayRec(0);
}
function displayData(d){
	var recPage = $("#recPage").val();
	if(recPage==""){
		displayRec(d);
	}else{
		displayRec(recPage-1);
		$("#recPage").val("");
	}
}
function displayRec(pageNo){
	var pageSize=$("#pageSize").val();
	var vehicleno = $("#vehicleno").val();
	var ownerName = $("#ownerName").val();
	var vehiclemobile = $("#vehiclemobile").val();
	var authtype = $("#authtype").val();
	var status = $("#vehiclestatus").val();
	$.ajax({
		url:'/admin/fileVehicle/page',
		data:{"pageNo":(pageNo),
			"pageSize":pageSize,
			"vehicleno":vehicleno,
			"vehicleowner":ownerName,
			"vehiclemobile":vehiclemobile,
			"authtype":authtype
		},
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				alert("加载失败");
			}else{
				$("#totalRecords").html(ret.data.count);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(ret.data.count == 0) {
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
			    	$("#totalPages").html(parseInt((ret.data.count-1)/pageSize+1));  
			    	var d = ret.data.list;
			    	innerHtml(d);
			    }  
				
				$("#pagination").pagination(ret.data.count, {   
				    callback: pageCallback,   
				    prev_text: '上一页',   
				    next_text: '下一页',   
				    items_per_page:pageSize,   
				    num_display_entries:4,   
				    current_page:pageNo,   
				    num_edge_entries:1, 
				    maxentries: ret.data.count, 
				    link_to:"javascript:void(0)"
				}); 
			}
		}
	});  
}

function innerHtml(d){
	var hml = "";
	for (var a = 0; a < d.length; a++) {
		var c = a+1;
		var sta = "";
		if(d[a].status=="-1"){
			sta = "认证失败";
		}
		if(d[a].status=="0"){
			sta = "未认证";
		}
		if(d[a].status=="1"){
			sta = "认证成功";
		}
		if(d[a].status=="2"){
			sta = "认证中";
		}
		var type = "";
		if(d[a].vehicletypename != undefined){
			type = d[a].vehicletypename;
		}
		
		var userName = d[a].userName;
		if(d[a].userName == undefined){
			userName = "";
		}
		var telphone = d[a].telphone;
		if(d[a].telphone == undefined){
			telphone = "";
		}
		var ownerphone = d[a].ownerphone;
		var ownername = d[a].ownername;
		if(d[a].ownerphone == undefined){
			ownerphone = d[a].companytel;
			ownername = d[a].companyname;
		}
		var anlian = "";
		if(d[a].status=="1"){
			//"认证成功";
			anlian = "<span><a data-toggle='modal' onclick=\"anLianDetails('"+d[a].id+"','"+d[a].vehicleprefix+d[a].vehicleno+"')\" data-target='#anlian'>【完善信息】</a></span>";
			
			if(d[a].desc1=="-1"){
				anlian = "认证失败";
			}
			if(d[a].desc1=="0"){
				anlian = "<span><a data-toggle='modal' onclick=\"anLianDetails('"+d[a].id+"','"+d[a].vehicleprefix+d[a].vehicleno+"')\" data-target='#anlian'>【完善信息】</a></span>";
			}
			if(d[a].desc1=="1"){
				anlian = "<span><a data-toggle='modal' onclick=\"anLian_renzheng_detail('"+d[a].id+"','"+d[a].vehicleprefix+d[a].vehicleno+"')\" data-target='#anlian_detail'>【查看详情】</a></span>";
			}
			if(d[a].desc1=="2"){
				anlian = "认证中";
			}
		}
		
		hml += "<tr><td>"+c+"</td>"+
			"<td>"+d[a].vehicleno+"</td>"+
			"<td>"+d[a].vehiclemobile+"</td>"+
			"<td>"+d[a].vehicletype+"</td>"+
			"<td>"+d[a].vehicleowner+"</td>"+
			"<td>"+d[a].vehicleowneridcard+"</td>"+
			"<td>"+d[a].drivinglicenseno+"</td>"+
			"<td>"+d[a].vehicleload+"</td>"+
			"<td>"+d[a].authtype+"</td>"+
			"<td>"+d[a].authtype+"</td>"+
			"<td>"+d[a].createtime+"</td>"+
			"<td><span><a data-toggle='modal' data-target='#detail'>【详情】</a></span>";
			if(d[a].status=="2"){
				hml += "<span><a>【审核】</a></span>";
			}
			if(d[a].status=="1"){
				hml += "<span><a data-toggle='modal' data-target='#updateDeatil'>【修改】</a></span>";
			}
			hml += "</td></tr>";
	}
	document.getElementById("innerhml").innerHTML = hml;
}	
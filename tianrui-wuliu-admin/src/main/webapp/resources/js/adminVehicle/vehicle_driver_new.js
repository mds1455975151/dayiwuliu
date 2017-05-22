
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
		url:'/admin/vehicleDriver/new/find',
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
				$("#totalRecords").html(ret.data.total);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(ret.data.total == 0) {
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
			    	$("#totalPages").html(parseInt((ret.data.total-1)/pageSize+1));  
			    	var d = ret.data.list;
			    	innerHtml(d);
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

function innerHtml(d){
	var hml = "";
	for (var a = 0; a < d.length; a++) {
		var c = a+1;
		var checkstatus = "";
		if(d[a].checkstatus==0){
			checkstatus = "未选中";
		}else if(d[a].checkstatus==1){
			checkstatus = "已选中";
		}
		
		var authstats = "";
		if(d[a].authstats==0){
			authstats = "未审核";
		}else if(d[a].authstats==1){
			authstats = "审核成功";
		}else if(d[a].authstats==2){
			authstats = "审核中";
		}else if(d[a].authstats==3){
			authstats = "审核失败";
		}
		hml += "<tr><td>"+c+"</td>"+
			"<td>"+d[a].vehicleno+"</td>"+
			"<td>"+d[a].drivername+"</td>"+
			"<td>"+d[a].driverlinktel+"</td>"+
			"<td>"+d[a].driveridcard+"</td>"+
			"<td>"+d[a].drivercardfirstlicens+"</td>"+
			"<td>"+d[a].drivercardusefullife+"</td>"+
			"<td>"+checkstatus+"</td>"+
			"<td>"+authstats+"</td>"+
			"<td>"+new Date(d[a].authtime).format("yyyy-MM-dd hh:mm:ss")+"</td>"+
			"<td>"+new Date(d[a].createtime).format("yyyy-MM-dd hh:mm:ss")+"</td>"+
			"<td><span><a data-toggle='modal' onclick=\"driverDetail('"+d[a].id+"')\" data-target='#detail'>【详情】</a></span>";
			if(d[a].status=="2"){
				hml += "<span><a>【审核】</a></span>";
			}
			if(d[a].status=="1"){
				hml += "<span><a data-toggle='modal' data-target='#updateDeatil'>【修改】</a></span>";
			}
			hml += "</td></tr>";
	}
	$("#innerhml").html(hml);
}	

function driverDetail(id){
	$.ajax({
		url:"/admin/vehicleDriver/new/selectBykey",
		type:"post",
		data:{"id":id},
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				
				$("#driverName_mg").html(data.drivername);
				$("#driverCardFirstlicens_mg").html(data.drivercardfirstlicens);
				$("#driverSex_mg").html(data.driversex=="xx"?"女":"男");
				$("#driverCardLicenceorg_mg").html(data.drivercardlicenceorg);
				$("#driverBirthDate_mg").html(data.driverbirthdate);
				$("#driverCardRegDate_mg").html(data.drivercardregdate);
				$("#driverLinkTel_mg").html(data.driverlinktel);
				$("#driverCardUsefullife_mg").html(data.drivercardusefullife);
				$("#driverIdCard_mg").html(data.driveridcard);
				$("#driverCardType_mg").html(data.drivercardtype);
				$("#driverIdCardAddr_mg").html(data.driveridcardaddr);
				$("#driverCardImg_mg").html(data.drivercardimg==undefined?"未上传":"<a href='/imageView/index?imageUrl="+data.drivercardimg+"' target='_blank'>查看图片</a>");
			}
		}
	});
}

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
		var authType = "";//0:默认 1:完全  2:临时  3:开票
	    if(d[a].authtype=="0"){
	    	authType="默认";
	    }else if(d[a].authtype=="1"){
	    	authType="完全认证";
	    }else if(d[a].authtype=="2"){
	    	authType="临时认证";
	    }else if(d[a].authtype=="3"){
	    	authType="开票认证";
	    }
	    
		var vehiclesource = "";//1历史数据导入,2用户添加'
		if(d[a].vehiclesource=="1"){
			vehiclesource ="历史数据导入";
		}else if(d[a].vehiclesource=="2"){
			vehiclesource ="用户添加";
		}
		hml += "<tr><td>"+c+"</td>"+
			"<td>"+d[a].vehicleno+"</td>"+
			"<td>"+d[a].vehiclemobile+"</td>"+
			"<td>"+checkVehicleType(d[a].vehicletype)+"</td>"+
			"<td>"+d[a].vehicleowner+"</td>"+
			"<td>"+d[a].vehicleload+"</td>"+
			"<td>"+authType+"</td>"+
			"<td>"+vehiclesource+"</td>"+
			"<td>"+new Date(d[a].createtime).format("yyyy-MM-dd hh:mm:ss")+"</td>"+
			"<td><span><a data-toggle='modal' onclick=\"detail_('"+d[a].id+"')\" data-target='#detail'>【详情】</a></span>";
			if(d[a].status=="2"){
				hml += "<span><a>【审核】</a></span>";
			}
			hml += "</td></tr>";
	}
	$("#innerhml").html(hml);
}	

function detail_(id){
	$.ajax({
		url:"/admin/fileVehicle/selectId",
		type:"post",
		data:{"id":id},
		success:function(ret){
			if(ret.code==000000){
				var data = ret.data;
				$("#vehicleNo_mg").html(data.vehicleno);
				$("#vehicleOwnerTel_mg").html(data.vehicleowner);
				$("#vehicleType_mg").html(checkVehicleType(data.vehicletype));
				$("#vehicleLoad_mg").html(data.vehicleload);
				$("#vehicleLen_mg").html(data.vehiclelen);
				$("#createTime_mg").html(new Date(data.createtime).format("yyyy-MM-dd hh:mm:ss"));
				$("#vehicleMobile_mg").html(data.vehiclemobile);
			}
		}
	});
}

/** 车辆类型处理*/
function checkVehicleType(type){
	switch (type) {
	case "1":
		return "厢式"
		break;
	case "2":
		return "板车"
		break;
	case "3":
		return "冷藏"
		break;
	case "4":
		return "散装罐车"
		break;
	case "5":
		return "半挂车"
		break;
	case "6":
		return "重型自卸货车"
		break;
	case "7":
		return "轻型自卸货车"
		break;
	case "8":
		return "三轮农用运输"
		break;
	case "9":
		return "四轮农用普通货车"
		break;
	case "10":
		return "四轮农用自卸车"
		break;
	case "11":
		return "小型轮式拖拉机"
		break;
	case "12":
		return "大型轮式拖拉机"
		break;

	default:
		return "暂无此类型"
		break;
	}
}
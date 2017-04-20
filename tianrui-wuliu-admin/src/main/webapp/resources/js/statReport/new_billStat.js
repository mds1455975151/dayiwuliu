var pageSize = 10
function displayData(pageNo){
	$.ajax({
		url:"/report/find",
		type:"post",
		data:{"pageNo":pageNo,
				"pageSize":pageSize
		},
		success:function(ret){
			if(ret.code=="000000"){
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
				    	document.getElementById("innerHml").innerHTML=hml;
					}
					$("#pagination").pagination(100, {   
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
		}
	});
}

function innerHTML(data){
	var hml = "";
	for (var a = 0; a < data.length; a++) {
		var pickupweight = "";
		if(data[a].pickupweight != undefined){
			pickupweight = data[a].pickupweight;
		}
		var signweight = "";
		if(data[a].signweight != undefined){
			signweight = data[a].signweight;
		}
		var trueweight = "";
		if(data[a].trueweight != undefined){
			trueweight = data[a].trueweight;
		}
		var status = "";
		switch (data[a].status) {
		case -1:
			status = "车主回收";
			break;
		case 0:
			status = "司机未确认";
			break;
		case 1:
			status = "司机已接受";
			break;	
		case 2:
			status = "司机已装货";
			break;
		case 3:
			status = "司机运输中";
			break;
		case 4:
			status = "司机已到达";
			break;
		case 5:
			status = "司机已卸货";
			break;	
		case 6:
			status = "已签收";
			break;
		case 7:
			status = "司机拒绝接单";
			break;	
		default:
			status = "安联运单";
			break;
		}
		hml +="<tr>" +
				"<td>"+a+"</td>" +
				"<td>"+data[a].type+"</td>" +
				"<td>"+data[a].createtimeStr+"</td>" +
				"<td>"+data[a].plancode+"</td>" +
				"<td>"+data[a].waybillno+"</td>" +
				"<td>"+data[a].shippermerchantname+"</td>" +
				"<td>"+data[a].consigneemerchantname+"</td>" +
				"<td>"+data[a].remarkname+"</td>" +
				"<td>"+data[a].vehicleno+"</td>" +
				"<td>"+data[a].drivername+"</td>" +
				"<td>"+data[a].cargoname+"</td>" +
				"<td>"+data[a].begintimeStr+"</td>" +
				"<td>"+data[a].unloadtimeStr+"</td>" +
				"<td>"+pickupweight+"</td>" +
				"<td>"+data[a].routename+"</td>" +
				"<td>"+data[a].weight+"</td>" +
				"<td>"+signweight+"</td>" +
				"<td>"+trueweight+"</td>" +
				"<td>"+status+"</td>" +
				"</tr>";
	}
	$("#innerHml").html(hml);
}

$(".exportReport").on("click",function(){
	$.ajax({
		type:"get",
		data:{},
		url:"",
		success:function(ret){
			
		}
	});
});



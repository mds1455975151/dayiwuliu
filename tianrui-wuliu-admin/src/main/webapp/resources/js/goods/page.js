function driverSearch(){
	init(0);
}
function displayData(pageNo){
	var page = $("#recPageNo").val();
	if(page != ""){
		init(page-1);
		$("#recPageNo").val("");
	}else{
		init(pageNo);
	}
}
function reset(){
	$("#vehicleNo").val("");
	init(0);
}
function getParams(pageNo){
	var params = {pageNo:pageNo,
			pageSize:10,
			vehicleNo:$("#vehicleNo").val()}
	return params;
}

function init(pageNo){
	$.ajax({
		url:"/adminGoods/select",
		type:"POST",
		data:getParams(pageNo),
		success:function(ret){
			if(ret.code == '000000'){
				var data = ret.data.list;
				var total = ret.data.total;
				var pageNo = ret.data.pageNo;
				var pageSize = ret.data.pageSize;
				innerHml(data);
				$('#totalRecords').html(total);
				document.getElementById("goPage").value = pageNo+1;
				$("#totalPages").html(parseInt((total-1)/pageSize+1));
				$('#totalPages').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
				$("#pagination").pagination(total, {
				    callback: pageCallback,
				    prev_text: '上一页',
				    next_text: '下一页',
				    items_per_page:pageSize,
				    num_display_entries:4,
				    current_page:pageNo,
				    num_edge_entries:1,
				    maxentries:total,
				    link_to:"javascript:void(0)"
				});
			}
		}
	});
}

function innerHml(data){
	$("#innerHtml").empty();
	for (var a = 0; a < data.length; a++) {
		var pay = "";
		if(data[a].payment == "1"){
			pay = "司机";
		}else if(data[a].payment == "2"){
			pay = "车主";
		}
		var status = "";
		//0 待审核；1-审核通过；2-发单中；3-已完成  4-已关闭 9-审核失败'
		if(data[a].status == "0"){
			status = "待审核";
		}else if(data[a].status == "1"){
			status = "审核通过";
		}else if(data[a].status == "2"){
			status = "发单中";
		}else if(data[a].status == "3"){
			status = "已完成";
		}else if(data[a].status == "4"){
			status = "已关闭";
		}else if(data[a].status == "9"){
			status = "审核失败";
		}
		var hml = "<tr onclick=\"showPage('"+data[a].id+"')\">" +
				"<td>"+(a+1)+"</td>" +
				"<td>"+(data[a].plancode||"")+"</td>" +
				"<td>"+status+"</td>" +
				"<td>"+(data[a].freightname||"")+"</td>" +
				"<td>"+(data[a].cargoname||"")+"</td>" +
				"<td>"+(data[a].startcity||"")+"</td>" +
				"<td>"+(data[a].endcity||"")+"</td>" +
				"<td>"+(data[a].totalplanned||"0")+data[a].measure+"</td>" +
				"<td>"+(data[a].completed||"0")+data[a].measure+"</td>" +
				"<td>"+(data[a].price||"")+data[a].priceunits+"</td>" +
				"<td>"+(data[a].sendperson+data[a].sendpersonphone||"")+"</td>" +
				"<td>"+(data[a].receiveperson+data[a].receivepersonphone||"")+"</td>" +
				"<td>"+pay+"</td>" +
				"<td>"+new Date(data[a].createtime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
				"</tr>";
		$("#innerHtml").append(hml);
		
	}
}

function showPage(id){
	window.location.href="/adminGoods/tration?id="+id+"&menuId="+$("#menuId").val();
}

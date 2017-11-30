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
	init(0);
	$("#billNo").val(""),
	$("#cellphone").val(""),
	$("#vehicleNo").val(""),
	$("#timexh").val("")
}
function getParams(pageNo){
	var params = {pageNo:pageNo,
			waybillno:$("#billNo").val(),
			cellphone:$("#cellphone").val(),
			useryhno:$("#vehicleNo").val(),
			createtime:$("#timexh").val(),
			pageSize:10}
	return params;
}

function init(pageNo){
	$.ajax({
		url:"/admin/money/pendingBillMoneySelect",
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
		var hml = "<tr>" +
				"<td>"+(a+1)+"</td>" +
				"<td>"+(data[a].username||"")+"</td>" +
				"<td>"+(data[a].cellphone||"")+"</td>" +
				"<td>"+(data[a].useryhno||"")+"</td>" +
				"<td>"+new Date(data[a].createtime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
				"<td>"+((data[a].pendingmoney/100).toFixed(2)||"")+"</td>" +
				"<td>"+(data[a].waybillno||"")+"</td>" +
				"</tr>";
		$("#innerHtml").append(hml);
		
	}
}

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
		url:"/access/log/find",
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
				"<td>"+(data[a].systemUrl||"")+"</td>" +
				"<td>"+(data[a].systemToken||"")+"</td>" +
				"<td>"+(data[a].vehicleNo||"")+"</td>" +
				"<td>"+(data[a].beginTime||"")+"</td>" +
				"<td>"+(data[a].endTime||"")+"</td>" +
				"<td>"+(data[a].respCode||"")+"</td>" +
				"<td>"+(data[a].respError||"")+"</td>" +
				"<td>"+(data[a].respData||"")+"</td>" +
				"<td>"+(data[a].respTotal||"")+"</td>" +
				"<td>"+new Date(data[a].createtime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
				"</tr>";
		$("#innerHtml").append(hml);
		
	}
}

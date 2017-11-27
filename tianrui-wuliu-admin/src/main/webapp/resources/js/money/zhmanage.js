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
	var type = $(".bag_tab").find(".select").attr("type");
	var params = {pageNo:pageNo,
			pageSize:10,
			transactionstate:type}
	return params;
}
function init(pageNo){
	$.ajax({
		url:"/admin/money/getCapitalAccount",
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
	$(".tbshow").hide();
	var type = $(".bag_tab").find(".select").attr("type");
	
	if(type == 2){
		$(".type_2").show();
	}else if(type == 3){
		$(".type_3").show();
	}
	for (var a = 0; a < data.length; a++) {
		var hml = "<tr>" +
			"<td>"+(a+1)+"</td>" +
			"<td>"+(data[a].username||"")+"</td>" +
			"<td>"+(data[a].cellphone||"")+"</td>" +
			"<td>"+(data[a].useryhno||"")+"</td>" +
			"<td>"+new Date(data[a].begintime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
			"<td>"+(data[a].capitalno||"")+"</td>" +
			"<td>"+((data[a].money/100).toFixed(2)||"")+"</td>" +
			"<td>"+(data[a].expectpaycompany||"")+"</td>" +
			"<td>"+(data[a].bankname||"")+"</td>" +
			"<td>"+(data[a].bankcodeno||"")+"</td>" +
			"<td>"+((data[a].availablemoney/100).toFixed(2)||"")+"</td>" +
			"</tr>";
			$("#innerHtml").append(hml);
	}
}

$(".withdrawststus").on("click",function(){
	$(".withdrawststus").removeClass("select");
	$(this).addClass("select");
	init(0);
});

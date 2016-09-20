var pageNo = 1;
var pageSize = 10;

$(function(){
	$("#paymain").addClass("selected");
	index(1,0);
});

function index(No,flag){
	$.ajax({
		url : "/trwuliu/payInvoice/page",//
		data : {"pageNo":No,
			"pageSize":pageSize},
		type : "post",
		success : function(rs){
			if(rs.code=="000000"){
				innerHTML(rs.data,flag)
			}else{
				alert(rs.error);
			}
		}
	});
}

function moreSearch(){
	var no = pageNo + 1;
	index(no,1);
}

function innerHTML(ret,flag){
	var data = ret.list;
	pageNo = ret.pageNo;
	var total = ret.total;
	if(pageNo * pageSize > total){
		$("#moredate").hide();
	}else{
		$("#moredate").show();
	}
	var hml = "";
	if(flag == 0){
		$("#paylist").empty();
	}
	for (var a = 0; a < data.length; a++) {
		var df = data[a].payDealPrice - data[a].paidPrice;
		hml += "<tr data-toggle='modal' data-target='#fp_dtail'>" +
				"<td >"+data[a].applyDate+"</td>" +
				"<td >"+data[a].invoiceType+" </td>" +
				"<td >"+data[a].applyDate+"</td>" +
				"<td >"+data[a].payDealPrice+"</td>" +
				"<td >"+data[a].paidPrice+"元</td>" +
				"<td >"+df+"</td>" +
				"<td>"+data[a].adviceStatus+"</td></tr>";
	}
	$("#paylist").append(hml);
}
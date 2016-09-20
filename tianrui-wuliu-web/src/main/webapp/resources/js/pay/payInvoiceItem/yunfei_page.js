var pageNo = 1;
var pageSize = 10;

$(function(){
	$("#yunfei").addClass("selected");
	index(1,0);
});

function index(No,flag){
	$.ajax({
		url : "/trwuliu/payInvoiceItem/page",//
		data : {"pageNo":No,
			"pageSize":pageSize},
		type : "post",
		success : function(rs){
			if(rs.code=="000000"){
				innerHTML(rs.data,flag);
			}else{
				alert(rs.error);
			}
		}
	});
}
//查看更多
function moreSearch(){
	var no = pageNo + 1;
	index(no,1);
}
function innerHTML(ret,flag){
	var data = ret.list;
	var hml = "";
	var total = ret.total;
	pageNo = ret.pageNo;
	if(pageNo * pageSize > total){
		$("#moredate").hide();
	}else{
		$("#moredate").show();
	}
	if(flag == 0){
		$("#yunfeilist").empty();
	}
	for (var a = 0; a < data.length; a++) {
		var piao = "";
		var ao = "";
		if(data[a].isInvoice == 0){
			//未开发票
			ao = "未开票";
			piao = "<input type='checkbox'>";
		}else{
			//已开发票
			ao = "已开票";
			piao = "已开发票";
		}
		hml += "<tr><td >"+piao+"</td>" +
				"<td >"+data[a].billCode+"</td>" +
				"<td >"+data[a].cargoName+"</td>" +
				"<td >"+data[a].signTime+"</td>" +
				"<td >"+data[a].billWeight+"吨</td>" +
				"<td >"+data[a].billPrice+"元</td>" +
				"<td >"+data[a].taxRate+"%</td>" +
				"<td >"+data[a].billTotalPrice+"元</td>" +
				"<td >"+ao+"</td>" +
				"</tr>";
		
	}
	$("#yunfeilist").append(hml);
}
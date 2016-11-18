var pageNo = 1;
var pageSize = 10;

$(function(){
	$("#driverpay").addClass("selected");
	index(1,0);
});

function index(No,flag){
	var billcode = $("#billcode").val();
	var signtime = $("#signtime").val();
	var cargoName = $("#cargoName").val();
	var invoiceType = $("#invoiceType").val();
	var isvoid = $("#isvoid").val();
	var ownername = $("#ownername").val();
	$.ajax({
		url : "/trwuliu/payInvoiceDriver/page",//
		data : {
			"billNO":billcode,
			"signTime":signtime,
			"cargoName":cargoName,
			"isInvoice":isvoid,
			"ownername":ownername,
			"pageNo":No,
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
	if(total == 0){
		return;
	}
	for (var a = 0; a < data.length; a++) {
		hml += "<tr><td >"+data[a].billCode+"</td>" +
				"<td >"+data[a].ownername+"</td>" +
				"<td >"+data[a].cargoName+"</td>" +
				"<td >"+data[a].signTime+"</td>" +
				"<td >"+data[a].billWeight+"吨</td>" +
				"<td >"+data[a].billTotalPrice+"元</td>" +
				"<td >"+ao+"</td>" +
				"</tr>";
		
	}
	$("#yunfeilist").append(hml);
}
//全选-反选
$("#allcheck").click(function(){
	if($("#allcheck").is(':checked')){
		$("[name = 'ckbox']:checkbox").prop("checked", true);
	}else{
		$("[name = 'ckbox']:checkbox").prop("checked", false);
	}
	
});


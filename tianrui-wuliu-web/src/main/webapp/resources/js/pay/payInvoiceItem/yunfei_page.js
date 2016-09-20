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

function innerHTML(data,flag){
	var hml = "";
	if(flag == 0){
		$("#yunfeilist").empty();
	}
	for (var a = 0; a < data.length; a++) {
		hml += "<tr><td ><input type='checkbox'></td>" +
				"<td >4545121231 </td>" +
				"<td >这是散装的水泥啊 </td>" +
				"<td >2016-06-09</td>" +
				"<td >45吨</td>" +
				"<td >20.6元</td>" +
				"<td >20%</td>" +
				"<td >20.6元</td>" +
				"<td >已开票</td>" +
				"</tr>";
		
	}
	$("#yunfeilist").append(hml);
}
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

function innerHTML(data,flag){
	var hml = "";
	if(flag == 0){
		$("#paylist").empty();
	}
	for (var a = 0; a < data.length; a++) {
		hml += "<tr data-toggle='modal' data-target='#fp_dtail'>" +
				"<td >t545415</td>" +
				"<td >psjwkej水泥 </td>" +
				"<td >2016-03-05 </td>" +
				"<td >2000万</td>" +
				"<td >20.6元</td>" +
				"<td >2000万</td>" +
				"<td>未完成</td></tr>";
	}
	$("#paylist").append(hml);
}
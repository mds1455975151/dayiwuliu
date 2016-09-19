$(function(){
	$("#yunfei").addClass("selected");
	index();
});

function index(){
	$.ajax({
		url : "/trwuliu/payInvoiceItem/page",
		data : {},
		type : "post",
		success : function(rs){
			if(rs.code=="000000"){

			}else{
				alert(rs.error);
			}
		}
	});
}
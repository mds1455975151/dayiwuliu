$(function(){
	$("#paymain").addClass("selected");
	index();
});

function index(){
	$.ajax({
		url : "/trwuliu/payInvoice/page",
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
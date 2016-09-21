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
			piao = "<input name='ckbox' value="+data[a].id+" type='checkbox'>";
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
//全选-反选
$("#allcheck").click(function(){
	if($("#allcheck").is(':checked')){
		$("[name = 'ckbox']:checkbox").prop("checked", true);
	}else{
		$("[name = 'ckbox']:checkbox").prop("checked", false);
	}
	
});
//申请支付发票账单
function checkdetail(){
	$(".fapiao_body").empty();
	 var ids="";  
	 var a = 0;
     $("#yunfeilist input[type=checkbox]:checked").each(function() {  
    	 a += 1;
    	 ids += $(this).val()+";";
     }); 
    
     $.ajax({
 		url : "/trwuliu/payInvoiceItem/calculated",//
 		data : {"ids":ids},
 		type : "post",
 		success : function(rs){
 			if(rs.code=="000000"){
 			}else{
 			}
 		}
 	});
     var hml = "<div class='fapiao_dt'>" +
	     			"<h4>数量："+a+"单</h4>" +
	     		"</div><div class='fapiao_dt'>" +
	     			"<h4>总价：100万</h4>" +
	     		"</div>" +
	     		"<div class='fapiao_dt'>" +
	     			"<h4>到货量：100万吨</h4>" +
	     		"</div>" +
	     		"<div class='fapiao_dt'>" +
	     			"<h4>货物名称：ps2.5水泥</h4>" +
	     		"</div>" +
	     		"<div class='fapiao_dt'>" +
	     			"<h4>含税价：1000元</h4>" +
	     		"</div>" +
	     		"<div class='fapiao_dt'>" +
	     			"<h4>税率：51%</h4>" +
	     		"</div>" +
	     		"<div class='fapiao_dt'>" +
	     			"<h4>账单总价：1000元</h4>" +
	     		"</div>";
     $(".fapiao_body").append(hml);
}
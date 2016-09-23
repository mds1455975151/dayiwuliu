var pageNo = 1;
var pageSize = 10;

$(function(){
	$("#yunfei").addClass("selected");
	index(1,0);
});

function index(No,flag){
	var billcode = $("#billcode").val();
	var signtime = $("#signtime").val();
	var cargoName = $("#cargoName").val();
	var isvoid = $("#isvoid").val();
	$.ajax({
		url : "/trwuliu/payInvoiceItem/page",//
		data : {
			"billNO":billcode,
			"signTime":signtime,
			"cargoName":cargoName,
			"isInvoice":isvoid,
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
     if(a==0){
    	 alert("请选择数据");
    	 return;
     }
     if(a>20){
    	 alert("最多不能超过20条数据");
    	 return;
     }
     return ids;
}
//插入一条记录
function saveDetail(){
	var ids = checkdetail();
	 $.ajax({
	 		url : "/trwuliu/payInvoiceItem/calculated",//
	 		data : {"ids":ids},
	 		type : "post",
	 		success : function(rs){
	 			if(rs.code=="000000"){
	 				index(1,0);
	 			}else{
	 				alert(rs.error);
	 			}
	 		}
	 	});
	     
}
//获取选中数据
function selectids(){
	var ids = checkdetail();
	$.ajax({
 		url : "/trwuliu/payInvoiceItem/selectIds",//
 		data : {"ids":ids},
 		type : "post",
 		success : function(rs){
 			if(rs.code=="000000"){
 				innerDetail(rs.data);
 			}else{
 				alert(rs.error);
 			}
 		}
 	});
}
//生成结算单
function innerDetail(ret){
	$("#showmodal").click();
	var data = ret.list
	var price = 0;
	var totprice = 0;
	var weight = 0;
	var taxRate ;
	var flag = true;
	for (var a = 0; a < data.length; a++) {
		if(data[0].bill_price != data[a].bill_price){
			flag = false;
		}
		taxRate = data[a].taxRate;
		price = Number(price + data[a].billPrice);
		totprice = Number(totprice + data[a].billTotalPrice);
		weight = Number(weight + data[a].billWeight);
	}
	if(!flag){
		alert("税率不同无法计算。");
		return;
	}
	var hml = "<div class='fapiao_dt'>" +
				"<h4>数量："+data.length+"单</h4>" +
			"</div><div class='fapiao_dt'>" +
				"<h4>总价："+price+"元</h4>" +
			"</div>" +
			"<div class='fapiao_dt'>" +
				"<h4>到货量："+weight+"万吨</h4>" +
			"</div>" +
			"<div class='fapiao_dt'>" +
				"<h4>货物名称：ps2.5水泥</h4>" +
			"</div>" +
			"<div class='fapiao_dt'>" +
				"<h4>含税价："+totprice+"元</h4>" +
			"</div>" +
			"<div class='fapiao_dt'>" +
				"<h4>税率："+taxRate+"%</h4>" +
			"</div>";
		$(".fapiao_body").append(hml);
}
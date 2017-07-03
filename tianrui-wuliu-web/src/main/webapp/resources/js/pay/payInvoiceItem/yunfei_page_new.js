var pageNo = 1;
var pageSize = 10;

$(function(){
	$("#yunfei").addClass("selected");
	index(0,0);
});

function index(No,flag){
	var billcode = $("#billcode").val();
	var signtime = $("#signtime").val();
	var cargoName = $("#cargoName").val();
	var invoiceType = $("#invoiceType").val();
	var isvoid = $("#isvoid").val();
	var ownername = $("#ownername").val();
	$.ajax({
		url : "/trwuliu/payInvoiceItem_1/page",//
		data : {
			"searchKey":$("#searchKey").val(),
			"billType":2,
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
		
		var remark = "";
		if(data[a].remark == "dy"){
			remark = "大易运单";
		}else if(data[a].remark == "al"){
			remark = "安联运单";
		}
		var price = 0;
		if(data[a].backstageBillTotalPrice != 0){
			//后台已运价确认
			price = data[a].backstageBillTotalPrice
				-data[a].backstageDeductMoney
				-data[a].backstageDeductOilCard
				-data[a].backstageDeductOther
				-data[a].backstageDeductWeightMisc;
			
		}else {
			//后台未运价确认
			price = data[a].receptionBillTotalPrice 
				-data[a].receptionDeductMoney
				-data[a].receptionDeductOther
				-data[a].receptionDeductWeightMisc
				-data[a].receptionDeductOilCard;
		}
		
		var piao
		if(data[a].whetherClose == false){
			piao = "<input name='ckbox' value="+data[a].id+" type='checkbox'>";
		}else{
			piao = "已开票";
		}
		
		hml += "<tr><td >"+piao+"</td>" +
				"<td >"+data[a].billCode+"</td>" +
				"<td >"+remark+"</td>" +
				"<td >"+data[a].cargoName+"</td>" +
				"<td >"+data[a].invoiceName+"</td>" +
				"<td >"+new Date(data[a].createTime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
				"<td >"+data[a].billWeight+"吨</td>" +
				"<td >"+price+"元</td>" +
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
	 		url : "/trwuliu/payInvoiceItem_1/calculated",//
	 		data : {"idStr":ids},
	 		type : "post",
	 		success : function(rs){
	 			if(rs.code=="000000"){
	 				index(0,0);
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
 		url : "/trwuliu/payInvoiceItem_1/selectIds",//
 		data : {"idStr":ids},
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
	
	var data = ret.list
	var totprice = 0;
	var weight = 0;
	var invoiceTypename;
	var flag = true;
	for (var a = 0; a < data.length; a++) {
		if(data[0].invoiceCode != data[a].invoiceCode){
			flag = false;
		}
		invoiceTypename = data[a].invoiceName;
		
		var price = 0;
		if(data[a].backstageBillTotalPrice != 0){
			//后台已运价确认
			price = data[a].backstageBillTotalPrice
				-data[a].backstageDeductMoney
				-data[a].backstageDeductOilCard
				-data[a].backstageDeductOther
				-data[a].backstageDeductWeightMisc;
			
		}else {
			//后台未运价确认
			price = data[a].receptionBillTotalPrice 
				-data[a].receptionDeductMoney
				-data[a].receptionDeductOther
				-data[a].receptionDeductWeightMisc
				-data[a].receptionDeductOilCard;
		}
		
		totprice = Number(totprice + price);
		weight = Number(weight + data[a].billWeight);
	}
	if(!flag){
		alert("请选择同一货物类型");
		return;
	}
	$("#showmodal").click();
	$(".fapiao_body").empty();
	var hml = "<div class='fapiao_dt'>" +
				"<h4>数量："+data.length+"单</h4>" +
			"</div>" +
			"<div class='fapiao_dt'>" +
				"<h4>到货量："+weight+"吨</h4>" +
			"</div>" +
			"<div class='fapiao_dt'>" +
				"<h4>发票类型："+invoiceTypename+"</h4>" +
			"</div>" +
			"<div class='fapiao_dt'>" +
				"<h4>账单总价："+totprice+"元</h4>" +
			"</div>";
		$(".fapiao_body").append(hml);
}
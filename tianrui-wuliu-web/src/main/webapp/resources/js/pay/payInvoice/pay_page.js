var pageNo = 1;
var pageSize = 10;

$(function(){
	$("#paymain").addClass("selected");
	index(1,0);
});

function index(No,flag){
	var paycode = $("#paycode").val();
	var applytime = $("#applytime").val();
	var status = $("#paystatus").val();
	var paystatus ;
	var adviceStatus ;
	//审核状态 0未审核  1 已审核
	//发票单 0新建  1 已推单 2支付中  3支付完成 
	if(status ==""){
		
	}else if(status == 0){
		adviceStatus = 0;
	}else if(status == 1){
		adviceStatus = 1;
	}else if(status == 2){
		paystatus = 0;
	}else if(status == 3){
		paystatus = 1;
	}else if(status == 4){
		paystatus = 2;
	}else if(status == 5){
		paystatus = 3;
	}
	$.ajax({
		url : "/trwuliu/payInvoice/page",//
		data : {
			"paystatus":paystatus,
			"adviceStatus":adviceStatus,
			"paycode":paycode,
			"applytime":applytime,
			"paystatus":paystatus,
			"pageNo":No,
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
	var payStatus;//发票单 0新建  1 已推单 2支付中  3支付完成
	var adviceStatus //审核状态  0未审核  1 已审核
	var statusStr;
	var shenhe = "";
	for (var a = 0; a < data.length; a++) {
		if(data[a].payStatus == "0"){
			if(data[a].adviceStatus == "0"){
				statusStr = "未审核";
				shenhe = "<button onclick=\"payAudit('"+data[a].id+"')\" class='btn btnyello'>审核</button>"
			}else if(data[a].adviceStatus == "1"){
				statusStr = "已审核";
				shenhe = "<button onclick=\"payInvoiceSave('"+data[a].id+"')\" class='btn btnyello'>提交</button>"
			}
		}else if(data[a].payStatus == "1"){
			statusStr = "已推单";
		}else if(data[a].payStatus == "2"){
			statusStr = "支付中";
		}else if(data[a].payStatus == "3"){
			statusStr = "支付完成";
		}
		var df = data[a].payDealPrice - data[a].paidPrice;
		hml += "<tr >" +
				"<td data-toggle='modal' onclick=\"showdetail('"+data[a].id+"')\" data-target='#fp_dtail'>"+data[a].payCode+"</td>" +
				"<td >"+data[a].invoiceTypeName+" </td>" +
				"<td >"+data[a].applyDate+"</td>" +
				"<td >"+data[a].payDealPrice+"</td>" +
				"<td >"+data[a].paidPrice+"元</td>" +
				"<td >"+df+"</td>" +
				"<td>"+statusStr+"</td>" +
				"<td>"+shenhe+"</td>" +
						"</tr>";
	}
	$("#paylist").append(hml);
}
//查看账单详情
function showdetail(id){
	$.ajax({
		url : "/trwuliu/payInvoiceItem/page",//
		data : {
				"payId":id,
				"pageNo":1,
				"pageSize":25},
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
//账单详情页面展示
function innerDetail(ret){
	$("#paydetails").empty();
	var data = ret.list;
	var hml ;
	for (var a = 0; a < data.length; a++) {
		hml += "<tr><td >"+data[a].billCode+"</td>" +
			"<td >"+data[a].cargoName+"</td>" +
			"<td >"+data[a].signTime+"</td>" +
			"<td >"+data[a].billWeight+"吨</td>" +
			"<td >"+data[a].billPrice+"元</td>" +
			"<td >"+data[a].taxRate+"%</td>" +
			"<td >"+data[a].billTotalPrice+"元</td></tr>";
	}
	$("#paydetails").append(hml);
}
/** 运费单自审*/
function payAudit(id){
	$.ajax({
		url : "/trwuliu/payInvoice/payAudit",//
		data : {"id":id},
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
/** 运费单提交*/
function payInvoiceSave(id){
	$.ajax({
		url : "/trwuliu/payInvoice/payInvoiceSave",//
		data : {"id":id},
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

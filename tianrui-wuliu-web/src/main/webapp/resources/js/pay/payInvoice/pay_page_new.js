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
		url : "/trwuliu/payInvoice_1/page",//
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
	for (var a = 0; a < data.length; a++) {
		var shenhe = ""
		var pay_audit_push_Status = "";
		//先判断支付状态
		if(data[a].payStatus == "2"){
			pay_audit_push_Status = "支付完成";
		}else if(data[a].payStatus == "1"){
			pay_audit_push_Status = "支付中";
		}else if(data[a].pushStatus == "2"){
			pay_audit_push_Status = "已推送";
		}else if(data[a].pushStatus == "1"){
			pay_audit_push_Status = "推送中";
			shenhe = "<button onclick=\"pushBack('"+data[a].id+"')\" class='btn btnyello'>推送</button>"
		}else if(data[a].auditStatus == "2"){
			pay_audit_push_Status = "已审核";
			shenhe = "<button onclick=\"payPush('"+data[a].id+"')\" class='btn btnyello'>推送</button>"
		}else if(data[a].auditStatus == "0"){
			pay_audit_push_Status = "未审核";
			shenhe = "<button onclick=\"payAudit('"+data[a].id+"')\" class='btn btnyello'>审核</button>"
		}
		hml += "<tr >" +
				"<td data-toggle='modal' onclick=\"showdetail('"+data[a].id+"')\" data-target='#fp_dtail'>"+data[a].code+"</td>" +
				"<td >"+data[a].invoiceName+" </td>" +
				"<td >"+new Date(data[a].applicationTime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
				"<td >"+data[a].amountPayable+"</td>" +
				"<td >"+data[a].paidAmount+"元</td>" +
				"<td >"+(data[a].amountPayable-data[a].paidAmount)+"元</td>" +
				"<td>"+pay_audit_push_Status+"</td>" +
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
/**推送收回*/
function pushBack(id){
	$.ajax({
		url : "/trwuliu/payInvoice_1/pushBack",//
		data : {"id":id},
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

/** 账单推送*/
function payPush(id){
	$.ajax({
		url : "/trwuliu/payInvoice_1/payPush",//
		data : {"id":id},
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

/** 运费单自审*/
function payAudit(id){
	$.ajax({
		url : "/trwuliu/payInvoice_1/payAudit",//
		data : {"id":id},
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
/** 运费单提交*/
function payInvoiceSave(id){
	$(this).attr('disabled',true);
	$.ajax({
		url : "/trwuliu/payInvoice/payInvoiceSave",//
		data : {"id":id},
		type : "post",
		success : function(rs){
			if(rs.code=="000000"){
				index(1,0);
			}else{
//				alert("操作失败，请稍后再试...");
				alert(rs.error);
			}
		}
	});
}
/** 运单收回*/
function payInvoiceDelt(id){
	$.ajax({
		url : "/trwuliu/payInvoice/withdrawPay",//
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

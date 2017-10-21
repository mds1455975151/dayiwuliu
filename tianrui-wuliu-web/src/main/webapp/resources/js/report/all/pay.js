$(function(){
	init(0,0);
	count();
});

var noPage = 0;

function init(pageNo,type){
	noPage = pageNo;
	
	$.ajax({
		url:"/trwuliu/ReportAll/pay",
		type:"POST",
		data:getParment(pageNo),
		success:function(ret){
			if(ret.code == "000000"){
				innerHTML(ret.data.list,type);
				if(pageNo*10>=ret.data.total){
					$(".pageMore").hide();
				}
			}else{
				alert(ret.error);
			}
		}
	});
	count();
}
function count(){
	$.ajax({
		url:"/trwuliu/ReportAll/payCount",
		type:"POST",
		data:getParment(),
		success:function(ret){
			if(ret.code=='000000'){
				var data = ret.data;
				var trueWeightCount = parseFloat(data.trueWeightCount) == undefined ? "0.00":parseFloat(data.trueWeightCount);
				var priceCount = parseFloat(data.priceCount) == undefined ? "0.00":parseFloat(data.priceCount);
				var totalPriceCount = parseFloat(data.totalPriceCount) == undefined ? "0.00":parseFloat(data.totalPriceCount);
				var oilCardCount = parseFloat(data.oilCardCount) == undefined ? "0.00":parseFloat(data.oilCardCount);
				var weightMiscCount = parseFloat(data.weightMiscCount) == undefined ? "0.00":parseFloat(data.weightMiscCount);
				var deductMoneyCount = parseFloat(data.deductMoneyCount) == undefined ? "0.00":parseFloat(data.deductMoneyCount);
				var deductOtherCount = parseFloat(data.deductOtherCount) == undefined ? "0.00":parseFloat(data.deductOtherCount);
				var amountPayableCount = parseFloat(data.amountPayableCount) == undefined ? "0.00":parseFloat(data.amountPayableCount);
				var paidAmountCount = parseFloat(data.paidAmountCount) == undefined ? "0.00":parseFloat(data.paidAmountCount);
				$('#trueWeightCount').html(trueWeightCount.toFixed(2));
				$('#priceCount').html(priceCount.toFixed(2));
				$('#totalPriceCount').html(totalPriceCount.toFixed(2));
				$('#oilCardCount').html(oilCardCount.toFixed(2));
				$('#weightMiscCount').html(weightMiscCount.toFixed(2));
				$('#deductMoneyCount').html(deductMoneyCount.toFixed(2));
				$('#deductOtherCount').html(deductOtherCount.toFixed(2));
				$('#amountPayableCount').html(amountPayableCount.toFixed(2));
				$('#paidAmountCount').html(paidAmountCount.toFixed(2));
			}else{
				$('#trueWeightCount').html('0.00');
				$('#priceCount').html('0.00');
				$('#totalPriceCount').html('0.00');
				$('#oilCardCount').html('0.00');
				$('#weightMiscCount').html('0.00');
				$('#deductMoneyCount').html('0.00');
				$('#deductOtherCount').html('0.00');
				$('#amountPayableCount').html('0.00');
				$('#paidAmountCount').html('0.00');
			}
			
		}
	});
}
$('.exportReport').off('click').on('click',function(){
	$.ajax({
		url : '/trwuliu/ReportAll/pay',
		data : getParment(null),
		type : 'post',
		success : function(result) {
			if(result.code == '000000'){
				if(result.data.total == 0){
					alert("没有要导出的数据！");
				}else if(result.data.total > 2000){
					alert("数据超过50000条，请联系管理员导出！");
				}else{
					var path = '/trwuliu/ReportAll/payReport?'+$.param(getParment(1));
					$('<form method="post" action="' + path + '"></form>').appendTo('body').submit().remove();
				}
			}
		}
	});
});

$(".pageMore").on("click",function(){
	init(noPage+1,1);
});

function getParment(pageNo){
	return {
		pageNo:pageNo,
		pageSize:10,
		reportType:$("#reportType").val(),
		paystarttime:$("#paystarttime").val(),
		payendtime:$("#payendtime").val(),
		payCode:$("#payCode").val(),
		planCode:$("#planCode").val(),
		routeName:$("#routeName").val(),
		sendMan:$("#sendMan").val(),
		sendPersian:$("#sendPersian").val(),
		venderName:$("#venderName").val(),
		receiptMan:$("#receiptMan").val(),
		receiptPerson:$("#receiptPerson").val(),
		cargoName:$("#cargoName").val(),
		payStatus:$("#payStatus").val(),
		driverName:$("#driverName").val(),
		billstarttime:$("#billstarttime").val(),
		billendtime:$("#billendtime").val()
	}
}

function regist(){
	$("#paystarttime").val("");
	$("#payendtime").val("");
	$("#payCode").val("");
	$("#planCode").val("");
	$("#routeName").val("");
	$("#sendMan").val("");
	$("#sendPersian").val("");
	$("#venderName").val("");
	$("#receiptMan").val("");
	$("#receiptPerson").val("");
	$("#cargoName").val("");
	$("#payStatus").val("");
	$("#driverName").val("");
	$("#billstarttime").val("");
	$("#billendtime").val("");
	init(0,0);
}

function innerHTML(data,type){
	if(type == 0){
		$("#innerHml").empty();
	}
	for (var a = 0; a < data.length; a++) {
		appendHtml(data[a]);
	}
}
function appendHtml(data){
	var payMent = data.payMent;
	if(data.payMent == "1"){
		payMent = "司机";
	}else if(data.payMent == "2"){
		payMent = "车主";
	}
	var payStatus = data.payStatus;
	if(data.payStatus == "0"){
		payStatus = "未支付";
	}else if(data.payStatus == "1"){
		payStatus = "支付中";
	}else if(data.payStatus == "2"){
		payStatus = "已支付";
	}else if(data.payStatus == "3"){
		payStatus = "支付失败";
	}
	var hml = "<tr>" +
				"<td>"+(data.payCreateTimeStr||"")+"</td>" +
				"<td data-toggle='modal' onclick=\"showdetail('"+data.id+"')\" data-target='#fp_dtail'><a>"+(data.payCode||"")+"</a></td>" +
				"<td>"+(payMent||"")+"</td>" +
				"<td><a onclick=\"getPlanDatail('"+data.planCode+"')\">"+(data.planCode||"")+"</a></td>" +
				"<td>"+(data.routeName||"")+"</td>" +
				"<td>"+(data.sendMan||"")+"</td>" +
				"<td>"+(data.sendPersian||"")+"</td>" +
				"<td>"+(data.venderName||"")+"</td>" +
				"<td>"+(data.receiptMan||"")+"</td>" +
				"<td>"+(data.receiptPerson||"")+"</td>" +
				"<td>"+(data.vehicleNo||"")+"</td>" +
				"<td>"+(data.billTimeStr||"")+"</td>" +
				"<td>"+(data.billNo||"")+"</td>" +
				"<td>"+(data.cargoName||"")+"</td>" +
				"<td>"+(data.trueWeight||"")+"</td>" +
				"<td>"+(data.price||"")+"</td>" +
				"<td>"+(data.totalPrice||"")+"</td>" +
				"<td>"+(data.oilCard||"")+"</td>" +
				"<td>"+(data.weightMisc||"")+"</td>" +
				"<td>"+(data.deductMoney||"")+"</td>" +
				"<td>"+(data.deductOther||"")+"</td>" +
				"<td>"+(data.amountPayable||"")+"</td>" +
				"<td>"+(data.paidAmount||"")+"</td>" +
				"<td>"+(payStatus||"")+"</td>" +
				"<td>"+(data.payType||"")+"</td>" +
				"<td>"+(data.payPerson||"")+"</td>" +
				"<td>"+(data.payBankName||"")+"</td>" +
				"<td>"+(data.payBankCode||"")+"</td>" +
			"</tr>";
	$("#innerHml").append(hml);
}

function getPlanDatail(planCode){
	$.ajax({
		url:"/trwuliu/ReportAll/getPayPlanCode",
		type:"POST",
		data:{planCode:planCode},
		success:function(ret){
			var planid = ret.data;
			if(ret.data!=null){
				window.location.href="/trwuliu/planvender/detail?id="+planid;
			}else{
				alert("网络异常");
			}
		}
	});
}

//查看账单详情
function showdetail(id){
	$.ajax({
		url : "/trwuliu/payInvoiceItem_1/findAll",//
		data : {
				"payInvoiceId":id},
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
		hml += "<tr><td ><a onclick=\"getBillDetail('"+data[a].billId+"','"+data[a].remark+"')\">"+data[a].billCode+"</a></td>" +
			"<td >"+data[a].cargoName+"</td>" +
			"<td >"+data[a].invoiceName+"</td>" +
			"<td >"+data[a].billWeight+"吨</td>" +
			"<td >"+new Date(data[a].createTime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
			"<td >"+price+"元</td></tr>";
	}
	$("#paydetails").append(hml);
}

function getBillDetail(id,type){
	if(type=="dy"){
		window.location.href="/trwuliu/ReportAll/billDatailPage?id="+id;
	}else if(type=="al"){
		window.location.href="/trwuliu/billAnlian/detail?id="+id;
	}
}
$(function(){
	init(0,0);
});
function init(pageNo,type){
	$.ajax({
		url:"/trwuliu/ReportAll/pay",
		type:"POST",
		data:getParment(pageNo),
		success:function(ret){
			if(ret.code == "000000"){
				innerHTML(ret.data.list,type);
			}else{
				alert(ret.error);
			}
		}
	});
}

function getParment(pageNo){
	return {
		pageNo:pageNo,
		pageSize:10,
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
	var hml = "<tr>" +
				"<td>"+data.payCreateTime+"</td>" +
				"<td>"+data.payCode+"</td>" +
				"<td>"+data.payMent+"</td>" +
				"<td>"+data.planCode+"</td>" +
				"<td>"+data.routeName+"</td>" +
				"<td>"+data.sendMan+"</td>" +
				"<td>"+data.sendPersian+"</td>" +
				"<td>"+data.venderName+"</td>" +
				"<td>"+data.receiptMan+"</td>" +
				"<td>"+data.receiptPerson+"</td>" +
				"<td>"+data.vehicleNo+"</td>" +
				"<td>"+data.billTime+"</td>" +
				"<td>"+data.billNo+"</td>" +
				"<td>"+data.cargoName+"</td>" +
				"<td>"+data.trueWeight+"</td>" +
				"<td>"+data.price+"</td>" +
				"<td>"+data.totalPrice+"</td>" +
				"<td>"+data.oilCard+"</td>" +
				"<td>"+data.weightMisc+"</td>" +
				"<td>"+data.deductMoney+"</td>" +
				"<td>"+data.deductOther+"</td>" +
				"<td>"+data.amountPayable+"</td>" +
				"<td>"+data.paidAmount+"</td>" +
				"<td>"+data.payStatus+"</td>" +
				"<td>"+data.payType+"</td>" +
				"<td>"+data.payPerson+"</td>" +
				"<td>"+data.payBankName+"</td>" +
				"<td>"+data.payBankCode+"</td>" +
			"</tr>";
	$("#innerHml").append(hml);
}

$('.exportReport').off('click').on('click',function(){
	$.ajax({
		url : '/reportAll/pay',
		data : getParment(null),
		type : 'post',
		success : function(result) {
			if(result.code == '000000'){
				if(result.data.total == 0){
					alert("没有要导出的数据！");
				}else if(result.data.total > 2000){
					alert("数据超过2000条，请联系管理员导出！");
				}else{
					var path = '/reportAll/payReport?'+$.param(getParams(1));
					$('<form method="post" action="' + path + '"></form>').appendTo('body').submit().remove();
				}
			}
		}
	});
});
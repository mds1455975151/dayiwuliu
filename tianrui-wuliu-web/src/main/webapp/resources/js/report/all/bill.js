$(function(){
	init(0,0);
});
function init(pageNo,type){
	$.ajax({
		url:"/trwuliu/ReportAll/bill",
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
		reportType:$("#reportType").val(),
		businessTimeStart:$("#businessTimeStart").val(),
		businessTimeEnd:$("#businessTimeEnd").val(),
		billType:$("#billType").val(),
		planNo:$("#planNo").val(),
		billNo:$("#billNo").val(),
		cargoName:$("#cargoName").val(),
		vehicleNo:$("#vehicleNo").val(),
		routeName:$("#routeName").val(),
		billStatus:$("#billStatus").val(),
		driverName:$("#driverName").val(),
		payMent:$("#payMent").val(),
		sendMan:$("#sendMan").val(),
		sendPersion:$("#sendPersion").val(),
		receiptMan:$("#receiptMan").val(),
		receiptPersion:$("#receiptPersion").val(),
		acceptTimeStart:$("#acceptTimeStart").val(),
		acceptTimeEnd:$("#acceptTimeEnd").val(),
		pickupTimeStart:$("#pickupTimeStart").val(),
		pickupTimeEnd:$("#pickupTimeEnd").val(),
		unloadTimeStart:$("#unloadTimeStart").val(),
		unloadTimeEndtime:$("#unloadTimeEndtime").val(),
		signTimeStart:$("#signTimeStart").val(),
		signTimeEnd:$("#signTimeEnd").val(),
	}
}

function regist(){
	$("#businessTimeStart").val("");
	$("#businessTimeEnd").val("");
	$("#billType").val("");
	$("#planNo").val("");
	$("#billNo").val("");
	$("#cargoName").val("");
	$("#vehicleNo").val("");
	$("#routeName").val("");
	$("#billStatus").val("");
	$("#driverName").val("");
	$("#payMent").val("");
	$("#sendMan").val("");
	$("#sendPersion").val("");
	$("#receiptMan").val("");
	$("#receiptPersion").val("");
	$("#acceptTimeStart").val("");
	$("#acceptTimeEnd").val("");
	$("#pickupTimeStart").val("");
	$("#pickupTimeEnd").val("");
	$("#unloadTimeStart").val("");
	$("#unloadTimeEndtime").val("");
	$("#signTimeStart").val("");
	$("#signTimeEnd").val("");
	init(0,0);
}

function innerHTML(data,type){
	if(type==0){
		$("#innerHml").empty();
	}
	for (var a = 0; a < data.length; a++) {
		appendHtml(data[a]);
	}
}
function appendHtml(data){
	var hml = "<tr>" +
				"<td>"+data.billType+"</td>" +
				"<td>"+data.businessTime+"</td>" +
				"<td>"+data.planNo+"</td>" +
				"<td>"+data.billNo+"</td>" +
				"<td>"+data.sendMan+"</td>" +
				"<td>"+data.sendPersion+"</td>" +
				"<td>"+data.receiptMan+"</td>" +
				"<td>"+data.receiptPersion+"</td>" +
				"<td>"+data.vehicleNo+"</td>" +
				"<td>"+data.cargoName+"</td>" +
				"<td>"+data.routeName+"</td>" +
				"<td>"+data.distinct+"</td>" +
				"<td>"+data.venderWeight+"</td>" +
				"<td>"+data.pickupWeight+"</td>" +
				"<td>"+data.unloadWeight+"</td>" +
				"<td>"+data.trueWeight+"</td>" +
				"<td>"+data.billStatus+"</td>" +
				"<td>"+data.driverName+"</td>" +
				"<td>"+data.payMent+"</td>" +
				"<td>"+data.billCreaterTime+"</td>" +
				"<td>"+data.acceptTime+"</td>" +
				"<td>"+data.pickupTime+"</td>" +
				"<td>"+data.unloadTime+"</td>" +
				"<td>"+data.signTime+"</td>" +
			"</tr>";
	$("#innerHml").append(hml);
}
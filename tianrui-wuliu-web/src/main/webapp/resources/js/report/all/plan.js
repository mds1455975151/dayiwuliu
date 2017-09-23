$(function(){
	init(0,0);
});
function init(pageNo,type){
	$.ajax({
		url:"/trwuliu/ReportAll/plan",
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
		planStarttime:$("#planStarttime").val(),
		planEndtime:$("#planEndtime").val(),
		planStatus:$("#planStatus").val(),
		routeName:$("#routeName").val(),
		cargoName:$("#cargoName").val(),
		sendMan:$("#sendMan").val(),
		sendPersion:$("#sendPersion").val(),
		venderName:$("#venderName").val(),
		receiptMan:$("#receiptMan").val(),
		receiptPersion:$("#receiptPersion").val(),
		payMent:$("#payMent").val()
	}
}

function regist(){
	$("#planStarttime").val("");
	$("#planEndtime").val("");
	$("#planStatus").val("");
	$("#routeName").val("");
	$("#cargoName").val("");
	$("#sendMan").val("");
	$("#sendPersion").val("");
	$("#venderName").val("");
	$("#receiptMan").val("");
	$("#receiptPersion").val("");
	$("#payMent").val("");
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
				"<td>"+data.planCreateTime+"</td>" +
				"<td>"+data.planCode+"</td>" +
				"<td>"+data.planBeginTime+"</td>" +
				"<td>"+data.planEndTime+"</td>" +
				"<td>"+data.planWeight+"</td>" +
				"<td>"+data.complitWeight+"</td>" +
				"<td>"+data.tempo+"</td>" +
				"<td>"+data.planStatus+"</td>" +
				"<td>"+data.cargoName+"</td>" +
				"<td>"+data.routeName+"</td>" +
				"<td>"+data.sendMan+"</td>" +
				"<td>"+data.sendPersion+"</td>" +
				"<td>"+data.venderName+"</td>" +
				"<td>"+data.receiptMan+"</td>" +
				"<td>"+data.receiptPersion+"</td>" +
				"<td>"+data.distant+"</td>" +
				"<td>"+data.price+"</td>" +
				"<td>"+data.tax+"</td>" +
				"<td>"+data.payMent+"</td>" +
			"</tr>";
	$("#innerHml").append(hml);
}
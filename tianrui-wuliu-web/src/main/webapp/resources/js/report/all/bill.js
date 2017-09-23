$(function(){
	init();
});
function init(){
	$.ajax({
		url:"/trwuliu/ReportAll/bill",
		type:"POST",
		data:{},
		success:function(ret){
			if(ret.code == "000000"){
				innerHTML(ret.data.list);
			}else{
				alert(ret.error);
			}
		}
	});
}
function innerHTML(data){
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
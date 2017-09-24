$(function(){
	init(0,0);
});
var noPage = 0;
function init(pageNo,type){
	noPage = pageNo;
	$.ajax({
		url:"/trwuliu/ReportAll/plan",
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
}
$(".pageMore").on("click",function(){
	init(noPage+1,1);
});

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
	//0 新建；1-待接单；2-执行中；3-已完成
	var planStatus = "";
	if(data.planStatus == "0"){
		planStatus = "新建";
	}else if(data.planStatus == "1"){
		planStatus = "待接";
	}else if(data.planStatus == "2"){
		planStatus = "执行中";
	}else if(data.planStatus == "3"){
		planStatus = "已完成";
	}
	var payMent = "";
	if(data.payMent == "1"){
		payMent = "司机";
	}else if(data.payMent=="2"){
		payMent = "车主";
	}
	var hml = "<tr>" +
				"<td>"+(data.planCreateTimeStr||"")+"</td>" +
				"<td>"+(data.planCode||"")+"</td>" +
				"<td>"+(data.planBeginTimeStr||"")+"</td>" +
				"<td>"+(data.planEndTimeStr||"")+"</td>" +
				"<td>"+(data.planWeight||"")+"</td>" +
				"<td>"+(data.complitWeight||"")+"</td>" +
				"<td>"+(data.tempo||"")+"</td>" +
				"<td>"+(planStatus||"")+"</td>" +
				"<td>"+(data.cargoName||"")+"</td>" +
				"<td>"+(data.routeName||"")+"</td>" +
				"<td>"+(data.sendMan||"")+"</td>" +
				"<td>"+(data.sendPersion||"")+"</td>" +
				"<td>"+(data.venderName||"")+"</td>" +
				"<td>"+(data.receiptMan||"")+"</td>" +
				"<td>"+(data.receiptPersion||"")+"</td>" +
				"<td>"+(data.distant||"")+"</td>" +
				"<td>"+(data.price||"")+"</td>" +
				"<td>"+(data.tax||"")+"</td>" +
				"<td>"+(payMent||"")+"</td>" +
			"</tr>";
	$("#innerHml").append(hml);
}
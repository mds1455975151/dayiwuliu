$(function(){
	init(0,0);
});

var noPage = 0;
function init(pageNo,type){
	noPage = pageNo;
	$.ajax({
		url:"/trwuliu/ReportAll/bill",
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

$('.exportReport').off('click').on('click',function(){
	$.ajax({
		url : '/trwuliu/ReportAll/bill',
		data : getParment(null),
		type : 'post',
		success : function(result) {
			if(result.code == '000000'){
				if(result.data.total == 0){
					alert("没有要导出的数据！");
				}else if(result.data.total > 2000){
					alert("数据超过2000条，请联系管理员导出！");
				}else{
					var path = '/trwuliu/ReportAll/billReport?'+$.param(getParment(1));
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
	var billType = "";
	if(data.billType == "al"){
		billType = "安联运单";
	}else if(data.billType == "dy"){
		billType = "大易运单";
	}
	var payMent = "";
	if(data.payMent == "1"){
		payMent = "司机";
	}else if(data.payMent == "2"){
		payMent = "车主";
	}
	var hml = "<tr>" +
				"<td>"+(billType||"")+"</td>" +
				"<td>"+(data.businessTimeStr||"")+"</td>" +
				"<td>"+(data.planNo||"")+"</td>" +
				"<td>"+(data.billNo||"")+"</td>" +
				"<td>"+(data.sendMan||"")+"</td>" +
				"<td>"+(data.sendPersion||"")+"</td>" +
				"<td>"+(data.receiptMan||"")+"</td>" +
				"<td>"+(data.receiptPersion||"")+"</td>" +
				"<td>"+(data.vehicleNo||"")+"</td>" +
				"<td>"+(data.cargoName||"")+"</td>" +
				"<td>"+(data.routeName||"")+"</td>" +
				"<td>"+(data.distinct||"")+"</td>" +
				"<td>"+(data.venderWeight||"")+"</td>" +
				"<td>"+(data.pickupWeight||"")+"</td>" +
				"<td>"+(data.unloadWeight||"")+"</td>" +
				"<td>"+(data.trueWeight||"")+"</td>" +
				"<td>"+(getBillStatus(data.billStatus)||"")+"</td>" +
				"<td>"+(data.driverName||"")+"</td>" +
				"<td>"+(payMent||"")+"</td>" +
				"<td>"+(data.billCreaterTimeStr||"")+"</td>" +
				"<td>"+(data.acceptTimeStr||"")+"</td>" +
				"<td>"+(data.pickupTimeStr||"")+"</td>" +
				"<td>"+(data.unloadTimeStr||"")+"</td>" +
				"<td>"+(data.signTimeStr||"")+"</td>" +
			"</tr>";
	$("#innerHml").append(hml);
}

function getBillStatus(sta){
	var status = "";
	switch (sta) {
	case "-1":
		status = "车主回收";
		break;
	case "0":
		status = "司机未确认";
		break;
	case "1":
		status = "司机已接受";
		break;	
	case "2":
		status = "司机已装货";
		break;
	case "3":
		status = "司机运输中";
		break;
	case "4":
		status = "司机已到达";
		break;
	case "5":
		status = "司机已卸货";
		break;	
	case "6":
		status = "已签收";
		break;
	case "7":
		status = "司机拒绝接单";
		break;	
	default:
		status = sta;
		break;
	}
	return status;
}
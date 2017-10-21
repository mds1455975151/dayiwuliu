$(function(){
	init(0,0);
});
var noPage = 0;
function planCount(){
	$('#planWeightCount').html('0.00');
	$('#complitWeightCount').html('0.00');
	$('#tempoCount').html('0.00');
	$('#distantCount').html('0.00');
	$('#priceCount').html('0.00');
	$('#taxCount').html('0.00');
	$.ajax({
		url:"/trwuliu/ReportAll/planCount",
		type:"POST",
		data:getParment(),
		success:function(ret){
			var data = ret.data;
			if(ret.code=='000000'){
				var planWeightCount = parseFloat(data.planWeightCount) == undefined ? "0.00":parseFloat(data.planWeightCount);
				var complitWeightCount = parseFloat(data.complitWeightCount) == undefined ? "0.00":parseFloat(data.complitWeightCount);
				var tempoCount = parseFloat(data.tempoCount) == undefined ? "0.00":parseFloat(data.tempoCount);
				var distantCount = parseFloat(data.distantCount) == undefined ? "0.00":parseFloat(data.distantCount);
				var priceCount = parseFloat(data.priceCount) == undefined ? "0.00":parseFloat(data.priceCount);
				var taxCount = parseFloat(data.taxCount) == undefined ? "0.00":parseFloat(data.taxCount);
				$('#planWeightCount').html(planWeightCount.toFixed(2));
				$('#complitWeightCount').html(complitWeightCount.toFixed(2));
				$('#tempoCount').html(tempoCount.toFixed(2));
				$('#distantCount').html(distantCount.toFixed(2));
				$('#priceCount').html(priceCount.toFixed(2));
				$('#taxCount').html(taxCount.toFixed(2));
			}
		}
	});
}
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
	planCount();
}

$('.exportReport').off('click').on('click',function(){
	$.ajax({
		url : '/trwuliu/ReportAll/plan',
		data : getParment(null),
		type : 'post',
		success : function(result) {
			if(result.code == '000000'){
				if(result.data.total == 0){
					alert("没有要导出的数据！");
				}else if(result.data.total > 2000){
					alert("数据超过50000条，请联系管理员导出！");
				}else{
					var path = '/trwuliu/ReportAll/planReport?'+$.param(getParment(1));
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
	}else if(data.planStatus == "-1"){
		planStatus = "已删除";
	}
	var payMent = "";
	if(data.payMent == "1"){
		payMent = "司机";
	}else if(data.payMent=="2"){
		payMent = "车主";
	}
	var hml = "<tr>" +
				"<td>"+(data.planCreateTimeStr||"")+"</td>" +
				"<td><a href='/trwuliu/planvender/detail?id="+data.id+"'>"+(data.planCode||"")+"</a></td>" +
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
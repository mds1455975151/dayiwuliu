function displayData(pageNo){
	init(pageNo);
}

function getParams(pageNo){
	var planStarttime =$("#planStarttime").val();
	var planEndtime =$("#planEndtime").val();
	var planCode =$("#planCode").val();
	var planStatus =$("#planStatus").val();
	var routeName =$("#routeName").val();
	var cargoName =$("#cargoName").val();
	var sendMan =$("#sendMan").val();
	var sendPersion =$("#sendPersion").val();
	var venderName =$("#venderName").val();
	var receiptMan =$("#receiptMan").val();
	var receiptPersion =$("#receiptPersion").val();
	var payMent =$("#payMent").val();
	var params = {
			planStarttime:$.trim(planStarttime),
			planEndtime:$.trim(planEndtime),
			planCode:$.trim(planCode),
			planStatus:$.trim(planStatus),
			routeName:$.trim(routeName),
			cargoName:$.trim(cargoName),
			sendMan:$.trim(sendMan),
			sendPersion:$.trim(sendPersion),
			venderName:$.trim(venderName),
			receiptMan:$.trim(receiptMan),
			receiptPersion:$.trim(receiptPersion),
			payMent:$.trim(payMent),
			pageNo:pageNo,
			pageSize:10
		};
	return params;
}
function reset(){
	$("#planStarttime").val("");
	$("#planEndtime").val("");
	$("#planCode").val("");
	$("#planStatus").val("");
	$("#routeName").val("");
	$("#cargoName").val("");
	$("#sendMan").val("");
	$("#sendPersion").val("");
	$("#venderName").val("");
	$("#receiptMan").val("");
	$("#receiptPersion").val("");
	$("#payMent").val("");
	init(0);
}
function init(pageNo){
	$.ajax({
		url:"/reportAll/plan",
		type:"POST",
		data:getParams(pageNo),
		success:function(ret){
			if(ret.code == '000000'){
				var data = ret.data.list;
				var total = ret.data.total;
				var pageNo = ret.data.pageNo;
				var pageSize = ret.data.pageSize;
				innerHml(data);
				$('#totalRecords').html(total);
				$('#totalPages').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
				$("#pagination").pagination(total, {
				    callback: pageCallback,
				    prev_text: '上一页',
				    next_text: '下一页',
				    items_per_page:pageSize,
				    num_display_entries:4,
				    current_page:pageNo-1,
				    num_edge_entries:1,
				    maxentries:total,
				    link_to:"javascript:void(0)"
				});
			}
		}
	});
}

function innerHml(data){
	$("#innerHtml").empty();
	for (var a = 0; a < data.length; a++) {
		var planCreateTimeStr = data[a].planCreateTimeStr == undefined ? "":data[a].planCreateTimeStr;
		var planCode = data[a].planCode == undefined ? "":data[a].planCode;
		var planBeginTimeStr = data[a].planBeginTimeStr == undefined ? "":data[a].planBeginTimeStr;
		var planEndTimeStr = data[a].planEndTimeStr == undefined ? "":data[a].planEndTimeStr;
		var planWeight = data[a].planWeight == undefined ? "":data[a].planWeight;
		var complitWeight = data[a].complitWeight == undefined ? "":data[a].complitWeight;
		var tempo = data[a].tempo == undefined ? "":data[a].tempo;
		var planStatus = data[a].planStatus == undefined ? "":data[a].planStatus;
		if(planStatus=="-1"){
			planStatus="已删除"
		}
		if(planStatus=="0"){
			planStatus="新建"
		}
		if(planStatus=="1"){
			planStatus="待接单"
		}
		if(planStatus=="2"){
			planStatus="执行中"
		}
		if(planStatus=="3"){
			planStatus="已完成"
		}
		var cargoName = data[a].cargoName == undefined ? "":data[a].cargoName;
		var routeName = data[a].routeName == undefined ? "":data[a].routeName;
		var sendMan = data[a].sendMan == undefined ? "":data[a].sendMan;
		var sendPersion = data[a].sendPersion == undefined ? "":data[a].sendPersion;
		var venderName = data[a].venderName == undefined ? "":data[a].venderName;
		var receiptMan = data[a].receiptMan == undefined ? "":data[a].receiptMan;
		var receiptPersion = data[a].receiptPersion == undefined ? "":data[a].receiptPersion;
		var distant = data[a].distant == undefined ? "":data[a].distant;
		var price = data[a].price == undefined ? "":data[a].price;
		var tax = data[a].tax == undefined ? "":data[a].tax;
		var payMent = data[a].payMent == undefined ? "":data[a].payMent;
		if(payMent=="1"){
			payMent="司机"
		}
		if(payMent=="2"){
			payMent="车主"
		}
		var hml = "<tr>" +
				"<td>"+planCreateTimeStr+"</td>" +
				"<td>"+planCode+"</td>" +
				"<td>"+planBeginTimeStr+"</td>" +
				"<td>"+planEndTimeStr+"</td>" +
				"<td>"+planWeight+"</td>" +
				"<td>"+complitWeight+"</td>" +
				"<td>"+tempo+"</td>" +
				"<td>"+planStatus+"</td>" +
				"<td>"+cargoName+"</td>" +
				"<td>"+routeName+"</td>" +
				"<td>"+sendMan+"</td>" +
				"<td>"+sendPersion+"</td>" +
				"<td>"+venderName+"</td>" +
				"<td>"+receiptMan+"</td>" +
				"<td>"+receiptPersion+"</td>" +
				"<td>"+distant+"</td>" +
				"<td>"+price+"</td>" +
				"<td>"+tax+"</td>" +
				"<td>"+payMent+"</td>" +
				"</tr>";
		$("#innerHtml").append(hml);
	}
}

$('.printReport').off('click').on('click',function(){
	$('#planReport').jqprint();
});
$('.exportReport').off('click').on('click',function(){
	$.ajax({
		url : '/reportAll/plan',
		data : getParams(),
		type : 'post',
		success : function(result) {
			if(result.code == '000000'){
				if(result.data.total == 0){
					alert("没有要导出的数据！");
				}else if(result.data.total > 2000){
					alert("数据超过2000条，请联系管理员导出！");
				}else{
					var path = '/reportAll/planReport?'+$.param(getParams(1));
					$('<form method="post" action="' + path + '"></form>').appendTo('body').submit().remove();
				}
			}
		}
	});
});
function displayData(){
	init(0);
}
function displayData(pageNo){
	var page = $("#recPageNo").val();
	if(page != ""){
		init(page-1);
		$("#recPageNo").val("");
	}else{
		init(pageNo);
	}
}
function getParams(pageNo){
	var cargoName =$("#cargoName").val();
	var payCode =$("#payCode").val();
	var planCode =$("#planCode").val();
	var routeName =$("#routeName").val();
	var sendMan =$("#sendMan").val();
	var sendPersian =$("#sendPersian").val();
	var venderName =$("#venderName").val();
	var receiptMan =$("#receiptMan").val();
	var receiptPerson =$("#receiptPerson").val();
	var payStatus =$("#payStatus").val();
	var paystarttime =$("#paystarttime").val();
	var payendtime =$("#payendtime").val();
	var billstarttime =$("#billstarttime").val();
	var billendtime =$("#billendtime").val();
	
	var params = {
			cargoName:$.trim(cargoName),
			payCode:$.trim(payCode),
			planCode:$.trim(planCode),
			routeName:$.trim(routeName),
			sendMan:$.trim(sendMan),
			sendPersian:$.trim(sendPersian),
			venderName:$.trim(venderName),
			receiptMan:$.trim(receiptMan),
			receiptPerson:$.trim(receiptPerson),
			payStatus:$.trim(payStatus),
			paystarttime:$.trim(paystarttime),
			payendtime:$.trim(payendtime),
			billstarttime:$.trim(billstarttime),
			billendtime:$.trim(billendtime),
			pageNo:(pageNo),
			pageSize:10
		};
	return params;
}
function reset(){
	$("#cargoName").val("");
	$("#payCode").val("");
	$("#planCode").val("");
	$("#routeName").val("");
	$("#sendMan").val("");
	$("#sendPersian").val("");
	$("#venderName").val("");
	$("#receiptMan").val("");
	$("#receiptPerson").val("");
	$("#payStatus").val("");
	$("#paystarttime").val("");
	$("#payendtime").val("");
	$("#billstarttime").val("");
	$("#billendtime").val("");
	init(0);
}
function init(pageNo){
	$.ajax({
		url:"/reportAll/pay",
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
				document.getElementById("goPage").value = pageNo+1;
				$("#totalPages").html(parseInt((total-1)/pageSize+1));
				$('#totalPages').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
				$("#pagination").pagination(total, {
				    callback: pageCallback,
				    prev_text: '上一页',
				    next_text: '下一页',
				    items_per_page:pageSize,
				    num_display_entries:4,
				    current_page:pageNo,
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
		var payCreateTime = data[a].payCreateTimeStr == undefined ? "":data[a].payCreateTimeStr;
		var payCode = data[a].payCode == undefined ? "":data[a].payCode;
		var payMent = data[a].payMent == undefined ? "":data[a].payMent;
		if(payMent=="1"){
			payMent="司机"
		}
		if(payMent=="2"){
			payMent="车主"
		}
		var planCode = data[a].planCode == undefined ? "":data[a].planCode;
		var routeName = data[a].routeName == undefined ? "":data[a].routeName;
		var sendMan = data[a].sendMan == undefined ? "":data[a].sendMan;
		var sendPersian = data[a].sendPersian == undefined ? "":data[a].sendPersian;
		var venderName = data[a].venderName == undefined ? "":data[a].venderName;
		var receiptMan = data[a].receiptMan == undefined ? "":data[a].receiptMan;
		var receiptPerson = data[a].receiptPerson == undefined ? "":data[a].receiptPerson;
		var vehicleNo = data[a].vehicleNo == undefined ? "":data[a].vehicleNo;
		var billTime = data[a].billTimeStr == undefined ? "":data[a].billTimeStr;
		var billNo = data[a].billNo == undefined ? "":data[a].billNo;
		var cargoName = data[a].cargoName == undefined ? "":data[a].cargoName;
		var trueWeight = data[a].trueWeight == undefined ? "":data[a].trueWeight;
		var price = data[a].price == undefined ? "":data[a].price;
		var totalPrice = data[a].totalPrice == undefined ? "":data[a].totalPrice;
		var oilCard = data[a].oilCard == undefined ? "":data[a].oilCard;
		var weightMisc = data[a].weightMisc == undefined ? "":data[a].weightMisc;
		var deductMoney = data[a].deductMoney == undefined ? "":data[a].deductMoney;
		var deductOther = data[a].deductOther == undefined ? "":data[a].deductOther;
		var amountPayable = data[a].amountPayable == undefined ? "":data[a].amountPayable;
		var paidAmount = data[a].paidAmount == undefined ? "":data[a].paidAmount;
		var payStatus = data[a].payStatus == undefined ? "":data[a].payStatus;
		if(payStatus=="0"){
			payStatus="未支付"
		}
		if(payStatus=="1"){
			payStatus="支付中"
		}
		if(payStatus=="2"){
			payStatus="已支付"
		}
		if(payStatus=="3"){
			payStatus="支付失败"
		}
		var payType = data[a].payType == undefined ? "":data[a].payType;
		var payPerson = data[a].payPerson == undefined ? "":data[a].payPerson;
		var payBankName = data[a].payBankName == undefined ? "":data[a].payBankName;
		var payBankCode = data[a].payBankCode == undefined ? "":data[a].payBankCode;
		var hml = "<tr>" +
				"<td>"+payCreateTime+"</td>" +
				"<td>"+payCode+"</td>" +
				"<td>"+payMent+"</td>" +
				"<td>"+planCode+"</td>" +
				"<td>"+routeName+"</td>" +
				"<td>"+sendMan+"</td>" +
				"<td>"+sendPersian+"</td>" +
				"<td>"+venderName+"</td>" +
				"<td>"+receiptMan+"</td>" +
				"<td>"+receiptPerson+"</td>" +
				"<td>"+vehicleNo+"</td>" +
				"<td>"+billTime+"</td>" +
				"<td>"+billNo+"</td>" +
				"<td>"+cargoName+"</td>" +
				"<td>"+trueWeight+"</td>" +
				"<td>"+price+"</td>" +
				"<td>"+totalPrice+"</td>" +
				"<td>"+oilCard+"</td>" +
				"<td>"+weightMisc+"</td>" +
				"<td>"+deductMoney+"</td>" +
				"<td>"+deductOther+"</td>" +
				"<td>"+amountPayable+"</td>" +
				"<td>"+paidAmount+"</td>" +
				"<td>"+payStatus+"</td>" +
				"<td>"+payType+"</td>" +
				"<td>"+payPerson+"</td>" +
				"<td>"+payBankName+"</td>" +
				"<td>"+payBankCode+"</td>" +
				"</tr>";
		$("#innerHtml").append(hml);
	}
}

$('.printReport').off('click').on('click',function(){
	$('#planReport').jqprint();
});
$('.exportReport').off('click').on('click',function(){
	$.ajax({
		url : '/reportAll/pay',
		data : getParams(),
		type : 'post',
		success : function(result) {
			if(result.code == '000000'){
				if(result.data.total == 0){
					alert("没有要导出的数据！");
				}else if(result.data.total > 20000){
					alert("数据超过20000条，请联系管理员导出！");
				}else{
					var path = '/reportAll/payReport?'+$.param(getParams(1));
					$('<form method="post" action="' + path + '"></form>').appendTo('body').submit().remove();
				}
			}
		}
	});
});
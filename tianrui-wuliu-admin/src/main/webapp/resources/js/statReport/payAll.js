function displayData(pageNo){
	init(pageNo);
}

function getParams(pageNo){
	var params = {
			pageNo:pageNo,
			pageSize:10
		};
	return params;
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
		var hml = "<tr>" +
				"<td>"+data[a].payCreateTime+"</td>" +
				"<td>"+data[a].payCode+"</td>" +
				"<td>"+data[a].payMent+"</td>" +
				"<td>"+data[a].planCode+"</td>" +
				"<td>"+data[a].routeName+"</td>" +
				"<td>"+data[a].sendMan+"</td>" +
				"<td>"+data[a].sendPersian+"</td>" +
				"<td>"+data[a].venderName+"</td>" +
				"<td>"+data[a].receiptMan+"</td>" +
				"<td>"+data[a].receiptPerson+"</td>" +
				"<td>"+data[a].vehicleNo+"</td>" +
				"<td>"+data[a].billTime+"</td>" +
				"<td>"+data[a].billNo+"</td>" +
				"<td>"+data[a].cargoName+"</td>" +
				"<td>"+data[a].trueWeight+"</td>" +
				"<td>"+data[a].price+"</td>" +
				"<td>"+data[a].totalPrice+"</td>" +
				"<td>"+data[a].oilCard+"</td>" +
				"<td>"+data[a].weightMisc+"</td>" +
				"<td>"+data[a].deductMoney+"</td>" +
				"<td>"+data[a].deductOther+"</td>" +
				"<td>"+data[a].amountPayable+"</td>" +
				"<td>"+data[a].paidAmount+"</td>" +
				"<td>"+data[a].payStatus+"</td>" +
				"<td>"+data[a].payType+"</td>" +
				"<td>"+data[a].payPerson+"</td>" +
				"<td>"+data[a].payBankName+"</td>" +
				"<td>"+data[a].payBankCode+"</td>" +
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
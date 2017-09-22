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
		var hml = "<tr>" +
				"<td>"+data[a].planCreateTime+"</td>" +
				"<td>"+data[a].planCode+"</td>" +
				"<td>"+data[a].planBeginTime+"</td>" +
				"<td>"+data[a].planEndTime+"</td>" +
				"<td>"+data[a].planWeight+"</td>" +
				"<td>"+data[a].complitWeight+"</td>" +
				"<td>"+data[a].tempo+"</td>" +
				"<td>"+data[a].planStatus+"</td>" +
				"<td>"+data[a].cargoName+"</td>" +
				"<td>"+data[a].routeName+"</td>" +
				"<td>"+data[a].sendMan+"</td>" +
				"<td>"+data[a].sendPersion+"</td>" +
				"<td>"+data[a].venderName+"</td>" +
				"<td>"+data[a].receiptMan+"</td>" +
				"<td>"+data[a].receiptPersion+"</td>" +
				"<td>"+data[a].distant+"</td>" +
				"<td>"+data[a].price+"</td>" +
				"<td>"+data[a].tax+"</td>" +
				"<td>"+data[a].payMent+"</td>" +
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
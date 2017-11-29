function driverSearch(){
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
function reset(){
	$("#vehicleNo").val("");
	init(0);
}
function getParams(pageNo){
	var type = $(".bag_tab").find(".select").attr("type");
	var params = {pageNo:pageNo,
			pageSize:10,
			transactionstate:type}
	return params;
}
function init(pageNo){
	$.ajax({
		url:"/admin/money/capitalRecordSelect",
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
	var type = $(".bag_tab").find(".select").attr("type");
	if(type == 1){
		$(".bag_tj").empty();
		var dongjie1 = "<div class=" + "bag_tjline" + ">" + "<label>今日支出金额：</label>" 
		+ "<span>"+ 845712+"元</span></div>"+
		"<div class=" + "bag_tjline" + ">" + "<label>今日收入金额：</label>" 
		+ "<span>"+ 845712+"元</span></div>"+
		"<div class=" + "bag_tjline" + ">" + "<label>昨日可用余额：</label>" 
		+ "<span>"+ 845712+"元</span></div>"+
		"<div class=" + "bag_tjline" + ">" + "<label>今日可用余额：</label>" 
		+ "<span>"+ 845712+"元</span></div>"
		$(".bag_tj").append(dongjie1);
	}
	else if(type == 2){
		$(".bag_tj").empty();
		var dongjie2 = "<div class=" + "bag_tjline" + ">" + "<label>今日解冻金额：</label>" 
		+ "<span>"+ 845712.01+"元</span></div>"+
		"<div class=" + "bag_tjline" + ">" + "<label>昨日冻结金额：</label>" 
		+ "<span>"+ 845712.01+"元</span></div>"+
		"<div class=" + "bag_tjline" + ">" + "<label>今日冻结金额：</label>" 
		+ "<span>"+ 845712.01+"元</span></div>"
		$(".bag_tj").append(dongjie2);
		
	 }else if(type == 3){
			$(".bag_tj").empty();
		var dongjie3 = "<div class=" + "bag_tjline" + ">" + "<label>提现冻结：</label>" 
		+ "<span>"+ 845712.01+"元</span></div>"+
		"<div class=" + "bag_tjline" + ">" + "<label>用户可用余额：</label>" 
		+ "<span>"+ 845712.01+"元</span></div>"+
		"<div class=" + "bag_tjline" + ">" + "<label>账户金额总额：</label>" 
		+ "<span>"+ 845712.01+"元</span></div>"
		$(".bag_tj").append(dongjie3);
	 }
	for (var a = 0; a < data.length; a++) {
		var hml = "<tr>" +
			"<td>"+(a+1)+"</td>" +
			"<td>"+(data[a].username||"")+"</td>" +
			"<td>"+(data[a].cellphone||"")+"</td>" +
			"<td>"+(data[a].useryhno||"")+"</td>" +
			"<td>"+(data[a].transactiontype||"")+"</td>" +
			"<td>"+(data[a].capitalno||"")+"</td>" +
			"<td>"+((data[a].money/100).toFixed(2)||"")+"</td>" +
			"<td>"+((data[a].income/100).toFixed(2) ||"")+"</td>" +
			"<td>"+((data[a].expenditure/100).toFixed(2) ||"")+"</td>" +
			"<td>"+((data[a].totalmoney/100).toFixed(2) ||"")+"</td>" +
			"<td>"+((data[a].availablemoney/100).toFixed(2) ||"")+"</td>" +
			"<td>"+new Date(data[a].createtime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
			"<td>"+((data[a].withdrawalslockmoney/100).toFixed(2)||"")+"</td>" +
			"<td>"+((data[a].otherlockmoney/100).toFixed(2)||"")+"</td>" +
			"</tr>";
			$("#innerHtml").append(hml);
	}
}

$(".withdrawststus").on("click",function(){
	$(".withdrawststus").removeClass("select");
	$(this).addClass("select");
	init(0);
});

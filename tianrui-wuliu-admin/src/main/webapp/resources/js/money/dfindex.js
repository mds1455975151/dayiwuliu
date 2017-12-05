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
	$("#billNo").val("");
	$("#cellphone").val("");
	$("#vehicleNo").val("");
	$("#timexh").val("");
	$("#starttime").val("");
	$("#endtime").val("");
	init(0);
}
function getParams(pageNo){
	var params = {pageNo:pageNo,
			waybillno:$("#billNo").val(),
			cellphone:$("#cellphone").val(),
			useryhno:$("#vehicleNo").val(),
			createtime:$("#timexh").val(),
			pageSize:10}
	return params;
}

function init(pageNo){
	$.ajax({
		url:"/admin/money/pendingBillMoneySelect",
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
	if(!data){
		var hmlnull = "";
		hmlnull +='<td colspan="12">';
		hmlnull +='<div class="ht_none">';
		hmlnull +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
		hmlnull +='<div>';
		hmlnull +='<h3>唉呀！查询不到您要找的内容</h3>';
		hmlnull +='<p>换个查询条件试试吧？</p>';
		hmlnull +='</div>';
		hmlnull +='</div>';
		hmlnull +='</td>';
		$("#innerHtml").append(hmlnull);
	}else {
		for (var a = 0; a < data.length; a++) {
			var hml = "<tr>" +
					"<td>"+(a+1)+"</td>" +
					"<td>"+(data[a].username||"")+"</td>" +
					"<td>"+(data[a].cellphone||"")+"</td>" +
					"<td>"+(data[a].useryhno||"")+"</td>" +
					"<td>"+new Date(data[a].createtime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
					"<td>"+((data[a].pendingmoney/100).toFixed(2)||"")+"</td>" +
					"<td>"+(data[a].waybillno||"")+"</td>" +
					"</tr>";
			$("#innerHtml").append(hml);
			
		}
	}

}


function timenow(){
	   var mydate = new Date();
	   var year = mydate.getFullYear();
	   var month = (mydate.getMonth()+1);
	   var day =  mydate.getDate();
	   var reday = year + "-" + month +"-" + day;
	   return reday;
}

function timetd(){
	$("#starttime").val("");
	$("#endtime").val("");
	   $("#starttime").val(timenow());
	   $("#endtime").val(timenow());
}
function timeseven(){
	$("#starttime").val("");
	$("#endtime").val("");
	var now = new Date();
	   var mydate = new Date(now.getTime() - 7 * 24 * 3600 * 1000);
	   var year = mydate.getFullYear();
	   var month = (mydate.getMonth()+1);
	   var day =  (mydate.getDate());
	   $("#starttime").val(year + "-" + month +"-" + day);
	   $("#endtime").val(timenow());
}
function timethirty(){
	$("#starttime").val("");
	$("#endtime").val("");
	   var mydate = new Date();
	   var year = mydate.getFullYear();
	   var mon = (mydate.getMonth());
	   var day =  mydate.getDate();
	   $("#starttime").val(year + "-" + mon +"-" + day);
	   $("#endtime").val(timenow());
}


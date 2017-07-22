/**未审核 */
function index0(s){
	var pageNo = $("#pageNo").val();
	var searchKey = $("#searchKey").val();
	$.ajax({
		url:'/admin/bank/card/find',
		data:{
			"bankautid":"2",
			"bankcard":$.trim(searchKey),
			"pageNo":((parseInt(pageNo))),
			"pageSize":10
		},
		type:"post",
		success: function(ret) {
			if(ret.code=="000000"){
				innerHTML(ret.data.list,0,s);
			}
		}
	});
}

/**已审核 */
function index1(s){
	var pageNo = $("#pageNo").val();
	var searchKey = $("#searchKey").val();
	$.ajax({
		url:'/admin/bank/card/find',
		data:{
			"bankcard":$.trim(searchKey),
			"pageNo":((parseInt(pageNo))),
			"pageSize":10
		},
		type:"post",
		success: function(ret) {
			if(ret.code=="000000"){
				innerHTML(ret.data.list,1,s);
			}
		}
	});
}

function innerHTML(data,type,s){
	if(s==0){
		$("#rzHTML"+type).empty();
	}
	for (var a = 0; a < data.length; a++) {
		var hml = "";
		var stats = "";
		if(data[a].bankautid=="3"){
			stats = "认证失败";
		}
		if(data[a].bankautid=="1"){
			stats = "认证成功";
		}
		if(data[a].bankautid=="2"){
			stats = "认证中";
		}
		
		hml += "<div class='menmber_line' onclick=\"pageView('/weixin/page/bankdetail?id="+data[a].id+"')\">" +
				"<p>银行卡："+data[a].bankcard+"</p>" +
				"<div class='menmber_line1'>" +
				"<div class='menmber_line2'>" +
				"<label>持卡人：<em>"+data[a].idname+"</em></label>" +
				"</div>" +
				"<span>"+stats+"</span></div></div>";
		$("#rzHTML"+type).append(hml);
	}
}
	
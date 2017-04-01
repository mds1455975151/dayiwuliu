/**未审核 */
function index0(){
	var pageNo = $("#pageNo").val();
	$.ajax({
		url:'/admin/ticket/page',
		data:{
			"status":"2",
			"percheck":"0",
			"pageNo":(parseInt(pageNo)),
			"pageSize":10
		},
		type:"post",
		success: function(ret) {
			if(ret.code=="000000"){
				innerHTML(ret.data.list,0);
			}
		}
	});
}

/**已审核 */
function index1(){
	var pageNo = $("#pageNo").val();
	$.ajax({
		url:'/admin/ticket/page',
		data:{
			"percheck":"1",
			"pageNo":(parseInt(pageNo)),
			"pageSize":10
		},
		type:"post",
		success: function(ret) {
			if(ret.code=="000000"){
				innerHTML(ret.data.list,1);
			}
		}
	});
}

function innerHTML(data,type){
	for (var a = 0; a < data.length; a++) {
		var hml = "";
		var userName = "";
		var stats = "";
		if(data[a].status == "-1"){
			stats = "认证失败";
		}else if(data[a].status == "1"){
			stats = "认证成功";
		}else if(data[a].status == "2"){
			stats = "认证中";
		}
		
		hml += "<div class='menmber_line'>" +
				"<p>车牌号："+data[a].desc1+"</p>" +
				"<div class='menmber_line1'>" +
				"<div class='menmber_line2'>" +
				"<label>所有人：<em>"+data[a].owner+"</em></label>" +
				"</div>" +
				"<span>"+stats+"</span></div></div>";
		$("#rzHTML"+type).append(hml);
	}
}
	
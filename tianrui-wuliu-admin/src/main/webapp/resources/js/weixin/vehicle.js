/**未审核 */
function index0(){
	var pageNo = $("#pageNo").val();
	$.ajax({
		url:'/AdminMember/findCarManager',
		data:{
			"status":"2",
			"percheck":"0",
			"pageNo":((parseInt(pageNo)+parseInt(1))),
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
		url:'/AdminMember/findCarManager',
		data:{
			"percheck":"1",
			"pageNo":((parseInt(pageNo)+parseInt(1))),
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
		if(data[a].status=="-1"){
			stats = "认证失败";
		}
		if(data[a].status=="0"){
			stats = "未认证";
		}
		if(data[a].status=="1"){
			stats = "认证成功";
		}
		if(data[a].status=="2"){
			stats = "认证中";
		}
		
		hml += "<div class='menmber_line'>" +
				"<p>车牌号："+data[a].vehicleprefix+data[a].vehicleno+"</p>" +
				"<div class='menmber_line1'>" +
				"<div class='menmber_line2'>" +
				"<label>所有人：<em>"+data[a].userName+"</em></label>" +
				"</div>" +
				"<span>"+stats+"</span></div></div>";
		$("#rzHTML"+type).append(hml);
	}
}
	
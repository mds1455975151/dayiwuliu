/**未审核 */
function index0(){
	var pageNo = $("#pageNo").val();
	$.ajax({
		url:'/AdminMember/findMemberList',
		data:{
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
		url:'/AdminMember/findMemberList',
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
		var stats = "未认证";
		if(data[a].companypercheck=='2' || data[a].userpercheck=="2" || data[a].companypercheck=='3' || data[a].userpercheck=="3" ){
			userName = data[a].remarkname;
			if(data[a].companypercheck=='2' || data[a].userpercheck=="2"){
				stats = "认证中";
			}else if(data[a].companypercheck=='3' || data[a].userpercheck=="3"){
				stats = "认证失败";
			}
		}
		if(data[a].companypercheck=='1'){
			userName = data[a].companyName;
			stats = "认证成功";
		}else if(data[a].userpercheck=='1'){
			userName = data[a].userName;
			stats = "认证成功";
		}
		
		hml += "<div class='menmber_line'>" +
				"<p>会员名："+userName+"</p>" +
				"<div class='menmber_line1'>" +
				"<div class='menmber_line2'>" +
				"<label>会员账号：<em>"+data[a].cellPhone+"</em></label>" +
				"</div>" +
				"<span>"+stats+"</span></div></div>";
		$("#rzHTML"+type).append(hml);
	}
}
	
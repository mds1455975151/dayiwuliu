/**未审核 */
function index0(s){
	var pageNo = $("#pageNo").val();
	var cellPhone = $("#searchKey").val();
	$.ajax({
		url:'/AdminMember/findMemberList',
		data:{
			"percheck":"0",
			"cellPhone":$.trim(cellPhone),
			"pageNo":((parseInt(pageNo)+parseInt(1))),
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
	var cellPhone = $("#searchKey").val();
	$.ajax({
		url:'/AdminMember/findMemberList',
		data:{
			"percheck":"1",
			"cellPhone":$.trim(cellPhone),
			"pageNo":((parseInt(pageNo)+parseInt(1))),
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
		var userName = "";
		var stats = "未认证";
		var t = "";
		if(data[a].companypercheck=='2' || data[a].userpercheck=="2" || data[a].companypercheck=='3' || data[a].userpercheck=="3" ){
			userName = data[a].remarkname;
			if(data[a].companypercheck=='2' || data[a].userpercheck=="2"){
				t = "2"
				stats = "认证中";
			}else if(data[a].companypercheck=='3' || data[a].userpercheck=="3"){
				stats = "认证失败";
			}
		}
		if(data[a].companypercheck=='1'){
			userName = data[a].companyName;
			t = "1";
			stats = "认证成功";
		}else if(data[a].userpercheck=='1'){
			t = "1";
			userName = data[a].userName;
			stats = "认证成功";
		}
		if(data[a].companypercheck=='3' || data[a].userpercheck=="3"){
			hml += "<div class='menmber_line'>";
		}else{
			hml += "<div class='menmber_line' onclick=\"pageView('/weixin/page/memberdetail?id="+data[a].id+"&type="+t+"')\">";
		}
		hml += "<p>会员名："+userName+"</p>" +
				"<div class='menmber_line1'>" +
				"<div class='menmber_line2'>" +
				"<label>会员账号：<em>"+data[a].cellPhone+"</em></label>" +
				"</div>" +
				"<span>"+stats+"</span></div></div>";
		$("#rzHTML"+type).append(hml);
	}
}

$(function(){
	search();
});
function search(){
	var type = $("#searchtype").val();
	if(type=="pass"){
		$("#htmlType").html("禁用时间/分钟");
	}else {
		$("#htmlType").html("访问次数/5分钟-次");
	}
	$.ajax({
		url : CONTEXTPATH + "/admin/redis/find",// 跳转到 action
		data : {type:type},
		type : "post",
		success : function(result) {
			if(result.code=="000000"  ){
				innerHTML(result.data);
			}
		}
	});
}

function innerHTML(data){
	var hml = "";
	for (var a = 0; a < data.length; a++) {
		hml +="<tr><td>"+data[a].ip+"</td>" +
				"<td>"+data[a].number+"</td>" +
				"<td>"+data[a].timeStr+"</td>" +
				"<td><a><span onclick=\"remove('"+data[a].ip+"')\">清除</span></a></td></tr>";
	}
	$("#innerhml").html(hml);
}

function remove(id){
	var type = $("#searchtype").val();
	$.ajax({
		url : CONTEXTPATH + "/admin/redis/remove",// 跳转到 action
		data : {type:type,id:id},
		type : "post",
		success : function(result) {
			if(result.code=="000000"  ){
				search();
			}
		}
	});
}


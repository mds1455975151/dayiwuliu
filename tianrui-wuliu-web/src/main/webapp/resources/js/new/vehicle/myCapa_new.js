var pageSize = 2;
var hml;
var total;
var pageNo;
$(function (){
	$("#showtext").hide();
	$('#mycapaPage_new').addClass('selected');
	index(0,0);
});

function index(pageNo,flag){
	$.ajax({
		url : '/trwuliu/member_vehicle/new/find',// 跳转到 action
		data : {
			"pageNo":pageNo,
			"search":$("#searchText").val(),
			"pageSize":pageSize
		},
		type : "post",
		success : function(result){
			if(result.code == "000000"){
				innerHTML(result.data,flag);
			}else{
				alert(result.error);
			}
		}
	});
}

function moreSearch(){
	index(pageNo+1,1);
}

function innerHTML(result,flag){
	var data = result.list;
	total = result.total;
	document.getElementById("total").innerHTML=total;
	pageNo = result.pageNo;
	if((pageNo+1)*pageSize>=total){
		$("#moredate").hide();
	}else{
		$("#moredate").show();
	}
	if(flag == 0){
		hml = "";
	}
	for (var a = 0; a < data.length; a++) {
		var billstatus = "";
		if(data[a].billstatus == "0"){
			billstatus = "空闲";
		}else if(data[a].billstatus == "1"){
			billstatus = "运输中";
		}
		hml += "<tr><td >" +
		"<div class='car_cont1'>"+
			"<p><i>"+data[a].vehicleno+"</i><em></em></p>"+
			"<p><i>"+data[a].drivername+"</i><em>"+data[a].drivertel+"</em></p>" +
		"</div>" +
		"</td>" +
		"<td ><div class='car_cont1'><p><i>"+data[a].remarkname+"</i></p><p><i>"+data[a].cellphone+"</i></p></div></td>" +
		"<td >"+new Date(data[a].createtime).format("yyyy-MM-dd hh:mm:ss")+" </td>" +
		"<td>"+billstatus+"</td>" +
		"<td class='f12 bill_lineh2'>";
		if(data[a].billstatus == "0"){
			hml +="<button class='btn btnyello' onclick=\"deletecapa('"+data[a].id+"')\">删除</button>";
		}
		hml += "</td></tr>";
	}
	$("#innerHML").html(hml);
}
function deletecapa(id){
	confirm("删除确认","确定删除这条运力吗,确认/取消?",function(){
		$.ajax({
			url : '/trwuliu/member_vehicle/new/delect',// 跳转到 action
			data : {
				"id":id
			},
			type : "post",
			success : function(result){
				if(result.code == "000000"){
					index(1,0);
				}else{
					alert(result.error);
				}
			}
		});
	})
}

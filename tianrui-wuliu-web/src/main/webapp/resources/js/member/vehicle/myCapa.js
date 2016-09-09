var pagenow ;
var pageSize = 10;
var hml;
var total;
var pageNo;
$(function (){
	$("#showtext").hide();
	$('#mycapaPage').addClass('selected');
	index(1,0);
});

function index(pageNo,flag){
	$.ajax({
		url : PATH + '/trwuliu/Member/capa/index',// 跳转到 action
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
	pageNo = result.pageNo;
	document.getElementById("total").innerHTML=total;
	if(pageNo*pageSize>=total){
		$("#moredate").hide();
	}else{
		$("#moredate").show();
	}
	if(flag == 0){
		hml = "";
	}
	for (var a = 0; a < data.length; a++) {
		var billstatus = "";//2-发货中3-运货中4-卸货中5-空闲中'
		if(data[a].billstatus == "2"){
			billstatus = "发货中";
		}else if(data[a].billstatus == "3"){
			billstatus = "运货中";
		}else if(data[a].billstatus == "4"){
			billstatus = "卸货中";
		}else if(data[a].billstatus == "5"){
			billstatus = "空闲中";
		}
		var status = "";//0-未处理 1-同意 2-拒绝
		var image = "round1.png";
		if(data[a].status == "0"){
			status = "未同意";
		}else if(data[a].status == "1"){
			status = "已同意";
		}else if(data[a].status == "2"){
			status = "拒绝";
		}else if(data[a].status == "-1"){
			status = "原有运力";
			image = "round2.png";
		}
		var drivername = data[a].drivername;
		var drivertel = data[a].drivertel;
		if(data[a].drivername == undefined){
			drivername = "未绑定车辆";
			drivertel = "未绑定车辆"
		}
		var username = data[a].username;
		var telphone = data[a].telphone;
		if(data[a].username == undefined){
			username = data[a].companyname;
			telphone = data[a].companytel;
		}
		
		hml += "<tr><td >" +
		"<div class='car_cont1'>"+
			"<p><i>车牌号："+data[a].vehicleno+"</i><em></em></p>"+
			"<p><i>司机："+drivername+"</i><em>"+drivertel+"</em></p>" +
			"<p>"+data[a].vehicletype+"<span>|</span>"+data[a].length+"<span>|</span>"+data[a].weight+"吨</p>" +
		"</div>" +
		"</td>" +
		"<td >车主："+username+"-"+telphone+"</td>" +
		"<td >"+status+" </td>" +
		"<td>"+billstatus+"</td>" +
		"<td class='f12 bill_lineh2'>" +
		"<button class='btn btnyello' onclick=\"deletecapa('"+data[a].id+"','"+data[a].status+"')\" >删除</button>" +
		"</td></tr>";
	}
	document.getElementById("innerHML").innerHTML=hml;
}
function deletecapa(id,status){
	if(status==-1){
		alert("自有运力请去我的车辆中解除绑定");
		return;
	}
	confirm("删除确认","确定删除这条运力吗,确认/取消?",function(){
		$.ajax({
			url : PATH + '/trwuliu/Member/capa/delete',// 跳转到 action
			data : {
				"id":id
			},
			type : "post",
			success : function(result){
				if(result.code == "000000"){
					index(pageNo,0);
				}else{
					alert(result.error);
				}
			}
		});
	})
}

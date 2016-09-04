var pagenow ;
var pageSize = 10;
var total;
var lastPage;
$(function (){
	$("#showtext").hide();
	$('#mycapaPage').addClass('selected');
	index(1);
});

function index(pageNo){
	
	$.ajax({
		url : PATH + '/trwuliu/Member/capa/index',// 跳转到 action
		data : {
			"pageNo":pageNo,
			"pageSize":pageSize
		},
		type : "post",
		success : function(result){
			if(result.code == "000000"){
				innerHTML(result.data);
			}else{
				alert(result.error);
			}
		}
	});
}
//上一页
function uppage(){
	if(pagenow - 1 == 0){
		alert("已到第一页");
	}else{
		index(pagenow - 1);
	}
}
//下一页
function downpage(){
	if(pagenow == lastPage){
		alert("已到最后一页");
	}else{
		index(pagenow + 1);
	}
}
//尾页
function lastpage(){
	index(lastPage);
}

function innerHTML(result){
	var data = result.list;
	pagenow = result.pageNo;
	total = result.total;
	if(total % pageSize != 0){
		lastPage = total / pageSize +1;
	}else{
		lastPage = total / pageSize ;
	}
	lastPage = Math.floor(lastPage);
	//Math.ceil() 向上取整
	//Math.floor() 向下取整
	//Math.round() 四舍五入
	document.getElementById("pagenow").innerHTML=pagenow;
	var hml = ""
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
		hml += "<tr><td ><img src='"+trRoot+"/tianrui/images/"+image+"'></td>" +
				"<td >"+data[a].vehicleno+" </td>" +
				"<td>"+drivername+"</td>" +
				"<td>"+drivertel+"</td>" +
				"<td>"+username+"</td>" +
				"<td>"+telphone+"</td>" +
				"<td>"+data[a].vehicletype+"/"+data[a].length+"米</td>" +
				"<td>"+data[a].weight+"吨</td>" +
				"<td>"+billstatus+"/"+status+"</td></tr>";
	}
	document.getElementById("innerHML").innerHTML=hml;
}
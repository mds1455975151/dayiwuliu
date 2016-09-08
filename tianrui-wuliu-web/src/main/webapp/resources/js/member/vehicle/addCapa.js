$(function (){
	$("#showtext").hide();
	$('#mycapaPage').addClass('selected');
});
function onSearch(){
	var vehicle = $("#vhicleno").val();
	/** 车牌号正则验证*/
	var re=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	if(vehicle.search(re)==-1){
		alert("请输入正确的车牌号");
		$("#showtext").hide();
		return;
	}
	$.ajax({
		url : PATH + '/trwuliu/Member/capa/selectVehicle',// 跳转到 action
		data : {
			"search": vehicle
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

function innerHTML(data){
	$("#showtext").show();
	$("#vehicleowner").attr("value",data.creator);
	$("#drivername").attr("value",data.drivername);
	$("#drivertel").attr("value",data.drivertel);
	$("#vehicletype").attr("value",data.vehicletypename);
	$("#vehicleid").attr("value",data.vehicleid);
}

function addCapa(){
	if($("#vhicleno").val()==""){
		alert("请填写车辆信息");
		return;
	}
	$.ajax({
		url : PATH + '/trwuliu/Member/capa/save',// 跳转到 action
		data : {
			"vehicleid": $("#vehicleid").val(),
			"vehicleno": $("#vhicleno").val(),
			"ownerid": $("#vehicleowner").val()
		},
		type : "post",
		success : function(result){
			if(result.code == "000000"){
				window.location.href="/trwuliu/Member/capa/indexPage";
			}else{
				alert(result.error);
			}
		}
	});
}
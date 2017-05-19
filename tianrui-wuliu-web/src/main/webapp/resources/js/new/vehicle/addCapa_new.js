$(function (){
	$("#showtext").hide();
	$('#mycapaPage_new').addClass('selected');
});
function onSearch(){
	var vehicle = $("#vhicleno").val();
	/** 车牌号正则验证*/
	var re=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
//	if(vehicle.search(re)==-1){
//		alert("请输入正确的车牌号");
//		$("#showtext").hide();
//		return;
//	}
	$.ajax({
		url : '/trwuliu/member_vehicle/new/selectVehicle',// 跳转到 action
		data : {
			"vheicleno": vehicle
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
	$("#drivername").attr("value",data.drivername);
	$("#drivertel").attr("value",data.drivertel);
	$("#vehicletype").attr("value",data.vehicletype);
	$("#vehicleid").attr("value",data.vehicleid);
}

function addCapa(){
	if($("#vhicleno").val()==""){
		alert("请填写车辆信息");
		return;
	}
	$.ajax({
		url : '/trwuliu/member_vehicle/new/save',// 跳转到 action
		data : {
			"vehicleid": $("#vehicleid").val()
		},
		type : "post",
		success : function(result){
			if(result.code == "000000"){
				window.location.href="/trwuliu/member_vehicle/new/page";
			}else{
				alert(result.error);
			}
		}
	});
}
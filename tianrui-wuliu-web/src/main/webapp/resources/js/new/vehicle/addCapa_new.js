$(function (){
	$("#showtext").hide();
	$('#mycapaPage_new').addClass('selected');
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
	$("#vehicletype").attr("value",checkVehicleType(data.vehicletype));
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
}/** 车辆类型处理*/
function checkVehicleType(type){
	switch (type) {
	case "1":
		return "厢式"
		break;
	case "2":
		return "板车"
		break;
	case "3":
		return "冷藏"
		break;
	case "4":
		return "散装罐车"
		break;
	case "5":
		return "半挂车"
		break;
	case "6":
		return "重型自卸货车"
		break;
	case "7":
		return "轻型自卸货车"
		break;
	case "8":
		return "三轮农用运输"
		break;
	case "9":
		return "四轮农用普通货车"
		break;
	case "10":
		return "四轮农用自卸车"
		break;
	case "11":
		return "小型轮式拖拉机"
		break;
	case "12":
		return "大型轮式拖拉机"
		break;

	default:
		return "暂无此类型"
		break;
	}
}
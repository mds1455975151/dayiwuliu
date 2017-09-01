//添加
function add(vehicleownerid){
	$.ajax({
		url:"/trwuliu/bank/card/addVehicleownerBankcard",
		type:"post",
		data:{"vehicleownerid":vehicleownerid},
		success:function(ret){
			if(ret.code=="000000"){
				alert("添加成功！");
			}else{
				alert(ret.error);
			}
		}
	});
}
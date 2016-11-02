//车辆审核
function shenheCar(type){
	var id = $("#vehicleid").val();
    var massage = $("#massage").val();
    var statue = $("#statue").val();
    var memberid = $("#memberid").val();
    var pageNo = $("#pageNo").val();
    if(statue!="2"){
    	alert("非认证中用户不得修改");
		return;
    }
    if(type=="-1"&&massage==""){
    		alert("请出入审核不通过原因");
    		return;
    }
    $.ajax({
		url: CONTEXTPATH+'/AdminMember/carReviw',
		data:{"id":id,
				"type":type,
				"massage":massage,
				"memberid":memberid
		},
		type:"post",
		success: function(ret){
			if(ret.code=="000000"){
				window.location.href="/AdminMember/carManager?menuId=3&pageNo="+pageNo;
			}else{
				alert(ret.error);
			}
		}
	});
}
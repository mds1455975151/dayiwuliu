function adminReview(rtype){
 	var mid = document.getElementById("infoid").value;
 	var massage = document.getElementById("massages").value;
 	if(rtype==3){
 		if(massage==""){
 			alert("请输入不通过原因");
 			return;
 		}
 	}
 	$.ajax({
		url:CONTEXTPATH+'/AdminMember/driverReview',
		data:{"id":mid,"driverpercheck": rtype,"massage":massage},
		type:"post",
		success: function(retVal) {
			if(retVal.code=="000000"){
				window.location.href="/AdminMember/userDriver?menuId=3";
			}else{
				alert(retVal.error);
			}
		}
	});  
 }
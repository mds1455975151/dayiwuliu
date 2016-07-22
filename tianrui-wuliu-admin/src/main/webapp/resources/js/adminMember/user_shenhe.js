function adminReview(rtype){
 	var mid = document.getElementById("infoid").value;
 	var massage = document.getElementById("massages").value;
 	var companypercheck = document.getElementById("companypercheck").value;
 	var userpercheck = document.getElementById("userpercheck").value;
 	if(rtype==3){
 		if(massage==""){
 			alert("请输入不通过原因");
 			return;
 		}
 	}
 	var url="";
 	if(companypercheck == 2){
 		url = "/AdminMember/companyReview";
 	}
 	if(userpercheck == 2){
 		url = "/AdminMember/userReview";
 	}
 	$.ajax({
		url:CONTEXTPATH+url,
		data:{"id":mid,"userpercheck": rtype,"companypercheck":rtype,"massage":massage},
		type:"post",
		success: function(retVal) {
			if(retVal.code=="000000"){
				window.location.href="/AdminMember/userPerson?menuId=3";
			}else{
				alert(retVal.error);
			}
		}
	});  
 }
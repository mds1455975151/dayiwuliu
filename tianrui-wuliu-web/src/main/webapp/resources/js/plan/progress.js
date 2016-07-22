	//完成计划
	function completePlan() {
		var dId= $("#planId").val();
		confirm("完成确认","确认提前完成此计划吗,确认/取消?",function(){
			$.ajax({
				url : "/trwuliu/planowner/complete",
				data : {"id":dId},
				type : "post",
				success : function(rs){
					if(rs.code=="000000"){
						window.location.reload(); 
					}else{
						alert(rs.error);
					}
				}
			});
		})
	}

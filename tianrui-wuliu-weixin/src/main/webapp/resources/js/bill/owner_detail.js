$(function(){
	
	$("#billowner").addClass("selected");
	var URL={
		signUrl:"/trwuliu/billowner/signConfirm",
		deleteUrl:"/trwuliu/billowner/delete",
		mainUrl:"/trwuliu/billowner/main"
	}
	
	var id =$("#billId").val();
	//签收按钮点击
	$(".detaildiv").on("click",".signBtn",function(){
		$("#signModal").modal();
	});
	
	
	$(".signsubmitbtn").click(function(){
		var weightInput=$("#weighttext").val();
    	if( !weightInput ){
    		alert("签收重量不能为空.");
    		return ;
    	}else if(!(/^([1-9]?\d+)(\.\d+)?$/.exec(weightInput)) ){
    		alert("签收重量格式非法");
    		return ;
    	}
    	$.ajax({
			url:URL.signUrl,
			data:{"id":id,"weight":weightInput},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					window.location.href=URL.mainUrl;
				}else{
					alert(rs.error);
				}
			}
		})
	});
	
	//删除按钮点击
	$(".detaildiv").on("click",".delBtn",function(){
		$.ajax({
			url:URL.deleteUrl,
			data:{"id":id},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					alert("操作成功.")
					window.location.href=URL.mainUrl;
				}else{
					alert(rs.error);
				}
			}
		})
	});
});

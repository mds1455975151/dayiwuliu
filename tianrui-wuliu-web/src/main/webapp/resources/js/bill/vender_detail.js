$(function(){
	var isAppoint = $('#isAppoint').val();
	if(isAppoint == '0'){
		$("#billvender").addClass("selected");
	}else{
		$("#billAppoint").addClass("selected");
	}
	var URL={
		cancleUrl:"/trwuliu/billvender/cancle",
		deleteUrl:"/trwuliu/billvender/delete",
		updateViewUrl:"/trwuliu/billvender/updateView",
		mainUrl:"/trwuliu/billvender/main"
	}
	
	var id =$("#billId").val();;
	//取消按钮点击
	$(".detaildiv").on("click",".cancleBtn",function(){
		$.ajax({
			url:URL.cancleUrl,
			data:{"id":id},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					alert("操作成功.")
					window.location.reload();
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
	//修改按钮点击
	$(".detaildiv").on("click",".editBtn",function(){
		window.location.href=URL.updateViewUrl+"?id="+id;
	});
});

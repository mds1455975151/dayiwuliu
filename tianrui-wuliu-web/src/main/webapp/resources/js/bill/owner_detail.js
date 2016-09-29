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
		weightInput = parseFloat(weightInput);
    	if( !weightInput ){
    		alert("签收重量不能为空.");
    		return ;
    	}else if(!(/^\d{1,6}(\.\d{0,2})?$/.test(weightInput)) ){
    		alert("签收重量格式整数最大6位，小数最大2位");
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

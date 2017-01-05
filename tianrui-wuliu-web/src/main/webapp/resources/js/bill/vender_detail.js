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
	
	$('#THBD').off('click').on('click',function(){
		var url = $(this).attr('item');
		var psweight = $(this).attr('psweight');
		if(psweight){
			psweight = parseFloat(psweight).toFixed(2)+"吨";
		}
		if(url){
			$('#bdImg').attr('src',url);
			$('#bdImg').parent('a').attr('href',"/imageView/index?imageUrl="+url);
			$('#psweight').html('提货量：'+psweight);
			$('#bdView').modal();
		}else{
			$('#bdts').modal();
		}
	});
	
	$('#XHBD').off('click').on('click',function(){
		var url = $(this).attr('item');
		var psweight = $(this).attr('psweight');
		if(psweight){
			psweight = parseFloat(psweight).toFixed(2)+"吨";
		}
		if(url){
			$('#bdImg').attr('src',url);
			$('#bdImg').parent('a').attr('href',"/imageView/index?imageUrl="+url);
			$('#psweight').html('卸货量：'+psweight);
			$('#bdView').modal();
		}else{
			$('#bdts').modal();
		}
	});
});

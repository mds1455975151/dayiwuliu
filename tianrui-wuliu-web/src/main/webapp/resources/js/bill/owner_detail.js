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
		$('.nav-tabs li:first').addClass('active').siblings('li').removeClass('active');
		$('.tab-content div:first').addClass('in active').siblings('div.tab-pane').removeClass('in active');
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
		var planWeight = parseFloat($('#planWeight').val());
		var planCompleteWeight = parseFloat($('#planCompleteWeight').val());
		var title = "本次运单的计划运输量为"+$("#weight").val()+"吨，签收量为"+weightInput+"吨；货主计划总量为"+planWeight+"吨，已累计签收（含本次）数量为"+(planCompleteWeight+weightInput)+"吨；";
    	if((planWeight - planCompleteWeight) <= weightInput){
    		title += "现已超过计划总量，确认将关闭货运计划。";
    	}
    	confirm("确认",title,function(){
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
    		});
    	},function(){
    		$("#signModal").modal('hide');
    		$("#weighttext").val("");
    	});
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

$(function(){
	$("#billvender").addClass("selected");
	var URL={
		addUrl:"/trwuliu/billvender/add",
		cancleUrl:"/trwuliu/Member/cargoPlan/planchengyun",
		successUrl:"/trwuliu/billvender/main",
	}
	
	//样式控制
    var chren = $('.bill_fabu ul li');
    chren.click(function(){
        $(this).addClass('selected');
    });
    
    
    //返回
    $(".cancleBtn").click(function(){
    	//$(".rz_list #planchengyun").trigger("click");
    	window.location.href=URL.cancleUrl;
    });
    
    //发布运单
    $(".submitBtn").click(function(){
    	//交验数据
    	var billStartTimeInput=$(".billStartTimeInput").val();
    	var billEndTimeInput=$(".billEndTimeInput").val();
    	var weightInput=$(".weightInput").val();
    	var priceInput=$(".priceInput").val();
    	var planId=$("#planId").val();
    	
    	if( !planId ){
    		alert("计划数据异常,请核对计划数据完整性.");
    		return ;
    	}
    	if( !billStartTimeInput ){
    		alert("开始时间不能为空.")
    		return ;
    	}
    	if( !billEndTimeInput ){
    		alert("结束时间不能为空.")
    		return ;
    	}
    	if( billStartTimeInput > billEndTimeInput ){
    		alert("结束时间早于开始时间.")
    		return ;
    	}
    	
    	//价格和运输量之能为小数或者整数
    	if( !priceInput ){
    		alert("运单价格不能为空.");
    		return ;
    	}else if(!(/^([1-9]?\d+)(\.\d+)?$/.exec(priceInput)) ){
    		alert("运单价格格式非法");
    		return ;
    	}
    	if( !weightInput ){
    		alert("运输量不能为空.");
    		return ;
    	}else if(!(/^([1-9]?\d+)(\.\d+)?$/.exec(weightInput)) ){
    		alert("运输量格式非法");
    		return ;
    	}
    	//
    	var vehicleDriverIds ="";
    	$(".bill_cllist .checkInput:checked").each(function(i,item){
    		vehicleDriverIds +=$(item).attr("dataId")+";";
    	})
    	if(!vehicleDriverIds){
    		alert("请至少选择一个车辆");
    		return ;
    	}
    	//参数拼装
    	var param={
    		"planId":planId,	
    		"billStartTime":billStartTimeInput,	
    		"billEndTime":billEndTimeInput,	
    		"weight":weightInput,	
    		"price":priceInput,	
    		"vehicleDriverIds":vehicleDriverIds,	
    	}
    	//表单数据提交
    	$.ajax({
    		url:URL.addUrl,
			data:param,
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					alert("操作成功");
					window.location.href=URL.successUrl;
				}else{
					alert(rs.error);
				}
			}
    	})
    });
    
});

$(function(){
	$("#billvender").addClass("selected");
	var URL={
		updateUrl:"/trwuliu/billvender/update",
		cancleUrl:"/trwuliu/billvender/main",
		successUrl:"/trwuliu/billvender/main",
	}
	
	$('.bill_cllist ul li').off('click').on('click',function(){
		$(this).siblings().removeClass('active').find('.checkInput').each(function(){
			this.checked = false;
		});
		$(this).addClass('active').find('.checkInput')[0].checked = true;
	});
	//限制输入格式位数字
    $('.ts').change(function(){
    	if(!/^[1-9]\d*$/.test($(this).val())){
    		$(this).val('');
    	}
    }).off('keyup').on('keyup',function(){
    	$(this).trigger('change');
    });

	$(".checkInput").change(function(){
		$(this).closest('li').siblings().find('.checkInput').each(function(){
			this.checked = false;
		});
	});
	
    //返回
    $(".cancleBtn").click(function(){
    	window.location.href=URL.cancleUrl;
    });
    
    //发布运单
    $(".submitBtn").click(function(){
    	//交验数据
    	var billStartTimeInput=$(".billStartTimeInput").val();
    	var billEndTimeInput=$(".billEndTimeInput").val();
    	var weightInput=$(".weightInput").val();
    	var priceInput=$(".priceInput").val();
    	var id=$("#id").val();
    	
    	if( !id ){
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
    	if($(".checkInput:checked").length!=1){
    		alert("请选择车辆");
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
    	var ts = $(".checkInput:checked ~ input.ts").val();
    	if(!ts){
    		alert('请输入趟数！');return; 
    	}
    	//参数拼装
    	var param={
    		"id":id,	
    		"billStartTime":billStartTimeInput,	
    		"billEndTime":billEndTimeInput,	
    		"weight":weightInput,	
    		"price":priceInput,	
    		"vehicleId":$(".checkInput:checked").attr("dataId"),
    		"overnumber":ts
    	}
    	var overweight = parseFloat($('#overweight').val());//剩余运输量
    	var sumweight = parseFloat(weightInput)*ts;
    	if(sumweight > overweight){
    		confirm("ok","当前运输量已超过计划剩余运输量，是否继续？",function(){
    			saveBills(param);
    		});
    	}else{
    		saveBills(param);
    	}
    });
   
    function saveBills(param){
    	//表单数据提交
    	$.ajax({
    		url:URL.updateUrl,
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
    }
    
});

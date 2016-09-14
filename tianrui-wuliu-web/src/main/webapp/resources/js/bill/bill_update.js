$(function(){
	var isAppoint = $('#isAppoint').val();
	if(isAppoint == '0'){
		$("#billvender").addClass("selected");
	}else{
		$("#billAppoint").addClass("selected");
	}
	searchVehicle();
	var URL={
		updateUrl:"/trwuliu/billvender/update",
		cancleUrl:"/trwuliu/billvender/main",
		successUrl:"/trwuliu/billvender/main",
		successAppointUrl:"/trwuliu/billAppoint/main"
	}
	
	//限制输入格式位数字
    $('.ts').change(function(){
    	if(!/^[1-9]\d*$/.test($(this).val())){
    		$(this).val('');
    	}
    }).off('keyup').on('keyup',function(){
    	$(this).trigger('change');
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
					if(isAppoint == '0'){
						window.location.href=URL.successUrl;
					}else{
						window.location.href=URL.successAppointUrl;
					}
				}else{
					alert(rs.error);
				}
			}
    	})
    }
    $("#searchVehicle").click(function(){
    	searchVehicle();
    })
    function searchVehicle(){
    	$.ajax({
    		url:"/trwuliu/billvender/searchCapa",
    		data:{"search":$("#search").val()},
    		type : "post",
    		dataType:"json",
    		success:function(rs){
    			if( rs && rs.code =="000000" ){
    				innerHTML(rs.data);
    			}else{
    				alert(rs.error);
    			}
    		}
    	});
    }
    function innerHTML(data){
    	var hml = "";
    	for (var a = 0; a < data.length; a++) {
    		var billstatus = "";
    		//2-发货中3-运货中4-卸货中5-空闲中
    		if(data[a].billstatus == 2){
    			billstatus = "发货中";
    		}else if(data[a].billstatus == 3){
    			billstatus = "运货中";
    		}else if(data[a].billstatus == 4){
    			billstatus = "卸货中";
    		}else if(data[a].billstatus == 5){
    			billstatus = "空闲中";
    		}
    		hml += "<li><input type='checkbox' dataId="+data[a].vehicledriverid+" class='checkInput'>"+
    		"<label>"+data[a].vehicleno+"</label>"+
    		"<span>"+data[a].drivername+"--"+data[a].vehicletype+"</span>"+
    		"<em>"+data[a].weight+"吨</em>"+
    		"<em>"+billstatus+"</em>"+
    		"<input type='text' class='ts' placeholder='输入趟数' value='1' />"+
    		"<i>趟</i></li>";
    	}
    	document.getElementById("capa").innerHTML=hml;
    	$('.bill_cllist ul li').off('click').on('click',function(){
    		$(this).siblings().removeClass('active').find('.checkInput').each(function(){
    			this.checked = false;
    		});
    		$(this).addClass('active').find('.checkInput')[0].checked = true;
    	});
    	$(".checkInput").change(function(){
    		$(this).closest('li').siblings().find('.checkInput').each(function(){
    			this.checked = false;
    		});
    	});
    }
});

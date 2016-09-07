$(function(){
	$("#billvender").addClass("selected");
	searchVehicle();
	var URL={
		addUrl:"/trwuliu/billvender/add",
		cancleUrl:"/trwuliu/planvender/main",
		successUrl:"/trwuliu/billvender/main",
		searchUrl:"/trwuliu/billvender/searchCapa",
	}
	$('.bill_cllist ul li .checkInput').off('click').on('click',function(e){
		if(this.checked){
			$(this).closest('li').addClass('active');
		}else{
			$(this).closest('li').removeClass('active');
		}
		e.stopPropagation();
	});
	$('.bill_cllist ul li').off('click').on('click',function(){
		$(this).addClass('active').find('.checkInput').trigger('click');
	});
	//样式控制
    var chren = $('.bill_fabu ul li');
    chren.click(function(){
        $(this).addClass('selected');
    });
    //限制输入格式位数字
    $('.ts').off('click').on('click',function(e){
    	$(this).siblings('.checkInput')[0].checked = true;
    	e.stopPropagation();
    }).change(function(){
    	if(!/^[1-9]\d*$/.test($(this).val())){
    		$(this).val('');
    	}
    }).off('keyup').on('keyup',function(){
    	$(this).trigger('change');
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
    	}else if(weightInput <= 0){
    		alert("运输量必须大于0");
    		return ;
    	}
    	if($(".bill_cllist .checkInput:checked").length == 0){
    		alert("请至少选择一个车辆");
    		return ;
    	}
    	//
    	var vehicleDriverIds ="";
    	var zts = 0;
    	var flag = false;
    	$(".bill_cllist .checkInput:checked").each(function(i,item){
    		var ts = $(this).siblings('input.ts').val();
    		if(!ts){
    			flag = false;
    			alert('请输入趟数！');return;
    		}else{
    			flag = true;
    		}
    		vehicleDriverIds +=$(item).attr("dataId")+","+ts+";";
    		zts += parseInt(ts);
    	})
    	//参数拼装
    	var param={
    		"planId":planId,	
    		"billStartTime":billStartTimeInput,	
    		"billEndTime":billEndTimeInput,	
    		"weight":weightInput,	
    		"price":priceInput,	
    		"vehicleDriverIds":vehicleDriverIds,	
    	}
    	var overweight = parseFloat($('#overweight').val());//剩余运输量
    	var sumweight = parseFloat(weightInput)*zts;
    	if(!flag){
    		return;
    	}
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
    	});
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
    		hml += "<li><input type='checkbox' dataId="+data[a].id+" class='checkInput'>"+
    		"<label>"+data[a].vehicleno+"</label>"+
    		"<span>"+data[a].drivername+"--"+data[a].vehicletype+"</span>"+
    		"<em>"+data[a].weight+"吨</em>"+
    		"<em>"+billstatus+"</em>"+
    		"<input type='text' class='ts' placeholder='输入趟数' value='1' />"+
    		"<i>趟</i></li>";
    	}
    	document.getElementById("capa").innerHTML=hml;
    }
});


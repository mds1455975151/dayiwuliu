$(function(){
	$("#billvender").addClass("selected");
	searchVehicle();
	var URL={
		addUrl:"/trwuliu/billvender/add",
		cancleUrl:"/trwuliu/planvender/main",
		successUrl:"/trwuliu/billvender/main",
		searchUrl:"/trwuliu/billvender/searchCapa",
		alsuccessUrl:"/trwuliu/billAnlian/vender"
	}
	//总趟数
	var tang = 0;
	
	
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
    	
    	var pflag = 0;
    	var psize = 0;
    	var pts = 0;
    	//判断所选运力是否有开票运力
    	$(".bill_cllist .checkInput:checked").each(function(i,item){
    		pts = $(this).siblings('input.ts').val();
    		var alVehicleId = $(item).attr("alVehicleId");
    		var alDriverId = $(item).attr("alDriverId");
    		psize = i+1;
    		if(alVehicleId=="1"&&alDriverId!=""){
    			pflag +=1 ;
    		}
    	})
    	if(pflag != 0 && psize > 1){
    		alert("包含开票运力，仅支持一车一单");
    		return;
    	}
    	if(pflag == 1 && psize == 1){
    		if(pts != 1){
    			alert("包含开票运力，仅支持一车一单");
        		return;
    		}
    		var _this = this;
    		$(_this).attr('disabled',true);
    		confirm("ok","此单为开票运单，是否继续？",function(){
    			saveBills(param,_this,URL.alsuccessUrl);
    		},function(){
    			$(_this).attr('disabled',false);
    		});
    	}else{
    		var _this = this;
			$(_this).attr('disabled',true);
			if(sumweight > overweight){
				confirm("ok","当前运输量已超过计划剩余运输量，是否继续？",function(){
					saveBills(param,_this,URL.successUrl);
				},function(){
					$(_this).attr('disabled',false);
				});
			}else{
				saveBills(param,_this,URL.successUrl);
			}
    	}
    });
    
    function saveBills(param,_this,resp_url){
    	//表单数据提交
    	$.ajax({
    		url:URL.addUrl,
			data:param,
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					alert("操作成功");
					window.location.href=resp_url;
				}else{
					alert(rs.error);
					$(_this).attr('disabled',false);
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
    		var yltype = "";
    		//2-发货中3-运货中4-卸货中5-空闲中
    		if(data[a].vehicledriverid != "" && data[a].desc1 == "1"){
    			yltype = "开票运力";
    		}else {
    			yltype = "普通运力";
    		}
    		hml += "<li><input class='checkInput' type='checkbox' dataId="+data[a].vehicledriverid+" alVehicleId="+data[a].desc1+" alDriverId="+data[a].aldriverid+">"+
    		"<label>"+data[a].vehicleno+"</label>"+
    		"<span style ='white-space:nowrap' >"+data[a].drivername+"--"+data[a].vehicletype+"</span>"+
    		"<em style='width: 60px;'>"+data[a].weight+"吨</em>"+
    		"<em>"+yltype+"</em>"+
    		"<input type='text' onchange='sum_weights()' class='ts' maxlength='2' placeholder='输入趟数' value='1' />"+
    		"<i>趟</i></li>";
    	}
    	document.getElementById("capa").innerHTML=hml;
    	$('.bill_cllist ul li .checkInput').off('click').on('click',function(e){
    		if(this.checked){
    			$(this).closest('li').addClass('active');
    		    var a =	$(this).closest('li').find('.ts').val();
    		}else{
    			$(this).closest('li').removeClass('active');
    		}
    		sum_weights();
    		e.stopPropagation();
    	});
    	$('.bill_cllist ul li').off('click').on('click',function(){
    		$(this).addClass('active').find('.checkInput').trigger('click');
    	});
    }
});

function sum_weights(){
	var ts = parseInt(0);
	$(".bill_cllist .checkInput:checked").each(function(i,item){
		ts += parseInt($(this).siblings('input.ts').val());
	})
	var weightInput = $(".weightInput").val();
	if(weightInput == ''){
		weightInput = 0;
	}
	$("#sum_weight").html((weightInput*ts).toFixed(2));
}

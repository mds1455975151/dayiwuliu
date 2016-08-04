$(function(){
	$('.venderList li').off('click').on('click',function(){
		$(this).addClass('active').siblings().removeClass('active');
		initDriver($(this).attr('item'));
	});
	var initDriver = function(memberId){
		$.ajax({
			url:'/trwuliu/billdriver/queryDriver',
			data:{
				memberId:memberId
			},
			dataType:'json',
			type:'POST',
			async:false,
			cache:false,
			success:function(result){
				console.info(result);
				if(result.code == "000000"){
					loadDriverList(result.data);
				}else{
					alert(result.error);
				}
			}
		});
	};
	
	function loadDriverList(driverList){
		if(driverList){
			var $ul = $('<ul>');
			for(var i=0;i<driverList.length;i++){
				var obj = driverList[i];//<span><input type="radio" name="hand"/></span>
				$li = $('<li driverid="'+obj.driverid+'" drivername="'+obj.drivername+'" drivertel="'+obj.drivertel+'"><span>'+obj.drivername+'</span><span>'+obj.drivertel+'</span></li>');
				$ul.append($li);
			}
			$('.driverList').empty().append($ul);
			$('.driverList ul li').off('click').on('click',function(){
				$(this).addClass('active').siblings().removeClass('active');
			});
		}
	}
	
	if($('.handBtn').attr('item') == '0'){
		$('.handBtn').val('申请交班');
	}else{
		$('.handBtn').val('收回');
	}
	$('.handBtn').off('click').on('click',function(){
		if($(this).attr('item') == '0'){
			if($('.driverList li.active').length == 0){
				alert('请选择交班人');return
			}else{
				$(this).off('click');
				applyHand();
			}
		}else{
			$(this).off('click');
			recover();	
		}
	});
	var applyHand = function(){
		var driverid = $('.driverList li.active').attr('driverid');
		var drivername = $('.driverList li.active').attr('drivername');
		var drivertel = $('.driverList li.active').attr('drivertel');
		$.ajax({
			url:'/trwuliu/billdriver/applyHand',
			data:{
				sendid:driverid,
				sender:drivername,
				sendtele:drivertel
			},
			dataType:'json',
			type:'POST',
			async:false,
			cache:false,
			success:function(result){
				if(result.code == '000000'){
					//按钮颜色置灰
					alert('申请交班成功');
					$('.handBtn').attr('item','1').val('收回').off('click').on('click',recover);
				}else{
					alert(result.error);
				}
			}
		});
	}
	var recover = function(){
		$.ajax({
			url:'/trwuliu/billdriver/recover',
			data:null,
			dataType:'json',
			type:'POST',
			async:true,
			cache:false,
			success:function(result){
				if(result.code == '000000'){
					//按钮颜色置亮
					alert('收回成功');
					$('.handBtn').attr('item','0').val('申请交班').off('click').on('click',applyHand);
				}else{
					alert(result.error);
				}
			}
		});
	};
	
	
	
	$('.agreeBtn').off('click').on('click',function(){
		updateHand(1);
	});
	$('.RefuseBtn').off('click').on('click',function(){
		updateHand(2);
	});
	var updateHand = function(status){
		var startid = $('.isAcceptUl').attr('startid');
		var starter = $('.isAcceptUl').attr('starter');
		var starttele = $('.isAcceptUl').attr('starttele');
		$.ajax({
			url:'/trwuliu/billdriver/updateHand',
			data:{
				status:status,
				startid:startid,
				starter:starter,
				starttele:starttele
			},
			dataType:'json',
			type:'POST',
			async:true,
			cache:false,
			success:function(result){
				if(result.code == "000000"){
					window.location.reload();
				}else{
					alert(result.error);
				}
			}
		});
	};
});
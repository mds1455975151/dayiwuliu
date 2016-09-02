;(function($,win){
	var operate = $('#operate').val();
	//返回
	$(".backBtn").off('click').on('click',function(){
		if(operate == 'add'){
			window.location.href = "/trwuliu/planvender/main";
		}
		if(operate == 'edit'){
			window.location.href = "/trwuliu/planAppoint/main";
		}
	});
	//委派计划
	$('.appointBtn').off('click').on('click',function(){
		this.disabled = true;
		if(operate == 'add'){
			addAppointPlan(this);
		}
		if(operate == 'edit'){
			editAppointPlan(this);
		}
	});
	
	var addAppointPlan = function(dom){
		var planid = $('#planid').val();
		var begintime = $('#begintime').val();
		var endtime = $('#endtime').val();
		var totalplanned = $('#totalplanned').val();
		
		var venderid = $('ul.venderList li').attr('venderid');
		var venderName = $('ul.venderList li').attr('venderName');
		var venderTel = $('ul.venderList li').attr('venderTel');
		$.ajax({
			url:'/trwuliu/planAppoint/addAppointPlan',
			data:{
				planid:planid,
				begintime:begintime,
				endtime:endtime,
				totalplanned:totalplanned,
				venderid:venderid,
				venderName:venderName,
				venderTel:venderTel
			},
			dataType:'json',
			type:'POST',
			cache:false,
			async:true,
			success:function(res){
				if(res.code=="000000"){
					window.location.href = CONTEXTPATH + "/trwuliu/planvender/main";
				}else{
					alert(res.error);
				}
				//解除按钮冻结
				if(dom){
					dom.disabled = false;
				}
			}
		});
	};
	
	var editAppointPlan = function(dom){
		var planid = $('#planid').val();
		var begintime = $('#begintime').val();
		var endtime = $('#endtime').val();
		var totalplanned = $('#totalplanned').val();
		
		var venderid = $('ul.venderList li').attr('venderid');
		var venderName = $('ul.venderList li').attr('venderName');
		var venderTel = $('ul.venderList li').attr('venderTel');
		$.ajax({
			url:'/trwuliu/planAppoint/editAppointPlan',
			data:{
				planid:planid,
				begintime:begintime,
				endtime:endtime,
				totalplanned:totalplanned,
				venderid:venderid,
				venderName:venderName,
				venderTel:venderTel
			},
			dataType:'json',
			type:'POST',
			cache:false,
			async:true,
			success:function(res){
				if(res.code=="000000"){
					window.location.href = CONTEXTPATH + "/trwuliu/planAppoint/main";
				}else{
					alert(res.error);
				}
				//解除按钮冻结
				if(dom){
					dom.disabled = false;
				}
			}
		});
	};
	
	
	
	
	
	
})(jQuery,window);
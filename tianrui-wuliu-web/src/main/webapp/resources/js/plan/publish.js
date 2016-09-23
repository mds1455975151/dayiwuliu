$(function(){
	//左侧选中
	$("#plantemplate").addClass("selected");
	
	var URL={
		savePlan:"/trwuliu/planowner/save"
	}
	
	
	//总量交验
	$("#totalplanned").blur(function(){
		var reg=/^\d+\.?\d{0,2}$/;
		var totalplanned=$("#totalplanned").val();
		if(reg.test( totalplanned) ){
			var price =$("#hprice").text();
			if(price){
				var totalPrice =(+price*totalplanned).toFixed(2)
				$("#totalPrice").html(".总价:"+totalPrice+"元")
			}
		}else{
			alert("计划总量格式错误,请输入最多2位小数或整数")	
			$("#totalplanned").val("");
			$("#totalPrice").html("");
		}
	});
	
	//熟车按钮事件
	$("ul .familayVender").click(function(){
		$("#isFamily").val("1");
		changeTabu(this);
	});
	//车主按钮事件
	$("ul .allVender").click(function(){
		$("#isFamily").val("0");
		changeTabu(this);
	});
	
	//添加计划按钮绑定事件
	$(".submitBtn").click(function(){
		//避免重复提交
		$(this).attr("disabled",true);
		if(validateForm()){
			$.ajax({
				cache: false,
				type:"post",
				url:URL.savePlan,
				data:$('#saveplan').serialize(),// 你的formid
				async: true,
				success: function(rs) {
					$(this).attr("disabled",false);
					if(rs.code=="000000"){
						window.location.href = CONTEXTPATH + "/trwuliu/planowner/main";
					}else{
						alert(rs.error);
					}
				}
			});
		}else{
			$(this).attr("disabled",false);
		}
	});

	
	//表单提交数据验证
	var validateForm=function(){
		var freightid = $("#freightname_v_id").val();
		if(freightid==""){
			alert("请选择运价策略");
			return false;
		}
		var totalplanned = $("#totalplanned").val();
		if(totalplanned==""){
			alert("计划总量不能为空");
			return false;
		}
		var linkman = $("#linkman").val();
		if(linkman==""){
			alert("联系人不能为空");
			return false;
		}
		var telephone = $("#telephone").val();
		if(telephone==""){
			alert("联系电话不能为空");
			return false;
		}
		if( !$("#begintime").val() ){
			alert("开始时间不能为空.");
			return false;
		}
		if( !$("#endtime").val() ){
			alert("结束时间不能为空.");
			return false;
		}
		if($("input[name=venderId]:checked").length==0){
			alert("请选择车主.");
			return false;
		}
		return true;
	}
	
	//切换熟车  车主tabu
	function changeTabu(that){
		var $tab_li = $('.plan_tab ul li');
		var index = $tab_li.index(that);
		$(that).addClass('select').siblings().removeClass('select');
		$('.plan_tabbox > .plan_tabcont').eq(index).show().siblings().hide();
		$("input[name=venderId]:checked").each(function(i,item){
			$(item).attr("checked",false);
		});
	}
});






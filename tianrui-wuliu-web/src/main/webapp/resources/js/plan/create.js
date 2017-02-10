$(function(){
	//左侧选中
	$("#plancreate").addClass("selected");
	$(".select2").select2(); 
	var URL={
		queryFreight:"/trwuliu/planowner/queryFreights",
		queryCargo:"/trwuliu/planowner/queryCargo",
		queryRoute:"/trwuliu/planowner/queryRoute",
		queryFreightInfo:"/trwuliu/planowner/queryFreightInfo",
		savePlan:"/trwuliu/planowner/save"
	}
	innerFreight();
//	init();
	//货物changge事件
	$(".cargoSel").change(function(){
		freightClear();
		if (!$(".cargoSel").val()) {
			cargoClear();
			return;
		}
		innerFreight();
		queryCargo(false);
//		init();
	});
	// 路线change事件
	$(".routeSel").change(function(){
		freightClear();
		if (!$(".routeSel").val()) {
			routeclear();
			return;
		}
		innerFreight();
		queryRoute(false);
//		init();
	});
	// 运价策略change事件
	$(".freightSel").change(function(){
		queryFreightinfoVo(false);
	});
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
	//运价策略 自动加载
	$( "#freightname_v" ).autocomplete({
		minLength: 0,
		max: 12, 
        source: function(request, response){
        	  $.ajax({
                  url: URL.queryFreight,
                  method:"POST",
                  dataType: "json",
                  data:{
                	  key: request.term,
                	  cargoId:$(".cargoSel").val(),
                	  routeId:$(".routeSel").val()
                  },
                  success: function( data ) {
                      response( $.map( data, function( item ) {
                          return {
                              key:item.id,
                              value:item.freightName,
                              cargoid:item.cargoid,
                              routeid:item.routeid,
                              price:item.price,
                              priceunits:item.priceunits,
                              tallage:item.tallage,
                              organizationname:item.organizationname
                          }
                      }));
                  }
              });
        },
        select: function( event, ui ) {
            $("#freightname_v" ).val( ui.item.value );
            $("#freightname_v_id" ).val( ui.item.key );
            $("#hprice").html( ui.item.price);
            $("#hpriceunits").html( ui.item.priceunits);
            $(".cargoSel").val( ui.item.cargoid);
            $(".routeSel").val( ui.item.routeid);
            if(ui.item.tallage && $.isNumeric(ui.item.tallage)){
            	$("#tallage").html(parseInt(ui.item.tallage)+'%');
            }
            $("#organizationname").val(ui.item.organizationname);
            queryFreightinfoVo(true);
            queryCargo(true);
            queryRoute(true);
            //总价
            var totalplanned =$("#totalplanned").val();
            if(totalplanned){
            	var totalPrice =(+(ui.item.price)*totalplanned).toFixed(2)
            	$("#totalPrice").html(".总价:"+totalPrice+"元")
            }
            return false;
        }
    });
	
	//货物信息清理
	var cargoClear =function(){
		$("#hcargono").html("");
		$("#hcargoname").html("");
		$("#hmeasure").html("");
		$("#measure_name").html("");
	}
	//路线信息清理
	var routeclear =function(){
		$("#receivepersion").html("");
		$("#sendpersion").html("");
		$("#hdistance").html("");
		$("#hstartcity").html("");
		$("#hendcity").html("");
	}
	//策略信息清理
	var freightClear=function(){
        $("#freightname_v" ).val( "" );
        $("#freightname_v_id" ).val("");
        $("#hprice").html("");
        $("#hpriceunits").html("");
        $("#totalPrice").html("");
        $("#tallage").html("");
	}
	//判断该运价策略当前状态
	var queryFreightinfoVo = function(flag){
		$.ajax({
			url : URL.queryFreightInfo,
			method : "POST",
			dataType : "json",
			data : {
				id : $(".freightSel").val(),
			},
			success : function(rs) {
				if( rs.code=="000000" ){
					var data = rs.data;
		            $("#hprice").html( data.price);
	            	$("#tallage").html(parseInt(data.tallage)+'%');
	                $("#hpriceunits").html( data.priceunits);
	                $(".cargoSel").val( data.cargoid);
	                $(".routeSel").val( data.routeid);
	                $("#organizationname").val(data.organizationname);
	                queryCargo(true);
	        		queryRoute(true);
				}else{
					alert(rs.error);
				}
			}
		})  
		return false;
	 }
	
	//获取货物详情 flag:false 不重置策略  true 重置策略
	var queryCargo=function(flag){
		$.ajax({
			url : URL.queryCargo,
			method : "POST",
			dataType : "json",
			data : {
				cargoId : $(".cargoSel").val(),
			},
			success : function(rs) {
				if( rs.code=="000000" ){
					var data = rs.data;
					$("#hcargono").html(data.materCode);
					$("#hcargoname").html(data.materName);
					$("#hmeasure").html(data.measUnit);
					$("#measure_name").html(data.measUnit);
					if( !flag ){
						freightClear();
					}
				}
			}
		})  
	}
	//查询路线详情 flag:false 不重置策略  true 重置策略
	var queryRoute=function(flag){
		$.ajax({
			url : URL.queryRoute,
			method : "POST",
			dataType : "json",
			data : {
				routeId : $(".routeSel").val(),
			},
			success : function(rs) {
				if( rs.code=="000000" ){
					var data = rs.data;
					$("#sendname").val(data.sendpersion);
					$("#sendtel").val(data.sendpersionphone);
					$("#recname").val(data.receivepersion);
					$("#rectell").val(data.receivepersionphone);
					$("#hdistance").html(data.distance);
					$("#hstartcity").html(data.oaddr);
					$("#hendcity").html(data.daddr);
					if( !flag ){
						freightClear();
					}
				}
			}
		}) 
	}
	
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
//		if( !$("#shipperMerchant").val() ){
//			alert("请选择发货方.");
//			return false;
//		}
//		if( !$("#consigneeMerchant").val() ){
//			alert("请选择收货方.");
//			return false;
//		}
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
	/** 运价策略下拉框填值*/
	function innerFreight(){
		var cargoSel = $(".cargoSel").val();
		var routeSel = $(".routeSel").val();
		$.ajax({
            url: URL.queryFreight,
            method:"POST",
            data:{
          	  	cargoId:$(".cargoSel").val(),
          	  	routeId:$(".routeSel").val()
            },
            dataType: "json",
            success: function( data ) {
            	$(".freightSel").empty();
            	$(".freightSel").append("<option value=''>请选择运价策略</option>");
	            for (var a = 0; a < data.length; a++) {
	            	 $(".freightSel").append("<option value='"+data[a].id+"'>"+data[a].freightName+"</option>"); 
				}
            }
        });
		
	}
	
	function init(){
		 $.ajax({
             url: URL.queryFreight,
             method:"POST",
             data:{
           	  	cargoId:$(".cargoSel").val(),
           	  	routeId:$(".routeSel").val()
             },
             dataType: "json",
             success: function( data ) {
            	 if(data && data.length==1){
            		  var item =data[0];
            		  $("#freightname_v" ).val( item.desc1 );
                      $("#freightname_v_id" ).val( item.id );
                      $("#hprice").html(item.price);
                      $("#hpriceunits").html( item.priceunits);
                      $(".cargoSel").val( item.cargoid);
                      $(".routeSel").val( item.routeid);
                      queryCargo(true);
                      queryRoute(true);
                      //总价
                      var totalplanned =$("#totalplanned").val();
                      if(totalplanned){
                      	var totalPrice =(+(ui.item.price)*totalplanned).toFixed(2)
                      	$("#totalPrice").html(".总价:"+totalPrice+"元")
                      }
            	 }
             }
         });
	}
});






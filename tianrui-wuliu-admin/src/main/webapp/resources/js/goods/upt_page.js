$(function(){
	innerOption();
	showGoodsList();
});
$("#venderCellphone").on("input propertychange",function(){
	innerOption();
});
function innerOption(){
	 var vender = $("#venderCellphone").val();
	    $.ajax({
	    	url:'/AdminMember/findDriverOwner',
			data:{"cellPhone":$.trim(vender),
				"capaType":"0",
				"pageNo":1,
				"pageSize":10
			},
			type:"post",
			success: function(ret) {
				if(ret.code==000000){
					$("#venderList").empty();
					var data = ret.data.list;
					$(".vender_detail").show();
					celarVender();
					if(data.length == 1){
						innerVender(data[0]);
					}
					for (var a = 0; a < data.length; a++) {
						var hml = "<option value='"+data[a].cellPhone+"'>"+data[a].remarkname+"</option>";
						$("#venderList").append(hml);
					}
				}
			}
	    });
}

function celarVender(){
	$("#vender_cellphone").html("");
	$("#vender_name").html("");
	$("#vender_cp3").html("");
	$("#vender_cp2").html("");
	$("#vender_cp1").html("");
	$("#vender_id").val("");
}

function innerVender(data){
	$("#vender_cellphone").html(data.cellPhone);
	$("#vender_name").html(data.remarkname);
	$("#vender_cp3").html(data.capacount);
	$("#vender_cp2").html(data.mocount);
	$("#vender_cp1").html(data.vdcount);
	$("#vender_id").val(data.id);
	$(".vender_detail").show();
}

$(".save_paln").on("click",function(){
	var price = $("#plan_price").val();
	var weight = $("#plan_weight").val();
	var venderId = $("#vender_id").val();
	var goodsId = $("#goods_id").val();
	var f = true;
	if(Number(price) <= 0){
		f = false;
	}
	if(Number(weight) <= 0){
		f = false;
	}
	if(isNaN(price)){
		f = false;
	}
	if(isNaN(weight)){
		f = false;
	}
	if(!f){
		alert("请输入正确的单价或重量...");
		return ;
	}
	if(venderId == ""){
		alert("请选择车主...");
		return ;
	}
	$.ajax({
		url:"/adminGoods/save",
		data:{goodsid:goodsId,
			venderid:venderId,
			price:price,
			weight:weight
		},
		type:"POST",
		success:function(ret){
			if(ret.code==000000){
				window.location.href=location;
			}else{
				alert(ret.error);
			}
		}
	});
})
/** 查询货物发送记录*/
function showGoodsList(){
	$.ajax({
		url:"/adminGoods/findGoodsPlan",
		data:{goodsId:$("#goods_id").val()},
		type:"POST",
		success:function(ret){
			if(ret.code == 000000){
				showGoodsDetail(ret.data.list);
			}
		}
	});
}

function showGoodsDetail(data){
	$("#goods_list").empty();
	for (var a = 0; a < data.length; a++) {
		var hml = "<li><i class='node-icon'></i>"+
                    "<span class='time'>"+data[a].createtimeStr+"</span>"+
                    "<span class='txt'>派发车主："+data[a].vehicleownername+"-"+data[a].vehicleownerphone+"</span>"+
                    "<span class='zhedie'><img src='"+trRoot+"/images/infor.png'></span>"+
                    "<div class='info_econt' style='display:none'>"+
                        "<div class='info_eleft'>"+
                            "<h3>计划详情</h3>"+
                            "<div class='info_esolo'>"+
                                "<label>计划编码：</label>"+
                                "<span>"+data[a].plancode+"</span>"+
                            "</div>"+
                            "<div class='info_esolo'>"+
                            "<label>货物名称：</label>"+
                            "<span>"+data[a].cargoname+"</span>"+
                            "</div>"+
                            "<div class='info_esolo'>"+
                                "<label>起运地：</label>"+
                                "<span>"+data[a].startcity+"</span>"+
                            "</div>"+
                            "<div class='info_esolo'>"+
                                "<label>目的地：</label>"+
                                "<span>"+data[a].endcity+"</span>"+
                            "</div>"+
                            "<div class='info_esolo'>"+
                                "<label>单价：</label>"+
                                "<span>"+data[a].price+"</span>"+
                            "</div>"+
                            "<div class='info_esolo'>"+
                                "<label>重量：</label>"+
                                "<span>"+data[a].totalplanned+"</span>"+
                            "</div>"+
                        "</div></div></li>";
		
		$("#goods_list").append(hml);
	}
	 $(".zhedie").on('click',function(){
         var div_hs = $(this).next();
         div_hs.toggle();
         if(div_hs.css("display") == 'none') {
         	$(this).children("img").attr('src',trRoot+"/images/infor.png");
         }else {
         	$(this).children("img").attr('src',trRoot+"/images/infol.png");
         }
     });
}


$(".audit").on("click",function(){
	var type = $(this).attr("type");
	$.ajax({
		url:"/adminGoods/auditGoods",
		type:"POST",
		data:{id:$("#goods_id").val(),
			audType:type},
		success:function(ret){
			if(ret.code==000000){
				window.location.href=location;
			}else{
				alert(ret.error);
			}
		}
	});
});

$(".audit_succe").on("click",function(){
	var ptype_1 = "";
	$('input[name="ptype_1"]:checked').each(function(){ 
		//平台推送
		ptype_1 = $(this).val();
	}); 
	var ptype_2 = "";
	$('input[name="ptype_2"]:checked').each(function(){ 
		//APP站内推送
		ptype_2 = $(this).val();
	}); 
	var ptype_3 = "";
	$('input[name="ptype_3"]:checked').each(function(){ 
		//短信推送
		ptype_3 = $(this).val();
	}); 
	
	var type = "0";
	if(ptype_1 + ptype_2 + ptype_3 == "111"){
		type = "3";
	}
	if(ptype_1 + ptype_2 + ptype_3 == "11"){
		if(ptype_2 == "1" && ptype_3 == "1"){
			type = "3";
		}else{
			if(ptype_2 == 1){
				type = "1";
			}
			if(ptype_3 == 1){
				type = "2";
			}
		}
	}
	if(ptype_1 + ptype_2 + ptype_3 == "1"){
		if(ptype_2 == 1){
			type = "1";
		}
		if(ptype_3 == 1){
			type = "2";
		}
		if(ptype_1 == 1){
			type = "9";
		}
	}
	$.ajax({
		url:"/adminGoods/auditGoods",
		type:"POST",
		data:{id:$("#goods_id").val(),
			messageType:type,
			audType:"1"},//审核成功
		success:function(ret){
			if(ret.code==000000){
				window.location.href=location;
			}else{
				alert(ret.error);
			}
		}
	});
	
});





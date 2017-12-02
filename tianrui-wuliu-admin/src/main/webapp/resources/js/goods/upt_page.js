$(function(){
	innerOption();
	showGoodsList();
});
$("#venderCellphone").on("change",function(){
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
					$(".vender_detail").hide();
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
	var price = $("#").val();
	var weight = $("#").val();
	var venderId = $("#vender_id").val();
	var goodsId = $("#goods_id").val();
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
				alert("添加成功");
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

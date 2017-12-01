$(function(){
	innerOption();
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

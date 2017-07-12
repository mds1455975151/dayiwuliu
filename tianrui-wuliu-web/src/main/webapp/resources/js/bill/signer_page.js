var pageSize = 10;
var pageNo = 0;
var hml = "";
$(function(){
	$("#billsigner").addClass("selected");
	$(".goods_more").hide();
	index(0);
});

function index(no){
	$.ajax({
		url:"/trwuliu/billSigner/find",
		data:{"searchKey":$("#searchKey").val(),
			"pageNo":no,
			"pageSize":pageSize
		},
		type : "post",
		dataType:"json",
		success:function(rs){
			if(rs.code=="000000"){
				var total = rs.data.total;
				var pno = rs.data.pageNo;
				var psize = rs.data.pageSize;
				if((pno+1)*psize<total){
					$(".pageMore").show();
				}else{
					$(".pageMore").hide();
				}
				if(total == 0){
					$(".pageNone").show();
				}
				innerHTML(rs.data.list);
				
			}
		}
	});
}

$(".searchBtn").on("click",function(){
	hml = "";
	index(0);
});


function innerHTML(data){
	for (var a = 0; a < data.length; a++) {
		var type = "";
		var billstatus = "";
		if(data[a].billtype == "al"){
			type = "开票运单";
			billstatus = (data[a].signed==undefined?"":data[a].signed);
		}else if(data[a].billtype == "dy"){
			type = "普通运单";
			if(data[a].billstatus ==0){
				billstatus ="新建";
			}else if(data[a].billstatus ==7 || data[a].billstatus==8){
				billstatus ="已拒绝";
			}else if(data[a].billstatus ==1 ){
				billstatus ="已接受";
			}else if(data[a].billstatus ==2 ){
				billstatus ="已提货";
			}else if(data[a].billstatus ==3 ){
				billstatus ="运输中";
			}else if(data[a].billstatus ==4 ){
				billstatus ="已到达";
			}else if(data[a].billstatus ==5 ){
				billstatus ="已卸货";
			}else if(data[a].billstatus ==6 ){
				billstatus ="已完成";
			}else if(data[a].billstatus ==-1 ){
				billstatus ="已收回";
			}
		}
		hml +="<tr>" +
			"<td><a onclick=\"billdetail('"+data[a].id+"','"+data[a].billtype+"')\">"+data[a].billno+"</a></td>" +
			"<td>"+type+"</td>" +
			"<td>"+data[a].cargoname+"</td>" +
			"<td>"+data[a].vehicleno+"</td>" +
			"<td>"+billstatus+"</td>" +
			"<td>"+new Date(data[a].createtime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
			"<td>";
		if(data[a].billstatus ==5){//普通运单签收
			hml +="<a ><button class='btn btnyello delBtn' onclick=\"billSign_('"+data[a].id+"','"+data[a].billtype+"')\">签收</button></a>";
		}else if(data[a].billtype == "al"){//安联运单签收
			if(data[a].billstatus == undefined && data[a].signedStr == '1'){//前台未运价确认
				hml +="<a ><button class='btn btnyello delBtn' onclick=\"billSign_('"+data[a].id+"','"+data[a].billtype+"')\">签收</button></a>";
			}
		}
		if(data[a].confirmPriceB == "1"){
			//后台已运价确认-运价确认按钮消失
		
		}else if(data[a].confirmPriceB == "0" && data[a].confirmPriceA == "1"){
			//前台已确认，后台未运价确认-运价修改
			hml +="<a ><button class='btn btnyello delBtn' onclick=\"yj_queren('"+data[a].id+"','"+data[a].billtype+"','"+data[a].totalprice+"','"+data[a].trueweight+"')\">运价修改</button></a>";
		}else if(data[a].confirmPriceA == "0"){
			//前台未运价确认-运价确认
			hml +="<a ><button class='btn btnyello delBtn' onclick=\"yj_queren('"+data[a].id+"','"+data[a].billtype+"','"+data[a].totalprice+"','"+data[a].trueweight+"')\">运价确认</button></a>";
		}
		
		hml +="<a ><button class='btn btnyello delBtn' onclick=\"billPosition('"+data[a].id+"','"+data[a].billtype+"')\"'>运单跟踪</button></a>" +
			"</td>" +
			"</tr>";
	}
	document.getElementById("innhtml").innerHTML = hml;
}
$(".pageMore").on("click",function(){
	pageNo = pageNo+1;
	index(pageNo);
});
//运价确认
function yj_queren(id,type,totalprice,trueweight){
	$("#deduct_bill_id").val(id);
	$("#deduct_bill_type").val(type);
	$("#totalprice").html(totalprice);
	$("#trueweight").val(trueweight);
	$("#bill_price").val(totalprice/trueweight);
	$("#deduct_weight_misc").val("0");
	$("#deduct_money").val("0");
	$("#deduct_other").val("0");
	$("#deduct_oil_card").val("0");
	$("#true_totalprice").html(totalprice);
	$.ajax({
		url:"/trwuliu/payInvoiceItem_1/billSelectPrice",
		type:"POST",
		data:{"billId":id},
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				
				$("#totalprice").html(data.receptionBillTotalPrice);
				$("#trueweight").val(data.billWeight);
				$("#bill_price").val(data.billPrice);
				
				$("#deduct_weight_misc").val(data.receptionDeductWeightMisc);
				$("#deduct_money").val(data.receptionDeductMoney);
				$("#deduct_other").val(data.receptionDeductOther);
				$("#deduct_oil_card").val(data.receptionDeductOilCard);
				$("#true_totalprice").html((data.receptionBillTotalPrice-data.receptionDeductMoney-data.receptionDeductWeightMisc-data.receptionDeductOther-data.receptionDeductOilCard).toFixed(2));
			}
		}
	});
	$("#yj_queren").modal();
}

//计算实际支付金额
$(".total_price_count").on("change",function(){
	var deduct_weight_misc = $("#deduct_weight_misc").val();
	if(!test_number(deduct_weight_misc)&&deduct_weight_misc!=""){
		alert("输入格式整数最大6位，小数最大2位");
		return ;
	}
	
	var deduct_money = $("#deduct_money").val();
	if(!test_number(deduct_money)&&deduct_money!=""){
		alert("输入格式整数最大6位，小数最大2位");
		return ;
	}
	
	var deduct_other = $("#deduct_other").val();
	if(!test_number(deduct_other)&&deduct_other!=""){
		alert("输入格式整数最大6位，小数最大2位");
		return ;
	}
	
	var deduct_oil_card = $("#deduct_oil_card").val();
	if(!test_number(deduct_oil_card)&&deduct_oil_card!=""){
		alert("输入格式整数最大6位，小数最大2位");
		return ;
	}
	//运单总额
	var totalprice = $("#totalprice").html();
	if(totalprice == ""){
		alert("当前运价为空，不能进行运价确认。");
		return;
	}
	//单价
	var bill_price = $("#bill_price").val();
	//签收量
	var trueweight = $("#trueweight").val();
	$("#totalprice").html(bill_price*trueweight);
	var true_totalprice = (bill_price*trueweight)-deduct_weight_misc-deduct_money-deduct_other-deduct_oil_card;
	$("#true_totalprice").html(true_totalprice.toFixed(2));
	
});
//验证输入数字
function test_number(number){
	if(!(/^\d{1,6}(\.\d{0,2})?$/.test(number)) ){
		return false;
	}else{
		return true;
	}
}
/**运价确认提交*/
$(".confirmPrice").on("click",function(){
	
	var deduct_weight_misc = $("#deduct_weight_misc").val().trim();
	var deduct_money = $("#deduct_money").val().trim();
	var deduct_other = $("#deduct_other").val().trim();
	var deduct_oil_card = $("#deduct_oil_card").val().trim();
	var deduct_bill_id = $("#deduct_bill_id").val().trim();
	var deduct_bill_type = $("#deduct_bill_type").val().trim();
	
	var billPrice = $("#bill_price").val().trim();
	var billTrueWeight = $("#trueweight").val().trim();
	$.ajax({
		url:"/trwuliu/billSigner/confirmTotalPrice",
		type:"POST",
		data:{"id":deduct_bill_id,
			"type":deduct_bill_type,
			"deduct_weight_misc":deduct_weight_misc,
			"deduct_money":deduct_money,
			"deduct_other":deduct_other,
			"deduct_oil_card":deduct_oil_card,
			"billPrice":billPrice,
			"billTrueWeight":billTrueWeight
		},
		success:function(ret){
			if(ret.code=="000000"){
				$(".searchBtn").trigger("click");
				$("#yj_queren").modal("hide");
			}else{
				alert(ret.error);
			}
		}
	});
});

function billSign_(id,type){
	$("#qhbdImgUrl").attr("src","");
	$("#bdimgurl").attr("src","");
	$("#bill_id").val("");
	
	$("#al_signBill_id").val("");
	$("#al_pickupweight").val("");
	$("#pickupimgurlReq_str").val("");
	$("#al_signweight").val("");
	$("#signimgurlReq_str").val("");
	$("#al_trueweight").val("");
	if(type=="dy"){
		//大易运单签收
		$.ajax({
			url:"/trwuliu/billSigner/findDybillDetail",
			type:"POST",
			data:{"id":id},
			success:function(ret){
				var data = ret.data;
				$("#qhbdImgUrl").attr("src",data.pickupimgurl);
				$("#bdimgurl").attr("src",data.signimgurl);
				$("#pickupweight").val(data.pickupweight);
				$("#signweight").val(data.signweight);
				$("#bill_id").val(id);
				$("#pickupweight_li").click();
			}
		});
		$("#signModal").modal();
	}else if(type=="al"){
		//安联运单签收
		$("#al_signBill_id").val(id)
		$("#anlian_signModal").modal();
	}
}

$("#signerWeight").on("click",function(){
	var weighttext = $("#weighttext").val();
	if(weighttext == ""){
		alert("请输入签收量");
		return;
	}
	$.ajax({
		url:"/trwuliu/billowner/signConfirm",
		type:"POST",
		data:{"id":$("#bill_id").val(),
			"weight":weighttext},
		success:function(ret){
			if(ret.code=="000000"){
				$(".searchBtn").trigger("click");
				$("#signModal").modal("hide");
			}else{
				alert(ret.error);
			}
		}
	});
});

$(".signsubmitbtn_al").on("click",function(){
	if($("#al_signBill_id").val()==""){
		alert("操作失败，请刷新页面");
		return;
	}
	if($("#al_trueweight").val()==""){
		alert("请填写签收重量");
		return;
	}
	$.ajax({
		url:"/trwuliu/billSigner/alBillsigner",
		type:"POST",
		data:{"id":$("#al_signBill_id").val(),
			"pickupweight":$("#al_pickupweight").val(),
			"pickupimgurl":$("#pickupimgurlReq_str").val(),
			"signweight":$("#al_signweight").val(),
			"signimgurl":$("#signimgurlReq_str").val(),
			"trueweight":$("#al_trueweight").val()
		},
		success:function(ret){
			if(ret.code=="000000"){
				$(".searchBtn").trigger("click");
				$("#anlian_signModal").modal("hide");
			}else{
				alert(ret.error);
			}
		}
	});
});


$("#pickupweight_li").on("click",function(){
	$("#stateWeightLabel").html("");
    var pick =	$("#pickupweight").val();
	if($("#pickupweight").val() != ""){
		$("#stateWeightLabel").html("提货量："+parseFloat(pick).toFixed(2)+"吨");
	}
});
$("#signweight_li").on("click",function(){
	$("#stateWeightLabel").html("");
	var sign = $("#signweight").val();
	if($("#signweight").val() != ""){
		$("#stateWeightLabel").html("卸货量："+parseFloat(sign).toFixed(2)+"吨");
	}
});

function billdetail(id,type){
	if(type=="dy"){
		window.location.href="/trwuliu/billowner/detail?id="+id;
	}else if(type=="al"){
		window.location.href="/trwuliu/billAnlian/detail?id="+id;
	}
}
function billPosition(id,type){
	if(type=="dy"){
		window.open("/trwuliu/billAppoint/track?id="+id);
	}else if(type=="al"){
		window.open("/trwuliu/billAppoint/tarckAnlian?id="+id);
	}
}

//图片上传
function fileupload(id){
	var file = $("#"+id)[0].files[0];
	var formData = new FormData();
	formData.append("file",file);
	// 后台处理
	$.ajax({
		url : '/upload/add',// 跳转到 action
		data : formData, 
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		type : "post",
		beforeSend : function() {
	        //请求前的处理
			$('#detail').modal({backdrop: 'static', keyboard: false});
			$("#showload").click();
		},
		success : function(result) {
			var ret = result.code;
			var msg = result.error;
			// 错误信息
			if (ret == 000000) {
				$("#"+id+"_str").val(result.data);
				$('#detail').modal("hide");
			}
		}
	});
}

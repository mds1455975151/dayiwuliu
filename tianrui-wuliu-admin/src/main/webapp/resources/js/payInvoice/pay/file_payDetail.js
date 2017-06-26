/**
 * 查询列表
 */
function searchMember(){
	displayData(0);
}
function displayData(pageNo){
	var cellPhone = $("#cellPhone").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:"/pay/InviceDetail1/find",
		data:{
			"cellphone":cellPhone,
			"pageNo":(pageNo),
			"pageSize":pageSize
		},
		type:"post",
		success:function(result){
			if(result.code=="000000"){
				var data = result.data.list;
				$("#totalRecords").html(result.data.total);
		    	document.getElementById("goPage").value = pageNo+1;
		    	var hml = "";
			    if(result.data.total == 0) {
			    	$("#totalPages").html(1);  
			    		hml +='<td colspan="12">';
			    		hml +='<div class="ht_none">';
			    		hml +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
			    		hml +='<div>';
			    		hml +='<h3>唉呀！查询不到您要找的内容</h3>';
			    		hml +='<p>换个查询条件试试吧？</p>';
			    		hml +='</div>';
			    		hml +='</div>';
			    		hml +='</td>';
			    }else {
			    	$("#totalPages").html(parseInt((result.data.total-1)/pageSize+1)); 
			    	for (var a = 0; a < data.length; a++) {
						var d = a+1;
						var bytpe = "";
						if(data[a].billType == "1"){
							bytpe = "司机";
						}
						if(data[a].billType == "2"){
							bytpe = "车主";
						}
						hml +=
							"<tr><td>"+d+"</td>"+
							"<td>"+data[a].billCode+"</td>"+
							"<td>"+data[a].invoiceName+"</td>"+
							"<td>"+(data[a].cargoName || "")+"</td>"+
							"<td>"+bytpe+"</td>"+
							"<td>"+new Date(data[a].createTime).format("yyyy-MM-dd hh:mm:ss")+"</td>"+
							"<td>";
						if(data[a].whetherClose == false){
							hml +="<span><a data-toggle='modal' onclick=\"selectBill('"+data[a].id+"')\" data-target='#audit'>【运价确认】</a></span>";
						}
						hml +="</td></tr>";
					}
			    }   
				document.getElementById("innerHml").innerHTML=hml;
				$("#pagination").pagination(result.data.total, {   
				    callback: pageCallback,   
				    prev_text: '上一页',   
				    next_text: '下一页',   
				    items_per_page:pageSize,   
				    num_display_entries:4,   
				    current_page:pageNo,   
				    num_edge_entries:1, 
				    maxentries: result.data.total, 
				    link_to:"javascript:void(0)"
				}); 
			}else{
				alert(result.error);
			}
		}
	});
}

function selectBill(id){
	$("#billTotalPrice").val("");
	//应付金额
	$("#amountPayable").val("");
	//油卡
	$("#deductOilCard").val("");
	//扣重扣杂
	$("#deductWeightMisc").val("");
	//扣款
	$("#deductMoney").val("");
	//其它款项
	$("#deductOther").val("");
	
	$.ajax({
		url:"/pay/InviceDetail1/findById",
		type:"POST",
		data:{id:id},
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				if(data.remark=="dy"){
					//账单总额
					$("#billTotalPrice").val(data.billPrice*data.billWeight);
				}else if(data.remark=="al"){
					//账单总额
					$("#billTotalPrice").val(data.billPrice);
				}
				//应付金额
				$("#amountPayable").val(data.receptionBillTotalPrice-data.receptionDeductOilCard-data.receptionDeductWeightMisc-data.receptionDeductMoney-data.receptionDeductOther);
				//油卡
				$("#deductOilCard").val(data.receptionDeductOilCard);
				//扣重扣杂
				$("#deductWeightMisc").val(data.receptionDeductWeightMisc);
				//扣款
				$("#deductMoney").val(data.receptionDeductMoney);
				//其它款项
				$("#deductOther").val(data.receptionDeductOther);
				//主键id
				$("#payId").val(id);
				
			}
		}
	});
}

//计算实际支付金额
$(".total_price_count").on("change",function(){
	
	var deduct_weight_misc = $("#deductWeightMisc").val();
	if(!test_number(deduct_weight_misc)&&deduct_weight_misc!=""){
		alert("输入格式整数最大6位，小数最大2位");
		return ;
	}
	
	var deduct_money = $("#deductMoney").val();
	if(!test_number(deduct_money)&&deduct_money!=""){
		alert("输入格式整数最大6位，小数最大2位");
		return ;
	}
	
	var deduct_other = $("#deductOther").val();
	if(!test_number(deduct_other)&&deduct_other!=""){
		alert("输入格式整数最大6位，小数最大2位");
		return ;
	}
	
	var deduct_oil_card = $("#deductOilCard").val();
	if(!test_number(deduct_oil_card)&&deduct_oil_card!=""){
		alert("输入格式整数最大6位，小数最大2位");
		return ;
	}
	//运单总额
	var totalprice = $("#billTotalPrice").val();
	if(totalprice == ""){
		alert("账单总额为空，不能进行运价确认。");
		return;
	}
	var true_totalprice = totalprice-deduct_weight_misc-deduct_money-deduct_other-deduct_oil_card;
	
	$("#amountPayable").val(true_totalprice);
	
});
//验证输入数字
function test_number(number){
	if(!(/^\d{1,6}(\.\d{0,2})?$/.test(number)) ){
		return false;
	}else{
		return true;
	}
}

$("#auditCommit").on("click",function(){
	$.ajax({
		url:"/pay/InviceDetail1/uptPrice",
		data:{"id":$("#payId").val(),
			"backstageBillTotalPrice":$("#amountPayable").val(),
			"backstageDeductWeightMisc":$("#deductWeightMisc").val(),
			"backstageDeductMoney":$("#deductMoney").val(),
			"backstageDeductOther":$("#deductOther").val(),
			"backstageDeductOilCard":$("#deductOilCard").val()
		},
		type:"POST",
		success:function(ret){
			if(ret.code=="000000"){
				$(".closeAudit").click();
				searchMember();
			}else {
				alert(ret.error);
			}
		}
	});
});




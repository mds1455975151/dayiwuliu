/**
 * 查询列表
 */
function searchMember(){
	displayData(0);
}
function displayData(pageNo){
	var code = $("#code").val();
	var invoiceName =  $("#invoiceName").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:"/pay/InviceDetail1/find",
		data:{
			"invoiceName":invoiceName,
			"code":code,
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
							if(data[a].backstageBillTotalPrice==0){
								hml +="<span><a data-toggle='modal' onclick=\"selectBill('"+data[a].id+"')\" data-target='#audit'>【运价确认】</a></span>";
							}else {
								hml +="<span><a data-toggle='modal' onclick=\"selectBill('"+data[a].id+"')\" data-target='#audit'>【运价修改】</a></span>";
							}
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
	//附件图片
	$("#file_fj").val("");
	//附件url
	$("#pay_fj").val("");
	$(".fileinput-remove-button").click();
	$.ajax({
		url:"/pay/InviceDetail1/findById",
		type:"POST",
		data:{id:id},
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				var price = 0;
				
				var appendix = data.appendix==undefined?"<span>未上传</span>":("<span><a href='/imageView/index?imageUrl="+data.appendix+"' target='_blank'>查看图片</a></span>");
				
				$("#appendix").html(appendix);
				if(data.backstageBillTotalPrice != 0){
					//后台已运价确认
					price = data.backstageBillTotalPrice
						-data.backstageDeductMoney
						-data.backstageDeductOilCard
						-data.backstageDeductOther
						-data.backstageDeductWeightMisc;
					//油卡
					$("#deductOilCard").val(data.backstageDeductOilCard);
					//扣重扣杂
					$("#deductWeightMisc").val(data.backstageDeductWeightMisc);
					//扣款
					$("#deductMoney").val(data.backstageDeductMoney);
					//其它款项
					$("#deductOther").val(data.backstageDeductOther);
					
				}else {
					//后台未运价确认
					price = data.receptionBillTotalPrice 
						-data.receptionDeductMoney
						-data.receptionDeductOther
						-data.receptionDeductWeightMisc
						-data.receptionDeductOilCard;
						//油卡
						$("#deductOilCard").val(data.receptionDeductOilCard);
						//扣重扣杂
						$("#deductWeightMisc").val(data.receptionDeductWeightMisc);
						//扣款
						$("#deductMoney").val(data.receptionDeductMoney);
						//其它款项
						$("#deductOther").val(data.receptionDeductOther);
				}
				
				//总额
				$("#billTotalPrice").val(data.receptionBillTotalPrice);
				//应付金额
				$("#amountPayable").val(price);
				
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
	
	$("#amountPayable").val(true_totalprice.toFixed(2));
	
});
//验证输入数字
function test_number(number){
	if(!(/^\d{1,6}(\.\d{0,2})?$/.test(number)) ){
		return false;
	}else{
		return true;
	}
}

//后台运价确认
$("#auditCommit").on("click",function(){
	setTimeout(function(){
		$.ajax({
			url:"/pay/InviceDetail1/uptPrice",
			data:{"id":$("#payId").val(),
				"appendix":$("#pay_fj").val(),
				"backstageDeductWeightMisc":$("#deductWeightMisc").val().trim(),
				"backstageDeductMoney":$("#deductMoney").val().trim(),
				"backstageDeductOther":$("#deductOther").val().trim(),
				"backstageDeductOilCard":$("#deductOilCard").val().trim()
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
	}, 800);
	
});

function fileUpload(type){
	var file = $("#file_"+type)[0].files[0];
	var formData = new FormData();
	formData.append("file",file);
	$.ajax({
		type:"post",
		url:"/upload/add",
		data:formData,
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		success: function(rs) {
			if(rs.code=="000000"){
				$("#pay_"+type).val(rs.data);
			}else{
				alert(rs.error);
			}
		}
	});
}




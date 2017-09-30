function displayData(){
	init(0);
}
function displayData(pageNo){
	var page = $("#recPageNo").val();
	if(page != ""){
		init(page-1);
		$("#recPageNo").val("");
	}else{
		init(pageNo);
	}
}
function getParams(pageNo){
	var cargoName =$("#cargoName").val();
	var payCode =$("#payCode").val();
	var planCode =$("#planCode").val();
	var routeName =$("#routeName").val();
	var sendMan =$("#sendMan").val();
	var sendPersian =$("#sendPersian").val();
	var venderName =$("#venderName").val();
	var receiptMan =$("#receiptMan").val();
	var receiptPerson =$("#receiptPerson").val();
	var payStatus =$("#payStatus").val();
	var paystarttime =$("#paystarttime").val();
	var payendtime =$("#payendtime").val();
	var billstarttime =$("#billstarttime").val();
	var billendtime =$("#billendtime").val();
	
	var params = {
			cargoName:$.trim(cargoName),
			payCode:$.trim(payCode),
			planCode:$.trim(planCode),
			routeName:$.trim(routeName),
			sendMan:$.trim(sendMan),
			sendPersian:$.trim(sendPersian),
			venderName:$.trim(venderName),
			receiptMan:$.trim(receiptMan),
			receiptPerson:$.trim(receiptPerson),
			payStatus:$.trim(payStatus),
			paystarttime:$.trim(paystarttime),
			payendtime:$.trim(payendtime),
			billstarttime:$.trim(billstarttime),
			billendtime:$.trim(billendtime),
			pageNo:(pageNo),
			pageSize:10
		};
	return params;
}
function reset(){
	$("#cargoName").val("");
	$("#payCode").val("");
	$("#planCode").val("");
	$("#routeName").val("");
	$("#sendMan").val("");
	$("#sendPersian").val("");
	$("#venderName").val("");
	$("#receiptMan").val("");
	$("#receiptPerson").val("");
	$("#payStatus").val("");
	$("#paystarttime").val("");
	$("#payendtime").val("");
	$("#billstarttime").val("");
	$("#billendtime").val("");
	init(0);
}
function init(pageNo){
	$.ajax({
		url:"/reportAll/pay",
		type:"POST",
		data:getParams(pageNo),
		success:function(ret){
			if(ret.code == '000000'){
				var data = ret.data.list;
				var total = ret.data.total;
				var pageNo = ret.data.pageNo;
				var pageSize = ret.data.pageSize;
				innerHml(data);
				$('#totalRecords').html(total);
				document.getElementById("goPage").value = pageNo+1;
				$("#totalPages").html(parseInt((total-1)/pageSize+1));
				$('#totalPages').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
				$("#pagination").pagination(total, {
				    callback: pageCallback,
				    prev_text: '上一页',
				    next_text: '下一页',
				    items_per_page:pageSize,
				    num_display_entries:4,
				    current_page:pageNo,
				    num_edge_entries:1,
				    maxentries:total,
				    link_to:"javascript:void(0)"
				});
			}
		}
	});
}

function innerHml(data){
	$("#innerHtml").empty();
	for (var a = 0; a < data.length; a++) {
		var payCreateTime = data[a].payCreateTimeStr == undefined ? "":data[a].payCreateTimeStr;
		var payCode = data[a].payCode == undefined ? "":data[a].payCode;
		var payMent = data[a].payMent == undefined ? "":data[a].payMent;
		var payType = data[a].payType == undefined ? "":data[a].payType;
		var payDriverId = data[a].payDriverId == undefined ? "":data[a].payDriverId;
		var payMentId = data[a].payMentId == undefined ? "":data[a].payMentId;
		if(payMent=="1"){
			payMent="司机"
			payType=payDriverId==payMentId?"司机支付":"委托支付";
		}
		if(payMent=="2"){
			payMent="车主"
			payType="车主支付"	
		}
		var planCode = data[a].planCode == undefined ? "":data[a].planCode;
		var routeName = data[a].routeName == undefined ? "":data[a].routeName;
		var sendMan = data[a].sendMan == undefined ? "":data[a].sendMan;
		var sendPersian = data[a].sendPersian == undefined ? "":data[a].sendPersian;
		var venderName = data[a].venderName == undefined ? "":data[a].venderName;
		var receiptMan = data[a].receiptMan == undefined ? "":data[a].receiptMan;
		var receiptPerson = data[a].receiptPerson == undefined ? "":data[a].receiptPerson;
		var vehicleNo = data[a].vehicleNo == undefined ? "":data[a].vehicleNo;
		var billTime = data[a].billTimeStr == undefined ? "":data[a].billTimeStr;
		var billNo = data[a].billNo == undefined ? "":data[a].billNo;
		var cargoName = data[a].cargoName == undefined ? "":data[a].cargoName;
		var trueWeight = data[a].trueWeight == undefined ? "":data[a].trueWeight;
		var price = data[a].price == undefined ? "":data[a].price;
		var totalPrice = data[a].totalPrice == undefined ? "":data[a].totalPrice;
		var oilCard = data[a].oilCard == undefined ? "":data[a].oilCard;
		var weightMisc = data[a].weightMisc == undefined ? "":data[a].weightMisc;
		var deductMoney = data[a].deductMoney == undefined ? "":data[a].deductMoney;
		var deductOther = data[a].deductOther == undefined ? "":data[a].deductOther;
		var amountPayable = data[a].amountPayable == undefined ? "":data[a].amountPayable;
		var paidAmount = data[a].paidAmount == undefined ? "":data[a].paidAmount;
		var payStatus = data[a].payStatus == undefined ? "":data[a].payStatus;
		if(payStatus=="0"){
			payStatus="未支付"
		}
		if(payStatus=="1"){
			payStatus="支付中"
		}
		if(payStatus=="2"){
			payStatus="已支付"
		}
		if(payStatus=="3"){
			payStatus="支付失败"
		}
		var payPerson = data[a].payPerson == undefined ? "":data[a].payPerson;
		var payBankName = data[a].payBankName == undefined ? "":data[a].payBankName;
		var payBankCode = data[a].payBankCode == undefined ? "":data[a].payBankCode;
		var hml = "<tr>" +
				"<td>"+payCreateTime+"</td>" +
				"<td><span><a data-toggle='modal' onclick=\"pays_detail('"+data[a].id+"')\" data-target='#pay_detail'>"+payCode+"</a></span></td>" +
				"<td>"+payMent+"</td>" +
				"<td><span><a data-toggle='modal' onclick=\"planId('"+data[a].id+"','"+payCode+"','"+1+"')\" data-target='#Plandetail'>"+planCode+"</a></span></td>" +
				"<td>"+routeName+"</td>" +
				"<td>"+sendMan+"</td>" +
				"<td>"+sendPersian+"</td>" +
				"<td>"+venderName+"</td>" +
				"<td>"+receiptMan+"</td>" +
				"<td>"+receiptPerson+"</td>" +
				"<td><span><a data-toggle='modal' onclick=\"vehicle_details('"+vehicleNo+"')\" data-target='#vehicledetail'>"+vehicleNo+"</a></span></td>" +
				"<td>"+billTime+"</td>" +
				"<td><span><a data-toggle='modal' onclick=\"planId('"+data[a].id+"','"+payCode+"','"+2+"')\" data-target='#Billdetail'>"+billNo+"</a></span></td>" +
				"<td>"+cargoName+"</td>" +
				"<td>"+trueWeight+"</td>" +
				"<td>"+price+"</td>" +
				"<td>"+totalPrice+"</td>" +
				"<td>"+oilCard+"</td>" +
				"<td>"+weightMisc+"</td>" +
				"<td>"+deductMoney+"</td>" +
				"<td>"+deductOther+"</td>" +
				"<td>"+amountPayable+"</td>" +
				"<td>"+paidAmount+"</td>" +
				"<td>"+payStatus+"</td>" +
				"<td>"+payType+"</td>" +
				"<td>"+payPerson+"</td>" +
				"<td>"+payBankName+"</td>" +
				"<td>"+payBankCode+"</td>" +
				"</tr>";
		$("#innerHtml").append(hml);
	}
}

$('.printReport').off('click').on('click',function(){
	$('#planReport').jqprint();
});
$('.exportReport').off('click').on('click',function(){
	$.ajax({
		url : '/reportAll/pay',
		data : getParams(),
		type : 'post',
		success : function(result) {
			if(result.code == '000000'){
				if(result.data.total == 0){
					alert("没有要导出的数据！");
				}else if(result.data.total > 20000){
					alert("数据超过20000条，请联系管理员导出！");
				}else{
					var path = '/reportAll/payReport?'+$.param(getParams(1));
					$('<form method="post" action="' + path + '"></form>').appendTo('body').submit().remove();
				}
			}
		}
	});
});


//车辆详情
function vehicle_details(vehicleno){
	$.ajax({
		type:"post",
		data:{"vehicleno":vehicleno.slice(2,7),
			"vehicleprefix":vehicleno.slice(0,2)},
		url:"/AdminMember/findCarManager",
		success:function(ret){
			var d = ret.data.list[0];
			var userName = d.userName;
			if(d.userName == undefined){
				userName = "";
			}
			var telphone = d.telphone;
			if(d.telphone == undefined){
				telphone = "";
			}
			var sta = "";
			if(d.status=="-1"){
				sta = "认证失败";
			}
			if(d.status=="0"){
				sta = "未认证";
			}
			if(d.status=="1"){
				sta = "认证成功";
			}
			if(d.status=="2"){
				sta = "认证中";
			}
			var type = "";
			if(d.vehicletypename!=undefined){
				type = d.vehicletypename;
			}
			var registcode = d.registimage==""?"<span>未上传</span>":("<span><a href='/imageView/index?imageUrl="+d.registimage+"' target='_blank'>查看图片</a></span>");
			var opercode = d.operimage==""?"<span>未上传</span>":("<span>证书编号："+d.opercode+"--<a href='/imageView/index?imageUrl="+d.operimage+"' target='_blank'>查看图片</a></span>");
			var roadtransport = d.roadtransportimage==""?"<span>未上传</span>":("<span>证书编号："+d.roadtransportcode+"--<a href='/imageView/index?imageUrl="+d.roadtransportimage+"' target='_blank'>查看图片</a></span>");
			var roadtransportcode = d.roadtransportcode==""?"未上传":d.roadtransportcode;
			var identitycode = d.identieyimage==""?"<span>未上传</span>":("<span>证书编号："+d.identitycode+"--<a href='/imageView/index?imageUrl="+d.identieyimage+"' target='_blank'>查看图片</a></span>");
			var hml = 
				"<div class='file_detail'><label>车牌号前缀：</label><span>"+d.vehicleprefix+"</span></div>"+
				"<div class='file_detail'><label>车牌号：</label><span>"+d.vehicleno+"</span></div>"+
				"<div class='file_detail'><label>所有人姓名：</label><span>"+userName+"</span></div>"+
				"<div class='file_detail'><label>联系方式：</label><span>"+telphone+"</span></div>"+
				"<div class='file_detail'><label>车型：</label><span>"+type+"</span></div>"+
				"<div class='file_detail'><label>载重：</label><span>"+d.vehiweight+"吨</span></div>"+
				"<div class='file_detail'><label>长度：</label><span>"+d.vehilength+"米</span></div>"+
				"<div class='file_detail'><label>认证状态：</label><span>"+sta+"</span></div>"+
				"<div class='file_detail'><label>认证时间：</label><span>"+d.createtimeStr+"</span></div>"+
				"<div class='file_detail2'><label>车辆登记证：</label>"+registcode+"</div>"+
				"<div class='file_detail2'><label>经营许可证号：</label>"+opercode+"</div>"+
				"<div class='file_detail2'><label>道路运输证号：</label>"+roadtransportcode+"</div>"+
				"<div class='file_detail2'><label>车辆照片：</label><span><a href='/imageView/index?imageUrl="+d.vehiheadimgpath+"' target='_blank'>查看图片</a></span></div>"+
				"<div class='file_detail2'><label>行驶证照片：</label><span><a href='/imageView/index?imageUrl="+d.vehilicenseimgpath+"' target='_blank'>查看图片</a></span></div>";
			$("#vehicledetailhml").html(hml);
		}
	});
}

function planId(id,payCode,tp){
	$.ajax({
		type:"post",
		data:{"id":id,"payCode":payCode},
		url:"/pay/InviceDetail1/findPlanId",
		success:function(ret){
			var d = ret.data;
			if(tp=="1"){
				plan_datails(d.planId)
			}
			if(tp=="2"){
				bill_details(d.billId,d.remark)
			}
			
		}
	})
}

//计划详情
function plan_datails(id){
	$.ajax({
		type:"post",
		data:{"id":id},
		url:"/admin/waybill/findPlanByid",
		success:function(ret){
			if(ret.code=="000000"){
				var d = ret.data;
				var hml = "<div class='file_detail'><label>计划编码：</label><span>"+trimVal(d.plancode)+"</span></div>"+
					"<div class='file_detail'><label>组织名称：</label><span>"+trimVal(d.orgname)+"</span></div>"+
					"<div class='file_detail'><label>创建人：</label><span>"+trimVal(d.ownerName)+"</span></div>"+
					"<div class='file_detail'><label>货物名称：</label><span>"+trimVal(d.cargoname)+"</span></div>"+
					"<div class='file_detail'><label>车主：</label><span>"+trimVal(d.vehicleownername)+"</span></div>"+
					"<div class='file_detail'><label>运价策略：</label><span>"+trimVal(d.freightname)+"</span></div>"+
					"<div class='file_detail'><label>计量单位：</label><span></span>"+trimVal(d.measure)+"</div>"+
					"<div class='file_detail'><label>计价单位：</label><span>"+trimVal(d.priceunits)+"</span></div>"+
					"<div class='file_detail'><label>单价：</label><span></span>"+trimVal(d.price)+"</div>"+
					"<div class='file_detail'><label>起运地：</label><span>"+trimVal(d.startcity)+"</span></div>"+
					"<div class='file_detail'><label>目的地：</label><span>"+trimVal(d.endcity)+" </span></div>"+
					"<div class='file_detail'><label>结算里程数：</label><span>"+trimVal(d.distance)+"</span></div>"+
					"<div class='file_detail'><label>计划总量：</label><span>"+trimVal(d.totalplanned)+"</span></div>"+
					"<div class='file_detail'><label>计划费用：</label><span>"+trimVal(d.planprice)+"元</span></div>"+
					"<div class='file_detail'><label>联系人：</label><span>"+trimVal(d.linkman)+"</span></div>"+
					"<div class='file_detail'><label>联系电话：</label><span>"+trimVal(d.telephone)+"</span></div>"+
					"<div class='file_detail'><label>开始时间：</label><span>"+trimVal(d.starttimeStr)+"</span></div>"+
					"<div class='file_detail'><label>结束时间：</label><span>"+trimVal(d.endtimeStr)+"</span></div>"+
					"<div class='file_detail'><label>创建时间：</label><span>"+trimVal(d.createtimeStr)+"</span></div>"+
					"<div class='clear'></div>";	
				$("#Plandetailhml").html(hml);
			}
		}
	});
}

function  trimVal(a){
	if(a ){
		return a;
	}else{
		return "";
	}
}


//查看账单详情
function pays_detail(id){
	$.ajax({
		type:"POST",
		data:{"payInvoiceId":id},
		url:"/pay/InviceDetail1/find",
		success:function(ret){
			if(ret.data.total!=0){
				var data = ret.data.list;
				var hml = "";
				for (var a = 0; a < data.length; a++) {
					var billType = "";
					if(data[a].billType==1){
						billType = "司机";
					}else if(data[a].billType==2){
						billType = "车主";
					}
					hml +="<tr>" +
							"<td>"+(a+1)+"</td>" +
							"<td>"+data[a].billCode+"</td>" +
							"<td>"+data[a].invoiceName+"</td>" +
							"<td>"+data[a].cargoName+"</td>" +
							"<td>"+billType+"</td>" +
							"<td><a onclick=\"detail_pay('"+data[a].id+"')\">【详情】</a></td>" +
							"</tr>";
				}
				$("#paylist").html(hml);
			}
		}
	});
}

function detail_pay(id){
	var menuId = $("#menuId").val();
	window.open("/pay/InviceDetail1/payInviceDetail?menuId="+menuId+"&id="+id);
}



/**
 * 运单详情
 * @param id
 * @param type
 */
function bill_details(id,type){
	var t = "";
	var urls = "";
	if(type=="al"){
		t = "a";
		urls = "/admin/waybill/findAnlianBillId";
	}else{
		t = "w";
		urls = "/admin/waybill/findWaybillByid";
	}
	$.ajax({
		type:"post",
		data:{"id":id,
			"type":t
		},
		url:urls,
		success:function(ret){
			var data = ret.data;
			if(t=="w"){
				bill_innerHml(data);
			}else{
				anlian_bill_innerHml(data);
			}
		}
	});
}

function anlian_bill_innerHml(data){
	var hml = "<div class='file_detail'><label>运单号：</label><span>"+data.billno+"</span></div>"+
	"<div class='file_detail'><label>货物名称：</label><span>"+data.hpmc+"</span></div>"+
	"<div class='file_detail'><label>承运方：</label><span>"+data.systemShipper+"</span></div>"+
	"<div class='file_detail'><label>创建时间：</label><span>"+data.createtimeStr+"</span></div>"+
	"<div class='file_detail'><label>计价单位：</label><span>"+data.dw+"</span></div>"+
	"<div class='file_detail'><label>起运地：</label><span>"+data.qycs+"</span></div>"+
	"<div class='file_detail'><label>目的地：</label><span>"+data.mdcs+"</span></div>"+
	"<div class='file_detail'><label>结算里程数：</label><span>"+data.lc+" </span></div>"+
	"<div class='file_detail'><label>收货人：</label><span>"+data.shr+" </span></div>"+
	"<div class='file_detail'><label>联系手机：</label><span>"+data.lxsj+"</span></div>"+
	"<div class='file_detail'><label>运输量：</label><span>"+data.sl+"吨</span></div>"+
	"<div class='file_detail'><label>要求提货日期：</label><span>"+data.yqthrq+"</span></div>"+
	"<div class='file_detail'><label>要求到货日期：</label><span>"+data.yqdhrq+"</span></div>"+
	"<div class='file_detail'><label>总运费：</label><span>"+data.yf+"元</span></div>"+
	"<div class='file_detail'><label>车辆：</label><span>"+data.cph+"</span></div>"+
	"<div class='file_detail'><label>司机(安联)：</label><span>"+data.sj+"</span></div>"+
	"<div class='file_detail'><label>联系方式：</label><span>"+data.drivertel+"</span></div>"+
	"<div class='file_detail2'><label>运单状态：</label><span>"+data.status+"</span>" +
	"<div class='clear'></div>";
	$("#Billdetailhml").html(hml);
}

function bill_innerHml(data){
	var orgName = data.orgName;
	if(orgName == undefined){
		orgName = "";
	}
	var venderName = data.venderName;
	if(venderName == undefined){
		venderName = "";
	}
	
	//提货磅单
	var pickupimgurl = data.pickupimgurl==undefined?"<span>未上传</span>":("<span><a href='/imageView/index?imageUrl="+data.pickupimgurl+"' target='_blank'>查看图片</a></span>");
	//卸货
	var signimgurl = data.signimgurl==undefined?"<span>未上传</span>":("<span><a href='/imageView/index?imageUrl="+data.signimgurl+"' target='_blank'>查看图片</a></span>");
	//提货位置偏差
	var q_deviation = data.q_deviation == undefined?"":data.q_deviation;
	//到货位置偏差
	var d_deviation = data.d_deviation == undefined?"":data.d_deviation;
	
	var hml = "<div class='file_detail'><label>运单编码：</label><span>"+data.waybillno+"</span></div>"+
				"<div class='file_detail'><label>组织名称：</label><span>"+orgName+"</span></div>"+
				"<div class='file_detail'><label>承运商：</label><span>"+venderName+"</span></div>"+
				"<div class='file_detail'><label>创建时间：</label><span>"+data.createtimeStr+"</span></div>"+
				"<div class='file_detail'><label>货物名称：</label><span>"+data.cargoname+"</span></div>"+
				"<div class='file_detail'><label>计量单位：</label><span>"+data.desc1+"</span></div>"+
				"<div class='file_detail'><label>计价单位：</label><span>"+data.priceunits+"</span></div>"+
				"<div class='file_detail'><label>起运地：</label><span>"+data.startcity+" </span></div>"+
				"<div class='file_detail'><label>目的地：</label><span>"+data.endcity+" </span></div>"+
				"<div class='file_detail'><label>结算里程数：</label><span>"+data.distance+"</span></div>"+
				"<div class='file_detail'><label>发货人：</label><span>"+data.consignorname+data.consignortel+"</span></div>"+
				"<div class='file_detail'><label>收货人：</label><span>"+data.receivername+data.receivertel+"</span></div>"+
				"<div class='file_detail'><label>开始时间：</label><span>"+data.starttime+"</span></div>"+
				"<div class='file_detail'><label>结束时间：</label><span>"+data.endtime+"</span></div>"+
				"<div class='file_detail'><label>原发运输量：</label><span>"+data.weight+"吨</span></div>"+
				"<div class='file_detail'><label>签收运输量：</label><span>"+data.trueweight+"吨</span></div>"+
				"<div class='file_detail'><label>运单价格：</label><span>"+data.price+"元</span></div>"+
				"<div class='file_detail2'><label>车辆信息：</label><span>"+data.vehicleno+"</span>" +
				"<span>"+data.drivername+"</span><span>"+data.drivertel+"</span></div>"+
				"<div class='file_detail'><label>提货磅单：</label><span>"+pickupimgurl+"</span></div>"+
				"<div class='file_detail'><label>卸货磅单：</label><span>"+signimgurl+"</span></div>"+
				"<div class='file_detail'><label>提货位置偏差：</label><span>"+q_deviation+"米</span></div>"+
				"<div class='file_detail'><label>卸货位置偏差：</label><span>"+d_deviation+"米</span></div>"+
				"<div class='clear'></div>";
				$("#Billdetailhml").html(hml);
}

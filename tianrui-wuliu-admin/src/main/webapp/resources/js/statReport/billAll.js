function driverSearch(){
	init(0);
	 billCount();
}
function displayData(pageNo){
	var page = $("#recPageNo").val();
	if(page != ""){
		init(page-1);
		 billCount();
		$("#recPageNo").val("");
	}else{
		init(pageNo);
		 billCount();
	}
}
function reset(){
	$("#billType").val("");
	$("#planNo").val("");
	$("#billNo").val("");
	$("#cargoName").val("");
	$("#vehicleNo").val("");
	$("#routeName").val("");
	$("#billStatus").val("");
	$("#driverName").val("");
	$("#payMent").val("");
	$("#sendMan").val("");
	$("#sendPersion").val("");
	$("#receiptMan").val("");
	$("#receiptPersion").val("");
	$("#businessTimeStart").val("");
	$("#businessTimeEnd").val("");
	$("#billCreaterTimeStart").val("");
	$("#billCreaterTimeEnd").val("");
	$("#acceptTimeStart").val("");
	$("#acceptTimeEnd").val("");
	$("#pickupTimeStart").val("");
	$("#pickupTimeEnd").val("");
	$("#unloadTimeStart").val("");
	$("#unloadTimeEndtime").val("");
	$("#signTimeStart").val("");
	$("#signTimeEnd").val("");
	$("#ownerName").val("");
	init(0);
}
function getParams(pageNo){
	var billType =$("#billType").val();
	var planNo =$("#planNo").val();
	var billNo =$("#billNo").val();
	var cargoName =$("#cargoName").val();
	var vehicleNo =$("#vehicleNo").val();
	var routeName =$("#routeName").val();
	var billStatus =$("#billStatus").val();
	var driverName =$("#driverName").val();
	var payMent =$("#payMent").val();
	var sendMan =$("#sendMan").val();
	var sendPersion =$("#sendPersion").val();
	var receiptMan =$("#receiptMan").val();
	var receiptPersion =$("#receiptPersion").val();
	var ownerName = $("#ownerName").val();
	var businessTimeStart =$("#businessTimeStart").val();
	var businessTimeEnd =$("#businessTimeEnd").val();
	var billCreaterTimeStart =$("#billCreaterTimeStart").val();
	var billCreaterTimeEnd =$("#billCreaterTimeEnd").val();
	var acceptTimeStart =$("#acceptTimeStart").val();
	var acceptTimeEnd =$("#acceptTimeEnd").val();
	var pickupTimeStart =$("#pickupTimeStart").val();
	var pickupTimeEnd =$("#pickupTimeEnd").val();
	var unloadTimeStart =$("#unloadTimeStart").val();
	var unloadTimeEndtime =$("#unloadTimeEndtime").val();
	var signTimeStart =$("#signTimeStart").val();
	var signTimeEnd =$("#signTimeEnd").val();
	
	var params = {
			billType:$.trim(billType),
			planNo:$.trim(planNo),
			billNo:$.trim(billNo),
			cargoName:$.trim(cargoName),
			vehicleNo:$.trim(vehicleNo),
			routeName:$.trim(routeName),
			billStatus:$.trim(billStatus),
			driverName:$.trim(driverName),
			payMent:$.trim(payMent),
			sendMan:$.trim(sendMan),
			sendPersion:$.trim(sendPersion),
			receiptMan:$.trim(receiptMan),
			receiptPersion:$.trim(receiptPersion),
			businessTimeStart:$.trim(businessTimeStart),
			businessTimeEnd:$.trim(businessTimeEnd),
			billCreaterTimeStart:$.trim(billCreaterTimeStart),
			billCreaterTimeEnd:$.trim(billCreaterTimeEnd),
			acceptTimeStart:$.trim(acceptTimeStart),
			acceptTimeEnd:$.trim(acceptTimeEnd),
			pickupTimeStart:$.trim(pickupTimeStart),
			pickupTimeEnd:$.trim(pickupTimeEnd),
			unloadTimeStart:$.trim(unloadTimeStart),
			unloadTimeEndtime:$.trim(unloadTimeEndtime),
			signTimeStart:$.trim(signTimeStart),
			signTimeEnd:$.trim(signTimeEnd),
			ownerName:$.trim(ownerName),
			pageNo:(pageNo),
			pageSize:10
		};
	return params;
}
function billCount(){
	$.ajax({
		url:"/reportAll/billCount",
		type:"POST",
		data:getParams(),
		success:function(ret){
			if(ret.code=='000000'){
				var data = ret.data;
				var distinctCount = parseFloat(data.distinctCount) == undefined ? "0.00":parseFloat(data.distinctCount);
				var venderWeightCount = parseFloat(data.venderWeightCount) == undefined ? "0.00":parseFloat(data.venderWeightCount);
				var pickupWeightCount = parseFloat(data.pickupWeightCount) == undefined ? "0.00":parseFloat(data.pickupWeightCount);
				var unloadWeightCount = parseFloat(data.unloadWeightCount) == undefined ? "0.00":parseFloat(data.unloadWeightCount);
				var trueWeightCount = parseFloat(data.trueWeightCount) == undefined ? "0.00":parseFloat(data.trueWeightCount);
				$('#distinctCount').html(distinctCount.toFixed(2));
				$('#venderWeightCount').html(venderWeightCount.toFixed(2));
				$('#pickupWeightCount').html(pickupWeightCount.toFixed(2));
				$('#unloadWeightCount').html(unloadWeightCount.toFixed(2));
				$('#trueWeightCount').html(trueWeightCount.toFixed(2));
			}else{
				$('#distinctCount').html('0.00');
				$('#venderWeightCount').html('0.00');
				$('#pickupWeightCount').html('0.00');
				$('#unloadWeightCount').html('0.00');
				$('#trueWeightCount').html('0.00');
			}
		}
	})
}
function init(pageNo){
	$.ajax({
		url:"/reportAll/bill",
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
	 billCount();
}

function innerHml(data){
	$("#innerHtml").empty();
	for (var a = 0; a < data.length; a++) {
		var billType = data[a].billType == undefined ? "":data[a].billType;
		var billStatus = data[a].billStatus == undefined ? "":data[a].billStatus;
		if(billType=="dy"){
			billType="大易运单"
		}
		if(billType=="al"){
			billType="安联运单"
		}
		var businessTime = data[a].businessTimeStr == undefined ? "":data[a].businessTimeStr;
		var planNo = data[a].planNo == undefined ? "":data[a].planNo;
		var billNo = data[a].billNo == undefined ? "":data[a].billNo;
		var sendMan = data[a].sendMan == undefined ? "":data[a].sendMan;
		var sendPersion = data[a].sendPersion == undefined ? "":data[a].sendPersion;
		var receiptMan = data[a].receiptMan == undefined ? "":data[a].receiptMan;
		var receiptPersion = data[a].receiptPersion == undefined ? "":data[a].receiptPersion;
		var vehicleNo = data[a].vehicleNo == undefined ? "":data[a].vehicleNo;
		var cargoName = data[a].cargoName == undefined ? "":data[a].cargoName;
		var routeName = data[a].routeName == undefined ? "":data[a].routeName;
		var distinct = data[a].distinct == undefined ? "":data[a].distinct;
		var venderWeight = data[a].venderWeight == undefined ? "":data[a].venderWeight;
		var pickupWeight = data[a].pickupWeight == undefined ? "":data[a].pickupWeight;
		var unloadWeight = data[a].unloadWeight == undefined ? "":data[a].unloadWeight;
		var trueWeight = data[a].trueWeight == undefined ? "":data[a].trueWeight;
		var driverName = data[a].driverName == undefined ? "":data[a].driverName;
		var ownerName = data[a].ownerName == undefined ? "":data[a].ownerName;
		var payMent = data[a].payMent == undefined ? "":data[a].payMent;
		if(payMent=="1"){
			payMent="司机"
		}
		if(payMent=="2"){
			payMent="车主"
		}
		var billCreaterTimeStr = data[a].billCreaterTimeStr == undefined ? "":data[a].billCreaterTimeStr;
		var acceptTimeStr = data[a].acceptTimeStr == undefined ? "":data[a].acceptTimeStr;
		var pickupTimeStr = data[a].pickupTimeStr == undefined ? "":data[a].pickupTimeStr;
		var unloadTimeStr = data[a].unloadTimeStr == undefined ? "":data[a].unloadTimeStr;
		var signTimeStr = data[a].signTimeStr == undefined ? "":data[a].signTimeStr;
		var hml = "<tr>" +
				"<td><a onclick=\"bill_map('"+billType+"','"+data[a].id+"')\"><span>"+billType+"</span></a></td>" +
				"<td>"+businessTime+"</td>" +
				"<td><span><a data-toggle='modal' onclick=\"planId('"+data[a].id+"','"+data[a].billType+"')\" data-target='#Plandetail'>"+planNo+"</a></span></td>" +
				"<td><span><a data-toggle='modal' onclick=\"bill_details('"+data[a].id+"','"+billType+"')\" data-target='#Billdetail'>"+billNo+"</a></span></td>" +
				"<td>"+sendMan+"</td>" +
				"<td>"+sendPersion+"</td>" +
				"<td>"+receiptMan+"</td>" +
				"<td>"+receiptPersion+"</td>" +
				"<td><span><a data-toggle='modal' onclick=\"vehicle_details('"+vehicleNo+"')\" data-target='#vehicledetail'>"+vehicleNo+"</a></span></td>" +
				"<td>"+cargoName+"</td>" +
				"<td>"+routeName+"</td>" +
				"<td>"+distinct+"</td>" +
				"<td>"+venderWeight+"</td>" +
				"<td>"+pickupWeight+"</td>" +
				"<td>"+unloadWeight+"</td>" +
				"<td>"+trueWeight+"</td>" +
				"<td>"+(getBillStatus(data[a].billStatus)||"")+"</td>" +
				"<td>"+driverName+"</td>" +
				"<td>"+ownerName+"</td>" +
				"<td>"+payMent+"</td>" +
				"<td>"+billCreaterTimeStr+"</td>" +
				"<td>"+acceptTimeStr+"</td>" +
				"<td>"+pickupTimeStr+"</td>" +
				"<td>"+unloadTimeStr+"</td>" +
				"<td>"+signTimeStr+"</td>" +
				"</tr>";
		$("#innerHtml").append(hml);
	}
}

$('.printReport').off('click').on('click',function(){
	$('#planReport').jqprint();
});
$('.exportReport').off('click').on('click',function(){
	$.ajax({
		url : '/reportAll/bill',
		data : getParams(),
		type : 'post',
		success : function(result) {
			if(result.code == '000000'){
				//alert(result.data.total);
				if(result.data.total == 0){
					alert("没有要导出的数据！");
				}else if(result.data.total > 20000){
					alert("数据超过20000条，请联系管理员导出！");
				}else{
					var path = '/reportAll/billReport?'+$.param(getParams(1));
					$('<form method="post" action="' + path + '"></form>').appendTo('body').submit().remove();
				}
			}
		}
	});
});

function getBillStatus(sta){
	var status = "";
	switch (sta) {
	case "-1":
		status = "车主回收";
		break;
	case "0":
		status = "司机未确认";
		break;
	case "1":
		status = "司机已接受";
		break;	
	case "2":
		status = "司机已装货";
		break;
	case "3":
		status = "司机运输中";
		break;
	case "4":
		status = "司机已到达";
		break;
	case "5":
		status = "司机已卸货";
		break;	
	case "6":
		status = "已签收";
		break;
	case "7":
		status = "司机拒绝接单";
		break;	
	default:
		status = sta;
		break;
	}
	return status;
}


function planId(id,type){
	$.ajax({
		type:"post",
		data:{"id":id,"billType":type},
		url:"/admin/waybill/findPlanId",
		success:function(ret){
			var d = ret.data;
			plan_datails(d);
		}
	})
}
/**
 * 计划详情
 * @param id
 */
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


/**
 * 运单详情
 * @param id
 * @param type
 */
function bill_details(id,type){
	var t = "";
	var urls = "";
	if(type=="安联运单"){
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
	//提货磅单
	var pickupimgurl = data.pickupimgurl==undefined?"<span>未上传</span>":("<span><a href='/imageView/index?imageUrl="+data.pickupimgurl+"' target='_blank'>查看图片</a></span>");
	//卸货榜单
	var signimgurl = data.signimgurl==undefined?"<span>未上传</span>":("<span><a href='/imageView/index?imageUrl="+data.signimgurl+"' target='_blank'>查看图片</a></span>");
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
	"<div class='file_detail'><label>提货榜单：</label><span>"+pickupimgurl+"</span></div>"+
	"<div class='file_detail'><label>卸货榜单：</label><span>"+signimgurl+"</span></div>"+
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

/**
 * 车辆详情
 * @param vehicleno
 */
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

/**
 * 轨迹查看
 * @param type
 * @param id
 */
function bill_map(type,id){
	if(type=="大易运单"){
		type = "w";	
	}else{
		type = "a";
	}
	window.open("/report/map?type="+type+"&id="+id+"&menuId=7");
}

$("#billType").on("change",function(){
	$(".bill_status").show();
	$("#billStatus").val("");
	var type = $("#billType").val();
	if(type == "dy"){
		$(".alstatus").hide();
	}else if(type == "al"){
		$(".dystatus").hide();
	}
})

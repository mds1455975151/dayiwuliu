function displayData(){
	init(0);
	planCount();
}
function displayData(pageNo){
	var page = $("#recPageNo").val();
	if(page != ""){
		init(page-1);
		planCount();
		$("#recPageNo").val("");
	}else{
		init(pageNo);
		planCount();
	}
}
function getParams(pageNo){
	var planStarttime =$("#planStarttime").val();
	var planEndtime =$("#planEndtime").val();
	var planCode =$("#planCode").val();
	var planStatus =$("#planStatus").val();
	var routeName =$("#routeName").val();
	var cargoName =$("#cargoName").val();
	var sendMan =$("#sendMan").val();
	var sendPersion =$("#sendPersion").val();
	var venderName =$("#venderName").val();
	var receiptMan =$("#receiptMan").val();
	var receiptPersion =$("#receiptPersion").val();
	var payMent =$("#payMent").val();
	var params = {
			planStarttime:$.trim(planStarttime),
			planEndtime:$.trim(planEndtime),
			planCode:$.trim(planCode),
			planStatus:$.trim(planStatus),
			routeName:$.trim(routeName),
			cargoName:$.trim(cargoName),
			sendMan:$.trim(sendMan),
			sendPersion:$.trim(sendPersion),
			venderName:$.trim(venderName),
			receiptMan:$.trim(receiptMan),
			receiptPersion:$.trim(receiptPersion),
			payMent:$.trim(payMent),
			pageNo:(pageNo),
			pageSize:10
		};
	return params;
}
function reset(){
	$("#planStarttime").val("");
	$("#planEndtime").val("");
	$("#planCode").val("");
	$("#planStatus").val("");
	$("#routeName").val("");
	$("#cargoName").val("");
	$("#sendMan").val("");
	$("#sendPersion").val("");
	$("#venderName").val("");
	$("#receiptMan").val("");
	$("#receiptPersion").val("");
	$("#payMent").val("");
	init(0);
}
function planCount(){
	$.ajax({
		url:"/reportAll/planCount",
		type:"POST",
		data:getParams(),
		success:function(ret){
			var data = ret.data;
			if(ret.code=='000000'){
				var planWeightCount = parseFloat(data.planWeightCount) == undefined ? "0.00":parseFloat(data.planWeightCount);
				var complitWeightCount = parseFloat(data.complitWeightCount) == undefined ? "0.00":parseFloat(data.complitWeightCount);
				var tempoCount = parseFloat(data.tempoCount) == undefined ? "0.00":parseFloat(data.tempoCount);
				var distantCount = parseFloat(data.distantCount) == undefined ? "0.00":parseFloat(data.distantCount);
				var priceCount = parseFloat(data.priceCount) == undefined ? "0.00":parseFloat(data.priceCount);
				var taxCount = parseFloat(data.taxCount) == undefined ? "0.00":parseFloat(data.taxCount);
				$('#planWeightCount').html(planWeightCount.toFixed(2));
				$('#complitWeightCount').html(complitWeightCount.toFixed(2));
				$('#tempoCount').html(tempoCount.toFixed(2));
				$('#distantCount').html(distantCount.toFixed(2));
				$('#priceCount').html(priceCount.toFixed(2));
				$('#taxCount').html(taxCount.toFixed(2));
			}else{
				$('#planWeightCount').html('0.00');
				$('#complitWeightCount').html('0.00');
				$('#tempoCount').html('0.00');
				$('#distantCount').html('0.00');
				$('#priceCount').html('0.00');
				$('#taxCount').html('0.00');
			}
		}
	})
}
function init(pageNo){
	$.ajax({
		url:"/reportAll/plan",
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
		var planCreateTimeStr = data[a].planCreateTimeStr == undefined ? "":data[a].planCreateTimeStr;
		var planCode = data[a].planCode == undefined ? "":data[a].planCode;
		var planBeginTimeStr = data[a].planBeginTimeStr == undefined ? "":data[a].planBeginTimeStr;
		var planEndTimeStr = data[a].planEndTimeStr == undefined ? "":data[a].planEndTimeStr;
		var planWeight = data[a].planWeight == undefined ? "":data[a].planWeight;
		var complitWeight = data[a].complitWeight == undefined ? "":data[a].complitWeight;
		var tempo = data[a].tempo == undefined ? "":data[a].tempo;
		var planStatus = data[a].planStatus == undefined ? "":data[a].planStatus;
		if(planStatus=="-1"){
			planStatus="已删除"
		}
		if(planStatus=="0"){
			planStatus="新建"
		}
		if(planStatus=="1"){
			planStatus="待接单"
		}
		if(planStatus=="2"){
			planStatus="执行中"
		}
		if(planStatus=="3"){
			planStatus="已完成"
		}
		var cargoName = data[a].cargoName == undefined ? "":data[a].cargoName;
		var routeName = data[a].routeName == undefined ? "":data[a].routeName;
		var sendMan = data[a].sendMan == undefined ? "":data[a].sendMan;
		var sendPersion = data[a].sendPersion == undefined ? "":data[a].sendPersion;
		var venderName = data[a].venderName == undefined ? "":data[a].venderName;
		var receiptMan = data[a].receiptMan == undefined ? "":data[a].receiptMan;
		var receiptPersion = data[a].receiptPersion == undefined ? "":data[a].receiptPersion;
		var distant = data[a].distant == undefined ? "":data[a].distant;
		var price = data[a].price == undefined ? "":data[a].price;
		var tax = data[a].tax == undefined ? "":data[a].tax;
		var payMent = data[a].payMent == undefined ? "":data[a].payMent;
		if(payMent=="1"){
			payMent="司机"
		}
		if(payMent=="2"){
			payMent="车主"
		}
		var hml = "<tr>" +
				"<td>"+planCreateTimeStr+"</td>" +
				"<td><span><a data-toggle='modal' onclick=\"plan_datails('"+data[a].id+"')\" data-target='#Plandetail'>"+planCode+"</a></span></td>" +
				"<td>"+planBeginTimeStr+"</td>" +
				"<td>"+planEndTimeStr+"</td>" +
				"<td>"+planWeight+"</td>" +
				"<td>"+complitWeight+"</td>" +
				"<td>"+tempo+"</td>" +
				"<td>"+planStatus+"</td>" +
				"<td>"+cargoName+"</td>" +
				"<td>"+routeName+"</td>" +
				"<td>"+sendMan+"</td>" +
				"<td>"+sendPersion+"</td>" +
				"<td>"+venderName+"</td>" +
				"<td>"+receiptMan+"</td>" +
				"<td>"+receiptPersion+"</td>" +
				"<td>"+distant+"</td>" +
				"<td>"+price+"</td>" +
				"<td>"+tax+"</td>" +
				"<td>"+payMent+"</td>" +
				"</tr>";
		$("#innerHtml").append(hml);
	}
}

$('.printReport').off('click').on('click',function(){
	$('#planReport').jqprint();
});
$('.exportReport').off('click').on('click',function(){
	$.ajax({
		url : '/reportAll/plan',
		data : getParams(),
		type : 'post',
		success : function(result) {
			if(result.code == '000000'){
				if(result.data.total == 0){
					alert("没有要导出的数据！");
				}else if(result.data.total > 20000){
					alert("数据超过20000条，请联系管理员导出！");
				}else{
					var path = '/reportAll/planReport?'+$.param(getParams(1));
					$('<form method="post" action="' + path + '"></form>').appendTo('body').submit().remove();
				}
			}
		}
	});
});

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
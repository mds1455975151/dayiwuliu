var pageSize = 10
$(function(){
	$(".select2").select2(); 
});
$(".search").on("click",function(){
	displayData(0);
});
function displayData(pageNo){
	$.ajax({
		url:"/report/find",
		type:"post",
		data:getParams(pageNo),
		success:function(ret){
			if(ret.code=="000000"){
				if(ret.code=="000000"){
					$("#totalRecords").html(ret.data.total);
			    	document.getElementById("goPage").value = pageNo+1;
				    if(ret.data.total == 0) {
				    	$("#totalPages").html(1); 
				    }else {
				    	$("#totalPages").html(parseInt((ret.data.total-1)/pageSize+1));  
				    }   
					list = ret.data.list;
					if(ret.data.total != 0 ){
						innerHTML(ret.data.list);
					}else{
						var hml="";
						hml +='<td colspan="23">';
				    	hml +='<div class="ht_none">';
				    	hml +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
				    	hml +='<div>';
				    	hml +='<h3>唉呀！查询不到您要找的内容</h3>';
				    	hml +='<p>换个查询条件试试吧？</p	>';
				    	hml +='</div>';
				    	hml +='</div>';
				    	hml +='</td>';
				    	document.getElementById("innerHml").innerHTML=hml;
					}
					$("#pagination").pagination(ret.data.total, {   
					    callback: pageCallback,   
					    prev_text: '上一页',   
					    next_text: '下一页',   
					    items_per_page:pageSize,   
					    num_display_entries:4,   
					    current_page:pageNo,   
					    num_edge_entries:1, 
					    maxentries: ret.data.total, 
					    link_to:"javascript:void(0)"
					}); 
				}
			}
		}
	});
}

function innerHTML(data){
	var hml = "";
	for (var a = 0; a < data.length; a++) {
		var pickupweight = "";
		if(data[a].pickupweight != undefined){
			pickupweight = data[a].pickupweight;
		}
		var signweight = "";
		if(data[a].signweight != undefined){
			signweight = data[a].signweight;
		}
		var trueweight = "";
		if(data[a].trueweight != undefined){
			trueweight = data[a].trueweight;
		}
		var status = "";
		switch (data[a].status) {
		case -1:
			status = "车主回收";
			break;
		case 0:
			status = "司机未确认";
			break;
		case 1:
			status = "司机已接受";
			break;	
		case 2:
			status = "司机已装货";
			break;
		case 3:
			status = "司机运输中";
			break;
		case 4:
			status = "司机已到达";
			break;
		case 5:
			status = "司机已卸货";
			break;	
		case 6:
			status = "已签收";
			break;
		case 7:
			status = "司机拒绝接单";
			break;	
		default:
			status = "安联运单";
			break;
		}
		var s = a+1;
		
		//提货位置偏差
		var q_deviation = (data[a].q_deviation == undefined || data[a].q_deviation == -1)?"":data[a].q_deviation+"米";
		//到货位置偏差
		var d_deviation = (data[a].d_deviation == undefined || data[a].d_deviation == -1)?"":data[a].d_deviation+"米";
		
		var interDistance = "";
		var interTime = "";
		var begintime = "";
		var ywtime="";
		if(data[a].type == "普通运单"){
			if(data[a].interTime != undefined){//(Number(data[a].interTime/(1000 * 60))).toFixed(2)
				interTime = (Number(data[a].interTime/(1000 * 60))).toFixed(2)+"分钟";
			}
			if(data[a].interDistance != undefined){
				interDistance = data[a].interDistance+"米";
			}
			if(data[a].begintime != ""&&data[a].begintime != undefined){
				begintime = data[a].begintimeStr;
				ywtime = new Date(data[a].begintime).format("yyyy-MM-dd");
			}
		}
		var ownerSigntime = "";
		if(data[a].ownerSigntime != undefined && data[a].ownerSigntime != ""){
			ownerSigntime = new Date(data[a].ownerSigntime).format("yyyy-MM-dd hh:mm:ss");
		}
		
		hml +="<tr>" +
				"<td>"+s+"</td>" +
				"<td><a onclick=\"bill_map('"+data[a].type+"','"+data[a].id+"')\"><span>"+data[a].type+"</span></a></td>" +
				"<td>"+ywtime+"</td>" +
				"<td><span><a data-toggle='modal' onclick=\"plan_datails('"+data[a].palnid+"')\" data-target='#Plandetail'>"+data[a].plancode+"</a></span></td>" +
				"<td><span><a data-toggle='modal' onclick=\"bill_details('"+data[a].id+"','"+data[a].type+"')\" data-target='#Billdetail'>"+data[a].waybillno+"</a></span></td>" +
				"<td>"+data[a].shippermerchantname+"</td>" +
				"<td>"+data[a].consigneemerchantname+"</td>" +
				"<td>"+data[a].remarkname+"</td>" +
				"<td><span><a data-toggle='modal' onclick=\"vehicle_details('"+data[a].vehicleno+"')\" data-target='#vehicledetail'>"+data[a].vehicleno+"</a></span></td>" +
				"<td>"+data[a].drivername+"</td>" +
				"<td>"+data[a].cargoname+"</td>" +
				"<td>"+interTime+"</td>" +
				"<td>"+interDistance+"</td>" +
				"<td>"+begintime+"</td>" +
				"<td>"+data[a].unloadtimeStr+"</td>" +
				"<td>"+ownerSigntime+"</td>" +
				"<td>"+q_deviation+"</td>" +
				"<td>"+d_deviation+"</td>" +
				"<td>"+pickupweight+"</td>" +
				"<td>"+data[a].routename+"</td>" +
				"<td>"+data[a].weight+"</td>" +
				"<td>"+signweight+"</td>" +
				"<td>"+trueweight+"</td>" +
				"<td>"+status+"</td>" +
				"</tr>";
	}
	$("#innerHml").html(hml);
}

function regist(){
	$("#status").val("");
	$("#remarkname").val("");
	$("#vehicleno").val("");
	$("#cargoname").val("");
	$("#orgid").val("");
	$("#starttimeStr").val("");
	$("#endtimeStr").val("");
	$("#bill_type").val("");
	$("#waybillno").val("");
	$("#plancode").val("");
	$("#d_position").val("");
	$("#t_position").val("");
	$("#ownerSigntime_0Str").val("");
	$("#ownerSigntime_1Str").val("");
	$("#interTimeStr").val("");
	$("#interDistanceStr").val("");
}

function getParams(pageNo){
	var orgids = $("#orgid").val();
	var orgid = "";
	if(orgids != null){
		for (var a = 0; a < orgids.length; a++) {
			if(a+1 == orgids.length){
				orgid += "'"+orgids[0]+"'";
			}else{
				orgid += "'"+orgids[0]+"',";
			}
		}
	}
	return {"pageNo":pageNo,
		"pageSize":pageSize,
		"status":$("#status").val(),
		"remarkname":$("#remarkname").val(),
		"vehicleno":$("#vehicleno").val(),
		"cargoname":$("#cargoname").val(),
		"waybillno":$("#waybillno").val(),
		"plancode":$("#plancode").val(),
		"d_position":$("#d_position").val(),
		"t_position":$("#t_position").val(),
		"ownerSigntime_0Str":$("#ownerSigntime_0Str").val(),
		"ownerSigntime_1Str":$("#ownerSigntime_1Str").val(),
		"interTimeStr":$("#interTimeStr").val(),
		"interDistanceStr":$("#interDistanceStr").val(),
		"orgid":orgid,
		"type":$("#bill_type").val(),
		"starttimeStr":$("#starttimeStr").val(),
		"endtimeStr":$("#endtimeStr").val()}
}

function bill_map(type,id){
	if(type=="普通运单"){
		type = "w";	
	}else{
		type = "a";
	}
	window.open("/report/map?type="+type+"&id="+id+"&menuId=7");
}

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

function bill_details(id,type){
	var t = "";
	var urls = "";
	if(type=="开票运单"){
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
	//支付对象
	var payment = "";
	if(data.payment=="1"){
		payment="司机"
	}else if(data.payment=="2"){
		payment="车主"
	}
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
	"<div class='file_detail'><label>支付对象：</label><span>"+payment+"</span></div>"+
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
	//支付对象
	var payment = "";
	if(data.payment=="1"){
		payment="司机"
	}else if(data.payment=="2"){
		payment="车主"
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
				"<div class='file_detail'><label>支付对象：</label><span>"+payment+"</span></div>"+
				"<div class='file_detail2'><label>车辆信息：</label><span>"+data.vehicleno+"</span>" +
				"<span>"+data.drivername+"</span><span>"+data.drivertel+"</span></div>"+
				"<div class='file_detail'><label>提货磅单：</label><span>"+pickupimgurl+"</span></div>"+
				"<div class='file_detail'><label>卸货磅单：</label><span>"+signimgurl+"</span></div>"+
				"<div class='file_detail'><label>提货位置偏差：</label><span>"+q_deviation+"米</span></div>"+
				"<div class='file_detail'><label>卸货位置偏差：</label><span>"+d_deviation+"米</span></div>"+
				"<div class='clear'></div>";
				$("#Billdetailhml").html(hml);
}

function plan_datails(id){
	$.ajax({
		type:"post",
		data:{"id":id},
		url:"/admin/waybill/findPlanByid",
		success:function(ret){
			if(ret.code=="000000"){
				var d = ret.data;
				var payment = "";
				if(d.payment=="1"){
					payment="司机"
				}else if(d.payment=="2"){
					payment="车主"
				}
				var hml = "<div class='file_detail'><label>计划编码：</label><span>"+trimVal(d.plancode)+"</span></div>"+
					"<div class='file_detail'><label>组织名称：</label><span>"+trimVal(d.orgname)+"</span></div>"+
					"<div class='file_detail'><label>创建人：</label><span>"+trimVal(d.ownerName)+"</span></div>"+
					"<div class='file_detail'><label>货物名称：</label><span>"+trimVal(d.cargoname)+"</span></div>"+
					"<div class='file_detail'><label>车主：</label><span>"+trimVal(d.vehicleownername)+"</span></div>"+
					"<div class='file_detail'><label>运价策略：</label><span>"+trimVal(d.freightname)+"</span></div>"+
					"<div class='file_detail'><label>计量单位：</label><span></span>"+trimVal(d.measure)+"</div>"+
					"<div class='file_detail'><label>计价单位：</label><span>"+trimVal(d.priceUnits)+"</span></div>"+
					"<div class='file_detail'><label>单价：</label><span></span>"+trimVal(d.price)+"</div>"+
					"<div class='file_detail'><label>起运地：</label><span>"+trimVal(d.startcity)+"</span></div>"+
					"<div class='file_detail'><label>目的地：</label><span>"+trimVal(d.endcity)+" </span></div>"+
					"<div class='file_detail'><label>结算里程数：</label><span>"+trimVal(d.distance)+"</span></div>"+
					"<div class='file_detail'><label>计划总量：</label><span>"+trimVal(d.totalplanned)+"</span></div>"+
					"<div class='file_detail'><label>计划费用：</label><span>"+trimVal(d.planprice)+"元</span></div>"+
					"<div class='file_detail'><label>支付对象：</label><span>"+trimVal(payment)+"</span></div>"+
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
//导出
$(".billReport_").on("click",function(){
	$(".billReport_").attr('disabled',true);
	$.ajax({
		type:"post",
		data:getParams(0),
		url:"/report/find",
		success:function(ret){
			if(ret.data.total>2000){
				alert("数据超过2000条，请联系管理员导出！");
				$(".billReport_").attr('disabled',false);
			}else if(ret.data.total=0){
				alert("没有要导出的数据！");
				$(".billReport_").attr('disabled',false);
			}else{
				var path = '/report/reload?'+$.param(getParams());
				$('<form method="post" action="' + path + '"></form>').appendTo('body').submit().remove();
				$(".billReport_").attr('disabled',false);
			}
		}
	});
});
//打印
$('.printReport').off('click').on('click',function(){
	$('#anlianBillReport').jqprint();
});

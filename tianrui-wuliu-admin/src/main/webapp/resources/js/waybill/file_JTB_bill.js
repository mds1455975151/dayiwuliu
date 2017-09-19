var list ;
function searchFile(){
	displayData(0);
}
function displayData(pageNo){
	var searchKey = $("#searchKey").val();
	var vehicleno =$("#vehicleno").val();
	var jtb =$("#jtb").val();
	var createtime = $("#createtime").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+"/admin/waybill/findJTBBill",
		data:{
			"billNo":searchKey,
			"vehicleno":vehicleno,
			"jtb":jtb,
			"createtime":createtime,
			"no":(pageNo),
			"size":pageSize
		},
		type:"post",
		success:function(ret){
			if(ret.code=="000000"){
				$("#totalRecords").html(ret.data.total);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(ret.data.total == 0) {
			    	$("#totalPages").html(1); 
			    }else {
			    	$("#totalPages").html(parseInt((ret.data.total-1)/pageSize+1));  
			    }   
				list = ret.data.list;
				if(ret.data.list ){
					innerHTML(ret.data.list);
				}else{
					var hml="";
					hml +='<td colspan="12">';
			    	hml +='<div class="ht_none">';
			    	hml +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
			    	hml +='<div>';
			    	hml +='<h3>唉呀！查询不到您要找的内容</h3>';
			    	hml +='<p>换个查询条件试试吧？</p	>';
			    	hml +='</div>';
			    	hml +='</div>';
			    	hml +='</td>';
			    	document.getElementById("innhml").innerHTML=hml;
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
	});
}

function innerHTML(data){
	var hml = "";
	for (var a = 0; a < data.length; a++) {
		var d = a+1;
		var createtimeStr = data[a].createtimeStr==undefined?"":data[a].createtimeStr;
		var jtb = "";
		if(data[a].jtb=="1"){
			jtb = "已推送";
		}else{
			jtb = "未推送";
		}
		hml +="<tr><td>"+d+"</td>"+
		"<td><a onclick=\"bill_map('"+data[a].id+"')\">"+data[a].waybillno+"</a></td>"+
		"<td>"+data[a].vehicleno+"</td>"+
		"<td>"+jtb+"</td>"+
		"<td>"+data[a].creatimeStr+"</td>"+
		"<td><span><a data-toggle='modal' onclick=\"getDetail('"+data[a].id+"')\">【查看详情】</a></span>"
			+"<span><a data-toggle='modal' onclick=\"submitView('"+data[a].id+"')\">【提交】</a></span>"+
		"</td>";
	}
	document.getElementById("innhml").innerHTML=hml;
}
function getDetail(id){
	$.get(CONTEXTPATH + '/admin/waybill/findJTBBillDetail', {id: id}, function(result){
		if (result && result.code == '000000') {
			details(result.data);
		}
	});
}

function bill_map(id){
	window.open("/report/map?type=w&id="+id+"&menuId=6");
}

//查看详情
function details(data){
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
	document.getElementById("datail_html").innerHTML=hml;
	$('#detail').modal('show');
}
//提交
function submitView(id){
	$.get(CONTEXTPATH + '/admin/waybill/findJTBBillDetail', {id: id}, function(result){
		if (result && result.code == '000000') {
			submit(result.data);
		}
	});
}

function addZero(number){
	if (number < 10) {
		number = "0" + number;
	}
	return number;
}

Date.prototype.toString = function() {
	return this.getFullYear() + '-' 
			+ addZero(this.getMonth()) + '-' 
			+ addZero(this.getDate()) + ' ' 
			+ addZero(this.getHours()) + ':' 
			+ addZero(this.getMinutes()) + ':' 
			+ addZero(this.getSeconds());
}

function submit(obj){
	$("#billId").val(obj.id);
	$("#billNo").html(obj.waybillno);
	$("#dj").html(obj.price || '').append('元');
	$("#ysjl").html(obj.interDistance || '').append('km');
	$("#qszl").html(obj.trueweight || '').append('吨');
	$("#thsj").html(new Date(obj.begintime).toString() || '');
	$("#dhsj").html(new Date(obj.unloadtime).toString() || '');
	$("#zj").html(obj.price * obj.trueweight || '').append('元');
//	$("#jgsj").html((obj.interTime/(1000*60)) || '').append('分钟');
	$("#submit").modal('show');
}

$("#putJtb").on("click",function(){
	var id = $("#billId").val();
	if(id==""){
		alert("网络异常请稍候再试");
	}
	$.ajax({
		url:"/admin/waybill/putJtbBill",
		data:{"id":id},
		type:"post",
		success:function(ret){
			if(ret.code=="000000"){
				$("#billId").val("");
				alert("操作成功");
				searchFile();
			}else{
				alert(ret.error);
			}
		}
	});
	
});

function clearSearch(){
	$("#searchKey").val("");
	$("#vehicleno").val("");
	$("#jtb").val("");
	$("#createtime").val("");
	displayData(0);
}

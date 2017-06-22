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
			"type":type,
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
			billstatus = "开票运单";
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
		if(data[a].billstatus ==5){
			hml +="<a ><button class='btn btnyello delBtn' onclick=\"billSign_('"+data[a].id+"','"+data[a].billtype+"')\">签收</button></a>";
		}else if(data[a].billtype == "al"){
			hml +="<a ><button class='btn btnyello delBtn' onclick=\"billSign_('"+data[a].id+"','"+data[a].billtype+"')\">签收</button></a>";
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
	//大易运单签收
	if(type=="dy"){
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

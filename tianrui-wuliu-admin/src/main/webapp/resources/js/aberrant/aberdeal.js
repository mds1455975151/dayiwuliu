function displayData(pageNo){
	var page = $("#recPageNo").val();
	if(page != ""){
		init(page-1);
		$("#recPageNo").val("");
	}else{
		init(pageNo);
	}
}
function reset(){
	$("#drivername").val("");
	$("#carnum").val("");
	$("#drivernum").val("");
	$("#drivertel").val("");
	$("#abertype").val("");
	$("#aberstatus").val("");
	init(0);
}
function getParams(pageNo){
	//开始时间  格式转换
	var starttime = $("#starttime").val();
	var starL = null;
	if(starttime != ""){
		starL = Date.parse(new Date(starttime));
	}
	//结束时间   格式转换
	var endtime = $("#endtime").val();
	var endL = null;
	if(endtime != ""){
		endL = Date.parse(new Date(endtime));
	}
	var params = {pageNo:pageNo,
			createTimeBegin:starL,
			createTimeEnd:endL,
			customerName:$("#drivername").val(),
			vehicleNo:$("#carnum").val(),
			cellphone:$("#drivernum").val(),
			contactNumber:$("#drivertel").val(),
			problemType:$("#abertype").val(),
			solvingState:$("#aberstatus").val(),
			pageSize:10}
	return params;
}

function init(pageNo){
	$.ajax({
		url:"/admin/aberrant/select",
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
	if(!data){
		var hmlnull = "";
		hmlnull +='<td colspan="12">';
		hmlnull +='<div class="ht_none">';
		hmlnull +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
		hmlnull +='<div>';
		hmlnull +='<h3>唉呀！查询不到您要找的内容</h3>';
		hmlnull +='<p>换个查询条件试试吧？</p>';
		hmlnull +='</div>';
		hmlnull +='</div>';
		hmlnull +='</td>';
		$("#innerHtml").append(hmlnull);
	}else {
		for (var a = 0; a < data.length; a++) {
			var tmess = data[a].ifPush;
			var tsms = data[a].ifSms;
			var tcall = data[a].ifCall;
			var abertype = data[a].problemType;
			var avertypecont='';
			if(abertype == 1){
				avertypecont="轨迹异常";
			}
			
			var tsid = data[a].id;
			var customerId =data[a].customerId;
			var hml = "<tr>" +
					"<td>"+(a+1)+"</td>" +
					"<td>"+(avertypecont ||"")+"</td>" +
					"<td>"+(data[a].vehicleNo||"")+"</td>" +
					"<td>"+(data[a].billNo||"")+"</td>" +
					"<td>"+(data[a].customerName||"")+"</td>" +
					"<td>"+(data[a].cellphone||"")+"</td>" +
					"<td>"+(data[a].contactNumber||"")+"</td>" +
					"<td>"+(data[a].createTime||"")+"</td>" +
					"<td>"+new Date(data[a].lossTime).format("yyyy-MM-dd ")+"</td>" +
					"<td>"+new Date(data[a].reconnectionTime).format("yyyy-MM-dd ")+"</td>" +
					"<td>"+(data[a].solvingState||"")+"</td>" +
					"<td>"+(data[a].solvingUsername||"")+"</td>" +
					"<td>"+new Date(data[a].endTime).format("yyyy-MM-dd ")+"</td>" +
					"<td ><span><a data-toggle='modal' data-target='#message' onclick=\"setMsgDetail('"+tsid+"','"+customerId+"')\">" +(tmess == 0 ? '推送' : tmess == 1 ? '' : '')+
					"</a></span></td>"+
					"<td ><span><a data-toggle='modal' data-target='#information' onclick=\"setSmsDetail('"+tsid+"','"+customerId+"')\">" +(tsms == 0 ? '推送' : tsms == 1 ? '' : '')+
					"</a></span></td>"+
					"<td ><span><a data-toggle='modal' data-target='#phone'onclick=\"setcallDetail('"+tsid+"','"+customerId+"')\">" +(tcall == 0 ? '推送' : tcall == 1 ? '' : '')+
					"</a></span></td>"+
					"<td ><span><a data-toggle='modal' data-target='#close'>" +(tcall == 0 ? '关闭' : tcall == 1 ? '' : '')+
					"</a></span></td>"+
					"</tr>";
			$("#innerHtml").append(hml);
			
		}
	}

}
//异常消息推送
function setMsgDetail(id,memberId){
	$("#id_message").val("");
	$("#message_memberId").val("");
	$("#id_message").val(id);
	$("#message_memberId").val(memberId);
}
$("#messconfirm").on("click",function(){
	var msgid = $("#id_message").val();
	var msgType = $("#msgType_message").val();
	var msgTxt = $("#msgTxt_message").val();
	var groupType = $("#groupType_message").val();
	var memberId = $("#message_memberId").val();
	$.ajax({
		url:"/admin/aberrant/pushGroupMsg",
		type:"POST",
		data:{"id":msgid,"msgType":msgType,"msgTxt":msgTxt,"groupType":groupType,"memberId":memberId},
		success: function(result) {
			if(result.code == "000000"){
				alert("推送成功！");
				init(0);
			}else{
				alert(result.error);
			}
		}
	
	});
})
//异常短信推送
function setSmsDetail(id,memberId){
	$("#id_sms").val("");
	$("#memberId_sms").val("");
	$("#id_sms").val(id);
	$("#memberId_sms").val(memberId);
}
$("#smsconfirm").on("click",function(){
	var smsid = $("#id_sms").val();
	var smsType = $("#msgType_sms").val();
	var smsTxt = $("#msgTxt_sms").val();
	var smsgroupType = $("#groupType_sms").val();
	var smsmemberId = $("#memberId_sms").val();
	$.ajax({
		url:"/admin/aberrant/pushGroupMsg",
		type:"POST",
		data:{"id":smsid,"msgType":smsType,"msgTxt":smsTxt,"groupType":smsgroupType,"memberId":smsmemberId},
		success: function(result) {
			if(result.code == "000000"){
				alert("推送成功！");
				init(0);
			}else{
				alert(result.error);
			}
		}
	
	});
})
//异常电话推送
function setcallDetail(id,memberId){
	$("#id_call").val("");
	$("#memberId_call").val("");
	$("#id_call").val(id);
	$("#memberId_call").val(memberId);
}
$("#callconfirm").on("click",function(){
	var callid = $("#id_call").val();
	var callType = $("#msgType_call").val();
	var callTxt = $("#msgTxt_call").val();
	var callgroupType = $("#groupType_call").val();
	var callmemberId = $("#memberId_call").val();
	$.ajax({
		url:"/admin/aberrant/pushGroupMsg",
		type:"POST",
		data:{"id":callid,"msgType":callType,"msgTxt":callTxt,"groupType":callgroupType,"memberId":callmemberId},
		success: function(result) {
			if(result.code == "000000"){
				alert("推送成功！");
				init(0);
			}else{
				alert(result.error);
			}
		}
	
	});
})



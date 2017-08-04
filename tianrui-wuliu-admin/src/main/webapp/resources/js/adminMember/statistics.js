
function loadSearch(){
	displayRec(0);
}
function displayData(d){
	var recPage = $("#recPage").val();
	if(recPage==""){
		displayRec(d);
	}else{
		displayRec(recPage-1);
		$("#recPage").val("");
	}
}
function displayRec(pageNo){
	var starttime = $('#starttime').val() || '';starttime = $.trim(starttime);
	var endtime = $('#endtime').val() || '';endtime = $.trim(endtime);
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+'/statusStatistics/find',
		data:{"starttime":starttime,
			"endtime":endtime,
			"pageNo":(pageNo+1),
			"pageSize":pageSize
		},
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				alert("加载失败");
			}else{
				var hml = "";
				$("#totalRecords").html(ret.data.count);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(ret.data.count == 0) {
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
			    	$("#totalPages").html(parseInt((ret.data.count-1)/pageSize+1));  
			    	var d = ret.data.list;
					for (var a = 0; a < d.length; a++) {
						var c = a+1;
						var userByNum = d[a].userByNum;
						if(d[a].userByNum == undefined){
							userByNum = "";
						}
						var userFailNum = d[a].userFailNum;
						if(d[a].userFailNum == undefined){
							userFailNum = "";
						}
						var driverByNum = d[a].driverByNum;
						if(d[a].driverByNum == undefined){
							driverByNum = "";
						}
						var driverFailNum = d[a].driverFailNum;
						if(d[a].driverFailNum == undefined){
							driverFailNum = "";
						}
						var vehicleByNum = d[a].vehicleByNum;
						if(d[a].vehicleByNum == undefined){
							vehicleByNum = "";
						}
						var vehicleFailNum = d[a].vehicleFailNum;
						if(d[a].vehicleFailNum == undefined){
							vehicleFailNum = "";
						}
						var vehicleByNumL = d[a].vehicleByNumL;
						if(d[a].vehicleByNumL == undefined){
							vehicleByNumL = "";
						}
						var vehicleFailNumL = d[a].vehicleFailNumL;
						if(d[a].vehicleFailNumL == undefined){
							vehicleFailNumL = "";
						}
						var bankcardByNum = d[a].bankcardByNum;
						if(d[a].bankcardByNum == undefined){
							bankcardByNum = "";
						}
						var bankcardFailNum = d[a].bankcardFailNum;
						if(d[a].bankcardFailNum == undefined){
							bankcardFailNum = "";
						}
						var waybillByPushDJ = d[a].waybillByPushDJ;
						if(d[a].waybillByPushDJ == undefined){
							waybillByPushDJ = "";
						}
						var waybillFailPushDJ = d[a].waybillFailPushDJ;
						if(d[a].waybillFailPushDJ == undefined){
							waybillFailPushDJ = "";
						}
						var waybillByPushAJ = d[a].waybillByPushAJ;
						if(d[a].waybillByPushAJ == undefined){
							waybillByPushAJ = "";
						}
						var waybillFailPushAJ = d[a].waybillFailPushAJ;
						if(d[a].waybillFailPushAJ == undefined){
							waybillFailPushAJ = "";
						}
						var reviewTime = d[a].reviewTimes;
						if(d[a].reviewTime == undefined){
							reviewTime = "";
						}
						var creatertime = d[a].creatertimes;
						if(d[a].creatertime == undefined){
							creatertime = "";
						}
						var modify = d[a].modify;
						if(d[a].modify == undefined){
							modify = "";
						}
						var modifytime = d[a].modifytime;
						if(d[a].modifytime == undefined){
							modifytime = "";
						}
						hml += "<tr><td>"+c+"</td>"+
						    "<td>"+reviewTime+"</td>"+
							"<td>"+userByNum+"</td>"+
							"<td>"+userFailNum+"</td>"+
							"<td>"+driverByNum+"</td>"+
							"<td>"+driverFailNum+"</td>"+
							"<td>"+vehicleByNum+"</td>"+
							"<td>"+vehicleFailNum+"</td>"+
							"<td>"+vehicleByNumL+"</td>"+
							"<td>"+vehicleFailNumL+"</td>"+
							"<td>"+bankcardByNum+"</td>"+
							"<td>"+bankcardFailNum+"</td>"+
							"<td>"+waybillByPushDJ+"</td>"+
							"<td>"+waybillFailPushDJ+"</td>"+
							"<td>"+waybillByPushAJ+"</td>"+
							"<td>"+waybillFailPushAJ+"</td>"+
							"<td>"+creatertime+"</td></tr>";
					}
			    }  
			   
				document.getElementById("innerhml").innerHTML = hml;
				$("#pagination").pagination(ret.data.count, {  
				    callback: pageCallback,   
				    prev_text: '上一页',   
				    next_text: '下一页',   
				    items_per_page:pageSize,   
				    num_display_entries:4,   
				    current_page:pageNo,   
				    num_edge_entries:1, 
				    maxentries: ret.data.count, 
				    link_to:"javascript:void(0)"
				}); 
			}
		}
	});  
}
/**
 * 清空搜索
 */
function clearSearch(){
	$("#endtime").val("");
	$("#starttime").val("");

}

$('.exportReport').off('click').on('click',function(){
	$.ajax({
		url : CONTEXTPATH+'/statusStatistics/getAuditReportCount',
		data : getParams(),
		type : 'post',
		success : function(result) {
			if(result.code == '000000'){
				if(result.data == 0){
					alert("没有要导出的数据！");
				}else if(result.data > 2000){
					alert("数据超过2000条，请联系管理员导出！");
				}else{
					var path = CONTEXTPATH + '/statusStatistics/auditReportExport?'+$.param(getParams(1));
					$('<form method="post" action="' + path + '"></form>').appendTo('body').submit().remove();
				}
			}else{
				alert('系统异常，请联系管理员！');
				console.log(result.data);
			} 
		}
	});
});

$('.printReport').off('click').on('click',function(){
	$('#auditReport').jqprint();
});

function getParams(pageNo){
	var starttime = $('#starttime').val() || '';starttime = $.trim(starttime);
	var endtime = $('#endtime').val() || '';endtime = $.trim(endtime);
	var pageSize = $('#pageSize').val() || 10;pageSize = $.trim(pageSize);
	return {
		starttimeStr:starttime,
		endtimeStr:endtime,
		pageNo:pageNo,
		pageSize:pageSize
	} || {};
}

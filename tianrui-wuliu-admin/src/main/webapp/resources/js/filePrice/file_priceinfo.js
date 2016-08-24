
var regDouble=/^[-\+]?\d+(\.\d+)?$/;

//$(function(){
//	SearchPrice();
//});
/**
 * 条件查询
 */
function SearchPrice(){
	displayData(0);
}

function displayData(pageNo){
	$.ajax({
		url : CONTEXTPATH + '/freightinfo/index',// 跳转到 action
		data : {
		},
		type : "post",
		success : function(result) {
			var data = result.data.list;
			interHTML(data);
		}
	});
}

function displayDatatt(pageNo){
	var Scargo = $("#Scargo").val();
	var Sdesc1 = $("#Sdesc1").val();
	var SRoute = $("#SRoute").val();
	var Sdesc2 = $("#Sdesc2").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url : CONTEXTPATH + '/frieght/findByFreightEntity',// 跳转到 action
		data : {"cargoid":Scargo,
				"desc1":Sdesc1,
				"routeid":SRoute,
				"desc2":Sdesc2,
				"pageNo":(pageNo + 1),
				"pageSize":pageSize
		},
		type : "post",
		success : function(result) {
			if( result && result.code=="000000"  ){
				var data = result.data.list;
				$("#totalRecords").html(result.data.count);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(result.data.count == 0) {
			    	$("#totalPages").html(1); 
			    	$("#tablelist").empty();
			    	var html;
			    	if(Scargo || Sdesc1 || SRoute ||Sdesc2){
			    		html +='<td colspan="12">';
			    		html +='<div class="ht_none">';
			    		html +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
			    		html +='<div>';
			    		html +='<h3>唉呀！查询不到您要找的内容</h3>';
			    		html +='<p>换个查询条件试试吧？</p>';
			    		html +='</div>';
			    		html +='</div>';
			    		html +='</td>';
			    	}else{
			    		html +=' <td colspan="12">';
			    		html +='<div class="ht_none">';
			    		html +='<img src="'+imagesRoot+'/none1.png" class="ht_nimg2">';
			    		html +='<div>';
			    		html +='<h3>组织下暂无可用运价策略！？</h3>';
			    		html +='<p>赶快动动手指加上吧！</p>';
			    		html +='</div>';
			    		html +='</div>';
			    		html +='</td>';
			    	}
			    	$("#tablelist").html(html);
			    }else {
			    	$("#totalPages").html(parseInt((result.data.count-1)/pageSize+1));
			    	interHTML(data);
			    }  
				$("#pagination").pagination(result.data.count, {   
				    callback: pageCallback,   
				    prev_text: '上一页',   
				    next_text: '下一页',   
				    items_per_page:pageSize,   
				    num_display_entries:4,   
				    current_page:pageNo,   
				    num_edge_entries:1, 
				    maxentries: result.data.count, 
				    link_to:"javascript:void(0)"
				}); 
			}else{
				alert(result.error);
			}
		}
	});
}
/**
 * 清空搜索
 */
function clearSearch(){
	document.getElementById("Scargo").value="";
	document.getElementById("Sdesc1").value="";
	document.getElementById("SRoute").value="";
	document.getElementById("Sdesc2").value="";
}
/**
 * 查询结果写在页面
 * @param data
 */
function interHTML(data){
	var hml = "";
	for (var a = 0; a < data.length; a++) {
		var s = a+1;
		var status = "";
		if(data[a].status == "0"){
			status = "停用";
		}else if(data[a].status == "1"){
			status = "启用";
		}
		var cargostatus = "";
		if(data[a].desc3=="0"){
			cargostatus ="已停用";
		}else if(data[a].desc3=="1"){
			cargostatus ="已启用";
		}
		var routestatus = "";
		if(data[a].desc4=="0"){
			routestatus ="已停用";
		}else if(data[a].desc4=="1"){
			routestatus ="已启用";
		}
		var flag = true;
		if(data[a].desc3=="0" || data[a].desc4=="0"){
			flag = false;
		}
		
		var auditstatus = "";
		if(data[a].auditstatus != undefined){
			if(data[a].auditstatus=="0"){
				auditstatus = "审核中";
			}else if(data[a].auditstatus=="1"){
				auditstatus = "审核成功";
			}else if(data[a].auditstatus=="2"){
				auditstatus = "审核失败";
			}
		}
		var tallage = "";
		if(data[a].tallage != undefined){
			tallage = data[a].tallage;
		}
		var tallageInfo = "";
		if(data[a].tallageInfo != undefined){
			tallageInfo = data[a].tallageInfo;
		}
		var price = "";
		if(data[a].price != undefined){
			price = data[a].price;
		}
		var priceInfo = "";
		if(data[a].priceInfo != undefined){
			priceInfo = data[a].priceInfo;
		}
		hml +="<tr>" +
				"<td>"+data[a].freightName+"</td>"+ 
			"<td>"+price+"</td>"+ 
			"<td>"+priceInfo+"</td>"+ 
			"<td>"+data[a].priceunits+"</td>"+ 
			"<td>"+tallage+"</td>"+ 
			"<td>"+tallageInfo+"</td>"+ 
			"<td>"+auditstatus+"</td>"+ 
			"<td>"+data[a].cargoid+"/("+cargostatus+")</td>"+ 
			"<td>"+data[a].routeid+"/("+routestatus+")</td>"+
			"<td>" +
			"<span><a data-toggle='modal' data-target='#tingyong'>审核</a></span>" +
			"</td>" +
			"</tr>";
	}
	document.getElementById("tablelist").innerHTML=hml;
}
/**
 * 
 */
function shenhe(){
	
}

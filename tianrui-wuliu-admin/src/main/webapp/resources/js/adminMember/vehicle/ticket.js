
var list;
var pageSize = 10;
/**
 * 初始化查询
 */
function ticketSearch(){
	displayRect(0);
}

function displayData(pageNo){
	var page = $("#recPageNo").val();
	if(page != ""){
		displayRect(page-1);
		$("#recPageNo").val("");
	}else{
		displayRect(pageNo);
	}
}
function displayRect(pageNo){
	var vehicleNo = $("#find_vehicleNo").val();
	var status = $("#find_status").val();
	$.ajax({
		url:CONTEXTPATH+'/admin/ticket/page',
		data:{
			"vehicleNo":$.trim(vehicleNo),
			"status":$.trim(status),
			"pageNo":pageNo,
			"pageSize":pageSize
		},
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				alert("加载失败");
			}else{
				var hml = "";
				$("#totalRecords").html(ret.data.total);
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
			    	var data=ret.data.list;
					list = ret.data.list;
					for (var a = 0; a < data.length; a++) {
						var nature = "";
						if(data[a].nature == "1"){
							nature = "营运";
						}else if(data[a].nature == "2"){
							nature = "非营运";
						}
						var status = "";
						if(data[a].status == "-1"){
							status = "认证失败";
						}else if(data[a].status == "1"){
							status = "认证成功";
						}else if(data[a].status == "2"){
							status = "认证中";
						}
						var d = a+1;
						hml += "<tr><td >"+d+"</td>"+
							"<td >"+data[a].desc1+"</td>"+
							"<td >"+nature+" </td>"+
							"<td >"+data[a].owner+"</td>"+
							"<td >"+data[a].idcard+"</td>"+
							"<td >"+data[a].quality+"</td>"+
							"<td >"+data[a].certificateno+"</td>"+
							"<td >"+data[a].expirydata+"</td>"+
							"<td >"+data[a].identification+"</td>"+
							"<td >"+data[a].motor+"</td>"+
							"<td >"+data[a].motorno+"</td>"+
						    "<td >"+status+"</td>" +
						    		"<td>";
							hml += "<span><a data-toggle='modal' onclick=\"Ticketdetails('"+a+"')\" data-target='#detail'>【详情】</a></span>";
							if(data[a].status == "2"){
								hml += "<span><a data-toggle='modal' onclick=\"Ticketshenhe('"+data[a].id+"')\" data-target='#shenhe'>【审核】</a></span>";
							}
								hml += "</td></tr>";
					}
			    } 
				document.getElementById("innerHml").innerHTML=hml;
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

function clearSearch(){
	$("#find_vehicleNo").val("");
	$("#find_status").val("");
}

function Ticketdetails(id){
	var a = list[id];
	var nature = "";
	if(a.nature == "1"){
		nature = "营运";
	}else if(a.nature == "2"){
		nature = "非营运";
	}
	var hml = "<div class='file_detail'><label>车牌号：</label><span>"+a.desc1+"</span></div>"+
	"<div class='file_detail'><label>使用性质：</label><span>"+nature+"</span></div>"+
	"<div class='file_detail'><label>所有人：</label><span>"+a.owner+"</span></div>"+
	"<div class='file_detail'><label>身份证号：</label><span>"+a.idcard+"</span></div>"+
	"<div class='file_detail'><label>总质量：</label><span>"+a.quality+"</span></div>"+
	"<div class='file_detail'><label>登记证书编号：</label><span>"+a.certificateno+"</span></div>"+
	"<div class='file_detail'><label>检验有效期：</label><span>"+a.expirydata+"</span></div>"+
	"<div class='file_detail'><label>车辆识别码：</label><span>"+a.identification+"</span></div>"+
	"<div class='file_detail'><label>发动机号：</label><span>"+a.motor+"</span></div>"+
	"<div class='file_detail2'><label>发动机型号：</label><span>"+a.motorno+"</span></div>";
	document.getElementById("detailid").innerHTML = hml;
}

function Ticketshenhe(id){
	$("#ticket_id").val(id);
	$("#ticket_status").val("");
	$(".butongguo").css('background','');
	$(".tongguo").css('background','');
}
$(".tongguo").on("click",function(){
	$(this).css('background','#B9D3EE');
	$(".butongguo").css('background','');
	$("#ticket_status").val("1");
});
$(".butongguo").on("click",function(){
	$(this).css('background','#B9D3EE');
	$(".tongguo").css('background','');
	$("#ticket_status").val("-1");
});

$(".ticket_shenhe").on("click",function(){
	if($("#ticket_status").val() == ""){
		alert("请选择通过或不通过");
		return;
	}
	$(this).attr("disabled",true);
	$.ajax({
		url:CONTEXTPATH+'/admin/ticket/shenhe',
		data:{"id":$("#ticket_id").val(),
			"status":$("#ticket_status").val()
		},
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				$(".ticket_shenhe").attr("disabled",false);
				alert(ret.error);
			}else{
				$(".ticket_shenhe").attr("disabled",false);
				$(".ticket_hide").click();
				ticketSearch();
			}
		}
	});
});



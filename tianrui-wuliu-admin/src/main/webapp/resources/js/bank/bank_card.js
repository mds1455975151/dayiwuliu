
var pageSize = 10;
/**
 * 初始化查询
 */
function banktSearch(){
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

function clearSearch(){
	$("#find_bankcard").val("");
	$("#find_idname").val("");
	$("#find_bankautid").val("");
}

function displayRect(pageNo){
	var find_bankcard = $("#find_bankcard").val();
	var find_idname = $("#find_idname").val();
	var find_bankautid = $("#find_bankautid").val();
	$.ajax({
		url:'/admin/bank/card/find',
		data:{
			"bankcard":$.trim(find_bankcard),
			"idname":$.trim(find_idname),
			"bankautid":$.trim(find_bankautid),
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
			    if(ret.data.total == 0) {
			    	$("#totalPages").html(1);  
			    	hml +='<td colspan="14">';
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
					for (var a = 0; a < data.length; a++) {
						var d = a+1;
						var bankautid = "";
						if(data[a].bankautid=="1"){
							bankautid = "认证成功";
						}else if(data[a].bankautid=="2"){
							bankautid = "认证中";
						}else if(data[a].bankautid=="3"){
							bankautid = "认证失败";
						}
						var pushStatus = data[a].pushStatus;
						var msg = data[a].errorMassage==undefined?"数据延时，请稍候再试":data[a].errorMassage;
						hml += "<tr><td >"+d+"</td>"+
							"<td >"+data[a].bankcard+"</td>"+
							"<td >"+data[a].idname+"</td>"+
							"<td >"+(data[a].idcard==undefined?"":data[a].idcard)+"</td>"+
							"<td >"+(data[a].bankname==undefined?"":data[a].bankname)+"</td>"+
							"<td >"+(data[a].bankcode==undefined?"":data[a].bankcode)+"</td>"+
							
							"<td >"+(data[a].desc1==undefined?"":data[a].desc1)+"</td>"+
							"<td >"+(data[a].bankLineNumber==undefined?"":data[a].bankLineNumber)+"</td>"+
							"<td >"+bankautid+"</td>"+
							"<td >"+(pushStatus == 0 ? "<a onclick=\"showMassage('"+msg+"')\">未推送</a>" : pushStatus == 1 ? '推送中' : pushStatus == 2 ? '已推送' : '')+"</td>"+
						    "<td >"+new Date(data[a].createtime).format("yyyy-MM-dd hh:mm:ss")+"</td>"+
						    		"<td>";
							hml += "<span><a data-toggle='modal' onclick=\"bankdetails('"+data[a].id+"')\" data-target='#detail'>【详情】</a></span>";
							if(data[a].bankautid == "2"){
								hml += "<span><a data-toggle='modal' onclick=\"Bankshenhe('"+data[a].id+"')\" data-target='#shenhe'>【审核】</a></span>";
							}
								hml += "</td></tr>";
					}
			    } 
			    $("#innerHml").html(hml);
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
function showMassage(msg){
		alert(msg);
}

function bankdetails(id){
	detailClear();
	$.ajax({
		url:"/admin/bank/card/findId",
		type:"post",
		data:{"id":id},
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				var bankautid = "";
				if(data.bankautid == "1"){
					bankautid = "认证成功";
				}else if(data.bankautid == "2"){
					bankautid = "认证中";
				}else if(data.bankautid == "3"){
					bankautid = "认证失败";
				}
				$("#bankcard_mg").html(data.bankcard);
				$("#idname_mg").html(data.idname);
				$("#bankname_mg").html(data.bankname);
				$("#bankautid_mg").html(bankautid);
				$("#idcard_mg").html(data.idcard);
				$("#desc1_mg").html(data.desc1);
				//<a href="/imageView/index?imageUrl=http://172.19.4.73/uploadimgs/048632411efd40b1a1991ee824e8db1e.png" target="_blank">查看图片</a>
				var img = "<a href='/imageView/index?imageUrl="+data.bankimg+"' target='_blank'>查看图片</a>";
				$("#bankautidImg_mg").html(img);
			}
		}
	});
}

function detailClear(){
	$("#bankcard_mg").html("");
	$("#idname_mg").html("");
	$("#bankname_mg").html("");
	$("#bankautid_mg").html("");
	$("#idcard_mg").html("");
	$("#desc1_mg").html("");
	$("#bankautidImg_mg").html("");
}

function Bankshenhe(id){
	$("#bank_id").val(id);
	$("#bank_status").val("");
	$(".butongguo").css('background','');
	$(".tongguo").css('background','');
	
	$("#error_massage").html("");
}
$(".tongguo").on("click",function(){
	$(this).css('background','#B9D3EE');
	$(".butongguo").css('background','');
	$("#bank_status").val("1");
});
$(".butongguo").on("click",function(){
	$(this).css('background','#B9D3EE');
	$(".tongguo").css('background','');
	$("#bank_status").val("3");
});

$(".bank_shenhe").on("click",function(){
	if($("#bank_status").val() == ""){
		alert("请选择通过或不通过");
		return;
	}
	$(this).attr("disabled",true);
	$.ajax({
		url:'/admin/bank/card/autid',
		data:{"id":$("#bank_id").val(),
			"bankautid":$("#bank_status").val()
		},
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				$(".bank_shenhe").attr("disabled",false);
				alert("审核失败. " + ret.error);
			}else{
				$(".bank_shenhe").attr("disabled",false);
				$(".bank_hide").click();
				banktSearch();
			}
		}
	});
});



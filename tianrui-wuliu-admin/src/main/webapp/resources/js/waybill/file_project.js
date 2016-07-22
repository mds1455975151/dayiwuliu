var data;
function searchPlan(){
	displayData(0);
}
function displayData(pageNo){
	var plancode = $("#planCode").val();
	var cargoName = $("#cargoName").val();
	var orgName = $("#orgName").val();
	var creatName = $("#creatName").val();
	var vichOwner = $("#vichOwner").val();
	var createfor = $("#createfor").val();
	var createend = $("#createend").val();
	var Sstatus = $("#Sstatus").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+"/admin/waybill/findPlan",
		data:{
			"plancode":plancode,
			"cargoname":cargoName,
			"organizationname":orgName,
			"createtimeForStr":createfor,
			"createtimeEndStr":createend,
			"venderName":vichOwner,
			"status":Sstatus,
			"pageNo":(pageNo+1),
			"pageSize":pageSize
			},
		type:"post",
		success : function(ret){
			if(ret.code!="000000"){
				alert("初始化失败");
			}else{
				var hml = "";
				$("#totalRecords").html(ret.data.total);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(ret.data.total == 0) {
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
			    	$("#totalPages").html(parseInt((ret.data.total-1)/pageSize+1));  
			    	data = ret.data.list;
					$("#innhml").html("")
					for (var a = 0; a < data.length; a++) {
						var d = a+1;
						var at = "";
						if(data[a].status=="-1"){
							at = "已回收";
						}else if(data[a].status=="0"){
							at = "待接单";
						}else if(data[a].status=="1"){
							at = "已拒绝";
						}else if(data[a].status=="2"){
							at = "执行中";
						}else if(data[a].status=="3"){
							at = "已完成";
						}
						hml += "<tr><td>"+d+"</td>"+
							"<td>"+data[a].plancode+"</td>"+
							"<td>"+(data[a].orgname||'')+"</td>"+
							"<td>"+(data[a].ownerName||'')+"</td>"+
							"<td>"+(data[a].cargoname||'')+"</td>"+
							"<td>"+(data[a].vehicleownername||'')+"</td>"+
							"<td>"+(data[a].createtimeStr||'') +"</td>"+
							"<td>"+at+"</td>"+
							"<td><span><a data-toggle='modal' onclick=\"datails('"+a+"')\" data-target='#detail'>查看详情</a></span></td></tr>";
					}	
			    }   
				$("#innhml").html(hml);
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

function clearSerach(){
	$("#planCode").val("");
	$("#cargoName").val("");
	$("#orgName").val("");
	$("#creatName").val("");
	$("#vichOwner").val("");
	$("#createfor").val("");
	$("#createend").val("");
	$("#Sstatus").val("");
}



function datails(a){
	var d = data[a];
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
		"<div class='file_detail'><label>联系人：</label><span>"+trimVal(d.linkman)+"</span></div>"+
		"<div class='file_detail'><label>联系电话：</label><span>"+trimVal(d.telephone)+"</span></div>"+
		"<div class='file_detail'><label>开始时间：</label><span>"+trimVal(d.starttimeStr)+"</span></div>"+
		"<div class='file_detail'><label>结束时间：</label><span>"+trimVal(d.endtimeStr)+"</span></div>"+
		"<div class='file_detail'><label>创建时间：</label><span>"+trimVal(d.createtimeStr)+"</span></div>"+
		"<div class='clear'></div>";	
	$("#detailhml").html(hml);
}

function  trimVal(a){
	if(a ){
		return a;
	}else{
		return "";
	}
}


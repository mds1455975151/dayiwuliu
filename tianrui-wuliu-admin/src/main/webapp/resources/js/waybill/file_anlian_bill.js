var list ;
function searchFile(){
	displayData(0);
}
function displayData(pageNo){
	var shr =$("#shr").val();
	var starttime =$("#starttime").val();
	var endtime =$("#endtime").val();
	var searchKey = $("#searchKey").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+"/admin/waybill/findAnlianBill",
		data:{
			"shr":shr,
			"starttime":starttime,
			"endtime":endtime,
			"searchKey":searchKey,
			"pageNo":(pageNo),
			"pageSize":pageSize
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
		hml +="<tr><td>"+d+"</td>"+
		"<td>"+data[a].billno+"</td>"+
		"<td>"+data[a].cph+"</td>"+
		"<td>"+data[a].qycs+"</td>"+
		"<td>"+data[a].mdcs+"</td>"+
		"<td>"+data[a].shr+"</td>"+
		"<td>"+createtimeStr+"</td>"+
		"<td><span><a data-toggle='modal' onclick=\"details('"+data[a].id+"')\" data-target='#detail'>【查看详情】</a></span>"+
		"</td>";
	}
	document.getElementById("innhml").innerHTML=hml;
}

function details(id){
	$.ajax({
		url:"/admin/waybill/findAnlianBillId",
		data:{"id":id},
		type:"post",
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				//提货磅单
				var pickupimgurl = data.pickupimgurl==undefined?"<span>未上传</span>":("<span><a href='/imageView/index?imageUrl="+data.pickupimgurl+"' target='_blank'>查看图片</a></span>");
				//卸货榜单
				var signimgurl = data.signimgurl==undefined?"<span>未上传</span>":("<span><a href='/imageView/index?imageUrl="+data.signimgurl+"' target='_blank'>查看图片</a></span>");
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
				"<div class='file_detail'><label>车辆：</label><span>"+data.cph+"</span></div>"+
				"<div class='file_detail'><label>司机(安联)：</label><span>"+data.sj+"</span></div>"+
				"<div class='file_detail'><label>提货榜单：</label><span>"+pickupimgurl+"</span></div>"+
				"<div class='file_detail'><label>卸货榜单：</label><span>"+signimgurl+"</span></div>"+
				"<div class='file_detail'><label>联系方式：</label><span>"+data.drivertel+"</span></div>"+
				"<div class='file_detail2'><label>运单状态：</label><span>"+data.status+"</span>" +
				"<div class='clear'></div>";
				document.getElementById("dateilshml").innerHTML=hml;
			}
		}
	})
}


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
	var vehicleno = $("#vehicleno").val();
	var drivername = $("#drivername").val();
	var drivertel = $("#drivertel").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/findCapacity',
		data:{
			"vehicleNo":$.trim(vehicleno),
			"driverName":$.trim(drivername),
			"driverTel":$.trim(drivertel),
			"pageNo":(pageNo+1),
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
			    	$("#totalPages").html(parseInt((ret.data.total-1)/pageSize+1));  
			    	var d = ret.data.list;
					for (var a = 0; a < d.length; a++) {
						var c = a+1;
						
						hml += "<tr><td>"+c+"</td>"+
							"<td>"+d[a].vehicleNo+"</td>"+
							"<td>"+d[a].vehicleTypeName+"</td>"+
							"<td>"+d[a].driverName+"</td>"+
							"<td>"+d[a].driverTel+"</td>"+
							"<td>"+d[a].createTime+"</td>"+
							"<td><span><a data-toggle='modal' onclick=\"details('"+d[a].vehicleId+"')\" data-target='#detail'>【详情】</a></span>"+
							"<span><a data-toggle='modal' onclick=\"unbundled('"+d[a].id+"')\" data-target='#unbundled'>【解绑】</a></span>"+
							"</td></tr>";
					}
			    }  
				document.getElementById("innerhml").innerHTML = hml;
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
/**
 * 清空搜索
 */
function clearSearch(){
	$("#vehicleno").val("");
	$("#drivername").val("");
	$("#drivertel").val("");
	displayRec(0);
}


/**
 * 司机详情
 */
function driverDetail(id){
	alert(id);
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/findDriverDetail',
		data:{"vehicleId":id},
		type:"post",
		success: function(ret) {
			alert(666);
			
		}
	})
			
}
/**
 * 查询详情
 * @param id
 */
function details(id){
	$.ajax({
		url: CONTEXTPATH+'/AdminMember/findCarManagerById',
		data:{"id":id},
		type:"post",
		success: function(ret){
			var d = ret.data;
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
			var desc3 = "";
			if(d.desc3 != undefined){
				desc3 = d.desc3;
			}
			var vehiwidth = "";
			if(d.vehiwidth != undefined){
				vehiwidth = d.vehiwidth;
			}
			var vehiheight = "";
			if(d.vehiheight != undefined){
				vehiheight = d.vehiheight;
			}
			var hml = 
				"<div class='file_detail'><label>车牌号前缀：</label><span>"+d.vehicleprefix+"</span></div>"+
				"<div class='file_detail'><label>车牌号：</label><span>"+d.vehicleno+"</span></div>"+
				"<div class='file_detail'><label>所有人姓名：</label><span>"+userName+"</span></div>"+
				"<div class='file_detail'><label>联系方式：</label><span>"+telphone+"</span></div>"+
				"<div class='file_detail'><label>车型：</label><span>"+type+"</span></div>"+
				"<div class='file_detail'><label>载重：</label><span>"+d.vehiweight+"吨</span></div>"+
				"<div class='file_detail'><label>长度：</label><span>"+d.vehilength+"米</span></div>"+
				"<div class='file_detail'><label>宽度：</label><span>"+vehiwidth+"米</span></div>"+
				"<div class='file_detail'><label>高度：</label><span>"+vehiheight+"米</span></div>"+
				"<div class='file_detail'><label>认证状态：</label><span>"+sta+"</span></div>"+
				"<div class='file_detail'><label>认证时间：</label><span>"+d.createtimeStr+"</span></div>"+
				"<div class='file_detail2'><label>车辆登记证：</label>"+registcode+"</div>"+
				"<div class='file_detail2'><label>经营许可证号：</label>"+opercode+"</div>"+
				"<div class='file_detail2'><label>经营许可证有效期：</label>"+desc3+"</div>"+
				"<div class='file_detail2'><label>道路运输证号：</label>"+roadtransportcode+"</div>"+
				"<div class='file_detail2'><label>车辆照片：</label><span><a href='/imageView/index?imageUrl="+d.vehiheadimgpath+"' target='_blank'>查看图片</a></span></div>"+
				"<div class='file_detail2'><label>行驶证照片：</label><span><a href='/imageView/index?imageUrl="+d.vehilicenseimgpath+"' target='_blank'>查看图片</a></span></div>";
			document.getElementById("detailid").innerHTML = hml;
		}
	});
}
	
	/**
	 * 解绑车辆
	 * @param id
	 */
	function unbundled(id){
		if (confirm('是否解绑？')) {
			var index = layer.load(2, {
				
				time: 1000*10,
				shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
		$.ajax({
			url: CONTEXTPATH+'/AdminMember/unbundled',
			data:{"id":id},
			type:"post",
			success: function(ret){
				if(ret.code=="000000"){
					alert("解绑成功");
					displayRec(0);
				}else{
					alert(ret.error);
				}
			}
		});
		}
}
	
	
	
	function hideWindow(id,type){
		if(type==1||type==2){
			$("#showcode").hide();
		}else{
			$("#showcode").show();
		}
		$('#updateDeatil').modal('hide');
		$("#vehicid").val(id);
		$("#vehictype").val(type);
		$("#code").val("");
	}
	
	
	
	
	
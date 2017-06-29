
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
	var prefix = $("#prefix").val();
	var vehicleno = $("#vehicleno").val();
	var userName = $("#userName").val();
	var telphone = $("#telphone").val();
	var ownername = $("#ownername").val();
	var ownerphone = $("#ownerphone").val();
	var vehiclestatus = $("#vehiclestatus").val();
	var vehiclestatusType = $("#vehiclestatusType").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/findCarManager',
		data:{"vehicleprefix":$.trim(prefix),
			"vehicleno":$.trim(vehicleno),
			"userName":$.trim(userName),
			"telphone":$.trim(telphone),
			"ownername":$.trim(ownername),
			"ownerphone":$.trim(ownerphone),
			"status":$.trim(vehiclestatus),
			"desc2":$.trim(vehiclestatusType),
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
						var sta = "";
						if(d[a].status=="-1"){
							sta = "认证失败";
						}
						if(d[a].status=="0"){
							sta = "未认证";
						}
						if(d[a].status=="1"){
							sta = "认证成功";
						}
						if(d[a].status=="2"){
							sta = "认证中";
						}
						var type = "";
						if(d[a].vehicletypename != undefined){
							type = d[a].vehicletypename;
						}
						
						var userName = d[a].userName;
						if(d[a].userName == undefined){
							userName = "";
						}
						var telphone = d[a].telphone;
						if(d[a].telphone == undefined){
							telphone = "";
						}
						var ownerphone = d[a].cellphone;
						var ownername = d[a].ownername;
						if(d[a].ownerphone == undefined){
//							ownerphone = d[a].companytel;
							ownername = d[a].companyname;
						}
						var anlian = "";
						if(d[a].status=="1"){
							//"认证成功";
							anlian = "<span><a data-toggle='modal' onclick=\"anLianDetails('"+d[a].id+"','"+d[a].vehicleprefix+d[a].vehicleno+"')\" data-target='#anlian'>【完善信息】</a></span>";
							
							if(d[a].desc1=="-1"){
								anlian = "认证失败";
							}
							if(d[a].desc1=="0"){
								anlian = "<span><a data-toggle='modal' onclick=\"anLianDetails('"+d[a].id+"','"+d[a].vehicleprefix+d[a].vehicleno+"')\" data-target='#anlian'>【完善信息】</a></span>";
							}
							if(d[a].desc1=="1"){
								anlian = "<span><a data-toggle='modal' onclick=\"anLian_renzheng_detail('"+d[a].id+"','"+d[a].vehicleprefix+d[a].vehicleno+"')\" data-target='#anlian_detail'>【查看详情】</a></span>";
							}
							if(d[a].desc1=="2"){
								anlian = "认证中";
							}
						}
						var linType = "";
						if(d[a].desc2==1){
							linType = "<a onclick=\"buquanPage('"+d[a].id+"','"+(pageNo+1)+"')\"><span>【临时】</span></a>";
							anlian = "临时";
							sta = "临时";
						}else if(d[a].desc2==2){
							linType = "完全";
						}
						
						hml += "<tr><td>"+c+"</td>"+
							"<td>"+d[a].vehicleprefix+d[a].vehicleno+"</td>"+
							"<td>"+userName+"</td>"+
							"<td>"+telphone+"</td>"+
							"<td>"+ownername+"</td>"+
							"<td>"+ownerphone+"</td>"+
							"<td>"+type+"</td>"+
							"<td>"+d[a].vehiweight+"</td>"+
							"<td>"+linType+"</td>"+
							"<td>"+anlian+"</td>"+
							"<td>"+sta+"</td>"+
							"<td>"+d[a].createtimeStr+"</td>"+
							"<td><span><a data-toggle='modal' onclick=\"details('"+d[a].id+"')\" data-target='#detail'>【详情】</a></span>";
							if(d[a].status=="2"){
								hml += "<span><a  onclick=\"shenhe('"+d[a].id+"','"+(pageNo+1)+"')\">【审核】</a></span>";
							}
							if(d[a].status=="1"){
								hml += "<span><a data-toggle='modal' onclick=\"uptDetails('"+d[a].id+"')\" data-target='#updateDeatil'>【修改】</a></span>";
								
							}
							hml +=
							//去除删除功能
							// "<span><a data-toggle='modal' data-target='#dele'>删除</a></span>"+
							"</td></tr>";
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
	$("#prefix").val("");
	$("#vehicleno").val("");
	$("#userName").val("");
	$("#telphone").val("");
	$("#ownername").val("");
	$("#ownerphone").val("");
}
/** 安联认证信息补全*/
function anLianDetails(id,vehicleNo){
	$("#anlian_vehicleid").val(id);
	$("#anlian_vehicleNo").val(vehicleNo);
}
/**查看认证详情*/
function anLian_renzheng_detail(id,vehicleNo){
	//TODO
	$("#anlian_vehicleNo_detail").val(vehicleNo);
	$.ajax({
		url: CONTEXTPATH+'/admin/ticket/findVehicleId',
		data:{"id":id},
		type:"post",
		success: function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				if(data.nature == "1"){
					$("#anlian_nature_detail").val("营运");
				}else if(data.nature == "2"){
					$("#anlian_nature_detail").val("非营运");
				}
				$("#anlian_quality_detail").val(data.quality);
				$("#anlian_owner_detail").val(data.owner);
				$("#anlian_idcard_detail").val(data.idcard);
				$("#anlian_certificateno_detail").val(data.certificateno);
				$("#anlian_expirydata_detail").val(data.expirydata);
				$("#anlian_identification_detail").val(data.identification);
				$("#anlian_motor_detail").val(data.motor);
				$("#anlian_motorno_detail").val(data.motorno);
			}
		}
	});
}

/**
 * 审核页面跳转
 */
function shenhe(id,pageNo){
	var menuId = $("#menuId").val();
	window.location.href =CONTEXTPATH+"/AdminMember/carShenhe?menuId="+menuId+"&id="+id+"&pageNo="+pageNo;
}
function buquanPage(id,pageNo){
	var menuId = $("#menuId").val();
	window.location.href =CONTEXTPATH+"/AdminMember/carBuquanPage?menuId="+menuId+"&id="+id+"&pageNo="+pageNo;
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
	 * 查询详情
	 * @param id
	 */
	function uptDetails(id){
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
				if(d.vehicletypename != undefined){
					type = d.vehicletypename;
				}
				//<a data-toggle='modal' data-target='#againPice'>【重新上传】</a>
				var registcode = d.registimage==""?"<span>未上传</span>":("<span>证书编号：<a href='/imageView/index?imageUrl="+d.registimage+"' target='_blank'>查看照片</a></span>");
				var opercode = d.operimage==""?"<span>未上传</span>":("<span>证书编号："+d.opercode+"--<a href='/imageView/index?imageUrl="+d.operimage+"' target='_blank'>查看照片</a></span>");
				var identitycode = d.identieyimage==""?"<span>未上传</span>":("<span>证书编号："+d.identitycode+"--<a href='/imageView/index?imageUrl="+d.identieyimage+"' target='_blank'>查看照片</a></span>");
				var roadtransport = d.roadtransportimage==""?"<span>未上传</span>":("<span>证书编号："+d.roadtransportcode+"--<a href='/imageView/index?imageUrl="+d.roadtransportimage+"' target='_blank'>查看照片</a></span>");
				var roadtransportcode = "<input type='text' id='vehicleSportid' value='"+d.roadtransportcode+"'><a onclick='updateVehicleSoprtcode(\""+d.id+"\")'>【修改】</a>"
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
					"<div class='file_detail2'><label>车辆登记证：</label>"+registcode+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+d.id+"\",\"3\")' data-target='#againPice'>【重新上传】</a></div>" +
					"<div class='file_detail2'><label>经营许可证号：</label>"+opercode+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+d.id+"\",\"4\")' data-target='#againPice'>【重新上传】</a></div>"+
					"<div class='file_detail2'><label>经营许可证有效期：</label>"+desc3+"</div>"+
					"<div class='file_detail2'><label>道路运输证号：</label>"+roadtransportcode+"</div>"+
//					"<div class='file_detail2'><label>道路运输证：</label>"+roadtransportcode+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+d.id+"\",\"5\")' data-target='#againPice'>【重新上传】</a></div>"+
//					"<div class='file_detail2'><label>所有人身份证：</label>"+identitycode+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+d.id+"\",\"6\")' data-target='#againPice'>【重新上传】</a></div>"+
					"<div class='file_detail2'><label>车辆照片：</label><span><a href='/imageView/index?imageUrl="+d.vehiheadimgpath+"' target='_blank'>查看照片</a><a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+d.id+"\",\"1\")' data-target='#againPice'>【重新上传】</a></span></div>" +
					"<div class='file_detail2'><label>行驶证照片：</label><span><a href='/imageView/index?imageUrl="+d.vehilicenseimgpath+"' target='_blank'>查看照片</a><a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+d.id+"\",\"2\")' data-target='#againPice'>【重新上传】</a></span></div>";
				document.getElementById("uptdetailid").innerHTML = hml;
			}
		});
}
	
	function updateVehicleSoprtcode(id){
		var vehicleSportid = $("#vehicleSportid").val();
		if(vehicleSportid==""){
			alert("道路运输证不能为空");
			return;
		}
		$.ajax({
			url:CONTEXTPATH+"/AdminMember/updateCarMamage",
			data:{"id":id,"roadtransportcode":vehicleSportid},
			type:"post",
			success: function(retVal) {
				if(retVal.code=="000000"){
					alert("修改成功");
				}else{
					alert(retVal.error);
				}
			}
	});
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
	
	function uploadfile(){
		// 营业执照图片路径
		var file = $("#file_yyzz")[0].files[0];
		if($.trim($("#code").val())==""&&($("#vehictype").val()=="3"||$("#vehictype").val()=="4"||$("#vehictype").val()=="5"||$("#vehictype").val()=="6")){
			alert("证书编码不能为空");
			return;
		}
		var formData = new FormData();
		formData.append("id",$("#vehicid").val());
		formData.append("file",file);
		formData.append("type",$("#vehictype").val());
		formData.append("code",$("#code").val());
		$.ajax({
			type:"post",
			url:"/AdminMember/uptVehicPic",
			data:formData,// 你的formid
			processData : false,//告诉jQuery不要去处理发送的数据
			contentType : false,//告诉jQuery不要去设置Content-Type请求头
			success: function(rs) {
				if(rs.code=="000000"){
					window.location=location;
				}else{
					alert(rs.error);
				}
			}
		});
	}
	
	/***
	 * 后台车辆安联认证
	 */
	$(".anlian_renzheng").on("click",function(){
		var anlian_quality = $("#anlian_quality").val();
		if(anlian_quality==""){
			alert("总质量不能为空");
			return;
		}
		var anlian_owner = $("#anlian_owner").val();
		if(anlian_owner==""){
			alert("所有人不能为空");
			return;
		}
		var anlian_idcard = $("#anlian_idcard").val();
		if(anlian_idcard==""){
			alert("身份证号不能为空");
			return;
		}
		var anlian_certificateno = $("#anlian_certificateno").val();
		if(anlian_certificateno==""){
			alert("登记证书编号不能为空");
			return;
		}
		
		//1900-2099
		var regexp = /^([1][9][0-9][0-9]|[2][0][0-9][0-9])(\-)([0][1-9]|[1][0-2])(\-)([0-2][0-9]|[3][0-1])$/;
		
		var anlian_expirydata = $("#anlian_expirydata").val();
		if(!regexp.test(anlian_expirydata)){
			alert("检验有效期止时间格式有误");
			return;
		}
		var anlian_identification = $("#anlian_identification").val();
		if(anlian_identification==""){
			alert("车辆识别码不能为空");
			return;
		}
		var anlian_motor = $("#anlian_motor").val();
		if(anlian_motor==""){
			alert("发动机号不能为空");
			return;
		}
		var anlian_motorno = $("#anlian_motorno").val();
		if(anlian_motorno==""){
			alert("发动机型号不能为空");
			return;
		}
		$(".anlian_renzheng").attr("disabled",true);
		$.ajax({
				url:CONTEXTPATH+"/admin/ticket/save",
				data:$('#anlian_form').serialize(),
				type:"post",
				success: function(retVal) {
					if(retVal.code!="000000"){
						$(".anlian_renzheng").attr("disabled",false);
						alert(retVal.error);
					}else{
						 $("#alhide").click();
						 $(".anlian_renzheng").attr("disabled",false);
						loadSearch();
						anlianclean();
					}
				}
		});
	});
	
	function anlianclean(){
		$("#anlian_quality").val("");
		$("#anlian_owner").val("");
		$("#anlian_idcard").val("");
		$("#anlian_certificateno").val("");
		$("#anlian_expirydata").val("");
		$("#anlian_identification").val("");
		$("#anlian_motor").val("");
		$("#anlian_motorno").val("");
	}
	
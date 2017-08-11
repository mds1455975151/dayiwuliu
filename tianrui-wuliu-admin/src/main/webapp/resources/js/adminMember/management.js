
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
	var desc1 = $("#desc1").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/findCapacity',
		data:{
			"vehicleNo":$.trim(vehicleno),
			"driverName":$.trim(drivername),
			"driverTel":$.trim(drivertel),
			"desc1":$.trim(desc1),
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
						var desc1 = "";
						if(d[a].desc1=='1'){
							desc1 = "开票车辆";
						}else{
							desc1 = "普通车辆";
						}
						var aldriverid = d[a].aldriverid;
						if(d[a].aldriverid == undefined){
							aldriverid = "";
						}
						if(d[a].aldriverid == 1){
							aldriverid = "";
						}
						var vehicleNo = d[a].vehicleNo;
						if(d[a].vehicleNo==undefined){
							vehicleNo=""
						}
						var vehicleprefix = d[a].vehicleprefix;
						if(d[a].vehicleprefix==undefined){
							vehicleprefix=""
						}
						var vehicleTypeName = d[a].vehicleTypeName;
						if(d[a].vehicleTypeName==undefined){
							vehicleTypeName=""
						}
						var driverName = d[a].driverName;
						if(d[a].driverName==undefined){
							driverName=""
						}
						var driverTel = d[a].driverTel;
						if(d[a].driverTel==undefined){
							driverTel=""
						}
						var createTime = d[a].createTime;
						if(d[a].createTime==undefined){
							createTime=""
						}
						hml += "<tr><td>"+c+"</td>"+
							"<td>"+"<span><a  data-toggle='modal' onclick=\"details('"+d[a].vehicleId+"')\" data-target='#detail'>"+vehicleprefix+vehicleNo+"</a></span></td>"+
							"<td>"+vehicleTypeName+"</td>"+
							"<td>"+desc1+"</td>"+
							"<td>"+aldriverid+"</td>"+
							"<td>"+driverName+"</td>"+
							"<td>"+"<span><a data-toggle='modal' onclick=\"driverDetail('"+d[a].driverId+"')\" data-target='#detail'>"+driverTel+"</a></span></td>"+
							"<td>"+createTime+"</td><td>";
							if(d[a].id==undefined){
								hml += "<span><a data-toggle='modal' onclick=\"binds('"+d[a].ids+"','"+(pageNo)+"')\"  data-target='#addModal'>【绑定】</a></span>";
							}
							if(d[a].id!=undefined){
								hml += "<span><a data-toggle='modal' onclick=\"unbundled('"+d[a].id+"','"+(pageNo)+"')\" data-target='#unbundled'>【解绑】</a></span>";
							}
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
	$("#desc1").val("");
	displayRec(0);
}


/**
 * 司机详情
 */
function driverDetail(id){
//	alert(id);
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/findDriverDetail',
		data:{"driverId":id},
		type:"post",
		success: function(ret) {
			var a = ret.data;
			var per = "";
			if(a.driverpercheck=='0'){
				per = "未认证";
			}
			if(a.driverpercheck=='1'){
				per = "认证通过";
			}
			if(a.driverpercheck=='2'){
				per = "认证中";
			}
			if(a.driverpercheck=='3'){
				per = "认证失败";
			}
			var userName = a.userName;
			if(a.userName == undefined){
				userName = "";
			}
			var telphone = a.telphone;
			if(a.telphone == undefined){
				telphone = "";
			}
			var identityCard = a.identityCard;
			if(a.identityCard == undefined){
				identityCard = "";
			}
			var sex = a.sex == undefined ? "":a.sex;
			var birthday = a.birthday == undefined ? "":a.birthday;
			var firstlicens = a.firstlicens == undefined ? "":a.firstlicens;
			var licenceorg = a.licenceorg == undefined ? "":a.licenceorg;
			var starttime = a.starttime == undefined ? "":a.starttime;
			var usefullife = a.usefullife == undefined ? "":a.usefullife;
			var idcardaddress = a.idcardaddress == undefined ? "":a.idcardaddress;
			if(sex == "xx"){
				sex = "女";
			}else if(sex == "xy"){
				sex = "男";
			}
			var driveImagePath = a.driveImagePath == undefined?"未上传":("<span><a href='/imageView/index?imageUrl="+a.driveImagePath+"' target='_blank'>查看图片</a>");
			var idcard_image_A = a.positive == undefined?"未上传":("<span><a href='/imageView/index?imageUrl="+a.positive+"' target='_blank'>查看图片</a>");
			var idcard_image_B = a.opposite == undefined?"未上传":("<span><a href='/imageView/index?imageUrl="+a.opposite+"' target='_blank'>查看图片</a>");
			var licenseType = a.licenseType == undefined ? "":a.licenseType;
			document.getElementById("detailid").innerHTML = "";	
			var hmls = "<div class='file_detail'><label>司机账号：</label><span>"+a.cellPhone+"</span></div>"+
				"<div class='file_detail'><label>司机姓名：</label><span>"+userName+"</span></div>"+
				
				"<div class='file_detail'><label>司机性别：</label><span>"+sex+"</span></div>"+
				"<div class='file_detail'><label>出生日期：</label><span>"+birthday+"</span></div>"+
				"<div class='file_detail'><label>身份证地址：</label><span>"+idcardaddress+"</span></div>"+
				"<div class='file_detail'><label>初次领证日期：</label><span>"+firstlicens+"</span></div>"+
				"<div class='file_detail'><label>发证机关：</label><span>"+licenceorg+"</span></div>"+
				"<div class='file_detail'><label>有效年限：</label><span>"+usefullife+"</span></div>"+
				"<div class='file_detail3'><label>驾驶证注册日期：</label><span>"+starttime+"</span></div>"+
				
				"<div class='file_detail'><label>联系方式：</label><span>"+telphone+"</span></div>"+
				"<div class='file_detail'><label>驾驶证号：</label><span>"+identityCard+"</span></div>"+
				"<div class='file_detail'><label>准驾车型：</label><span>"+licenseType+"</span></div>"+
				"<div class='file_detail'><label>档案状态：</label><span>"+per+"</span></div>"+
				"<div class='file_detail'><label>注册时间：</label><span>"+a.registtimeStr+"</span></div>"+
				"<div class='file_detail'><label>认证时间：</label><span>"+a.submitDateStr+"</span></div>"+
				"<div class='file_detail2'><label>驾驶证照片：</label><span>"+driveImagePath+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+a.id+"\",\"2\")' data-target='#againPice'>【重新上传】</a></span></div>"+
				"<div class='file_detail2'><label>身份证正面：</label><span>"+idcard_image_A+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+a.id+"\",\"5\")' data-target='#againPice'>【重新上传】</a></span></div>"+
				"<div class='file_detail2'><label>身份证反面：</label><span>"+idcard_image_B+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+a.id+"\",\"6\")' data-target='#againPice'>【重新上传】</a></span></div>";
			document.getElementById("detailid").innerHTML = hmls;	
			
		}
	});
			
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
	function unbundled(id,pageNo){
		if (confirm('是否解绑？')) {
		$.ajax({
			url: CONTEXTPATH+'/AdminMember/unbundled',
			data:{"id":id},
			type:"post",
			success: function(ret){
				if(ret.code=="000000"){
					alert("解绑成功");
					displayRec(parseInt(pageNo));
				}else{
					alert(ret.error);
				}
			}
		});
		}
}
	function binds(id,pageNo){
		$("#id").val(id);
		$("#pageNo").val(parseInt(pageNo));
		$(".mast").hide();
		$("#alDriver").val("");
	}
	/**
	 * 绑定车辆
	 * @param id
	 * @param type
	 */
	function bind(type){
		var id = $("#id").val();
		var pageNo = $("#pageNo").val();
		var driverid=$("#driverid").val();
		var alDriver = $("#alDriver").val();
		if(type=="1"&&alDriver==""){
			alert("司机安联账号不能为空");
			return;
		}
		$.ajax({
				url: CONTEXTPATH+'/AdminMember/bind',
				data:{"id":id,
					"driverid":driverid,
					"alDriver":alDriver},
				type:"post",
				success: function(ret){
					if(ret.code=="000000"){
						document.getElementById("addclick").click();
						alert("绑定车辆成功！");
						displayRec(parseInt(pageNo));
					}else{
						if(ret.error == "IDHadBeenRegistered"){
							$(".mast").show();
							$("#alDriver").val("");
							$("#errorMassage").html(ret.error);
						}else{
							document.getElementById("addclick").click();
							alert(ret.error);
						}
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
	//搜索全平台司机
	function searchPhone(){
		var phone = $("#addcellphone").val();
		$.ajax({
			url:CONTEXTPATH+"/AdminMember/bindDriver",
			data:{"phone":phone},
			type:"post",
			success:function(ret){
				if(ret.code!="000000"){
					document.getElementById("error").innerHTML=ret.error;
				}else{
					var data = ret.data;
					var userName = data.userName;
					$("#memberName").val(userName);
					var driverid = data.id;
					$("#driverid").val(driverid);
//					searchMember();
//					document.getElementById("addclick").click();
				}
			}
		});
	}
	
	/** 重置*/
	function resetvalue(){
		$("#cellPhone").val("");
		$("#addcellphone").val("");
		$("#error").html("");
		$("#memberName").val("");
		$("#massage").val(""); 
	}
	
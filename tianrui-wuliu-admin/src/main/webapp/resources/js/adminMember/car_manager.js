
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
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/findCarManager',
		data:{"vehicleprefix":$.trim(prefix),
			"vehicleno":$.trim(vehicleno),
			"userName":$.trim(userName),
			"telphone":$.trim(telphone),
			"ownername":$.trim(ownername),
			"ownerphone":$.trim(ownerphone),
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
						if(d[a].vehicletype=="1"){
							type = "箱式";
						}
						if(d[a].vehicletype=="2"){
							type = "板车";
						}
						if(d[a].vehicletype=="3"){
							type = "冷藏";
						}
						if(d[a].vehicletype=="4"){
							type = "散装罐车";
						}
						if(d[a].vehicletype=="5"){
							type = "半挂车";
						}
						if(d[a].vehicletype=="6"){
							type = "重型自卸货车";
						}
						var userName = d[a].userName;
						if(d[a].userName == undefined){
							userName = "";
						}
						var telphone = d[a].telphone;
						if(d[a].telphone == undefined){
							telphone = "";
						}
						var ownerphone = d[a].ownerphone;
						var ownername = d[a].ownername;
						if(d[a].ownerphone == undefined){
							ownerphone = d[a].companytel;
							ownername = d[a].companyname;
						}
						hml += "<tr><td>"+c+"</td>"+
							"<td>"+d[a].vehicleprefix+d[a].vehicleno+"</td>"+
							"<td>"+userName+"</td>"+
							"<td>"+telphone+"</td>"+
							"<td>"+ownername+"</td>"+
							"<td>"+ownerphone+"</td>"+
							"<td>"+type+"</td>"+
							"<td>"+d[a].vehiweight+"</td>"+
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

/**
 * 审核页面跳转
 */
function shenhe(id,pageNo){
	var menuId = $("#menuId").val();
	window.location.href =CONTEXTPATH+"/AdminMember/carShenhe?menuId="+menuId+"&id="+id+"&pageNo="+pageNo;
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
			if(d.vehicletype=="1"){
				type = "箱式";
			}
			if(d.vehicletype=="2"){
				type = "板车";
			}
			if(d.vehicletype=="3"){
				type = "冷藏";
			}
			if(d.vehicletype=="4"){
				type = "散装罐车";
			}
			if(d.vehicletype=="5"){
				type = "半挂车";
			}
			if(d.vehicletype=="6"){
				type = "重型自卸货车";
			}
			var hml = 
				"<div class='file_detail'><label>车牌号前缀：</label><span>"+d.vehicleprefix+"</span></div>"+
				"<div class='file_detail'><label>车牌号：</label><span>"+d.vehicleno+"</span></div>"+
				"<div class='file_detail'><label>所有人姓名：</label><span>"+userName+"</span></div>"+
				"<div class='file_detail'><label>联系方式：</label><span>"+telphone+"</span></div>"+
				"<div class='file_detail'><label>车型：</label><span>"+type+"</span></div>"+
				"<div class='file_detail'><label>载重：</label><span>"+d.vehiweight+"吨</span></div>"+
				"<div class='file_detail'><label>长度：</label><span>"+d.vehilength+"米</span></div>"+
				"<div class='file_detail'><label>认证状态：</label><span>"+sta+"</span></div>"+
				"<div class='file_detail'><label>认证时间：</label><span>"+d.createtimeStr+"</span></div>"+
				"<div class='file_detail2'><label>车辆照片：</label><span><a href='"+d.vehiheadimgpath+"' target='_blank'><img height='180' src='"+d.vehiheadimgpath+"'></a></span></div>"+
				"<div class='file_detail2'><label>行驶证照片：</label><span><a href='"+d.vehilicenseimgpath+"' target='_blank'><img height='180' src='"+d.vehilicenseimgpath+"'></a></span></div>";
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
				if(d.vehicletype=="1"){
					type = "箱式";
				}
				if(d.vehicletype=="2"){
					type = "板车";
				}
				if(d.vehicletype=="3"){
					type = "冷藏";
				}
				if(d.vehicletype=="4"){
					type = "散装罐车";
				}
				if(d.vehicletype=="5"){
					type = "半挂车";
				}
				if(d.vehicletype=="6"){
					type = "重型自卸货车";
				}
				//<a data-toggle='modal' data-target='#againPice'>【重新上传】</a>
				var hml = 
					"<div class='file_detail'><label>车牌号前缀：</label><span>"+d.vehicleprefix+"</span></div>"+
					"<div class='file_detail'><label>车牌号：</label><span>"+d.vehicleno+"</span></div>"+
					"<div class='file_detail'><label>所有人姓名：</label><span>"+userName+"</span></div>"+
					"<div class='file_detail'><label>联系方式：</label><span>"+telphone+"</span></div>"+
					"<div class='file_detail'><label>车型：</label><span>"+type+"</span></div>"+
					"<div class='file_detail'><label>载重：</label><span>"+d.vehiweight+"吨</span></div>"+
					"<div class='file_detail'><label>长度：</label><span>"+d.vehilength+"米</span></div>"+
					"<div class='file_detail'><label>认证状态：</label><span>"+sta+"</span></div>"+
					"<div class='file_detail'><label>认证时间：</label><span>"+d.createtimeStr+"</span></div>"+
					"<div class='file_detail2'><label>车辆照片：</label><span><a href='"+d.vehiheadimgpath+"' target='_blank'><img height='180' src='"+d.vehiheadimgpath+"'></a><a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+d.id+"\",\"1\")' data-target='#againPice'>【重新上传】</a></span></div>" +
					"<div class='file_detail2'><label>行驶证照片：</label><span><a href='"+d.vehilicenseimgpath+"' target='_blank'><img height='180' src='"+d.vehilicenseimgpath+"'></a><a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+d.id+"\",\"2\")' data-target='#againPice'>【重新上传】</a></span></div>";
				document.getElementById("uptdetailid").innerHTML = hml;
			}
		});
}
	
	function hideWindow(id,type){
		$('#updateDeatil').modal('hide');
		$("#vehicid").val(id);
		$("#vehictype").val(type);
//		 $(".hidemodel").on("click",function(){
//	            $('#updateDeatil').modal('hide');
//	            $('#againPice').on('hidden.bs.modal', function (e) {
//	                $('#closeupt').modal('show');
//	            })
//	        });
//		$("#closeupt").click();
//		$("#updateDeatil").hide();
	}
	
	function uploadfile(){
		// 营业执照图片路径
		var file = $("#file_yyzz")[0].files[0];
		
		var formData = new FormData();
		formData.append("id",$("#vehicid").val());
		formData.append("file",file);
		formData.append("type",$("#vehictype").val());
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
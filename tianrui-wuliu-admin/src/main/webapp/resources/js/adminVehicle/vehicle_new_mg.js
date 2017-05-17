
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
	var pageSize=$("#pageSize").val();
	var vehicleno = $("#vehicleno").val();
	var ownerName = $("#ownerName").val();
	var vehiclemobile = $("#vehiclemobile").val();
	var authtype = $("#authtype").val();
	var status = $("#vehiclestatus").val();
	$.ajax({
		url:'/admin/fileVehicle/find',
		data:{"pageNo":(pageNo),
			"pageSize":pageSize
		},
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				alert("加载失败");
			}else{
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
			    	var d = ret.data.list;
			    	innerHtml(d);
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

function innerHtml(d){
	var hml = "";
	for (var a = 0; a < d.length; a++) {
		var c = a+1;
	    var date = new Date(d[a].createTime);
		hml += "<tr><td>"+c+"</td>"+
			"<td>"+d[a].vehicleNo+"</td>"+
			"<td>"+d[a].vehicleMobile+"</td>"+
			"<td>"+d[a].vehicleType+"</td>"+
			"<td>"+d[a].vehicleOwner+"</td>"+
			"<td>"+d[a].vehicleOwnerIdCard+"</td>"+
			"<td>"+d[a].drivingLicenseNo+"</td>"+
			"<td>"+d[a].vehicleLoad+"</td>"+
			"<td>新增</td>"+
			"<td>"+d[a].checkStatus+"</td>"+
			"<td>"+date.format("yyyy-MM-dd hh:mm:ss")+"</td>"+
			"<td><span><a data-toggle='modal' onclick=\"detail_id('"+d[a].id+"')\" data-target='#detail'>【详情】</a></span>";
			hml += "<span><a data-toggle='modal' onclick=\"VehicleShenhe('"+d[a].id+"')\" data-target='#shenhe'>【审核】</a></span>";
			hml += "</td></tr>";
	}
	$("#innerhml").html(hml);
}	

function detail_id(id){
	cleanMassage();
	$.ajax({
		url:"/admin/fileVehicle/findId",
		type:"post",
		data:{"id":id},
		success:function(ret){
			if(ret.code=="000000"){
				var data = ret.data;
				$("#vehicleNo_mg").html(data.vehicleNo);
				$("#vehicleOwnerTel_mg").html(data.vehicleOwnerTel);
				$("#vehicleType_mg").html(data.vehicleType);
				$("#vehicleLoad_mg").html(data.vehicleLoad);
				$("#vehicleLen_mg").html(data.vehicleLen);
				$("#checkStatus_mg").html(data.checkStatus);
				$("#createTime_mg").html(data.createTime);
				$("#vehicleMobile_mg").html(data.vehicleMobile);
				//营运证号
				$("#taxiLicenseNo_mg").html(data.taxiLicenseNo);
				//营运证图片
				$("#taxiLicenseImg_mg").html(data.taxiLicenseImg==undefined?"未上传":"<a href='/imageView/index?imageUrl="+data.taxiLicenseImg+"' target='_blank'>查看图片</a>");
				//行驶证
				$("#drivingLicenseImg_mg").html(data.drivingLicenseImg==undefined?"未上传":"<a href='/imageView/index?imageUrl="+data.drivingLicenseImg+"' target='_blank'>查看图片</a>");
				//车辆照片
				$("#vehicleImg_mg").html(data.vehicleImg==undefined?"未上传":"<a href='/imageView/index?imageUrl="+data.vehicleImg+"' target='_blank'>查看图片</a>");
				//车辆登记证   "<a href='/imageView/index?imageUrl="+data.taxiLicenseImg+"' target='_blank'>查看图片</a>"
				$("#vehicleGradeImg_mg").html(data.vehicleGradeImg==undefined?"未上传":"<a href='/imageView/index?imageUrl="+data.vehicleGradeImg+"' target='_blank'>查看图片</a>");
				$("#driverName_mg").html(data.driverName);
				$("#driverCardFirstlicens_mg").html(data.driverCardFirstlicens);
				$("#driverSex_mg").html(data.driverSex);
				$("#driverCardLicenceorg_mg").html(data.driverCardLicenceorg);
				$("#driverBirthDate_mg").html(data.driverBirthDate);
				$("#driverCardRegDate_mg").html(data.driverCardRegDate);
				$("#driverLinkTel_mg").html(data.driverLinkTel);
				$("#driverCardUsefullife_mg").html(data.driverCardUsefullife);
				$("#driverIdCard_mg").html(data.driverIdCard);
				$("#driverCardType_mg").html(data.driverCardType);
				$("#driverIdCardAddr_mg").html(data.driverIdCardAddr);
				//行驶证 
				$("#driverCardImg_mg").html(data.driverCardImg==undefined?"未上传":"<a href='/imageView/index?imageUrl="+data.driverCardImg+"' target='_blank'>查看图片</a>");
				
				
			}
		}
	});
}
/**清除展示信息*/
function cleanMassage(){
	$("#vehicleNo_mg").html("");
	$("#vehicleOwnerTel_mg").html("");
	$("#vehicleType_mg").html("");
	$("#vehicleLoad_mg").html("");
	$("#vehicleLen_mg").html("");
	$("#checkStatus_mg").html("");
	$("#createTime_mg").html("");
	$("#vehicleMobile_mg").html("");
	$("#taxiLicenseNo_mg").html("");
	$("#drivingLicenseImg_mg").html("");
	$("#vehicleImg_mg").html("");
	$("#vehicleGradeImg_mg").html("");
	$("#driverName_mg").html("");
	$("#driverCardFirstlicens_mg").html("");
	$("#driverSex_mg").html("");
	$("#driverCardLicenceorg_mg").html("");
	$("#driverBirthDate_mg").html("");
	$("#driverCardRegDate_mg").html("");
	$("#driverLinkTel_mg").html("");
	$("#driverCardUsefullife_mg").html("");
	$("#driverIdCard_mg").html("");
	$("#driverCardType_mg").html("");
	$("#driverIdCardAddr_mg").html("");
	$("#driverCardImg_mg").html("");
}

$(".tongguo").on("click",function(){
	$(this).css('background','#B9D3EE');
	$(".butongguo").css('background','');
	$("#vehicle_status").val("1");
});
$(".butongguo").on("click",function(){
	$(this).css('background','#B9D3EE');
	$(".tongguo").css('background','');
	$("#vehicle_status").val("-1");
});

function VehicleShenhe(id){
	$(".butongguo").css('background','');
	$(".tongguo").css('background','');
	$("#vehicle_status").val("");
	$("#vehicle_id").val(id);
}

$(".vehicle_shenhe").on("click",function(){
	if($("#vehicle_status").val()==""){
		alert("请选择是否通过");
		return;
	}
	if($("#vehicle_id").val()==""){
		alert("网络异常，请刷新页面重新选择");
		return;
	}
	$(".vehicle_shenhe").attr("disabled",true);
	$.ajax({
		url:"/admin/fileVehicle/saveDriverAndVehicle",
		type:"post",
		data:{"id":$("#vehicle_id").val(),
			"status":$("#vehicle_status").val()
			},
		success:function(ret){
			$(".vehicle_shenhe").attr("disabled",false);
			if(ret.code == 000000){
				alert("操作成功");
			}else{
				alert(ret.error);
			}
		}
	});
})



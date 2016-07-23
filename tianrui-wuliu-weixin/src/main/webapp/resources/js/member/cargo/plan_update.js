///**
// * 货运计划
// */
$(function(){
	FreightChange_new();
//	initialize();
});
/**
 * 添加计划
 */
function updatePlan(){
	var Freightname = $("#Freightname").val();
	if(Freightname==""){
		alert("请选择运价策略");
		return;
	}
	var totalplanned = $("#totalplanned").val();
	if(totalplanned==""){
		alert("计划总量不能为空");
		return;
	}
	var planprice = $("#planprice").val();
	if(planprice==""){
		alert("计划费用不能为空");
		return;
	}
	var linkman = $("#linkman").val();
	if(linkman==""){
		alert("联系人不能为空");
		return;
	}
	var telephone = $("#telephone").val();
	if(telephone==""){
		alert("联系电话不能为空");
		return;
	}
	$.ajax({
		cache: true,
		type:"post",
		url:CONTEXTPATH+'/trwuliu/Member/cargoPlan/updatePlanEntity',
		data:$('#updateplan').serialize(),// 你的formid
		async: false,
		success: function(rs) {
			if(rs.code=="000000"){
				window.location.href = CONTEXTPATH + "/trwuliu/Member/cargoPlan/planFabu";
			}else{
				alert(rs.error);
			}
			
		}
	});
}
//
//function initialize(){
//	var planId = $("#planId").val();
//	if(planId != null){
//		$.ajax({
//			url:CONTEXTPATH+'/trwuliu/Member/cargoPlan/findPlan',
//			data:{"id":planId},
//			type:"post",
//			success: function(rs) {
//				if(rs.code!="000000"){
//					alert("页面加载失败");
//				}else{
//					var freightid = "";
//					var data = rs.data;
//					for(var i=0;i<rs.data.length;i++){
//						freightid = data[i].freightid;
//					}
//					FreightChangeNew(freightid);
//				}
//			}
//		});
//	}
//}

function FreightChange_new(){
	var freightid = $("#freightname_v_id").val();
	var freightname = $("#freightname_v").val();
//	if(freightname != ""&&freightName.indexOf(freightname)==-1){
//		alert("请选择运价策略");
//		return;
//	}
	if(freightname == ""){
//		clearMassage();
//		blurSearch();
		return;
	}
	$.ajax({
		url:CONTEXTPATH+'/trwuliu/Member/cargo/findFreightById',
		data:{ 
			"id":freightid
		},
		type:"post",
		success: function(rs) {
			if(rs.code!="000000"){
				alert("页面加载失败");
			}else{
				//页面
				var aa = rs.data;
				if(freightname.replace(/(^s*)|(s*$)/g, "").length == 0){
					clearMassage();
				}else{
					document.getElementById("hprice").innerHTML = aa.price;
					document.getElementById("price").value = aa.price;
					document.getElementById("hpriceunits").innerHTML = aa.priceUnits;
					document.getElementById("priceunits").value = aa.priceUnits;
					document.getElementById("hstartcity").innerHTML = aa.oAddr;
					document.getElementById("hendcity").innerHTML = aa.dAddr;
					document.getElementById("startcity").value = aa.oAddr;
					document.getElementById("endcity").value = aa.dAddr;
					document.getElementById("receivepersion").innerHTML = aa.receivePersion;
					document.getElementById("sendpersion").innerHTML = aa.sendPersion;
					document.getElementById("hdistance").innerHTML = aa.distance;
					document.getElementById("distance").value = aa.distance;
					document.getElementById("hcargono").innerHTML = aa.cargono;
					document.getElementById("hcargoname").innerHTML = aa.cargoname;
					document.getElementById("hmeasure").innerHTML = aa.measureName;
					document.getElementById("measure").value = aa.measureName;
					document.getElementById("measure_name").text = aa.measureName;
					document.getElementById("cargono").value = aa.cargono;
					document.getElementById("cargoname").value = aa.cargoname;
					document.getElementById("cargoname_v_id").value = aa.cargoid;
					document.getElementById("routename_v_id").value = aa.routeid;
					document.getElementById("cargoname_v").value = aa.cargoname;
					document.getElementById("routename_v").value = aa.routename;
					document.getElementById("freightname_v").value = aa.desc1;
				}
				
			}
		}
	});
}
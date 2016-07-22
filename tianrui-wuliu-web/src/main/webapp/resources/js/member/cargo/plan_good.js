///**
// * 货运计划
// */
var freightName = new Array();
var routName = new Array();
var cargoName = new Array();
$(function(){
	blurSearch();
	$("#plancreate").addClass("selected");
});
/**
 * 货物名称改变
 */
function nameChange(){
	var routeid = $("#routename_v_id").val();
	var cargoid = $("#cargoname_v_id").val();
	var routename = $("#routename_v").val();
	var cargoname = $("#cargoname_v").val();
	if(cargoname != ""&&cargoName.indexOf(cargoname)==-1){
		alert("请选择货物名称");
		return;
	}
	if(routename != ""&&routName.indexOf(routename)==-1){
		alert("请选择路径名称");
		return;
	}
	if(cargoname == ""){
		cargoid = "";
	}
	if(routename == ""){
		routeid = "";
	}
	if(cargoname == ""&&routename == ""){
		blurSearch();
	}
	$.ajax({
		url:CONTEXTPATH+'/trwuliu/Member/cargo/findFreight',
		data:{ "cargoid":cargoid,
			"routeid":routeid
		},
		type:"post",
		success: function(rs) {
			if(rs.code!="000000"){
				alert("页面加载失败");
			}else{
				//页面
				var list = rs.data;
				freighthml(list);
			}
		}
	});
}

//function innerHml(list){
//	var hml = "<label> 运价策略：</label>"+
//	"<select class='form-control' id='Freightname' name='freightid' onblur='FreightChange();'>"+
//	"<option value=''>请选择</option>";
//	var eml ="</select>";
//	var m = "";
//	for (var a = 0; a < list.length; a++) {
//		m += "<option value='"+list[a].id+"'>"+list[a].desc1+"</option>";
//	}
//	document.getElementById("select03").innerHTML = hml+m+eml;
//}

/**
 * 运价策略联动
 */
function FreightChange(){
	var freightid = $("#freightname_v_id").val();
	var freightname = $("#freightname_v").val();
	if(freightname != ""&&freightName.indexOf(freightname)==-1){
		alert("请选择运价策略");
		return;
	}
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
/**
 * 清空信息
 */
function clearMassage(){
	document.getElementById("hprice").innerHTML = "";
	document.getElementById("price").value = "";
	document.getElementById("hpriceunits").innerHTML = "";
	document.getElementById("priceunits").value = "";
	document.getElementById("hstartcity").innerHTML = "";
	document.getElementById("hendcity").innerHTML = "";
	document.getElementById("startcity").value = "";
	document.getElementById("endcity").value = "";
	document.getElementById("receivepersion").innerHTML = "";
	document.getElementById("sendpersion").innerHTML = "";
	document.getElementById("hdistance").innerHTML = "";
	document.getElementById("distance").value = "";
	document.getElementById("hdesc2").innerHTML = "";
	document.getElementById("hcargoname").innerHTML = "";
	document.getElementById("hmeasure").innerHTML = "";
	document.getElementById("measure").value = "";
	document.getElementById("measure_name").text = "";
	document.getElementById("desc2").value = "";
	document.getElementById("cargoname").value = "";
	document.getElementById("cargoname_v_id").value = "";
	document.getElementById("routename_v_id").value = "";
	document.getElementById("cargoname_v").value = "";
	document.getElementById("routename_v").value = "";
	document.getElementById("freightname_v").value = "";
}

function FreightChangeNew(id){
		$.ajax({
			url:CONTEXTPATH+'/trwuliu/Member/cargo/findFreightById',
			data:{ 
				"id":id
			},
			type:"post",
			success: function(rs) {
				if(rs.code!="000000"){
					alert("页面加载失败");
				}else{
					//页面
					var aa = rs.data;
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
		});
	}
/**
 * 添加计划
 */
function savePlan(){
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
		url:CONTEXTPATH+'/trwuliu/Member/cargoPlan/savePlan',
		data:$('#saveplan').serialize(),// 你的formid
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
function blurSearch(){
	$.ajax({
		url : CONTEXTPATH + '/trwuliu/Member/cargo/findPlanGood',// 跳转到 action
		data : {},
		type : "post",
		success : function(result) {
			var data = result.data || [];
			var cargo = data.cargoList || [];
			var freight = data.freightList  || [];
			var route = data.routeList;
			var cargos = new Array();
			var routes = new Array();
			
			for (var int = 0; int < cargo.length; int++) {
				var carObj = new Object();
				carObj.value = cargo[int].id;
				carObj.label = cargo[int].materName;
				cargos[int] = carObj;
				
				var name = new Object();
				name = cargo[int].materName;
				cargoName[int] = name;
			}
			$( "#cargoname_v" ).autocomplete({
				minLength: 0,
		        source: cargos,
		        select: function( event, ui ) {
		            $( "#cargoname_v" ).val( ui.item.label );
		            $( "#cargoname_v_id" ).val( ui.item.value );
		            return false;
		        }
		    });
			freighthml(freight);
			for (var int = 0; int < route.length; int++) {
				var rouObj = new Object();
				rouObj.value = route[int].id;
				rouObj.label = route[int].routename;
				routes[int] = rouObj;
				var name = new Object();
				name = route[int].routename;
				routName[int] = name;
			}
			$( "#routename_v" ).autocomplete({
				minLength: 0,
		        source: routes,
		        select: function( event, ui ) {
		            $( "#routename_v" ).val( ui.item.label );
		            $( "#routename_v_id" ).val( ui.item.value );
		            return false;
		        }
		    });
			
		}
	});
}

function freighthml(freight){
	var freights = new Array();
	
	for (var int = 0; int < freight.length; int++) {
		var freObj = new Object();
		freObj.value = freight[int].id;
		freObj.label = freight[int].desc1;
		freights[int] = freObj;
		
		var name = new Object();
		name = freight[int].desc1;
		freightName[int] = name;
	}
//	FreightChange();
	$( "#freightname_v" ).autocomplete({
		minLength: 0,
        source: freights,
        select: function( event, ui ) {
            $( "#freightname_v" ).val( ui.item.label );
            $( "#freightname_v_id" ).val( ui.item.value );
//            FreightChange();
            return false;
        }
    });
}

function countCost(){
	var price = $("#price").val();
	var totalplanned = $("#totalplanned").val();
	var count = price*totalplanned;
	$("#planprice").val(count);
}

    $(function(){
    	FreightChange_new();
    });
    
    function FreightChange_new(){
    	var freightid = $("#freightname_v_id").val();
    	var freightname = $("#freightname_v").val();
//    	if(freightname != ""&&freightName.indexOf(freightname)==-1){
//    		alert("请选择运价策略");
//    		return;
//    	}
    	if(freightname == ""){
//    		clearMassage();
//    		blurSearch();
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
$(function(){
	planModel();
});

function ownerChange(){
	$.ajax({
		url : CONTEXTPATH + '/ownerDriver/findById',// 跳转到 action
		data : {},
		type : "post",
		success : function(result) {
			var hml = "";
			if(result.code!="000000"){
				alert("数据传输失败");
			}else{
				var data = result.data ;
				var hm = "<ul>";
				var hl = "<div class='clear'></div></ul>"
					var hml = "";
				for (var int = 0; int < data.length; int++) {
					var avatarsPath = data[int].avatarsPath;
					if(avatarsPath == null){
						avatarsPath = "<%=basePath%>"
					}
					hml +="<li><input type='radio'  name='chezhu'><label>"+data[int].drivername+"</label>"+
                                "<div class='goods_listdt' style='display: none;'>"+
                                   "<div class='goods_listdt1'>"+
                                        "<img src='"+data[int].avatarsPath+"' class='img-circle'></img>"+
                                        "<p>"+data[int].drivername+"</p>"+
                                    "</div>"+
                                    "<div class='goods_listdt2'>"+
                                    	"<input type='hidden' id='drivername"+int+"' value='"+data[int].drivername+"'>"+
                                    	"<input type='hidden' id='drivertel"+int+"' value='"+data[int].drivertel+"'>"+
                                    	"<input type='hidden' id='driverid"+int+"' value='"+data[int].driverid+"'>"+
                                        "<p>手机："+data[int].drivertel+"</p>"+
                                        "<p>信息：郑州司机</p>"+
                                        "<p>备注：郑州司机</p>"+
                                    "</div></div></li>";
				}
				document.getElementById("chezhumingxi").innerHTML = hm+hml+hl;
				// 车主列表名片的显示隐藏
			    $(".goods_czlist ul li").mouseover(function () {
			    	$(this).find('.goods_listdt').show();
			    });
			    $(".goods_czlist ul li").mouseout(function () {
			    	$(this).find('.goods_listdt').hide();
			    });
			}
		}
	});
}

$("#add_save").click(function() {
	var freightid = $("#Freightname option:selected").val();
	var routeid = $("#routename option:selected").val();
	var cargono = $("#cargono").val();
	var cargoname = $("#cargoname").val();
	var measure = $("#measure").val();
	var opoc = $("#opoc").val();
	var dpdc = $("#dpdc").val();
	var distance = $("#distance").val();
	var priceunits = $("#priceunits").val();
	var price = $("#price").val();
	var totalplanned = $("#totalplanned").val();
	var starttime = $("#starttime").val();
	var endtime = $("#endtime").val();
	var planprice = $("#planprice").val();
	var linkman = $("#linkman").val();
	var telephone = $("#telephone").val();
	var status = "0";
	var flag = $("input[name='istemplate'][type='checkbox']").is(':checked');
	var chezhu = document.getElementsByName("chezhu");
	var ss ="";
	for (var int = 0; int < chezhu.length; int++) {
		if(chezhu[int].checked){
			ss = int;
		}
	}
	var drivertel = $("#drivertel"+ss).val();
	var driverid = $("#driverid"+ss).val();
	var drivername = $("#drivername"+ss).val();
	var goods = document.getElementsByName("goods");
	var desc1 = "";
	for (var int = 0; int < goods.length; int++) {
		if(goods[int].checked){
			desc1 = goods[int].value;
		}
	}
	var istemplate ="";
	if(flag){
		istemplate = "0";
	}else{
		istemplate = "1";
	}
	$.ajax({
		url : CONTEXTPATH + '/trwuliu/Member/cargoPlan/savePlan',// 跳转到 action
		data : {freightid:freightid,routeid:routeid,measure:measure,cargono:cargono,cargoname:cargoname,
			startcity:opoc,endcity:dpdc,distance:distance,priceunits:priceunits,price:price,totalplanned:totalplanned,
			planprice:planprice,linkman:linkman,telephone:telephone,
			status:status,istemplate:istemplate,DESC1:desc1,starttimeStr:starttime,endtimeStr:endtime,
			vehicleownerid:driverid,vehicleownername:drivername,vehicleownerphone:drivertel},
			type : "post",
			success : function(result) {
				if(result && result.code=="000000"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
			}
	});
});

function planModel(){
	var id = $("#planId").val();
	if(id!=null){
		$.ajax({
			url : CONTEXTPATH + '/trwuliu/Member/cargoPlan/findPlanById',
			data : {id:id},
			type : "post",
			success : function(result){
				if(result.code!="000000"){
					alert("数据传输失败");
				}else{
					var data = result.data;
					alert("1111"+data.cargono+":"+data.cargoname+":"+data.measure+":"+data.starttime+":"+data.linkman);
					$("#Freightname option").val(data.freightid);
					$("#routename option:selected").val(data.routeid);
					$("#cargono").val(data.cargono);
					$("#cargoname").val(data.cargoname);
					$("#measure").val(data.measure);
					$("#opoc").val(data.startcity);
					$("#dpdc").val(data.endcity);
					$("#distance").val(data.distance);
					$("#priceunits").val(data.priceunits);
					$("#price").val(data.price);
					$("#totalplanned").val(data.totalplanned);
					$("#starttime").val(data.starttime);
					$("#endtime").val(data.endtime);
					$("#planprice").val(data.planprice);
					$("#linkman").val(data.linkman);
					$("#telephone").val(data.telephone);
					$("#drivertel").val(data.drivertel);
					$("#driverid").val(data.driverid);
					$("#drivername").val(data.drivername);
					var cghm ="<tr><td id='cargono'>"+data.cargono+"</td>"+
                    "<td id='cargoname1'>"+data.cargoname+"</td></tr>";
					document.getElementById("cargoH").innerHTML = cghm;
					document.getElementById("measure").innerHTML = cargo.measure;
					var cghm ="<tr><td id='priceunits'>"+data.priceunits+"</td>"+
	                "<td id='price'>"+data.price+"</td>"+
	                "<td ></td></tr>";
					document.getElementById("freightH").innerHTML = cghm;
				}
			}
		});
	}
}

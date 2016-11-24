$(function(){
	$("#table_0").hide();
	$("#table_1").hide();
	$("#table_2").hide();
	$("#table_3").hide();
	$("#table_4").hide();
	$("#table_5").hide();
	var a=0 ;
	function index(){
		//旧图淡出
		var fadeout = a%6;
		$("#table_"+fadeout).fadeOut(3000);
		a++;
		if(a==0){
			findDetail("原煤运输业务",0,a);
		}else if(a==1){
			findDetail("原煤运输业务",1,a);
		}else if(a==2){
			findDetail("熟料运输业务",0,a);
		}else if(a==3){
			findDetail("熟料运输业务",1,a);
		}else if(a==4){
			findDetail("水泥运输业务",0,a);
		}else if(a==5){
			findDetail("水泥运输业务",1,a);
		}
		$("#showvalue").val(a);
		//新图淡入
		var fadein = a%6;
		$("#table_"+fadein).fadeIn(3000);
	}
	setInterval(index,5000);
});

function findDetail(data,pageNo,id){
	$("#html_table_"+id).empty();
	$.ajax({
		url : "/adcount/detailRoutetable",//
		data : {"type":data,
				"pageNo":pageNo
		},
		type : "post",
		success : function(rs){
			var data = rs.data;
			var hml = "";
			for (var a = 0; a < data.length; a++) {
				hml += "<tr>";
				if(a==0){
					hml +="<td rowspan='2'>"+data[a].type+"</td>";
				}
				hml +="<td >"+data[a].place1+"</td>" +
						"<td >"+data[a].data1+"</td>" +
						"<td >"+data[a].data2+"</td>" +
						"<td >"+data[a].data3+"</td>" +
						"<td >"+data[a].data4+"</td>" +
						"<td >"+data[a].data5+"</td>" +
						"<td >"+data[a].data6+"</td>" +
						"<td >"+data[a].data7+"</td>" +
						"<td >"+data[a].data8+"</td>" +
						"<td >"+data[a].data9+"</td>" +
						"<td >"+data[a].data10+"</td>" +
						"<td >"+data[a].data11+"</td>" +
						"<td >"+data[a].data12+"</td>" +
						"<td >"+data[a].data13+"</td>" +
						"<td >"+data[a].data14+"</td>" +
						"</tr>";
			}
			$("#html_table_"+id).html(hml);
		}
		
	});
}

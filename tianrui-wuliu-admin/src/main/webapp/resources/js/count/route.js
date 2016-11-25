
// 初始化处理
$(function() { 
	$("#table_0").hide();
	$("#table_2").hide();
	$("#table_4").hide();
	index();
});

function index(){
	$("#table_0").hide();
	$("#table_2").hide();
	$("#table_4").hide();
	$.ajax({
		url : '/countRoute/find',// 跳转到 action
		data : {"pageSize":4,
				"pageNo":0,
				"type":$("#routetype").val()
		},
		type : "post",
		success : function(rs) {
			var data = rs.data;
			var hml = "";
			for (var a = 0; a < data.length; a++) {
				var id = "";
				if(data[a].type == "熟料运输业务"){
					id = "2";
				}else if(data[a].type == "原煤运输业务"){
					id = "0";
				}else if(data[a].type == "水泥运输业务"){
					id = "4";
				}
				$("#table_"+id).show();
				hml += "<tr>";
				if(a==0){
					hml +="<td rowspan='4'>"+data[a].type+"</td>";
				}
				hml +=  "<td ><input type='text' id='place1value"+data[a].id+"' class='inputStyle' value='"+data[a].place1+"'/></td>" +
						"<td ><input type='text' id='data1value"+data[a].id+"' class='inputdata' value='"+data[a].data1+"'/></td>" +
						"<td ><input type='text' id='data2value"+data[a].id+"' class='inputdata' value='"+data[a].data2+"'/></td>" +
						"<td ><input type='text' id='data3value"+data[a].id+"' class='inputdata' value='"+data[a].data3+"'/></td>" +
						"<td ><input type='text' id='data4value"+data[a].id+"' class='inputdata' value='"+data[a].data4+"'/></td>" +
						"<td ><input type='text' id='data5value"+data[a].id+"' class='inputdata' value='"+data[a].data5+"'/></td>" +
						"<td ><input type='text' id='data6value"+data[a].id+"' class='inputdata' value='"+data[a].data6+"'/></td>" +
						"<td ><input type='text' id='data7value"+data[a].id+"' class='inputdata' value='"+data[a].data7+"'/></td>" +
						"<td ><input type='text' id='data8value"+data[a].id+"' class='inputdata' value='"+data[a].data8+"'/></td>" +
						"<td ><input type='text' id='data9value"+data[a].id+"' class='inputdata' value='"+data[a].data9+"'/></td>" +
						"<td ><input type='text' id='data10value"+data[a].id+"' class='inputdata' value='"+data[a].data10+"'/></td>" +
						"<td ><input type='text' id='data11value"+data[a].id+"' class='inputdata' value='"+data[a].data11+"'/></td>" +
						"<td ><input type='text' id='data12value"+data[a].id+"' class='inputdata' value='"+data[a].data12+"'/></td>" +
						"<td ><input type='text' id='data13value"+data[a].id+"' class='inputdata' value='"+data[a].data13+"'/></td>" +
						"<td ><input type='text' id='data14value"+data[a].id+"' class='inputdata' value='"+data[a].data14+"'/></td>" +
						"<td ><a onclick='updataValue(\""+data[a].id+"\")'>【修改】</a></td>" +
						"</tr>";
				}
				$("#html_table_"+id).html(hml);
		}
	});
}

function updataValue(id){
	if(window.confirm('确定修改本条数据吗？')){
		$.ajax({
			url : '/countRoute/update',// 跳转到 action
			data : {
				"id":id,
				"place1":$("#place1value"+id).val(),
				"data1":$("#data1value"+id).val(),
				"data2":$("#data2value"+id).val(),
				"data3":$("#data3value"+id).val(),
				"data4":$("#data4value"+id).val(),
				"data5":$("#data5value"+id).val(),
				"data6":$("#data6value"+id).val(),
				"data7":$("#data7value"+id).val(),
				"data8":$("#data8value"+id).val(),
				"data9":$("#data9value"+id).val(),
				"data10":$("#data10value"+id).val(),
				"data11":$("#data11value"+id).val(),
				"data12":$("#data12value"+id).val(),
				"data14":$("#data13value"+id).val(),
				"data14":$("#data14value"+id).val(),
			},
			type : "post",
			success : function(rs) {
				if(rs.code==000000){
					index();
				}else{
					alert(rs.error);
				}
			}
		});
	}
}

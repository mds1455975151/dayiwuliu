$(function(){
	$.ajax({
		url : CONTEXTPATH + '/freightinfo/lineChart',
		data : {id:$("#freightid").val()},
		type : "post",
		success : function(result) {
			if(result.code == "000000"){
				var tem = [];
				var dat = [];
				var data = result.data;
				for (var i = 0; i < data.length; i++) {
					tem.push(data[i].updatetimeStr);
					dat.push(data[i].price);
				}
				// 折线图
			   	var lineChartData = {
			        "labels" : tem,
			        "datasets" : [{
			                "fillColor" : "rgba(220,220,220,0.5)",
			                "strokeColor" : "rgba(220,220,220,1)",
			                "pointColor" : "rgba(220,220,220,1)",
			                "pointStrokeColor" : "#fff",
			                "data" : dat
			            },]}
			   	var myLine = new Chart(document.getElementById("myChart").getContext("2d")).Line(lineChartData);
			}
		}
		});
});

var stm = $("#stimestr").val();
	if(stm == 1){
		$(".stm1").attr("disabled",true);
		$(".stm2").attr("disabled",false);
	}else if(stm == 2){
		$(".stm1").attr("disabled",false);
		$(".stm2").attr("disabled",true);
	}
function driverSearch(){
	init(0);
}
function displayData(pageNo){
	var page = $("#recPageNo").val();
	if(page != ""){
		init(page-1);
		$("#recPageNo").val("");
	}else{
		init(pageNo);
	}
}
function reset(){
	$("#vehicleNo").val("");
	init(0);
}
function getParams(pageNo){
	var params = {pageNO:pageNo,
			pageSize:10,
			dataType:$("#dataType").val(),
			ledType:$("#ledType").val()}
	return params;
}
$(".uptConfig").on("click",function(){
	if(window.confirm('修改状态，LED展示数据将会改变，确定修改吗？')){
		var index = layer.load(2, {
			//time: 1000*10,
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		$(this).attr("disabled",true);
		$.ajax({
			url:"/admin/LED/setConfig",
			data:{},
			type:"POST",
			success:function(ret){
				if(ret.code==000000){
					location=location;
				}else{
					alert(ret.error);
				}
				$(this).attr("disabled",false);
			}
		});
	}
});

function init(pageNo){
	$.ajax({
		url:"/admin/LED/find",
		type:"POST",
		data:getParams(pageNo),
		success:function(ret){
			if(ret.code == '000000'){
				var data = ret.data.list;
				var total = ret.data.total;
				var pageNo = ret.data.pageNo;
				var pageSize = ret.data.pageSize;
				innerHml(data);
				$('#totalRecords').html(total);
				document.getElementById("goPage").value = pageNo+1;
				$("#totalPages").html(parseInt((total-1)/pageSize+1));
				$('#totalPages').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
				$("#pagination").pagination(total, {
				    callback: pageCallback,
				    prev_text: '上一页',
				    next_text: '下一页',
				    items_per_page:pageSize,
				    num_display_entries:4,
				    current_page:pageNo,
				    num_edge_entries:1,
				    maxentries:total,
				    link_to:"javascript:void(0)"
				});
			}
		}
	});
}

function innerHml(data){
	$("#innerHtml").empty();
	for (var a = 0; a < data.length; a++) {
		var hml = "<tr>" +
				"<td>"+(a+1)+"</td>" +
				"<td>"+changeType(data[a].dataType)+"</td>" +
				"<td>"+changeLEDType(data[a].ledType)+"</td>" +
				"<td><input type='text' class='uptRemark' dataId='"+data[a].id+"' value='"+(data[a].remark||"")+"'></td>" +
				"<td><input type='text' class='uptCountData' dataId='"+data[a].id+"' value='"+(data[a].countdata||"")+"'</td>" +
				"<td>"+new Date(data[a].createTime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
				"</tr>";
		$("#innerHtml").append(hml);
		
	}
	
	$(".uptRemark").on("change",function(){
		var value = $(this).val();
		var id = $(this).attr("dataId");
		$.ajax({
			url:"/admin/LED/update",
			data:{id:id,remark:value},
			type:"POST",
			success:function(ret){
				if(ret.code!=000000){
					alert(ret.error);
				}
			}
		});
	});

	$(".uptCountData").on("change",function(){
		var value = $(this).val();
		var id = $(this).attr("dataId");
		$.ajax({
			url:"/admin/LED/update",
			data:{id:id,countdata:value},
			type:"POST",
			success:function(ret){
				if(ret.code!=000000){
					alert(ret.error);
				}
			}
		});
	});
}



function changeType(type){
	var neme = "";
	switch (type) {
	case "1":
		neme = "正式数据";
		break;
	case "2":
		neme = "测试数据";
		break;
	default:
		break;
	}
	return neme;
}

function changeLEDType(type){
	var neme = "";
	switch (type) {
	case "1":
		neme = "运量";
		break;
	case "2":
		neme = "货物类型";
		break;
	case "3":
		neme = "车辆类型";
		break;
	case "4":
		neme = "车辆归属地";
		break;
	case "5":
		neme = "车辆使用频率";
		break;
	case "6":
		neme = "运费统计";
		break;
	case "7":
		neme = "车主";
		break;
	case "8":
		neme = "货主";
		break;
	case "9":
		neme = "head统计";
		break;
	case "10":
		neme = "当天统计";
		break;
	default:
		neme = "配置数据";
		break;
	}
	return neme;
}



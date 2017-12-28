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
	$("#selecttype").val("");
	init(0);

}
function getParams(pageNo){
	var params = {pageNo:pageNo,
			groupType:$("#selecttype").val(),
			pageSize:10}
	return params;
}

function init(pageNo){
	$.ajax({
		url:"/admin/aberrant/selectMsgGroup",
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
	if(!data){
		var hmlnull = "";
		hmlnull +='<td colspan="12">';
		hmlnull +='<div class="ht_none">';
		hmlnull +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
		hmlnull +='<div>';
		hmlnull +='<h3>唉呀！查询不到您要找的内容</h3>';
		hmlnull +='<p>换个查询条件试试吧？</p>';
		hmlnull +='</div>';
		hmlnull +='</div>';
		hmlnull +='</td>';
		$("#innerHtml").append(hmlnull);
	}else {
		for (var a = 0; a < data.length; a++) {
			
			var grouptype=data[a].groupType;
			var typeshow='';
			if(grouptype == 1){
				typeshow="司机";
			}else if(grouptype == 2){
				typeshow="车主";
			}else if(grouptype == 3){
				typeshow="货主";
			}
			
			var tsid = data[a].id;
			var customerId =data[a].customerId;
			var hml = "<tr>" +
					"<td>"+(a+1)+"</td>" +
					"<td>"+(data[a].chinnlId||"")+"</td>" +
					"<td>"+(data[a].userName||"")+"</td>" +
					"<td>"+(data[a].userPhone||"")+"</td>" +
					"<td>"+(data[a].cellphone||"")+"</td>" +
					"<td>"+(data[a].groupName||"")+"</td>" +
					"<td>"+(data[a].groupRemark||"")+"</td>" +
					"</tr>";
			$("#innerHtml").append(hml);
		}
	}
}

//更新操作
$("#typeupdate").on("click",function(){
	var datatype = $("#selecttype").val();
	if(datatype==1 || datatype==2 || datatype==3){
		$(".loadingbg").show();
		$.ajax({
			url:"/admin/aberrant/updategroup",
			type:"POST",
			data:{"groupType":datatype},
			success: function(result) {
				$(".loadingbg").hide();
				if(result.code == "000000"){
					init(0);
				}else{
					alert(result.error);
				}
			}
		
		});
	}
	else{
		alert("请先选择一个群体类型再更新");
	}

})

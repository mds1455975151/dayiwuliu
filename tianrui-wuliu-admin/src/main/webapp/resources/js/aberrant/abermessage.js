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
	$("#pushqudao").val("");
	$("#pushgroup").val("");
	$("#starttime").val("");
	$("#endtime").val("");
	init(0);

}
function getParams(pageNo){
	var params = {pageNo:pageNo,
			groupType:$("#pushgroup").val(),
			pageSize:10}
	return params;
}

function init(pageNo){
	$.ajax({
		url:"/admin/aberrant/selectMsgGroupPush",
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
			var messtatus=data[a].pushStatus;
			var statusshow='';
			if(messtatus == 0){
				statusshow="未推送";
			}else if(messtatus == 1){
				statusshow="已推送";
			}
			var hml = "<tr>" +
					"<td>"+(a+1)+"</td>" +
					"<td>"+(data[a].modifyTime == undefined ? "" : (new Date(data[a].modifyTime).format("yyyy-MM-dd hh:mm:ss")))+"</td>" +
					"<td>"+(data[a].chinnalType||"")+"</td>" +
					"<td>"+(data[a].groupName||"")+"</td>" +
					"<td>"+(statusshow||"")+"</td>" +
					"<td>"+(data[a].pushMessage||"")+"</td>" +
					"</tr>";
			$("#innerHtml").append(hml);
		}
	}
}

//新增操作
function newmodal(){
	var newtype = $("#newtype").val();
	var newqudao = $("#newqudao").val();
	var newtext = $("#newtext").val();
	$.ajax({
		url:"/admin/aberrant/updategroup",
		type:"POST",
		data:{"groupType":newtype},
		success: function(result) {
			if(result.code == "000000"){
				alert("更新完成！");
				init(0);
			}else{
				alert(result.error);
			}
		}
	
	});
}

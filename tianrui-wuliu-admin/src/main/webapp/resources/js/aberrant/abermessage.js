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
	//开始时间  格式转换
	var starttime = $("#starttime").val();
	var starL = null;
	if(starttime != ""){
		starL = Date.parse(new Date(starttime));
	}
	//结束时间   格式转换
	var endtime = $("#endtime").val();
	var endL = null;
	if(endtime != ""){
		endL = Date.parse(new Date(endtime));
	}
	if(starttime != "" && endtime == ""){
		alert("请选择结束时间");
	}
	if(starttime == "" && endtime != ""){
		alert("请选择开始时间");
	}
	var params = {pageNo:pageNo,
			msgType:$("#pushqudao").val(),
			groupType:$("#pushgroup").val(),
			timeBegin:starL,
			timeEnd:endL,
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
	if(data.length==0){
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
			var hml = "<tr>" +
					"<td>"+(a+1)+"</td>" +
					"<td>"+(data[a].createTime == undefined ? "" : (new Date(data[a].createTime).format("yyyy-MM-dd hh:mm:ss")))+"</td>" +
					"<td>"+(data[a].chinnalName||"")+"</td>" +
					"<td>"+(data[a].groupName||"")+"</td>" +
					"<td>"+(data[a].pushCount||"")+"</td>" +
					"<td>"+(data[a].pushMessage||"")+"</td>" +
					"</tr>";
			$("#innerHtml").append(hml);
		}
	}
}

//新增操作
$("#messpush_new").on("click",function(){
	var newtype = $("#newtype").val();
	var newqudao = $("#newqudao").val();
	var newtext = $("#newtext").val();
	$(".loadingbg").show();
	$.ajax({
		url:"/admin/aberrant/groupPushMsg",
		type:"POST",
		data:{"groupType":newtype,"msgType":newqudao,"msgTxt":newtext},
		success: function(result) {
			if(result.code == "000000"){
				init(0);
				$(".loadingbg").hide();
				var newtype = $("#newtype").val("");
				var newqudao = $("#newqudao").val("");
				var newtext = $("#newtext").val("");
			}else{
				alert(result.error);
			}
		}
	
	});
})

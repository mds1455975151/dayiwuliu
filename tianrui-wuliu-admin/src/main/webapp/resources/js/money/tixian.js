
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
	$("#txbank").val("");
	$("#txcell").val("");
	$("#txno").val("");
	$("#txliushui").val("");
	$("#txqudao").val("");
	$("#starttime").val("");
	$("#endtime").val("");
	init(0);
}
function getParams(pageNo){
	var type = $(".bag_tab").find(".select").attr("type");
	var expectpaycompany = "";
	if($("#txqudao").val() != -1){
		expectpaycompany = $("#txqudao").val();
	}
	//开始时间 格式转换
	var starttime = $("#starttime").val();
	var starL = null;
	if(starttime != ""){
		starL = Date.parse(new Date(starttime));
	}
	//结束时间 格式转换
	var endtime = $("#endtime").val();
	var endL = null;
	if(endtime != ""){
		endL = Date.parse(new Date(endtime)) + (24 * 3600 * 1000);
	}
	var params = {pageNo:pageNo,
			pageSize:10,
			transactionstate:type,
			timeBegin:starL,
			timeEnd:endL,
			bankcodeno:$("#txbank").val(),
			cellPhone:$("#txcell").val(),
			useryhno:$("#txno").val(),
			capitalno:$("#txliushui").val(),
			expectpaycompany:expectpaycompany
	}
	return params;
}
function init(pageNo){
	$.ajax({
		url:"/admin/money/withdrawRecordSelect",
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
	$(".tbshow").hide();
	var type = $(".bag_tab").find(".select").attr("type");
	
	if(type == 2){
		$(".type_2").show();
	}else if(type == 3){
		$(".type_3").show();
	}
	
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
			var showTxt = "";
			if(type == 2){
				 showTxt = "<td>"+new Date(data[a].endtime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
					"<td>"+((data[a].availablemoney/100).toFixed(2)||"")+"</td>";
			}else if(type == 3){
				 showTxt = "<td>"+new Date(data[a].endtime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
					"<td>"+(data[a].errormessage||"")+"</td>";
			}
			var hml = "<tr>" +
				"<td>"+(a+1)+"</td>" +
				"<td>"+(data[a].username||"")+"</td>" +
				"<td>"+(data[a].cellphone||"")+"</td>" +
				"<td>"+(data[a].useryhno||"")+"</td>" +
				"<td>"+new Date(data[a].begintime).format("yyyy-MM-dd hh:mm:ss")+"</td>" +
				"<td>"+(data[a].capitalno||"")+"</td>" +
				"<td>"+((data[a].money/100).toFixed(2)||"")+"</td>" +
				"<td>"+(data[a].expectpaycompany||"")+"</td>" +
				"<td>"+(data[a].bankname||"")+"</td>" +
				"<td>"+(data[a].bankcodeno||"")+"</td>" +
				"<td>"+((data[a].availablemoney/100).toFixed(2)||"")+"</td>" +
				showTxt +
				"</tr>";
				$("#innerHtml").append(hml);
		}
	}
	
}

$(".withdrawststus").on("click",function(){
	$(".withdrawststus").removeClass("select");
	$(this).addClass("select");
	init(0);
});


function timeYear(ti){
	   var mydate = new Date(ti);
	   var year = mydate.getFullYear();
	   return year;
}
function timeMonth(ti){
	   var reday = ""; 
	    if (ti >= 10 )
        {
	    	reday += ti;
        }
        else
        {
        	reday += "0" + ti;
        } 
	   return reday;
}
function timeDay(ti){
	   var mydate = new Date(ti);
	   var Day =  mydate.getDate();
	   var reday = ""; 
	    if (Day >= 10 )
     {
	    	reday += Day ;
     }
     else
     {
     	reday += "0" + Day ;
     } 

	   return reday;
}
function timetd(){
	$("#starttime").val("");
	$("#endtime").val("");
	 var mydate = new Date();
	 var month = mydate.getMonth()+1; 
	   $("#starttime").val(timeYear(mydate) +"-"+ timeMonth(month) + "-" + timeDay(mydate));
	   $("#endtime").val(timeYear(mydate) +"-"+ timeMonth(month) + "-" + timeDay(mydate));
}
function timeseven(){
	$("#starttime").val("");
	$("#endtime").val("");
	
	var mydate = new Date();
	var alltime =new Date(mydate.getTime() - 7 * 24 * 3600 * 1000);
	 var month1 = mydate.getMonth()+1; 
	 var month2 = alltime.getMonth()+1; 
	   $("#starttime").val(timeYear(alltime) + "-" + timeMonth(month2) +"-" + timeDay(alltime));
	   $("#endtime").val(timeYear(mydate) +"-"+ timeMonth(month1) + "-" + timeDay(mydate));
}
function timethirty(){
	$("#starttime").val("");
	$("#endtime").val("");
	   var mydate = new Date();
	   var month = mydate.getMonth(); 
	   $("#starttime").val(timeYear(mydate) + "-" + timeMonth(month) +"-" + timeDay(mydate));
	   $("#endtime").val(timeYear(mydate) +"-"+ timeMonth(month+1) + "-" + timeDay(mydate));
}


var pageSize = 10;
var pageNo = 0;
var hml = "";
$(function(){
	$("#ownerAnlian").addClass("selected");
	$(".goods_more").hide();
	index(0);
});

function index(no){
	$.ajax({
		url:"/trwuliu/billAnlian/page",
		data:{"searchKey":$("#searchKey").val(),
			"type":type,
			"pageNo":no,
			"pageSize":pageSize
		},
		type : "post",
		dataType:"json",
		success:function(rs){
			if(rs.code=="000000"){
				var total = rs.data.total;
				var pno = rs.data.pageNo;
				var psize = rs.data.pageSize;
				if((pno+1)*psize<total){
					$(".pageMore").show();
				}else{
					$(".pageMore").hide();
				}
				if(total == 0){
					$(".pageNone").show();
				}
				innerHTML(rs.data.list);
				
			}
		}
	});
}

$(".searchBtn").on("click",function(){
	hml = "";
	index(0);
});


function innerHTML(data){
	for (var a = 0; a < data.length; a++) {
		hml +="<tr>" +
			"<td>"+data[a].billno+"</td>" +
			"<td>"+data[a].cph+"</td>" +
			"<td><p><i class='iconfont icon-dizhi billc1'></i>"+data[a].qycs+"</td>" +
			"<td><p><i class='iconfont icon-dizhi billc2'></i>"+data[a].mdcs+"</td>" +
			"<td>"+data[a].shr+"</td>" +
			"<td>"+data[a].createtimeStr+"</td>" +
			"<td><a ><button class='btn btnyello delBtn' onclick=\"billdetail('"+data[a].id+"')\"'>查看详情</button></a>" +
					"<a ><button class='btn btnyello delBtn' onclick=\"billPosition('"+data[a].id+"')\"'>运单跟踪</button></a>" +
					"</td>" +
			"</tr>";
	}
	document.getElementById("innhtml").innerHTML = hml;
}
$(".pageMore").on("click",function(){
	pageNo = pageNo+1;
	index(pageNo);
});

function billdetail(id){
	window.location.href="/trwuliu/billAnlian/detail?id="+id;
}
function billPosition(id){
	window.location.href="/trwuliu/billAppoint/tarckAnlian?id="+id;
}

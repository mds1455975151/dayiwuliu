$(function(){
	$("#plantemplate").addClass("selected");
	planSearch(1);
	
	//更多绑定事件
	$(".pageMore").click(function(){
		planSearch($(this).attr("pageNo"));
	});
});
function planSearch(pageNo){
	var istemplate = "1";
	
	if( pageNo ==1 ){
		$("#plan_tbody").empty();
	}
	
	$.ajax({
		url : CONTEXTPATH + "/trwuliu/Member/cargoPlan/findPlan",
		data : {creator:memberId,istemplate:istemplate,status:"-1","key":$("#search_v").val(),"pageNo":pageNo},
		type : "post",
		success : function (result){
			if(result.code !="000000"){
				alert("数据加载失败");
			}else{
				var data = result.data.list || [];
				var hml = "";
				var hl = "";
				for(var i=0;i<data.length;i++){
					var sta = "";
					hml += "<tr>"+
						"<td ><a href='#' target='_blank'>"+data[i].desc2+"</a></td>"+
						"<td >"+data[i].linkman+" </td>"+
						"<td >"+data[i].telephone+" </td>"+
						"<td title='"+data[i].startcity+"—>"+data[i].endcity+"'>"+data[i].startcity+"—>"+data[i].endcity+"</td>"+
//						"<td >"+data[i].starttimeStr+"-"+data[i].endtimeStr+"</td>"+
						"<td >"+data[i].createtimeStr+"</td>"
								+"<td>"
									+"<a data-toggle='modal' data-target='#publish'><button onclick=\"retPlan('"+data[i].id+"')\" class='btn btnblue'>发布</button></a>"
									+"<a data-toggle='modal' data-target='#deleModal'><button class='btn btnyello' onclick=\"delPlan('"+data[i].id+"')\">删除</button></a>"
								+"</td>"	
							+"</tr>";	
				}
				//数据渲染
				$("#plan_tbody").append(hml);
				
				//是否显示无数据
				if( data.length==0 && pageNo ==1){
					$(".pageNone").show();
				}else{
					$(".pageNone").hide();
				}
				//是否显示更多
				if( data.length ==10 ){
					$(".pageMore").attr("pageNo",+pageNo+1);
					$(".pageMore").show();
				}else{
					$(".pageMore").hide();
				}
			}
		}
	});
}

//查询按钮点击
$(".searchBtn").click(function(){
	planSearch(1);
});



function delPlan(id){
	document.getElementById("deleteid").value = id;
}

function delPlanById(){
	var id = $("#deleteid").val();
	$.ajax({
		url : CONTEXTPATH + "/trwuliu/Member/cargoPlan/updatePlanEntity",
		data : {id:id,status:"-1",istemplate:"0"},
		type : "post",
		success : function(result){
			if(result.code != "000000"){
				alert("数据加载失败");
			}else{
				alert("删除成功");
			}
		}
	});
}
function retPlan(id){
	$("#planId").val(id);
}

function releasePlan(){
	var id = $("#planId").val();
	window.location.href = PATH +"/trwuliu/Member/cargo/saveModel?id="+id;
}
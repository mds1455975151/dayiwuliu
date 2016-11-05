/**
 * 我发布的计划页面初始化
 */
$("#planowner").addClass("selected");

var PlanUrl={
		page:	"/trwuliu/planowner/page",
		cancle:	"/trwuliu/planowner/cancle",
		del:	"/trwuliu/planowner/plandel",
		update:	"/trwuliu/planowner/editView",
		detail:	"/trwuliu/planowner/detail",
		billCreate:"/trwuliu/billvender/addView",
		progress:"/trwuliu/planowner/progress"
};

function pageCallback(pageNo) {
	displayData(pageNo);  
} 

function displayData(pageNo){
	if(pageNo && pageNo >= 0){
		searchPlan(pageNo+1);
	}else{
		searchPlan(1);
	}
}

//查询按钮点击
$(".searchBtn").click(function(){
	searchPlan(1);
});
//生成运单绑定事件
$("#planlist").on("click",".createBtn",function(){
	var dId= $(this).attr("dataId");
	window.location.href=PlanUrl.billCreate+"?planId="+dId;
});
//收回计划绑定事件
$("#planlist").on("click",".cancleBtn",function(){
	var dId= $(this).attr("dataId");
	confirm("收回确认","确认收回计划["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
		ajaxMethod(dId,PlanUrl.cancle);
	})
});
//修改计划绑定事件
$("#planlist").on("click",".editBtn",function(){
	var dId= $(this).attr("dataId");
	window.location.href=PlanUrl.update+"?id="+dId;
});
//查看计划绑定事件
$("#planlist").on("click",".detailBtn",function(){
	var dId= $(this).attr("dataId");
	window.location.href=PlanUrl.detail+"?id="+dId;
});
//删除计划绑定事件
$("#planlist").on("click",".deleteBtn",function(){
	var dId= $(this).attr("dataId");
	confirm("删除确认","确认收回计划["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
		ajaxMethod(dId,PlanUrl.del);
	})
});
//查看计划进度详情事件
$("#planlist").on("click",".completeBtn",function(){
	var dId= $(this).attr("dataId");
	window.location.href=PlanUrl.progress+"?id="+dId;
});
//运单收回
function ajaxMethod(id,postUrl){
	$.ajax({
		url : postUrl,
		data : {"id":id},
		type : "post",
		success : function(rs){
			if(rs.code=="000000"){
				alert("操作成功")
				searchPlan($('pageNo').val());
			}else{
				alert(rs.error);
			}
		}
	});
};

//请求数据
function searchPlan(pageNo){
	$.ajax({
		url:PlanUrl.page,
		data:{"pageNo":pageNo,"searchParam":$("#search_v").val()},
		type:"post",
		success: function(rs) {
			if(rs.code!="000000"){
				alert("初始化失败");
			}else{
				var data = rs.data.list || [];
				var total = rs.data.total;
				var pageNo = rs.data.pageNo;
				var pageSize = rs.data.pageSize;
				renderHtml(data);
				//是否显示无数据
				if( data.length==0 && pageNo ==1){
					var hml = "";
					hml+= '<div class="nodata">';
					hml+= '<img src="'+trImgRoot+'/none_pro.png">';
					hml+= '<h3>未发现发布的货运计划！</h3>';
					hml+= '</div>';
					document.getElementById("dateContent").innerHTML = hml;
				}
				
				$("#totalPages").html(parseInt((total+pageSize-1)/pageSize));  
				$('#totalRecords').html(total);
				$('#pageNo').val(pageNo);
				$("#pagination").pagination(total, {
				    callback: pageCallback,
				    prev_text: '上一页',
				    next_text: '下一页',
				    items_per_page:pageSize,
				    num_display_entries:4,
				    current_page:pageNo-1,
				    num_edge_entries:1,
				    maxentries:total,
				    link_to:"javascript:void(0)"
				});
			}
		}
	});
};

//渲染页面
function renderHtml(data){
	var hml="";
	for (var i = 0; i < data.length; i++) {
		var hm = "";  //操作
		var sta = ""; //状态
		var isfamily="";
		var item =data[i];
		var dataType =item.isfamily;
		//熟车运单
		if( dataType ==1 ){
			if( item.status=="2" ){
				sta="执行中";
				hm="<button class='btn btnblue createBtn' dataId='"+item.id+"'  >生成运单</button>";
			}else{
				sta="已完成";
			}
			isfamily="[熟]";
		//正常运单	
		}else{
			if(item.status=="0"){
				sta="待接单";
				hm = "<button class='btn btnyello cancleBtn' dataId='"+item.id+"' dataCode='"+item.plancode+"' >收回</button>"
			}else if(item.status=="1"){
				sta="被拒绝";
				hm = "<button class='btn btnblue editBtn' dataId='"+item.id+"' dataCode='"+item.plancode+"'>修改</button>"+
				"<button  class='btn btnyello deleteBtn' dataId='"+item.id+"' dataCode='"+item.plancode+"'>删除</button>"
			}else if(item.status=="2"){
				sta="执行中";
				hm = "<button class='btn btnblue completeBtn' dataId='"+item.id+"' dataCode='"+item.plancode+"'>进度信息</button>";
			}else if(item.status=="3"){
				sta="已完成";
			}else if(item.status=="-1"){
				sta="已收回";
				hm = "<button class='btn btnblue editBtn' dataId='"+item.id+"' dataCode='"+item.plancode+"'>修改</button>"+
				"<button  class='btn btnyello deleteBtn' dataId='"+item.id+"' dataCode='"+item.plancode+"'>删除</button>"
			}
		}
		
		hml +="<tr>"+
				"<td ><a class='detailBtn' dataId='"+item.id+"'  target='_blank'>"+item.plancode+isfamily+"</a></td>"+
				"<td title='"+item.startcity+"—>"+item.endcity+"'>"+item.startcity+"—>"+item.endcity+"</td>"+
				"<td >"+item.cargoname+" </td>"+
				"<td >"+item.vehicleownername+"</td>"+
				"<td >"+item.modifytimeStr+"</td>"+
				"<td >"+sta+"</td>"+
				"<td class='planbtn'>"+hm+"</td>" +
			 "</tr>";
		//数据渲染
	}
	$("#planlist").empty().append(hml);
};

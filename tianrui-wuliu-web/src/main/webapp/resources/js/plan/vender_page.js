/**
 * 我发布的计划页面初始化
 */
$(function(){
	$("#planvender").addClass("selected");
	
	var PlanUrl={
			page:	"/trwuliu/planvender/page",
			refuse:	"/trwuliu/planvender/refuse",
			del:	"/trwuliu/planvender/delete",
			accept:	"/trwuliu/planvender/accept",
			detail:	"/trwuliu/planvender/detail",
			billCreate:"/trwuliu/billvender/addView",
			billAppoint:"/trwuliu/planAppoint/initAppointPage"
	};

	//查询按钮点击
	$(".searchBtn").click(function(){
		searchPlan(1);
	}).trigger("click");
	
	//更多绑定事件
	$(".pageMore").click(function(){
		searchPlan($(this).attr("pageNo"));
	});
	//拒绝计划绑定事件
	$("#planlist").on("click",".refuseBtn",function(){
		var dId= $(this).attr("dataId");
		$("#hidbid").val(dId);
		$("#refuseModal").modal();
//		var dId= $(this).attr("dataId");
//		confirm("拒绝确认","确认拒绝计划["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
//			ajaxMethod(dId,PlanUrl.refuse);
//		})
	});
	
	//拒绝运单 弹出层提交按钮绑定事件
	$("#refuseModal").off("click",".refusesubmitbtn").on("click",".refusesubmitbtn",function(){
		if(!$(".refuseCheckbox:checked").length==1){
			alert("请输入拒绝类型.");
			return ;	
		}
		if($(".refuseCheckbox:checked").val()=="其他" && !$(".refuseContent").val()){
			alert("请输入拒绝原因.");
			return ;	
		}
		$.ajax({
			url:PlanUrl.refuse,
			data:{"id":$("#hidbid").val(),"resonType":$(".refuseCheckbox:checked").val(),"reson":$(".refuseContent").val()},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					$("#refuseModal").modal("hide")
					alert("操作成功");
					searchPlan(1);
				}else{
					alert(rs.error);
				}
			}
		})
	})
	
		//监听拒绝模态框关闭事件
	$('#refuseModal').on('hidden.bs.modal', function (e) {
		$("#hidbid").val("");
		$("#refuseModal .refuseContent").val("");
		$("#refuseModal").find("[name=refuseCheckbox]:checked").attr("checked",false);
	});
	
	//查看计划绑定事件
	$("#planlist").on("click",".detailBtn",function(){
		var dId= $(this).attr("dataId");
		window.location.href=PlanUrl.detail+"?id="+dId;
	});
	//生成运单
	$("#planlist").on("click",".createPlanBtn",function(){
		var dId= $(this).attr("dataId");
		window.location.href=PlanUrl.billCreate+"?planId="+dId;
	});
	//委派计划
	$("#planlist").on("click",".appointPlanBtn",function(){
		var dId= $(this).attr("dataId");
		window.location.href=PlanUrl.billAppoint+"?id="+dId;
	});
	//删除计划绑定事件
	$("#planlist").on("click",".deleteBtn",function(){
		var dId= $(this).attr("dataId");
		confirm("删除确认","确认收回计划["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
			ajaxMethod(dId,PlanUrl.del);
		})
	});
	//接受计划
	$("#planlist").on("click",".acceptBtn",function(){
		var dId= $(this).attr("dataId");
		confirm("接受确认","确认接受计划["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
			ajaxMethod(dId,PlanUrl.accept);
		})
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
					searchPlan(1);
				}else{
					alert(rs.error);
				}
			}
		});
	};

	//请求数据
	function searchPlan(pageNo){
		if( pageNo ==1 ){
			$("#planlist").empty();
		}
		
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
					renderHtml(data);
					//是否显示无数据
					if( data.length==0 && pageNo ==1){
						var hml = "";
						hml+= '<div class="nodata">';
						hml+= '<img src="'+trImgRoot+'/none_pro.png">';
						hml+= '<h3>未发现承接的货运计划！</h3>';
						hml+= '</div>';
						$('#emptyCont').html(hml).show();
						$('#dateContent').hide();
					}else{
						$(".pageNone").hide();
						$('#dateContent').show();
						$('#emptyCont').hide();
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
				}else{
					sta="已完成";
				}
				isfamily="[熟]";
			//正常运单	
			}else{
				if(item.status=="0"){
					sta="待接单";
					hm = "<button class='btn btnblue acceptBtn' dataId='"+item.id+"' dataCode='"+item.plancode+"' >接受</button>"+
					 "<button class='btn btnyello refuseBtn ml5' dataId='"+item.id+"' dataCode='"+item.plancode+"' >拒绝</button>"
				}else if(item.status=="1"){
					sta="已拒绝";
					hm ="<button  class='btn btnyello deleteBtn' dataId='"+item.id+"' dataCode='"+item.plancode+"'>删除</button>"
				}else if(item.status=="2"){
					sta="执行中";
					hm = "<button class='btn btnblue createPlanBtn' dataId='"+item.id+"' dataCode='"+item.plancode+"'>直接派车</button>"+
						 "<button class='btn btnblue appointPlanBtn ml5' dataId='"+item.id+"' dataCode='"+item.plancode+"'>委派车主</button>";
				}else if(item.status=="3"){
					sta="已完成";
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
		$("#planlist").append(hml);
	};
});




/**
 * 我的承运计划 查询功能
 */
$(function(){
	$("#planvender").addClass("selected");
	findPlan(1);
	
	//更多绑定事件
	$(".pageMore").click(function(){
		findPlan($(this).attr("pageNo"));
	});
});

function findPlan(pageNo){
	
	if( pageNo ==1 ){
		$("#planlist").empty();
	}
	
	$.ajax({
		url:CONTEXTPATH+'/trwuliu/Member/cargoPlan/findPlan',
		data:{vehicleownerid:memberId,desc3:"-1","pageNo":pageNo,status:"-1","key":$("#search_v").val()},
		type:"post",
		success: function(rs) {
			if(rs.code!="000000"){
				alert("初始化失败");
			}else{
				var data = rs.data.list || [];
				var total = rs.data.total;
				var hml = "";
				var hm = "";
				for (var i = 0; i < data.length; i++) {
					var sta = "";
					if(data[i].status=="0"){
						sta="待接单";
						hm="<button class='btn btnblue' onclick=\"receivePlan('"+data[i].id+"')\">接收</button>"+
						"<a data-toggle='modal' data-target='#refuseplan'><button onclick=\"refuse('"+data[i].id+"')\" class='btn btnyello'>拒绝</button></a>";
					}else if(data[i].status=="1"){
						sta="已拒绝";
						hm="<a data-toggle='modal' data-target='#deleteplan'><button onclick=\"deleteId('"+data[i].id+"')\" class='btn btnyello'>删除</button></a>";
					}else if(data[i].status=="2"){
						sta="执行中";
						hm="<button class='btn btnblue' onclick=\"newBill('"+data[i].id+"')\">生成运单</button>"
					}else if(data[i].status=="3"){
						sta="已完成";
						hm="";
					}
					hml +="<tr>"+
					"<td ><a href='/trwuliu/Member/cargoPlan/planchengyun?id="+data[i].id+"' target='_blank'>"+data[i].desc2+"</a></td>"+
					"<td >"+data[i].linkman+" </td>"+
					"<td >"+data[i].telephone+" </td>"+
					"<td title='"+data[i].startcity+"—>"+data[i].endcity+"'>"+data[i].startcity+"—>"+data[i].endcity+"</td>"+
//					"<td >"+data[i].starttimeStr+"-"+data[i].endtimeStr+"</td>"+
					"<td >"+data[i].createtimeStr+"</td>"+
					"<td >"+sta+"</td>"+
					"<td class='planbtn'>"+
					hm+
					"</td></tr>";
			}
			
			//数据渲染
			$("#planlist").append(hml);
			
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
	findPlan(1);
});


/**
 * 接收计划
 * @param id
 */
function receivePlan(id){
	var status = "2";
	var desc3 = "2";
	$.ajax({
		url : CONTEXTPATH+'/trwuliu/Member/cargoPlan/updatePlanEntity',
		data : {id:id,status:status,desc3:desc3},
		type : "post",
		success : function(rs){
			if(rs.code!="000000"){
				alert("接收计划失败");
			}else{
				window.location.reload()
			}
		}
	});
}
/**
 * 为当前拒绝的计划ID赋值
 * @param id
 */
function refuse(id){
	document.getElementById("refusePlanId").value = id;
}
/**
 * 拒绝货运计划
 */
function refusePlan(){
	var status = "1";
	var desc3 = "1";
	var id = $("#refusePlanId").val();
	$.ajax({
		url : CONTEXTPATH+'/trwuliu/Member/cargoPlan/updatePlanEntity',
		data : {id:id,status:status,desc3:desc3},
		type : "post",
		success : function(rs){
			if(rs.code!="000000"){
				alert("拒绝计划失败");
			}else{
				window.location.reload();
			}
		}
	});
}
/**
 * 为删除计划的ID赋值
 * @param id
 */
function deleteId(id){
	document.getElementById("deletePlanId").value = id;
}
/**
 * 删除货运计划
 */
function deletePlan(){
	var desc3 = "-1";
	var id = $("#deletePlanId").val();
	$.ajax({
		url : CONTEXTPATH+'/trwuliu/Member/cargoPlan/updatePlanEntity',
		data : {id:id,status:status,desc3:desc3},
		type : "post",
		success : function(rs){
			if(rs.code!="000000"){
				alert("删除计划失败");
			}else{
				window.location.reload()
			}
		}
	});
}
function newBill(id){
	window.location.href = CONTEXTPATH +"/trwuliu/billvender/addView?planId="+id;
}
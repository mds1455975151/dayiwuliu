/**
 * 我发布的计划页面初始化
 */
$(function(){
	$("#planowner").addClass("selected");
	searchPlan(1);
	
	
	//更多绑定事件
	$(".pageMore").click(function(){
		searchPlan($(this).attr("pageNo"));
	});
});
function searchPlan(pageNo){
	//是否把以前的数据删除
	if( pageNo ==1 ){
		$("#planlist").empty();
	}
	
	$.ajax({
		url:CONTEXTPATH+'/trwuliu/Member/cargoPlan/findPlan',
		data:{"creator":memberId,status:"-1","pageNo":pageNo,"key":$("#search_v").val()},
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
						if(data[i].desc1 == "1"){
							sta="执行中";
							hm="<button class='btn btnblue' onclick=\"newBill('"+data[i].id+"')\">生成运单</button>"
						}else{
							sta="待接单";
							hm = "<a data-toggle='modal' data-target='#callback'><button onclick=\"callbackId('"+data[i].id+"')\" class='btn btnyello'>收回</button></a>"
						}
					}else if(data[i].status=="1"){
						sta="被拒绝";
						hm = "<button class='btn btnblue' onclick=\"updatePlan('"+data[i].id+"')\">修改</button>"+
						"<a data-toggle='modal' data-target='#closeplan'><button onclick=\"closePlan('"+data[i].id+"')\" class='btn btnyello'>删除</button></a>"
					}else if(data[i].status=="2"){
						sta="执行中";
						hm = "";
					}else if(data[i].status=="3"){
						sta="已完成";
						hm = "";
					}else if(data[i].status=="4"){
						sta="待发布"
						hm = "<button class='btn btnblue' onclick=\"releasePlan('"+data[i].id+"')\">发布</button>"+
						"<a data-toggle='modal' data-target='#closeplan'><button onclick=\"closePlan('"+data[i].id+"')\" class='btn btnyello'>删除</button></a>"
					}
					hml +="<tr>"+
							"<td ><a href='/trwuliu/Member/cargoPlan/planFabu?id="+data[i].id+"' target='_blank'>"+data[i].desc2+"</a></td>"+
							"<td title='"+data[i].startcity+"—>"+data[i].endcity+"'>"+data[i].startcity+"—>"+data[i].endcity+"</td>"+
							"<td >"+data[i].cargoname+" </td>"+
							"<td >"+data[i].starttimeStr+"—"+data[i].endtimeStr+"</td>"+
							"<td >"+data[i].createtimeStr+"</td>"+
							"<td >"+sta+"</td>"+
//							"<td >"+data[i].type+"</td>"+
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
	searchPlan(1);
});

function addPageNo(){
	searchPlan(pageNo);
}

/**
 * 订单关闭
 * @param id
 */
function closePlan(id){
	document.getElementById("deleteid").value = id;
}
/**
 * 删除订单
 */
function deletePlan(){
	var id = $("#deleteid").val();
	$.ajax({
		url:CONTEXTPATH+'/trwuliu/Member/cargoPlan/delectFreightById',
		data:{
			"id":id
				},
		type:"post",
		success: function(rs) {
			if(rs.code=="000000"){
				document.getElementById("deletetext").innerHTML = "删除成功";
				window.location.reload();
			}
		}
	});
}
/**
 * 修改跳转
 * @param id
 */
function updatePlan(id){
	window.location.href=CONTEXTPATH+'/trwuliu/Member/cargo/planUpdate?id='+id; 
}
/**
 * 跳转到发布页面
 * @param id
 */
function releasePlan(id){
	window.location.href = PATH +"/trwuliu/Member/cargo/planUpdate?id="+id;
}
/**
 * 为当前收回的计划的ID赋值
 * @param id
 */
function callbackId(id){
	document.getElementById("callbackId").value = id;
}
/**
 * 收回当前计划
 */
function callbackPlan(){
	var id = $("#callbackId").val();
	$.ajax({
		url : CONTEXTPATH+'/trwuliu/Member/cargoPlan/updatePlanEntity',
		data : {id:id,status:"4",desc3:"-1"},
		type : "post",
		success : function(rs){
			if(rs.code!="000000"){
				alert("收回计划失败");
			}else{
				window.location.reload()
			}
		}
	});
}
function newBill(id){
	window.location.href = CONTEXTPATH +"/trwuliu/billvender/addView?planId="+id;
}
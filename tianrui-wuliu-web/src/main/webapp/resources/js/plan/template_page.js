/**
 * 我发布的计划页面初始化
 */
$(function(){
	$("#plantemplate").addClass("selected");
	
	var PlanUrl={
			page:	"/trwuliu/plantemplate/page",
			detail:	"/trwuliu/plantemplate/detail",
			publish:"/trwuliu/plantemplate/publishView",
			del:	"/trwuliu/plantemplate/del"
	};

	//查询按钮点击
	searchPlan(1);

	//查看计划绑定事件
	$("#planlist").on("click",".detailBtn",function(){
		var dId= $(this).attr("dataId"); 
		window.location.href=PlanUrl.detail+"?id="+dId;
	});
	//删除计划绑定事件
	$("#planlist").on("click",".deleteBtn",function(){
		var dId= $(this).attr("dataId");
		confirm("删除确认","确认删除计划模版["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
			ajaxMethod(dId,PlanUrl.del);
		})
	});
	//发布
	$("#planlist").on("click",".publishBtn",function(){
		var dId= $(this).attr("dataId");
		var freightid= $(this).attr("freight");
		var dataCode = $(this).attr("dataCode");
		$.ajax({
			url : "/trwuliu/planowner/queryFreightById",
			data : {"id":freightid},
			type : "post",
			success : function(rs){
				if(rs.code != "000000"){
					var error = rs.error;
					confirm(error,error+",是否删除此计划模版,确认/取消?",function(){
						ajaxMethod(dId,PlanUrl.del);
					});
				}else{
					confirm("发布确认","确认发布计划模版["+dataCode+"]吗,确认/取消?",function(){
						window.location.href=PlanUrl.publish+"?id="+dId;
					});
				}
			}
		});
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
					window.location.reload();
					//searchPlan(1);
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
			type:"post",
			success: function(rs) {
				if(rs.code!="000000"){
					alert("初始化失败");
				}else{
					var data = rs.data || [];
					renderHtml(data);
					//是否显示无数据
					if( data.length==0 && pageNo ==1){
						var hml = "";
						hml+= '<div class="nodata">';
						hml+= '<img src="'+trImgRoot+'/none_pro.png">';
						hml+= '<h3>未发现计划模板！添加计划时勾选是否为模板可以保存计划模板哦！</h3>';
						hml+= '</div>';
						document.getElementById("dateContent").innerHTML = hml;
					}else{
						$(".pageNone").hide();
					}
					$(".pageMore").hide();
					
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
				isfamily="[熟]";
			//正常运单	
			}
			hm = "<button class='btn  btnblue publishBtn' freight='"+item.freightid+"' dataId='"+item.id+"' dataCode='"+item.plancode+"' >发布</button>"+
			 "<button class='btn btnyello deleteBtn' dataId='"+item.id+"' dataCode='"+item.plancode+"' >删除</button>"
			hml +="<tr>"+
					"<td ><a class='detailBtn' dataId='"+item.id+"'  target='_blank'>"+item.plancode+isfamily+"</a></td>"+
					"<td title='"+item.startcity+"—>"+item.endcity+"'>"+item.startcity+"—>"+item.endcity+"</td>"+
					"<td >"+item.cargoname+" </td>"+
					"<td >"+item.vehicleownername+"</td>"+
					"<td >"+item.modifytimeStr+"</td>"+
					"<td class='planbtn'>"+hm+"</td>" +
				 "</tr>";
			//数据渲染
		}
		$("#planlist").append(hml);
	};
});




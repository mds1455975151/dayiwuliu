;(function($,win){
	var PlanUrl={
			cancle:	"/trwuliu/planAppoint/cancle",
			del:	"/trwuliu/planAppoint/plandel",
			detail:"/trwuliu/planvender/detail",
			update:"/trwuliu/planAppoint/editView"
	};
	
	$('.pageMore').off('click').on('click',function(){
		initAppointPlanList(false);
	});
	initAppointPlanList = function(flag,dom){
		if(flag) {
			$('#planlist').empty();
			$('.pageMore').attr('pageNo','1');
		}
		var search_v = $('#search_v').val();
		var pageNo = $('.pageMore').attr('pageNo');
		if(pageNo <= 0){
			pageNo = 1;
		}
		$.ajax({
			url:'/trwuliu/planAppoint/page',
			data:{
				pageNo:pageNo,
				searchParam:$.trim(search_v),
				isAppoint:"1"
			},
			dataType:'json',
			type:'POST',
			cache:false,
			async:true,
			success:function(res){
				if(res.code!="000000"){
					alert("初始化失败");
				}else{
					var data = res.data.list || [];
					var total = res.data.total;
					renderHtml(data);
					//是否显示无数据
					if( data.length == 0 && pageNo == 1){
						var hml = "";
						hml+= '<div class="nodata">';
						hml+= '<img src="'+trImgRoot+'/none_pro.png">';
						hml+= '<h3>未发现委派的货运计划！</h3>';
						hml+= '</div>';
						$('#emptyCont').html(hml).show();
						$('#dateContent').hide();
					}else{
						$(".pageNone").hide();
						$('#dateContent').show();
						$('#emptyCont').hide();
					}
					//是否显示更多
					if( data.length == 10 ){
						$(".pageMore").attr("pageNo",+pageNo+1);
						$(".pageMore").show().on('click',function(){
							initAppointPlanList(false);
						});
					}else{
						$(".pageMore").hide().off('click');
					}
				}
				//解除按钮冻结
				if(dom){
					dom.disabled = false;
				}
			}
		});
	}
	
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
					//hm="<button class='btn btnblue createBtn' dataId='"+item.id+"'  >生成运单</button>";
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
		$("#planlist").append(hml);
	}
	
	//收回计划绑定事件
	$("#planlist").on("click",".cancleBtn",function(){
		var dId= $(this).attr("dataId");
		var _this = this;
		confirm("收回确认","确认收回计划["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
			_this.disabled = true;
			ajaxMethod(dId,PlanUrl.cancle,_this);
		})
	});
	//运单收回
	function ajaxMethod(id,postUrl,dom){
		$.ajax({
			url : postUrl,
			data : {"id":id},
			type : "post",
			success : function(rs){
				if(rs.code=="000000"){
					alert("操作成功")
					initAppointPlanList(true);
				}else{
					alert(rs.error);
				}
				//解除按钮冻结
				if(dom){
					dom.disabled = false;
				}
			}
		});
	};

	
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
		var _this = this;
		confirm("删除确认","确认收回计划["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
			_this.disabled = true;
			ajaxMethod(dId,PlanUrl.del,_this);
		})
	});
	//搜索
	$('.searchBtn').off('click').on('click',function(){
		initAppointPlanList(true, null);
	});
	
})(jQuery,window);
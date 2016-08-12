$(function(){
	$("#messagePage").addClass("selected");
	
	queryPage(1);
	function queryPage(pageNo){
		$.ajax({
			url : CONTEXTPATH+'/trwuliu/Member/message/findMessage',
			data : {recid:memberId,pageNo:pageNo,pageSize:10},
			type : "post",
			success: function(rs) {
				if(rs.code!=000000){
					alert("初始化失败");
				}else{
					var data = rs.data.list ||[];
					var hm = "";
					
					if(data.length && data.length > 0){
						for(var i=0;i<data.length;i++){
							var aa = "";
							var item =data[i];
							//查看按钮
							if(item.uri){
								aa = "<button class='btn btnyello detailBtn'  dataId='"+data[i].id+"'   dataUrl='"+data[i].uri+"' >查看</button>";
							}
							//特殊处理
							if( item.code=="161" ||  item.code=="162" || item.code=="221" ){
								if( item.isreply =="0" ){
									aa = "<button class='btn btnblue refuseBtn' dataId='"+data[i].id+"' >拒绝</button><button class='btn btnyello applyBtn'  dataId='"+data[i].id+"' >同意</button>";
								}else if(data[i].isreply == "1"){
									aa = "已同意"
								}else if(data[i].isreply == "2"){
									aa = "已拒绝"
								}else if(data[i].isreply == "3"){
									aa = "已收回";
								}
							}
							
							//未读
							if(data[i].status == "1"){
								hm +="<div class='messline border_gray message_not' dataId='"+item.id+"'>"+
								"<div class= 'mess_left'>"+
								"<div class='messimg1'></div>"+
								"<label>"+data[i].sendtimeStr+"</label>"+
								"<span>"+data[i].content+"</span>"+
								"</div>"+
								"<div class='mess_right'>"+
								aa+
								"</div>"+
								"</div>";   
							//已读	
							}else if(data[i].status == "2"){
								hm +="<div class='messline border_gray message_yes'>"+
								"<div class= 'mess_left'>"+
								"<div class='messimg2'></div>"+
								"<label>"+data[i].sendtimeStr+"</label>"+
								"<span>"+data[i].content+"</span>"+
								"</div>"+
								"<div class='mess_right'>"+
								"<div>"+aa+"</div>"+
								"</div>"+
								"</div>"; 
							}
						}
						if( pageNo==1 ){
							$("#messagelist").html(hm);
						}else{
							$("#messagelist").append(hm);
						}
					}else{
						hm+= '<div class="nodata">';
						hm+= '<img src="'+trImgRoot+'/none_m.png">';
						hm+= '<h3>欢迎您登陆天瑞物流系统，暂无消息</h3>';
						hm+= '</div>';
						$("#messagelist").html(hm);
					}
					
					if(data.length && data.length==10){
						$(".pageMore").show();
						$(".pageMore").attr("pageNo",+pageNo+1);
					}else{
						$(".pageMore").hide();
					}
				}
			}
		});
	}
	
	
	//更多绑定事件
	$(".pageMore").click(function(){
		queryPage($(this).attr("pageNo"));
	});
	//已读
	$("#messagelist").on("click",".message_not",function(event){
		var $that =$(this);
		event.stopPropagation(); 
		$.ajax({
			url : CONTEXTPATH + "/trwuliu/Member/message/updateMessage",
			data : {id:$that.attr("dataId")},
			type : "post",
			success : function(rs){
				if(rs.code!="000000"){
					alert("操纵失败");
				}else{
//					$that.find(".messimg1").addClass("messimg2").removeClass("messimg1");
//					$that.addClass("message_yes").removeClass("message_not");
					window.location.reload();
				}
			}
		});
	});
	//查看
	$("#messagelist").on("click",".detailBtn",function(event){
		var $that =$(this);
		event.stopPropagation(); 
		$.ajax({
			url : CONTEXTPATH + "/trwuliu/Member/message/updateMessage",
			data : {id:$that.attr("dataId")},
			type : "post",
			success : function(rs){
				if(rs.code!="000000"){
					alert("数据加载失败");
				}else{
					window.location.href=$that.attr("dataUrl");
				}
			}
		});
	});
	//同意
	$("#messagelist").on("click",".applyBtn",function(event){
		var $that =$(this);
		event.stopPropagation(); 
		var isreply = "1";
		var status = "2";
		$.ajax({
			url : CONTEXTPATH + "/trwuliu/Member/message/updateMessage",
			data : {id:$that.attr("dataId"),status:status,isreply:isreply},
			type : "post",
			success : function(rs){
				if(rs.code!="000000"){
					alert("数据加载失败");
				}else{
					 window.location.reload();
				}
			}
		});
	});
	//拒绝
	$("#messagelist").on("click",".refuseBtn",function(event){
		var $that =$(this);
		event.stopPropagation(); 
		var isreply = "2";
		var status = "2";
		$.ajax({
			url : CONTEXTPATH + "/trwuliu/Member/message/updateMessage",
			data : {id:$that.attr("dataId"),status:status,isreply:isreply},
			type : "post",
			success : function(rs){
				if(rs.code!="000000"){
					alert("数据加载失败");
				}else{
					 window.location.reload();
				}
			}
		});
	});
});





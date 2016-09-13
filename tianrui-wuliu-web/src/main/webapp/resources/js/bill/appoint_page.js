$(function(){
	$("#billAppoint").addClass("selected");
	var URL={
		pageUrl:"/trwuliu/billAppoint/page",
		cancleUrl:"/trwuliu/billvender/cancle",
		deleteUrl:"/trwuliu/billvender/delete",
		updateViewUrl:"/trwuliu/billvender/updateView",
		detailViewUrl:"/trwuliu/billvender/detail"
	}
	
	//分页数据查询
	var pageData=function(pageNo){
		if(pageNo ==1){
			$(".bill_table").find("tbody").empty();
		}
    	$.ajax({
			url:URL.pageUrl,
			data:{"key":$(".searchKey").val(),"pageNo":pageNo},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					renderDate(rs.data.list || [],pageNo);
					$(".pageMore").off('click').on('click',moreData);
				}else{
					alert(rs.error);
				}
			}
		})
	}
	
	//页面数据渲染
	var renderDate=function(bills,pageNo){
		var currId = $('#currId').val();
		var dataArr=[]; 
		$(bills).each(function(i,item){
			dataArr.push('<tr>');
			dataArr.push('<td>'+item.waybillno+'</td>');
			dataArr.push('<td><p>'+item.cargoname+'</p></td>');
			dataArr.push('<td><p><i class="iconfont icon-dizhi billc1"></i>'+item.startcity+'</p>');
			dataArr.push('<p><i class="iconfont icon-dizhi billc2"></i>'+item.endcity+'</p></td>');
			dataArr.push('<td>');
			if(item.type == 2){
				dataArr.push('剩余'+item.overnumber+'趟');
			}else{
				if(item.status ==0){
					dataArr.push('新建');
				}else if(item.status ==7 ){
					dataArr.push('已拒绝');
				}else if(item.status ==1 ){
					dataArr.push('已接受');
				}else if(item.status ==2 ){
					dataArr.push('已提货');
				}else if(item.status ==3 ){
					dataArr.push('运输中');
				}else if(item.status ==4 ){
					dataArr.push('已到达');
				}else if(item.status ==5 ){
					dataArr.push('已卸货');
				}else if(item.status ==6 ){
					dataArr.push('已完成');
				}else if(item.status ==-1 ){
					dataArr.push('已收回');
				}
			}
			dataArr.push('</td>');
			dataArr.push('<td>'+item.weight+'</td>');
			dataArr.push('<td>'+item.venderName+'</td>');
			/*dataArr.push('<td>');
			if(item.creator == currId){
				if(item.status ==0){
					dataArr.push('<a ><button class="btn btnyello cancleBtn" dataId="'+item.id+'" dataCode="'+item.waybillno+'"  >收回</button></a>');
				}else if(item.status ==7){
					dataArr.push('<a  target="_blank"  href="'+URL.updateViewUrl+'?id='+item.id+'"><button class="btn btnyello">修改</button></a>');
					dataArr.push('<a ><button class="btn btnyello delBtn"  dataId="'+item.id+'" dataCode="'+item.waybillno+'" >删除</button></a>');
				}else if(item.status ==-1){
					dataArr.push('<a  target="_blank"  href="'+URL.updateViewUrl+'?id='+item.id+'"><button class="btn btnyello">修改</button></a>');
					dataArr.push('<a ><button class="btn btnyello delBtn"  dataId="'+item.id+'" dataCode="'+item.waybillno+'" >删除</button></a>');
				}
			}
			
			dataArr.push('</td>');*/
			dataArr.push('</tr>');
		});	
		
		//是否显示无数据
		if( dataArr.length==0 && pageNo ==1){
			var hml = "";
			hml+= '<div class="nodata">';
			hml+= '<img src="'+trImgRoot+'/none_bill.png">';
			hml+= '<h3>未发现货物运单！</h3>';
			hml+= '</div>';
			document.getElementById("dateContent").innerHTML = hml;
		}else{
			$(".pageNone").hide();
		}
		//是否显示更多
		if( bills.length ==10 ){
			$(".pageMore").attr("pageNo",+pageNo+1);
			$(".pageMore").show();
		}else{
			$(".pageMore").hide();
		}
		
		$(".bill_table").find("tbody").append(dataArr.join(" "));
	}

	//更多绑定事件
	$(".pageMore").off('click').on('click',moreData);
	var moreData = function(){
		$(".pageMore").off('click');
		pageData($(this).attr("pageNo"));
	}
	//查询按钮点击
	$(".searchBtn").click(function(){
		pageData(1);
	}).trigger("click");
	
	//取消按钮点击
	$(".table").on("click",".cancleBtn",function(){
		var dId= $(this).attr("dataId");
		confirm("取消","确认取消运单["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
			$.ajax({
				url:URL.cancleUrl,
				data:{"id":dId},
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						alert("操作成功.")
						$(".searchBtn").trigger("click");
					}else{
						alert(rs.error);
					}
				}
			})
		})
	});
	//删除按钮点击
	$(".table").on("click",".delBtn",function(){
		var dId= $(this).attr("dataId");
		confirm("删除","确认删除运单["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
			$.ajax({
				url:URL.deleteUrl,
				data:{"id":dId},
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						alert("操作成功.")
						$(".searchBtn").trigger("click");
					}else{
						alert(rs.error);
					}
				}
			})
		})
	});
});

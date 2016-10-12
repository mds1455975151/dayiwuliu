$(function(){
	$("#billowner").addClass("selected");
	var URL={
		pageUrl:"/trwuliu/billowner/page",
		signUrl:"/trwuliu/billowner/signConfirm",
		detailViewUrl:"/trwuliu/billowner/detail",
		deleteUrl:"/trwuliu/billowner/delete",
	}
	
	//分页数据查询
	var pageData=function(pageNo){
		if(pageNo ==1){$(".bill_table").find("tbody").empty();}
    	$.ajax({
			url:URL.pageUrl,
			data:{"key":$(".searchKey").val(),"pageNo":pageNo},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					renderDate(rs.data.list || [],pageNo);
				}else{
					alert(rs.error);
				}
			}
		})
	}
	
	//页面数据渲染
	var renderDate=function(bills,pageNo){
		var dataArr=[]; 
		$(bills).each(function(i,item){
			dataArr.push('<tr>');
			dataArr.push('<td><a href="'+URL.detailViewUrl+'?id='+item.id+'">'+item.waybillno+'</a></td>');
			dataArr.push('<td>'+item.cargoname+'</td>');
			dataArr.push('<td><p><i class="iconfont icon-dizhi billc1"></i>'+item.startcity+'</p>');
			dataArr.push('<p><i class="iconfont icon-dizhi billc2"></i>'+item.endcity+'</p></td>');
			
			dataArr.push('<td>');
			if(item.status ==0){
				dataArr.push('新建');
			}else if(item.status ==7 || item.status==8){
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
			dataArr.push('</td>');
			dataArr.push('<td>'+item.modifytimeStr+'</td>');
			dataArr.push('<td>');
			/*dataArr.push('<a target="_blank" href="'+URL.detailViewUrl+'?id='+item.id+'"><button class="btn btnyello">查看</button></a>');*/
			if(item.status ==5){
				dataArr.push('<a ><button class="btn btnyello signBtn" dataId="'+item.id+'"  dataImg="'+item.signimgurl+'" qhdataImg="'+item.pickupimgurl+'" weight="'+item.weight+'" planweight="'+item.planweight+'">签收</button></a>');
			}else if(item.status ==7 ||item.status ==-1){
				dataArr.push('<a ><button class="btn btnyello delBtn"  dataId="'+item.id+'">删除</button></a>');
			}
			
			dataArr.push('</td>');
			dataArr.push('</tr>');
		});	
		//是否显示无数据
		if( bills.length==0 && pageNo ==1){
			var hml = "";
			hml+= '<div class="nodata">';
			hml+= '<img src="'+trImgRoot+'/none_bill.png">';
			hml+= '<h3>未发现您的货物运单！</h3>';
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
	$(".pageMore").click(function(){
		pageData($(this).attr("pageNo"));
	});
	//查询按钮点击
	$(".searchBtn").click(function(){
		pageData(1);
	}).trigger("click");
	
	//签收按钮点击
	var _qhimgurl_defult = $("#bdimgurl").attr("src");
	var _xhimgurl_defult = $("#bdimgurl").attr("src");
	$(".table").on("click",".signBtn",function(){
		var dId= $(this).attr("dataId");
		var weight= $(this).attr("weight");
		var planweight= $(this).attr("planweight");
		$("#hidid").val(dId);
		$("#weight").val(weight);
		$("#planweight").val(planweight);
		if(!$(this).attr("qhdataImg")){
			$("#qhbdImgUrl").hide();
		}else{
			$("#qhbdImgUrl").attr( "src",$(this).attr("qhdataImg"))
		}
		if(!$(this).attr("dataImg")){
			$("#bdimgurl").hide();
		}else{
			$("#bdimgurl").attr( "src",$(this).attr("dataImg"))
		}
		$("#signModal").modal();
	});
	//监听拒绝模态框关闭事件
	$('#signModal').on('hidden.bs.modal', function (e) {
		$("#hidid").val("");
		$("#weight").val("");
		$("#planweight").val("");
		$("#weighttext").val("");
		$("#qhbdImgUrl").attr( "src",_qhimgurl_defult);
		$("#bdimgurl").attr( "src",_xhimgurl_defult);
	});
	
	
	$(".signsubmitbtn").click(function(){
		var weightInput=$("#weighttext").val();
		weightInput = parseFloat(weightInput);
    	if( !weightInput ){
    		alert("签收重量不能为空.");
    		return ;
    	}else if(!(/^\d{1,6}(\.\d{0,2})?$/.test(weightInput)) ){
    		alert("签收重量格式整数最大6位，小数最大2位");
    		return ;
    	}
    	var title = "";
    	if(parseFloat($('#planweight').val()) - weightInput <=0){
    		title = "该运单的运输量为"+$("#weight").val()+"，签收量为"+weightInput+"，计划剩余量为"+$('#planweight').val()+",<br/>确认回使计划自动关闭，是否继续？";
    	}else{
    		title = "该运单的运输量为"+$("#weight").val()+"，签收量为"+weightInput+"，是否确认签收？";
    	}
    	confirm("确认",title,function(){
    		$.ajax({
    			url:URL.signUrl,
    			data:{"id":$("#hidid").val(),"weight":weightInput},
    			type : "post",
    			dataType:"json",
    			success:function(rs){
    				if( rs && rs.code =="000000" ){
    					$(".searchBtn").trigger("click");
    					$("#signModal").modal("hide");
    				}else{
    					alert(rs.error);
    				}
    			}
    		});
    	},function(){
    		$("#signModal").modal('hide');
    		$("#hidid").val("");
    		$("#weight").val("");
    		$("#weighttext").val("");
    		$("#qhbdImgUrl").attr( "src",_qhimgurl_defult);
    		$("#bdimgurl").attr( "src",_xhimgurl_defult);
    	});
    	
	});
	
	//删除按钮点击
	$(".table").on("click",".delBtn",function(){
		var dId= $(this).attr("dataId");
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
	});
});

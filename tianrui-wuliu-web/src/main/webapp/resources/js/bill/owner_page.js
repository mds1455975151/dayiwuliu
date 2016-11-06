$("#billowner").addClass("selected");
var URL={
	pageUrl:"/trwuliu/billowner/page",
	signUrl:"/trwuliu/billowner/signConfirm",
	detailViewUrl:"/trwuliu/billowner/detail",
	deleteUrl:"/trwuliu/billowner/delete",
}

var loading = {
		$loadHtml:$('<tr class="loadtr"><td colspan="6" class="bill_load" style="text-align: center;"><img src="'+trRoot+'/tianrui/images/reloader.gif"><h5>数据载入中，请稍后......</h5></td></tr>'),
		append:function(){
			$('#billlist').empty().append(this.$loadHtml);
		},
		remove:function(){
			this.$loadHtml.remove();
		}
};

function pageCallback(pageNo) {
	displayData(pageNo);  
} 

function displayData(pageNo){
	$('.hasdata').show();
	$('.nodata').hide();
	loading.append();
	if(pageNo && pageNo >= 0){
		pageData(pageNo+1);
	}else{
		pageData(1);
	}
}

//分页数据查询
var pageData=function(pageNo){
	$.ajax({
		url:URL.pageUrl,
		data:{"key":$(".searchKey").val(),"pageNo":pageNo},
		type : "post",
		dataType:"json",
		success:function(rs){
			loading.remove();
			if( rs && rs.code =="000000" ){
				var data = rs.data.list || [];
				var total = rs.data.total;
				var pageNo = rs.data.pageNo;
				var pageSize = rs.data.pageSize;
				renderDate(data || [],pageNo);
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
		dataArr.push('<td><p>'+item.venderName+'</p>');
		dataArr.push('<p>'+item.vehicleno+'</p></td>');
		
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
			dataArr.push('<a ><button class="btn btnyello signBtn" dataId="'+item.id+'"  dataImg="'+item.signimgurl+'" qhdataImg="'+item.pickupimgurl+'" '
					+'weight="'+item.weight+'" planWeight="'+item.planWeight+'" planCompleteWeight="'+item.planCompleteWeight+'" '
					+'pickupweight="'+item.pickupweight+'" signweight="'+item.signweight+'">签收</button></a>');
		}else if(item.status ==7 ||item.status ==-1){
			dataArr.push('<a ><button class="btn btnyello delBtn"  dataId="'+item.id+'">删除</button></a>');
		}
		
		dataArr.push('</td>');
		dataArr.push('</tr>');
	});	
	//是否显示无数据
	if( bills.length==0 && pageNo ==1){
		$('.hasdata').hide();
		$('.nodata').show();
	}
	$("#billlist").empty().append(dataArr.join(" "));
}

//查询按钮点击
$(".searchBtn").click(function(){
	displayData(0);
});

//签收按钮点击
var _qhimgurl_defult = $("#bdimgurl").attr("src");
var _xhimgurl_defult = $("#bdimgurl").attr("src");
$(".table").on("click",".signBtn",function(){
	var dId= $(this).attr("dataId");
	var weight= $(this).attr("weight");
	var planWeight= $(this).attr("planWeight");
	var planCompleteWeight= $(this).attr("planCompleteWeight");
	var pickupweight = $(this).attr('pickupweight');
	if(pickupweight){
		pickupweight = parseFloat(pickupweight).toFixed(2);
	}else{
		pickupweight = '';
	}
	var signweight = $(this).attr('signweight');
	if(signweight){
		signweight = parseFloat(signweight).toFixed(2);
	}else{
		signweight = '';
	}
	$("#hidid").val(dId);
	$("#weight").val(weight);
	$("#planWeight").val(planWeight);
	$("#planCompleteWeight").val(planCompleteWeight);
	$("#pickupweight").val(pickupweight);
	$("#signweight").val(signweight);
	if(!$(this).attr("qhdataImg")){
		$("#qhbdImgUrl").hide();
		$("#qhbdImgUrl").parent('a').removeAttr("href").hide();
		$("#notImg").show();
		$('#stateWeightLabel').html('卸货量：'+signweight+"吨");
	}else{
		$("#qhbdImgUrl").attr( "src",$(this).attr("qhdataImg")).show();
		$("#qhbdImgUrl").parent('a').attr("href",$(this).attr("qhdataImg")).show();
		$("#notImg").hide();
		$('#stateWeightLabel').html('提货量：'+pickupweight+"吨");
	}
	if(!$(this).attr("dataImg")){
		$("#bdimgurl").hide();
		$("#bdimgurl").parent('a').removeAttr("href").hide();
	}else{
		$("#bdimgurl").attr( "src",$(this).attr("dataImg"))
		$("#bdimgurl").parent('a').attr( "href",$(this).attr("dataImg"))
	}
	$('.nav-tabs li:first').addClass('active').siblings('li').removeClass('active');
	$('.tab-content div:first').addClass('in active').siblings('div.tab-pane').removeClass('in active');
	$("#signModal").modal();
});
$('.nav-tabs li').off('click').on('click',function(){
	var index = $(this).index();
	if(index == 0){
		$('#stateWeightLabel').html('提货量：'+$("#pickupweight").val()+"吨");
	}
	if(index == 1){
		$('#stateWeightLabel').html('卸货量：'+$("#signweight").val()+"吨");
	}
});
//监听拒绝模态框关闭事件
$('#signModal').on('hidden.bs.modal', function (e) {
	$("#hidid").val("");
	$("#weight").val("");
	$("#planWeight").val("");
	$("#planCompleteWeight").val("");
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
	var planWeight = parseFloat($('#planWeight').val());
	var planCompleteWeight = parseFloat($('#planCompleteWeight').val());
	var title = "本次运单的计划运输量为"+$("#weight").val()+"吨，签收量为"+weightInput+"吨；货主计划总量为"+planWeight+"吨，已累计签收（含本次）数量为"+(planCompleteWeight+weightInput)+"吨；";
	if((planWeight - planCompleteWeight) <= weightInput){
		title += "现已超过计划总量，确认将关闭货运计划。";
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

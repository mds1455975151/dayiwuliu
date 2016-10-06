$(function(){
	$("#billdriver").addClass("selected");
	var URL={
		pageUrl:"/trwuliu/billdriver/page",
		refuseUrl:"/trwuliu/billdriver/refuseConfirm",
		deleteUrl:"/trwuliu/billdriver/delete",
		//装货确认
		pickupConfirmUrl:"/trwuliu/billdriver/pickupConfirm",
		//装完发车
		departureConfirmUrl:"/trwuliu/billdriver/departureConfirm",
		//到达目的地
		arrivedConfirmUrl:"/trwuliu/billdriver/arrivedConfirm",
		//卸货完成
		dischargeConfirmUrl:"/trwuliu/billdriver/dischargeConfirm",
		acceptConfirmUrl:"/trwuliu/billdriver/acceptConfirm",
		detailViewUrl:"/trwuliu/billdriver/detail"
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
			dataArr.push('<tr item="'+item.type+'">');
			if(item.type == 2){
				dataArr.push('<td><a href="'+URL.detailViewUrl+'?id='+item.id+'">批量运单</a></td>');
			}else{
				//已交班
				if(item.status == -10){
					dataArr.push('<td><span>'+item.waybillno+item.status+'</span></td>');
				}else{
					dataArr.push('<td><a href="'+URL.detailViewUrl+'?id='+item.id+'">'+item.waybillno+item.status+'</a></td>');
				}
			}
			dataArr.push('<td>'+item.cargoname+'</td>');
			dataArr.push('<td><p><i class="iconfont icon-dizhi billc1"></i>'+item.startcity+'</p>');
			dataArr.push('<p><i class="iconfont icon-dizhi billc2"></i>'+item.endcity+'</p></td>');
			dataArr.push('<td>');
			if(item.type == 2){
				dataArr.push('剩余'+item.overnumber+'趟');
			}else{
				if(item.status ==0){
					dataArr.push('新建');
				}else if(item.status ==7){
					dataArr.push('已取消');
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
					dataArr.push('已拒绝');
				}else if(item.status ==-10 ){
					dataArr.push('已交班');
				}
			}
			dataArr.push('</td>');
			dataArr.push('<td>'+item.modifytimeStr+'</td>');
			dataArr.push('<td>');
			/*dataArr.push('<a href="'+URL.detailViewUrl+'?id='+item.id+'"><button class="btn btnyello">查看</button></a>');*/
			if(item.status ==0){
				if(item.totalnumber == item.overnumber){
					dataArr.push('<a ><button class="btn btnyello refuseBtn" dataId="'+item.id+'"  dataCode="'+item.waybillno+'" >拒绝</button></a>');
				}
				dataArr.push('<a ><button class="btn btnyello acceptBtn" dataId="'+item.id+'"  dataCode="'+item.waybillno+'" >接受</button></a>');
			}else if(item.status ==7 ||item.status ==8){
				dataArr.push('<a ><button class="btn btnyello delBtn"  dataId="'+item.id+'"   dataCode="'+item.waybillno+'">删除</button></a>');
			}else if(item.status ==1){
				dataArr.push('<a ><button class="btn btnyello pickupBtn" dataId="'+item.id+'"   dataCode="'+item.waybillno+'">提货确认</button></a>');
//			}else if(item.status ==2){
//				dataArr.push('<a ><button class="btn btnyello departureBtn" dataId="'+item.id+'"  dataCode="'+item.waybillno+'" >装货完成</button></a>');
//			}else if(item.status ==3){
//				dataArr.push('<a ><button class="btn btnyello arrivedBtn" dataId="'+item.id+'"   dataCode="'+item.waybillno+'">到达确认</button></a>');
//			}else if(item.status ==4){
			}else if(item.status ==2){
				dataArr.push('<a ><button class="btn btnyello dischargeBtn" dataId="'+item.id+'"  dataCode="'+item.waybillno+'" >卸货完成</button></a>');
			}
			
			dataArr.push('</td>');
			dataArr.push('</tr>');
		});	
		
		//是否显示无数据
		if( bills.length==0 && pageNo ==1){
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
	$(".pageMore").click(function(){
		pageData($(this).attr("pageNo"));
	});
	//查询按钮点击
	$(".searchBtn").click(function(){
		pageData(1);
	}).trigger("click");
	
	//拒绝按钮点击
	$(".table").on("click",".refuseBtn",function(){
		var dId= $(this).attr("dataId");
		$("#hidbid").val(dId);
		$("#refuseModal").modal();
	});
	
	//拒绝运单 弹出层提交按钮绑定事件
	$("#refuseModal").off("click",".refusesubmitbtn").on("click",".refusesubmitbtn",function(){
		if(!$(".refuseCheckbox:checked").length==1){
			alert("请输入拒绝类型.");
			return ;	
		}
		if($(".refuseCheckbox:checked").val() =="其他" &&  !$(".refuseContent").val()){
			alert("请输入拒绝原因.");
			return ;	
		}
		$.ajax({
			url:URL.refuseUrl,
			data:{"id":$("#hidbid").val(),"refuseType":$(".refuseCheckbox:checked").val(),"refuseReson":$(".refuseContent").val()},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					$("#refuseModal").modal("hide");
					$(".searchBtn").trigger("click");
				}else{
					alert(rs.error);
				}
			}
		})
	})
	//上传榜单点击完成按钮
	$(".departsubmitbtn").off("click").on("click",function(){
		var id = $("#hidbid").val();
		var file = $("#file_bd")[0].files[0];
		var url = $("#urlReq").val();
		if(id && file && url){
			var formData = new FormData();
			formData.append("file",file);
			formData.append("id",id);
			$.ajax({
				url:PATH + url,
				data : formData, 
				processData : false,//告诉jQuery不要去处理发送的数据
				contentType : false,//告诉jQuery不要去设置Content-Type请求头
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						alert(rs.data);
						$("#upbangdan").modal("hide");
						$(".searchBtn").trigger("click");
					}else{
						alert(rs.error);
					}
				}
			});
		}else{
			alert("请先选择图片");
		}
		
	});
	//监听拒绝模态框关闭事件
	$('#refuseModal').on('hidden.bs.modal', function (e) {
		$("#hidbid").val("");
		$("#refuseModal .refuseContent").val("");
		$("#refuseModal").find("[name=refuseCheckbox]:checked").attr("checked",false);
	});
	var _htmlsrc =$(".bangdan_img").html();
	//监听上传模态框关闭事件
	$('#upbangdan').on('hidden.bs.modal', function (e) {
		$("#hidbid").val("");
		$("#imgdata").val("")
		$('.bangdan_img').html(_htmlsrc);
	});
	
	//接受按钮点击
	$(".table").on("click",".acceptBtn",function(){
		var dId= $(this).attr("dataId");
		var type = $(this).closest('tr').attr('item');
		confirm("确认","确认接受承运运单["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
			$.ajax({
				url:URL.acceptConfirmUrl,
				data:{"id":dId,"type":type},
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						$(".searchBtn").trigger("click");
					}else{
						alert(rs.error);
						$(".searchBtn").trigger("click");
					}
				}
			})
		})
	});
	//删除按钮点击
	$(".table").on("click",".delBtn",function(){
		var dId= $(this).attr("dataId");
		confirm("确认","确认删除运单["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
			$.ajax({
				url:URL.deleteUrl,
				data:{"id":dId},
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						$(".searchBtn").trigger("click");
					}else{
						alert(rs.error);
					}
				}
			})
		})
	});
	//pickupBtn 提货确认
	$(".table").on("click",".pickupBtn",function(){
		var dId= $(this).attr("dataId");
		confirm("上传榜单","是否要上传提货榜单？",function(){
			$("#urlReq").val(URL.pickupConfirmUrl);
			$("#hidbid").val(dId);
			$('.imageBox_bd').removeAttr('style');
			initFileInput();
			$("#upbangdan").modal();
		},function(){
			$.ajax({
				url:URL.pickupConfirmUrl,
				data:{"id":dId},
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						alert(rs.data);
						$(".searchBtn").trigger("click");
					}else{
						alert(rs.error);
					}
				}
			});
		});
	});
	//dischargeBtn 卸货完成
	$(".table").on("click",".dischargeBtn",function(){
		var dId= $(this).attr("dataId");
		$("#urlReq").val(URL.dischargeConfirmUrl);
		$("#hidbid").val(dId);
		$('.imageBox_bd').removeAttr('style');
		initFileInput();
		$("#upbangdan").modal();
	});
	//arrivedBtn 到达确认
	$(".table").on("click",".arrivedBtn",function(){
		var dId= $(this).attr("dataId");
		confirm("确认","确认运单["+$(this).attr("dataCode")+"]到达收货地吗,确认/取消?",function(){
			$.ajax({
				url:URL.arrivedConfirmUrl,
				data:{"id":dId},
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						alert(rs.data);
						$(".searchBtn").trigger("click");
					}else{
						alert(rs.error);
					}
				}
			})
		})
	});
	//departureBtn 发车确认
	$(".table").on("click",".departureBtn",function(){
		var dId= $(this).attr("dataId");
		confirm("确认","确认运单["+$(this).attr("dataCode")+"]发车确认吗,确认/取消?",function(){
			$.ajax({
				url:URL.departureConfirmUrl,
				data:{"id":dId},
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						alert(rs.data);
						$(".searchBtn").trigger("click");
					}else{
						alert(rs.error);
					}
				}
			})
		})
	});
	
	function initFileInput(){
		$('#file_bd').val('').fileinput('destroy');
		$("#file_bd").fileinput({
			language : 'zh',
			initialPreview: ['<img src="'+trRoot+'/tianrui/images/bd.png" class="file-preview-image">'],
			showUpload : false,
			dropZoneEnabled : true,
			maxFileCount : 1,
		//	minImageWidth: 50, //图片的最小宽度
		//	 	minImageHeight: 50,//图片的最小高度
		// 	maxImageWidth: 600,//图片的最大宽度
		//	  	maxImageHeight: 600,//图片的最大高度
			maxFileSize : 5000,//单位为kb，如果为0表示不限制文件大小
			resizeImage : true,
			showCaption : true,
			showPreview : true,
			allowedFileExtensions : [ 'jpg', 'png', 'jpeg' ]// 支持的图片类型
		}).on('fileuploaderror',function(event, data, previewId, index) {
			var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
			console.log(data);
			console.log('File upload error');
		}).on('fileerror', function(event, data) {
			console.log(data.id);
			console.log(data.index);
			console.log(data.file);
			console.log(data.reader);
			console.log(data.files);
		}).on('fileuploaded',function(event, data, previewId, index) {
			var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
			console.log('File uploaded triggered');
		});
	}
});

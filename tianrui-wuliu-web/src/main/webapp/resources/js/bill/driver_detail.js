$(function(){
	
	$("#billdriver").addClass("selected");
	var id =$("#billId").val();
	var URL={
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
			mainUrl:"/trwuliu/billdriver/main",
			//重新上传磅单
			updateBillImage:"/trwuliu/billdriver/updateBillImage"
	}
	
	
	//拒绝按钮点击
	$(".detailDiv").on("click",".refuseBtn",function(){
		$("#refuseModal").modal();
	});
	
	//拒绝运单 弹出层提交按钮绑定事件
	$("#refuseModal").off("click",".refusesubmitbtn").on("click",".refusesubmitbtn",function(){
		if(!$(".refuseCheckbox:checked").length==1){
			alert("请输入拒绝类型.");
			return ;	
		}
		if($(".refuseCheckbox:checked").val() =="其他" && !$(".refuseContent").val()){
			alert("请输入拒绝原因.");
			return ;	
		}
		$.ajax({
			url:URL.refuseUrl,
			data:{"id":$("#billId").val(),"refuseType":$(".refuseCheckbox:checked").val(),"refuseReson":$(".refuseContent").val()},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					window.location.reload();
				}else{
					alert(rs.error);
				}
			}
		})
	})
	
	//接受按钮点击
	$(".detailDiv").on("click",".acceptBtn",function(){
		var type = $('#billType').val();
		var star = $("#startcity").html();
		var end = $("#endcity").html();
		confirm("确认","确认接受承运运单["+$("#waybillno").html()+"]吗,确认/取消?<br>始发地-["+star+"],<br>目的地-["+end+"]",function(){
			$.ajax({
				url:URL.acceptConfirmUrl,
				data:{"id":$("#billId").val(),"type":type},
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						window.location.reload();
					}else{
						alert(rs.error);
					}
				}
			})
		})
	});
	//删除按钮点击
	$(".detailDiv").on("click",".delBtn",function(){
		$.ajax({
			url:URL.deleteUrl,
			data:{"id":$("#billId").val()},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					window.location.href=URL.mainUrl;
				}else{
					alert(rs.error);
				}
			}
		})
	});
	//pickupBtn 提货确认
	$(".detailDiv").on("click",".pickupBtn",function(){
		var dId= $("#billId").val();
		var frebilltype = $('#frebilltype').val();
		frebilltype = $.trim(frebilltype);
		if(frebilltype == "1"){
			$("#urlReq").val(URL.pickupConfirmUrl);
			initFileInput();
			$('#stateWeightLabel').html('提货量：');
			$("#upbangdan").modal();
		}
		if(frebilltype == "2"){
			confirm("上传磅单","是否要上传提货磅单？",function(){
				$("#urlReq").val(URL.pickupConfirmUrl);
				initFileInput();
				$('#stateWeightLabel').html('提货量：');
				$("#upbangdan").modal();
			},function(){
				$.ajax({
					url:URL.pickupConfirmUrl,
					data:{"id":dId},
					type : "post",
					dataType:"json",
					success:function(rs){
						if( rs && rs.code =="000000" ){
							window.location.reload();
						}else{
							alert(rs.error);
						}
					}
				});
			});
		}
	});
	//dischargeBtn 卸货完成确认
	$(".detailDiv").on("click",".dischargeBtn",function(){
		$("#urlReq").val(URL.dischargeConfirmUrl);
		initFileInput();
		$('#stateWeightLabel').html('卸货量：');
		$("#upbangdan").modal();
	});
	//dischargeBtn 到达确认
	$(".detailDiv").on("click",".arrivedBtn",function(){
		$.ajax({
			url:URL.arrivedConfirmUrl,
			data:{"id":$("#billId").val()},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					window.location.reload();
				}else{
					alert(rs.error);
				}
			}
		})
	});
	//departureBtn 装货完成确认
	$(".detailDiv").on("click",".departureBtn",function(){
		$.ajax({
			url:URL.departureConfirmUrl,
			data:{"id":$("#billId").val()},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					window.location.reload();
				}else{
					alert(rs.error);
				}
			}
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
			maxFileSize : 5120,//单位为kb，如果为0表示不限制文件大小
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
	
	var _htmlsrc =$(".bangdan_img").html();
	//监听上传模态框关闭事件
	$('#upbangdan').on('hidden.bs.modal', function (e) {
		$("#imgdata").val("")
		$('.bangdan_img').html(_htmlsrc);
	});
	
	//上传磅单点击完成按钮
	$(".departsubmitbtn").off("click").on("click",function(){
		var id = $("#billId").val();
		var file = $("#file_bd")[0].files[0];
		var url = $("#urlReq").val();
		var type = $("#bdType").val();
		var psweight = $('#stateWeight').val();
		if(!file){
			alert("请先选择图片");
			return ;
		}
		if(!$.trim(psweight)){
			alert("提货量或卸货量不能为空！");
    		return ;
		}
		if(!/^\d{1,6}(\.\d{0,2})?$/.test($.trim(psweight))){
			alert("提货量或卸货量格式整数最大6位，小数最大2位！");
			return;
		}
		if(id && url){
			var formData = new FormData();
			formData.append("file",file);
			formData.append("id",id);
			formData.append("type",type);
			formData.append("psweight",$.trim(psweight));
			$.ajax({
				url:PATH + url,
				data : formData, 
				processData : false,//告诉jQuery不要去处理发送的数据
				contentType : false,//告诉jQuery不要去设置Content-Type请求头
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						window.location.reload();
					}else{
						alert(rs.error);
					}
				}
			})
		}else{
			alert("参数异常！");
		}
		
	});
	
	$('#THBD').off('click').on('click',function(){
		var url = $(this).attr('item');
		var psweight = $(this).attr('psweight');
		if(psweight){
			psweight = parseFloat(psweight).toFixed(2)+"吨";
		}
		$("#bdType").val('TH');
		if(url){
			$('#bdImg').attr('src',url);
			$('#bdImg').parent('a').attr('href',"/imageView/index?imageUrl="+url);
			$('#psweight').html('提货量：'+psweight);
			$('#bdView').modal();
		}else{
			$('#bdts').modal();
		}
	});
	
	$('#XHBD').off('click').on('click',function(){
		var url = $(this).attr('item');
		var psweight = $(this).attr('psweight');
		if(psweight){
			psweight = parseFloat(psweight).toFixed(2)+"吨";
		}
		$("#bdType").val('XH');
		if(url){
			$('#bdImg').attr('src',url);
			$('#bdImg').parent('a').attr('href',"/imageView/index?imageUrl="+url);
			$('#psweight').html('卸货量：'+psweight);
			$('#bdView').modal();
		}else{
			$('#bdts').modal();
		}
	});
	
	$('#uploadImg,#againUploadImg').off('click').on('click',function(){
		$('#bdts,#bdView').modal('hide');
		$("#urlReq").val(URL.updateBillImage);
		initFileInput();
		$("#upbangdan").modal();
	});
	
});
/** 查询银行卡*/
function findMyBank(){
	$('#bank_card').modal();
	$("#bank_list").empty();
	$.ajax({
		url:"/trwuliu/bank/card/find",
		type:"post",
		data:{bankautid:"1"},
		success:function(ret){
			var data = ret.data.list;
			for (var a = 0; a < data.length; a++) {
				var bankType = "我的银行卡";
				if(data[a].type=="0"){
					bankType = "引用银行卡";
				}
				var hml = "<li>" +
						"<label style='width: 60px;'>"+data[a].idname+"</label>" +
						"<label style='width: 120px;'>"+data[a].telphone+"</label>" +
						"<label style='width: 160px;'>"+data[a].bankcard+"</label>" +
						"<label style='width: 120px;'>"+bankType+"</label>" +
						"<label style='width: 60px;'>" +
						"<button class='btn btnyello' onclick=\"uptBillBank('"+data[a].id+"','"+data[a].type+"','"+data[a].bankcard+"')\" style='width: 56px;height: 36px'>更换</button>" +
						"</label>" +
						"</li>";
				$("#bank_list").append(hml);
			}
		}
	});
}
function uptBillBank(id,type,bankcard){
	if(type == "0"){
		confirm("更换银行卡确认","该银行卡为引用车主银行卡，确定设置"+bankcard+"为支付银行卡吗？",function(){
			$.ajax({
				url:"/trwuliu/billdriver/uptBankCard",
				data:{"bankId":id,
					"bankType":"0",
					"billId":$("#billId").val()},
					type:"POST",
					success:function(ret){
						if(ret.code==000000){
							window.location.href=location;
						}else{
							alert(ret.error);
						}
					}
			});
		});
	}else{
		confirm("更换银行卡确认","确定设置"+bankcard+"为支付银行卡吗？",function(){
			$.ajax({
				url:"/trwuliu/billdriver/uptBankCard",
				data:{"bankId":id,
					"bankType":"1",
					"billId":$("#billId").val()},
					type:"POST",
					success:function(ret){
						if(ret.code==000000){
							window.location.href=location;
						}else{
							alert(ret.error);
						}
					}
			});
		});
		
	}
}



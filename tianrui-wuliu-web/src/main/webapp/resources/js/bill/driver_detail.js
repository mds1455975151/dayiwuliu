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
			//重新上传榜单
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
		confirm("上传榜单","是否要上传提货榜单？",function(){
			$("#urlReq").val(URL.pickupConfirmUrl);
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
						window.location.reload();
					}else{
						alert(rs.error);
					}
				}
			});
		});
	});
	//dischargeBtn 卸货完成确认
	$(".detailDiv").on("click",".dischargeBtn",function(){
		$("#urlReq").val(URL.dischargeConfirmUrl);
		initFileInput();
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
	
	//上传榜单点击完成按钮
	$(".departsubmitbtn").off("click").on("click",function(){
		var id = $("#billId").val();
		var file = $("#file_bd")[0].files[0];
		var url = $("#urlReq").val();
		var type = $("#bdType").val();
		if(id && file && url){
			var formData = new FormData();
			formData.append("file",file);
			formData.append("id",id);
			formData.append("type",type);
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
			alert("请先选择图片")
		}
		
	});
	
	$('#THBD').off('click').on('click',function(){
		var url = $(this).attr('item');
		$("#bdType").val('TH');
		if(url){
			$('#bdImg').attr('src',url);
			$('#bdView').modal();
		}else{
			$('#bdts').modal();
		}
	});
	
	$('#XHBD').off('click').on('click',function(){
		var url = $(this).attr('item');
		$("#bdType").val('XH');
		if(url){
			$('#bdImg').attr('src',url);
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

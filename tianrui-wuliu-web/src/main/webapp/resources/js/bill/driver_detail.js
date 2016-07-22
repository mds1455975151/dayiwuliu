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
			mainUrl:"/trwuliu/billdriver/main"
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
					alert("操作成功.")
					window.location.reload();
				}else{
					alert(rs.error);
				}
			}
		})
	})
	
	//接受按钮点击
	$(".detailDiv").on("click",".acceptBtn",function(){
		$.ajax({
			url:URL.acceptConfirmUrl,
			data:{"id":$("#billId").val()},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					alert("操作成功.")
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
					alert("操作成功.")
					window.location.href=URL.mainUrl;
				}else{
					alert(rs.error);
				}
			}
		})
	});
	//pickupBtn 提货确认
	$(".detailDiv").on("click",".pickupBtn",function(){
		$.ajax({
			url:URL.pickupConfirmUrl,
			data:{"id":$("#billId").val()},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					alert("操作成功.")
					window.location.reload();
				}else{
					alert(rs.error);
				}
			}
		})
	});
	//dischargeBtn 卸货完成确认
	$(".detailDiv").on("click",".dischargeBtn",function(){
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
					alert("操作成功.")
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
					alert("操作成功.")
					window.location.reload();
				}else{
					alert(rs.error);
				}
			}
		})
		
		
	});
	var _htmlsrc =$(".bangdan_img").html();
	//监听上传模态框关闭事件
	$('#upbangdan').on('hidden.bs.modal', function (e) {
		$("#imgdata").val("")
		$('.bangdan_img').html(_htmlsrc);
	});
	
	//上传榜单点击完成按钮
	$(".departsubmitbtn").off("click").on("click",function(){
		if( $("#billId").val() &&  $("#imgdata").val()){
			$.ajax({
				url:URL.dischargeConfirmUrl,
				data:{"id":$("#billId").val(),"imgdata":$("#imgdata").val()},
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						alert("操作成功.")
						window.location.reload();
					}else{
						alert(rs.error);
					}
				}
			})
		}else{
			alert("请先裁剪图片")
		}
		
	});
	

	
	//图片上传相关
	 // 点击修改头像按钮，图片裁剪框显示出来
   $(".bdup").on('click',function(){
       $(".bangdan_upload").show();
   });
   // 修改头像的收起按钮
   $(".tx_shouqi").on('click',function(){
       $(".bangdan_upload").hide();
   });
   // 图片裁切块的大小自定义，margin-top是height一半，margin-left是width一半
   var thumb = $(".imageBox_bd .thumbBox");
   thumb.width(350);
   thumb.height(140);
   thumb.css({ "margin-top": -70, "margin-left": -175 });
   // 给cropbox.js传参
   var options =
   {
       thumbBox: '.thumbBox',
       spinner: '.spinner'
   }
   var cropper = $('.imageBox_bd').cropbox(options);
   // 文件上传按钮操作
   $('#upload-file').on('change', function(){
       var reader = new FileReader();
       reader.onload = function(e) {
           options.imgSrc = e.target.result;
           cropper = $('.imageBox_bd').cropbox(options);
       }
       reader.readAsDataURL(this.files[0]);
       this.files = [];
   })
   // 裁切按钮操作
   $('#btnCrop').on('click', function(){
       var img = cropper.getDataURL();
       $('.bangdan_img').html('');
       $('.bangdan_img').append('<img src="'+img+'" align="absmiddle" >');
       $("#imgdata").val(img)
       $(".tx_cancel").on('click',function(){
           $(".acc_touxiang").hide();
       });
   })
   // 图片放大按钮操作
   $('#btnZoomIn').on('click', function(){
       cropper.zoomIn();
   })
   // 图片缩小按钮操作
   $('#btnZoomOut').on('click', function(){
       cropper.zoomOut();
   })
	
});

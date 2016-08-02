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
			dataArr.push('<tr>');
			//已交班
			if(item.status == -10){
				dataArr.push('<td><span>'+item.waybillno+item.status+'</span></td>');
			}else{
				dataArr.push('<td><a href="'+URL.detailViewUrl+'?id='+item.id+'">'+item.waybillno+item.status+'</a></td>');
			}
			dataArr.push('<td>'+item.cargoname+'</td>');
			dataArr.push('<td><p><i class="iconfont icon-dizhi billc1"></i>'+item.startcity+'</p>');
			dataArr.push('<p><i class="iconfont icon-dizhi billc2"></i>'+item.endcity+'</p></td>');
			dataArr.push('<td>');
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
			dataArr.push('</td>');
			dataArr.push('<td>'+item.modifytimeStr+'</td>');
			dataArr.push('<td>');
			/*dataArr.push('<a href="'+URL.detailViewUrl+'?id='+item.id+'"><button class="btn btnyello">查看</button></a>');*/
			if(item.status ==0){
				dataArr.push('<a ><button class="btn btnyello refuseBtn" dataId="'+item.id+'"  dataCode="'+item.waybillno+'" >拒绝</button></a>');
				dataArr.push('<a ><button class="btn btnyello acceptBtn" dataId="'+item.id+'"  dataCode="'+item.waybillno+'" >接受</button></a>');
			}else if(item.status ==7 ||item.status ==8){
				dataArr.push('<a ><button class="btn btnyello delBtn"  dataId="'+item.id+'"   dataCode="'+item.waybillno+'">删除</button></a>');
			}else if(item.status ==1){
				dataArr.push('<a ><button class="btn btnyello pickupBtn" dataId="'+item.id+'"   dataCode="'+item.waybillno+'">提货确认</button></a>');
			}else if(item.status ==2){
				dataArr.push('<a ><button class="btn btnyello departureBtn" dataId="'+item.id+'"  dataCode="'+item.waybillno+'" >装货完成</button></a>');
			}else if(item.status ==3){
				dataArr.push('<a ><button class="btn btnyello arrivedBtn" dataId="'+item.id+'"   dataCode="'+item.waybillno+'">到达确认</button></a>');
			}else if(item.status ==4){
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
		if( $("#hidbid").val()  &&  $("#imgdata").val()){
			$.ajax({
				url:URL.dischargeConfirmUrl,
				data:{"id":$("#hidbid").val(),"imgdata":$("#imgdata").val()},
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						$("#upbangdan").modal("hide");
						$(".searchBtn").trigger("click");
					}else{
						alert(rs.error);
					}
				}
			})
		}else{
			alert("请先裁剪图片")
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
		confirm("确认","确认接受承运运单["+$(this).attr("dataCode")+"]吗,确认/取消?",function(){
			$.ajax({
				url:URL.acceptConfirmUrl,
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
		confirm("确认","确认运单["+$(this).attr("dataCode")+"]提货确认吗,确认/取消?",function(){
			$.ajax({
				url:URL.pickupConfirmUrl,
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
	//dischargeBtn 卸货完成
	$(".table").on("click",".dischargeBtn",function(){
		var dId= $(this).attr("dataId");
		$("#hidbid").val(dId);
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
						$(".searchBtn").trigger("click");
					}else{
						alert(rs.error);
					}
				}
			})
		})
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

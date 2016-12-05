var list ;
function searchFile(){
	displayData(0);
}
function displayData(pageNo){
	var waybillno = $("#waybillno").val();
	var drivername = $("#drivername").val();
	var drivertel = $("#drivertel").val();
	var orgName = $("#orgName").val();
	var creater = $("#creater").val();
	var status = $("#status").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+"/admin/waybill/findWaybill",
		data:{
			"status":status,
			"key":waybillno,
			"pageNo":(pageNo+1),
			"pageSize":pageSize
		},
		type:"post",
		success:function(ret){
			if(ret.code=="000000"){
				$("#totalRecords").html(ret.data.total);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(ret.data.total == 0) {
			    	$("#totalPages").html(1); 
			    }else {
			    	$("#totalPages").html(parseInt((ret.data.total-1)/pageSize+1));  
			    }   
				list = ret.data.list;
				if(ret.data.list ){
					innerHTML(ret.data.list);
				}else{
					var hml="";
					hml +='<td colspan="12">';
			    	hml +='<div class="ht_none">';
			    	hml +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
			    	hml +='<div>';
			    	hml +='<h3>唉呀！查询不到您要找的内容</h3>';
			    	hml +='<p>换个查询条件试试吧？</p	>';
			    	hml +='</div>';
			    	hml +='</div>';
			    	hml +='</td>';
			    	document.getElementById("innhml").innerHTML=hml;
				}
				$("#pagination").pagination(ret.data.total, {   
				    callback: pageCallback,   
				    prev_text: '上一页',   
				    next_text: '下一页',   
				    items_per_page:pageSize,   
				    num_display_entries:4,   
				    current_page:pageNo,   
				    num_edge_entries:1, 
				    maxentries: ret.data.total, 
				    link_to:"javascript:void(0)"
				}); 
			}
		}
	});
}

function innerHTML(data){
	var hml = "";
	for (var a = 0; a < data.length; a++) {
		var d = a+1;
		var sta = "";
		if(data[a].status == "0"){
			sta = "司机未确认";
		}
		if(data[a].status == "1"){
			sta = "已接受";
		}
		if(data[a].status == "2"){
			sta = "已装货";
		}
		if(data[a].status == "3"){
			sta = "运输中";
		}
		if(data[a].status == "4"){
			sta = "已到达";
		}
		if(data[a].status == "5"){
			sta = "已卸货";
		}
		if(data[a].status == "6"){
			sta = "已完成";
		}
		if(data[a].status == "7"){
			sta = "司机拒绝接单";
		}
		if(data[a].status == "-1"){
			sta = "车主取消";
		}
		var orgName = data[a].orgName;
		if(orgName == undefined){
			orgName = "";
		}
		var venderName = data[a].venderName;
		if(venderName == undefined){
			venderName = "";
		}
		hml +="<tr><td>"+d+"</td>"+
		"<td>"+data[a].waybillno+"</td>"+
		"<td>"+data[a].drivername+"</td>"+
		"<td>"+data[a].drivertel+"</td>"+
		"<td>"+orgName+"</td>"+
		"<td>"+venderName+"</td>"+
		"<td>"+data[a].createtimeStr+"</td>"+
		"<td>"+sta+"</td>"+
		"<td><span><a data-toggle='modal' onclick=\"details('"+a+"')\" data-target='#detail'>【查看详情】</a></span>"+
		(data[a].status == "6"&&data[a].isClearing=="0"? "<span><a  data-toggle='modal' onclick=\"yunjia('"+data[a].id+"')\" data-target='#yunjia'>【运价确认】</a></span>":"")+
		(data[a].status == "6" ? (data[a].isAssess == '1' ? '<span><a data-toggle="modal" onclick="showAssess(\''+data[a].id+'\')" data-target="#showAssess">【查看评价】</a></span>' : '<span><a data-toggle="modal" onclick="billAssess(\''+data[a].id+'\')" data-target="#assess">【信用评价】</a></span>') : "")+
//		"<span><a data-toggle='modal' data-target='#tingyong'>停用</a></span>" +
		"</td>";
	}
	document.getElementById("innhml").innerHTML=hml;
}

function details(a){
	var orgName = list[a].orgName;
	if(orgName == undefined){
		orgName = "";
	}
	var venderName = list[a].venderName;
	if(venderName == undefined){
		venderName = "";
	}
	var hml = "<div class='file_detail'><label>运单编码：</label><span>"+list[a].waybillno+"</span></div>"+
				"<div class='file_detail'><label>组织名称：</label><span>"+orgName+"</span></div>"+
				"<div class='file_detail'><label>承运商：</label><span>"+venderName+"</span></div>"+
				"<div class='file_detail'><label>创建时间：</label><span>"+list[a].createtimeStr+"</span></div>"+
				"<div class='file_detail'><label>货物名称：</label><span>"+list[a].cargoname+"</span></div>"+
				"<div class='file_detail'><label>计量单位：</label><span>"+list[a].desc1+"</span></div>"+
				"<div class='file_detail'><label>计价单位：</label><span>"+list[a].priceunits+"</span></div>"+
				"<div class='file_detail'><label>起运地：</label><span>"+list[a].startcity+" </span></div>"+
				"<div class='file_detail'><label>目的地：</label><span>"+list[a].endcity+" </span></div>"+
				"<div class='file_detail'><label>结算里程数：</label><span>"+list[a].distance+"</span></div>"+
				"<div class='file_detail'><label>发货人：</label><span>"+list[a].consignorname+list[a].consignortel+"</span></div>"+
				"<div class='file_detail'><label>收货人：</label><span>"+list[a].receivername+list[a].receivertel+"</span></div>"+
				"<div class='file_detail'><label>开始时间：</label><span>"+list[a].starttime+"</span></div>"+
				"<div class='file_detail'><label>结束时间：</label><span>"+list[a].endtime+"</span></div>"+
				"<div class='file_detail'><label>原发运输量：</label><span>"+list[a].weight+"吨</span></div>"+
				"<div class='file_detail'><label>签收运输量：</label><span>"+list[a].trueweight+"吨</span></div>"+
				"<div class='file_detail'><label>运单价格：</label><span>"+list[a].price+"元</span></div>"+
				"<div class='file_detail2'><label>车辆信息：</label><span>"+list[a].vehicleno+"</span>" +
						"<span>"+list[a].drivername+"</span><span>"+list[a].drivertel+"</span></div>"+
				"<div class='clear'></div>";
	document.getElementById("dateilshml").innerHTML=hml;
}


function queren(){
	var price = $("#trueprice").val();
	
	var reg=/\d+(\.\d+)?/;
	if(!reg.test(price)) {
		alert("请输入数字");
		return;
	}
	
	if(window.confirm('确定对运单进行运价确认吗,确定/取消?')){
		$("#ncdisable").attr("disabled",true); 
		$.ajax({
			url:"/admin/waybill/priceConfrim",
			data:{"billId":$("#billid").val(),
				"trueprice":$("#trueprice").val()
			},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					$("#billid").val("");
					$("#trueprice").val("");
					$("#ncdisable").attr("disabled",false); 
					window.location.reload();
				}else{
					alert(rs.error);
					$("#ncdisable").attr("disabled",false); 
				}
			}
		});
	}
}

function yunjia(id){
	$("#billid").val("");
	$("#trueprice").val("");
	$.ajax({
		url:"/admin/waybill/billPrice",
		data:{"billId":id},
		type : "post",
		dataType:"json",
		success:function(rs){
			if( rs && rs.code =="000000" ){
				$("#billid").val(id);
				$("#pricenow").html(rs.data.price+rs.data.priceunits);
				$("#pickupweight").html(rs.data.pickupweight);
				$("#signweight").html(rs.data.signweight);
				$("#trueweight").html(rs.data.trueweight);
			}else{
				alert(rs.error);
			}
		}
	});
}

function billAssess(id){
	$('#bid').val(id);
	$('.assessBody input[name="timelystart"][value="1"]').trigger('click');
	$('.assessBody input[name="timelydelivery"][value="1"]').trigger('click');
	$('.assessBody input[name="timelyreturn"][value="1"]').trigger('click');
	$('.assessBody input[name="cardamage"][value="0"]').trigger('click');
	$('.assessBody input[name="transportaccident"][value="0"]').trigger('click');
}

$('.assessComfirm').off('click').on('click',function(){
	$(this).addClass('disabled');
	var billid = $('#bid').val();
	if(!billid){
		alert('运单ID不能为空！');return;
	}
	var timelystart = $('.assessBody input[name="timelystart"]:checked').val();
	if(!timelystart){
		alert('请评价是否及时发车！');return;
	}
	var timelydelivery = $('.assessBody input[name="timelydelivery"]:checked').val();
	if(!timelydelivery){
		alert('请评价是否及时送达！');return;
	}
	var timelyreturn = $('.assessBody input[name="timelyreturn"]:checked').val();
	if(!timelyreturn){
		alert('请评价是否及时回单！');return;
	}
	var cardamage = $('.assessBody input[name="cardamage"]:checked').val();
	if(!cardamage){
		alert('请评价是否车质损耗！');return;
	}
	var transportaccident = $('.assessBody input[name="transportaccident"]:checked').val();
	if(!transportaccident){
		alert('请评价是否有运输事故！');return;
	}
	$.ajax({
		url:"/admin/waybill/billAssess",
		data:{
			billid:billid,
			timelystart:timelystart,
			timelydelivery:timelydelivery,
			timelyreturn:timelyreturn,
			cardamage:cardamage,
			transportaccident:transportaccident
		},
		type : "post",
		dataType:"json",
		success:function(rs){
			if(rs && rs.code =="000000"){
				alert(rs.data);
				window.location.reload();
			}else{
				alert(rs.error);
			}
		}
	});
});

function showAssess(id){
	$.ajax({
		url:"/admin/waybill/queryAssess",
		data:{
			billid:id
		},
		type : "post",
		dataType:"json",
		success:function(rs){
			if(rs && rs.code =="000000"){
				var data = rs.data;
				$('#timelystart').html(data.timelystart == '0' ? '否' : '是');
				$('#timelydelivery').html(data.timelydelivery == '0' ? '否' : '是');
				$('#timelyreturn').html(data.timelyreturn == '0' ? '否' : '是');
				$('#cardamage').html(data.cardamage == '0' ? '否' : '是');
				$('#transportaccident').html(data.transportaccident == '0' ? '无' : data.transportaccident == '1' ? '无责事故' : '责任事故');
			}else{
				alert(rs.error);
			}
		}
	});
}


var pageSize = 10;
var datalist;
function displayData(pageNo){
	$.ajax({
		url:"/merchant/page",
		data:{
			"code":$.trim($("#scode").val()),
			"address":$.trim($("#address").val()),
			"onlycode":$.trim($("#sonlycode").val()),
			"name":$.trim($("#sname").val()),
			"pageNo":pageNo*pageSize,
			"pageSize":pageSize},// 你的formid
		type:"post",
		success:function(result){
			var code = result.code;
			if(code == "000000"){
				$("#totalRecords").html(result.data.total);
				document.getElementById("goPage").value = pageNo+1;
				$("#pagination").pagination(result.data.total, {   
				    callback: pageCallback,   
				    prev_text: '上一页',   
				    next_text: '下一页',   
				    items_per_page:pageSize,   
				    num_display_entries:4,   
				    current_page:pageNo,   
				    num_edge_entries:1, 
				    maxentries: result.data.total, 
				    link_to:"javascript:void(0)"
				}); 
				innerHtml(result.data,pageNo);
			}else{
				alert(result.error);
			}
		}
	});
}

function innerHtml(ret,pageNo){
	var hml = ""
	var total = ret.total;
	if(total != 0){
		datalist = ret.list;
		for (var a = 0; a < datalist.length; a++) {
			var remark = datalist[a].remark == undefined ? "" : datalist[a].remark;
			var type = "";
			if(datalist[a].type=="1"){
				type = "合同";
			}else if(datalist[a].type=="2"){
				type = "指导";
			}
			
			var nature = "";
			if(datalist[a].nature == "1"){
				nature = "国营";
			}else if(datalist[a].nature == "2"){
				nature = "私营";
			}
			
			var desc1 = "";
			var desStr = "";
			if(datalist[a].desc1 == 1){
				desStr = "已启用";
				desc1 = "停用"
			}else if(datalist[a].desc1 == 0){
				desStr = "已停用";
				desc1 = "启用"
			}
			
			hml += "<tr><td>"+datalist[a].code+"</td>" +
			"<td>"+datalist[a].name+"</td>" +
			"<td>"+datalist[a].onlycode+"</td>" +
			"<td>"+type+"</td>" +
			"<td>"+nature+"</td>" +
			"<td>"+datalist[a].linkman+"</td>" +
			"<td>"+datalist[a].linknumber+"</td>" +
			"<td>"+datalist[a].address+"</td>" +
			"<td>"+desStr+"</td>" +
			"<td>"+remark+"</td>" +
			"<td>" +
			"<span><a data-toggle='modal' onclick=\"findByIndex('"+a+"','"+pageNo+"')\" data-target='#edit_merchant'>【修改】</a></span>" +
			"<span><a onclick=\"deleteId('"+datalist[a].id+"','"+datalist[a].desc1+"','"+pageNo+"')\">【"+desc1+"】<a></span></td></tr>";
		}
	}
	$("#innerHtml").html(hml);
}

function deleteId(id,status,pageNo){
	if(window.confirm('确定执行本次操作吗？')){
		var desc1 ;
		if(status == 0){
			desc1 = 1
		}else if(status == 1){
			desc1 = 0
		}
		$.ajax({
			url:"/merchant/delete",
			data:{"id":id,
				"desc1":desc1},
			type:"post",
			success:function(ret){
				var code = ret.code;
				if(code == "000000"){
					displayData(parseInt(pageNo));
				}else{
					alert(ret.error);
				}
			}
		});
	}
}

function findByIndex(a,pageNo){
	var data = datalist[a];
	$("#merchantid").val(data.id);
	$("#uptcode").val(data.code);
	$("#uptname").val(data.name);
	$("#uptonlycode").val(data.onlycode);
	$("#upttype").val(data.type);
	$("#uptnature").val(data.nature);
	$("#uptlinkman").val(data.linkman);
	$("#uptlinknumber").val(data.linknumber);
	$("#uptaddress").val(data.address);
	$("#uptremark").val(data.remark);
	$("#pageNo").val(pageNo);
}

$(".save_Merchant").on("click",function(){
	var savecode = $("#savecode").val();
	var savename = $("#savename").val();
	var saveonlycode = $("#saveonlycode").val();
	var savetype = $("#savetype").val();
	var savenature = $("#savenature").val();
	var savelinkman = $("#savelinkman").val();
	var savelinknumber = $("#savelinknumber").val();
	var saveaddress = $("#saveaddress").val();
	
	if(!$.trim(savecode)){
		alert("客商编码不能为空");
		return;
	}
	if(!$.trim(savename)){
		alert("客商名称不能为空");
		return;
	}
	if(!$.trim(savetype)){
		alert("客商分类不能为空");
		return;
	}
	if(!$.trim(saveonlycode)){
		alert("唯一识别码不能为空");
		return;
	}
	if(!$.trim(savenature)){
		alert("企业性质不能为空");
		return;
	}
	if(!$.trim(savelinkman)){
		alert("联系人不能为空");
		return;
	}
	var f = /^1[34578]\d{9}$/;
	if(!f.test(savelinknumber)){
		alert("请输入正确联系方式");
		return;
	}
	if(!$.trim(saveaddress)){
		alert("详细地址不能为空");
		return;
	}
	$(".save_Merchant").attr("disabled",true);
	$.ajax({
		url:"/merchant/insert",
		data:$('#saveMerchant').serialize(),// 你的formid
		type:"post",
		success:function(ret){
			var code = ret.code;
			if(code == "000000"){
				$(".save_Merchant").attr("disabled",false);
				alert("添加成功");
				cleanValue();
				$("#addclick").click();
				displayData(0);
			}else{
				$(".save_Merchant").attr("disabled",false);
				alert(ret.error);
			}
			
		}
	});
});


function updateMerchart(){
	var savecode = $("#uptcode").val();
	var savename = $("#uptname").val();
	var saveonlycode = $("#uptonlycode").val();
	var savetype = $("#upttype").val();
	var savenature = $("#uptnature").val();
	var savelinkman = $("#uptlinkman").val();
	var savelinknumber = $("#uptlinknumber").val();
	var saveaddress = $("#uptaddress").val();
	var pageNo =$("#pageNo").val();
	if(!$.trim(savecode)){
		alert("客商编码不能为空");
		return;
	}
	if(!$.trim(savename)){
		alert("客商名称不能为空");
		return;
	}
	if(!$.trim(savetype)){
		alert("客商分类不能为空");
		return;
	}
	if(!$.trim(saveonlycode)){
		alert("唯一识别码不能为空");
		return;
	}
	if(!$.trim(savenature)){
		alert("客商分类不能为空");
		return;
	}
	if(!$.trim(savelinkman)){
		alert("联系人不能为空");
		return;
	}
	var f = /^1[34578]\d{9}$/;
	if(!f.test(savelinknumber)){
		alert("请输入正确联系方式");
		return;
	}
	if(!$.trim(saveaddress)){
		alert("详细地址不能为空");
		return;
	}
	
	$.ajax({
		url:"/merchant/update",
		data:$('#uptMerchant').serialize(),// 你的formid
		type:"post",
		success:function(ret){
			var code = ret.code;
			if(code == "000000"){
				alert("修改成功");
				$("#updateclick").click();
				displayData(parseInt(pageNo));
			}else{
				alert(ret.error);
			}
		}
	});
}

function cleanValue(){
	$("#savecode").val("");
	$("#savename").val("");
	$("#saveonlycode").val("");
	$("#savetype").val("");
	$("#savenature").val("");
	$("#savelinkman").val("");
	$("#savelinknumber").val("");
	$("#saveaddress").val("");
	$("#saveremark").val("");
}
function clearSearch(){
	$("#scode").val("");
	$("#sonlycode").val("");
	$("#sname").val("");
	$("#address").val("");
	displayData(0);
}

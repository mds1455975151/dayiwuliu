
var pageSize = 10;
var datalist;
function displayData(pageNo){
	$.ajax({
		url:"/merchant/page",
		data:{
			"code":$.trim($("#scode").val()),
			"type":$.trim($("#stype").val()),
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
				innerHtml(result.data);
			}else{
				alert(result.error);
			}
		}
	});
}

function innerHtml(ret){
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
			hml += "<tr><td>"+datalist[a].code+"</td>" +
			"<td>"+datalist[a].name+"</td>" +
			"<td>"+datalist[a].onlycode+"</td>" +
			"<td>"+type+"</td>" +
			"<td>"+nature+"</td>" +
			"<td>"+datalist[a].linkman+"</td>" +
			"<td>"+datalist[a].linknumber+"</td>" +
			"<td>"+datalist[a].address+"</td>" +
			"<td>"+remark+"</td>" +
			"<td>" +
			"<span><a data-toggle='modal' onclick=\"findByIndex('"+a+"')\" data-target='#edit_merchant'>【修改】</a></span>" +
			"<span><a onclick=\"deleteId('"+datalist[a].id+"')\">【删除】<a></span></td></tr>";
		}
	}
	$("#innerHtml").html(hml);
}

function deleteId(id){
	if(window.confirm('确定删除本条数据吗？')){
		$.ajax({
			url:"/merchant/delete",
			data:{"id":id},
			type:"post",
			success:function(ret){
				var code = ret.code;
				if(code == "000000"){
					displayData(0);
				}else{
					alert(ret.error);
				}
			}
		});
	}
}

function findByIndex(a){
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
}

/** 新增*/
function saveMerchant(){
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
	if(!$.trim(savelinknumber)){
		alert("联系电话不能为空");
		return;
	}
	if(!$.trim(saveaddress)){
		alert("详细地址不能为空");
		return;
	}
	$.ajax({
		url:"/merchant/insert",
		data:$('#saveMerchant').serialize(),// 你的formid
		type:"post",
		success:function(ret){
			var code = ret.code;
			if(code == "000000"){
				alert("添加成功");
				cleanValue();
				$("#addclick").click();
				displayData(0);
			}else{
				alert(ret.error);
			}
		}
	});
}

function updateMerchart(){
	var savecode = $("#uptcode").val();
	var savename = $("#uptname").val();
	var saveonlycode = $("#uptonlycode").val();
	var savetype = $("#upttype").val();
	var savenature = $("#uptnature").val();
	var savelinkman = $("#uptlinkman").val();
	var savelinknumber = $("#uptlinknumber").val();
	var saveaddress = $("#uptaddress").val();
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
	if(!$.trim(savelinknumber)){
		alert("联系电话不能为空");
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
				displayData(0);
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
	$("#stype").val("");
	$("#sname").val("");
}

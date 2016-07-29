
var regDouble=/^[-\+]?\d+(\.\d+)?$/;

/**
 * 条件查询
 */
function SearchPrice(){
	displayData(0);
}
function displayData(pageNo){
	var Scargo = $("#Scargo").val();
	var Sdesc1 = $("#Sdesc1").val();
	var SRoute = $("#SRoute").val();
	var Sdesc2 = $("#Sdesc2").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url : CONTEXTPATH + '/frieght/findByFreightEntity',// 跳转到 action
		data : {"cargoid":Scargo,
				"desc1":Sdesc1,
				"routeid":SRoute,
				"desc2":Sdesc2,
				"pageNo":(pageNo + 1),
				"pageSize":pageSize
		},
		type : "post",
		success : function(result) {
			if( result && result.code=="000000"  ){
				var data = result.data.list;
				$("#totalRecords").html(result.data.count);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(result.data.count == 0) {
			    	$("#totalPages").html(1); 
			    	$("#tablelist").empty();
			    	var html;
			    	if(Scargo || Sdesc1 || SRoute ||Sdesc2){
			    		html +='<td colspan="12">';
			    		html +='<div class="ht_none">';
			    		html +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
			    		html +='<div>';
			    		html +='<h3>唉呀！查询不到您要找的内容</h3>';
			    		html +='<p>换个查询条件试试吧？</p>';
			    		html +='</div>';
			    		html +='</div>';
			    		html +='</td>';
			    	}else{
			    		html +=' <td colspan="12">';
			    		html +='<div class="ht_none">';
			    		html +='<img src="'+imagesRoot+'/none1.png" class="ht_nimg2">';
			    		html +='<div>';
			    		html +='<h3>组织下暂无可用运价策略！？</h3>';
			    		html +='<p>赶快动动手指加上吧！</p>';
			    		html +='</div>';
			    		html +='</div>';
			    		html +='</td>';
			    	}
			    	$("#tablelist").html(html);
			    }else {
			    	$("#totalPages").html(parseInt((result.data.count-1)/pageSize+1));
			    	interHTML(data);
			    }  
				$("#pagination").pagination(result.data.count, {   
				    callback: pageCallback,   
				    prev_text: '上一页',   
				    next_text: '下一页',   
				    items_per_page:pageSize,   
				    num_display_entries:4,   
				    current_page:pageNo,   
				    num_edge_entries:1, 
				    maxentries: result.data.count, 
				    link_to:"javascript:void(0)"
				}); 
			}else{
				alert(result.error);
			}
		}
	});
}
/**
 * 清空搜索
 */
function clearSearch(){
	document.getElementById("Scargo").value="";
	document.getElementById("Sdesc1").value="";
	document.getElementById("SRoute").value="";
	document.getElementById("Sdesc2").value="";
}
/**
 * 查询结果写在页面
 * @param data
 */
function interHTML(data){
	var hml = "";
	for (var a = 0; a < data.length; a++) {
		var s = a+1;
		var status = "";
		if(data[a].status == "0"){
			status = "停用";
		}else if(data[a].status == "1"){
			status = "启用";
		}
		var cargostatus = "";
		if(data[a].desc3=="0"){
			cargostatus ="已停用";
		}else if(data[a].desc3=="1"){
			cargostatus ="已启用";
		}
		var routestatus = "";
		if(data[a].desc4=="0"){
			routestatus ="已停用";
		}else if(data[a].desc4=="1"){
			routestatus ="已启用";
		}
		var pricestatus = "停用";
		if(data[a].status == "0" && data[a].desc3=="1" && data[a].desc4=="1"){
			pricestatus = "启用";
		}
		var flag = true;
		if(data[a].desc3=="0" || data[a].desc4=="0"){
			flag = false;
		}
		if(flag && data[a].status == "0"){
			hml +="<tr>"+
			'<td><input type="checkbox"  value="'+data[a].id+'" name="user"></td>';
		}else {
			hml +="<tr>"+
			'<td></td>';
		}
		var tallage = "";
		if(data[a].tallage != undefined){
			tallage = data[a].tallage;
		}
		hml +="<td>"+data[a].freightName+"</td>"+ 
			"<td>"+data[a].price+"</td>"+ 
			"<td>"+data[a].priceunits+"</td>"+ 
			"<td>"+tallage+"</td>"+ 
			"<td>"+pricestatus+"</td>"+ 
			"<td>"+data[a].cargoid+"/("+cargostatus+")</td>"+ 
			"<td>"+data[a].routeid+"/("+routestatus+")</td><td>";
		if(flag){
			hml +="<span><a data-toggle='modal' onclick=\"tyPrice('"+data[a].id+"','"+data[a].status+"')\" data-target='#tingyong'>【"+status+"】</a></span>";
		}
		if(data[a].count == 0){
			hml +="<span><a data-toggle='modal' onclick=\"findById('"+data[a].id+"')\" data-target='#edit_price'>【修改】</a></span>"+ 
			"<span><a data-toggle='modal' onclick=\"delectPrice1('"+data[a].id+"')\" data-target='#dele_price'>【删除】</a></span>";
		}
		hml +="</td></tr>";
	}
	document.getElementById("tablelist").innerHTML=hml;
}
/**
 * 添加纪录
 */
function savePrice(){
	var adddesc1 = $("#adddesc1").val();
	var addcargoid = $("#addcargoid").val();
	var addrouteid = $("#addrouteid").val();
	var addpriceunits = $("#addpriceunits").val();
	var addmeasure = $("#addmeasure").val();
	var addprice = $("#addprice").val();
	var adddesc2 = $("#adddesc2").val();
	if(adddesc1 == ""){
		alert("策略名称不能为空");
		return;
	}
	if(addcargoid == ""){
		alert("请选择货物");
		return;
	}
	if(addrouteid == ""){
		alert("请选择路径");
		return;
	}
	if(adddesc2 == ""){
		alert("请选择价格类型");
		return;
	}
	if(addprice == ""){
		alert("请输入单价");
		return;
	}else{
		if(!regDouble.test(addprice)){
			alert("单价必须为数值");
			return;
		}else{
			$.ajax({
				cache: true,
				type: "POST",
				url:CONTEXTPATH + '/frieght/saveFreight',// 跳转到 action
				data:$('#saveFreight').serialize(),// 你的formid
				async: false,
				success: function(rs) {
					if(rs.code=="000000"){
						SearchPrice();
						document.getElementById("addclick").click();
					}else{
						alert(rs.error);
					}
				}
			});
		}
	}
}
/**
 * 删除提示
 * @param id
 */
function delectPrice1(id){
	document.getElementById("deleteid").value = id;
}
/**
 * 删除记录
 */
function deletePrice(){
	var id = $("#deleteid").val();
	$.ajax({
		type: "POST",
		url:CONTEXTPATH + '/frieght/delectFreight',// 跳转到 action
		data:{"id":id},// 你的formid
		success: function(rs) {
			SearchPrice();
		}
	});
}
/**
 * 停用提示
 * @param id
 */
function tyPrice(id,statue){
	document.getElementById("tyId").value = id;
	document.getElementById("tystatue").value = statue;
}
/**
 * 停用
 */
function tingyongPrice(){
	var id = $("#tyId").val();
	var status = $("#tystatue").val();
	var sta = "";
	if(status == 0){
		sta = "1";
	}
	if(status == 1){
		sta = "0";
	}
	$.ajax({
		type: "POST",
		url:CONTEXTPATH + '/frieght/updateFreight',// 跳转到 action
		data:{"id":id,
				"status":sta
				},// 你的formid
		success: function(rs) {
			if(rs.code=="000000"){
				document.getElementById("tyId").value = "";
				document.getElementById("tystatue").value = "";
				SearchPrice();
			}else{
				alert(rs.error);
			}
		}
	});
}
/**
 * 通过id查询
 * @param id
 */
function findById(id){
	$.ajax({
		type: "POST",
		url:CONTEXTPATH + '/frieght/findByFreightId',// 跳转到 action
		data:{"id":id},// 你的formid
		success: function(rs) {
			if(rs.code=="000000"){
				var data = rs.data;
				document.getElementById("uptId").value = data.id;
				document.getElementById("uptdesc1").value = data.freightName;
				document.getElementById("uptcargoid").value = data.cargoid;
				document.getElementById("uptrouteid").value = data.routeid;
				document.getElementById("uptprice").value = data.price;
				document.getElementById("uptdesc2").value = data.freightType;
				document.getElementById("uptpriceunits").value = data.priceunits;
				document.getElementById("uptmeasure").value = data.measure;
			}
		}
	});
}
/**
 * 修改纪录
 */
function updatePrice(){
	var adddesc1 = $("#uptdesc1").val();
	var addcargoid = $("#uptcargoid").val();
	var addrouteid = $("#uptrouteid").val();
	var addpriceunits = $("#uptpriceunits").val();
	var addprice = $("#uptprice").val();
	var adddesc2 = $("#uptdesc2").val();
	var uptmeasure = $("#uptmeasure").val();
	if(adddesc1 == ""){
		alert("策略名称不能为空");
		return;
	}
	if(addcargoid == ""){
		alert("请选择货物");
		return;
	}
	if(addrouteid == ""){
		alert("请选择路径");
		return;
	}
	if(adddesc2 == ""){
		alert("请选择价格类型");
		return;
	}
	if(addprice == ""){
		alert("请输入单价");
		return;
	}else{
		if(!regDouble.test(addprice)){
			alert("单价必须为数值");
			return;
		}
	}
	$.ajax({
		cache: true,
		type: "POST",
		url:CONTEXTPATH + '/frieght/updateFreight',// 跳转到 action
		data:$('#uptform').serialize(),// 你的formid
		async: false,
		success: function(rs) {
			if(rs.code=="000000"){
				SearchPrice();
				document.getElementById("updateclick").click();
			}else{
				alert(rs.error);
			}
		}
	});
}
function cargoChange(type){
	var id = $("#addcargoid").val();
	$.ajax({
		type: "POST",
		url:CONTEXTPATH + '/myCargo/getMyCargoInfo',// 跳转到 action
		data:{"id":id},// 你的formid
		success: function(rs) {
			var d = rs.data.list[0].measUnit;
			document.getElementById(type+"priceunits").value=d+"/元";
			document.getElementById(type+"measure").value=d;
		}
	});
}
/**
 *『批量停用』功能点击事件
 * 
 * @param null
 * @return null
 * @author tank
 * @time 2016.07.05
 */
$("#batchDisable").click(function() {
	// 获取表体所有checkbox被选中数据的用户ID
	var checkedRoleId = "";
	$("#tablelist input[type=checkbox]:checked").each(function() {
		if($(this).val()!=""){
			if (checkedRoleId != "") {
				checkedRoleId += ",'" + $(this).val() + "'";
			}else {
				checkedRoleId += "'" + $(this).val() + "'";
			}
		}
	});
	if(checkedRoleId == ""){
		alert("请选择要停用的运价策略！");
		return;
	}
	if(window.confirm('停用后关联的下游单据将无法使用！你确定要停用选择的运价策略？')){
		$.ajax({
			url : "/frieght/updateStatus",// 跳转到 action
			data : {
				id: checkedRoleId
			},
			type : "post",
			success : function(result) {
				$("#modal_common_content").html("批量停用完成！");
				$("#commonModal").modal();
				displayData(0);
			}
		});
    }else{
        return false;
    }
});
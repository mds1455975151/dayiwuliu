/**
 * 页面初始化
 */
$(function(){
	search();
});
var regDouble=/^[-\+]?\d+(\.\d+)?$/;
/**
 *计量单位 
 */
//单量纲添加
function oneMeasureSave(){
	var code = $("#code").val();
	var cname = $("#cname").val();
	var type = $("#type").val();
	var conversion = $("#conversion").val();
	var newType = $("#newType").val();
	var base = document.getElementsByName("base");
	var abase = "";
	for(var i=0;i<base.length;i++){
	     if(base[i].checked){
	    	 abase = base[i].value;
	     }
	}
	if(code==""){
		alert("计量编码不能为空");
		return;
	}
	if(cname==""){
		alert("计量名称不能为空");
		return;
	}
	if(newType != "" ){
		var flag = true;
		$("#type option").each(function () {
            if( $(this).val() == newType){
            	flag = false;
            }
        });
		if(flag){
			abase = "0";
			conversion = "1";
		}
		type = newType;
	}else if(type = ""){
		alert("请选择或填写量纲");
		return;
	}
	if(conversion==""){
		alert("请填写换算率");
		return;
	}else{
		if(!regDouble.test(conversion)){
			alert("换算率必须为数值");
			return;
		}
	}
	$.ajax({
		url : CONTEXTPATH + '/measure/measureSave',// 跳转到 action
		data : {
			"measureCode":code,
			"measureName":cname,
			"measureType":type,
			"measureBase":abase,
			"conversion":conversion
		},
		type : "post",
		success : function(result) {
			if(result.code=="000000"){
				if(newType != ""){
					$("#oType").append($("<option></option>").attr("value",type).text(type));
					$("#oType").val(type);
				}
				$("#searchMeasure").trigger("click");
				$("#closeSave").trigger("click");
			}else{
				alert(result.error);
			}
		}
	});
}
/**
 * 查询基本量纲
 */
function changeType(type){
	var measureType = document.getElementById(type).value;
	$.ajax({
		url : CONTEXTPATH + '/measure/findMeasureCode',// 跳转到 action
		data : {
			"measureType":measureType
		},
		type : "post",
		success : function(result) {
			
			if(result.code=="000000"){
				document.getElementById("codeBase"+type).innerHTML = result.data.measureName;
			}else{
				//alert(result.error);
			}
		}
	});
}

/**
 * 修改基本量纲
 */
function changeBase(id){
	$.ajax({
		url : CONTEXTPATH + '/measure/updateMeasureBase',// 跳转到 action
		data : {
			"id":id,
			"conversion":1,
			"measureBase":"0"
		},
		type : "post",
		success : function(result) {
			if(result.code=="000000"){
				$("#searchMeasure").trigger("click");
			}else{
				alert(result.error);
			}
		}
	});
}
/**
 * 删除单位
 */
function delectMeasure(id){
	document.getElementById("measureId").value=id;
}
function measureDelect(){
	var id = $("#measureId").val();
	$.ajax({
		url : CONTEXTPATH + '/measure/deleteMeasureById',// 跳转到 action
		data : {
			"id":id
		},
		type : "post",
		success : function(result) {
			if(result.code=="000000"){
				document.getElementById("measureId").value="";
				$("#searchMeasure").trigger("click");
				$("#closedelect").trigger("click");
			}else{
				document.getElementById("dtext").innerHTML=result.error;
			}
		}
	});
}
/**
 * 清空删除提示
 */
function clearDText(){
	document.getElementById("dtext").innerHTML="";
}

/**
 * 清空条件
 */
function resetOne(){
	document.getElementById("oCode").value="";
	document.getElementById("oName").value="";
//	document.getElementById("oType").value="";
}
/**
 * 多量纲查询
 */
function measureMainSearch(){
	$.ajax({
		url : CONTEXTPATH + '/measure/findMeasureMain',// 跳转到 action
		data : {
		},
		type : "post",
		success : function(result) {
			if(result.code!="000000"){
				alert("查询失败");
			}else{
			var data = result.data;
			var hml = "";
			for (var int = 0; int < data.length; int++) {
				var s = int +1 ;
				hml +="<tr><td >"+s+"</td>"+
				"<td>"+data[int].measureCode+"</td>"+
				"<td>"+data[int].measureMain+"</td>"+
				"<td>"+data[int].conversion+"</td>"+
				//data-toggle='modal' data-target='#add_multi'moreSelectById
				"<td ><span><a data-toggle='modal' onclick=\"moreSelectById('"+data[int].id+"')\" data-target='#update_multi' >【修改】</a></span>"+
				"<span onclick=\"delectMeasure('"+data[int].id+"')\"><a data-toggle='modal' "+
				" data-target='#dele'>【删除】</a></span>"+
				"</td></tr>";
			}
			document.getElementById("innHml").innerHTML = hml;
			}
		}
	});
}
/**
 * 添加量纲间换算
 */
function saveMeasureMain(){
	var measureType = $("#m1type").val();
	var measureMain = $("#m2type").val();
	var conversion = $("#mconversion").val();
	$.ajax({
		url : CONTEXTPATH + '/measure/measureTypeConver',// 跳转到 action
		data : {
			"measureType":measureType,
			"measureMain":measureMain,
			"conversion":conversion
		},
		type : "post",
		success : function(result) {
			if(result.code=="000000"){
				measureMainSearch();
			}else{
				alert(result.error);
			}
		}
	});
}

/**
 * 通过id查询
 */
function selectById(id){
	$.ajax({
		url : CONTEXTPATH + '/measure/findMeasureById',// 跳转到 action
		data : {
			"id":id
		},
		type : "post",
		success : function(result) {
			if(result.code!="000000"){
				alert(result.error);
			}else{
				var data = result.data;
				document.getElementById("update_id").value=data.id;
				document.getElementById("update_code").value=data.measureCode;
				document.getElementById("update_cname").value=data.measureName;
				$("#update_type").empty();
				$("#update_type").append($("<option></option>").attr("value",data.measureType).text(data.measureType));
				$("#update_type").val(data.measureType);
				document.getElementById("update_conversion").value=data.conversion;
				//调用方法查询基本单位
				changeType("update_type");
				
				var base = document.getElementsByName("update_base");
				for(var i=0;i<base.length;i++){
				     if(base[i].value==data.measureBase){
				    	 base[i].checked = 'checked';
				     }
				}
			}
		}
	});
}
/**
 * 修改单量纲换算
 */
function oneMeasureUpdate(){
	var id = $("#update_id").val();
	var measureCode = $("#update_code").val();
	var measureName = $("#update_cname").val();
	var measureType = $("#update_type").val();
	var conversion = $("#update_conversion").val();
	var base = document.getElementsByName("update_base");
	var measureBase = "";
	for(var i=0;i<base.length;i++){
		if(base[i].checked){
			measureBase = base[i].value;
	     }
	}
	
	$.ajax({
		url : CONTEXTPATH + '/measure/updateMeasureBase',// 跳转到 action
		data : {
			"id":id,
			"measureCode":measureCode,
			"measureName":measureName,
			"measureType":measureType,
			"conversion":conversion,
			"measureBase":measureBase
		},
		type : "post",
		success : function(result) {
			if(result.code!="000000"){
				alert(result.error);
			}else{
				$("#searchMeasure").trigger("click");
				$("#closeUpdate").trigger("click");
			}
		}
	});
}
/**
 * 多量纲修改
 */
function moreMeasureUpdate(){
	var id = $("#Upt_id").val();
	var measureCode = $("#m1type_upt").val();
	var measureMain = $("#m2type_upt").val();
	var conversion = $("#mconversion_upt").val();
	
	$.ajax({
		url : CONTEXTPATH + '/measure/updateMeasureBase',// 跳转到 action
		data : {
			"id":id,
			"measureCode":measureCode,
			"conversion":conversion,
			"measureMain":measureMain
		},
		type : "post",
		success : function(result) {
			if(result.code!="000000"){
				alert(result.error);
			}else{
				//alert(result.code);
			}
		}
	});
}

/**
 * 通过id查询
 */
function moreSelectById(id){
	$.ajax({
		url : CONTEXTPATH + '/measure/findMeasureById',// 跳转到 action
		data : {
			"id":id
		},
		type : "post",
		success : function(result) {
			var data = result.data;
			if(result.code!="000000"){
				alert(result.error);
			}else{
				document.getElementById("Upt_id").value=data.id;
				document.getElementById("m1type_upt").value=data.measureCode;
				document.getElementById("m2type_upt").value=data.measureMain;
				document.getElementById("mconversion_upt").value=data.conversion;
				
				changeType("m1type_upt");
				changeType("m2type_upt");
			}
		}
	});
}
/**
 * 计算结果
 */
function countValue(){
	var value = $("#text1").val();
	var code = $("#text2").val();
	var main = $("#text2Type").val();
	
	$.ajax({
		url : CONTEXTPATH + '/measure/reckonMeasureMain',// 跳转到 action
		data : {
			"value":value,
			"measureCode":code,
			"measureMain":main
		},
		type : "post",
		success : function(result) {
			var data = result.data;
			if(result.code!="000000"){
				alert(result.error);
			}else{
				alert(result.data);
			}
		}
	});
}
/**
 * 查询计量单位
 */
function search(){
	//计量单位编码
	var oCode = $("#oCode").val();
	//计量单位名称
	var oName = $("#oName").val();
	//量纲
	var oType = $("#oType option:selected").text();
	$.ajax({
		url : CONTEXTPATH + '/measure/findMeasureBlur',// 跳转到 action
		data : {
			oCode:oCode,
			oName:oName,
			oType:oType
		},
		type : "post",
		success : function(result) {
			if(result.code!="000000"){
				alert(result.error);
			}else{
				var data = result.data;
				var html = "";
				if(data.length > 0){
					for (var i = 0; i < data.length; i++) {
						var s = i +1 ;
						html += "<tr>";
						html += "<td>"+s+"</td>";
						html += "<td>"+data[i].measureCode+"</td>";
						html += "<td>"+data[i].measureName+"</td>";
						html += "<td>"+data[i].measureType+"</td>";
						if(data[i].measureBase == "0"){
							html += "<td>是</td>";
						}else{
							html += "<td><input type='button' onclick=\"changeBase('"+data[i].id+"');\" value='设置'></td>";
						}
						html += "<td>"+data[i].conversion+"</td>";
						html += "<td >";
						html += "<span><a data-target='#update_osin' data-toggle='modal' onclick=\"selectById('"+data[i].id+"')\" >【修改】</a></span>";
						html += "<span><a data-target='#dele' data-toggle='modal' onclick=\"delectMeasure('"+data[i].id+"');\">【删除】</a></span>";
						html += "</td>";
						html += "</tr>";
					}
				}else {
					if(oCode || oName ){
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
			    		html +='<h3>系统暂无可用计量单位！？</h3>';
			    		html +='<p>赶快动动手指加上吧！</p>';
			    		html +='</div>';
			    		html +='</div>';
			    		html +='</td>';
			    	}
				}
			
				document.getElementById("measureList").innerHTML = html;
			}
		}
	});
}

var ownerPage = 1;
var vehicPage = 1;
var pageSize = 10;
/**
 * 初始化查询
 */
function driverSearch(){
	displayData(0);
}
function displayData(pageNo){
	var userName = $("#username").val();
	var cellPhone = $("#cellphone").val();
	var submitdateFor = $("#subtimefor").val();
	var submitdateEnd = $("#subtimeend").val();
	var personalType = $("#personalType").val();//2-个人账户， 1-企业账户 
	var pageSize=$("#pageSize").val();
	if(submitdateFor!=""&& submitdateEnd!=""){
		if(submitdateFor>submitdateEnd){
			alert("请选择正确时间段");
			return;
		}
	}
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/findDriverOwner',
		data:{"userName":userName,
			"cellPhone":cellPhone,
			"submitdateForStr":submitdateFor,
			"submitdateEndStr":submitdateEnd,
			"personalType":personalType,
			"pageNo":(pageNo+1),
			"pageSize":pageSize
		},
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				alert("加载失败");
			}else{
				var hml = "";
				$("#totalRecords").html(ret.data.count);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(ret.data.count == 0) {
			    	$("#totalPages").html(1);  
			    	hml +='<td colspan="12">';
		    		hml +='<div class="ht_none">';
		    		hml +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
		    		hml +='<div>';
		    		hml +='<h3>唉呀！查询不到您要找的内容</h3>';
		    		hml +='<p>换个查询条件试试吧？</p>';
		    		hml +='</div>';
		    		hml +='</div>';
		    		hml +='</td>';
			    }else {
			    	$("#totalPages").html(parseInt((ret.data.count-1)/pageSize+1));  
			    	var data=ret.data.list;
					for (var a = 0; a < data.length; a++) {
						var d = a+1;
						var per = "";
						var username = "";
						var telphone = "";
						if(data[a].companypercheck=='0'){//企业未认证个人用户
							per = "个人用户";
							username = data[a].userName;
							telphone = data[a].telphone;
						}else{//"企业用户"
							per = "企业用户";
							username = data[a].companyName;
							telphone = data[a].contactTel;
						}
						var staus = "";//0失效。1正常
						if(data[a].status=="1"){
							staus = "禁用";
						}
						if(data[a].status=="0"){
							staus = "启用";
						}
						if(username == undefined ){
							username = "";
						}
						var cellPhone = data[a].cellPhone;
						if(data[a].cellPhone == undefined){
							cellPhone = "";
						}
						if(telphone == undefined){
							telphone = "";
						}
						var identityCard = data[a].identityCard;
						if(data[a].companypercheck=='1'){
							identityCard = data[a].companycode
						}else{
							if(data[a].identityCard == undefined){
								identityCard = "";
							}
						}
						var submitDate = data[a].submitDateStr;
						if(data[a].submitDate == undefined){
							submitDate = "";
						}
						var capacount = data[a].capacount;
						var vdcount = data[a].vdcount;
						var mocount = data[a].mocount;
						var ownertype = "";
						if(vdcount != 0){
							ownertype += "自有/";
						}
						if(capacount != 0){
							ownertype += "调用/";
						}
						if(mocount != 0 ){
							ownertype += "委派/";
						}
						ownertype += "运力车主";
						hml += "<tr><td >"+d+"</td>"+
							"<td >"+ownertype+"</td>"+
							"<td >"+cellPhone+" </td>"+
							"<td >"+username+"</td>"+
							"<td >"+telphone+"</td>"+
							"<td >"+identityCard+"</td>"+
							"<td >"+submitDate+"</td><td>";
						if(data[a].capacount != 0 || data[a].vdcount != 0 ){
							hml += "<span><a data-toggle='modal' onclick=\"details('"+data[a].id+"')\" data-target='#detail'>【运力详情】</a></span>";
						}
						if(data[a].mocount != 0){
							hml += "<span><a data-toggle='modal' onclick=\"ownerdatail('"+data[a].id+"')\" data-target='#chengyun'>【承运商详情】</a></span>";
						}
						hml += "<span><a data-toggle='modal' onclick=\"getType('"+data[a].id+"','"+data[a].status+"')\" data-target='#tingyong'>【"+staus+"】</a></span>"+
							//去除删除功能
							//"<span><a data-toggle='modal' onclick=\"deletebyid('"+data[a].id+"')\" data-target='#dele'>删除</a></span>"+
							"</td></tr>";
					}
			    }   
				document.getElementById("innerHml").innerHTML=hml;
				$("#pagination").pagination(ret.data.count, {   
				    callback: pageCallback,   
				    prev_text: '上一页',   
				    next_text: '下一页',   
				    items_per_page:pageSize,   
				    num_display_entries:4,   
				    current_page:pageNo,   
				    num_edge_entries:1, 
				    maxentries: ret.data.count, 
				    link_to:"javascript:void(0)"
				}); 
			}
		}
	});  
}
/**
 * 删除02
 */
function deleteMember(){
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/deleteMemberById',
		data:{"id":$("#memberid").val()
		},
		type:"post",
		async: false,
		success: function(ret) {
			if(ret.code == "000000"){
				driverSearch();
			}
		}
	
	});
}
/**
 * 删除01
 * @param id
 */
function deletebyid(id){
	document.getElementById("memberid").value=id;
}
/**
 * 清空查询
 */
function clearSearch(){
	$("#username").val("");
	$("#cellphone").val("");
	$("#usertype").val("");
	$("#phone").val("");
	$("#status").val("");
	$("#restimefor").val("");
	$("#restimeend").val("");
	$("#subtimefor").val("");
	$("#subtimeend").val("");
	$("#personalType").val("");
	$("#perCheckStatus").val("");
	driverSearch();
}

function getType(id,status){
		var mas="";
	if(status==1){
		mas="确定要停用吗";
	}else{
		mas="确定要启用吗";
	}
	document.getElementById("satus").innerHTML=mas;
	document.getElementById("memid").value=id;
	document.getElementById("statustype").value=status;
}
//修改会员状态
function changeType(){
	 var memid = document.getElementById("memid").value;
	 var statesType = document.getElementById("statustype").value;
	 $.ajax({
			url:CONTEXTPATH+'/AdminMember/userprohibition',
			data:{"id":memid ,"status":statesType},
			type:"post",
			beforeSend: function() {
			},
			success: function(retVal) {
				if(retVal.code!="000000"){
					alert(retVal.error);
				}else{
					window.location=location;
				}
			}
	});
}

function clearDriver(){
	$("#driverSearch").val("");
	$("#ownersearch").val("");
}
/**
 * 司机信息查询
 */
function driverdetails(pageNo,flag){
	//TODO
	var memberid = $("#memberid").val();
	var search = $("#driverSearch").val();
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/findMyDriver',
		data:{"memberid":memberid,
			"search":search,
			"pageNo":pageNo,
			"pageSize":pageSize
			
		},
		type:"post",
		beforeSend: function() {
		},
		success: function(ret) {
			if(ret.code!="000000"){
				alert(ret.error);
			}else{
				innVehicHtml(ret,flag);
			}
		}
	});
}
/**
 * 司机详情
 */
function details(id){
	document.getElementById("memberid").value=id;
	driverdetails(1,0);
	vehicPage = 1;
}
function ownerdatail(id){
	document.getElementById("memberid").value=id;
	vehicOwner(1,0);
	ownerPage = 1;
}
/** 承运商详情*/
function vehicOwner(pageNo,flag){
	//TODO
	var ownerid = $("#memberid").val();
	var search = $("#ownersearch").val();
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/vehicOwner',
		data:{"memberId":ownerid ,
			"search":search,
			"pageNo":pageNo,
			"pageSize":pageSize
			},
		type:"post",
		beforeSend: function() {
		},
		success: function(retVal) {
			if(retVal.code!="000000"){
				alert(retVal.error);
			}else{
				innOwnerHtml(retVal,flag);
			}
		}
	});
}

function chenyunMore(){
	ownerPage += 1;
	vehicOwner(ownerPage,1);
}
function vheicMore(){
	vehicPage += 1;
	driverdetails(vehicPage,1);
}
function showOrHide(page,total){
	if(page * pageSize >= total){
		$(".goods_more").hide();
	}else{
		$(".goods_more").show();
	}
}
/** 我的承运商*/
function innOwnerHtml(ret,flag){
	if(flag == 0){
		$("#ownerlist").empty();
	}
	var da = ret.data.list;
	var total = ret.data.total;
	ownerPage = ret.data.pageNo;
	showOrHide(ownerPage,total);
	if(total == 0){
		return ;
	}
	var hml = "";
	for (var a = 0; a < da.length; a++) {
		s = ((ownerPage-1) * pageSize) + a + 1; 
		hml += "<tr><td>"+s+"</td>"+
		"<td>"+da[a].ownerName+"</td>"+
		"<td>"+da[a].ownerTel+"</td>"+
		"</tr>";
	}
	$("#ownerlist").append(hml);
}
/** 我的运力*/
function innVehicHtml(ret,flag){
	if(flag == 0){
		$("#drivlist").empty();
	}
	var hml = "";
	var data = ret.data.list;
	var total = ret.data.total;
	var vehicPage = ret.data.pageNo;
	showOrHide(vehicPage,total);
	for (var a = 0; a < data.length; a++) {
		var s = ((vehicPage-1) * pageSize) + a + 1; 
		var vehiPrefix = data[a].vehicleprefix;
		if(data[a].vehicleprefix == undefined){
			vehiPrefix = "";
		}
		var vehiNo = data[a].vehicleno;
		if(data[a].vehicleno == undefined){
			vehiNo = "";
		}
		var vehiTypeName = data[a].vehicletype;
		if(data[a].vehicletype == undefined){
			vehiTypeName = "";
		}
		var username = data[a].username;
		if(data[a].username == undefined){
			username = "";
		}
		var telphone = data[a].telphone;
		if(data[a].telphone == undefined){
			telphone = "";
		}
		var driverName = data[a].drivername;
		if(data[a].drivername == undefined){
			driverName = "未绑定司机";
		}
		var driverTel = data[a].drivertel;
		if(data[a].drivertel == undefined){
			driverTel = "";
		}
		var identityCard = data[a].identityCard;
		if(data[a].identityCard == undefined){
			identityCard = "";
		}
		var status = data[a].status;
		if(status == -1){
			status = "自有车辆";
		}else {
			status = "调用车辆";
		}
		hml += "<tr><td>"+s+"</td>"+
			"<td>"+vehiPrefix+vehiNo+"</td>"+
			"<td>"+vehiTypeName+"</td>"+
			"<td>"+status+"</td>"+
			"<td>"+username+"</td>"+
			"<td>"+telphone+"</td>"+
			"<td>"+driverName+"</td>"+
			"<td>"+driverTel+"</td>"+
			"</tr>";
	}
	$("#drivlist").append(hml);
}

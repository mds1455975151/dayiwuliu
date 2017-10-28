
var list;
/**
 * 初始化查询
 */
function driverSearch(){
	displayRect(0);
}

function displayData(pageNo){
	var page = $("#recPageNo").val();
	if(page != ""){
		displayRect(page-1);
		$("#recPageNo").val("");
	}else{
		displayRect(pageNo);
	}
}
function displayRect(pageNo){
	var telphone =$("#telphone").val();
	var auditName =$("#auditName").val();
	var starttime =$("#starttime").val();
	var endtime =$("#endtime").val();
	var aldriverid =$("#aldriverid").val();
	var userName = $("#username").val();
	var cellPhone = $("#cellphone").val();
	var status = $("#status").val();
	var perCheckStatus = $("#perCheckStatus").val(); 
	var registtimeFor = $("#restimefor").val();
	var registtimeEnd = $("#restimeend").val();
	var submitdateFor = $("#subtimefor").val();
	var submitdateEnd = $("#subtimeend").val();
	var ncStatus = $("#ncStatus").val();
	var pageSize=$("#pageSize").val();
	if(registtimeFor != '' && registtimeEnd != ''){
		if(registtimeFor>registtimeEnd){
			alert("请选择合适注册时间段");
			return;
		}
	}
	if(submitdateFor != '' && submitdateEnd != ''){
		if(submitdateFor > submitdateEnd){
			alert("请选择合适认证时间段");
			return;
		}
	}
	
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/findDriverMember',
		data:{"userName":$.trim(userName),
			"cellPhone":$.trim(cellPhone),
			"status":status,
			"registtimeForStr":registtimeFor,
			"registtimeEndStr":registtimeEnd,
			"submitdateForStr":submitdateFor,
			"driverpercheck":perCheckStatus,
			"submitdateEndStr":submitdateEnd,
			"ncStatus":ncStatus,
			"auditName":auditName,
			"starttime":starttime,
			"endtime":endtime,
			"aldriverid":aldriverid,
			"telphone":telphone,
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
					list = ret.data.list;
					for (var a = 0; a < data.length; a++) {
						var d = a+1;
						var per = "";
						if(data[a].driverpercheck=='0'){
							per = "未认证";
						}
						if(data[a].driverpercheck=='1'){
							per = "认证通过";
						}
						if(data[a].driverpercheck=='2'){
							per = "认证中";
						}
						if(data[a].driverpercheck=='3'){
							per = "认证失败";
						}
						var staus = "";//0失效。1正常
						if(data[a].status=="1"){
							staus = "禁用";
						}
						if(data[a].status=="0"){
							staus = "启用";
						}
						var userName = data[a].userName;
						var telphone = data[a].telphone;
						if(data[a].driverpercheck=="2" || data[a].driverpercheck=="3"){
							userName = data[a].remarkname;
						}
						if(userName == undefined){
							userName = "";
						}
						if(telphone == undefined){
							telphone = "";
						}
						var identityCard = data[a].identityCard;
						if(data[a].identityCard == undefined){
							identityCard = "";
						}
						var submitDate = data[a].submitDateStr;
						if(data[a].submitDate == undefined){
							submitDate = "";
						}
						
						var auditName = data[a].auditName;
						if(data[a].auditName == undefined){
							auditName = "";
						}
						var auditTimeStr = data[a].auditTimeStr;
						if(data[a].auditTimeStr == undefined){
							auditTimeStr = "";
						}
						var anlian = "";
						if(data[a].driverpercheck=='1'){
							anlian = "<span><a data-toggle='modal' onclick=\"anlianrenzheng('"+a+"')\" data-target='#anlian'>【安联认证】</a></span>";
						}
						var aldriverid = data[a].aldriverid == undefined ? anlian:data[a].aldriverid;
						if(aldriverid == "1"){
							aldriverid = "<span><a onclick=\"alert('请前台用户绑定车辆')\">【认证成功】</a></span>";
						}
						var pushStatus = data[a].pushStatus;
						var ncStatus = data[a].ncStatus;
						hml += "<tr><td >"+d+"</td>"+
							"<td >"+data[a].cellPhone+"</td>"+
							"<td >"+aldriverid+"</td>"+
							"<td >"+userName+" </td>"+
							"<td >"+telphone+"</td>"+
							"<td >"+identityCard+"</td>"+
							"<td>";
							if(per=='认证失败'){
								hml += "<span><a data-toggle='modal' onclick=\"yuanyin('"+data[a].id+"','"+(per)+"','"+data[a].submitDate+"')\" data-target='#yuanyin'>"+per+"</a></span>";
							}else{
								hml += "<span><a >" + per + "</a></span>";
							}
							hml += "<span><a ></a></span>"+
							"<td >"+(pushStatus == 0 ? '未推送' : pushStatus == 1 ? '推送中' : pushStatus == 2 ? '已推送' : '')+"</td>"+
							"<td >"+(ncStatus == 1 ? '供应商不存在' : ncStatus == 2 ? '未审核' : ncStatus == 3 ? '审核未通过' : ncStatus == 4 ? '审核中' : ncStatus == 5 ? '审核通过，但组织未分配' : ncStatus == 6 ? '审核通过，且组织已分配' : '')+"</td>"+
							"<td >"+auditName+"</td>"+
							"<td >"+auditTimeStr+"</td>"+
							"<td >"+data[a].registtimeStr+"</td>"+
							"<td >"+submitDate+"</td><td>";
							if(data[a].driverpercheck=="1"){
								hml += "<span><a data-toggle='modal' onclick=\"details('"+a+"')\" data-target='#detail'>【详情】</a></span>";
							}
							if(data[a].driverpercheck=="2"){
								hml += "<span><a onclick=\"driverShenhe('"+data[a].id+"','"+data[a].driverpercheck+"','"+(pageNo+1)+"')\">【审核】</a></span>";
							}
							if((data[a].driverpercheck == '1') && data[a].pushStatus == 0 && (data[a].ncStatus == '0' || data[a].ncStatus == '1' || data[a].ncStatus == '3')){
								hml += "<span><a onclick=\"push('"+data[a].id+"','"+(pageNo)+"')\">【推送】</a></span>";
							}
							hml += "<span><a data-toggle='modal' onclick=\"getType('"+data[a].id+"','"+data[a].status+"','"+(pageNo)+"')\" data-target='#tingyong'>【"+staus+"】</a></span>"+
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
//推送NC
function push(id,pageNo){
	if (confirm('是否推送到NC？')) {
		var index = layer.load(2, {
			time: 1000*10,
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		$.ajax({
			url:CONTEXTPATH+'/AdminMember/pushNc',
			data:{id: id},
			type:"post",
			async: true,
			success: function(result) {
				if(result.code == "000000"){
					alert("推送成功！");
					displayRect(parseInt(pageNo));
				}else{
					alert(result.error);
				}
				layer.close(index);
			}
		
		});
	}
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
	$("#status").val("");
	$("#restimefor").val("");
	$("#restimeend").val("");
	$("#subtimefor").val("");
	$("#subtimeend").val("");
	$("#perCheckStatus").val("");
	$("#ncStatus").val("");
	$("#telphone").val("");
	$("#auditName").val("");
	$("#aldriverid").val("");
	$("#starttime").val("");
	$("#endtime").val("");
	driverSearch();
}
/**
 * 详情
 * @param id
 */
function details(id){
		var a=list[id];
		var per = "";
		if(a.driverpercheck=='0'){
			per = "未认证";
		}
		if(a.driverpercheck=='1'){
			per = "认证通过";
		}
		if(a.driverpercheck=='2'){
			per = "认证中";
		}
		if(a.driverpercheck=='3'){
			per = "认证失败";
		}
		var userName = a.userName;
		if(a.userName == undefined){
			userName = "";
		}
		var telphone = a.telphone;
		if(a.telphone == undefined){
			telphone = "";
		}
		var identityCard = a.identityCard;
		if(a.identityCard == undefined){
			identityCard = "";
		}
		var sex = a.sex == undefined ? "":a.sex;
		var birthday = a.birthday == undefined ? "":a.birthday;
		var firstlicens = a.firstlicens == undefined ? "":a.firstlicens;
		var licenceorg = a.licenceorg == undefined ? "":a.licenceorg;
		var starttime = a.starttime == undefined ? "":a.starttime;
		var usefullife = a.usefullife == undefined ? "":a.usefullife;
		var idcardaddress = a.idcardaddress == undefined ? "":a.idcardaddress;
		if(sex == "xx"){
			sex = "女";
		}else if(sex == "xy"){
			sex = "男";
		}
		var driveImagePath = a.driveImagePath == undefined?"未上传":("<span><a href='/imageView/index?imageUrl="+a.driveImagePath+"' target='_blank'>查看图片</a>");
		var idcard_image_A = a.positive == undefined?"未上传":("<span><a href='/imageView/index?imageUrl="+a.positive+"' target='_blank'>查看图片</a>");
		var idcard_image_B = a.opposite == undefined?"未上传":("<span><a href='/imageView/index?imageUrl="+a.opposite+"' target='_blank'>查看图片</a>");
		var licenseType = a.licenseType == undefined ? "":a.licenseType;
		var hml = "<div class='file_detail'><label>司机账号：</label><span>"+a.cellPhone+"</span></div>"+
			"<div class='file_detail'><label>司机姓名：</label><span>"+userName+"</span></div>"+
			
			"<div class='file_detail'><label>司机性别：</label><span>"+sex+"</span></div>"+
			"<div class='file_detail'><label>出生日期：</label><span>"+birthday+"</span></div>"+
			"<div class='file_detail'><label>身份证地址：</label><span>"+idcardaddress+"</span></div>"+
			"<div class='file_detail'><label>初次领证日期：</label><span>"+firstlicens+"</span></div>"+
			"<div class='file_detail'><label>发证机关：</label><span>"+licenceorg+"</span></div>"+
			"<div class='file_detail'><label>有效年限：</label><span>"+usefullife+"</span></div>"+
			"<div class='file_detail3'><label>有效起始日期：</label><span>"+starttime+"</span></div>"+
			
			"<div class='file_detail'><label>联系方式：</label><span>"+telphone+"</span></div>"+
			"<div class='file_detail'><label>驾驶证号：</label><span>"+identityCard+"</span></div>"+
			"<div class='file_detail'><label>准驾车型：</label><span>"+licenseType+"</span></div>"+
			"<div class='file_detail'><label>档案状态：</label><span>"+per+"</span></div>"+
			"<div class='file_detail'><label>注册时间：</label><span>"+a.registtimeStr+"</span></div>"+
			"<div class='file_detail'><label>认证时间：</label><span>"+a.submitDateStr+"</span></div>"+
			"<div class='file_detail2'><label>驾驶证照片：</label><span>"+driveImagePath+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+a.id+"\",\"2\")' data-target='#againPice'>【重新上传】</a></span></div>"+
			"<div class='file_detail2'><label>身份证正面：</label><span>"+idcard_image_A+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+a.id+"\",\"5\")' data-target='#againPice'>【重新上传】</a></span></div>"+
			"<div class='file_detail2'><label>身份证反面：</label><span>"+idcard_image_B+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+a.id+"\",\"6\")' data-target='#againPice'>【重新上传】</a></span></div>";
		document.getElementById("detailid").innerHTML = hml;	
}

/**
 * 审核失败原因
 * @param id
 * @param per
 * @param submitDate
 */
function yuanyin(id,per,submitDate){
	$("#errorMassage").empty();
		$.ajax({
			type:"post",
			url:"/AdminMember/findReason",
			data:{"id":id,"submitDate":submitDate},
			success: function(rs) {
				if(rs.code=="000000"){
					var per = "";
					if(rs.data.driverpercheck=='0'){
						per = "未认证";
					}
					if(rs.data.driverpercheck=='1'){
						per = "认证通过";
					}
					if(rs.data.driverpercheck=='2'){
						per = "认证中";
					}
					if(rs.data.driverpercheck=='3'){
						per = "认证失败";
					}
					var telphone = rs.data.telphone;//联系人电话
					if(rs.data.telphone == undefined){
						telphone = "";
					}
					var idcard = rs.data.idcard;
					if(rs.data.idcard == undefined){
						idcard = "";
					}
					var idcardimage = rs.data.idcardimage;
					var sex = rs.data.sex == undefined ? "":rs.data.sex;
					var birthday = rs.data.birthday == undefined ? "":rs.data.birthday;
					if(sex == "xx"){
						sex = "女";
					}else if(sex == "xy"){
						sex = "男";
					}
					var username = rs.data.username==undefined ? "":rs.data.username;
					var auditresson = rs.data.auditresson==undefined ? "":rs.data.auditresson;
					var rtblno =rs.data.rtblno==undefined?"":rs.data.rtblno;
					var auditname =rs.data.auditname==undefined?"":rs.data.auditname;
					var submittime =rs.data.submittime==undefined?"":rs.data.submittime;idcardaddress
					
					var cellphone =rs.data.cellphone==undefined?"":rs.data.submittime;cellphone
					var firstlicens=rs.data.firstlicens==undefined?"":rs.data.firstlicens;
					var idcardaddress =rs.data.idcardaddress==undefined?"":rs.data.idcardaddress;
					var licenseType =rs.data.licenseType==undefined?"":rs.data.licenseType;
					var licenceorg=rs.data.licenceorg==undefined?"":rs.data.licenceorg;
					var usefullife = rs.data.usefullife==undefined?"":rs.data.usefullife;
					var registtime = rs.data.registtime==undefined?"":rs.data.registtime;
					var rtblimgurl = rs.data.rtblimgurl == ""?"未上传":("<a href='/imageView/index?imageUrl="+rs.data.rtblimgurl+"' target='_blank'>查看图片</a>");
					var positive = rs.data.positive == ""?"未上传":("<a href='/imageView/index?imageUrl="+rs.data.positive+"' target='_blank'>查看图片</a>");
					var opposite = rs.data.opposite == ""?"未上传":("<a href='/imageView/index?imageUrl="+rs.data.opposite+"' target='_blank'>查看图片</a>");
					if(registtime!=""){
						var registtime=new Date(registtime).toLocaleDateString().replace(/\//g, "-"); 
					}
//					var idcardimage = "身份证号："+idcard+"--<a href='/imageView/index?imageUrl="+rs.data.idcardimage+"' target='_blank'>查看图片</a>";
//					var rtblimgurl = "证书编号："+rtblno+"--<a href='/imageView/index?imageUrl="+rs.data.rtblimgurl+"' target='_blank'>查看图片</a>";
					
					
					var hml = "<div class='file_detail'><label>审核人名称：</label><span>"+auditname+"</span></div>"+
					"<div class='file_detail'><label>性别：</label><span>"+sex+"</span></div>"+
					
					"<div class='file_detail'><label>出生日期：</label><span>"+birthday+"</span></div>"+
					
					"<div class='file_detail'><label>联系方式：</label><span>"+telphone+"</span></div>"+
					
					"<div class='file_detail'><label>用户名称：</label><span>"+username+"</span></div>"+
					"<div class='file_detail'><label>失败原因：</label><span>"+auditresson+"</span></div>"+
					
					"<div class='file_detail'><label>会员状态：</label><span>"+per+"</span></div>"+
					"<div class='file_detail'><label>身份证号：</label><span>"+idcard+"</span></div>"+
					"<div class='file_detail'><label>司机账号：</label><span>"+cellphone+"</span></div>"+
					"<div class='file_detail'><label>身份证地址：</label><span>"+idcardaddress+"</span></div>"+
					"<div class='file_detail'><label>有效年限：</label><span>"+usefullife+"</span></div>"+
					"<div class='file_detail'><label>发证机关：</label><span>"+licenceorg+"</span></div>"+
					"<div class='file_detail'><label>初次领证日期：</label><span>"+firstlicens+"</span></div>"+
					"<div class='file_detail'><label>准驾车型：</label><span>"+licenseType+"</span></div>"+
					"<div class='file_detail'><label>注册日期：</label><span>"+registtime+"</span></div>"+
					"<div class='file_detail3'><label>运输经营许可证号码：</label><span>"+rtblno+"</span></div>"+
					"<div class='file_detail3'><label>运输经营许可证图片：</label><span>"+rtblimgurl+"</span></div>"+
					"<div class='file_detail3'><label>身份证照正面：</label><span>"+positive+"</span></div>"+
					"<div class='file_detail3'><label>身份证照反面：</label><span>"+opposite+"</span></div>";
					document.getElementById("errorMassage").innerHTML = hml;
					}else{
						$("#yuanyin").hide();
					}
//				}else{
//					alert(rs.error);
				}
		});
	
}

function anlianrenzheng(id){
	var a=list[id];
	var per = "";
	if(a.driverpercheck=='0'){
		per = "未认证";
	}
	if(a.driverpercheck=='1'){
		per = "认证通过";
	}
	if(a.driverpercheck=='2'){
		per = "认证中";
	}
	if(a.driverpercheck=='3'){
		per = "认证失败";
	}
	$("#anlian_id").val(a.id);
	$("#anlian_phone").val(a.cellPhone);
	$("#anlian_licenseType").val(a.licenseType);
	
}

function hideWindow(id,type){
	if(type == "4"){
		$("#showcode").show();
	}else{
		$("#showcode").hide();
	}
	$("#uptmemberid").val(id);
	$("#uptmembertype").val(type);
//	$("#detail").hide();
} 


function uploadfile(){
	if($("#uptmembertype").val()=="4"&&$.trim($("#uptmembercode").val())==""){
		alert("编号不能为空");
		return;
	}
	imgUrl();
	var file = $("#imgUrl").val();
//	var file = $("#file_yyzz")[0].files[0];
	var formData = new FormData();
	formData.append("id",$("#uptmemberid").val());
	formData.append("file",file);
	formData.append("type",$("#uptmembertype").val());
	formData.append("code",$("#uptmembercode").val());
	$.ajax({
		type:"post",
		url:"/AdminMember/uptMemberPic",
		data:formData,// 你的formid
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		success: function(rs) {
			if(rs.code=="000000"){
				window.location=location;
			}else{
				alert(rs.error);
			}
		}
	});
}
  

/**
 * 验证账号正确性
 */
function selectPhone(){
	var cellphone = $("#ckphone").val();
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/findMember',
		data:{"cellPhone":cellphone
			},
		type:"post",
		success: function(ret) {
			var data = ret.data;
			if(data.length!=1){
				document.getElementById("retphone").innerHTML="司机账号有误";
			}
		}
	});  
}

function getType(id,status,pageNo){
		var mas="";
	if(status==1){
		mas="确定要停用吗";
	}else{
		mas="确定要启用吗";
	}
	document.getElementById("satus").innerHTML=mas;
	document.getElementById("memid").value=id;
	document.getElementById("statustype").value=status;
	var pageNo = $("#pageNo").val(pageNo);
}
//修改会员状态
function changeType(){
	 var memid = document.getElementById("memid").value;
	 var statesType = document.getElementById("statustype").value;
	 var pageNo = $("#pageNo").val();
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
					displayRect(parseInt(pageNo))
				}
			}
	});
}
/**
 * 审核
 * @param id
 * @param per
 */
function driverShenhe(id,stat,pageNo){
	var mendId = $("#menuId").val();
	if(stat!=2){
  		alert("该用户暂无认证信息");
  		return;
  	}
	window.location.href=CONTEXTPATH+"/AdminMember/driverShenhe?menuId="+mendId+"&id="+id+"&pageNo="+pageNo;
}

/***
 * 后台司机安联认证
 */
$(".anlian_renzheng").on("click",function(){
	//1900-2099
	var regexp = /^([1][9][0-9][0-9]|[2][0][0-9][0-9])(\-)([0][1-9]|[1][0-2])(\-)([0-2][0-9]|[3][0-1])$/;
	
	var anlian_birthday = $("#anlian_birthday").val();
	if(!regexp.test(anlian_birthday)){
		alert("出生日期格式不正确");
		return;
	}
	var anlian_address = $("#anlian_address").val();
	if(anlian_address==""){
		alert("身份证地址不能为空");
		return;
	}
	var anlian_firstlicens = $("#anlian_firstlicens").val();
	if(!regexp.test(anlian_firstlicens)){
		alert("初次领证日期格式不正确");
		return;
	}
	var anlian_licenceorg = $("#anlian_licenceorg").val();
	if(anlian_licenceorg==""){
		alert("发证机关不能为空");
		return;
	}
	var anlian_usefullife = $("#anlian_usefullife").val();
	if(anlian_usefullife==""){
		alert("有效年限不能为空");
		return;
	}
	var anlian_starttime = $("#anlian_starttime").val();
	if(!regexp.test(anlian_starttime)){
		alert("有效起始日期格式不正确");
		return;
	}
	var anlian_licenseType = $("#anlian_licenseType").val();
	if(anlian_licenseType==""){
		alert("准驾车型不能为空");
		return;
	}
	$(this).attr("disabled",true);
	$.ajax({
			url:CONTEXTPATH+"/admin/driverTicket/upt",
			data:$('#anlian_form').serialize(),
			type:"post",
			success: function(retVal) {
				if(retVal.code!="000000"){
					alert(retVal.error);
					$(".anlian_renzheng").attr("disabled",false);
				}else{
					driverSearch();
					anlianclean();
					$(".anlian_renzheng").attr("disabled",false);
					$("#alhide").click();
				}
			}
	});
});

function anlianclean(){
	$("#anlian_birthday").val("");
	$("#anlian_address").val("");
	$("#anlian_firstlicens").val("");
	$("#anlian_licenceorg").val("");
	$("#anlian_usefullife").val("");
	$("#anlian_starttime").val("");
	$("#anlian_licenseType").val("");
}
function empty(){
	$("#submitDate").val("");
	$("#auditname").val("");
	$("#userType").val("");
	$("#birthday").val("");
	$("#sex").val("");
	$("#telphone").val("");
	$("#per").val("");
	$("#registtime").val("");
	$("#username").val("");
	$("#auditresson").val("");
	$("#status").val("");
	$("#idcard").val("");
	$("#userpercheck").val("");
	$("#rtblno").val("");
}

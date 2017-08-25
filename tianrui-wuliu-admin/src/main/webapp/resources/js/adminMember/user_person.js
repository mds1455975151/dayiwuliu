var list;
function searchSubmit(){
	displayRect(0);
}

function displayData(pageNo){
	var page = $("#recPageNo").val();
	if(page == ""){
		displayRect(pageNo);
	}else{
		displayRect(page-1);
		$("#recPageNo").val("")
	}
}

function displayRect(pageNo){
	var menuId = $("#menuId").val();
	var userName = $("#userName").val();//用户名称
	var cellPhone = $("#cellPhone").val();//登陆账号
	var personalType = $("#personalType").val();//用户类型 个人or企业
	var status = $("#status").val();
	var perCheckStatus = $("#perCheckStatus").val();//认证状态 
	var ncStatus = $("#ncStatus").val();
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/findMemberList',
		data:{"userName":$.trim(userName),
			"companyName":$.trim(userName),
			"cellPhone":$.trim(cellPhone),
			"status":$.trim(status),
			"personalType":$.trim(personalType),
			"userpercheck":$.trim(perCheckStatus),
			"companypercheck":$.trim(perCheckStatus),
			"ncStatus":ncStatus,
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
					list=ret.data.list;
					for (var a = 0; a < data.length; a++) {
						var d = a+1;
						var per = "";
						if(data[a].companypercheck=='0'||data[a].userpercheck=='0'){
							per = "未认证";
						}
						if(data[a].companypercheck=='1'||data[a].userpercheck=='1'){
							per = "认证通过";
						}
						if(data[a].companypercheck=='2'||data[a].userpercheck=='2'){
							per = "认证中";
						}
						if(data[a].companypercheck=='3'||data[a].userpercheck=='3'){
							per = "认证失败";
						}
						//操作展示
						var staus = "";//0失效。1正常
						//页面展示
						var s = "";
						if(data[a].status=="1"){
							staus = "禁用";
							s = "正常";
						}
						if(data[a].status=="0"){
							staus = "启用";
							s = "失效";
						}
						var userName = "";
						var telphone = "";
						if(data[a].companypercheck=='2' || data[a].userpercheck=="2" || data[a].companypercheck=='3' || data[a].userpercheck=="3" ){
							userName = data[a].remarkname;
						}else if(data[a].companypercheck=='0'){//企业未认证个人用户
							userName = data[a].userName;
							telphone = data[a].telphone;
						}else{//"个人用户"
							userName = data[a].companyName;
							telphone = data[a].contactTel;
						}
						if(userName == undefined){
							userName = "";
						}
						var cellPhone = data[a].cellPhone;
						if(data[a].cellPhone == undefined){
							cellPhone = "";
						}
						var registtime = data[a].registtimeStr;
						if(data[a].registtimeStr == undefined){
							registtime = "";
						}
						var submitDate = data[a].submitDateStr;
						if(data[a].submitDateStr == undefined){
							submitDate = "";
						}
						var lastTime = data[a].lastTimeStr;
						if(data[a].lastTimeStr == undefined){
							lastTime = "";
						}
						
						var auditName = data[a].auditName;
						if(data[a].auditName == undefined){
							auditName = "";
						}
						var auditTimeStr = data[a].auditTimeStr;
						if(data[a].auditTimeStr == undefined){
							auditTimeStr = "";
						}
						var pushStatus = data[a].pushStatus;
						var ncStatus = data[a].ncStatus;
						hml += "<tr>" +
							"<td >"+userName+" </td>"+
							"<td >"+cellPhone+"</td>"+
							"<td >"+registtime+"</td>"+
							"<td >"+submitDate+"</td>"+
							"<td >"+s+"</td>"+
							"<td>";
							if(data[a].companypercheck=='3'||data[a].userpercheck=='3'){
								hml += "<span><a data-toggle='modal' onclick=\"yuanyin('"+data[a].id+"','"+(per)+"','"+data[a].submitDate+"')\" data-target='#yuanyin'>"+per+"</a></span>";
							}else{
								hml += "<span><a >" + per + "</a></span>";
							}
							hml += "<span><a ></a></span>"+
							"<td >"+(pushStatus == 0 ? '未推送' : pushStatus == 1 ? '推送中' : pushStatus == 2 ? '已推送' : '')+"</td>"+
							"<td >"+(ncStatus == 1 ? '供应商不存在' : ncStatus == 2 ? '未审核' : ncStatus == 3 ? '审核未通过' : ncStatus == 4 ? '审核中' : ncStatus == 5 ? '审核通过，但组织未分配' : ncStatus == 6 ? '审核通过，且组织已分配' : '')+"</td>"+
							"<td>";
							if(data[a].companypercheck=='1'||data[a].userpercheck=='1'){
								hml += "<span><a data-toggle='modal' onclick=\"details('"+a+"')\" data-target='#detail'>【详情】</a></span>";
							}
							if(data[a].companypercheck=='2'||data[a].userpercheck=='2'){
								hml += "<span><a onclick=\"shenHe('"+data[a].id+"','2','"+menuId+"','"+(pageNo+1)+"')\">【审核】</a></span>";
							}
							if((data[a].companypercheck=='1'||data[a].userpercheck=='1') && data[a].pushStatus == 0 && (data[a].ncStatus == '0' || data[a].ncStatus == '1' || data[a].ncStatus == '3')){
								hml += "<span><a onclick=\"push('"+data[a].id+"','"+(pageNo)+"')\">【推送】</a></span>";
							}
							hml += "<span><a data-toggle='modal' onclick=\"getType('"+data[a].id+"','"+data[a].status+"')\" data-target='#qiyong'>【"+staus+"】</a></span>"+
							//去除删除功能
							//"<span><a data-toggle='modal' onclick=\"deletebyid('"+data[a].id+"')\" data-target='#dele'>删除</a></span></td>" +
							"<td >"+auditName+"</td>" +
							"<td >"+auditTimeStr+"</td>" +
							"<td >"+lastTime+"</td>" +
							"</tr>";
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
				searchSubmit();
			}
		}
	
	});
}
/**
 * 删除
 * @param id
 */
function deletebyid(id){
	document.getElementById("memberid").value=id;
}
//获取会员状态
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
  //会员审核
  function shenHe(id,stat,mendId,pageNo){
		  if(stat!=2){
	  		alert("该用户暂无认证信息");
		return;
	}
	window.location.href=CONTEXTPATH+"/AdminMember/userShenhe?menuId="+mendId+"&id="+id+"&pageNo="+pageNo;
  }
  
  //清空数据
  function memberReset(){
	document.getElementById("cellPhone").value="";
	document.getElementById("userName").value="";
	document.getElementById("status").value="";
	document.getElementById("personalType").value="";
	document.getElementById("perCheckStatus").value="";
	document.getElementById("ncStatus").value="";
	displayRect(0);
  }
  /**
   * 详情
   * @param id
   */
  function details(id){
		var a=list[id]
		var per = "";
		if(a.companypercheck=='0'||a.userpercheck=='0'){
			per = "未认证";
		}
		if(a.companypercheck=='1'||a.userpercheck=='1'){
			per = "认证通过";
		}
		if(a.companypercheck=='2'||a.userpercheck=='2'){
			per = "认证中";
		}
		if(a.companypercheck=='3'||a.userpercheck=='3'){
			per = "认证失败";
		}
		var cellPhone = a.cellPhone;
		if(a.cellPhone == undefined){
			cellPhone = "";
		}
		var userName = a.userName;
		if(a.userName == undefined){
			userName = "";
		}
		var companyContact = a.companyContact;//公司联系人
		if(a.companyContact == undefined){
			companyContact = "";
		}
		var companyName = a.companyName;//公司名称
		if(a.companyName == undefined){
			companyName = "";
		}
		var contactTel = a.contactTel;//联系人电话
		if(a.contactTel == undefined){
			contactTel = "";
		}
		var telphone = a.telphone;
		if(a.telphone == undefined){
			telphone = "";
		}
		var registtime = a.registtimeStr;
		if(a.registtime == undefined){
			registtime = "";
		}
		var submitDate = a.submitDateStr;
		if(a.submitDate == undefined){
			submitDate = "";
		}
		var identityCard = a.identityCard;
		if(a.identityCard == undefined){
			identityCard = "";
		}
		var idcardsImagePath = a.idcardsImagePath;
		var userType = "";
		var pictype = "";
		//企业用户展示企业相应信息
		if(a.companypercheck=='1'){
			userType = "企业用户";
			userName = companyContact;
			telphone = contactTel;
			identityCard = a.companycode;
			idcardsImagePath = a.licenseImagePath;
			pictype = "3"
		}else if(a.userpercheck=='1'){
			userType = "个人用户";
			pictype = "1"
		}else{
			userType = "暂无";
		}
		
		var sex = a.sex == undefined ? "":a.sex;
		var birthday = a.birthday == undefined ? "":a.birthday;
		if(sex == "xx"){
			sex = "女";
		}else if(sex == "xy"){
			sex = "男";
		}
		
		var rtblimgurl = a.rtblimgurl == ""?"未上传":("证书编号："+a.rtblno+"--<a href='/imageView/index?imageUrl="+a.rtblimgurl+"' target='_blank'>查看图片</a>");
		var idcardsImagePath = idcardsImagePath == ""?"未上传":("<span><a href='/imageView/index?imageUrl="+idcardsImagePath+"' target='_blank'>查看图片</a>");
		var hml = "<div class='file_detail'><label>会员类别：</label><span>"+userType+"</span></div>"+
			"<div class='file_detail'><label>会员账号：</label><span>"+cellPhone+"</span></div>"+
			"<div class='file_detail'><label>会员名称：</label><span>"+userName+"</span></div>"+
			"<div class='file_detail'><label>性别：</label><span>"+sex+"</span></div>"+
			"<div class='file_detail'><label>出生日期：</label><span>"+birthday+"</span></div>"+
			"<div class='file_detail'><label>联系方式：</label><span>"+telphone+"</span></div>"+
			"<div class='file_detail'><label>会员状态：</label><span>"+per+"</span></div>"+
			"<div class='file_detail'><label>注册时间：</label><span>"+registtime+"</span></div>"+
			"<div class='file_detail'><label>认证时间：</label><span>"+submitDate+"</span></div>"+
			"<div class='file_detail3'><label>道路运输经营许可证：</label><span>"+rtblimgurl+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+a.id+"\",\"4\")' data-target='#againPice'>【重新上传】</a></span></div>"+
			"<div class='file_detail3'><label>营业执照/身份证号：</label><span>"+identityCard+"</span></div>"+
			"<div class='file_detail3'><label>营业执照/身份证照片：</label><span>"+idcardsImagePath+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+a.id+"\",\""+pictype+"\")' data-target='#againPice'>【重新上传】</a></span></div>";
		document.getElementById("detailid").innerHTML = hml;	
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
  
function yuanyin(id,per,submitDate){
	$("#errorMassage").empty();
	if(per=="认证失败"){
		$.ajax({
			type:"post",
			url:"/AdminMember/findReason",
			data:{"id":id,"submitDate":submitDate},
			success: function(rs) {
				if(rs.code=="000000"){
					
					var per =rs.data.per;
					if(rs.data.companypercheck=='0'||rs.data.userpercheck=='0'){
						per = "未认证";
					}
					if(rs.data.companypercheck=='1'||rs.data.userpercheck=='1'){
						per = "认证通过";
					}
					if(rs.data.companypercheck=='2'||rs.data.userpercheck=='2'){
						per = "认证中";
					}
					if(rs.data.companypercheck=='3'||rs.data.userpercheck=='3'){
						per = "认证失败";
					}
					if(per=="认证失败"){
						
					var companyContact = rs.data.companyContact;//公司联系人
					if(rs.data.companyContact == undefined){
						companyContact = "";
					}
					var companyName = rs.data.companyName;//公司名称
					if(rs.data.companyName == undefined){
						companyName = "";
					}
					var contactTel = rs.data.contactTel;//联系人电话
					if(rs.data.contactTel == undefined){
						contactTel = "";
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
					var userType = "";
					var pictype = "";
					//企业用户展示企业相应信息
					if(rs.data.companypercheck=='1'){
						userType = "企业用户";
						userName = companyContact;
						telphone = contactTel;
						idcard = rs.data.companycode;
						idcardimage =rs.data.licenseImagePath;
						pictype = "3"
					}else if(rs.data.userpercheck=='1'){
						userType = "个人用户";
						pictype = "1"
					}else if(rs.data.companypercheck=='3'){
						userType = "企业用户";
						pictype = "3"
					}else if(rs.data.userpercheck=='3'){
						userType = "个人用户";
						pictype = "1"
					}else {
						userType = "暂无";
					}
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
					var submittime =rs.data.submittime==undefined?"":rs.data.submittime;
					var idcardimage = rs.data.idcardimage == ""?"未上传":("<a href='/imageView/index?imageUrl="+rs.data.idcardimage+"' target='_blank'>查看图片</a>");
					var rtblimgurl = rs.data.rtblimgurl == ""?"未上传":("<a href='/imageView/index?imageUrl="+rs.data.rtblimgurl+"' target='_blank'>查看图片</a>");
					
//					var idcardimage = "身份证号："+idcard+"--<a href='/imageView/index?imageUrl="+rs.data.idcardimage+"' target='_blank'>查看图片</a>";
//					var rtblimgurl = "证书编号："+rtblno+"--<a href='/imageView/index?imageUrl="+rs.data.rtblimgurl+"' target='_blank'>查看图片</a>";
					
					
					var hml = "<div class='file_detail'><label>审核人名称：</label><span>"+auditname+"</span></div>"+
					"<div class='file_detail'><label>性别：</label><span>"+sex+"</span></div>"+
					
					"<div class='file_detail'><label>会员类别：</label><span>"+userType+"</span></div>"+
					"<div class='file_detail'><label>出生日期：</label><span>"+birthday+"</span></div>"+
					
					"<div class='file_detail'><label>联系方式：</label><span>"+telphone+"</span></div>"+
					"<div class='file_detail'><label>用户名称：</label><span>"+username+"</span></div>"+
					"<div class='file_detail'><label>失败原因：</label><span>"+auditresson+"</span></div>"+
					
					"<div class='file_detail'><label>会员状态：</label><span>"+per+"</span></div>"+
					"<div class='file_detail'><label>身份证号：</label><span>"+idcard+"</span></div>"+
					
					"<div class='file_detail3'><label>道路运输经营许可证：</label><span>"+rtblno+"</span></div>"+
					
					"<div class='file_detail3'><label>营业执照/身份证照：</label><span>"+idcardimage+"</span></div>";
					"<div class='file_detail3'><label>道路运输经营许可证：</label><span>"+rtblimgurl+"</span></div>";
					document.getElementById("errorMassage").innerHTML = hml;
					}else{
						$("#yuanyin").hide();
					}
//				}else{
//					alert(rs.error);
				}
			}
		});
	}
	
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
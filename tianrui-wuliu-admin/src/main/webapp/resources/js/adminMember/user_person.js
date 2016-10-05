var list;
function searchSubmit(){
	displayData(0);
}
function displayData(pageNo){
	var menuId = $("#menuId").val();
	var userName = $("#userName").val();//用户名称
	var cellPhone = $("#cellPhone").val();//登陆账号
	var personalType = $("#personalType").val();//用户类型 个人or企业
	var status = $("#status").val();
	var perCheckStatus = $("#perCheckStatus").val();//认证状态 
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+'/AdminMember/findMemberList',
		data:{"userName":userName,
			"companyName":userName,
			"cellPhone":cellPhone,
			"status":status,
			"personalType":personalType,
			"userpercheck":perCheckStatus,
			"companypercheck":perCheckStatus,
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
						hml += "<tr>" +
							"<td >"+userName+" </td>"+
							"<td >"+cellPhone+"</td>"+
							"<td >"+registtime+"</td>"+
							"<td >"+submitDate+"</td>"+
							"<td >"+s+"</td>"+
							"<td >"+per+"</td>"+
							"<td>";
							if(data[a].companypercheck=='1'||data[a].userpercheck=='1'){
								hml += "<span><a data-toggle='modal' onclick=\"details('"+a+"')\" data-target='#detail'>【详情】</a></span>";
							}
							if(data[a].companypercheck=='2'||data[a].userpercheck=='2'){
								hml += "<span><a onclick=\"shenHe('"+data[a].id+"','2','"+menuId+"')\">【审核】</a></span>";
							}
							hml += "<span><a data-toggle='modal' onclick=\"getType('"+data[a].id+"','"+data[a].status+"')\" data-target='#qiyong'>【"+staus+"】</a></span>"+
							//去除删除功能
							//"<span><a data-toggle='modal' onclick=\"deletebyid('"+data[a].id+"')\" data-target='#dele'>删除</a></span></td>" +
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
  function shenHe(id,stat,mendId){
		  if(stat!=2){
	  		alert("该用户暂无认证信息");
		return;
	}
	window.location.href=CONTEXTPATH+"/AdminMember/userShenhe?menuId="+mendId+"&id="+id;
  }
  
  //清空数据
  function memberReset(){
	document.getElementById("cellPhone").value="";
	document.getElementById("userName").value="";
	document.getElementById("status").value="";
	document.getElementById("personalType").value="";
	document.getElementById("perCheckStatus").value="";
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
		//企业用户展示企业相应信息
		if(a.companypercheck=='1'){
			userType = "企业用户";
			userName = companyContact;
			telphone = contactTel;
			identityCard = a.companycode;
			idcardsImagePath = a.licenseImagePath;
		}else if(a.userpercheck=='1'){
			userType = "个人用户";
		}else{
			userType = "暂无";
		}
		var hml = "<div class='file_detail'><label>会员类别：</label><span>"+userType+"</span></div>"+
			"<div class='file_detail'><label>会员账号：</label><span>"+cellPhone+"</span></div>"+
			"<div class='file_detail'><label>会员名称：</label><span>"+userName+"</span></div>"+
			"<div class='file_detail'><label>联系方式：</label><span>"+telphone+"</span></div>"+
			"<div class='file_detail'><label>会员状态：</label><span>"+per+"</span></div>"+
			"<div class='file_detail'><label>注册时间：</label><span>"+registtime+"</span></div>"+
			"<div class='file_detail'><label>认证时间：</label><span>"+submitDate+"</span></div><br>"+
			"<div class='file_detail3'><label>营业执照号/身份证号：</label><span>"+identityCard+"</span></div>"+
			"<div class='file_detail3'><label>营业执照/身份证照片：</label><span><a href='"+idcardsImagePath+"' target='_blank'><img width='180' src='"+idcardsImagePath+"'></a></span></div>";
		document.getElementById("detailid").innerHTML = hml;	
  }

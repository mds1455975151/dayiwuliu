
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
	var userName = $("#username").val();
	var cellPhone = $("#cellphone").val();
	var status = $("#status").val();
	var perCheckStatus = $("#perCheckStatus").val(); 
	var registtimeFor = $("#restimefor").val();
	var registtimeEnd = $("#restimeend").val();
	var submitdateFor = $("#subtimefor").val();
	var submitdateEnd = $("#subtimeend").val();
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
						hml += "<tr><td >"+d+"</td>"+
							"<td >"+data[a].cellPhone+"</td>"+
							"<td >"+userName+" </td>"+
							"<td >"+telphone+"</td>"+
							"<td >"+identityCard+"</td>"+
							"<td >"+per+"</td>"+
							"<td >"+data[a].registtimeStr+"</td>"+
							"<td >"+submitDate+"</td><td>";
							if(data[a].driverpercheck=="1"){
								hml += "<span><a data-toggle='modal' onclick=\"details('"+a+"')\" data-target='#detail'>【详情】</a></span>";
							}
							if(data[a].driverpercheck=="2"){
								hml += "<span><a onclick=\"driverShenhe('"+data[a].id+"','"+data[a].driverpercheck+"','"+(pageNo+1)+"')\">【审核】</a></span>";
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
	$("#status").val("");
	$("#restimefor").val("");
	$("#restimeend").val("");
	$("#subtimefor").val("");
	$("#subtimeend").val("");
	$("#perCheckStatus").val("");
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
		var driveImagePath = a.driveImagePath == ""?"未上传":("<span><a href='/imageView/index?imageUrl="+a.driveImagePath+"' target='_blank'>查看图片</a>");
		var licenseType = a.licenseType == undefined ? "":a.licenseType;
		var hml = "<div class='file_detail'><label>司机账号：</label><span>"+a.cellPhone+"</span></div>"+
			"<div class='file_detail'><label>司机姓名：</label><span>"+userName+"</span></div>"+
			
			"<div class='file_detail'><label>司机性别：</label><span>"+sex+"</span></div>"+
			"<div class='file_detail'><label>出生日期：</label><span>"+birthday+"</span></div>"+
			"<div class='file_detail'><label>身份证地址：</label><span>"+idcardaddress+"</span></div>"+
			"<div class='file_detail'><label>初次领证日期：</label><span>"+firstlicens+"</span></div>"+
			"<div class='file_detail'><label>发证机关：</label><span>"+licenceorg+"</span></div>"+
			"<div class='file_detail'><label>有效年限：</label><span>"+usefullife+"</span></div>"+
			"<div class='file_detail3'><label>驾驶证注册日期：</label><span>"+starttime+"</span></div>"+
			
			"<div class='file_detail'><label>联系方式：</label><span>"+telphone+"</span></div>"+
			"<div class='file_detail'><label>驾驶证号：</label><span>"+identityCard+"</span></div>"+
			"<div class='file_detail'><label>准驾车型：</label><span>"+licenseType+"</span></div>"+
			"<div class='file_detail'><label>档案状态：</label><span>"+per+"</span></div>"+
			"<div class='file_detail'><label>注册时间：</label><span>"+a.registtimeStr+"</span></div>"+
			"<div class='file_detail'><label>认证时间：</label><span>"+a.submitDateStr+"</span></div>"+
			"<div class='file_detail2'><label>驾驶证照片：</label><span>"+driveImagePath+"<a data-toggle='modal' class='hidemodel' onclick='hideWindow(\""+a.id+"\",\"2\")' data-target='#againPice'>【重新上传】</a></span></div>";
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
	$("#detail").hide();
} 


function uploadfile(){
	if($("#uptmembertype").val()=="4"&&$.trim($("#uptmembercode").val())==""){
		alert("编号不能为空");
		return;
	}
	var file = $("#file_yyzz")[0].files[0];
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

/**
 * 查询车辆详情
 * @param id
 */
function details(id){
	$.ajax({
		url:'/AdminMember/findCarManagerById',
		data:{"id":id},
		type:"post",
		success: function(ret){
			var d = ret.data;
			var userName = d.userName;
			if(d.userName == undefined){
				userName = "";
			}
			var telphone = d.telphone;
			if(d.telphone == undefined){
				telphone = "";
			}
			var sta = "";
			if(d.status=="-1"){
				sta = "认证失败";
			}
			if(d.status=="0"){
				sta = "未认证";
			}
			if(d.status=="1"){
				sta = "认证成功";
			}
			if(d.status=="2"){
				sta = "认证中";
			}
			var type = "";
			if(d.vehicletypename!=undefined){
				type = d.vehicletypename;
			}
			var registcode = d.registimage==""?"<span>未上传</span>":("<span><a href='/imageView/index?imageUrl="+d.registimage+"' target='_blank'>查看图片</a></span>");
			var opercode = d.operimage==""?"<span>未上传</span>":("<span>证书编号："+d.opercode+"--<a href='/imageView/index?imageUrl="+d.operimage+"' target='_blank'>查看图片</a></span>");
			var roadtransport = d.roadtransportimage==""?"<span>未上传</span>":("<span>证书编号："+d.roadtransportcode+"--<a href='/imageView/index?imageUrl="+d.roadtransportimage+"' target='_blank'>查看图片</a></span>");
			var roadtransportcode = d.roadtransportcode==""?"未上传":d.roadtransportcode;
			var identitycode = d.identieyimage==""?"<span>未上传</span>":("<span>证书编号："+d.identitycode+"--<a href='/imageView/index?imageUrl="+d.identieyimage+"' target='_blank'>查看图片</a></span>");
			var desc3 = "";
			if(d.desc3 != undefined){
				desc3 = d.desc3;
			}
			var vehiwidth = "";
			if(d.vehiwidth != undefined){
				vehiwidth = d.vehiwidth;
			}
			var vehiheight = "";
			if(d.vehiheight != undefined){
				vehiheight = d.vehiheight;
			}
			var hml = 
				"<div class='file_detail'><label>车牌号前缀：</label><span>"+d.vehicleprefix+"</span></div>"+
				"<div class='file_detail'><label>车牌号：</label><span>"+d.vehicleno+"</span></div>"+
				"<div class='file_detail'><label>所有人姓名：</label><span>"+userName+"</span></div>"+
				"<div class='file_detail'><label>联系方式：</label><span>"+telphone+"</span></div>"+
				"<div class='file_detail'><label>车型：</label><span>"+type+"</span></div>"+
				"<div class='file_detail'><label>载重：</label><span>"+d.vehiweight+"吨</span></div>"+
				"<div class='file_detail'><label>长度：</label><span>"+d.vehilength+"米</span></div>"+
				"<div class='file_detail'><label>宽度：</label><span>"+vehiwidth+"米</span></div>"+
				"<div class='file_detail'><label>高度：</label><span>"+vehiheight+"米</span></div>"+
				"<div class='file_detail'><label>认证状态：</label><span>"+sta+"</span></div>"+
				"<div class='file_detail'><label>认证时间：</label><span>"+d.createtimeStr+"</span></div>"+
				"<div class='file_detail'><label>行驶证有效期：</label><span>"+(d.drivingTime||"")+"</span></div>"+
				"<div class='file_detail2'><label>车辆登记证：</label>"+registcode+"</div>"+
				"<div class='file_detail2'><label>经营许可证号：</label>"+opercode+"</div>"+
				"<div class='file_detail2'><label>经营许可证有效期：</label>"+desc3+"</div>"+
				"<div class='file_detail2'><label>道路运输证号：</label>"+roadtransportcode+"</div>"+
				"<div class='file_detail2'><label>车辆照片：</label><span><a href='/imageView/index?imageUrl="+d.vehiheadimgpath+"' target='_blank'>查看图片</a></span></div>"+
				"<div class='file_detail2'><label>行驶证照片：</label><span><a href='/imageView/index?imageUrl="+d.vehilicenseimgpath+"' target='_blank'>查看图片</a></span></div>";
			document.getElementById("detailid").innerHTML = hml;
		}
	});
}


/**
 * 司机详情
 * @param cellPhone
 */
function sjdetails(cellPhone){
	$.ajax({
		url:'/AdminMember/findDriverMember',
		data:{"cellPhone":cellPhone},
		type:"post",
		success: function(ret){
			var a=ret.data.list[0];
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
				"<div class='file_detail2'><label>驾驶证照片：</label><span>"+driveImagePath+"</span></div>"+
				"<div class='file_detail2'><label>身份证正面：</label><span>"+idcard_image_A+"</span></div>"+
				"<div class='file_detail2'><label>身份证反面：</label><span>"+idcard_image_B+"</span></div>";
			document.getElementById("sjdetails").innerHTML = hml;	
		}
	});
}
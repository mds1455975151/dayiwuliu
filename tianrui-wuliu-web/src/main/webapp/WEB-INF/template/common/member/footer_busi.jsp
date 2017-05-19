<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--底部begin-->
<div class="bgblack">
    <div class="container">
            <div class="foot">
                <div class="foot_logo">
                        <img src="${trRoot}/tianrui/images/logobig.png">
                </div>
                <div class="foot_line mt20">
                	<img title="货主android" width="130" height="130" src="${trRoot}/tianrui/images/huozhu.png">
                </div>
                <div class="foot_line mt20">
                   	 <img title="android" width="130" height="130" src="${trRoot}/tianrui/images/erandroid.png">
                </div>
                <div class="foot_er mt20">
                    <img title="IOS" width="130" height="130" src="${trRoot}/tianrui/images/erios.png">
                </div>
            </div>
        </div>
</div>
<!--底部end-->
     
<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>

<script>
var PATH = "${path}";
var CONTEXTPATH="${path}";
var memberId = "${user.id}";
var member_id="${user.id}";
var loginName = "${user.realName}";
var cellPhone = "${user.cellphone}";
var userpercheck = "${user.userpercheck}";
var driverpercheck = "${user.driverpercheck}";
var companypercheck = "${user.companypercheck}";
var memberInfoId = "${user.id}";
var idCard = "${user.idcard}";
var imagesRoot ="${imagesRoot}";
var _userName="${user.userName}";
var _compantName="${user.companyName}";
var rsd = "${session_member.driverpercheck}";
var trImgRoot = "${trRoot}/tianrui/images/";
window.alert =function(msg,title){
	$("#modal_common_content").html(msg);
	$("#modal_common_title").html(title||"确认");
	$("#commonModal").modal();
}
window.confirm =function(msg,title,commitCallback,cancelCallBack){
	$("#modal_confirm_title").html(msg);
	$("#modal_confirm_content").html(title||"确认");
	if(commitCallback){
		$("#confirmModal").off("click",".confirmsubmitbtn").on("click",".confirmsubmitbtn",function(){
			$("#confirmModal").modal("hide");
			commitCallback();
		});
	}
	if(cancelCallBack){
		$("#confirmModal").off("click",".cancelsubmitbtn").on("click",".cancelsubmitbtn",function(){
			$("#confirmModal").modal("hide");
			cancelCallBack();
		});
	}
	
	$("#confirmModal").modal();
}

//long类型装dataStr-----new Date(data[a].createtime).format("yyyy-MM-dd hh:mm:ss")
Date.prototype.format = function(f){
    var o ={
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(f))f=f.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(f))f = f.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));return f
}
</script>
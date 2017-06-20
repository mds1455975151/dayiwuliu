<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--底部begin-->
<style type="text/css">
	.bottom_btn {
			font-size: 14px;
		    background: #466bf8;
		    color: #ffffff;
		    padding: 8px 20px;
		}
</style>
<div class="bgblack innfoot">
    <div class="wrapinn foot">
        <div class="footcont">
            <img src="${trRoot}/tianrui/images/footlogo2.png">
            <div class="footline">
                <ul>
                    <li><a href="/count/route">首页</a></li>
                    <li><a href="/count/aboutUs">关于我们</a></li>
                    <li><a href="/count/contactUs">联系我们</a></li>
                </ul>
            </div>
            <div class="footline">
                <p>Copyright © 2016 豫ICP备16036132号-1</p>
                <p>中原大易物流科技有限公司版权所有</p>
            </div>
              <div class="footxin">
                <a href="/zs" target="_blank">
                    <img src="${trRoot}/tianrui/images/xin.png">
                </a>
            </div>
        </div>
       	<div class="footmid">
	        <div class="footimg">
	            <img  src="${trRoot}/tianrui/images/phone_load.png">
	            <a href="/publicMember/loadPage"><h4>大易物流客户端APP</h4></a>
	        </div>
        </div>
        <div class="foot400">
            <h3>全国服务热线</h3>
            <h4>400-056-1156</h4>
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
window.alert =function(msg,title,callback){
	$("#modal_common_content").html(msg);
	$("#modal_common_title").html(title||"确认");
	if(callback){
		$("#commonModal").off("click",".closeBtn").on("click",".closeBtn",function(){
			$("#commonModal").modal("hide");
			callback();
		});
	}else{
		$("#commonModal").off("click",".closeBtn").on("click",".closeBtn",function(){
			$("#commonModal").modal("hide");
		});
	}
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
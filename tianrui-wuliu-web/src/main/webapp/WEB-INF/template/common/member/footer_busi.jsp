<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--底部begin-->
<div class="bgblack">
    <div class="container">
            <div class="foot">
                <div class="foot_logo">
                        <img src="${trRoot}/tianrui/images/logobig.png">
                </div>
                    <div class="foot_line mt20">
                        <h3>友情链接</h3>
                        <ul>
                            <li>>天瑞集团水泥责任有限公司</li>
                            <li>>天瑞集团水泥</li>
                            <li>>天瑞集团水泥</li>
                            <li>>天瑞集团水泥</li>
                        </ul>
                    </div>
                <div class="foot_line mt20">
                    <h3>友情链接</h3>
                    <ul>
                        <li>>天瑞集团水泥责任有限公司</li>
                        <li>>天瑞集团水泥</li>
                        <li>>天瑞集团水泥</li>
                        <li>>天瑞集团水泥</li>
                    </ul>
                </div>
                <div class="foot_line mt20">
                    <h3>友情链接</h3>
                    <ul>
                        <li>>天瑞集团水泥责任有限公司</li>
                        <li>>天瑞集团水泥</li>
                        <li>>天瑞集团水泥</li>
                        <li>>天瑞集团水泥</li>
                    </ul>
                </div>
                <div class="foot_er mt20">
                    <img title="IOS" width="130" height="130" src="${trRoot}/tianrui/images/erios.png">
                    <img title="android" width="130" height="130" src="${trRoot}/tianrui/images/erandroid.png">
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
</script>
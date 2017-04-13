<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <title>注册用户</title>
    <link href="${trRoot }/weixin/css/base.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/layer.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/swiper.css" rel='stylesheet' type='text/css'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=no">
</head>
<body>
<c:if test="${memberInfo.driverpercheck ne '0' }">
	<div class="car_dangan">
	    <div class="carda_line">
	        <label>司机账号：</label>
	        <span>${memberInfo.cellphone }</span>
	    </div>
	    <div class="carda_line">
	        <label>司机姓名：</label>
	        <span>${memberInfo.username }</span>
	    </div>
	    <div class="carda_line">
	        <label>联系方式：</label>
	        <span>${memberInfo.telphone }</span>
	    </div>
	    <div class="carda_line">
	        <label>驾驶证号：</label>
	        <span>${memberInfo.idcard }</span>
	    </div>
	    <div class="carda_line">
	        <label>准驾车型：</label>
	        <span>${memberInfo.licenseType }</span>
	    </div>
	    <div class="carda_line">
	        <c:if test="${memberInfo.sex eq 'xx'}">
            <label>性别：</label><span>女</span><br>
            </c:if>
            <c:if test="${memberInfo.sex eq 'xy'}">
            <label>性别：</label><span>男</span><br>
            </c:if>
	    </div>
	    <div class="carda_line">
	        <label>出生日期：</label>
	        <span>${memberInfo.birthday }</span>
	    </div>
	    <div class="carda_line">
	        <label>初次领证日期：</label>
	        <span>${memberInfo.firstlicens }</span>
	    </div>
	    <div class="carda_line">
	        <label>发证机关：</label>
	        <span>${memberInfo.licenceorg }</span>
	    </div>
	    <div class="carda_line">
	        <label>驾驶证注册日期：</label>
	        <span>${memberInfo.starttime }</span>
	    </div>
	    <div class="carda_line">
	        <label>有效年限：</label>
	        <span>${memberInfo.usefullife }</span>
	    </div>
	    <div class="carda_line">
	        <label>身份证地址：</label>
	        <span>${memberInfo.idcardaddress }</span>
	    </div>
	    <div class="carda_line">
	         <c:if test="${memberInfo.driverpercheck eq '0'}">
             <label>认证状态：</label><span>未认证</span><br>
             </c:if>
             <c:if test="${memberInfo.driverpercheck eq '1'}">
             <label>认证状态：</label><span>认证通过</span><br>
             </c:if>
             <c:if test="${memberInfo.driverpercheck eq '2'}">
             <label>认证状态：</label><span>认证中</span><br>
             </c:if>
             <c:if test="${memberInfo.driverpercheck eq '3'}">
             <label>认证状态：</label><span>认证失败</span><br>
             </c:if>
	    </div>
	    <div class="carda_line">
	        <label>注册时间：</label>
	        <span>${memberInfo.registtimeStr }</span>
	    </div>
	    <div class="carda_line">
	        <label>认证时间：</label>
	        <span>${memberInfo.submittimeStr }</span>
	    </div>
	</div>
	<div class="car_dangan">
	    <div class="carda_line">
	        <label>认证文件</label>
	    </div>
	    <div class="carda_line">
	        <label>驾驶证照片：</label>
	        <span class="colorblue zhengjian" onclick="picView('${memberInfo.driverimage }')">查看图片</span>
	    </div>
	</div>
	<c:if test="${memberInfo.driverpercheck eq '2' }">
		<div class="fixed_btn">
		    <a><button class="pass colorblue">通过</button></a>
		    <a><button class="fail colorred">不通过</button></a>
		</div>
	</c:if>
</c:if>
<input type="hidden" id="infoid" value="${memberInfo.id }">
<input type="hidden" id="memberid" value="${memberInfo.memberid }">
<script src="${trRoot }/weixin/js/jquery-1.11.1.js"></script>
<script src="${trRoot }/weixin/js/layer.js"></script>
<script src="${trRoot }/weixin/js/swiper.jquery.js"></script>
<script>
    //通过按钮
    $(".pass").on("click", function () {
        layer.open({
            content: '您确定要通过审核吗？'
            ,btn: ['取消', '确认']
            ,no: function(index){
            	var mid = $("#infoid").val();
             	var massage = $("#massages").val();
             	var memberid = $("#memberid").val();
             	$.ajax({
            		url:'/AdminMember/driverReview',
            		data:{"id":mid,"driverpercheck": "1","massage":massage},
            		type:"post",
            		success: function(retVal) {
            			if(retVal.code=="000000"){
            				window.location.href="/weixin/page/driverdetail?id="+memberid+"&type=1";
            			}else{
            				alert(retVal.error);
            			}
            		}
            	});  
                layer.close(index);
            }
        });
    });
    //不通过按钮
    $(".fail").on("click", function () {
        layer.open({
            title: [
                '请输入审核不通过原因'
            ],
            content: '<div class="shenhe_alt"><textarea id="massages"  rows="10"></textarea></div>'
            ,btn: ['取消', '确认']
            ,no: function(index){
            	var mid = $("#infoid").val();
             	var massage = $("#massages").val();
             	if(!$.trim(massage)){
            		alert("请输入审核不通过原因");
            		return;
            	}
             	$.ajax({
            		url:'/AdminMember/driverReview',
            		data:{"id":mid,"driverpercheck": "3","massage":massage},
            		type:"post",
            		success: function(retVal) {
            			if(retVal.code=="000000"){
            				window.location.href="/weixin/login/yiShenPage?state=driver";
            			}else{
            				alert(retVal.error);
            			}
            		}
            	});  
                layer.close(index);
            }
        });
    });
    //证件图片
    function picView(url){
    	layer.open({
            content: "<div class='imgview'><img src='"+url+"'></div>"
        });
    }
</script>

</body>
</html>



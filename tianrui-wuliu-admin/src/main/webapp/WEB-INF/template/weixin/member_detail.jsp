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
<!-- 企业认证 -->
<c:if test="${memberInfo.companypercheck ne '0' }">
	<div class="car_dangan">
	    <div class="carda_line">
	        <label>会员类别：</label>
	        <span>企业用户</span>
	    </div>
	    <div class="carda_line">
	        <label>会员账号：</label>
	        <span>${memberInfo.cellphone }</span>
	    </div>
	    <div class="carda_line">
	        <label>联系人：</label>
	        <span>${memberInfo.companycontact }</span>
	    </div>
	    <div class="carda_line">
	        <label>联系人电话：</label>
	        <span>${memberInfo.companytel }</span>
	    </div>
	    <div class="carda_line">
	        <label>公司名称：</label>
	        <span>${memberInfo.companyname }</span>
	    </div>
	    <div class="carda_line">
	        <label>认证状态：</label>
	        <c:if test="${memberInfo.companypercheck eq '0' }">
	        <span>未认证</span>
	        </c:if>
	        <c:if test="${memberInfo.companypercheck eq '1' }">
	        <span>认证通过</span>
	        </c:if>
	        <c:if test="${memberInfo.companypercheck eq '2' }">
	        <span>认证中</span>
	        </c:if>
	        <c:if test="${memberInfo.companypercheck eq '3' }">
	        <span>认证失败</span>
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
	    <div class="carda_line">
	        <label>公司所在地址：</label>
	        <span>${memberInfo.companyAddress }</span>
	    </div>
	</div>
<div class="car_dangan">
    <div class="carda_line">
        <label>认证文件</label>
    </div>
    <div class="carda_line">
        <label>证件照：</label>
        <span class="colorblue zhengjian" onclick="picView('${memberInfo.licenseImagePath }')">查看图片</span>
    </div>
</div>
<c:if test="${memberInfo.companypercheck eq '2' }">
	<div class="fixed_btn">
	    <a><button class="pass colorblue">通过</button></a>
	    <a><button class="fail colorred">不通过</button></a>
	</div>
</c:if>
</c:if>
<!-- 个人用户 -->
<c:if test="${memberInfo.userpercheck ne '0' }">
	<div class="car_dangan">
	    <div class="carda_line">
	        <label>会员类别：</label>
	        <span>个人用户</span>
	    </div>
	    <div class="carda_line">
	        <label>会员账号：</label>
	        <span>${memberInfo.cellphone }</span>
	    </div>
	    <div class="carda_line">
	        <label>会员姓名：</label>
	        <span>${memberInfo.username }</span>
	    </div>
	    <div class="carda_line">
	        <label>联系方式：</label>
	        <span>${memberInfo.telphone }</span>
	    </div>
	    <div class="carda_line">
	        <label>出生日期：</label>
	        <span>${memberInfo.birthday }</span>
	    </div>
	    <div class="carda_line">
	        <label>身份证号：</label>
	        <span>${memberInfo.idcard }</span>
	    </div>
	    <div class="carda_line">
	        <label>认证状态：</label>
	        <c:if test="${memberInfo.userpercheck eq '0' }">
	        <span>未认证</span>
	        </c:if>
	        <c:if test="${memberInfo.userpercheck eq '1' }">
	        <span>认证通过</span>
	        </c:if>
	        <c:if test="${memberInfo.userpercheck eq '2' }">
	        <span>认证中</span>
	        </c:if>
	        <c:if test="${memberInfo.userpercheck eq '3' }">
	        <span>认证失败</span>
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
        <label>证件照：</label>
        <span class="colorblue zhengjian" onclick="picView('${memberInfo.idcardimage }')">查看图片</span>
    </div>
</div>
<c:if test="${memberInfo.userpercheck eq '2' }">
	<div class="fixed_btn">
	    <a><button class="pass colorblue">通过</button></a>
	    <a><button class="fail colorred">不通过</button></a>
	</div>
</c:if>
</c:if>
<input type="hidden" id="infoid" value="${memberInfo.id }">
<input type="hidden" id="companypercheck" value="${memberInfo.companypercheck}">
<input type="hidden" id="userpercheck" value="${memberInfo.userpercheck}">

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
             	var companypercheck = $("#companypercheck").val();
             	var userpercheck = $("#userpercheck").val();
             	
             	var memberid = $("#memberid").val();
             	var url="";
             	if(companypercheck == "2"){
             		url = "/AdminMember/companyReview";
             	}else if(userpercheck == "2"){
             		url = "/AdminMember/userReview";
             	}else{
	             	alert("数据有误请联系管理员");
             	}
            	$.ajax({
            		url:url,
            		data:{"id":mid,"userpercheck": "1","companypercheck":"1","massage":massage},
            		type:"post",
            		success: function(retVal) {
            			if(retVal.code=="000000"){
            				window.location.href="/weixin/page/memberdetail?id="+memberid+"&type=1";
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
             	var companypercheck = $("#companypercheck").val();
             	var userpercheck = $("#userpercheck").val();
             	var memberid = $("#memberid").val();
            	
             	if(!$.trim(massage)){
            		alert("请输入审核不通过原因");
            		return;
            	}
             	
             	var url="";
             	if(companypercheck == "2"){
             		url = "/AdminMember/companyReview";
             	}
             	if(userpercheck == "2"){
             		url = "/AdminMember/userReview";
             	}
            	$.ajax({
            		url:url,
            		data:{"id":mid,"userpercheck": "3","companypercheck":"3","massage":massage},
            		type:"post",
            		success: function(retVal) {
            			if(retVal.code=="000000"){
            				window.location.href="/weixin/login/yiShenPage?state=member";
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



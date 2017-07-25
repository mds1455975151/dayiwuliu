<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <title>银行卡档案</title>
    <link href="${trRoot }/weixin/css/base.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/layer.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/swiper.css" rel='stylesheet' type='text/css'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=no">
</head>
<body>
<div class="car_dangan">
    <div class="carda_line">
        <label>银行卡账户 ：</label>
        <span>${bank.bankcard }</span>
    </div>
    <div class="carda_line">
        <label>持卡人名称：</label>
        <span>${bank.idname }</span>
    </div>
    <div class="carda_line">
        <label>身份证号：</label>
        <span>${bank.idcard }</span>
    </div>
    <div class="carda_line">
        <label>添加账号：</label>
        <span>${bank.telphone }</span>
    </div>
    <div class="carda_line">
        <label>银行名称：</label>
        <span>${bank.bankname}</span>
    </div>
    <div class="carda_line">
        <label>银行编码：</label>
        <span>${bank.bankLineCode }</span>
    </div>
    <div class="carda_line">
        <label>开户行联行号：</label>
        <span>${bank.bankLineNumber }</span>
    </div>
    <div class="carda_line">
        <label>开户行名称：</label>
        <span>${bank.desc1 }</span>
    </div>
    <div class="carda_line">
        <label>认证状态：</label>
        <span> <c:if test="${bank.bankautid eq 3 }">认证失败</c:if>
               <c:if test="${bank.bankautid eq 1 }">认证成功</c:if>
               <c:if test="${bank.bankautid eq 2 }">认证中</c:if></span>
    </div>
    <div class="carda_line">
        <label>认证时间：</label>
        <span>${bank.createtimeStr}
        </span>
    </div>
    <div class="carda_line">
        <label>银行卡照片：</label>
        <span class="colorblue zhengjian" onclick="picView('${bank.bankimg }')">查看图片</span>
    </div>
</div>
<c:if test="${bank.bankautid eq 2 }">
	<div class="fixed_btn">
	    <a><button class="pass colorblue">通过</button></a>
	    <a><button class="fail colorred">不通过</button></a>
	</div>
</c:if>
<input type="hidden" id="bankid" value="${bank.id }">
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
            	 $.ajax({
            			url: '/admin/bank/card/autid',
            			data:{"id":$("#bankid").val(),
            					"bankautid":"1"
            			},
            			type:"post",
            			success: function(ret){
            				if(ret.code=="000000"){
				               window.location.reload();
            				}else{
            					alert(ret.error);
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
            content: '<div class="shenhe_alt"><textarea id="massage"  rows="10"></textarea></div>'
            ,btn: ['取消', '确认']
            ,no: function(index){
            	var massage = $("#massage").val();
            	if(!$.trim(massage)){
            		alert("请输入审核不通过原因");
            		return;
            	}
            	$.ajax({
        			url: '/admin/bank/card/autid',
        			data:{"id":$("#bankid").val(),
        					"bankautid":"3",
        					"auditMassage":$("#massage").val()
        			},
        			type:"post",
        			success: function(ret){
        				if(ret.code=="000000"){
			               window.location.reload();
        				}else{
        					alert(ret.error);
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



<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <title>开票车辆档案</title>
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
        <label>车牌号：</label>
        <span>${tick.desc1 }</span>
    </div>
    <div class="carda_line">
        <label>使用性质：</label>
       	<c:if test="${tick.nature eq '1' }">营运</c:if>
       	<c:if test="${tick.nature eq '2' }">非营运</c:if>
    </div>
    <div class="carda_line">
        <label>所有人：</label>
        <span>${tick.owner }</span>
    </div>
    <div class="carda_line">
        <label>身份证号：</label>
        <span>${tick.idcard }</span>
    </div>
    <div class="carda_line">
        <label>总质量：</label>
        <span>${tick.quality }</span>
    </div>
    <div class="carda_line">
        <label>登记证书编号：</label>
        <span>${tick.certificateno }</span>
    </div>
    <div class="carda_line">
        <label>检验有效期：</label>
        <span>${tick.expirydata }</span>
    </div>
    <div class="carda_line">
        <label>车辆识别码：</label>
        <span>${tick.identification }</span>
    </div>
    <div class="carda_line">
        <label>发动机号：</label>
        <span>${tick.motor }</span>
    </div>
    <div class="carda_line">
        <label>发动机型号：</label>
        <span>${tick.motorno }</span>
    </div>
    <div class="carda_line">
        <label>认证状态：</label>
        <c:if test="${tick.status eq '-1'}"><span>认证失败</span></c:if>
        <c:if test="${tick.status eq '0'}"><span>未认证</span></c:if>
        <c:if test="${tick.status eq '1'}"><span>认证成功</span></c:if>
        <c:if test="${tick.status eq '2'}"><span>认证中</span></c:if>
    </div>
</div>
<c:if test="${tick.status eq '2'}">
<div class="fixed_btn">
    <a><button class="pass colorblue">通过</button></a>
    <a><button class="fail colorred">不通过</button></a>
</div>
</c:if>
<input type="hidden" id="ticket_id" value="${tick.id }">
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
            		url:'/admin/ticket/shenhe',
            		data:{"id":$("#ticket_id").val(),
            			"status":'1'
            		},
            		type:"post",
            		success: function(ret) {
            			if(ret.code!="000000"){
            				alert(ret.error);
            			}else{
            				window.location.reload();
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
            content: '您确定审核不通过吗？'
            ,btn: ['取消', '确认']
            ,no: function(index){
            	$.ajax({
            		url:'/admin/ticket/shenhe',
            		data:{"id":$("#ticket_id").val(),
            			"status":'-1'
            		},
            		type:"post",
            		success: function(ret) {
            			if(ret.code!="000000"){
            				alert(ret.error);
            			}else{
            				window.location.reload();
            			}
            		}
            	});
                layer.close(index);
            }
        });
    });
    //证件图片
    $(".zhengjian").on("click", function () {
        layer.open({
            content: '<div class="imgview"><img src="images/11.jpg"></div>'
        });
    });
</script>

</body>
</html>



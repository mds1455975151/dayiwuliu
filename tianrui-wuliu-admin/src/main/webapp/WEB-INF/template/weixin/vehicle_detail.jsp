<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <title>车辆档案</title>
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
        <label>车牌号前缀：</label>
        <span>${Vehicle.vehicleprefix }</span>
    </div>
    <div class="carda_line">
        <label>车牌号：</label>
        <span>${Vehicle.vehicleno }</span>
    </div>
    <div class="carda_line">
        <label>所有人姓名：</label>
        <span>${Vehicle.userName }</span>
    </div>
    <div class="carda_line">
        <label>联系方式：</label>
        <span>${Vehicle.telphone }</span>
    </div>
    <div class="carda_line">
        <label>车型：</label>
        <span>${Vehicle.vehicletypename}</span>
    </div>
    <div class="carda_line">
        <label>载重(吨)：</label>
        <span>${Vehicle.vehiweight }</span>
    </div>
    <div class="carda_line">
        <label>长度(米)：</label>
        <span>${Vehicle.vehilength }</span>
    </div>
    <div class="carda_line">
        <label>认证状态：</label>
        <span> <c:if test="${Vehicle.status eq -1 }">认证失败</c:if>
               <c:if test="${Vehicle.status eq 0 }">未认证</c:if>
               <c:if test="${Vehicle.status eq 1 }">认证成功</c:if>
               <c:if test="${Vehicle.status eq 2 }">认证中</c:if></span>
    </div>
    <div class="carda_line">
        <label>认证时间：</label>
        <span>${Vehicle.createtimeStr }</span>
    </div>
    <div class="carda_line">
        <label>道路运输证号：</label>
        <span>${Vehicle.roadtransportcode }</span>
    </div>
    <div class="carda_line">
        <label>经营许可证号：</label>
        <span>${Vehicle.opercode }</span>
    </div>
    <div class="carda_line">
        <label>行驶证照片：</label>
        <span class="colorblue zhengjian" onclick="picView('${Vehicle.vehilicenseimgpath }')">查看图片</span>
    </div>
    <div class="carda_line">
        <label>车辆照片：</label>
        <span class="colorblue zhengjian" onclick="picView('${Vehicle.vehiheadimgpath }')">查看图片</span>
    </div>
    <div class="carda_line">
        <label>经营许可证号：</label>
        <span class="colorblue zhengjian" onclick="picView('${Vehicle.operimage }')">查看图片</span>
    </div>
    <div class="carda_line">
        <label>车辆登记证：</label>
        <span class="colorblue zhengjian" onclick="picView('${Vehicle.registimage }')">查看图片</span>
    </div>
</div>
<c:if test="${Vehicle.status eq 2 }">
	<div class="fixed_btn">
	    <a><button class="pass colorblue">通过</button></a>
	    <a><button class="fail colorred">不通过</button></a>
	</div>
</c:if>
<input type="hidden" id="vehicleId" value="${Vehicle.id }">
<input type="hidden" id="memberId" value="${Vehicle.memberid }">
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
            			url: '/AdminMember/carReviw',
            			data:{"id":$("#vehicleId").val(),
            					"type":"1",
            					"memberid":$("#memberId").val()
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
        			url: '/AdminMember/carReviw',
        			data:{"id":$("#vehicleId").val(),
        					"type":"-1",
        					"massage":$("#massage").val(),
        					"memberid":$("#memberId").val()
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



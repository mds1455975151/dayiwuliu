<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <title>注册用户管理</title>
    <link href="${trRoot }/weixin/css/base.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/iconfont.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/swiper.css" rel='stylesheet' type='text/css'/>
    <meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

</head>
<body>
<div class="searchbox">
    <i class="iconfont icon-sousuo"></i>
    <input placeholder="会员账号" id="searchKey" value="${searchKey }" type="text">
</div>
<!--列表切换选项卡-->
<div class="maple-tab">
    <ul>
        <li onclick="yiShenpage('member1')">已审核</li>
        <li onclick="yiShenpage('member')" class="active">待审核</li>
    </ul>
</div>

<!--列表内容-->
<div class="swiper-container">
    <div class="swiper-wrapper">
        <!-- 未审核 -->
        <div class="swiper-slide" id="rzHTML0">
            
        </div>
    </div>
</div>
</body>
<input type="hidden" id="pageNo" value="0">
<script src="${trRoot }/weixin/js/jquery-1.11.1.js"></script>
<script src="${trRoot }/weixin/js/layer.js"></script>
<script src="${trRoot }/weixin/js/swiper.jquery.js"></script>
<script type="text/javascript" src="/resources/js/weixin/scroll/scroll.js?12"></script>
<script type="text/javascript" src="/resources/js/weixin/member.js?21"></script>
<script type="text/javascript">
function indexPageOf(){
	index0();
}

function pageView(url){
	window.location.href=url;
}
</script>
</html>



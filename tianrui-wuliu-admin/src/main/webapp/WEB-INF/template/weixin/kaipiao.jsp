<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <title>开票车辆档案</title>
    <link href="${trRoot }/weixin/css/base.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/iconfont.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/layer.css" rel='stylesheet' type='text/css'/>
    <link href="${trRoot }/weixin/css/swiper.css" rel='stylesheet' type='text/css'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="searchbox">
    <i class="iconfont icon-sousuo"></i>
    <input placeholder="请输入车牌号/司机/联系方式/000" type="text">
</div>
<!--列表切换选项卡-->
<div class="maple-tab">
    <ul>
        <li onclick="yiShenpage('kaipiao1')" >已审核</li>
        <li onclick="yiShenpage('kaipiao')" class="active">待审核</li>
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
<input type="hidden" id="pageNo" value="0">
<script src="${trRoot }/weixin/js/jquery-1.11.1.js"></script>
<script src="${trRoot }/weixin/js/layer.js"></script>
<script src="${trRoot }/weixin/js/swiper.jquery.js"></script>
<script type="text/javascript" src="/resources/js/weixin/kaipiao.js?11"></script>
<script type="text/javascript" src="/resources/js/weixin/scroll/scroll.js?11"></script>
<script type="text/javascript">
function indexPageOf(){
	index0();
}
</script>
</body>
</html>



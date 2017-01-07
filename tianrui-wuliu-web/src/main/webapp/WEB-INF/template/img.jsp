<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>index</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/easyTree.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="Shortcut Icon" href="${trRoot}/tianrui/css/favicon.ico" type="image/x-icon">
    <link href="${trRoot}/tianrui/css/imgcut.css" rel="stylesheet">

</head>
<body>
<div class="img_prev">
    <img src="${imageUrl }" width="800px" class="img_contr">
    <div class="opera">
        <ul class="opera_inn">
        
        
        
        
        
        
            <li class=" zoom_large">
                <i class="icononline"> &#xe66a;</i>
            </li>
            <li class=" zoom_small">
                <i class="icononline"> &#xe642;</i>
            </li>
            <li class="transf_shunshizhen">
                <i class="icononline"> &#xe77d;</i>
            </li>
            <li class="transf_nishizhen">
                <i class="icononline">&#xe644;</i>
                
            </li>
        </ul>

    </div>
</div>

<script type="text/javascript" src="${scriptsRoot }/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap.js"></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.resizableColumns.js"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot }/wfljs.js"></script>
<script type="text/javascript" src="${scriptsRoot }/cropbox.js"></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.rotate.js"></script>
<script>
    $(window).load(function(){
        // 图片大小控制 不超过屏幕宽高
        var imgsr = $(".img_prev .img_contr");
        var widclient = $(window).width();
        var heightclient = $(window).height();
        var img = new Image;
        img.onload = function(){
            if(img.width >= widclient  && (img.height >= heightclient) ){
                imgsr.css("height", heightclient-1);
            }
            else if(img.width >= widclient  && (img.height < heightclient) ){
                imgsr.css("width", widclient-1);
            }
            else if(img.width < widclient  && (img.height > heightclient) ){
                imgsr.css("height", heightclient-1);
            }
        };
        img.src = "";
        // 图片放大缩小
        var zoom_large = $(".img_prev .opera_inn li.zoom_large");
        zoom_large.on("click", function () {
            imgsr.css("width", imgsr.width()*1.1);
            imgsr.css("height", imgsr.height()*1.1);
        });
        var zoom_small = $(".img_prev .opera_inn li.zoom_small");
        zoom_small.on("click", function () {
            imgsr.css("width", imgsr.width()*0.9);
            imgsr.css("height", imgsr.height()*0.9);
        });
        // 图片顺时针旋转
        var trans_shun = $(".img_prev .opera_inn li.transf_shunshizhen");
        var value = 0;
        trans_shun.rotate({
            bind:
            {
                click: function(){
                    value +=90;
                    imgsr.rotate({ animateTo:value})
                }
            }
        });
        // 图片逆时针旋转
        var trans_ni = $(".img_prev .opera_inn li.transf_nishizhen");
        trans_ni.rotate({
            bind:
            {
                click: function(){
                    value -=90;
                    imgsr.rotate({ animateTo:value})
                }
            }
        });

    })


</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>index</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/dystyle.css" rel="stylesheet">

</head>
<body class="bgtz">
    <div class="wraptz">
        <div class="fl">
            <img src="${trRoot}/tianrui/images/tz.png" class="mt20">
        </div>
        <div class="tz_pc">
            <h2>大易物流客户端</h2>
            <div class="tz_vtotal">
                <h4>车主版APP</h4>
                <div class="tz_vers">
                   	<a href="${android }">
                    <div class="tz_vandroid">
                        <h5 style="color: white;">安卓端下载</h5>
                    </div>
                    <img src="${trRoot}/tianrui/images/erandroid.png">
                   	</a>
                </div>
                <div class="tz_vers">
                    <div class="tz_vios">
                        <h5>IOS端下载</h5>
                    </div>
                    <img src="${trRoot}/tianrui/images/erios.png">
                </div>
            </div>
            <div class="tz_vtotal">
                <h4>货主版APP</h4>
                <div class="tz_vers">
                   	<a href="${huozhu_android }">
                    <div class="tz_vandroid">
                        <h5 style="color: white;">安卓端下载</h5>
                    </div>
                    <img src="${trRoot}/tianrui/images/huozhu.png">
                   	</a>
                </div>
                <div class="tz_vers">
                    <div class="tz_vios">
                        <h5>IOS端下载</h5>
                    </div>
                    <img src="${trRoot}/tianrui/images/huozhu_ios.png">
                </div>
            </div>
        </div>
    </div>

<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script>
    var htotal = $(window).height();
    $(".wraptz").css("min-height",htotal);
</script>

</body>
</html>
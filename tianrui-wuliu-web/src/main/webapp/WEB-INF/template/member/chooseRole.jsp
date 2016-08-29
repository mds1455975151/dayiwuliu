<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我的消息</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<!-- 引用公共header部分 -->
<jsp:include page="../common/member/header_busi.jsp"></jsp:include>
<div class="bghui">
    <div class="container">
        <div class="id_selct">
            <h3>选择进入系统身份</h3>
            <div class="id_selctbody bgwhite">
                <h5>我的身份</h5>
                <div class="id_selbtn">
                    <button id="hzBtn" class="btn btn_cz"><img src="${trRoot}/tianrui/images/hzwhite.png">货主</button>
                    <button id="czBtn" class="btn btn_cz"><img src="${trRoot}/tianrui/images/czwhite.png">车主</button>
                    <button id="sjBtn" class="btn btn_cz"><img src="${trRoot}/tianrui/images/sjwhite.png">司机</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--登录内容部分end-->

<!--底部begin-->
<!-- 引用公共footer部分 -->
<jsp:include page="../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="/resources/js/member/chooseRole.js" ></script>
</body>
</html>
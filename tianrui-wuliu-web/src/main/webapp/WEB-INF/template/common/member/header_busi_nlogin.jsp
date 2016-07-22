<%--
/**
 *用于登录前的界面，作为公共头部使用
 *
 *@author kowuka   
 *@time 2016.05.09
 */
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/font-awesome.css" rel="stylesheet" />
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body onkeydown="keyLogin();">
<!--Header-->
<div class="bghui">
    <div class="container">
        <!--登录头部行begin-->
        <div class="header">
            <div class="header_left">
                <a><i class=" iconfont icon-hert"></i><label>收藏</label></a>
                <a><i class=" iconfont icon-shouji"></i><label>手机版</label></a>
            </div>
            <div class="header_right">
				<label id="header_welcome">欢迎来到天瑞物流 </label>
                <label id="header_loginHref"> <a href="/publicMember/loginPage">请登录</a></label>
                <label id="header_registerHref"> <a href="/publicMember/registerPage">免费注册</a></label>
<!--                 <label> <a href="">我的车队</a></label>
                <label> <a href="">我的运单</a></label>
                <label> <a href="">我的账户</a></label> -->
            </div>
        </div>
        <!--登录头部行end-->
    </div>
</div>
<div class="bgwhite">
    <div class="container">
        <!--导航条begin-->
        <div class="navtotal">
            <div class="login_logo">
                <img src="${trRoot}/tianrui/images/logo2.png">
            </div>
            <div class="mynav">
                <ul>
                    <li id="header_home">
                        <a ><p>Capacity</p><h4>运力管理</h4></a>
                    </li>
          			<li id="header_findgood">
                        <a ><p>Plans </p><h4>计划管理</h4></a>
                    </li>
                    <li id="header_findcar">
                        <a ><p>Waybill</p><h4>运单管理</h4></a>
                    </li>
                    <li id="header_myteam">
                        <a ><p>My kernel</p><h4>账户中心</h4></a>
                    </li> 
                </ul>
            </div>
            <img src="${trRoot}/tianrui/images/tel.png">
        </div>
        <!--导航条end-->
    </div>
</div>
<!--Header-->

<!--公共弹出模态框begin-->
<div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width:400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <input type="hidden" id="modal_del_id">
                <input type="hidden" id="modal_del_flag">
                <input type="hidden" id="modal_del_rowno">
                <h4 class="modal-title" id="modal_del_title">确认信息</h4>
            </div>
            <div class="modal-body">
                <h4 id="modal_common_content"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--公共弹出模态框end-->  

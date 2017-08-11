<%--
/**
 *用于登录后的界面，作为公共头部使用
 *
 *@author kowuka   
 *@time 2016.04.13
 */
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/dystyle.css" rel="stylesheet">
	<link href="/resources/ui/layer/mobile/need/layer.css" rel="stylesheet"/>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!--Header-->
<div class="bghui">
    <div class="container">
        <!--登录头部行begin-->
        <div class="header">
            <div class="header_left">
                <a><i class=" iconfont icon-hert"></i><label>收藏</label></a>
                <a><i class=" iconfont icon-shouji"></i><label>手机版</label></a>
                 <a href="/trwuliu/Member/chooseRole"><i class=" iconfont icon-huiyuan"></i><label>切换角色</label></a>
            </div>
            <div class="header_right">
                <label id="header_welcome">欢迎来到天瑞物流 </label>
                <label id="header_loginHref"> <a href="/publicMember/loginPage">请登录</a></label>
                <label id="header_registerHref"> <a href="/publicMember/registerPage">免费注册</a></label>
                <label id="header_logout"><a href="/trwuliu/Member/logout">注销</a></label>
                <label id="header_uptpassword"><a href="/trwuliu/Member/uptPassPage">修改密码</a></label>
                <!-- 
                <label> <a href="">我的运力</a></label>
                <label> <a href="">我的计划</a></label>
                <label> <a href="">我的运单</a></label>
                 -->
            </div>
        </div>
        <!--登录头部行end-->
    </div>
</div>
<div class="bgblue">
    <div class="container">
        <!--导航条begin-->
        <div class="navreg">
                <img src="${trRoot}/tianrui/images/logoreg.png">
           <!--  <div class="regnav" >
                <ul>
                    <li class="active" id="header_home">
                        <a href=""><p>HOME</p><h4>首页</h4></a>
                    </li>
                    <li id="header_findgood">
                        <a href=""><p>Find Good</p><h4>找货</h4></a>
                    </li>
                    <li id="header_findcar">
                        <a href=""><p>Find Car</p><h4>找车</h4></a>
                    </li>
                    <li id="header_myteam">
                        <a href=""><p>My team</p><h4>专车专线</h4></a>
                    </li>
                </ul>
            </div>
            <div class="regbtn">
                <i class="iconfont icon-huiyuan"></i>
                <a href="/trwuliu/Member/cargo/planGood">
                <span>发布货源</span>
                </a>
            </div> -->
            <c:if test="${not empty role }">
	            <div class="regbtn" id="countMessage" >
	                
	            </div>
            </c:if>
        </div>
        <!--导航条end-->
    </div>
</div>
<!--Header-->

<!--公共弹出模态框begin-->
<div class="modal fade" style="z-index:1055;" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width:400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <input type="hidden" id="modal_del_id">
                <input type="hidden" id="modal_del_flag">
                <input type="hidden" id="modal_del_rowno">
                <h4 class="modal-title" id="modal_del_title" id="modal_common_title">提示信息</h4>
            </div>
            <div class="modal-body">
                <h4 id="modal_common_content"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary closeBtn"  data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" style="z-index:1055;" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width:400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <input type="hidden" id="modal_del_id">
                <input type="hidden" id="modal_del_flag">
                <input type="hidden" id="modal_del_rowno">
                <h4 class="modal-title" id="modal_del_title" id="modal_confirm_title">确认信息</h4>
            </div>
            <div class="modal-body">
                <h4 id="modal_confirm_content"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary confirmsubmitbtn" >确认</button>
                <button type="button" class="btn btn-primary cancelsubmitbtn" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--公共弹出模态框end-->  

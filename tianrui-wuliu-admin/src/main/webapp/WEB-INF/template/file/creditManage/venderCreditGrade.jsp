<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>信用管理</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css" rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css" rel="stylesheet">
    <link href="${stylesRoot }/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="Shortcut Icon" href="${imagesRoot}/favicon.ico" type="image/x-icon">
    <script language="javascript" type="text/javascript" src="${scriptsRoot}/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

<div class="container-fluid">
	<jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
        <!--后台右侧布局begin-->
        <div class="col-md-10 ">
            <div class="ht_content">
                <div id="content-header">
                    <div class="tj_honorma">
                    <h3>车主信用评级表</h3>
                        <select id="credityear"></select><label>年</label>
                        <select id="creditmonth"></select><label>月</label>
                        <button class="btn btnblue searchCredit">搜索</button>
                    </div>
                </div>
            <div class="row mt15">
                    <div class="col-md-12">
                        <div class="content-user">
                            <!--用户表格begin-->
                            <table class="table table-bordered" >
                                <thead >
                                <tr>
                                    <th>序号</th>
                                    <th>注册手机号</th>
                                    <th>车主名称</th>
                                    <th>拥有车辆数</th>
                                    <th>承运车次</th>
                                    <th>加权总分</th>
                                    <th>绩效评价等级</th>
                                </tr>
                                </thead>
                                <tbody id="gradeBody">
                                </tbody>
                            </table>
                            <!--用户表格end-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--后台右侧布局end-->
    </div>
    <!--后台整体布局end-->
</div>
<!--侧边栏end-->
</div>

<!--个人账户begin-->
<div class="modal fade" id="account" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">个人信息修改</h4>
            </div>
            <div class="modal-body">
                <div class="juesemodal">
                    <p><label>用户名：</label><span>adsgdgweg</span></p>
                    <p><label>姓名：</label><input type="text" value="王大大"></p>
                    <div class="modal_note"><h4>姓名不能为空，不超过20个字符</h4></div>
                    <p><label>手机号：</label><input type="text" value="1583652415"></p>
                    <div class="modal_note"><h4>请输入11位手机号</h4></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--个人账户end-->
<!--修改密码begin-->
<div class="modal fade" id="password" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="juesemodal">
                    <p><label>当前密码：</label><input type="password" value="王大大"></p>
                    <p><label>新密码：</label><input type="password" ></p>
                    <p><label>确认密码：</label><input type="password"></p>
                    <div class="modal_note"><h4>两次密码不一致</h4></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--退出begin-->
<div class="modal fade" id="exit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <h4>您确定要退出此账户吗？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--退出end-->


<!--修改密码end-->
<%@include file="../../common/footer.jsp"%>
<script type="text/javascript">
	var CONTEXTPATH = "${contextPath}";
	var imagesRoot = "${imagesRoot}";
</script>
<script type="text/javascript" src="${scriptsRoot}/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot}/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="/resources/js/creditManage/venderCreditGrade.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        // 表格列宽度手动调整
        $("table").resizableColumns({});
    });
</script>
</body>
</html>
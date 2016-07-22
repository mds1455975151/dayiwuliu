<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>货源详情</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/imgcut.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css"  rel="stylesheet">
</head>
<body>
<!--Header-->
<!-- 引用公共header部分 -->
<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
<!--Header-->
<!--内容部分begin-->
<div class="bghui">
<div class="container">
    <!--网站位置-->
    <div class="row">
            <div class="rz_line">
                <label>当前位置：首页</label><span>></span> <label>账号</label><span>></span><label>个人中心</label>
            </div>
    </div>
    <div class="row">
         <!--个人中心左侧begin-->
       	<!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心左侧end-->
        <!--个人中心右侧begin-->
        <div class="rz_right">
            <div class="car_title bgblue">
                <h2>计划进度</h2>
            </div>
            <!--个人中心右侧搜索框begin-->
            <div class="plan_progre">
                <div class="plan_pro1">计划:TRJH-01</div>
                <div class="plan_pro_percent">
                    <span>完成度：</span>
                    <div class="plan_pro_contr">
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                            </div>
                        </div>
                        <div class="progress_data">
                            <h4>60%</h4>
                        </div>
                        <!--
                        <div class="progress_dataend">
                            <h4>100%</h4>
                        </div>
                        -->
                        <div class="clear"></div>
                    </div>

                </div>
                <button type="submit" class="btn btnblue">确认完成</button>
            </div>
            <!--个人中心右侧搜索框end-->
            <div class="plan_fege">  </div>
            <div class="plan_procont">
                <div class="plan_prodetail">
                    <div class="plan_prohead">
                        <h3>货源详情</h3>
                    </div>
                    <div class="plan_probody">
                        <p>计划编码:TRJH-01</p>
                        <p>货物名称：水泥</p>
                        <p>货物重量体积：133吨 </p>
                        <p>期望运费：800元 </p>
                    </div>
                </div>
                <div class="plan_prodetail">
                    <div class="plan_prohead">
                        <h3>计划详情</h3>
                    </div>
                    <div class="plan_probody">
                        <p>路线：河南省郑州市-河南省汝州市</p>
                        <p>运价策略：运价策略A</p>
                        <p>计划总量：100吨 </p>
                        <p> 里程数：30里</p>
                        <p> 计价单价：50/吨/公里</p>
                        <p> 计价方式：按公里吨数计价</p>
                    </div>
                </div>
                <div class="plan_prodetail">
                    <div class="plan_prohead">
                        <h3>联系人信息</h3>
                    </div>
                    <div class="plan_probody">
                        <p>联系人: 张先生</p>
                        <p>联系电话：13000000000</p>
                    </div>
                </div>
                <button class="btn btnblue">关闭</button>
            </div>
        </div>
        <!--个人中心右侧end-->
        </div>
</div>
</div>
<!--内容部分end-->

<!--重新发布begin-->
<div class="modal fade" id="againplan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">重新发布计划</h4>
            </div>
            <div class="modal-body">
                <h4>您确定要重新发布计划吗？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--重新发布end-->

<!--关闭发布begin-->
<div class="modal fade" id="closeplan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">批量删除</h4>
            </div>
            <div class="modal-body">
                <h4>您确定要删除此货源吗？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--关闭发布end-->

<!--底部begin-->
<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<!--底部end-->
<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>

</body>
</html>
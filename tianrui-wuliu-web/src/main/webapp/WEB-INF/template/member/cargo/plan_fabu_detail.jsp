<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>计划详情</title>
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
<!--内容部分begin-->
<div class="bghui">
<div class="container">
    <!--网站位置-->
    <div class="row">
            <div class="rz_line">
                <label>首页</label><span>></span> <label>账号</label><span>></span><label>个人中心</label>
            </div>
    </div>
    <div class="row">
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心右侧begin-->
        <div class="rz_right">
            <div class=" bgblue">
                <h2>我发布的计划详情</h2>
            </div>
            <!-- 货源计划内容begin -->
            <div class="goods_box">
                <div class="goods_line">
                    <div class="plan_table">
                        <table class="table " >
                            <thead>
                            <tr>
                                <th  >计划编码	</th>
                                <th >货物名称	</th>

                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td >${plan.desc2} </td>
                                <td > ${plan.cargoname }</td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th >车主姓名	</th>
                                <th >车主电话</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td > ${plan.vehicleownername} </td>
                                <td >${plan.vehicleownerphone}</td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>始发地</th>
                                <th >目的地</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td >${plan.startcity}  </td>
                                <td > ${plan.endcity} </td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>计划周期开始时间</th>
                                <th>计划周期结束时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td >
                                   ${plan.starttimeStr} 
                                </td>
                                <td >
                                    ${plan.endtimeStr}
                                </td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th >更新时间</th>
                                <th>状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td> ${plan.createtimeStr}</td>
                                <td > ${plan.status}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- 货源计划内容end -->
        </div>
        <!--个人中心右侧end-->
        </div>
</div>
</div>
<!--内容部分end-->

<!--底部begin-->
<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<!--底部end-->
<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript">
	var PATH = "${path}";
	var memberId = "<%= member.getId() %>";
	var loginName = "<%= member.getLoginName() %>";
	var cellPhone = "<%= member.getCellPhone() %>";
	var CONTEXTPATH="${contextPath}";
</script>
<script>
    // 点击联系人输入框，显示常用联系人
    var kuang = $('.goods_lxr');
    kuang.click(function(){
        $('.goods_cyren').toggle();
    });
    // 双击常用联系人某一项，给input赋值，并隐藏
    $(".goods_cyren ul li").on("dblclick",function(){
        var planlabel = $(this).find("label").text();
        $(".goods_lxr").val(planlabel);
        var planSpan = $(this).find("span").text();
        $(".goods_tel").val(planSpan);
        $(".goods_cyren").hide();
    });
    // 常用联系人列表某一项选中
    var chren = $('.goods_cyren ul li');
    chren.click(function(){
        $(this).addClass('selected').siblings().removeClass('selected');
    });
    // 常用联系人框关闭按钮
    var cyclose = $('.goods_cyren i');
    cyclose.click(function(){
        $(".goods_cyren").hide();
    });
    // 发布对象 车主列表显示隐藏
        $(":radio").click(function(){
            if ($("input[name='bill']:eq(0)").is(":checked")) {
                $(".bill_driver").hide();
                $(".bill_car").show();
            } else if ($("input[name='bill']:eq(1)").is(":checked")) {
                $(".bill_car").hide();
                $(".bill_driver").show();
            }
        });
    // 车主列表名片的显示隐藏
    $(".goods_czlist ul li").mouseover(function () {
        $(this).find('.goods_listdt').show();
    });
    $(".goods_czlist ul li").mouseout(function () {
        $(this).find('.goods_listdt').hide();
    });
</script>
</body>
</html>
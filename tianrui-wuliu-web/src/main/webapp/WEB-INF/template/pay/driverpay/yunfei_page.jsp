
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
    <title>运费结算单</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/imgcut.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css"  rel="stylesheet">
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${trRoot }/tianrui/My97DatePicker/WdatePicker.js"></script>
    
</head>
<body>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
<!--Header-->
<!--内容部分begin-->
<div class="bghui">
<div class="container">
    <!--网站位置-->
    <div class="row">
            <div class="rz_line">
                <label>当前位置：支付</label><span>></span> <label>运费结算单</label>
            </div>
    </div>
    <div class="row">
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心左侧end-->
   <!--个人中心右侧begin-->
           <div class="rz_right">
            <div class="car_title bgblue">
                <h2>运费结算单</h2>
            </div>
            <!--个人中心右侧搜索框begin-->
            <div class="yf_search">
                <div class="yf_fl">
                    <div class="yf_sline">
                        <label>运单号：</label>
                        <input type="text" id="billcode" placeholder="请输入运单号">
                    </div>
                    <div class="yf_sline">
                        <label>货主名称：</label>
                        <input type="text" id="ownername" placeholder="请输入货主名称">
                    </div>
              
                </div>
                <div class="yf_fl">
                    <div class="yf_sline">
                        <label>状态：</label>
                        <select id="pay_status" class="form-control">
                            <option value="2">支付中</option>
                            <option value="3">已完成</option>
                        </select>
                    </div>
                    <div class="yf_sline">
                        <label>到货时间：</label>
                        <input type="text" id="signtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_fapiao" style="width:200px"/>
                    </div>
                </div>
                <button type="submit" onclick="index(1,0)" class="btn btnblue">搜索</button>
            </div>
            <!--个人中心右侧搜索框end-->
            <!--计划模板表格begin-->
             <div class="yunfei">
                 <input type="hidden" data-toggle="modal" data-target="#yf_choose" id="showmodal">
                 <table class="table table-hover" >
                     <thead>
                     <tr>
                         <th >运单号</th>
                         <th>货主名称</th>
                         <th >货物名称</th>
                         <th >到货时间</th>
                         <th>到货量</th>
                         <th >账单总价</th>
                         <th> 状态</th>
                     </tr>
                     </thead>
                     <tbody id="yunfeilist">
                     <!-- 加载数据  -->
                     </tbody>
                 </table>
                 <div class="goods_more pageMore" id="moredate">
                     <h4 onclick="moreSearch();">查看更多</h4>
	            </div>
             </div>
            <!--计划模板表格end-->
        </div>
        <!--个人中心右侧end-->
        </div>
</div>
</div>
<!--内容部分end-->

<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="/resources/js/pay/driverpay/yunfei_page.js" ></script>

</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>委派计划详情</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${trRoot}/tianrui/css/jquery-ui.min.css" rel="stylesheet">
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${trRoot }/tianrui/My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">
    	.goods_line label {
    		width: 92px!important;
    	}
    </style>
</head>
<body>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
<!--内容部分begin-->
<div class="bghui">
<div class="container">
    <!--网站位置-->
    <div class="row">
            <div class="rz_line">
                <label>当前位置：计划管理-委派计划详情</label>
            </div>
    </div>
    <div class="row">
        <!--个人中心左侧begin-->
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心右侧begin-->
        <div class="rz_right">
            <div class=" bgblue">
                <h2>委派计划详情</h2>
            </div>
            <!-- 货源计划内容begin -->
             <div class="goods_box">
                <form id="saveplan">
                <input type="hidden" name="id" value="${plan.id }" />
                <div class="goods_line">
                    <div class="plan_table">
                        <table class="table " >
                            <thead>
                            <tr>
                                <th>计划编码</th>
                                <th>货物名称</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="hcargono">${plan.plancode }</span></td>
                            	<td><span id="hcargoname">${plan.cargoname }</span></td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>起运地</th>
                                <th >目的地</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="hstartcity">${plan.startcity }</span></td>
                            	<td><span id="hendcity">${plan.endcity }</span></td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>计量单位</th>
                                <th >里程数</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="hmeasure">${plan.measure }</span></td>
                            	<td><span id="hdistance">${plan.distance }</span> </td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>发货人</th>
                                <th>收货人</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="sendpersion">${plan.sendperson }<br/>${plan.sendpersonphone }</span></td>
                            	<td><span id="receivepersion">${plan.receiveperson }<br/>${plan.receivepersonphone }</span> </td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>计价单位</th>
                                <th>税率</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="hpriceunits">${plan.priceunits }</span></td>
                            	<td style="height: 35px"><span id="tallage">
                            	<c:if test="${not empty plan.tallage}">
                            		<fmt:formatNumber type="number" value="${plan.tallage}" maxFractionDigits="0"/>%
                            	</c:if>
                            	</span></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                 <c:if test="${not empty plan.refuseresontype }">
					<div class="goods_line">
	                    <div class="good_time callback_dtl">
	                        <label>拒绝原因</label>
	                        <span>${plan.refuseresontype}</span>
	                    </div>
	                </div>
	             	<div class="goods_line">
	                    <div class="good_time callback_dtl">
	                        <label>客户备注</label>
	                        <span>${plan.refusereson}</span>
	                    </div>
	                </div>
                </c:if>
                <div class="goods_line">
                    <label> 计划总量：</label>
                    <input type="text" value="${plan.totalplanned }" style="width:180px" readOnly/>
                    <span id="measure_name">${plan.measure }</span>
                </div>
                 <div class="goods_line">
                    <label> 发货方：</label>
                    <input type="text" value="${plan.shipper }" readonly>
                </div>
                <div class="goods_line">
                    <label> 收货方：</label>
                    <input type="text" value="${plan.consignee }" readonly>
                </div>
                <div class="goods_line">
	                <label>开始时间：</label>
	                <input type="text" value="${plan.starttimeStr}" class="Wdate_plan" readOnly/>
                </div>
                <div class="goods_line">
                    <label>结束时间：</label>
                    <input type="text" value="${plan.endtimeStr}"  class="Wdate_plan" readOnly/>
                </div>
                <div class="goods_line">
                    <label> 联系人：</label>
                    <input type="text" value="${plan.linkman }" readOnly/>
                </div>
                <div class="goods_line">
                    <label> 联系电话：</label>
                    <input type="text"value="${plan.telephone }" readOnly/>
                </div>
                <div class="goods_line">
                    <label> 二级承运商：</label>
                   	<input type="text" value="${plan.vehicleownername }" readOnly/>
                </div>
                <div class="goods_foot">
                    <button class="btn btnyello mr20 submitBtn"  type="button">返回</button>
                </div>
            </div>
            <!-- 货源计划内容end -->
        </div>
        <!--个人中心右侧end-->
        </div>
</div>
</div>
<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript">
$(function(){
	//左侧选中
	$("#planAppoint").addClass("selected");
	$(".submitBtn").click(function(){
		window.location.href = "/trwuliu/planAppoint/main";
	});
});
</script>
</body>

</html>
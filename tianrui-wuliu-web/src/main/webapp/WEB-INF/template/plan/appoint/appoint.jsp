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
    <title>委派计划</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${trRoot}/tianrui/css/jquery-ui.min.css" rel="stylesheet">
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${trRoot }/tianrui/My97DatePicker/WdatePicker.js"></script>
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
                <label>当前位置：计划管理-委派计划</label>
            </div>
    </div>
    <div class="row">
        <!--个人中心左侧begin-->
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心右侧begin-->
        <div class="rz_right">
            <div class=" bgblue">
                <h2>委派计划</h2>
            </div>
            <!-- 货源计划内容begin -->
             <div class="goods_box">
                <form id="saveplan">
                <input type="hidden" id="planid" value="${plan.id }" />
                <input type="hidden" id="operate" value="${operate }" />
                <div class="goods_line">
                    <div class="plan_table">
                        <table class="table " >
                            <thead>
                            <tr>
                                <th>货物名称</th>
                                <th>计量单位</th>
                                <th>计价单位</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td><span id="hcargoname">${plan.cargoname }</span></td>
                            	<td style="height: 35px"><span id="hmeasure">${plan.measure }</span></td>
                            	<td style="height: 35px"><span id="hpriceunits">${plan.priceunits }</span></td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>起运地</th>
                                <th>目的地</th>
                                <th>结算里程数</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="hstartcity">${plan.startcity }</span></td>
                            	<td><span id="hendcity">${plan.endcity }</span></td>
                            	<td><span id="distance">${plan.distance }</span></td>
                            </tr>
                            </tbody>
                            <thead>
                            </tbody>
                            <thead>
                            <tr>
                                <th>发货人</th>
                                <th>收货人</th>
                                <th>税率</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="sendpersion">${plan.sendperson }<br/>${plan.sendpersonphone }</span></td>
                            	<td><span id="receivepersion">${plan.receiveperson }<br/>${plan.receivepersonphone }</span> </td>
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
                 <c:if test="${plan.refuseresontype!= null }">
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
                    <div class="bill_yunshu mr20">
                        <label>开始时间：</label>
                        <input type="text" id="begintime" name="starttimeStr" value='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:00:00"/>' onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" class="Wdate billStartTimeInput" style="width:160px"/>
                    </div>
                    <div class="bill_yunshu">
                        <label>结束时间：</label>
                        <input type="text" id="endtime" name="endtimeStr" value='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:00:00"/>' onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" class="Wdate billStartTimeInput" style="width:160px"/>
                    </div>
                </div>
                <div class="goods_line">
                    <label>运输量：</label>
                    <input type="text" id="totalplanned" name="totalplanned" placeholder="剩余运输量${plan.overweight }" style="width:180px" maxlength="10"/>吨
                </div>
                <!--发布对象begin-->
                <div class="plan_fabu">
                    <label class="lineh40"> 发布对象：</label>
                    <div class="plan_tab">
                        <!--tab切换标题-->
                        <ul class="plan_tabmenu">
                        	<c:if test="${plan.isfamily ==0 }" >
	                            <li class="allVender ${plan.isfamily=='0'?'select':''}">我的车主</li>
                        	</c:if>
                        	<c:if test="${plan.isfamily ==1 }" >
	                           <%--  <li class="familayVender ${plan.isfamily=='1'?'select':''}">熟车车主</li> --%>
                        	</c:if>
                        </ul>
                        <!--tab切换标题end-->

                        <!--tab切换的内容-->
                        <div class="plan_tabbox">
                            <div class="plan_tabcont">
                                <ul class="plan_line venderList">
                               		<c:forEach items="${venderList }" var="v" varStatus="status">
    	                            	<li class="<c:if test="${status.index eq 0}">active</c:if>" venderid="${v.ownerId }" venderName="${v.ownerName }" venderTel="${v.ownerTel }">
											<input name="venderid" type="radio" <c:if test="${status.index eq 0}">checked</c:if>><label>${v.ownerName }</label><label>${v.ownerTel }</label>
	                                	</li>
                               		</c:forEach>
                                </ul>
                            </div>
                            
                        </div>
                        <!--tab切换的内容end-->
                    </div>
                </div>
                <!--发布对象end-->
               
                <div class="goods_foot">
                    <button class="btn btnyello mr20 appointBtn"  type="button">委派计划</button>
                    <button class="btn btnyello mr20 backBtn"  type="button">返回</button>
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
<script type="text/javascript" src="/resources/js/plan/appoint.js" ></script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>发布计划</title>
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
                <label>当前位置：计划管理-发布计划</label>
            </div>
    </div>
    <div class="row">
        <!--个人中心左侧begin-->
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心右侧begin-->
        <div class="rz_right">
            <div class=" bgblue">
                <h2>发布计划</h2>
            </div>
            <!-- 货源计划内容begin -->
             <div class="goods_box">
                <form id="saveplan">
                <input type="hidden" name="freightid" value="${plan.freightid }" />
                <input type="hidden" name="routeid" value="${plan.routeid }" />
                <input type="hidden" name="cargoid" value="${plan.cargoid }" />
                <input type="hidden" name="nowTime" value="${nowTime }">
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
                                <th>含税单价</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="hpriceunits">${plan.priceunits }</span></td>
                            	<td><span id="hprice">${plan.price }</span></td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>税率</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
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
                <div class="goods_line">
                    <label> 发货单位：</label>
                    <input type="text" name="organizationname" id ="organizationname" value="${plan.orgname }" readonly>
                </div>
                <div class="goods_line">
                    <label> 计划总量：</label>
                    <input type="text" name="totalplanned" placeholder="保留两位小数" id ="totalplanned" style="width:180px" value="${plan.totalplanned }"  maxlength="10">
                    <span id="measure_name">${plan.measure }</span><span id="totalPrice">.总价:<fmt:formatNumber type="number" value="${plan.totalplanned * plan.price }" pattern="0.00" maxFractionDigits="2"/>元</span>
                </div>
                <div class="goods_line">
                    <div class="good_time mr20">
                        <label>开始时间：</label>
                        <input type="text" id="begintime" name="starttimeStr" value="${plan.starttimeStr}:00" readOnly onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endtime\')}',minDate: '${nowTime}'})" class="Wdate_plan" style="width:390px" />
                    </div>
                </div>
                <div class="goods_line">
                    <div class="good_time">
                        <label>结束时间：</label>
                        <input type="text" id="endtime" name="endtimeStr" value="${plan.endtimeStr}:00" readOnly onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'begintime\') || \'${nowTime}\'}'})" class="Wdate_plan" style="width:390px" />
                    </div>
                </div>
                <div class="goods_line">
                    <label> 联系人：</label>
                    <input type="text" value="${plan.linkman }" name="linkman" placeholder="" maxlength="20" class="goods_lxr" id="linkman">
                      
                </div>
                <div class="goods_line">
                    <label> 联系电话：</label>
                    <input type="text" placeholder="" name="telephone"  value="${plan.telephone }" class="goods_tel" id="telephone" maxlength="20">
                </div>
                <!--发布对象begin-->
                <div class="plan_fabu">
                    <label class="lineh40"> 发布对象：</label>
                    <div class="plan_tab">
                        <!--tab切换标题-->
                        <ul class="plan_tabmenu">
                            <li class="allVender ${plan.isfamily=='0'?'select':''}">全部车主</li>
                            <%-- <li class="familayVender ${plan.isfamily=='1'?'select':''}">熟车车主</li> --%>
                            <input type="hidden" name="isFamily" value="${plan.isfamily }" id="isFamily" >
                        </ul>
                        <!--tab切换标题end-->

                        <!--tab切换的内容-->
                        <div class="plan_tabbox">
                            <!--我的车主内容begin-->
                            <div class="plan_tabcont  allVenderDiv ${plan.isfamily == '1'?'hide':'' }">
                                <!--车主信息begin-->
                                <ul class="plan_line">
                                    <c:forEach items="${ownerResp}" var="item">
                                    <li class="select">
                                       <input type="radio"  ${ (plan.isfamily=='0' && plan.vehicleownerid==item.ownerId)?'checked':'' } name="venderId" value="${item.ownerId }"><label>${item.ownerName }</label><label>${item.ownerTel }</label>
									</li>
									</c:forEach>
                                </ul>
                                <!--车主信息begin-->
                            </div>
                            <!--我的车主内容end-->
                            
                            <!--我的熟车内容begin-->
                            <div class="plan_tabcont familayVenderDiv ${plan.isfamily == '1'?'':'hide' }">
                                <!--车主信息begin-->
                                    <ul class="plan_line">
                                         <c:forEach items="${ownerResp}" var="item">
                                         	<c:if test="${item.isEnabled == '1' }">
			                                    <li class="select">
			                                       <input type="radio" ${ (plan.isfamily=='1' && plan.vehicleownerid==item.ownerId)?'checked':'' }  name="venderId" value="${item.ownerId }"><label>${item.ownerName }</label><label>${item.ownerTel }</label>
												</li>
											</c:if>	
										</c:forEach>
                                    </ul>
                                <!--车主信息begin-->
                            </div>
                            <!--我的熟车内容end-->
                        </div>
                        <!--tab切换的内容end-->
                    </div>
                </div>
                <!--发布对象end-->
              
                <div class="goods_foot">
                   <input type="checkbox" name="istemplate" value="1"><label>是否存为模板</label><br>
                    <button class="btn btnyello mr20 submitBtn"  type="button">发布</button>
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
<script type="text/javascript" src="/resources/js/plan/publish.js" ></script>
</body>

</html>
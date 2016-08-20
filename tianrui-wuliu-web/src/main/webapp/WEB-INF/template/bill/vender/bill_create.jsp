<%@ page language="java" pageEncoding="UTF-8"%>
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
    <title>发布运单</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

	 <script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	 
    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/imgcut.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css"  rel="stylesheet">
    <style type="text/css">
    .ts {
   	    width: 20px!important;
	    height: 20px!important;
	    padding: 0px!important;
	    margin: 0px!important;
	    text-align: center;
    	border-radius: 2px!important;
	}
	.ts_label {
		float: none!important;
   	 	padding-left: 4px;
	}
</style>
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
                <label>当前位置：首页</label><span>></span> <label>账号</label><span>></span><label>个人中心</label>
            </div>
    </div>
    <div class="row">
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心左侧end-->
  <!--个人中心右侧begin-->
        <div class="rz_right">
            <div class=" bgblue">
                <h2>运单发布</h2>
            </div>
            <!-- 货源计划内容begin -->
            <div class="goods_box">
                <div class="goods_line">
                    <div class="plan_table">
                    	<input type="hidden" id="planId" value="${plan.planId }"/>
                        <table class="table " >
                            <thead>
                            <tr>
                                <th >货物名称	</th>
                                <th >计量单位</th>
                                <th >计价单位</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${plan.cargoname} </td>
                                <td>${plan.measure}</td>
                                <td>${plan.priceunits}</td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>起运地</th>
                                <th>目的地</th>
                                <th> 结算里程数</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td >${plan.startcity }  </td>
                                <td >${plan.endcity }  </td>
                                <td >${plan.distance}</td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>发货人</th>
                                <th>收货人</th>
                                <th>税率</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="bill_td">
                                <td >
                                    <h4>${plan.sendpersion }</h4>
                                    <h4>${plan.sendpersionphone }</h4>
                                </td>
                                <td >
                                    <h4>${plan.receivepersion }</h4>
                                    <h4>${plan.receivepersionphone }</h4>
                                </td>
                                <td>
                                	<c:if test="${not empty plan.tallage}">
                            			<fmt:formatNumber type="number" value="${plan.tallage}" maxFractionDigits="0"/>%
                            		</c:if>
                            	</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="goods_line">
                    <div class="bill_yunshu mr20">
                        <label>开始时间：</label>
                        <input type="text"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"  value="${plan.startTime  }" class="Wdate billStartTimeInput" style="width:160px"/>
                    </div>
                    <div class="bill_yunshu">
                        <label>结束时间：</label>
                        <input type="text"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"  value="${plan.endeTime}" class="Wdate billEndTimeInput" style="width:160px"/>
                    </div>
                </div>

                <div class="goods_line">
                    <div class="bill_yunshu mr20">
                        <label> 运输量：</label>
                        <input id="overweight" value="${plan.overweight }" type="hidden" />
                        <input type="text" class="weightInput" placeholder="剩余运输量${plan.overweight }" maxlength="10" />${plan.measure}
                    </div>
                    <div class="bill_yunshu">
                        <label> 含税单价：</label>
                        <input type="text" class="priceInput" readOnly value="${plan.price }" maxlength="10" />元
                    </div>
                </div>
                <div class="bill_fabu">
                    <label> 车辆选择：</label>
                    <div class="bill_fabu_cont">
<!--                         <div class="bill_search">
                            <input type="text"  placeholder="车牌号/司机姓名/司机电话">
                            <button class="btn btnyello">搜索</button>
                        </div> -->
                        <div class="bill_cllist">
                            <ul>
                            	<c:forEach var="item" items="${vlist}" > 
                            		
                            			<c:if test="${item.isUsed==1 }">
                            				<li title="该车与当前计划已经有未完成的运单." >
	                                		<input type="checkbox" dataId="${item.id }" class="checkInput" disabled >
	                                	</c:if>
                            			<c:if test="${item.isUsed==0}">
                            				<li>
	                                		<input type="checkbox" dataId="${item.id }" class="checkInput"  >
	                                	</c:if>
	                                    <span>${item.vehicleNo }</span>
	                                    <span>${item.driverName }--${item.vehicleTypeName }</span>
	                                    <span><fmt:formatNumber type="number" value="${item.vehiweight }" maxFractionDigits="0"/>吨</span>
	                                    <span>${item.billstatus eq 2 ? '发货中' : item.billstatus eq 3 ? '运货中' : item.billstatus eq 4 ? '卸货中' : item.billstatus eq 5 ? '空闲中' : ''}</span>
	                                    <input type="text" class="ts"/><label class="ts_label">趟</label>
	                                </li>
                            	</c:forEach>
                            </ul>
                        </div>
                     </div>
                </div>
                <div class="goods_foot">
                    <button class="btn btnyello mr20 submitBtn" type="button">发布运单</button>
                    <button class="btn btnblue cancleBtn" type="button">返回</button>
                </div>
            </div>
            <!-- 货源计划内容end -->
            <!-- 货源计划内容end -->
        </div>
        <!--个人中心右侧end-->
        </div>
</div>
</div>
<!--内容部分end-->

<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<!--底部end-->
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="/resources/js/bill/bill_create.js" ></script>

</body>
</html>
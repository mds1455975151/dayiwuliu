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
    <title>运单修改</title>
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
   /*  .ts {
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
	li.active {
	    background: #d0d0c8!important;	
	} */
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
                    	<input type="hidden" id="id" value="${bill.id }"/>
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
                                <td>${bill.cargoname} </td>
                                <td>${bill.desc1}</td>
                                <td>${bill.priceunits}</td>
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
                                <td >${bill.startcity }  </td>
                                <td >${bill.endcity }  </td>
                                <td >${bill.distance}</td>
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
                                    <h4>${bill.consignorname }</h4>
                                    <h4>${bill.consignortel }</h4>
                                </td>
                                <td >
                                    <h4>${bill.receivername }</h4>
                                    <h4>${bill.receivertel }</h4>
                                </td>
                                <td>
                                	<c:if test="${not empty bill.tallage}">
                            			<fmt:formatNumber type="number" value="${bill.tallage}" maxFractionDigits="0"/>%
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
                        <input type="text"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"  value="${bill.starttime  }" class="Wdate billStartTimeInput" style="width:160px"/>
                    </div>
                    <div class="bill_yunshu">
                        <label>结束时间：</label>
                        <input type="text"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"  value="${bill.endtime}" class="Wdate billEndTimeInput" style="width:160px"/>
                    </div>
                </div>

                <div class="goods_line">
                    <div class="bill_yunshu mr20">
                        <label> 运输量：</label>
                        <input id="overweight" value="${bill.overweight }" type="hidden" />
                        <input type="text" class="weightInput" placeholder="剩余运输量${bill.overweight }" maxlength="10" value="${bill.weight }" />${bill.desc1}
                    </div>
                    <div class="bill_yunshu">
	                    <label> 含税单价：</label>
                        <input type="text" class="priceInput" readOnly maxlength="10" value="${bill.price }" />元
                    </div>
                </div>
                <div>
                    <label class="mr20"> 实际运输量：${complete }吨</label>
                    <label> 实际剩余量：${remain }吨</label>
                </div>
                <div class="bill_fabu">
                    <label> 车辆选择：</label>
                    <div class="bill_fabu_cont">
	                    <div class="bill_search">
                            <input type="text" id="search"  placeholder="车牌号/司机姓名/司机电话">
                            <button id="searchVehicle" class="btn btnyello">搜索</button>
                        </div>
                        <div class="bill_cllist">
                            <ul id="capa">
                            </ul>
                        </div>
                     </div>
                </div>
                <div class="goods_foot">
                    <button class="btn btnyello mr20 submitBtn" type="button">修改运单</button>
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
<script type="text/javascript" src="/resources/js/bill/bill_update.js" ></script>

</body>
</html>
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
    <title>发布货源</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/jquery-ui.min.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/select2.css" rel="stylesheet">
    
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${trRoot }/tianrui/My97DatePicker/WdatePicker.js"></script>
    <style>
     .ui-autocomplete {
	    max-height: 100px;
	    overflow-y: auto;
	    /* 防止水平滚动条 */
	    overflow-x: hidden;
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
                <label>当前位置：计划管理-创建计划</label>
            </div>
    </div>
    <div class="row">
        <!--个人中心左侧begin-->
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心右侧begin-->
        <div class="rz_right">
            <div class=" bgblue">
                <h2>货运计划</h2>
            </div>
            <!-- 货源计划内容begin -->
             <div class="goods_box">
                <form id="saveplan">
                <div class="goods_line" id="select01">
                    <label> 货物名称：</label>
                    <select name="cargoid" class="form-control cargoSel" >
                    	<option value="">请选择货物</option>
                     	<c:forEach	 items="${ cargoList}" var="item">
                    		<option value="${item.id}">${item.materName}</option>
                    	</c:forEach> 
                    </select>
                </div>
                <div class="goods_line" id="select02">
                    <label> 路线：</label>
                    <select name="routeid" class="form-control routeSel">
                    	<option value="">请选择路线</option>
                 		<c:forEach	 items="${routeList}" var="item">
                    		<option value="${item.id}">${item.routename}</option>
                    	</c:forEach>
                    </select>
                </div>
                <div class="goods_line" id="select03">
                    <label> 运价策略：</label>
                     <select name="freightid" class="form-control freightSel select2">
                    	<option value="">请选择运价策略</option>
                    </select>
                </div>
                <div class="goods_line">
                    <div class="plan_table">
                        <table class="table " >
                            <thead>
                            <tr>
                                <th>货物编码</th>
                                <th>货物名称</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="hcargono"></span></td>
                            	<td><span id="hcargoname"></span></td>
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
                            	<td style="height: 35px"><span id="hstartcity"></span></td>
                            	<td><span id="hendcity"></span></td>
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
                            	<td style="height: 35px"><span id="hmeasure"></span></td>
                            	<td><span id="hdistance"></span> </td>
                            </tr>
                            </tbody>
                            <thead>
                            </tbody>
                            <thead>
                            <tr>
                                <th>计价单位</th>
                                <th>含税单价</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="hpriceunits"></span></td>
                            	<td><span id="hprice"></span></td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>税率</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="tallage"></span></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="goods_line">
                    <label> 发货单位：</label>
                    <input type="text" name="organizationname" id ="organizationname" readonly >
                </div>
                <div class="goods_line">
                    <label> 发货方：</label>
                    <select class="form-control select2" id="shipperMerchant" name="shipperMerchant">
                    	<option value="">请选择</option>
                    	<c:forEach items="${merchant }" var="mer">
                    		<option value="${mer.id }">${mer.name }</option>
                    	</c:forEach>
                    </select>
                </div>
                <div class="goods_line">
                    <label> 收货方：</label>
                    <select class="form-control select2" id="consigneeMerchant" name="consigneeMerchant">
                    	<option value="">请选择</option>
                    	<c:forEach items="${merchant }" var="mer">
                    		<option value="${mer.id }">${mer.name }</option>
                    	</c:forEach>
                    </select>
                </div>
                
                <div class="goods_line">
                    <label> 发货人：</label>
                    <input type="text" id="sendname" name="shipperName" style="width:160px">
                    <input type="text" id="sendtel" name="shipperTell" style="width:220px"  >
                </div>
                <div class="goods_line">
                    <label> 收货人：</label>
                    <select class="form-control" id="receiveid_req" name="receiveid">
                    	<option>请选择</option>
                    	<c:forEach items="${signer }" var="signer">
                    		<option value="${signer.id }">${signer.membername }-${signer.cellphone }</option>
                    	</c:forEach>
                    </select>
                    <input type="hidden" id="recname" name="consigneeName">
                    <input type="hidden" id="rectell" name="consigneeTell">
                </div>
                <div class="goods_line">
                    <label> 计划总量：</label>
                    <input type="text" name="totalplanned" placeholder="保留两位小数" id ="totalplanned" style="width:180px" maxlength="10" >
                    <span id="measure_name"></span><span id="totalPrice"></span>
                </div>
                <div class="goods_line">
                    <div class="good_time ">
                        <label>开始时间：</label>
                        <input type="text" id="begintime" name="starttimeStr" readOnly onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime\')}',minDate: '${nowTime}'})" class="Wdate_plan" style="width:390px" />
                    </div>
                </div>
                <div class="goods_line">
                    <div class="good_time">
                        <label>结束时间：</label>
                        <input type="text" id="endtime" name="endtimeStr" readOnly onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'begintime\') || \'${nowTime}\'}'})" class="Wdate_plan" style="width:390px" />
                    </div>
                </div>
                <div class="goods_line">
                    <label> 联系人：</label>
                    <input type="text" value="${currUser.realName }" name="linkman" placeholder="" class="goods_lxr" id="linkman" maxlength="20">
                      
                </div>
                <div class="goods_line">
                    <label> 联系电话：</label>
                    <input type="text" placeholder="" name="telephone"  value="${currUser.cellphone }" class="goods_tel" id="telephone" maxlength="20">
                </div>
                <div class="goods_line">
                    <label> 支付对象：</label>
              		<input type="radio" name="payment" checked="checked" value="2">&nbsp&nbsp车主
              		<input type="radio" name="payment" value="1">&nbsp&nbsp司机
                </div>
                
                <div class="clear"></div>
                <div class="goods_foot">
                   	<!-- 
                   	<input type="checkbox" name="istemplate" value="1"><label>是否存为模板</label><br>
                   	 -->
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
<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/select2.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/select_locale_zh-CN.js" ></script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="/resources/js/plan/goodsSave.js?1202" ></script>
</body>
</html>
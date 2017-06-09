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
    <title>我发布的计划</title>
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
<!-- 引用公共header部分 -->
<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
<!--内容部分begin-->
<!--内容部分begin-->
<div class="bghui">
<div class="container">
    <!--网站位置-->
    <div class="row">
            <div class="rz_line">
            	<label>当前位置：计划管理-我发布的计划-计划详情</label>
            </div>
    </div>
    <div class="row">
        <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
    <!--个人中心右侧begin-->
        <div class="rz_right">
            <div class="car_title bgblue">
                <h2>计划进度</h2>
            </div>
            <div class="plan_procont">
                <div class="plan_prodetail">
                    <div class="plan_prohead">
                        <h3>进度详情</h3>
                    </div>
                    <div class="plan_probody">
                        <p>计划总量：<span  >${plan.totalplanned }</span> 吨</p>
                        <p>已完成量：<span  >${plan.completed }</span> 吨</p>
                        <p>开始时间：<span  >${plan.starttimeStr}:00</span>
                        <p>结束时间：<span  >${plan.endtimeStr}:00</span>
                    </div>
                </div>
                <!--进度开始-->
                <div class="plan_prodetail">
                    <div class="plan_prohead">
                        <h3>完成进度</h3>
                    </div>
                    <div class="plan_probody">
                        <div class="plan_pro_percent">
                            <span>完成度：</span>
                            <!--动态进度条开始-->
                            <div class="plan_pro_contr">
                                <!--长进度条开始,style的wdith内容就是进度条的实心显示-->
                                <div class="progress">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"
                                     style="width: <c:if test="${plan.status eq '3' }">
                                		 100%
                                	  </c:if>
                                	   <c:if test="${plan.status eq '2' }">
                                		 ${plan.completed/plan.totalplanned*100 }%
                                	  </c:if>;">
                                    </div>
                                </div>
                                <!--长进度条结束-->
                                <!--进度条下方数字开始-->
                                <div class="progress_data" style="left: 
										<c:if test="${plan.status eq '3' }">
                                			100%;
                                	  	</c:if>
                                	   	<c:if test="${plan.status eq '2' }">
                                			<fmt:formatNumber value="${plan.completed/plan.totalplanned*100 }" pattern="00.00"/>%;
                                	  	</c:if>">
                                	 <c:if test="${plan.status eq '3' }">
                                		 <h4>100%</h4>
                                	  </c:if>
                                	   <c:if test="${plan.status eq '2' }">
                                		 <h4><fmt:formatNumber value="${plan.completed/plan.totalplanned*100 }" pattern="00.00"/>%</h4>
                                	  </c:if>
                                </div>
                                <!--进度条下方数字结束-->
                                <div class="clear"></div>
                            </div>
                            <!--动态进度条结束-->
                        </div>
                        <!--动态进度条结束-->
                        <!--button二选一-->
                        <input type="hidden" id="planId" value="${plan.id }" >
                        			  <c:if test="${plan.status eq '3' }">
                                		  <button class="btn">已完成</button>
                                	  </c:if>
                                	   <c:if test="${plan.status eq '2' }">
                                		 <button type="button"  onclick="completePlan()" class="btn btnblue">确认完成</button>
                                	  </c:if>
                    </div>
                </div>
                <!--进度结束-->
            </div>
            <div class="plan_back">
                <button class="btn btnblue" onclick="history.go(-1)" >返回</button>
            </div>
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
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="/resources/js/plan/progress.js" ></script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
	<link href="${trRoot}/tianrui/css/pagination/pagination.css" rel="stylesheet"/>
	<style type="text/css">
		tr.loadtr:hover {
		    background-color: inherit!important;
		}
	</style>
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
                <h2>我发布的计划</h2>
            </div>
    		<input type="hidden" id="pageNo" value="1">
            <!--个人中心右侧搜索框begin-->
            <div class="plan_search">
                <input type="text" placeholder="计划编码,货物名称,车主名称,车主手机号" id="search_v">
                <button type="submit" class="btn btnblue searchBtn">搜索货源</button>
            </div>
            <!--个人中心右侧搜索框end-->
            <div class="plan_fege">  </div>
            <!--计划模板表格begin-->
             <div class="planfabu " id="dateContent">
				<div class="hasdata">
					<table class="table table-hover" >
					    <thead>
					    <tr>
					        <th>计划编码</th>
					        <th>始发地-目的地</th>
					        <th>货物名称</th>
					        <th>车主</th>
					        <th>更新时间</th>
					        <th>状态</th>
					        <th> 操作</th>
					    </tr>
					    </thead>
					    <tbody id="planlist">
					    
					    </tbody>
					</table>
					<!-- 分页部分  开始-->
		            <div class="row pr20 fr">
						<%@include file="../../common/pagination.jsp" %>
		            </div>
					<!-- 分页部分 结束 -->
				</div>
				<div class="nodata hide">
					<img src="${trRoot}/tianrui/images/none_pro.png">
					<h3>未发现发布的货运计划！</h3>
				</div>
             </div>
            <!--计划模板表格end-->
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
<script type="text/javascript">
	trRoot = '${trRoot}';
</script>
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/jquery.pagination.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/pagination.js"></script>
<script type="text/javascript" src="/resources/js/plan/owner_page.js" ></script>
</body>
</html>
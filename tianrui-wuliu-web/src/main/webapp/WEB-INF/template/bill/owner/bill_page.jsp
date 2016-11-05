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
     <title>我发起的运单</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/imgcut.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css"  rel="stylesheet">
	<link href="${trRoot}/tianrui/css/pagination/pagination.css" rel="stylesheet"/>
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
                <label>当前位置：运单</label><span>></span> <label>我发布的运单</label>
            </div>
    </div>
    <div class="row">
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心左侧end-->
        	<input type="hidden" id="pageNo" value="1">
   		<!--个人中心右侧begin-->
            <div class="rz_right">
                <div class="car_title bgblue">
                    <h2>运单</h2>
                </div>
                <!--个人中心右侧搜索框begin-->
                <div class="plan_search">
                    <input type="text" class="searchKey" placeholder="请出入搜索内容">
                    <button type="button" class="btn btnblue searchBtn">搜索</button>
                </div>
                <!--个人中心右侧搜索框end-->
                <div class="plan_fege"></div>
                <!--计划模板表格begin-->
                <div class="bill_table" id="dateContent" >
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th  >运单号</th>
                            <th >货物名称</th>
                            <th > 车牌号-车主</th>
                            <th >运单状态</th>
                            <th >更新时间</th>
                            <th > 操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <!-- 分页部分  开始-->
		            <div class="row pr20 fr">
						<%@include file="../../common/pagination.jsp" %>
		            </div>
					<!-- 分页部分 结束 -->
                </div>
                <!--tab切换的内容end-->
            </div>
            <!--计划模板表格end-->
        </div>
        <!--个人中心右侧end-->
        </div>
</div>
</div>
<!--内容部分end-->

<!-- 签收modal -->

<div class="modal fade" id="signModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">运单签收</h4>
            </div>
            <div class="modal-body">
            	<input type="hidden" id="hidid"/>
            	<input type="hidden" id="weight"/>
            	<input type="hidden" id="planWeight"/>
            	<input type="hidden" id="planCompleteWeight"/>
            	<input type="hidden" id="pickupweight"/>
            	<input type="hidden" id="signweight"/>
                <div class="bdimg">
	                <div class="modal-body">
		                <ul class="nav nav-tabs">
		                    <li class="active"><a href="#single" data-toggle="tab">提货磅单</a></li>
		                    <li><a href="#multiple" data-toggle="tab">卸货磅单</a></li>
		                </ul>
		                <div class="tab-content">
		                    <div class="tab-pane fade in active" id="single">
		                        <div class="bdimg">
				                    <a target="_blank"><img src="${trRoot}/tianrui/images/bd.png" id="qhbdImgUrl"></a>
				                    <label></label>
				                    <div id="notImg" class="bd_note" hidden>
	                        			<label>司机未上传磅单</label>
					                </div>
		                        </div>
		                    </div>
		                    <div class="tab-pane fade" id="multiple">
		                        <div class="bdimg">
				                    <a target="_blank"><img src="${trRoot}/tianrui/images/bd.png" id="bdimgurl"></a>
		                        </div>
		                    </div>
		                </div>
		            </div>
                    <p><label>签收重量：</label><input type="text" id="weighttext"><label id="stateWeightLabel" style="padding-left: 50px;"></label></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary signsubmitbtn">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 签收moal结束 -->
<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<!--底部end-->
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/jquery.pagination.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/pagination.js"></script>
<script type="text/javascript" src="/resources/js/bill/owner_page.js" ></script>

</body>
</html>
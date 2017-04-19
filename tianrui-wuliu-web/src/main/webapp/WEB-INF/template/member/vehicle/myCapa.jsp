<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>大易物流平台-我的运力</title>
	    <meta name="keywords" content=" 天瑞"/>
	    <meta name="description" content="">
	    <meta name="author" content="">
    
	<!-- 引用公共header部分 -->
	<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
		<!--内容部分begin-->
		<div class="bghui">
			<div class="container">
			    <!--网站位置-->
			    <div class="row">
			            <div class="rz_line">
			                <label>首页</label><span>></span> <label>账号</label><span>></span><label>个人中心</label>
			            </div>
			    </div>
			    <div class="row">
			        <!--个人中心左侧begin-->
			        <jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
			        <!--个人中心左侧end-->
		            <!--个人中心右侧begin-->
		            <div class="car_right">
			            <div class="car_title bgblue">
			                <h2>我的运力</h2><a href="/trwuliu/Member/capa/capa"><span>新增</span></a>
			            </div>
		                <!--个人中心右侧搜索框begin-->
		                <div class="plan_search">
		                    <input type="text" placeholder="司机姓名/司机电话/车牌号" id="searchText">
		                    <button type="submit" class="btn btnyello" onclick="index(1,0)">搜索</button>
		                </div>
		                <!--个人中心右侧搜索框end-->
			            <div style="height: 15px;background: #f0f0f0;"></div>
			            <div class="car_shuliang">
			                <h4>共<span id="total"></span>辆车</h4>
			            </div>
			            <div class="car_mycar">
			                <table class="table table-hover" >
			                    <thead>
			                    <tr>
			                        <th >车辆信息</th>
			                        <th >车主信息</th>
			                        <th >状态</th>
			                        <th > 位置信息</th>
			                        <th> 操作</th>
			                    </tr>
			                    </thead>
			                    <tbody id="innerHML">
			                   
			                    </tbody>
			                </table>
						<div class="goods_more pageMore" id="moredate">
		                     <h4 onclick="moreSearch();">查看更多</h4>
			            </div>
			            </div>
			        </div>
		            <!--个人中心右侧end-->
		        </div>
		    </div>
		</div>
		<!--内容部分end-->
		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
		<script type="text/javascript"> var trRoot = "${trRoot}";</script>
		<script type="text/javascript" src="/resources/js/member/vehicle/myCapa.js?0419" ></script>
	</body>
</html>

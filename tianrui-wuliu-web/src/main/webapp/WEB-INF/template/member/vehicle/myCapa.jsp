<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>天瑞物流平台-添加司机</title>
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
			            <div class="yl_show">
			                <span><img src="${trRoot}/tianrui/images/round1.png"></span>
			                <label>新增</label>
			                <span><img src="${trRoot}/tianrui/images/round2.png"></span>
			                <label>原有</label>
			            </div>
			            <div class="yl_mytable">
			                <table class="table table-bordered" >
			                    <thead>
			                    <tr>
			                        <th > </th>
			                        <th >车牌号</th>
			                        <th >司机</th>
			                        <th > 电话</th>
			                        <th> 车辆类型</th>
			                        <th> 承重</th>
			                        <th> 状态</th>
			                    </tr>
			                    </thead>
			                    <tbody>
			                    <tr>
			                        <td > <img src="${trRoot}/tianrui/images/round1.png"></td>
			                        <td >豫A12125 </td>
			                        <td>张大伟</td>
			                        <td>1586525411</td>
			                        <td>箱式/222米</td>
			                        <td>100吨</td>
			                        <td>空闲中</td>
			                    </tr>
			                    <tr>
			                        <td > <img src="${trRoot}/tianrui/images/round2.png"></td>
			                        <td >豫A12125 </td>
			                        <td>张大伟</td>
			                        <td>1586525411</td>
			                        <td>箱式/20米</td>
			                        <td>100吨</td>
			                        <td>空闲中</td>
			                    </tr>
			                    </tbody>
			                </table>
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
		<script type="text/javascript" src="/resources/js/member/vehicle/myCapa.js" ></script>
	</body>
</html>

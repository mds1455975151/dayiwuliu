<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运单中心</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">

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
					<label>当前位置：运单</label><span>></span> <label>我运输的运单</label>
				</div>
			</div>
			<div class="row">
				<!-- 引用公共left部分 -->
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
				<!--个人中心右侧begin-->
				<div class="rz_right detailDiv">
					<div class="bgblue">
						<h2>运单跟踪</h2>
						<input type="hidden" id="bid" value="${bid}"/>
					</div>
					<div id="_bmap" style="height:700px;width:100%;margin-top:10px">
					</div>
				</div>
				<!--个人中心右侧end-->
			</div>
		</div>
	</div>
	<!--内容部分end-->
	

	
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
	<!--底部end-->
	<script type="text/javascript" src="/resources/js/common/member/header_busi.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resources/js/bill/driver_detail.js"></script>
	<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=7wG9zl9ryQt25NHfHxMECnbScLmSSkKj"></script>
	<script type="text/javascript">
		// 百度地图API功能
		var map = new BMap.Map("_bmap");
		map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
		map.enableScrollWheelZoom(true);
		
		$.ajax({
    		url:"/trwuliu/billAppoint/trackdata",
			data:{id:$("#bid").val()},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					renderMap(rs.data);
				}else{
					alert(rs.error);
				}
			}
    	})
		
    	function renderMap(pArr){
			var l_pois =pArr.length;
			//发货地
			var p1 = new BMap.Point(parseP(pArr[0].lon),parseP(pArr[0].lat));
			//到货地
			var p2 = new BMap.Point(parseP(pArr[l_pois-1].lon),parseP(pArr[l_pois-1].lat));
			//途径地点
			var waypointsItem =[];
			$.each(pArr,function(idx,data){
				if(idx==0 || idx==(l_pois-1)){
					
				}else{
					var pItem = new BMap.Point(parseP(data.lon),parseP(data.lat));
					waypointsItem.push(pItem);
				}
			})
			
			var drivingOptions={renderOptions:{map: map, autoViewport: true,highlightMode:BMAP_HIGHLIGHT_ROUTE}};
			drivingOptions.onMarkersSet=function(pois){
				pois[0].title=pArr[0].proxyGps;
				pois[pois.length-1].title=pArr[l_pois-1].proxyGps;
			}
			
			var driving = new BMap.DrivingRoute(map, drivingOptions);
			driving.search(p1, p2,{waypoints:waypointsItem});//waypoints表示途经点
		}
		function parseP(aa){
			if(aa){
				return ((+aa)/1000000).toFixed(6); 
			}
			return 0;
		}
    	
	</script>
</body>
</html>
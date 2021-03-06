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
<title>轨迹测试</title>
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
					<label>当前位置：运单</label><span>></span> <label>轨迹测试</label>
				</div>
			</div>
			<div class="row">
				<!-- 引用公共left部分 -->
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
				<!--个人中心右侧begin-->
				<div class="rz_right detailDiv">
					<div class="bgblue">
						<h2>轨迹测试</h2>
						<input type="text" id="bid" onchange="init()" value="豫AC7336"/>
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
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/getscript?v=1.5&ak=7wG9zl9ryQt25NHfHxMECnbScLmSSkKj&services=&t=20150522094656"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=7wG9zl9ryQt25NHfHxMECnbScLmSSkKj"></script>
<script type="text/javascript">
	// 百度地图API功能
   	var map;
	$(function () {
		init();
	});
	
	function init(){
		// 百度地图API功能
		  map = new BMap.Map("_bmap");
		  map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
		  map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
		  map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
		  map.enableScrollWheelZoom();                            //启用滚轮放大缩小
		  map.addControl(new BMap.MapTypeControl());          //添加地图类型控件
		  map.centerAndZoom(new BMap.Point(114.309531, 30.59619),50);
		  showToolAutoDef(map);
	}
	
	function showToolAutoDef(map) { 
		$.ajax({
    		url:"/trwuliu/billAppoint/zjxltrackData",
			data:{vehicleNo:$("#bid").val()},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					var list = rs.data;
					var points = new Array();
					var lon;
					var lat;
					var nlon;
					var nlat
					//位置长度
					var length = list.length;
					for (var a = 0; a < list.length; a++) {
						lon = list[a].lon;
						lat = list[a].lat;
						addMarker(lon,lat);
							var thePoint1 = new BMap.Point(lon,lat);
							nlon = lon;
							nlat = lat
							points.push(thePoint1);
					}
					if(list.length==0){
						map.centerAndZoom(new BMap.Point(113.663221, 34.7568711),8);
					}else{
						map.centerAndZoom(new BMap.Point(nlon, nlat),8);
					}
					drawPolyline(map, points);
				}else{
					alert(rs.error);
				}
			}
    	})
				
	}
	
	//创建marker
	function addMarker(lng, lat){
		    var point = new BMap.Point(lng,lat);
		    var iconImg = createIcon();
		    var marker = new BMap.Marker(point,{icon:iconImg});
		    
		    var _marker = marker;
					_marker.addEventListener("click",function(){
					    alert(lng+" |  "+lat);
				    });
		    
		    map.addOverlay(marker);
	}
	
	/**
	 * 画线
	 * @param bMap
	 * @param points
	 */
	function drawPolyline(bMap, points) {
		if (points==null || points.length<=1) {
			return;
		}
		bMap.addOverlay(new BMap.Polyline(points, {
			strokeColor : "blue",
			strokeWeight : 3,
			strokeOpacity : 0.6
		})); // 画线
	}
	
	//创建一个Icon
	function createIcon(){
		var m = "bposition.png";
		var icon ;
			icon = new BMap.Icon("${trRoot}/tianrui/images/"+m, 
		        	new BMap.Size(100, 80), 
		        	{anchor: new BMap.Size(40, 40),
	        		 imageOffset: new BMap.Size(20, 20)});
		return icon;
	}
   	
</script>
</body>
</html>
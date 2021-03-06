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
					<label>当前位置：运单</label><span>></span> <label>运单跟踪</label>
				</div>
			</div>
			<div class="row">
				<!-- 引用公共left部分 -->
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
				<!--个人中心右侧begin-->
				<div class="rz_right detailDiv">
					<div class="car_title bgblue">
						<h2>运单跟踪</h2>
						<span>
							<select id="postType" style="width: 90px;float: right;font-size:16px;border: 0;color:#ffffff" class="bgblue" onchange="setPosition()">
								<option value="0">全部轨迹</option>
								<option value="1">中交轨迹</option>
								<option value="2">大易轨迹</option>
							</select>
						</span>
					</div>
					<input type="hidden" id="bid" value="${bid}"/>
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
	var polyline;
	$(function () {
	  // 百度地图API功能
	  map = new BMap.Map("_bmap");
	  map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
	  map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
	  map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
	  map.enableScrollWheelZoom();                            //启用滚轮放大缩小
	  map.addControl(new BMap.MapTypeControl());          //添加地图类型控件
	  
	  zjshowToolAutoDef();
	  dyshowToolAutoDef();
	});
	
	function setPosition(){
		polyline.hide();
		var allOverlay = map.getOverlays();
		for (var i = 0; i < allOverlay.length -1; i++){
			map.removeOverlay(allOverlay[i]);
		}
		var type = $("#postType").val();
		if(type == 0 ){
			//全部轨迹
			zjshowToolAutoDef();
			dyshowToolAutoDef();
		}else if(type == 1 ){
			//中交轨迹
			zjshowToolAutoDef();
		}else if(type == 2 ){
			//大易轨迹
			dyshowToolAutoDef();
		}
	}
	
	function zjshowToolAutoDef() { 
		$.ajax({
    		url:"/trwuliu/billAppoint/zjPositiondata",
			data:{id:$("#bid").val()},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					position(rs.data,"blue","卫星获取");
				}else{
					alert(rs.error);
				}
			}
    	})
				
	}
	function dyshowToolAutoDef() { 
		$.ajax({
    		url:"/trwuliu/billAppoint/dyPositiondata",
			data:{id:$("#bid").val()},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					position(rs.data,"red","手机获取");
				}else{
					alert(rs.error);
				}
			}
    	})
				
	}
	
	function position(data,colour,ptype){
		var list = data;
		var points = new Array();
		var lon;
		var lat;
		var nlon;
		var nlat
		//位置长度
		var length = list.length;
		//运单状态
		var billStatus = list[0].billStatus;
		console.log("billStatus="+billStatus);
		for (var a = 0; a < list.length; a++) {
			lon = list[a].lon/1000000;
			lat = list[a].lat/1000000;
			addMarker(lon,lat,list[a].status,list[a].createtime,ptype);
			var thePoint1 = new BMap.Point(lon,lat);
			nlon = lon;
			nlat = lat;
			if(list[a].status != "1" && list[a].status != "4"){
				nlon = lon;
				nlat = lat;
				points.push(thePoint1);
			}
		}
		map.centerAndZoom(new BMap.Point(nlon, nlat),14);
		drawPolyline(map, points,colour);
	}
	
	//创建marker
	function addMarker(lng, lat, status, time ,ptype){
		    var point = new BMap.Point(lng,lat);
		    var iconImg = createIcon(status,ptype);
		    var marker = new BMap.Marker(point,{icon:iconImg});
		    var ltime = "<br>";
		    if(time){
		    ltime = "<br>跟踪时间："+ new Date(time).format("yyyy-MM-dd hh:mm:ss")
		    }
		    var _marker = marker;
					_marker.addEventListener("click",function(){
						 openInfo(lng,lat, ptype +"<br>车牌号：${vehicle}<br>司机：${driver}<br>司机电话：${drivertel}<br>经度："+lng+"<br>纬度："+lat+ltime);
				    });
		    
		    map.addOverlay(marker);
	}
	
	var opts = {
			width : 250,     // 信息窗口宽度
			height: 120,     // 信息窗口高度
			title : "轨迹信息" , // 信息窗口标题
			enableMessage:true//设置允许信息窗发送短息
		   };
	function openInfo(lng,lat,addr){
		var point = new BMap.Point(lng, lat);
		var infoWindow = new BMap.InfoWindow(addr,opts);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}
	
	/**
	 * 画线
	 * @param bMap
	 * @param points
	 */
	function drawPolyline(bMap, points,colour) {
		if (points==null || points.length<=1) {
			return;
		}
		polyline = new BMap.Polyline(points, {
			strokeColor : colour,
			strokeWeight : 3,
			strokeOpacity : 0.6
		});
		bMap.addOverlay(polyline); // 画线
	}
	
	//创建一个Icon
	function createIcon(status,ptype){
		var m = "bposition"+status+".png";
		var icon ;
		if(status!=""){
			icon = new BMap.Icon("${trRoot}/tianrui/images/"+m, 
		        	new BMap.Size(100, 80), 
		        	{anchor: new BMap.Size(40, 40),
	        		 imageOffset: new BMap.Size(20, -3)});
		}else{
			if(ptype == "手机获取"){
				m = "bposition_r.png";
			}
			icon = new BMap.Icon("${trRoot}/tianrui/images/"+m, 
		        	new BMap.Size(100, 80), 
		        	{anchor: new BMap.Size(40, 40),
	        		 imageOffset: new BMap.Size(20, 20)});
		}
		return icon;
	}
</script>
</body>
</html>
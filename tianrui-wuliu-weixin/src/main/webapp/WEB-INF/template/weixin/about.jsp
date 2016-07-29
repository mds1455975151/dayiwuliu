<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>About</title>
<!--Custom Theme files-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--Custom Theme files-->
<link href="/resources/weixinpage/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link type="text/css" rel="stylesheet" href="/resources/weixinpage/css/jquery.mmenu.all.css" />
<!--//Custom Theme files-->
<!--web-fonts-->
<link href='http://fonts.useso.com/css?family=Jura:400,300,500,600' rel='stylesheet' type='text/css'>
<!--//web-fonts-->
<!--js-->
<script src="/resources/weixinpage/js/jquery-1.11.1.min.js"></script> 
<script type="text/javascript" src="/resources/weixinpage/js/jquery.mmenu.min.all.js"></script>
<!-- //js -->
</head>
<body>
		<div class=" mm-wrapper"  id="page">
			<div class="push-menu ">
				<div class="banner">
					<div class="banner-text">
						<div class="menu">
							<a href="#menu"><img src="/resources/weixinpage/images/menu-icon.png" alt=""/></a>
							<nav id="menu">
								<ul class="">
									<li class="menu-title">MENU</li>
									<li><a href="#"><span class="icons"> </span>Dashboard</a></li>
									<li><a href="#"><span class="icons icn2"> </span> Activity</a></li>
									<li><a href="#"><span class="icons icn3"> </span> Tasks</a></li>
									<li><a href="#"><span class="icons icn4"> </span> Gallery</a></li>
									<li><a href="#"><span class="icons icn5"> </span> Account settings</a></li>
								</ul> 	
							</nav>
								<script type="text/javascript">
									$(function() {
										$("#menu")
											.mmenu({
								extensions 	: [ "theme-dark", "effect-listitems-slide" ],
								iconPanels	: {
									add 		: true,
									visible		: 1
								},
								navbar		: {
									add 		: false
								},
								counters	: true
											}).on( 'click',
												'a[href^="#/"]',
												function() {
													alert( "Thank you for clicking, but that's a demo link." );
													return false;
												}
											);
									});
								</script>
							<!-- /script-for-menu -->
						</div>
						<div class="title">
							<div class="title-left">
								<img src="${headimgurl }" alt=""/>
							</div>
							<div class="title-right">
								<h2>${session_member.cellphone } </h2>
								<c:if test="${session_member.driverpercheck eq '1' }">
									<h6>司机用户</h6>
								</c:if>
								<c:if test="${session_member.userpercheck eq '1' }">
									<h6>个人用户</h6>
								</c:if>
								<c:if test="${session_member.companypercheck eq '1' }">
									<h6>企业用户</h6>
								</c:if>
							</div>
							<div class="clear"> </div>
						</div>
					</div>
				</div>
				<div class="clear"> </div>
				<div class="banner-bottom">
					<div class="banner-bottom-left">
						<h3>13</h3>
						<p>Remaining tasks </p>
					</div>
					<div class="banner-bottom-right">
						<h3>25</h3>
						<p>Completed tasks</p>
					</div>
					<div class="clear"> </div>
				</div>
				<div class="work-text">
					<h3>TODAY</h3>
					<section class="ac-container">
						<div>
							<input id="ac-1" name="accordion-1" type="checkbox" />
							<label for="ac-1" class="grid1"><i></i>Finish landing page concept</label>
							<article class="ac-small">
								<p>Lorem Ipsum is simply dummy text of the printing and industry.</p>
							</article>
						</div>
						<div>
							<input id="ac-2" name="accordion-1" type="checkbox" />
							<label for="ac-2" class="grid2"><i></i>Design app illustrations</label>
							<article class="ac-small">
								<p>Lorem Ipsum is simply dummy text of the printing and industry.</p>
							</article>
						</div>
						<div>
							<input id="ac-3" name="accordion-1" type="checkbox" />
							<label for="ac-3" class="grid3"><i></i>Javascript training</label>
							<article class="ac-small">
								<p>Lorem Ipsum is simply dummy text of the printing and industry.</p>
							</article>
						</div>
						<div>
							<input id="ac-4" name="accordion-1" type="checkbox" />
							<label for="ac-4" class="grid4"><i></i>Surprise Party for Meet</label>
							<article class="ac-small">
								<p>Lorem Ipsum is simply dummy text of the printing and industry.</p>
							</article>
						</div>
					</section>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
	//下拉刷新
	//文档高度
	function getDocumentTop() {
		var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
		if (document.body) {
			bodyScrollTop = document.body.scrollTop;
		}
		if (document.documentElement) {
			documentScrollTop = document.documentElement.scrollTop;
		}
		scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
		return scrollTop;
	}
	
	//可视窗口高度
	function getWindowHeight() {
		var windowHeight = 0;
		if (document.compatMode == "CSS1Compat") {
			windowHeight = document.documentElement.clientHeight;
		} else {
			windowHeight = document.body.clientHeight;
		}
		return windowHeight;
	}
	
	//滚动条滚动高度
	function getScrollHeight() {
		var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
		if (document.body) {
			bodyScrollHeight = document.body.scrollHeight;
		}
		if (document.documentElement) {
			documentScrollHeight = document.documentElement.scrollHeight;
		}
		scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
		return scrollHeight;
	}
	
	window.onscroll = function () {
		//监听事件内容
		if(getDocumentTop() + getWindowHeight() >= getScrollHeight()){
		//当滚动条到底时,这里是触发内容
		//异步请求数据,局部刷新dom
			alert("滑到底啦");
		}
	}
	
	


</script>
</html>
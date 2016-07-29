<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>天瑞物流</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="images/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link href="css/base.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="css/sm.css">
</head>
<body>
<!-- SUI 入口beigin-->
<div class="page-group">
    <div class="page page-current">
        <!-- 你的html代码 -->
        <header class="bar bar-nav bghead">
            <img src="images/logo.png">
            <a class="pull-right" href="#"> 登录</a>
        </header>
        <div class="content">
            <div class="buttons-tab">
                <a href="#tab1" class="tab-link active button">全部</a>

            </div>
            <div class="content-block">
                <div class="tabs">
                    <div id="tab1" class="tab active">
                        <div class="content-block">
                            <!-- content应该拥有"pull-to-refresh-content"类,表示启用下拉刷新 -->
                            <div class=" pull-to-refresh-content" data-ptr-distance="55">
                                <!-- 默认的下拉刷新层 -->
                                <div class="pull-to-refresh-layer">
                                    <div class="preloader"></div>
                                    <div class="pull-to-refresh-arrow"></div>
                                </div>
                                <!-- 下面是正文 -->
                                <div class="card-container">
                                    <div class="card">
                                        <div class="card-header">card</div>
                                        <div class="card-content">
                                            <div class="card-content-inner">
                                                这里是第1个card，下拉刷新会出现第2个card。
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-container">
                                    <div class="card">
                                        <div class="card-header">card</div>
                                        <div class="card-content">
                                            <div class="card-content-inner">
                                                这里是第1个card，下拉刷新会出现第2个card。
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-container">
                                    <div class="card">
                                        <div class="card-header">card</div>
                                        <div class="card-content">
                                            <div class="card-content-inner">
                                                这里是第1个card，下拉刷新会出现第2个card。
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-container">
                                    <div class="card">
                                        <div class="card-header">card</div>
                                        <div class="card-content">
                                            <div class="card-content-inner">
                                                这里是第1个card，下拉刷新会出现第2个card。
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-container">
                                    <div class="card">
                                        <div class="card-header">card</div>
                                        <div class="card-content">
                                            <div class="card-content-inner">
                                                这里是第1个card，下拉刷新会出现第2个card。
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-container">
                                    <div class="card">
                                        <div class="card-header">card</div>
                                        <div class="card-content">
                                            <div class="card-content-inner">
                                                这里是第1个card，下拉刷新会出现第2个card。
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-container">
                                    <div class="card">
                                        <div class="card-header">card</div>
                                        <div class="card-content">
                                            <div class="card-content-inner">
                                                这里是第1个card，下拉刷新会出现第2个card。
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-container">
                                    <div class="card">
                                        <div class="card-header">card</div>
                                        <div class="card-content">
                                            <div class="card-content-inner">
                                                这里是第1个card，下拉刷新会出现第2个card。
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                    <div id="tab2" class="tab">
                        <div class="content-block">
                            <p>This is tab 2 content</p>
                        </div>
                    </div>
                    <div id="tab3" class="tab">
                        <div class="content-block">
                            <p>This is tab 3 content</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery-1.11.1.js"></script>
<!-- 默认必须要执行$.init(),实际业务里一般不会在HTML文档里执行，通常是在业务页面代码的最后执行 -->
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
		alert("滑到底啦");
		//监听事件内容
		if(getDocumentTop() + getWindowHeight() >= getScrollHeight()){
		//当滚动条到底时,这里是触发内容
		//异步请求数据,局部刷新dom
			alert("滑到底啦");
		}
	}
	

</script>
</html>
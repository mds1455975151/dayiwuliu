<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
         <!--增加最大高度，数据多出滚动条-->
	    <style>
	     .ui-autocomplete {
		    max-height: 200px;
		    overflow-y: auto;
		    /* 防止水平滚动条 */
		    overflow-x: hidden;
		    padding-bottom: 10px;
		  }
    	</style>
    <!--公共头部begin-->
    <input type="hidden" id="menuId" value="${menuId}">
    <div class="row">
    	<div class="admin_head">
	        <div class="col-md-2 bghead">
	            <div class="header_logo">
	                <img src="${imagesRoot }/logo2.png">
	            </div>
	        </div>
	        <div class="col-md-10 bghead">
	            <!--start-top-serch
	            <div id="search">
	                <input type="text" placeholder="请输入搜索内容"/>
	                <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
	            </div>
	            -->
	            <!--close-top-serch-->
	            <!--start-top-Header-menu-->
	            <div id="user-nav" class="">
	                <ul class="headnav">
	                    
	                </ul>
	            </div>
	            <!--close-top-Header-menu-->
	        </div>
    	</div>
        
    </div>

    <!--公共头部end-->
    <!--侧边栏begin-->
    <div class="row">
        <!--后台整体布局begin-->
        <div class="ht">
            <!--后台左侧布局begin-->
	        <div class="col-md-2 bgleft">
	            <div id="sidebar">
	            </div>
	        </div>
        	<!--后台左侧布局end-->


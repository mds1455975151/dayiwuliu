<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>统计路线管理</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css" rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css" rel="stylesheet">
    <link href="${trRoot}/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
	<style type="text/css">
	.inputStyle{
		width: 80px
	}
	.inputdata{
		width: 70px
	}
	</style>
</head>
<body>

<div class="container-fluid">
     <input type="hidden" id="recPage" value="${pageNo }">
     <!--公共头部begin-->
    <jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
            <!--后台右侧布局begin-->
           <div class="col-md-10 ">
                <div class="ht_content">
                    <div id="content-header">
                        <h3>路线管理</h3>
                    </div>
                    <!--查询框begin-->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="contuser_search">
                                <div class="ht_div">
                                    <label>业务类别：</label>
                                    <select id="routetype">
                                    	<option value="熟料运输业务">熟料运输业务</option>
                                    	<option value="原煤运输业务">原煤运输业务</option>
                                    	<option value="水泥运输业务">水泥运输业务</option>
                                    </select>
                                </div>
                            </div>
                            <div class="contuser_search">
                                <div class="ht_divbtn">
                                    <button class="btn btnblue " onclick="index()" type="button">搜索</button>
                                </div>
                            </div>
                    </div>
                    </div>
                    <!--查询框end-->
                    <div class="row mt15">
                        <div class="col-md-12">
                            <div class="content-user">
								<!-- 表格原煤 -->
								<table class="table table-bordered" id="table_0">
						            <thead>
						            <tr>
						                <th rowspan="2">运输业务	</th>
						                <th rowspan="2">运输地点	</th>
						                <th colspan="2">卫辉</th>
						                <th colspan="2">汝州 </th>
						                <th colspan="2">南召</th>
						                <th colspan="2">荥阳</th>
						                <th colspan="2"> 光山</th>
						                <th colspan="2"> 禹州</th>
						                <th colspan="2"> 萧县</th>
						                <th rowspan="2"> 操作</th>
						            </tr>
						            <tr>
						                <th >运距（公里）</th>
						                <th>年次车数 </th>
						                <th >运距（公里）</th>
						                <th>年次车数 </th>
						                <th >运距（公里）</th>
						                <th>年次车数 </th>
						                <th >运距（公里）</th>
						                <th>年次车数 </th>
						                <th >运距（公里）</th>
						                <th>年次车数 </th>
						                <th >运距（公里）</th>
						                <th>年次车数 </th>
						                <th >运距（公里）</th>
						                <th>年次车数 </th>
						
						            </tr>
						            </thead>
						            <tbody id="html_table_0">
						            
						            </tbody>
						        </table>
						        <!-- 表格熟料 -->
						        <table class="table table-bordered"  id="table_2">
					            <thead>
					            <tr>
					                <th rowspan="2">运输业务	</th>
					                <th rowspan="2">运输地点	</th>
					                <th colspan="2">中牟</th>
					                <th colspan="2">商丘 </th>
					                <th colspan="2">周口</th>
					                <th colspan="2">姚电</th>
					                <th colspan="2"> 许昌</th>
					                <th colspan="2"> 诚兴</th>
					                <th colspan="2"> 威企</th>
					                <th rowspan="2"> 操作</th>
					            </tr>
					            <tr>
					                <th >运距（公里）</th>
					                <th>年次车数 </th>
					                <th >运距（公里）</th>
					                <th>年次车数 </th>
					                <th >运距（公里）</th>
					                <th>年次车数 </th>
					                <th >运距（公里）</th>
					                <th>年次车数 </th>
					                <th >运距（公里）</th>
					                <th>年次车数 </th>
					                <th >运距（公里）</th>
					                <th>年次车数 </th>
					                <th >运距（公里）</th>
					                <th>年次车数 </th>
					
					            </tr>
					            </thead>
					            <tbody id="html_table_2">
					            </tbody>
					        </table>
					        <!-- 表格水泥 -->
					        <table class="table table-bordered" id="table_4">
				            <thead>
				            <tr>
				                <th rowspan="2">运输业务	</th>
				                <th rowspan="2">运输地点	</th>
				                <th colspan="2">辽中</th>
				                <th colspan="2">阜新 </th>
				                <th colspan="2">徐州</th>
				                <th colspan="2">亳州</th>
				                <th colspan="2">荥阳</th>
				                <th colspan="2"> 马市坪</th>
				                <th colspan="2"> 鲁山</th>
				                <th rowspan="2"> 操作</th>
				            </tr>
				            <tr>
				                <th >运距（公里）</th>
				                <th>年次车数 </th>
				                <th >运距（公里）</th>
				                <th>年次车数 </th>
				                <th >运距（公里）</th>
				                <th>年次车数 </th>
				                <th >运距（公里）</th>
				                <th>年次车数 </th>
				                <th >运距（公里）</th>
				                <th>年次车数 </th>
				                <th >运距（公里）</th>
				                <th>年次车数 </th>
				                <th >运距（公里）</th>
				                <th>年次车数 </th>
				
				            </tr>
				            </thead>
				            <tbody id="html_table_4">
				            </tbody>
				        </table>
                            </div>
                        </div>
                    </div>
                  <div class="clear"> </div>
                    
                </div>
            </div>
            <!--后台右侧布局end-->
           </div>
            <!--后台整体布局end-->
         </div>
    <!--侧边栏end-->
    </div>

<%@include file="../../common/footer.jsp" %>
<script type="text/javascript">
	var CONTEXTPATH="${path}";
	var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="${trRoot}/js/jquery-1.11.1.js"></script>

<script type="text/javascript" src="/resources/js/count1/route.js" ></script>

</body>
</html>
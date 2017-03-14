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
    <title>安联运单管理</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css" rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css" rel="stylesheet">
    <link href="${stylesRoot }/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="Shortcut Icon" href="${imagesRoot}/favicon.ico" type="image/x-icon">
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
	<style type="text/css">
		.assessBody {
		    height: 35px;
		    line-height: 35px;
		}
		
		.assessBody label {
		    margin-left: 40px;
		}
		
		.assessBody label input {
		    vertical-align: text-top;
		    margin-top: 2px;*
		    margin-bottom:-4px;
		    margin-right: 10px;
		}
	</style>
</head>
<body>

<div class="container-fluid">
 <!--公共头部begin-->
    <jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
    <!--后台右侧布局begin-->
    <div class="col-md-10 ">
        <div class="ht_content">
            <div id="content-header">
                <h3>运单管理</h3>
            </div>
            <!--查询框begin-->
            <div class="row">
                <div class="col-md-12">
                    <div class="contuser_search">
                        <div class="ht_div">
                            <label>运单编码：</label>
                            <input type="text" id="searchKey" placeholder=" ">
                        </div>
                    <div class="ht_divbtn">
                        <button class="btn btnblue " onclick="searchFile();" type="button">搜索</button>
                    </div>
                </div>
            </div>
            <!--查询框end-->
            <div class="row mt15">
                <div class="col-md-12">
                    <div class="content-user">
                        <!--用户表格begin-->
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>运单号</th>
                                <th>车辆信息</th>
                                <th>始发地</th>
                                <th>目的地</th>
                                <th>收货人</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="innhml">
                            </tbody>
                        </table>
                        <!--用户表格end-->
                    </div>
                </div>
            </div>
          					  <!-- 分页部分  开始-->
						            <div class="row pr20 fr">
										<%@include file="../../common/pagination.jsp" %>
						            </div>
						         <!-- 分页部分 结束 -->
        </div>
    </div>
    <!--后台右侧布局end-->
</div>
<!--后台整体布局end-->
</div>
<!--侧边栏end-->

<!--查看详情begin-->
<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">运单详情</h4>
            </div>
            <div class="modal-body" style=" " id="dateilshml">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--查看详情end-->
<%@include file="../../common/footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        // 表格列宽度手动调整
        $("table").resizableColumns({});
    });
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="/resources/js/waybill/file_anlian_bill.js?03.14" ></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
</body>
</html>
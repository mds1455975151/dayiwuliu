<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="${basePath}">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>档案管理-位置档案</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css"  rel="stylesheet">
    <link href="${stylesRoot }/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link href="${stylesRoot }/imgcut.css" rel="stylesheet">
    <link href="${stylesRoot }/stylevalid.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
    <style type="text/css">
/*城市弹出层*/
.citybox {
	width: 258px;
	overflow-y: auto;
	border: solid 1px #ccc;
	background: #fff;
	position: absolute;
	top: 80px;
	left: 120px;
	z-index: 7;
}

.citybox ul {
	height: 32px;
}

.citybox li {
	width: 85px;
	height: 30px;
	line-height: 30px;
	text-align: center;
	border-right: solid 1px #ccc;
	border-bottom: solid 1px #ccc;
	float: left;
	background: #f0f0f0;
	color: #444;
}

.citybox li.no-border-right {
	border-right: none;
}

.citybox li.active {
	border-bottom: none;
	background: #fff;
	color: #ff6600;
}

.citybox li:hover {
	cursor: pointer;
}

.cityTags {
	padding: 10px 10px 0 10px;
}

.cityTags dl {
	width: 100%;
	display: block;
	clear: both;
}

.cityTags dt {
	width: 35px;
	padding-top: 0px;
	float: left;
	font-size: 14px;
	color: #ff6600;
}

.cityTags dd {
	word-wrap: break-word;
	word-break: normal;
	float: left;
}

.cityTags a {
	line-height: 22px;
	margin: 0 0 0 10px !important;
	color: #888;
	display: block;
	float: left;
}

.cityTags a:hover {
	color: #444;
}

.clear-data {
	padding: 10px;
	text-align: center;
}

.f12 {
	font-size: 12px
}

/*地图选点*/
.layoutSearch {
	width: 100%;
	height: 50px;
	border-radius: 0px;
	background-color: #1d1d1d;
}

.Layout {
	display: none;
	width: 900px;
	height: 550px;
	position: fixed;
	z-index: 9999;
}

.Layout label {
	color: #fff;
	line-height: 34px;
	font-size: 14px;
}

.Layout .input-group .form-control:first-child {
	border-radius: 0px;
}

.Layout .btn-info, .Layout .btn-info:hover, .Layout .btn-info:focus,
	.Layout .btn-info.focus, .Layout .btn-info:active, .Layout .btn-info.active,
	.Layout .open>.dropdown-toggle.btn-info {
	color: #795548;
	background-color: #ffd200;
	border-color: #ffd200;
}

.Layout .btn {
	border-radius: 0;
}

.LayoutClose {
	width: 40px;
	height: 100%;
	float: right;
	padding: 18px 0 0 10px;
}

#addressContainer>div {
	border: none !important;
	max-height: 650px;
}

.mapDiv {
	position: relative;
	width: 900px;
	float: right;
}

.mapLayout {
	border-radius: 0;
}

.LayoutClose a {
	color: #fff;
}

.LayoutClose a:hover {
	color: #ccc;
}

/*地图弹出层*/
.CCity {
	position: absolute;
	right: 30px;
	top: 20px;
	z-index: 100;
	display: block;
}

.CCity>img {
	border-radius: 3px;
}

.CCity:hover input {
	color: #0078b6;
}

.CCity:hover i {
	background: url(../Images/icons.png) no-repeat 0 -86px;
	-webkit-transform: rotate(180deg);
	-moz-transform: rotate(180deg);
	-ms-transform: rotate(180deg);
	-o-transform: rotate(180deg);
	transform: rotate(180deg);
}

.CCity i {
	width: 7px;
	height: 4px;
	background: url(../Images/icons.png) no-repeat 0 -86px;
	display: block;
	position: absolute;
	right: 12px;
	top: 11px;
	z-index: 101;
	-webkit-transition: .3s ease-in;
	-moz-transition: .3s ease-in;
	-o-transition: .3s ease-in;
	transition: .3s ease-in;
}

body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form,
	fieldset, input, textarea, p, blockquote, th, td {
	margin: 0;
	padding: 0;
}

html {
	min-height: 100%;
	position: relative;
}

ul {
	clear: both;
/* 	overflow: hidden; */
	width: 100%;
}

ul, li {
	list-style: none;
}

.form-control {
	height: 32px;
}

.btn, button {
	outline: none !important;
}
</style>
</head>
<body>

<div class="container-fluid">
	<jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
    <!--后台右侧布局begin-->
    <div class="col-md-10 ">
        <div class="ht_content">
            <div id="content-header">
                <h3>位置档案</h3>
            </div>
            <!--查询框begin-->
            <div class="row">
                <div class="col-md-12">
                    <div class="contuser_search">
                        <div class="ht_div">
                            <label >名称：</label>
                            <input id="organizationname_search" type="text" placeholder="位置名称" class="routeName">
                        </div>
                        <button class="btn btn-primary btn-sm searchbtn" type="submit" >搜索</button>
                        <button class="btn btn-success btn-sm resetbtn" type="submit" >重置</button>
                    </div>
                </div>
            </div>
            <!--查询框end-->
            <div class="row mt15">
                <div class="col-md-12">
                    <div class="content-user fileRouteDiv">
                        <div class="content-tou addDiv">
                            <button><i class="glyphicon glyphicon-plus"></i><span>新增</span></button>
                        </div>
                        <!--用户表格begin-->
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>名称</th>
                                <th>行政区域</th>
                                <th>详细地点</th>
                                <th>地图坐标</th>
                                <th>创建者</th>
                                <th>状态</th>
                                <th>备注</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="org_tbody">
                         
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


<!-- 新增 start -->
<div class="modal fade" id="addModal"  role="dialog"  >
    <div class="modal-dialog" role="document">
    	<form id="addForm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" ><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title titleSpan">新增</h4>
            </div>
            <div class="modal-body">
              	<!-- body内容 -->
              	<div class="usermodal userlabel4">
                    <label>名称：</label><input class="nameInput formV" name="name" type="text" dataType="s2-18" >
                     <input type="hidden" class="formV id" name="id"/>
                </div>
              	<div class="usermodal userlabel4">
                    <label>行政区域：</label><input class="pcaInput" id="addpca"  type="text" readonly="readonly" >
                    <input type="hidden" class="formV op" name="op"  id="opcAdd" dataType="*" />
                    <input type="hidden" class="formV opc" name="opc"/>
                    <input type="hidden" class="formV oc" name="oc" id="occAdd"/>
                    <input type="hidden" class="formV occ" name="occ" />
                    <input type="hidden" class="formV oa" name="oa" id="oacAdd"/>
                    <input type="hidden" class="formV oac" name="oac" />
                </div>
              	<div class="usermodal userlabel4">
                    <label>具体地点：</label><input class="addrInput formV" name="addr" type="text" readonly="readonly"  data-backdrop="static" >
                    <input type="hidden" class="formV lat" name="lat"  dataType="*"/>
                    <input type="hidden" class="formV lon" name="lng"/>
                </div>
              	<div class="usermodal userlabel4">
                    <label>备注：</label>
                    <input class="formV remark" name="remark" type="text"  data-backdrop="static" >
                </div>
                <!-- body内容  end -->
            </div>
            <div class="modal-footer">
                <button type="button"  class="btn btn-primary addSubBtn">确定</button>
                <button type="button"  class="btn btn-default" data-dismiss="modal" >取消</button>
            </div>
        </div>
        </form>
    </div>
</div>
<!-- 新增 end -->



<%@include file="../../common/footer.jsp" %>
<script type="text/javascript" src="${base}/resources/js/utils/cityselect/cityDatas.js"></script>
<script type="text/javascript" src="${base}/resources/js/utils/cityselect/citySelect.js"></script>
<script type="text/javascript" src="${base}/resources/js/utils/baidumap/baidukey.js"></script>
<script type="text/javascript" src="/resources/js/utils/baidumap/baiduDrawing.js"></script>
<script type="text/javascript" src="/resources/js/utils/baidumap/mapSetPoint.js"></script>
<script type="text/javascript" src="/resources/js/jqueryvalid/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="/resources/js/fileposition/main.js?38.10"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // 表格列宽度手动调整
        $("table").resizableColumns({});
    });
    var CONTEXTPATH = "${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
</body>
</html>
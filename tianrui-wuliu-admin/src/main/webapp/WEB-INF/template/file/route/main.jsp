<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="${basePath}">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>档案管理-路径档案</title>
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
</head>
<body>

<div class="container-fluid">
	<jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
    <!--后台右侧布局begin-->
    <div class="col-md-10 ">
        <div class="ht_content">
            <div id="content-header">
                <h3>路径档案</h3>
            </div>
            <!--查询框begin-->
            <div class="row">
                <div class="col-md-12">
                    <div class="contuser_search">
                        <div class="ht_div">
                            <label >路径名称：</label>
                            <input id="organizationname_search" type="text" placeholder="路径名称" class="routeName">
                        </div>
                        <div class="ht_div">
                            <label >路径状态：</label>
                            <select  class="routeStatus form-control" >
                            	<option value="">全部</option>
                            	<option value="1">启用</option>
                            	<option value="0">禁用</option>
                            </select>
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
                        <div class="content-tou">
                            <button data-toggle="modal" data-target="#addModal">
                            <i class="glyphicon glyphicon-plus"></i><span>新增</span></button>
                            <button id="batchDisable"><i class="iconfont icon-tingyong"></i><span>批量停用</span></button>
                        </div>
                        <!--用户表格begin-->
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th data-options="field:'ck',checkbox:true"><input type="checkbox" onclick="selectAll()" name="allUser"></th>
                                <th>路径名称</th>
                                <th>发货地</th>
                                <th>收货地</th>
                                <th>距离</th>
                                <th>发货人</th>
                                <th>收货人</th>
                                <th>状态</th>
                                <th>操作人</th>
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
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<form id="addForm">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">详情</h4>
            </div>
            <div class="modal-body">
              <!-- body内容 -->
              	<div class="usermodal userlabel4">
                    <label>名称：</label>
                    <input class="routename formV" name="routename" type="text"   dataType="s2-18" >
                    <input type="hidden" class="formV id" name="id"/>
                </div>
              	<div class="usermodal userlabel4">
                    <label>发货地点：</label>
                    <input class="oaddr formV" name="oaddr"  type="hidden" >
                    <select class="form-control opositionid formV" name="opositionid" dataType="*">
                    	<option value="">请选择</option>
                    	<c:forEach items="${positionList}" var="item"  varStatus="status">
                    		<option value="${item.id}"><c:out value="${item.name}"/></option>
                    	</c:forEach>
                    </select>
                </div>
              	<div class="usermodal userlabel4">
                    <label>发货人：</label><input class="sendpersion formV" name="sendpersion"  type="text" dataType="s2-18" />
                </div>
              	<div class="usermodal userlabel4">
                    <label>发货电话：</label><input maxlength="11" class="sendpersionphone formV" name="sendpersionphone"  type="text" dataType="m" />
                </div>
              	<div class="usermodal userlabel4">
              		 <label>收货地点：</label>
              		 <input class="daddr formV" name="daddr"  type="hidden" >
                     <select class="form-control dpositionid formV" name="dpositionid" dataType="*" >
                    	<option value="">请选择</option>
                   		<c:forEach  var="item"   items="${positionList}"  >
                   			<option value="${item.id}">${item.name}</option>
                   		</c:forEach>
                    </select>
                </div>
              	<div class="usermodal userlabel4">
                    <label>收货账号：</label>
                    <select class="form-control receiveid formV" id="receiveid_req" name="receiveid" dataType="*" >
                    	<option value="">请选择</option>
                   		<c:forEach  var="item"   items="${signer}"  >
                   			<option value="${item.id}">${item.cellphone}</option>
                   		</c:forEach>
                    </select>
                    <input class="receivepersionphone formV" id="receivepersionphone_req" name="receivepersionphone"  type="hidden" dataType="m" >
                </div>
              	<div class="usermodal userlabel4">
                    <label>收货人：</label>
                    <input class="receivepersion formV" id="receivepersion_req" name="receivepersion" readonly="readonly"  type="text">
                </div>
              	<div class="usermodal userlabel4">
                    <label>距离：</label><input class="distanceStr formV" name="distanceStr"  type="text" dataType="n">
                </div>
                <!-- body内容  end -->
            </div>
            <div class="modal-footer">
                <button type="button"  class="btn btn-primary submutBtn">确定</button>
                <button type="button"  class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
    </form>
</div>
<!-- 新增 end -->



<%@include file="../../common/footer.jsp" %>
<script type="text/javascript" src="/resources/js/jqueryvalid/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="/resources/js/fileroute/main.js?0930"></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
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
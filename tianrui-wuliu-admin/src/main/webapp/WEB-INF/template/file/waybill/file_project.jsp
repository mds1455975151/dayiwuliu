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
    <title>平台计划管理</title>
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
    <%-- <link href="${imagesRoot}/imgcut.css" rel="stylesheet"> --%>
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
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
                <h3>计划管理</h3>
            </div>
            <!--查询框begin-->
            <div class="row">
                <div class="col-md-12">
                    <div class="contuser_search">
                        <div class="ht_div">
                            <label>计划编码：</label>
                            <input id="planCode" type="text" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>货物名称：</label>
                            <input id="cargoName" type="text" placeholder=" ">
                        </div>
                    </div>
                    <div class="contuser_search">
                        <div class="ht_div">
                            <label>车主姓名：</label>
                            <input id="vichOwner" type="text" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>创建时间：</label>
                            <input type="text" id="createfor"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" class="Wdate" style="width:160px"/>
                            <i>-</i>
                            <input type="text" id="createend"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" class="Wdate" style="width:160px"/>
                        </div>
                        <div class="ht_div">
                            <label>状态：</label>
                            <select id="Sstatus" class="form-control">
                                <option value="">请选择</option>
                                <option value="-1">已回收</option>
                                <option value="0">待接单</option>
                                <option value="1">已拒绝</option>
                                <option value="2">执行中</option>
                                <option value="3">已完成</option>
                            </select>
                        </div>
                    </div>
                    <div class="ht_divbtn">
                        <button class="btn btnblue " onclick="searchPlan();" type="button">搜索</button>
                        <button class="btn btngreen" onclick="clearSerach();" type="submit">重置</button>
                    </div>
                </div>
            </div>
            <!--查询框end-->
            <div class="row mt15">
                <div class="col-md-12">
                    <div class="content-user">
<!--                         <div class="content-tou">
                            <button><i class="iconfont icon-daochu1"></i><span>导出</span></button>
                        </div> -->
                        <!--用户表格begin-->
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>计划编码</th>
                                <th>组织名称</th>
                                <th>创建人</th>
                                <th>货物名称</th>
                                <th>车主</th>
                                <th>创建时间</th>
                                <th>状态</th>
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
            <div class="row pr20 fr">
                			<!-- 分页部分  开始-->
						            <div class="row pr20 fr">
										<%@include file="../../common/pagination.jsp" %>
						            </div>
						         <!-- 分页部分 结束 -->
            </div>
        </div>
    </div>
    <!--后台右侧布局end-->
</div>
<!--后台整体布局end-->
</div>
<!--侧边栏end-->
</div>

<!--查看详情begin-->
<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">计划详情</h4>
            </div>
            <div class="modal-body" style=" " id="detailhml">
               
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--查看详情end-->
<!--启用begin-->
<div class="modal fade" id="qiyong" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
            	<input type="hidden" id="status" value="">
            	<input type="hidden" id="uptid" value="">
                <h4><span id="massage"></span></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="changeStatus();" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--启用end-->
<%@include file="../../common/footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        // 表格列宽度手动调整
        $("table").resizableColumns({});
    });
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="/resources/js/waybill/file_project.js" ></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
</body>
</html>
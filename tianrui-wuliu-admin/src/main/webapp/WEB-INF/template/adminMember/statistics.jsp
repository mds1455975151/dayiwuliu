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
    <title>审核统计管理</title>
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
</head>
<body>

<div class="container-fluid">
     <input type="hidden" id="recPage" value="${pageNo }">
     <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
            <!--后台右侧布局begin-->
           <div class="col-md-10 ">
                <div class="ht_content">
                    <div id="content-header">
                        <h3>审核统计</h3>
                    </div>
                    <!--查询框begin-->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="contuser_search">
	                            <div class="ht_div">
									<label>审核日期：</label> <input id="starttime" type="text"
										onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\');}',dateFmt:'yyyy-MM-dd'})"
										class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i> <input
										id="endtime" type="text"
										onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\');}',dateFmt:'yyyy-MM-dd'})"
										class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
								</div>
                                <div class="ht_divbtn">
                                    <button class="btn btnblue " onclick="loadSearch()" type="button">搜索</button>
                                    <button class="btn btngreen" onclick="clearSearch()" type="button">重置</button>
                                </div>
                            </div>
                            <div class="pro_opra">
								<button class="btn btnblue exportReport">导出</button>
								<button class="btn btnorange printReport">打印</button>
                            </div>
                    </div>
                    </div>
                    <!--查询框end-->
                    <div class="row mt15">
                        <div class="col-md-12">
                            <div class="content-user">

                                <!--用户表格begin-->
                                <table id="auditReport" class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th >序号</th>
                                        <th >审核时间</th>
						                <th >用户审核失败数量</th>
						                <th >用户审核成功数量</th>
						                <th >司机审核失败数量</th>
						                <th >司机审核成功数量</th>
						                <th >完全认证车辆审核失败数量</th>
						                <th >完全认证车辆审核成功数量</th>
						                <th >临时认证车辆审核失败数量</th>
						                <th >临时认证车辆审核成功数量</th>
						                <th >银行卡审核失败数量</th>
						                <th >银行卡审核成功数量</th>
						                <th >大易/交通部运单未推送数量</th>
						                <th >大易/交通部运单已推送数量</th>
						                <th >安联/交通部运单未推送数量</th>
						                <th >安联/交通部运单已推送数量</th>
						                <th >创建时间</th>
                                    </tr>
                                    </thead>
                                    <tbody id="innerhml">
                                    
                                    </tbody>
                                </table>
                                <!--用户表格end-->
                            </div>
                        </div>
                    </div>
                        <div class="clear"> </div>
                    <!-- 分页部分  开始-->
						            <div class="row pr20 fr">
										<%@include file="../common/pagination.jsp" %>
						            </div>
					<!-- 分页部分 结束 -->
                </div>
            </div>
            <!--后台右侧布局end-->
           </div>
            <!--后台整体布局end-->
         </div>
    <!--侧边栏end-->
    </div>

<!--修改begin-->
<div class="modal fade" id="updateDeatil" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >车辆信息修改</h4>
            </div>
            <div class="modal-body" id="uptdetailid" style="">
            	
            </div>
            
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" id="closeupt" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--修改end-->
<!--删除begin-->
<div class="modal fade" id="dele" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">物料删除</h4>
            </div>
            <div class="modal-body">
                <h4>确定要删除此条信息吗?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--删除end-->


<%@include file="../common/footer.jsp" %>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>
<script type="text/javascript">
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="${trRoot}/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/js/bootstrap.js"></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot}/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${scriptsRoot}/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="/resources/js/adminMember/statistics.js?0758" ></script>
<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>

</body>
</html>
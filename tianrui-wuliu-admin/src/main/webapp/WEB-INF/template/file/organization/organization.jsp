<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>组织档案管理</title>
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
    <link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
<!--  <link rel="Shortcut Icon" href="${path }/images/favicon.ico" type="image/x-icon">  -->   

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div class="container-fluid">
	<jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
    <!--后台右侧布局begin-->
    <div class="col-md-10 ">
        <div class="ht_content">
            <div id="content-header">
                <h3>组织档案</h3>
            </div>
            <!--查询框begin-->
            <div class="row">
                <div class="col-md-12">
                    <div class="contuser_search">
                        <div class="ht_div">
                            <label >组织名称：</label>
                            <input id="organizationname_search" type="text" maxlength="25" placeholder="组织名称">
                        </div>
                        <div class="ht_div">
                            <label >组织编码：</label>
                            <input id="organizationno_search" type="text" maxlength="16" placeholder="组织编码 ">
                        </div>
                        <div class="ht_div">
                            <label >组织类型：</label>
                            <select id="organizationtype_search" class="form-control">
                               	<option value="" selected="selected"> 请选择</option>
                                <option value="1" >天瑞旗下</option>
                                <option value="2">山水旗下</option>
                                <option value="3"> 社会</option>
                            </select>
                        </div>
                        <button class="btn btn-primary btn-sm" type="submit" id="org_search" onclick="organSearch();">搜索</button>
                        <button class="btn btn-success btn-sm" type="submit" id="org_reset">重置</button>
                    </div>
                </div>
            </div>
            <!--查询框end-->
            <div class="row mt15">
                <div class="col-md-12">
                    <div class="content-user">
                        <div class="content-tou">
                            <button data-toggle="modal" data-target="#addModal">
                                <i class="glyphicon glyphicon-plus"></i><span>新增</span></button>
                        </div>
                        <!--用户表格begin-->
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>组织名称</th>
                                <th>组织编码</th>
                                <th>组织类型</th>
                                <th>组织状态</th>
                                <th>创建者</th>
                                <th>创建时间</th>
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
</div>
<!--侧边栏end-->

</div>
<!--新增组织begin-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">组织档案新增</h4>
            </div>
            <div class="modal-body" style=" ">
                <div class="usermodal userlabel3">
                    <label><i style="color: #ff2f00;">*</i>组织名称：</label><input type="text" maxlength="25" id="organizationname_add">
                    <label><i style="color: #ff2f00;">*</i>组织类型：</label> <select class="form-control" id="organizationtype_add">
                                <option selected="selected" value="1">天瑞旗下</option>
                                <option value="2">山水旗下</option>
                                <option value="3"> 社会</option>
                </select>
                </div>
                <div class="usermodal userlabel3">
                    <label><i style="color: #ff2f00;">*</i>组织编码：</label><input type="text" maxlength="16" id="organizationno_add">
                    <label><i style="color: #ff2f00;">*</i>组织状态：</label>
                    <select disabled="disabled" class="form-control" id="status_add">
                   	 <option selected="selected" value="1">启用</option>
                    </select>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="add_save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--新增组织end-->

<!--修改组织begin-->
<div class="modal fade" id="edit_org" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <input type="hidden" id="id_edit">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">组织档案修改</h4>
            </div>
            <div class="modal-body" style=" ">
                <div class="usermodal userlabel3">
                    <label><i style="color: #ff2f00;">*</i>组织名称：</label>
                    <input id="organizationname_edit" type="text" maxlength="16">
                    <label><i style="color: #ff2f00;">*</i>组织类型：</label> 
                    <select id="organizationtype_edit" class="form-control">
                                <option value="1">天瑞旗下</option>
                                <option value="2">山水旗下</option>
                                <option value="3"> 社会</option>
                </select>
                </div>
                <div class="usermodal userlabel3">
                    <label><i style="color: #ff2f00;">*</i>组织编码：</label><input id="organizationno_edit" type="text" maxlength="16">
                    <label><i style="color: #ff2f00;">*</i>组织状态：</label>
                    <select id="status_edit" class="form-control">
                        <option value="1">启用</option>
                        <option value="2">停用</option>
                    </select>
                </div>

            </div>
            <div class="modal-footer">
              	<input type="hidden" id="pageNo">
                <button type="button" class="btn btn-primary" onclick="OrgUpdate();">修改</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--修改组织end-->
<%@include file="../../common/footer.jsp" %>
<script type="text/javascript" src="<%=basePath%>/resources/js/organization/organization.js?01.21" ></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
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
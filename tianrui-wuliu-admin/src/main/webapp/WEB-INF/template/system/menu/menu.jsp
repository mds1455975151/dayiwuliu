<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>权限管理-菜单管理</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${stylesRoot}/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot}/base.css" rel="stylesheet">
    <link href="${stylesRoot}/style.css" rel="stylesheet">
    <link href="${stylesRoot}/tr-media.css" rel="stylesheet">
    <link href="${stylesRoot}/jquery.selectbox.css" type="text/css" rel="stylesheet"/>

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
                    <h3>菜单管理</h3>
                </div>
                <div class="row mt15">
                    <div class="col-md-12">
                        <div class="content-user">
                            <div class="content-tou">
                                <button data-toggle="modal" data-target="#addmenu"><i
                                        class="glyphicon glyphicon-plus"></i><span>新增</span></button>
                            </div>
                            <!--用户表格begin-->
                            <table id="treeTable1" class="table table-bordered" >
                              <thead>
                                <tr>
                                    <th></th>
                                    <th style="width: 200px;">名称</th>
                                    <th>域名称</th>
                                    <th>URL</th>
                                    <th>显示顺序</th>
                                    <th>按钮操作</th>
                                    <th>操作</th>
                                </tr>
                              </thead>
                              <tbody id="menu_tbody">
                            <!--用户表格end-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--后台右侧布局end-->
    </div>
    <!--后台整体布局end-->
</div>
<!--侧边栏end-->
</div>
<!--头部新增菜单begin-->
<div class="modal fade" id="addmenu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">新增菜单</h4>
            </div>
            <div class="modal-body" style="">
                <div class="juesemodal">
                    <p><label><i style="color: #ff2f00;">*</i>名称：</label><input type="text" maxlength="16"  id="menu_add_name">
                    </p>

                    <div class="selectrow">
                        <label>父级节点：</label>
                        <div class="level_sel" id="menu_select">
                            <select name="modal_add_pid" id="modal_add_pid">
                            	<option value="1">物流平台</option>
                            </select>
                        </div>
                    </div>

                    
                    <div class="clear"></div>
                    <p><label>URL：</label><input type="text" maxlength="64"  id="menu_add_url"></p>
                    <p><label><i style="color: #ff2f00;">*</i>显示顺序：</label><input type="text" maxlength="8"  id="menu_add_order"></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="menu_add_save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--头部新增菜单end-->
<!--行内新增菜单begin-->
<div class="modal fade" id="addmenu2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">新增菜单</h4>
                <input type="hidden" id="menu_add2_id">
            </div>
            <div class="modal-body" style="">
                <div class="juesemodal">
                    <p><label><i style="color: #ff2f00;">*</i>名称：</label><input type="text" maxlength="16"  id="menu_add2_name">
                    </p>

                    <p><label>父结点：</label><input type="text" maxlength="16" id="menu_add2_parentName"  readonly></p>
                    <p><label>URL：</label><input type="text" maxlength="64"  id="menu_add2_url"></p>

                    <p><label><i style="color: #ff2f00;">*</i>显示顺序：</label><input maxlength="8"  type="text" id="menu_add2_order"></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="menu_add2_save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--行内新增菜单end-->
<!--菜单删除begin-->
<div class="modal fade" id="deleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">删除</h4>
                <input type="hidden" id="modal_del_menuId">
            </div>
            <div class="modal-body">
                <h4>您确定要删除吗？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="modal_del_confirm">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--菜单删除end-->
<!--修改菜单begin-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">菜单修改</h4>
            </div>
            <div class="modal-body" style="">
                <div class="juesemodal">
                    <p><label><i style="color: #ff2f00;">*</i>名称：</label><input maxlength="16"  type="text" id="menu_edit_name">
                        <input type="hidden" id="menu_edit_id">
                    </p>
                    <p><label>URL：</label><input type="text" id="menu_edit_url"></p>
                    <p><label><i style="color: #ff2f00;">*</i>显示顺序：</label><input maxlength="8" type="text" id="menu_edit_order"></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="menu_edit_save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--修改菜单end-->
<!--设置访问权限begin-->
<div class="modal fade" id="setyemian" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">设置访问权限</h4>
                <input type="hidden" id="modal_set_id">
            </div>
            <div class="modal-body" style="height: 300px;">
                <div class="modal_head">
                    <div class="juesemodal">
                        <p id="modal_set_title">页面名称：系统管理员</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="qxalert4">
                            <div class="checkleft fl">
                                <h4>拒绝访问</h4>
                                <div class="checkout">
                                    <ul class="left_ul" id="modal_set_left">
                                        <li ><a>角色管理1</a></li>
                                        <li><a>角色管理2</a></li>
                                        <li><a>角色管理3</a></li>
                                        <li ><a>角色管理4</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="checkmid">
                                <p>
                                    <button  class="right_shuang btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
                                </p>
                                <p>
                                    <button class="right_dan btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
                                </p>
                                <p>
                                    <button class="left_shuang btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
                                </p>
                                <p>
                                    <button  class="left_dan btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
                                </p>
                            </div>
                            <div class="checkleft fr">
                                <h4>允许访问</h4>
                                <div class="checkout">
                                    <ul  class="right_ul" id="modal_set_right">
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="modal_set_save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--设置访问权限end-->
<%@include file="../../common/footer.jsp" %>
<script type="text/javascript">
	var CONTEXTPATH="${path}";
</script>
<script type="text/javascript" src="${scriptsRoot}/treeTable.js?"></script>
<script type="text/javascript" src="${scriptsRoot}/jquery.selectbox-0.2.js"></script>
<script type="text/javascript" src="/resources/js/menu/menu.js" ></script>
</body>
</html>

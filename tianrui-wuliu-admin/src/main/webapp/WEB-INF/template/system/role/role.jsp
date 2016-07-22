<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>index</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/font-awesome.css"  rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css"  rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />

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
                        <h3>角色管理</h3>
                    </div>
                    <!--查询框begin-->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="contuser_search">
	                            <div class="form-inline">
	                                <div class="form-group">
	                                    <label for="exampleInputName2">角色编号：</label>
	                                    <input type="text" class="form-control" maxlength="16" id="role_search_no" placeholder=" ">
	                                </div>
	                                <div class="form-group">
	                                    <label for="exampleInputEmail2">角色名称：</label>
	                                    <input type="text" class="form-control" maxlength="16"  id="role_search_name" placeholder=" ">
	                                </div>
	                                <button class="btn btn-primary btn-sm" type="submit" id="role_search" name="role_search">搜索</button>
	                                <button class="btn btn-success btn-sm" type="reset" id="role_reset" name="role_reset">重置</button>
	                            </div>
                            </div>
                        </div>
                    </div>
                    <!--查询框end-->
                    <div class="row mt15">
                        <div class="col-md-12">
                            <div class="content-user">
                                <div class="content-tou">
                                    <button data-toggle="modal" data-target="#addModal"><i
                                            class="glyphicon glyphicon-plus"></i><span>新增</span></button>
                                    <button id="batchDelete"><i class="glyphicon glyphicon-trash"></i><span>批量删除</span></button>
                                </div>
                                <!--用户表格begin-->
                                <table class="table table-bordered" style="font-size:12px;" data-resizable-columns-id="demo-table">
                                    <thead>
                                    <tr>
                                        <th class="check-col" data-resizable-column-id="#"><input type="checkbox" onclick="selectAll()" name="allUser"></th>
                                        <th data-resizable-column-id="first">角色编号</th>
                                        <th data-resizable-column-id="last">角色名称</th>
                                        <th data-resizable-column-id="#">角色描述</th>
                                        <th data-resizable-column-id="#">包含用户数量</th>
                                        <th data-resizable-column-id="#">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="role_tbody">
                                    	<!-- ***js中进行数据获取*** -->
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

<!--新增角色begin-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">新增角色</h4>
            </div>
            <div class="modal-body" style="">
                <div class="juesemodal">
                    <p><label><i style="color: #ff2f00;">*</i>角色名称：</label><input type="text" maxlength="16"  id="roleName"></p>

                    <p><label><i style="color: #ff2f00;">*</i>角色编号：</label><input type="text" maxlength="16"  id="roleNumber"></p>

                    <p><label>角色描述：</label><textarea class="form-control" rows="2" id="roleDescription"></textarea></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="add_save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--新增角色end-->

<!--修改角色begin-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">修改角色</h4>
            </div>
            <div class="modal-body" style="">
                <div class="juesemodal">
                    <p><label><i style="color: #ff2f00;">*</i>角色名称：</label><input type="text" maxlength="16" id="edit_roleName"></p>

                    <p>
                    	<label><i style="color: #ff2f00;">*</i>角色编号：</label><input type="text" maxlength="16" id="edit_roleNumber">
                    	<input type="hidden" id="edit_roleId">
                    	<input type="hidden" id="edit_rowno">
                    </p>

                    <p><label>角色描述：</label><textarea class="form-control" rows="2" id="edit_roleDescription"></textarea></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="edit_save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--修改角色end-->

<!--批量删除begin-->
<div class="modal fade" id="deleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="delete_title">批量删除</h4>
                <input type="hidden" id="delete_roleId">
                <input type="hidden" id="delete_flag">
                <input type="hidden" id="delete_rowno">
            </div>
            <div class="modal-body">
                <h4>您确定要删除吗？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="delete_confirm">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--批量删除end-->

<!--设置页面权限begin-->
<div class="modal fade" id="setyemian" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="pagePermissionSet">设置页面权限</h4>
            </div>
            <div class="modal-body" style="">
                <div class="juesemodal">
                    <p id="pagePermission_name">角色名称：系统管理员</p>
                    <input type="hidden" id="setyemian_roleID" value=""/>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="juese_quanxian">
                            <div class="tree well" id="pagePermission_div">
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="pagePerSave">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--设置页面权限end-->

<!--数据访问权限begin-->
<!-- Modal -->
<div class="modal fade" id="setquanxian" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">设置数据权限</h4>
            </div>
            <div class="modal-body" style="height: 350px; ">
                <div class="modal_head">
                    <div class="juesemodal">
                        <p>角色名称：系统管理员</p>

                        <p><label>数据权限：</label>
                            <select class="">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h4>设置数据权限</h4>
                        <div class="qxalert4">
                            <div class="checkleft fl">
                                <h4>待选项</h4>
                                <div class="checkout">
                                    <ul class="left_ul" id="">
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
                                <h4>已选项</h4>
                                <div class="checkout">
                                    <ul  class="right_ul" id="">
                                        <li ><a>角色管理1</a></li>
                                        <li><a>角色管理1</a></li>
                                        <li><a>角色管理1</a></li>
                                        <li><a>角色管理4</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--数据访问权限end-->

<!--角色详情begin-->
<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">角色详情</h4>
            </div>
            <div class="modal-body" style="">
                <div class="juesemodal">
                    <p id="detail_name"><!-- 角色名称：系统管理员 --></p>
                    <input type="hidden" id="modal_detail_id">
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="juese_quanxian">
                            <div class="tree well" id="detail_div">
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <!-- <button type="button" class="btn btn-primary" id="save1">保存</button> -->
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>

    </div>
</div>
<!--角色详情end-->

<!--复制角色begin-->
<div class="modal fade" id="copyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">复制角色</h4>
            </div>
            <div class="modal-body" style="">
                <div class="juesemodal">
                    <p>
                    	<label>被复制名称：</label>
                    	<input type="text" id="copiedName" value=""  disabled/>
                    	<input type="hidden" id="copiedRoleId"/>
                    	<input type="hidden" id="copiedRowno"/>
                    </p>

                    <p><label><i style="color: #ff2f00;">*</i>角色名称：</label><input type="text" maxlength="16"  id="copiedRoleName"></p>

                    <p><label><i style="color: #ff2f00;">*</i>角色编号：</label><input type="text" maxlength="16" id="copiedRoleNum"></p>

                    <p><label>角色描述：</label><textarea class="form-control" rows="2" id="copiedRoleDesc"></textarea></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="copy_save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!--复制角色end-->
<script type="text/javascript">
	var CONTEXTPATH="${path}";
	var imagesRoot="${imagesRoot }";
</script>
<%@include file="../../common/footer.jsp" %>-->
<script type="text/javascript" src="/resources/js/role/role.js" ></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
<!--表格宽度调整-->
<script type="text/javascript">
    $(function(){
        $("table").resizableColumns({});
    });
</script>
</body>
</html>

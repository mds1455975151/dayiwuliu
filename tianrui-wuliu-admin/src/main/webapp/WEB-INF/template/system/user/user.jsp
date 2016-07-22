<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<title>权限管理-用户管理</title>
		<meta name="keywords" content=" 天瑞" />
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
	    <link href="${stylesRoot }/base.css" rel="stylesheet">
	    <link href="${stylesRoot }/style.css" rel="stylesheet">
	    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
	    <link href="${stylesRoot}/jquery-ui.min.css" rel="stylesheet">
	    <link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
	    <!-- icon -->
	    <link rel="Shortcut Icon" href="${imagesRoot}/favicon.ico" type="image/x-icon">
	    
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
                        <h3>用户管理</h3>
                    </div>
                    <!--查询框begin-->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="contuser_search">
                                <label >账号：</label>
                                <input type="text" class="" maxlength="16"  placeholder=" " id="user_code">
                                <label >姓名：</label>
                                <input type="text" class="" maxlength="16" placeholder=" " id="user_name">
                                <label >手机号：</label>
                                <input type="text" class="" maxlength="11" placeholder=" " id="user_tel">
                                <label >组织名称：</label>
                                <input type="text" class="" maxlength="20"  placeholder=" " id="user_orgName">
                                <input type="hidden" id="user_orgId">
                                <button class="btn btn-primary btn-sm" type="submit" id="user_search">搜索</button>
                                <button class="btn btn-success btn-sm" type="reset" id="user_reset" >重置</button>
                                <div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--查询框end-->
                    <div class="row mt15">
                         <div class="col-md-12">
                             <div class="content-user">
                                 <div class="content-tou">
                                 	<button data-toggle="modal" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i><span>新增</span></button>
                                    <!-- <button data-toggle="modal" data-target="#editModal" ><i class="glyphicon glyphicon-ok"></i><span>赋权</span></button> -->
                                    <button id="batchDelete"><i class="glyphicon glyphicon-trash"></i><span>批量删除</span></button>
                                 </div>
                                 <!--用户表格begin-->
                                 <table id="dg" class="table table-hover" data-options="">
                                     <thead>
	                                     <tr>
	                                         <th data-options="field:'ck',checkbox:true"><input type="checkbox" onclick="selectAll()" name="allUser"></th>
	                                         <th data-options="field:'登录账号',width:80">登录账号</th>
	                                         <th data-options="field:'name',width:100">姓名</th>
	                                         <th data-options="field:'orgname',width:100">组织名称</th>
	                                         <th data-options="field:'accountStatus',width:80">用户状态</th>
	                                         <th data-options="field:'roles',width:80">角色数量</th>
	                                         <th data-options="field:'operation',width:220">操作</th>
	                                     </tr>
                                     </thead>
                                     <tbody id="user_tbody">
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
            </div>
                <!--后台右侧布局end-->
            </div>
            <!--后台整体布局end-->
    </div>
    <!--侧边栏end-->
</div>

<!--新增用户对话框begin-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="height: 500px;">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >新增用户</h4>
            </div>
            <div class="modal-body" style="height: 400px;">
                <div class="modal_head">
                    <div class="usermodal userlabel1">
                        <label><i style="color: #ff2f00;">*</i>账号：</label><input type="text" maxlength="16" id="modal_add_account">
                        <input type="hidden" id="modal_add_id">
                        <label><i style="color: #ff2f00;">*</i>姓名：</label><input type="text" class="" maxlength="16" id="modal_add_name">
                    </div>
                    <div class="usermodal userlabel1">
                        <label><i style="color: #ff2f00;">*</i>手机号：</label><input type="text" maxlength="11" id="modal_add_tel">
                        <label><i style="color: #ff2f00;">*</i>组织：</label><input type="text" maxlength="20" id="modal_add_orgName">
                        <!-- 组织ID隐藏项 -->
                        <input type="hidden" id="modal_add_orgId">
                    </div>
                    <div class="usermodal userlabel1">
                        <label>用户状态：</label>
                        <i class=""><input type="radio" name="modal_add_status" value="1">有效</i>
                        <i class=""><input type="radio" name="modal_add_status" value="0">无效</i>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h4>新用户赋权</h4>
                        <div class="qxalert1">
                            <div class="checkleft fl">
                                <h4>待选项</h4>
                                <div class="checkout">
                                    <ul class="left_ul" id="modal_add_left">
                                        <!-- <li ><a>角色管理1</a></li>
                                        <li><a>角色管理2</a></li>
                                        <li><a>角色管理3</a></li>
                                        <li ><a>角色管理4</a></li> -->
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
                                    <ul  class="right_ul" id="modal_add_right">
                                        <!-- <li ><a>角色管理1</a></li>
                                        <li><a>角色管理1</a></li>
                                        <li><a>角色管理1</a></li>
                                        <li><a>角色管理4</a></li> -->
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    </div>
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="modal_add_save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--新增用户对话框end-->

<!--赋权对话框begin-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">赋权</h4>
            </div>
            <div class="modal-body" style="height: 300px;">
                <div class="modal_head">
                    <div class="usermodal">
                        <label><i style="color: #ff2f00;">*</i>账号：</label><input type="text">
                        <label>有效</label><input type="checkbox">
                        <label>姓名：</label><input type="text" class="">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h4>新用户赋权</h4>
                        <div class="qxalert2">
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
<!--赋权对话框end-->

<!--修改权限begin-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >用户修改</h4>
            </div>
            <div class="modal-body" style="height: 330px;">
                <div class="modal_head">
                    <div class="usermodal userlabel1">
                        <label><i style="color: #ff2f00;">*</i>账号：</label>
                        			<input type="text" disabled="disabled" id="modal_edit_account">
                        			<input type="hidden" id="modal_edit_id">
                        			<input type="hidden" id="modal_edit_rowno">
                        <label><i style="color: #ff2f00;">*</i>姓名：</label><input type="text" maxlength="16" class="" id="modal_edit_name">
                    </div>
                    <div class="usermodal userlabel1">
                        <label><i style="color: #ff2f00;">*</i>手机号：</label><input type="text" maxlength="11" id="modal_edit_tel">
                        <label>用户状态：</label>
                        <i class=""><input type="radio" name="modal_edit_status" value="1">有效</i>
                        <i class=""><input type="radio" name="modal_edit_status" value="0">无效</i>
                        <label style="margin-left: 10px;" >重置密码：</label><input type="checkbox" id="modal_edit_resetPassword">

                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="qxalert3">
                            <div class="checkleft fl">
                                <h4>待选项</h4>
                                <div class="checkout">
                                    <ul class="left_ul" id="modal_edit_left">
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
                                    <ul  class="right_ul" id="modal_edit_right">
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
                <button type="button" class="btn btn-primary" id="modal_edit_save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--修改权限end-->

<!--批量删除begin-->
<div class="modal fade" id="deleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <input type="hidden" id="modal_del_id">
                <input type="hidden" id="modal_del_flag">
                <input type="hidden" id="modal_del_rowno">
                <h4 class="modal-title" id="modal_del_title">批量删除</h4>
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
<!--批量删除end-->
<!--删除权限begin-->
<div class="modal fade" id="delquanxian" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">批量删除</h4>
            </div>
            <div class="modal-body">
                <h4>您确定要删除权限吗？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--删除权限end-->
<%@include file="../../common/footer.jsp" %>
		<script type="text/javascript">
			var CONTEXTPATH="${path}";
			var imagesRoot="${imagesRoot }";
		</script>
		<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>
		<script type="text/javascript" src="/resources/js/user/user.js" ></script>
		<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
		<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
	</body>
</html>

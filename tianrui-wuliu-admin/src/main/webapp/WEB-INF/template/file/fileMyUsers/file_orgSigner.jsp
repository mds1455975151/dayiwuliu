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
    <title>我的收货员</title>
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
    <link href="${stylesRoot}/imgcut.css" rel="stylesheet">
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
                <h3>我的收货员</h3>
            </div>
            <!--查询框begin-->
            <div class="row">
                <div class="col-md-12">
                    <div class="contuser_search">
                        <div class="ht_div">
                            <label>手机号：</label>
                            <input type="text" id="cellPhone" maxlength="11" placeholder=" ">
                        </div>
                         <div class="ht_div">
                            <label>姓名：</label>
                            <input type="text" id="membername"  placeholder=" ">
                        </div>
                        <div class="ht_divbtn">
                            <button class="btn btnblue " onclick="searchMember();" type="submit">搜索</button>
                            <button class="btn btngreen" onclick="resetvalue();" type="submit">重置</button>
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
                        </div>
                        <!--用户表格begin-->
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>手机号</th>
                                <th>姓名</th>
                                <th>备注</th>
                                <th>添加时间</th>
                                <th>添加人</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="innerHml">
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

<!--新增会员begin-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >新增</h4>
            </div>
            <div class="modal-body">
                <div class="juesemodal">
                    <p><label><i style="color: #ff2f00;">*</i>手机号：</label>
                    <input id="addcellphone" type="text" maxlength="11" >
                    <span style="color: red" id="error"></span>
                    <button class="btn btnblue " onclick="searchPhone()" type="submit">搜索</button>
                    </p>
                    <p><label><i style="color: #ff2f00;">*</i>姓名：</label>
                    <input id="memberName" type="text" readonly="readonly"></p>
                    <input type="hidden" id="req_memberId">
                    <p><label>备注：</label><textarea id="massage" class="form-control" rows="2"></textarea></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveorgMember();">保存</button>
                <button type="button" id="addclick" class="btn btn-default" onclick="resetvalue();" data-dismiss="modal" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--新增会员end-->

<!--修改会员begin-->
<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">修改</h4>
            </div>
            <div class="modal-body" style="">
                <div class="juesemodal">
                    <p><label><i style="color: #ff2f00;">*</i>手机号：</label>
                    <input type="text" id="uptmemberTel" readonly="readonly"></p>
                    <input type="hidden" id="uptid"></p>
                    <p><label><i style="color: #ff2f00;">*</i>姓名：</label>
                    <input type="text" id="uptmembername" readonly="readonly"></p>
                    <p><label>备注：</label>
                    <textarea id="uptmemberDesc" class="form-control" rows="2"></textarea></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="updateOrgMember();" class="btn btn-primary">保存</button>
                <button type="button" id="updateclick" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--修改货物end-->

<!--查看详情begin-->
<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">会员详情</h4>
            </div>
            <div class="modal-body" style="">
                <div class="file_detail2">
                    <label>手机号：</label><span id="memberTel"></span>
                </div>
                <div class="file_detail2">
                    <label>备注：</label><span id="memberDesc"></span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--查看详情end-->
<!--删除begin-->
<div class="modal fade" id="dele" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">会员删除</h4>
            </div>
            <div class="modal-body">
            	<input type="hidden" id="detid" value="">
                <h4>确定要删除此条信息吗?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="deleteOrgMember();" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--删除end-->
<%@include file="../../common/footer.jsp" %>
<script type="text/javascript">
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="<%=basePath%>/resources/js/fileMyUsers/file_orgSigner.js?09.09" ></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
</body>
</html>
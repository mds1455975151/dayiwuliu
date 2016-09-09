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
    <title>会员管理</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
</head>
<body>

<div class="container-fluid">
    <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
                <!--后台右侧布局begin-->
            <div class="col-md-10 ">
                <div class="ht_content">
                    <div id="content-header">
                        <h3>个人用户管理</h3>
                    </div>
                    <!--查询框begin-->
                   <form id="searchid">
                   	<input type="hidden" name="menuId" value="${menuId }">
                   	<input type="hidden" name="pageNo" value="1">
                   	<input type="hidden" name="pageSize" value="10">
                    <div class="row">
                            <div class="col-md-12">
                                <div class="contuser_search">
                                    <div class="ht_div">
                                        <label>会员名称:</label>
                                        <input type="text"  value="${member.userName }" id="userName" placeholder=" ">
                                    </div>
                                    <div class="ht_div">
                                        <label>会员账号:</label>
                                        <input type="text"  value="${member.cellPhone }" id="cellPhone" placeholder=" ">
                                    </div>
                                   	<div class="ht_div">
                                        <label>会员类别:</label>
                                        <select class="form-control" id="personalType">
                                            <option value="">请选择</option>
                                            <option value="2">个人用户</option>
                                            <option value="1">企业用户</option>
                                        </select>
                                    </div>
                                   	
                                   	<div class="ht_div">
                                        <label>认证状态:</label>
                                        <select class="form-control" id="perCheckStatus">
                                            <option value="">请选择</option>
                                            <option value="2">认证中</option>
                                            <option value="3">认证失败</option>
                                            <option value="1">认证成功</option>
                                        </select>
                                    </div>
                                <div class="contuser_search">
                                 	<div class="ht_div">
                                        <label>会员状态:</label>
                                        <select class="form-control" name="status" id="status">
                                            <option value="">请选择</option>
                                            <option value="1">启用</option>
                                            <option value="0">停用</option>
                                        </select>
                                  	</div>
                                  	<div class="ht_divbtn">   
                                    <button class="btn btn-primary btn-sm" type="button" onclick="searchSubmit()">搜索</button>
                                    <button class="btn btn-success btn-sm" type="button" onclick="memberReset()">重置</button>
                                	</div>
                                </div>
                        </div>
                    </div>
                    </form>
                    <!--查询框end-->
                    <div class="row mt15">
                         <div class="col-md-12">
                             <div class="content-user">
                                 <!--用户表格begin-->
                                 <table id="sample2" class="table table-bordered"   data-options="">
                                     <thead>
                                     <tr>
                                         <th >会员名 </th>
                                         <th >会员账号 </th>
                                         <th >注册时间</th>
                                         <th >提交时间</th>
                                         <th > 会员状态 </th>
                                         <th > 认证状态 </th>
                                         <th > 操作 </th>
                                         <th >  最后登录时间</th>
                                     </tr>
                                     </thead>
                                    
                                     <tbody id="innerHml">
                                    	
                                     </tbody>
                                 </table>
                                 <!--用户表格end-->
                                  <!-- 分页部分  开始-->
						            <div class="row pr20 fr">
										<%@include file="../common/pagination.jsp" %>
						            </div>
						         <!-- 分页部分 结束 -->
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
<!--查看详情begin-->
<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >注册会员详情</h4>
            </div>
            <div class="modal-body" style=" " id="detailid">
                <div class="file_detail">
                    <label>会员类别：</label><span>个人</span>
                </div>
                <div class="file_detail">
                    <label>会员账号：</label><span>tr15451212</span>
                </div>
                <div class="file_detail">
                    <label>会员名称：</label><span>shhnkdjl</span>
                </div>
                <div class="file_detail">
                    <label>联系方式：</label><span>315641212</span>
                </div>
                <div class="file_detail">
                    <label>会员状态：</label><span>认证成功</span>
                </div>
                <div class="file_detail">
                    <label>注册时间：</label><span>2016-02-13</span>
                </div>
                <div class="file_detail">
                    <label>认证时间：</label><span>2016-02-13</span>
                </div>
                <div class="file_detail3">
                    <label>营业执照/身份证号：</label><span>41222219880000111111</span>
                </div>
                <div class="file_detail2">
                    <label>驾驶证照片：</label><span><img src=""></span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--查看详情end-->
<!--停用begin-->
<div class="modal fade" id="qiyong" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >提示</h4>
            </div>
            <div class="modal-body">
                <h4><span id="satus">确定要启用吗</span></h4>
                <input type="hidden" id="statustype" value="">
                <input type="hidden" id="memid" value="">
            </div>
            <div class="modal-footer">
                <button type="button" onclick="changeType()" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--停用end-->
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
            	<input type="hidden" id="memberid" value="">
                <h4>确定要删除此条信息吗?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="deleteMember()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--删除end-->
<jsp:include page="../common/footer.jsp" flush="false"></jsp:include>

<script type="text/javascript">
	var CONTEXTPATH="${contextPath}";
	var imagesRoot="${imagesRoot }";
    $(function(){
        $("table").resizableColumns({});
/*         $('.form_date').datetimepicker({
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        }); */
    });

</script>
<script type="text/javascript" src="/resources/js/adminMember/navigation.js" ></script>
<script type="text/javascript" src="/resources/js/adminMember/user_person.js" ></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
</body>
</html>
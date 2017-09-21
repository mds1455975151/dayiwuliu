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
    <title>银行卡管理</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css"  rel="stylesheet">
    <link href="${stylesRoot }/imgcut.css" rel="stylesheet">
    <link href="${trRoot}/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
</head>
<body>

<div class="container-fluid">
	<input type="hidden" id="recPageNo" value="${pageNo }">
    <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
                <!--后台右侧布局begin-->
            <div class="col-md-10 ">
                <div class="ht_content">
                    <div id="content-header">
                        <h3>银行卡管理</h3>
                    </div>
                    <!--查询框begin-->
                    <div class="row">
                            <div class="col-md-12">
                                <div class="contuser_search">
                                    <div class="ht_div">
                                        <label>银行卡号：</label>
                                        <input type="text" maxlength="20" id="find_bankcard" placeholder=" ">
                                    </div>
                                    <div class="ht_div">
                                        <label>持卡人名称：</label>
                                        <input type="text" maxlength="20" id="find_idname" placeholder=" ">
                                    </div>
                                    <div class="ht_div">
                                        <label>认证状态:</label>
                                        <select class="form-control" id="find_bankautid">
                                            <option value="">请选择</option>
                                            <option value="2">认证中</option>
                                            <option value="3">认证失败</option>
                                            <option value="1">认证成功</option>
                                        </select>
                                    </div>
                                    <div class="ht_div">
                                        <label>持卡人电话：</label>
                                        <input type="text" maxlength="20" id="find_telphone" placeholder=" ">
                                    </div>
                                </div>
                                <div class="contuser_search">
                                    <div class="ht_divbtn">
                                        <button class="btn btnblue " onclick="banktSearch();" type="button">搜索</button>
                                        <button class="btn btngreen" onclick="clearSearch();" type="submit">重置</button>
                                    </div>
                                </div>
                        </div>
                    </div>
                    <!--查询框end-->
                    <div class="row mt15">
                         <div class="col-md-12">
                             <div class="content-user">
                                 <!--用户表格begin-->
                                 <table id="sample2" class="table table-bordered"   data-options="">
                                     <thead>
                                     <tr>
                                         <th>序号</th>
                                         <th>银行卡账户 </th>
                                         <th>持卡人名称</th>
                                         <th>持卡人电话</th>
                                         <th>银行名称</th>
                                         <th>开户行名称</th>
                                         <th>认证状态 </th>
                                         <th>推送状态 </th>
                                         <th>创建时间</th>
                                         <th>操作 </th>
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
                <h4 class="modal-title" >详情</h4>
            </div>
            <div class="modal-body" id="detailid" style="">
            	<div class="file_detail">
					<label>银行卡账户 ：</label>
					<span id="bankcard_mg">二二</span>
				</div>
				<div class="file_detail">
					<label>持卡人名称：</label>
					<span id="idname_mg">二二</span>
				</div>
				<div class="file_detail">
					<label>身份证号：</label>
					<span id="idcard_mg">二二</span>
				</div>
				<div class="file_detail">
					<label>银行名称：</label>
					<span id="bankname_mg">二二</span>
				</div>
				<div class="file_detail">
					<label>银行编码：</label>
					<span id="bankLineCode_mg">二二</span>
				</div><div class="file_detail">
					<label>开户行联行号：</label>
					<span id="bankLineNumber_mg">二二</span>
				</div>
				<div class="file_detail">
					<label>开户行名称：</label>
					<span id="desc1_mg">二二</span>
				</div>
				
				<div class=file_detail>
					<label>认证状态 ：</label>
					<span id="bankautid_mg">二二</span>
				</div>
				<div class="file_detail">
					<label>银行卡照片：</label>
					<span id="bankautidImg_mg">二二</span>
				</div>
				<div class="clear"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--查看详情end-->
<!--审核begin-->
<div class="modal fade" id="shenhe" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >审核</h4>
            </div>
            <div class="modal-body">
            <button type="button" class="btn tongguo">通过</button>
            <button type="button" class="btn butongguo">不通过</button></br></br></br>
            <input type="hidden" id="bank_status">
            <input type="hidden" id="bank_id">
            <textarea id="auditMassage" name="auditMassage" rows="4" cols="80"></textarea>
            </div>
            <div class="modal-body">
			<span style="color: red" id="error_massage"></span>
			</div>           
            <div class="modal-footer">
             	<input type="hidden" id="pageNo">
                <button type="button" class="btn btn-primary bank_shenhe" >确定</button>
                <button type="button" class="btn btn-default bank_hide" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--审核详情end-->
<%@include file="../common/footer.jsp" %>
<script type="text/javascript">
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="${trRoot}/js/fileinput.js"></script>
<script type="text/javascript" src="${trRoot}/js/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
<script type="text/javascript" src="/resources/js/bank/bank_card.js?0709" ></script>
</body>
</html>
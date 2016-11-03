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
    <title>运价策略</title>
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
    <link href="${stylesRoot }/imgcut.css" rel="stylesheet">
	 <link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
</head>
<body>

<div class="container-fluid">
 <!--公共头部begin-->
    <jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
    <!--后台右侧布局begin-->
    <div class="col-md-10 ">
        <div class="ht_content">
            <div id="content-header">
                <h3>运价策略</h3>
            </div>
            <!--查询框begin-->
            <div class="row">
                <div class="col-md-12">
                    <div class="contuser_search">
                        <div class="ht_div">
                            <label>策略名称：</label>
                            <input type="text" maxlength="16" id="SfreightName" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>货物名称：</label>
                            <input type="text" maxlength="16" id="Scargo" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>路线名称：</label>
                            <input type="text" maxlength="16" id="SRoute" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>审核状态：</label>
                            <select class="form-control" id="Saudit">
                                <option value="">请选择</option>
	                            <option value="0">审批中</option>
	                            <option value="1">审批成功</option>
	                            <option value="2">审批失败</option>
                            </select>
                        </div>
                        <div class="ht_divbtn">
                            <button onclick="SearchPrice();" class="btn btnblue" type="submit">搜索</button>
                            <button onclick="clearSearch();" class="btn btngreen" type="submit">重置</button>
                        </div>
                    </div>
                </div>
            </div>
            <!--查询框end-->
            <div class="row mt15">
                <div class="col-md-12">
                    <div class="content-user">
                        <!--用户表格begin-->
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>策略名称</th>
                                <th>原价</th>
                                <th>审核价</th>
                                <th>计价单位</th>
                                <th>原税率</th>
                                <th>运单类型</th>
                                <th>审核税率</th>
                                <th>状态</th>
                                <th>货物名称</th>
                                <th>路线名称</th>
                                <th>生效日期</th>
                                <th>调价原因</th>
                                <th>历史价格</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="tablelist">
                            <tr>
                                <td>1</td>
                                <td>水泥</td>
                                <td>运价策略1</td>
                                <td>吨/公里</td>
                                <td>10</td>
                                <td>河南郑州</td>
                                <td>河北石家庄</td>
                                <td>
                                    <span><a data-toggle="modal" data-target="#tingyong">审核</a></span>
                                </td>
                            </tr>
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

<!--审核begin-->
<div class="modal fade" id="tingyong" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">审核</h4>
            </div>
            <form id="updateform">
	          <div class="modal-body">
	          	<div class="user_shenno">
	          	    <input type="hidden" id="freightid" name="id">
	          	    <input type="hidden" id="freightInfoid" name="infoid">
		          	<input type="hidden" id="modifytime" name="modifytime">
		          	<div class="shenhe_alt">
		              <input type="radio" value="1" name="audit"><label>审核通过</label>
		              <input type="radio" value="2" name="audit"><label>审核不通过</label>
		         	</div>
		         	<h4>请输入不通过审核原因：</h4>
		            <textarea name="auditresson" class="form-control" rows="3"></textarea>
	         	</div>
	          </div>
            </form>
            <div class="modal-footer">
            	<span id="hiden" data-dismiss="modal"></span>
                <button type="button" onclick="shenhe();" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--审核end-->
<%@include file="../../common/footer.jsp" %>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="/resources/js/filePrice/file_priceinfo.js?11.3" ></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
</body>
</html>
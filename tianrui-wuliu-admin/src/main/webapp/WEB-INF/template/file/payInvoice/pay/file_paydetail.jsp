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
    <title>运价确认</title>
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
    <jsp:include page="../../../common/header.jsp" flush="false"></jsp:include>
    <!--后台左侧布局end-->
    <!--后台右侧布局begin-->
    <div class="col-md-10 ">
        <div class="ht_content">
            <div id="content-header">
                <h3>运价确认</h3>
            </div>
            <!--查询框begin-->
            <div class="row">
                <div class="col-md-12">
                    <div class="contuser_search">
                        <div class="ht_div">
                            <label>手机号：</label>
                            <input type="text" id="cellPhone" maxlength="11" placeholder=" ">
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
                                <th>运单号</th>
                                <th>发票类型</th>
                                <th>货物名称</th>
                                <th>支付对象</th>
                                <th>创建时间</th>
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
				<%@include file="../../../common/pagination.jsp" %>
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

<div class="modal fade" id="audit" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<div class="juesemodal payMoney" style="margin-bottom: 0px;">
						<div class="model_width">
							<label>账单总额：</label>
							<input type="text" id="billTotalPrice" readonly="readonly">
						</div>
						<div class="model_width">
							<label>应付金额：</label>
							<input type="text" id="amountPayable" readonly="readonly">
						</div>
						<div class="model_width">
							<label>油卡：</label>
							<input type="text" class="total_price_count" id="deductOilCard">
						</div>
						<div class="model_width">
							<label>扣重扣杂：</label>
							<input type="text" class="total_price_count" id="deductWeightMisc">
						</div>
						<div class="model_width">
							<label>扣款：</label>
							<input type="text" class="total_price_count" id="deductMoney">
						</div>
						<div class="model_width">
							<label>其他款项：</label>
							<input type="text" class="total_price_count" id="deductOther">
							<input type="hidden" id="payId">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="auditCommit">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
<!--删除end-->
<%@include file="../../../common/footer.jsp" %>
<script type="text/javascript">
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="<%=basePath%>/resources/js/payInvoice/pay/file_payDetail.js?0623" ></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
</body>
</html>
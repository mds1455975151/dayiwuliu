<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>司机账单</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
<link href="${stylesRoot }/base.css" rel="stylesheet">
<link href="${stylesRoot }/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${stylesRoot }/pagination/pagination.css" />
<link rel="Shortcut Icon" href="${imagesRoot}/favicon.ico"
	type="image/x-icon">
<style type="text/css">
.btnprimary {
	width: 60px;
	height: 22px;
	line-height: 20px;
	padding: 0px;
	margin: 0px;
	background: transparent;
	border: 1px solid #0089e4;
	font-size: 12px;
	color: #0089e4;
}
.juesemodal input {
	height: 26px!important;
}
.juesemodal input[readonly='readonly'] {
	background: #f7f7f7;
}
.juesemodal label {
    width: 110px!important;
}    
.model_width {
    line-height: 22px!important;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="../../../common/header.jsp" flush="false"></jsp:include>
		<!--后台右侧布局begin-->
		<div class="col-md-10 ">
			<div class="ht_content">
				<div id="content-header">
					<h3>司机账单</h3>
				</div>
				<!--查询框begin-->
				<div class="row">
					<div class="col-md-12">
						<div class="contuser_search">
							<div class="ht_div">
								<label>运单号：</label> <input id="billCode" type="text"
									placeholder="请输入运单号">
							</div>
							<div class="ht_div">
								<label>货物名称：</label> <input id="cargoName" type="text"
									placeholder="请输入货物名称">
							</div>
							<div class="ht_divbtn">
								<button class="btn btnblue search" type="submit">搜索</button>
								<button class="btn btngreen reset" type="submit">重置</button>
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
										<th>序号</th>
										<th>账单号</th>
										<th>发票名称</th>
										<th>账单总价</th>
										<th>收款人</th>
										<th>账单状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="dataBody">
								</tbody>
							</table>
							<!--用户表格end-->
						</div>
						<!--分页效果开始-->
						<div class="page_wrap">
							<div class="page_date">
								<label>数据共：</label><i id="total">0</i><label>条</label>
							</div>
							<div class="page_date">
								<label>跳到第：</label> <input id="jumpPageNo" type="text" value="1">
								<label>页</label>
								<button id="jumpPageNoBtn" class="btn btn-default">确定</button>
							</div>
							<div class="page_select">
								<label>每页记录：</label> <select id="pageSize" class="form-control">
									<option value="10">10</option>
									<option value="20">20</option>
									<option value="30">30</option>
								</select>
							</div>
							<div class="page_btn" id="pagination"></div>
							<!--分页效果结束-->
						</div>
					</div>
				</div>
			</div>
			<!--后台右侧布局end-->
		</div>
		<!--后台整体布局end-->
		<!--侧边栏end-->
	</div>
	
	<!-- 账单详情开始 -->
	<div class="modal fade" id="pay_detail" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				 <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >账单详情</h4>
            </div>
				<div class="modal-body">
					<div class="chezhu_box" style="height: 300px">
	                    <table class="table">
	                        <thead>
	                        <tr>
	                            <th>序号</th>
	                            <th>运单号</th>
	                            <th>发票类型</th>
	                            <th>货物名称</th>
	                            <th>支付对象</th>
	                        </tr>
	                        </thead>
	                        <tbody id="paylist">
	                        </tbody>
	                    </table>
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
	<!-- end -->
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
					<div class="juesemodal" style="border-bottom: 1px solid #e5e5e5;padding-bottom: 11px;">
						<label>收款人：</label><span data-name="payeeName"></span>
						<label>银行卡账户：</label><span data-name="payeeBankCardNumber"></span>
					</div>
					<div class="juesemodal payMoney" style="margin-bottom: 0px;">
						<div class="model_width">
							<label>账单总额：</label>
							<input type="text" data-name="billTotalPrice">
						</div>
						<div class="model_width">
							<label>应付金额：</label>
							<input type="text" data-name="amountPayable" readonly="readonly">
						</div>
						<div class="model_width">
							<label>油卡：</label>
							<input type="text" data-name="deductOilCard">
						</div>
						<div class="model_width">
							<label>扣重扣杂：</label>
							<input type="text" data-name="deductWeightMisc">
						</div>
						<div class="model_width">
							<label>扣款：</label>
							<input type="text" data-name="deductMoney">
						</div>
						<div class="model_width">
							<label>其他款项：</label>
							<input type="text" data-name="deductOther">
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
	<%@include file="../../../common/footer.jsp"%>
	<script type="text/javascript">
		$('.payMoney input').on('keyup', function(){
			var value = this.value;
			if(!$.isNumeric(value)){
				this.value = '';
			}
		}).on('focus', function(){
			var value = this.value;
			if(value == 0){
				this.value = '';
			}
		}).on('blur', function(){
			var value = this.value;
			if(!value){
				this.value = '0.00';
			}
		}).change(function(){
			var value = this.value;
			this.value = Number(value).toFixed(2);
		});
	</script>
	<script type="text/javascript"
		src="${scriptsRoot}/jquery.pagination.js"></script>
	<script type="text/javascript"
		src="/resources/js/payInvoice/driver/payInvoice.js"></script>
</body>
</html>
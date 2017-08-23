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
    <title>支付单详情</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css"  rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css"  rel="stylesheet">
    <link href="${stylesRoot }/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<body>

<div class="container-fluid">
   <!--公共头部begin-->
    <jsp:include page="../../../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
                <!--后台右侧布局begin-->
            <div class="col-md-10 ">
                <div class="ht_content">
                    <div style="float:left;">
	                    <div class="user_shtitle mt20">
	                        <h3>账单信息</h3>
	                    </div>
	                    <div class="row">
	                        <div class="col-md-12">
	                            <div class="person_cont">
	                               <label>运单号：</label><span>${pay.billNo }</span><br>
	                               <label>计划号：</label><span>${pay.plancode }</span><br>
	                               <label>发票类型：</label><span>${pay.invoiceName }</span><br>
	                               <label>货物名称：</label><span>${pay.cargoName }</span><br>
	                               <label>税率：</label><span>${pay.taxRate }</span><br>
	                               <label>车牌号：</label><span>${pay.vehicleNo }</span><br>
	                               <label>发货方名称：</label><span>${pay.shipMer }</span><br>
	                               <label>发货方编码：</label><span>${pay.shipMerCode }</span><br>
	                               <label>收货方名称：</label><span>${pay.conMer }</span><br>
	                               <label>收货方编码：</label><span>${pay.conMerCode }</span><br>
	                               <label>路线名称：</label><span>${pay.routeName }</span><br>
	                               <c:if test="${pay.pickupweight ne null}">
	                               <label>提货重量：</label><span>${pay.pickupweight }吨</span><br>
	                               </c:if>
	                               <c:if test="${pay.signweight ne null}">
	                               <label>卸货重量：</label><span>${pay.signweight }吨</span><br>
	                               </c:if>
	                               <c:if test="${pay.trueweight ne null}">
	                               <label>签收重量：</label><span>${pay.trueweight }吨</span><br>
	                               </c:if>
                              	 <c:if test="${pay.pickupimgurl ne null}">
	                               <label>磅单图片：</label><span><a href="/imageView/index?imageUrl=${pay.pickupimgurl }" target="_blank">提货磅单</a></span><br>
                              	 </c:if>
                              	  <c:if test="${pay.signimgurl ne null}">
	                               <label>磅单图片：</label><span><a href="/imageView/index?imageUrl=${pay.signimgurl }" target="_blank">卸货磅单</a></span><br>
                              	  </c:if>
	                               <c:if test="${pay.remarkImg ne null}">
	                               <label>磅单图片：</label><span><a href="/imageView/index?imageUrl=${pay.remarkImg }" target="_blank">附件图片</a></span><br>
	                               </c:if>
	                            </div>
	                        </div>
	                    </div>
                    </div>
                    <div style="float:left;">
	                    <div class="user_shtitle mt20">
	                        <h3>用户信息</h3>
	                    </div>
	                    <div class="row">
	                        <div class="col-md-12">
	                            <div class="person_cont">
	                               <label>车主账号：</label><span>${pay.venderCellphone }</span><br>
	                               <label>车主名称：</label><span>${pay.venderName }</span><br>
	                               <label>货主账号：</label><span>${pay.ownerCellphone }</span><br>
	                               <label>货主名称：</label><span>${pay.ownerName }</span><br>
	                               
	                               <label>签收账号：</label><span>${pay.receiveCellphone }</span><br>
	                               <label>签收名称：</label><span>${pay.receiveName }</span><br>
	                               
	                               <label>司机账号：</label><span>${pay.driverCellphone }</span><br>
	                               <label>司机名称：</label><span>${pay.driverName }</span><br>
	                               <label>司机安联账号：</label><span>${pay.driverAlcode }</span><br>
	                            </div>
	                        </div>
	                    </div>
                    </div>
                    <div style="float:left;">
	                    <div class="user_shtitle mt20">
	                        <h3>前台扣款信息</h3>
	                    </div>
	                    <div class="row ">
	                         <div class="col-md-12">
	                            <div class="person_cont">
	                               <label>运单重量：</label><span>${pay.billWeight }吨</span><br>
	                               <label>运单单价：</label><span>${pay.billPrice }元/吨</span><br>
	                               <label>运单总价：</label><span>${pay.receptionBillTotalPrice }元</span><br>
	                               <label>扣重扣杂：</label><span>${pay.receptionDeductWeightMisc }元</span><br>
	                               <label>扣款：</label><span>${pay.receptionDeductMoney }元</span><br>
	                               <label>其他扣款：</label><span>${pay.receptionDeductOther }元</span><br>
	                               <label>油卡扣款：</label><span>${pay.receptionDeductOilCard }元</span><br>
	                            </div>
	                        </div>
	                    </div>
                    </div>
                    <div style="float:left;">
	                    <div class="user_shtitle mt20">
	                        <h3>后台扣款信息</h3>
	                    </div>
	                    <div class="row ">
	                         <div class="col-md-12">
	                            <div class="person_cont">
	                               <label>运单重量：</label><span>${pay.billweightB }吨</span><br>
	                               <label>运单单价：</label><span>${pay.billpriceB }元/吨</span><br>
	                               <label>运单总价：</label><span>${pay.backstageBillTotalPrice }元</span><br>
	                               <label>扣重扣杂：</label><span>${pay.backstageDeductWeightMisc }元</span><br>
	                               <label>扣款：</label><span>${pay.backstageDeductMoney }元</span><br>
	                               <label>其他扣款：</label><span>${pay.backstageDeductOther }元</span><br>
	                               <label>油卡扣款：</label><span>${pay.backstageDeductOilCard }元</span><br>
	                            </div>
	                        </div>
	                    </div>
                    </div>
                    <div style="float:left;">
	                    <div class="user_shtitle mt20">
	                        <h3>支付对象-<c:if test="${pay.payMent eq '1'}">司机</c:if>
	                                <c:if test="${pay.payMent eq '2'}">车主</c:if></h3>
	                    </div>
	                    <div class="row ">
	                         <div class="col-md-12">
	                            <div class="person_cont">
	                                <c:if test="${pay.payMent eq '1'}">
										   <label>支付账号：</label><span>${pay.driverCellphone }</span><br>
			                               <label>收款名称：</label><span>${pay.driverName }</span><br>
									</c:if>
			                        <c:if test="${pay.payMent eq '2'}">
			                        	<label>支付账号：</label><span>${pay.venderCellphone }</span><br>
			                            <label>收款名称：</label><span>${pay.venderName }</span><br>
			                        </c:if>
			                        <label>银行卡号：</label><span>${pay.bankcard }</span><br>
			                        <label>银行卡名称：</label><span>${pay.bankname }</span><br>
			                        <label>开户行名称：</label><span>${pay.bankAdreess }</span><br>
	                            </div>
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
<!--通过begin-->
<div class="modal fade" id="pass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >个人用户审核</h4>
            </div>
            <div class="modal-body">
                确定通过审核吗？
            </div>
            <div class="modal-footer">
                <button type="button" onclick="adminReview('1')" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--通过end-->
<%@include file="../../../common/footer.jsp" %>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
</body>
</html>
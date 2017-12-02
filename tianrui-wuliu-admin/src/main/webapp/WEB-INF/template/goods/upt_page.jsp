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
    <title>货源派发管理</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
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
                    <div class="user_shtitle mt10">
                        <h3>基本信息</h3>
                    </div>
                    <!--基本信息begin-->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="info_base">
                                <div class="info_base_solo">
                                    <label>货源编号：</label>
                                    <span>${data.plancode }</span>
                                </div>
                                <div class="info_base_solo">
                                    <label>货物名称：</label>
                                    <span>${data.cargoname }</span>
                                </div>
                                 <div class="info_base_solo">
                                    <label>组织名称：</label>
                                    <span>${data.orgname }</span>
                                </div>
                                
                                <div class="info_base_solo">
                                    <label>货主名称：</label>
                                    <span>${data.ownerName }</span>
                                </div>
                                <div class="info_base_solo">
                                    <label>货主账号：</label>
                                    <span>${data.ownerCellphone }</span>
                                </div>
                                <div class="info_base_solo">
                                    <label>开始时间：</label>
                                    <span>${data.starttime }</span>
                                </div>
                                
                                <div class="info_base_solo">
                                    <label>发货方：</label>
                                    <span>${data.shipper }</span>
                                </div>
                                 <div class="info_base_solo">
                                    <label>收货方：</label>
                                    <span>${data.consignee }</span>
                                </div>
                                <div class="info_base_solo">
                                    <label>结束时间：</label>
                                    <span>${data.endtime }</span>
                                </div>
                                
                                <div class="info_base_solo">
                                    <label>发货人：</label>
                                    <span>${data.sendperson }-${data.sendpersonphone }</span>
                                </div>
                                <div class="info_base_solo">
                                    <label>收货人：</label>
                                    <span>${data.receiveperson }-${data.receivepersonphone }</span>
                                </div>
                                 <div class="info_base_solo">
                                    <label>联系人：</label>
                                    <span>${data.linkman }-${data.telephone }</span>
                                </div>
                                
                                 <div class="info_base_solo">
                                    <label>发货总量：</label>
                                    <span>${data.totalplanned }${data.measure }</span>
                                </div>
                                 <div class="info_base_solo">
                                    <label>已发量：</label>
                                    <span>${data.completed }</span>
                                </div>
                                <div class="info_base_solo">
                                    <label>货物单价：</label>
                                    <span>${data.price }${data.priceunits }</span>
                                </div>
                                
                                <div class="info_base_solo">
                                    <label>支付对象：</label>
                                    <span>
                                    <c:if test="${data.payment eq 1}">
                                    	司机
                                    </c:if>
                                    <c:if test="${data.payment eq 2}">
                                    	车主
                                    </c:if>
                                    </span>
                                </div>
                                 <div class="info_base_solo">
                                    <label>货源状态：</label>
                                    <span>
		                                  <c:if test="${data.status eq 0}">
		                                  	待审核
		                                  </c:if>
		                                  <c:if test="${data.status eq 1}">
		                                  	审核通过
		                                  </c:if>
		                                  <c:if test="${data.status eq 2}">
		                                  	发单中
		                                  </c:if>
		                                  <c:if test="${data.status eq 3}">
		                                  	已完成
		                                  </c:if>
		                                  <c:if test="${data.status eq 4}">
		                                  	已关闭 
		                                  </c:if>
		                                  <c:if test="${data.status eq 9}">
		                                  	审核失败
		                                  </c:if>
                                    </span>
                                </div>
                                <div class="info_base_solo">
                                </div>
                                <div class="info_base_solo">
                                </div>
                                
                                <div class="info_base_solo">
                                    <span>
		                                  <c:if test="${data.status eq 0}">
		                                  <!-- 
		                                  	<button class="btn btnblue audit" type="1">审核通过</button>
		                                   -->
		                                  	<button class="btn btnblue" data-toggle='modal'  data-target='#shenhe'>审核通过</button>
	                                    	<button class="btn btngreen audit" type="9">审核失败</button>
		                                  </c:if>
		                                  <c:if test="${data.status eq 1}">
		                                  	<button class="btn btnblue audit" type="4">关闭货源</button>
		                                  </c:if>
		                                  <c:if test="${data.status eq 2}">
		                                  	<button class="btn btnblue audit" type="4">关闭货源</button>
		                                  </c:if>
		                                  <c:if test="${data.status eq 3}">
		                                  	<button class="btn btnblue audit" type="4">关闭货源</button>
		                                  </c:if>
                                    </span>
                                </div>
                                 
                            </div>
                        </div>
                    </div>
                    <div class="user_shtitle mt20">
                        <h3>车主信息</h3>
                    </div>
                    <div class="row">
	                    <div class="col-md-12">
	                    	 <div class="info_base">
	                             <div class="info_base_solo">
		                             <label>请输入车主账号：</label>
		                             <span> <input id="venderCellphone" type="text" list="venderList">
				                        <datalist id="venderList">
				                        </datalist>
		                             </span>
	                             </div>
	                         </div>
	                    </div>
                    </div>
                    <div class="user_shtitle mt20">
                    </div>
                    <div class="row vender_detail">
	                    <div class="col-md-12">
	                    	 <div class="info_base">
		                    	 <div class="info_base_solo">
		                             <label>车主账号：</label>
		                             <span id="vender_cellphone">
		                             </span>
		                         </div>
		                         <div class="info_base_solo">
		                             <label>车主姓名：</label>
		                             <span id="vender_name">
		                             </span>
		                         </div>
		                         <div class="info_base_solo">
		                         </div>
		                         <div class="info_base_solo">
		                             <label>自有运力：</label>
		                             <span id="vender_cp1">
		                             </span>
		                         </div>
		                         <div class="info_base_solo">
		                             <label>委派运力：</label>
		                             <span id="vender_cp2">
		                             </span>
		                         </div>
		                         <div class="info_base_solo">
		                             <label>调用运力：</label>
		                             <span id="vender_cp3">
		                             </span>
		                         </div>
	                    	 </div>
	                    </div>
	                   <div class="row">
	                    <div class="col-md-12">
	                    	 <div class="info_base">
	                            <div class="info_base_solo">
		                             <label>单价：</label>
		                             <span><input id="plan_price" type="text" value="${data.price }">${data.priceunits }
		                             </span>
		                         </div>
		                         <div class="info_base_solo">
		                             <label>发货量：</label>
		                             <span><input id="plan_weight" type="text" value="${data.totalplanned - data.completed}">${data.measure }
		                             </span>
		                         </div>
		                         <input type="hidden" id="vender_id">
		                         <input type="hidden" id="goods_id" value="${data.id }">
	                            <div class="info_base_solo">
	                                    <button class="btn btnblue save_paln">派发</button>
	                                    <!-- 
	                                    <button class="btn btngreen reset">返回</button>
	                                     -->
	                            </div>
	                        </div>
	                    </div>
                    </div>
                </div>
                <div class="user_shtitle mt20">
                    <h3>派发记录</h3>
                </div>
                <div class="track-rcol">
                    <div class="track-list">
                        <ul id="goods_list">
                       
                        </ul>
                    </div>
                </div>
            </div>
                <!--后台右侧布局end-->
            </div>
            <!--后台整体布局end-->
    </div>
    <!--侧边栏end-->
<!--审核begin-->
<div class="modal fade" id="shenhe" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >推送选择</h4>
            </div>
            <div class="modal-body">
           		<div class="file_detail">
					<input name="ptype_1" type="checkbox" value="1">
					平台推送
				</div>
				<div class="file_detail">
					<input name="ptype_2" type="checkbox" value="1">
					APP站内推送
				</div>
				<div class="file_detail">
					<input name="ptype_3" type="checkbox" value="1">
					短信推送
				</div>
           		<div class="clear"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary audit_succe" >确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
<script type="text/javascript" src="/resources/js/goods/upt_page.js?1202"></script>
<script type="text/javascript">
    var trRoot = "${trRoot}";
</script>
</body>
</html>
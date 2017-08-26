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
    <title>中交兴路车辆管理</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css" rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css" rel="stylesheet">
    <link href="${trRoot}/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
</head>
<body>

<div class="container-fluid">
     <input type="hidden" id="recPage" value="${pageNo }">
     <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
            <!--后台右侧布局begin-->
           <div class="col-md-10 ">
                <div class="ht_content">
                    <div id="content-header">
                        <h3>中交兴路车辆管理</h3>
                    </div>
                    <!--查询框begin-->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="contuser_search">
                                <div class="ht_div">
                                    <label>车牌号码:</label>
                                    <input type="text"   id="vehicleno" >
                                </div>
                                <div class="ht_div">
                                    <label>车辆状态:</label>
                                    <select class="form-control" id="vehiclelogos">
                                        <option value="">请选择</option>
                                        <option value="1">启用</option>
                                        <option value="0">禁用</option>
                                    </select>
                            	</div>
                               	<div class="ht_divbtn">
			                        <button class="btn btnblue " onclick="loadSearch()" type="button">搜索</button>
                                   	<button class="btn btngreen" onclick="clearSearch()" type="button">重置</button>
		                        </div>
                            </div>
                    	</div>
                    </div>
                    <!--查询框end-->
                    <div class="row mt15">
                        <div class="col-md-12">
                            <div class="content-user">
                             <div class="content-tou">
                                 	<button data-toggle="modal"  onclick="empty()" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i><span>新增</span></button>
                                 </div>
                                <!--用户表格begin-->
                                <table id="auditReport" class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th >序号</th>
                                        <th >车牌号</th>
						                <th >中交标识</th>
						                <th >车辆标识</th>
						                <th >创建人</th>
						                <th >创建时间</th>
						                <th >操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="innerhml">
                                    
                                    </tbody>
                                </table>
                                <!--用户表格end-->
                            </div>
                        </div>
                    </div>
                        <div class="clear"> </div>
                    <!-- 分页部分  开始-->
						            <div class="row pr20 fr">
										<%@include file="../common/pagination.jsp" %>
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


<!--新增车辆对话框begin-->
<!--  <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="height: 500px;">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >新增车辆</h4>
            </div>
            <div class="modal-body" style="height: 200px;">
                        <label><i style="color: #ff2f00;">*</i>车牌号码：</label><input type="text" maxlength="16" id="vehiclenos">
                        <button type="button" class="btn btn-primary" onclick="select()">搜索</button></br>
                        <input type="hidden" id="modal_add_id">
                        <label><i style="color: #ff2f00;">*</i>中交标识：</label><input type="text" maxlength="16" id="crossloge"></br>
                        <label><i style="color: #ff2f00;">*</i>车辆状态:</label>
                        <select id="vehiclelogo" maxlength="50" >
                            <option value="">请选择</option>
                            <option value="0">启用</option>
                            <option value="1">停用</option>
                        </select>
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="modal_add_save" onclick="saveAdd()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>-->
<!--新增车辆对话框end-->
<!--新增车辆begin-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <form id="saveMerchant">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">新增车辆信息</h4>
            </div>
            <div class="modal-body" style=" ">
                <div class="usermodal userlabel3">
                    <div >
                        <label><i style="color: #ff2f00;">*</i>车牌号码：</label>
                        <input name="vehiclenos" id="vehiclenos" type="text"> <button type="button" class="btn btn-primary" onclick="select()">搜索</button>
                    </div><br>
                    <div class="model_width"  style="display: none" name="selects">
                        <label>添加账号：</label>
                        <input name="cellphone" id="cellphone" type="text">
                    </div>
                     <div class="model_width" style="display: none" name="selects">
                        <label>所有人电话：</label>
                        <input maxlength="8" name="vehiOwnerTel" maxlength="11"  id="vehiOwnerTel" type="text">
                    </div>
                    <div class="model_width" style="display: none" name="selects">
                        <label>所有人名称：</label>
                        <input type="text" id="vehiOwnerName" name="vehiOwnerName">
                    </div>
                    <div class="model_width" style="display: none" name="selects">
                        <label>车辆名称：</label>
                        <input type="text" id="vehicletypename" name="vehicletypename">
                    </div>
                    <div class="model_width" style="display: none" name="selects">
                        <label>车辆状态：</label>
                        <input type="text" id="status" name="status">
                    </div>
                    <div class="model_width" style="display: none" name="selects">
                        <label>开票状态：</label>
                        <input type="text" id="desc1" name="desc1" >
                    </div>
                    <div class="model_width" style="display: none" name="selects">
                        <label>认证方式：</label>
                        <input type="text" id="desc2" name="desc2">
                    </div>
                    
                    <div class="model_width" >
                        <label>中交标识：</label>
                        <input name="crossloge" id="crossloge" type="text">
                    </div>
                    <div class="model_width">
                        <label>车辆标识：</label>
                        <select class="form-control" id="vehiclelogo" name="type">
                           	<option value="">请选择</option>
                            <option  value="1" >启用</option>
                            <option value="0">禁用</option>
                        </select>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary save_Merchant" onclick="saveAdd()">保存</button>
                <button type="button" id="addclick" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
    </form>
</div>
<!--新增车辆end-->
<%@include file="../common/footer.jsp" %>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>
<script type="text/javascript">
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="${trRoot}/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/js/bootstrap.js"></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot}/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${scriptsRoot}/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="/resources/js/adminMember/cross_vehicle.js?0819" ></script>
<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>

</body>
</html>
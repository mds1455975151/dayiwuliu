<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.tianrui.service.admin.bean.Users" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Users user = (Users) request.getSession().getAttribute("session_user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>天瑞物流平台-我的货物</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot}/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot}/base.css" rel="stylesheet">
    <link href="${stylesRoot}/style.css" rel="stylesheet">
    <link href="${stylesRoot}/tr-media.css" rel="stylesheet">
    <link href="${stylesRoot}/easyTree.css" rel="stylesheet">
    <link href="${stylesRoot}/jquery-ui.min.css" rel="stylesheet">
    <link href="${stylesRoot}/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
    <%-- <link href="${stylesRoot}/jquery-ui.min.css" rel="stylesheet"> --%>
    <link href="${stylesRoot}/imgcut.css" rel="stylesheet">
    <link rel="Shortcut Icon" href="${imagesRoot}/favicon.ico" type="image/x-icon">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<div class="container-fluid">
		<!--公共头部begin-->
		<jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
		<!--公共头部end-->
		    <!--后台右侧布局begin-->
		    <div class="col-md-10 ">
		        <div class="ht_content">
		            <div id="content-header">
		                <h3>我的货物</h3>
		            </div>
		            <!--查询框begin-->
		            <div class="row">
		                <div class="col-md-12">
		                    <div class="contuser_search">
		                        <!-- <div class="ht_div">
		                            <label>组织名称：</label>
		                            <input type="text" placeholder=" " id="myCargo_orgName" value="天瑞股份有限公司">
		                        </div> -->
		                        <div class="ht_div">
		                            <label>物料编码：</label>
		                            <input type="text" placeholder=" " maxlength="16" id="myCargo_materCode">
		                        </div>
							    <div class="ht_div">
		                            <label>物料名称：</label>
		                            <input type="text" placeholder=" " maxlength="16" id="myCargo_materName">
		                            <input type="hidden" id="myCargo_materId">
		                        </div>
		                        <div class="ht_div">
		                            <label>状态：</label>
		                            <select class="form-control" id="myCargo_state">
		                                <option value="-1">全部</option>
		                                <option value="1">启用</option>
		                                <option value="0">停用</option>
		                            </select>
		                        </div>
		                        <!-- 
		                        <div class="ht_div">
		                            <label>计量单位：</label>
		                            <select class="form-control" id="myCargo_mainMeasUnit">
		                                <option>请选择</option>
		                                <c:forEach items="${measureBase }" var="aa">
					                       <option value="${aa.measureName }">${aa.measureName }</option>
				                        </c:forEach>
		                            </select>
		                        </div>
		                         -->
		                        <div class="ht_divbtn">
			                        <button class="btn btnblue" type="submit" id="myCargo_search">搜索</button>
			                        <button class="btn btngreen" type="reset" id="myCargo_reset">重置</button>
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
		                                    class="glyphicon glyphicon-plus" id="addButton"></i><span>新增</span></button>
		                             <button id="batchDisable"><i class="iconfont icon-tingyong"></i><span>批量停用</span></button>
		                            <!--  
		                            <a href=" ">
		                                <button id="importButton"><i class="iconfont icon-daoru06"></i>导入</button>
		                            </a>
		                            <button id="exportButton"><i class="iconfont icon-daochu1"></i><span>导出</span></button>
		                        	-->
		                        </div>
		                        <!--用户表格begin-->
		                        <table class="table table-bordered">
		                            <thead>
		                            <tr>
		                                <th data-options="field:'ck',checkbox:true"><input type="checkbox" onclick="selectAll()" name="allUser"></th>
		                                <th>物料编码</th>
		                                <th>物料名称</th>
		                                <th>计量单位</th>
		                                <th>状态</th>
		                                <th>操作</th>
		                            </tr>
		                            </thead>
		                            <tbody id="myCargo_tbody">
		                            
		                            	<!-- TO GET DATA! -->
		                            
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
	<!--新增我的货物begin-->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id=" ">货物新增</h4>
	            </div>
	            <div class="modal-body" style="height: 460px;">
	                <div class="usermodal userlabel3">
		                <div class="model_width">
		                    <label><i style="color: #ff2f00;">*</i>物料名称：</label><input type="text" maxlength="5" id="modal_add_materName">
		                	<input type="hidden" id="modal_add_materId">
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
	<!--新增货物end-->
	
	<!--查看详情begin-->
	<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id=" ">货物档案详情</h4>
	            </div>
	            <div class="modal-body" style="height: 350px;">
	                <!-- <div class="file_detail">
	                    <label>组织名称：</label><span id="modal_detail_orgName">山水公司水泥厂</span>
	                </div>
	                <div class="file_detail">
	                    <label>组织类型：</label><span id="modal_detail_orgType">组织的类型</span>
	                </div>
	                <div class="file_detail">
	                    <label>组织编码：</label><span id="modal_detail_orgCode">shhnkdjl</span>
	                </div> -->
	                <div class="file_detail">
	                    <label>物料编码：</label><span id="modal_detail_materCode">4564212315641212</span>
	                </div>
	                <div class="file_detail">
	                    <label>物料名称：</label><span id="modal_detail_materName">钢铁呵呵呵呵</span>
	                </div>
	                <div class="file_detail">
	                    <label>物料类别：</label><span id="modal_detail_materClass">钢铁</span>
	                </div>
	                <div class="file_detail">
	                    <label>状态：</label><span id="modal_detail_state">可用</span>
	                </div>
	                <div class="file_detail">
	                    <label>货物规格：</label><span id="modal_detail_spec">shhnkdjl</span>
	                </div>
	                <div class="file_detail">
	                    <label>货物型号：</label><span id="modal_detail_model">452121</span>
	                </div>
	                <div class="file_detail">
	                    <label>物料助记码：</label><span id="modal_detail_materMNCode">shhnkdjl</span>
	                </div>
	                <div class="file_detail">
	                    <label>主计量单位：</label><span id="modal_detail_mainMeasUnit">吨</span>
	                </div>
	                <br/>
	                <div class="file_detail2">
	                    <label>货物图片：</label><span><img id="modal_detail_img" src="${imagesRoot}/img3.jpg"></span>
	                </div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" id="modal_detail_save">保存</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--查看详情end-->
	
	<!--启用/停用begin-->
	<div class="modal fade" id="enDisableModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">提示</h4>
	                <input type="hidden" id="modal_endisable_id">
	                <input type="hidden" id="modal_endisable_state">
	                <input type="hidden" id="modal_endisable_rowIndex">
	            </div>
	            <div class="modal-body">
	                <h4 id="modal_endisable_content">确定要停用吗</h4>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" id="modal_endisable_button">确定</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--启用/停用end-->
	<!--删除begin-->
	<div class="modal fade" id="dele_mate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">我的货物信息删除</h4>
	                <input type="hidden" id="modal_del_id">
	                <input type="hidden" id="modal_del_rowIndex">
	            </div>
	            <div class="modal-body">
	                <h4>确定要删除此条信息吗?</h4>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" id="modal_del_button">确定</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--删除end-->
	<%@include file="../../common/footer.jsp" %>
	<script type="text/javascript">
	    var CONTEXTPATH="${contextPath}";
	    var imagesRoot = "${imagesRoot}";
	    var userName = "<%= user.getUsertype() %>";
	    var orgName = "<%= user.getOrgname() %>";
	    var orgId = "<%= user.getOrgid() %>";
	    var imagesRoot="${imagesRoot }";
	</script>
	<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/resources/js/myCargo/myCargoPage.js?0811"></script>
	<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
	<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
</body>
</html>

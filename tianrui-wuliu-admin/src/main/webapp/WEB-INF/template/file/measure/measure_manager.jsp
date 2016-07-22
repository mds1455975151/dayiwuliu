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
    <title>档案管理—计量单位</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css" rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css" rel="stylesheet">
    <link href="${stylesRoot }/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="Shortcut Icon" href="${imagesRoot }/favicon.ico" type="image/x-icon">

</head>
<body>

<div class="container-fluid">
    <jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
    <!--侧边栏begin-->
            <div class="col-md-10 ">
                <div class="ht_content">
                    <div id="content-header">
                        <h3>计量单位</h3>
                    </div>
                    <!--计量单位切换begin-->
                    <div>
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="#single" data-toggle="tab">
                                    	单量纲单位
                                </a>
                            </li>
                            <!-- 
                            <li><a href="#multiple" onclick="measureMainSearch();" data-toggle="tab"> 多量纲单位</a></li>
                             -->
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="single">
                                <!--查询框begin-->
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="contuser_search">
                                            <div class="ht_div">
                                                <label>计量单位编码：</label>
                                                <input type="text" placeholder="" maxlength="16" id="oCode" name="measureCode" >
                                            </div>
                                            <div class="ht_div">
                                                <label>计量单位名称：</label>
                                                <input type="text" placeholder=" " maxlength="16" id="oName" name="measureName">
                                            </div>
                                            <div class="ht_div">
                                                <label>量纲：</label>
                                                <select class="form-control" id="oType" name="measureType">
                                                    <c:forEach items="${measureBase }" var="base">
	                                                    <option value="${base.measureType }"
                                                    	<c:if test="${measure.measureType eq base.measureType}">
                                                    	selected="selected"
                                                    	</c:if>
	                                                    >${base.measureType }</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <button class="btn btn-primary btn-sm"  onclick="search();" id="searchMeasure" type="button">搜索</button>
                                            <button class="btn btn-success btn-sm" onclick="resetOne();" id="reset" type="reset">重置</button>
                                        </div>
                                        </div>
                                </div>
                                <!--查询框end-->
                                <div class="row mt15">
                                    <div class="col-md-12">
                                        <div class="content-user">
                                            <div class="content-tou">
                                                <button data-toggle="modal" data-target="#add_sin"><i
                                                        class="glyphicon glyphicon-plus"></i><span>新增</span></button>
                                               <!--  
                                                <a href="file_importdetail.html"><button><i class="iconfont icon-daoru06"></i>导入</button></a>
                                                <button><i class="iconfont icon-daochu1"></i><span>导出</span></button>
                                            	-->
                                            </div>
                                            <!--用户表格begin-->
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>序号</th>
                                                    <th>计量单位编码</th>
                                                    <th>计量单位名称</th>
                                                    <th> 量纲</th>
                                                    <th>是否基本单位</th>
                                                    <th>换算率</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="measureList">
                                                </tbody>
                                            </table>
                                            <!--用户表格end-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                             <!-- 
                             <div class="ht_div">
                                       		数值：<input type="text" id="text1" value="">
                                       	</div>
                                         <div class="ht_div">  
                                        	编号： <input type="text" id="text2" value="">
                                         </div> 
                                         <div class="ht_div">
                                            <select onchange="changeType('text2Type');" class="form-control" id="text2Type">
                                                <option value="" selected="selected">请选择</option>
                                                <c:forEach items="${measureBase }" var="base">
	                                                 <option value="${base.measureType }">${base.measureType }</option>
                                                </c:forEach>
                                            </select>
                                            <span id="codeBasetext2Type"></span>
                                       </div>
                                        <button class="btn btn-primary btn-sm" onclick="countValue()" type="button">计算</button>
                              -->
                                   
                            <div class="tab-pane fade" id="multiple">
                                <div class="row mt15">
                                    <div class="col-md-12">
                                        <div class="content-user">
                                            <div class="content-tou">
                                                <button data-toggle="modal" data-target="#add_multi"><i
                                                        class="glyphicon glyphicon-plus"></i><span>新增</span></button>
                                                <a href="file_importdetail.html"><button><i class="iconfont icon-daoru06"></i>导入</button></a>
                                                <button><i class="iconfont icon-daochu1"></i><span>导出</span></button>
                                            </div>
                                            <!--用户表格begin-->
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>序号</th>
                                                    <th>量纲1</th>
                                                    <th>量纲2</th>
                                                    <th>换算率</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="innHml">
                                                </tbody>
                                            </table>
                                            <!--用户表格end-->
                                        </div>
                                    </div>
                                </div>
                                <div class="row pr20 fr">
                                    <ul class="pagination ">
                                        <li><a href="#">&laquo;首页</a></li>
                                        <li><a href="#">&lsaquo;上一页</a></li>
                                        <li><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">...</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                        <li><a href="#">下一页&rsaquo;</a></li>
                                        <li><a href="#">尾页&raquo;</a></li>
                                    </ul>
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
<!--新增单量纲计量单位begin-->
<div class="modal fade" id="add_sin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" id="closeSave" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">单量纲计量单位新增</h4>
            </div>
            <div class="modal-body" style="  ">
                <div class="usermodal userlabel4">
                    <label><i style="color: #ff2f00;">*</i>计量单位编码：</label><input id="code" value="" maxlength="16" type="text" >
                    <label><i style="color: #ff2f00;">*</i>计量单位名称：</label><input id="cname" maxlength="16" value="" type="text">
                </div>
                <div class="usermodal userlabel4">
                    <label><i style="color: #ff2f00;">*</i>计量单位量纲：</label>
                    <select onchange="changeType('type');" class="form-control" id="type">
                        <option value="" selected="selected">请选择</option>
                        <c:forEach items="${measureBase }" var="aa">
	                        <option value="${aa.measureType }">${aa.measureType }</option>
                        </c:forEach>
                    </select>
                    	新加量纲：<input id="newType" maxlength="16" value="" type="text">
                </div>
                <div class="usermodal userlabel4">
                    <label><i style="color: #ff2f00;">*</i>是否基本单位：</label>
                    <span><input type="radio" value="0" name="base" > 是 <input type="radio" value="1" name="base" checked>否</span>
                    <label><i style="color: #ff2f00;">*</i>原基本单位：</label><span id="codeBasetype"></span>
                    <label><i style="color: #ff2f00;">*</i>换算率：</label><input id="conversion" maxlength="10" value="" type="text">
                </div>
                <div class="usermodal_note">
                    提示：是否基本单位选择“是”以后，此单位将成为基本单位，换算率是1，原基本单位作废；选“否”后，换算率以基本单位为基准，例如基本单位是kg，则g的换算率是0.001
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="oneMeasureSave();" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--新增单量纲计量单位end-->

<!--修改单量纲计量单位begin-->
<div class="modal fade" id="update_osin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            	<input type="hidden" value="" id="update_id">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" id="closeUpdate" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">单量纲计量单位修改</h4>
            </div>
            <div class="modal-body" style="  ">
                <div class="usermodal userlabel4">
                    <label><i style="color: #ff2f00;">*</i>计量单位编码：</label><input id="update_code" value="" maxlength="16" type="text" >
                    <label><i style="color: #ff2f00;">*</i>计量单位名称：</label><input id="update_cname" value="" maxlength="16" type="text">
                </div>
                <div class="usermodal userlabel4">
                    <label><i style="color: #ff2f00;">*</i>计量单位量纲：</label>
                    <select onchange="changeType('update_type');" class="form-control" id="update_type">
                      	<!-- 
                        <option value="" selected="selected">请选择</option>
                        <c:forEach items="${measureBase }" var="aa">
	                        <option value="${aa.measureType }">${aa.measureType }</option>
                        </c:forEach>
                         -->
                    </select>
                </div>
                <div class="usermodal userlabel4">
                    <label><i style="color: #ff2f00;">*</i>是否基本单位：</label>
                    <span><input type="radio" value="0" name="update_base" checked> 是 <input type="radio" value="1" name="update_base">否</span>
                    <label><i style="color: #ff2f00;">*</i>原基本单位：</label><span id="codeBaseupdate_type"></span>
                    <label><i style="color: #ff2f00;">*</i>换算率：</label><input id="update_conversion" value="" maxlength="16" type="text">
                </div>
                <div class="usermodal_note">
                    提示：若为基本量纲请慎重修改，基本量纲不可修改为普通量纲
              </div>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="oneMeasureUpdate();" class="btn btn-primary">修改</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--修改单量纲计量单位end-->

<!--新增多量纲计量单位begin-->
<div class="modal fade" id="add_multi" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">多量纲计量单位新增</h4>
            </div>
            <div class="modal-body" style="  ">
                
                <div class="usermodal userlabel4">
                    <!-- 量纲1 -->
                    <label><i style="color: #ff2f00;">*</i>量纲1：</label>
                    <select class="form-control" id="m1type" onchange="changeType('m1type')">
                       <option value="" selected="selected">请选择</option>
                        <c:forEach items="${measureBase }" var="aa">
	                        <option value="${aa.measureType }">${aa.measureType }</option>
                        </c:forEach>
                    </select> 基本单位: <span id="codeBasem1type"></span><br>
                    <!-- 量纲2 -->
                    <label><i style="color: #ff2f00;">*</i>量纲2：</label>
                    <select class="form-control" id="m2type" onchange="changeType('m2type')">
                        <option value="" selected="selected">请选择</option>
                        <c:forEach items="${measureBase }" var="aa">
	                        <option value="${aa.measureType }">${aa.measureType }</option>
                        </c:forEach>
                    </select> 基本单位: <span id="codeBasem2type"></span>
                </div>
                <div class="usermodal userlabel4">
                    <label><i style="color: #ff2f00;">*</i>换算率：</label><input id="mconversion" type="text">
                </div>
                <div class="usermodal_note">
                    提示：以10kg/M<sup>2</sup> 要换算成kg或米为例，量纲1的基本单位是10kg/M<sup>2</sup> ，量纲2的基本单位选kg，
                    则换算率是1kg除以10kg/M<sup>2</sup>，等于0.1M，如果量纲2的基本单位选M，则换算率是10kg/M<sup>2</sup>乘以1M，等于10，
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="saveMeasureMain();" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--新增多量纲计量单位end-->

<!--修改多量纲计量单位begin-->
<div class="modal fade" id="update_multi" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">多量纲计量单位修改</h4>
            </div>
            <div class="modal-body" style="  ">
                
                <div class="usermodal userlabel4">
                    <input type="hidden" id="Upt_id" value="">
                    <!-- 量纲1 -->
                    <label><i style="color: #ff2f00;">*</i>量纲1：</label>
                    <select class="form-control" id="m1type_upt" onchange="changeType('m1type_upt')">
                       <option value="" selected="selected">请选择</option>
                        <c:forEach items="${measureBase }" var="aa">
	                        <option value="${aa.measureType }">${aa.measureType }</option>
                        </c:forEach>
                    </select> 基本单位: <span id="codeBasem1type_upt"></span><br>
                    <!-- 量纲2 -->
                    <label><i style="color: #ff2f00;">*</i>量纲2：</label>
                    <select class="form-control" id="m2type_upt" onchange="changeType('m2type_upt')">
                        <option value="" selected="selected">请选择</option>
                        <c:forEach items="${measureBase }" var="aa">
	                        <option value="${aa.measureType }">${aa.measureType }</option>
                        </c:forEach>
                    </select> 基本单位: <span id="codeBasem2type_upt"></span>
                </div>
                <div class="usermodal userlabel4">
                    <label><i style="color: #ff2f00;">*</i>换算率：</label><input id="mconversion_upt" type="text">
                </div>
                <div class="usermodal_note">
                    提示：以10kg/M<sup>2</sup> 要换算成kg或米为例，量纲1的基本单位是10kg/M<sup>2</sup> ，量纲2的基本单位选kg，
                    则换算率是1kg除以10kg/M<sup>2</sup>，等于0.1M，如果量纲2的基本单位选M，则换算率是10kg/M<sup>2</sup>乘以1M，等于10，
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="moreMeasureUpdate();" class="btn btn-primary">修改</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--修改多量纲计量单位end-->

<!--单位删除begin-->
<div class="modal fade" id="dele" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" id="closedelect" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">计量单位删除</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="measureId" value="">
                <h4>您确定要删除此计量单位吗？</h4>
                <h4 id="dtext" style="color: red"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="measureDelect();" class="btn btn-primary">确定</button>
                <button type="button" onclick="clearDText();" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--单位删除end-->
<%@include file="../../common/footer.jsp" %>
<script type="text/javascript">
    $(function(){
        $("table").resizableColumns({});
    });
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>

<script type="text/javascript" src="<%=basePath%>/resources/js/measure/measure_manager.js" ></script>

</body>
</html>
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
    <title>客商档案</title>
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
	<script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

<div class="container-fluid">
 <!--公共头部begin-->
    <jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
    <!--后台右侧布局begin-->
    <div class="col-md-10 ">
        <div class="ht_content">
            <div id="content-header">
                <h3>客商档案</h3>
            </div>
            <!--查询框begin-->
            <div class="row">
                <div class="col-md-12">
                    <div class="contuser_search">
                        <div class="ht_div">
                            <label>客商编码：</label>
                            <input type="text" id="scode" maxlength="16" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>唯一识别码：</label>
                            <input type="text" id="sonlycode" maxlength="16" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>客商名称：</label>
                            <input type="text" id="sname" maxlength="16" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>地址：</label>
                            <input type="text" id="address" maxlength="16" placeholder=" ">
                        </div>
                        <div class="ht_divbtn">
                            <button onclick="displayData(0);" class="btn btnblue" type="submit">搜索</button>
                            <button onclick="clearSearch();" class="btn btngreen" type="submit">重置</button>
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
                                <th>客商编码</th>
                                <th>客商名称</th>
                                <th>唯一识别码</th>
                                <th>客商分类</th>
                                <th>企业性质</th>
								<th>联系人</th>
								<th>联系电话</th>
                                <th>详细地址</th>
                                <th>状态</th>
                                <th>备注</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="innerHtml">
                            
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
<!--侧边栏end-->

<!--新增begin-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <form id="saveMerchant">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">新增</h4>
            </div>
            <div class="modal-body" style=" ">
                <div class="usermodal userlabel3">
                    <div class="model_width">
                        <label><i style="color: #ff2f00;">*</i>客商编码：</label>
                        <input name="code" id="savecode" type="text">
                    </div>
                    <div class="model_width" id="blurcargo">
                        <label><i style="color: #ff2f00;">*</i>客商名称：</label>
                        <input name="name" id="savename" type="text">
                    </div>
                    <div class="model_width" id="blurroute">
                        <label><i style="color: #ff2f00;">*</i>唯一识别码：</label>
                        <input name="onlycode" id="saveonlycode" type="text">
                    </div>
                    <div class="model_width">
                        <label><i style="color: #ff2f00;">*</i>客商分类：</label>
                        <select class="form-control" id="savetype" name="type">
                           	<option value="">请选择</option>
                            <option value="1">合同</option>
                            <option value="2">指导</option>
                        </select>
                    </div>
                    <div class="model_width">
                        <label><i style="color: #ff2f00;">*</i>企业性质：</label>
                        <select class="form-control" id="savenature" name="nature">
                           	<option value="">请选择</option>
                            <option value="1">国营</option>
                            <option value="2">私营</option>
                        </select>
                    </div>
                    <div class="model_width">
                        <label><i style="color: #ff2f00;">*</i>联系人：</label>
                        <input maxlength="8" name="linkman" id="savelinkman" type="text">
                    </div>
                    <div class="model_width">
                        <label><i style="color: #ff2f00;">*</i>联系电话：</label>
                        <input type="text" maxlength="11" id="savelinknumber" name="linknumber">
                    </div>
                    <div class="model_width">
                        <label><i style="color: #ff2f00;">*</i>详细地址：</label>
                        <input type="text" id="saveaddress" name="address">
                    </div>
                    <div class="model_width">
                        <label><i style="color: #ff2f00;"></i>备注：</label>
                        <input type="text" id="saveremark" name="remark">
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary save_Merchant">保存</button>
                <button type="button" id="addclick" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
    </form>
</div>
<!--新增end-->

<!--修改begin-->
<div class="modal fade" id="edit_merchant" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <form id="uptMerchant">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">修改</h4>
            </div>
            <div class="modal-body" style=" ">
                <div class="usermodal userlabel3">
                    <div class="model_width">
                        <label><i style="color: #ff2f00;">*</i>客商编码：</label>
                        <input name="code" readonly="readonly" id="uptcode" type="text">
                        <input type="hidden" id="merchantid" name="id">
                    </div>
                    <div class="model_width" id="blurcargo">
                        <label><i style="color: #ff2f00;">*</i>客商名称：</label>
                        <input name="name" id="uptname" type="text">
                    </div>
                    <div class="model_width" id="blurroute">
                        <label><i style="color: #ff2f00;">*</i>唯一识别码：</label>
                        <input name="onlycode" readonly="readonly" id="uptonlycode" type="text">
                    </div>
                    <div class="model_width">
                        <label><i style="color: #ff2f00;">*</i>客商分类：</label>
                        <select class="form-control" id="upttype" name="type">
                           	<option value="">请选择</option>
                            <option value="1">合同</option>
                            <option value="2">指导</option>
                        </select>
                    </div>
                    <div class="model_width">
                        <label><i style="color: #ff2f00;">*</i>企业性质：</label>
                        <select class="form-control" id="uptnature" name="nature">
                            <option value="">请选择</option>
                            <option value="1">国营</option>
                            <option value="2">私营</option>
                        </select>
                    </div>
                    <div class="model_width">
                        <label><i style="color: #ff2f00;">*</i>联系人：</label>
                        <input maxlength="8" name="linkman" id="uptlinkman" type="text">
                    </div>
                    <div class="model_width">
                        <label><i style="color: #ff2f00;">*</i>联系电话：</label>
                        <input type="text" maxlength="11" id="uptlinknumber" name="linknumber">
                    </div>
                    <div class="model_width">
                        <label><i style="color: #ff2f00;">*</i>详细地址：</label>
                        <input type="text" id="uptaddress" name="address">
                    </div>
                    <div class="model_width">
                        <label><i style="color: #ff2f00;"></i>备注：</label>
                        <input type="text" id="uptremark" name="remark">
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="modal-footer">
            	<input type="hidden" id="pageNo" >
                <button type="button" onclick="updateMerchart();" data-dismiss="modal" class="btn btn-primary">修改</button>
                <button type="button" id="updateclick" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
    </form>
</div>
<!--修改运价end-->
<!--停用end-->
<!--删除begin-->
<div class="modal fade" id="dele_price" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">运价策略删除</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" value="" id="deleteid">
                <h4>确定要删除此运价策略吗?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="deletePrice();" data-dismiss="modal" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--删除end-->
<%@include file="../../common/footer.jsp" %>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // 表格列宽度手动调整
        $("table").resizableColumns({});

    });
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
<script type="text/javascript" src="/resources/js/merchant/file_merchant.js?09.10"></script>
</body>
</html>
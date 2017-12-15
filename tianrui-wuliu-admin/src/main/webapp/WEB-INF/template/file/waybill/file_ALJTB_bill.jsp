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
    <title>交通部运单管理/安联</title>
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
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
	<style type="text/css">
		.assessBody {
		    height: 35px;
		    line-height: 35px;
		}
		
		.assessBody label {
		    margin-left: 40px;
		}
		
		.assessBody label input {
		    vertical-align: text-top;
		    margin-top: 2px;*
		    margin-bottom:-4px;
		    margin-right: 10px;
		}
		div.submit_detail {
		    padding: 5px 0px;
		}
		div.submit_detail label {
		    margin: 0px;
		    padding: 0px;
		}
		div.submit_detail span {
		    padding-right: 50px;
		}
	</style>
</head>
<body>

<div class="container-fluid">
 <!--公共头部begin-->
    <jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
    <!--后台右侧布局begin-->
    <div class="col-md-10 ">
        <div class="ht_content">
            <div id="content-header">
                <h3>运单管理/安联</h3>
            </div>
            <!--查询框begin-->
            <div class="row">
                <div class="col-md-12">
                    <div class="contuser_search">
                        <div class="ht_div">
                            <label>运单编码：</label>
                            <input type="text" id="searchKey" placeholder=" ">
                        </div>
                         <div class="ht_div">
                            <label>车辆信息：</label>
                            <input type="text" id="vehicleno" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>推送状态：</label>
                            <select id="jtb"  class="form-control">
                                 <option value="0">未推送</option>
                                 <option value="1">已推送</option>
                                 <option value="3">已查看</option>
                            </select>
                        </div>
                         <div class="ht_div">
                             <label>创建时间：</label>
                             <input type="text" id="createtime"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width:160px"/>
                         </div>
                    <div class="ht_divbtn">
                        <button class="btn btnblue " onclick="searchFile();" type="button">搜索</button>
                        <button class="btn btngreen" onclick="clearSearch();" type="submit">重置</button>
                    </div>
                </div>
            </div>
            <!--查询框end-->
            <div class="row mt15">
                <div class="col-md-12">
                    <div class="content-user">
                   		<div class="content-tou">
                         <button id="batchDisable"><i class="iconfont icon-tingyong"></i><span>批量查看</span></button>
                        </div>
                        <!--用户表格begin-->
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                            	<th><input class="check_main" type="checkbox"></th>
                            	<th>序号</th>
                                <th>运单号</th>
                                <th>车辆信息</th>
                                <th>推送状态</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="innhml">
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
<!--查看详情begin-->
<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">运单详情</h4>
            </div>
            <div class="modal-body" style=" " id="dateilshml">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--查看详情end-->
<!--提交begin-->
<div class="modal fade" id="submit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作确认</h4>
            </div>
           	<input type="hidden" id="billId">
            <div class="modal-body">
				<div class="submit_detail">
					是否将运单<i id="billNo"></i>提交交通部？
				</div>
				<div class="submit_detail">
				<!-- 	<label>单价：</label><span id="dj"></span>
					<label>运输距离：</label><span id="ysjl"></span> -->
					<label>重量：</label><span id="qszl"></span>
			<!-- 	</div>
				<div class="submit_detail"> -->
					<label>总价：</label><span id="zj"></span>
				</div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="uptDelete" data-dismiss="modal">已查看</button>
                <button type="button" class="btn btn-default" id="putJtb" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--提交end-->
<%@include file="../../common/footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        // 表格列宽度手动调整
        $("table").resizableColumns({});
    });
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="/resources/js/waybill/file_ALJTB_bill.js?1215" ></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
</body>
</html>
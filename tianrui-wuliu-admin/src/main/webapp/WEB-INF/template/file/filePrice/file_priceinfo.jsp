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
                            <label>货物名称：</label>
                            <input type="text" maxlength="16" id="Scargo" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>策略名称：</label>
                            <input type="text" maxlength="16" id="Sdesc1" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>路线名称：</label>
                            <input type="text" maxlength="16" id="SRoute" placeholder=" ">
                        </div>
                        <div class="ht_div">
                            <label>价格类型：</label>
                            <select class="form-control" id="Sdesc2">
                                <option value="">请选择</option>
	                            <option value="1">合同</option>
	                            <option value="2">指导</option>
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
                        <div class="content-tou">
                            <button data-toggle="modal" data-target="#addModal"><i
                                    class="glyphicon glyphicon-plus"></i><span>新增</span></button>
                            <button id="batchDisable"><i class="iconfont icon-tingyong"></i><span>批量停用</span></button>
                        </div>
                        <!--用户表格begin-->
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>策略名称</th>
                                <th>单价</th>
                                <th>计价单位</th>
                                <th>税率</th>
                                <th>状态</th>
                                <th>货物名称</th>
                                <th>路线名称</th>
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

<!--停用begin-->
<div class="modal fade" id="tingyong" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
				<input type="hidden" value="" id="tyId">
				<input type="hidden" value="" id="tystatue">
                <h4>只有启用后的策略才能被下游单据使用！请确定操作？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="tingyongPrice();" data-dismiss="modal" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--停用end-->
<%@include file="../../common/footer.jsp" %>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // 表格列宽度手动调整
        $("table").resizableColumns({});

        // 点击修改头像按钮，图片裁剪框显示出来
        $(".tx_contr").on('click', function () {
            $(".acc_touxiang").show();
        });
        // 修改头像的收起按钮
        $(".tx_shouqi").on('click', function () {
            $(".acc_touxiang").hide();
        });
        // 图片裁切块的大小自定义，margin-top是height一半，margin-left是width一半
        var thumb = $(".imgBox_file .thumbBox");
        thumb.height(150);
        thumb.width(150);
        thumb.css({ "margin-top": -75, "margin-left": -75 });
        // 给cropbox.js传参
        var options =
        {
            thumbBox: '.thumbBox',
            spinner: '.spinner',
            imgSrc: ''
        };
        var cropper = $('.imgBox_file').cropbox(options);
        // 文件上传按钮操作
        $('#upload-file').on('change', function () {
            var reader = new FileReader();
            reader.onload = function (e) {
                options.imgSrc = e.target.result;
                cropper = $('.imgBox_file').cropbox(options);
            };
            reader.readAsDataURL(this.files[0]);
            this.files = [];
        });
        // 裁切按钮操作
        $('#btnCrop').on('click', function () {
            var img = cropper.getDataURL();
            $('.user_oldtx').html('');
            $('.user_oldtx').append('<img src="' + img + '" align="absmiddle" style="box-shadow:0px 0px 12px #7E7E7E;">');

            $(".tx_cancel").on('click', function () {
                $(".acc_touxiang").hide();
            });
        });
        // 图片放大按钮操作
        $('#btnZoomIn').on('click', function () {
            cropper.zoomIn();
        });
        // 图片缩小按钮操作
        $('#btnZoomOut').on('click', function () {
            cropper.zoomOut();
        })
    });
    var CONTEXTPATH="${contextPath}";
    var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我的承运</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/imgcut.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css"  rel="stylesheet">
	<link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
<!--Header-->
<!--内容部分begin-->
<div class="bghui">
<div class="container">
    <!--网站位置-->
    <div class="row">
            <div class="rz_line">
                <label>当前位置：运单</label><span>></span> <label>我运输的运单</label>
            </div>
    </div>
    <div class="row">
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心左侧end-->
   		<!--个人中心右侧begin-->
            <div class="rz_right">
                <div class="car_title bgblue">
                    <h2>运单</h2>
                </div>
                <!--个人中心右侧搜索框begin-->
                <div class="plan_search">
                    <input type="text" placeholder="请出入搜索内容" class="searchKey">
                    <button type="submit" class="btn btnblue searchBtn">搜索</button>
                </div>
                <!--个人中心右侧搜索框end-->
                <div class="plan_fege"></div>
                <!--计划模板表格begin-->
                <div class="bill_table" id="dateContent" >
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th >运单号</th>
                            <th >货物名称</th>
                            <th > 始发地-目的地</th>
                            <th >运单状态</th>
                            <th >更新时间</th>
                            <th> 操作</th>
                        </tr>
                        </thead>
                        <tbody>
                      
                        </tbody>
                    </table>
                    <div class="goods_more pageNone" style="display:hide">
	                     <h4>暂无数据</h4>
	                 </div>
                    <div class="goods_more pageMore" style="display:hide">
	                     <h4>查看更多</h4>
	                 </div>
                </div>
                <!--tab切换的内容end-->
            </div>
            <!--计划模板表格end-->
        </div>
        <!--个人中心右侧end-->
        </div>
</div>
</div>
<!--内容部分end-->

<!-- 拒绝modal -->

<div class="modal fade" id="refuseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">运单拒绝</h4>
            </div>
            <div class="modal-body">
            	<input type="hidden" id="hidbid" />
                <div class="bill_rcont">
                    <div class="bii_refuse">
                        <label> 请选择拒绝运单理由：</label>
                        <div class="bill_Rradio ">
                            <p><input type="radio" value="货太多了接不了" name="refuseCheckbox" class="refuseCheckbox"><span>货太多了接不了</span></p>
                            <p><input type="radio" value="车辆暂时无法使用" name="refuseCheckbox" class="refuseCheckbox"><span>车辆暂时无法使用</span></p>
                            <p><input type="radio" value="已有货，不用了" name="refuseCheckbox" class="refuseCheckbox" ><span>已有货，不用了</span></p>
                            <p><input type="radio" value="其他" name="refuseCheckbox" class="refuseCheckbox" ><span>其他</span></p>
                        </div>
                    </div>
                    <div class="bii_refuse">
                        <label> 备注：</label>
                        <div class="bill_Rradio ">
                            <textarea class="form-control refuseContent" rows="5"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary refusesubmitbtn">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 拒绝moal结束 -->
<!--磅单begin-->
<div class="modal fade" id="upbangdan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">磅单图片上传</h4>
            </div>
            <div class="modal-body">
                <!--磅单图片上传弹出-->
                <input id="urlReq" type="hidden"/>
                <div class="bd_alert">
                    <div class="bangdan_note">
                        <label>磅单示例图片：</label>
                    </div>
                    <!--磅单图片begin-->
                    <div class="img_upload">
						<input id="file_bd" class="file" type="file">
						<span class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
					</div>
                    <!--磅单图片end-->
                    <div style="margin-top: 10px;">
                    	<label id="stateWeightLabel">提货量：</label><input id="stateWeight" type="text" maxlength="6" style="margin-left: 5px;"/>
                    </div>
                </div>
                <!--磅单图片上传弹出-->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary departsubmitbtn">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--磅单end-->

<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="/resources/js/bill/driver_page.js?09.16" ></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>
<script type="text/javascript">
	var trRoot = "${trRoot}";	
</script>
</body>
</html>
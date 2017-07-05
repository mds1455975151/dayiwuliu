
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>支付发票账单/司机</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/imgcut.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css"  rel="stylesheet">
    
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${trRoot }/tianrui/My97DatePicker/WdatePicker.js"></script>
    
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
                <label>当前位置：支付</label><span>></span> <label>支付发票账单</label>
            </div>
    </div>
    <div class="row">
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心左侧end-->
   <!--个人中心右侧begin-->
            <div class="rz_right">
            <div class="car_title bgblue">
                <h2>支付发票账单</h2>
            </div>
            <!--个人中心右侧搜索框begin-->
             <div class="yf_search">
                <div class="yf_fl">
                    <div class="yf_sline">
                        <label>账单编号：</label>
                         <input type="text" id="code_req" placeholder="请输入账单编号">
                    </div>
                     <div class="yf_sline">
                        <label>运单编号：</label>
                         <input type="text" id="likeBillCode_req" placeholder="请输入运单编号">
                    </div>
                </div>    
                <div class="yf_fl">    
                    <div class="yf_sline">
                        <label>发票类型：</label>
                         <select id="invoiceName_req" class="form-control">
                            <option value="">请选择</option>
                            <option value="熟料">熟料运输费用</option>
                            <option value="原煤">原煤运输费用</option>
                            <option value="水泥">水泥运输费用</option>
                        </select>
                    </div>
                    <div class="yf_sline">
                        <label>状态：</label>
                        <select id="pay_req" class="form-control">
                            <option value="">请选择</option>
                            <option value="1">未审核</option>
                            <option value="2">未推单 </option>
                            <option value="3">推单中</option>
                            <option value="4">已推送</option>
                            <option value="5">支付中</option>
                            <option value="6">已支付 </option>
                        </select>
                    </div>
                </div>
               <button type="submit" onclick="index(1,0)" class="btn btnblue fpbtn">搜索</button>
            </div>
            <!--个人中心右侧搜索框end-->
            <!--计划模板表格begin-->
             <div class="fapiao_table">
                 <table class="table table-hover" >
                     <thead>
                     <tr>
                         <th >账单编号</th>
                         <th >账单类型</th>
                         <th >申请时间</th>
                         <th>账单总价</th>
                         <th>已付款</th>
                         <th >待付款</th>
                         <th> 账单状态</th>
                         <th>详情</th>
                     </tr>
                     </thead>
                     <tbody id = "paylist">
                     
                     </tbody>
                 </table>
               	<div class="goods_more pageMore" id="moredate">
               		<h4 onclick="moreSearch();">查看更多</h4>
       	 		</div>
             </div>
            <!--计划模板表格end-->
        </div>
        <!--个人中心右侧end-->
        </div>
</div>
</div>
<!--内容部分end-->
<!--每一行弹出发票的详细信息begin-->
<div class="modal fade" id="fp_dtail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">账单详情</h4>
            </div>
            <div class="modal-body">
                <div class="fapiao_body">
                    <table class="table table-hover" >
                        <thead>
                        <tr>
                            <th >运单号</th>
                            <th >货物名称</th>
                            <th >发票类型</th>
                            <th>到货量</th>
                            <th >创建时间</th>
                            <th >运价</th>
                        </tr>
                        </thead>
                        <tbody id="paydetails">
                        
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>
<!--每一行弹出发票的详细信息end-->

<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="/resources/js/pay/driverpay/pay_page_new.js?07050" ></script>
</body>
</html>
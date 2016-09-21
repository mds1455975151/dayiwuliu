
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>运费结算单</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/imgcut.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css"  rel="stylesheet">
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
                <label>当前位置：支付</label><span>></span> <label>运费结算单</label>
            </div>
    </div>
    <div class="row">
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心左侧end-->
   <!--个人中心右侧begin-->
           <div class="rz_right">
            <div class="car_title bgblue">
                <h2>运费结算单</h2>
            </div>
            <!--个人中心右侧搜索框begin-->
            <div class="yf_search">
                <div class="yf_fl">
                    <div class="yf_sline">
                        <label>运单号：</label>
                        <input type="text" placeholder="请输入运单号">
                    </div>
                    <div class="yf_sline">
                        <label>截止时间：</label>
                        <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_fapiao" style="width:200px"/>
                    </div>
                </div>
                <div class="yf_fl">
                    <div class="yf_sline">
                        <label>货物名称：</label>
                        <select class="form-control">
                            <option>水泥</option>
                            <option>大沙</option>
                            <option>3</option>
                        </select>
                    </div>
                    <div class="yf_sline">
                        <label>状态：</label>
                        <select class="form-control">
                            <option>未开票</option>
                            <option>已开票</option>
                        </select>
                    </div>
                </div>
                <button type="submit" onclick="index(1,0)" class="btn btnblue">搜索</button>
            </div>
            <!--个人中心右侧搜索框end-->
            <!--计划模板表格begin-->
             <div class="yunfei">
                 <button data-toggle="modal" data-target="#yf_choose" onclick="checkdetail()" class="colorblue">
                     <i class="icononline">&#xe60f;</i>申请支付发票账单
                 </button>
                 <table class="table table-hover" >
                     <thead>
                     <tr>
                         <th > <input id="allcheck" type="checkbox">全选</th>
                         <th >运单号</th>
                         <th >货物名称</th>
                         <th >截止时间</th>
                         <th>到货量</th>
                         <th>含税价</th>
                         <th >税率</th>
                         <th >结算总价</th>
                         <th> 状态</th>
                     </tr>
                     </thead>
                     <tbody id="yunfeilist">
                     <tr>
                         <td ><input type="checkbox"></td>
                         <td >4545121231 </td>
                         <td >这是散装的水泥啊 </td>
                         <td >2016-06-09
                         </td>
                         <td >45吨</td>
                         <td >20.6元</td>
                         <td >20%</td>
                         <td >20.6元</td>
                         <td >已开票</td>
                     </tr>
                     <tr>
                         <td > 未开票没有选择框</td>
                         <td >4545121231 </td>
                         <td >这是散装的水泥啊 </td>
                         <td >2014-01-09
                         </td>
                         <td >45吨</td>
                         <td >20.6元</td>
                         <td >20%</td>
                         <td >20.6元</td>
                         <td >未开票</td>
                     </tr>
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
<!--操作提示begin-->
<div class="modal fade" id="yf_choose" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">支付发票账单详情</h4>
            </div>
            <div class="modal-body">
                <div class="fapiao_body">
                <!-- 运费总和 -->
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--操作提示end-->

<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="/resources/js/pay/payInvoiceItem/yunfei_page.js" ></script>

</body>
</html>
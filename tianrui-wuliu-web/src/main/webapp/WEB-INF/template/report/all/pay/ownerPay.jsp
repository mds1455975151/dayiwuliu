<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>货主账单报表</title>
<meta name="keywords" content="天瑞" />
<meta name="description" content="">
<meta name="author" content="">

<link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
<link href="${trRoot}/tianrui/css/tr-media.css" rel="stylesheet">
<script language="javascript" type="text/javascript" src="${trRoot }/tianrui/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
	.goods_more {
	    cursor: pointer;
	}
	.goods_more:hover {
	    background: #e4e4e4;
	}
	.goods_more:active {
	    background: #d0d0d0;
	}
	.table {
	    margin: 0px!important;
	}
	.nodata {
		margin-bottom: 0px!important;
	}
</style>
</head>
<body>
	<jsp:include page="../../../common/member/header_busi.jsp"></jsp:include>
	<div class="bghui">
		<div class="container">
			<div class="row">
				<div class="rz_line">
					<label>当前位置：运单</label><span>></span> <label>货主账单报表</label>
				</div>
			</div>
			<div class="row">
				<jsp:include page="../../../common/member/left_busi.jsp"></jsp:include>
				<div class="car_right">
					<div class="car_title bgblue">
						<h2>货主账单报表</h2>
						<!-- 1-司机，2-车主，3-货主 -->
						<input type="hidden" id="reportType" value="3">
						<span class="exportReport">导出报表</span>
					</div>
					<!--个人中心右侧搜索框begin-->
            <!--个人中心右侧搜索框begin-->
            <div class="bb_total">
            <div class="bb_search ">
                <div class="bb_line">
                    <div class="bb_czline">
                        <label>账单日期开始：</label>
                        <input type="text" id="paystarttime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                    <div class="bb_czline">
                        <label>账单日期结束：</label>
                        <input type="text" id="payendtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                    <div class="bb_czline">
                        <label>账单编号：</label>
                        <input type="text" id="payCode" placeholder="请输入账单编号">
                    </div>
                </div>
                <div class="bb_line hidemore">
                    <div class="bb_czline">
                        <label>路线：</label>
                        <input type="text" id="routeName" placeholder="请输入路线">
                    </div>
                    <div class="bb_czline">
                        <label>发货方：</label>
                        <input type="text" id="sendMan" placeholder="请输入发货方">
                    </div>
                    <div class="bb_czline">
                        <label>发货人：</label>
                         <input type="text" id="sendPersian" placeholder="请输入发货人">
                    </div>
                 </div>
                 <div class="bb_line hidemore">      
                    <div class="bb_czline">
                        <label>收货方：</label>
                        <input type="text" id="receiptMan" placeholder="请输入收货方">
                    </div>
                    <div class="bb_czline">
                        <label>签收人：</label>
                        <input type="text" id="receiptPerson" placeholder="请输入签收人">
                    </div>
                    <div class="bb_czline">
                        <label>货物名称：</label>
                        <input type="text" id="cargoName" placeholder="请输入货物名称">
                    </div>
                 </div> 
                 <div class="bb_line hidemore"> 
                 	<div class="bb_czline">
                        <label>支付状态：</label>
                        <select id="payStatus">
                        	<option value="">请选择</option>
                        	<option value="0">未支付</option>
                        	<option value="1">支付中</option>
                        	<option value="2">已支付</option>
                        	<option value="3">支付失败</option>
                        </select>
                    </div>
                 	<div class="bb_czline">
                        <label>计划单号：</label>
                        <input type="text" id="planCode" placeholder="请输入计划单号">
                    </div>
                    <div class="bb_czline">
                        <label>车主：</label>
                        <input type="text" id="venderName" placeholder="请输入车主">
                    </div>
                 </div>  
                 <div class="bb_line hidemore">      
                    <div class="bb_czline">
                        <label>司机姓名： </label>
                        <input type="text" id="driverName" placeholder="请输入司机姓名">
                    </div>
                </div>
                <div class="bb_line">
                    <div class="bb_czline">
                        <label>运单日期开始：</label>
                        <input type="text" id="billstarttime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                    <div class="bb_czline">
                        <label>运单日期结束：</label>
                        <input type="text" id="billendtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                    <div class="bb_czline">
                        <button type="submit" onclick="regist()" class="btn btnyello">重置</button>
                        <button type="submit" onclick="init(0,0)" class="btn btnblue">搜索</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="position_more">
                            <div class="c_Screen">
                                <label>更多筛选条件</label>
                                <span><img src="${trRoot}/tianrui/images/more2.png" id="gengduo"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            <!--个人中心右侧搜索框end-->
            <!--搜索修改结束-->
					<div class="baob_table" style="overflow-x:scroll;">
						<div class="nodata hide">
							<img src="${trRoot}/tianrui/images/none_bill.png">
							<h3>未发现货物运单！</h3>
						</div>
						<table id="report" style="white-space: nowrap" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>账单日期</th>
                                    <th>账单编号</th>
                                    <th>支付对象</th>
                                    <th>计划单号</th>
                                    <th>路线</th>
                                    <th>发货方</th>
                                    <th>发货人</th>
                                    <th>车主</th>
                                    <th>收货方</th>
                                    <th>签收人</th>
                                    <th>车牌号</th>
                                    <th>运单日期</th>
                                    <th>运单号</th>
                                    <th>货物名称 </th>
                                    <th>签收量</th>
                                    <th>含税单价</th>
                                    <th>总价</th>
                                    <th>油卡</th>
                                    <th>扣重扣杂</th>
                                    <th>扣款</th>
                                    <th>其他款项</th>
                                    <th>应付金额</th>
                                    <th>付款金额</th>
                                    <th>支付状态</th>
                                    <th>付款方式</th>
                                    <th>收款人</th>
                                    <th>银行名称</th>
                                    <th>收款账户</th>
								</tr>
							</thead>
							<tbody id="innerHml">
								
							</tbody>
						</table>
					</div>
					<div class="goods_more pageMore" pageno="1">
	                     <h4>查看更多</h4>
	                 </div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../../common/member/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resources/js/common/member/header_busi.js"></script>
	<script type="text/javascript" src="/resources/js/report/all/pay.js?0923"></script>
	<script type="text/javascript">
    $(function () {
        $(".c_Screen").on('click',function(){
            var div_hs = $(".hidemore");
            div_hs.toggle();
            if(div_hs.css("display") == 'none') {
                $("#gengduo").attr('src',"${trRoot}/tianrui/images/more2.png");
            }
            else {
                $("#gengduo").attr('src',"${trRoot}/tianrui/images/more1.png");
            }
        });
        function show_hidden(obj) {
            if(obj.css("display") == 'block') {
                obj.css("display",'none');

            } else if(obj.css("display") == 'none'){
                obj.css("display",'block');
            }
        }
    });
</script>
</body>
</html>
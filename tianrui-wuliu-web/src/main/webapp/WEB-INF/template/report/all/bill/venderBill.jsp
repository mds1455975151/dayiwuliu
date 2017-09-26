<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车主运单报表</title>
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
					<label>当前位置：运单</label><span>></span> <label>车主运单报表</label>
				</div>
			</div>
			<div class="row">
				<jsp:include page="../../../common/member/left_busi.jsp"></jsp:include>
				<div class="car_right">
					<div class="car_title bgblue">
						<h2>车主运单报表</h2>
						<!-- 1-司机，2-车主，3-货主 -->
						<input type="hidden" id="reportType" value="2">
						<span class="exportReport">导出报表</span>
					</div>
					<!--个人中心右侧搜索框begin-->
            <!--个人中心右侧搜索框begin-->
           	<div class="bb_total">
            <div class="bb_search ">
                <div class="bb_line">
                    <div class="bb_czline">
                        <label>业务日期开始：</label>
                        <input type="text" id="businessTimeStart" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                    <div class="bb_czline">
                        <label>业务日期结束：</label>
                        <input type="text" id="businessTimeEnd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                    <div class="bb_czline">
                        <label>运单类型：</label>
                        <select id="billType">
                        	<option value="">请选择</option>
                        	<option value="al">安联运单</option>
                        	<option value="dy">大易运单</option>
                        </select>
                    </div>
                </div>
                <div class="bb_line hidemore">
                    <div class="bb_czline">
                        <label>计划单号：</label>
                        <input type="text" id="planNo" placeholder="请输入计划单号">
                    </div>
                    <div class="bb_czline">
                        <label>运单号：</label>
                        <input type="text" id="billNo" placeholder="请输入运单号">
                    </div>
                    <div class="bb_czline">
                        <label>货物名称：</label>
                         <input type="text" id="cargoName" placeholder="货物名称">
                    </div>
                 </div>
                 <div class="bb_line hidemore">      
                    <div class="bb_czline">
                        <label>车牌号：</label>
                        <input type="text" id="vehicleNo" placeholder="请输入车牌号">
                    </div>
                    <div class="bb_czline">
                        <label>路线：</label>
                        <input type="text" id="routeName" placeholder="请输入路线">
                    </div>
                    <div class="bb_czline">
                        <label>运单状态：</label>
                        <select id="billStatus">
                        	<option value="">请选择</option>
                            <option value="-1">车主回收</option>
                            <option value="0">司机未确认</option>
                            <option value="1">司机已接受</option>
                            <option value="2">司机已装货</option>
                            <option value="3">司机运输中</option>
                            <option value="4">司机已到达</option>
                            <option value="5">司机已卸货</option>
                            <option value="6">已签收</option>
                            <option value="7">司机拒绝接单</option>
                        </select>
                    </div>
                 </div> 
                 <div class="bb_line hidemore"> 
                 	<div class="bb_czline">
                        <label>司机姓名：</label>
                        <input type="text" id="driverName" placeholder="请输入司机姓名">
                    </div>
                 	<div class="bb_czline">
                        <label>支付对象：</label>
                        <select id="payMent">
	                       	<option value="">请选择</option>
	                        <option value="1">司机</option>
	                        <option value="2">车主</option>
                        </select>
                    </div>
                    <div class="bb_czline">
                        <label>发货方：</label>
                        <input type="text" id="sendMan" placeholder="请输入发货方">
                    </div>
                 </div>  
                 <div class="bb_line hidemore">      
                    <div class="bb_czline">
                        <label>发货人： </label>
                        <input type="text" id="sendPersion" placeholder="请输入发货人">
                    </div>
                    <div class="bb_czline">
                        <label>收货方： </label>
                        <input type="text" id="receiptMan" placeholder="请输入收货方">
                    </div>
                    <div class="bb_czline">
                        <label>签收人： </label>
                        <input type="text" id="receiptPersion" placeholder="请输入签收人">
                    </div>
                </div>
                <div class="bb_line hidemore">
                    <div class="bb_czline">
                        <label>运单创建开始：</label>
                        <input type="text" id="billCreaterTimeStart" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                    <div class="bb_czline">
                        <label>运单创建结束：</label>
                        <input type="text" id="billCreaterTimeEnd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                     <div class="bb_czline">
                        <label>接受运单开始：</label>
                        <input type="text" id="acceptTimeStart" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                </div>
                <div class="bb_line hidemore">
                	<div class="bb_czline">
                        <label>接受运单结束：</label>
                        <input type="text" id="acceptTimeEnd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                    <div class="bb_czline">
                        <label>提货确认开始：</label>
                        <input type="text" id="pickupTimeStart" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                    <div class="bb_czline">
                        <label>提货确认结束：</label>
                        <input type="text" id="pickupTimeEnd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                </div>
                <div class="bb_line hidemore">
                    <div class="bb_czline">
                        <label>卸货确认开始：</label>
                        <input type="text" id="unloadTimeStart" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                    <div class="bb_czline">
                        <label>卸货确认结束：</label>
                        <input type="text" id="unloadTimeEndtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                    <div class="bb_czline">
                        <label>车主姓名：</label>
                        <input type="text" id="ownerName" placeholder="请输入车主姓名">
                    </div>
                </div>
                <div class="bb_line">
                    <div class="bb_czline">
                        <label>签收运单开始：</label>
                        <input type="text" id="signTimeStart" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                               style="width:140px"/>
                    </div>
                    <div class="bb_czline">
                        <label>签收运单结束：</label>
                        <input type="text" id="signTimeEnd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
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
									<th>运单类型</th>
                                    <th>业务日期</th>
                                    <th>计划单号</th>
                                    <th>运单号</th>
                                    <th>发货方</th>
                                    <th>发货人</th>
                                    <th>收货方</th>
                                    <th>签收人</th>
                                    <th>车牌号</th>
                                    <th>货物名称</th>
                                    <th>路线</th>
                                    <th>运距</th>
                                    <th>车主派单量</th>
                                    <th>提货榜单净重 </th>
                                    <th>卸货榜单净重</th>
                                    <th>签收量</th>
                                    <th>运单状态</th>
                                    <th>司机姓名</th>
                                    <th>车主姓名</th>
                                    <th>支付对象</th>
                                    <th>运单创建时间</th>
                                    <th>接受运单时间</th>
                                    <th>提货确认时间</th>
                                    <th>卸货确认时间</th>
                                    <th>签收运单时间</th>
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
	<script type="text/javascript" src="/resources/js/report/all/bill.js?0923"></script>
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
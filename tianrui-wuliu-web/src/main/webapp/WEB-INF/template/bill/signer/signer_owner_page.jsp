
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>签收运单</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/imgcut.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <!--这个日历控件js必须放头部-->
<script language="javascript" type="text/javascript" src="${scriptsRoot}/My97DatePicker/WdatePicker.js"></script>
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
                <label>当前位置：运单</label><span>></span> <label>签收运单</label>
            </div>
    </div>
    <div class="row">
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心左侧end-->
   <!--个人中心右侧begin-->
            <div class="rz_right">
                <div class="car_title bgblue">
                    <h2>签收运单</h2>
                </div>
                <!--个人中心右侧搜索框begin-->
                <div class="bb_search">
						<div class="bb_line">
	                        <div class="bb_czline">
	                            <label>运单类别：</label>
	                            <select id="billtype" class="form-control">
									<option value="">全部</option>
									<option value="dy">大易</option>
									<option selected="selected" value="al">安联</option>
								</select>
	                        </div>
	                        <div class="bb_czline">
	                            <label>货物名称：</label>
	                            <input id="cargoname" type="text" placeholder="请输入货物名称">
	                        </div>
	                        <div class="bb_czline">
	                            <label>车牌号：</label>
	                            <input id="vehicleno" type="text" placeholder="请输入车牌号">
	                        </div>
	                        <div class="bb_czline">
	                            <label>运单号：</label>
	                            <input id="billno" type="text" placeholder="请输入运单号">
	                        </div>
	                    </div>
	                    <div class="bb_line">
		                    <div class="bb_czline">
	                            <label>车主姓名：</label>
	                            <input id="venderName" type="text" placeholder="请输入车主姓名">
	                        </div>
	                        <div class="bb_czline">
	                            <label>车主账号：</label>
	                            <input id="venderPhone" type="text" placeholder="请输入车主账号">
	                        </div>
	                        <div class="bb_czline">
	                            <label>运单状态：</label>
	                            <select id=paystatus class="form-control">
									<option value="">全部</option>
									<option value="5">待接单</option>
									<option value="6">待提货</option>
									<option value="7">运输中</option>
									<option value="2">未签收</option>
									<option value="3">运价确认</option>
									<option value="4">运价修改</option>
									<option value="1">已付款</option>
								</select>
	                        </div>
	                        
	                    </div>
	                    <div class="bb_line">
	                        <div class="ht_div">
								<label>到货日期：</label> <input id="starttime" type="text"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i> <input
									id="endtime" type="text"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\');}',dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
	                            <button type="submit" class="btn btnblue resetBtn">重置</button>
								<button type="submit" class="btn btnyello searchBtn">搜索</button>
							</div>
	                        <div class="bb_czline">
	                        </div>
	                    </div>
					</div>
               <!--  <div class="plan_search">
                    <input type="text" id="searchKey" placeholder="请出入搜索内容">
                    <button type="button" class="btn btnblue searchBtn">搜索</button>
                </div> -->
                <!--个人中心右侧搜索框end-->
                <div class="plan_fege"></div>
                <!--计划模板表格begin-->
                <div class="bill_table" id="dateContent" style="overflow-x:scroll;">
                    <table class="table table-hover" style="white-space:nowrap;">
                        <thead>
                        <tr>
                            <th>运单号</th>
                            <th>运单类型 </th>
                            <th>货物名称</th>
                            <th>车牌号</th>
                            <th>始发地-目的地</th>
                            <th>运单状态</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="innhtml">
                        
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

<!-- 普通签收modal -->
<div class="modal fade" id="signModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">运单签收</h4>
            </div>
            <div class="modal-body">
                <div class="bdimg">
	                <div class="modal-body">
		                <ul class="nav nav-tabs">
		                    <li class="active"><a href="#single" id="pickupweight_li" data-toggle="tab">提货磅单</a></li>
		                    <li><a href="#multiple" id="signweight_li" data-toggle="tab">卸货磅单</a></li>
		                </ul>
		                <div class="tab-content">
		                    <div class="tab-pane fade in active" id="single">
		                        <div class="bdimg">
				                    <a target="_blank" id="hrefqhbdImgUrl"><img src="${trRoot}/tianrui/images/bd.png" id="qhbdImgUrl"></a>
				                    <label></label>
				                    <div id="notImg" class="bd_note" hidden>
	                        			<label>司机未上传磅单</label>
					                </div>
		                        </div>
		                    </div>
		                    <div class="tab-pane fade" id="multiple">
		                        <div class="bdimg">
				                    <a target="_blank" id="hrefbdimgurl"><img src="${trRoot}/tianrui/images/bd.png" id="bdimgurl"></a>
		                        </div>
		                    </div>
		                </div>
		            </div>
                    <p><label>签收量：</label>
                    <input type="text" id="weighttext">
                    <input type="hidden" id="bill_id">
                    <input type="hidden" id="pickupweight">
                    <input type="hidden" id="signweight">
                    <input type="hidden" id="bill_desc1">
                    <label id="stateWeightLabel" style="padding-left: 50px;"></label></p>
                </div>
            </div>
            <div class="modal-footer">
             	<input  type="hidden" id="pageNo">
                <button type="button" id="signerWeight" class="btn btn-primary signsubmitbtn">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 签收moal结束 -->

<!-- 安联签收modal -->
<div class="modal fade" id="anlian_signModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">安联运单签收</h4>
            </div>
            <div class="modal-body">
                <div class="bdimg">
	                <div class="modal-body">
		                <ul class="nav nav-tabs">
		                    <li class="active"><a href="#single_al" id="pickupweight_li" data-toggle="tab">提货磅单</a></li>
		                    <li><a href="#multiple_al" id="signweight_li" data-toggle="tab">卸货磅单</a></li>
		                </ul>
		                <div class="tab-content">
		                    <div class="tab-pane fade in active" id="single_al">
		                        <div class="bdimg">
				                    <div class="img_upload">
										<input id="pickupimgurlReq" onchange="fileupload('pickupimgurlReq')" class="file" type="file"> <span
											class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
										<input type="hidden" id="pickupimgurlReq_str" value="" >
									</div>
		                        </div>
		                    </div>
		                    <div class="tab-pane fade" id="multiple_al">
		                        <div class="bdimg">
									 <div class="img_upload">
										<input id="signimgurlReq" onchange="fileupload('signimgurlReq')" class="file" type="file"> <span
											class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
										<input type="hidden" id="signimgurlReq_str" value="" >
									</div>
		                        </div>
		                    </div>
		                </div>
		            </div>
                    <p>
                    <label>提货量：</label>
                    <input type="text" id="al_pickupweight">
                    <label>卸货量：</label>
                    <input type="text" id="al_signweight">
                    </p>
                    <p><label>签收量：</label>
                    <input type="text" id="al_trueweight">
                    <span id="alBill_dw"></span>
                    <input type="hidden" id="al_signBill_id">
                    </p>
                </div>
            </div>
            <div class="modal-footer">
            	<input  type="hidden" id="pageNos">
                <button type="button" class="btn btn-primary signsubmitbtn_al">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 签收moal结束 -->

<!-- 运价确认modal -->
<div class="modal fade" id="yj_queren" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id=" ">运价确认</h4>
            </div>
            <div class="modal-body">
                <div class="bdimg">
	                <div class="modal-body">
		                <div class="tab-content">
		                    <div class="tab-pane fade in active">
			                    <div>
			                    	<span >
				                    <label>当前运价：</label><span id="totalprice"></span>元
			                    	</span>
			                    </div>
			                    <div style="margin-top: 10px">
			                    	<span>
				                    <label>签收重量：</label><input class="total_price_count" id="trueweight" type="text" readonly >吨
			                    	</span>
									<span style="float:right;">	
				                    <label>单价：</label><input style="width: 137px" class="total_price_count" id="bill_price" type="text">元/吨
			                    	</span>
			                    </div>
			                    <div style="margin-top: 10px">
			                    	<span>
				                    <label>扣重扣杂：</label><input class="total_price_count" id="deduct_weight_misc" type="text">元
			                    	</span>
									<span style="float:right;">	
				                    <label>扣款：</label><input class="total_price_count" id="deduct_money" type="text">元
			                    	</span>
			                    </div>
			                    <div style="margin-top: 10px">
				                    <span>
				                    <label>其它扣款：</label><input class="total_price_count" id="deduct_other" type="text">元
				                    </span>
				                    <span style="float:right;">
				                    <label>油卡：</label><input class="total_price_count" id="deduct_oil_card" type="text">元
			                    	</span>
			                    </div>
			                    <input type="hidden" id="deduct_bill_id">
			                    <input type="hidden" id="deduct_bill_type">
			                    <div style="margin-top: 10px">
				                    <span style="float:right;">
				                    <label>实际支付：</label><span id="true_totalprice"></span>元
			                    	</span>
			                    </div>
		                    </div>
		                </div>
		            </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary confirmPrice">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 运价确认moal结束 -->
<!--上传进度条-->
	<a id="showload" data-toggle="modal" data-target="#detail"></a>
	<div class="modal fade" id="detail" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 400px;">
		<div class="upmodal">
			<div class="modal-content">
				<div class="modal-body">
					<div class="upload">
						<img src="${trRoot}/tianrui/images/upload.gif">
						<div class="upload_font">
							<img src="${trRoot}/tianrui/images/sc.png">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!--上传进度条end-->
<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>
<script type="text/javascript">
var type = "owner";
</script>
<script type="text/javascript" src="/resources/js/bill/signer_page.js?1107" ></script>
<script type="text/javascript">
		$(".file").fileinput({
			language : 'zh',
			showUpload : false,
			dropZoneEnabled : false,
			maxFileCount : 1,
			resizeImage : true,
			showCaption : true,
			showPreview : true,
			allowedFileExtensions : [ 'jpg', 'png', 'jpeg' ]// 支持的图片类型
		}).on('fileuploaderror',function(event, data, previewId, index) {
			var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
		}).on('fileerror', function(event, data) {
		}).on('fileuploaded',function(event, data, previewId, index) {
			var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
		});
</script>
</body>
</html>
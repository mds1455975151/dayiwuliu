<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>我委派的计划</title>
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
	<!--内容部分begin-->
	<!--内容部分begin-->
	<div class="bghui">
	<div class="container">
	    <!--网站位置-->
	    <div class="row">
	            <div class="rz_line">
	            	<label>当前位置：计划管理-我委派的计划</label>
	            </div>
	    </div>
	    <div class="row">
	        <!-- 引用公共left部分 -->
				<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
	        <!--个人中心右侧begin-->
	        <div class="rz_right">
	            <div class="car_title bgblue">
	                <h2>我委派的计划</h2>
	            </div>
	            <!--个人中心右侧搜索框begin-->
	            <div class="plan_search">
	                <input type="text" placeholder="计划编码,货物名称,车主名称,车主手机号" id="search_v">
	                <button type="submit" class="btn btnblue searchBtn">搜索货源</button>
	            </div>
	            <!--个人中心右侧搜索框end-->
	            <div class="plan_fege">  </div>
	            <!--计划模板表格begin-->
                 <div id="emptyCont" style="display: none;"></div>
	             <div class="planfabu " id="dateContent" >
	                 <table class="table table-hover" >
	                     <thead>
	                     <tr>
	                         <th>计划编码	</th>
	                         <th>始发地-目的地</th>
	                         <th>货物名称</th>
	                         <th>二级承运商</th>
	                         <th>更新时间</th>
	                         <th>状态</th>
	                         <th> 操作</th>
	                     </tr>
	                     </thead>
	                     <tbody id="planlist">
	                     </tbody>
	                 </table>
	                    <div class="goods_more pageNone" style="display:hide">
		                     <h4>暂无数据</h4>
		                 </div>
	                    <div class="goods_more pageMore" pageNo="1" style="display:hide">
		                     <h4>查看更多</h4>
		                 </div>
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
	                <h4 class="modal-title" id=" ">计划拒绝</h4>
	            </div>
	            <div class="modal-body">
	            	<input type="hidden" id="hidbid" />
	                <div class="bill_rcont">
	                    <div class="bii_refuse">
	                        <label> 请选择拒绝计划理由：</label>
	                        <div class="bill_Rradio ">
	                            <p><input type="radio" value="运力不足" name="refuseCheckbox" class="refuseCheckbox"><span>运力不足</span></p>
	                            <p><input type="radio" value="时间太紧" name="refuseCheckbox" class="refuseCheckbox"><span>时间太紧</span></p>
	                            <p><input type="radio" value="运费太低" name="refuseCheckbox" class="refuseCheckbox"><span>运费太低</span></p>
	                            <p><input type="radio" value="其他" name="refuseCheckbox" class="refuseCheckbox"><span>其他</span></p>
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
	<!--底部begin-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
	<!--底部end-->
	<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
	<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
	<script type="text/javascript" src="/resources/js/plan/appoint_page.js" ></script>
	<script type="text/javascript">
		$(function(){
			$("#planAppoint").addClass("selected");
			initAppointPlanList(true);
		});
	</script>
</body>
</html>
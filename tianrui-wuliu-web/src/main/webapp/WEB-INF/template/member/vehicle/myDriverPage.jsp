<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>大易物流平台-我的司机</title>
	    <meta name="keywords" content=" 天瑞"/>
	    <meta name="description" content="">
	    <meta name="author" content="">
    
	<!-- 引用公共header部分 -->
	<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
		<!--内容部分begin-->
		<div class="bghui">
			<div class="container">
			    <!--网站位置-->
			    <div class="row">
			            <div class="rz_line">
			                <label>首页</label><span>></span> <label>账号</label><span>></span><label>个人中心</label>
			            </div>
			    </div>
			    <div class="row">
			        <!--个人中心左侧begin-->
			        <jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
			        <!--个人中心左侧end-->
		            <!--个人中心右侧begin-->
		            <div class="rz_right">
		                <div class="car_title bgblue">
		                    <h2>我的司机</h2><a href="${path}/trwuliu/Member/myDriver/addDriverPage"><span>添加司机</span></a>
		                </div>
		               	<div class="car_search">
			                <ul>
			                    <li>
			                        <div class="addr-box">
			                            <p >司机姓名：</p>
			                            <input class="em" type="text" id="drivername"/>
			                        </div>
			                    </li>
			                    <li>
			                        <div class="addr-box">
			                            <p >司机电话：</p>
			                            <input class="em" type="text" id="drivertell"/>
			                        </div>
			
			                    </li>
			                    <li>
			                        <button type="submit" class="btn btnyello" id="searchDriver">搜索司机</button>
			                    </li>
			                </ul>
			                <div class="driver_data">
		                        <label class="colorblue">司机总数：<span id="driverCount"></span>人</label>
		                    </div>
			            </div>
		                <div class="driver_all" id="driver_div">
		                
		                	<!-- TO GET DATA! -->
		                	
		                </div>
		                <div class="goods_more pageMore" style="display:hide">
		                     <h4 onclick="moreDriver();">查看更多</h4>
			            </div>
		            </div>
		            <!--个人中心右侧end-->
		        </div>
		    </div>
		</div>
		<!--内容部分end-->
		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
		
		<!--修改begin-->
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
		                        aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title">司机信息修改</h4>
		                <input type="hidden" id="modal_edit_id">
		            </div>
		            <div class="modal-body">
		                <div class="cz_search">
		                	<div class="chezhu_div">
		                        <label>姓名：</label>
		                        <input type="text" id="modal_edit_driverName" readonly>
		                    </div>
		                    <div class="chezhu_div">
		                        <label>备注名：</label>
		                        <input type="text" id="modal_edit_remarkName">
		                    </div>
		                    <div class="chezhu_div">
		                        <label>手机：</label>
		                        <input type="text" id="modal_edit_driverTel" readonly>
		                    </div>
		                    <div class="clear"></div>
		                </div>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-primary" id="modal_edit_save">保存</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            </div>
		        </div>
		    </div>
		</div>
		<!--修改end-->
		<!--删除begin-->
		<div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
		                        aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title" id=" ">删除</h4>
		                <input type="hidden" id="modal_del_id">
		                <input type="hidden" id="modal_del_divNo">
		                <input type="hidden" id="modal_del_driverTel">
		            </div>
		            <div class="modal-body">
		                <h4>您确定要删除此司机吗？</h4>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-primary" id="modal_del_yes">确定</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            </div>
		        </div>
		    </div>
		</div>
		<!--删除end-->
		
		
		<script type="text/javascript" src="${trRoot}/tianrui/js/cropbox.js"></script>
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
		<script type="text/javascript" src="/resources/js/member/vehicle/myDriverPage.js" ></script>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>天瑞物流平台-二级运营商</title>
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
		                    <h2>二级运营商</h2><a href="${path}/trwuliu/Member/myVehiOwner/addVehiOwnerPage"><span>添加车主</span></a>
		                </div>
		                <!--个人中心右侧搜索框begin-->
		                <div class="plan_search">
		                    <input type="text" placeholder="请出入车主姓名或电话" id="searchText">
		                    <button type="submit" class="btn btnyello" onclick="vehiOwnerSearch(0)">搜索</button>
		                </div>
		                <!--个人中心右侧搜索框end-->
		                <div class="plan_fege"></div>
		                <!--计划模板表格begin-->
		             <div id="vehicle_none" >
		                <div class="myplan" >
		                    <table class="table table-hover" >
		                        <thead>
		                        <tr>
		                            <th>车主信息</th>
		                            <th>联系电话</th>
		                            <th> 状态</th>
		                            <th> 操作</th>
		                        </tr>
		                        </thead>
		                        <tbody id="vehiOwner_tbody">
		                        
									<!-- TO GET DATA! -->	
										
		                        </tbody>
		                    </table>
		                    <div class="goods_more">
		                        <h4 onclick = "addpage()">查看更多</h4>
		                    </div>
		                </div>
		                <div class="nodata"></div>
		              </div>
		                <!--计划模板表格end-->
		            </div>
		            <!--个人中心右侧end-->
		        </div>
			</div>
		</div>
		<!--内容部分end-->

		<!--修改begin-->
		<div class="modal fade" id="enDisableModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document" style="width:400px;">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
		                        aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title" id="modal_enDisable_title"></h4>
		                <!-- 隐藏项：ID、开启FLG -->
		                <input type="hidden" id="modal_enDisable_id">
		                <input type="hidden" id="modal_enDisable_flg">
		            </div>
		            <div class="modal-body">
		                <h4 id="modal_enDisable_content"></h4>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-primary" id="modal_enDisable_OK">确定</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            </div>
		        </div>
		    </div>
		</div>
		<!--修改end-->
		<!--显示车辆begin-->
		<div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document" style="width: 670px; ">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
		                        aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title">车辆信息搜索</h4>
		                <!-- 隐藏项：当前车主主键 -->
		                <input type="hidden" id="modal_detail_id">
		            </div>
		            <div class="modal-body">
		                <div class="cz_search">
		                    <div class="chezhu_div">
		                        <input type="text" placeholder="车牌号 " id="modal_detail_vehiNo">
		                    </div>
		                    <div class="chezhu_div">
		                        <input type="text" placeholder="司机姓名 " id="modal_detail_driverName">
		                    </div>
		                    <div class="chezhu_div">
		                        <input type="text" placeholder=" 联系方式" id="modal_detail_driverTel">
		                    </div>
		                    <button class="btn btnyello" type="submit" id="modal_detail_search">搜索</button>
		                </div>
		                <div class="chezhu_modalb" id="modal_detail_div">
		                
		                    <!-- TO GET DATA! -->
		                    
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		<!--显示车辆end-->
		<!--删除begin-->
		<div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
		                        aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title" id=" ">删除</h4>
		                <!-- 隐藏项：主键、行号 -->
		                <input type="hidden" id="modal_del_id">
		                <input type="hidden" id="modal_del_rowIndex">
		            </div>
		            <div class="modal-body">
		                <h4>您确定要删除此车主吗？</h4>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-primary" id="modal_del_yes">确定</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            </div>
		        </div>
		    </div>
		</div>
		<!--删除end-->
		<!--拒绝begin-->
		<div class="modal fade" id="refuse" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
		                        aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title">拒绝原因</h4>
		            </div>
		            <div class="modal-body">
		                <div class="chezhu_refuse">
		                    <label>拒绝原因</label>
		                    <button class="btn btnyello">重新添加</button>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		<!--拒绝end-->

		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
		<script type="text/javascript" src="/resources/js/member/vehicle/myVehiOwnerPage.js" ></script>
	</body>
</html>

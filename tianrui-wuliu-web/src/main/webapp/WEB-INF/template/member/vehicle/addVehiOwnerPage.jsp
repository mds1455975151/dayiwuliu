<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>大易物流平台-添加车主</title>
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
		                <div class=" bgblue">
		                    <h2>添加车主</h2>
		                </div>
		                <!-- 个人车辆begin -->
		                <div class="car_box">
		                    <div class="reg_tel">
		                        <label>车主账号：</label>
		                        <input type="text" id="owner_account" maxlength="11">
		                        <!-- 隐藏项：车主账号备份、车主主键、防止重复添加Flg -->
		                        <input type="hidden" id="owner_account_back">
		                        <input type="hidden" id="ownerId">
		                        <input type="hidden" id="isResultChangedFlg">
		                        <button type="submit" class="btn btnyello chezhu_btn" id="owner_searchBtn">搜索</button>
		                    </div>
		                    <div class="car_photo">
		                        <div class="chezhu_table" id="div_result">
		                            <table class="table table-bordered">
		                                <thead>
		                                <tr>
		                                    <th data-resizable-column-id="first" id="th_name">公司名称/车主名称</th>
		                                    <th data-resizable-column-id="last" id="th_cardName">营业执照/身份证号</th>
		                                    <th data-resizable-column-id="#">联系方式</th>
		                                </tr>
		                                </thead>
		                                <tbody>
		                                <tr>
		                                    <td id="owner_name"></td>
		                                    <td id="owner_cardNo"></td>
		                                    <td id="owner_tel"></td>
		                                </tr>
		                                </tbody>
		                            </table>
		                        </div>
		                        <div class="car_addbtn">
		                            <button type="submit" class="btn btnyello" id="owner_addBtn">添加</button>
		                            <button type="submit" class="btn btnblue" id="owner_cancelBtn">取消</button>
		                        </div>
		                    </div>
		                </div>
		                <!-- 个人车辆end -->
		            </div>
		            <!--个人中心右侧end-->
		        </div>
		    </div>
		</div>
		<!--内容部分end-->
		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
		<script type="text/javascript" src="/resources/js/member/vehicle/addVehiOwnerPage.js" ></script>
	</body>
</html>

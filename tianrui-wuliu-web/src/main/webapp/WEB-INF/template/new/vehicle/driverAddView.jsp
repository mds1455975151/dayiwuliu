<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>驾驶员添加</title>
	    <meta name="keywords" content=" 天瑞"/>
	    <meta name="description" content="">
	    <meta name="author" content="">
	<!-- 引用公共header部分 -->
	<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
		 <style>
	    	.rz_box2 .reg_tel label {
			    text-align: left;
			    width: 120px;
			    font-size: 14px;
			}
	    </style>
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
			                <h2>添加驾驶员</h2>
			            </div>
			            <div class="rz_box2">
			                <div class="reg_tel">
			                    <label>姓名</label>
			                    <input type="text"  id="name" >
			                </div>
			                <div class="reg_tel">
			                    <label>性别</label>
			                    <div class="geren_sex">
			                        <label class="radio-inline">
			                            <input type="radio" name="personal_sex" value="xy" > 男
			                        </label>
			                        <label class="radio-inline">
			                            <input type="radio" name="personal_sex" value="xx" > 女
			                        </label>
			                    </div>
			                </div>
			                <div class="reg_tel">
			                    <label>出生日期</label>
			                    <input type="text" placeholder="日期格式：2012-02-02" >
			                </div>
			                <div class="reg_tel">
			                    <label>联系电话</label>
			                    <input type="text" placeholder="请输入手机号" >
			                </div>
			                <div class="reg_tel">
			                    <label>证件号</label>
			                    <input type="text" placeholder="请输入证件号码">
			                </div>
			                <div class="reg_tel">
			                    <label>身份证地址</label>
			                    <input type="text" placeholder="请输入身份证地址">
			                </div>
			                <div class="reg_tel">
			                    <label>初次领证日期</label>
			                    <input type="text" placeholder="请输入驾驶证发证机关,格式如:2012-02-02">
			                </div>
			                <div class="reg_tel">
			                    <label>发证机关</label>
			                    <input type="text" placeholder="请输入驾驶证发证机关">
			                </div>
			                <div class="reg_tel">
			                    <label>驾驶证注册日期</label>
			                    <input type="text" placeholder="日期格式：2012-02-02">
			                </div>
			                <div class="reg_tel">
			                    <label>有效年限</label>
			                    <input type="text" placeholder="请输入有效年限,例如:6">
			                </div>
			                <div class="reg_tel">
			                    <label>准驾车型</label>
			                    <input type="text" >
			                </div>
			                <div class="reg_tel">
			                    <label>驾驶证图片</label>
			                    <input type="text" placeholder="">
			                </div>
			               
			            </div>
			            <div class="tx_edit">
			                <button type="submit" class="btn" id="personal_button">保存</button>
			            </div>
			
			        </div>
		            
		            <!--个人中心右侧end-->
		        </div>
		    </div>
		</div>
		<!--内容部分end-->
		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
	</body>
</html>

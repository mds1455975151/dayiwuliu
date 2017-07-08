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
    <title>大易物流平台-企业认证</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${trRoot}/tianrui/css/imgcut.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    
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
               <h2>企业认证</h2>
           </div>
           <div class="mycar_dt">
           	   <div class="mycar_dtsolo">
						<label>会员账号:${member.cellphone }</label> 
			   </div>
               <div class="mycar_dtsolo">
						<label>企业名称:${member.companyname }</label> 
			   </div>
			   <div class="mycar_dtsolo">
						<label>公司地址:${member.companyAddress }</label> 
			   </div>
			   <div class="mycar_dtsolo">
						<label>公司联系人:${member.companycontact }</label> 
			   </div>
			   <div class="mycar_dtsolo">
						<label>联系人电话:${member.companytel }</label> 
			   </div>
			   <div class="mycar_dtsolo">
						<label>营业执照号:${member.companycode }</label> 
			   </div>
			   <div class="mycar_dtsolo">
						<label>道路运输经营许可证号:${member.rtblno }</label> 
			   </div>
			   
			   <div class="clear"></div>
					
				<div class="mycar_div">
					<p>营业执照</p>
	                   <img style="width: 420px" src="${member.licenseImagePath }">
				</div>
				<div class="mycar_div">
					<p>道路运输经营许可证</p>
	                   <c:if test="${member.rtblimgurl eq null}">
	                   	<span style="color: red">未上传</span>
	                   </c:if>
	                   <img style="width: 420px" src="${member.rtblimgurl }">
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
	<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
</body>
</html>

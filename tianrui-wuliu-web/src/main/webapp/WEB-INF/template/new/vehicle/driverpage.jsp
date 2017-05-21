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
	    <title>new-添加运力</title>
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
		                    <h2>驾驶员列表</h2><a href="driverAddView.do"><span>添加驾驶员</span></a>
		                </div>
						 
		                <div class="driver_list">
		                    <table class="table" >
		                        <tbody>
		                        <c:forEach  var="item" items="${driverPage}" >
		                        	<tr class="driver_tbchoose">
			                            <td >
			                          		<input type="hidden"   value="${item.id }" />
			                            </td>
			                            <td >姓名：${item.name } </td>
			                            <td >电话：${item.telphone } </td>
			                            <td>身份证号：${item.idcard }</td>
			                            <td >状态：
			                            	<c:if test="${ item.authSatus=='1'}">
			                            	<label class="colorblue">已审核</label> 
			                            	</c:if>
			                            	<c:if test="${ item.authSatus=='2'}">
			                            	<label class="coloryello">审核中</label> 
			                            	</c:if>
			                            	<c:if test="${ item.authSatus=='3'}">
			                            	<label class="colorgray">审核失败</label> 
			                            	</c:if>
			                            </td>
			                            <td class="f12 bill_lineh2">
											<c:if test="${item.checkStatus =='0' && item.authSatus=='1'  }">
												<button class="btn btnblue chooseBtn"  dataId="${item.id}" dataName="${item.name}">选择</button>
											</c:if>
											<c:if test="${item.checkStatus =='1'  }">
												<button class="btn btnyello" dataId="${item.id}">已选择</button>
											</c:if>
											<c:if test="${ item.authSatus=='3'  }">
												<button class="btn btnblue delBtn" dataId="${item.id}" dataName="${item.name}">删除</button>
											</c:if>
			                            </td>
			                        </tr>
						 		</c:forEach>
		                        
		                        </tbody>
		                    </table>
		
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
		<script>
			$(function(){
				$(".delBtn").click(function(){
					var title=$(this).attr("dataName");
					var id=$(this).attr("dataId");
					confirm("操作提示","确认删除驾驶员["+title+"]吗,是否确认操作?",function(){
						$.ajax({
							url : '/trwuliu/vehicle/new/driverDel',// 跳转到 action
							data : {"id":id},
							type : "post",
							dataType:"json",
							success : function(result) {
								if(result.code=="000000"){
									alert("操作成功","提示",function(){
										window.location.reload(true);
									});
								}else{
									alert(result.error,"提示",function(){
										window.location.reload(true);
									});
								}
							}
						}); 
					});
				});	
				$(".chooseBtn").click(function(){
					var title=$(this).attr("dataName");
					var id=$(this).attr("dataId");
					confirm("操作提示","确认选中该驾驶员["+title+"]为当前驾驶员吗,是否确认操作?",function(){
						$.ajax({
							url : '/trwuliu/vehicle/new/driverCheck',// 跳转到 action
							data : {"id":id},
							type : "post",
							dataType:"json",
							success : function(result) {
								if(result.code=="000000"){
									alert("操作成功","提示",function(){
										window.location.reload(true);
									});
								}else{
									alert(result.error,"提示",function(){
										window.location.reload(true);
									});
								}
							}
						}); 
					});
				});	
			})
		</script>
	</body>
</html>

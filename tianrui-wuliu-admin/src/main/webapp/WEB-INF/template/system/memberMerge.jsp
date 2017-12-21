<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>会员合并</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
</head>
<body>

<div class="container-fluid">
	<input type="hidden" id="recPageNo" value="${pageNo }">
    <!--公共头部begin-->
    <jsp:include page="../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
                <!--后台右侧布局begin-->
            <div class="col-md-10 ">
               <div class="ht_content">
                    <div class="user_shtitle mt20">
                        <h3>用户信息</h3>
                    </div>
                    <div class="row">
	                    <div class="col-md-12">
	                    	 <div class="info_base">
	                             <div class="info_base_solo">
		                             <label>请输入身份证号：</label>
		                             <input id="idCard" style="width: 300px" type="text" placeholder="请输入身份证号">
	                             </div>
	                            <div class="ht_divbtn">
									<button class="btn btnblue search" onclick="search()"
										type="submit">搜索</button>
									<button class="btn btngreen reset" onclick="reset()"
										type="submit">重置</button>
								</div>
								<div class="ht_divbtn">
									<button class="btn btngreen reset" onclick="merge()"
									type="submit">合并</button>
								</div>
	                         </div>
	                    </div>
                    </div>
                   <div class="user_shtitle mt20"></div>
               	
	               	<div class="content-user"
						style="overflow-x: scroll; width: 1544px;">
						<!-- 用户表格begin -->
               			<table id="memberInfo" style="white-space: nowrap"
							class="table table-bordered" data-options="">
							<thead>
								<tr>
									<th>单选</th>
									<th data-options="field:'cellphone',width:100">账号</th>
									<th data-options="field:'identityCard',width:100">身份证号</th>
									<th data-options="field:'userName',width:100">认证名称</th>
									<th data-options="field:'registtimeStr',width:100">注册时间</th>
									<th data-options="field:'operation',width:220">操作</th>	
								</tr>
							</thead>
							<tbody id="innerHtml">
							</tbody>
						</table>
						<!-- 用户表格end -->
				    </div>         
            </div>
                <!--后台右侧布局end-->
        </div>
            <!--后台整体布局end-->
</div>

<%@include file="../common/footer.jsp" %>
<script type="text/javascript">
    var trRoot = "${trRoot}";
</script>
<script type="text/javascript" src="/resources/js/memberMerge.js?1221"></script>
</body>
</html>
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
    <title>统计数据管理</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css" rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css" rel="stylesheet">
    <link href="${trRoot}/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
	<style type="text/css">
	.inputStyle{
		width: 80px
	}
	.inputdata{
		width: 70px
	}
	</style>
</head>
<body>

<div class="container-fluid">
     <input type="hidden" id="recPage" value="${pageNo }">
     <!--公共头部begin-->
    <jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
            <!--后台右侧布局begin-->
           <div class="col-md-10 ">
                <div class="ht_content">
                    <div id="content-header">
                        <h3>统计数据管理</h3>
                    </div>
                    <!--查询框begin-->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="contuser_search">
                                <div class="ht_div">
                                    <label>业务类别：</label>
                                    <select id="routetype">
                                    	<option value="1">各省车辆数量</option>
                                    	<option value="2">车型分布</option>
                                    	<option value="3">货物分布</option>
                                    	<option value="4">车辆总数</option>
                                    	<option value="5">线路总数 </option>
                                    	<option value="7">运单统计 </option>
                                    	<option value="8">运费统计 </option>
                                    </select>
                                </div>
                            </div>
                    </div>
                    </div>
                    <!--查询框end-->
                    <div class="row mt15">
                        <div class="col-md-12">
                            <div class="content-user">
								<!-- 表格原煤 -->
								<table class="table table-bordered" id="table_0">
						            <thead>
						         
						            <tr>
						                <th >序号</th>
						                <th>描述 </th>
						                <th >数据</th>
						            </tr>
						            </thead>
						            <tbody id="html_table_0" class="data_tbody">
						            
						            </tbody>
						        </table>
				        </table>
                            </div>
                        </div>
                    </div>
                  <div class="clear"> </div>
                    
                </div>
            </div>
            <!--后台右侧布局end-->
           </div>
            <!--后台整体布局end-->
         </div>
    <!--侧边栏end-->
    </div>

<%@include file="../../common/footer.jsp" %>
<script type="text/javascript">
	var CONTEXTPATH="${path}";
	var imagesRoot="${imagesRoot }";
</script>
<script type="text/javascript" src="${trRoot}/js/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#routetype").change(function(){
		getData();
	}).trigger("change");
	$(".data_tbody").on("change",".valInput",function(){
		var val=$(this).val();
		var dataId=$(this).attr("dataId");
		updata(dataId,val);
	});
	
	function getData(){
		$.ajax({
			url : '/systemDataManger/find',// 跳转到 action
			data : {
				type:$("#routetype").val()
			},
			type : "post",
			success : function(rs) {
				if( rs && rs.code==000000){
					console.info(rs);
					rederData(rs.data||[]);
				}else{
					alert(rs.error);
				}
			}
		});
	}
	
	function rederData(data){
		$(".data_tbody").empty();
		var dataArr=[];
		$.each(data,function(i,item){
			dataArr.push("<tr>");
			dataArr.push("<td>"+i+"</td>");
			dataArr.push("<td>"+item.remark+"</td>");
			dataArr.push("<td><input type='text' value='"+item.data+"' dataId='"+item.id+"' class='valInput'</td>");
			dataArr.push("</tr>");
		});
		$(".data_tbody").html(dataArr.join(" "));
	}
	function updata(dataid,val){
		$.ajax({
			url : '/systemDataManger/update',// 跳转到 action
			data : {
				id:dataid,
				value:val
			},
			type : "post",
			success : function(rs) {
				if( rs && rs.code==000000){
				}else{
					alert(rs.error);
				}
			}
		});
	}
});
</script>
</body>
</html>
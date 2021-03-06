<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>消息维护</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
<link href="${stylesRoot }/base.css" rel="stylesheet">
<link href="${stylesRoot }/style.css" rel="stylesheet">
<link href="${stylesRoot }/styleadmin.css" rel="stylesheet">
<link href="${stylesRoot }/tr-media.css" rel="stylesheet">
<link href="${stylesRoot }/easyTree.css" rel="stylesheet">
<link href="${stylesRoot }/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<link href="${stylesRoot}/jquery-ui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${stylesRoot }/pagination/pagination.css" />
<link rel="Shortcut Icon" href="${imagesRoot}/favicon.ico"
	type="image/x-icon">
<!--这个日历控件js必须放头部-->
<script language="javascript" type="text/javascript"
	src="${scriptsRoot}/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="container-fluid">
		 <input type="hidden" id="recPageNo" value="${pageNo }">
		<jsp:include page="../common/header.jsp" flush="false"></jsp:include>
		<!--后台右侧布局begin-->
		<div class="col-md-10 ">
			<div class="ht_content">
				<div id="content-header">
					<h3>消息维护</h3>
				</div>
				<!--查询框begin-->
				<div class="row">
                    <div class="col-md-12">
                        <div class="ht_div">
                           <label>通知渠道：</label>
                           <select class="form-control" id="pushqudao">
                               <option value="">请选择</option>
                               <option value="1">手机(短信) </option>
                               <option value="2">APP</option>
                               <option value="3">电话通知</option>
                           </select>
                       </div>
                       <div class="ht_div">
                           <label>推送群体：</label>
                           <select class="form-control" id="pushgroup">
                               <option value="">请选择</option>
                               <option value="GROUP_DRIVER">司机 </option>
                       		   <option value="GROUP_VENDER">车主</option>
                        	   <option value="GROUP_OWNER">货主</option>
                           </select>
                       </div>
                        <div class="ht_div">
							<label>通知时间:</label>
						 <input id="starttime" type="text" 
						 onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\');}',dateFmt:'yyyy-MM-dd'})"
								class="Wdate" style="width: 160px" placeholder="请选择开始日期" readonly/> <i>-</i>
						<input id="endtime" type="text"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\');}',dateFmt:'yyyy-MM-dd'})"
								class="Wdate" style="width: 160px" placeholder="请选择结束日期" readonly/>
						</div>
                        <div class="ht_divbtn">
                            <button class="btn btnblue search" onclick="init(0)" type="submit">搜索</button>
                            <button class="btn btngreen reset" onclick="reset()" type="submit">重置</button>
                        </div>
                    </div>
                </div>
				<!--查询框end-->
				<div class="row mt15">
					<div class="col-md-12">
						<div class="content-user">
						<div class="content-tou">
                            <button data-toggle="modal" data-target="#addModal">
                             <i class="glyphicon glyphicon-plus"></i><span>新增</span>
                             </button>
                        </div>
							<!--用户表格begin-->
							<table id="planReport" style="white-space: nowrap"
								class="table table-bordered">
								<thead>
									<tr>
                                    <th>序号</th>
                                    <th>通知时间</th>
                                    <th>通知渠道</th>
                                    <th>推送群体</th>
                                    <th>成功到达</th>
                                    <th>内容</th>
                                </tr>
								</thead>
								<tbody id="innerHtml">
								</tbody>
							</table>
							<!--用户表格end-->
						</div>
						<!-- 分页部分  开始-->
						<div class="row pr20 fr">
							<%@include file="../common/pagination.jsp"%>
						</div>
						<!-- 分页部分 结束 -->
					</div>
				</div>
			</div>
		</div>
		<!--后台右侧布局end-->
					<div class="loadingbg">
               <div class="loadingposi" role="document">
                  <img src="${imagesRoot}/loading3.gif">
               </div>
            </div>
	</div>
	<!--后台整体布局end-->
	<!--侧边栏end-->
	</div>
	
	<!--新增begin-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >新增司机</h4>
            </div>
            <div class="modal-body" style=" ">
                <div class="yichmodal">
                    <div class="ycmodel_width">
                        <label><i style="color: #ff2f00;">*</i>推送群体：</label>
                        <div class="newmessline">
                            <input type="checkbox" name="newgroup" value="1">
                            <span>司机</span>
                        </div>
                        <div class="newmessline">
                            <input type="checkbox" name="newgroup" value="2">
                            <span>车主</span>
                        </div>
                        <div class="newmessline">
                            <input type="checkbox" name="newgroup" value="3">
                            <span>货主</span>
                        </div>
                    </div>
                    <div class="ycmodel_width">
                        <label><i style="color: #ff2f00;">*</i>推送渠道：</label>
                        <select class="form-control" id="newqudao">
                            <option value="">请选择</option>
                            <option value="1">手机(短信) </option>
                            <option value="2">APP</option>
                            <option value="3">电话通知</option>
                        </select>
                    </div>
                    <div class="yctexarea_width">
                        <label><i style="color: #ff2f00;">*</i>推送内容：</label>
                        <textarea class="form-control" rows="4" id="newtext"></textarea>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="messpush_new">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--新增end-->

	<!--修改密码end-->
	<%@include file="../common/footer.jsp"%>
	<script type="text/javascript" src="${scriptsRoot}/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${scriptsRoot}/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery.pagination.js"></script>
	<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${scriptsRoot}/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript" src="/resources/js/aberrant/abermessage.js?1229"></script>
</body>
</html>
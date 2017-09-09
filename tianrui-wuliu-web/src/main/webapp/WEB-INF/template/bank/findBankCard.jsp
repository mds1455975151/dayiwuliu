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
	    <title>我的银行卡</title>
	    <meta name="keywords" content=" 天瑞"/>
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link href="${trRoot}/tianrui/css/imgcut.css" rel="stylesheet">
		<link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
		<style type="text/css">
			.head_body{
				margin-top: 20px;
				margin-left: 20px;
			}
			.head_background{
				margin-top: 20px; 
				background-image: url('${trRoot}/tianrui/images/bank_img.png');
				width: 336px;
				height: 96px;
			}
			.inline{
				display: inline-block;
			}
			.display_3{
				float: right;
				margin-left: 28px;
				margin-top: 35px;
				color: white;
				font-size: 15px;
			}
			.display_1{
				width: 30px;
				height: 60px;
				margin-left: 14px;
			}
			.display_2{
				margin-left: 6px;
			}
			.font_size_1{
				font-size: 16px;
				color: white;
				margin-top: 20px;
			}
			
			.font_size_2{
				color: white;
				font-size: 15px;
				margin-top: 10px;
			}
			
			.bank_img{
				margin-top: -20px
			}
			
			
		</style>
    
	<!-- 引用公共header部分 -->
	<jsp:include page="../common/member/header_busi.jsp"></jsp:include>
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
			        <jsp:include page="../common/member/left_busi.jsp"></jsp:include>
			        <!--个人中心左侧end-->
		            <!--个人中心右侧begin-->
		            <div class="rz_right">
		             	<div class="car_title bgblue">
							<h2>银行卡详情</h2>
							
							<a href="/trwuliu/bank/card/savePage"><span>添加银行卡</span></a>
							
							<a href="/trwuliu/bank/card/saveOwnerPage"><span>引用别人银行卡</span></a>
						</div>
						<div class="head_body" id="innerHml">
							<!-- 循环开始 
								<div class="head_background">
									<div class="inline">
										<div class="display_3 inline">
												<div style="margin-top: -10px">默认</div>
												<div>认证成功</div>
										</div>
										<div class="display_1 inline">
											<img class="bank_img" src="${trRoot}/tianrui/images/fangzi.png">
										</div>
										<div class="display_2 inline">
											<div class="font_size_1">bankname</div>
											<div class="font_size_2">bankcard</div>
										</div>
									</div>	
								</div>
							 循环结束 -->
						</div>
					</div>
		            <!--个人中心右侧end-->
		        </div>
		    </div>
		</div>
		<!--内容部分end-->
		<!-- 引用公共footer部分 -->
		<jsp:include page="../common/member/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
		<script type="text/javascript"> var trRoot = "${trRoot}";</script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>
	<script type="text/javascript">
	$(function(){
		index();
	});
	
	function index(){
		$.ajax({
			url:"/trwuliu/bank/card/find",
			type:"post",
			data:{desc4: 1},
			success:function(ret){
				if(ret.code=="000000"){
					innerHtml(ret.data.list);
				}
			}
		});
	}
	
	function innerHtml(list){
		$("#innerHml").empty();
		for (var a = 0; a < list.length; a++) {
			var idname =list[a].idname == undefined ? "":list[a].idname;
			var telphone =list[a].telphone == undefined ? "":list[a].telphone;
			var bankstatus = "";
			var bankName = list[a].bankname;
			if(list[a].type=="0"){
				bankstatus = "引用";
				bankName =idname;
			}else if(list[a].bankstatus=="1"){
				bankstatus = "默认";
			}
			if(list[a].bankstatus=="0"){
				bankstatus = "非默认";
			}
			var bankautid = "";
			if(list[a].bankautid == "1"){
				bankautid = "认证成功";
			}
			if(list[a].bankautid == "2"){
				bankautid = "认证中";
			}
			if(list[a].bankautid == "3"){
				bankautid = "认证失败";
			}
			
			var type = "";
			if(list[a].type == undefined){
				type = "";
			}
			if(list[a].type == "0"){
				type = "车主";
			}
			var hml = "";
			hml = "<div class='head_background' onclick=\"moreng('"+list[a].id+"','"+list[a].bankstatus+"','"+list[a].bankautid+"','"+list[a].bankcard+"')\">"+
			"<div class='inline'><div class='display_3 inline'>"+
			"<div style='margin-top: -10px'>"+bankstatus+"</div><div>"+bankautid+"</div></div>"+
			"<div class='display_1 inline'><img class='bank_img' src='${trRoot}/tianrui/images/fangzi.png'>"+
			"</div><div class='display_2 inline'>"+
	        "<div class='font_size_1'>"+bankName+"</div>"+
	        "<div class='font_size_2'>"+list[a].bankcard+"</div></div>"+
			"<div></div>"+
			"</div></div>";
			$("#innerHml").append(hml);
		}
	}
	function moreng(id,status,autid,card){
		//认证失败
		if(autid == "3"){
			window.location.href="/trwuliu/bank/card/uptAutidPage?id="+id+"&bankcard="+card;
		}else if(status=="0"&&autid=="1"){
			confirm("确认修改","确认设置该银行卡为默认银行卡吗?",function(){
				$.ajax({
					type:"post",
					url:"/trwuliu/bank/card/upt",
					data:{"id":id},
					success:function(ret){
						index();
					}
				});
			})
		}
	}
	</script>
	</body>
</html>

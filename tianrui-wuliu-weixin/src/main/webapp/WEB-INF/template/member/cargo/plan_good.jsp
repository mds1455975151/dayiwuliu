<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新建计划</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

   <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/style.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/tr-media.css"  rel="stylesheet">
    <link href="${trRoot}/tianrui/css/jquery-ui.min.css" rel="stylesheet">
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${scriptsRoot }/My97DatePicker/WdatePicker.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
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
       <!-- 引用公共left部分 -->
			<jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
        <!--个人中心右侧begin-->
        <div class="rz_right">
            <div class=" bgblue">
                <h2>货运计划</h2>
            </div>
            <!-- 货源计划内容begin -->
             <div class="goods_box">
                <form id="saveplan">
                <div class="goods_line" id="select01">
                    <label> 货物名称：</label>
                    <input type="text" onblur="nameChange();" placeholder=" " id="cargoname_v">
                    <input type="hidden" id="cargoname_v_id" name="cargoid">
                </div>
                <div class="goods_line" id="select02">
                    <label> 路线：</label>
                   <input type="text" placeholder=" " onblur="nameChange();" id="routename_v">
                    <input type="hidden" id="routename_v_id" name="routeid">
                </div>
                <div class="goods_line" id="select03">
                    <label> 运价策略：</label>
                     <input type="text" onblur="FreightChange();" placeholder=" " id="freightname_v">
                    <input type="hidden" id="freightname_v_id" name="freightid">
                </div>
                <div class="goods_line">
                    <div class="plan_table">
                        <table class="table " >
                            <thead>
                            <tr>
                                <th>货物编码</th>
                                <th>货物名称</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="hcargono"></span></td>
                            	<td><span id="hcargoname"></span></td>
                            	<input type="hidden" id="cargono" name="cargono" value="">
                            	<input type="hidden" id="cargoname" name="cargoname" value="">
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>起运地</th>
                                <th >目的地</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="hstartcity"></span></td>
                            	<td><span id="hendcity"></span></td>
                            	<input type="hidden" id="startcity" name="startcity" value="">
                            	<input type="hidden" id="endcity" name="endcity" value="">
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>计量单位</th>
                                <th >里程数</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="hmeasure"></span></td>
                            	<td><span id="hdistance"></span> </td>
                            	<input type="hidden" id="measure" name="measure" value="">
                            	<input type="hidden" id="distance" name="distance" value="">
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>发货人</th>
                                <th>收货人</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="receivepersion"></span></td>
                            	<td><span id="sendpersion"></span> </td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>计价单位</th>
                                <th>单价</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td style="height: 35px"><span id="hpriceunits"></span></td>
                            	<td><span id="hprice"></span></td>
                            	<input type="hidden" id="priceunits" name="priceunits" value="">
                            	<input type="hidden" id="price" name="price" value="">
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="goods_line">
                    <label> 计划总量：</label>
                    <input type="text" name="totalplanned" placeholder="" id ="totalplanned" onblur="countCost();">
                    	<a id="measure_name"></a>
                </div>
                <div class="goods_line">
                    <div class="good_time mr20">
                        <label>开始时间：</label>
                        <input type="text" name="starttimeStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" class="Wdate" style="width:170px" id="starttime"/>
                    </div>
                    <div class="good_time">
                        <label>结束时间：</label>
                        <input type="text" name="endtimeStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" class="Wdate" style="width:170px" id="endtime"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="goods_line">
                    <label> 计划费用：</label>
                    <input type="text" placeholder="" name="planprice" id="planprice">
                    元
                </div>
                <div class="goods_line">
                    <label> 联系人：</label>
                    <input type="text" value="${session_member.userName }" name="linkman" placeholder="" class="goods_lxr" id="linkman">
                    <!--常用联系人弹出框begin-->
  <!--                <div class="goods_cyren">
                        <h4>常用联系人</h4><i class="iconfont icon-shibai"></i>
                        <ul>
                            <li class="selected"><label>王先生</label>手机：<span>12345678954</span></li>
                            <li><label>张先生2</label>手机：<span>12345678954</span></li>
                            <li><label>李先生</label>手机：<span>12345678954</span></li>
                            <li><label>张先生2</label>手机：<span>12345678954</span></li>
                            <li><label>王小姐</label>手机：<span>12345678954</span></li>
                            <li><label>张先生2</label>手机：<span>12345678954</span></li>
                            <li><label>王小花</label>手机：<span>12345678954</span></li>
                            <li><label>欧阳哈哈</label>手机：<span>12345678954</span></li>
                            <li><label>王小姐</label>手机：<span>12345678954</span></li>
                        </ul>
                    </div>
                    <!--常用联系人弹出框end-->
                      
                </div>
                <div class="goods_line">
                    <label> 联系电话：</label>
                    <input type="text" placeholder="" name="telephone"  value="${session_member.cellPhone }" class="goods_tel" id="telephone">
                </div>
                <!--发布对象begin-->
                <div class="plan_fabu">
                    <label> 发布对象：</label>
                    <div class="plan_fabu_cont">
                        <div class="goods_radio">
                            <input type="radio" name="desc1" value="1"><label>熟车</label>
                            <input type="radio" class="goods_radiocz" name="desc1" id="radio1" value="2"><label>车主</label>
                        </div>
                        <div class="plan_shuche ">
									<div class="goods_czlist">
										<ul>
											<c:forEach items="${ownerResp1 }" var="aa">
												<li><input type="radio" value="${aa.ownerId }"
													name="vehicleownerid"><label>${aa.ownerName }</label>
													<!--车主名片弹出框begin-->
													<div class="goods_listdt">
														<div class="goods_listdt1">
															<img src="${aa.avatarsPath }" class="img-circle" />
															<p>${aa.ownerName }</p>
														</div>
														<div class="goods_listdt2">
															<p>手机：${aa.ownerTel }</p>
															<p>信息：</p>
															<p>备注：</p>
														</div>
													</div> <!--车主名片弹出框begin--></li>
											</c:forEach>
											<div class="clear"></div>
										</ul>
									</div>
								</div>
                        <div class="plan_chezhu ">
									<div class="goods_czlist">
										<ul>
											<c:forEach items="${ownerResp }" var="bb">
												<li><input type="radio" value="${bb.ownerId }"
													name="vehicleownerid"><label>${bb.ownerName }</label>
													<!--车主名片弹出框begin-->
													<div class="goods_listdt">
														<div class="goods_listdt1">
															<img src="${bb.avatarsPath }" class="img-circle" />
															<p>${bb.ownerName }</p>
														</div>
														<div class="goods_listdt2">
															<p>手机：${bb.ownerTel }</p>
															<p>信息：</p>
															<p>备注：</p>
														</div>
													</div> <!--车主名片弹出框begin--></li>
											</c:forEach>
											<div class="clear"></div>
										</ul>
									</div>
							</div>
                    </div>
                </div>
                <!--发布对象end-->
                <div class="clear"></div>
                <div class="goods_foot">
                   <input type="checkbox" name="istemplate" value="1"><label>是否存为模板</label><br>
                    <button class="btn btnyello mr20" onclick="savePlan();" type="button">发布</button>
                    <button class="btn btnblue " type="submit">返回</button>
                </div>
            </div>
            <!-- 货源计划内容end -->
        </div>
        <!--个人中心右侧end-->
        </div>
</div>
</div>
<!-- 引用公共footer部分 -->
<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
<script type="text/javascript">
	var PATH = "${path}";
	var PATH = "${path}";
	var memberId = "<%= member.getId() %>";
	var loginName = "<%= member.getLoginName() %>";
	var cellPhone = "<%= member.getCellPhone() %>";
</script>
<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>

<script>
    // 点击联系人输入框，显示常用联系人
    var kuang = $('.goods_lxr');
    kuang.click(function(){
        $('.goods_cyren').toggle();
    });
    // 双击常用联系人某一项，给input赋值，并隐藏
    $(".goods_cyren ul li").on("dblclick",function(){
        var planlabel = $(this).find("label").text();
        $(".goods_lxr").val(planlabel);
        var planSpan = $(this).find("span").text();
        $(".goods_tel").val(planSpan);
        $(".goods_cyren").hide();
    });
    // 常用联系人列表某一项选中
    var chren = $('.goods_cyren ul li');
    chren.click(function(){
        $(this).addClass('selected').siblings().removeClass('selected');
    });
    // 常用联系人框关闭按钮
    var cyclose = $('.goods_cyren i');
    cyclose.click(function(){
        $(".goods_cyren").hide();
    });
    // 发布对象-车主列表显示隐藏
        $(":radio").click(function(){
            if ($("input[name='desc1']:eq(0)").is(":checked")) {
                $(".plan_shuche .goods_czlist").show();
                $(".plan_chezhu .goods_czlist").hide();
            } else if ($("input[name='desc1']:eq(1)").is(":checked")) {
                $(".plan_shuche .goods_czlist").hide();
                $(".plan_chezhu .goods_czlist").show();
            }
        });
    // 车主列表名片的显示隐藏
    $(".goods_czlist ul li").mouseover(function () {
        $(this).find('.goods_listdt').show();
    });
    $(".goods_czlist ul li").mouseout(function () {
        $(this).find('.goods_listdt').hide();
    });
    var CONTEXTPATH="${contextPath}";
</script>
</body>
<script type="text/javascript" src="/resources/js/member/cargo/plan_good.js" ></script>
</html>
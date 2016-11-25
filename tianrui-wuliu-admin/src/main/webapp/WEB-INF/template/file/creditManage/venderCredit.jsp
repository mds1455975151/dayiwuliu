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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>信用管理</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
    <link href="${stylesRoot }/tr-media.css" rel="stylesheet">
    <link href="${stylesRoot }/easyTree.css" rel="stylesheet">
    <link href="${stylesRoot }/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="Shortcut Icon" href="${imagesRoot}/favicon.ico" type="image/x-icon">
    <script language="javascript" type="text/javascript" src="${scriptsRoot}/My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">
    	.tr_sel {
    		height: 40px;
   		    margin-left: 30px;
    	}
    	.tr_sel label {
		    width: 150px;
		    text-align: right;
		}
		
		.tr_sel select {
		    width: 70px;
		    text-align: left;
		    margin-left: 10px;
		}
    </style>
</head>
<body>

<div class="container-fluid">
	<jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
        <!--后台右侧布局begin-->
        <div class="col-md-10 ">
            <div class="ht_content">
                <div id="content-header">
                    <div class="tj_honorma">
                    <h3>车主信用管理</h3>
                        <select id="credityear"></select><label>年</label>
                        <select id="creditmonth"></select><label>月</label>
                        <button class="btn btnblue searchCredit">搜索</button>
                    </div>
                </div>
            <div class="row mt15">
                    <div class="col-md-12">
                        <div class="content-user">
                            <!--用户表格begin-->
                            <table class="table table-bordered" >
                                <thead >
                                <tr>
                                    <th>序号</th>
                                    <th>注册手机号</th>
                                    <th>车主名称</th>
                                    <th>拥有车辆数</th>
                                    <th>承运车次</th>
                                    <th>发运及时率</th>
                                    <th>到达及时率</th>
                                    <th>回单及时率</th>
                                    <th>货物车质损率</th>
                                    <th>运输事故率</th>
                                    <th>加分项管理</th>
                                    <th>扣分项管理</th>
                                </tr>
                                </thead>
                                <tbody id="creditBody">
                                </tbody>
                            </table>
                            <!--用户表格end-->
                        </div>
                        <!--分页效果开始-->
                        <!-- <div class="page_wrap">
                            <div class="page_date">
                                <label>数据共：</label><i>100</i><label>条</label>
                            </div>
                            <div class="page_date">
                                <label>跳到第：</label>
                                <input type="text">
                                <label>页</label>
                                <button class="btn btn-default">确定</button>
                            </div>
                            <div class="page_select">
                                <label>每页记录：</label>
                                <select class="form-control">
                                    <option>10</option>
                                    <option>20</option>
                                    <option>30</option>
                                </select>
                            </div>
                            <div class="page_btn">
                                <ul class="pagination">
                                    <li><a href="#">&laquo;上一页</a></li>
                                    <li><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">...</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li><a href="#">下一页&raquo;</a></li>
                                </ul>
                            </div> -->
                            <!--分页效果结束-->
                    </div>
                </div>
            </div>
        </div>
        <!--后台右侧布局end-->
    </div>
    <!--后台整体布局end-->
</div>
<!--侧边栏end-->
</div>

<!--加分项 begin-->
<input id="csId" type="hidden">
<div class="modal fade" id="adddetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">加分项管理</h4>
            </div>
            <div class="modal-body">
                <div class="tr_sel">
                    <label>协助发运</label>
					<select id="addAssist">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
                </div>
                <div class="tr_sel">
                	<label>协助公司完成特殊任务</label>
                	<select id="addSpecial">
                		<option value="0">0</option>
                		<option value="2">2</option>
                		<option value="4">4</option>
                		<option value="6">6</option>
                		<option value="8">8</option>
                		<option value="10">10</option>
                	</select>
                </div>
                <div class="tr_sel">
                	<label>重大贡献</label>
                	<select id="addContribute">
                		<option value="0">0</option>
                		<option value="10">10</option>
                		<option value="20">20</option>
                	</select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary addComfirm">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--加分项end-->

<!--加分项 begin-->
<div class="modal fade" id="subdetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">扣分项管理</h4>
            </div>
            <div class="modal-body">
                <div class="tr_sel">
                	<label>违规背车/货车拉人</label>
                	<select id="subManned">
                		<option value="0">0</option>
                		<option value="40">40</option>
                	</select>
                </div>
                <div class="tr_sel">
                	<label>违规运作</label>
                	<select id="subOperation">
                		<option value="0">0</option>
                		<option value="2">2</option>
                		<option value="10">10</option>
                		<option value="20">20</option>
                	</select>
                </div>
                <div class="tr_sel">
                	<label>重、特大责任事故</label>
                	<select id="subAccident">
                		<option value="0">0</option>
                		<option value="20">20</option>
                		<option value="40">40</option>
                	</select>
                </div>
                <div class="tr_sel">
                	<label>客户严重投诉/加注劣质燃料</label>
                	<select id="subComplaint">
                		<option value="0">0</option>
                		<option value="10">10</option>
                		<option value="11">11</option>
                		<option value="12">12</option>
                		<option value="13">13</option>
                		<option value="14">14</option>
                		<option value="15">15</option>
                		<option value="16">16</option>
                		<option value="17">17</option>
                		<option value="18">18</option>
                		<option value="19">19</option>
                		<option value="20">20</option>
                	</select>
                </div>
                <div class="tr_sel">
                	<label>运力资源流动率</label>
                	<select id="subTurnover">
                		<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option>
						<option value="32">32</option>
						<option value="33">33</option>
						<option value="34">34</option>
						<option value="35">35</option>
						<option value="36">36</option>
						<option value="37">37</option>
						<option value="38">38</option>
						<option value="39">39</option>
						<option value="40">40</option>
						<option value="41">41</option>
						<option value="42">42</option>
						<option value="43">43</option>
						<option value="44">44</option>
						<option value="45">45</option>
						<option value="46">46</option>
						<option value="47">47</option>
						<option value="48">48</option>
						<option value="49">49</option>
						<option value="50">50</option>
						<option value="51">51</option>
						<option value="52">52</option>
						<option value="53">53</option>
						<option value="54">54</option>
						<option value="55">55</option>
						<option value="56">56</option>
						<option value="57">57</option>
						<option value="58">58</option>
						<option value="59">59</option>
						<option value="60">60</option>
						<option value="61">61</option>
						<option value="62">62</option>
						<option value="63">63</option>
						<option value="64">64</option>
						<option value="65">65</option>
						<option value="66">66</option>
						<option value="67">67</option>
						<option value="68">68</option>
						<option value="69">69</option>
						<option value="70">70</option>
						<option value="71">71</option>
						<option value="72">72</option>
						<option value="73">73</option>
						<option value="74">74</option>
						<option value="75">75</option>
						<option value="76">76</option>
						<option value="77">77</option>
						<option value="78">78</option>
						<option value="79">79</option>
						<option value="80">80</option>
						<option value="81">81</option>
						<option value="82">82</option>
						<option value="83">83</option>
						<option value="84">84</option>
						<option value="85">85</option>
						<option value="86">86</option>
						<option value="87">87</option>
						<option value="88">88</option>
						<option value="89">89</option>
						<option value="90">90</option>
                	</select>
                </div>
                <div class="tr_sel">
                	<label>扰乱正常经营</label>
                	<select id="subDisrupt">
                		<option value="0">0</option>
                		<option value="40">40</option>
                	</select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary subComfirm">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--加分项end-->

<!--个人账户begin-->
<div class="modal fade" id="account" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">个人信息修改</h4>
            </div>
            <div class="modal-body">
                <div class="juesemodal">
                    <p><label>用户名：</label><span>adsgdgweg</span></p>
                    <p><label>姓名：</label><input type="text" value="王大大"></p>
                    <div class="modal_note"><h4>姓名不能为空，不超过20个字符</h4></div>
                    <p><label>手机号：</label><input type="text" value="1583652415"></p>
                    <div class="modal_note"><h4>请输入11位手机号</h4></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--个人账户end-->
<!--修改密码begin-->
<div class="modal fade" id="password" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="juesemodal">
                    <p><label>当前密码：</label><input type="password" value="王大大"></p>
                    <p><label>新密码：</label><input type="password" ></p>
                    <p><label>确认密码：</label><input type="password"></p>
                    <div class="modal_note"><h4>两次密码不一致</h4></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--退出begin-->
<div class="modal fade" id="exit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <h4>您确定要退出此账户吗？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--退出end-->


<!--修改密码end-->
<%@include file="../../common/footer.jsp"%>
<script type="text/javascript">
	var CONTEXTPATH = "${contextPath}";
	var imagesRoot = "${imagesRoot}";
</script>
<script type="text/javascript" src="${scriptsRoot}/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${scriptsRoot}/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="/resources/js/creditManage/venderCredit.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        // 表格列宽度手动调整
        $("table").resizableColumns({});
    });
</script>
</body>
</html>
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
<title>大易物流-运输合同</title>
<link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
<link href="${stylesRoot }/base.css" rel="stylesheet">
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
	 <style>
        .textBox{
            border: 1px solid #ccc;
            font-size: 14px;
            font-family: "微软雅黑";
            line-height: 40px;
            margin-top: 10px;
            text-indent:1em;
        }
        .red{
            color:red;
        }
    </style>
</head>
<body>
	 <div class="container">
		<!--后台右侧布局begin-->
		        <div class="row">
            <div class="textBox">
                <p style="text-align: center;font-size: 24px;">运输合同</p>
                <p style="text-align: right">运单号：<span class="red">【取系统运单号】</span></p>
                <p>运输合同条款:</p>
                <p> 1.承运人运输前应核实运输货物信息，如发现实际装车的货物数量、体积、重量与清单不符，承运人应及时提出异议，双方协商变更运费、实际货物信息或解除本合同。装车起运后视为承运人同意托运货物信息，承运人不得以货物及其数量、体积、重量的变更向托运人主张任何权利</p>
                <p>2、承运人承运货物途中不得给物料掺假、兑水、偷换或添加杂质。如托运人发现视情况轻重有权追究承运人责任，赔偿数额应以承运人运输物料价值的2倍作为赔偿金。</p>
                <p> 3、甲方（托运方）代实际承运人投保货物险，投保费用从运费中扣除，保险费率按照保险公司规定执行。</p>
                <p>4、货物装车后，承运人应在约定时间将货物送到收货方，不得延误送货时间或不得以其它原因暂扣所承运的货物。若暂扣所承运的货物或其他原因延误送货时间导致货物损毁或造成收货方因延误收货而索赔，则承运人须承担因此给托运人造成的一切损失。</p>
                <p>5、承运人应保证货物安全，如在承运过程中因交通事故或其它原因造成第三人损失，承运人应承担赔偿责任，托运人不承担任何责任。如托运人对外承担责任，托运人可直接向承运人追偿。</p>
                <p> 6、货到指定地点后，托运方指定单位首先复磅，磅差和路途损失标准按     计算，超出正常损耗部分承运方照价赔偿。</p>
                <p>7、如本合同履行过程中双方发生争议，应协商解决，引起纠纷双方应依法向托运单位所在地人民法院起诉。</p>

                <p>附：运单明细单据：</p>
                <table width="100%" border="1" cellspacing="0" cellpadding="0">
                    <tr><td colSpan="8">&nbsp;&nbsp;承运人：<span class="red">【实际承运人】</span></td></tr>
                    <tr>
                        <td colSpan="2">收货人：<span class="red">取收货人名字</span></td>
                        <td colSpan="2">手机号：<span class="red">取收货人手机号</span></td>
                        <td colSpan="2">装货地点：<span class="red">取运单上起运地</span></td>
                        <td colSpan="2">卸货地点：<span class="red">取运单上目的地</span></td>
                    </tr>
                    <tr>
                        <td colSpan="2">驾驶员：<span class="red">取司机名字</span></td>
                        <td colSpan="2">身份证号：<span class="red">取司机身份证号</span></td>
                        <td>承运车牌号：<span class="red">取车牌号</span></td>
                        <td>发动机号：<span class="red">取车辆发动机号</span></td>
                        <td>随车电话：<span class="red">取司机电话</span></td>
                    </tr>
                    <tr>
                        <td>货物名称</td>
                        <td>规格</td>
                        <td>始发数量</td>
                        <td>计量单位</td>
                        <td>运费单价</td>
                        <td>实收数量</td>
                        <td>到达时间</td>
                    </tr>
                    <tr>
                        <td> 取运单货物名</td>
                        <td></td>
                        <td>取运单原发数</td>
                        <td>取运单计量单位</td>
                        <td>取运单运费单价</td>
                        <td>取运单实收数</td>
                        <td rowSpan="2">取运单卸货时间</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>合计</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colSpan="4">  货物总价值（元):<span class="red">取货物价值 保险金额</span>  &nbsp; &nbsp; 运费总价：<span class="red">   取运单结算金额</span> </td>
                        <td colSpan="4">付款方式：<span class="red">  根据支付对象   </span>  &nbsp; &nbsp;  现付 <input type="checkbox">     &nbsp; &nbsp;  月付 <input type="checkbox"></td>
                    </tr>
                    <tr>
                        <td colSpan="4"> 托运单位：中原大易科技有限公司</td>
                        <td colSpan="4">	承运人（代理人）签字：<span class="red">取司机名字</span>   &nbsp; &nbsp; 电话：<span class="red">取司机电话</span></td>
                    </tr>
                    <tr>
                        <td colSpan="4">托运人（代理人）签字：   <span class="red">取发货人</span> &nbsp; &nbsp;电话： <span class="red">发货人电话</span></td>
                        <td colSpan="4">收货人（代理人）签字：<span class="red">取收货人名字</span>  &nbsp; &nbsp;电话：<span class="red">取收货人电话</span></td>
                    </tr>
                    <tr><td colSpan="8" style="text-align: center"> 24小时热线电话：400-056-1156  &nbsp;  &nbsp; &nbsp;  取运单时间  &nbsp; &nbsp; 年  &nbsp; &nbsp; 月 &nbsp; &nbsp; 日</td></tr>
                </table>
				
            </div>
				<br><br><br><br><br><br><br><br>
        </div>
        </div>
	</body>	
</html>
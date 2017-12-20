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
<title>大易物流-电子运单</title>
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
<body  onmousemove=\HideMenu()\ oncontextmenu="return false" ondragstart="return false" onselectstart ="return false" onselect="document.selection.empty()" oncopy="document.selection.empty()" onbeforecopy="return false" onmouseup="document.selection.empty()">
	 <div class="container">
		<!--后台右侧布局begin-->
		        <div class="row">
		        
            <div class="textBox">
            	<div style="width:95%;margin:0 auto;">
            	<p style="text-align: center;font-size: 24px;">中原大易科技有限公司运输业务电子运单</p>
		                <p style="text-align: left">
		                <div style="float:left;">运单号：${agreement.billNo }</div>
		                <div style="float:right;">运单日期：${agreement.dateAcctept }</div>
		                	</p>
                <table width="100%" border="1" cellspacing="0" cellpadding="0">
                    <tr>
                        <td colSpan="3">托运人：中原大易科技有限公司</td>
                        <td colSpan="2">装货地点：${agreement.sposition }</td>
                        <td colSpan="2">卸货地点：${agreement.eposition }</td>
                        <td>计划到达时间</td>
                    </tr>
                    <tr>
                        <td colSpan="3">承运人：${agreement.venderName }</td>
                        <td colSpan="2">承运车牌号：${agreement.vehicleNo }</td>
                        <td colSpan="2">发动机号：${agreement.motor }</td>
                        <td rowspan="5">${agreement.dateUnloading }</td>
                    </tr>
                    <tr>
                        <td colSpan="3">司机姓名：${agreement.driverName }</td>
                        <td colSpan="2">身份证号：${agreement.driverIDCardNO }</td>
                        <td colSpan="2">随车电话：${agreement.driverCellphone }</td>
                    </tr>
                     <tr>
                        <td colSpan="3">发货人及联系方式：${agreement.linkman }  &nbsp; &nbsp;${agreement.linknumber }</td>
                        <td colSpan="4">收货人及联系方式：${agreement.receiveName }&nbsp; &nbsp;${agreement.receiveCellphone }</td>
                    </tr>
                    <tr>
                        <td >货物名称</td>
                        <td>规格</td>
                        <td>计量单位</td>
                        <td>运费单价</td>
                        <td>实发数量</td>
                        <td colspan="2">实收数量</td>
                    </tr>
                    <tr>
                        <td> ${agreement.cargoName }</td>
                        <td>${agreement.specifications }</td>
                        <td>${agreement.measure }</td>
                        <td>${agreement.billPrice }</td>
                        <td>${agreement.pickupweight }</td>
                        <td colspan="2"></td>
                    </tr>
                        
                    <tr>
                    	<td colSpan="2">付款方式： &nbsp; &nbsp; 
                        		<c:if test="${agreement.payMent eq 1}">
	                               	现付 <input type="checkbox" checked="checked" disabled="disabled" >     &nbsp; &nbsp;  月付 <input type="checkbox" disabled="disabled"></td>
	                            </c:if> 
	                            <c:if test="${agreement.payMent eq 2}">
	                               	现付 <input type="checkbox" disabled="disabled" >     &nbsp; &nbsp;  月付 <input type="checkbox" checked="checked" disabled="disabled"></td>
	                            </c:if> 
                        <td colSpan="3">托运人（代理人）签字：${agreement.linkman }   &nbsp; &nbsp;${agreement.timeAcctept }</td>
                        <td colSpan="3">司机签字：${agreement.driverName }  &nbsp; &nbsp;${agreement.timeBIllAcctept }</td>
                    </tr>
                    <tr><td colSpan="8" style="text-align: left;">
		                <p>运输合同条款:</p>
		                <p>1、承运人运输前应核实运输货物信息，如发现实际装车的货物数量、体积、重量与清单不符，承运人应及时提出异议，双方协商变更运费、实际货物信息或解除本合同。装车起运后视为承运人同意托运货物信息，承运人不得以货物及其数量、体积、重量的变更向托运人主张任何权利</p>
		                <p>2、承运人承运货物途中不得给物料掺假、兑水、偷换或添加杂质。如托运人发现视情况轻重有权追究承运人责任，赔偿数额应以承运人运输物料价值的2倍作为赔偿金。</p>
		              	<p>3、承运人为托运人运输过程中产生成品油和支付的道路、桥、闸通行费由托运人承担。</p>
		                <p>4、甲方（托运方）代实际承运人投保货物险，投保费用从运费中扣除，保险费率按照保险公司规定执行。</p>
		                <p>5、货物装车后，承运人应在约定时间将货物送到收货方，不得延误送货时间或不得以其它原因暂扣所承运的货物。若暂扣所承运的货物或其他原因延误送货时间导致货物损毁或造成收货方因延误收货而索赔，则承运人须承担因此给托运人造成的一切损失。</p>
		                <p>6、承运人应保证货物安全，如在承运过程中因交通事故或其它原因造成第三人损失，承运人应承担赔偿责任，托运人不承担任何责任。如托运人对外承担责任，托运人可直接向承运人追偿。</p>
		                <p>7、货到指定地点后，托运方指定单位首先复磅，磅差和路途损失标准按     计算，超出正常损耗部分承运方照价赔偿。</p>
		                <p>8、如本合同履行过程中双方发生争议，应协商解决，引起纠纷双方应依法向托运单位所在地人民法院起诉。</p>
                    </td></tr>
                </table>
				</div>
            </div>
				<br><br><br><br><br><br><br><br>
        </div>
        </div>
	</body>	
</html>
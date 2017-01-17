  <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!--个人中心左侧begin-->
        <div class="rz_left">
        <c:choose>
        	<c:when test="${role eq 'hz' }">
        		<div class="rz_lhead bgblue">
	                <i class="iconfont icon-user"></i><span>我是货主</span>
	            </div>
	            <div class="rz_list">
	                <div class="rz_listb bgwhite">
	                    <ul>
	                        <li><a href="/trwuliu/Member/myVehiOwner/myVehiOwnerPage" id="myVehiOwnerPage">我的车主</a></li>
	                        <li><a href="/trwuliu/planowner/create" id="plancreate">新建计划</a></li>
	                        <li><a href="/trwuliu/planowner/main" id="planowner">我发布的计划</a></li>
	                        <li><a href="/trwuliu/plantemplate/main" id="plantemplate">我的计划模板</a></li>
	                        <li><a href="/trwuliu/billowner/main" id="billowner">我发布的运单</a></li>
	                        <!-- <li><a href="/trwuliu/reportdemo/main" id="billreport">我的报表</a></li> -->
	                        <li><a href="/trwuliu/ownerreport/main" id="ownerreport">我的报表</a></li>
	                        <li><a href="/trwuliu/billAnlian/owner" id="ownerAnlian">开票运单</a></li>
	                    </ul>
	                </div>
	            </div>
        	</c:when>
        	<c:when test="${role eq 'cz' }">
        		<div class="rz_lhead bgblue">
	                <i class="iconfont icon-user"></i><span>我是车主</span>
	            </div>
	            <div class="rz_list">
	                <div class="rz_listb bgwhite">
	                    <ul>
	                        <li><a href="/trwuliu/Member/myVehicle/myVehiclePage" id="myVehiclePage">我的车辆</a></li>
	                        <li><a href="/trwuliu/Member/myDriver/myDriverPage" id="myDriverPage">我的司机</a></li>
	                        <li><a href="/trwuliu/Member/myVehiOwner/myVehiOwnerPage" id="myVehiOwnerPage">二级运营商</a></li>
	                        <li><a href="/trwuliu/Member/capa/indexPage" id="mycapaPage">我的运力</a></li>
	                        <li><a href="/trwuliu/planvender/main" id="planvender">我承运的计划</a></li>
	                        <li><a href="/trwuliu/planAppoint/main" id="planAppoint">我委派的计划</a></li>
	                        <li><a href="/trwuliu/billvender/main" id="billvender">我承运的运单</a></li>
	                        <li><a href="/trwuliu/billAppoint/main" id="billAppoint">我委派的运单</a></li>
	 					    <li><a href="/trwuliu/payInvoiceItem/main" id="yunfei">运费结算单</a></li>
	                        <li><a href="/trwuliu/payInvoice/main" id="paymain">支付发票账单</a></li>
	 					    <!-- 
	 					     -->
	                        <li><a href="/trwuliu/venderreport/main" id="venderreport">我的报表</a></li>
	                        <li><a href="/trwuliu/billAnlian/vender" id="venderAnlian">开票运单</a></li>
	                    </ul>
	                </div>
	            </div>
        	</c:when>
        	<c:when test="${role eq 'sj' }">
        		<div class="rz_lhead bgblue">
	                <i class="iconfont icon-user"></i><span>我是司机</span>
	            </div>
	            <div class="rz_list">
	                <div class="rz_listb bgwhite">
	                    <ul>
	                    	<li><a href="/trwuliu/billdriver/main"  id="billdriver">我运输的运单</a></li>
	                        <li><a href="/trwuliu/billdriver/handView" id="handdriver">司机交班</a></li>
	                        <li><a href="/trwuliu/driverreport/main" id="driverreport">我的报表</a></li>
	                        <li><a href="/trwuliu/payInvoiceDriver/main" id="driverpay">运费结算单</a></li>
	                        <li><a href="/trwuliu/billAnlian/driver" id="driverAnlian">开票运单</a></li>
	                    </ul>
	                </div>
	            </div>
        	</c:when>
        </c:choose>

            <div class="rz_list">
                <div class="rz_listb bgwhite">
                    <ul>
                        <li><a href="/trwuliu/Member/personalDataPage?role=${role }" id="personalDataPage" >个人资料</a></li>
                        <li ><a href="/trwuliu/Member/authenPage?role=${role }" id="authenPage"> 实名认证</a></li>
<!--                        	<li><a>我的账单</a></li>
                        <li><a>账号安全</a></li> -->
                        <li><a href="/trwuliu/Member/message/message?role=${role }" id="messagePage">消息中心<span id="messageCount"></span> </a></li> 
                    </ul>
                </div>
            </div>
        </div>
        <!--个人中心左侧end-->
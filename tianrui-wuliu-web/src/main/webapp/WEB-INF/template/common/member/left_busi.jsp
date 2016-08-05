  <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
        <!--个人中心左侧begin-->
        <div class="rz_left">
            <div class="rz_lhead bgblue">
                <i class="iconfont icon-user"></i><span>个人中心</span>
            </div>
            <div class="rz_list">
                <div class="rz_listh">
                    <i class="iconfont icon-trunk"></i><h4>运力管理</h4>
                </div>
                <div class="rz_listb bgwhite">
                    <ul>
                        <li><a href="/trwuliu/Member/myVehicle/myVehiclePage" id="myVehiclePage">我的车辆</a></li>
                        <li><a href="/trwuliu/Member/myDriver/myDriverPage" id="myDriverPage">我的司机</a></li>
                        <li><a href="/trwuliu/Member/myVehiOwner/myVehiOwnerPage" id="myVehiOwnerPage">我的车主</a></li>
                    </ul>
                </div>
            </div>
            <div class="rz_list">
                <div class="rz_listh">
                    <i class="iconfont icon-fahuo"></i><h4>计划管理</h4>
                </div>
                <div class="rz_listb bgwhite">
                    <ul>
                        <li><a href="/trwuliu/planowner/create" id="plancreate">新建计划</a></li>
                        <li><a href="/trwuliu/planowner/main" id="planowner">我发布的计划</a></li>
                        <li><a href="/trwuliu/planvender/main" id="planvender">我承运的计划</a></li>
                        <li><a href="/trwuliu/plantemplate/main" id="plantemplate">我的计划模板</a></li>
                    </ul>
                </div>
            </div>
            <div class="rz_list">
                <div class="rz_listh">
                    <i class="iconfont icon-yundangenzong"></i><h4>运单管理</h4>
                </div>
                <div class="rz_listb bgwhite">
                    <ul>
                        <li ><a href="/trwuliu/billowner/main" id="billowner">我发布的运单</a></li>
                        <li ><a href="/trwuliu/billvender/main" id="billvender">我承运的运单</a></li>
                        <li><a href="/trwuliu/billdriver/main"  id="billdriver">我运输的运单</a></li>
                        <li><a href="/trwuliu/billdriver/handView" id="handdriver">司机交班</a></li>
                    </ul>
                </div>
            </div>
            <div class="rz_list">
                <div class="rz_listh">
                    <i class="iconfont icon-huiyuan"></i><h4>账户中心</h4>
                </div>
                <div class="rz_listb bgwhite">
                    <ul>
                        <li><a href="/trwuliu/Member/personalDataPage" id="personalDataPage" >个人资料</a></li>
                        <li ><a href="/trwuliu/Member/authenPage" id="authenPage"> 实名认证</a></li>
<!--                        	<li><a>我的账单</a></li>
                        <li><a>账号安全</a></li> -->
                        <li><a href="/trwuliu/Member/message/message" id="messagePage">消息中心<span id="messageCount"></span> </a></li> 
                    </ul>
                </div>
            </div>
        </div>
        <!--个人中心左侧end-->
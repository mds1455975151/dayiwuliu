<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<div class="bghui">
    <div class="wrap">
        <!--登录头部行begin-->
        <div class="header">
            <div class="header_left">
                <label class="mr10">欢迎来到大易物流平台！</label>
                <a href=""><label> 请[登录]</label></a>
                <a href="" class="colorreg"><label> [免费注册]</label></a>
            </div>
        </div>
        <!--登录头部行end-->
    </div>
</div>
<div class="banner">
    <div class="login">
        <h2>用户登录</h2>
        <div class="lg_cont">
            <div class="login_tel">
                <i class="icononline"> &#xe616;</i><span>|</span>
                <input type="text" placeholder="请输入手机号">
            </div>
            <div class="login_tel">
                <i class="icononline">&#xe618;</i><span>|</span>
                <input type="text" placeholder="请输入手机号">
            </div>
            <div class="lg_reg">
                <label class="colorblue">没有账号？</label>
                <a class="colorreg">立即注册</a>
            </div>
            <div class="lg_btn">
                <h4>立即登录</h4>
            </div>
        </div>
    </div>
</div>
<div class="bgdata">
    <div class="wrap">
        <div class="data_total">
            <h4 class="fbold">数据总览</h4>
            <ul class="data_tta">
                <li class="bgred">
            	<a href="/count/plan">
                    <div class="dtail_tit">
                        <label>货运总量</label>
                        <span class="fr">
                        	<c:if test="${plan.sumdate - monthplan.sumdate >= 0}">
                        	同比上月增长
                        	<fmt:formatNumber value="${(plan.sumdate - monthplan.sumdate)/monthplan.sumdate }" type="number" pattern="0.00%" />
                        	</c:if>
                        	<c:if test="${plan.sumdate - monthplan.sumdate < 0}">
                        	同比上月减少
                        	<fmt:formatNumber value="${(monthplan.sumdate - plan.sumdate)/monthplan.sumdate }" type="number" pattern="0.00%" />
                        	</c:if>
                        	
                       	</span>
                    </div>
                    <div class="divder_tm"></div>
                    <div class="dtail_body">
                        <label>${plan.sumdate }</label><span>吨</span>
                    </div>
                    <div class="dtail_foot">
                        <label>本月</label><i class="icononline">
						<c:if test="${plan.sumdate - monthplan.sumdate >= 0}">
						&#xe617;</i>
						<span>${plan.sumdate - monthplan.sumdate }</span>
						</c:if>
						<c:if test="${plan.sumdate - monthplan.sumdate < 0}">
						&#xe619;</i>
						<span>${monthplan.sumdate - plan.sumdate }</span>
						</c:if>
						
                        
                    </div>
            	</a>
                </li>
                <li class="bgyello">
            	<a href="/count/vehicle">
                    <div class="dtail_tit">
                        <label>车辆总数</label>
                        <span class="fr">
                        	<c:if test="${vehicle.sumdate - monthvehicle.sumdate >= 0}">
                       		同比上月增长
                       		<fmt:formatNumber value="${(vehicle.sumdate - monthvehicle.sumdate)/monthvehicle.sumdate }" type="number" pattern="0.00%" />
                        	</c:if>
                        	<c:if test="${vehicle.sumdate - monthvehicle.sumdate < 0}">
                       		同比上月减少
                       		<fmt:formatNumber value="${(monthvehicle.sumdate - vehicle.sumdate)/monthvehicle.sumdate }" type="number" pattern="0.00%" />
                        	</c:if>
                       		
                       		</span>
                    </div>
                    <div class="divder_tm"></div>
                    <div class="dtail_body">
                        <label>${vehicle.sumdate }</label><span>辆</span>
                    </div>
                    <div class="dtail_foot">
                        <label>本月</label><i class="icononline">
						<c:if test="${vehicle.sumdate - monthvehicle.sumdate >= 0}">&#xe617;
						</i>
                        <span>${vehicle.sumdate - monthvehicle.sumdate }</span>
						</c:if>
						<c:if test="${vehicle.sumdate - monthvehicle.sumdate < 0}">&#xe619;
						</i>
                        <span>${monthvehicle.sumdate - vehicle.sumdate }</span>
						</c:if>
						
                    </div>
            	</a>
                </li>
                <li class="bggreen">
            	<a href="/count/bill">
                    <div class="dtail_tit">
                        <label>交易总量</label>
                        <span class="fr">
                        	<c:if test="${bill.sumdate - monthbill.sumdate >= 0}">
                        	同比上月增长
                        	<fmt:formatNumber value="${(bill.sumdate - monthbill.sumdate)/monthbill.sumdate }" type="number" pattern="0.00%" />
                        	</c:if>
                        	<c:if test="${bill.sumdate - monthbill.sumdate < 0}">
                        	同比上月减少
                        	<fmt:formatNumber value="${(monthbill.sumdate - bill.sumdate)/monthbill.sumdate }" type="number" pattern="0.00%" />
                        	</c:if>
                       	</span>
                    </div>
                    <div class="divder_tm"></div>
                    <div class="dtail_body">
                        <label>${bill.sumdate }</label><span>单</span>
                    </div>
                    <div class="dtail_foot">
                        <label>本月</label><i class="icononline">
						<c:if test="${bill.sumdate - monthbill.sumdate >= 0}">&#xe617;
						</i>
                        <span>${bill.sumdate - monthbill.sumdate }</span>
						</c:if>
						<c:if test="${bill.sumdate - monthbill.sumdate < 0}">&#xe619;
						</i>
                        <span>${monthbill.sumdate - bill.sumdate }</span>
						</c:if>
						
                    </div>
            	</a>
                </li>
                <li class="bgblue">
            	<a href="/count/pay">
                    <div class="dtail_tit">
                        <label>运费总额</label>
                        <span class="fr">
                        <c:if test="${pay.sumdate - monthpay.sumdate >= 0}">
                        	同比上月增长
                        <fmt:formatNumber value="${(pay.sumdate - monthpay.sumdate)/monthpay.sumdate }" type="number" pattern="0.00%" />
                        </c:if>
                        <c:if test="${pay.sumdate - monthpay.sumdate < 0}">
                        	同比上月增长
                        <fmt:formatNumber value="${(monthpay.sumdate - pay.sumdate)/monthpay.sumdate }" type="number" pattern="0.00%" />
                        </c:if>
                        
                        </span>
                    </div>
                    <div class="divder_tm"></div>
                    <div class="dtail_body">
                        <label>${pay.sumdate }</label><span>元</span>
                    </div>
					
                    <div class="dtail_foot">
                        <label>本月</label><i class="icononline">
						<c:if test="${pay.sumdate - monthpay.sumdate >= 0}">&#xe617;
						</i>
                        <span>${pay.sumdate - monthpay.sumdate }</span>
						</c:if>
						<c:if test="${pay.sumdate - monthpay.sumdate < 0}">&#xe619;
						</i>
                        <span>${monthpay.sumdate - pay.sumdate }</span>
						</c:if>
						
                    </div>
            	</a>
                </li>
            </ul>
        </div>
    </div>
</div>

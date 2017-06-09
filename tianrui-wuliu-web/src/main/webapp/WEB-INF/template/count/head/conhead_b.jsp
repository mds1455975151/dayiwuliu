<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<div class="bghui">
    <div class="wrap">
        <!--登录头部行begin-->
        <div class="header">
            <div class="header_left">
                <label class="mr10">欢迎来到大易物流平台！</label>
                <a href="/count/route"><label> 请[登录]</label></a>
                <a href="/publicMember/registerPage" class="colorreg"><label> [免费注册]</label></a>
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
                <input type="text" maxlength="11" id="login_tel" placeholder="请输入手机号">
            </div>
            <div class="login_tel">
                <i class="icononline">&#xe618;</i><span>|</span>
                <input type="password" id="login_psw" placeholder="请输入密码">
            </div>
            <div class="note">
                <h4><span id="message_loginError" style="color: white;"></span></h4>
            </div>
            <div class="lg_reg">
                <label class="colorblue">没有账号？</label>
                <a href="/publicMember/registerPage" class="colorreg">立即注册</a>
                <a href="/publicMember/lostPass" class="colorreg">忘记密码</a>
            </div>
            <div class="lg_btn" onclick="loginIn()">
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
                    </div>
                    <div class="divder_tm"></div>
                    <div class="dtail_body">
                        <label>623万</label><span>吨</span>
                    </div>
                    <div class="dtail_foot">
                        <label>本月</label>
						<span>
						78.85万吨
						</span>
                    </div>
            	</a>
                </li>
                <li class="bgyello">
            	<a href="/count/vehicle">
                    <div class="dtail_tit">
                        <label>车辆总数</label>
                    </div>
                    <div class="divder_tm"></div>
                    <div class="dtail_body">
                        <label>8521</label><span>辆</span>
                    </div>
                    <div class="dtail_foot">
                        <label>本月</label>
						</i>
                        <span>263辆</span>
                    </div>
            	</a>
                </li>
                <li class="bggreen">
            	<a href="/count/bill">
                    <div class="dtail_tit">
                        <label>交易总量</label>
                    </div>
                    <div class="divder_tm"></div>
                    <div class="dtail_body">
                        <label>123659</label><span>单</span>
                    </div>
                    <div class="dtail_foot">
                        <label>本月</label>
                        <span>23895单</span>
                    </div>
            	</a>
                </li>
                <li class="bgblue">
            	<a href="/count/pay">
                    <div class="dtail_tit">
                        <label>运费总额</label>
                    </div>
                    <div class="divder_tm"></div>
                    
                    <div class="dtail_body">
                        <label>
                       869.56
                        </label>
                        <span>万元</span>
                    </div>
					
                    <div class="dtail_foot">
                        <label>本月</label>
                        <span>
                       		510万元
                        </span>
                    </div>
            	</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<!--公共弹出模态框begin-->
<div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width:400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal_del_title">确认信息</h4>
            </div>
            <div class="modal-body">
                <h4 id="modal_common_content"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--公共弹出模态框end--> 
<script type="text/javascript" src="/resources/js/utils/md5.js" ></script>
<script type="text/javascript" src="/resources/js/count/head/login.js"></script>

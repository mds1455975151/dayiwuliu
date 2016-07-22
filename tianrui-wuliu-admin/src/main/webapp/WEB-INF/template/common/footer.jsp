<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.tianrui.service.admin.bean.Users" %>
<%
Users sesionuser = (Users) request.getSession().getAttribute("session_user");
%>
<script type="text/javascript">var navigation = null;</script>
<script type="text/javascript" src="${scriptsRoot }/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${scriptsRoot }/bootstrap.js"></script>
<script type="text/javascript" src="${scriptsRoot }/jquery.resizableColumns.js"></script>
<script type="text/javascript" src="/resources/js/mainmenu/mainmenu.js" ></script>
<script type="text/javascript" src="${scriptsRoot }/wfljs.js"></script>
<script type="text/javascript" src="${scriptsRoot }/cropbox.js"></script>
    
    
	<!--公共弹出模态框begin-->
	<div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <input type="hidden" id="modal_del_id">
	                <input type="hidden" id="modal_del_flag">
	                <input type="hidden" id="modal_del_rowno">
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
                    <p><label>登录账号：</label><span><%= sesionuser.getAccount() %></span></p>
                    <p><label><i style="color: #ff2f00;">*</i>姓名：</label><input maxlength="16" type="text" id="username" value="<%= sesionuser.getUsertype() %>"></p>
                    <p><label><i style="color: #ff2f00;">*</i>手机号：</label><input maxlength="11" type="text" id="usertel" value="<%= sesionuser.getTelnum() %>"></p>
                    <div class="modal_note"  ><h4 id="usermessage"></h4></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updatemyUser()" >确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="closeduser" >取消</button>
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
                    <p><label><i style="color: #ff2f00;">*</i>当前密码：</label><input type="password" id="yuanmima" maxlength="12" placeholder="请输入当前密码" /></p>
                    <p><label><i style="color: #ff2f00;">*</i>新密码：</label><input type="password" id="pass1" maxlength="12" placeholder="请输入新密码"/> </p>
                    <p><label><i style="color: #ff2f00;">*</i>确认密码：</label><input type="password" id="pass2" maxlength="12" placeholder="请确认新密码"/></p>
                    <div class="modal_note"><h4 id="passMessage"></h4></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button"  onclick="updatemyPass()" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="closed" >取消</button>
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
                <h4>您确定要注销此账户吗？</h4>
            </div>
            <div class="modal-footer">
                <button type="submit"   class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--退出end-->
<!--修改密码end-->
<script type="text/javascript">
var password = "<%= sesionuser.getPassword() %>";
</script>